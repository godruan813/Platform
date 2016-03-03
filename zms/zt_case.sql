/*
Navicat MySQL Data Transfer

Source Server         : 游轮
Source Server Version : 50610
Source Host           : 192.168.0.151:3306
Source Database       : zentao

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2015-10-27 10:27:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `zt_case`
-- ----------------------------
DROP TABLE IF EXISTS `zt_case`;
CREATE TABLE `zt_case` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `className` varchar(500) DEFAULT NULL COMMENT '自动化脚本类名',
  `methodName` varchar(500) DEFAULT NULL COMMENT '自动化脚本方法名',
  `ip` varchar(20) DEFAULT NULL COMMENT '远程机IP',
  `developer` varchar(20) DEFAULT NULL COMMENT '自动化脚本开发者',
  `isAuto` char(2) DEFAULT '0' COMMENT '0:不自动化，1:自动化',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zt_case
-- ----------------------------

-- ----------------------------
-- Table structure for `zt_computer`
-- ----------------------------
DROP TABLE IF EXISTS `zt_computer`;
CREATE TABLE `zt_computer` (
  `ip` varchar(50) NOT NULL,
  `computerStatus` varchar(20) DEFAULT NULL COMMENT '未执行，执行中，执行完毕',
  `MoudleName` varchar(100) DEFAULT NULL COMMENT '机器对应的功能模块名',
  `filePath` varchar(100) DEFAULT NULL COMMENT '例如：D:\\BackEndTest',
  PRIMARY KEY (`ip`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zt_computer
-- ----------------------------
INSERT INTO `zt_computer` VALUES ('192.168.0.1', '就绪', '下单1', 'D:\\BackEndTest\\VSTRouteManagement\\VSTRouteManagement.jar');
INSERT INTO `zt_computer` VALUES ('192.168.0.140', '运行结束', '下单', 'D:\\FrontendTest\\VSTOrderManagement\\VSTOrderManagement.jar');
INSERT INTO `zt_computer` VALUES ('192.168.0.142', '运行结束', '前台酒店邮轮', 'D:\\FrontendTest\\VSTFrontEndSearch\\VSTFrontEndSearch.jar');
INSERT INTO `zt_computer` VALUES ('192.168.0.143', '运行结束', '前台搜索', 'D:\\FrontEndTest\\VSTFrontEndSearch\\VSTFrontEndSearch.jar');
INSERT INTO `zt_computer` VALUES ('192.168.0.146', '运行结束', '前台UED一', 'D:\\FrontendTest\\VSTFrontEndUed\\VSTFrontEndUed.jar');
INSERT INTO `zt_computer` VALUES ('192.168.0.147', '运行结束', '前台UED二', 'D:\\FrontendTest\\VSTFrontEndUed\\VSTFrontEndUed.jar');
INSERT INTO `zt_computer` VALUES ('192.168.0.149', '运行结束', '前台旅游攻略二', 'D:\\FrontendTest\\VSTFrontEndUed\\VSTFrontEndUed.jar');
INSERT INTO `zt_computer` VALUES ('192.168.0.150', '运行结束', '前台旅游攻略一', 'D:\\FrontendTest\\VSTFrontEndUed\\VSTFrontEndUed.jar');
INSERT INTO `zt_computer` VALUES ('192.168.0.197', '运行结束', '线路2', 'D:\\BackEndTest\\VSTRouteManagement\\VSTRouteManagement.jar');
INSERT INTO `zt_computer` VALUES ('192.168.0.199', '运行结束', '线路1', 'D:\\BackEndTest\\VSTRouteManagement\\VSTRouteManagement.jar');
INSERT INTO `zt_computer` VALUES ('192.168.0.200', '就绪', '线路1', 'D:\\BackEndTest\\VSTRouteManagement\\VSTRouteManagement.jar');
INSERT INTO `zt_computer` VALUES ('192.168.0.255', '执行完毕', '门票项目', 'D:\\BackEndTest\\batfile');

-- ----------------------------
-- Table structure for `zt_ptask`
-- ----------------------------
DROP TABLE IF EXISTS `zt_ptask`;
CREATE TABLE `zt_ptask` (
  `id` bigint(50) NOT NULL AUTO_INCREMENT COMMENT '父任务id',
  `taskname` varchar(255) DEFAULT NULL,
  `caseIds` varchar(255) DEFAULT NULL,
  `ips` varchar(100) DEFAULT NULL COMMENT '对应case执行的机器地址',
  `opertime` datetime DEFAULT NULL COMMENT '操作时间',
  `creater` varchar(50) DEFAULT NULL COMMENT 'task创建者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zt_ptask
-- ----------------------------
INSERT INTO `zt_ptask` VALUES ('1', '任务1 主要是用来测试某个功能用例', '010,011,012,015,021,022,023,024,025,026,028,030', '192.168.0.1,192.168.0.2,192.168.0.140,149.168.0.141,192.168.0.142,192,168,0,200', '2015-09-14 14:46:21', '杨帆');
INSERT INTO `zt_ptask` VALUES ('2', '929下单1陈姗姗', '018,020', '192.168.0.200', '2015-09-14 14:46:21', '朱梅');
INSERT INTO `zt_ptask` VALUES ('3', '十一前台搜索3', '010,011,012,015,021', '192.168.0.1,192,168,0.100', '2015-09-14 14:46:21', '阮圣迪');
INSERT INTO `zt_ptask` VALUES ('4', '鲁坤乐 旅游攻略1', '010,011,012,015,021', '192.168.0.1,192.168.0.1,192,168,0,100', '2015-09-14 14:56:21', '四个中文');
INSERT INTO `zt_ptask` VALUES ('5', '朱梅 928 ued2', '018,020', '192.168.0.200', '2015-09-15 14:56:21', '鲁坤乐');
INSERT INTO `zt_ptask` VALUES ('6', '任务6', '010,011,012,015,021', '192.168.0.1,192.168.0.1,192,168,0,100', '2015-09-15 14:56:21', '陈珊珊');
INSERT INTO `zt_ptask` VALUES ('7', null, null, null, null, '阮圣迪');
INSERT INTO `zt_ptask` VALUES ('8', null, null, null, null, '阮圣迪');
INSERT INTO `zt_ptask` VALUES ('9', null, '192.168.0.197', null, '2015-10-15 14:06:09', '阮圣迪');
INSERT INTO `zt_ptask` VALUES ('10', null, '192.168.0.197', null, '2015-10-15 14:13:59', '阮圣迪');
INSERT INTO `zt_ptask` VALUES ('11', '154', '192.168.0.197', null, '2015-10-15 14:15:24', 'admin');
INSERT INTO `zt_ptask` VALUES ('12', null, '192.168.0.197', null, '2015-10-15 14:15:44', '阮圣迪');
INSERT INTO `zt_ptask` VALUES ('13', '156', '192.168.0.197', null, '2015-10-15 14:18:22', 'admin');
INSERT INTO `zt_ptask` VALUES ('14', '44', '192.168.0.197', null, '2015-10-15 14:19:59', 'admin');
INSERT INTO `zt_ptask` VALUES ('15', '222', '192.168.0.197', null, '2015-10-15 14:25:03', 'admin');
INSERT INTO `zt_ptask` VALUES ('16', '333', '16', '192.168.0.197', '2015-10-15 14:27:45', 'admin');
INSERT INTO `zt_ptask` VALUES ('17', '444', '16', '192.168.0.197', '2015-10-15 14:28:26', 'admin');
INSERT INTO `zt_ptask` VALUES ('18', '223232', '16', '192.168.0.197', '2015-10-15 14:33:05', 'admin');
INSERT INTO `zt_ptask` VALUES ('19', '3432423', '16', '192.168.0.197', '2015-10-15 14:35:07', 'admin');
INSERT INTO `zt_ptask` VALUES ('20', '555', '192.168.0.197,192.168.0.141', null, '2015-10-15 15:01:25', '阮圣迪');
INSERT INTO `zt_ptask` VALUES ('21', '555', '192.168.0.197,192.168.0.141', null, '2015-10-15 15:01:27', '阮圣迪');
INSERT INTO `zt_ptask` VALUES ('22', '555', '192.168.0.197,192.168.0.141', null, '2015-10-15 15:01:50', '阮圣迪');
INSERT INTO `zt_ptask` VALUES ('23', '555', '192.168.0.197,192.168.0.141', null, '2015-10-15 15:02:27', '阮圣迪');
INSERT INTO `zt_ptask` VALUES ('24', '555', '192.168.0.199,192.168.0.141', null, '2015-10-15 15:04:00', '阮圣迪');
INSERT INTO `zt_ptask` VALUES ('25', '555', '192.168.0.199,192.168.0.141', null, '2015-10-15 15:04:38', '阮圣迪');
INSERT INTO `zt_ptask` VALUES ('26', '555', '192.168.0.199,192.168.0.141', null, '2015-10-15 15:05:02', '阮圣迪');
INSERT INTO `zt_ptask` VALUES ('27', '555', '192.168.0.199,192.168.0.141', null, '2015-10-15 15:05:25', '阮圣迪');
INSERT INTO `zt_ptask` VALUES ('28', '555', '192.168.0.197,192.168.0.199', null, '2015-10-15 15:06:03', '阮圣迪');
INSERT INTO `zt_ptask` VALUES ('29', '555', '192.168.0.197,192.168.0.199', null, '2015-10-15 15:06:37', '阮圣迪');
INSERT INTO `zt_ptask` VALUES ('30', '555', '192.168.0.140', null, '2015-10-15 15:32:15', '阮圣迪');
INSERT INTO `zt_ptask` VALUES ('31', '任务测试222', '20', '192.168.0.199', '2015-10-15 15:33:49', 'admin');
INSERT INTO `zt_ptask` VALUES ('32', '555', '192.168.0.140', null, '2015-10-15 15:34:53', '阮圣迪');
INSERT INTO `zt_ptask` VALUES ('33', '任务2', '16', '192.168.0.197', '2015-10-16 13:57:40', 'admin');
INSERT INTO `zt_ptask` VALUES ('34', '任务tset', '16', '192.168.0.197', '2015-10-16 14:02:09', 'admin');
INSERT INTO `zt_ptask` VALUES ('35', '555', '192.168.0.199', null, '2015-10-20 14:25:34', '陈洁');
INSERT INTO `zt_ptask` VALUES ('36', '啊', '192.168.0.202', null, '2015-10-20 14:48:19', 'admin');
INSERT INTO `zt_ptask` VALUES ('37', '555', '192.168.0.199', null, '2015-10-20 14:58:35', '陈洁');
INSERT INTO `zt_ptask` VALUES ('38', '555', '192.168.0.199', null, '2015-10-20 14:59:14', '陈洁');
INSERT INTO `zt_ptask` VALUES ('39', '555', '192.168.0.199', null, '2015-10-20 15:00:57', '陈洁');
INSERT INTO `zt_ptask` VALUES ('40', '555', '192.168.0.199', null, '2015-10-20 15:01:08', '陈洁');
INSERT INTO `zt_ptask` VALUES ('41', '555', '192.168.0.199', null, '2015-10-20 15:01:20', '陈洁');
INSERT INTO `zt_ptask` VALUES ('42', '555', '192.168.0.199', null, '2015-10-20 15:56:18', '陈洁');
INSERT INTO `zt_ptask` VALUES ('43', '555', '192.168.0.199', null, '2015-10-20 15:57:21', '陈洁');
INSERT INTO `zt_ptask` VALUES ('44', '555', '192.168.0.199', null, '2015-10-20 15:58:02', '陈洁');
INSERT INTO `zt_ptask` VALUES ('45', '555', '192.168.0.199', null, '2015-10-20 15:58:12', '陈洁');
INSERT INTO `zt_ptask` VALUES ('46', '555', '192.168.0.199', null, '2015-10-20 15:58:56', '陈洁');
INSERT INTO `zt_ptask` VALUES ('47', '555', '192.168.0.199', null, '2015-10-20 16:00:05', '陈洁');
INSERT INTO `zt_ptask` VALUES ('48', '555', '192.168.0.199', null, '2015-10-20 16:00:18', '陈洁');
INSERT INTO `zt_ptask` VALUES ('49', 'tset测试324234234234234', '21', '192.168.0.199', '2015-10-20 16:23:12', 'admin');
INSERT INTO `zt_ptask` VALUES ('50', 'oo', '192.168.0.199', null, '2015-10-20 16:24:23', '陈洁');
INSERT INTO `zt_ptask` VALUES ('51', '555', '192.168.0.197,192.168.0.199', null, '2015-10-20 16:25:28', '陈洁');
INSERT INTO `zt_ptask` VALUES ('52', 'test22', '22', '192.168.0.199', '2015-10-20 16:25:15', 'admin');
INSERT INTO `zt_ptask` VALUES ('53', '555', '192.168.0.197,192.168.0.199', null, '2015-10-20 16:25:36', '陈洁');
INSERT INTO `zt_ptask` VALUES ('54', '555', '192.168.0.197,192.168.0.199', null, '2015-10-20 16:25:37', '陈洁');
INSERT INTO `zt_ptask` VALUES ('55', '555', '192.168.0.197,192.168.0.199', null, '2015-10-20 16:25:42', '陈洁');
INSERT INTO `zt_ptask` VALUES ('56', '555', '192.168.0.197,192.168.0.199', null, '2015-10-20 16:27:41', '陈洁');
INSERT INTO `zt_ptask` VALUES ('57', 'fff', '16', '192.168.0.197', '2015-10-20 16:29:42', 'admin');
INSERT INTO `zt_ptask` VALUES ('58', '任务123213', '25,22,21,20', '192.168.0.199', '2015-10-20 16:30:58', 'admin');
INSERT INTO `zt_ptask` VALUES ('59', '555', '192.168.0.197,192.168.0.199', null, '2015-10-20 16:33:08', '陈洁');
INSERT INTO `zt_ptask` VALUES ('60', '555', '192.168.0.197,192.168.0.199', null, '2015-10-20 16:33:33', '陈洁');
INSERT INTO `zt_ptask` VALUES ('61', '啊啊啊', '22', '192.168.0.199', '2015-10-20 16:36:01', '陈洁');
INSERT INTO `zt_ptask` VALUES ('62', '凤飞飞凤飞飞', '16', '192.168.0.197', '2015-10-20 16:39:16', '陈洁');
INSERT INTO `zt_ptask` VALUES ('63', 'fz', '16', '192.168.0.197', '2015-10-20 16:45:05', 'admin');
INSERT INTO `zt_ptask` VALUES ('64', 'ff2', '20', '192.168.0.199', '2015-10-20 16:45:35', '陈洁');
INSERT INTO `zt_ptask` VALUES ('65', '555', '192.168.0.197,192.168.0.199', null, '2015-10-20 19:12:49', 'é®å£è¿ª');
INSERT INTO `zt_ptask` VALUES ('66', '10263', '192.168.0.197', null, '2015-10-26 18:09:31', 'admin');
INSERT INTO `zt_ptask` VALUES ('67', '333', '192.168.0.197,192.168.0.199', null, '2015-10-26 18:22:33', 'admin');
INSERT INTO `zt_ptask` VALUES ('68', 'ddtest', '192.168.0.199', null, '2015-10-27 09:28:51', 'dd');
INSERT INTO `zt_ptask` VALUES ('69', 'ddtest', '192.168.0.197,192.168.0.199', null, '2015-10-27 10:03:50', 'dd');
INSERT INTO `zt_ptask` VALUES ('70', 'ddtest', '192.168.0.197,192.168.0.199', null, '2015-10-27 10:18:28', 'dd');

-- ----------------------------
-- Table structure for `zt_task`
-- ----------------------------
DROP TABLE IF EXISTS `zt_task`;
CREATE TABLE `zt_task` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `project` mediumint(8) unsigned NOT NULL DEFAULT '0',
  `module` mediumint(8) unsigned NOT NULL DEFAULT '0',
  `story` mediumint(8) unsigned NOT NULL DEFAULT '0',
  `storyVersion` smallint(6) NOT NULL DEFAULT '1',
  `fromBug` mediumint(8) unsigned NOT NULL DEFAULT '0',
  `name` varchar(255) NOT NULL,
  `type` varchar(20) NOT NULL,
  `pri` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `estimate` float unsigned NOT NULL,
  `consumed` float unsigned NOT NULL,
  `left` float unsigned NOT NULL,
  `deadline` date NOT NULL,
  `status` enum('wait','doing','done','pause','cancel','closed') NOT NULL DEFAULT 'wait',
  `mailto` varchar(255) NOT NULL DEFAULT '',
  `desc` text NOT NULL,
  `openedBy` varchar(30) NOT NULL,
  `openedDate` datetime NOT NULL,
  `assignedTo` varchar(30) NOT NULL,
  `assignedDate` datetime NOT NULL,
  `estStarted` date NOT NULL,
  `realStarted` date NOT NULL,
  `finishedBy` varchar(30) NOT NULL,
  `finishedDate` datetime NOT NULL,
  `canceledBy` varchar(30) NOT NULL,
  `canceledDate` datetime NOT NULL,
  `closedBy` varchar(30) NOT NULL,
  `closedDate` datetime NOT NULL,
  `closedReason` varchar(30) NOT NULL,
  `lastEditedBy` varchar(30) NOT NULL,
  `lastEditedDate` datetime NOT NULL,
  `deleted` enum('0','1') NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `type` (`type`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zt_task
-- ----------------------------
