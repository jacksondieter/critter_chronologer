CREATE DATABASE IF NOT EXISTS `critter`;
USE `critter`;

CREATE USER 'sa'@'%' IDENTIFIED BY 'sa1234';
GRANT ALL PRIVILEGES ON *.* TO 'sa'@'%';