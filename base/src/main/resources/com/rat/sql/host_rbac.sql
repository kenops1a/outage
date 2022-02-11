/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50736
 Source Host           : localhost:3306
 Source Schema         : host_rbac

 Target Server Type    : MySQL
 Target Server Version : 50736
 File Encoding         : 65001

 Date: 11/02/2022 15:25:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for hm_asset
-- ----------------------------
DROP TABLE IF EXISTS `hm_asset`;
CREATE TABLE `hm_asset`  (
  `id` int(11) NOT NULL,
  `status` varchar(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '认证状态，0-失败，1-成功，2-审核中',
  `create_by` int(11) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` int(11) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hm_asset
-- ----------------------------
INSERT INTO `hm_asset` VALUES (1, '1', 1, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for hm_form
-- ----------------------------
DROP TABLE IF EXISTS `hm_form`;
CREATE TABLE `hm_form`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单编号',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '活动地址',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '活动类型',
  `date` datetime NOT NULL COMMENT '活动日期',
  `nick` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户昵称',
  `host_id` int(11) NOT NULL COMMENT '主持人id',
  `host_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '主持人姓名',
  `money` double NOT NULL COMMENT '交易金额',
  `status` varchar(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '2' COMMENT '订单状态0-失败，1-成功，2-进行中，3-删除',
  `create_by` int(11) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` int(11) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
  `update_label` varchar(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '订单更新标识，0-不可更新，待用户确认，1-可以修改',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hm_form
-- ----------------------------
INSERT INTO `hm_form` VALUES (1, '法国', '竞赛', '2021-12-13 15:08:03', 'dio', 1, '迪奥布兰度', 9999, '2', 2, '2021-12-13 15:08:29', 1, NULL, NULL, NULL);
INSERT INTO `hm_form` VALUES (2, '澳大利亚', '逃亡', '2021-12-23 15:32:31', '程心', 1, '迪奥布兰度', 4444, '3', 2, '2021-12-15 15:33:08', NULL, NULL, NULL, NULL);
INSERT INTO `hm_form` VALUES (3, '德国', '婚礼', '2029-03-02 15:34:09', '云天明', 1, '迪奥布兰度', 6666, '2', 3, '2021-12-15 15:34:53', NULL, NULL, NULL, NULL);
INSERT INTO `hm_form` VALUES (4, 'fsdafasdf', 'test', '2021-12-14 18:00:30', '迪奥', 1, 'dio', 0, '2', 3, '2021-12-14 18:00:30', 0, NULL, NULL, NULL);
INSERT INTO `hm_form` VALUES (5, 'fsdafasdf', 'test', '2021-12-14 18:01:38', '迪奥', 1, 'dio', 0, '1', 3, '2021-12-14 18:01:38', 0, NULL, NULL, NULL);
INSERT INTO `hm_form` VALUES (6, 'fsdafasdf', 'test', '2021-12-14 18:02:38', '迪奥', 1, 'dio', 0, '3', 3, '2021-12-14 18:02:38', 0, NULL, NULL, NULL);
INSERT INTO `hm_form` VALUES (7, 'a省d市f区fas', '生日', '2022-01-21 14:11:34', '我真的已经', 10, '371', 4, '3', 12, '2022-01-21 14:26:17', NULL, NULL, NULL, NULL);
INSERT INTO `hm_form` VALUES (8, 'a省d市f区null', '测试', '2020-02-02 02:02:00', '我真的已经', 10, '371', 9999, '3', 12, '2022-01-21 14:28:36', NULL, NULL, NULL, NULL);
INSERT INTO `hm_form` VALUES (9, 'a省d市f区null', '测试', '2020-02-02 02:02:00', '我真的已经', 10, '371', 9999, '3', 12, '2022-01-21 14:30:07', NULL, NULL, NULL, NULL);
INSERT INTO `hm_form` VALUES (10, 'a省d市f区', '测试', '2020-04-07 15:32:00', '我真的已经', 10, '371', 2, '3', 12, '2022-01-21 16:35:16', NULL, NULL, NULL, NULL);
INSERT INTO `hm_form` VALUES (11, 'a省d市f区', '测试', '2020-04-07 15:32:00', '我真的已经', 10, '371', 2, '3', 12, '2022-01-21 16:35:18', NULL, NULL, NULL, NULL);
INSERT INTO `hm_form` VALUES (12, 'a省d市f区', '测试', '2020-04-07 15:32:00', '我真的已经', 10, '371', 2, '3', 12, '2022-01-21 16:35:20', NULL, NULL, NULL, NULL);
INSERT INTO `hm_form` VALUES (13, 'a省d市f区', '测试', '2020-04-07 15:32:00', '我真的已经', 10, '371', 2, '1', 12, '2022-01-21 16:35:21', NULL, NULL, NULL, NULL);
INSERT INTO `hm_form` VALUES (14, 'a省d市f区', '测试', '2020-04-07 15:32:00', '我真的已经', 10, '371', 2, '1', 12, '2022-01-21 16:35:22', NULL, NULL, NULL, NULL);
INSERT INTO `hm_form` VALUES (15, 'a省d市f区', '测试', '2020-04-07 15:32:00', '我真的已经', 10, '371', 2, '0', 12, '2022-01-21 16:35:24', NULL, NULL, NULL, NULL);
INSERT INTO `hm_form` VALUES (16, 'a省d市f区', '测试', '2020-04-07 15:32:00', '我真的已经', 10, '371', 2, '0', 12, '2022-01-21 16:35:26', NULL, NULL, NULL, NULL);
INSERT INTO `hm_form` VALUES (17, 'a省d市f区', '测试', '2020-04-07 15:32:00', '我真的已经', 10, '371', 2, '2', 12, '2022-01-21 16:35:27', NULL, NULL, NULL, NULL);
INSERT INTO `hm_form` VALUES (18, 'a省d市f区', '测试', '2020-04-07 15:32:00', '我真的已经', 10, '371', 2, '2', 12, '2022-01-21 16:35:29', NULL, NULL, NULL, NULL);
INSERT INTO `hm_form` VALUES (19, 'b省c市f区', '测试', '2020-02-12 12:12:00', '我真的已经', 10, '371', 9999, '2', 12, '2022-01-24 16:51:56', NULL, NULL, NULL, NULL);
INSERT INTO `hm_form` VALUES (20, 'b省c市f区', '测试', '2020-02-12 12:12:00', '我真的已经', 10, '371', 9999, '2', 12, '2022-01-24 16:52:02', NULL, NULL, NULL, NULL);
INSERT INTO `hm_form` VALUES (21, 'b省c市f区', '测试', '2020-02-12 12:12:00', '我真的已经', 10, '371', 9999, '2', 12, '2022-01-24 16:52:04', NULL, NULL, NULL, NULL);
INSERT INTO `hm_form` VALUES (22, 'b省c市f区', '测试', '2020-02-12 12:12:00', '我真的已经', 10, '371', 9999, '2', 12, '2022-01-24 16:52:06', NULL, NULL, NULL, NULL);
INSERT INTO `hm_form` VALUES (23, 'b省c市f区', '测试', '2020-02-12 12:12:00', '我真的已经', 10, '371', 9999, '2', 12, '2022-01-24 16:52:07', NULL, NULL, NULL, NULL);
INSERT INTO `hm_form` VALUES (24, 'b省c市f区', '测试', '2020-02-12 12:12:00', '我真的已经', 10, '371', 9999, '2', 12, '2022-01-24 16:52:09', NULL, NULL, NULL, NULL);
INSERT INTO `hm_form` VALUES (25, 'b省c市f区', '测试', '2020-02-12 12:12:00', '我真的已经', 10, '371', 9999, '2', 12, '2022-01-24 16:52:10', NULL, NULL, NULL, NULL);
INSERT INTO `hm_form` VALUES (26, 'b省c市f区', '测试', '2020-02-12 12:12:00', '我真的已经', 10, '371', 9999, '2', 12, '2022-01-24 16:52:12', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for hm_host
-- ----------------------------
DROP TABLE IF EXISTS `hm_host`;
CREATE TABLE `hm_host`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型',
  `money` double NULL DEFAULT NULL COMMENT '佣金',
  `counts` int(11) NULL DEFAULT NULL COMMENT '成功交易数量',
  `create_by` int(11) NOT NULL COMMENT '创建者,添加unique唯一标识',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` int(11) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`, `create_by`) USING BTREE,
  UNIQUE INDEX `create_by`(`create_by`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of hm_host
-- ----------------------------
INSERT INTO `hm_host` VALUES (1, '中文', 1, NULL, 1, '2021-12-28 10:35:49', NULL, NULL, NULL);
INSERT INTO `hm_host` VALUES (2, '竞赛', 2, NULL, 2, '2021-12-18 14:22:41', NULL, NULL, NULL);
INSERT INTO `hm_host` VALUES (3, '测试', 3, NULL, 3, '2021-12-01 14:23:01', NULL, NULL, NULL);
INSERT INTO `hm_host` VALUES (4, '开发', 4, NULL, 4, '2021-12-01 14:23:17', NULL, NULL, NULL);
INSERT INTO `hm_host` VALUES (18, '我靠', 999, NULL, 6, '2021-12-30 14:12:09', NULL, NULL, NULL);
INSERT INTO `hm_host` VALUES (19, '婚礼', 777, NULL, 7, '2022-01-04 11:25:03', NULL, NULL, NULL);
INSERT INTO `hm_host` VALUES (20, '婚礼', 999, NULL, 8, '2022-01-12 11:25:20', NULL, NULL, NULL);
INSERT INTO `hm_host` VALUES (21, '婚礼', 999, NULL, 9, '2022-01-18 11:25:36', NULL, NULL, NULL);
INSERT INTO `hm_host` VALUES (22, '婚礼', 9999, NULL, 10, '2022-01-04 11:25:51', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for hm_menu
-- ----------------------------
DROP TABLE IF EXISTS `hm_menu`;
CREATE TABLE `hm_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单id',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '菜单名称',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '菜单路径',
  `pid` int(11) NOT NULL COMMENT '父级菜单',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hm_menu
-- ----------------------------
INSERT INTO `hm_menu` VALUES (1, '个人信息', '/user/info', 0);
INSERT INTO `hm_menu` VALUES (2, '订单管理', '/form/manager', 0);
INSERT INTO `hm_menu` VALUES (3, '认证管理', '/host/asset', 0);
INSERT INTO `hm_menu` VALUES (4, '主持人信息', '/host/info', 0);
INSERT INTO `hm_menu` VALUES (5, '消息管理', '/user/message', 0);
INSERT INTO `hm_menu` VALUES (6, '动态管理', '/user/weblock', 0);

-- ----------------------------
-- Table structure for hm_message
-- ----------------------------
DROP TABLE IF EXISTS `hm_message`;
CREATE TABLE `hm_message`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '消息id',
  `user_id` int(11) NOT NULL COMMENT '接收者id',
  `message` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT ' ' COMMENT '消息内容',
  `status` varchar(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '0' COMMENT '消息状态，0-未发送，1-已发送, 2-已删除',
  `create_by` int(11) NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` int(11) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hm_message
-- ----------------------------
INSERT INTO `hm_message` VALUES (1, 1, '不做人了', '0', 2, '2021-12-07 08:56:34', NULL, NULL, NULL);
INSERT INTO `hm_message` VALUES (2, 2, '不做人了', '0', 1, '2021-12-01 08:57:26', NULL, NULL, NULL);
INSERT INTO `hm_message` VALUES (3, 1, ' ', '0', 2, '2021-12-23 16:26:51', NULL, NULL, NULL);
INSERT INTO `hm_message` VALUES (4, 0, ' ', '0', 0, '0000-00-00 00:00:00', NULL, NULL, NULL);
INSERT INTO `hm_message` VALUES (5, 0, ' ', '0', 0, '0000-00-00 00:00:00', NULL, NULL, NULL);
INSERT INTO `hm_message` VALUES (6, 1, 'what\'s up', '1', 2, '2021-12-23 16:51:26', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for hm_role
-- ----------------------------
DROP TABLE IF EXISTS `hm_role`;
CREATE TABLE `hm_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '角色名称',
  `status` varchar(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '角色状态0-禁用，1-正常，2-删除',
  `create_by` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '修改者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hm_role
-- ----------------------------
INSERT INTO `hm_role` VALUES (1, '普通用户', '1', 'dio', NULL, NULL, NULL, NULL);
INSERT INTO `hm_role` VALUES (2, '主持人', '1', 'dio', NULL, NULL, NULL, NULL);
INSERT INTO `hm_role` VALUES (3, '管理员', '1', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for hm_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `hm_role_menu`;
CREATE TABLE `hm_role_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色菜单绑定',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `menu_id` int(11) NOT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hm_role_menu
-- ----------------------------
INSERT INTO `hm_role_menu` VALUES (1, 3, 1);
INSERT INTO `hm_role_menu` VALUES (2, 3, 2);
INSERT INTO `hm_role_menu` VALUES (3, 3, 3);
INSERT INTO `hm_role_menu` VALUES (4, 3, 5);
INSERT INTO `hm_role_menu` VALUES (6, 3, 6);
INSERT INTO `hm_role_menu` VALUES (7, 1, 1);
INSERT INTO `hm_role_menu` VALUES (8, 1, 2);
INSERT INTO `hm_role_menu` VALUES (9, 1, 5);
INSERT INTO `hm_role_menu` VALUES (10, 1, 6);

-- ----------------------------
-- Table structure for hm_user
-- ----------------------------
DROP TABLE IF EXISTS `hm_user`;
CREATE TABLE `hm_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '真实姓名',
  `nick` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '昵称',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '手机号',
  `sfz` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '身份证',
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '邮箱',
  `sex` varchar(1) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '2' COMMENT '性别0-男，1-女，2-保密，默认为2',
  `age` int(3) NULL DEFAULT NULL COMMENT '年龄',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '地区',
  `yhk` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '银行卡',
  `status` varchar(1) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '1' COMMENT '用户状态0-禁用，1-正常，2删除，默认为1',
  `password` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '密码',
  `create_by` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '修改者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hm_user
-- ----------------------------
INSERT INTO `hm_user` VALUES (1, 'dio', '迪奥', '10086', NULL, 'kenops1a@qq.com', '0', 100, '阿富汗酋长国', NULL, '1', 'helloworld', NULL, '2021-12-28 15:16:20', NULL, NULL, NULL);
INSERT INTO `hm_user` VALUES (2, 'jojo', 'jojo', '10087', NULL, 'kenops2a@qq.com', '0', 18, '阿富汗酋长国', NULL, '1', 'xx', '0', '2021-01-28 15:16:23', '0', NULL, NULL);
INSERT INTO `hm_user` VALUES (3, '灰太狼', '灰太狼', '10076', NULL, 'huitailang.com', '0', 20, '阿富汗酋长国', NULL, '1', 'helloworld', NULL, '2021-01-01 15:16:29', NULL, NULL, NULL);
INSERT INTO `hm_user` VALUES (4, '喜羊羊', 'xyy', '10010', NULL, 'xx', '2', 20, '阿富汗酋长国', NULL, '1', 'helloworld', NULL, '2019-02-28 15:16:35', NULL, NULL, NULL);
INSERT INTO `hm_user` VALUES (5, '神奇海螺', '神奇海螺', '10010', NULL, '10086@qq.com', '2', 20, '阿富汗酋长国', NULL, '1', 'helloworld', '0', '2008-10-01 15:16:41', '0', NULL, NULL);
INSERT INTO `hm_user` VALUES (6, 'what\'s up', 'what\'up', '10010', NULL, 'magicsea@qq.com', '2', 20, '阿富汗酋长国', NULL, '1', 'xx', '0', '2016-10-28 15:16:49', '0', NULL, NULL);
INSERT INTO `hm_user` VALUES (7, '你酒桶玩得跟shi一样', '你酒桶玩得跟屎一样', '10010', NULL, 'kenopsia21@qq.com', '2', 20, '阿富汗酋长国', NULL, '1', 'helloworld', '0', '2021-12-22 16:48:30', '0', NULL, NULL);
INSERT INTO `hm_user` VALUES (8, '369', '一个赛季练个纳尔有那么难吗', '10010', NULL, '10000001@qq.com', '2', 20, '阿富汗酋长国', NULL, '1', '10000001', NULL, '2021-12-30 14:17:44', NULL, NULL, NULL);
INSERT INTO `hm_user` VALUES (9, '370', '我就不会玩一个纳尔', '10010', NULL, '10000002@qq.com', '2', 20, '阿富汗酋长国', NULL, '1', '10000002', NULL, '2021-12-30 14:18:28', NULL, NULL, NULL);
INSERT INTO `hm_user` VALUES (10, '371', '我真的已经', '10086', NULL, '10000003@qq.com', '2', 99, '阿富汗酋长国', NULL, '1', '10000003', NULL, '2021-12-23 14:19:08', NULL, NULL, NULL);
INSERT INTO `hm_user` VALUES (11, '372', '不信你去看录像', '10010', NULL, '10000004@qq.com', '2', 20, '阿富汗酋长国', NULL, '1', '10000004', NULL, '2021-12-30 14:21:31', NULL, NULL, NULL);
INSERT INTO `hm_user` VALUES (12, '刘玄德', '山泥若', '18697534623', '142602200005074012', '2737767241@qq.com', '男', 30, '阿拉伯', NULL, '1', 'helloworld', NULL, '2022-01-06 11:47:21', '0', NULL, NULL);
INSERT INTO `hm_user` VALUES (13, 'dio', 'xyy', '10086', NULL, 'jks@qq.com', '2', 20, '阿富汗酋长国', NULL, '1', '10000000', NULL, '2022-01-19 16:32:02', NULL, NULL, NULL);
INSERT INTO `hm_user` VALUES (14, 'xiuhuadazhentou', 'xiuhuadazhentou', '10010', NULL, '1508300855@qq.com', '2', 20, '阿富汗酋长国', NULL, '1', 'w100200300', '0', '2022-01-12 15:52:54', '0', NULL, NULL);

-- ----------------------------
-- Table structure for hm_user_role
-- ----------------------------
DROP TABLE IF EXISTS `hm_user_role`;
CREATE TABLE `hm_user_role`  (
  `user_id` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户id',
  `role_id` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '角色id',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic STATS_PERSISTENT = 0;

-- ----------------------------
-- Records of hm_user_role
-- ----------------------------
INSERT INTO `hm_user_role` VALUES ('1', '3');
INSERT INTO `hm_user_role` VALUES ('10', '2');
INSERT INTO `hm_user_role` VALUES ('2', '3');
INSERT INTO `hm_user_role` VALUES ('3', '2');
INSERT INTO `hm_user_role` VALUES ('4', '3');
INSERT INTO `hm_user_role` VALUES ('6', '2');
INSERT INTO `hm_user_role` VALUES ('7', '2');
INSERT INTO `hm_user_role` VALUES ('8', '2');
INSERT INTO `hm_user_role` VALUES ('9', '2');

SET FOREIGN_KEY_CHECKS = 1;
