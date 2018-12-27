# Host: localhost  (Version: 5.5.53)
# Date: 2018-12-27 10:06:06
# Generator: MySQL-Front 5.3  (Build 4.234)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "change_code"
#

DROP TABLE IF EXISTS `change_code`;
CREATE TABLE `change_code` (
  `code` int(11) NOT NULL AUTO_INCREMENT,
  `Description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

#
# Data for table "change_code"
#

INSERT INTO `change_code` VALUES (0,'转系'),(1,'休学'),(2,'复学'),(3,'退学'),(4,'毕业');

#
# Structure for table "department"
#

DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `dID` int(11) NOT NULL AUTO_INCREMENT,
  `dDepName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`dID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

#
# Data for table "department"
#

INSERT INTO `department` VALUES (1,'计算机科学与工程学院');

#
# Structure for table "punish_levels"
#

DROP TABLE IF EXISTS `punish_levels`;
CREATE TABLE `punish_levels` (
  `code` int(11) NOT NULL AUTO_INCREMENT,
  `Description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

#
# Data for table "punish_levels"
#

INSERT INTO `punish_levels` VALUES (0,'警告'),(1,'严重警告'),(2,'记过'),(3,'记大过'),(4,'开除');

#
# Structure for table "reward_levels"
#

DROP TABLE IF EXISTS `reward_levels`;
CREATE TABLE `reward_levels` (
  `code` int(11) NOT NULL AUTO_INCREMENT,
  `Description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

#
# Data for table "reward_levels"
#

INSERT INTO `reward_levels` VALUES (0,'校特等奖学金'),(1,'校一等奖学金'),(2,'校二等奖学金'),(3,'校三等奖学金'),(4,'系一等奖学金'),(5,'系二等奖学金'),(6,'系三等奖学金');

#
# Structure for table "student"
#

DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `StudentID` int(11) NOT NULL COMMENT '学号（主关键字）',
  `sName` varchar(8) NOT NULL COMMENT '姓名',
  `sSex` varchar(4) DEFAULT NULL COMMENT '性别',
  `Class` int(11) DEFAULT '0' COMMENT '班级编号（外部关键字）',
  `Department` int(11) DEFAULT '0' COMMENT '所属院系编号（外部关键字）',
  `sBirthday` date NOT NULL COMMENT '生日',
  `sNativePlace` varchar(20) DEFAULT NULL COMMENT '籍贯',
  PRIMARY KEY (`StudentID`),
  KEY `Class` (`Class`),
  KEY `Department` (`Department`),
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`Class`) REFERENCES `class` (`cID`),
  CONSTRAINT `student_ibfk_2` FOREIGN KEY (`Department`) REFERENCES `department` (`dID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "student"
#

INSERT INTO `student` VALUES (1705010112,'李平','男',1711,1,'1997-08-17','湖南'),(1705010223,'王丫','女',1712,1,'1997-08-17','湖南'),(1705010313,'钟信','男',1713,1,'1997-08-18','湖南'),(1705010433,'木林','女',1714,1,'1997-08-17','湖南'),(1705010616,'丽思','女',1716,1,'1997-08-17','湖南'),(1805010518,'李文','男',1715,1,'1997-08-17','湖南'),(1805020127,'铮镐铧','男',1821,1,'1999-11-07','广东'),(1805020134,'锻铷鉣','女',1821,1,'1999-03-25','天津'),(1805020329,'锺釪铱','女',1823,1,'1999-05-19','湖南'),(1805030330,'鋄镆','女',1833,1,'1999-12-22','黑龙江'),(1805050122,'錋镓鉥','男',1851,1,'2000-02-01','湖南'),(1805050123,'镗镐锗','男',1851,1,'1999-08-01','湖南'),(1805050226,'鍖镯','男',1852,1,'1999-12-20','内蒙古');

#
# Structure for table "class"
#

DROP TABLE IF EXISTS `class`;
CREATE TABLE `class` (
  `cID` int(11) NOT NULL AUTO_INCREMENT,
  `cClassName` varchar(255) DEFAULT NULL,
  `Monitor` int(11) DEFAULT NULL COMMENT '班长学号（外部关键字）',
  PRIMARY KEY (`cID`),
  KEY `Monitor` (`Monitor`),
  CONSTRAINT `class_ibfk_1` FOREIGN KEY (`Monitor`) REFERENCES `student` (`StudentID`)
) ENGINE=InnoDB AUTO_INCREMENT=1853 DEFAULT CHARSET=utf8;

#
# Data for table "class"
#

INSERT INTO `class` VALUES (1711,'17计算机科学与技术一班',1705010111),(1712,'17计算机科学与技术二班',1705010211),(1713,'17计算机科学与技术三班',1705010311),(1714,'17计算机科学与技术四班',1705010411),(1715,'17计算机科学与技术五班',1705010511),(1716,'17计算机科学与技术六班',1705010611),(1721,'17网络工程一班',1705020111),(1722,'17网络工程二班',1705020211),(1723,'17网络工程三班',1705020311),(1731,'17信息安全一班',1705030111),(1732,'17信息安全二班',1705030211),(1733,'17信息安全三班',1705030311),(1741,'17物联网工程一班',1705040111),(1742,'17物联网工程二班',1705040211),(1751,'17软件工程一班',1705050111),(1752,'17软件工程二班',1705050211),(1811,'18计算机科学与技术一班',1805010111),(1812,'18计算机科学与技术二班',1805010211),(1813,'18计算机科学与技术三班',1805010311),(1814,'18计算机科学与技术四班',1805010411),(1815,'18计算机科学与技术五班',1805010511),(1816,'18计算机科学与技术六班',1805010611),(1821,'18网络工程一班',1805020111),(1822,'18网络工程二班',1805020211),(1823,'18网络工程三班',1805020311),(1831,'18信息安全一班',1805030111),(1832,'18信息安全二班',1805030211),(1833,'18信息安全三班',1805030311),(1841,'18物联网工程一班',1805040111),(1842,'18物联网工程二班',1805040211),(1851,'18软件工程一班',1805050111),(1852,'18软件工程二班',1805040211);

#
# Structure for table "punishment"
#

DROP TABLE IF EXISTS `punishment`;
CREATE TABLE `punishment` (
  `pNo` int(11) NOT NULL AUTO_INCREMENT,
  `StudentID` int(11) NOT NULL DEFAULT '0' COMMENT '学号（外部关键字）',
  `Levels` int(11) DEFAULT '0' COMMENT '级别代码（外部关键字）',
  `pRecTime` varchar(255) DEFAULT NULL COMMENT '记录时间',
  `pEnable` varchar(255) DEFAULT NULL COMMENT '是否生效（T、F）',
  `pDescription` varchar(255) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`pNo`),
  KEY `StudentID` (`StudentID`),
  KEY `Levels` (`Levels`),
  CONSTRAINT `punishment_ibfk_1` FOREIGN KEY (`StudentID`) REFERENCES `student` (`StudentID`),
  CONSTRAINT `punishment_ibfk_2` FOREIGN KEY (`Levels`) REFERENCES `punish_levels` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

#
# Data for table "punishment"
#

INSERT INTO `punishment` VALUES (1,1805050226,0,'2018-12-26','是','啊啊'),(2,1805050122,0,'2018-11-11','是','警告'),(3,1805050123,2,'2018-12-27','是','（');

#
# Structure for table "reward"
#

DROP TABLE IF EXISTS `reward`;
CREATE TABLE `reward` (
  `rid` int(11) NOT NULL AUTO_INCREMENT COMMENT '记录号（主关键字）',
  `StudentID` int(11) DEFAULT NULL COMMENT '学号（外部关键字）',
  `Levels` int(11) DEFAULT '0' COMMENT '级别代码（外部关键字）',
  `rRecTime` date NOT NULL DEFAULT '0000-00-00' COMMENT '记录时间',
  `rDescription` varchar(255) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`rid`),
  KEY `StudentID` (`StudentID`),
  KEY `Levels` (`Levels`),
  CONSTRAINT `reward_ibfk_1` FOREIGN KEY (`StudentID`) REFERENCES `student` (`StudentID`),
  CONSTRAINT `reward_ibfk_2` FOREIGN KEY (`Levels`) REFERENCES `reward_levels` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

#
# Data for table "reward"
#

INSERT INTO `reward` VALUES (1,1805020127,0,'2018-12-22','花样作死比赛一等奖'),(2,1805050226,0,'2018-12-26','花样作死比赛二等奖'),(6,1705010433,0,'2018-12-22','啊啊啊啊'),(7,1805010518,1,'2015-12-11','11'),(8,1805010518,0,'2011-11-11','是');

#
# Structure for table "changexj"
#

DROP TABLE IF EXISTS `changexj`;
CREATE TABLE `changexj` (
  `cID` int(11) NOT NULL AUTO_INCREMENT,
  `StudentID` int(11) NOT NULL,
  `ChangeID` int(11) NOT NULL,
  `cRecTime` date NOT NULL,
  `cDescription` varchar(255) NOT NULL,
  PRIMARY KEY (`cID`),
  KEY `StudentID` (`StudentID`),
  KEY `changexj_ibfk_2` (`ChangeID`),
  CONSTRAINT `changexj_ibfk_1` FOREIGN KEY (`StudentID`) REFERENCES `student` (`StudentID`),
  CONSTRAINT `changexj_ibfk_2` FOREIGN KEY (`ChangeID`) REFERENCES `change_code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "changexj"
#

INSERT INTO `changexj` VALUES (1,1805050226,3,'2018-12-28','作死');
