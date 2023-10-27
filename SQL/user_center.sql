/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80032
 Source Host           : localhost:3306
 Source Schema         : user_center

 Target Server Type    : MySQL
 Target Server Version : 80032
 File Encoding         : 65001

 Date: 27/10/2023 08:22:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bonus_event_log
-- ----------------------------
DROP TABLE IF EXISTS `bonus_event_log`;
CREATE TABLE `bonus_event_log`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `user_id` bigint(0) DEFAULT NULL COMMENT '用户id',
  `value` int(0) DEFAULT NULL COMMENT '积分操作值',
  `event` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '积分事件（签到、投稿、兑换等）',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `description` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_bonus_event_log_user1_idx`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1716795538481278978 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '积分变更记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bonus_event_log
-- ----------------------------
INSERT INTO `bonus_event_log` VALUES (1, 1, -20, 'BUY', '2023-09-26 14:38:33', '兑换分享');
INSERT INTO `bonus_event_log` VALUES (2, 2, -5, 'BUY', '2023-09-26 14:44:04', '兑换分享');
INSERT INTO `bonus_event_log` VALUES (3, 1, -10, 'BUY', '2023-09-26 14:55:09', '兑换分享');
INSERT INTO `bonus_event_log` VALUES (4, 1, -10, 'BUY', '2023-09-26 15:00:49', '兑换分享');
INSERT INTO `bonus_event_log` VALUES (5, 2, 50, 'CONTRIBUTE', '2023-09-26 15:02:17', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (6, 2, 50, 'CONTRIBUTE', '2023-09-26 15:04:18', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (7, 2, 50, 'CONTRIBUTE', '2023-09-26 15:04:39', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (8, 1, -10, 'BUY', '2023-09-26 15:04:58', '兑换分享');
INSERT INTO `bonus_event_log` VALUES (1712351677935112193, 1, -11, 'BUY', '2023-10-12 14:17:09', '兑换分享');
INSERT INTO `bonus_event_log` VALUES (1712352415293677570, 1, -11, 'BUY', '2023-10-12 14:20:04', '兑换分享');
INSERT INTO `bonus_event_log` VALUES (1712362429471338498, 2, -11, 'BUY', '2023-10-12 14:59:52', '兑换分享');
INSERT INTO `bonus_event_log` VALUES (1712362838080385026, 2, -10, 'BUY', '2023-10-12 15:01:29', '兑换分享');
INSERT INTO `bonus_event_log` VALUES (1712372493758263298, 2, -10, 'BUY', '2023-10-12 15:39:52', '兑换分享');
INSERT INTO `bonus_event_log` VALUES (1712406849315000321, 1, -10, 'BUY', '2023-10-12 17:56:23', '兑换分享');
INSERT INTO `bonus_event_log` VALUES (1712429818179342338, 2, -10, 'BUY', '2023-10-12 19:27:39', '兑换分享');
INSERT INTO `bonus_event_log` VALUES (1712430038355136514, 1, -10, 'BUY', '2023-10-12 19:28:31', '兑换分享');
INSERT INTO `bonus_event_log` VALUES (1712430127060471810, 1, -10, 'BUY', '2023-10-12 19:28:52', '兑换分享');
INSERT INTO `bonus_event_log` VALUES (1713105582155399169, 1, 50, 'CONTRIBUTE', '2023-10-14 16:12:53', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (1713139993148162049, 1, 50, 'CONTRIBUTE', '2023-10-14 18:29:38', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (1713141058891493378, 1, 50, 'CONTRIBUTE', '2023-10-14 18:33:52', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (1713165284486754305, 1, -5, 'BUY', '2023-10-14 20:10:08', '兑换分享');
INSERT INTO `bonus_event_log` VALUES (1713165975846465538, 1, -10, 'BUY', '2023-10-14 20:12:52', '兑换分享');
INSERT INTO `bonus_event_log` VALUES (1713388919331819521, 2, 50, 'CONTRIBUTE', '2023-10-15 10:58:46', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (1716795538481278978, 1, -20, 'BUY', '2023-10-24 20:35:28', '兑换分享');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '手机号',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '密码',
  `nickname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '昵称',
  `roles` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '角色',
  `avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '头像地址',
  `bonus` int(0) NOT NULL DEFAULT 300 COMMENT '积分',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1710585047586836480 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '分享' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '13951905171', '123123', 'litao', 'admin', 'https://litao-oss.oss-cn-shanghai.aliyuncs.com/backiee-129115.jpg', 817, '2023-09-16 12:23:50', '2023-09-16 16:23:50');
INSERT INTO `user` VALUES (2, '13951905172', '123123', 'InfinityX7', 'user', 'https://litao-oss.oss-cn-shanghai.aliyuncs.com/backiee-98927.jpg', 359, '2023-09-18 10:38:51', '2023-09-18 12:38:51');
INSERT INTO `user` VALUES (1710566794391064576, '17751111829', '123123', '新用户', 'user', 'https://litao-oss.oss-cn-shanghai.aliyuncs.com/backiee-98927.jpg', 100, '2023-10-07 16:04:39', '2023-10-07 16:04:39');
INSERT INTO `user` VALUES (1710585047586836480, '17751111821', '123123', '新用户', 'user', 'https://litao-oss.oss-cn-shanghai.aliyuncs.com/backiee-98927.jpg', 100, '2023-10-07 17:17:11', '2023-10-07 17:17:11');

SET FOREIGN_KEY_CHECKS = 1;
