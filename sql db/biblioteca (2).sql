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
-- Database: `biblioteca`
--

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

--
-- Dumping data for table `deuda`
--

INSERT INTO `deuda` (`serial`, `pedido`, `codigo_usuario`, `codigo_libro`, `estado`, `dias_mora`, `multa`) VALUES
(32, 1, 3, 1, 'limpio', -5, 0.00),
(33, 2, 2, 3, 'limpio', 0, 0.00),
(34, 3, 5, 2, 'deudor', 10, 20.00),
(35, 4, 6, 5, 'deudor', 13, 26.00),
(36, 5, 2, 9, 'deudor', 15, 30.00),
(37, 6, 4, 2, 'deudor', 17, 34.00),
(38, 7, 3, 4, 'deudor', 17, 34.00),
(39, 8, 1, 1, 'deudor', 19, 38.00),
(40, 9, 6, 3, 'deudor', 19, 38.00),
(41, 10, 3, 7, 'deudor', 28, 56.00),
(42, 11, 2, 3, 'deudor', 32, 64.00),
(47, 1, 3, 1, 'limpio', -6, 0.00),
(48, 2, 2, 3, 'limpio', -1, 0.00),
(49, 3, 5, 2, 'deudor', 9, 18.00),
(50, 4, 6, 5, 'deudor', 12, 24.00),
(51, 5, 2, 9, 'deudor', 14, 28.00),
(52, 6, 4, 2, 'deudor', 16, 32.00),
(53, 7, 3, 4, 'deudor', 16, 32.00),
(54, 8, 1, 1, 'deudor', 18, 36.00),
(55, 9, 6, 3, 'deudor', 18, 36.00),
(56, 10, 3, 7, 'deudor', 27, 54.00),
(57, 11, 2, 3, 'deudor', 31, 62.00);

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;

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
(9, 'Fortuna y Jacinta', 'Paza & Janéz', 'Perez Galdós', 'Novela', 'España', 625, '1984-01-01', 725.00);

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

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
(11, 3, 2, '2015-12-05 00:00:00', '2015-12-22 00:00:00', '2015-12-20 00:00:00');

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `usuario`
--

INSERT INTO `usuario` (`codigo`, `nombre`, `apellido`, `cedula`, `domicilio`, `ciudad`, `departamento`, `nacimiento`) VALUES
(1, 'Eva', 'Santana Paex', '2578829', 'Edifici Lares', 'Manizales', 'Caldas', '1980-05-23 00:00:00'),
(2, 'Yolanda', 'Betancour Diaz', '2578829', 'Cr 10-20-20', 'Manizales', 'Caldas', '1976-09-17 00:00:00'),
(3, 'Ines', 'Posada Gil', '3875929', 'Edifici Alpes ap201', 'Pereira', 'Risaralda', '1971-04-07 00:00:00'),
(4, 'Jose', 'Sanchez Pons', '3777929', 'Av Bolivar 2N 40', 'Armenia', 'Quindio', '1966-06-09 00:00:00'),
(5, 'migue', 'Gomez Saez', '73240455', 'Las Colinas casa 20', 'Armenia', 'Quindio', '1976-12-09 00:00:00'),
(6, 'Carlos Mario', 'Marin Duque', '98092261506', 'cr 15 16-43', 'Caicedonia', 'Valle', '1998-09-22 00:00:00');

--
-- Constraints for dumped tables
--

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
