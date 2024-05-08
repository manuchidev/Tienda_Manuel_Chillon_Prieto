-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 19-04-2024 a las 10:43:23
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

CREATE DATABASE IF NOT EXISTS tienda_manuel_chillon_prieto;
USE tienda_manuel_chillon_prieto;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `tienda_manuel_chillon_prieto`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias`
--

CREATE TABLE `categorias` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `fecha_baja` timestamp NULL DEFAULT NULL,
  `imagen` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `categorias`
--

INSERT INTO `categorias` (`id`, `nombre`, `descripcion`, `fecha_baja`, `imagen`) VALUES
(1, 'Cascos', 'Cascos de seguridad', NULL, 'cascos.png'),
(2, 'Chaquetas', 'Chaquetas de cuero o textil', NULL, 'chaquetas.png'),
(3, 'Guantes', 'Guantes resistentes y protectores', NULL, 'guantes.png'),
(4, 'Pantalones', 'Pantalones de moto resistentes y cómodos', NULL, 'pantalones.png'),
(5, 'Botas', 'Botas de carretera y de offroad', NULL, 'botas.png'),
(6, 'Accesorios', 'Otros accesorios útiles', NULL, 'accesorios.png');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `configuracion`
--

CREATE TABLE `configuracion` (
  `id` int(11) NOT NULL,
  `clave` varchar(255) DEFAULT NULL,
  `valor` varchar(255) DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

INSERT INTO configuracion VALUES
	(1, 'num_factura', '0', ''),
	(2, 'nombre_empresa', 'Riders Shop', ''),
	(3, 'cif', 'B41527894', ''),
	(4, 'direccion', 'C/ Candelaria 5, 49015, Zamora', ''),
	(5, 'email', 'riders_shop@outlook.com', '');

--
-- Estructura de tabla para la tabla `descuentos`
--

CREATE TABLE `descuentos` (
  `id` int(11) NOT NULL,
  `codigo` varchar(255) DEFAULT NULL,
  `descuento` DECIMAL(10, 2) DEFAULT NULL,
  `fecha_inicio` timestamp NULL DEFAULT NULL,
  `fecha_fin` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalles_pedido`
--

CREATE TABLE `detalles_pedido` (
  `id` int(11) NOT NULL,
  `id_pedido` int(11) DEFAULT NULL,
  `id_producto` int(11) DEFAULT NULL,
  `precio_unidad` DECIMAL(10, 2) DEFAULT NULL,
  `unidades` int(11) DEFAULT NULL,
  `impuesto` float DEFAULT NULL,
  `total` DECIMAL(10, 2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `impuestos`
--

CREATE TABLE `impuestos` (
  `id` int(11) NOT NULL,
  `impuesto` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `metodos_pago`
--

CREATE TABLE `metodos_pago` (
  `id` int(11) NOT NULL,
  `metodo_pago` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Volcado de datos para la tabla `metodos_pago`
--

INSERT INTO `metodos_pago` (`id`, `metodo_pago`) VALUES
(1, 'Tarjeta'),
(2, 'Paypal'),
(3, 'Contra reembolso');


--
-- Estructura de tabla para la tabla `opciones_menu`
--

CREATE TABLE `opciones_menu` (
  `id` int(11) NOT NULL,
  `id_rol` int(11) DEFAULT NULL,
  `nombre_opcion` varchar(255) DEFAULT NULL,
  `url_opcion` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedidos`
--

CREATE TABLE `pedidos` (
  `id` int(11) NOT NULL,
  `id_usuario` int(11) DEFAULT NULL,
  `fecha` timestamp NULL DEFAULT NULL,
  `metodo_pago` varchar(255) DEFAULT NULL,
  `estado` ENUM('PE', 'PC', 'E', 'C') DEFAULT NULL,
  `num_factura` varchar(255) DEFAULT NULL,
  `total` DECIMAL(10, 2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `id` int(11) NOT NULL,
  `id_categoria` int(11) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `precio` DECIMAL(10, 2) DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `fecha_alta` timestamp NULL DEFAULT NULL,
  `fecha_baja` timestamp NULL DEFAULT NULL,
  `impuesto` DECIMAL(4, 2) DEFAULT NULL,
  `imagen` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`id` ,`id_categoria`, `nombre`, `descripcion`, `precio`, `stock`, `fecha_alta`, `fecha_baja`, `impuesto`, `imagen`) VALUES
(1, 1, 'Casco integral negro mate', 'Casco de moto integral negro mate, resistente y seguro', 89.99, 50, NOW(), NULL, 21, 'casco_integral.png'),
(2, 1, 'Casco modular con visera', 'Casco modular con visera para mayor comodidad y versatilidad', 129.99, 30, NOW(), NULL, 21, 'casco_modular_visera.png'),
(3, 1, 'Casco de motocross', 'Casco de motocross resistente, ideal para off-road', 149.99, 15, NOW(), NULL, 21, 'casco_motocross.png'),
(4, 1, 'Casco de moto abierto', 'Casco de moto abierto', 79.99, 20, NOW(), NULL, 21, 'casco_abierto.png'),

(5, 2, 'Chaqueta de cuero con protecciones', 'Chaqueta de cuero con protecciones para motociclistas', 199.99, 40, NOW(), NULL, 21, 'chaqueta_cuero.png'),
(6, 2, 'Chaqueta textil impermeable', 'Chaqueta textil impermeable para todas las estaciones', 129.99, 35, NOW(), NULL, 21, 'chaqueta_textil.png'),
(7, 2, 'Chaqueta de verano transpirable', 'Chaqueta ligera y transpirable ideal para verano', 99.99, 30, NOW(), NULL, 21, 'chaqueta_verano.png'),
(8, 2, 'Chaqueta de motocross', 'Chaqueta ligera y transpirable ideal para motocross', 49.99, 20, NOW(), NULL, 21, 'chaqueta_motocross.png'),
(9, 2, 'Chaqueta estilo calle', 'Chaqueta de moto estilo calle con protecciones', 149.99, 25, NOW(), NULL, 21, 'chaqueta_calle.png'),

(10, 3, 'Guantes de cuero con refuerzos', 'Guantes de cuero con refuerzos para mayor protección', 59.99, 60, NOW(), NULL, 21, 'guantes_cuero.png'),
(11, 3, 'Guantes de invierno impermeables', 'Guantes térmicos e impermeables para el invierno', 49.99, 50, NOW(), NULL, 21, 'guantes_invierno.png'),
(12, 3, 'Guantes deportivos', 'Guantes deportivos para moto con ventilación', 39.99, 70, NOW(), NULL, 21, 'guantes_deportivos.png'),
(13, 3, 'Guantes de motocross', 'Guantes para motocross ligeros y con ventilación', 39.99, 50, NOW(), NULL, 21, 'guantes_motocross.png'),
(14, 3, 'Guantes de verano', 'Guantes cortos y ventilados con protecciones para el verano', 69.99, 45, NOW(), NULL, 21, 'guantes_verano.png'),

(15, 4, 'Pantalones de moto de invierno', 'Pantalones de moto ajustables, resistentes e impermeables para el invierno', 119.99, 30, NOW(), NULL, 21, 'pantalones_invierno.png'),
(16, 4, 'Pantalones de cuero', 'Pantalones de cuero con protecciones y deslizaderas', 149.99, 25, NOW(), NULL, 21, 'pantalones_cuero.png'),
(17, 4, 'Pantalones vaqueros con kevlar', 'Pantalones vaqueros reforzados con kevlar para mayor seguridad', 99.99, 40, NOW(), NULL, 21, 'pantalones_vaqueros.png'),
(18, 4, 'Pantalones de motocross', 'Pantalones para motocross con pequeñas protecciones', 79.99, 50, NOW(), NULL, 21, 'pantalones_motocross.png'),

(19, 5, 'Botas deportivas', 'Botas de cuero para largas distancias y protección', 169.99, 20, NOW(), NULL, 21, 'botas_deportivas.png'),
(20, 5, 'Botas de motocross enduro', 'Botas de motocross enduro resistentes y cómodas', 199.99, 15, NOW(), NULL, 21, 'botas_motocross.png'),
(21, 5, 'Botas urbanas estilo motero', 'Botas urbanas estilo motero con protecciones', 129.99, 30, NOW(), NULL, 21, 'botas_urbanas.png'),
(22, 5, 'Botas touring con membrana impermeable', 'Botas touring con membrana impermeable y transpirable', 189.99, 25, NOW(), NULL, 21, 'botas_touring.png'),

(23, 6, 'Mochila impermeable para moto', 'Mochila impermeable diseñada para llevar en moto', 49.99, 40, NOW(), NULL, 21, 'mochila_impermeable.png'),
(24, 6, 'Kit de herramientas para moto', 'Kit completo de herramientas esenciales para motociclistas', 39.99, 30, NOW(), NULL, 21, 'kit_herramientas.png'),
(25, 6, 'Soporte móvil para moto', 'Soporte resistente para montar un teléfono móvil en la moto', 19.99, 20, NOW(), NULL, 21, 'soporte_movil.png'),
(26, 6, 'Cepillo limpia cadena', 'Cepillo para limpiar la cadena de la motocicleta', 29.99, 15, NOW(), NULL, 21, 'cepillo_cadena.png');
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedores`
--

CREATE TABLE `proveedores` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `localidad` varchar(255) DEFAULT NULL,
  `provincia` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `cif` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `rol` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`id`, `rol`) VALUES
(1, 'administrador'),
(2, 'empleado'),
(3, 'cliente');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `id_rol` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `clave` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `apellido1` varchar(255) DEFAULT NULL,
  `apellido2` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `provincia` varchar(255) DEFAULT NULL,
  `localidad` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `dni` varchar(255) DEFAULT NULL,
  `fecha_baja` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `id_rol`, `email`, `clave`, `nombre`, `apellido1`, `apellido2`, `direccion`, `provincia`, `localidad`, `telefono`, `dni`, `fecha_baja`) VALUES
(1, 1, 'admin1_riders@hotmail.com', 'wVh8cR7nMc8Ta82QXzTEJUMshq8wrDDmhb3gyoj3t5q70hW1jqkzxCTfa0ZpGPpj', 'María', 'López', 'Martínez', 'Avenida Central 456', 'Barcelona', 'Barcelona', '555123456', '87654321B', NULL),
(2, 2, 'pepe_prieto@hotmail.com', '9MJhr0uMviZUYZ8bzzqY+E+Ss2aH6jE5KZxz9UTcBR4z1l+1/PmZfLxmK1k6tX2j', 'Pepe', 'Prieto', 'Gómez', 'Calle Maestra 789', 'Valencia', 'Valencia', '600364857', '45678912V', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `valoraciones`
--

CREATE TABLE `valoraciones` (
  `id` int(11) NOT NULL,
  `id_producto` int(11) DEFAULT NULL,
  `id_usuario` int(11) DEFAULT NULL,
  `valoracion` int(11) DEFAULT NULL,
  `comentario` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categorias`
--
ALTER TABLE `categorias`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `configuracion`
--
ALTER TABLE `configuracion`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `descuentos`
--
ALTER TABLE `descuentos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `detalles_pedido`
--
ALTER TABLE `detalles_pedido`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_pedido` (`id_pedido`),
  ADD KEY `id_producto` (`id_producto`);

--
-- Indices de la tabla `impuestos`
--
ALTER TABLE `impuestos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `metodos_pago`
--
ALTER TABLE `metodos_pago`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `opciones_menu`
--
ALTER TABLE `opciones_menu`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_rol` (`id_rol`);

--
-- Indices de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_categoria` (`id_categoria`);

--
-- Indices de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_rol` (`id_rol`);

--
-- Indices de la tabla `valoraciones`
--
ALTER TABLE `valoraciones`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_producto` (`id_producto`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categorias`
--
ALTER TABLE `categorias`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `configuracion`
--
ALTER TABLE `configuracion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `descuentos`
--
ALTER TABLE `descuentos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `detalles_pedido`
--
ALTER TABLE `detalles_pedido`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `impuestos`
--
ALTER TABLE `impuestos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `metodos_pago`
--
ALTER TABLE `metodos_pago`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `opciones_menu`
--
ALTER TABLE `opciones_menu`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=303;

--
-- AUTO_INCREMENT de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `valoraciones`
--
ALTER TABLE `valoraciones`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `detalles_pedido`
--
ALTER TABLE `detalles_pedido`
  ADD CONSTRAINT `detalles_pedido_ibfk_1` FOREIGN KEY (`id_pedido`) REFERENCES `pedidos` (`id`),
  ADD CONSTRAINT `detalles_pedido_ibfk_2` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id`);

--
-- Filtros para la tabla `opciones_menu`
--
ALTER TABLE `opciones_menu`
  ADD CONSTRAINT `opciones_menu_ibfk_1` FOREIGN KEY (`id_rol`) REFERENCES `roles` (`id`);

--
-- Filtros para la tabla `pedidos`
--
ALTER TABLE `pedidos`
  ADD CONSTRAINT `pedidos_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`);

--
-- Filtros para la tabla `productos`
--
ALTER TABLE `productos`
  ADD CONSTRAINT `productos_ibfk_1` FOREIGN KEY (`id_categoria`) REFERENCES `categorias` (`id`);

--
-- Filtros para la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`id_rol`) REFERENCES `roles` (`id`);

--
-- Filtros para la tabla `valoraciones`
--
ALTER TABLE `valoraciones`
  ADD CONSTRAINT `valoraciones_ibfk_1` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id`),
  ADD CONSTRAINT `valoraciones_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
