# Todayart v1.56
/*
[v1.3]
    - 모든 테이블에 default now() -> default current_timestamp로 변경
    - order -> ordered 문자열 변경
    - member_role, role : 테이블 삭제
    - member : authority 컬럼 삭제, role 컬럼 추가
    - ordered_detail : 컬럼 추가
        > `product_name` varchar(255) not null
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
[V1.53]
	- ordered 에 감추기용 isHidden 컬럼 추가(DELETE 기능 대신)
[V1.55]
	- 더미데이터 수정/추가
    - cart 테이블 전체적인 구조 수정
    - member_address 테이블 post_code -> postal_code 변경
    - payment 테이블 pg_number 추가
    - shiping 테이블 postal_code 추가
    - shipping_items 테이블 status 컬럼 enum 타입으로 변경
[V1.56]
	- file : 컬럼 명 변경
    > file_ip -> file_upload_ip
  - product : 컬럼 추가
    > `product_content` TEXT NOT NULL
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
AUTO_INCREMENT = 10003
DEFAULT CHARACTER SET = utf8;
 
INSERT INTO member VALUE(1, "admin@admin.com", "admin", "admin", "admin", now(), "01012345678", "ROLE_ADMIN", now(), 0, null, 1);
INSERT INTO member VALUE(3000, "member@member.com", "member", "member", "member", now(), "01012345678", "ROLE_CUSTOMER", now(), 0, null, 1);
INSERT INTO member VALUE(10001, "artist@artist.com", "artist", "artist", "artist", now(), "01012345678", "ROLE_ARTIST", now(), 0, null, 1);
INSERT INTO member VALUE(10002, "artist2@artist.com", "artist2", "artist2", "artist2", now(), "01012345678", "ROLE_ARTIST", now(), 0, null, 1);

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
INSERT INTO todayart.file value (2, 'pretty.png', '예쁜그림', 500, 'image', now(), "127.0.0.1", 10001 );
INSERT INTO todayart.file value (3, 'goodPoem.png', '좋은시', 4, 'text', now(), "127.0.0.1", 10002 );
INSERT INTO todayart.file value (4, 'powerful.png', '굉장한그림', 2048, 'image', now(), "127.0.0.1", 10002 );
INSERT INTO todayart.file value (5, 'beaty.png', '미인화', 1024, 'image', now(), "127.0.0.1", 10001 );
INSERT INTO todayart.file value (6, 'stuff.png', '쓰레기', 1, 'image', now(), "127.0.0.1", 10001 );

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
INSERT INTO `todayart`.`artist` VALUE(2, 10002, "유명한 화가", NULL, "대표작", 1, 1);

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

INSERT INTO `todayart`.`board_category`
VALUE(1,'homepage_qna');
INSERT INTO `todayart`.`board_category`
VALUE(2,'product_qna');
INSERT INTO `article`
VALUES(1,1,3000,now(),null,'제목1',"이 편지는 영국에서 시작되었습니다.",null,0,null,0,null,0,null,0,0,null);

-- -----------------------------------------------------
-- Table `todayart`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `todayart`.`category` (
  `category_id` INT(11) NOT NULL AUTO_INCREMENT,
  `category_name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`category_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

INSERT INTO `todayart`.`category` VALUE(1, '그림');
INSERT INTO `todayart`.`category` VALUE(2, '기타');


-- -----------------------------------------------------
-- Table `todayart`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `todayart`.`product` (
  `product_id` INT(11) NOT NULL AUTO_INCREMENT,
  `artist_id` INT(11) NOT NULL,
  `category_id` INT(11) NOT NULL,
  `product_name` VARCHAR(255) NOT NULL,
  `product_content` TEXT NOT NULL,
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
  INDEX `fk_product_category_idx` (`category_id` ASC) VISIBLE,
  INDEX `fk_product_file_idx` (`thumbnail_id` ASC) VISIBLE,
  CONSTRAINT `fk_product_artist`
    FOREIGN KEY (`artist_id`)
    REFERENCES `todayart`.`artist` (`artist_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_product_category`
    FOREIGN KEY (`category_id`)
    REFERENCES `todayart`.`category` (`category_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_product_file`
    FOREIGN KEY (`thumbnail_id`)
    REFERENCES `todayart`.`file` (`file_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

INSERT INTO `todayart`.`product`(`artist_id`, `category_id`, `product_name`, `product_content`, `product_size`, `product_price`, `thumbnail_id`, `shipping_fee`)
VALUE(1, 1, '멋있는 그림', '멋있는그림 내용', '매우 크다', 50000, 2, 10000);
INSERT INTO `todayart`.`product`(`artist_id`, `category_id`, `product_name`, `product_content`, `product_size`, `product_price`, `thumbnail_id`, `shipping_fee`)
VALUE(1, 1, '예쁜 그림', '멋있는그림 내용', '매우 크다', 50000, 2, 10000);
INSERT INTO `todayart`.`product`(`artist_id`, `category_id`, `product_name`, `product_content`, `product_size`, `product_price`, `thumbnail_id`, `shipping_fee`)
VALUE(1, 2, '시집', '멋있는그림 내용', '작음', 5000, 3, 0);
INSERT INTO `todayart`.`product`(`artist_id`, `category_id`, `product_name`, `product_content`, `product_size`, `product_price`, `thumbnail_id`, `shipping_fee`)
VALUE(2, 1, '굉장한 그림', '멋있는그림 내용', '엄청나게 크다', 780000, 4, 20000);
INSERT INTO `todayart`.`product`(`artist_id`, `category_id`, `product_name`, `product_content`, `product_size`, `product_price`, `thumbnail_id`, `shipping_fee`)
VALUE(2, 1, '미인화', '멋있는그림 내용', '중간크기', 150000, 5, 5000);
INSERT INTO `todayart`.`product`(`artist_id`, `category_id`, `product_name`, `product_content`, `product_size`, `product_price`, `thumbnail_id`, `shipping_fee`)
VALUE(1, 2, '잡동사니', '멋있는그림 내용', '매우 작음', 100, 6, 2500);


-- -----------------------------------------------------
-- Table `todayart`.`cart`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `todayart`.`cart` (
  `cart_id` INT(11) NOT NULL AUTO_INCREMENT,
  `member_id` INT(11) NOT NULL,
  `product_id` INT(11) NOT NULL,
  `product_price` int not null,
  `product_size` varchar(255) not null,
  `quantity` int not null default 1,
  `shipping_fee` int not null default 0,
  `enroll_dated` DATETIME NULL DEFAULT current_timestamp,
  `is_deleted` int(11) not null default 0 comment '0: not deleted, 1: deleted ',
  `is_stock` INT(11) NOT NULL DEFAULT 1 COMMENT '0:재고없음, 1:재고있음',
  PRIMARY KEY (`cart_id`),
  INDEX `fk_cart_member_idx` (`member_id` ASC) VISIBLE,
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

insert into cart (`member_id`,`product_id`,`product_price`,`product_size`, `shipping_fee`, `is_deleted`)
value (3000, 1, 10000000, "1024", 2500, 1);
insert into cart (`member_id`,`product_id`,`product_price`,`product_size`,`shipping_fee`)
value (3000, 2, 50000, "매우 크다", 10000);
insert into cart (`member_id`,`product_id`,`product_price`,`product_size`, `quantity`)
value (3000, 3, 5000, "작음", 4 );
insert into cart (`member_id`,`product_id`,`product_price`,`product_size`, `quantity`, `is_deleted`)
value (10002, 3, 5000, "작음", 2,1);
insert into cart (`member_id`,`product_id`,`product_price`,`product_size`, `quantity`,`shipping_fee`, `is_deleted`)
value (3000, 6, 100, "매우 작음", 10, 2500, 1);
insert into cart (`member_id`,`product_id`,`product_price`,`product_size`, `shipping_fee`, `is_deleted`)
value (10001, 4, 780000, "엄청나게 크다", 20000, 1);
insert into cart (`member_id`,`product_id`,`product_price`,`product_size`, `shipping_fee`, `is_deleted`)
value (10001,6,100,"매우 작음", 2500, 1);
insert into cart (`member_id`,`product_id`,`product_price`,`product_size`,`shipping_fee`)
value (10001,5,150000,"중간크기",5000);
insert into cart (`member_id`,`product_id`,`product_price`,`product_size`, `shipping_fee`, `is_deleted`)
value (3000, 4, 780000, "엄청나게 크다", 20000,1);
insert into cart (`member_id`,`product_id`,`product_price`,`product_size`, `quantity`,`shipping_fee`, `is_deleted`)
value (3000, 6, 100, "매우 작음", 100, 2500, 1);


-- -----------------------------------------------------
-- Table `todayart`.`member_address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `todayart`.`member_address` (
  `address_id` INT(11) NOT NULL AUTO_INCREMENT,
  `member_id` INT(11) NOT NULL,
  `postal_number` INT(11) NOT NULL,
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

INSERT INTO `todayart`.`member_address` VALUE(1, 3000, 04108, '서울특별시 마포구 백범로 23', '지하 1층', 1);


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
  `member_id` INT(11) NOT NULL COMMENT "구매자의 ID",
  `order_dated` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `total_price` INT(11) NOT NULL,
  `shipping_fee` INT(11) NULL DEFAULT NULL,
  `isHidden` INT NOT NULL DEFAULT 0 COMMENT "0:보이기 1:감추기",
  PRIMARY KEY (`ordered_id`),
  INDEX `fk_ordered_member_idx` (`member_id` ASC) VISIBLE,
  CONSTRAINT `fk_ordered_member`
    FOREIGN KEY (`member_id`)
    REFERENCES `todayart`.`member` (`member_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

INSERT INTO `todayart`.`ordered` VALUE(1, 3000, now(), 10002500, 2500, 0);
INSERT INTO `todayart`.`ordered` VALUE(2, 3000, now(), 3500, 2500,0);
INSERT INTO `todayart`.`ordered` VALUE(3, 10001, now(), 802600, 22500, 0);
INSERT INTO `todayart`.`ordered` VALUE(4, 3000, now(), 812500, 22500, 0);
INSERT INTO `todayart`.`ordered` VALUE(5, 10002, now(), 10000, 0, 0);


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
  `cart_id` INT(11) NOT NULL,
  `quantity` INT(11) NOT NULL,
  `total_price` INT(11) NOT NULL,
  `product_price` INT(11) NOT NULL,
  `product_name` VARCHAR(255) NOT NULL,
  `product_size` VARCHAR(255) NOT NULL,
  `shipping_fee` INT(11) NOT NULL,
  `tracking_number` VARCHAR(255) NULL DEFAULT NULL,
  `status` ENUM('결제대기', '결제취소', '결제완료', '배송준비', '배송중', '배송완료', '주문확정', '주문취소', '반품대기','반품중','반품완료', '환불처리중', '환불완료') NOT NULL DEFAULT '결제대기',
  PRIMARY KEY (`ordered_detail_id`),
  INDEX `fk_orderedDetail_ordered_idx` (`ordered_id` ASC) VISIBLE,
  INDEX `fk_orderedDetail_product_idx` (`product_id` ASC) VISIBLE,
  INDEX `fk_orderedDetail_cart_idx` (`cart_id` ASC) VISIBLE,
  CONSTRAINT `fk_orderedDetail_ordered`
    FOREIGN KEY (`ordered_id`)
    REFERENCES `todayart`.`ordered` (`ordered_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_orderedDetail_product`
    FOREIGN KEY (`product_id`)
    REFERENCES `todayart`.`product` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
CONSTRAINT `fk_orderedDetail_cart`
    FOREIGN KEY (`cart_id`)
    REFERENCES `todayart`.`cart` (`cart_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

INSERT INTO `todayart`.`ordered_detail` VALUE(1, 1, 1, 1, 1, 10000000, 10000000, '멋있는 그림', '1024', 2500, '1002243905116', '주문확정');
INSERT INTO `todayart`.`ordered_detail` VALUE(2, 2, 6, 5,10, 3500, 100, "잡동사니", "매우 작음", 2500, '8012550995032', '배송중');
INSERT INTO `todayart`.`ordered_detail` VALUE(3, 3, 4, 6,1, 800000, 780000, "굉장한 그림", "엄청나게 크다", 20000, NULL, '결제완료');
INSERT INTO `todayart`.`ordered_detail` VALUE(4, 3, 6, 7, 1, 2600, 100, "잡동사니", "매우 작음", 2500, NULL, '결제완료');
INSERT INTO `todayart`.`ordered_detail` VALUE(5, 4, 4, 9, 1, 800000, 780000, "굉장한 그림", "엄청나게 크다", 20000, NULL, '결제대기');
INSERT INTO `todayart`.`ordered_detail` VALUE(6, 4, 6, 10, 100, 12500, 10000, "잡동사니", "매우 작음", 2500, NULL, '결제대기');
INSERT INTO `todayart`.`ordered_detail` VALUE(7, 5, 3, 4,2, 10000, 10000, "시집", "작음", 0, NULL, '결제대기');

-- -----------------------------------------------------
-- Table `todayart`.`payment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `todayart`.`payment` (
  `payment_id` INT(11) NOT NULL AUTO_INCREMENT,
  `order_id` INT(11) NOT NULL,
  `product_id` INT(11) NOT NULL,
  `pay_dated` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `pay_method` VARCHAR(255) NOT NULL,
  `card_number` VARCHAR(255) NULL DEFAULT NULL,
  `pay_price` INT(11) NULL DEFAULT NULL,
  `total_price` INT(11) NULL DEFAULT NULL,
  `status` ENUM ('결제대기', '결제완료', '결제취소', '결제환불') NOT NULL DEFAULT '결제대기',
  `pg_number` BIGINT default null,
  `refund_comment` VARCHAR(255) DEFAULT NULL COMMENT '환불사유',
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


INSERT INTO `todayart`.`payment` VALUE (1, 1, 1, now(), '카카오페이', null, 10002500, 10002500, '결제완료', 1000100010, null);
INSERT INTO `todayart`.`payment` VALUE (2, 2, 6, now(), '신용카드', "8054669811003465", 10002500, 10002500, '결제완료', 8801666001, null);
INSERT INTO `todayart`.`payment` VALUE (3, 3, 4, now(), '카카오페이', null, 10002500, 10002500, '결제완료', 1000100011,null);
INSERT INTO `todayart`.`payment` VALUE (4, 3, 6, now(), '카카오페이', null, 10002500, 10002500, '결제완료',1000100011, null);
INSERT INTO `todayart`.`payment` VALUE (5, 4, 4, NULL, '무통장입금', null, 10002500, 10002500, '결제대기', NULL, null);
INSERT INTO `todayart`.`payment` VALUE (6, 4, 6, NULL, '무통장입금', null, 10002500, 10002500, '결제대기', NULL, null);
INSERT INTO `todayart`.`payment` VALUE (7, 5, 3, NULL, '무통장입금', null, 10002500, 10002500, '결제대기', NULL, null);




-- -----------------------------------------------------
-- Table `todayart`.`shipping`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `todayart`.`shipping` (
  `shipping_id` INT(11) NOT NULL AUTO_INCREMENT,
  `ordered_id` INT(11) NOT NULL,
  `shipping_dated` DATETIME NULL DEFAULT NULL,
  `shipping_fee` INT(11) NULL DEFAULT NULL,
  `consignee` VARCHAR(255) NOT NULL,
  `postal_code` INT NOT NULL,
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

INSERT INTO `todayart`.`shipping` VALUE (1, 1, now(), 2500, "비트캠프 신촌", 04108, "서울특별시 마포구 백범로 23 지하 1층", 01012345678, 1, 3000, "우체국택배", "1002243905116");
INSERT INTO `todayart`.`shipping` VALUE (2, 2, now(), 2500, "청와대", 03048, "서울 특별시 종로구 청와대대로1", 0288888888, 2, 3000, "우체국택배", "8012550995032");

-- -----------------------------------------------------
-- Table `todayart`.`shipping_items`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `todayart`.`shipping_items` (
  `item_id` INT(11) NOT NULL AUTO_INCREMENT,
  `shipping_id` INT(11) NOT NULL,
  `ordered_id` INT(11) NOT NULL,
  `product_id` INT(11) NOT NULL,
  `last_updated` DATETIME DEFAULT current_timestamp,
  `status` enum('배송중', '배송완료', '수취거부', '반송대기', '반송중', '반송완료') NOT NULL DEFAULT '배송중',
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

insert into `todayart`.`shipping_items` value(1, 1, 1, 1, now(), '배송완료');
insert into `todayart`.`shipping_items` value(2, 2, 2, 6, now(), '배송중');

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

INSERT INTO `todayart`.`update_log` VALUE(1, 1, now(), 'member_1_create',
	'[
    {"column":"member_id", "before":"null", "after":"1"},
    {"column":"email", "before":"null", "after":"admin@admin.com"},
    {"column":"password", "before":"null", "after":"admin"},
    {"column":"nickname", "before":"null", "after":"admin"},
    {"column":"username", "before":"null", "after":"admin"},
    {"column":"reg_dated", "before":"null", "after":"2019-08-09 19:03:45"},
    {"column":"phone", "before":"null", "after":"01012345678"},
    {"column":"role", "before":"null", "after":"ROLE_ADMIN"},
    {"column":"last_connect_dated", "before":"null", "after":"2019-08-09 19:03:45"},
    {"column":"expired", "before":"null", "after":"0"},
    {"column":"expired_dated", "before":"null", "after":"null"},
    {"column":"email_checked", "before":"null", "after":"0"}
    ]');
INSERT INTO `todayart`.`update_log` VALUE(2, 1, now(), 'member_3000_create',
		'[
    {"column":"member_id", "before":"null", "after":"3000"},
    {"column":"email", "before":"null", "after":"member@member.com"},
    {"column":"password", "before":"null", "after":"member"},
    {"column":"nickname", "before":"null", "after":"member"},
    {"column":"username", "before":"null", "after":"amember"},
    {"column":"reg_dated", "before":"null", "after":"2019-08-09 19:03:45"},
    {"column":"phone", "before":"null", "after":"01012345678"},
    {"column":"role", "before":"null", "after":"ROLE_CUSTOMER"},
    {"column":"last_connect_dated", "before":"null", "after":"2019-08-09 19:03:45"},
    {"column":"expired", "before":"null", "after":"0"},
    {"column":"expired_dated", "before":"null", "after":"null"},
    {"column":"email_checked", "before":"null", "after":"1"}
    ]');
INSERT INTO `todayart`.`update_log` VALUE(3, 1, now(), 'member_10001_create',
		'[
    {"column":"member_id", "before":"null", "after":"10001"},
    {"column":"email", "before":"null", "after":"artist@artist.com"},
    {"column":"password", "before":"null", "after":"artist"},
    {"column":"nickname", "before":"null", "after":"artist"},
    {"column":"username", "before":"null", "after":"artist"},
    {"column":"reg_dated", "before":"null", "after":"2019-08-09 19:03:45"},
    {"column":"phone", "before":"null", "after":"01012345678"},
    {"column":"role", "before":"null", "after":"ROLE_ARTIST"},
    {"column":"last_connect_dated", "before":"null", "after":"2019-08-09 19:03:45"},
    {"column":"expired", "before":"null", "after":"0"},
    {"column":"expired_dated", "before":"null", "after":"null"},
    {"column":"email_checked", "before":"null", "after":"1"}
    ]');
INSERT INTO `todayart`.`update_log` VALUE(4, 1, now(), 'member_10002_create',
	'[
    {"column":"member_id", "before":"null", "after":"10002"},
    {"column":"email", "before":"null", "after":"artist2@artist.com"},
    {"column":"password", "before":"null", "after":"artist2"},
    {"column":"nickname", "before":"null", "after":"artist2"},
    {"column":"username", "before":"null", "after":"artist2"},
    {"column":"reg_dated", "before":"null", "after":"2019-08-09 19:03:45"},
    {"column":"phone", "before":"null", "after":"01012345678"},
    {"column":"role", "before":"null", "after":"ROLE_ARTIST"},
    {"column":"last_connect_dated", "before":"null", "after":"2019-08-09 19:03:45"},
    {"column":"expired", "before":"null", "after":"0"},
    {"column":"expired_dated", "before":"null", "after":"null"},
    {"column":"email_checked", "before":"null", "after":"1"}
    ]');

INSERT INTO `todayart`.`update_log` VALUE(5, 1, now(), 'artist_level_1_create',
	'[
    {"column":"artist_level_id", "before":"null", "after":"1"},
    {"column":"level", "before":"null", "after":"Bronze"},
    {"column":"commission", "before":"null", "after":"15"}
    ]');

INSERT INTO `todayart`.`update_log` VALUE(6, 10001, now(), 'file_1_create',
	'[
    {"column":"file_id", "before":"null", "after":"1"},
    {"column":"file_originname", "before":"null", "after":"a.jpg"},
    {"column":"file_name", "before":"null", "after":"그림1"},
    {"column":"file_size", "before":"null", "after":"1024"},
    {"column":"file_type", "before":"null", "after":"image"},
    {"column":"file_reg_dated", "before":"null", "after":"2019-08-09 19:03:46"},
    {"column":"file_ip", "before":"null", "after":"127.0.0.1"},
    {"column":"member_id", "before":"null", "after":"10001"}
    ]');
INSERT INTO `todayart`.`update_log` VALUE(7, 10001, now(), 'file_2_create',
	'[
    {"column":"file_id", "before":"null", "after":"2"},
    {"column":"file_originname", "before":"null", "after":"pretty.png"},
    {"column":"file_name", "before":"null", "after":"예쁜그림"},
    {"column":"file_size", "before":"null", "after":"500"},
    {"column":"file_type", "before":"null", "after":"image"},
    {"column":"file_reg_dated", "before":"null", "after":"2019-08-09 19:03:46"},
    {"column":"file_ip", "before":"null", "after":"127.0.0.1"},
    {"column":"member_id", "before":"null", "after":"10001"}
    ]');

INSERT INTO `todayart`.`update_log` VALUE(8, 10002, now(), 'file_3_create',
	'[
    {"column":"file_id", "before":"null", "after":"3"},
    {"column":"file_originname", "before":"null", "after":"goodPoem.png"},
    {"column":"file_name", "before":"null", "after":"좋은시"},
    {"column":"file_size", "before":"null", "after":"4"},
    {"column":"file_type", "before":"null", "after":"text"},
    {"column":"file_reg_dated", "before":"null", "after":"2019-08-09 19:03:46"},
    {"column":"file_ip", "before":"null", "after":"127.0.0.1"},
    {"column":"member_id", "before":"null", "after":"10002"}
    ]');
INSERT INTO `todayart`.`update_log` VALUE(9, 10001, now(), 'file_4_create',
'[
{"column":"file_id", "before":"null", "after":"4"},
{"column":"file_originname", "before":"null", "after":"powerful.png"},
{"column":"file_name", "before":"null", "after":"굉장한그림"},
{"column":"file_size", "before":"null", "after":"2048"},
{"column":"file_type", "before":"null", "after":"image"},
{"column":"file_reg_dated", "before":"null", "after":"2019-08-09 19:03:46"},
{"column":"file_ip", "before":"null", "after":"127.0.0.1"},
{"column":"member_id", "before":"null", "after":"10002"}
]');
    
INSERT INTO `todayart`.`update_log` VALUE(10, 10001, now(), 'file_5_create',
	'[
    {"column":"file_id", "before":"null", "after":"5"},
    {"column":"file_originname", "before":"null", "after":"beaty.png"},
    {"column":"file_name", "before":"null", "after":"미인화"},
    {"column":"file_size", "before":"null", "after":"1024"},
    {"column":"file_type", "before":"null", "after":"image"},
    {"column":"file_reg_dated", "before":"null", "after":"2019-08-09 19:03:46"},
    {"column":"file_ip", "before":"null", "after":"127.0.0.1"},
    {"column":"member_id", "before":"null", "after":"10001"}
    ]');


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