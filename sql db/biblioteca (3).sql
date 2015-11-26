-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 26, 2015 at 01:12 PM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `biblioteca`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `buscarlibro`(in parametro varchar(50))
begin
		
        select codigo, nombre, editorial,autor,genero, pais_autor,
			paginas,anno_edicion,precio from libro
			where concat(codigo,' ',nombre,' ', editorial,' ',autor,' ',genero,
            ' ', pais_autor,' ', paginas,' ', anno_edicion,' ',precio)
			like  concat('%', parametro, '%');
	end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `buscarusuario`(in parametro varchar(50))
begin
		
        select 	codigo, nombre, apellido,cedula,domicilio, ciudad,
				departamento,nacimiento from usuario
				where concat(nombre,' ', apellido,' ',cedula,' ',domicilio,
				' ', ciudad,' ', departamento,' ', nacimiento)
                  like  concat('%', parametro, '%');
	end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `nuevolibro`(in _nombre varchar(50), in _editorial varchar(50),
				in _autor varchar(50), in _genero varchar(50), in _pais_autor varchar(50),
                in _paginas smallint, in _anno_edicion date, in _precio float(9,2))
begin
		
        insert into libro(nombre, editorial, autor, genero, pais_autor,
				paginas, anno_edicion, precio) values(_nombre , _editorial ,
				_autor ,_genero , _pais_autor ,
				_paginas ,_anno_edicion , _precio );
            
	end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `nuevousuario`(in _nombre varchar(50), in _apellido varchar(50),
				in _cedula varchar(50), in _domicilio varchar(50), in _ciudad varchar(50),
                in _departamento varchar(50), in _nacimiento date)
begin
		
        insert into usuario() values(null, _nombre , _apellido ,
				_cedula ,_domicilio , _ciudad ,
				_departamento ,_nacimiento);
            
	end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `selectciudad`(in parametro varchar(50))
begin
		
        select c.nombre as nom_cidudad from ciudad as c
        join departamento  as d on d.codigo= c.departamento where d.nombre=parametro;
	end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `selectdepartamento`()
begin
		
        select nombre from departamento;
	end$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `ciudad`
--

CREATE TABLE IF NOT EXISTS `ciudad` (
  `codigo` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  `departamento` smallint(5) unsigned DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  KEY `departamento` (`departamento`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Dumping data for table `ciudad`
--

INSERT INTO `ciudad` (`codigo`, `nombre`, `departamento`) VALUES
(2, 'caicedonia', 1),
(3, 'sevilla', 1),
(4, 'tulua', 1),
(5, 'cali', 1),
(6, 'pereira', 2),
(7, 'manizales', 3),
(8, 'chinchina', 3),
(9, 'armenia', 4),
(10, 'calarca', 4),
(11, 'barcelona', 4);

-- --------------------------------------------------------

--
-- Table structure for table `departamento`
--

CREATE TABLE IF NOT EXISTS `departamento` (
  `codigo` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `departamento`
--

INSERT INTO `departamento` (`codigo`, `nombre`) VALUES
(1, 'valle'),
(2, 'risaralda'),
(3, 'caldas'),
(4, 'quindio');

-- --------------------------------------------------------

--
-- Table structure for table `deuda`
--

CREATE TABLE IF NOT EXISTS `deuda` (
  `serial` smallint(6) NOT NULL AUTO_INCREMENT,
  `pedido` smallint(5) unsigned DEFAULT NULL,
  `codigo_usuario` smallint(5) unsigned NOT NULL DEFAULT '0',
  `codigo_libro` smallint(5) unsigned NOT NULL DEFAULT '0',
  `estado` varchar(50) DEFAULT NULL,
  `dias_mora` smallint(5) DEFAULT NULL,
  `multa` float(9,2) unsigned DEFAULT NULL,
  PRIMARY KEY (`serial`),
  KEY `codigo_libro` (`codigo_libro`),
  KEY `codigo_usuario` (`codigo_usuario`),
  KEY `pedido` (`pedido`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=62 ;

-- --------------------------------------------------------

--
-- Table structure for table `libro`
--

CREATE TABLE IF NOT EXISTS `libro` (
  `codigo` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  `editorial` varchar(50) DEFAULT NULL,
  `autor` varchar(50) DEFAULT NULL,
  `genero` varchar(50) DEFAULT NULL,
  `pais_autor` varchar(50) DEFAULT NULL,
  `paginas` smallint(5) unsigned DEFAULT NULL,
  `anno_edicion` date DEFAULT NULL,
  `precio` float(9,2) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=14 ;

--
-- Dumping data for table `libro`
--

INSERT INTO `libro` (`codigo`, `nombre`, `editorial`, `autor`, `genero`, `pais_autor`, `paginas`, `anno_edicion`, `precio`) VALUES
(1, 'Don Quijote de la Mancha I', 'anaya', 'Miguel de Cervantes', 'Caballeresco', 'España', 517, '1991-01-01', 2750.00),
(2, 'Don Quijote de la Mancha II', 'anaya', 'Miguel de Cervantes', 'Caballeresco', 'España', 611, '1991-01-01', 2750.00),
(3, 'Historias de Nuevo Orleans', 'Alfaguara', 'William Faulkner', 'Novela', 'Estados Unidos', 189, '1996-01-01', 675.00),
(4, 'El Principito', 'Andian', 'Antoine Saint Exupery', 'Aventura', 'Francia', 120, '1996-01-01', 750.00),
(5, 'El Principe', 'S.M.', 'Maquavelo', 'Politico', 'Italia', 210, '1995-01-01', 1125.00),
(6, 'Diplomacio', 'S.M.', 'Henry Kissinger', 'Politico', 'Alemania', 825, '1997-01-01', 1750.00),
(7, 'Los Windsor', 'Paza & Janéz', 'Kitty Kalley', 'Biografías', 'Gran Bretaña', 620, '1998-01-01', 1130.00),
(8, 'El Ultimo Emperador', 'Caralt', 'Pu-Yu', 'Autobiografías', 'China', 353, '1989-01-01', 995.00),
(9, 'Fortuna y Jacinta', 'Paza & Janéz', 'Perez Galdós', 'Novela', 'España', 625, '1984-01-01', 725.00),
(13, 'progamacion con java', 'Thomson Learning', 'Rick Decker , Stuar Hirshfield', 'Educacion', 'USA', 618, '2001-01-01', 2900.00);

-- --------------------------------------------------------

--
-- Table structure for table `prestamo`
--

CREATE TABLE IF NOT EXISTS `prestamo` (
  `pedido` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `codigo_libro` smallint(5) unsigned DEFAULT NULL,
  `codigo_usuario` smallint(5) unsigned DEFAULT NULL,
  `fecha_salida` datetime DEFAULT NULL,
  `fecha_maxima` datetime DEFAULT NULL,
  `fecha_devolucion` datetime DEFAULT NULL,
  PRIMARY KEY (`pedido`),
  KEY `codigo_libro` (`codigo_libro`),
  KEY `codigo_usuario` (`codigo_usuario`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;

--
-- Dumping data for table `prestamo`
--

INSERT INTO `prestamo` (`pedido`, `codigo_libro`, `codigo_usuario`, `fecha_salida`, `fecha_maxima`, `fecha_devolucion`) VALUES
(1, 1, 3, '2015-11-01 00:00:00', '2015-11-15 00:00:00', '2015-11-13 00:00:00'),
(2, 3, 2, '2015-11-03 00:00:00', '2015-11-20 00:00:00', '2015-11-22 00:00:00'),
(3, 2, 5, '2015-11-18 00:00:00', '2015-11-30 00:00:00', '2015-11-25 00:00:00'),
(4, 5, 6, '2015-11-21 00:00:00', '2015-12-03 00:00:00', '2015-12-05 00:00:00'),
(5, 9, 2, '2015-11-21 00:00:00', '2015-12-05 00:00:00', '2015-11-30 00:00:00'),
(6, 2, 4, '2015-11-26 00:00:00', '2015-12-07 00:00:00', '2015-12-01 00:00:00'),
(7, 4, 3, '2015-11-30 00:00:00', '2015-12-07 00:00:00', '2015-12-08 00:00:00'),
(8, 1, 1, '2015-12-01 00:00:00', '2015-12-09 00:00:00', '2015-12-11 00:00:00'),
(9, 3, 6, '2015-12-03 00:00:00', '2015-12-09 00:00:00', '2015-12-09 00:00:00'),
(10, 7, 3, '2015-12-03 00:00:00', '2015-12-18 00:00:00', '2015-12-15 00:00:00'),
(11, 3, 2, '2015-12-05 00:00:00', '2015-12-22 00:00:00', '2015-12-20 00:00:00'),
(12, 7, 2, '2015-11-24 20:55:08', '2015-11-29 20:55:08', '2015-11-27 20:55:08');

--
-- Triggers `prestamo`
--
DROP TRIGGER IF EXISTS `fechasalida`;
DELIMITER //
CREATE TRIGGER `fechasalida` BEFORE INSERT ON `prestamo`
 FOR EACH ROW begin
		set new.fecha_salida= now();
        set new.fecha_devolucion= now()+ interval 3 day;
        set new.fecha_maxima= now()+ interval 5 day;
    
    
    end
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `codigo` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  `apellido` varchar(50) DEFAULT NULL,
  `cedula` varchar(50) DEFAULT NULL,
  `domicilio` varchar(50) DEFAULT NULL,
  `ciudad` varchar(50) DEFAULT NULL,
  `departamento` varchar(50) DEFAULT NULL,
  `nacimiento` datetime DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `usuario`
--

INSERT INTO `usuario` (`codigo`, `nombre`, `apellido`, `cedula`, `domicilio`, `ciudad`, `departamento`, `nacimiento`) VALUES
(1, 'Eva', 'Santana Paex', '2578829', 'Edifici Lares', 'Manizales', 'Caldas', '1980-05-23 00:00:00'),
(2, 'Yolanda', 'Betancour Diaz', '2578829', 'Cr 10-20-20', 'Manizales', 'Caldas', '1976-09-17 00:00:00'),
(3, 'Ines', 'Posada Gil', '3875929', 'Edifici Alpes ap201', 'Pereira', 'Risaralda', '1971-04-07 00:00:00'),
(4, 'Jose', 'Sanchez Pons', '3777929', 'Av Bolivar 2N 40', 'Armenia', 'Quindio', '1966-06-09 00:00:00'),
(5, 'migue', 'Gomez Saez', '73240455', 'Las Colinas casa 20', 'Armenia', 'Quindio', '1976-12-09 00:00:00'),
(6, 'Carlos Mario', 'Marin Duque', '98092261506', 'cr 15 16-43', 'Caicedonia', 'Valle', '1998-09-22 00:00:00'),
(9, 'cristian', 'guerrero', '94463816', 'cr 10-15-66', 'caicedonia', 'valle', '1984-05-05 00:00:00');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `ciudad`
--
ALTER TABLE `ciudad`
  ADD CONSTRAINT `ciudad_ibfk_1` FOREIGN KEY (`departamento`) REFERENCES `departamento` (`codigo`);

--
-- Constraints for table `deuda`
--
ALTER TABLE `deuda`
  ADD CONSTRAINT `deuda_ibfk_1` FOREIGN KEY (`codigo_libro`) REFERENCES `libro` (`codigo`),
  ADD CONSTRAINT `deuda_ibfk_2` FOREIGN KEY (`codigo_usuario`) REFERENCES `usuario` (`codigo`),
  ADD CONSTRAINT `deuda_ibfk_3` FOREIGN KEY (`pedido`) REFERENCES `prestamo` (`pedido`);

--
-- Constraints for table `prestamo`
--
ALTER TABLE `prestamo`
  ADD CONSTRAINT `prestamo_ibfk_1` FOREIGN KEY (`codigo_libro`) REFERENCES `libro` (`codigo`),
  ADD CONSTRAINT `prestamo_ibfk_2` FOREIGN KEY (`codigo_usuario`) REFERENCES `usuario` (`codigo`);

DELIMITER $$
--
-- Events
--
CREATE DEFINER=`root`@`localhost` EVENT `deudor` ON SCHEDULE EVERY 1 DAY STARTS '2015-11-20 20:00:00' ON COMPLETION NOT PRESERVE ENABLE DO begin
			if prestamo.fecha_maxima >now() then
            delete from deuda;
			insert into deuda(pedido,codigo_usuario,codigo_libro,estado,dias_mora,multa) select pedido,
			codigo_usuario, codigo_libro, if(fecha_maxima>now(),'deudor', 'limpio')as estado, 
			datediff(fecha_maxima, now())  as mora,datediff(fecha_maxima, now())*2 as multa from prestamo;
		end if;
    end$$

DELIMITER ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
