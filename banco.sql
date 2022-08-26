create database `banco`;

use `banco`;

create table `gerente` (
                         `idgerente` int not null auto_increment primary key,
                         `nome` varchar(40) not null
);
create table `cliente` (
                         `idcliente` int not null auto_increment primary key,
                         `nome` varchar(40) not null,
                         `cpf` int not null
);
create table `conta_corrente` (
                                `idconta` int not null auto_increment primary key,
                                `idgerente` int not null,
                                `idcliente` int not null,
                                `numero`  int not null,
                                `saldo` double default 0.0,
                                `limiteTotal` double not null default 0,
                                `limiteUtilizado` double not null  default 0,
                                `tipo` char not null default 's',
                                `ativo` boolean not null default true,
                                constraint `fkgerenteconta`
                                    foreign key(idgerente) references gerente(idgerente),
                                constraint `fkclienteconta`
                                    foreign key(idcliente) references cliente(idcliente)
);
create table `conta_poupanca` (
                                `idpoupanca` int not null auto_increment primary key,
                                `idgerente` int not null,
                                `idcliente` int not null,
                                `numero` int not null,
                                `saldo` double default 0.0,
                                `ativo` boolean not null default true,
                                `dtrendimento` date,
                                constraint `fkgerentepoupanca`
                                    foreign key(idgerente) references gerente(idgerente),
                                constraint `fkclientepoupanca`
                                    foreign key(idcliente) references cliente(idcliente)
);
create table `tipoconta` (
                           `tipo` varchar(1) not null primary key,
                           `descricao` varchar(40)
);
create table `tipotransacao` (
                               `tipo` char not null primary key,
                               `descricao` varchar(40)
);
create table `movimentacao` (
                              `idmovimentacao` int not null auto_increment primary key,
                              `idconta` int not null,
                              `datamovimentacao` date,
                              `tipoconta` varchar(1) not null,
                              `valor` double not null,
                              `tipotransacao` varchar(1) not null,
                              constraint `fkidtipoconta`
                                  foreign key(tipoconta) references tipoconta(tipo),
                              constraint `fkidtipotransacao`
                                  foreign key(tipotransacao) references tipotransacao(tipo)
);

insert into tipoconta(tipo, descricao) values (1, 'poupanca'), (2, 'corrente');

insert into tipotransacao(tipo, descricao) values (1, 'saque'), (2, 'deposito');

delimiter $$
create trigger `tgmovimentacaocp`
    after update on conta_poupanca
    for each row
begin
    if(new.saldo > old.saldo) then
		insert into movimentacao (idconta, datamovimentacao, tipoconta, valor, tipotransacao) values
		(old.idpoupanca, curdate(),1,new.saldo - old.saldo,2);
    else
		insert into movimentacao (idconta, datamovimentacao, tipoconta, valor, tipotransacao) values
		(old.idpoupanca, curdate(),1,old.saldo - new.saldo,1);
end if;
end$$

create trigger `tgmovimentacaocc`
    after update on conta_corrente
    for each row
begin
    if(new.saldo > old.saldo) then
		insert into movimentacao (idconta, datamovimentacao, tipoconta, valor, tipotransacao) values
		(old.idconta, curdate(),2,new.saldo - old.saldo,2);
    else
		insert into movimentacao (idconta, datamovimentacao, tipoconta, valor, tipotransacao) values
		(old.idconta, curdate(),2,old.saldo - new.saldo,1);
end if;
end$$
delimiter ;