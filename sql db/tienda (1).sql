-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 04, 2015 at 04:11 AM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `tienda`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `cantidad`(in parametro varchar(50))
begin

select cantidad from producto where nombre= parametro ;

end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insertfactura`()
begin
	
   insert into factura(fecha) values(now());
    

-- insert into facturaproducto(producto, cantidad) values(_producto, _cantidad);
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insertfacturaproducto`(in _producto tinyint ,in _cantidad tinyint )
begin
	
    insert into facturaproducto(producto, cantidad) values(_producto,_cantidad);
    

-- insert into facturaproducto(producto, cantidad) values(_producto, _cantidad);
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insertproducto`(in _nombre varchar(50), in _valor float(9,2),
                    in _cantidad tinyint unsigned)
begin
    insert into producto(nombre, valor,cantidad) values(_nombre,_valor,_cantidad);
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `productos`()
begin

select * from producto;

end$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `factura`
--

CREATE TABLE IF NOT EXISTS `factura` (
  `codigo` tinyint(3) unsigned NOT NULL AUTO_INCREMENT,
  `fecha` datetime DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=44 ;

--
-- Dumping data for table `factura`
--

INSERT INTO `factura` (`codigo`, `fecha`) VALUES
(1, '2015-11-27 00:00:00'),
(2, '2015-11-27 00:00:00'),
(3, '2015-11-27 11:58:06'),
(4, '2015-11-27 11:58:09'),
(5, '2015-11-27 12:53:26'),
(7, '2015-11-27 12:59:08'),
(8, '2015-11-27 13:03:02'),
(9, '2015-11-27 13:03:58'),
(10, '2015-11-27 13:04:20'),
(12, '2015-11-27 13:05:08'),
(13, '2015-11-27 13:15:09'),
(14, '2015-11-27 13:16:31'),
(15, '2015-11-27 13:17:31'),
(16, '2015-11-27 13:20:16'),
(21, '2015-11-27 14:25:16'),
(22, '2015-11-27 14:25:27'),
(23, '2015-11-27 14:52:55'),
(24, '2015-11-27 14:56:12'),
(25, '2015-11-27 14:56:24'),
(26, '2015-11-27 14:57:08'),
(27, '2015-11-27 14:57:36'),
(28, '2015-11-27 14:58:46'),
(29, '2015-11-27 14:58:47'),
(30, '2015-11-27 15:04:27'),
(31, '2015-11-27 15:09:28'),
(32, '2015-11-27 15:11:23'),
(33, '2015-11-27 15:13:59'),
(34, '2015-11-27 15:16:24'),
(35, '2015-11-29 15:25:09'),
(36, '2015-11-29 15:25:21'),
(37, '2015-11-29 16:05:03'),
(38, '2015-11-29 16:05:34'),
(39, '2015-11-29 16:05:48'),
(40, '2015-11-29 16:07:40'),
(41, '2015-11-29 16:07:55'),
(42, '2015-11-29 16:08:04'),
(43, '2015-11-29 16:08:13');

-- --------------------------------------------------------

--
-- Table structure for table `facturaproducto`
--

CREATE TABLE IF NOT EXISTS `facturaproducto` (
  `producto` tinyint(3) unsigned DEFAULT NULL,
  `factura` tinyint(3) unsigned DEFAULT NULL,
  `cantidad` tinyint(3) unsigned DEFAULT NULL,
  KEY `producto` (`producto`),
  KEY `factura` (`factura`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `facturaproducto`
--

INSERT INTO `facturaproducto` (`producto`, `factura`, `cantidad`) VALUES
(3, 25, 10),
(3, 26, 4),
(3, 27, 20),
(1, 28, 1),
(1, 29, 1),
(1, 30, 1),
(2, 30, 1),
(3, 30, 1),
(4, 31, 5),
(1, 32, 2),
(1, 33, 1),
(3, 33, 1),
(3, 33, 5),
(3, 34, 1),
(3, 34, 1),
(3, 34, 1),
(3, 34, 1),
(3, 34, 1),
(4, 38, 7),
(4, 39, 110),
(4, 42, 100),
(4, 43, 33);

--
-- Triggers `facturaproducto`
--
DROP TRIGGER IF EXISTS `resta`;
DELIMITER //
CREATE TRIGGER `resta` BEFORE INSERT ON `facturaproducto`
 FOR EACH ROW begin

set @_viejo=0; -- cantidad 
set @_nuevo=0; -- cantidad vendida
set @_resultado=0; -- rnueva cantidad
set @_codigo=0; -- codigo del producto
set @_nombre='';
set @_codigofactura=0; 
-- insert into factura(fecha) values(now());

set @_codigofactura=(select codigo  from factura order by codigo desc limit 1);
set @_viejo=(select cantidad from producto where codigo= new.producto);
set @_nuevo= new.cantidad;
set @_resultado= @_viejo - @_nuevo;
set @_codigo= new.producto;

update producto set cantidad= @_resultado where codigo=@_codigo ;

set new.factura=@_codigofactura;

end
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `producto`
--

CREATE TABLE IF NOT EXISTS `producto` (
  `codigo` tinyint(3) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  `valor` float(9,2) DEFAULT NULL,
  `cantidad` tinyint(3) unsigned DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `producto`
--

INSERT INTO `producto` (`codigo`, `nombre`, `valor`, `cantidad`) VALUES
(1, 'jabon de baño', 2000.00, 64),
(2, 'galletas de soda', 2500.00, 84),
(3, 'aceite de cocina 1000cm', 5000.00, 34),
(4, 'shampoo sabila', 600.00, 100),
(5, 'jabon de manos', 2400.00, 85);

-- --------------------------------------------------------

--
-- Table structure for table `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `codigo` smallint(5) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `alias` varchar(50) DEFAULT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `nivel` tinyint(4) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  UNIQUE KEY `alias` (`alias`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `usuario`
--

INSERT INTO `usuario` (`codigo`, `alias`, `nombre`, `nivel`, `fecha`) VALUES
(00001, 'cmch05', 'cristian mauricio guerrero', 1, NULL),
(00003, 'usuario1', 'alguien', 3, NULL);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `facturaproducto`
--
ALTER TABLE `facturaproducto`
  ADD CONSTRAINT `facturaproducto_ibfk_1` FOREIGN KEY (`producto`) REFERENCES `producto` (`codigo`),
  ADD CONSTRAINT `facturaproducto_ibfk_2` FOREIGN KEY (`factura`) REFERENCES `factura` (`codigo`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
