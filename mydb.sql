/*
 Navicat MySQL Data Transfer

 Source Server         : Local
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : localhost:3306
 Source Schema         : mydb

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 16/04/2020 02:13:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for asignaturas
-- ----------------------------
DROP TABLE IF EXISTS `asignaturas`;
CREATE TABLE `asignaturas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) DEFAULT NULL,
  `creditos` int(11) DEFAULT NULL,
  `estado` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of asignaturas
-- ----------------------------
BEGIN;
INSERT INTO `asignaturas` VALUES (1, 'Matematicas Basica', 1, NULL);
INSERT INTO `asignaturas` VALUES (2, 'Calculo Integral', 12, NULL);
COMMIT;

-- ----------------------------
-- Table structure for dias
-- ----------------------------
DROP TABLE IF EXISTS `dias`;
CREATE TABLE `dias` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) DEFAULT NULL,
  `estado` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dias
-- ----------------------------
BEGIN;
INSERT INTO `dias` VALUES (1, 'Lunes', 0);
INSERT INTO `dias` VALUES (2, 'Martes', 0);
INSERT INTO `dias` VALUES (3, 'Miercoles', 0);
INSERT INTO `dias` VALUES (4, 'Jueves', 0);
INSERT INTO `dias` VALUES (5, 'Viernes', 0);
COMMIT;

-- ----------------------------
-- Table structure for disponibilidades
-- ----------------------------
DROP TABLE IF EXISTS `disponibilidades`;
CREATE TABLE `disponibilidades` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dia_id` int(11) DEFAULT NULL,
  `docente_id` int(11) DEFAULT NULL,
  `hora_inicial` varchar(45) DEFAULT NULL,
  `hora_final` varchar(45) DEFAULT NULL,
  `comentario` varchar(45) DEFAULT NULL,
  `estado` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_dia_disponibilidad_idx` (`dia_id`),
  KEY `fk_docente_disponibilidad_idx` (`docente_id`),
  CONSTRAINT `fk_dia_disponibilidad` FOREIGN KEY (`dia_id`) REFERENCES `dias` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_docente_disponibilidad` FOREIGN KEY (`docente_id`) REFERENCES `docentes` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='	';

-- ----------------------------
-- Records of disponibilidades
-- ----------------------------
BEGIN;
INSERT INTO `disponibilidades` VALUES (8, 1, 5, '07:00', '12:20', '', NULL);
INSERT INTO `disponibilidades` VALUES (9, 2, 5, '07:00', '12:00', 'Pruedo trabajar de corrido', NULL);
INSERT INTO `disponibilidades` VALUES (10, 4, 5, '07:00', '13:00', '', NULL);
COMMIT;

-- ----------------------------
-- Table structure for docentes
-- ----------------------------
DROP TABLE IF EXISTS `docentes`;
CREATE TABLE `docentes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `identificacion` int(11) DEFAULT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  `tipo_documento_id` int(11) DEFAULT NULL,
  `profesion_id` int(11) DEFAULT NULL,
  `nombres` varchar(45) DEFAULT NULL,
  `apellidos` varchar(45) DEFAULT NULL,
  `fecha_nacimiento` varchar(45) DEFAULT NULL,
  `correo` varchar(45) DEFAULT NULL,
  `estado` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fktipo_documento_idx` (`tipo_documento_id`),
  KEY `fkprofesion_docente_idx` (`profesion_id`),
  KEY `fkusuario_docente_idx` (`usuario_id`),
  CONSTRAINT `fkprofesion_docente` FOREIGN KEY (`profesion_id`) REFERENCES `profesiones` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fktipo_documento_docente` FOREIGN KEY (`tipo_documento_id`) REFERENCES `tipoDocumentos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fkusuario_docente` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='		';

-- ----------------------------
-- Records of docentes
-- ----------------------------
BEGIN;
INSERT INTO `docentes` VALUES (1, 1043605421, 1, 1, 4, 'Miguel Angel', 'Lopez Ariza', '9/10/0021', 'lopezarizamiguel@gmail.com', NULL);
INSERT INTO `docentes` VALUES (5, 123456, 1, 1, 4, 'Jorge', 'Salgado', '14/06/0010', 'jorgesalgado@gmail.com', NULL);
COMMIT;

-- ----------------------------
-- Table structure for horarios
-- ----------------------------
DROP TABLE IF EXISTS `horarios`;
CREATE TABLE `horarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(11) DEFAULT NULL,
  `dia_id` int(11) DEFAULT NULL,
  `salon_id` int(11) DEFAULT NULL,
  `programa_id` int(11) DEFAULT NULL,
  `asignatura_id` int(11) DEFAULT NULL,
  `docente_id` int(11) DEFAULT NULL,
  `anno` int(11) DEFAULT NULL,
  `semetre` int(11) DEFAULT NULL,
  `hora_inicial` varchar(45) DEFAULT NULL,
  `hora_final` varchar(45) DEFAULT NULL,
  `estado` tinyint(4) DEFAULT NULL,
  `disponibilidad_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fr_horario_usuario_id_idx` (`id_usuario`),
  KEY `fk_horario_dia_id_idx` (`dia_id`),
  KEY `fk_horario_salon_id_idx` (`salon_id`),
  KEY `fk_horario_programa_idx` (`programa_id`),
  KEY `fk_horario_asignatura_idx` (`asignatura_id`),
  KEY `fk_horario_docente_idx` (`docente_id`),
  KEY `horarios_disponibilidades_FK` (`disponibilidad_id`),
  CONSTRAINT `fk_horario_asignatura` FOREIGN KEY (`asignatura_id`) REFERENCES `asignaturas` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_horario_dia_id` FOREIGN KEY (`dia_id`) REFERENCES `dias` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_horario_docente` FOREIGN KEY (`docente_id`) REFERENCES `docentes` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_horario_programa` FOREIGN KEY (`programa_id`) REFERENCES `programas` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_horario_salon_id` FOREIGN KEY (`salon_id`) REFERENCES `salones` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_horario_usuario_id` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `horarios_disponibilidades_FK` FOREIGN KEY (`disponibilidad_id`) REFERENCES `disponibilidades` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='			';

-- ----------------------------
-- Records of horarios
-- ----------------------------
BEGIN;
INSERT INTO `horarios` VALUES (1, NULL, 1, 8, 6, 1, 5, 2020, NULL, '07:00', '09:00', NULL, 8);
INSERT INTO `horarios` VALUES (2, NULL, 2, 8, 6, 2, 5, 2020, NULL, '07:30', '11:00', NULL, 9);
COMMIT;

-- ----------------------------
-- Table structure for profesiones
-- ----------------------------
DROP TABLE IF EXISTS `profesiones`;
CREATE TABLE `profesiones` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) DEFAULT NULL,
  `estado` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of profesiones
-- ----------------------------
BEGIN;
INSERT INTO `profesiones` VALUES (1, 'Doc. Matematica Pura', 0);
INSERT INTO `profesiones` VALUES (2, 'Lic. en Naturales', 0);
INSERT INTO `profesiones` VALUES (3, 'Ing. Eletrica', 0);
INSERT INTO `profesiones` VALUES (4, 'Ing. de Sistemas', 0);
COMMIT;

-- ----------------------------
-- Table structure for programa_asignatura
-- ----------------------------
DROP TABLE IF EXISTS `programa_asignatura`;
CREATE TABLE `programa_asignatura` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `programa_id` int(11) DEFAULT NULL,
  `asignatura_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_programa_asignatura_idx` (`programa_id`),
  CONSTRAINT `fk_programa_asignatura` FOREIGN KEY (`programa_id`) REFERENCES `programas` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for programas
-- ----------------------------
DROP TABLE IF EXISTS `programas`;
CREATE TABLE `programas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) DEFAULT NULL,
  `estado` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of programas
-- ----------------------------
BEGIN;
INSERT INTO `programas` VALUES (3, 'Enfermeria Superior', 0);
INSERT INTO `programas` VALUES (6, 'Ingenieria de Sistema y computacion', 0);
INSERT INTO `programas` VALUES (8, 'Educacion Fisica', 0);
COMMIT;

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) DEFAULT NULL,
  `estado` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roles
-- ----------------------------
BEGIN;
INSERT INTO `roles` VALUES (1, 'Admin', 1);
INSERT INTO `roles` VALUES (2, 'Docente', 1);
COMMIT;

-- ----------------------------
-- Table structure for salones
-- ----------------------------
DROP TABLE IF EXISTS `salones`;
CREATE TABLE `salones` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) DEFAULT NULL,
  `estado` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of salones
-- ----------------------------
BEGIN;
INSERT INTO `salones` VALUES (8, 'Salon A1', 0);
INSERT INTO `salones` VALUES (9, 'Salon A2', 0);
INSERT INTO `salones` VALUES (10, 'Salon A3', 0);
COMMIT;

-- ----------------------------
-- Table structure for tipoDocumentos
-- ----------------------------
DROP TABLE IF EXISTS `tipoDocumentos`;
CREATE TABLE `tipoDocumentos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) DEFAULT NULL,
  `estado` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='		';

-- ----------------------------
-- Records of tipoDocumentos
-- ----------------------------
BEGIN;
INSERT INTO `tipoDocumentos` VALUES (1, 'Cedula de ciudadania', 0);
INSERT INTO `tipoDocumentos` VALUES (3, 'Tarjeta de Identidad', 0);
COMMIT;

-- ----------------------------
-- Table structure for usuarios
-- ----------------------------
DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `usuario` varchar(45) DEFAULT NULL,
  `clave` varchar(45) DEFAULT NULL,
  `rol_id` int(11) DEFAULT NULL,
  `docente_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_rol_usuario_idx` (`rol_id`),
  KEY `usuarios_docentes_FK` (`docente_id`),
  CONSTRAINT `fk_usuario_rol` FOREIGN KEY (`rol_id`) REFERENCES `roles` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `usuarios_docentes_FK` FOREIGN KEY (`docente_id`) REFERENCES `docentes` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='	';

-- ----------------------------
-- Records of usuarios
-- ----------------------------
BEGIN;
INSERT INTO `usuarios` VALUES (1, 'admin', 'admin', 1, 1);
INSERT INTO `usuarios` VALUES (2, 'jorge', 'jorge', 2, 5);
COMMIT;

-- ----------------------------
-- Procedure structure for actualizar_asignatura
-- ----------------------------
DROP PROCEDURE IF EXISTS `actualizar_asignatura`;
delimiter ;;
CREATE PROCEDURE `mydb`.`actualizar_asignatura`(IN _id INT(11), IN _descripcion VARCHAR(45), IN _creditos INT(11))
BEGIN
	UPDATE asignaturas SET descripcion = _descripcion, creditos = _creditos WHERE id = _id;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for actualizar_dia
-- ----------------------------
DROP PROCEDURE IF EXISTS `actualizar_dia`;
delimiter ;;
CREATE PROCEDURE `mydb`.`actualizar_dia`(IN _id INT(11), IN _descripcion VARCHAR(45))
BEGIN
	UPDATE dias SET descripcion = _descripcion WHERE id = _id;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for actualizar_disponibilidad
-- ----------------------------
DROP PROCEDURE IF EXISTS `actualizar_disponibilidad`;
delimiter ;;
CREATE PROCEDURE `mydb`.`actualizar_disponibilidad`(IN _id INT(11), IN _diaId INT(11), IN _docenteId INT(11), IN _horaInicial VARCHAR(45),
IN _horaFinal VARCHAR(45), IN _comentario VARCHAR(45))
BEGIN
	UPDATE disponibilidades SET 
	dia_id = _diaId,
	docente_id = _docenteId,
	hora_inicial = _horaInicial,
	hora_final = _horaFinal,
	comentario = _comentario
	
	WHERE id = _id;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for actualizar_docente
-- ----------------------------
DROP PROCEDURE IF EXISTS `actualizar_docente`;
delimiter ;;
CREATE PROCEDURE `mydb`.`actualizar_docente`(IN _id INT(11), IN _identificacion INT(11), IN _usuarioId INT(11), IN _tipoDocumento INT(11), IN _profesion INT(11), 
IN _nombre VARCHAR(45), IN _apellidos VARCHAR(45), IN _fechaNacimiento VARCHAR(45), IN _correo VARCHAR(45))
BEGIN
	UPDATE docentes SET 
	identificacion = _identificacion, usuario_id = _usuarioId, 
	tipo_documento_id = _tipoDocumento, profesion_id = _profesion, 
	nombres = _nombre, apellidos = _apellidos, fecha_nacimiento = _fechaNacimiento, correo = _correo
WHERE id = _id; 
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for actualizar_horario
-- ----------------------------
DROP PROCEDURE IF EXISTS `actualizar_horario`;
delimiter ;;
CREATE PROCEDURE `mydb`.`actualizar_horario`(IN _id INT(11),
	IN _DocenteId INT(11),
	IN _ProgramaId INT(11),
	IN _AsignaturaId INT(11),
	IN _SalonId INT(11),
	IN _DiaId INT(11),
	IN _DisponibilidadId INT(11),
	IN _Anno INT(11),
	IN _HoraInicial INT(11),
	IN _HoraFinal INT(11))
BEGIN
	UPDATE horarios SET 
	docente_id = _DocenteId, 
	programa_id = _ProgramaId,
	asignatura_id = _AsignaturaId,
	salon_id = _SalonId,
	dia_id = _DiaId,
	disponibilidad_id = _DisponibilidadId,
	anno = _Anno,
	hora_inicial = _HoraInicial,
	hora_final = _HoraFinal
	WHERE id = _id;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for actualizar_profesion
-- ----------------------------
DROP PROCEDURE IF EXISTS `actualizar_profesion`;
delimiter ;;
CREATE PROCEDURE `mydb`.`actualizar_profesion`(IN _id INT(11), IN _decripcion VARCHAR(45))
BEGIN
	UPDATE profesiones SET descripcion = _decripcion WHERE id = _id;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for actualizar_programa
-- ----------------------------
DROP PROCEDURE IF EXISTS `actualizar_programa`;
delimiter ;;
CREATE PROCEDURE `mydb`.`actualizar_programa`(IN _id INT(11), IN _descripcion VARCHAR(45))
BEGIN
	UPDATE programas SET descripcion = _descripcion WHERE id = _id;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for actualizar_rol
-- ----------------------------
DROP PROCEDURE IF EXISTS `actualizar_rol`;
delimiter ;;
CREATE PROCEDURE `mydb`.`actualizar_rol`(IN _id INT(11), IN _descripcion VARCHAR(45))
BEGIN
	UPDATE roles SET descripcion = _descripcion WHERE id = _id;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for actualizar_salon
-- ----------------------------
DROP PROCEDURE IF EXISTS `actualizar_salon`;
delimiter ;;
CREATE PROCEDURE `mydb`.`actualizar_salon`(IN _id INT(11), IN _descripcion VARCHAR(45))
BEGIN
	UPDATE salones SET descripcion = _descripcion WHERE id = _id;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for actualizar_tipo_documento
-- ----------------------------
DROP PROCEDURE IF EXISTS `actualizar_tipo_documento`;
delimiter ;;
CREATE PROCEDURE `mydb`.`actualizar_tipo_documento`(IN _id INT(11), IN _descripcion VARCHAR(45))
BEGIN
	UPDATE tipoDocumentos SET descripcion = _descripcion WHERE id = _id;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for actualizar_usuario
-- ----------------------------
DROP PROCEDURE IF EXISTS `actualizar_usuario`;
delimiter ;;
CREATE PROCEDURE `mydb`.`actualizar_usuario`(IN _id INT(11), IN _docente INT(11), IN _rol INT(11), IN _usuario VARCHAR(45), IN _clave VARCHAR(45))
BEGIN
	UPDATE usuarios SET docente_id = _docente, rol_id = _rol, usuarios = _usuario, clave = _clave  WHERE id = _id;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for eliminar_asignatura
-- ----------------------------
DROP PROCEDURE IF EXISTS `eliminar_asignatura`;
delimiter ;;
CREATE PROCEDURE `mydb`.`eliminar_asignatura`(IN _id INT(11))
BEGIN
	DELETE FROM asignaturas WHERE id = _id;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for eliminar_dia
-- ----------------------------
DROP PROCEDURE IF EXISTS `eliminar_dia`;
delimiter ;;
CREATE PROCEDURE `mydb`.`eliminar_dia`(IN _id INT(11))
BEGIN
	DELETE FROM dias WHERE id = _id;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for eliminar_disponibilidad
-- ----------------------------
DROP PROCEDURE IF EXISTS `eliminar_disponibilidad`;
delimiter ;;
CREATE PROCEDURE `mydb`.`eliminar_disponibilidad`(IN _id INT(11))
BEGIN
	DELETE FROM disponibilidades WHERE id = _id;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for eliminar_docente
-- ----------------------------
DROP PROCEDURE IF EXISTS `eliminar_docente`;
delimiter ;;
CREATE PROCEDURE `mydb`.`eliminar_docente`(IN _id INT(11))
BEGIN
	DELETE FROM docentes WHERE id = _id;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for eliminar_horario
-- ----------------------------
DROP PROCEDURE IF EXISTS `eliminar_horario`;
delimiter ;;
CREATE PROCEDURE `mydb`.`eliminar_horario`(IN _id INT(11))
BEGIN
	DELETE FROM horarios WHERE id = _id;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for eliminar_profesion
-- ----------------------------
DROP PROCEDURE IF EXISTS `eliminar_profesion`;
delimiter ;;
CREATE PROCEDURE `mydb`.`eliminar_profesion`(IN _id INT(11))
BEGIN
	DELETE FROM profesiones WHERE id = _id;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for eliminar_programa
-- ----------------------------
DROP PROCEDURE IF EXISTS `eliminar_programa`;
delimiter ;;
CREATE PROCEDURE `mydb`.`eliminar_programa`(IN _id INT(11))
BEGIN
	DELETE FROM programas WHERE id=_id;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for eliminar_rol
-- ----------------------------
DROP PROCEDURE IF EXISTS `eliminar_rol`;
delimiter ;;
CREATE PROCEDURE `mydb`.`eliminar_rol`(IN _id INT(11))
BEGIN
	DELETE FROM roles WHERE id = _id;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for eliminar_salon
-- ----------------------------
DROP PROCEDURE IF EXISTS `eliminar_salon`;
delimiter ;;
CREATE PROCEDURE `mydb`.`eliminar_salon`(IN _id INT(11))
BEGIN
	DELETE FROM salones WHERE id = _id;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for eliminar_tipo_documento
-- ----------------------------
DROP PROCEDURE IF EXISTS `eliminar_tipo_documento`;
delimiter ;;
CREATE PROCEDURE `mydb`.`eliminar_tipo_documento`(IN _id INT(11))
BEGIN
	DELETE FROM tipoDocumentos WHERE id = _id;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for eliminar_usuario
-- ----------------------------
DROP PROCEDURE IF EXISTS `eliminar_usuario`;
delimiter ;;
CREATE PROCEDURE `mydb`.`eliminar_usuario`(IN _id INT(11))
BEGIN
	DELETE FROM usuarios WHERE id = _id;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for get_usuario
-- ----------------------------
DROP PROCEDURE IF EXISTS `get_usuario`;
delimiter ;;
CREATE PROCEDURE `mydb`.`get_usuario`(IN _id INT(11))
BEGIN
	SELECT 
	u.id,
	u.usuario,
	u.clave,
	u.docente_id,
	dc.nombres,
	dc.apellidos,
	dc.correo,
	dc.fecha_nacimiento,
	dc.identificacion,
	u.rol_id
FROM usuarios AS u
INNER JOIN docentes AS dc ON dc.id = u.docente_id
WHERE u.id = _id;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for guardar_asignatura
-- ----------------------------
DROP PROCEDURE IF EXISTS `guardar_asignatura`;
delimiter ;;
CREATE PROCEDURE `mydb`.`guardar_asignatura`(IN _descripcion VARCHAR(45), IN _creditos INT(11))
BEGIN
	INSERT INTO asignaturas (descripcion, creditos) VALUES (_descripcion, _creditos);
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for guardar_dia
-- ----------------------------
DROP PROCEDURE IF EXISTS `guardar_dia`;
delimiter ;;
CREATE PROCEDURE `mydb`.`guardar_dia`(IN _descripcion VARCHAR(45), IN _estado BINARY)
BEGIN
	INSERT INTO dias (descripcion, estado) VALUES (_descripcion, _estado);
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for guardar_disponibilidad
-- ----------------------------
DROP PROCEDURE IF EXISTS `guardar_disponibilidad`;
delimiter ;;
CREATE PROCEDURE `mydb`.`guardar_disponibilidad`(IN _diaId INT(11), IN _docenteId INT(11), IN _horaInicial VARCHAR(45),
IN _horaFinal VARCHAR(45), IN _comentario VARCHAR(45))
BEGIN
	INSERT INTO disponibilidades (dia_id, docente_id, hora_inicial, hora_final, comentario) VALUES
	(_diaId,_docenteId,_horaInicial,_horaFinal,_comentario);
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for guardar_docente
-- ----------------------------
DROP PROCEDURE IF EXISTS `guardar_docente`;
delimiter ;;
CREATE PROCEDURE `mydb`.`guardar_docente`(IN _identificacion INT(11), IN _usuarioId INT(11), IN _tipoDocumento INT(11), IN _profesionId INT(11),
IN _nombres VARCHAR(45), IN _apellidos VARCHAR(45), IN _fechaNacimiento VARCHAR(45), IN _correo VARCHAR(45))
BEGIN
	INSERT INTO docentes (identificacion, usuario_id, tipo_documento_id, profesion_id, nombres, apellidos, fecha_nacimiento, correo)
	VALUES (_identificacion, _usuarioId, _tipoDocumento, _profesionId, _nombres, _apellidos, _fechaNacimiento, _correo);
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for guardar_horario
-- ----------------------------
DROP PROCEDURE IF EXISTS `guardar_horario`;
delimiter ;;
CREATE PROCEDURE `mydb`.`guardar_horario`(IN _DocenteId INT(11),
	IN _ProgramaId INT(11),
	IN _AsignaturaId INT(11),
	IN _SalonId INT(11),
	IN _DiaId INT(11),
	IN _DisponibilidadId INT(11),
	IN _Anno INT(11),
	IN _HoraInicial VARCHAR(45),
	IN _HoraFinal VARCHAR(45))
BEGIN
	INSERT INTO horarios (docente_id, programa_id, asignatura_id, salon_id, dia_id, disponibilidad_id, anno, hora_inicial, hora_final)
	VALUES (_DocenteId, _ProgramaId, _AsignaturaId, _SalonId, _DiaId, _DisponibilidadId, _Anno, _HoraInicial, _HoraFinal);
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for guardar_profesion
-- ----------------------------
DROP PROCEDURE IF EXISTS `guardar_profesion`;
delimiter ;;
CREATE PROCEDURE `mydb`.`guardar_profesion`(IN _descripcion VARCHAR(45), IN _estado BINARY)
BEGIN
	INSERT INTO profesiones (descripcion, estado) VALUES (_descripcion, _estado);
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for guardar_programa
-- ----------------------------
DROP PROCEDURE IF EXISTS `guardar_programa`;
delimiter ;;
CREATE PROCEDURE `mydb`.`guardar_programa`(IN _descripcion VARCHAR(100), IN _estado BINARY)
BEGIN
	INSERT INTO programas (descripcion, estado) VALUES (_descripcion, _estado);
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for guardar_rol
-- ----------------------------
DROP PROCEDURE IF EXISTS `guardar_rol`;
delimiter ;;
CREATE PROCEDURE `mydb`.`guardar_rol`(IN _descripcion VARCHAR(45), IN _estado BINARY)
BEGIN
	INSERT INTO roles (descripcion, estado) VALUES (_descripcion, _estado);
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for guardar_salon
-- ----------------------------
DROP PROCEDURE IF EXISTS `guardar_salon`;
delimiter ;;
CREATE PROCEDURE `mydb`.`guardar_salon`(IN _descripcion VARCHAR(45), IN _estado BINARY)
BEGIN
	INSERT INTO salones (descripcion, estado) VALUES (_descripcion, _estado);
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for guardar_tipo_documento
-- ----------------------------
DROP PROCEDURE IF EXISTS `guardar_tipo_documento`;
delimiter ;;
CREATE PROCEDURE `mydb`.`guardar_tipo_documento`(IN _descripcion VARCHAR(45), IN _estado BINARY)
BEGIN
	INSERT INTO tipoDocumentos (descripcion, estado) VALUES (_descripcion, _estado);
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for guardar_usuario
-- ----------------------------
DROP PROCEDURE IF EXISTS `guardar_usuario`;
delimiter ;;
CREATE PROCEDURE `mydb`.`guardar_usuario`(IN _docente INT(11), IN _rol INT(11), IN _usuario VARCHAR(45), IN _clave VARCHAR(45))
BEGIN
	INSERT INTO usuarios (usuario, clave, rol_id, docente_id) VALUES(
		_usuario, _clave, _rol, _docente
	);
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for listar_asignaturas
-- ----------------------------
DROP PROCEDURE IF EXISTS `listar_asignaturas`;
delimiter ;;
CREATE PROCEDURE `mydb`.`listar_asignaturas`()
BEGIN
	SELECT * FROM asignaturas;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for listar_dias
-- ----------------------------
DROP PROCEDURE IF EXISTS `listar_dias`;
delimiter ;;
CREATE PROCEDURE `mydb`.`listar_dias`()
BEGIN
	SELECT id, descripcion FROM dias;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for listar_disponibilidades
-- ----------------------------
DROP PROCEDURE IF EXISTS `listar_disponibilidades`;
delimiter ;;
CREATE PROCEDURE `mydb`.`listar_disponibilidades`()
BEGIN
	SELECT 
	dis.id, 
	dis.dia_id,
	dis.docente_id,
	dis.hora_inicial,
	dis.hora_final,
	dis.comentario,
	d.descripcion as dia
FROM disponibilidades AS dis
INNER JOIN dias AS d ON d.id = dis.dia_id;

END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for listar_docentes
-- ----------------------------
DROP PROCEDURE IF EXISTS `listar_docentes`;
delimiter ;;
CREATE PROCEDURE `mydb`.`listar_docentes`()
BEGIN
SELECT
	dc.id,
	dc.identificacion,
	dc.usuario_id,
	dc.tipo_documento_id,
	dc.profesion_id,
	dc.nombres,
	dc.apellidos,
	dc.fecha_nacimiento,
	dc.correo,
	td.descripcion AS tipoDocumento,
	pr.descripcion AS profesion
FROM docentes AS dc
INNER JOIN tipoDocumentos AS td ON td.id = dc.tipo_documento_id
INNER JOIN profesiones AS pr ON pr.id = dc.profesion_id;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for listar_horario
-- ----------------------------
DROP PROCEDURE IF EXISTS `listar_horario`;
delimiter ;;
CREATE PROCEDURE `mydb`.`listar_horario`()
BEGIN
	SELECT 
	ho.id,
	ho.docente_id,
	ho.programa_id,
	ho.asignatura_id,
	ho.salon_id,
	ho.dia_id,
	ho.disponibilidad_id,
	ho.anno,
	ho.hora_inicial,
	ho.hora_final,
	do.nombres,
	do.apellidos,
	po.descripcion AS programa,
	ag.descripcion AS asignatura,
	sa.descripcion AS salon,
	di.descripcion AS dia
	FROM horarios AS ho
	INNER JOIN docentes AS do ON do.id = ho.docente_id
	INNER JOIN programas AS po ON po.id = ho.programa_id
	INNER JOIN asignaturas AS ag ON ag.id = ho.asignatura_id
	INNER JOIN salones AS sa ON sa.id = ho.salon_id
	INNER JOIN dias AS di ON di.id = ho.dia_id;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for listar_profesiones
-- ----------------------------
DROP PROCEDURE IF EXISTS `listar_profesiones`;
delimiter ;;
CREATE PROCEDURE `mydb`.`listar_profesiones`()
BEGIN
	SELECT id, descripcion FROM profesiones;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for listar_programas
-- ----------------------------
DROP PROCEDURE IF EXISTS `listar_programas`;
delimiter ;;
CREATE PROCEDURE `mydb`.`listar_programas`()
BEGIN
	SELECT id,descripcion,estado FROM programas;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for listar_roles
-- ----------------------------
DROP PROCEDURE IF EXISTS `listar_roles`;
delimiter ;;
CREATE PROCEDURE `mydb`.`listar_roles`()
BEGIN
	SELECT id, descripcion FROM roles;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for listar_salones
-- ----------------------------
DROP PROCEDURE IF EXISTS `listar_salones`;
delimiter ;;
CREATE PROCEDURE `mydb`.`listar_salones`()
BEGIN
	SELECT id, descripcion FROM salones;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for listar_tipo_documentos
-- ----------------------------
DROP PROCEDURE IF EXISTS `listar_tipo_documentos`;
delimiter ;;
CREATE PROCEDURE `mydb`.`listar_tipo_documentos`()
BEGIN
	SELECT id, descripcion FROM tipoDocumentos;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for listar_usuarios
-- ----------------------------
DROP PROCEDURE IF EXISTS `listar_usuarios`;
delimiter ;;
CREATE PROCEDURE `mydb`.`listar_usuarios`()
BEGIN
	SELECT u.id, u.docente_id, u.rol_id, u.usuario, u.clave, dc.nombres, dc.apellidos, ro.descripcion  FROM usuarios as u
	INNER JOIN docentes AS dc ON dc.id = u.docente_id
	INNER JOIN roles AS ro ON ro.id = u.rol_id;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for list_por_docente
-- ----------------------------
DROP PROCEDURE IF EXISTS `list_por_docente`;
delimiter ;;
CREATE PROCEDURE `mydb`.`list_por_docente`(IN _id INT(11))
BEGIN
	SELECT 
	dis.id, 
	dis.dia_id,
	dis.docente_id,
	dis.hora_inicial,
	dis.hora_final,
	dis.comentario,
	d.descripcion as dia
FROM disponibilidades AS dis
INNER JOIN dias AS d ON d.id = dis.dia_id
WHERE dis.docente_id = _id;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
