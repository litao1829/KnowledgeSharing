/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80032
 Source Host           : localhost:3306
 Source Schema         : content_center

 Target Server Type    : MySQL
 Target Server Version : 80032
 File Encoding         : 65001

 Date: 27/10/2023 08:20:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mid_user_share
-- ----------------------------
DROP TABLE IF EXISTS `mid_user_share`;
CREATE TABLE `mid_user_share`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `share_id` bigint(0) NOT NULL COMMENT 'share.id',
  `user_id` bigint(0) NOT NULL COMMENT 'user.id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_mid_user_share_share1_idx`(`share_id`) USING BTREE,
  INDEX `fk_mid_user_share_user1_idx`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1716795538934235138 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户-分享中间表【描述用户购买的分享】' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mid_user_share
-- ----------------------------
INSERT INTO `mid_user_share` VALUES (1, 1, 1);
INSERT INTO `mid_user_share` VALUES (2, 12, 1);
INSERT INTO `mid_user_share` VALUES (3, 11, 1);
INSERT INTO `mid_user_share` VALUES (1712429818573496321, 22, 2);
INSERT INTO `mid_user_share` VALUES (1713165284759351297, 9, 1);
INSERT INTO `mid_user_share` VALUES (1716795538934235138, 1712726634108092417, 1);

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '内容',
  `show_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否显示 0:否 1:是',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (1, '程序员的启蒙课', 1, '2023-09-29 12:36:49');
INSERT INTO `notice` VALUES (2, '终身学习者', 0, '2023-10-04 11:16:18');

-- ----------------------------
-- Table structure for share
-- ----------------------------
DROP TABLE IF EXISTS `share`;
CREATE TABLE `share`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(0) NOT NULL DEFAULT 0 COMMENT '发布人id',
  `title` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '标题',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '修改时间',
  `is_original` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否原创 0:否 1:是',
  `author` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '作者',
  `cover` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '封面',
  `summary` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '概要信息',
  `price` int(0) NOT NULL DEFAULT 0 COMMENT '价格（需要的积分）',
  `download_url` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '下载地址',
  `buy_count` int(0) NOT NULL DEFAULT 0 COMMENT '下载数 ',
  `show_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否显示 0:否 1:是',
  `audit_status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '审核状态 NOT_YET: 待审核 PASSED:审核通过 REJECTED:审核不通过',
  `reason` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '审核不通过原因',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1717175302639501313 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '分享表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of share
-- ----------------------------
INSERT INTO `share` VALUES (1, 2, 'Vue.js 核心技术', '2023-10-18 11:41:05', '2023-10-18 11:41:05', 0, 'Brian', 'https://img3.sycdn.imooc.com/5c21e60d0822d58e06000338-360-202.jpg', '了解vue 的核心技术，建立前端组件化的思想', 10, '链接: https://pan.baidu.com/s/1Hk9i9VOTz2eSuy8p-kFGrQ  密码: 5njn', 0, 1, 'PASS', '通过审核');
INSERT INTO `share` VALUES (2, 1, 'Go语言从入门到达人之路', '2023-10-15 17:02:06', '2023-10-15 17:02:08', 0, 'Google工程师', 'https://img1.sycdn.imooc.com/szimg/5cf47ccf0834940306000338-240-180.jpg', '从0开始搭建分布式爬虫，理解分布式系统设计思想\n从0开始搭建分布式爬虫，理解分布式系统设计思想\n从0开始搭建分布式爬虫，理解分布式系统设计思想\n从0开始搭建分布式爬虫，理解分布式系统设计思想\n', 10, '链接: https://pan.baidu.com/s/1Hk9i9VOTz2eSuy8p-kFGrQ  密码: 5njn', 0, 1, 'PASS', '通过审核');
INSERT INTO `share` VALUES (3, 1, 'Spring Cloud分布式微服务实战', '2023-10-15 16:00:23', '2023-10-15 16:00:26', 0, '风间影月', 'https://img1.sycdn.imooc.com/szimg/5cf47ccf0834940306000338-240-180.jpg', 'Spring Cloud分布式微服务实战\n养成应对复杂业务的综合技术能力\n分布式/前后端分离/项目分层聚合 一体化开发门户平台＋媒体中心+运营中心3大业务平台', 10, '链接: https://pan.baidu.com/s/1Hk9i9VOTz2eSuy8p-kFGrQ  密码: 5njn', 0, 1, 'PASS', '通过审核');
INSERT INTO `share` VALUES (4, 1, '算法与数据结构', '2023-10-01 17:19:56', '2023-10-01 17:19:58', 0, '算法爱好者', 'https://niit-soft.oss-cn-hangzhou.aliyuncs.com/share-app/算法与数据结构.jpg', '数据结构是一种具有一定逻辑关系，在计算机中应用某种存储结构，并且封装了相应操作的数据元素集合。它包含三方面的内容，逻辑关系、存储关系及操作。', 5, '链接: https://pan.baidu.com/s/1Hk9i9VOTz2eSuy8p-kFGrQ  密码: 5njn', 20, 1, 'PASS', '通过审核');
INSERT INTO `share` VALUES (5, 1, '微信小程序实战', '2023-10-01 17:19:56', '2023-10-01 17:19:58', 0, 'Tencent', 'https://niit-soft.oss-cn-hangzhou.aliyuncs.com/share-app/微信小程序.jpg', '小程序是一种新的开放能力，开发者可以快速地开发一个小程序。小程序可以在微信内被便捷地获取和传播，同时具有出色的使用体验。', 10, '链接: https://pan.baidu.com/s/1Hk9i9VOTz2eSuy8p-kFGrQ  密码: 5njn', 20, 1, 'PASS', '通过审核');
INSERT INTO `share` VALUES (6, 1, '消息队列rabbitMQ', '2023-10-01 17:19:56', '2023-10-01 17:19:58', 0, 'MQ社区', 'https://niit-soft.oss-cn-hangzhou.aliyuncs.com/share-app/rabbitMQ.jpg', '消息（Message）是指在应用间传送的数据。消息可以非常简单，比如只包含文本字符串，也可以更复杂，可能包含嵌入对象。', 15, '链接: https://pan.baidu.com/s/1Hk9i9VOTz2eSuy8p-kFGrQ  密码: 5njn', 20, 1, 'PASS', '通过审核');
INSERT INTO `share` VALUES (7, 1, 'Vue.js中文文档', '2023-10-01 17:19:56', '2023-10-01 17:19:58', 0, '尤雨溪', 'https://niit-soft.oss-cn-hangzhou.aliyuncs.com/share-app/Vue.jpg', 'Vue是一套用于构建用户界面的渐进式框架。与其它大型框架不同的是，Vue 被设计为可以自底向上逐层应用。', 10, '链接: https://pan.baidu.com/s/1Hk9i9VOTz2eSuy8p-kFGrQ  密码: 5njn', 20, 1, 'PASS', '通过审核');
INSERT INTO `share` VALUES (8, 1, 'Spring Cloud Alibaba实战', '2023-10-01 17:19:56', '2023-10-01 17:19:58', 0, '阿里巴巴', 'https://niit-soft.oss-cn-hangzhou.aliyuncs.com/share-app/Spring Cloud Alibaba.jpg', 'Spring Cloud Alibaba 致力于提供微服务开发的一站式解决方案。此项目包含开发分布式应用服务的必需组件，方便开发者通过 Spring Cloud 编程模型轻松使用这些组件来开发分布式应用服务。', 10, '链接: https://pan.baidu.com/s/1Hk9i9VOTz2eSuy8p-kFGrQ  密码: 5njn', 20, 1, 'PASS', '通过审核');
INSERT INTO `share` VALUES (9, 1, 'Python学习笔记', '2023-10-01 17:19:56', '2023-10-01 17:19:58', 1, 'Python之父', 'https://niit-soft.oss-cn-hangzhou.aliyuncs.com/share-app/Python.jpg', 'Python 是一种解释型、面向对象、动态数据类型的高级程序设计语言。Python 由 Guido van Rossum 于 1989 年底发明，第一个公开发行版发行于 1991 年。', 5, '链接: https://pan.baidu.com/s/1Hk9i9VOTz2eSuy8p-kFGrQ  密码: 5njn', 20, 1, 'PASS', '通过审核');
INSERT INTO `share` VALUES (10, 2, 'Linux学习笔记', '2023-10-01 17:19:56', '2023-10-01 17:19:58', 1, 'Linux之父', 'https://niit-soft.oss-cn-hangzhou.aliyuncs.com/share-app/Linux.jpg', '一提到 Linux，许多人都会说到“自由”，但我不认为他们都知道“自由”的真正涵义。“自由”是一种权力， 它决定你的计算机能做什么，同时能够拥有这种“自由”的唯一方式就是知道计算机正在做什么。 “自由”是指一台没有任何秘密的计算机，你可以从它那里了解一切，只要你用心的去寻找。', 10, '链接: https://pan.baidu.com/s/1Hk9i9VOTz2eSuy8p-kFGrQ  密码: 5njn', 20, 1, 'PASS', '通过审核');
INSERT INTO `share` VALUES (11, 2, 'JavaScript学习笔记', '2023-10-01 17:19:56', '2023-10-01 17:19:58', 1, 'JS大神', 'https://niit-soft.oss-cn-hangzhou.aliyuncs.com/share-app/JavaScript.jpg', 'JavaScript ( JS ) 是一种具有函数优先的轻量级，解释型或即时编译型的编程语言。', 10, '链接: https://pan.baidu.com/s/1Hk9i9VOTz2eSuy8p-kFGrQ  密码: 5njn', 20, 1, 'PASS', '通过审核');
INSERT INTO `share` VALUES (12, 2, 'Java并发编程实战', '2023-09-29 16:14:21', '2023-09-29 16:14:24', 0, 'Java大神', 'https://coding.imooc.com/static/module/class/content/img/132/section2-1.png', '对于一个Java 程序员而言，能否熟练掌握并发编程是判断他优秀与否的重要标准之一。因为并发编程是Java 语言中最为晦涩的知识点，它涉及操作系统、内存、CPU、编程语言等多方面的基础能力，更为考验一个程序员的内功。', 10, '链接: https://pan.baidu.com/s/1Hk9i9VOTz2eSuy8p-kFGrQ  密码: 5njn', 20, 1, 'PASS', '通过审核');
INSERT INTO `share` VALUES (13, 1, '微服务技术', '2023-09-29 12:35:16', '2023-09-29 12:35:20', 1, '佚名', 'https://niit-soft.oss-cn-hangzhou.aliyuncs.com/share-app/Spring%20Cloud%20Alibaba.jpg', 'Spring 要理解微服务，首先要先理解不是微服务的那些。通常跟微服务相对的是单体应用，即将所有功能都打包成在一个独立单元的应用程序。从单体应用到微服务并不是一蹴而就的，这是一个逐渐演变的过程。', 20, '链接: https://pan.baidu.com/s/1Hk9i9VOTz2eSuy8p-kFGrQ  密码: 5njn', 30, 0, 'PASS', '通过审核');
INSERT INTO `share` VALUES (14, 2, '计算机基础', '2023-10-17 20:02:37', '2023-10-17 20:02:37', 1, 'mqxu', 'https://img2.sycdn.imooc.com/szimg/5b3082da0001d7e905400300-360-202.jpg', '计算机基础知识大全', 5, '链接: https://pan.baidu.com/s/1Hk9i9VOTz2eSuy8p-kFGrQ  密码: 5njn', 5, 0, 'NOT_YET', '');
INSERT INTO `share` VALUES (15, 2, '测试资源', '2023-10-17 20:42:13', '2023-10-17 20:42:13', 1, '陶然然', 'https://img3.sycdn.imooc.com/szimg/5b3082da0001d7e905400300-360-202.jpg', '测试资源', 10, '链接: https://pan.baidu.com/s/1Hk9i9VOTz2eSuy8p-kFGrQ  密码: 5njn', 0, 1, 'PASS', '通过审核');
INSERT INTO `share` VALUES (16, 2, 'HTTP协议', '2023-10-18 00:07:16', '2023-10-18 00:07:16', 0, 'mqxu', 'https://img2.sycdn.imooc.com/szimg/5d1032ab08719e0906000338-360-202.jpg', 'HTTP协议相关知识', 5, '链接: https://pan.baidu.com/s/1Hk9i9VOTz2eSuy8p-kFGrQ  密码: 5njn', 0, 0, 'PASS', '通过审核');
INSERT INTO `share` VALUES (17, 2, 'JavaScript中的算法', '2023-10-18 10:59:09', '2023-10-18 10:59:09', 0, 'Lewis', 'https://img4.sycdn.imooc.com/szimg/5edf24fd081fde7906000338-360-202.jpg', '用JS语言解决LeetCode上的经典算法题，对每一道题都进行线上测试，每题都有时间/空间复杂度分析。结合前端实际开发情景，带你掌握数据结构与算法。', 15, '链接: https://pan.baidu.com/s/1Hk9i9VOTz2eSuy8p-kFGrQ  密码: 5njn', 0, 0, 'NOT_YET', '');
INSERT INTO `share` VALUES (18, 2, 'Node.js调试入门', '2023-10-18 11:38:10', '2023-10-18 11:38:10', 1, 'Lewis', 'https://img3.sycdn.imooc.com/5c3eaa0a08d7052706000338-360-202.jpg', '学会高效调试 Node.js 会让日常开发更高效', 10, '链接: https://pan.baidu.com/s/1Hk9i9VOTz2eSuy8p-kFGrQ  密码: 5njn', 0, 0, 'PASS', '通过审核');
INSERT INTO `share` VALUES (19, 2, '软件测试进阶之路', '2023-10-18 11:39:58', '2023-10-18 11:39:58', 1, '风落几番', 'https://img3.sycdn.imooc.com/5c60f2e80984689c05400300-360-202.png', '新时代软件测试人员的职责、功能测试、性能测试等知识，让测试人员能更好的掌握学习路线。', 10, '链接: https://pan.baidu.com/s/1Hk9i9VOTz2eSuy8p-kFGrQ  密码: 5njn', 0, 0, 'NOT_YET', '');
INSERT INTO `share` VALUES (20, 2, 'Spring Cloud Alibaba', '2023-10-18 16:20:34', '2023-10-18 16:20:34', 0, '阿里巴巴', 'https://img2.sycdn.imooc.com/szimg/5b3082da0001d7e905400300-360-202.jpg', '微服务全家桶系列组件', 20, '链接: https://pan.baidu.com/s/1Hk9i9VOTz2eSuy8p-kFGrQ  密码: 5njn', 0, 0, 'PASS', '通过审核');
INSERT INTO `share` VALUES (21, 2, 'Docker 系统性入门+进阶实践', '2023-09-26 14:39:14', '2023-09-26 14:39:14', 1, 'ssy', 'https://img3.sycdn.imooc.com/szimg/60cc058408c34ece12000676-360-202.jpg', '近年来，容器技术在互联网行业大火，特别是在开发和运维方向，极大地解决了规模化和灵活化部署的问题。', 10, '链接: https://pan.baidu.com/s/1Hk9i9VOTz2eSuy8p-kFGrQ  密码: 5njn', 0, 0, 'PASS', '通过审核');
INSERT INTO `share` VALUES (22, 2, 'ChatGPT 辅助开发 Vue3 项目', '2023-09-26 14:48:04', '2023-09-26 14:48:04', 1, '双越', 'https://img3.sycdn.imooc.com/szimg/64b0cc4f09a30aa812000676-360-202.png', '带你使用 ChatGPT + Copilot 等 AI 工具，从 0 到 1 开发一个 Vue3 仿简书项目，体验以 3 倍速度完成项目的需求、设计、开发、优化和测试，全面碾压“人肉”开发。让你轻松掌握开发工作提效方法，快速提升职场竞争力。', 10, '链接: https://pan.baidu.com/s/1Hk9i9VOTz2eSuy8p-kFGrQ  密码: 5njn', 0, 1, 'PASS', '通过审核');
INSERT INTO `share` VALUES (1712714219752620033, 2, '详解Vue.js', '2023-10-13 14:17:45', '2023-10-13 14:17:45', 1, '尤雨溪', 'https://litao-oss.oss-cn-shanghai.aliyuncs.com/backiee-109636.jpg', '详情Vue.js详解Vue.js详解Vue.js详解Vue.js详解Vue.js详解Vue.js详解', 10, 'http://cn.vuejs.org', 0, 1, 'PASS', '通过审核');
INSERT INTO `share` VALUES (1712725117628121090, 1, 'JavaScript学习笔记', '2023-10-13 15:01:04', '2023-10-13 15:01:04', 1, '李涛', 'https://litao-oss.oss-cn-shanghai.aliyuncs.com/backiee-129378.jpg', '测试文章测试文章测试文章测试文章测试文章测试文章测试文章测试文章', 10, 'http://pan.baid', 0, 0, 'NOT_YET', '未审核');
INSERT INTO `share` VALUES (1712726546489081858, 1, 'Spring Cloud Alibaba', '2023-10-13 15:06:44', '2023-10-13 15:06:44', 1, '李涛', 'https://litao-oss.oss-cn-shanghai.aliyuncs.com/backiee-119149.jpg', '测试四测试四测试四测试四测试四测试四测试四', 20, 'http://pan.baid', 0, 0, 'NOT_YET', '未审核');
INSERT INTO `share` VALUES (1712726634108092417, 1, '计算机基础', '2023-10-13 15:07:05', '2023-10-13 15:07:05', 1, '李涛', 'https://litao-oss.oss-cn-shanghai.aliyuncs.com/backiee-109636.jpg', '测试三测试三测试三测试三测试三测试三测试三测试三测试三测试三测试三', 20, 'http://pan.baid', 0, 1, 'PASS', '通过审核');
INSERT INTO `share` VALUES (1712748486197026818, 1, '消息队列rabbitMQ', '2023-10-13 16:33:55', '2023-10-13 16:33:55', 1, '李涛', 'https://litao-oss.oss-cn-shanghai.aliyuncs.com/backiee-129378.jpg', '测试效果', 10, 'http：//www.baidu.com', 0, 0, 'NOT_YET', '未审核');
INSERT INTO `share` VALUES (1712749640112971777, 1, '微服务技术', '2023-10-13 16:38:30', '2023-10-13 16:38:30', 1, '尤雨溪2', 'https://litao-oss.oss-cn-shanghai.aliyuncs.com/backiee-129378.jpg', '详情Vue.js详解Vue.js详解Vue.js详解Vue.js详解Vue.js详解Vue.js详解', 10, 'http://cn.vuejs.org', 0, 1, 'REJECT', '');
INSERT INTO `share` VALUES (1712766110809235458, 1, 'Go语言从入门到达人之路', '2023-10-13 17:43:57', '2023-10-13 17:43:57', 1, '李涛', 'https://litao-oss.oss-cn-shanghai.aliyuncs.com/backiee-109636.jpg', '55555', 10, 'abc', 0, 1, 'PASS', '通过审核');
INSERT INTO `share` VALUES (1717175302639501313, 1, '鉴权测试', '2023-10-25 21:44:30', '2023-10-25 21:44:30', 0, '李涛', 'https://img3.sycdn.imooc.com/szimg/60cc058408c34ece12000676-360-202.jpg', '鉴权测试', 20, 'http://www.baidu.com', 0, 0, 'NOT_YET', '未审核');

SET FOREIGN_KEY_CHECKS = 1;
