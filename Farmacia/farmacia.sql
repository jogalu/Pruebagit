-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 18-06-2012 a las 18:20:26
-- Versión del servidor: 5.5.16
-- Versión de PHP: 5.3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `farmacia`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `laboratorios`
--

CREATE TABLE IF NOT EXISTS `laboratorios` (
  `CodLab` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(40) NOT NULL,
  `Telefono` int(11) NOT NULL,
  `Direccion` varchar(100) NOT NULL,
  `Poblacion` varchar(50) NOT NULL,
  PRIMARY KEY (`CodLab`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Volcado de datos para la tabla `laboratorios`
--

INSERT INTO `laboratorios` (`CodLab`, `Nombre`, `Telefono`, `Direccion`, `Poblacion`) VALUES
(1, 'Bayer', 934956500, 'C\\Pau Clarís, 196', 'Barcelona'),
(3, 'Ferrer', 936003700, 'Diagonal,549,5ªPlanta', 'Barcelona'),
(4, 'Norgine', 913758870, 'C\\Julian Camarillo, Nº 21B,4ªPlanta', 'Madrid');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `medicamentos`
--

CREATE TABLE IF NOT EXISTS `medicamentos` (
  `CodMed` int(11) NOT NULL AUTO_INCREMENT,
  `NombreM` varchar(50) NOT NULL,
  `Unidades` int(11) NOT NULL,
  `Precio` double NOT NULL,
  `Tipo` varchar(50) NOT NULL,
  `Receta` tinyint(1) NOT NULL,
  `CodLab` int(11) NOT NULL,
  PRIMARY KEY (`CodMed`),
  KEY `CodLab` (`CodLab`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `medicamentos`
--
ALTER TABLE `medicamentos`
    ADD CONSTRAINT `medicamentos_ibfk_4` FOREIGN KEY (`CodLab`) REFERENCES `laboratorios` (`CodLab`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
