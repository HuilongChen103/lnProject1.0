/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : trainingsys

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 09/05/2020 17:24:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_account
-- ----------------------------
DROP TABLE IF EXISTS `t_account`;
CREATE TABLE `t_account`  (
  `uid` int(8) NOT NULL COMMENT 'id',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `state` enum('online','offline') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登陆状态(离线：0 在线：1)',
  PRIMARY KEY (`uid`) USING BTREE,
  INDEX `account_id`(`uid`) USING BTREE,
  CONSTRAINT `account_id` FOREIGN KEY (`uid`) REFERENCES `t_user` (`uid`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_account
-- ----------------------------
INSERT INTO `t_account` VALUES (2, '22222', 'online');

-- ----------------------------
-- Table structure for t_assessment
-- ----------------------------
DROP TABLE IF EXISTS `t_assessment`;
CREATE TABLE `t_assessment`  (
  `assess_id` int(11) NOT NULL COMMENT '测评编号',
  `target_id` int(11) NOT NULL COMMENT '被测者id',
  `assessor_id` int(11) NULL DEFAULT NULL COMMENT '测评者id',
  `semester` enum('春季','夏季','秋季','冬季') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学期',
  `year` int(255) NULL DEFAULT NULL COMMENT '学年',
  `event_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '事件编号(班级，招聘，活动等)',
  `grade` int(255) NULL DEFAULT NULL COMMENT '分数',
  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评价',
  PRIMARY KEY (`assess_id`) USING BTREE,
  INDEX `assess_assessor`(`assessor_id`) USING BTREE,
  INDEX `assess_target`(`target_id`) USING BTREE,
  INDEX `assess_recruit`(`event_code`) USING BTREE,
  CONSTRAINT `assess_assessor` FOREIGN KEY (`assessor_id`) REFERENCES `t_user` (`uid`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `assess_class` FOREIGN KEY (`event_code`) REFERENCES `t_class` (`class_code`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `assess_recruit` FOREIGN KEY (`event_code`) REFERENCES `t_recruit` (`recruit_code`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `assess_target` FOREIGN KEY (`target_id`) REFERENCES `t_user` (`uid`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_audit
-- ----------------------------
DROP TABLE IF EXISTS `t_audit`;
CREATE TABLE `t_audit`  (
  `audit_serial` int(255) NOT NULL AUTO_INCREMENT COMMENT '审核编号',
  `auditor_id` int(11) NULL DEFAULT NULL COMMENT '审核人id',
  `applicant_id` int(11) NULL DEFAULT NULL COMMENT '申请人id',
  `event_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '事件编号(招聘，物资调用...)',
  `event` enum('请假','调休','物资调用','转岗','退课','辞职','物资采购') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '事件类型',
  `apply_date` timestamp(0) NULL DEFAULT NULL COMMENT '申请时间',
  `audit_date` timestamp(0) NULL DEFAULT NULL COMMENT '审核时间',
  `state` enum('通过','未通过','待审核') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核状态(通过，未通过...)',
  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`audit_serial`) USING BTREE,
  INDEX `audit_auditor`(`auditor_id`) USING BTREE,
  INDEX `audit_event_expense`(`event_code`) USING BTREE,
  INDEX `audit_applicant`(`applicant_id`) USING BTREE,
  CONSTRAINT `audit_applicant` FOREIGN KEY (`applicant_id`) REFERENCES `t_user` (`uid`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `audit_auditor` FOREIGN KEY (`auditor_id`) REFERENCES `t_user` (`uid`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `audit_event_device` FOREIGN KEY (`event_code`) REFERENCES `t_deviceusage` (`usage_code`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `audit_event_expense` FOREIGN KEY (`event_code`) REFERENCES `t_expense` (`expense_code`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `audit_event_recruit` FOREIGN KEY (`event_code`) REFERENCES `t_recruit` (`recruit_code`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `audit_event_revenue` FOREIGN KEY (`event_code`) REFERENCES `t_revenue` (`revenue_code`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_benefitevaluation
-- ----------------------------
DROP TABLE IF EXISTS `t_benefitevaluation`;
CREATE TABLE `t_benefitevaluation`  (
  `benefit_serial` int(255) NULL DEFAULT NULL COMMENT '绩效考评流水号',
  `stuff_id` int(11) NULL DEFAULT NULL COMMENT '员工id（职员、教师）',
  `year` int(255) NULL DEFAULT NULL COMMENT '年份',
  `semester` enum('春季','夏季','秋季','冬季') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '季度',
  `benefit` int(255) NULL DEFAULT NULL COMMENT '效益（具体金额）',
  `assessment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '考评（文字说明）',
  INDEX `benefit_stuff`(`stuff_id`) USING BTREE,
  CONSTRAINT `benefit_stuff` FOREIGN KEY (`stuff_id`) REFERENCES `t_user` (`uid`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_class
-- ----------------------------
DROP TABLE IF EXISTS `t_class`;
CREATE TABLE `t_class`  (
  `class_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '班级号',
  `course_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程号',
  `student_num` int(11) NULL DEFAULT NULL COMMENT '学生数量',
  `teacher_id` int(11) NULL DEFAULT NULL COMMENT '教师id',
  `class_num` int(11) NULL DEFAULT NULL COMMENT '班级在对应课程中的序号',
  PRIMARY KEY (`class_code`) USING BTREE,
  INDEX `class_course`(`course_code`) USING BTREE,
  INDEX `class_teacher`(`teacher_id`) USING BTREE,
  CONSTRAINT `class_course` FOREIGN KEY (`course_code`) REFERENCES `t_course` (`course_code`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `class_teacher` FOREIGN KEY (`teacher_id`) REFERENCES `t_teacher` (`teacher_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_course
-- ----------------------------
DROP TABLE IF EXISTS `t_course`;
CREATE TABLE `t_course`  (
  `course_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程编号',
  `course_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程名称',
  `duration` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程时长',
  `schedule_id` int(11) NULL DEFAULT NULL COMMENT '日程安排id',
  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`course_code`) USING BTREE,
  INDEX `course_schedule`(`schedule_id`) USING BTREE,
  CONSTRAINT `course_schedule` FOREIGN KEY (`schedule_id`) REFERENCES `t_schedule` (`schedule_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_courseware
-- ----------------------------
DROP TABLE IF EXISTS `t_courseware`;
CREATE TABLE `t_courseware`  (
  `courseware_serial` int(255) NOT NULL COMMENT '课件流水号',
  `class_code` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '班级号',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `file_serial` int(255) NULL DEFAULT NULL COMMENT '文件序号',
  PRIMARY KEY (`courseware_serial`) USING BTREE,
  INDEX `courseware_file`(`file_serial`) USING BTREE,
  INDEX `courseware_class`(`class_code`) USING BTREE,
  CONSTRAINT `courseware_class` FOREIGN KEY (`class_code`) REFERENCES `t_class` (`class_code`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `courseware_file` FOREIGN KEY (`file_serial`) REFERENCES `t_file` (`file_serial`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_department
-- ----------------------------
DROP TABLE IF EXISTS `t_department`;
CREATE TABLE `t_department`  (
  `department_id` int(11) NOT NULL COMMENT '部门id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门名称',
  PRIMARY KEY (`department_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_device
-- ----------------------------
DROP TABLE IF EXISTS `t_device`;
CREATE TABLE `t_device`  (
  `device_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '器材编号',
  `PIC_id` int(11) NULL DEFAULT NULL COMMENT '采办人id(person in charge)',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '器材名称',
  `catagory` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类目',
  `stock_in_date` datetime(0) NULL DEFAULT NULL COMMENT '入库时间',
  `stock_out_date` datetime(0) NULL DEFAULT NULL COMMENT '出库时间',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `room_num` int(255) NULL DEFAULT NULL COMMENT '现在的地点（房间号）',
  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`device_code`) USING BTREE,
  INDEX `device_PIC`(`PIC_id`) USING BTREE,
  INDEX `device_location`(`room_num`) USING BTREE,
  CONSTRAINT `device_PIC` FOREIGN KEY (`PIC_id`) REFERENCES `t_user` (`uid`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `device_location` FOREIGN KEY (`room_num`) REFERENCES `t_room` (`room_num`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_deviceusage
-- ----------------------------
DROP TABLE IF EXISTS `t_deviceusage`;
CREATE TABLE `t_deviceusage`  (
  `usage_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '使用编号',
  `device_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '器材编号',
  `rentor_id` int(11) NULL DEFAULT NULL COMMENT '租借者id（若为空，则是官方分配，比如教室的桌椅）',
  `PIC_id` int(11) NULL DEFAULT NULL COMMENT '负责人id（person in charge）',
  `rent_date` datetime(0) NULL DEFAULT NULL COMMENT '租借时间',
  `return_date` datetime(0) NULL DEFAULT NULL COMMENT '归还时间',
  PRIMARY KEY (`usage_code`) USING BTREE,
  INDEX `deviceusage_device`(`device_code`) USING BTREE,
  INDEX `deviceusage_rentor`(`rentor_id`) USING BTREE,
  INDEX `deviceusage_PIC`(`PIC_id`) USING BTREE,
  CONSTRAINT `deviceusage_PIC` FOREIGN KEY (`PIC_id`) REFERENCES `t_user` (`uid`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `deviceusage_device` FOREIGN KEY (`device_code`) REFERENCES `t_device` (`device_code`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `deviceusage_rentor` FOREIGN KEY (`rentor_id`) REFERENCES `t_user` (`uid`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_expense
-- ----------------------------
DROP TABLE IF EXISTS `t_expense`;
CREATE TABLE `t_expense`  (
  `expense_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '开支流水号',
  `PIC_id` int(255) NOT NULL COMMENT '负责人',
  `pay_account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '付款账户',
  `receive_account` int(255) NULL DEFAULT NULL COMMENT '收款账户',
  `trade_method` enum('支付宝','微信','银行转账') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易方式',
  `amount` int(255) NULL DEFAULT NULL COMMENT '数额',
  `date` timestamp(0) NULL DEFAULT NULL COMMENT '日期时间',
  `category` enum('员工薪水','器材采购','水电租金','广告宣传') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类目',
  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`expense_code`) USING BTREE,
  INDEX `exoense_sponsor`(`PIC_id`) USING BTREE,
  CONSTRAINT `exoense_sponsor` FOREIGN KEY (`PIC_id`) REFERENCES `t_user` (`uid`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_file
-- ----------------------------
DROP TABLE IF EXISTS `t_file`;
CREATE TABLE `t_file`  (
  `file_serial` int(255) NOT NULL COMMENT '文件流水号',
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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_grade
-- ----------------------------
DROP TABLE IF EXISTS `t_grade`;
CREATE TABLE `t_grade`  (
  `grade_serial` int(255) NOT NULL COMMENT '分数流水号',
  `test_id` int(11) NOT NULL COMMENT '考试号',
  `student_id` int(11) NULL DEFAULT NULL COMMENT '学生id',
  `grade` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分数',
  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`grade_serial`) USING BTREE,
  INDEX `grade_student`(`student_id`) USING BTREE,
  INDEX `grade_test`(`test_id`) USING BTREE,
  CONSTRAINT `grade_student` FOREIGN KEY (`student_id`) REFERENCES `t_user` (`uid`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `grade_test` FOREIGN KEY (`test_id`) REFERENCES `t_test` (`test_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_homework
-- ----------------------------
DROP TABLE IF EXISTS `t_homework`;
CREATE TABLE `t_homework`  (
  `hw_serial` int(255) NOT NULL COMMENT '作业流水号',
  `arrange_serial` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作业安排流水号',
  `student_id` int(11) NOT NULL COMMENT '学生id',
  `hw_file` int(255) NULL DEFAULT NULL COMMENT '作业文件',
  `grade` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分数',
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
  `arrange_serial` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作业安排流水号',
  `class_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '班级编号',
  `arrange_file` int(255) NULL DEFAULT NULL COMMENT '相关文件 可为null',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作业内容文字说明',
  INDEX `arrange_serial`(`arrange_serial`) USING BTREE,
  INDEX `hwarrange_class`(`class_code`) USING BTREE,
  INDEX `hwarrange_file`(`arrange_file`) USING BTREE,
  CONSTRAINT `hwarrange_class` FOREIGN KEY (`class_code`) REFERENCES `t_class` (`class_code`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `hwarrange_file` FOREIGN KEY (`arrange_file`) REFERENCES `t_file` (`file_serial`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_messageboard
-- ----------------------------
DROP TABLE IF EXISTS `t_messageboard`;
CREATE TABLE `t_messageboard`  (
  `message_serial` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '留言流水号',
  `uploader_id` int(11) NOT NULL COMMENT '上传人id',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容',
  `date` timestamp(0) NULL DEFAULT NULL COMMENT '时间',
  `class_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '班级号',
  PRIMARY KEY (`message_serial`) USING BTREE,
  INDEX `messageboard_class`(`class_code`) USING BTREE,
  INDEX `messageboard_uploader`(`uploader_id`) USING BTREE,
  CONSTRAINT `messageboard_class` FOREIGN KEY (`class_code`) REFERENCES `t_class` (`class_code`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `messageboard_uploader` FOREIGN KEY (`uploader_id`) REFERENCES `t_user` (`uid`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_recruit
-- ----------------------------
DROP TABLE IF EXISTS `t_recruit`;
CREATE TABLE `t_recruit`  (
  `recruit_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '招聘编号',
  `PIC_id` int(11) NULL DEFAULT NULL COMMENT '主办人id',
  `date` datetime(0) NULL DEFAULT NULL COMMENT '日期',
  `place` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地点',
  `method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '方式（网络，实地等）',
  `catagory` enum('教师','职工','学生') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '招聘对象类型（教师，学生，职工）',
  PRIMARY KEY (`recruit_code`) USING BTREE,
  INDEX `recruit_sponsor`(`PIC_id`) USING BTREE,
  CONSTRAINT `recruit_sponsor` FOREIGN KEY (`PIC_id`) REFERENCES `t_user` (`uid`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_recruitee
-- ----------------------------
DROP TABLE IF EXISTS `t_recruitee`;
CREATE TABLE `t_recruitee`  (
  `recruitee_serial` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '受招募人流水号',
  `recruit_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '招聘会编号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名字',
  `resume_file` int(255) NULL DEFAULT NULL COMMENT '简历文档',
  `catagory` enum('教师','职工','学生') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型（学生、职员、教师）',
  PRIMARY KEY (`recruitee_serial`) USING BTREE,
  INDEX `recruitee_recruit`(`recruit_code`) USING BTREE,
  INDEX `recruitee_resume`(`resume_file`) USING BTREE,
  CONSTRAINT `recruitee_recruit` FOREIGN KEY (`recruit_code`) REFERENCES `t_recruit` (`recruit_code`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `recruitee_resume` FOREIGN KEY (`resume_file`) REFERENCES `t_file` (`file_serial`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_revenue
-- ----------------------------
DROP TABLE IF EXISTS `t_revenue`;
CREATE TABLE `t_revenue`  (
  `revenue_code` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收入流水号',
  `PIC_id` int(11) NOT NULL COMMENT '负责人',
  `receive_account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收款账户',
  `pay_account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '付款账户',
  `trade_method` enum('支付宝','微信','银行转账') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易方式',
  `amount` int(255) NULL DEFAULT NULL COMMENT '数额',
  `date` timestamp(0) NULL DEFAULT NULL COMMENT '日期时间',
  `category` enum('课程费用','教材费用') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类目',
  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`revenue_code`) USING BTREE,
  INDEX `revenue_sponsor`(`PIC_id`) USING BTREE,
  CONSTRAINT `revenue_sponsor` FOREIGN KEY (`PIC_id`) REFERENCES `t_user` (`uid`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_room
-- ----------------------------
DROP TABLE IF EXISTS `t_room`;
CREATE TABLE `t_room`  (
  `room_num` int(11) NULL DEFAULT NULL COMMENT '房间号',
  `usage` enum('教室','库房','办公室','茶水间','休息室','卫生间') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用途（教室，库房，办公室等）',
  `available` int(255) NULL DEFAULT NULL COMMENT '是否可用（0：可用 1：占用）',
  INDEX `room_num`(`room_num`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_salary
-- ----------------------------
DROP TABLE IF EXISTS `t_salary`;
CREATE TABLE `t_salary`  (
  `stuff_id` int(255) NULL DEFAULT NULL,
  `salary` decimal(10, 2) NULL DEFAULT NULL,
  `bonus` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `insurance` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_schedule
-- ----------------------------
DROP TABLE IF EXISTS `t_schedule`;
CREATE TABLE `t_schedule`  (
  `schedule_id` int(11) NOT NULL COMMENT '日程id',
  `event_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '事件编号',
  `time` datetime(0) NULL DEFAULT NULL COMMENT '时间段',
  `week` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '周数',
  `year` int(255) NULL DEFAULT NULL COMMENT '年份',
  `semester` enum('春季','夏季','秋季','冬季') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '季度',
  PRIMARY KEY (`schedule_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_student
-- ----------------------------
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student`  (
  `student_id` int(11) NOT NULL COMMENT '学生id',
  `class_array` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程数组',
  INDEX `student_id`(`student_id`) USING BTREE,
  CONSTRAINT `student_id` FOREIGN KEY (`student_id`) REFERENCES `t_user` (`uid`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_stuff
-- ----------------------------
DROP TABLE IF EXISTS `t_stuff`;
CREATE TABLE `t_stuff`  (
  `stuff_id` int(11) NOT NULL COMMENT '职工id',
  `department_id` int(255) NULL DEFAULT NULL COMMENT '部门id',
  `position` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职位',
  PRIMARY KEY (`stuff_id`) USING BTREE,
  INDEX `stuff_department`(`department_id`) USING BTREE,
  CONSTRAINT `stuff_department` FOREIGN KEY (`department_id`) REFERENCES `t_department` (`department_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `stuff_id` FOREIGN KEY (`stuff_id`) REFERENCES `t_user` (`uid`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_teacher
-- ----------------------------
DROP TABLE IF EXISTS `t_teacher`;
CREATE TABLE `t_teacher`  (
  `teacher_id` int(11) NULL DEFAULT NULL COMMENT '教师id',
  `class_array` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '班级数组',
  `course_array` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程数组',
  INDEX `teacher_id`(`teacher_id`) USING BTREE,
  CONSTRAINT `teacher_id` FOREIGN KEY (`teacher_id`) REFERENCES `t_user` (`uid`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_test
-- ----------------------------
DROP TABLE IF EXISTS `t_test`;
CREATE TABLE `t_test`  (
  `test_id` int(11) NOT NULL COMMENT '考试号',
  `class_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '班级号',
  `tester_id` int(11) NULL DEFAULT NULL COMMENT '监考人',
  `test_file` int(255) NULL DEFAULT NULL COMMENT '考试内容文件',
  `start_time` timestamp(0) NULL DEFAULT NULL COMMENT '考试开始时间',
  `end_time` timestamp(0) NULL DEFAULT NULL COMMENT '考试结束时间',
  PRIMARY KEY (`test_id`) USING BTREE,
  INDEX `test_class`(`class_code`) USING BTREE,
  INDEX `test_tester`(`tester_id`) USING BTREE,
  INDEX `test_file`(`test_file`) USING BTREE,
  CONSTRAINT `test_class` FOREIGN KEY (`class_code`) REFERENCES `t_class` (`class_code`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `test_file` FOREIGN KEY (`test_file`) REFERENCES `t_file` (`file_serial`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `test_tester` FOREIGN KEY (`tester_id`) REFERENCES `t_user` (`uid`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `uid` int(8) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `birthday` timestamp(0) NULL DEFAULT NULL,
  `position` enum('职工','学生','老师','管理员') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `enable` int(4) NOT NULL DEFAULT 1,
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'user3', '男', '1997-04-02 08:00:00', '职工', 1);
INSERT INTO `t_user` VALUES (2, 'yahaha', '女', '1997-04-02 20:20:20', '职工', 0);
INSERT INTO `t_user` VALUES (3, 'user2', '男', '1997-04-02 17:20:20', '职工', 1);
INSERT INTO `t_user` VALUES (4, 'user2', '男', '1997-04-02 17:20:20', '职工', 1);
INSERT INTO `t_user` VALUES (5, 'user2', '男', '1997-04-02 17:20:20', '职工', 1);
INSERT INTO `t_user` VALUES (6, 'user2', '男', '1997-04-02 17:20:20', '职工', 1);
INSERT INTO `t_user` VALUES (7, 'user2', '男', '1997-04-02 17:20:20', '职工', 1);
INSERT INTO `t_user` VALUES (8, 'user2', '男', '1997-04-02 17:20:20', '职工', 1);
INSERT INTO `t_user` VALUES (9, 'user2', '男', '1997-04-02 17:20:20', '职工', 1);
INSERT INTO `t_user` VALUES (10, 'user2', '男', '1997-04-02 17:20:20', '职工', 1);
INSERT INTO `t_user` VALUES (11, 'user2', '男', '1997-04-02 17:20:20', '职工', 1);
INSERT INTO `t_user` VALUES (12, 'user2', '男', '1997-04-02 17:20:20', '职工', 1);
INSERT INTO `t_user` VALUES (13, 'user2', '男', '1997-04-02 17:20:20', '职工', 1);
INSERT INTO `t_user` VALUES (14, 'unique', '女', '1997-04-02 17:20:20', '职工', 1);
INSERT INTO `t_user` VALUES (15, 'unique', '女', '1997-04-02 17:20:20', NULL, 1);

SET FOREIGN_KEY_CHECKS = 1;
