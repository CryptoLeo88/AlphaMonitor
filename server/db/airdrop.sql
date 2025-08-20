/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : personal_info

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 18/07/2025 16:01:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for airdrop
-- ----------------------------
DROP TABLE IF EXISTS `airdrop`;
CREATE TABLE `airdrop`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '项目名称',
  `token_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '代币名称',
  `amount` bigint(255) NULL DEFAULT NULL COMMENT '分发数量',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `airdrop_time` datetime(0) NULL DEFAULT NULL COMMENT '空头时间',
  `score` int(255) NULL DEFAULT NULL COMMENT '积分要求',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '项目类型',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '地址',
  `chain` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所在链',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of airdrop
-- ----------------------------
INSERT INTO `airdrop` VALUES (1, 'Chainbase', 'C', 750, NULL, '2025-07-14 12:00:00', 160, '空投', '0xc32cc70741c3A8433dCbcB5adE071c299B55FfC8', 'bsc');
INSERT INTO `airdrop` VALUES (2, 'TAC', 'TAC', 1875, NULL, '2025-07-15 10:00:00', 224, '空投', '0x1219c409fabe2c27bd0d1a565daeed9bd9f271de', 'bsc');
INSERT INTO `airdrop` VALUES (3, 'ERA', 'ERA', 150, NULL, '2025-07-17 14:30:00', 223, '空投', '0x00312400303d02c323295f6E8b7309bc30FB6BcE', 'bsc');
INSERT INTO `airdrop` VALUES (4, 'Taker', 'Taker', 1000, NULL, '2025-07-18 10:00:00', 165, '空投', '0xc19539eb93444523ec8f1432624924d2e6226546', 'bsc');

-- ----------------------------
-- Table structure for education_info
-- ----------------------------
DROP TABLE IF EXISTS `education_info`;
CREATE TABLE `education_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `person_id` int(11) NOT NULL,
  `school_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `college_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `major_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `admission_time` date NOT NULL,
  `graduation_time` date NOT NULL,
  `degree_level` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `is_graduated` tinyint(1) NULL DEFAULT 0,
  `authentication_number` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `created_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `updated_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `authentication_number`(`authentication_number`) USING BTREE,
  INDEX `person_id`(`person_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of education_info
-- ----------------------------
INSERT INTO `education_info` VALUES (1, 32, '南京邮电大学', 'a学院', 'b专业', '2019-04-30', '2023-04-30', '学士', 0, '13131313131313', '2023-05-05 03:10:21', '2023-05-05 04:06:04');
INSERT INTO `education_info` VALUES (2, 33, 'xx大学', 'xx学院', 'xx\'专业', '2021-05-03', '2023-05-02', '硕士', 0, '421312312', '2023-05-05 04:15:15', '2023-05-05 04:19:43');
INSERT INTO `education_info` VALUES (4, 32, '南邮', '计算机', '计算机', '2023-05-14', '2023-05-25', '硕士', 0, '24234234', '2023-05-05 21:24:29', '2023-05-05 21:24:29');

-- ----------------------------
-- Table structure for family_members
-- ----------------------------
DROP TABLE IF EXISTS `family_members`;
CREATE TABLE `family_members`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `person_id` int(11) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `relationship` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `occupation` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `created_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `updated_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `person_id`(`person_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of family_members
-- ----------------------------
INSERT INTO `family_members` VALUES (1, 32, '张大', '父亲', 'C开发', '2023-05-05 02:54:50', '2023-05-05 04:06:01');
INSERT INTO `family_members` VALUES (2, 33, '李大', '祖父', '工人', '2023-05-05 04:14:19', '2023-05-05 04:19:32');
INSERT INTO `family_members` VALUES (3, 33, '李九', '儿子', '学生', '2023-05-05 04:17:54', '2023-05-05 04:19:38');
INSERT INTO `family_members` VALUES (4, 33, '胡土', '哥哥', '律师', '2023-05-05 04:19:17', '2023-05-05 04:19:17');
INSERT INTO `family_members` VALUES (5, 32, '张二', '哥哥', '律师', '2023-05-05 21:48:43', '2023-05-05 21:48:43');

-- ----------------------------
-- Table structure for files
-- ----------------------------
DROP TABLE IF EXISTS `files`;
CREATE TABLE `files`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `origin_name` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片初始名称',
  `picture_name` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片名称',
  `picture_path` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片路径',
  `date` datetime(0) NULL DEFAULT NULL COMMENT '图片上传日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 65 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文件' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of files
-- ----------------------------
INSERT INTO `files` VALUES (30, '2.png', '8f3bbb83-15bf-4298-a6a3-bd85d228c4f0.png', '/Brand/8f3bbb83-15bf-4298-a6a3-bd85d228c4f0.png', '2020-10-16 19:45:02');
INSERT INTO `files` VALUES (51, '16114457-张岩松.docx', '7f16bc51-6c4e-448c-bf7f-8e0b4587ee5d.docx', '/file/7f16bc51-6c4e-448c-bf7f-8e0b4587ee5d.docx', '2020-10-18 00:00:52');

-- ----------------------------
-- Table structure for id
-- ----------------------------
DROP TABLE IF EXISTS `id`;
CREATE TABLE `id`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `num` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of id
-- ----------------------------
INSERT INTO `id` VALUES (1, 6);

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '授权',
  `type` int(11) NULL DEFAULT NULL COMMENT '类型',
  `icon` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) NULL DEFAULT NULL COMMENT '排序',
  `component` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件',
  `path` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路径',
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of menu
-- ----------------------------

-- ----------------------------
-- Table structure for organization
-- ----------------------------
DROP TABLE IF EXISTS `organization`;
CREATE TABLE `organization`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机构名称',
  `simplify` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机构简称',
  `contact` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `detailed_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详细地址',
  `administrative_area` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所在行政区域',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电子邮件',
  `phone_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `organization_type_id` int(11) NULL DEFAULT NULL COMMENT '机构类型ID',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '机构表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of organization
-- ----------------------------
INSERT INTO `organization` VALUES (3, '化工厂1', '北京化工1厂', '夏新发', '北京', '北京工业园区', '2412640775@qq.com', '15942865321', 3, 1);
INSERT INTO `organization` VALUES (5, '易燃气股份有限公司', '易燃气公司', '郑中明', '北京工业园区', '北京工业园区', NULL, '18075482872', 4, 1);
INSERT INTO `organization` VALUES (12, '测试机构名称', '测试', '武翠云', '33是是是', '', '2222', '15655113090', 5, 2);

-- ----------------------------
-- Table structure for organization_product
-- ----------------------------
DROP TABLE IF EXISTS `organization_product`;
CREATE TABLE `organization_product`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `organization_id` int(11) NULL DEFAULT NULL COMMENT '机构ID',
  `product_id` int(11) NULL DEFAULT NULL COMMENT '产品ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '机构产品' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of organization_product
-- ----------------------------
INSERT INTO `organization_product` VALUES (1, 5, 1);

-- ----------------------------
-- Table structure for organization_type
-- ----------------------------
DROP TABLE IF EXISTS `organization_type`;
CREATE TABLE `organization_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机构类型名称',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '机构类型' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of organization_type
-- ----------------------------
INSERT INTO `organization_type` VALUES (1, '行业主管部门', NULL);
INSERT INTO `organization_type` VALUES (2, '危化品销售方', NULL);
INSERT INTO `organization_type` VALUES (3, '危化品生产商', NULL);
INSERT INTO `organization_type` VALUES (4, '危化品流通商', NULL);
INSERT INTO `organization_type` VALUES (5, '危化品监督方', NULL);

-- ----------------------------
-- Table structure for organization_type_role
-- ----------------------------
DROP TABLE IF EXISTS `organization_type_role`;
CREATE TABLE `organization_type_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `organization_type_id` int(11) NULL DEFAULT NULL COMMENT '机构类型ID',
  `role_id` int(11) NULL DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '机构类型角色' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of organization_type_role
-- ----------------------------

-- ----------------------------
-- Table structure for person_info
-- ----------------------------
DROP TABLE IF EXISTS `person_info`;
CREATE TABLE `person_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `gender` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `birthday` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `height` float NULL DEFAULT NULL,
  `weight` float NULL DEFAULT NULL,
  `nationality` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `created_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `updated_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of person_info
-- ----------------------------
INSERT INTO `person_info` VALUES (1, '张三', '男', '2006-05-10T16:00:00.000Z', 180, 90, '美国', '12323', '123123@qq.com', '北京市', '2023-05-04 21:38:54', '2023-05-04 21:38:54', 32);
INSERT INTO `person_info` VALUES (2, '李四', '男', '2023-05-15T16:00:00.000Z', 177, 77, '中国', '2432432423', '4124324@QQ.COM', '南京市', '2023-05-05 03:44:28', '2023-05-05 03:44:28', 33);
INSERT INTO `person_info` VALUES (3, '王五', '女', '2023-05-07T16:00:00.000Z', 188, 88, '美国', '11434242423', '1421412@qq.com', '洛杉矶', '2023-05-05 21:58:40', '2023-05-05 21:58:40', 34);
INSERT INTO `person_info` VALUES (7, '李四', '男', '2023-06-03T16:00:00.000Z', 173, 85, '中国', '1234567890', '123@qq.com', '北京朝阳', '2023-06-08 12:11:16', '2023-06-08 12:11:16', 32);

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `product_type` int(11) NULL DEFAULT NULL,
  `dangerous_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `other` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, '甲烷', 1, '非常危险', NULL);
INSERT INTO `product` VALUES (2, '氢气', 2, '一般危险', NULL);
INSERT INTO `product` VALUES (3, '乙醛', 8, '', '123');

-- ----------------------------
-- Table structure for product_type
-- ----------------------------
DROP TABLE IF EXISTS `product_type`;
CREATE TABLE `product_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_type_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of product_type
-- ----------------------------
INSERT INTO `product_type` VALUES (1, '气体', NULL);
INSERT INTO `product_type` VALUES (2, '液体', NULL);
INSERT INTO `product_type` VALUES (8, '固体', '1');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `organization_id` int(11) NULL DEFAULT NULL COMMENT '所属机构ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'superadmin', '超级管理员', '2020-09-21 02:49:35', 1);
INSERT INTO `role` VALUES (2, 'admin', '管理员', '2020-09-21 02:49:43', 1);
INSERT INTO `role` VALUES (3, 'government', '政府', '2020-08-29 12:38:48', 0);
INSERT INTO `role` VALUES (4, 'user', '一般用户', '2023-05-04 18:24:59', NULL);

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `role_id` int(11) NULL DEFAULT NULL COMMENT '角色ID',
  `menu_id` int(11) NULL DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色菜单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES (6, 1, 1);
INSERT INTO `role_menu` VALUES (7, 1, 2);

-- ----------------------------
-- Table structure for up_block
-- ----------------------------
DROP TABLE IF EXISTS `up_block`;
CREATE TABLE `up_block`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hashcode` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `real_id` int(10) UNSIGNED NULL DEFAULT NULL,
  `up_Block_info` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of up_block
-- ----------------------------
INSERT INTO `up_block` VALUES (5, NULL, 4, '{\"transactionHash\":\"0xee162e7c399cf2021f1058895403343a14437cc2db3935ee3f772b7f47094325\",\"transactionIndex\":0,\"blockNumber\":197,\"blockHash\":\"0xe96ed8cec2ef3e03506685b65f7b62c5d0f7d3c7b3d6c1d5731153caef9cdf2d\",\"from\":\"0x5bb89dd7deae5f9f6ac9583ce6580db4cdfb3fc0\",\"to\":\"0x24035020401cafbf93a401ff3b6a09e347d588ef\",\"cumulativeGasUsed\":329822,\"gasUsed\":329822,\"contractAddress\":null,\"logsBloom\":\"0x00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000\",\"status\":true,\"effectiveGasPrice\":2500000008,\"type\":\"0x2\",\"events\":{}}', NULL, NULL);
INSERT INTO `up_block` VALUES (6, NULL, 5, '{\"transactionHash\":\"0x1c28b41a41a012406b141ee02f923a89d83d6a90c330640d1c68ab6c10fe254f\",\"transactionIndex\":0,\"blockNumber\":198,\"blockHash\":\"0x8cf66e16f7877f1619ff5b2f25e526129ce114d957475a9f2c3ec82c56c0aee0\",\"from\":\"0x5bb89dd7deae5f9f6ac9583ce6580db4cdfb3fc0\",\"to\":\"0x24035020401cafbf93a401ff3b6a09e347d588ef\",\"cumulativeGasUsed\":330206,\"gasUsed\":330206,\"contractAddress\":null,\"logsBloom\":\"0x00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000\",\"status\":true,\"effectiveGasPrice\":2500000008,\"type\":\"0x2\",\"events\":{}}', NULL, NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `mobile` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话号码',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `organization_id` int(11) NULL DEFAULT NULL COMMENT '所属机构',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `salt` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '盐',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '87a9d9452632a1708d59ca770ea885dfd55533758048778733196ee1f31ac5c2', '18075482872', '1169313164@qq.com', 12, NULL, '2023-04-20 13:26:17', 'sBNU18Vr7B95t3ez1Vyt');
INSERT INTO `user` VALUES (23, 'qiye1', '87a9d9452632a1708d59ca770ea885dfd55533758048778733196ee1f31ac5c2', '15956932476', 'zhao@bjut.edu.cn', 5, NULL, '2023-04-21 23:10:37', 'sBNU18Vr7B95t3ez1Vyt');
INSERT INTO `user` VALUES (28, 'enterprise', 'c813c805c1459d2c489be125be0c7c25c841ae1e96bfa9a4a4ef0128763244dd', '15533334444', '', 3, NULL, '2020-11-08 10:39:20', '0WMuGF4tUmHx1QuZVSAF');
INSERT INTO `user` VALUES (29, 'product', '1cfc325e8c0a111d3309a6c1bb2528a64a859e6b5e1712503ce8e19fb230c29c', '123456', '123@qqc.om', NULL, NULL, '2023-04-25 13:32:55', 'wbDByFfIB33JKACTMDbU');
INSERT INTO `user` VALUES (32, 'abc', 'cff38ad387206568698dfa67fbcd002a7009c7dcd4bbe6243aeda183936e8eba', '13412342435', '1234@qq.com', NULL, NULL, '2023-05-04 18:20:18', 'Ye0vc1c6kchgPeBVW7zU');
INSERT INTO `user` VALUES (33, 'qwer', '5eb4604374a2e80c649020fed87009796fc096d11ca49993c8e89881d462a0cd', '13413455432', 'qwer@qq.com', NULL, NULL, '2023-05-05 03:27:09', 'It0VgPdbatY2VuvenlHI');
INSERT INTO `user` VALUES (34, 'user1', 'b579ca244bd11c675a35602d1d31977726194ff65aad570b0b8aff2db3f8338d', '13456434245', '123123113@qq.com', NULL, NULL, '2023-05-05 21:57:54', '3VdwFJL2vm0FC2ahwp3m');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `role_id` int(11) NULL DEFAULT NULL COMMENT '角色ID',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 2, 1);
INSERT INTO `user_role` VALUES (2, 3, 1);
INSERT INTO `user_role` VALUES (3, 1, 1);
INSERT INTO `user_role` VALUES (32, 4, 32);
INSERT INTO `user_role` VALUES (33, 4, 33);
INSERT INTO `user_role` VALUES (34, 4, 34);

-- ----------------------------
-- Table structure for work_experience
-- ----------------------------
DROP TABLE IF EXISTS `work_experience`;
CREATE TABLE `work_experience`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `person_id` int(11) NOT NULL,
  `company_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `position_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `entry_time` date NOT NULL,
  `departure_time` date NULL DEFAULT NULL,
  `work_desc` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `created_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `updated_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `person_id`(`person_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of work_experience
-- ----------------------------
INSERT INTO `work_experience` VALUES (1, 32, 'a公司', 'Java开发', '2019-04-30', '2023-05-01', '312312', '2023-05-05 02:24:15', '2023-05-05 04:06:11');
INSERT INTO `work_experience` VALUES (2, 33, 'x公司', 'C开发', '2023-05-08', '2023-05-02', '432423', '2023-05-05 04:20:23', '2023-05-05 04:20:23');
INSERT INTO `work_experience` VALUES (3, 32, '阿里巴巴', 'Java架构师', '2020-05-05', '2023-05-16', 'Java架构', '2023-05-05 21:53:41', '2023-05-05 21:53:41');

SET FOREIGN_KEY_CHECKS = 1;
