/*
 Navicat MySQL Data Transfer

 Source Server         : 192.168.0.53
 Source Server Version : 50623
 Source Host           : 192.168.0.53
 Source Database       : ss-template

 Target Server Version : 50623
 File Encoding         : utf-8

 Date: 10/12/2015 16:51:33 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `tb_permission`
-- ----------------------------
DROP TABLE IF EXISTS `tb_permission`;
CREATE TABLE `tb_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pms_code` varchar(100) NOT NULL,
  `parent_code` varchar(100) DEFAULT NULL,
  `pms_name` varchar(50) DEFAULT NULL,
  `pms_status` int(2) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_user` varchar(64) DEFAULT NULL,
  `access_uri` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `tb_permission`
-- ----------------------------
BEGIN;
INSERT INTO `tb_permission` VALUES ('1', '0_1', '0', '首页', '1', '2015-04-29 14:42:48', 'user', '/index/welcome'), ('2', '0_2', '0', '系统管理', '1', '2015-04-16 21:04:00', 'user', '#'), ('3', '0_2_1', '0_2', '用户列表', '1', '2015-04-14 20:29:34', 'user', '/admin/user'), ('4', '0_2_2', '0_2', '添加用户', '1', '2015-04-14 20:29:38', 'user', '/register'), ('9', '0_2_3', '0_2', '角色管理', '1', '2015-04-29 10:41:47', 'user', '/role'), ('10', '0_2_4', '0_2', '权限管理', '1', '2015-04-29 10:42:06', 'user', '/pms');
COMMIT;

-- ----------------------------
--  Table structure for `tb_role`
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_code` varchar(50) NOT NULL,
  `role_name` varchar(50) DEFAULT NULL,
  `role_desc` varchar(250) DEFAULT NULL,
  `role_status` int(2) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_user` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `tb_role`
-- ----------------------------
BEGIN;
INSERT INTO `tb_role` VALUES ('1', 'user', '用户组', '拥有基本权限', '1', '2015-04-14 11:14:39', 'user'), ('2', 'admin', '系统组', '拥有系统所有权限', '1', '2015-04-14 11:14:36', 'user'), ('3', 'youke', '游客', '极少权限', '1', '2015-04-15 14:57:22', 'admin');
COMMIT;

-- ----------------------------
--  Table structure for `tb_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_permission`;
CREATE TABLE `tb_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_code` varchar(50) NOT NULL,
  `pms_code` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `tb_role_permission`
-- ----------------------------
BEGIN;
INSERT INTO `tb_role_permission` VALUES ('28', 'youke', '0_1'), ('29', 'admin', '0_1'), ('30', 'admin', '0_2'), ('31', 'admin', '0_2_1'), ('32', 'admin', '0_2_2'), ('33', 'admin', '0_2_3'), ('34', 'admin', '0_2_4'), ('35', 'user', '0_1');
COMMIT;

-- ----------------------------
--  Table structure for `tb_task`
-- ----------------------------
DROP TABLE IF EXISTS `tb_task`;
CREATE TABLE `tb_task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(128) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tb_user`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(64) NOT NULL,
  `name` varchar(64) NOT NULL,
  `password` varchar(255) NOT NULL,
  `salt` varchar(64) NOT NULL,
  `roles` varchar(255) NOT NULL,
  `register_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_name` (`login_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `tb_user`
-- ----------------------------
BEGIN;
INSERT INTO `tb_user` VALUES ('1', 'user', 'user', '69a198f189afe8c16c84790b08fe6757228298d2', '880e82064342548d', 'user', '2015-08-12 15:43:16'), ('2', 'admin', 'admin', '97ae47852f1e931af496ca6f0dee0ff9ba46bed7', '71070ba8f3f27dc2', 'admin', '2015-08-31 20:02:23');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
