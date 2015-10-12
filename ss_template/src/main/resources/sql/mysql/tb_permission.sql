-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.6.23 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  8.3.0.4694
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出  表 ss-template.tb_permission 结构
CREATE TABLE IF NOT EXISTS `tb_permission` (
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

-- 正在导出表  ss-template.tb_permission 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `tb_permission` DISABLE KEYS */;
INSERT INTO `tb_permission` (`pms_code`, `parent_code`, `pms_name`, `pms_status`, `create_time`, `create_user`, `access_uri`) VALUES
	('0_1', '0', '首页', 1, '2015-04-29 14:42:48', 'user', '/index/welcome'),
	('0_2', '0', '系统管理', 1, '2015-04-16 21:04:00', 'user', '#'),
	('0_2_1', '0_2', '用户列表', 1, '2015-04-14 20:29:34', 'user', '/admin/user'),
	('0_2_2', '0_2', '添加用户', 1, '2015-04-14 20:29:38', 'user', '/register'),
	('0_2_3', '0_2', '角色管理', 1, '2015-04-29 10:41:47', 'user', '/role'),
	('0_2_4', '0_2', '权限管理', 1, '2015-04-29 10:42:06', 'user', '/pms');
/*!40000 ALTER TABLE `tb_permission` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
