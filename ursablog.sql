/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50529
Source Host           : localhost:3306
Source Database       : shack2

Target Server Type    : MYSQL
Target Server Version : 50529
File Encoding         : 65001

Date: 2013-07-29 16:43:07
*/

-- ----------------------------
-- Table structure for `admin_shack`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `remark` varchar(50) DEFAULT NULL,
  `lastLoginIp` varchar(15) DEFAULT NULL,
  `lastLoginTime` datetime DEFAULT NULL,
  `password` varchar(20) NOT NULL,
  `username` varchar(20) NOT NULL,
  `id` smallint(2) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_shack
-- ----------------------------

-- ----------------------------
-- Table structure for `articleinfo`
-- ----------------------------
DROP TABLE IF EXISTS `article_info`;
CREATE TABLE `article_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type_id` tinyint(1) NOT NULL COMMENT '文章类型ID',
  `title` varchar(100) CHARACTER SET utf8 NOT NULL COMMENT '文章标题',
  `content` longtext CHARACTER SET utf8 NOT NULL COMMENT '博文内容',
  `author` varchar(20) CHARACTER SET latin1 NOT NULL COMMENT '作者',
  `read_count` int(11) NOT NULL COMMENT '阅读次数',
  `comment_count` int(11) NOT NULL COMMENT '评论次数',
  `ip` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT 'IP地址',
  `create_gmt_date` datetime NOT NULL COMMENT '创建时间',
  `modify_gmt_date` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `id` (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for `black`
-- ----------------------------
DROP TABLE IF EXISTS `blacklist`;
CREATE TABLE `blacklist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ip` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=96 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of black
-- ----------------------------
INSERT INTO blacklist VALUES ('1', '188.143.232.84');

-- ----------------------------
-- Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `articleId` int(11) DEFAULT NULL,
  `uname` varchar(20) DEFAULT NULL,
  `mail_qq` varchar(50) DEFAULT NULL,
  `content` text NOT NULL,
  `ip` varchar(15) NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_msg_articleId` (`articleId`),
  CONSTRAINT `fk_msg_articleId` FOREIGN KEY (`articleId`) REFERENCES `articleinfo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=277 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------

-- ----------------------------
-- Table structure for `linkinfo`
-- ----------------------------
DROP TABLE IF EXISTS `linkinfo`;
CREATE TABLE `linkinfo` (
  `id` smallint(11) NOT NULL AUTO_INCREMENT,
  `linkname` varchar(20) DEFAULT NULL,
  `linkurl` varchar(50) NOT NULL,
  `orderby` smallint(6) DEFAULT NULL,
  `remark` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of linkinfo
-- ----------------------------
INSERT INTO linkinfo VALUES ('1', '百度', 'http://www.baidu.com/', '1', '1');

-- ----------------------------
-- Table structure for `msginfo`
-- ----------------------------
DROP TABLE IF EXISTS `msginfo`;
CREATE TABLE `msginfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) DEFAULT NULL,
  `mail_qq` varchar(30) DEFAULT NULL,
  `weburl` varchar(50) DEFAULT NULL,
  `content` text NOT NULL,
  `ip` varchar(15) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of msginfo
-- ----------------------------

-- ----------------------------
-- Table structure for `path`
-- ----------------------------
DROP TABLE IF EXISTS `path`;
CREATE TABLE `path` (
  `remark` text,
  `path` varchar(512) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of path
-- ----------------------------

-- ----------------------------
-- Table structure for `taginfo`
-- ----------------------------
DROP TABLE IF EXISTS `taginfo`;
CREATE TABLE `taginfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `articleId` int(11) NOT NULL,
  `tagName` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_articleId` (`articleId`),
  CONSTRAINT `fk_articleId` FOREIGN KEY (`articleId`) REFERENCES `articleinfo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=235 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of taginfo
-- ----------------------------
INSERT INTO taginfo VALUES ('1', '1', 'css/html');

-- ----------------------------
-- Table structure for `typeinfo`
-- ----------------------------
DROP TABLE IF EXISTS `typeinfo`;
CREATE TABLE `typeinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `typeName` varchar(20) NOT NULL,
  `shortName` varchar(10) DEFAULT NULL,
  `orderby` smallint(6) DEFAULT NULL,
  `remark` varchar(50) DEFAULT NULL,
  `isFType` int(11) NOT NULL,
  `fTypeId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of typeinfo
-- ----------------------------
INSERT INTO typeinfo VALUES ('1', 'JAVA', 'java', '2', 'Java编程', '0', '11');

-- ----------------------------
-- Table structure for `weblogs`
-- ----------------------------
DROP TABLE IF EXISTS `weblogs`;
CREATE TABLE `weblogs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `un` varchar(20) DEFAULT NULL,
  `pw` varchar(20) DEFAULT NULL,
  `ip` varchar(15) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of weblogs
-- ----------------------------

-- ----------------------------
-- Table structure for `xss`
-- ----------------------------
DROP TABLE IF EXISTS `xss`;
CREATE TABLE `xss` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `location` varchar(256) DEFAULT NULL,
  `ck` text,
  `ip` varchar(15) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=492 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xss
-- ----------------------------
