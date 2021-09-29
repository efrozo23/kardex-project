-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema kardexdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `kardexdb` ;

-- -----------------------------------------------------
-- Schema kardexdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `kardexdb` ;
USE `kardexdb` ;

-- -----------------------------------------------------
-- Table `kardexdb`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kardexdb`.`user` (
  `id_user` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `phone` INT NULL,
  `address` VARCHAR(45) NULL,
  PRIMARY KEY (`id_user`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `email_UNIQUE` ON `kardexdb`.`user` (`email` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `kardexdb`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kardexdb`.`category` (
  `id_category` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_category`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `kardexdb`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kardexdb`.`product` (
  `id_product` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NULL,
  `price` DECIMAL(10,2) NULL,
  `creation_date` VARCHAR(45) NULL,
  `stock` INT NULL,
  `type_product` INT NOT NULL,
  PRIMARY KEY (`id_product`),
  CONSTRAINT `fk_produc_category`
    FOREIGN KEY (`type_product`)
    REFERENCES `kardexdb`.`category` (`id_category`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;

CREATE INDEX `fk_produc_category_idx` ON `kardexdb`.`product` (`type_product` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `kardexdb`.`shopping_car`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kardexdb`.`shopping_car` (
  `id_shopping_car` INT NOT NULL AUTO_INCREMENT,
  `id_user` INT NOT NULL,
  `id_product` INT NOT NULL,
  `quantity` INT NOT NULL,
  PRIMARY KEY (`id_shopping_car`),
  CONSTRAINT `fk_shopping_car_product`
    FOREIGN KEY (`id_product`)
    REFERENCES `kardexdb`.`product` (`id_product`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_shopping_car_user`
    FOREIGN KEY (`id_user`)
    REFERENCES `kardexdb`.`user` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_shopping_car_product_idx` ON `kardexdb`.`shopping_car` (`id_product` ASC) VISIBLE;

CREATE INDEX `fk_shopping_car_user_idx` ON `kardexdb`.`shopping_car` (`id_user` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `kardexdb`.`purchase`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kardexdb`.`purchase` (
  `id_purchase` INT NOT NULL AUTO_INCREMENT,
  `id_user_fk` INT NOT NULL,
  `date_purchase` DATETIME NULL,
  `total_price` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`id_purchase`),
  CONSTRAINT `fk_purchase_user`
    FOREIGN KEY (`id_user_fk`)
    REFERENCES `kardexdb`.`user` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_purchase_user_idx` ON `kardexdb`.`purchase` (`id_user_fk` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `kardexdb`.`purchase_detail`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kardexdb`.`purchase_detail` (
  `id_purchase_detail` INT NOT NULL,
  `id_purchase_fk` INT NOT NULL,
  `id_product_fk` INT NOT NULL,
  `price` DECIMAL(10,2) NOT NULL,
  `quantity` INT NOT NULL,
  PRIMARY KEY (`id_purchase_detail`),
  CONSTRAINT `fk_purchase_detail`
    FOREIGN KEY (`id_purchase_fk`)
    REFERENCES `kardexdb`.`purchase` (`id_purchase`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_purchase_detail_product`
    FOREIGN KEY (`id_product_fk`)
    REFERENCES `kardexdb`.`product` (`id_product`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_purchase_detail_idx` ON `kardexdb`.`purchase_detail` (`id_purchase_fk` ASC) VISIBLE;

CREATE INDEX `fk_purchase_detail_product_idx` ON `kardexdb`.`purchase_detail` (`id_product_fk` ASC) VISIBLE;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

CREATE USER 'userkardex'@'%' IDENTIFIED BY '123456';
GRANT DELETE,INSERT,SELECT,UPDATE ON kardexdb.* TO 'userkardex'@'%';


insert into category values (1,'DC');
insert into category values (2,'MARVEL');
