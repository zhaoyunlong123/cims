/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50729
Source Host           : localhost:3306
Source Database       : javakc82

Target Server Type    : MYSQL
Target Server Version : 50729
File Encoding         : 65001

Date: 2020-09-08 13:49:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `cims_user`
-- ----------------------------
DROP TABLE IF EXISTS `cims_user`;
CREATE TABLE `cims_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `nick_name` varchar(25) DEFAULT NULL COMMENT '昵称',
  `user_name` varchar(16) DEFAULT NULL COMMENT '账号',
  `user_pass` varchar(126) DEFAULT NULL COMMENT '密码',
  `user_gender` int(11) DEFAULT NULL COMMENT '性别 0:男 1:女',
  `birthday` date DEFAULT NULL COMMENT '出生日期',
  `address` varchar(200) DEFAULT NULL COMMENT '家庭住址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cims_user
-- ----------------------------
INSERT INTO `cims_user` VALUES ('1', '阿洁', 'ajie', '123456', '1', '1995-02-18', '北京市东城区');
