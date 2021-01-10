CREATE TABLE IF NOT EXISTS `atm`.`user`
(
  `user_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(255) DEFAULT NULL,
  `password` VARCHAR(255) DEFAULT NULL,
  `active` BIT DEFAULT 1,
  PRIMARY KEY (`user_id`)
) 
ENGINE=InnoDB
AUTO_INCREMENT = 1;

ALTER TABLE `atm`.`user`
ADD `acct_id` BIGINT(20);

ALTER TABLE `atm`.`user`
ADD FOREIGN KEY (`acct_id`) REFERENCES `atm`.`account`(`id`);

CREATE TABLE IF NOT EXISTS `atm`.`role`
(
	`role_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `role` VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (`role_id`)
)
ENGINE=InnoDB
AUTO_INCREMENT = 1;

CREATE TABLE IF NOT EXISTS `atm`.`user_role`
(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `user_id` BIGINT(20) NOT NULL,
    `role_id`BIGINT(20) NOT NULL,
    PRIMARY KEY (`id`)
)
ENGINE=InnoDB
AUTO_INCREMENT = 1;

CREATE TABLE IF NOT EXISTS `atm`.`account`
(
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `create_date` DATETIME(6) NOT NULL,
    `type` VARCHAR(255) NOT NULL,
    `active` boolean not null,
    `acct_balance` BIGINT(20) NOT NULL,
    PRIMARY KEY (`id`)
)
ENGINE=InnoDB
AUTO_INCREMENT = 1;