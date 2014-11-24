create schema test_stepanenko;
use test_stepanenko;
CREATE  TABLE products (
  good_id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL ,
  price decimal NOT NULL ,
  PRIMARY KEY (good_id));

INSERT INTO products(name,price)
VALUES ('TV', 500);
INSERT INTO products(name,price)
VALUES ('PC', 1500);
INSERT INTO products(name,price)
VALUES ('PhotoCamera', 2500);
INSERT INTO products(name,price)
VALUES ('VideoCamera', 4000);
INSERT INTO products(name,price)
VALUES ('Radio', 100);

create schema test_stepanenko2;
use test_stepanenko2;
CREATE  TABLE products (
  good_id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL ,
  price decimal NOT NULL ,
  PRIMARY KEY (good_id));