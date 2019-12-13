CREATE TABLE books (
  id  int UNSIGNED NOT NULL AUTO_INCREMENT,
  name  varchar(255) NULL,
  createTime  datetime NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);