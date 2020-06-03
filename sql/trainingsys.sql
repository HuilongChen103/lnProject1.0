/*
 Navicat Premium Data Transfer

 Source Server         : database
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost:3306
 Source Schema         : trainingsys

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 03/06/2020 17:25:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_assessment
-- ----------------------------
DROP TABLE IF EXISTS `t_assessment`;
CREATE TABLE `t_assessment`  (
  `assess_serial` int(11) NOT NULL AUTO_INCREMENT COMMENT '测评流水号',
  `target_id` int(11) NOT NULL COMMENT '被测者id',
  `assessor_id` int(11) NULL DEFAULT NULL COMMENT '测评者id',
  `semester` enum('春季','夏季','秋季','冬季') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学期',
  `year` int(255) NULL DEFAULT NULL COMMENT '学年',
  `event_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '事件编号(班级，招聘，活动等)',
  `grade` int(255) NULL DEFAULT NULL COMMENT '分数',
  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评价',
  PRIMARY KEY (`assess_serial`) USING BTREE,
  INDEX `assess_assessor`(`assessor_id`) USING BTREE,
  INDEX `assess_target`(`target_id`) USING BTREE,
  INDEX `assess_recruit`(`event_code`) USING BTREE,
  CONSTRAINT `assess_assessor` FOREIGN KEY (`assessor_id`) REFERENCES `t_user` (`uid`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `assess_class` FOREIGN KEY (`event_code`) REFERENCES `t_class` (`class_code`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `assess_recruit` FOREIGN KEY (`event_code`) REFERENCES `t_recruit` (`recruit_code`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `assess_target` FOREIGN KEY (`target_id`) REFERENCES `t_user` (`uid`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_audit
-- ----------------------------
DROP TABLE IF EXISTS `t_audit`;
CREATE TABLE `t_audit`  (
  `audit_serial` int(11) NOT NULL AUTO_INCREMENT COMMENT '审核编号',
  `auditor_id` int(11) NULL DEFAULT NULL COMMENT '审核人id',
  `applicant_id` int(11) NULL DEFAULT NULL COMMENT '申请人id',
  `event_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '事件编号(招聘，物资调用，退课...)',
  `event` enum('学生请假','职工请假','调休','物资调用','转岗','退课','辞职','物资采购') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '事件类型',
  `apply_date` timestamp(0) NULL DEFAULT NULL COMMENT '申请时间',
  `audit_date` timestamp(0) NULL DEFAULT NULL COMMENT '审核时间',
  `state` enum('通过','未通过','待审核') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '待审核' COMMENT '审核状态(通过，未通过...)',
  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`audit_serial`) USING BTREE,
  INDEX `audit_auditor`(`auditor_id`) USING BTREE,
  INDEX `audit_applicant`(`applicant_id`) USING BTREE,
  INDEX `audit_event_goods`(`event_code`) USING BTREE,
  CONSTRAINT `audit_applicant` FOREIGN KEY (`applicant_id`) REFERENCES `t_user` (`uid`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `audit_auditor` FOREIGN KEY (`auditor_id`) REFERENCES `t_user` (`uid`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 31467804 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_audit
-- ----------------------------
INSERT INTO `t_audit` VALUES (31467800, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_audit` VALUES (31467803, NULL, 116193704, 'ZHE10120200301', '学生请假', '2020-06-03 10:49:52', NULL, '待审核', '身体不适，下周三的课程需要请假。');

-- ----------------------------
-- Table structure for t_benefitevaluation
-- ----------------------------
DROP TABLE IF EXISTS `t_benefitevaluation`;
CREATE TABLE `t_benefitevaluation`  (
  `benefit_serial` int(11) NOT NULL AUTO_INCREMENT COMMENT '绩效考评流水号',
  `stuff_id` int(11) NULL DEFAULT NULL COMMENT '员工id（职员、教师）',
  `year` int(11) NULL DEFAULT NULL COMMENT '年份',
  `month` int(11) NULL DEFAULT NULL COMMENT '月份',
  `benefit` int(11) NULL DEFAULT NULL COMMENT '效益（具体金额）',
  `assessment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '考评（文字说明）',
  PRIMARY KEY (`benefit_serial`) USING BTREE,
  INDEX `benefit_stuff`(`stuff_id`) USING BTREE,
  CONSTRAINT `benefit_stuff` FOREIGN KEY (`stuff_id`) REFERENCES `t_user` (`uid`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_class
-- ----------------------------
DROP TABLE IF EXISTS `t_class`;
CREATE TABLE `t_class`  (
  `class_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '班级号',
  `course_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程号',
  `course_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程名',
  `student_max` int(11) NULL DEFAULT NULL COMMENT '最大学生数量',
  `student_num` int(11) NULL DEFAULT NULL COMMENT '起始学生数量(开学的时候)',
  `real_num` int(11) NULL DEFAULT NULL COMMENT '实际学生数量',
  `teacher_id` int(11) NULL DEFAULT NULL COMMENT '教师id',
  `schedule_serial` int(11) NULL DEFAULT NULL COMMENT '日程安排编号',
  `enable` int(11) NULL DEFAULT 1 COMMENT '是否有效',
  `room_num` int(11) NULL DEFAULT NULL COMMENT '房间号',
  PRIMARY KEY (`class_code`) USING BTREE,
  INDEX `class_course`(`course_code`) USING BTREE,
  INDEX `class_teacher`(`teacher_id`) USING BTREE,
  INDEX `class_schedule`(`schedule_serial`) USING BTREE,
  INDEX `class_room`(`room_num`) USING BTREE,
  CONSTRAINT `class_course` FOREIGN KEY (`course_code`) REFERENCES `t_course` (`course_code`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `class_schedule` FOREIGN KEY (`schedule_serial`) REFERENCES `t_schedule` (`schedule_serial`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `class_teacher` FOREIGN KEY (`teacher_id`) REFERENCES `t_user` (`uid`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `class_room` FOREIGN KEY (`room_num`) REFERENCES `t_room` (`room_num`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_class
-- ----------------------------
INSERT INTO `t_class` VALUES ('BEL10120200301', 'BEL101', '美声入门', 20, 2, 2, 116193701, 691874000, 1, 102);
INSERT INTO `t_class` VALUES ('BEL10220200301', 'BEL102', '美声入门', 10, 0, 0, 116193701, 691874001, 1, 102);
INSERT INTO `t_class` VALUES ('ZHE10120200301', 'ZHE101', '古筝初级', 20, 1, 1, 116193714, 691874002, 1, 101);
INSERT INTO `t_class` VALUES ('ZHE10220200301', 'ZHE102', '古筝初级', 10, 0, 0, 116193714, 691874003, 1, 101);

-- ----------------------------
-- Table structure for t_course
-- ----------------------------
DROP TABLE IF EXISTS `t_course`;
CREATE TABLE `t_course`  (
  `course_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程编号',
  `course_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程名称',
  `duration` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程时长',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型',
  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `fee` decimal(10, 0) NULL DEFAULT NULL COMMENT '课程费用（/小时）',
  PRIMARY KEY (`course_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_course
-- ----------------------------
INSERT INTO `t_course` VALUES ('ARR101', '编曲初级', '1小时', '声乐', '教授编曲初级知识技巧，适合新手。', 30);
INSERT INTO `t_course` VALUES ('ARR201', '编曲中级', '1.5小时', '声乐', '教授编曲中级知识技巧，适合有所学习的学员。', 40);
INSERT INTO `t_course` VALUES ('ARR301', '编曲高级', '2小时', '声乐', '教授编曲高级知识技巧，适合熟悉编曲的学员。', 50);
INSERT INTO `t_course` VALUES ('ARR401', '编曲业余', '2小时', '声乐', '教授编曲业余知识', 60);
INSERT INTO `t_course` VALUES ('BEL101', '美声入门', '1小时', '声乐', '教授美声基本发声技巧和知识，适合美声入门者。', 30);
INSERT INTO `t_course` VALUES ('BEL102', '美声入门', '1小时', '声乐', '教授美声基本发声技巧和知识，适合美声入门者。(小班教学)', 45);
INSERT INTO `t_course` VALUES ('BEL201', '美声中级', '1.5小时', '声乐', '教授美声进阶发声技巧和知识，适合有一定基础的学员。', 35);
INSERT INTO `t_course` VALUES ('BEL202', '美声中级', '1.5小时', '声乐', '教授美声进阶发声技巧和知识，适合有一定基础的学员。（小班教学）', 45);
INSERT INTO `t_course` VALUES ('BEL301', '美声高级', '2小时', '声乐', '教授美声专业发声技巧和知识，适合专业学员。', 40);
INSERT INTO `t_course` VALUES ('BEL401', '美声业余', '2小时', '声乐', '教授美声专业发声技巧和知识，适合专业学员。', 50);
INSERT INTO `t_course` VALUES ('ENK101', '演歌初级', '1小时', '声乐', '教授演歌初级知识技巧，适合新手。', 30);
INSERT INTO `t_course` VALUES ('PIA101', '钢琴初级', '1小时', '乐器', '教授钢琴1-4级技巧及曲目，适合钢琴新手。', 30);
INSERT INTO `t_course` VALUES ('PIA102', '钢琴初级', '1小时', '乐器', '教授钢琴1-4级技巧及曲目，适合钢琴新手。（小班教学）', 45);
INSERT INTO `t_course` VALUES ('PIA201', '钢琴中级', '1.5小时', '乐器', '教授钢琴5-7级技巧及曲目，适合获得钢琴4级证书学员。', 45);
INSERT INTO `t_course` VALUES ('PIA202', '钢琴中级', '1.5小时', '乐器', '教授钢琴5-7级技巧及曲目，适合钢琴新手。（小班教学）', 60);
INSERT INTO `t_course` VALUES ('PIA301', '钢琴高级', '2小时', '乐器', '教授钢琴8-10级技巧及曲目，适合获得钢琴7级证书学员。', 60);
INSERT INTO `t_course` VALUES ('PIA401', '钢琴业余', '2小时', '乐器', '教授钢琴业余技巧及曲目，适合获得钢琴10级证书学员。', 75);
INSERT INTO `t_course` VALUES ('VOC101', '演唱基础', '1小时', '声乐', '教授基本发声技巧，乐理常识，适合声乐初学者。', 20);
INSERT INTO `t_course` VALUES ('VOC102', '演唱基础', '1小时', '声乐', '教授基本发声技巧，乐理常识，适合声乐初学者。(小班教学)', 40);
INSERT INTO `t_course` VALUES ('VOC201', '乐理入门', '1小时', '声乐', '教授乐理知识，适合乐理初学者。', 20);
INSERT INTO `t_course` VALUES ('VOC202', '乐理入门', '1小时', '声乐', '教授乐理知识，适合乐理初学者。（小班教学）', 30);
INSERT INTO `t_course` VALUES ('VOL101', '小提琴初级', '1小时', '乐器', '教授小提琴1-4级技巧及曲目，适合小提琴新手。', 20);
INSERT INTO `t_course` VALUES ('VOL102', '小提琴初级', '1小时', '乐器', '教授小提琴1-4级技巧及曲目，适合小提琴新手。（小班教学）', 30);
INSERT INTO `t_course` VALUES ('VOL201', '小提琴中级', '1.5小时', '乐器', '教授小提琴5-7级技巧及曲目，适合获得小提琴4级证书学员。', 30);
INSERT INTO `t_course` VALUES ('VOL301', '小提琴高级', '2小时', '乐器', '教授小提琴8-10级技巧及曲目，适合获得小提琴7级证书学员。', 40);
INSERT INTO `t_course` VALUES ('ZHE101', '古筝初级', '1小时', '乐器', '教授古筝1-4级技巧及曲目，适合古筝新手。', 20);
INSERT INTO `t_course` VALUES ('ZHE102', '古筝初级', '1小时', '乐器', '教授古筝1-4级技巧及曲目，适合古筝新手。(小班教学)', 40);
INSERT INTO `t_course` VALUES ('ZHE201', '古筝中级', '1.5小时', '乐器', '教授古筝5-7级技巧及曲目，适合获得古筝4级证书学员。', 30);
INSERT INTO `t_course` VALUES ('ZHE202', '古筝中级', '1.5小时', '乐器', '教授古筝5-7级技巧及曲目，适合获得古筝4级证书学员。（小班教学）', 45);
INSERT INTO `t_course` VALUES ('ZHE301', '古筝高级', '2小时', '乐器', '教授古筝8-10级曲目及内容，适合获得古筝七级证书学员', 40);
INSERT INTO `t_course` VALUES ('ZHE401', '古筝业余', '1.5小时', '乐器', '教授古筝业余技巧及曲目，难度较大，适合获得古筝10级证书学员。', 50);

-- ----------------------------
-- Table structure for t_courseware
-- ----------------------------
DROP TABLE IF EXISTS `t_courseware`;
CREATE TABLE `t_courseware`  (
  `courseware_serial` int(255) NOT NULL AUTO_INCREMENT COMMENT '课件流水号',
  `class_code` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '班级号',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `file_serial` int(255) NULL DEFAULT NULL COMMENT '文件序号',
  PRIMARY KEY (`courseware_serial`) USING BTREE,
  INDEX `courseware_file`(`file_serial`) USING BTREE,
  INDEX `courseware_class`(`class_code`) USING BTREE,
  CONSTRAINT `courseware_class` FOREIGN KEY (`class_code`) REFERENCES `t_class` (`class_code`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `courseware_file` FOREIGN KEY (`file_serial`) REFERENCES `t_file` (`file_serial`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_department
-- ----------------------------
DROP TABLE IF EXISTS `t_department`;
CREATE TABLE `t_department`  (
  `department_id` int(11) NOT NULL COMMENT '部门id',
  `department_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门名称',
  PRIMARY KEY (`department_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_department
-- ----------------------------
INSERT INTO `t_department` VALUES (45000, '销售-宣传');
INSERT INTO `t_department` VALUES (45001, '销售-招生');
INSERT INTO `t_department` VALUES (46000, '人事-招聘');
INSERT INTO `t_department` VALUES (47000, '教育-声乐');
INSERT INTO `t_department` VALUES (47001, '教育-乐器');
INSERT INTO `t_department` VALUES (48000, '管理');

-- ----------------------------
-- Table structure for t_file
-- ----------------------------
DROP TABLE IF EXISTS `t_file`;
CREATE TABLE `t_file`  (
  `file_serial` int(11) NOT NULL AUTO_INCREMENT COMMENT '文件流水号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件名',
  `displayname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件展示名',
  `extension` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件扩展名',
  `contenttype` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件种类',
  `file_data` varbinary(255) NULL DEFAULT NULL COMMENT '文件二进制格式',
  `file_size` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件大小',
  `upload_time` timestamp(0) NULL DEFAULT NULL COMMENT '上传时间',
  `uploader_id` int(11) NULL DEFAULT NULL COMMENT '上传人id',
  PRIMARY KEY (`file_serial`) USING BTREE,
  INDEX `file_uploader`(`uploader_id`) USING BTREE,
  CONSTRAINT `file_uploader` FOREIGN KEY (`uploader_id`) REFERENCES `t_user` (`uid`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_finance
-- ----------------------------
DROP TABLE IF EXISTS `t_finance`;
CREATE TABLE `t_finance`  (
  `finance_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '开支流水号',
  `in_out` enum('EXP','REV') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'EXP:支出 REV支出',
  `PIC_id` int(255) NOT NULL COMMENT '负责人',
  `pay_account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '付款账户',
  `receive_account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收款账户',
  `trade_method` enum('支付宝','微信','银行转账') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易方式',
  `amount` int(255) NULL DEFAULT NULL COMMENT '数额',
  `date` timestamp(0) NULL DEFAULT NULL COMMENT '日期时间',
  `category` enum('员工薪水','器材采购','水电租金','广告宣传','学员缴费') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类目',
  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`finance_code`) USING BTREE,
  INDEX `exoense_PIC`(`PIC_id`) USING BTREE,
  CONSTRAINT `exoense_PIC` FOREIGN KEY (`PIC_id`) REFERENCES `t_user` (`uid`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_finance
-- ----------------------------
INSERT INTO `t_finance` VALUES ('REV202005252353', 'REV', 116193706, '12637328210', '1010101010101', '银行转账', 360, '2020-05-25 23:54:49', '学员缴费', NULL);
INSERT INTO `t_finance` VALUES ('REV202005271621', 'REV', 116193704, '182930472523', '1010101010101', '支付宝', 240, NULL, '学员缴费', NULL);
INSERT INTO `t_finance` VALUES ('REV202005271624', 'REV', 116193704, '2124782394', '1010101010101', '支付宝', 240, NULL, '学员缴费', NULL);

-- ----------------------------
-- Table structure for t_goods
-- ----------------------------
DROP TABLE IF EXISTS `t_goods`;
CREATE TABLE `t_goods`  (
  `goods_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '物资编号',
  `PIC_id` int(11) NULL DEFAULT NULL COMMENT '采办人id(person in charge)',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '物资名称',
  `catagory` enum('乐器','家具','电器','书籍') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类目',
  `stock_in_date` datetime(0) NULL DEFAULT NULL COMMENT '入库时间',
  `stock_out_date` datetime(0) NULL DEFAULT NULL COMMENT '出库时间',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `room_num` int(11) NULL DEFAULT NULL COMMENT '现在的地点（房间号）',
  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`goods_code`) USING BTREE,
  INDEX `goods_PIC`(`PIC_id`) USING BTREE,
  INDEX `goods_location`(`room_num`) USING BTREE,
  CONSTRAINT `goods_PIC` FOREIGN KEY (`PIC_id`) REFERENCES `t_user` (`uid`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `goods_location` FOREIGN KEY (`room_num`) REFERENCES `t_room` (`room_num`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_goods
-- ----------------------------
INSERT INTO `t_goods` VALUES ('CHA0001', 116193705, '椅子', '家具', '2015-07-15 15:38:49', NULL, 200.00, 101, '');
INSERT INTO `t_goods` VALUES ('CHA0002', 116193705, '椅子', '家具', '2015-07-15 15:38:49', NULL, 200.00, 101, NULL);
INSERT INTO `t_goods` VALUES ('CHA0003', 116193705, '椅子', '家具', '2015-07-15 15:38:49', NULL, 200.00, 101, NULL);
INSERT INTO `t_goods` VALUES ('CHA0004', 116193705, '椅子', '家具', '2015-07-15 15:38:49', NULL, 200.00, 101, NULL);
INSERT INTO `t_goods` VALUES ('CHA0005', 116193705, '椅子', '家具', '2015-07-15 15:38:49', NULL, 200.00, 101, NULL);
INSERT INTO `t_goods` VALUES ('CHA0006', 116193705, '椅子', '家具', '2015-07-15 15:38:49', NULL, 200.00, 101, NULL);
INSERT INTO `t_goods` VALUES ('CHA0007', 116193705, '椅子', '家具', '2015-07-15 15:38:49', NULL, 200.00, 101, NULL);
INSERT INTO `t_goods` VALUES ('CHA0008', 116193705, '椅子', '家具', '2015-07-15 15:38:49', NULL, 200.00, 101, NULL);
INSERT INTO `t_goods` VALUES ('CHA0009', 116193705, '椅子', '家具', '2015-07-15 15:38:49', NULL, 200.00, 101, NULL);
INSERT INTO `t_goods` VALUES ('CHA0010', 116193705, '椅子', '家具', '2015-07-15 15:38:49', NULL, 200.00, 101, NULL);
INSERT INTO `t_goods` VALUES ('CHA0011', 116193705, '椅子', '家具', '2015-07-15 15:38:49', NULL, 200.00, 101, NULL);
INSERT INTO `t_goods` VALUES ('PRO0001', 116193715, '投影仪', '电器', '2017-06-02 22:26:29', NULL, 700.00, 101, NULL);
INSERT INTO `t_goods` VALUES ('PRO0002', 116193715, '投影仪', '电器', '2017-06-02 22:26:29', NULL, 700.00, 102, NULL);
INSERT INTO `t_goods` VALUES ('PRO0003', 116193715, '投影仪', '电器', '2017-06-02 22:26:29', NULL, 700.00, 103, NULL);
INSERT INTO `t_goods` VALUES ('PRO0004', 116193715, '投影仪', '电器', '2017-06-02 22:26:29', NULL, 700.00, 107, NULL);
INSERT INTO `t_goods` VALUES ('PRO0005', 116193715, '投影仪', '电器', '2017-06-02 22:26:29', NULL, 700.00, 201, NULL);
INSERT INTO `t_goods` VALUES ('PRO0006', 116193715, '投影仪', '电器', '2017-06-02 22:26:29', NULL, 700.00, 202, NULL);
INSERT INTO `t_goods` VALUES ('PRO0007', 116193715, '投影仪', '电器', '2017-06-02 22:26:29', NULL, 700.00, 203, NULL);
INSERT INTO `t_goods` VALUES ('PRO0008', 116193715, '投影仪', '电器', '2017-06-02 22:26:29', NULL, 700.00, 207, NULL);
INSERT INTO `t_goods` VALUES ('VOL0001', 116193703, '小提琴', '乐器', '2015-07-14 10:23:06', NULL, 1100.00, 103, NULL);
INSERT INTO `t_goods` VALUES ('VOL0002', 116193703, '小提琴', '乐器', '2015-07-14 10:23:06', NULL, 1100.00, 103, NULL);
INSERT INTO `t_goods` VALUES ('VOL0003', 116193703, '小提琴', '乐器', '2015-07-14 10:23:06', NULL, 1100.00, 103, NULL);
INSERT INTO `t_goods` VALUES ('VOL0004', 116193703, '小提琴', '乐器', '2015-07-14 10:23:06', NULL, 1100.00, 103, NULL);
INSERT INTO `t_goods` VALUES ('VOL0005', 116193703, '小提琴', '乐器', '2015-07-14 10:23:06', NULL, 1100.00, 103, NULL);
INSERT INTO `t_goods` VALUES ('VOL0006', 116193703, '小提琴', '乐器', '2015-07-14 10:23:06', NULL, 1100.00, 103, NULL);
INSERT INTO `t_goods` VALUES ('VOL0007', 116193703, '小提琴', '乐器', '2015-07-14 10:23:06', NULL, 1100.00, 103, NULL);
INSERT INTO `t_goods` VALUES ('VOL0008', 116193703, '小提琴', '乐器', '2015-07-14 10:23:06', NULL, 1100.00, 103, NULL);
INSERT INTO `t_goods` VALUES ('VOL0009', 116193703, '小提琴', '乐器', '2015-07-14 10:23:06', NULL, 1100.00, 103, NULL);
INSERT INTO `t_goods` VALUES ('VOL0010', 116193703, '小提琴', '乐器', '2015-07-14 10:23:06', NULL, 1100.00, 103, NULL);
INSERT INTO `t_goods` VALUES ('VOL0011', 116193703, '小提琴', '乐器', '2015-07-14 10:23:06', NULL, 1100.00, 103, NULL);
INSERT INTO `t_goods` VALUES ('WAT0001', 116193702, '饮水器', '电器', '2015-07-15 09:29:53', NULL, 500.00, 104, NULL);
INSERT INTO `t_goods` VALUES ('WAT0002', 116193702, '饮水器', '电器', '2015-07-15 09:29:53', NULL, 500.00, 204, NULL);
INSERT INTO `t_goods` VALUES ('WAT0003', 116193702, '饮水器', '电器', '2015-07-15 09:29:53', NULL, 500.00, 304, NULL);
INSERT INTO `t_goods` VALUES ('WAT0004', 116193702, '饮水器', '电器', '2015-07-15 09:29:53', NULL, 500.00, 404, NULL);
INSERT INTO `t_goods` VALUES ('WAT0005', 116193702, '饮水器', '电器', '2015-07-15 09:29:53', NULL, 500.00, 505, NULL);
INSERT INTO `t_goods` VALUES ('WAT0006', 116193702, '饮水器', '电器', '2015-07-15 09:29:53', NULL, 500.00, 505, NULL);
INSERT INTO `t_goods` VALUES ('WAT0007', 116193702, '饮水器', '电器', '2015-07-15 09:29:53', NULL, 500.00, 505, NULL);
INSERT INTO `t_goods` VALUES ('WAT0008', 116193702, '饮水器', '电器', '2015-07-15 09:29:53', NULL, 500.00, 505, NULL);
INSERT INTO `t_goods` VALUES ('ZHE0001', 116193703, '古筝', '乐器', '2015-07-15 15:29:44', NULL, 1200.00, 101, NULL);
INSERT INTO `t_goods` VALUES ('ZHE0002', 116193703, '古筝', '乐器', '2015-07-15 15:35:00', NULL, 1200.00, 101, NULL);
INSERT INTO `t_goods` VALUES ('ZHE0003', 116193703, '古筝', '乐器', '2015-07-15 15:38:41', NULL, 1200.00, 101, NULL);
INSERT INTO `t_goods` VALUES ('ZHE0004', 116193703, '古筝', '乐器', '2015-07-15 15:39:29', NULL, 1200.00, 101, NULL);
INSERT INTO `t_goods` VALUES ('ZHE0005', 116193703, '古筝', '乐器', '2015-07-15 15:45:09', NULL, 1200.00, 101, NULL);
INSERT INTO `t_goods` VALUES ('ZHE0006', 116193703, '古筝', '乐器', '2015-07-15 15:48:30', NULL, 1200.00, 101, NULL);
INSERT INTO `t_goods` VALUES ('ZHE0007', 116193703, '古筝', '乐器', '2015-07-15 15:50:39', NULL, 1200.00, 101, NULL);
INSERT INTO `t_goods` VALUES ('ZHE0008', 116193703, '古筝', '乐器', '2015-07-15 15:51:09', NULL, 1200.00, 101, NULL);
INSERT INTO `t_goods` VALUES ('ZHE0009', 116193703, '古筝', '乐器', '2015-07-15 15:52:48', NULL, 1200.00, 101, NULL);
INSERT INTO `t_goods` VALUES ('ZHE0010', 116193703, '古筝', '乐器', '2015-07-15 15:53:49', NULL, 1200.00, 101, NULL);
INSERT INTO `t_goods` VALUES ('ZHE0011', 116193703, '古筝', '乐器', '2015-07-15 15:54:43', NULL, 1200.00, 101, NULL);

-- ----------------------------
-- Table structure for t_goodsusage
-- ----------------------------
DROP TABLE IF EXISTS `t_goodsusage`;
CREATE TABLE `t_goodsusage`  (
  `usage_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '使用编号',
  `goods_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '器材编号',
  `rentor_id` int(11) NULL DEFAULT NULL COMMENT '租借者id（若为空，则是官方分配，比如教室的桌椅）',
  `PIC_id` int(11) NULL DEFAULT NULL COMMENT '负责人id（person in charge）',
  `rent_date` datetime(0) NULL DEFAULT NULL COMMENT '租借时间',
  `return_date` datetime(0) NULL DEFAULT NULL COMMENT '归还时间',
  PRIMARY KEY (`usage_code`) USING BTREE,
  INDEX `goodsusage_PIC`(`PIC_id`) USING BTREE,
  INDEX `goodsusage_device`(`goods_code`) USING BTREE,
  INDEX `goodsusage_rentor`(`rentor_id`) USING BTREE,
  CONSTRAINT `goodsusage_PIC` FOREIGN KEY (`PIC_id`) REFERENCES `t_user` (`uid`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `goodsusage_device` FOREIGN KEY (`goods_code`) REFERENCES `t_goods` (`goods_code`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `goodsusage_rentor` FOREIGN KEY (`rentor_id`) REFERENCES `t_user` (`uid`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_grade
-- ----------------------------
DROP TABLE IF EXISTS `t_grade`;
CREATE TABLE `t_grade`  (
  `grade_serial` int(255) NOT NULL AUTO_INCREMENT COMMENT '分数流水号',
  `test_serial` int(11) NOT NULL COMMENT '考试号',
  `student_id` int(11) NULL DEFAULT NULL COMMENT '学生id',
  `grade` int(11) NULL DEFAULT NULL COMMENT '分数',
  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`grade_serial`) USING BTREE,
  INDEX `grade_student`(`student_id`) USING BTREE,
  INDEX `grade_test`(`test_serial`) USING BTREE,
  CONSTRAINT `grade_student` FOREIGN KEY (`student_id`) REFERENCES `t_user` (`uid`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `grade_test` FOREIGN KEY (`test_serial`) REFERENCES `t_test` (`test_serial`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_han
-- ----------------------------
DROP TABLE IF EXISTS `t_han`;
CREATE TABLE `t_han`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `han` enum('微憨','中憨','很憨') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_han
-- ----------------------------
INSERT INTO `t_han` VALUES (2, '辉辉', '很憨');

-- ----------------------------
-- Table structure for t_homework
-- ----------------------------
DROP TABLE IF EXISTS `t_homework`;
CREATE TABLE `t_homework`  (
  `hw_serial` int(11) NOT NULL COMMENT '作业流水号',
  `arrange_serial` int(11) NULL DEFAULT NULL COMMENT '作业安排流水号',
  `student_id` int(11) NOT NULL COMMENT '学生id',
  `hw_file` int(11) NULL DEFAULT NULL COMMENT '作业文件',
  `grade` int(11) NULL DEFAULT NULL COMMENT '分数',
  `overtime` enum('超时','未超时') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否超时(超时，未超时)',
  PRIMARY KEY (`hw_serial`) USING BTREE,
  INDEX `homework_student`(`student_id`) USING BTREE,
  INDEX `homework_arrange`(`arrange_serial`) USING BTREE,
  INDEX `homework_file`(`hw_file`) USING BTREE,
  CONSTRAINT `homework_arrange` FOREIGN KEY (`arrange_serial`) REFERENCES `t_homeworkarrange` (`arrange_serial`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `homework_file` FOREIGN KEY (`hw_file`) REFERENCES `t_file` (`file_serial`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `homework_student` FOREIGN KEY (`student_id`) REFERENCES `t_user` (`uid`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_homeworkarrange
-- ----------------------------
DROP TABLE IF EXISTS `t_homeworkarrange`;
CREATE TABLE `t_homeworkarrange`  (
  `arrange_serial` int(11) NOT NULL AUTO_INCREMENT COMMENT '作业安排流水号',
  `class_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '班级编号',
  `arrange_file` int(11) NULL DEFAULT NULL COMMENT '相关文件 可为null',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作业内容文字说明',
  `deadline` timestamp(0) NULL DEFAULT NULL COMMENT '截至时间',
  PRIMARY KEY (`arrange_serial`) USING BTREE,
  INDEX `arrange_serial`(`arrange_serial`) USING BTREE,
  INDEX `hwarrange_class`(`class_code`) USING BTREE,
  INDEX `hwarrange_file`(`arrange_file`) USING BTREE,
  CONSTRAINT `hwarrange_class` FOREIGN KEY (`class_code`) REFERENCES `t_class` (`class_code`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `hwarrange_file` FOREIGN KEY (`arrange_file`) REFERENCES `t_file` (`file_serial`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 7747001 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_homeworkarrange
-- ----------------------------
INSERT INTO `t_homeworkarrange` VALUES (7747000, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_messageboard
-- ----------------------------
DROP TABLE IF EXISTS `t_messageboard`;
CREATE TABLE `t_messageboard`  (
  `message_serial` int(11) NOT NULL AUTO_INCREMENT COMMENT '留言流水号',
  `uploader_id` int(11) NOT NULL COMMENT '上传人id',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容',
  `date` timestamp(0) NULL DEFAULT NULL COMMENT '时间',
  `class_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '班级号',
  `reply` int(255) NULL DEFAULT NULL COMMENT '回复留言',
  PRIMARY KEY (`message_serial`) USING BTREE,
  INDEX `messageboard_class`(`class_code`) USING BTREE,
  INDEX `messageboard_uploader`(`uploader_id`) USING BTREE,
  INDEX `messageboard_reply`(`reply`) USING BTREE,
  CONSTRAINT `messageboard_class` FOREIGN KEY (`class_code`) REFERENCES `t_class` (`class_code`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `messageboard_reply` FOREIGN KEY (`reply`) REFERENCES `t_messageboard` (`message_serial`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `messageboard_uploader` FOREIGN KEY (`uploader_id`) REFERENCES `t_user` (`uid`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 789004006 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_messageboard
-- ----------------------------
INSERT INTO `t_messageboard` VALUES (789004002, 116193704, 'hello world！', '2020-05-31 00:54:19', 'ZHE10120200301', NULL);
INSERT INTO `t_messageboard` VALUES (789004003, 116193704, 'What is the homework today?', '2020-05-31 01:02:55', 'ZHE10120200301', NULL);
INSERT INTO `t_messageboard` VALUES (789004004, 116193704, 'Is there going to be a test tomorrow?', '2020-05-31 01:03:53', 'ZHE10120200301', NULL);
INSERT INTO `t_messageboard` VALUES (789004005, 116193704, 'Today‘s lecture is fairly hard, could it be a little easier next time?', '2020-05-31 01:06:38', 'ZHE10120200301', NULL);

-- ----------------------------
-- Table structure for t_recruit
-- ----------------------------
DROP TABLE IF EXISTS `t_recruit`;
CREATE TABLE `t_recruit`  (
  `recruit_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '招聘编号',
  `PIC_id` int(11) NULL DEFAULT NULL COMMENT '主办人id',
  `schedule_serial` int(11) NULL DEFAULT NULL COMMENT '日程安排流水号',
  `place` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地点',
  `method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '方式（网络，实地等）',
  `catagory` enum('教师','职工','学生') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '招聘对象类型（教师，学生，职工）',
  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `enable` int(11) NULL DEFAULT NULL COMMENT '是否结束',
  PRIMARY KEY (`recruit_code`) USING BTREE,
  INDEX `recruit_PIC`(`PIC_id`) USING BTREE,
  INDEX `recruit_schedule`(`schedule_serial`) USING BTREE,
  CONSTRAINT `recruit_PIC` FOREIGN KEY (`PIC_id`) REFERENCES `t_user` (`uid`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `recruit_schedule` FOREIGN KEY (`schedule_serial`) REFERENCES `t_schedule` (`schedule_serial`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_recruitee
-- ----------------------------
DROP TABLE IF EXISTS `t_recruitee`;
CREATE TABLE `t_recruitee`  (
  `recruitee_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '受招募人流水号',
  `recruit_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '招聘会编号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名字',
  `resume_file` int(11) NULL DEFAULT NULL COMMENT '简历文档',
  `catagory` enum('教师','职工','学生') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型（学生、职员、教师）',
  `audit_serial` int(11) NULL DEFAULT NULL COMMENT '审核编号',
  PRIMARY KEY (`recruitee_code`) USING BTREE,
  INDEX `recruitee_recruit`(`recruit_code`) USING BTREE,
  INDEX `recruitee_resume`(`resume_file`) USING BTREE,
  INDEX `recruitee_audit`(`audit_serial`) USING BTREE,
  CONSTRAINT `recruitee_audit` FOREIGN KEY (`audit_serial`) REFERENCES `t_audit` (`audit_serial`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `recruitee_recruit` FOREIGN KEY (`recruit_code`) REFERENCES `t_recruit` (`recruit_code`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `recruitee_resume` FOREIGN KEY (`resume_file`) REFERENCES `t_file` (`file_serial`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_room
-- ----------------------------
DROP TABLE IF EXISTS `t_room`;
CREATE TABLE `t_room`  (
  `room_num` int(11) NOT NULL COMMENT '房间号',
  `usage` enum('教室','库房','办公室','茶水间','休息室','卫生间','暂无') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用途（教室，库房，办公室等）',
  `available` int(11) NULL DEFAULT NULL COMMENT '是否可用（0：可用 1：占用）',
  PRIMARY KEY (`room_num`) USING BTREE,
  INDEX `room_num`(`room_num`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_room
-- ----------------------------
INSERT INTO `t_room` VALUES (101, '教室', 1);
INSERT INTO `t_room` VALUES (102, '教室', 1);
INSERT INTO `t_room` VALUES (103, '教室', 1);
INSERT INTO `t_room` VALUES (104, '茶水间', 1);
INSERT INTO `t_room` VALUES (105, '休息室', 1);
INSERT INTO `t_room` VALUES (106, '卫生间', 1);
INSERT INTO `t_room` VALUES (107, '教室', 1);
INSERT INTO `t_room` VALUES (108, '办公室', 1);
INSERT INTO `t_room` VALUES (201, '教室', 1);
INSERT INTO `t_room` VALUES (202, '教室', 1);
INSERT INTO `t_room` VALUES (203, '教室', 1);
INSERT INTO `t_room` VALUES (204, '茶水间', 1);
INSERT INTO `t_room` VALUES (205, '休息室', 1);
INSERT INTO `t_room` VALUES (206, '卫生间', 1);
INSERT INTO `t_room` VALUES (207, '教室', 1);
INSERT INTO `t_room` VALUES (208, '办公室', 1);
INSERT INTO `t_room` VALUES (301, '暂无', 0);
INSERT INTO `t_room` VALUES (302, '暂无', 0);
INSERT INTO `t_room` VALUES (303, '暂无', 0);
INSERT INTO `t_room` VALUES (304, '茶水间', 1);
INSERT INTO `t_room` VALUES (305, '休息室', 1);
INSERT INTO `t_room` VALUES (306, '卫生间', 1);
INSERT INTO `t_room` VALUES (307, '暂无', 0);
INSERT INTO `t_room` VALUES (308, '暂无', 0);
INSERT INTO `t_room` VALUES (401, '暂无', 0);
INSERT INTO `t_room` VALUES (402, '暂无', 0);
INSERT INTO `t_room` VALUES (403, '暂无', 0);
INSERT INTO `t_room` VALUES (404, '茶水间', 1);
INSERT INTO `t_room` VALUES (405, '休息室', 1);
INSERT INTO `t_room` VALUES (406, '卫生间', 1);
INSERT INTO `t_room` VALUES (407, '暂无', 0);
INSERT INTO `t_room` VALUES (408, '暂无', 0);
INSERT INTO `t_room` VALUES (501, '暂无', 0);
INSERT INTO `t_room` VALUES (502, '暂无', 0);
INSERT INTO `t_room` VALUES (503, '暂无', 0);
INSERT INTO `t_room` VALUES (504, '暂无', 0);
INSERT INTO `t_room` VALUES (505, '库房', 1);
INSERT INTO `t_room` VALUES (506, '库房', 1);
INSERT INTO `t_room` VALUES (507, '库房', 1);
INSERT INTO `t_room` VALUES (508, '库房', 1);

-- ----------------------------
-- Table structure for t_salary
-- ----------------------------
DROP TABLE IF EXISTS `t_salary`;
CREATE TABLE `t_salary`  (
  `salary_serial` int(11) NOT NULL AUTO_INCREMENT COMMENT '工资流水号',
  `stuff_id` int(11) NULL DEFAULT NULL COMMENT '员工id',
  `basic_salary` decimal(10, 2) NULL DEFAULT NULL COMMENT '基本工资',
  `bonus` decimal(10, 0) NULL DEFAULT NULL COMMENT '奖金',
  `total_salary` decimal(10, 0) NULL DEFAULT NULL COMMENT '总工资',
  `insurance` decimal(10, 0) NULL DEFAULT NULL COMMENT '五险一金数额',
  `month` int(11) NULL DEFAULT NULL COMMENT '月份',
  `year` int(11) NULL DEFAULT NULL COMMENT '年份',
  PRIMARY KEY (`salary_serial`) USING BTREE,
  INDEX `salary_stuff`(`stuff_id`) USING BTREE,
  CONSTRAINT `salary_stuff` FOREIGN KEY (`stuff_id`) REFERENCES `t_user` (`uid`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 370749002 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_salary
-- ----------------------------
INSERT INTO `t_salary` VALUES (370749000, 116193701, 5000.00, 0, 4800, 200, 5, 2020);
INSERT INTO `t_salary` VALUES (370749001, 116193714, 5000.00, 0, 4800, 200, 5, 2020);

-- ----------------------------
-- Table structure for t_schedule
-- ----------------------------
DROP TABLE IF EXISTS `t_schedule`;
CREATE TABLE `t_schedule`  (
  `schedule_serial` int(11) NOT NULL AUTO_INCREMENT COMMENT '日程id',
  `event_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '事件编号',
  `start_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '结束时间',
  `week` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '周数',
  `year` int(11) NULL DEFAULT NULL COMMENT '年份',
  `semester` enum('春季','夏季','秋季','冬季') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '季度',
  `start_date` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开始日期',
  `end_date` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '结束日期',
  PRIMARY KEY (`schedule_serial`) USING BTREE,
  INDEX `year`(`year`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 691874005 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_schedule
-- ----------------------------
INSERT INTO `t_schedule` VALUES (691874000, 'BEL10120200301', '09:00', '10:00', '星期三', 2020, '春季', '3.1', '6.1');
INSERT INTO `t_schedule` VALUES (691874001, 'BEL10220200301', '11:30', '12:30', '星期三', 2020, '春季', '3.1', '6.1');
INSERT INTO `t_schedule` VALUES (691874002, 'ZHE10120200301', '14:00', '15:00', '星期四', 2020, '夏季', '6.3', '9.3');
INSERT INTO `t_schedule` VALUES (691874003, 'ZHE10220200301', '10:00', '11:00', '星期四', 2020, '夏季', '6.3', '9.3');

-- ----------------------------
-- Table structure for t_stucourse
-- ----------------------------
DROP TABLE IF EXISTS `t_stucourse`;
CREATE TABLE `t_stucourse`  (
  `sc_serial` int(11) NOT NULL AUTO_INCREMENT COMMENT 'sc=student course流水号',
  `student_id` int(11) NOT NULL COMMENT '学生id',
  `course_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程编号',
  `class_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '班级编号',
  `fee` int(11) NULL DEFAULT NULL COMMENT '费用',
  `pay` enum('已支付','未支付') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '未支付' COMMENT '是否支付(已支付，未支付)',
  `finance_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收支编号',
  `state` enum('未退课','已退课') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '未退课' COMMENT '是否退课',
  `enable` int(11) NULL DEFAULT NULL COMMENT '是否结课',
  PRIMARY KEY (`sc_serial`) USING BTREE,
  INDEX `stucourse_id`(`student_id`) USING BTREE,
  INDEX `stucourse_class`(`class_code`) USING BTREE,
  INDEX `stucourse_course`(`course_code`) USING BTREE,
  INDEX `stucourse_finance`(`finance_code`) USING BTREE,
  CONSTRAINT `stucourse_class` FOREIGN KEY (`class_code`) REFERENCES `t_class` (`class_code`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `stucourse_course` FOREIGN KEY (`course_code`) REFERENCES `t_course` (`course_code`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `stucourse_finance` FOREIGN KEY (`finance_code`) REFERENCES `t_finance` (`finance_code`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `stucourse_id` FOREIGN KEY (`student_id`) REFERENCES `t_user` (`uid`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 80597407 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_stucourse
-- ----------------------------
INSERT INTO `t_stucourse` VALUES (80597400, 116193706, 'BEL101', 'BEL10120200301', 360, '已支付', 'REV202005252353', '未退课', 1);
INSERT INTO `t_stucourse` VALUES (80597405, 116193704, 'ZHE101', 'ZHE10120200301', 240, '未支付', 'REV202005271621', '未退课', 1);
INSERT INTO `t_stucourse` VALUES (80597406, 116193704, 'BEL101', 'BEL10120200301', 240, '已支付', 'REV202005271624', '未退课', 1);

-- ----------------------------
-- Table structure for t_stuff
-- ----------------------------
DROP TABLE IF EXISTS `t_stuff`;
CREATE TABLE `t_stuff`  (
  `stuff_id` int(11) NOT NULL COMMENT '职工id',
  `department_id` int(11) NULL DEFAULT NULL COMMENT '部门id',
  `position` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职位',
  PRIMARY KEY (`stuff_id`) USING BTREE,
  INDEX `stuff_department`(`department_id`) USING BTREE,
  CONSTRAINT `stuff_department` FOREIGN KEY (`department_id`) REFERENCES `t_department` (`department_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `stuff_id` FOREIGN KEY (`stuff_id`) REFERENCES `t_user` (`uid`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_stuff
-- ----------------------------
INSERT INTO `t_stuff` VALUES (116193701, 47000, '教师');
INSERT INTO `t_stuff` VALUES (116193706, 47000, '教师');
INSERT INTO `t_stuff` VALUES (116193710, 47001, '教师');
INSERT INTO `t_stuff` VALUES (116193714, 47001, '教师');

-- ----------------------------
-- Table structure for t_teacourse
-- ----------------------------
DROP TABLE IF EXISTS `t_teacourse`;
CREATE TABLE `t_teacourse`  (
  `tc_serial` int(11) NOT NULL AUTO_INCREMENT COMMENT 'tc=teacher course流水号',
  `teacher_id` int(11) NULL DEFAULT NULL COMMENT '教师id',
  `course_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程编号',
  `class_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '班级编号',
  `percentage` int(11) NULL DEFAULT NULL COMMENT '班级表现(学生均分/满分)',
  `remain` int(11) NULL DEFAULT NULL COMMENT '学生留存率(实际学生数量/初始学生数量)',
  `intro` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '教师个人介绍，课程介绍。',
  `enable` int(11) NULL DEFAULT NULL COMMENT '是否结课',
  PRIMARY KEY (`tc_serial`) USING BTREE,
  INDEX `teacourse_id`(`teacher_id`) USING BTREE,
  INDEX `teacourse_class`(`class_code`) USING BTREE,
  INDEX `teacourse_course`(`course_code`) USING BTREE,
  CONSTRAINT `teacourse_class` FOREIGN KEY (`class_code`) REFERENCES `t_class` (`class_code`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `teacourse_course` FOREIGN KEY (`course_code`) REFERENCES `t_course` (`course_code`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `teacourse_id` FOREIGN KEY (`teacher_id`) REFERENCES `t_user` (`uid`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_test
-- ----------------------------
DROP TABLE IF EXISTS `t_test`;
CREATE TABLE `t_test`  (
  `test_serial` int(11) NOT NULL AUTO_INCREMENT COMMENT '考试号',
  `class_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '班级号',
  `tester_id1` int(11) NULL DEFAULT NULL COMMENT '监考人甲',
  `tester_id2` int(11) NULL DEFAULT NULL COMMENT '监考人乙',
  `test_file` int(11) NULL DEFAULT NULL COMMENT '考试内容文件',
  `schedule_serial` int(11) NULL DEFAULT NULL COMMENT '日程安排流水号',
  PRIMARY KEY (`test_serial`) USING BTREE,
  INDEX `test_class`(`class_code`) USING BTREE,
  INDEX `test_tester`(`tester_id2`) USING BTREE,
  INDEX `test_file`(`test_file`) USING BTREE,
  INDEX `test_tester1`(`tester_id1`) USING BTREE,
  INDEX `test_schedule`(`schedule_serial`) USING BTREE,
  CONSTRAINT `test_class` FOREIGN KEY (`class_code`) REFERENCES `t_class` (`class_code`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `test_file` FOREIGN KEY (`test_file`) REFERENCES `t_file` (`file_serial`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `test_schedule` FOREIGN KEY (`schedule_serial`) REFERENCES `t_schedule` (`schedule_serial`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `test_tester1` FOREIGN KEY (`tester_id1`) REFERENCES `t_user` (`uid`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `test_tester2` FOREIGN KEY (`tester_id2`) REFERENCES `t_user` (`uid`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `uid` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名字',
  `gender` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `birthday` date NULL DEFAULT NULL COMMENT '生日',
  `position` enum('职工','学生','老师','管理员') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '学生' COMMENT '职位',
  `tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `enable` int(11) NOT NULL DEFAULT 1 COMMENT '是否可用',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `state` enum('online','offline') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'offline' COMMENT '登陆状态(oneline,offline)',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 116193716 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (116193701, '车岚', '女', '1980-03-07', '老师', '13708342137', 1, '666666', 'offline');
INSERT INTO `t_user` VALUES (116193702, '冷飞', '男', '2000-08-26', '职工', '15923343188', 1, '666666', 'offline');
INSERT INTO `t_user` VALUES (116193703, '俞溪', '女', '1959-11-22', '管理员', '18990769422', 1, '666666', 'offline');
INSERT INTO `t_user` VALUES (116193704, '蔺霓霓', '女', '2004-07-08', '学生', '17649322369', 1, '666666', 'online');
INSERT INTO `t_user` VALUES (116193705, '罗兮', '女', '1998-08-04', '职工', '19823476431', 1, '666666', 'offline');
INSERT INTO `t_user` VALUES (116193706, '林梨', '女', '1998-07-17', '学生', '16756733409', 1, '666666', 'offline');
INSERT INTO `t_user` VALUES (116193707, '王缤', '男', '1975-08-23', '老师', '19967584367', 1, '666666', 'offline');
INSERT INTO `t_user` VALUES (116193708, '柳儒彦', '男', '1995-10-21', '学生', '17625360495', 1, '666666', 'offline');
INSERT INTO `t_user` VALUES (116193709, '秋观雪', '女', '1994-04-06', '学生', '17898764588', 1, '666666', 'offline');
INSERT INTO `t_user` VALUES (116193710, '叶翎', '男', '1987-11-10', '老师', '14926302108', 1, '666666', 'offline');
INSERT INTO `t_user` VALUES (116193711, '谭筱', '女', '2001-03-12', '学生', '18837482930', 1, '666666', 'offline');
INSERT INTO `t_user` VALUES (116193712, '韩岳凡', '男', '1990-04-21', '学生', '16627392039', 1, '666666', 'online');
INSERT INTO `t_user` VALUES (116193713, '崔昀', '男', '1995-12-14', '学生', '16738392900', 1, '666666', 'offline');
INSERT INTO `t_user` VALUES (116193714, '兰喃', '女', '1989-05-25', '老师', '18928394778', 1, '666666', 'offline');
INSERT INTO `t_user` VALUES (116193715, '陈晖', '男', '1997-04-01', '管理员', '15878652667', 1, '666666', 'offline');

SET FOREIGN_KEY_CHECKS = 1;
