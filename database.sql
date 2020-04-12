-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`tipoDocumentos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tipoDocumentos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(45) NULL,
  `estado` TINYINT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '		';


-- -----------------------------------------------------
-- Table `mydb`.`profesiones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`profesiones` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(45) NULL,
  `estado` TINYINT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`roles` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(45) NULL,
  `estado` TINYINT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`usuarios` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `usuario` VARCHAR(45) NULL,
  `clave` VARCHAR(45) NULL,
  `rol_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_rol_usuario_idx` (`rol_id` ASC),
  CONSTRAINT `fk_usuario_rol`
    FOREIGN KEY (`rol_id`)
    REFERENCES `mydb`.`roles` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
COMMENT = '	';


-- -----------------------------------------------------
-- Table `mydb`.`salones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`salones` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(45) NULL,
  `estado` TINYINT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`programas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`programas` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(45) NULL,
  `estado` TINYINT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`asignaturas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`asignaturas` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(45) NULL,
  `creditor` INT NULL,
  `estado` TINYINT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`dias`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`dias` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(45) NULL,
  `estado` TINYINT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`docentes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`docentes` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `identificacion` INT NULL,
  `usuario_id` INT NULL,
  `tipo_documento_id` INT NULL,
  `profesion_id` INT NULL,
  `nombres` VARCHAR(45) NULL,
  `apellidos` VARCHAR(45) NULL,
  `fecha_nacimiento` DATE NULL,
  `correo` VARCHAR(45) NULL,
  `estado` TINYINT NULL,
  PRIMARY KEY (`id`),
  INDEX `fktipo_documento_idx` (`tipo_documento_id` ASC),
  INDEX `fkprofesion_docente_idx` (`profesion_id` ASC),
  INDEX `fkusuario_docente_idx` (`usuario_id` ASC),
  CONSTRAINT `fktipo_documento_docente`
    FOREIGN KEY (`tipo_documento_id`)
    REFERENCES `mydb`.`tipoDocumentos` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fkprofesion_docente`
    FOREIGN KEY (`profesion_id`)
    REFERENCES `mydb`.`profesiones` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fkusuario_docente`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `mydb`.`usuarios` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
COMMENT = '		';


-- -----------------------------------------------------
-- Table `mydb`.`disponibilidades`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`disponibilidades` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `dia_id` INT NULL,
  `docente_id` INT NULL,
  `hora_inicial` TIME NULL,
  `hora_final` TIME NULL,
  `comentario` VARCHAR(45) NULL,
  `estado` TINYINT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_dia_disponibilidad_idx` (`dia_id` ASC),
  INDEX `fk_docente_disponibilidad_idx` (`docente_id` ASC),
  CONSTRAINT `fk_dia_disponibilidad`
    FOREIGN KEY (`dia_id`)
    REFERENCES `mydb`.`dias` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_docente_disponibilidad`
    FOREIGN KEY (`docente_id`)
    REFERENCES `mydb`.`docentes` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
COMMENT = '	';


-- -----------------------------------------------------
-- Table `mydb`.`horarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`horarios` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_usuario` INT NULL,
  `dia_id` INT NULL,
  `salon_id` INT NULL,
  `programa_id` INT NULL,
  `asignatura_id` INT NULL,
  `docente_id` INT NULL,
  `anno` INT NULL,
  `semetre` INT NULL,
  `hora_inicial` TIME NULL,
  `hora_final` TIME NULL,
  `estado` TINYINT NULL,
  PRIMARY KEY (`id`),
  INDEX `fr_horario_usuario_id_idx` (`id_usuario` ASC),
  INDEX `fk_horario_dia_id_idx` (`dia_id` ASC),
  INDEX `fk_horario_salon_id_idx` (`salon_id` ASC),
  INDEX `fk_horario_programa_idx` (`programa_id` ASC),
  INDEX `fk_horario_asignatura_idx` (`asignatura_id` ASC),
  INDEX `fk_horario_docente_idx` (`docente_id` ASC),
  CONSTRAINT `fk_horario_usuario_id`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `mydb`.`usuarios` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_horario_dia_id`
    FOREIGN KEY (`dia_id`)
    REFERENCES `mydb`.`dias` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_horario_salon_id`
    FOREIGN KEY (`salon_id`)
    REFERENCES `mydb`.`salones` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_horario_programa`
    FOREIGN KEY (`programa_id`)
    REFERENCES `mydb`.`programas` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_horario_asignatura`
    FOREIGN KEY (`asignatura_id`)
    REFERENCES `mydb`.`asignaturas` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_horario_docente`
    FOREIGN KEY (`docente_id`)
    REFERENCES `mydb`.`docentes` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
COMMENT = '			';


-- -----------------------------------------------------
-- Table `mydb`.`programa_asignatura`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`programa_asignatura` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `programa_id` INT NULL,
  `asignatura_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_programa_asignatura_idx` (`programa_id` ASC),
  CONSTRAINT `fk_programa_asignatura`
    FOREIGN KEY (`programa_id`)
    REFERENCES `mydb`.`programas` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
