--insert into tipo_lembrete(nome) values ('Aniversario','Retorno de saldo','Ligar para cliente','Atender cliente','Verificar status proposta','Outros'); 

--insert into tipo_documento(nome) values ('RG/CPF','Comprovante de residência','Comprovante de banco','Extrato'); 

--insert into tipo_operacao(nome) values ('Portabilidade','Refin','Cartão'); 

--insert into status_contrato(nome) values ('Pago','Em andamento','REP','Cancelado'); 

--insert into banco(nome) values ('Safra','Bradesco','OLE','Banco do Brasil','ITAÚ','PAN','Banrisul'); 

insert into perfil(nome) values ('Admin'),('Gerente'),('Funcionario'),('Supervisor'); 
insert into usuario(login,senha,ativo,admin, perfil_id) values ('moniqueluiza','mon2812',1,1,1);
insert into status_contrato(nome) values ('EM ANDAMENTO'),('PAGO'),('CANCELADO');

