graphQL
前端工程师又可以称为人机交互工程师，其主要关注UI模型，后端工程师更加关注业务模型，前后端的模型不匹配产生了mvc这一经典模式。
而mvc中的C即controller, 可能很多人没注意到，它是属于前端却由往往由后端来开发和维护的。
在某种程度上，grqphQL的出现代表了后端让渡了部分controller层的开发权限这一趋势，后端就可以专注于深耕业务相关的领域服务。
事实上，一个客户端的请求往往需要组合多个领域服务的数据，如查询订单详情可能需要订单服务，商品服务，支付服务，快递服务等等，在单体时代这些都可以直接在事务脚本（Martin fowler《企业应用架构模式）中完成，而在微服务风行的当下，考虑到客户端与微服务之间往往通过速率不高的互联网进行交互，就不能不考虑到延迟的影响。还有一个问题，不同的客户端可能需要不同的API，如手机端需要的数据往往没有PC端需要的数据详尽，第三方开发者对接口的稳定性有非常强的需求。事实证明，给所有的客户端提供一个大而全的API往往不是一个好主意。以上两个问题催生了APIgateway模式（Chris Richardson《微服务架构设计模式》). 而graphQL算是这一模式的有益探索和实践。
graphQL（APIgateway）在软件架构当中的位置是客户端与服务端提供的领域服务之间，作为一个独立的组件包揽了路由转发，api组合，协议转换（如果有，如http转grpc），可能还包含一些边缘功能（如身份安全验证）等功能。
graphQL是怎么解决前面提到的两个问题呢，针对一个客户端请求往往需要组合多个领域服务的数据这一问题，应尽可能的并发执行调用，但并发的代码相较于单线程同步代码有比较高的复杂度，所以graphQL的实现通常使用相对友好的响应式声明式的编程模型帮助开发者完成这方面的开发。第二个问题则是客户端可通过查询文档（query document后面介绍）自行指定要获取哪些数据，好像客户端能通sql定制自己的api一样（严格来说只能定制select子句的部分），而服务端不需要为此编写额外的代码。这也符合面向对象设计的接口隔离原则（接口属于客户，不应让客户依赖于它不需要的接口）。
需要说明的是，graphQL跟sql一样是一个标准而不是一个实现，如java，node都有自己的graphQL的实现，当然，他们在概念上是一致共通的。大致说来，graphQL主要包含了schema(指定服务器的数据模型和可支持的查询），解析器函数（将schema中定义的元素映射到各种后端微服务接口即数据源），查询文档。

graphQL顾名思义，是基于图(graph)的查询语言(query language)。这个图是什么呢？在离散数学中图是由一些端点与这些端点之间的联系构成的。但你也可以简单理解为图是对象数据库的schema，图中的端点是对象，对象之间的联系用对象包含的对象来表达。而graphQL就是这个对象数据库的查询语言。这个对象数据库的数据源又是什么？就是领域服务（如通过restful,rpc)所能提供的业务数据的建模，两者（schema与数据源）通过解析器连接起来。如groovy语言中javabean所做的那样，每一个类中声明字段背后都有一个编译器自动生成的getter方法给用户提供访问途径。在graphQL中，服务者在schema中声明一个个类型和类型中的字段以及支持的操作（操作类型包括查询，变更，注册），使用者（graphQL客户端）通过指明要做的操作（schema提供的操作可视为命名化的sql语句）传入相应的参数（亦如sql的参数），指定要检索的数据（如select子句中的字段），执行引擎（这里是graphQL的解析器）会通过代理对象（可选，也可能直接rest调用相关服务）执行相关服务的远程调用并组合数据返回给客户端。

如下是一个schema:

type Query{
    orders(consumerId:Int!):[Order]   //返回指定consumer的所有订单信息，中括号代表数组，惊叹号代表该参数必填。
    order(orderId:Int!):Order           //返回指定的Order
    consumer(consumerId:Int!):Consumer  //返回指定的consumer信息
}
type Consumer{
    id:ID
    firstName:String
    lastName:String
    orders:[Order]
}
type Order{
    orderId:ID
    consumerId:Int
    consumer:Consumer
    payMethod:PayMethods
}
enum PayMethods{
    WECHAT_PAY
    ALIPAY
    PAYPAL
}
以上schema定义了三个支持的查询，分别是orders(),order(),consumer().其它的都是数据模型的定义，跟java中的对象定义有很大的相似度（所以我说它像一个对象数据库的schema）。

graphQL提供数据模型（schema)和支持的查询修改等操作,客户端就根据schema模型指定自己需要的返回字段（通过类似于http://localhost:3000/graphql?query={order(consumerId:1){id,firstName,lastName,orders{orderId, payMethod}}})，一般的GraphQL的实现会提供便利的client来执行这种查询请求，它还会负责正确的格式化请求。
{
    consumer(consumerId:1) {
        id,
        fistName,
        lastName,
        
        orders {
            orderId,
            payMethod
        }
    }
}
以上这部分在graphQL中被称为查询文档，可以类比于一个被实例化的sql语句，sql被命名为consumer（通过schema),形参为cusumerId,实参为1,要返回的数据是一个用json描述的对象，客户端只提供要执行的操作名与需要的返回数据，其它都包含在graphQL的的schema里。所以本质上graphQL要做的是一个超级接口，客户端只需要调用一个graph接口即可，类似于spring mvc中的前端控制器，所以graphQL在这一方面又可说是后端前置模式（backends for frontend即BFF）的实践者。
然后是解析器，一般来说解析器是一个函数，绑定到schema中的某个对象（或对象中字段）上，用以消费相关的领域服务。如
function resolveConsumer(parent,args, context){             //每一个解析器都可以获取它的父字段所代表的对象中的标量值，因为它极可能依赖于父对象信息来查询自身信息。同时还能从上下文对象（如果是spring则直接从容器中取）获取领域服务的代理对象。
    return context.consumerServiceProxy.findConsumer(args.consumerId);
}
function resolveOrders(consumer,args,argscontext){
    return context.orderServiceProxy.findOrdersByCusumerId(consumer.consumerId);
}


为性能计，可以在graphQL的实践中使用批处理和缓存来优化负载。批处理就是将多个单次请求（如在查询嵌套的订单列表时）合并成一次请求，也可以使用redis等技术将当前用户的可能会重复执行请求缓存起来。

现在大家对graphQL出现的背景和它解决问题所使用的方法有了一些感性的认识，下面我们来看一些可运行的代码。

