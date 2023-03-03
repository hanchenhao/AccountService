
CREATE TABLE `account_userinfo`
(
    `id`          bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `username`    varchar(64)         NOT NULL COMMENT 'user name',
    `password`    varchar(64)         NOT NULL,
    `salt`        varchar(50)         NOT NULL,
    `create_time` datetime            NOT NULL,
    `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY `pk_id` (`id`),
    UNIQUE KEY `uk_username` (`username`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

insert into account_userinfo(id, username, password, salt, create_time, update_time)
values (1, 'hanchenhao','123456','123',now(),now());