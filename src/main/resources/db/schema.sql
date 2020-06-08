create table if not exists consumer
(
    id      int(11) not null primary key auto_increment,
    name    varchar(100),
    sex     varchar(1),
    mobile  varchar(16),
    address varchar(64)
);


create table if not exists restaurant
(
    id      int(11) not null primary key auto_increment,
    name    varchar(100),
    address varchar(100)
);
create table if not exists `t_order`
(
    id            int(11) not null primary key auto_increment,
    total_price   decimal(10, 2),
    status        varchar(16),
    restaurant_id int(11),
    consumer_id   int(11)

);
create table if not exists order_item
(
    id         int(11) not null primary key auto_increment,
    order_id   int(11),
    goods_name varchar(64),
    price      decimal(10, 2),
    count      int(11)
);
