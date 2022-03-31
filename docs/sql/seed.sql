/*
 Navicat Premium Data Transfer

 Source Server         : 云MySQL5.7
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : cdb-pr3h14sl.gz.tencentcdb.com:10001
 Source Schema         : seed

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 24/11/2020 15:01:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`          varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '主键',
    `name`        varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL COMMENT '名称',
    `password`    varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
    `email`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL COMMENT '邮箱',
    `create_time` datetime(0)                                                   NULL DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime(0)                                                   NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user`
VALUES ('08ee656852d253b8cc0f80b9e4a7c0f2', '李建', '6sSY/6YrFcbHud21UUxNaw==', 'a2417978181@163.com', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
