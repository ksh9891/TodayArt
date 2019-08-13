# Todayart v1.52
/*
[v1.3]
    - 모든 테이블에 default now() -> default current_timestamp로 변경
    - order -> ordered 문자열 변경
    - member_role, role : 테이블 삭제
    - member : authority 컬럼 삭제, role 컬럼 추가
    - ordered_detail : 컬럼 추가
        > `productName` varchar(255) not null
        > `product_size` varchar(255) not null
        > `shipping_fee` int not null
    - payment : 컬럼추가
        > `refund_comment` varchar(255) comment "환불 사유"
    - shipping_items : 테이블 추가
    - shipping : 컬럼 추가
        > `tracking_number` varchar(255) null
        > `shipping`
[v1.4]
    - 모든 테이블 primary key 지정
    - 복합키 삭제, auto_increament 로 대체
    - shipping_items 테이블 추가
    - update_log 테이블 추가
    - ordered_detail track_number 컬럼 추가
    - ordered_datail status 옵션 추가
[v1.5]
    - 테이블 간 관계 연결 완료
[v1.51]
	  - 더미데이터 추가
      > 몇 칼럼 자료형을 enum 으로 변경
[v1.52]
	  - mileage_invoice : 컬럼추가
        > `is_delete` INT(11) NOT NULL DEFAULT 0 COMMENT '0: 미삭제, 1: 삭제'
      - 더미데이터 추가
*/

-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema todayart
-- -----------------------------------------------------

DROP DATABASE IF EXISTS `todayart`;
 
CREATE DATABASE IF NOT EXISTS `todayart`
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;
 
CREATE USER IF NOT EXISTS 'art_master'@'%' IDENTIFIED BY 'art12#$';
 
GRANT ALL PRIVILEGES ON todayart.* To 'art_master'@'%';
 
USE `todayart`;

-- -----------------------------------------------------
-- Table `todayart`.`member`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `todayart`.`member` (
  `member_id` INT(11) NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `nickname` VARCHAR(255) NOT NULL,
  `username` VARCHAR(255) NULL DEFAULT NULL,
  `reg_dated` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `phone` VARCHAR(255) NULL DEFAULT NULL,
  `role` ENUM('ROLE_ADMIN', 'ROLE_CUSTOMER', 'ROLE_ARTIST') NOT NULL DEFAULT 'ROLE_CUSTOMER',
  `last_connect_dated` DATETIME NULL DEFAULT NULL,
  `expired` INT(11) NOT NULL DEFAULT '0' COMMENT '0: 미탈퇴, 1: 탈퇴',
  `expired_dated` DATETIME NULL DEFAULT NULL,
  `email_checked` INT(11) NOT NULL DEFAULT '0' COMMENT '0: 미인증, 1: 인증',
  PRIMARY KEY (`member_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 10002
DEFAULT CHARACTER SET = utf8;
 
INSERT INTO member VALUE(1, "admin@admin.com", "admin", "admin", "admin", now(), "01012345678", "ROLE_ADMIN", now(), 0, null, 1);
INSERT INTO member VALUE(3000, "member@member.com", "member", "member", "member", now(), "01012345678", "ROLE_CUSTOMER", now(), 0, null, 1);
INSERT INTO member VALUE(10001, "artist@artist.com", "artist", "artist", "artist", now(), "01012345678", "ROLE_ARTIST", now(), 0, null, 1);


-- -----------------------------------------------------
-- Table `todayart`.`artist_level`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `todayart`.`artist_level` (
  `artist_level_id` INT(11) NOT NULL AUTO_INCREMENT,
  `level` VARCHAR(10) NOT NULL DEFAULT 'Bronze',
  `commission` INT(11) NOT NULL DEFAULT '15',
  PRIMARY KEY (`artist_level_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

INSERT INTO `todayart`.`artist_level` VALUE(1, 'Bronze', 15);

-- -----------------------------------------------------
-- Table `todayart`.`file`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `todayart`.`file` (
  `file_id` INT(11) NOT NULL AUTO_INCREMENT,
  `file_originname` VARCHAR(255) NOT NULL,
  `file_name` VARCHAR(255) NOT NULL,
  `file_size` INT(11) NOT NULL DEFAULT '0',
  `file_type` VARCHAR(255) NULL DEFAULT NULL,
  `file_reg_dated` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `file_ip` VARCHAR(255) NULL DEFAULT NULL,
  `member_id` INT(11) NOT NULL,
  PRIMARY KEY (`file_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

INSERT INTO `todayart`.`file` VALUE(1, 'a.jpg', '그림1', '1024', 'image', now(), '127.0.0.1', 10001);

-- -----------------------------------------------------
-- Table `todayart`.`artist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `todayart`.`artist` (
  `artist_id` INT(11) NOT NULL AUTO_INCREMENT,
  `member_id` INT(11) NOT NULL,
  `artist_desc` TEXT NOT NULL,
  `profile_id` INT(11) NULL DEFAULT NULL,
  `adm_product_desc` TEXT NOT NULL,
  `adm_check` INT(1) NOT NULL DEFAULT '0' COMMENT '0: 미승인, 1: 승인',
  `artist_level_id` INT(11) NOT NULL,
  PRIMARY KEY (`artist_id`),
  INDEX `member_id_idx` (`member_id` ASC) VISIBLE,
  INDEX `fk_artist_artistLevel_idx` (`artist_level_id` ASC) VISIBLE,
  INDEX `fk_artist_file_idx` (`profile_id` ASC) VISIBLE,
  CONSTRAINT `fk_artist_member`
    FOREIGN KEY (`member_id`)
    REFERENCES `todayart`.`member` (`member_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_artist_artistLevel`
    FOREIGN KEY (`artist_level_id`)
    REFERENCES `todayart`.`artist_level` (`artist_level_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_artist_file`
    FOREIGN KEY (`profile_id`)
    REFERENCES `todayart`.`file` (`file_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

INSERT INTO `todayart`.`artist` VALUE(1, 10001, "나는야 작가", NULL, "비밀스러운 사진", 1, 1);

-- -----------------------------------------------------
-- Table `todayart`.`account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `todayart`.`account` (
  `account_id` INT NOT NULL AUTO_INCREMENT,
  `artist_id` INT NOT NULL,
  `account_number` VARCHAR(255) NOT NULL,
  `bank` VARCHAR(255) NOT NULL,
  `main_account` INT(11) NOT NULL DEFAULT '0' COMMENT '0:일반계좌, 1:대표계좌',
  PRIMARY KEY (`account_id`),
  INDEX `fk_account_artist_idx` (`artist_id` ASC) VISIBLE,
  CONSTRAINT `fk_account_artist`
    FOREIGN KEY (`artist_id`)
    REFERENCES `todayart`.`artist` (`artist_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `todayart`.`board_category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `todayart`.`board_category` (
  `board_id` INT(11) NOT NULL AUTO_INCREMENT,
  `board_name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`board_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `todayart`.`article`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `todayart`.`article` (
  `article_id` INT(11) NOT NULL AUTO_INCREMENT,
  `board_id` INT(11) NOT NULL,
  `member_id` INT(11) NOT NULL,
  `write_dated` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_dated` DATETIME NULL DEFAULT NULL,
  `title` VARCHAR(255) NOT NULL,
  `content` TEXT NOT NULL,
  `group` INT(11) NULL DEFAULT NULL,
  `depth` INT(11) NOT NULL DEFAULT '0',
  `reply_order` INT(11) NULL DEFAULT NULL,
  `is_deleted` INT(11) NOT NULL DEFAULT '0' COMMENT '0:미삭제, 1:삭제',
  `delete_dated` DATETIME NULL DEFAULT NULL,
  `is_hidden` INT(11) NOT NULL DEFAULT '0' COMMENT '0:공개글, 1:비밀글',
  `password` VARCHAR(255) NULL DEFAULT NULL,
  `is_reply` INT(11) NOT NULL DEFAULT '0' COMMENT '0:미답변, 1:답변',
  `views` INT(11) NOT NULL DEFAULT '0',
  `product_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`article_id`),
  INDEX `fk_article_boardCategory_idx` (`board_id` ASC) VISIBLE,
  INDEX `fk_article_member_idx` (`member_id` ASC) VISIBLE,
  CONSTRAINT `fk_article_boardCategory`
    FOREIGN KEY (`board_id`)
    REFERENCES `todayart`.`board_category` (`board_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_article_member`
    FOREIGN KEY (`member_id`)
    REFERENCES `todayart`.`member` (`member_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `todayart`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `todayart`.`category` (
  `categoryId` INT(11) NOT NULL AUTO_INCREMENT,
  `category_name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`categoryId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

INSERT INTO `todayart`.`category` VALUE(1, '그림');

-- -----------------------------------------------------
-- Table `todayart`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `todayart`.`product` (
  `product_id` INT(11) NOT NULL AUTO_INCREMENT,
  `artist_id` INT(11) NOT NULL,
  `categoryId` INT(11) NOT NULL,
  `productName` VARCHAR(255) NOT NULL,
  `product_size` VARCHAR(255) NOT NULL,
  `product_price` INT(11) NOT NULL,
  `thumbnail_id` INT(11) NOT NULL,
  `is_delete` INT(11) NOT NULL DEFAULT '0' COMMENT '0: 미삭제, 1:삭제',
  `delete_dated` DATETIME NULL DEFAULT NULL,
  `enroll_dated` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_dated` DATETIME NULL DEFAULT NULL,
  `is_sold_out` INT(11) NOT NULL DEFAULT '0' COMMENT '0: 판매가능, 1: 품절',
  `remain` INT(11) NOT NULL DEFAULT '1',
  `count_cart` INT(11) NOT NULL DEFAULT '0',
  `count_wishlist` INT(11) NOT NULL DEFAULT '0',
  `shipping_fee` INT(11) NOT NULL,
  PRIMARY KEY (`product_id`),
  INDEX `fk_product_artist_idx` (`artist_id` ASC) VISIBLE,
  INDEX `fk_product_category_idx` (`categoryId` ASC) VISIBLE,
  INDEX `fk_product_file_idx` (`thumbnail_id` ASC) VISIBLE,
  CONSTRAINT `fk_product_artist`
    FOREIGN KEY (`artist_id`)
    REFERENCES `todayart`.`artist` (`artist_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_product_category`
    FOREIGN KEY (`categoryId`)
    REFERENCES `todayart`.`category` (`categoryId`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_product_file`
    FOREIGN KEY (`thumbnail_id`)
    REFERENCES `todayart`.`file` (`file_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

INSERT INTO `todayart`.`product` VALUE(1, 1, 1, '멋있는 그림', 1024, 10000000, 1, 0, null, now(), null, 0, 50, 1, 0, 2500);

-- -----------------------------------------------------
-- Table `todayart`.`cart`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `todayart`.`cart` (
  `cart_id` INT(11) NOT NULL AUTO_INCREMENT,
  `member_id` INT(11) NOT NULL,
  `product_id` INT(11) NOT NULL,
  `enroll_dated` DATETIME NULL DEFAULT NULL,
  `is_stock` INT(11) NOT NULL DEFAULT '1' COMMENT '0:재고없음, 1:재고있음',
  PRIMARY KEY (`cart_id`),
  INDEX `fk_cart_member_idx` (`member_id` ASC) VISIBLE,
  INDEX `fk_cart_product_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `fk_cart_member`
    FOREIGN KEY (`member_id`)
    REFERENCES `todayart`.`member` (`member_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_cart_product`
    FOREIGN KEY (`product_id`)
    REFERENCES `todayart`.`product` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

INSERT INTO `todayart`.`cart` VALUE(1, 3000, 1, now(), 0);

-- -----------------------------------------------------
-- Table `todayart`.`member_address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `todayart`.`member_address` (
  `address_id` INT(11) NOT NULL AUTO_INCREMENT,
  `member_id` INT(11) NOT NULL,
  `post_number` INT(11) NOT NULL,
  `address` VARCHAR(255) NOT NULL,
  `address_detail` VARCHAR(255) NULL DEFAULT NULL,
  `main_address` INT(11) NOT NULL DEFAULT '0' COMMENT '0:일반배송지, 1:대표배송지',
  PRIMARY KEY (`address_id`),
  INDEX `fk_memberAddress_member_idx` (`member_id` ASC) VISIBLE,
  CONSTRAINT `fk_memberAddress_member`
    FOREIGN KEY (`member_id`)
    REFERENCES `todayart`.`member` (`member_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `todayart`.`mileage_invoice`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `todayart`.`mileage_invoice` (
  `mileage_id` INT(11) NOT NULL AUTO_INCREMENT,
  `artist_id` INT(11) NOT NULL,
  `exchange_dated` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `exchange_mileage` INT(11) NOT NULL,
  `exchange_cash` INT(11) NOT NULL,
  `bank_name` VARCHAR(255) NOT NULL,
  `deposit_account` VARCHAR(255) NOT NULL,
  `balance` INT(11) NOT NULL,
  `is_delete` INT(11) NOT NULL DEFAULT 0 COMMENT '0: 미삭제, 1: 삭제',
  PRIMARY KEY (`mileage_id`),
  INDEX `fk_mileageInvoice_artist_idx` (`artist_id` ASC) VISIBLE,
  CONSTRAINT `fk_mileageInvoice_artist`
    FOREIGN KEY (`artist_id`)
    REFERENCES `todayart`.`artist` (`artist_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `todayart`.`ordered`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `todayart`.`ordered` (
  `ordered_id` INT(11) NOT NULL AUTO_INCREMENT,
  `member_id` INT(11) NOT NULL,
  `cart_id` INT(11) NULL DEFAULT NULL,
  `order_dated` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `total_price` INT(11) NOT NULL,
  `shipping_fee` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`ordered_id`),
  INDEX `fk_ordered_member_idx` (`member_id` ASC) VISIBLE,
  INDEX `fk_ordered_cart_idx` (`cart_id` ASC) VISIBLE,
  CONSTRAINT `fk_ordered_member`
    FOREIGN KEY (`member_id`)
    REFERENCES `todayart`.`member` (`member_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_ordered_cart`
    FOREIGN KEY (`cart_id`)
    REFERENCES `todayart`.`cart` (`cart_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

INSERT INTO `todayart`.`ordered` VALUE(1, 10001, 1, now(), 10002500, 2500);

-- -----------------------------------------------------
-- Table `todayart`.`mileage_statement`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `todayart`.`mileage_statement` (
  `trade_id` INT(11) NOT NULL AUTO_INCREMENT,
  `artist_id` INT(11) NOT NULL,
  `order_id` INT(11) NOT NULL,
  `balance` INT(11) NULL DEFAULT NULL,
  `income` INT(11) NULL DEFAULT NULL,
  `commission_rate` INT(11) NULL DEFAULT NULL,
  `commission` INT(11) NULL DEFAULT NULL,
  `deposit_mileage` INT(11) NULL DEFAULT NULL,
  `invoice_id` INT(11) NULL DEFAULT NULL,
  `withdraw_mileage` INT(11) NULL DEFAULT NULL,
  `create_dated` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `is_delete` INT(11) NOT NULL DEFAULT '0' COMMENT '0: 삭제안됨, 1: 삭제',
  PRIMARY KEY (`trade_id`),
  INDEX `fk_mileageStatement_artist_idx` (`artist_id` ASC) VISIBLE,
  INDEX `fk_mileageStatement_ordered_idx` (`order_id` ASC) VISIBLE,
  CONSTRAINT `fk_mileageStatement_artist`
    FOREIGN KEY (`artist_id`)
    REFERENCES `todayart`.`artist` (`artist_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_mileageStatement_ordered`
    FOREIGN KEY (`order_id`)
    REFERENCES `todayart`.`ordered` (`ordered_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `todayart`.`oauth_access_token`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `todayart`.`oauth_access_token` (
  `token_id` VARCHAR(255) NULL DEFAULT NULL,
  `token` BLOB NULL DEFAULT NULL,
  `authentication_id` VARCHAR(255) NOT NULL,
  `user_name` VARCHAR(255) NULL DEFAULT NULL,
  `client_id` VARCHAR(255) NULL DEFAULT NULL,
  `authentication` BLOB NULL DEFAULT NULL,
  `refresh_token` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`authentication_id`))
ENGINE = MyISAM
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `todayart`.`oauth_approvals`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `todayart`.`oauth_approvals` (
  `userid` VARCHAR(255) NULL DEFAULT NULL,
  `clientid` VARCHAR(255) NULL DEFAULT NULL,
  `scope` VARCHAR(255) NULL DEFAULT NULL,
  `status` VARCHAR(10) NULL DEFAULT NULL,
  `expiresat` TIMESTAMP NULL DEFAULT NULL,
  `lastmodifiedat` TIMESTAMP NULL DEFAULT NULL)
ENGINE = MyISAM
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `todayart`.`oauth_client_details`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `todayart`.`oauth_client_details` (
  `client_id` VARCHAR(255) NOT NULL,
  `resource_ids` VARCHAR(255) NULL DEFAULT NULL,
  `client_secret` VARCHAR(255) NULL DEFAULT NULL,
  `scope` VARCHAR(255) NULL DEFAULT NULL,
  `authorized_grant_types` VARCHAR(255) NULL DEFAULT NULL,
  `web_server_redirect_uri` VARCHAR(255) NULL DEFAULT NULL,
  `authorities` VARCHAR(255) NULL DEFAULT NULL,
  `access_token_validity` INT(11) NULL DEFAULT NULL,
  `refresh_token_validity` INT(11) NULL DEFAULT NULL,
  `additional_information` VARCHAR(4096) NULL DEFAULT NULL,
  `autoapprove` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`client_id`))
ENGINE = MyISAM
DEFAULT CHARACTER SET = utf8;


INSERT INTO `oauth_client_details` (`client_id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `authorities`, `access_token_validity`, `refresh_token_validity`, `autoapprove`)
  VALUES ('test', 'todayart-server-rest-api', 'test', 'read,write', 'password,authorization_code,refresh_token,client_credentials', 'ROLE_GUEST', 86400, 2592000, true);

-- -----------------------------------------------------
-- Table `todayart`.`oauth_client_token`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `todayart`.`oauth_client_token` (
  `token_id` VARCHAR(255) NULL DEFAULT NULL,
  `token` BLOB NULL DEFAULT NULL,
  `authentication_id` VARCHAR(255) NOT NULL,
  `user_name` VARCHAR(255) NULL DEFAULT NULL,
  `client_id` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`authentication_id`))
ENGINE = MyISAM
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `todayart`.`oauth_code`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `todayart`.`oauth_code` (
  `code` VARCHAR(255) NULL DEFAULT NULL,
  `authentication` BLOB NULL DEFAULT NULL)
ENGINE = MyISAM
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `todayart`.`oauth_refresh_token`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `todayart`.`oauth_refresh_token` (
  `token_id` VARCHAR(255) NULL DEFAULT NULL,
  `token` BLOB NULL DEFAULT NULL,
  `authentication` BLOB NULL DEFAULT NULL)
ENGINE = MyISAM
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `todayart`.`ordered_detail`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `todayart`.`ordered_detail` (
  `ordered_detail_id` INT(11) NOT NULL AUTO_INCREMENT,
  `ordered_id` INT(11) NOT NULL,
  `product_id` INT(11) NOT NULL,
  `quantity` INT(11) NOT NULL,
  `total_price` INT(11) NOT NULL,
  `product_price` INT(11) NOT NULL,
  `productName` VARCHAR(255) NOT NULL,
  `product_size` VARCHAR(255) NOT NULL,
  `shipping_fee` INT(11) NOT NULL,
  `tracking_number` VARCHAR(255) NULL DEFAULT NULL,
  `status` ENUM('결제대기', '결제취소', '결제완료', '배송준비', '배송중', '배송완료', '주문확정', '주문취소', '반품대기','반품중','반품완료', '환불처리중', '환불완료') NOT NULL DEFAULT '결제대기',
  PRIMARY KEY (`ordered_detail_id`),
  INDEX `fk_orderedDetail_ordered_idx` (`ordered_id` ASC) VISIBLE,
  INDEX `fk_orderedDetail_product_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `fk_orderedDetail_ordered`
    FOREIGN KEY (`ordered_id`)
    REFERENCES `todayart`.`ordered` (`ordered_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_orderedDetail_product`
    FOREIGN KEY (`product_id`)
    REFERENCES `todayart`.`product` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `todayart`.`payment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `todayart`.`payment` (
  `payment_id` INT(11) NOT NULL AUTO_INCREMENT,
  `order_id` INT(11) NOT NULL,
  `product_id` INT(11) NOT NULL,
  `pay_dated` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `pay_method` VARCHAR(255) NOT NULL,
  `card_number` VARCHAR(255) NULL DEFAULT NULL,
  `pay_price` INT(11) NULL DEFAULT NULL,
  `total_price` INT(11) NULL DEFAULT NULL,
  `status` ENUM ('결제대기', '결제완료', '결제취소1', '결제취소2') NOT NULL DEFAULT '결제대기',
  `refund_comment` VARCHAR(255) NULL DEFAULT NULL COMMENT '환불사유',
  PRIMARY KEY (`payment_id`),
  INDEX `fk_payment_ordered_idx` (`order_id` ASC) VISIBLE,
  INDEX `fk_payment_product_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `fk_payment_ordered`
    FOREIGN KEY (`order_id`)
    REFERENCES `todayart`.`ordered` (`ordered_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_payment_product`
    FOREIGN KEY (`product_id`)
    REFERENCES `todayart`.`product` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `todayart`.`shipping`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `todayart`.`shipping` (
  `shipping_id` INT(11) NOT NULL AUTO_INCREMENT,
  `ordered_id` INT(11) NOT NULL,
  `shipping_dated` DATETIME NULL DEFAULT NULL,
  `shipping_fee` INT(11) NULL DEFAULT NULL,
  `consignee` VARCHAR(255) NOT NULL,
  `receive_addr` VARCHAR(255) NOT NULL,
  `consignee_phone` INT(11) NOT NULL,
  `artist_id` INT(11) NOT NULL,
  `member_id` INT(11) NOT NULL,
  `courier` VARCHAR(255) NULL DEFAULT NULL,
  `tracking_number` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`shipping_id`),
  UNIQUE INDEX `tracking_number` (`tracking_number` ASC) VISIBLE,
  INDEX `fk_shipping_ordered_idx` (`ordered_id` ASC) VISIBLE,
  INDEX `fk_shipping_artist_idx` (`artist_id` ASC) VISIBLE,
  INDEX `fk_shipping_member_idx` (`member_id` ASC) VISIBLE,
  CONSTRAINT `fk_shipping_ordered`
    FOREIGN KEY (`ordered_id`)
    REFERENCES `todayart`.`ordered` (`ordered_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_shipping_artist`
    FOREIGN KEY (`artist_id`)
    REFERENCES `todayart`.`member` (`member_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_shipping_member`
    FOREIGN KEY (`member_id`)
    REFERENCES `todayart`.`member` (`member_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `todayart`.`shipping_items`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `todayart`.`shipping_items` (
  `item_id` INT(11) NOT NULL AUTO_INCREMENT,
  `shipping_id` INT(11) NOT NULL,
  `ordered_id` INT(11) NOT NULL,
  `product_id` INT(11) NOT NULL,
  `last_updated` DATETIME NULL DEFAULT NULL,
  `status` VARCHAR(255) NOT NULL DEFAULT '배송중',
  PRIMARY KEY (`item_id`),
  INDEX `fk_shippingItems_shipping_idx` (`shipping_id` ASC) VISIBLE,
  INDEX `fk_shippingItems_ordered_idx` (`ordered_id` ASC) VISIBLE,
  INDEX `fk_shippingItems_product_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `fk_shippingItems_shipping`
    FOREIGN KEY (`shipping_id`)
    REFERENCES `todayart`.`shipping` (`shipping_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_shippingItems_ordered`
    FOREIGN KEY (`ordered_id`)
    REFERENCES `todayart`.`ordered` (`ordered_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_shippingItems_product`
    FOREIGN KEY (`product_id`)
    REFERENCES `todayart`.`product` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `todayart`.`update_log`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `todayart`.`update_log` (
  `log_id` INT(11) NOT NULL AUTO_INCREMENT,
  `member_id` INT(11) NOT NULL,
  `updated` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `log_type` VARCHAR(255) NOT NULL COMMENT 'tablename_pk_action',
  `log_desc` JSON NOT NULL COMMENT '{log_data: [{\"column\":\"column_name\"\\n
			\"before\":\"before_data\",\\n										
			\"after\":\"afer_data\"},{},{}...]}',
  PRIMARY KEY (`log_id`),
  INDEX `fk_updateLog_member_idx` (`member_id` ASC) VISIBLE,
  CONSTRAINT `fk_updateLog_member`
    FOREIGN KEY (`member_id`)
    REFERENCES `todayart`.`member` (`member_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `todayart`.`wishlist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `todayart`.`wishlist` (
  `wishlist_id` INT(11) NOT NULL AUTO_INCREMENT,
  `member_id` INT(11) NOT NULL,
  `product_id` INT(11) NOT NULL,
  `enroll_dated` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`wishlist_id`),
  INDEX `fk_wishlist_member_idx` (`member_id` ASC) VISIBLE,
  INDEX `fk_wishlist_product_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `fk_wishlist_member`
    FOREIGN KEY (`member_id`)
    REFERENCES `todayart`.`member` (`member_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_wishlist_product`
    FOREIGN KEY (`product_id`)
    REFERENCES `todayart`.`product` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;