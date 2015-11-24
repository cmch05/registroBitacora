-- /////////////
-- ejemplo de while
delimiter //
drop procedure if exists inpares; //
create procedure inpares(in n int)
	begin
		declare contador int default 0;
        bucle1: while contador <= n do
			if (contador %2 !=0) then
				select contador;
			end if;
		set contador = contador +1;
		end while bucle1;
	end; //
            
call inpares(5);                
                
-- codigo curp 
--  select floor(rand()*100);
-- select round(rand()*100,10);
--  select substring(year(curdate()),3,2);               
delimiter //
drop trigger if exists curp; //
create trigger curp
before insert on usuario
for each row
begin
	declare nom varchar(20) default '' ;
    declare ap1 varchar(20) default '';
    declare ap2 varchar(20) default '';
    declare sex varchar(1) default '';
    declare nacim date;
    declare entid varchar (20) default '';
    -- -------------------------------
    declare l1 varchar(1) default ''; -- primera letra apellido paterno
    declare l2 varchar(1) default ''; -- primera vocal apellido paterno
    declare l3 varchar(1) default ''; -- primera letra apellido materno o X
    declare l4 varchar(1) default ''; -- primera letra  nombre
    declare fecha varchar(6) default ''; -- dos ultimos digitos del año dos digitos del dia dos digitos del mes
    declare l11 varchar(1) default ''; -- sexo
    declare codigo varchar(2) default ''; -- codigo entidad federal 
    declare l14 varchar(1) default ''; -- primera consonante interna apellido paterno
    declare l15 varchar(1) default ''; -- primera consonante interna apellido materno
    declare l16 varchar(1) default ''; -- primera consonante interna apellido nombre
    declare aleatorio varchar(2) default ''; -- dos numeros aleatoreos
	-- ---------------------------------------
    declare dia varchar(2) default '';
    declare mes varchar(2) default '';
    declare año varchar(2) default '';
    -- ----------------------------
    set año = year(new.nacimiento); 		-- año nacimiento
    set l1= substring(new.apellido1, 1,1); 	-- apellido 1
    -- ---------------------------------------
    
	if (month(new.nacimiento)<10) then
		set mes= '0'+ month(new.nacimiento);  	-- mes nacimiento
    else
		set mes= month(new.nacimiento); 		-- mes nacimiento
    end if;
    
    comment '
    if day(new.nacimiento)<10 then
		set dia= '0'+ day(new.nacimiento);
    else
		set dia= day(new.nacimiento);
    end if ;
    '
    
    
    declare i int default 0;
    bu1: while i< length(new.apellido1) do
		if(substring(new.apellido1,i,1)='a'||substring(new.apellido1,i,1)='e'
			||substring(new.apellido1,i,1)='i'||substring(new.apellido1,i,1)='o'
            ||substring(new.apellido1,i,1)='u')  	then
            set l2 =substring(new.apellido1,i,1);
            break;
		end if;
    
    end while bu1 ;
    
    

end; //



-- estados
comment '
            
            
            "BAJA CALIFORNIA SUR","NUEVO LEON","CAMPECHE","OAXACA",
            "CHIAPAS","PUEBLA","CHIHUAHUA","QUERETARO","COAHUILA","QUINTANA ROO",
            "COLIMA","SAN LUIS POTOSI",
            
            
            "SONORA","GUANAJUATO","TABASCO","GUERRERO","TAMAULIPAS","HIDALGO",
            "TLAXCALA","JALISCO","VERACRUZ","MEXICO","YUCATÁN",
            "MICHOACAN","ZACATECAS"
        
        insert into entidad() values("DF","DISTRITO FEDERAL"), ("SL","SINALOA"),("DG","DURANGO");
            
            "BS","NL","CC","OC","CS","PL","CH","QT",
            "CL","QR","CM","SP",
            "SR","GT","TC","GR","TS",
            "HG","TL","JC","VZ","MC","YN","MN","ZS"
            
            
            "DF","SL","DG","AS","MS","BC","NT",
			
            "DISTRITO FEDERAL","SINALOA","DURANGO",
            "AGUASCALIENTES","MORELOS","BAJA CALIFORNIA","NAYARIT",
'





CREATE DEFINER=`root`@`localhost` PROCEDURE `add_userblock`(IN user INT(11), IN blk INT(11))
BEGIN
    DECLARE blockNum INT DEFAULT -1;
    DECLARE entryExists INT DEFAULT 0;

    -- Determine how many blocks you can add
    SELECT addCount INTO blockNum
        FROM block
        WHERE blockId = blk;

    -- Determine if the block already exists for the user
    SELECT COUNT(*) INTO entryExists
        FROM userblock
        WHERE blockId = blk AND userId = user;

    IF entryExists = 0
    THEN
        -- This is a new entry
        INSERT INTO userblock (userId, blockId, num) VALUES (user, blk, blockNum);
        SELECT LAST_INSERT_ID() as 'id';
    ELSE
        -- This is an existing entry
        UPDATE userblock
            SET num = (num + blockNum)
            WHERE userId = user AND blockId = blk;
        SELECT LAST_INSERT_ID() as 'id';
    END IF;
END