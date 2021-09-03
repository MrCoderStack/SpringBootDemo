/*
Navicat MySQL Data Transfer

Source Server         : vm
Source Server Version : 50723
Source Host           : 127.0.0.1:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2019-04-10 16:04:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `age` int(11) NOT NULL,
  `grade` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
