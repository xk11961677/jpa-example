DROP TABLE IF EXISTS `client`;
CREATE TABLE `client` (
  `id` bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT 'id|niocoder|2019-03-07',
  `module_id` bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT 'module_id|niocoder|2019-03-07',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT 'client name|niocoder|2019-03-07',
  `address` varchar(100) NOT NULL DEFAULT '' COMMENT 'client address|niocoder|2019-03-07',
  `city` varchar(30) NOT NULL DEFAULT '' COMMENT 'client city|niocoder|2019-03-07',
  `telephone` varchar(20) NOT NULL DEFAULT '' COMMENT 'client telephone|niocoder|2019-03-07',
  PRIMARY KEY (`id`,module_id)
);

DROP TABLE IF EXISTS `card`;
CREATE TABLE `card` (
  `id` int(20) unsigned NOT NULL DEFAULT 0 COMMENT 'id|niocoder|2019-03-07',
  `module_id` bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT 'module_id|niocoder|2019-03-07',
  `number` varchar(20) NOT NULL DEFAULT '' COMMENT 'card number|niocoder|2019-03-07',
  `ccv` int(4) NOT NULL DEFAULT '0' COMMENT 'card ccv|niocoder|2019-03-07',
  `type` varchar(50) NOT NULL DEFAULT '' COMMENT 'card type|niocoder|2019-03-07',
  PRIMARY KEY (`id`,module_id)
);

DROP TABLE IF EXISTS `module`;
CREATE TABLE `module` (
    `id` int(20) unsigned NOT NULL DEFAULT 0 COMMENT '',
    `name` varchar(20) NOT NULL DEFAULT '' COMMENT '',
    PRIMARY KEY (`id`)
);