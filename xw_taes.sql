/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50728
Source Host           : localhost:3306
Source Database       : xw_taes

Target Server Type    : MYSQL
Target Server Version : 50728
File Encoding         : 65001

Date: 2020-07-17 09:30:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for job_info
-- ----------------------------
DROP TABLE IF EXISTS `job_info`;
CREATE TABLE `job_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `company_name` varchar(100) DEFAULT NULL COMMENT '公司名称',
  `company_addr` varchar(200) DEFAULT NULL COMMENT '公司联系方式',
  `company_info` text COMMENT '公司信息',
  `job_name` varchar(100) DEFAULT NULL COMMENT '职位名称',
  `job_addr` varchar(255) DEFAULT NULL COMMENT '工作地点',
  `job_info` text COMMENT '职位信息',
  `salary_min` int(11) DEFAULT NULL COMMENT '最小薪资',
  `salary_max` int(11) DEFAULT NULL COMMENT '最大薪资',
  `url` varchar(150) DEFAULT NULL COMMENT '招聘信息详情页',
  `time` varchar(10) DEFAULT NULL COMMENT '职位最近发布时间',
  `create_time` datetime DEFAULT NULL COMMENT '插入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3805 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for job_info_1
-- ----------------------------
DROP TABLE IF EXISTS `job_info_1`;
CREATE TABLE `job_info_1` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `company_name` varchar(100) DEFAULT NULL COMMENT '公司名称',
  `company_addr` varchar(200) DEFAULT NULL COMMENT '公司联系方式',
  `company_info` text COMMENT '公司信息',
  `job_name` varchar(100) DEFAULT NULL COMMENT '职位名称',
  `job_addr` varchar(255) DEFAULT NULL COMMENT '工作地点',
  `job_info` text COMMENT '职位信息',
  `salary_min` int(11) DEFAULT NULL COMMENT '最小薪资',
  `salary_max` int(11) DEFAULT NULL COMMENT '最大薪资',
  `url` varchar(150) DEFAULT NULL COMMENT '招聘信息详情页',
  `time` varchar(10) DEFAULT NULL COMMENT '职位最近发布时间',
  `create_time` datetime DEFAULT NULL COMMENT '插入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3803 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for weather_event
-- ----------------------------
DROP TABLE IF EXISTS `weather_event`;
CREATE TABLE `weather_event` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `event_date` varchar(10) DEFAULT NULL,
  `event_img` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `description` text,
  `url` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=300 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for weather_history
-- ----------------------------
DROP TABLE IF EXISTS `weather_history`;
CREATE TABLE `weather_history` (
  `id` varchar(255) NOT NULL,
  `weather_date` varchar(20) DEFAULT NULL COMMENT '天气时间',
  `weather_name` varchar(255) DEFAULT NULL COMMENT '天气名',
  `t_max` int(11) DEFAULT NULL COMMENT '最高温度',
  `t_min` int(11) DEFAULT NULL COMMENT '最低温度',
  `win_direction` varchar(10) DEFAULT NULL COMMENT '风向',
  `win_level` varchar(10) DEFAULT NULL COMMENT '风级',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for weather_info
-- ----------------------------
DROP TABLE IF EXISTS `weather_info`;
CREATE TABLE `weather_info` (
  `id` varchar(255) NOT NULL,
  `weather_date` varchar(20) DEFAULT NULL COMMENT '天气时间',
  `weather_name` varchar(255) DEFAULT NULL COMMENT '天气名',
  `t_max` int(11) DEFAULT NULL COMMENT '最高温度',
  `t_min` int(11) DEFAULT NULL COMMENT '最低温度',
  `win_direction` varchar(10) DEFAULT NULL COMMENT '风向',
  `win_level` varchar(10) DEFAULT NULL COMMENT '风级',
  `url` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
