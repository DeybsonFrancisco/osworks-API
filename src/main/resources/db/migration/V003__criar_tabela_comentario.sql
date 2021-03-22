create table comentario(

	id bigint not null primary key auto_increment,

	descricao text not null,

	data_envio datetime not null,

	ordem_servico_id bigint not null,



	foreign key(ordem_servico_id) references ordem_servico(id) ) 