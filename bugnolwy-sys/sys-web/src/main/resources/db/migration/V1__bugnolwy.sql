/*
 Navicat Premium Data Transfer

 Source Server         : bugnolwy
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : bugnolwy

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 15/10/2020 04:15:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

/* Table structure for sys_dept */
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '资源名称',
  `note` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `parentId` int(0) NULL DEFAULT NULL COMMENT '上级部门',
  `create_time` timestamp(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modified_time` timestamp(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  `version` int(0) NULL DEFAULT NULL COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门管理' ROW_FORMAT = Dynamic;

/* Records of sys_dept */
INSERT INTO `sys_dept` VALUES (1, '技术大牛', '轻松手撕代码', NULL, '2020-10-15 04:08:51', '2020-10-15 04:08:51', 0, NULL);
INSERT INTO `sys_dept` VALUES (3, 'Java练习生', '天天CRUD', 1, '2020-10-05 04:23:13', '2020-10-05 04:23:13', 0, NULL);

/* Table structure for sys_log */
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '日志id',
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建用户',
  `operation` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户操作',
  `method` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '请求方法',
  `time` int(0) NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `create_time` timestamp(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 581 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '系统日志' ROW_FORMAT = Dynamic;


/* Table structure for sys_menu */
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '资源名称',
  `url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '资源URL',
  `type` int(0) NULL DEFAULT NULL COMMENT '类型     1：菜单   2：按钮',
  `parentId` int(0) NULL DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `create_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modified_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  `version` int(0) NULL DEFAULT NULL COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '资源管理' ROW_FORMAT = Dynamic;

/* Records of sys_menu */
INSERT INTO `sys_menu` VALUES (1, '系统管理', '', 1, NULL, '2020-07-10 00:00:00', '2020-07-10 02:56:40', 0, NULL);
INSERT INTO `sys_menu` VALUES (2, '日志管理', 'log/log_list', 1, 1, '2020-07-10 02:55:31', '2020-07-10 02:56:40', 0, NULL);
INSERT INTO `sys_menu` VALUES (3, '用户管理', 'user/user_list', 1, 1, '2020-07-10 02:55:36', '2020-07-10 02:56:40', 0, NULL);
INSERT INTO `sys_menu` VALUES (4, '菜单管理', 'menu/menu_list', 1, 1, '2020-07-10 02:55:41', '2020-07-10 02:56:40', 0, NULL);
INSERT INTO `sys_menu` VALUES (5, '角色管理', 'role/role_list', 1, 1, '2020-07-10 02:55:45', '2020-07-10 02:56:40', 0, NULL);
INSERT INTO `sys_menu` VALUES (6, '查询', 'menu/doFindObjects', 2, 4, '2020-07-10 02:55:49', '2020-07-10 02:56:40', 0, NULL);
INSERT INTO `sys_menu` VALUES (7, '添加', 'menu/doSaveObject', 2, 4, '2020-07-10 02:55:52', '2020-07-10 02:56:40', 0, NULL);
INSERT INTO `sys_menu` VALUES (8, '修改', 'menu/doUpdateObject', 2, 4, '2020-07-10 02:55:57', '2020-07-10 02:56:40', 0, NULL);
INSERT INTO `sys_menu` VALUES (9, '删除', 'menu/doDeleteObject', 2, 4, '2020-07-10 02:56:00', '2020-07-10 02:56:40', 0, NULL);
INSERT INTO `sys_menu` VALUES (10, '查询', 'user/doFindPageObjects', 2, 3, '2020-07-10 02:56:04', '2020-07-10 02:56:40', 0, NULL);
INSERT INTO `sys_menu` VALUES (11, '查询', 'role/doFindPageObjects', 2, 5, '2020-07-10 02:56:08', '2020-07-10 02:56:40', 0, NULL);
INSERT INTO `sys_menu` VALUES (12, '新增', 'user/doSaveObject', 2, 3, '2020-07-10 02:56:11', '2020-07-10 02:56:40', 0, NULL);
INSERT INTO `sys_menu` VALUES (13, '修改', 'user/doUpdateObject', 2, 3, '2020-07-10 02:56:15', '2020-07-10 02:56:40', 0, NULL);
INSERT INTO `sys_menu` VALUES (14, '添加', 'role/doSaveObject', 2, 5, '2020-07-10 02:56:19', '2020-07-10 02:56:40', 0, NULL);
INSERT INTO `sys_menu` VALUES (15, '修改', 'role/doUpdateObject', 2, 5, '2020-07-10 02:56:23', '2020-07-10 02:56:40', 0, NULL);
INSERT INTO `sys_menu` VALUES (16, '删除', 'role/doDeleteObject', 2, 5, '2020-07-10 02:56:27', '2020-07-10 02:56:40', 0, NULL);
INSERT INTO `sys_menu` VALUES (17, '删除', 'log/doDeleteObjects', 2, 2, '2020-07-10 02:56:31', '2020-07-10 02:56:40', 0, NULL);
INSERT INTO `sys_menu` VALUES (18, '禁用启用', 'user/doEnableById', 2, 3, '2020-07-10 02:56:35', '2020-07-10 02:56:40', 0, NULL);
INSERT INTO `sys_menu` VALUES (19, '部门管理', 'dept/dept_list', 1, 1, '2020-10-05 03:12:36', '2020-10-05 03:12:36', 0, NULL);
INSERT INTO `sys_menu` VALUES (20, '添加', 'dept/doSaveObject', 2, 19, '2020-07-10 02:56:40', '2020-07-10 02:56:40', 0, NULL);
INSERT INTO `sys_menu` VALUES (21, '修改', 'dept/dept_edit', 2, 19, '2020-07-10 02:56:40', '2020-07-10 02:56:40', 0, NULL);
INSERT INTO `sys_menu` VALUES (22, '删除', 'dept/doDeleteObject', 2, 19, '2020-07-10 02:56:40', '2020-07-10 02:56:40', 0, NULL);
INSERT INTO `sys_menu` VALUES (23, '查询', 'dept/doFindObjects', 2, 19, '2020-07-10 02:56:40', '2020-07-10 02:56:40', 0, NULL);
INSERT INTO `sys_menu` VALUES (24, '查询', 'log/doFindPageObjects', 2, 2, '2020-07-10 02:56:40', '2020-07-10 02:56:40', 0, NULL);
INSERT INTO `sys_menu` VALUES (25, '修改密码', 'user/pwd_edit', 1, 1, '2020-07-10 02:56:40', '2020-07-10 02:56:40', 0, NULL);

/* Table structure for sys_role */
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '角色名称',
  `note` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modified_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  `version` int(0) NULL DEFAULT NULL COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '角色' ROW_FORMAT = Dynamic;

/* Records of sys_role */
INSERT INTO `sys_role` VALUES (1, 'admin', '系统管理员', '2020-10-05 03:13:53', '2020-10-05 03:13:54', 0, NULL);

/* Table structure for sys_role_menu */
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `role_id` int(0) NULL DEFAULT NULL COMMENT '角色id',
  `menu_id` int(0) NULL DEFAULT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 468 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '角色与菜单对应关系' ROW_FORMAT = Dynamic;

/* Records of sys_role_menu */
INSERT INTO `sys_role_menu` VALUES (1, 1, 1);
INSERT INTO `sys_role_menu` VALUES (2, 1, 2);
INSERT INTO `sys_role_menu` VALUES (3, 1, 3);
INSERT INTO `sys_role_menu` VALUES (4, 1, 4);
INSERT INTO `sys_role_menu` VALUES (5, 1, 5);
INSERT INTO `sys_role_menu` VALUES (6, 1, 6);
INSERT INTO `sys_role_menu` VALUES (7, 1, 7);
INSERT INTO `sys_role_menu` VALUES (8, 1, 8);
INSERT INTO `sys_role_menu` VALUES (9, 1, 9);
INSERT INTO `sys_role_menu` VALUES (10, 1, 10);
INSERT INTO `sys_role_menu` VALUES (11, 1, 11);
INSERT INTO `sys_role_menu` VALUES (12, 1, 12);
INSERT INTO `sys_role_menu` VALUES (13, 1, 13);
INSERT INTO `sys_role_menu` VALUES (14, 1, 14);
INSERT INTO `sys_role_menu` VALUES (15, 1, 15);
INSERT INTO `sys_role_menu` VALUES (16, 1, 16);
INSERT INTO `sys_role_menu` VALUES (17, 1, 17);
INSERT INTO `sys_role_menu` VALUES (18, 1, 18);
INSERT INTO `sys_role_menu` VALUES (19, 1, 19);
INSERT INTO `sys_role_menu` VALUES (20, 1, 20);
INSERT INTO `sys_role_menu` VALUES (21, 1, 21);
INSERT INTO `sys_role_menu` VALUES (22, 1, 22);
INSERT INTO `sys_role_menu` VALUES (23, 1, 23);
INSERT INTO `sys_role_menu` VALUES (24, 1, 24);
INSERT INTO `sys_role_menu` VALUES (25, 1, 25);

/* Table structure for sys_user */
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '手机号',
  `dept_id` int(0) NULL DEFAULT NULL COMMENT '部门id',
  `create_time` timestamp(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modified_time` timestamp(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `enabled` tinyint(1) NULL DEFAULT 1 COMMENT '状态  0：禁用   1：正常',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  `version` int(0) NULL DEFAULT NULL COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '系统用户' ROW_FORMAT = Dynamic;

/* Records of sys_user */
INSERT INTO `sys_user` VALUES (1, 'admin', '$2a$10$bmB4DduKtDPUgP5.v3z4S.yGQSchZ/OEejLoux0qeAuwdzdhOUxdi', 'lwylwy777777@163.com', '', 3, '2020-10-15 04:08:32', '2020-10-15 04:08:32', 1, 0, NULL);

/* Table structure for sys_user_role */
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` int(0) NULL DEFAULT NULL COMMENT '用户ID',
  `role_id` int(0) NULL DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 62 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户与角色对应关系' ROW_FORMAT = Dynamic;

/* Records of sys_user_role */
INSERT INTO `sys_user_role` VALUES (50, 1, 1);

SET FOREIGN_KEY_CHECKS = 1;
