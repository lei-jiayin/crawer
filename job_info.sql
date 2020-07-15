/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.110
Source Server Version : 50555
Source Host           : 192.168.1.110:3306
Source Database       : xw_taes

Target Server Type    : MYSQL
Target Server Version : 50555
File Encoding         : 65001

Date: 2020-06-08 16:58:15
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
  `job_addr` varchar(50) DEFAULT NULL COMMENT '工作地点',
  `job_info` text COMMENT '职位信息',
  `salary_min` int(11) DEFAULT NULL COMMENT '最小薪资',
  `salary_max` int(11) DEFAULT NULL COMMENT '最大薪资',
  `url` varchar(150) DEFAULT NULL COMMENT '招聘信息详情页',
  `time` varchar(10) DEFAULT NULL COMMENT '职位最近发布时间',
  `create_time` datetime DEFAULT NULL COMMENT '插入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2635 DEFAULT CHARSET=utf8;
