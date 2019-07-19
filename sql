create schema cloudDB01 collate utf8mb4_general_ci;

create table dept
(
	deptno bigint auto_increment
		primary key,
	dname varchar(60) null,
	db_source varchar(60) null
);




