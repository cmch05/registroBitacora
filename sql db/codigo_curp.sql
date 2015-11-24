-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 24, 2015 at 03:04 AM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `codigo_curp`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `inpares`(in n int)
begin
		declare contador int default 0;
        bucle1: while contador <= n do
			if (contador %2 !=0) then
				select contador;
			end if;
		set contador = contador +1;
		end while bucle1;
	end$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `entidad`
--

CREATE TABLE IF NOT EXISTS `entidad` (
  `codigo` varchar(2) NOT NULL DEFAULT '',
  `nombre` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `entidad`
--

INSERT INTO `entidad` (`codigo`, `nombre`) VALUES
('AS', 'AGUASCALIENTES'),
('BC', 'BAJA CALIFORNIA'),
('DF', 'DISTRITO FEDERAL'),
('DG', 'DURANGO'),
('MS', 'MORELOS'),
('NT', 'NAYARIT'),
('SL', 'SINALOA');

-- --------------------------------------------------------

--
-- Table structure for table `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `curp` varchar(50) NOT NULL DEFAULT '',
  `nombre` varchar(20) DEFAULT NULL,
  `apellido1` varchar(20) DEFAULT NULL,
  `apellido2` varchar(20) DEFAULT NULL,
  `sexo` enum('h','m') NOT NULL,
  `nacimiento` date DEFAULT NULL,
  `entidad` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`curp`),
  KEY `entidad` (`entidad`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usuario`
--

INSERT INTO `usuario` (`curp`, `nombre`, `apellido1`, `apellido2`, `sexo`, `nacimiento`, `entidad`) VALUES
('FAXA870222MDFLXN54', 'anacheta', 'fallez', NULL, 'm', '1987-02-22', 'df'),
('MERF971212HDGNDB64', 'fabian', 'mendez', 'rodriguez', 'h', '1997-12-12', 'dg');

--
-- Triggers `usuario`
--
DROP TRIGGER IF EXISTS `curp1`;
DELIMITER //
CREATE TRIGGER `curp1` BEFORE INSERT ON `usuario`
 FOR EACH ROW begin
	-- declare nom varchar(20) ;
    -- declare ap1 varchar(20);
	   declare ap2 varchar(20) ; -- default 'x';
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
    declare aleatorio1 varchar(1); -- dos numeros aleatoreos
    declare aleatorio2 varchar(1);
	-- ---------------------------------------
    declare dia varchar(2);
    declare mes varchar(2);
    declare año varchar(2);
    -- ----------------------------
    declare i int(2) default 1; -- contador
	set ap2 =new.apellido2; -- variable apellido 2 
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
    
    if ap2 is null then 
		set l3= 'x'; -- x sin  apellido2 es null
    else
		set l3= substring(ap2, 1,1);	-- primera letra apellido 2						
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
    set i = 2; --  set el contador
    bu2: while i< length(new.apellido1) do
        if(substring(new.apellido1,i,1)!='a'&& substring(new.apellido1,i,1)!='e'
			&& substring(new.apellido1,i,1)!='i' && substring(new.apellido1,i,1)!='o'
            &&substring(new.apellido1,i,1)!='u')  	then
            set l14 =substring(new.apellido1,i,1); -- primera consonante interna apellido paterno
            leave bu2;
		end if;
        set i = i+1;
    end while bu2 ;
    
    set i = 2; -- set el contador
    if ap2 is null then
		set l15= 'x'; -- x sin  apellido2 es null
    else
		bu3: while i< length(ap2) do
			if(substring(ap2,i,1)!='a'&& substring(ap2,i,1)!='e'
				&& substring(ap2,i,1)!='i' && substring(ap2,i,1)!='o'
				&&substring(ap2,i,1)!='u')  	then
				set l15 =substring(ap2,i,1); -- primera consonante interna apellido materno
				leave bu3;
			end if;
            set i = i+1;
		end while bu3;
									
    end if ;
    
    set i = 2; -- set el contador
	bu4: while i< length(new.nombre) do
        if(substring(new.nombre,i,1)!='a'&& substring(new.nombre,i,1)!='e'
			&& substring(new.nombre,i,1)!='i' && substring(new.nombre,i,1)!='o'
            &&substring(new.apellido1,i,1)!='u')  	then
            set l16 =substring(new.nombre,i,1); -- primera consonante interna nombre
            leave bu4;
		end if;
        set i = i+1;
    end while bu4 ;
   set aleatorio1= floor(rand()*10);
   set aleatorio2= floor(rand()*10);
   set new.curp=upper(concat(l1,l2,l3,l4,año,mes,dia,l11,cod,l14,l15,l16,aleatorio1,aleatorio2));
   

end
//
DELIMITER ;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`entidad`) REFERENCES `entidad` (`codigo`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
