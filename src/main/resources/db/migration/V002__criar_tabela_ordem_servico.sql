create table ordem_servico(
   id bigint not null auto_increment primary key,
	cliente_id bigint not null,
	descricao text not null,
	preco decimal(10,2),
	status varchar(20) not null, 
	data_abertura datetime not null,
	data_finalizacao datetime,

	foreign key(Cliente_id) references cliente(id)
	)