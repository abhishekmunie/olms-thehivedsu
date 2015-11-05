-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema olms
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Table `Manager`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Manager` ;

CREATE TABLE IF NOT EXISTS `Manager` (
  `id` INT NOT NULL COMMENT '',
  `employeeId` INT NOT NULL COMMENT '',
  `teamName` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `MEmployeeId_idx` (`employeeId` ASC)  COMMENT '',
  CONSTRAINT `MEmployeeId`
    FOREIGN KEY (`employeeId`)
    REFERENCES `Employee` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Employee`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Employee` ;

CREATE TABLE IF NOT EXISTS `Employee` (
  `id` INT NOT NULL COMMENT '',
  `givenName` VARCHAR(55) NOT NULL COMMENT '',
  `familyName` VARCHAR(45) NOT NULL COMMENT '',
  `email` VARCHAR(45) NOT NULL COMMENT '',
  `contactNumber` VARCHAR(15) NULL COMMENT '',
  `dateOfBirth` DATE NULL COMMENT '',
  `address` VARCHAR(100) NULL COMMENT '',
  `managerId` INT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  UNIQUE INDEX `email_UNIQUE` (`email` ASC)  COMMENT '',
  INDEX `EManagerIdFK_idx` (`managerId` ASC)  COMMENT '',
  CONSTRAINT `EManagerIdFK`
    FOREIGN KEY (`managerId`)
    REFERENCES `Manager` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `LeaveMasterReference`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `LeaveMasterReference` ;

CREATE TABLE IF NOT EXISTS `LeaveMasterReference` (
  `typeId` CHAR(2) NOT NULL COMMENT '',
  `maximumLimit` INT NOT NULL COMMENT '',
  PRIMARY KEY (`typeId`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `LeaveApplication`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `LeaveApplication` ;

CREATE TABLE IF NOT EXISTS `LeaveApplication` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `employeeId` INT NOT NULL COMMENT '',
  `fromDateTime` DATETIME NOT NULL COMMENT '',
  `toDateTime` DATETIME NOT NULL COMMENT '',
  `type` CHAR(2) NOT NULL COMMENT '',
  `reason` VARCHAR(150) NOT NULL COMMENT '',
  `status` CHAR(1) NOT NULL DEFAULT 'P' COMMENT '',
  `approverId` VARCHAR(45) NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `LAEmployeeIdFK_idx` (`employeeId` ASC)  COMMENT '',
  INDEX `LATypeFK_idx` (`type` ASC)  COMMENT '',
  CONSTRAINT `LAEmployeeIdFK`
    FOREIGN KEY (`employeeId`)
    REFERENCES `Employee` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `LATypeFK`
    FOREIGN KEY (`type`)
    REFERENCES `LeaveMasterReference` (`typeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `LeaveBalance`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `LeaveBalance` ;

CREATE TABLE IF NOT EXISTS `LeaveBalance` (
  `employeeId` INT NOT NULL COMMENT '',
  `typeId` CHAR(2) NOT NULL COMMENT '',
  `balance` INT NOT NULL COMMENT '',
  PRIMARY KEY (`employeeId`, `typeId`)  COMMENT '',
  INDEX `LBTypeIdFK_idx` (`typeId` ASC)  COMMENT '',
  CONSTRAINT `LBEmployeeIdFK`
    FOREIGN KEY (`employeeId`)
    REFERENCES `Employee` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `LBTypeIdFK`
    FOREIGN KEY (`typeId`)
    REFERENCES `LeaveMasterReference` (`typeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Credential`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Credential` ;

CREATE TABLE IF NOT EXISTS `Credential` (
  `username` VARCHAR(45) NOT NULL COMMENT '',
  `password` VARCHAR(100) NOT NULL COMMENT '',
  `employeeId` INT NOT NULL COMMENT '',
  `lastLogin` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '',
  PRIMARY KEY (`username`, `password`)  COMMENT '',
  CONSTRAINT `CEmployeeId`
    FOREIGN KEY (`employeeId`)
    REFERENCES `Employee` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- procedure authenticate
-- -----------------------------------------------------
DROP procedure IF EXISTS `authenticate`;

DELIMITER $$
CREATE PROCEDURE `authenticate` (IN check_username VARCHAR(45), IN check_password VARCHAR(100))
BEGIN
    SELECT employeeId FROM Credential WHERE username = check_username AND `password` = check_password;
END
$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure leaveRequestForManagerWithEmployeeId
-- -----------------------------------------------------
DROP procedure IF EXISTS `leaveRequestForManagerWithEmployeeId`;

DELIMITER $$
CREATE PROCEDURE `leaveRequestForManagerWithEmployeeId` (IN managerEmployeeID INT)
BEGIN
    SELECT LeaveApplication.* FROM Manager INNER JOIN Employee ON Employee.managerId = Manager.id INNER JOIN LeaveApplication ON LeaveApplication.employeeID = Employee.id WHERE Manager.employeeID = managerEmployeeID;
END
$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure approveLeaveWithApplicationId
-- -----------------------------------------------------
DROP procedure IF EXISTS `approveLeaveWithApplicationId`;

DELIMITER $$
CREATE PROCEDURE `approveLeaveWithApplicationId` (IN applicationId INT)
BEGIN
DECLARE currectEmployeeId INT;
DECLARE currentTypeID CHAR(2);
START TRANSACTION;
    UPDATE LeaveApplication SET `status` = "A" WHERE id = applicationId;
    SELECT employeeId, `type`INTO currectEmployeeId, currentTypeID FROM LeaveApplication WHERE id = applicationId;
    INSERT INTO LeaveBalance (employeeID, typeID, balance) VALUES (currectEmployeeId, currentTypeID, (SELECT maximumLimit FROM LeaveMasterReference WHERE typeId = currentTypeID)) ON DUPLICATE KEY UPDATE balance=balance+1;
    -- UPDATE LeaveBalance SET  WHERE employeeId = currectEmployeeId AND typeId = currentTypeID;
    -- IF mysql_affected_rows() = 0 THEN
    --     INSERT INTO LeaveBalance (employeeID, typeID, balance) VALUES (currectEmployeeId, currentTypeID, (SELECT maximumLimit FROM LeaveMasterReference WHERE typeId = currentTypeID));
    -- END IF;
COMMIT;
END
$$

DELIMITER ;

-- -----------------------------------------------------
-- Data for table `Manager`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `Manager` (`id`, `employeeId`, `teamName`) VALUES (1, 1, 'The Hive');
INSERT INTO `Manager` (`id`, `employeeId`, `teamName`) VALUES (2, 2, 'Reports Group');
INSERT INTO `Manager` (`id`, `employeeId`, `teamName`) VALUES (3, 3, 'Manager Group');
INSERT INTO `Manager` (`id`, `employeeId`, `teamName`) VALUES (4, 4, 'Employee Group');

COMMIT;


-- -----------------------------------------------------
-- Data for table `Employee`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `Employee` (`id`, `givenName`, `familyName`, `email`, `contactNumber`, `dateOfBirth`, `address`, `managerId`) VALUES (1, 'Abhishek', 'Munie', 'dsu@abhishekmunie.com', '1234567823', '1992-01-01', 'Roopena Agrahara', NULL);
INSERT INTO `Employee` (`id`, `givenName`, `familyName`, `email`, `contactNumber`, `dateOfBirth`, `address`, `managerId`) VALUES (2, 'Raghu', 'R', 'raghuraju406@gmail.com', '4234553232', '1992-02-02', 'Nelamangala', 1);
INSERT INTO `Employee` (`id`, `givenName`, `familyName`, `email`, `contactNumber`, `dateOfBirth`, `address`, `managerId`) VALUES (3, 'Sai Prasad', 'M', 'gksaiprasad@gmail.com', '3344554321', '1992-03-03', 'Banashenkari', 1);
INSERT INTO `Employee` (`id`, `givenName`, `familyName`, `email`, `contactNumber`, `dateOfBirth`, `address`, `managerId`) VALUES (4, 'Tejaswini', 'Naidu', 'tejumouli@gmail.com', '1232433455', '1992-04-04', 'Hebbal', 1);
INSERT INTO `Employee` (`id`, `givenName`, `familyName`, `email`, `contactNumber`, `dateOfBirth`, `address`, `managerId`) VALUES (5, 'G R Kalle', 'Gowda', 'grkallegowda@gmail.com', '6764748489', '1992-05-05', 'Kumarswamy Layout', 3);
INSERT INTO `Employee` (`id`, `givenName`, `familyName`, `email`, `contactNumber`, `dateOfBirth`, `address`, `managerId`) VALUES (6, 'Balaji', 'S S', '123balajiss123@gmail.com', '1232433455', '1992-06-06', 'Palya', 3);
INSERT INTO `Employee` (`id`, `givenName`, `familyName`, `email`, `contactNumber`, `dateOfBirth`, `address`, `managerId`) VALUES (7, 'Tejas', 'S', 'tejas@gmail.com', '3986364778', '1992-07-07', 'Banashenkari', 3);
INSERT INTO `Employee` (`id`, `givenName`, `familyName`, `email`, `contactNumber`, `dateOfBirth`, `address`, `managerId`) VALUES (8, 'Sai', 'Pra', 'saipra@gmail.com', '3671456796', '1992-08-08', 'K R Puram', 3);
INSERT INTO `Employee` (`id`, `givenName`, `familyName`, `email`, `contactNumber`, `dateOfBirth`, `address`, `managerId`) VALUES (9, 'Ranga', 'G', 'ranga@gmail.com', '234567653', '1992-09-09', 'Banashenkari', 3);
INSERT INTO `Employee` (`id`, `givenName`, `familyName`, `email`, `contactNumber`, `dateOfBirth`, `address`, `managerId`) VALUES (10, 'Raksha', 'V', 'rakshav94@gmail.com', '213689787', '1992-10-10', 'E City', 4);
INSERT INTO `Employee` (`id`, `givenName`, `familyName`, `email`, `contactNumber`, `dateOfBirth`, `address`, `managerId`) VALUES (11, 'Shruthi', 'Hiregoudar', 'shruthi.hiregoudar@gmail.com', '3423465788', '1992-11-11', 'Palya', 4);
INSERT INTO `Employee` (`id`, `givenName`, `familyName`, `email`, `contactNumber`, `dateOfBirth`, `address`, `managerId`) VALUES (12, 'Soumya', 'G V', 'gvsoumya9@gmail.com', '5445678989', '1992-12-12', 'Banashenkari', 4);
INSERT INTO `Employee` (`id`, `givenName`, `familyName`, `email`, `contactNumber`, `dateOfBirth`, `address`, `managerId`) VALUES (13, 'Shruthi', 'T', 'shruthi1293@gmail.com', '54425465768', '1992-01-02', 'Hennur', 4);
INSERT INTO `Employee` (`id`, `givenName`, `familyName`, `email`, `contactNumber`, `dateOfBirth`, `address`, `managerId`) VALUES (14, 'Deepthi', 'B E', 'deepthibe@gmail.com', '64543546576', '1992-02-03', 'K R Puram', 4);
INSERT INTO `Employee` (`id`, `givenName`, `familyName`, `email`, `contactNumber`, `dateOfBirth`, `address`, `managerId`) VALUES (15, 'Alfred', 'Pennyworth', 'contact@abhishekmunie.com', '1111111111', '1992-04-05', 'Gotham', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `LeaveMasterReference`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `LeaveMasterReference` (`typeId`, `maximumLimit`) VALUES ('ML', 100);
INSERT INTO `LeaveMasterReference` (`typeId`, `maximumLimit`) VALUES ('CL', 10);
INSERT INTO `LeaveMasterReference` (`typeId`, `maximumLimit`) VALUES ('VL', 30);
INSERT INTO `LeaveMasterReference` (`typeId`, `maximumLimit`) VALUES ('SL', 200);
INSERT INTO `LeaveMasterReference` (`typeId`, `maximumLimit`) VALUES ('PC', 60);
INSERT INTO `LeaveMasterReference` (`typeId`, `maximumLimit`) VALUES ('FR', 20);
INSERT INTO `LeaveMasterReference` (`typeId`, `maximumLimit`) VALUES ('UL', 10);

COMMIT;


-- -----------------------------------------------------
-- Data for table `LeaveApplication`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `LeaveApplication` (`id`, `employeeId`, `fromDateTime`, `toDateTime`, `type`, `reason`, `status`, `approverId`) VALUES (DEFAULT, 2, '2015-10-26 17:00', '2015-10-25 09:00:00', 'ML', 'Sick', DEFAULT, NULL);
INSERT INTO `LeaveApplication` (`id`, `employeeId`, `fromDateTime`, `toDateTime`, `type`, `reason`, `status`, `approverId`) VALUES (DEFAULT, 2, '2015-11-26 17:00', '2015-11-03 12:00:00', 'CL', 'Schollarship', 'A', '1');
INSERT INTO `LeaveApplication` (`id`, `employeeId`, `fromDateTime`, `toDateTime`, `type`, `reason`, `status`, `approverId`) VALUES (DEFAULT, 2, '2015-10-26 17:00', '2015-10-25 09:00:00', 'CL', 'Meet Friend', 'R', '1');

COMMIT;


-- -----------------------------------------------------
-- Data for table `Credential`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `Credential` (`username`, `password`, `employeeId`, `lastLogin`) VALUES ('abhishekmunie', 'codenameolms', 1, NULL);
INSERT INTO `Credential` (`username`, `password`, `employeeId`, `lastLogin`) VALUES ('raghuraju406@gmail.com', 'codenameraghu', 2, NULL);
INSERT INTO `Credential` (`username`, `password`, `employeeId`, `lastLogin`) VALUES ('gksaiprasad@gmail.com', 'codenamesai', 3, NULL);
INSERT INTO `Credential` (`username`, `password`, `employeeId`, `lastLogin`) VALUES ('tejumouli@gmail.com', 'rvt', 4, NULL);
INSERT INTO `Credential` (`username`, `password`, `employeeId`, `lastLogin`) VALUES ('grkallegowda@gmail.com', 'codenamegowda', 5, NULL);
INSERT INTO `Credential` (`username`, `password`, `employeeId`, `lastLogin`) VALUES ('123balajiss123@gmail.com', '123balajiss123', 6, NULL);
INSERT INTO `Credential` (`username`, `password`, `employeeId`, `lastLogin`) VALUES ('tejas@gmail.com', 'codenametejas', 7, NULL);
INSERT INTO `Credential` (`username`, `password`, `employeeId`, `lastLogin`) VALUES ('saipra@gmail.com', 'codenamepra', 8, NULL);
INSERT INTO `Credential` (`username`, `password`, `employeeId`, `lastLogin`) VALUES ('ranga@gmail.com', 'codenameranga', 9, NULL);
INSERT INTO `Credential` (`username`, `password`, `employeeId`, `lastLogin`) VALUES ('rakshav94@gmail.com', 'rakshav94', 10, NULL);
INSERT INTO `Credential` (`username`, `password`, `employeeId`, `lastLogin`) VALUES ('shruthi.hiregoudar@gmail.com', 'shruthi.hiregoudar', 11, NULL);
INSERT INTO `Credential` (`username`, `password`, `employeeId`, `lastLogin`) VALUES ('gvsoumya9@gmail.com', 'gvsoumya9', 12, NULL);
INSERT INTO `Credential` (`username`, `password`, `employeeId`, `lastLogin`) VALUES ('shruthi1293@gmail.com', 'shruthi1293', 13, NULL);
INSERT INTO `Credential` (`username`, `password`, `employeeId`, `lastLogin`) VALUES ('deepthibe@gmail.com', 'deepthibe', 14, NULL);
INSERT INTO `Credential` (`username`, `password`, `employeeId`, `lastLogin`) VALUES ('alfred', 'batman', 15, NULL);

COMMIT;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
