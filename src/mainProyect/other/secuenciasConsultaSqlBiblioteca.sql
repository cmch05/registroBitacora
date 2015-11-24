delimiter //
drop procedure if exists buscarusuario; //
create procedure buscarusuario(in parametro varchar(50))
	begin
		
        select 	codigo, nombre, apellido,cedula,domicilio, ciudad,
				departamento,nacimiento from usuario
				where concat(nombre,' ', apellido,' ',cedula,' ',domicilio,
				' ', ciudad,' ', departamento,' ', nacimiento)
                  like  concat('%', parametro, '%');
	end; //
-- ------------------------------------------------------------------------------    
 
 delimiter //
drop procedure if exists buscarlibro; //
create procedure buscarlibro(in parametro varchar(50))
	begin
		
        select codigo, nombre, editorial,autor,genero, pais_autor,
			paginas,anno_edicion,precio from libro
			where concat(codigo,' ',nombre,' ', editorial,' ',autor,' ',genero,
            ' ', pais_autor,' ', paginas,' ', anno_edicion,' ',precio)
			like  concat('%', parametro, '%');
	end; //
-- --------------------------------------------------------------------------------
 delimiter //
drop procedure if exists nuevolibro; //
create procedure nuevolibro(in _nombre varchar(50), in _editorial varchar(50),
				in _autor varchar(50), in _genero varchar(50), in _pais_autor varchar(50),
                in _paginas smallint, in _anno_edicion date, in _precio float(9,2))
	begin
		
        insert into libro(nombre, editorial, autor, genero, pais_autor,
				paginas, anno_edicion, precio) values(_nombre , _editorial ,
				_autor ,_genero , _pais_autor ,
				_paginas ,_anno_edicion , _precio );
            
	end; //
-- ----------------------------------------------------------------------------------------

 delimiter //
drop procedure if exists nuevousuario; //
create procedure nuevousuario(in _nombre varchar(50), in _apellido varchar(50),
				in _cedula varchar(50), in _domicilio varchar(50), in _ciudad varchar(50),
                in _departamento varchar(50), in _nacimiento date)
	begin
		
        insert into usuario() values(null, _nombre , _apellido ,
				_cedula ,_domicilio , _ciudad ,
				_departamento ,_nacimiento);
            
	end; //
    -- ------------------------------------------------------------
    
     delimiter //
drop procedure if exists selectdepartamento; //
create procedure selectdepartamento()
	begin
		
        select nombre from departamento;
	end; //
    -- ----------------------------------------------------------
         delimiter //
drop procedure if exists selectciudad; //
create procedure selectciudad(in parametro varchar(50))
	begin
		
        select c.nombre as nom_cidudad from ciudad as c
        join departamento  as d on d.codigo= c.departamento where d.nombre=parametro;
	end; //
    
-- ----------------------------------------------------------------------------------------
--  select c.nombre as nom_cidudad from ciudad as c  join departamento  as d on d.codigo= c.departamento where d.nombre='valle';
    -- llamar el procedimiento 
   -- call biblioteca.buscarlibro('manc');
   -- call biblioteca.buscarlibro('san');
   -- call biblioteca.nuevolibro('progamacion con java', 'Thomson Learning', 'Rick Decker , Stuar Hirshfield', 'Educacion', 'USA', 618, '2001', 2900);
   -- call biblioteca.nuevousuario('cristian', 'guerrero', '94463816', 'cr 10-15-66', 'caicedonia', 'valle', '1984-05-05');