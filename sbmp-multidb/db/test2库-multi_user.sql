/*
Navicat MySQL Data Transfer

Source Server         : vm
Source Server Version : 50723
Source Host           : 127.0.0.1:3306
Source Database       : test2

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2019-03-11 16:12:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for multi_user
-- ----------------------------
DROP TABLE IF EXISTS `multi_user`;
CREATE TABLE `multi_user` (
  `id` bigint(64) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `age` int(30) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of multi_user
-- ----------------------------
INSERT INTO `multi_user` VALUES ('1103566261448732674', '主库添加', '20');
INSERT INTO `multi_user` VALUES ('1103566303475658753', '主库添加', '20');
INSERT INTO `multi_user` VALUES ('1103566435235524610', '主库添加', '20');
