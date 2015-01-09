/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50151
Source Host           : localhost:3306
Source Database       : sechand_db

Target Server Type    : MYSQL
Target Server Version : 50151
File Encoding         : 65001

Date: 2015-01-09 12:44:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- CREATE DATABASE sechand_db
-- ----------------------------

-- DROP DATABASE IF EXISTS sechand_db;
-- CREATE DATABASE  sechand_db DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

-- ----------------------------
-- Table structure for tb_account
-- ----------------------------
DROP TABLE IF EXISTS `tb_account`;
CREATE TABLE `tb_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_code` int(11) NOT NULL,
  `role_name` varchar(45) NOT NULL,
  `user_name` varchar(45) NOT NULL,
  `real_name` varchar(45) DEFAULT NULL,
  `password` varchar(45) NOT NULL,
  `nick_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `tel` varchar(45) DEFAULT NULL,
  `source` varchar(45) DEFAULT NULL,
  `last_login_time` varchar(45) DEFAULT NULL,
  `register_time` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_account
-- ----------------------------
INSERT INTO `tb_account` VALUES ('1', '1', '管理员', 'admin', null, 'Eeoc9PntH9npT0UZY8kDZQ==', '管理员', null, null, '手动录入', '2015-01-09 12:41:18', '2015-01-09 12:40:38', '正常');

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `company_id` int(11) DEFAULT NULL,
  `create_time` varchar(45) DEFAULT NULL,
  `complete_time` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_order
-- ----------------------------

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `code` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES ('1', '管理员', '1');
