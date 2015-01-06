/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50151
Source Host           : localhost:3306
Source Database       : sechand_db

Target Server Type    : MYSQL
Target Server Version : 50151
File Encoding         : 65001

Date: 2015-01-06 14:16:14
*/
-- ----------------------------
-- CREATE DATABASE sechand_db
-- ----------------------------

-- DROP DATABASE IF EXISTS sechand_db;
-- CREATE DATABASE  sechand_db DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_account
-- ----------------------------
DROP TABLE IF EXISTS `tb_account`;
CREATE TABLE `tb_account` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`role_id`  int(11) NOT NULL ,
`role_type`  varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`user_name`  varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`real_name`  varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`password`  varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`nick_name`  varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`email`  varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`sex`  varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`birth_date`  varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`age`  varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`portrait`  varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`address`  varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`identification`  varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=12

;

-- ----------------------------
-- Records of tb_account
-- ----------------------------
BEGIN;
INSERT INTO `tb_account` (`id`, `role_id`, `role_type`, `user_name`, `real_name`, `password`, `nick_name`, `email`, `sex`, `birth_date`, `age`, `portrait`, `address`, `identification`) VALUES ('1', '1', 'admin', 'admin', 'Helen', 'Eeoc9PntH9npT0UZY8kDZQ==', '管理员', null, null, null, null, null, null, null), ('2', '1', 'admin', 'admin1', 'Helen', 'Eeoc9PntH9npT0UZY8kDZQ==', '管理员', null, null, null, null, null, null, null), ('3', '1', 'admin', 'admin2', 'Helen', 'Eeoc9PntH9npT0UZY8kDZQ==', '管理员', null, null, null, null, null, null, null), ('4', '1', 'admin', 'admin3', 'Helen', 'Eeoc9PntH9npT0UZY8kDZQ==', '管理员', null, null, null, null, null, null, null), ('5', '1', 'admin', 'admin4', 'Helen', 'Eeoc9PntH9npT0UZY8kDZQ==', '管理员', null, null, null, null, null, null, null), ('6', '1', 'admin', 'admin5', 'Helen', 'Eeoc9PntH9npT0UZY8kDZQ==', '管理员', null, null, null, null, null, null, null), ('7', '1', 'admin', 'admin6', 'Helen', 'Eeoc9PntH9npT0UZY8kDZQ==', '管理员', null, null, null, null, null, null, null), ('8', '1', 'admin', 'admin7', 'Helen', 'Eeoc9PntH9npT0UZY8kDZQ==', '管理员', null, null, null, null, null, null, null), ('9', '1', 'admin', 'admin8', 'Helen', 'Eeoc9PntH9npT0UZY8kDZQ==', '管理员', null, null, null, null, null, null, null), ('10', '1', 'admin', 'admin9', 'Helen', 'Eeoc9PntH9npT0UZY8kDZQ==', '管理员', null, null, null, null, null, null, null), ('11', '1', 'admin', 'admin10', 'Helen', 'Eeoc9PntH9npT0UZY8kDZQ==', '管理员', null, null, null, null, null, null, null);
COMMIT;

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`user_id`  int(11) NULL DEFAULT NULL ,
`company_id`  int(11) NULL DEFAULT NULL ,
`create_time`  varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`complete_time`  varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`status`  varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=1

;

-- ----------------------------
-- Records of tb_order
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`name`  varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`type`  varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=2

;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
BEGIN;
INSERT INTO `tb_role` (`id`, `name`, `type`) VALUES ('1', '管理员', 'admin');
COMMIT;

-- ----------------------------
-- Auto increment value for tb_account
-- ----------------------------
ALTER TABLE `tb_account` AUTO_INCREMENT=12;

-- ----------------------------
-- Auto increment value for tb_order
-- ----------------------------
ALTER TABLE `tb_order` AUTO_INCREMENT=1;

-- ----------------------------
-- Auto increment value for tb_role
-- ----------------------------
ALTER TABLE `tb_role` AUTO_INCREMENT=2;
