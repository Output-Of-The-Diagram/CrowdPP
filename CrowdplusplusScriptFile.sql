-- MySQL Workbench Forward Engineering
-- 만약 서버 실행 시 에러가 발생한다면 아래 3번 줄의 주석을 풀고 PASSWORD란에 패스워드를 입력해주세요
-- ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'PASSWORD';

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema CrowdPP
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema CrowdPP
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `CrowdPP` DEFAULT CHARACTER SET utf8mb3 ;
USE `CrowdPP` ;

-- -----------------------------------------------------
-- Table `CrowdPP`.`Crowd`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CrowdPP`.`Crowd` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `genDate` DATETIME NOT NULL,
  `description` VARCHAR(1000) NULL DEFAULT NULL,
  `representImage` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `CrowdPP`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CrowdPP`.`User` (
  `id` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `joinDate` DATETIME NOT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `gender` TINYINT NULL DEFAULT NULL,
  `e_mail` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `CrowdPP`.`Belong`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CrowdPP`.`Belong` (
  `userID` VARCHAR(45) NOT NULL,
  `crowdID` INT NOT NULL,
  `isLeader` TINYINT NOT NULL,
  PRIMARY KEY (`userID`, `crowdID`),
  INDEX `fk_Belong_Crowd1_idx` (`crowdID` ASC) VISIBLE,
  CONSTRAINT `fk_Belong_Crowd1`
    FOREIGN KEY (`crowdID`)
    REFERENCES `CrowdPP`.`Crowd` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_Belong_User1`
    FOREIGN KEY (`userID`)
    REFERENCES `CrowdPP`.`User` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `CrowdPP`.`Request`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CrowdPP`.`Request` (
  `userID` VARCHAR(45) NOT NULL,
  `crowdID` INT NOT NULL,
  `applyDate` DATETIME NOT NULL,
  PRIMARY KEY (`userID`, `crowdID`),
  INDEX `fk_Request_Crowd1_idx` (`crowdID` ASC) VISIBLE,
  CONSTRAINT `fk_Request_Crowd1`
    FOREIGN KEY (`crowdID`)
    REFERENCES `CrowdPP`.`Crowd` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_Request_User`
    FOREIGN KEY (`userID`)
    REFERENCES `CrowdPP`.`User` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `CrowdPP`.`Tag`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CrowdPP`.`Tag` (
  `crowdID` INT NOT NULL,
  `tagName` INT NOT NULL,
  PRIMARY KEY (`crowdID`, `tagName`),
  INDEX `fk_Tag_Crowd1_idx` (`crowdID` ASC) VISIBLE,
  CONSTRAINT `fk_Tag_Crowd1`
    FOREIGN KEY (`crowdID`)
    REFERENCES `CrowdPP`.`Crowd` (`id`)
    ON DELETE CASCADE)
    
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

