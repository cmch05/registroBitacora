/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  cmch05
 * Created: 19-nov-2015
 */

delimiter //
	drop trigger if exists insertar_estado;//
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
    
    
    delimiter //
    
    create trigger update_usuario
    after update on usuario
    for each row
    begin
		insert into historial values(null,new.login, concat('actualizacion : ', new.login,' ', new.password,' ',new.estado,
        ' ',new.fecha,' ' , new.nivel ),now());
    
    end //
    delimiter;
    
        
	