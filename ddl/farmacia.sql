-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 05-06-2023 a las 16:47:31
-- Versión del servidor: 10.4.27-MariaDB
-- Versión de PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `farmacia`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `doctor`
--

CREATE TABLE `doctor` (
  `mail` varchar(50) NOT NULL,
  `pass` varchar(100) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `last_log` date DEFAULT NULL,
  `session` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `doctor`
--

INSERT INTO `doctor` (`mail`, `pass`, `name`, `last_log`, `session`) VALUES
('mateu@example.com', '1234', 'mateu', '2023-06-05', 931195721);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `medicine`
--

CREATE TABLE `medicine` (
  `id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `tmax` double DEFAULT NULL,
  `tmin` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `medicine`
--

INSERT INTO `medicine` (`id`, `name`, `tmax`, `tmin`) VALUES
(1, 'Ibuprofeno', 35.2, 0),
(2, 'Diazepan', 56, 5),
(3, 'Paracetamol', 70, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `patient`
--

CREATE TABLE `patient` (
  `mail` varchar(100) NOT NULL,
  `name` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `patient`
--

INSERT INTO `patient` (`mail`, `name`) VALUES
('Esteban@yahoo.com', 'Esteban'),
('mateups213@gmail.com', 'Mateu'),
('patient1@gmail.com', 'Joan'),
('pau@example.com', 'Pau');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `xip`
--

CREATE TABLE `xip` (
  `id` int(10) NOT NULL,
  `doctor_mail` varchar(50) DEFAULT NULL,
  `id_medicine` int(10) DEFAULT NULL,
  `id_patient` varchar(50) DEFAULT NULL,
  `date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `xip`
--

INSERT INTO `xip` (`id`, `doctor_mail`, `id_medicine`, `id_patient`, `date`) VALUES
(1, 'mateu@example.com', 1, 'patient1@gmail.com', '2023-05-31');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `doctor`
--
ALTER TABLE `doctor`
  ADD PRIMARY KEY (`mail`);

--
-- Indices de la tabla `medicine`
--
ALTER TABLE `medicine`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`mail`);

--
-- Indices de la tabla `xip`
--
ALTER TABLE `xip`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `medicine`
--
ALTER TABLE `medicine`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
