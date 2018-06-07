/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : cssr

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-05-29 11:07:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `company`
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `company_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '公司ID',
  `company_name` varchar(50) NOT NULL COMMENT '公司名称',
  `company_address` varchar(100) NOT NULL COMMENT '公司地址',
  `company_score` float(2,1) NOT NULL DEFAULT '5.0' COMMENT '公司评分',
  `company_location` varchar(30) DEFAULT NULL COMMENT '公司地理位置（经纬度）逗号隔开',
  `company_phone` varchar(20) DEFAULT NULL,
  `company_email` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='公司表';

-- ----------------------------
-- Records of company
-- ----------------------------
INSERT INTO `company` VALUES ('0', '出厂商111', '四川省成都市双流区蓝润·华府春天', '5.0', '103.999474,30.52435', '13980069479', '1101494689@qq.com');
INSERT INTO `company` VALUES ('1', '西华大学', '四川省成都市郫都区', '5.0', '103.950172, 30.772898', '13980069479', null);

-- ----------------------------
-- Table structure for `demand`
-- ----------------------------
DROP TABLE IF EXISTS `demand`;
CREATE TABLE `demand` (
  `demand_id` varchar(50) NOT NULL COMMENT '需求ID',
  `demand_type` int(11) NOT NULL COMMENT '需求类型',
  `demand_title` varchar(100) NOT NULL COMMENT '需求标题',
  `demand_content` varchar(500) NOT NULL COMMENT '需求内容',
  `demand_img` varchar(100) DEFAULT NULL,
  `demand_remark` varchar(100) DEFAULT NULL COMMENT '需求备注',
  `demand_repay` float(8,1) DEFAULT NULL COMMENT '需求回报',
  `demand_state` varchar(1) NOT NULL DEFAULT '1' COMMENT '需求状态：1，发布中，2，处理中，3，已结束，4，中断，5，已撤销',
  `company_id` int(11) NOT NULL COMMENT '发起公司id',
  `create_time` varchar(20) NOT NULL COMMENT '创建时间',
  `last_update_time` varchar(20) NOT NULL COMMENT '上次更新时间',
  PRIMARY KEY (`demand_id`),
  KEY `FK_D_T` (`demand_type`),
  KEY `FK_D_C` (`company_id`),
  CONSTRAINT `FK_D_C` FOREIGN KEY (`company_id`) REFERENCES `company` (`company_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_D_T` FOREIGN KEY (`demand_type`) REFERENCES `dtype` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公司需求表';

-- ----------------------------
-- Records of demand
-- ----------------------------
INSERT INTO `demand` VALUES ('000b07c259df11e8955800ff551a87c2', '1', 'bbbb', 'bbbbbbbb', null, 'bbbbbbbbb', '20.0', '3', '0', '20180517223136', '20180518163231');
INSERT INTO `demand` VALUES ('021476665fda11e8955800ff551a87c2', '2', '车灯损坏', '车灯损坏车灯损坏车灯损坏', 'http://p6i3f0nn3.bkt.clouddn.com/FuSFs7xpBrx3nsuRGCz-6YQ67s76', '严重', '1000.0', '2', '0', '20180525131100', '20180528181831');
INSERT INTO `demand` VALUES ('0375c4513b8f11e8b4b7005056c00001', '1', '需求添加测试', '哈哈哈哈哈', null, 'oooooo', '400.0', '5', '0', '20180409084315', '20180514124541');
INSERT INTO `demand` VALUES ('0a684cb159d611e8955800ff551a87c2', '2', 'demand1', '...', null, '...', '10.0', '2', '0', '20180517212728', '20180517215228');
INSERT INTO `demand` VALUES ('14dd6a58363d11e8b4b7005056c00001', '1', '1111', '1111111111', null, '11111', '400.0', '2', '0', '20180402141414', '20180517215514');
INSERT INTO `demand` VALUES ('19aac39159e011e8955800ff551a87c2', '2', 'cccccc', 'cccccc', null, 'cccccc', '2.0', '3', '0', '20180517223929', '20180518163653');
INSERT INTO `demand` VALUES ('1ce8b7b659d611e8955800ff551a87c2', '2', 'demand2', '2222222', null, '22222222', '20.0', '2', '0', '20180517212759', '20180517213242');
INSERT INTO `demand` VALUES ('25b436a859d611e8955800ff551a87c2', '2', 'demand3', '33333', null, '3333333', '30.0', '5', '0', '20180517212814', '20180518163524');
INSERT INTO `demand` VALUES ('2909cd77626211e8b65000ff00a3fd29', '2', 'llllllllllllllll', 'lllllllllllllllllllllll', 'http://p6i3f0nn3.bkt.clouddn.com/FuSFs7xpBrx3nsuRGCz-6YQ67s76', 'lllllllllllllll', '200.0', '2', '0', '20180528183036', '20180528183054');
INSERT INTO `demand` VALUES ('460d727659dd11e8955800ff551a87c2', '2', '888888', '88888888', null, '888888888', '88.0', '5', '0', '20180517221915', '20180518172105');
INSERT INTO `demand` VALUES ('4718f25759df11e8955800ff551a87c2', '1', 'bbb', 'bbbbb', null, 'bbbbbb', '10.0', '2', '0', '20180517223335', '20180518172420');
INSERT INTO `demand` VALUES ('4ec5993b59da11e8955800ff551a87c2', '2', '5555', '55555555', null, '5555555555', '55.0', '2', '0', '20180517215801', '20180517215847');
INSERT INTO `demand` VALUES ('5153ab785d7b11e8955800ff551a87c2', '0', '图片测试', '11111', null, '111111', '1113.0', '2', '0', '20180522124809', '20180528182709');
INSERT INTO `demand` VALUES ('586a3a6659e211e8955800ff551a87c2', '1', 'ffffff', 'ffffff', null, 'ffffffff', '2.0', '2', '0', '20180517225533', '20180517225542');
INSERT INTO `demand` VALUES ('587a91aa626211e8b65000ff00a3fd29', '2', 'sssssssssssssss', 'ssssssssssssssss', 'http://p6i3f0nn3.bkt.clouddn.com/FuSFs7xpBrx3nsuRGCz-6YQ67s76', 'ssssssssssssss', '500.0', '2', '0', '20180528183156', '20180528183203');
INSERT INTO `demand` VALUES ('5ee4d6e327f411e882b8005056c00001', '2', '11111', '哦哦', null, '哈哈哈哈', '100.0', '3', '0', '20180315095833', '20180408130922');
INSERT INTO `demand` VALUES ('7c37485f59e211e8955800ff551a87c2', '0', 'gggggg', 'ggggg', null, 'gggggg', '1.0', '2', '0', '20180517225633', '20180517225646');
INSERT INTO `demand` VALUES ('836d5a135a7e11e8955800ff551a87c2', '2', 'qqq', 'qqqqq', null, 'qqqqq', '1.0', '3', '0', '20180518173327', '20180519113010');
INSERT INTO `demand` VALUES ('85f33272469611e8b84300ff551a87c2', '0', '44', '4444444', null, '444', '2.0', '2', '0', '20180423093449', '20180517211707');
INSERT INTO `demand` VALUES ('8fe26b8759dc11e8955800ff551a87c2', '2', '666', '666666', null, '66666666', '66.0', '2', '0', '20180517221409', '20180517221424');
INSERT INTO `demand` VALUES ('8ffcf2f15aab11e8955800ff551a87c2', '1', 'jjj', 'jjj', null, 'jjj', '3.0', '2', '0', '20180518225555', '20180518225612');
INSERT INTO `demand` VALUES ('9253488759de11e8955800ff551a87c2', '2', 'aaaa', 'aaaaaaa', null, 'aaaaaaaaaa', '10.0', '2', '0', '20180517222832', '20180517222839');
INSERT INTO `demand` VALUES ('9bf606865b1811e8955800ff551a87c2', '1', '6', '6', null, '6', '6.0', '4', '0', '20180519115630', '20180522114841');
INSERT INTO `demand` VALUES ('9ea7cd8d4b9411e8b84300ff551a87c2', '2', '紧急！！！！', '紧急情况，发动机异常', null, '', '1000.0', '3', '0', '20180429180346', '20180518163057');
INSERT INTO `demand` VALUES ('a1775af75d7b11e8955800ff551a87c2', '1', '图片需求2', '11111111111', 'http://p6i3f0nn3.bkt.clouddn.com/FlV9slvxUyLHNQ0oHdZJO4jVh0qt', '11111', '233.0', '2', '0', '20180522125023', '20180522134727');
INSERT INTO `demand` VALUES ('b50f876b4b9411e8b84300ff551a87c2', '1', '招聘有能力者', '111', null, '1', '2000.0', '2', '0', '20180429180424', '20180517203931');
INSERT INTO `demand` VALUES ('b51a755e4ba311e8b84300ff551a87c2', '2', '重大bug', '高薪寻求有能力者解决', null, '哈哈', '2000.0', '3', '0', '20180429195149', '20180514105734');
INSERT INTO `demand` VALUES ('b61209685b1511e8955800ff551a87c2', '1', 'llll', '11111', null, '111', '300.0', '2', '0', '20180519113546', '20180519113602');
INSERT INTO `demand` VALUES ('be924e7259dd11e8955800ff551a87c2', '2', '99999', '99999999999', null, '9999999999999', '99.0', '2', '0', '20180517222237', '20180517222245');
INSERT INTO `demand` VALUES ('c5d3c3b6626211e8b65000ff00a3fd29', '2', 'aaaaaaaa', 'aaaaaaaaaa', 'http://p6i3f0nn3.bkt.clouddn.com/FuSFs7xpBrx3nsuRGCz-6YQ67s76', 'aaaaaaaaa', '600.0', '2', '0', '20180528183459', '20180528183509');
INSERT INTO `demand` VALUES ('ca40ce4e3ac111e8b4b7005056c00001', '0', '555', '555', null, '555', '555.0', '3', '0', '20180408081413', '20180514125232');
INSERT INTO `demand` VALUES ('cb45aad159dc11e8955800ff551a87c2', '2', '7777', '7777777', null, '7777', '77.0', '2', '0', '20180517221549', '20180517221557');
INSERT INTO `demand` VALUES ('d074d312302d11e8b4b7005056c00001', '1', '99', '11', null, '11', '999.0', '3', '0', '20180327210956', '20180327164019');
INSERT INTO `demand` VALUES ('d558227759e111e8955800ff551a87c2', '1', 'eeeee', 'eeeee', null, 'eeeeee', '2.0', '2', '0', '20180517225153', '20180517225208');
INSERT INTO `demand` VALUES ('d591c13b303411e8b4b7005056c00001', '0', '88', '88', null, '8', '888.0', '4', '0', '20180328220011', '20180328220011');
INSERT INTO `demand` VALUES ('e62ded0e5b1111e8955800ff551a87c2', '2', '222', '222', null, '2222', '1.0', '3', '0', '20180519110828', '20180519111519');
INSERT INTO `demand` VALUES ('f22abbb643a511e8b84300ff551a87c2', '0', 'uuuu', 'uuu', null, 'uuu', '777.0', '5', '0', '20180419154741', '20180518162149');
INSERT INTO `demand` VALUES ('f539de4c59e011e8955800ff551a87c2', '2', 'ddd', 'ddddddd', null, 'ddddddd', '3.0', '3', '0', '20180517224537', '20180518161345');

-- ----------------------------
-- Table structure for `dtype`
-- ----------------------------
DROP TABLE IF EXISTS `dtype`;
CREATE TABLE `dtype` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '需求类型主键',
  `name` varchar(50) NOT NULL COMMENT '需求类型名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='需求类型表';

-- ----------------------------
-- Records of dtype
-- ----------------------------
INSERT INTO `dtype` VALUES ('0', '其他');
INSERT INTO `dtype` VALUES ('1', '招聘');
INSERT INTO `dtype` VALUES ('2', '故障处理');
INSERT INTO `dtype` VALUES ('3', '测试');

-- ----------------------------
-- Table structure for `lease`
-- ----------------------------
DROP TABLE IF EXISTS `lease`;
CREATE TABLE `lease` (
  `lease_id` varchar(50) NOT NULL COMMENT '租赁信息主键ID',
  `user_id` int(11) NOT NULL COMMENT '借者用户id',
  `tool_id` int(11) NOT NULL COMMENT '工具ID',
  `company_id` int(11) NOT NULL COMMENT '公司ID',
  `lease_mes` varchar(200) DEFAULT NULL COMMENT '租赁详情',
  `lease_repay` float(5,1) NOT NULL COMMENT '租赁金额，元/日',
  `lease_term` varchar(5) NOT NULL COMMENT '租赁天数',
  `lease_state` varchar(1) NOT NULL COMMENT '租赁状态：1，出租中：2，已结束',
  `total_money` float(5,1) DEFAULT NULL COMMENT '租赁总金额',
  `create_time` varchar(20) NOT NULL COMMENT '创建时间',
  `end_time` varchar(20) DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`lease_id`,`tool_id`),
  KEY `index_id` (`user_id`) USING HASH,
  KEY `index_tool` (`tool_id`) USING HASH,
  KEY `FK_L_C` (`company_id`),
  CONSTRAINT `FK_L_C` FOREIGN KEY (`company_id`) REFERENCES `company` (`company_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_L_T` FOREIGN KEY (`tool_id`) REFERENCES `tool` (`tool_id`) ON UPDATE CASCADE,
  CONSTRAINT `FK_L_U` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='租赁信息表';

-- ----------------------------
-- Records of lease
-- ----------------------------
INSERT INTO `lease` VALUES ('11111111111', '1', '1', '0', '租赁信息测试', '200.8', '3', '1', '602.4', '20180321142455', '20180321142455');
INSERT INTO `lease` VALUES ('2d7bce39626c11e8b65000ff00a3fd29', '4', '14', '0', '', '1.0', '1', '1', '1.0', '20180528194219', '20180529194219');
INSERT INTO `lease` VALUES ('45c7fcdb5b1511e8955800ff551a87c2', '27', '13', '0', '哈哈哈哈哈', '200.0', '3', '2', '600.0', '20180519113237', '20180522113237');
INSERT INTO `lease` VALUES ('734a5051572b11e8955800ff551a87c2', '2', '4', '0', '请租一个螺丝刀', '800.0', '3', '1', '2400.0', '20180514120116', '20180517120116');
INSERT INTO `lease` VALUES ('74f6cbbf3c6a11e8b4b7005056c00001', '1', '9', '0', '接口测试', '500.0', '3', '1', '1500.0', '20180410105405', null);
INSERT INTO `lease` VALUES ('934d6d395a9b11e8955800ff551a87c2', '4', '7', '0', '1', '1.0', '1', '2', '1.0', '20180518210129', '20180519210129');
INSERT INTO `lease` VALUES ('9809515233f611e8b4b7005056c00001', '1', '4', '0', '接口测试', '500.0', '3', '2', '1500.0', '20180330164441', '20180321142455');
INSERT INTO `lease` VALUES ('9de327a65d6f11e8955800ff551a87c2', '27', '13', '0', '哈哈哈', '300.0', '2', '1', '600.0', '20180522112423', '20180524112423');
INSERT INTO `lease` VALUES ('9f53edd05b1111e8955800ff551a87c2', '2', '7', '0', '请租一个螺丝刀', '800.0', '3', '1', '2400.0', '20180519110629', '20180522110629');
INSERT INTO `lease` VALUES ('a343b4d65d6e11e8955800ff551a87c2', '4', '12', '0', '1', '1.0', '1', '1', '1.0', '20180522111723', '20180523111723');
INSERT INTO `lease` VALUES ('bd97146a626d11e8b65000ff00a3fd29', '4', '15', '0', '解决车头损坏问题', '5.0', '30', '1', '150.0', '20180528195330', '20180627195330');
INSERT INTO `lease` VALUES ('e5e25e1a33e911e8b4b7005056c00001', '1', '7', '0', 'oooooooooo', '200.5', '3', '2', '601.5', '20180330151348', '20180321142455');
INSERT INTO `lease` VALUES ('f49f3ba05b1011e8955800ff551a87c2', '4', '6', '0', '1', '1.0', '1', '1', '1.0', '20180519110143', '20180520110143');
INSERT INTO `lease` VALUES ('f6fe11d83c6b11e8b4b7005056c00001', '1', '6', '0', '接口测试', '500.0', '3', '2', '1500.0', '20180410110452', '20180413110452');
INSERT INTO `lease` VALUES ('fffca9625a9c11e8955800ff551a87c2', '4', '13', '0', '1', '1.0', '1', '2', '1.0', '20180518211140', '20180519211140');

-- ----------------------------
-- Table structure for `lreq`
-- ----------------------------
DROP TABLE IF EXISTS `lreq`;
CREATE TABLE `lreq` (
  `l_req_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '租赁请求Id',
  `user_id` int(11) NOT NULL COMMENT '用户Id',
  `company_id` int(11) NOT NULL COMMENT '公司Id',
  `tool_id` int(11) NOT NULL COMMENT '工具Id',
  `l_req_money` float(5,1) DEFAULT '0.0' COMMENT '日租价格',
  `l_req_term` varchar(5) DEFAULT NULL COMMENT '租赁天数',
  `l_req_content` varchar(200) NOT NULL COMMENT '申请内容',
  `l_req_result` varchar(1) DEFAULT '0' COMMENT '处理结果 1,同意，2，拒绝',
  `l_req_create_time` varchar(20) NOT NULL COMMENT '申请时间',
  `l_req_handle_time` varchar(20) DEFAULT NULL COMMENT '处理时间',
  PRIMARY KEY (`l_req_id`),
  KEY `FK_LR_U` (`user_id`),
  KEY `FK_LR_C` (`company_id`),
  KEY `FK_LR_T` (`tool_id`),
  CONSTRAINT `FK_LR_C` FOREIGN KEY (`company_id`) REFERENCES `company` (`company_id`) ON UPDATE CASCADE,
  CONSTRAINT `FK_LR_T` FOREIGN KEY (`tool_id`) REFERENCES `tool` (`tool_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_LR_U` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lreq
-- ----------------------------
INSERT INTO `lreq` VALUES ('4', '1', '0', '7', '200.5', '3', 'oooooooooo', '2', '201801012132112', '20180519110629');
INSERT INTO `lreq` VALUES ('8', '1', '0', '4', '500.0', '3', '接口测试', '1', '20180330164436', '20180330164441');
INSERT INTO `lreq` VALUES ('17', '1', '0', '6', '500.0', '3', '接口测试', '2', '20180331195549', '20180519110143');
INSERT INTO `lreq` VALUES ('18', '1', '0', '6', '500.0', '3', '接口测试', '2', '20180331195835', '20180519110143');
INSERT INTO `lreq` VALUES ('19', '1', '0', '6', '500.0', '3', '接口测试', '2', '20180331200044', '20180519110143');
INSERT INTO `lreq` VALUES ('20', '1', '0', '6', '500.0', '3', '接口测试', '2', '20180331200428', '20180519110143');
INSERT INTO `lreq` VALUES ('21', '1', '0', '6', '500.0', '3', '接口测试', '2', '20180331202125', '20180519110143');
INSERT INTO `lreq` VALUES ('23', '1', '0', '6', '500.0', '3', '接口测试', '2', '20180331202640', '20180519110143');
INSERT INTO `lreq` VALUES ('24', '1', '0', '6', '500.0', '3', '接口测试', '2', '20180331202850', '20180519110143');
INSERT INTO `lreq` VALUES ('25', '1', '0', '6', '500.0', '3', '接口测试', '2', '20180331203049', '20180519110143');
INSERT INTO `lreq` VALUES ('26', '1', '0', '6', '500.0', '3', '接口测试', '2', '20180331203317', '20180519110143');
INSERT INTO `lreq` VALUES ('27', '1', '0', '6', '500.0', '3', '接口测试', '2', '20180331203339', '20180519110143');
INSERT INTO `lreq` VALUES ('28', '1', '0', '6', '500.0', '3', '接口测试', '2', '20180331203400', '20180519110143');
INSERT INTO `lreq` VALUES ('29', '1', '0', '9', '500.0', '3', '接口测试', '1', '20180402104533', '20180410105405');
INSERT INTO `lreq` VALUES ('30', '1', '0', '6', '500.0', '3', '接口测试', '2', '20180410110428', '20180519110143');
INSERT INTO `lreq` VALUES ('31', '2', '0', '4', '800.0', '3', '请租一个螺丝刀', '1', '20180514120104', '20180514120116');
INSERT INTO `lreq` VALUES ('32', '4', '0', '6', '1.0', '1', '1', '1', '20180518204811', '20180519110143');
INSERT INTO `lreq` VALUES ('33', '4', '0', '7', '1.0', '1', '1', '2', '20180518205523', '20180519110629');
INSERT INTO `lreq` VALUES ('34', '4', '0', '12', '1.0', '1', '1', '1', '20180518210559', '20180522111723');
INSERT INTO `lreq` VALUES ('35', '4', '0', '13', '1.0', '1', '1', '2', '20180518210820', '20180522112423');
INSERT INTO `lreq` VALUES ('36', '2', '0', '6', '800.0', '3', '请租一个螺丝刀', '2', '20180519110006', '20180519110143');
INSERT INTO `lreq` VALUES ('37', '2', '0', '7', '800.0', '3', '请租一个螺丝刀', '1', '20180519110527', '20180519110629');
INSERT INTO `lreq` VALUES ('38', '3', '0', '7', '800.0', '3', '请租一个螺丝刀', '2', '20180519110554', '20180519110629');
INSERT INTO `lreq` VALUES ('39', '27', '0', '13', '200.0', '3', '哈哈哈哈哈', '2', '20180519113225', '20180522112423');
INSERT INTO `lreq` VALUES ('40', '27', '0', '12', null, '', '', '2', '20180522110945', '20180522111723');
INSERT INTO `lreq` VALUES ('41', '27', '0', '13', null, '', '', '2', '20180522111806', '20180522112423');
INSERT INTO `lreq` VALUES ('42', '4', '0', '13', null, '', '', '2', '20180522111910', '20180522112423');
INSERT INTO `lreq` VALUES ('43', '27', '0', '13', '300.0', '2', '哈哈哈', '1', '20180522112254', '20180522112423');
INSERT INTO `lreq` VALUES ('44', '4', '0', '13', '522.0', '3', '', '2', '20180522112414', '20180522112423');
INSERT INTO `lreq` VALUES ('45', '4', '0', '14', '1.0', '1', '', '1', '20180528194114', '20180528194219');
INSERT INTO `lreq` VALUES ('46', '4', '0', '15', '5.0', '30', '解决车头损坏问题', '1', '20180528195307', '20180528195330');

-- ----------------------------
-- Table structure for `manage`
-- ----------------------------
DROP TABLE IF EXISTS `manage`;
CREATE TABLE `manage` (
  `manage_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理者id',
  `manage_name` varchar(20) NOT NULL COMMENT '管理者登录名',
  `manage_mc` varchar(20) DEFAULT NULL COMMENT '管理员姓名',
  `manage_pwd` varchar(30) NOT NULL COMMENT '管理者密码',
  `manage_phone` varchar(20) DEFAULT NULL COMMENT '管理者电话',
  `manage_email` varchar(20) DEFAULT NULL COMMENT '管理者邮箱',
  `manage_role` int(11) NOT NULL COMMENT '管理者角色',
  `create_time` varchar(20) NOT NULL COMMENT '创建时间',
  `last_login_time` varchar(20) DEFAULT NULL COMMENT '上次登录时间',
  `company_id` int(11) DEFAULT NULL COMMENT '公司ID',
  PRIMARY KEY (`manage_id`),
  KEY `FK_MA_C` (`company_id`) USING BTREE,
  KEY `FK_MA_R` (`manage_role`) USING BTREE,
  CONSTRAINT `FK_MA_C` FOREIGN KEY (`company_id`) REFERENCES `company` (`company_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_MA_R` FOREIGN KEY (`manage_role`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='管理员信息表';

-- ----------------------------
-- Records of manage
-- ----------------------------
INSERT INTO `manage` VALUES ('1', 'admin', '张三', '123456', '13980069478', '1101494689@qq.com', '0', '20170321142455', '20180528162648', '0');
INSERT INTO `manage` VALUES ('2', 'wyl', '吴永林', '111111', '13912783166', '11231223@qq.com', '1', '20180517171022', '20180517172231', '0');

-- ----------------------------
-- Table structure for `message`
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `mes_id` varchar(50) NOT NULL COMMENT '消息ID',
  `mes_content` varchar(30) DEFAULT NULL COMMENT '消息内容',
  `mes_state` varchar(1) NOT NULL COMMENT '消息状态：0，未查看，1，已查看',
  `company_id` int(11) NOT NULL COMMENT '消息发起公司ID',
  `create_time` varchar(20) NOT NULL COMMENT '创建消息时间',
  PRIMARY KEY (`mes_id`),
  KEY `FK_M_C` (`company_id`),
  CONSTRAINT `FK_M_C` FOREIGN KEY (`company_id`) REFERENCES `company` (`company_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息推送表';

-- ----------------------------
-- Records of message
-- ----------------------------

-- ----------------------------
-- Table structure for `orders`
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `order_id` varchar(50) NOT NULL COMMENT '订单ID',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `demand_id` varchar(50) NOT NULL COMMENT '需求ID',
  `company_id` int(11) NOT NULL COMMENT '公司ID',
  `order_title` varchar(100) NOT NULL,
  `order_state` varchar(1) NOT NULL DEFAULT '1' COMMENT '订单状态：1，处理中，2，已结束，3，订单中断',
  `order_score` float(2,1) DEFAULT NULL,
  `create_time` varchar(20) NOT NULL COMMENT '创建时间',
  `last_update_time` varchar(20) NOT NULL COMMENT '上次更新时间',
  PRIMARY KEY (`order_id`),
  KEY `index_user` (`user_id`) USING HASH,
  KEY `index_demand` (`demand_id`) USING HASH,
  KEY `FK_O_C` (`company_id`),
  CONSTRAINT `FK_O_C` FOREIGN KEY (`company_id`) REFERENCES `company` (`company_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_O_D` FOREIGN KEY (`demand_id`) REFERENCES `demand` (`demand_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_O_U` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('0193c9a759e111e8955800ff551a87c2', '4', 'f539de4c59e011e8955800ff551a87c2', '0', 'ddd', '2', '5.0', '20180517224558', '20180517224558');
INSERT INTO `orders` VALUES ('098108cb59df11e8955800ff551a87c2', '4', '000b07c259df11e8955800ff551a87c2', '0', 'bbbb', '2', null, '20180517223152', '20180517223152');
INSERT INTO `orders` VALUES ('1111111111111111', '1', '5ee4d6e327f411e882b8005056c00001', '1', '', '2', null, '20180327142455', '20180321142455');
INSERT INTO `orders` VALUES ('1b9a9947535311e8b84300ff551a87c2', '2', 'f22abbb643a511e8b84300ff551a87c2', '0', 'uuuu', '4', null, '20180509143500', '20180509143500');
INSERT INTO `orders` VALUES ('21312312312', '1', '14dd6a58363d11e8b4b7005056c00001', '1', '1', '2', null, '20180412121212', '20180413121212');
INSERT INTO `orders` VALUES ('2222222222222222', '2', 'd074d312302d11e8b4b7005056c00001', '0', '1', '2', '4.0', '20180312121212', '20180321121212');
INSERT INTO `orders` VALUES ('22eae2ce59e011e8955800ff551a87c2', '4', '19aac39159e011e8955800ff551a87c2', '0', 'cccccc', '2', '4.0', '20180517223944', '20180517223944');
INSERT INTO `orders` VALUES ('3360fdcb626211e8b65000ff00a3fd29', '4', '2909cd77626211e8b65000ff00a3fd29', '0', 'llllllllllllllll', '1', null, '20180528183054', '20180528183054');
INSERT INTO `orders` VALUES ('40d48b8a59d611e8955800ff551a87c2', '4', '25b436a859d611e8955800ff551a87c2', '0', 'demand3', '4', null, '20180517212859', '20180517212859');
INSERT INTO `orders` VALUES ('4dda0ec759dd11e8955800ff551a87c2', '4', '460d727659dd11e8955800ff551a87c2', '0', '888888', '4', null, '20180517221928', '20180517221928');
INSERT INTO `orders` VALUES ('5029ccc359df11e8955800ff551a87c2', '4', '4718f25759df11e8955800ff551a87c2', '0', 'bbb', '1', null, '20180517223351', '20180517223351');
INSERT INTO `orders` VALUES ('57aa9a6059cf11e8955800ff551a87c2', '4', 'b50f876b4b9411e8b84300ff551a87c2', '0', '招聘有能力者', '1', null, '20180517203931', '20180517203931');
INSERT INTO `orders` VALUES ('5cb9a516626211e8b65000ff00a3fd29', '4', '587a91aa626211e8b65000ff00a3fd29', '0', 'sssssssssssssss', '1', null, '20180528183203', '20180528183203');
INSERT INTO `orders` VALUES ('5de6d62559e211e8955800ff551a87c2', '4', '586a3a6659e211e8955800ff551a87c2', '0', 'ffffff', '1', null, '20180517225542', '20180517225542');
INSERT INTO `orders` VALUES ('6a9b779d59da11e8955800ff551a87c2', '4', '4ec5993b59da11e8955800ff551a87c2', '0', '5555', '1', null, '20180517215847', '20180517215847');
INSERT INTO `orders` VALUES ('71c3538e573211e8955800ff551a87c2', '1', 'ca40ce4e3ac111e8b4b7005056c00001', '0', '555', '2', null, '20180514125120', '20180514125120');
INSERT INTO `orders` VALUES ('73323343573111e8955800ff551a87c2', '2', '0375c4513b8f11e8b4b7005056c00001', '0', '需求添加测试', '4', null, '20180514124413', '20180514124413');
INSERT INTO `orders` VALUES ('83bb842959e211e8955800ff551a87c2', '4', '7c37485f59e211e8955800ff551a87c2', '0', 'gggggg', '1', null, '20180517225646', '20180517225646');
INSERT INTO `orders` VALUES ('885d5a4d59d911e8955800ff551a87c2', '4', '0a684cb159d611e8955800ff551a87c2', '0', 'demand1', '1', null, '20180517215228', '20180517215228');
INSERT INTO `orders` VALUES ('96b1712b59de11e8955800ff551a87c2', '4', '9253488759de11e8955800ff551a87c2', '0', 'aaaa', '1', null, '20180517222839', '20180517222839');
INSERT INTO `orders` VALUES ('98703a9e59d411e8955800ff551a87c2', '4', '85f33272469611e8b84300ff551a87c2', '0', '44', '1', null, '20180517211707', '20180517211707');
INSERT INTO `orders` VALUES ('98962e0d59dc11e8955800ff551a87c2', '4', '8fe26b8759dc11e8955800ff551a87c2', '0', '666', '1', null, '20180517221424', '20180517221424');
INSERT INTO `orders` VALUES ('99fc97455aab11e8955800ff551a87c2', '4', '8ffcf2f15aab11e8955800ff551a87c2', '0', 'jjj', '1', null, '20180518225612', '20180518225612');
INSERT INTO `orders` VALUES ('9a50f9915d8311e8955800ff551a87c2', '27', 'a1775af75d7b11e8955800ff551a87c2', '0', '图片需求2', '1', null, '20180522134727', '20180522134727');
INSERT INTO `orders` VALUES ('9e14eac9625711e8b65000ff00a3fd29', '4', '021476665fda11e8955800ff551a87c2', '0', '车灯损坏', '1', null, '20180528171508', '20180528171508');
INSERT INTO `orders` VALUES ('aaa9cb155b1411e8955800ff551a87c2', '27', '836d5a135a7e11e8955800ff551a87c2', '0', 'qqq', '2', '5.0', '20180519112817', '20180519112817');
INSERT INTO `orders` VALUES ('ac60ba295d6c11e8955800ff551a87c2', '27', '9bf606865b1811e8955800ff551a87c2', '0', '6', '3', null, '20180522110319', '20180522110319');
INSERT INTO `orders` VALUES ('ad2c9661626111e8b65000ff00a3fd29', '4', '5153ab785d7b11e8955800ff551a87c2', '0', '图片测试', '1', null, '20180528182709', '20180528182709');
INSERT INTO `orders` VALUES ('bb24e7a85b1211e8955800ff551a87c2', '26', 'e62ded0e5b1111e8955800ff551a87c2', '0', '222', '2', null, '20180519111426', '20180519111426');
INSERT INTO `orders` VALUES ('bfa109a35b1511e8955800ff551a87c2', '27', 'b61209685b1511e8955800ff551a87c2', '0', 'llll', '1', null, '20180519113602', '20180519113602');
INSERT INTO `orders` VALUES ('c38b641059dd11e8955800ff551a87c2', '4', 'be924e7259dd11e8955800ff551a87c2', '0', '99999', '1', null, '20180517222245', '20180517222245');
INSERT INTO `orders` VALUES ('c5af32ad59d611e8955800ff551a87c2', '4', '1ce8b7b659d611e8955800ff551a87c2', '0', 'demand2', '1', null, '20180517213242', '20180517213242');
INSERT INTO `orders` VALUES ('cb5d510c626211e8b65000ff00a3fd29', '4', 'c5d3c3b6626211e8b65000ff00a3fd29', '0', 'aaaaaaaa', '1', null, '20180528183509', '20180528183509');
INSERT INTO `orders` VALUES ('cffdc27759dc11e8955800ff551a87c2', '4', 'cb45aad159dc11e8955800ff551a87c2', '0', '7777', '1', null, '20180517221557', '20180517221557');
INSERT INTO `orders` VALUES ('d5b6cb704ba311e8b84300ff551a87c2', '2', 'b51a755e4ba311e8b84300ff551a87c2', '0', '重大bug', '2', '5.0', '20180429195243', '20180429195243');
INSERT INTO `orders` VALUES ('de81e9b059e111e8955800ff551a87c2', '4', 'd558227759e111e8955800ff551a87c2', '0', 'eeeee', '1', null, '20180517225208', '20180517225208');
INSERT INTO `orders` VALUES ('e5fec52a4b9511e8b84300ff551a87c2', '2', '9ea7cd8d4b9411e8b84300ff551a87c2', '0', '紧急！！！！', '2', '4.0', '20180429181255', '20180429181255');
INSERT INTO `orders` VALUES ('eb6ff54e59d911e8955800ff551a87c2', '4', '14dd6a58363d11e8b4b7005056c00001', '0', '1111', '1', null, '20180517215514', '20180517215514');

-- ----------------------------
-- Table structure for `oreq`
-- ----------------------------
DROP TABLE IF EXISTS `oreq`;
CREATE TABLE `oreq` (
  `oreq_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '请求id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `company_id` int(11) NOT NULL COMMENT '公司id',
  `order_id` varchar(50) NOT NULL COMMENT '订单id',
  `oreq_type` varchar(1) NOT NULL COMMENT '请求类型  1:请求订单结束  2：中断订单请求',
  `oreq_content` varchar(200) NOT NULL COMMENT '请求内容',
  `oreq_result` varchar(1) DEFAULT '0' COMMENT '处理结果 0未处理 1同意 2拒绝',
  `oreq_create_time` varchar(20) NOT NULL COMMENT '请求创建时间',
  `oreq_handle_time` varchar(20) DEFAULT NULL COMMENT '请求处理时间',
  PRIMARY KEY (`oreq_id`),
  KEY `FK_OR_C` (`company_id`),
  KEY `FK_OR_U` (`user_id`),
  KEY `FK_OR_O` (`order_id`),
  CONSTRAINT `FK_OR_C` FOREIGN KEY (`company_id`) REFERENCES `company` (`company_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_OR_O` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_OR_U` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oreq
-- ----------------------------
INSERT INTO `oreq` VALUES ('3', '2', '0', 'd5b6cb704ba311e8b84300ff551a87c2', '1', 'hehhehehehhe', '1', '20180429195425', '20180429195441');
INSERT INTO `oreq` VALUES ('5', '2', '0', '1b9a9947535311e8b84300ff551a87c2', '2', '无法处理', '2', '20180514104518', '20180514110739');
INSERT INTO `oreq` VALUES ('8', '2', '0', 'e5fec52a4b9511e8b84300ff551a87c2', '1', '订单结束', '1', '20180514112518', '20180518163057');
INSERT INTO `oreq` VALUES ('11', '4', '0', '0193c9a759e111e8955800ff551a87c2', '1', '订单：0193c9a759e111e8955800ff551a87c2,类型：结束请求', '1', '20180518154303', '20180518161345');
INSERT INTO `oreq` VALUES ('12', '4', '0', '098108cb59df11e8955800ff551a87c2', '1', '订单：098108cb59df11e8955800ff551a87c2,类型：结束请求', '1', '20180518155250', '20180518163231');
INSERT INTO `oreq` VALUES ('14', '4', '0', '40d48b8a59d611e8955800ff551a87c2', '2', '订单：40d48b8a59d611e8955800ff551a87c2,类型：中断请求', '1', '20180518160900', '20180518163524');
INSERT INTO `oreq` VALUES ('16', '4', '0', '5029ccc359df11e8955800ff551a87c2', '1', '订单：5029ccc359df11e8955800ff551a87c2,类型：结束请求', '2', '20180518172140', '20180518172202');
INSERT INTO `oreq` VALUES ('17', '4', '0', '5029ccc359df11e8955800ff551a87c2', '1', '订单：5029ccc359df11e8955800ff551a87c2,类型：结束请求', '2', '20180518172213', '20180518172308');
INSERT INTO `oreq` VALUES ('18', '4', '0', '5029ccc359df11e8955800ff551a87c2', '1', '订单：5029ccc359df11e8955800ff551a87c2,类型：结束请求', '2', '20180518172333', '20180518172414');
INSERT INTO `oreq` VALUES ('19', '4', '0', '5029ccc359df11e8955800ff551a87c2', '2', '订单：5029ccc359df11e8955800ff551a87c2,类型：中断请求', '2', '20180518172420', '20180518172439');
INSERT INTO `oreq` VALUES ('20', '26', '0', 'bb24e7a85b1211e8955800ff551a87c2', '1', '订单：bb24e7a85b1211e8955800ff551a87c2,类型：结束请求', '1', '20180519111445', '20180519111519');
INSERT INTO `oreq` VALUES ('21', '27', '0', 'aaa9cb155b1411e8955800ff551a87c2', '1', '订单：aaa9cb155b1411e8955800ff551a87c2,类型：结束请求', '2', '20180519112929', '20180519112956');
INSERT INTO `oreq` VALUES ('22', '27', '0', 'aaa9cb155b1411e8955800ff551a87c2', '1', '订单：aaa9cb155b1411e8955800ff551a87c2,类型：结束请求', '1', '20180519113003', '20180519113010');
INSERT INTO `oreq` VALUES ('24', '27', '0', 'ac60ba295d6c11e8955800ff551a87c2', '2', '订单：ac60ba295d6c11e8955800ff551a87c2,类型：中断请求', '0', '20180522114841', null);
INSERT INTO `oreq` VALUES ('25', '4', '0', 'c5af32ad59d611e8955800ff551a87c2', '1', '订单：c5af32ad59d611e8955800ff551a87c2,类型：结束请求', '0', '20180524210149', null);
INSERT INTO `oreq` VALUES ('26', '4', '0', '9e14eac9625711e8b65000ff00a3fd29', '2', '订单：9e14eac9625711e8b65000ff00a3fd29,类型：中断请求', '2', '20180528181656', '20180528181818');
INSERT INTO `oreq` VALUES ('27', '4', '0', '9e14eac9625711e8b65000ff00a3fd29', '2', '订单：9e14eac9625711e8b65000ff00a3fd29,类型：中断请求', '2', '20180528181831', '20180528181932');
INSERT INTO `oreq` VALUES ('28', '4', '0', '9e14eac9625711e8b65000ff00a3fd29', '1', '订单：9e14eac9625711e8b65000ff00a3fd29,类型：结束请求', '0', '20180528181940', null);

-- ----------------------------
-- Table structure for `recruit`
-- ----------------------------
DROP TABLE IF EXISTS `recruit`;
CREATE TABLE `recruit` (
  `recruit_id` int(11) NOT NULL AUTO_INCREMENT,
  `recruit_number` int(11) NOT NULL,
  `create_time` varchar(20) NOT NULL,
  `company_id` int(11) NOT NULL,
  `end_time` varchar(20) NOT NULL,
  `recruit_type` int(11) NOT NULL,
  `recruit_remark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`recruit_id`),
  KEY `FK_R_C` (`company_id`),
  KEY `FK_R_T` (`recruit_type`),
  CONSTRAINT `FK_R_C` FOREIGN KEY (`company_id`) REFERENCES `company` (`company_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_R_T` FOREIGN KEY (`recruit_type`) REFERENCES `rtype` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of recruit
-- ----------------------------
INSERT INTO `recruit` VALUES ('112', '2', '20180524120000', '0', '20180526080000', '1', '');
INSERT INTO `recruit` VALUES ('113', '2', '20180523120000', '0', '20180524080000', '2', '');
INSERT INTO `recruit` VALUES ('114', '2', '20180522120000', '0', '20180524080000', '1', '');

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色类型ID',
  `name` varchar(50) NOT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('0', '系统管理员');
INSERT INTO `role` VALUES ('1', '普通管理员');
INSERT INTO `role` VALUES ('2', '低级管理员');

-- ----------------------------
-- Table structure for `rreq`
-- ----------------------------
DROP TABLE IF EXISTS `rreq`;
CREATE TABLE `rreq` (
  `rreq_id` int(11) NOT NULL AUTO_INCREMENT,
  `recruit_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `company_id` int(11) NOT NULL,
  `rreq_state` int(1) NOT NULL,
  `rreq_content` varchar(500) DEFAULT NULL,
  `create_time` varchar(20) NOT NULL,
  PRIMARY KEY (`rreq_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rreq
-- ----------------------------
INSERT INTO `rreq` VALUES ('3', '112', '27', '0', '1', null, '20180522203304');

-- ----------------------------
-- Table structure for `rtype`
-- ----------------------------
DROP TABLE IF EXISTS `rtype`;
CREATE TABLE `rtype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rtype
-- ----------------------------
INSERT INTO `rtype` VALUES ('1', '初级技师');
INSERT INTO `rtype` VALUES ('2', '高级技师');
INSERT INTO `rtype` VALUES ('3', '特级技师');

-- ----------------------------
-- Table structure for `sign`
-- ----------------------------
DROP TABLE IF EXISTS `sign`;
CREATE TABLE `sign` (
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `company_id` int(11) NOT NULL COMMENT '公司id',
  `sign_time` varchar(5) NOT NULL COMMENT '签约年限',
  `sign_date` varchar(20) NOT NULL COMMENT '签约日期',
  `end_date` varchar(20) NOT NULL,
  PRIMARY KEY (`user_id`,`company_id`),
  KEY `Fk_S_C` (`company_id`),
  CONSTRAINT `FK_S_U` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE,
  CONSTRAINT `Fk_S_C` FOREIGN KEY (`company_id`) REFERENCES `company` (`company_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户与公司的签约表';

-- ----------------------------
-- Records of sign
-- ----------------------------
INSERT INTO `sign` VALUES ('1', '0', '1', '20180328142455', '20190328142455');
INSERT INTO `sign` VALUES ('2', '0', '2', '20180519102347', '20200519102347');
INSERT INTO `sign` VALUES ('4', '0', '2', '20180519110141', '20200519110141');
INSERT INTO `sign` VALUES ('26', '0', '2', '20180519111748', '20200519111748');

-- ----------------------------
-- Table structure for `sreq`
-- ----------------------------
DROP TABLE IF EXISTS `sreq`;
CREATE TABLE `sreq` (
  `sr_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '请求id',
  `user_id` int(11) NOT NULL,
  `company_id` int(11) NOT NULL,
  `sr_time` int(5) NOT NULL COMMENT '签约天数',
  `sr_state` int(11) DEFAULT '0' COMMENT '用户处理结果：0:未处理 1:同意 2:拒绝',
  `create_time` varchar(20) NOT NULL COMMENT '创建时间',
  `handle_time` varchar(20) DEFAULT NULL COMMENT '处理时间',
  PRIMARY KEY (`sr_id`),
  KEY `FK_SR_U` (`user_id`),
  KEY `FK_SR_C` (`company_id`),
  CONSTRAINT `FK_SR_C` FOREIGN KEY (`company_id`) REFERENCES `company` (`company_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_SR_U` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sreq
-- ----------------------------
INSERT INTO `sreq` VALUES ('2', '4', '0', '2', '1', '20180518233510', '20180519110141');
INSERT INTO `sreq` VALUES ('3', '2', '0', '2', '1', '20180519101335', '20180519102347');
INSERT INTO `sreq` VALUES ('4', '26', '0', '2', '1', '20180519111632', '20180519111748');
INSERT INTO `sreq` VALUES ('5', '27', '0', '1', '1', '20180519113649', '20180519113703');
INSERT INTO `sreq` VALUES ('6', '27', '0', '1', '1', '20180519113826', '20180519113831');
INSERT INTO `sreq` VALUES ('7', '27', '0', '1', '2', '20180519114137', '20180519114140');
INSERT INTO `sreq` VALUES ('8', '27', '0', '1', '2', '20180519122146', '20180522102722');
INSERT INTO `sreq` VALUES ('9', '27', '0', '2', '1', '20180522113510', '20180522113636');
INSERT INTO `sreq` VALUES ('10', '1', '0', '2', '0', '20180522201403', null);
INSERT INTO `sreq` VALUES ('11', '27', '0', '2', '2', '20180522203318', '20180522203444');
INSERT INTO `sreq` VALUES ('12', '27', '0', '2', '2', '20180522203841', '20180522204100');
INSERT INTO `sreq` VALUES ('13', '27', '0', '2', '2', '20180522204105', '20180522204255');
INSERT INTO `sreq` VALUES ('14', '27', '0', '1', '2', '20180522204310', '20180522204443');
INSERT INTO `sreq` VALUES ('15', '27', '0', '1', '2', '20180522204447', '20180522204541');
INSERT INTO `sreq` VALUES ('16', '27', '0', '1', '1', '20180522204545', '20180522204603');
INSERT INTO `sreq` VALUES ('17', '27', '0', '2', '0', '20180522204851', null);

-- ----------------------------
-- Table structure for `student`
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `stu_id` int(2) NOT NULL AUTO_INCREMENT,
  `stu_name` varchar(100) DEFAULT NULL,
  `pxh` int(2) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`stu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('2', '王五', '1', null, '2018-02-25 15:45:49');
INSERT INTO `student` VALUES ('4', '张三', '1', null, null);
INSERT INTO `student` VALUES ('5', '周七', '3', '2018-02-25 18:50:59', '2018-02-25 18:50:59');
INSERT INTO `student` VALUES ('6', '李牧', '1', '2018-02-25 18:55:19', '2018-02-25 18:56:17');
INSERT INTO `student` VALUES ('8', '龚培', '0', '2018-03-03 14:17:18', '2018-03-03 14:17:18');
INSERT INTO `student` VALUES ('9', '张三', '1', null, null);

-- ----------------------------
-- Table structure for `tool`
-- ----------------------------
DROP TABLE IF EXISTS `tool`;
CREATE TABLE `tool` (
  `tool_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '物品ID',
  `tool_type` int(11) NOT NULL COMMENT '物品类型',
  `tool_des` varchar(200) NOT NULL COMMENT '物品描述',
  `tool_img` varchar(100) DEFAULT NULL COMMENT '物品图片',
  `tool_state` varchar(1) DEFAULT '1' COMMENT '物品状态：1，闲置中，2：出租中',
  `company_id` int(11) NOT NULL COMMENT '所属公司id',
  `tool_name` varchar(50) NOT NULL COMMENT '工具名称',
  PRIMARY KEY (`tool_id`),
  KEY `index_company` (`company_id`) USING HASH,
  KEY `FK_T_T` (`tool_type`),
  CONSTRAINT `FK_T_C` FOREIGN KEY (`company_id`) REFERENCES `company` (`company_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_T_T` FOREIGN KEY (`tool_type`) REFERENCES `ttype` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='汽修厂工具信息表';

-- ----------------------------
-- Records of tool
-- ----------------------------
INSERT INTO `tool` VALUES ('1', '1', '修车工具', null, '2', '0', '扳手');
INSERT INTO `tool` VALUES ('4', '2', '哈哈', 'http://p6i3f0nn3.bkt.clouddn.com/FlKjxR5gtgyybD3rOg919sFHCczG', '2', '0', '螺丝刀');
INSERT INTO `tool` VALUES ('6', '3', '哦哦哦', 'http://p6i3f0nn3.bkt.clouddn.com/Fq2ocXZY7-GdzLzmYC4I-DN4TJln', '2', '0', '你在说啥');
INSERT INTO `tool` VALUES ('7', '3', '呵呵呵', null, '2', '0', '钢铲');
INSERT INTO `tool` VALUES ('8', '2', '99999999999', '', '1', '0', '999');
INSERT INTO `tool` VALUES ('9', '1', '', '', '2', '0', 'ppp');
INSERT INTO `tool` VALUES ('12', '2', '', 'http://p6i3f0nn3.bkt.clouddn.com/Fq2ocXZY7-GdzLzmYC4I-DN4TJln', '2', '0', '00000');
INSERT INTO `tool` VALUES ('13', '1', '扳手', 'http://p6i3f0nn3.bkt.clouddn.com/Fh5iHcVodL38N3dP1pj1wXfpFIB-', '2', '0', '钢扳手');
INSERT INTO `tool` VALUES ('14', '1', '螺丝刀', 'http://p6i3f0nn3.bkt.clouddn.com/FgXxS-C4-7WUmn06KOchBn-My5So', '2', '0', '螺丝刀');
INSERT INTO `tool` VALUES ('15', '0', '承重工具', 'http://p6i3f0nn3.bkt.clouddn.com/Fs327-QxQVccWJieb0gCXl9dUAy5', '2', '0', '千斤顶');

-- ----------------------------
-- Table structure for `ttype`
-- ----------------------------
DROP TABLE IF EXISTS `ttype`;
CREATE TABLE `ttype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='工具类型表';

-- ----------------------------
-- Records of ttype
-- ----------------------------
INSERT INTO `ttype` VALUES ('0', '其他');
INSERT INTO `ttype` VALUES ('1', '修理工具');
INSERT INTO `ttype` VALUES ('2', '检修工具');
INSERT INTO `ttype` VALUES ('3', '测试工具');
INSERT INTO `ttype` VALUES ('4', '搬运工具');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(20) NOT NULL COMMENT '用户账号名',
  `user_pwd` varchar(20) DEFAULT NULL COMMENT '用户口令',
  `user_mc` varchar(20) DEFAULT NULL COMMENT '用户姓名',
  `user_sex` varchar(5) DEFAULT NULL COMMENT '用户性别',
  `user_phone` varchar(20) DEFAULT NULL COMMENT '用户手机号',
  `user_email` varchar(20) DEFAULT NULL COMMENT '用户邮箱',
  `user_address` varchar(20) DEFAULT NULL COMMENT '用户家庭地址',
  `user_score` float(2,1) DEFAULT '5.0',
  `user_open_id` varchar(50) DEFAULT NULL COMMENT '微信开发id',
  `company_id` int(11) DEFAULT NULL COMMENT '公司ID',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `index_opneid` (`user_open_id`) USING HASH,
  KEY `FK_U_C` (`company_id`),
  CONSTRAINT `FK_U_C` FOREIGN KEY (`company_id`) REFERENCES `company` (`company_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'user', '123456', '李四', '男', '13080069479', '1101494689@qq.com', '西华大学', '4.7', null, null);
INSERT INTO `user` VALUES ('2', 'zhangsan', '123456', '张三', '男', null, null, null, '4.4', null, '0');
INSERT INTO `user` VALUES ('3', 'wangwei', '11111', '王伟', null, null, null, null, '5.0', null, '0');
INSERT INTO `user` VALUES ('4', '茂悠', null, '茂悠', null, null, null, null, '4.8', 'oclJG47MZN9hkBiVoQHW9Qx94OU0', '0');
INSERT INTO `user` VALUES ('25', '殷涵', null, '殷涵', null, null, null, null, '5.0', 'oclJG43CUPcI8fnQcwaIsCmbsFt0', null);
INSERT INTO `user` VALUES ('26', 'hugisd', null, 'hugisd', null, null, null, null, '5.0', 'oclJG4yp4J309bDZf-wjSvfeXvbc', '0');
INSERT INTO `user` VALUES ('27', '作死就会死', null, '作死就会死', null, null, null, null, '5.0', 'oclJG42fo4s8IcJWwdJvScB8ep8s', null);
INSERT INTO `user` VALUES ('28', 'hugisd', null, 'hugisd', null, null, null, null, '5.0', null, null);

-- ----------------------------
-- Table structure for `version`
-- ----------------------------
DROP TABLE IF EXISTS `version`;
CREATE TABLE `version` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(50) NOT NULL,
  `developer` varchar(50) NOT NULL,
  `uptime` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='系统版本表';

-- ----------------------------
-- Records of version
-- ----------------------------
INSERT INTO `version` VALUES ('1', 'version1.0', 'gp，lk', '20180410101010');
