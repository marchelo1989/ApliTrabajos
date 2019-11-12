/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  march
 * Created: 09-11-2019
 * Verción: v1.9.7
 */
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema Trabajos
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `Trabajos` ;

-- -----------------------------------------------------
-- Schema Trabajos
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Trabajos` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci ;
USE `Trabajos` ;

-- -----------------------------------------------------
-- Table `Trabajos`.`Cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Trabajos`.`Cliente` (
  `idCliente` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(25) NOT NULL,
  `celular` VARCHAR(13) NULL,
  `correo` VARCHAR(45) NULL,
  PRIMARY KEY (`idCliente`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Trabajos`.`Trabajo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Trabajos`.`Trabajo` (
  `idTrabajo` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `descripcion` VARCHAR(200) NULL,
  `abono` INT NULL,
  `pago` INT NULL,
  `total` INT NULL,
  `fechaInicio` DATE NOT NULL,
  `fechaTermino` DATE NULL,
  `Cliente_idCliente` INT NOT NULL,
  PRIMARY KEY (`idTrabajo`),
  INDEX `fk_trabajo_Cliente_idx` (`Cliente_idCliente` ASC),
  CONSTRAINT `fk_trabajo_Cliente`
    FOREIGN KEY (`Cliente_idCliente`)
    REFERENCES `Trabajos`.`Cliente` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Trabajos`.`AudiCliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Trabajos`.`AudiCliente` (
  `idCliente` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(25) NULL,
  `celular` VARCHAR(13) NULL,
  `correo` VARCHAR(45) NULL,
  `fecha` DATETIME NULL,
  `proceso` VARCHAR(25) NULL,
  `usuario_bd` VARCHAR(25) NULL,
  `Cliente_idCliente` INT NULL,
  PRIMARY KEY (`idCliente`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Trabajos`.`AudiTrabajo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Trabajos`.`AudiTrabajo` (
  `idAudiTrabajo` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `descripcion` VARCHAR(200) NULL,
  `abono` INT NULL,
  `pago` INT NULL,
  `total` INT NULL,
  `fechaInicio` DATE NULL,
  `fechaTermino` DATE NULL,
  `idCliente` INT NULL,
  `fecha` DATETIME NULL,
  `proceso` VARCHAR(25) NULL,
  `usuario_bd` VARCHAR(25) NULL,
  `Trabajo_IdTrabajo` INT NULL,
  PRIMARY KEY (`idAudiTrabajo`))
ENGINE = InnoDB;

USE `Trabajos` ;

-- -----------------------------------------------------
-- procedure ProClienteInsertar
-- -----------------------------------------------------

DELIMITER $$
USE `Trabajos`$$
CREATE PROCEDURE `ProClienteInsertar` (in Nombre varchar(25),in Celular varchar(13),in Correo varchar(45))
BEGIN
insert into Cliente(nombre,celular,correo)
value(Nombre,Celular,Correo);
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure ProClienteModificar
-- -----------------------------------------------------

DELIMITER $$
USE `Trabajos`$$
CREATE PROCEDURE `ProClienteModificar` (in Nombre varchar(25),in Celular varchar(13),in Correo varchar(45),in id int)
BEGIN
update
`Cliente`
set
nombre=Nombre,
celular=Celular,
correo=Correo
WHERE 
idCliente=id;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure ProClienteEliminar
-- -----------------------------------------------------

DELIMITER $$
USE `Trabajos`$$
CREATE PROCEDURE `ProClienteEliminar` (in id int)
BEGIN
delete
from
`Cliente`
where idCliente=id;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure ProClienteListarAll
-- -----------------------------------------------------

DELIMITER $$
USE `Trabajos`$$
CREATE PROCEDURE `ProClienteListarAll` (in Desde bigint,in Cuantos bigint,in Busqueda varchar(200))
reads sql data
BEGIN
select 
right(idCliente, 5) as idCliente,
nombre, celular, correo
from `Cliente`
where 
nombre like concat('%',Busqueda,'%') or celular like concat('%',Busqueda,'%') or 
correo like concat('%',Busqueda,'%')
LIMIT desde, cuantos;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure ProClienteCuantos
-- -----------------------------------------------------

DELIMITER $$
USE `Trabajos`$$
CREATE PROCEDURE `ProClienteCuantos` (in Busqueda varchar(200))
reads sql data
deterministic
BEGIN
select 
count(*) as cuantos
from
`Cliente`
where
nombre like concat('%',Busqueda,'%') or celular like concat('%',Busqueda,'%') or 
correo like concat('%',Busqueda,'%');
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure ProClienteBuscarID
-- -----------------------------------------------------

DELIMITER $$
USE `Trabajos`$$
CREATE PROCEDURE `ProClienteBuscarID` (in id bigint)
reads sql data
BEGIN
select right(idCliente, 5) as idCliente,
nombre,
celular,
correo
from
`Cliente`
where idCliente=id;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure ProTrabajosCuantos
-- -----------------------------------------------------

DELIMITER $$
USE `Trabajos`$$
CREATE PROCEDURE `ProTrabajosCuantos` (in ID int)
reads sql data
deterministic
BEGIN
select 
count(*) as cuantos
from
`Trabajo`
where Cliente_idCliente=ID;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure ProTrabajoListarIdT
-- -----------------------------------------------------

DELIMITER $$
USE `Trabajos`$$
CREATE PROCEDURE `ProTrabajoListarIdT` (in ID INT(11))
reads sql data
BEGIN
select 
right(idTrabajo, 5) as idTrabajo,
nombre, descripcion, abono, pago, total, fechaInicio, ifnull(fechaTermino,'0000-00-00') as fechaTermino, Cliente_idCliente
from `Trabajo`
where idTrabajo=ID;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure ProTrabajoInsertar
-- -----------------------------------------------------

DELIMITER $$
USE `Trabajos`$$
CREATE PROCEDURE `ProTrabajoInsertar` (in Nombre varchar(45),in Descripcion varchar(200),in Abono int,in Pago int,in Total int,in FechaInicio Date,in FechaTermino Date,in ClienteId int)
BEGIN
INSERT INTO Trabajo(nombre, descripcion, abono, pago, total, fechaInicio, fechaTermino, Cliente_idCliente) 
VALUES (Nombre,Descripcion,Abono,Pago,Total,FechaInicio,FechaTermino,ClienteId);
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure ProTrabajosListarIdC
-- -----------------------------------------------------

DELIMITER $$
USE `Trabajos`$$
CREATE PROCEDURE `ProTrabajosListarIdC` (in Desde bigint,in Cuantos bigint,in Busqueda varchar(200),in ID int)
BEGIN
select 
right(idTrabajo, 5) as idTrabajo,
nombre, descripcion, abono, pago, total, fechaInicio, fechaTermino, Cliente_idCliente
from `Trabajo`
where Cliente_idCliente=ID 
LIMIT desde, cuantos;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure ProTrabajosModificar
-- -----------------------------------------------------

DELIMITER $$
USE `Trabajos`$$
CREATE PROCEDURE `ProTrabajosModificar` (in Nombre varchar(25),in Descripcion varchar(200),in Abono int,in Pago int,in Total int,in FechaInicio date,in FechaTermino date,in IdCliente int,in ID int)
BEGIN
UPDATE 
`Trabajo` 
SET 
nombre=Nombre,
descripcion=Descripcion,
abono=Abono,
pago=Pago,
total=Total,
fechaInicio=FechaInicio,
fechaTermino=FechaTermino,
Cliente_idCliente=IdCliente 
WHERE
idTrabajo=ID;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure ProTrabajoEliminar
-- -----------------------------------------------------

DELIMITER $$
USE `Trabajos`$$
CREATE PROCEDURE `ProTrabajoEliminar` (in ID int)
BEGIN
delete
from
`Trabajo`
where idTrabajo=ID;
END$$

DELIMITER ;
USE `Trabajos`;

DELIMITER $$
USE `Trabajos`$$
CREATE DEFINER = CURRENT_USER TRIGGER `Trabajos`.`insertAudiC` AFTER INSERT ON `Cliente` FOR EACH ROW
BEGIN
insert
into
AudiCliente(nombre,celular,correo,fecha,proceso,usuario_bd,Cliente_IdCliente)
value(new.nombre,new.celular,new.correo,NOW(),"Insertado",CURRENT_USER(),new.idCliente);
END$$

USE `Trabajos`$$
CREATE DEFINER = CURRENT_USER TRIGGER `Trabajos`.`updateAudiC` AFTER UPDATE ON `Cliente` FOR EACH ROW
BEGIN
insert
into
AudiCliente(nombre,celular,correo,fecha,proceso,usuario_bd,Cliente_IdCliente)
value(new.nombre,new.celular,new.correo,NOW(),"Modificado",CURRENT_USER(),new.idCliente);
END$$

USE `Trabajos`$$
CREATE DEFINER = CURRENT_USER TRIGGER `Trabajos`.`deleteAudiC` AFTER DELETE ON `Cliente` FOR EACH ROW
BEGIN
insert
into
AudiCliente(nombre,celular,correo,fecha,proceso,usuario_bd,Cliente_IdCliente)
value(old.nombre,old.celular,old.correo,NOW(),"Eliminado",CURRENT_USER(),old.idCliente);
END$$

USE `Trabajos`$$
CREATE DEFINER = CURRENT_USER TRIGGER `Trabajos`.`insertAudiT` AFTER INSERT ON `Trabajo` FOR EACH ROW
BEGIN
insert
into
AudiTrabajo(nombre,descripcion,abono,pago,total,fechaInicio,FechaTermino,idCliente,fecha,proceso,usuario_bd,Trabajo_idTrabajo)
value(new.nombre,new.descripcion,new.abono,new.pago,new.total,new.fechaInicio,new.FechaTermino,new.Cliente_idCliente, NOW(), "Insertado", CURRENT_USER(),new.idTrabajo);
END$$

USE `Trabajos`$$
CREATE DEFINER = CURRENT_USER TRIGGER `Trabajos`.`updateAudiT` AFTER UPDATE ON `Trabajo` FOR EACH ROW
BEGIN
insert
into
AudiTrabajo(nombre,descripcion,abono,pago,total,fechaInicio,FechaTermino,idCliente,fecha,proceso,usuario_bd,Trabajo_idTrabajo)
value(new.nombre,new.descripcion,new.abono,new.pago,new.total,new.fechaInicio,new.FechaTermino,new.Cliente_idCliente, NOW(), "Modificado", CURRENT_USER(),new.idTrabajo);
END$$

USE `Trabajos`$$
CREATE DEFINER = CURRENT_USER TRIGGER `Trabajos`.`deleteAudiT` AFTER DELETE ON `Trabajo` FOR EACH ROW
BEGIN
insert
into
AudiTrabajo(nombre,descripcion,abono,pago,total,fechaInicio,FechaTermino,idCliente,fecha,proceso,usuario_bd,Trabajo_idTrabajo)
value(old.nombre,old.descripcion,old.abono,old.pago,old.total,old.fechaInicio,old.FechaTermino,old.Cliente_idCliente, NOW(), "Modificado", CURRENT_USER(),old.idTrabajo);
END$$


DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


