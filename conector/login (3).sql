-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 14, 2015 at 08:12 PM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `login`
--

-- --------------------------------------------------------

--
-- Table structure for table `bitacora`
--

CREATE TABLE IF NOT EXISTS `bitacora` (
  `serial` int(10) NOT NULL AUTO_INCREMENT,
  `login` varchar(20) NOT NULL DEFAULT '',
  `fecha_ingreso` date DEFAULT NULL,
  `fecha_salida` date DEFAULT NULL,
  `hora_ingreso` time DEFAULT NULL,
  `hora_salida` time DEFAULT NULL,
  `nivel` int(2) DEFAULT NULL,
  PRIMARY KEY (`serial`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=211 ;

--
-- Dumping data for table `bitacora`
--

INSERT INTO `bitacora` (`serial`, `login`, `fecha_ingreso`, `fecha_salida`, `hora_ingreso`, `hora_salida`, `nivel`) VALUES
(1, '', '2015-11-05', NULL, '15:14:50', NULL, NULL),
(2, 'cmch05', '2015-11-05', NULL, '15:27:32', NULL, NULL),
(3, 'cmch05', '2015-11-05', '2015-11-05', '15:40:51', '15:40:55', NULL),
(4, 'usuario1', '2015-11-05', '2015-11-05', '15:41:55', '15:42:05', NULL),
(5, 'cmch05', '2015-11-05', '2015-11-05', '16:00:48', '16:00:51', NULL),
(6, 'usuario1', '2015-11-05', '2015-11-05', '17:26:11', '17:58:55', NULL),
(7, 'cmch05', '2015-11-06', '2015-11-06', '09:30:41', '10:02:21', NULL),
(8, 'cmch05', '2015-11-06', NULL, '10:04:18', NULL, NULL),
(9, 'usuario1', '2015-11-06', '2015-11-06', '10:05:26', '10:05:48', NULL),
(10, 'usuario1', '2015-11-06', NULL, '10:06:00', NULL, NULL),
(11, 'usuario1', '2015-11-06', '2015-11-06', '10:06:42', '10:06:50', NULL),
(12, 'cmch05', '2015-11-06', '2015-11-06', '10:53:11', '10:53:21', NULL),
(13, 'usuario1', '2015-11-06', '2015-11-06', '10:55:37', '10:55:42', NULL),
(14, 'cmch05', '2015-11-06', '2015-11-06', '10:55:54', '10:56:05', NULL),
(15, 'cmch05', '2015-11-06', '2015-11-06', '10:57:48', '11:46:21', NULL),
(16, 'cmch05', '2015-11-06', '2015-11-06', '11:46:34', '11:47:49', NULL),
(17, 'cmch05', '2015-11-06', '2015-11-06', '11:48:00', '11:51:36', NULL),
(18, 'cmch05', '2015-11-06', '2015-11-06', '11:51:52', '11:51:55', NULL),
(19, 'cmch05', '2015-11-06', '2015-11-06', '11:52:19', '11:52:24', NULL),
(20, 'cmch05', '2015-11-06', '2015-11-06', '11:53:32', '11:53:48', NULL),
(21, 'cmch05', '2015-11-06', '2015-11-06', '11:54:15', '11:55:41', NULL),
(22, 'cmch05', '2015-11-06', '2015-11-06', '11:55:59', '11:57:00', NULL),
(23, 'usuario1', '2015-11-06', '2015-11-06', '11:57:18', '11:57:24', NULL),
(24, 'cmch05', '2015-11-06', '2015-11-06', '11:57:32', '11:58:27', NULL),
(25, 'cmch05', '2015-11-06', '2015-11-06', '11:58:58', '13:04:53', NULL),
(26, 'cmch05', '2015-11-06', '2015-11-06', '22:05:04', '22:05:12', NULL),
(27, 'usuario1', '2015-11-06', '2015-11-06', '22:08:56', '22:09:00', NULL),
(28, 'cmch05', '2015-11-06', '2015-11-06', '22:16:27', '22:16:31', NULL),
(29, 'cmch05', '2015-11-06', '2015-11-06', '22:17:32', '22:19:16', NULL),
(30, 'cmch05', '2015-11-06', '2015-11-06', '22:19:25', '22:20:09', NULL),
(31, 'cmch05', '2015-11-06', '2015-11-06', '22:20:26', '22:23:09', NULL),
(32, 'cmch05', '2015-11-06', '2015-11-06', '22:23:22', '22:23:27', NULL),
(33, 'cmch05', '2015-11-06', '2015-11-06', '22:23:50', '22:24:41', NULL),
(34, 'cmch05', '2015-11-06', '2015-11-06', '22:24:51', '22:24:54', NULL),
(35, 'cmch05', '2015-11-06', '2015-11-06', '22:26:16', '22:26:21', NULL),
(36, 'cmch05', '2015-11-06', '2015-11-06', '22:27:49', '22:27:51', NULL),
(37, 'cmch05', '2015-11-06', '2015-11-06', '22:28:48', '22:28:55', NULL),
(38, 'cmch05', '2015-11-06', '2015-11-06', '22:29:07', '22:29:12', NULL),
(39, 'usuario1', '2015-11-06', '2015-11-06', '22:29:24', '22:29:26', NULL),
(40, 'cmch05', '2015-11-06', '2015-11-06', '22:29:42', '22:29:45', NULL),
(41, 'usuario1', '2015-11-06', '2015-11-06', '22:30:03', '22:30:39', NULL),
(42, 'cmch05', '2015-11-06', '2015-11-06', '22:31:15', '22:31:40', NULL),
(43, 'cmch05', '2015-11-06', '2015-11-06', '22:31:51', '22:31:53', NULL),
(44, 'usuario1', '2015-11-06', '2015-11-06', '22:37:07', '22:37:09', NULL),
(45, 'usuario1', '2015-11-06', '2015-11-06', '22:37:53', '22:37:55', NULL),
(46, 'cmch05', '2015-11-06', '2015-11-06', '22:38:16', '22:38:21', NULL),
(47, 'cmch05', '2015-11-06', '2015-11-06', '22:40:21', '22:41:21', NULL),
(48, 'cmch05', '2015-11-06', '2015-11-06', '22:46:22', '22:46:49', NULL),
(49, 'cmch05', '2015-11-06', '2015-11-06', '22:47:07', '22:47:10', NULL),
(50, 'cmch05', '2015-11-07', '2015-11-07', '00:27:46', '00:28:12', NULL),
(51, 'cmch05', '2015-11-07', '2015-11-07', '00:28:29', '00:28:40', NULL),
(52, 'usuario1', '2015-11-07', '2015-11-07', '00:28:50', '00:28:57', NULL),
(53, 'cmch05', '2015-11-07', '2015-11-07', '00:31:37', '00:33:16', NULL),
(54, 'cmch05', '2015-11-07', '2015-11-07', '00:33:26', '00:33:56', NULL),
(55, 'cmch05', '2015-11-07', '2015-11-07', '00:34:05', '00:34:18', NULL),
(56, 'cmch05', '2015-11-07', '2015-11-07', '00:37:32', '00:37:36', NULL),
(57, 'usuario1', '2015-11-07', '2015-11-07', '00:37:44', '00:40:31', NULL),
(58, 'cmch05', '2015-11-07', '2015-11-07', '00:47:25', '00:47:32', NULL),
(59, 'cmch05', '2015-11-07', '2015-11-07', '00:48:10', '00:48:20', NULL),
(60, 'cmch05', '2015-11-07', '2015-11-07', '00:48:55', '00:49:00', NULL),
(61, 'cmch05', '2015-11-07', '2015-11-07', '00:53:15', '00:53:25', NULL),
(62, 'cmch05', '2015-11-07', '2015-11-07', '00:53:40', '00:55:00', NULL),
(63, 'cmch05', '2015-11-07', '2015-11-07', '00:55:19', '00:55:41', NULL),
(64, 'usuario1', '2015-11-07', '2015-11-07', '01:00:16', '01:00:21', NULL),
(65, 'cmch05', '2015-11-07', '2015-11-07', '09:21:30', '09:21:46', NULL),
(66, 'cmch05', '2015-11-07', '2015-11-07', '09:23:47', '09:31:07', NULL),
(67, 'cmch05', '2015-11-07', '2015-11-07', '09:55:28', '10:05:23', NULL),
(68, 'cmch05', '2015-11-07', '2015-11-07', '10:56:43', '10:57:29', NULL),
(69, 'cmch05', '2015-11-07', '2015-11-07', '19:27:45', '19:28:36', NULL),
(70, 'cmch05', '2015-11-07', '2015-11-07', '21:07:49', '21:08:27', NULL),
(71, 'usuario1', '2015-11-07', '2015-11-07', '21:09:11', '21:10:48', NULL),
(72, 'cmch05', '2015-11-07', '2015-11-07', '21:12:32', '21:12:59', NULL),
(73, 'cmch05', '2015-11-07', '2015-11-07', '21:39:24', '21:39:34', NULL),
(74, 'cmch05', '2015-11-08', '2015-11-08', '17:41:15', '17:41:17', NULL),
(75, 'cmch05', '2015-11-08', '2015-11-08', '17:45:13', '17:45:44', NULL),
(76, 'cmch05', '2015-11-08', '2015-11-08', '17:48:29', '17:48:32', NULL),
(77, 'cmch05', '2015-11-08', '2015-11-08', '17:49:47', '17:49:49', NULL),
(78, 'cmch05', '2015-11-08', '2015-11-08', '17:53:39', '17:56:17', NULL),
(79, 'cmch05', '2015-11-08', '2015-11-08', '17:56:28', '17:56:47', NULL),
(80, 'cmch05', '2015-11-08', '2015-11-08', '17:56:54', '17:57:00', NULL),
(81, 'cmch05', '2015-11-08', '2015-11-08', '17:57:16', '17:57:23', NULL),
(82, 'cmch05', '2015-11-08', '2015-11-08', '17:59:15', '17:59:21', NULL),
(83, 'cmch05', '2015-11-08', '2015-11-08', '17:59:31', '17:59:36', NULL),
(84, 'cmch05', '2015-11-08', '2015-11-08', '18:12:32', '18:12:41', NULL),
(85, 'cmch05', '2015-11-08', '2015-11-08', '18:12:50', '18:12:55', NULL),
(86, 'cmch05', '2015-11-08', '2015-11-08', '18:13:12', '18:13:16', NULL),
(87, 'usuario1', '2015-11-08', '2015-11-08', '18:14:14', '18:14:19', NULL),
(88, 'cmch05', '2015-11-08', '2015-11-08', '18:15:51', '18:15:56', NULL),
(89, 'cmch05', '2015-11-08', '2015-11-08', '18:16:20', '18:16:25', NULL),
(90, 'usuario1', '2015-11-08', '2015-11-08', '18:23:18', '18:23:23', NULL),
(91, 'cmch05', '2015-11-08', '2015-11-08', '22:39:22', '22:39:26', NULL),
(92, 'cmch05', '2015-11-09', '2015-11-09', '13:05:25', '13:05:31', NULL),
(93, 'usuario1', '2015-11-09', '2015-11-09', '13:05:41', '13:07:19', NULL),
(94, 'cmch05', '2015-11-09', '2015-11-09', '13:21:37', '13:21:47', NULL),
(95, 'cmch05', '2015-11-09', '2015-11-09', '13:22:26', '13:22:33', NULL),
(96, 'cmch05', '2015-11-09', '2015-11-09', '13:22:49', '13:24:46', NULL),
(97, 'cmch05', '2015-11-09', '2015-11-09', '13:24:56', '13:31:15', NULL),
(98, 'cmch05', '2015-11-09', '2015-11-09', '13:31:27', '13:38:50', NULL),
(99, 'cmch05', '2015-11-09', '2015-11-09', '13:39:14', '13:57:29', NULL),
(100, 'cmch05', '2015-11-09', '2015-11-09', '13:57:50', '13:58:25', NULL),
(101, 'usuario10', '2015-11-09', '2015-11-09', '13:58:32', '14:02:05', NULL),
(102, 'cmch05', '2015-11-09', '2015-11-09', '14:02:18', '14:03:50', NULL),
(103, 'cmch05', '2015-11-09', '2015-11-09', '14:05:19', '14:06:18', NULL),
(104, 'cmch05', '2015-11-09', '2015-11-09', '14:06:32', '14:12:49', NULL),
(105, 'cmch05', '2015-11-09', '2015-11-09', '14:13:12', '14:22:48', NULL),
(106, 'cmch05', '2015-11-09', '2015-11-09', '14:22:59', '14:23:53', NULL),
(107, 'cmch05', '2015-11-09', '2015-11-09', '14:25:10', '14:25:57', NULL),
(108, 'cmch05', '2015-11-09', '2015-11-09', '14:26:24', '14:27:21', NULL),
(109, '', '2015-11-09', '2015-11-09', '15:04:54', '15:07:21', NULL),
(110, '', '2015-11-09', '2015-11-09', '15:07:41', '15:08:15', NULL),
(111, '', '2015-11-09', '2015-11-09', '15:08:31', '15:12:42', NULL),
(112, '', '2015-11-09', '2015-11-09', '15:12:57', '15:24:41', NULL),
(113, 'cmch05', '2015-11-09', '2015-11-09', '23:35:03', '23:35:11', NULL),
(114, '', '2015-11-09', '2015-11-09', '23:38:53', '23:39:03', NULL),
(115, 'cmch05', '2015-11-09', '2015-11-09', '23:46:46', '23:46:53', NULL),
(116, '', '2015-11-10', '2015-11-10', '00:05:28', '00:09:05', NULL),
(117, '', '2015-11-10', '2015-11-10', '00:11:56', '00:22:29', NULL),
(118, 'usuario1', '2015-11-10', '2015-11-10', '00:22:38', '00:22:56', NULL),
(119, 'cmch05', '2015-11-10', '2015-11-10', '00:33:55', '00:42:12', NULL),
(120, 'cmch05', '2015-11-10', '2015-11-10', '00:54:06', '00:54:10', NULL),
(121, 'cmch05', '2015-11-10', '2015-11-10', '07:41:19', '07:41:27', NULL),
(122, '', '2015-11-10', '2015-11-10', '07:41:49', '07:42:58', NULL),
(123, '', '2015-11-10', '2015-11-10', '07:43:07', '07:43:16', NULL),
(124, '', '2015-11-10', '2015-11-10', '07:44:30', '07:44:37', NULL),
(125, '', '2015-11-10', '2015-11-10', '07:54:05', '07:55:22', NULL),
(126, 'usuario3', '2015-11-10', '2015-11-10', '07:55:54', '07:58:36', NULL),
(127, 'cmch05', '2015-11-10', '2015-11-10', '07:58:44', '08:06:51', NULL),
(128, '', '2015-11-10', '2015-11-10', '08:06:59', '08:07:42', NULL),
(129, '', '2015-11-10', '2015-11-10', '08:51:34', '08:56:58', NULL),
(130, '', '2015-11-10', '2015-11-10', '08:57:07', '08:59:05', NULL),
(131, '', '2015-11-10', '2015-11-10', '08:59:14', '08:59:24', NULL),
(132, '', '2015-11-10', '2015-11-10', '09:16:46', '09:18:34', NULL),
(133, '', '2015-11-10', '2015-11-10', '09:19:55', '09:20:27', NULL),
(134, '', '2015-11-10', '2015-11-10', '09:25:14', '09:25:20', NULL),
(135, '', '2015-11-10', '2015-11-10', '09:25:41', '09:26:26', NULL),
(136, '', '2015-11-10', '2015-11-10', '10:49:36', '10:49:38', NULL),
(137, '', '2015-11-10', '2015-11-10', '10:50:05', '10:50:07', NULL),
(138, '', '2015-11-10', '2015-11-10', '10:50:17', '10:50:22', NULL),
(139, '', '2015-11-10', '2015-11-10', '10:51:23', '10:51:27', NULL),
(140, '', '2015-11-10', '2015-11-10', '10:51:34', '10:51:39', NULL),
(141, '', '2015-11-10', '2015-11-10', '10:52:36', '10:52:38', NULL),
(142, '', '2015-11-10', '2015-11-10', '10:56:32', '10:56:35', NULL),
(143, '', '2015-11-10', '2015-11-10', '11:18:45', '11:21:26', NULL),
(144, '', '2015-11-10', '2015-11-10', '11:21:37', '11:22:36', NULL),
(145, '', '2015-11-10', '2015-11-10', '11:22:43', '11:23:15', NULL),
(146, 'cmch05', '2015-11-10', '2015-11-10', '11:24:01', '11:25:29', NULL),
(147, 'cmch05', '2015-11-10', '2015-11-10', '11:25:37', '11:27:04', NULL),
(148, 'cmch05', '2015-11-10', '2015-11-10', '11:27:15', '11:31:13', NULL),
(149, '', '2015-11-10', '2015-11-10', '11:31:32', '11:34:31', NULL),
(150, '', '2015-11-10', '2015-11-10', '11:39:14', '11:39:24', NULL),
(151, 'cmch05', '2015-11-10', '2015-11-10', '11:41:09', '11:41:59', NULL),
(152, '', '2015-11-10', '2015-11-10', '11:45:18', '11:45:28', NULL),
(153, 'cmch05', '2015-11-10', '2015-11-10', '18:18:52', '18:20:37', NULL),
(154, 'usuario1', '2015-11-10', '2015-11-10', '18:22:19', '18:22:23', NULL),
(155, '', '2015-11-10', '2015-11-10', '18:28:42', '18:31:59', NULL),
(156, '', '2015-11-10', '2015-11-10', '18:32:09', '18:32:37', NULL),
(157, '', '2015-11-10', '2015-11-10', '18:34:20', '18:34:28', NULL),
(158, '', '2015-11-10', '2015-11-10', '18:35:16', '18:35:23', NULL),
(159, '', '2015-11-10', '2015-11-10', '18:35:44', '18:37:19', NULL),
(160, '', '2015-11-10', '2015-11-10', '18:37:29', '18:40:24', NULL),
(161, '', '2015-11-10', '2015-11-10', '18:40:31', '18:40:47', NULL),
(162, '', '2015-11-10', '2015-11-10', '18:43:20', '18:44:45', NULL),
(163, '', '2015-11-10', '2015-11-10', '18:44:54', '18:45:17', NULL),
(164, '', '2015-11-10', '2015-11-10', '19:56:47', '19:57:00', NULL),
(165, '', '2015-11-11', '2015-11-11', '08:07:51', '08:08:09', NULL),
(166, '', '2015-11-11', '2015-11-11', '08:12:12', '08:13:38', NULL),
(167, '', '2015-11-11', '2015-11-11', '08:13:46', '08:14:02', NULL),
(168, 'cmch05', '2015-11-11', '2015-11-11', '08:14:23', '08:15:07', NULL),
(169, '', '2015-11-11', '2015-11-11', '08:15:30', '08:21:15', NULL),
(170, '', '2015-11-11', '2015-11-11', '08:22:03', '08:23:56', NULL),
(171, 'cmch05', '2015-11-11', '2015-11-11', '08:24:10', '08:25:11', NULL),
(172, 'cmch05', '2015-11-11', '2015-11-11', '08:26:00', '08:26:19', NULL),
(173, '', '2015-11-11', '2015-11-11', '08:37:18', '08:37:46', NULL),
(174, '', '2015-11-11', '2015-11-11', '08:37:54', '08:38:09', NULL),
(175, '', '2015-11-11', '2015-11-11', '08:38:23', '08:54:03', NULL),
(176, '', '2015-11-11', '2015-11-11', '13:59:19', '14:00:06', NULL),
(177, 'cmch05', '2015-11-11', '2015-11-11', '14:24:03', '14:24:20', NULL),
(178, 'usuario100', '2015-11-11', '2015-11-11', '22:28:05', '22:28:31', NULL),
(179, 'cmch05', '2015-11-11', '2015-11-11', '22:45:33', '22:45:58', NULL),
(180, 'cmch05', '2015-11-12', '2015-11-12', '00:12:50', '00:24:17', NULL),
(181, 'cmch05', '2015-11-12', '2015-11-12', '07:31:21', '07:32:03', NULL),
(182, '', '2015-11-12', '2015-11-12', '07:34:43', '07:36:03', NULL),
(183, '', '2015-11-12', '2015-11-12', '07:39:01', '07:39:55', NULL),
(184, '', '2015-11-12', '2015-11-12', '11:41:16', '11:48:58', NULL),
(185, '', '2015-11-12', '2015-11-12', '12:06:28', '12:06:43', NULL),
(186, 'cmch05', '2015-11-12', '2015-11-12', '17:17:13', '17:23:19', NULL),
(187, '', '2015-11-12', '2015-11-12', '17:23:49', '17:25:35', NULL),
(188, 'cmch05', '2015-11-12', '2015-11-12', '17:25:45', '17:26:12', NULL),
(189, 'cmch05', '2015-11-12', '2015-11-12', '17:26:53', '17:28:13', NULL),
(190, 'cmch05', '2015-11-12', '2015-11-12', '17:38:20', '17:38:57', NULL),
(191, '', '2015-11-12', '2015-11-12', '17:45:53', '17:47:27', NULL),
(192, '', '2015-11-12', '2015-11-12', '17:48:06', '17:48:59', NULL),
(193, '', '2015-11-12', '2015-11-12', '20:19:16', '20:22:08', NULL),
(194, '', '2015-11-14', '2015-11-14', '09:14:54', '09:15:57', NULL),
(195, '', '2015-11-14', '2015-11-14', '09:16:57', '09:17:11', NULL),
(196, '', '2015-11-14', '2015-11-14', '09:20:11', '09:21:42', NULL),
(197, '', '2015-11-14', '2015-11-14', '09:21:49', '09:23:01', NULL),
(198, '', '2015-11-14', '2015-11-14', '11:29:39', '11:30:24', NULL),
(199, '', '2015-11-14', '2015-11-14', '11:42:16', '11:42:17', NULL),
(200, '', '2015-11-14', '2015-11-14', '11:47:28', '11:47:29', NULL),
(201, '', '2015-11-14', '2015-11-14', '11:58:12', '11:58:13', NULL),
(202, '', '2015-11-14', '2015-11-14', '11:59:03', '11:59:05', NULL),
(203, '', '2015-11-14', '2015-11-14', '12:02:54', '12:02:56', NULL),
(204, 'quinto', '2015-11-14', '2015-11-14', '12:03:29', '12:03:31', NULL),
(205, '', '2015-11-14', '2015-11-14', '12:12:52', '12:12:55', NULL),
(206, '', '2015-11-14', '2015-11-14', '12:13:57', '12:13:58', NULL),
(207, '', '2015-11-14', '2015-11-14', '12:14:10', '12:14:14', NULL),
(208, '', '2015-11-14', NULL, '12:15:54', NULL, NULL),
(209, '', '2015-11-14', '2015-11-14', '12:17:02', '12:17:08', NULL),
(210, '', '2015-11-14', '2015-11-14', '12:41:13', '12:41:18', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `perfil`
--

CREATE TABLE IF NOT EXISTS `perfil` (
  `id` int(10) NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `perfil`
--

INSERT INTO `perfil` (`id`, `descripcion`) VALUES
(1, 'administrador'),
(2, 'invitado'),
(3, 'editor');

-- --------------------------------------------------------

--
-- Table structure for table `perfil_permiso`
--

CREATE TABLE IF NOT EXISTS `perfil_permiso` (
  `id_perfil` int(10) DEFAULT NULL,
  `id_permiso` int(10) DEFAULT NULL,
  KEY `id_permisio` (`id_permiso`),
  KEY `id_perfil` (`id_perfil`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `perfil_permiso`
--

INSERT INTO `perfil_permiso` (`id_perfil`, `id_permiso`) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(2, 3),
(3, 3),
(3, 2);

-- --------------------------------------------------------

--
-- Table structure for table `permiso`
--

CREATE TABLE IF NOT EXISTS `permiso` (
  `id` int(10) NOT NULL,
  `permiso` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `permiso`
--

INSERT INTO `permiso` (`id`, `permiso`) VALUES
(1, 'crear'),
(2, 'actualizar'),
(3, 'ver'),
(4, 'eliminar');

-- --------------------------------------------------------

--
-- Table structure for table `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `login` varchar(20) NOT NULL DEFAULT '',
  `password` varchar(100) NOT NULL,
  `estado` tinyint(1) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `nivel` int(2) DEFAULT NULL,
  PRIMARY KEY (`login`),
  KEY `nivel` (`nivel`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usuario`
--

INSERT INTO `usuario` (`login`, `password`, `estado`, `fecha`, `nivel`) VALUES
('', '827ccb0eea8a706c4c34a16891f84e7b', 1, '2015-12-31', 1),
('cmch05', '827ccb0eea8a706c4c34a16891f84e7b', 1, '2015-12-11', 1),
('maximilian', '827ccb0eea8a706c4c34a16891f84e7b', 0, '2011-11-11', 3),
('mod22', 'a4dfb3166e19ce3284c77fda27ab683d', 1, '2015-12-30', 3),
('quinto', '827ccb0eea8a706c4c34a16891f84e7b', 1, '2015-12-31', 2),
('sexto', '827ccb0eea8a706c4c34a16891f84e7b', 0, '2013-12-02', 2),
('usuario3s', '62661721940b9ff41bb6eae91c2c39dd', 1, '2017-12-30', 2),
('usuario8', '827ccb0eea8a706c4c34a16891f84e7b', 0, '2013-11-11', 1),
('usuario9', '827ccb0eea8a706c4c34a16891f84e7b', 1, '2015-12-12', 2),
('vennus_n', 'd54ae843c1735094ca6fe51cb2008712', 1, '2017-12-31', 2),
('william', '827ccb0eea8a706c4c34a16891f84e7b', 1, '2015-12-13', 2),
('williamc', '335a60a5dec0e853a204bcc01a096758', 1, '2015-12-25', 3);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `perfil_permiso`
--
ALTER TABLE `perfil_permiso`
  ADD CONSTRAINT `perfil_permiso_ibfk_1` FOREIGN KEY (`id_permiso`) REFERENCES `permiso` (`id`),
  ADD CONSTRAINT `perfil_permiso_ibfk_2` FOREIGN KEY (`id_perfil`) REFERENCES `perfil` (`id`);

--
-- Constraints for table `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`nivel`) REFERENCES `perfil` (`id`),
  ADD CONSTRAINT `usuario_ibfk_2` FOREIGN KEY (`nivel`) REFERENCES `perfil` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
