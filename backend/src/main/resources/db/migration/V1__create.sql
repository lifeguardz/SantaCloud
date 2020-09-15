USE santacloud;

DROP TABLE IF EXISTS `family`;
CREATE TABLE `family` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `status` varchar(255) DEFAULT NULL,
  `special` tinyint(1) DEFAULT NULL,
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `street` varchar(255) DEFAULT NULL,
  `postcode` int DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `phonenumbers` text,
  `email` varchar(255) DEFAULT NULL,
  `emailnotification` tinyint(1) DEFAULT NULL,
  `families` int DEFAULT NULL,
  `children` int DEFAULT NULL,
  `availabledays` varchar(255) DEFAULT NULL,
  `final_day` varchar(255) DEFAULT NULL,
  `descriptionoffice` text,
  `descriptionteam` text,
  `emailnews` tinyint(1) DEFAULT NULL,
  `teamid` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

LOCK TABLES `family` WRITE;
UNLOCK TABLES;


DROP TABLE IF EXISTS `helper`;
CREATE TABLE `helper` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `emailnotification` tinyint(1) DEFAULT NULL,
  `description` text,
  `creationdate` datetime NOT NULL,
  `role` smallint NOT NULL,
  `status` smallint NOT NULL,
  `street` varchar(255) DEFAULT NULL,
  `postcode` int DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `birthyear` int DEFAULT NULL,
  `phonenumbers` text,
  `fieldingdays` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

LOCK TABLES `helper` WRITE;
UNLOCK TABLES;

DROP TABLE IF EXISTS `team`;
CREATE TABLE `team` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `number` int NOT NULL,
  `helper_id` bigint DEFAULT NULL,
  `family_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `family_id` (`family_id`),
  KEY `helper_id` (`helper_id`),
  CONSTRAINT `family_id` FOREIGN KEY (`family_id`) REFERENCES `family` (`id`),
  CONSTRAINT `helper_id` FOREIGN KEY (`helper_id`) REFERENCES `helper` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `team` WRITE;
UNLOCK TABLES;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `helper_Idu` bigint DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `helperId_UNIQUE` (`helper_Idu`),
  CONSTRAINT `helper_Idu` FOREIGN KEY (`helper_Idu`) REFERENCES `helper` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

LOCK TABLES `user` WRITE;
UNLOCK TABLES;
