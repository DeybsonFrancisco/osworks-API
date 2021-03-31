create table funcionario(
	id bigint not null auto_increment primary key,
	nome varchar(255) not null,
	cargo varchar(60) not null,
	senha varchar(60) not null
)