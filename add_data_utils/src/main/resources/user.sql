CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `gender` char(2) DEFAULT NULL COMMENT '性别',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(255) DEFAULT NULL COMMENT '手机号码',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建者',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_user` bigint(20) DEFAULT NULL COMMENT '修改人',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `version` int(1) DEFAULT NULL COMMENT '版本',
  PRIMARY KEY (`id`),
  UNIQUE KEY `_id` (`id`) USING BTREE,
  KEY `_name` (`name`) USING BTREE,
  KEY `com` (`id`,`name`,`age`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=428630 DEFAULT CHARSET=utf8