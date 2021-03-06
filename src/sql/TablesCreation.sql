DROP DATABASE IF EXISTS `idearepository`;
CREATE DATABASE `idearepository` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;

USE `idearepository`;

DROP TABLE IF EXISTS `USERS`;
CREATE TABLE USERS (
    USER_ID BIGINT NOT NULL AUTO_INCREMENT,
    USER_NAME VARCHAR(60) NOT NULL,
    LOGIN VARCHAR(20) NOT NULL,
    PSWD VARCHAR(20) NOT NULL,
    USER_TYPE BIT NOT NULL DEFAULT 0,
    USER_ENABLED BIT NOT NULL DEFAULT 1,
    UNIQUE UQ_USER_1 (USER_ID, USER_NAME),
    PRIMARY KEY (USER_ID) );

DROP TABLE IF EXISTS `CONTENTS`;
CREATE TABLE CONTENTS (
    CONTENT_ID BIGINT NOT NULL AUTO_INCREMENT,
    IDEA_CONTENT TEXT NOT NULL,
    UNIQUE UQ_CONTENT_IDEA_1 (CONTENT_ID),
    PRIMARY KEY (CONTENT_ID) );

DROP TABLE IF EXISTS `IDEAS`;
CREATE TABLE IDEAS (
    IDEA_ID BIGINT NOT NULL AUTO_INCREMENT,
    IDEA_NAME VARCHAR(60) NOT NULL,
    IDEA_CONTENT_ID BIGINT NOT NULL,
    DESCRIPTION VARCHAR(254) NOT NULL,
    IDEA_IMAGE VARCHAR(254) DEFAULT NULL,
    IDEA_LIKES INT NOT NULL DEFAULT 0,
    IDEA_DISLIKES INT NOT NULL DEFAULT 0,
    IDEA_AUTHOR_ID BIGINT NOT NULL DEFAULT 1,
    POSTED_DATE TIMESTAMP NOT NULL DEFAULT NOW(),
    VIEWED_DATE TIMESTAMP NOT NULL DEFAULT NOW() ON UPDATE CURRENT_TIMESTAMP,
    VIEWED_COUNT BIGINT NOT NULL DEFAULT 0,
    IDEA_ENABLED BIT NOT NULL DEFAULT 1,
    UNIQUE UQ_IDEA_MAG_1 (IDEA_ID),
    PRIMARY KEY (IDEA_ID),
    CONSTRAINT FK_IDEA_AUTHOR_1 FOREIGN KEY (IDEA_AUTHOR_ID) REFERENCES USERS (USER_ID) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FK_CONTENT_IDEA_1 FOREIGN KEY (IDEA_CONTENT_ID) REFERENCES CONTENTS (CONTENT_ID) ON DELETE CASCADE ON UPDATE CASCADE) ;

DROP TABLE IF EXISTS `COMMENTS`;
CREATE TABLE COMMENTS (
    COMMENT_ID BIGINT NOT NULL AUTO_INCREMENT,
    COMMENT_AUTHOR_ID BIGINT NOT NULL,
    COMMENT_IDEA_ID BIGINT NOT NULL,
    COMMENT_CONTENT TEXT NOT NULL,
    COMMENT_POSTED TIMESTAMP NOT NULL DEFAULT NOW(),
    COMMENT_ENABLED BIT NOT NULL DEFAULT 1,
    UNIQUE UQ_COMMENT_1 (COMMENT_ID, COMMENT_POSTED),
    PRIMARY KEY (COMMENT_ID),
    CONSTRAINT FK_AUTHOR_COMMENT_1 FOREIGN KEY (COMMENT_AUTHOR_ID) REFERENCES USERS (USER_ID) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FK_IDEA_COMMENT_1 FOREIGN KEY (COMMENT_IDEA_ID) REFERENCES IDEAS (IDEA_ID) ON DELETE CASCADE ON UPDATE CASCADE );

DROP TABLE IF EXISTS `TAGS`;
CREATE TABLE TAGS (
    TAG_ID BIGINT NOT NULL AUTO_INCREMENT,
    TAG_CONTENT VARCHAR(60) NOT NULL,
    TAG_CREATED TIMESTAMP NOT NULL DEFAULT NOW(),
    TAG_ENABLED BIT NOT NULL DEFAULT 1,
    UNIQUE UQ_TAG_1 (TAG_ID),
    PRIMARY KEY (TAG_ID) );

DROP TABLE IF EXISTS `TAG_MAGAZINE`;
CREATE TABLE TAG_MAGAZINE (
    MAGAZINE_ID  BIGINT NOT NULL AUTO_INCREMENT,
    TAG_MAG_ID BIGINT NOT NULL,
    TAG_IDEA_ID BIGINT NOT NULL,
    TAG_MAG_ENABLED BIT NOT NULL DEFAULT 1,
    PRIMARY KEY (MAGAZINE_ID),
    KEY (TAG_MAG_ID),
    KEY (TAG_IDEA_ID),
    CONSTRAINT FK_TAGMAG_TAGS_1 FOREIGN KEY (TAG_MAG_ID) REFERENCES TAGS (TAG_ID) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FK_TAGMAG_IDEA_1 FOREIGN KEY (TAG_IDEA_ID) REFERENCES IDEAS (IDEA_ID) ON DELETE CASCADE ON UPDATE CASCADE );

DROP TABLE IF EXISTS `IMAGES`;
CREATE TABLE IMAGES (
    IMAGE_ID BIGINT NOT NULL AUTO_INCREMENT,
    IMAGE_NAME VARCHAR(150) NOT NULL,
    IMAGE_CREATED TIMESTAMP NOT NULL DEFAULT NOW(),
    IMAGE_AUTHOR BIGINT NOT NULL DEFAULT 1,
    IMAGE_PUBLIC BIT NOT NULL DEFAULT 1,
    UNIQUE UQ_IMAGE_1 (IMAGE_CREATED),
    PRIMARY KEY (IMAGE_ID) );