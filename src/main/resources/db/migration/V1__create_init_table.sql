CREATE TABLE `account_userinfo`
(
    `id`          bigint(20) unsigned  AUTO_INCREMENT,
    `username`    varchar(64)         NOT NULL COMMENT 'user name',
    `password`    varchar(64)         NOT NULL,
    `salt`        varchar(50)         NOT NULL,
    `create_time` datetime            NOT NULL,
    `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    `roles`       set ('normal'),
    `perms`       set ('normal'),
    `status`      tinyint DEFAULT 1 ,
    PRIMARY KEY `pk_id` (`id`),
    UNIQUE KEY `uk_username` (`username`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

