delimiter //
drop trigger if exists curp1; //
create trigger curp1
before insert on usuario
for each row
begin
	-- declare nom varchar(20) ;
    -- declare ap1 varchar(20);
    -- declare ap2 varchar(20);
    -- declare sex varchar(1);
    -- declare nacim date;
    -- declare entid varchar (20);
    -- -------------------------------
    declare l1 varchar(1); 		-- primera letra apellido paterno
    declare l2 varchar(1); 		-- primera vocal apellido paterno
    declare l3 varchar(1);		-- primera letra apellido materno o X
    declare l4 varchar(1); 		-- primera letra  nombre
    -- declare fecha varchar(6); -- dos ultimos digitos del año dos digitos del dia dos digitos del mes
    declare l11 varchar(1); 	-- sexo
    declare cod varchar(2); 	-- codigo entidad federal 
    declare l14 varchar(1); -- primera consonante interna apellido paterno
    declare l15 varchar(1); -- primera consonante interna apellido materno
    declare l16 varchar(1); -- primera consonante interna apellido nombre
    declare aleatorio varchar(2); -- dos numeros aleatoreos
	-- ---------------------------------------
    declare dia varchar(2);
    declare mes varchar(2);
    declare año varchar(2);
    -- ----------------------------
    declare i int(2) default 1; -- contador
    -- ---------------------------------
    set año = substring(year(new.nacimiento),3,2); 		-- año nacimiento
    set l1= substring(new.apellido1, 1,1); 	-- primera letra apellido 1
    set l11 = new.sexo;						-- sexo
    set l4= substring(new.nombre, 1,1); 	-- primera letra nombre  OJO arrreglar para nombre compuesto
    set cod= new.entidad;
    -- ---------------------------------------
    
	if month(new.nacimiento)<10 then
		set mes= concat('0', month(new.nacimiento));  	-- mes nacimiento
    else
		set mes= month(new.nacimiento); 		-- mes nacimiento
    end if;
      if day(new.nacimiento)<10 then
		set dia= concat('0', day(new.nacimiento));		-- dia nacimiento
    else
		set dia= day(new.nacimiento);			-- dia nacimiento
    end if ;
    
    if new.apellido2=null then
		set l3= 'x';							-- x sin  apellido2 es null
    else
		set l3= substring(new.apellido2, 1,1);	-- primera letra apellido 1
    end if ;
    
    
    bu1: while i< length(new.apellido1) do
		if(substring(new.apellido1,i,1)='a'||substring(new.apellido1,i,1)='e'
			||substring(new.apellido1,i,1)='i'||substring(new.apellido1,i,1)='o'
            ||substring(new.apellido1,i,1)='u')  	then
            set l2 =substring(new.apellido1,i,1); -- primera vocal apellido paterno
            leave bu1;
		end if;
        set i = i+1;
    end while bu1 ;
    set i = i+0;
    bu2: while i< length(new.apellido1) do
        if(substring(new.apellido1,i,1)!='a'&& substring(new.apellido1,i,1)!='e'
			&& substring(new.apellido1,i,1)!='i' && substring(new.apellido1,i,1)!='o'
            &&substring(new.apellido1,i,1)!='u')  	then
            set l14 =substring(new.apellido1,i,1); -- primera consonante interna apellido paterno
            leave bu2;
		end if;
        set i = i+1;
    end while bu2 ;
    
    
    if new.apellido2=null then
		set l15= 'x';							-- x sin  apellido2 es null
    else
		set i = i+0;
		bu3: while i< length(new.apellido2) do
			if(substring(new.apellido2,i,1)!='a'&& substring(new.apellido2,i,1)!='e'
				&& substring(new.apellido2,i,1)!='i' && substring(new.apellido2,i,1)!='o'
				&&substring(new.apellido2,i,1)!='u')  	then
				set l15 =substring(new.apellido2,i,1); -- primera consonante interna apellido materno
				leave bu3;
			end if;
            set i = i+1;
		end while bu3;
    
    end if ;
    
    set i = i+0;
	bu4: while i< length(new.nombre) do
        if(substring(new.nombre,i,1)!='a'&& substring(new.nombre,i,1)!='e'
			&& substring(new.nombre,i,1)!='i' && substring(new.nombre,i,1)!='o'
            &&substring(new.apellido1,i,1)!='u')  	then
            set l16 =substring(new.nombre,i,1); -- primera consonante interna nombre
            leave bu4;
		end if;
        set i = i+1;
    end while bu4 ;
   set aleatorio= floor(rand()*100);
   
   
   set new.curp=upper(concat(l1,l2,l3,l4,año,mes,dia,l11,cod,l14,l15,l16,aleatorio));
   

end; //