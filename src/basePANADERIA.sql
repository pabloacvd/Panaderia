-- --------------------------------------------------------
-- Host:                         localhost
-- Versión del servidor:         8.0.23 - MySQL Community Server - GPL
-- SO del servidor:              Win64
-- HeidiSQL Versión:             11.2.0.6213
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para panaderia
CREATE DATABASE IF NOT EXISTS `panaderia` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `panaderia`;

-- Volcando estructura para tabla panaderia.pedidos
CREATE TABLE IF NOT EXISTS `pedidos` (
  `idPedido` int NOT NULL AUTO_INCREMENT,
  `total` int DEFAULT NULL,
  PRIMARY KEY (`idPedido`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- Volcando datos para la tabla panaderia.pedidos: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `pedidos` DISABLE KEYS */;
REPLACE INTO `pedidos` (`idPedido`, `total`) VALUES
	(1, 104),
	(2, 10),
	(3, 550),
	(4, 22650);
/*!40000 ALTER TABLE `pedidos` ENABLE KEYS */;

-- Volcando estructura para tabla panaderia.productos
CREATE TABLE IF NOT EXISTS `productos` (
  `idProducto` int NOT NULL AUTO_INCREMENT,
  `producto` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `precio` int DEFAULT NULL,
  `stock` int DEFAULT NULL,
  PRIMARY KEY (`idProducto`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- Volcando datos para la tabla panaderia.productos: ~4 rows (aproximadamente)
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
REPLACE INTO `productos` (`idProducto`, `producto`, `precio`, `stock`) VALUES
	(1, 'torta', 1500, 100),
	(2, 'facturas', 50, 3000),
	(3, 'pan', 100, 500),
	(4, 'galletas', 30, 1500);
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;

-- Volcando estructura para tabla panaderia.productosxpedido
CREATE TABLE IF NOT EXISTS `productosxpedido` (
  `idPedido` int NOT NULL,
  `idProducto` int NOT NULL,
  `cantidad` int DEFAULT NULL,
  PRIMARY KEY (`idPedido`,`idProducto`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- Volcando datos para la tabla panaderia.productosxpedido: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `productosxpedido` DISABLE KEYS */;
REPLACE INTO `productosxpedido` (`idPedido`, `idProducto`, `cantidad`) VALUES
	(3, 2, 1),
	(3, 3, 5),
	(4, 1, 15),
	(4, 2, 3);
/*!40000 ALTER TABLE `productosxpedido` ENABLE KEYS */;

-- Volcando estructura para tabla panaderia.productosxpromo
CREATE TABLE IF NOT EXISTS `productosxpromo` (
  `idPromo` int NOT NULL,
  `idProducto` int NOT NULL,
  PRIMARY KEY (`idPromo`,`idProducto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- Volcando datos para la tabla panaderia.productosxpromo: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `productosxpromo` DISABLE KEYS */;
/*!40000 ALTER TABLE `productosxpromo` ENABLE KEYS */;

-- Volcando estructura para tabla panaderia.promos
CREATE TABLE IF NOT EXISTS `promos` (
  `idPromo` int NOT NULL AUTO_INCREMENT,
  `descuento` int NOT NULL DEFAULT '0',
  `nombrePromo` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`idPromo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- Volcando datos para la tabla panaderia.promos: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `promos` DISABLE KEYS */;
/*!40000 ALTER TABLE `promos` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
