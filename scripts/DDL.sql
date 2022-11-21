CREATE DATABASE selab_auction CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

CREATE TABLE `auction`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `created_at`  datetime DEFAULT NULL,
    `modified_at` datetime DEFAULT NULL,
    `item_price`  bigint NOT NULL,
    `item_id`     bigint NOT NULL,
    `member_id`   bigint NOT NULL,
    PRIMARY KEY (`id`),
    KEY           `auction_index` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE `auction_buy_comment`
(
    `id`             bigint NOT NULL AUTO_INCREMENT,
    `created_at`     datetime                                DEFAULT NULL,
    `modified_at`    datetime                                DEFAULT NULL,
    `buy_member_id`  bigint                                  DEFAULT NULL,
    `comment`        varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `grade`          double                                  DEFAULT NULL,
    `item_id`        bigint                                  DEFAULT NULL,
    `sale_member_id` bigint                                  DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY              `auction_buy_comment_index` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `auction_sale_comment`
(
    `id`             bigint NOT NULL AUTO_INCREMENT,
    `created_at`     datetime                                DEFAULT NULL,
    `modified_at`    datetime                                DEFAULT NULL,
    `buy_member_id`  bigint                                  DEFAULT NULL,
    `comment`        varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `grade`          double                                  DEFAULT NULL,
    `item_id`        bigint                                  DEFAULT NULL,
    `sale_member_id` bigint                                  DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY              `auction_sale_comment_index` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `board`
(
    `id`          bigint                                  NOT NULL AUTO_INCREMENT,
    `created_at`  datetime                                DEFAULT NULL,
    `modified_at` datetime                                DEFAULT NULL,
    `category`    varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
    `content`     varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `member_id`   bigint                                  NOT NULL,
    `title`       varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `board_comment`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `created_at`  datetime                                DEFAULT NULL,
    `modified_at` datetime                                DEFAULT NULL,
    `board_id`    bigint NOT NULL,
    `comment`     varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `member_id`   bigint NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `buy_history`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `created_at`  datetime DEFAULT NULL,
    `modified_at` datetime DEFAULT NULL,
    `item_id`     bigint   DEFAULT NULL,
    `item_price`  bigint   DEFAULT NULL,
    `member_id`   bigint   DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY           `buy_history_index` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `item`
(
    `id`                bigint                                  NOT NULL AUTO_INCREMENT,
    `created_at`        datetime                                DEFAULT NULL,
    `modified_at`       datetime                                DEFAULT NULL,
    `auction_period`    int                                     DEFAULT NULL,
    `category`          varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `item_description`  varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `immediately_price` bigint                                  NOT NULL,
    `member_id`         bigint                                  NOT NULL,
    `item_name`         varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
    `item_price`        bigint                                  NOT NULL,
    `state`             varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `item_category`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `created_at`  datetime                                DEFAULT NULL,
    `modified_at` datetime                                DEFAULT NULL,
    `category`    varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `member`
(
    `id`          bigint                                  NOT NULL AUTO_INCREMENT,
    `created_at`  datetime DEFAULT NULL,
    `modified_at` datetime DEFAULT NULL,
    `address`     varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
    `email`       varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
    `grade`       double   DEFAULT NULL,
    `nickname`    varchar(30) COLLATE utf8mb4_general_ci  NOT NULL,
    `password`    varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
    `phone`       varchar(20) COLLATE utf8mb4_general_ci  NOT NULL,
    `role`        int      DEFAULT NULL,
    `sex`         varchar(20) COLLATE utf8mb4_general_ci  NOT NULL,
    `state`       int                                     NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_mbmcqelty0fbrvxp1q58dn57t` (`email`),
    UNIQUE KEY `UK_hh9kg6jti4n1eoiertn2k6qsc` (`nickname`),
    KEY           `member_index` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `sale_history`
(
    `id`            bigint NOT NULL AUTO_INCREMENT,
    `created_at`    datetime DEFAULT NULL,
    `modified_at`   datetime DEFAULT NULL,
    `buy_member_id` bigint   DEFAULT NULL,
    `item_id`       bigint   DEFAULT NULL,
    `item_price`    bigint   DEFAULT NULL,
    `member_id`     bigint   DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY             `sale_history_index` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
