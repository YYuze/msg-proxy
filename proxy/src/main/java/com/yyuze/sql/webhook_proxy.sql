/*
 Navicat MySQL Data Transfer

 Source Server         : yyuze
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : webhook_agent

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 30/09/2018 14:49:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for Jar
-- ----------------------------
DROP TABLE IF EXISTS `Jar`;
CREATE TABLE `Jar`  (
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `class_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `jar_file_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of Jar
-- ----------------------------
INSERT INTO `Jar` VALUES ('BK-DINGDING', 'com.yyuzeprocessor.Bk2DingDing', 'file:///D:/work/projects/bk/webhook-agent/proxy-bk/target/proxy-bk-jar-with-dependencies.jar');
INSERT INTO `Jar` VALUES ('DEFAULT', 'com.yyuzeprocessor.Default', 'file:///D:/work/projects/bk/webhook-agent/proxy-demo/target/proxy-demo-jar-with-dependencies.jar');
INSERT INTO `Jar` VALUES ('DEMO-LOCALTEST-1', 'com.yyuzeprocessor.Demo2LocalTest1', 'file:///D:/work/projects/bk/webhook-agent/proxy-demo/target/proxy-demo-jar-with-dependencies.jar');
INSERT INTO `Jar` VALUES ('DEMO-LOCALTEST-2', 'com.yyuzeprocessor.Demo2LocalTest2', 'file:///D:/work/projects/bk/webhook-agent/proxy-demo/target/proxy-demo-jar-with-dependencies.jar');

-- ----------------------------
-- Table structure for Route
-- ----------------------------
DROP TABLE IF EXISTS `Route`;
CREATE TABLE `Route`  (
  `token` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'pk',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'fk',
  `request_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `request_token` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `request_method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `request_header_str` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '用 %&&% 分隔每个头',
  `request_success_flag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  PRIMARY KEY (`token`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of Route
-- ----------------------------
INSERT INTO `Route` VALUES ('00562287-8a42-464e-81f2-9b8390e15164', 'DEFAULT', 'http://localhost:8085/proxy/test_rcv', '123456789', 'POST', '', '{\"result\":true,\"code\":\"00\",\"message\":\"OK \"}');
INSERT INTO `Route` VALUES ('a0c1c35a-65e9-44ca-b712-37b52a37293b', 'BK-DINGDING', 'https://oapi.dingtalk.com/robot/send', '0693a754ac8def1f7f30afc1b59301ff89aa1d9cef7dde04c1e46c6e82281e9a', 'POST', 'Content-Type:application/json; charset=utf-8', '{\"errmsg\":\"ok\",\"errcode\":0}');
INSERT INTO `Route` VALUES ('a1a8d650-310b-4d79-a718-e754fb0237a6', 'DEMO-LOCALTEST-2', 'http://localhost:8085/proxy/test_rcv', '123456789', 'POST', '', '{\"result\":true,\"code\":\"00\",\"message\":\"OK \"}');
INSERT INTO `Route` VALUES ('a23fe06f-dab0-4e99-bf04-75a86a7e0ef7', 'DEMO-LOCALTEST-1', 'http://localhost:8085/proxy/test_rcv', '1234567', 'POST', '', '{\"result\":true,\"code\":\"00\",\"message\":\"OK \"}');

-- ----------------------------
-- Table structure for dropped_message
-- ----------------------------
DROP TABLE IF EXISTS `dropped_message`;
CREATE TABLE `dropped_message`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_on` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `transfer_data` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `route_token` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dropped_message
-- ----------------------------
INSERT INTO `dropped_message` VALUES (1, '2018-09-25 06:06:25', '{\"date\":\"2018-09-20 13:08\",\"heading\":\"test\",\"remark\":\"webhook\",\"message\":\"this is a bk webhook test.\",\"is_message_base64\":false} formatted (this is demo 2)', 'a1a8d650-310b-4d79-a718-e754fb0237a6');
INSERT INTO `dropped_message` VALUES (2, '2018-09-25 06:09:38', '{\"date\":\"2018-09-20 13:08\",\"heading\":\"test\",\"remark\":\"webhook\",\"message\":\"this is a bk webhook test.\",\"is_message_base64\":false} formatted (this is demo 2)', 'a1a8d650-310b-4d79-a718-e754fb0237a6');
INSERT INTO `dropped_message` VALUES (3, '2018-09-25 06:10:19', '{\"date\":\"2018-09-20 13:08\",\"heading\":\"test\",\"remark\":\"webhook\",\"message\":\"this is a bk webhook test.\",\"is_message_base64\":false} formatted (this is demo 2)', 'a1a8d650-310b-4d79-a718-e754fb0237a6');
INSERT INTO `dropped_message` VALUES (4, '2018-09-25 06:11:49', '{\"date\":\"2018-09-20 13:08\",\"heading\":\"test\",\"remark\":\"webhook\",\"message\":\"this is a bk webhook test.\",\"is_message_base64\":false} formatted (this is demo 2)', 'a1a8d650-310b-4d79-a718-e754fb0237a6');
INSERT INTO `dropped_message` VALUES (5, '2018-09-25 06:12:26', '{\"date\":\"2018-09-20 13:08\",\"heading\":\"test\",\"remark\":\"webhook\",\"message\":\"this is a bk webhook test.\",\"is_message_base64\":false} formatted (this is demo 2)', 'a1a8d650-310b-4d79-a718-e754fb0237a6');
INSERT INTO `dropped_message` VALUES (6, '2018-09-25 06:13:28', '{\"date\":\"2018-09-20 13:08\",\"heading\":\"test\",\"remark\":\"webhook\",\"message\":\"this is a bk webhook test.\",\"is_message_base64\":false} formatted (this is demo 2)', 'a1a8d650-310b-4d79-a718-e754fb0237a6');

-- ----------------------------
-- Table structure for overflowed_message
-- ----------------------------
DROP TABLE IF EXISTS `overflowed_message`;
CREATE TABLE `overflowed_message`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `route_token` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `transfer_data` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_loaded` int(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
