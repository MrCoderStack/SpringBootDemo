/*
Navicat MySQL Data Transfer

Source Server         : 开发
Source Server Version : 50643
Source Host           : 10.39.35.149:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50643
File Encoding         : 65001

Date: 2019-04-22 14:36:27
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
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('37', '10', '10', 's1');
INSERT INTO `student` VALUES ('39', '1', '1', 'student');
INSERT INTO `student` VALUES ('40', '10', '10', 's1');
INSERT INTO `student` VALUES ('42', '1', '1', 'student');
INSERT INTO `student` VALUES ('43', '10', '10', 's1');
INSERT INTO `student` VALUES ('44', '10', '10', 's1');
