
/////////////////////////////////////////////////////////////
triggers login

delimiter //
    drop trigger if exists insert_usuario; //
    create trigger insert_usuario
    after insert on usuario
    for each row
    begin
		insert into historial(usuario,cambio,fecha) values(new.login, concat(
        'usuario agregado: ', new.login ),now());
    
    end //
    delimiter;


delimiter //
    drop trigger if exists delete_usuario; //
    create trigger delete_usuario
    after delete on usuario
    for each row
    begin
		insert into historial(usuario,cambio,fecha) values(old.login, concat(
        'usuario eliminado: ', old.login ),now());
    
    end //
    delimiter;




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


delimiter //
	drop trigger if exists insertar_estado; //
	create trigger insertar_estado 
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
    
    
////////////////////////////////////////////////////
triggers biblioteca    
    

 