INSERT INTO consumer (id,name,sex,mobile,address) VALUES(
1,'赵一','M','12345678910','河南省开封县');
INSERT INTO consumer (id,name,sex,mobile,address) VALUES(
2,'赵二','M','13366107293','河南省尉氏县');
INSERT INTO consumer (id,name,sex,mobile,address) VALUES(
3,'赵三','F','13382823323','北京市海滨区');

insert into `t_order` (id,consumer_id, total_price, status, restaurant_id) values(
1, 2,'33', '2', 1
);
insert into `t_order`(id,consumer_id, total_price, status, restaurant_id) values(
2, 2,'20', '2', 1
);
insert into `t_order` (id,consumer_id, total_price, status, restaurant_id) values(
3, 2,'40', '2', 1
);
insert into order_item (id,order_id, goods_name, price, count) values(
1, 1,'白粥', '12', 1
);
insert into order_item (id,order_id, goods_name, price, count) values(
2, 1,'包子', '3', 3
);
insert into restaurant (id,name, address) values (
1, '白状元餐厅','北京市海淀区苏州街白状元餐椅'
);