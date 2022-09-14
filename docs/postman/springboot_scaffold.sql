/*
 Navicat Premium Data Transfer

 Source Server         : 公司-210-MySQL5.7-内网连接
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : 172.16.156.210:3306
 Source Schema         : springboot_scaffold

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 14/09/2022 14:53:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`           varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
    `create_time`  datetime(0)                                                  NULL DEFAULT NULL COMMENT '创建时间',
    `update_time`  datetime(0)                                                  NULL DEFAULT NULL COMMENT '更新时间',
    `deleted`      int(2)                                                       NULL DEFAULT 0 COMMENT '逻辑删除',
    `name`         varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
    `password`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
    `email`        varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
    `phone_number` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号码',
    `id_number`    varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '身份证号码',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user`
VALUES ('35eb8d9f41e6dd1952ff689cf731ccbb', '2022-09-14 14:51:15', NULL, 0, 'admin', 'qnO8qTn/sbOyuW2VkBcv6g==',
        'a2417978181@163.com', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
