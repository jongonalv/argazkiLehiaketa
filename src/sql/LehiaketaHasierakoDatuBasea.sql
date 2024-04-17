-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Lehiaketa
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Lehiaketa
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Lehiaketa` DEFAULT CHARACTER SET utf8 ;
USE `Lehiaketa` ;

-- -----------------------------------------------------
-- Table `Lehiaketa`.`Lehiaketa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Lehiaketa`.`Lehiaketa` (
  `lehiaketa_ID` INT NOT NULL,
  `lehiaketa_izena` VARCHAR(45) NOT NULL,
  `data_hasiera` DATE NOT NULL,
  `data_bukaera` DATE NOT NULL,
  `Deskribapena` VARCHAR(300) NOT NULL,
  `helbidea_logotipoa` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`lehiaketa_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Lehiaketa`.`Erabiltzailea`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Lehiaketa`.`Erabiltzailea` (
  `erabiltzaile_ID` INT NOT NULL,
  `izena` VARCHAR(45) NOT NULL,
  `abizena` VARCHAR(45) NOT NULL,
  `erabiltzaile_izena` VARCHAR(20) NOT NULL,
  `pasahitza` VARCHAR(20) NOT NULL,
  `epailea_izan` VARCHAR(3) NOT NULL,
  PRIMARY KEY (`erabiltzaile_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Lehiaketa`.`Kudeatzaileak`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Lehiaketa`.`Kudeatzaileak` (
  `kudeatzaile_ID` INT NOT NULL,
  `erabiltzailea` VARCHAR(45) NOT NULL,
  `korreoa` VARCHAR(45) NOT NULL,
  `administratzailea` INT NOT NULL,
  PRIMARY KEY (`kudeatzaile_ID`),
  INDEX `fk_Kudeatzaileak_Erabiltzailea1_idx` (`administratzailea` ASC) VISIBLE,
  CONSTRAINT `fk_Kudeatzaileak_Erabiltzailea1`
    FOREIGN KEY (`administratzailea`)
    REFERENCES `Lehiaketa`.`Erabiltzailea` (`erabiltzaile_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Lehiaketa`.`Atala`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Lehiaketa`.`Atala` (
  `atala_ID` INT NOT NULL,
  `izena` VARCHAR(45) NOT NULL,
  `saria` VARCHAR(45) NOT NULL,
  `helbidea_Irudiak` VARCHAR(255) NOT NULL,
  `argazki_maximo` INT NOT NULL,
  `lehiaketa_ID` INT NOT NULL,
  `Irabazlea` INT NOT NULL,
  PRIMARY KEY (`atala_ID`),
  INDEX `fk_Atalak_Lehiaketa1_idx` (`lehiaketa_ID` ASC) VISIBLE,
  INDEX `fk_Atalak_Erabiltzailea1_idx` (`Irabazlea` ASC) VISIBLE,
  CONSTRAINT `fk_Atalak_Lehiaketa1`
    FOREIGN KEY (`lehiaketa_ID`)
    REFERENCES `Lehiaketa`.`Lehiaketa` (`lehiaketa_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Atalak_Erabiltzailea1`
    FOREIGN KEY (`Irabazlea`)
    REFERENCES `Lehiaketa`.`Erabiltzailea` (`erabiltzaile_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Lehiaketa`.`Parte_hartu`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Lehiaketa`.`Parte_hartu` (
  `Erabiltzaileak_erabiltzaile_ID` INT NOT NULL,
  `Lehiaketa_lehiaketa_ID` INT NOT NULL,
  PRIMARY KEY (`Erabiltzaileak_erabiltzaile_ID`, `Lehiaketa_lehiaketa_ID`),
  INDEX `fk_Erabiltzaileak_has_Lehiaketa_Lehiaketa1_idx` (`Lehiaketa_lehiaketa_ID` ASC) VISIBLE,
  INDEX `fk_Erabiltzaileak_has_Lehiaketa_Erabiltzaileak_idx` (`Erabiltzaileak_erabiltzaile_ID` ASC) VISIBLE,
  CONSTRAINT `fk_Erabiltzaileak_has_Lehiaketa_Erabiltzaileak`
    FOREIGN KEY (`Erabiltzaileak_erabiltzaile_ID`)
    REFERENCES `Lehiaketa`.`Erabiltzailea` (`erabiltzaile_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Erabiltzaileak_has_Lehiaketa_Lehiaketa1`
    FOREIGN KEY (`Lehiaketa_lehiaketa_ID`)
    REFERENCES `Lehiaketa`.`Lehiaketa` (`lehiaketa_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Lehiaketa`.`Faseak`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Lehiaketa`.`Faseak` (
  `ID_Fasea` INT NOT NULL,
  `etapa` VARCHAR(45) NOT NULL,
  `data_hasiera` DATE NOT NULL,
  `data_bukaera` DATE NOT NULL,
  `Lehiaketa_ID` INT NOT NULL,
  PRIMARY KEY (`ID_Fasea`),
  INDEX `fk_Faseak_Lehiaketa1_idx` (`Lehiaketa_ID` ASC) VISIBLE,
  CONSTRAINT `fk_Faseak_Lehiaketa1`
    FOREIGN KEY (`Lehiaketa_ID`)
    REFERENCES `Lehiaketa`.`Lehiaketa` (`lehiaketa_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Lehiaketa`.`Kudeatzaileak_has_Lehiaketa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Lehiaketa`.`Kudeatzaileak_has_Lehiaketa` (
  `Kudeatzaileak_kudeatzaile_ID` INT NOT NULL,
  `Lehiaketa_lehiaketa_ID` INT NOT NULL,
  PRIMARY KEY (`Kudeatzaileak_kudeatzaile_ID`, `Lehiaketa_lehiaketa_ID`),
  INDEX `fk_Kudeatzaileak_has_Lehiaketa_Lehiaketa1_idx` (`Lehiaketa_lehiaketa_ID` ASC) VISIBLE,
  INDEX `fk_Kudeatzaileak_has_Lehiaketa_Kudeatzaileak1_idx` (`Kudeatzaileak_kudeatzaile_ID` ASC) VISIBLE,
  CONSTRAINT `fk_Kudeatzaileak_has_Lehiaketa_Kudeatzaileak1`
    FOREIGN KEY (`Kudeatzaileak_kudeatzaile_ID`)
    REFERENCES `Lehiaketa`.`Kudeatzaileak` (`kudeatzaile_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Kudeatzaileak_has_Lehiaketa_Lehiaketa1`
    FOREIGN KEY (`Lehiaketa_lehiaketa_ID`)
    REFERENCES `Lehiaketa`.`Lehiaketa` (`lehiaketa_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
