-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema argazkiLehiaketa
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema argazkiLehiaketa
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `argazkiLehiaketa` DEFAULT CHARACTER SET utf8 ;
USE `argazkiLehiaketa` ;

-- -----------------------------------------------------
-- Table `argazkiLehiaketa`.`Lehiaketa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `argazkiLehiaketa`.`Lehiaketa` (
  `lehiaketa_ID` INT NOT NULL AUTO_INCREMENT,
  `izena` VARCHAR(45) NOT NULL,
  `data_hasiera` DATE NOT NULL,
  `data_bukaera` DATE NOT NULL,
  `deskribapena` VARCHAR(300) NOT NULL,
  `logotipoa` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`lehiaketa_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `argazkiLehiaketa`.`Erabiltzailea`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `argazkiLehiaketa`.`Erabiltzailea` ( 
  `erabiltzailea_ID` INT NOT NULL AUTO_INCREMENT,
  `izena` VARCHAR(45) NOT NULL,
  `abizena` VARCHAR(45) NOT NULL,
  `korreoa` VARCHAR(60) NOT NULL,
  `erabiltzaile_izena` VARCHAR(45) NOT NULL,
  `pasahitza` VARCHAR(20) NOT NULL,
  `mota` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`erabiltzailea_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `argazkiLehiaketa`.`Atala`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `argazkiLehiaketa`.`Atala` (
  `atala_ID` INT NOT NULL AUTO_INCREMENT,
  `izena` VARCHAR(45) NOT NULL,
  `saria` VARCHAR(45) NOT NULL,
  `lehiaketa_ID` INT NOT NULL,
  PRIMARY KEY (`atala_ID`),
  INDEX `fk_Atala_Lehiaketa1_idx` (`lehiaketa_ID` ASC) VISIBLE,
  CONSTRAINT `fk_Atala_Lehiaketa1`
    FOREIGN KEY (`lehiaketa_ID`)
    REFERENCES `argazkiLehiaketa`.`Lehiaketa` (`lehiaketa_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `argazkiLehiaketa`.`Argazkia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `argazkiLehiaketa`.`Argazkia` (
  `irudia_ID` INT NOT NULL AUTO_INCREMENT,
  `kokapena` VARCHAR(300) NOT NULL,
  `botoak` INT NOT NULL,
  `egilea` INT NOT NULL,
  `atala` INT NOT NULL,
  PRIMARY KEY (`irudia_ID`),
  INDEX `fk_Argazkia_Erabiltzailea1_idx` (`egilea` ASC) VISIBLE,
  INDEX `fk_Argazkia_Atala1_idx` (`atala` ASC) VISIBLE,
  CONSTRAINT `fk_Argazkia_Erabiltzailea1`
    FOREIGN KEY (`egilea`)
    REFERENCES `argazkiLehiaketa`.`Erabiltzailea` (`erabiltzailea_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Argazkia_Atala1`
    FOREIGN KEY (`atala`)
    REFERENCES `argazkiLehiaketa`.`Atala` (`atala_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `argazkiLehiaketa`.`Fasea`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `argazkiLehiaketa`.`Fasea` (
  `fasea_ID` INT NOT NULL AUTO_INCREMENT,
  `etapa` VARCHAR(45) NOT NULL,
  `data_hasiera` DATE NOT NULL,
  `data_bukaera` DATE NOT NULL,
  `lehiaketa` INT NOT NULL,
  PRIMARY KEY (`fasea_ID`),
  INDEX `fk_Fasea_Lehiaketa1_idx` (`lehiaketa` ASC) VISIBLE,
  CONSTRAINT `fk_Fasea_Lehiaketa1`
    FOREIGN KEY (`lehiaketa`)
    REFERENCES `argazkiLehiaketa`.`Lehiaketa` (`lehiaketa_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `argazkiLehiaketa`.`Parte_hartu`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `argazkiLehiaketa`.`Parte_hartu` (
  `erabiltzailea` INT NOT NULL,
  `lehiaketa` INT NOT NULL,
  PRIMARY KEY (`erabiltzailea`, `lehiaketa`),
  INDEX `fk_Erabiltzailea_has_Lehiaketa_Lehiaketa1_idx` (`lehiaketa` ASC) VISIBLE,
  INDEX `fk_Erabiltzailea_has_Lehiaketa_Erabiltzailea_idx` (`erabiltzailea` ASC) VISIBLE,
  CONSTRAINT `fk_Erabiltzailea_has_Lehiaketa_Erabiltzailea`
    FOREIGN KEY (`erabiltzailea`)
    REFERENCES `argazkiLehiaketa`.`Erabiltzailea` (`erabiltzailea_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Erabiltzailea_has_Lehiaketa_Lehiaketa1`
    FOREIGN KEY (`lehiaketa`)
    REFERENCES `argazkiLehiaketa`.`Lehiaketa` (`lehiaketa_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `argazkiLehiaketa`.`Bozkatu`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `argazkiLehiaketa`.`Bozkatu` (
  `Epailea` INT NOT NULL,
  `Argazkia` INT NOT NULL,
  PRIMARY KEY (`Epailea`, `Argazkia`),
  INDEX `fk_Erabiltzailea_has_Argazkia_Argazkia1_idx` (`Argazkia` ASC) VISIBLE,
  INDEX `fk_Erabiltzailea_has_Argazkia_Erabiltzailea1_idx` (`Epailea` ASC) VISIBLE,
  CONSTRAINT `fk_Erabiltzailea_has_Argazkia_Erabiltzailea1`
    FOREIGN KEY (`Epailea`)
    REFERENCES `argazkiLehiaketa`.`Erabiltzailea` (`erabiltzailea_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Erabiltzailea_has_Argazkia_Argazkia1`
    FOREIGN KEY (`Argazkia`)
    REFERENCES `argazkiLehiaketa`.`Argazkia` (`irudia_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
