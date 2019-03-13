/*
Navicat MySQL Data Transfer

Source Server         : vm
Source Server Version : 50723
Source Host           : 192.168.145.131:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2019-03-13 10:00:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for person
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of person
-- ----------------------------
INSERT INTO `person` VALUES ('1', '1', '1');
INSERT INTO `person` VALUES ('2', '2', '2');
INSERT INTO `person` VALUES ('3', '3', '3');
INSERT INTO `person` VALUES ('4', '4', '4');
INSERT INTO `person` VALUES ('5', '5', '5');
INSERT INTO `person` VALUES ('6', '6', '6');
INSERT INTO `person` VALUES ('7', '7', '7');
INSERT INTO `person` VALUES ('8', '8', '8');
INSERT INTO `person` VALUES ('9', '9', '9');
INSERT INTO `person` VALUES ('10', '10', '10');
INSERT INTO `person` VALUES ('11', '11', '11');
