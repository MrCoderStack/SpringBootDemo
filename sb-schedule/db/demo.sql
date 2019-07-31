DROP DATABASE
IF
	EXISTS `demo`;
CREATE DATABASE `demo`;
USE `demo`;
DROP TABLE
IF
	EXISTS `crontab`;
CREATE TABLE `crontab` ( `cron_id` VARCHAR ( 30 ) NOT NULL PRIMARY KEY, `cron` VARCHAR ( 30 ) NOT NULL );
INSERT INTO `crontab`
VALUES
	( '1', '0/10 * * * * ?' );