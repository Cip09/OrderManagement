/*
drop table if exists `order`;
drop table if exists `product`;
drop table if exists `client`;
*/

drop table if exists `client`;


create table if not exists `client`(
`id` int(10) not null auto_increment,
`name` varchar(40) default null,
`age` int(10) default null,
primary key (id)
);

insert into `client` values (2,'Marcu',22);



create table if not exists `product`(
`id` int(12) not null auto_increment,
`name` varchar(12) default null,
`quantity` int(11) default null,
`pricePerUnit` int(11) default null,
primary key (`id`)
);





create table if not exists `order`(
`idOrder` int(11) not null,
`idClient` int (12)  default null,
`idProduct` int(12)  default null,
primary key (`idOrder`),
key index1 (`idClient`),
key fk_products_idx (`idProduct`),
constraint `fk_clients` foreign key (`idClient`) references `client` (`id`) on delete no action on update no action,
constraint `fk_products` foreign key (`idProduct`) references `product` (`id`) on delete no action on update no action
);


select * from client;



