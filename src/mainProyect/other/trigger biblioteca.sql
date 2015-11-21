

delimiter //
   drop trigger if exists update_usuario; //
    create trigger update_usuario
    after update on usuario
    for each row
    begin
		insert into historial(usuario,cambio,fecha) values(old.login, concat(
        'actualizacion en: nombre ', new.login,'  contraseña: ',
        new.password,'  estado: ',new.estado,
        '  fecha: ',new.fecha,' nivel: ' , new.nivel ),now());
    
    end //
    delimiter;

//////////////////////////////////////////////////
evento diario revisar deudores s

delimiter //
	set global event_scheduler=on; //
    drop event if exists deudor; //
    create event deudor
    on schedule every 1 day starts '2015-11-20-20-00-00'
    do 
		begin
			if prestamo.fecha_maxima >now() then
            delete from deuda;
			insert into deuda(pedido,codigo_usuario,codigo_libro,estado,dias_mora,multa) select pedido,
			codigo_usuario, codigo_libro, if(fecha_maxima>now(),'deudor', 'limpio')as estado, 
			datediff(fecha_maxima, now())  as mora,datediff(fecha_maxima, now())*2 as multa from prestamo;
		end if;
    end;//	
			
 
 ////////////////////////////////////////////////////////////////////////
 ejemplos consultas
 comment '   
insert into deuda(pedido,codigo_usuario,codigo_libro,estado,dias_mora,multa) select pedido,
	codigo_usuario, codigo_libro, if(fecha_maxima>now(),'deudor', 'limpio')as estado, 
	datediff(fecha_maxima, now())  as mora,datediff(fecha_maxima, now())*2 as multa from prestamo;
    '
comment' mysql> select usuario.nombre as usuario, libro.nombre as nombre_libro from usuario 
        join deuda on deuda.codigo_usuario=usuario.codigo join libro 
        on deuda.codigo_libro=libro.codigo where deuda.estado='limpio';
    '
comment' mysql> select count(*), usuario.nombre as usuario, libro.nombre as
         nombre_libro from usuario join deuda on deuda.codigo_usuario=usuario.codigo 
        join libro on deuda.codigo_libro=libro.codigo where deuda.estado='deudor' group by usuario;
    '
comment ' select count(*), usuario.nombre as usuario, libro.nombre as nombre_libro 
        from usuario join deuda on deuda.codigo_usuario=usuario.codigo join 
        libro on deuda.codigo_libro=libro.codigo where deuda.estado='deudor' 
        group by usuario having count(*)>1;
'
    
	drop trigger if exists update_deuda; //
	create trigger update_deuda 
    before insert on usuario
    for each row
    begin
    if new.fecha <curdate() then
    set new.estado='0';
    elseif new.fecha >curdate() then
    set new.estado='1';
    end if;
    end;//
    
    delimiter;