本项目使用h2内存数据库，本地即可运行。
下面是通过postman访问本项目http://localhost:9999/graphql,记得把请求头设置为application/json
#查询
{
	"operationName":null,
	"variables":{},
	"query":"{ order(id:\"1\"){  id \n totalPrice\n restaurant{name,address} }}"
	
}
{
	"operationName":null,
	"variables":{},
	"query":"{ orderOfConsumer(consumerId:\"2\"){  id \n totalPrice\n restaurant{name,address} }}"
	
}
#下订单
{
	"operationName":null,
	"variables":{"orderInput":{"restaurantId":1}},
	"query":"mutation placeOrder($orderInput:OrderInput){ placeOrder(orderInput:$orderInput){  id \n totalPrice\n restaurant{name,address} }}"
	
}
#更新订单
{
	"operationName":null,
	"variables":{"orderInput":{"restaurantId":1,"totalPrice":55}},
	"query":"mutation updateOrder($orderInput:OrderInput){ updateOrder(orderInput:$orderInput){  id \n totalPrice\n restaurant{name,address} }}"
	
}