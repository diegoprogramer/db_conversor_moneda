-- creacion bd abogados...
# create database abogados;
# use abogados;
# create table abogado (idabogado varchar(3) primary key,nombre varchar(25));
 create table municipio(idmunicipio varchar(2),descripcion varchar(55));
 select * from abogado;
 select * from municipio;
 delete from municipio where idmunicipio="4";
 alter table municipio add constraint primary key(idmunicipio);
 alter table abogado add constraint primary key (idabogado);
 -- creacion de moneda de conversion
 create table conversion(id integer(3),valor double,fecha datetime);
 alter table conversion add constraint primary key (id);

-- insertar conversiones
insert into  conversion(valor,fecha) values(7000,'2000-02-01'),
(8000,'2007-03-03'),(5000,'2020-02-03');

select * from conversion order by fecha desc;

select fecha from conversion  where valor = 3000 order by fecha desc;
describe conversion;

insert into conversion(valor,fecha) values (16000,'2030-2-12')
,(14000,'2028-03-12');

SELECT  max(fecha) FROM conversion WHERE valor in (select distinct valor from conversion)GROUP BY valor ORDER BY valor  DESC;

select distinct valor from conversion;
select distinct valor from conversion;

-- SEA LA TABLA CONVERSION CON LOS CAMPOS VALOR  Y FECHA
-- ENTONCES PARA OBTENER LA FECHA MAS RECIENTE POR VALOR SE TIENE  
select valor, max(fecha) as fecha from conversion group by valor order by valor desc;
-- Y LISTO


delete from conversion where fecha<'2020-10-01';
select * from conversion where valor = 6000;
