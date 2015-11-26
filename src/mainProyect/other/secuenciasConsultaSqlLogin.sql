-- consultas login ------------------ 

delimiter //
drop procedure if exists login; //
create procedure login(in _usr varchar(50), in _pass varchar(50))
	begin
		
        set @_login ='onomatopeya';
        set @_password ='onomatopeya';
        set @_estado ='';
        set @_fecha ='';
        set @_nivel ='';
        set @_respuesta='';
        
        select login  into
				@_login
				from usuario where login = _usr; -- fecha >= curdate() and 
				-- concat('md5(',_pass,')') ; -- and estado = true;
		
        select pass ,estado ,fecha,nivel  into
				@_password,@_estado,@_fecha,@_nivel
				from usuario where -- fecha >= curdate() and 
				pass= md5(_pass) and login = _usr;-- concat('md5(',_pass,')') ; -- and estado = true;
        
        if @_login ='onomatopeya' then
			set @_respuesta='usuario_incorrecto';
		else
			if @_password ='onomatopeya' then
				set @_respuesta='contrasaña_incorrecta';
			else
				if @_estado=false then
					set @_respuesta='sin_permiso';
					else
						if curdate()>@_fecha then
							set @_respuesta='Fecha_caduca';
						else
							set @_respuesta='usuario_aceptado';
						end if;
				end if;
			end if;
		end if;
        
        select @_respuesta, @_nivel;
	end; //
    --  ------------------------------------------------------------------
    delimiter //
    drop procedure if exists cambiointerface; //
    create procedure cambiointerface(in _usr varchar(20))
    begin
    
    insert into bitacora(login,fecha_ingreso,hora_ingreso)
                values(_usr,curdate(),curtime());
    
    end; //

