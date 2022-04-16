/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : cjgl3

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2021-03-31 12:18:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `class_hours` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', '100', '语文');
INSERT INTO `course` VALUES ('2', '120', '数学');
INSERT INTO `course` VALUES ('3', '100', '英语');
INSERT INTO `course` VALUES ('4', '80', '物理');
INSERT INTO `course` VALUES ('5', '70', '化学');
INSERT INTO `course` VALUES ('6', '70', '政治');
INSERT INTO `course` VALUES ('7', '50', '生物');

-- ----------------------------
-- Table structure for grade
-- ----------------------------
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `grade` int(11) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  `student_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7e8ca7hfmrpruicqhocskjlf2` (`course_id`),
  KEY `FK5secqnjjwgh9wxk4h1xwgj1n0` (`student_id`),
  CONSTRAINT `FK5secqnjjwgh9wxk4h1xwgj1n0` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`),
  CONSTRAINT `FK7e8ca7hfmrpruicqhocskjlf2` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of grade
-- ----------------------------
INSERT INTO `grade` VALUES ('1', '84', '1', '2');
INSERT INTO `grade` VALUES ('2', '74', '1', '3');
INSERT INTO `grade` VALUES ('3', '63', '1', '4');
INSERT INTO `grade` VALUES ('4', '86', '1', '5');
INSERT INTO `grade` VALUES ('5', '88', '1', '6');
INSERT INTO `grade` VALUES ('6', '73', '1', '7');
INSERT INTO `grade` VALUES ('7', '87', '2', '2');
INSERT INTO `grade` VALUES ('8', '76', '2', '3');
INSERT INTO `grade` VALUES ('9', '67', '2', '4');
INSERT INTO `grade` VALUES ('10', '84', '2', '5');
INSERT INTO `grade` VALUES ('11', '53', '2', '6');
INSERT INTO `grade` VALUES ('12', '98', '2', '7');
INSERT INTO `grade` VALUES ('13', '45', '3', '2');
INSERT INTO `grade` VALUES ('14', '76', '3', '3');
INSERT INTO `grade` VALUES ('15', '91', '3', '4');
INSERT INTO `grade` VALUES ('16', '65', '3', '5');
INSERT INTO `grade` VALUES ('17', '68', '3', '6');
INSERT INTO `grade` VALUES ('18', '72', '3', '7');
INSERT INTO `grade` VALUES ('19', '65', '4', '2');
INSERT INTO `grade` VALUES ('20', '63', '4', '3');
INSERT INTO `grade` VALUES ('21', '68', '4', '4');
INSERT INTO `grade` VALUES ('22', '66', '4', '5');
INSERT INTO `grade` VALUES ('23', '82', '4', '6');
INSERT INTO `grade` VALUES ('24', '65', '4', '7');
INSERT INTO `grade` VALUES ('25', '76', '5', '2');
INSERT INTO `grade` VALUES ('26', '65', '5', '3');
INSERT INTO `grade` VALUES ('27', '87', '5', '4');
INSERT INTO `grade` VALUES ('28', '66', '5', '5');
INSERT INTO `grade` VALUES ('29', '92', '5', '6');
INSERT INTO `grade` VALUES ('30', '67', '5', '7');
INSERT INTO `grade` VALUES ('31', '77', '6', '2');
INSERT INTO `grade` VALUES ('32', '65', '6', '3');
INSERT INTO `grade` VALUES ('33', '88', '6', '4');
INSERT INTO `grade` VALUES ('34', '70', '6', '5');
INSERT INTO `grade` VALUES ('35', '64', '6', '6');
INSERT INTO `grade` VALUES ('36', '71', '6', '7');
INSERT INTO `grade` VALUES ('37', '65', '7', '2');
INSERT INTO `grade` VALUES ('38', '54', '7', '3');
INSERT INTO `grade` VALUES ('39', '70', '7', '4');
INSERT INTO `grade` VALUES ('40', '54', '7', '5');
INSERT INTO `grade` VALUES ('41', '61', '7', '6');
INSERT INTO `grade` VALUES ('42', '57', '7', '7');
INSERT INTO `grade` VALUES ('43', '65', '1', '8');
INSERT INTO `grade` VALUES ('44', '87', '1', '9');
INSERT INTO `grade` VALUES ('45', '43', '1', '10');
INSERT INTO `grade` VALUES ('46', '84', '1', '11');
INSERT INTO `grade` VALUES ('47', '67', '2', '8');
INSERT INTO `grade` VALUES ('48', '66', '2', '9');
INSERT INTO `grade` VALUES ('49', '65', '2', '10');
INSERT INTO `grade` VALUES ('50', '72', '2', '11');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `sex` varchar(255) DEFAULT NULL,
  `stu_num` varchar(255) DEFAULT NULL,
  `id` int(11) NOT NULL,
  `tb_class_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7mnhghacy9fg6ddf7nf58r79f` (`tb_class_id`),
  CONSTRAINT `FKt1enxfujglcm8pio0xa8qbty5` FOREIGN KEY (`id`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `FK7mnhghacy9fg6ddf7nf58r79f` FOREIGN KEY (`tb_class_id`) REFERENCES `tb_class` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('男', '0101', '2', '1');
INSERT INTO `student` VALUES ('男', '0102', '3', '1');
INSERT INTO `student` VALUES ('女', '0103', '4', '1');
INSERT INTO `student` VALUES ('女', '0104', '5', '1');
INSERT INTO `student` VALUES ('男', '0105', '6', '1');
INSERT INTO `student` VALUES ('女', '0106', '7', '1');
INSERT INTO `student` VALUES ('男', '0201', '8', '2');
INSERT INTO `student` VALUES ('男', '0202', '9', '2');
INSERT INTO `student` VALUES ('女', '0203', '10', '2');
INSERT INTO `student` VALUES ('男', '0204', '11', '2');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'ROLE_ADMIN', '管理员');
INSERT INTO `sys_role` VALUES ('2', 'ROLE_STUDENT', '学生');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_51bvuyvihefoh4kp5syh2jpi4` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '陈老师', '$2a$10$WjKwQrWTt8SeJpB6rI4hR.VTFqQJkI9eg7uNFy.1ps/PPhL3dPST.', 'admin');
INSERT INTO `sys_user` VALUES ('2', '张三', '$2a$10$lZ3o7Ba9E5jeKcyLLREfgORqRfB.G5MTWs5GTuMT943mFa.LQ1EHO', '0101');
INSERT INTO `sys_user` VALUES ('3', '李四', '$2a$10$e263V7PiZxxsAUi4IrWps.QhP7lu5BB4wiRUoo2yo6KVTopdxqT.K', '0102');
INSERT INTO `sys_user` VALUES ('4', '王五', '$2a$10$cwSre5cMe99HJkC6bzsqWu.p3mtc9Dc/ZJsZZ5zcW4n2OjtDjfIvy', '0103');
INSERT INTO `sys_user` VALUES ('5', '赵六', '$2a$10$Ubznqnp7UHKW42Y/UalsguRhvgMk3FTEG66m78U3dgZMPvVQhwigG', '0104');
INSERT INTO `sys_user` VALUES ('6', '马七', '$2a$10$XYzc6RcTQciuxg4sfv46gOlMyCUN34QvNwF4m2XD5SYg81d5TxCTq', '0105');
INSERT INTO `sys_user` VALUES ('7', '侯八', '$2a$10$CFEb0OoJBQfBydtS79Yn6eQSUvv31OHY3nSqmZp9Fx9eu4O3JAkzC', '0106');
INSERT INTO `sys_user` VALUES ('8', '唐三藏', '$2a$10$JEnhjJuzYmYBcjX.JFa6aeJ3ZYqZMOt2vs9hW3D1TTHr7cF3qzhfa', '0201');
INSERT INTO `sys_user` VALUES ('9', '孙悟空', '$2a$10$YIRfi8xpunrgGD.CWuT4Eu8yXIjdGtFo2auUgms2QzeT0SC5J8n8C', '0202');
INSERT INTO `sys_user` VALUES ('10', '猪悟能', '$2a$10$uaTPSXYL3PWLlmvy.P69C.YoMkGHPfkGdq51.uAyOJJWPdg7dc0.i', '0203');
INSERT INTO `sys_user` VALUES ('11', '沙悟净', '$2a$10$g8Dl2cLW.iDJDe3fPkbS/ePhzku8zvaWMiefyICtXTpkQPJ9CDOm.', '0204');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  KEY `FKhh52n8vd4ny9ff4x9fb8v65qx` (`role_id`),
  KEY `FKb40xxfch70f5qnyfw8yme1n1s` (`user_id`),
  CONSTRAINT `FKb40xxfch70f5qnyfw8yme1n1s` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `FKhh52n8vd4ny9ff4x9fb8v65qx` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1');
INSERT INTO `sys_user_role` VALUES ('2', '2');
INSERT INTO `sys_user_role` VALUES ('3', '2');
INSERT INTO `sys_user_role` VALUES ('4', '2');
INSERT INTO `sys_user_role` VALUES ('5', '2');
INSERT INTO `sys_user_role` VALUES ('6', '2');
INSERT INTO `sys_user_role` VALUES ('7', '2');
INSERT INTO `sys_user_role` VALUES ('8', '2');
INSERT INTO `sys_user_role` VALUES ('9', '2');
INSERT INTO `sys_user_role` VALUES ('10', '2');
INSERT INTO `sys_user_role` VALUES ('11', '2');

-- ----------------------------
-- Table structure for tb_class
-- ----------------------------
DROP TABLE IF EXISTS `tb_class`;
CREATE TABLE `tb_class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `manager` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_class
-- ----------------------------
INSERT INTO `tb_class` VALUES ('1', '张老师', '19级软件1班');
INSERT INTO `tb_class` VALUES ('2', '李老师', '19级软件2班');
