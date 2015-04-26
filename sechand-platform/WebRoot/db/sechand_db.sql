/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50529
Source Host           : 127.0.0.1:3306
Source Database       : sechand_db

Target Server Type    : MYSQL
Target Server Version : 50529
File Encoding         : 65001

Date: 2015-04-26 23:34:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_account
-- ----------------------------
DROP TABLE IF EXISTS `tb_account`;
CREATE TABLE `tb_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `create_time` varchar(45) DEFAULT NULL,
  `complete_time` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `user_name` varchar(45) DEFAULT NULL,
  `nick_name` varchar(45) DEFAULT NULL,
  `money` varchar(45) DEFAULT NULL,
  `balance` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `remark` varchar(45) DEFAULT NULL,
  `source` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_account
-- ----------------------------

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
  `quote_time` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `repair_content` longtext,
  `quote_content` longtext,
  `repair_man` longtext,
  `customer_user` varchar(45) DEFAULT NULL,
  `customer_company` varchar(45) DEFAULT NULL,
  `contact_tel_user` varchar(45) DEFAULT NULL,
  `contact_tel_company` varchar(45) DEFAULT NULL,
  `price` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
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
  `code` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES ('1', '管理员', '1');
INSERT INTO `tb_role` VALUES ('2', '维修公司', '2');
INSERT INTO `tb_role` VALUES ('3', '普通用户', '3');
INSERT INTO `tb_role` VALUES ('4', '维修人员', '4');

-- ----------------------------
-- Table structure for tb_trade
-- ----------------------------
DROP TABLE IF EXISTS `tb_trade`;
CREATE TABLE `tb_trade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `from_user_id` int(11) DEFAULT NULL,
  `to_user_id` int(11) DEFAULT NULL,
  `from_user_name` varchar(45) DEFAULT NULL,
  `to_user_name` varchar(45) DEFAULT NULL,
  `from_user_nick_name` varchar(45) DEFAULT NULL,
  `to_user_nick_name` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `time` varchar(45) DEFAULT NULL,
  `money` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_trade
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_code` varchar(45) NOT NULL,
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
  `introduction` longtext,
  `balance` varchar(45) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', '1', '管理员', 'admin', '管理员', 'dpLc3BnkHmbGri3lSmlrJQ==', '管理员', '', '', '手动录入', '2015-04-26 23:31:33', '2015-01-16 20:16:20', '正常', '', '0');
INSERT INTO `tb_user` VALUES ('2', '2', '维修公司', '维修百货公司', '维修百货公司', 'Eeoc9PntH9npT0UZY8kDZQ==', '维修百货公司', '123456@qq.com', '15759216890', '平台注册', null, '2015-04-26 23:32:51', '正常', '维修百货公司', '0');
INSERT INTO `tb_user` VALUES ('3', '3', '普通用户', 'smart001', 'smart', 'Eeoc9PntH9npT0UZY8kDZQ==', 'smart001', 'smart@qq.com', '15960234327', '平台注册', null, '2015-04-26 23:34:11', '正常', 'smart', '0');
