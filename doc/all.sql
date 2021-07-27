create table `test` (
                        `id` bigint not null comment 'id',
                        `name` varchar(50) comment 'name',
                        `password` varchar(50) comment 'password',
                        primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='test'



-- user table
drop table if exists `user`;
create table `user` (
                        `id` bigint not null comment 'ID',
                        `login_name` varchar(50) not null comment 'login name',
                        `name` varchar(50) comment 'nick name',
                        `password` char(32) not null comment 'password',
                        primary key (`id`),
                        unique key `login_name_unique` (`login_name`)
) engine=innodb default charset=utf8mb4 comment='user';

insert into `user` (id, `login_name`, `name`, `password`) values (1, 'test', 'test', 'e70e2222a9d67c4f2eae107533359aa4');