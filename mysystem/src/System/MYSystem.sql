SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS apply;
DROP TABLE IF EXISTS dish;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS depart;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS takeout;
DROP TABLE IF EXISTS manager;
DROP TABLE IF EXISTS menus;




/* Create Tables */

CREATE TABLE apply
(
	id varchar(15) NOT NULL,
	name varchar(20),
	sex varchar(4),
	job varchar(10),
	wages double,
	reason varchar(10000),
	emid varchar(15),
	taid varchar(15),
	PRIMARY KEY (id)
);


CREATE TABLE customer
(
	cuid varchar(15) NOT NULL,
	name varchar(20),
	passwd varchar(16) NOT NULL,
	sex varchar(4),
	number varchar(12),
	address varchar(5000),
	flag tinyint,
	PRIMARY KEY (cuid)
);


CREATE TABLE depart
(
	deid varchar(15) NOT NULL,
	name varchar(20),
	passwd varchar(16) NOT NULL,
	sex varchar(4),
	reason varchar(10000),
	date varchar(20),
	PRIMARY KEY (deid)
);


CREATE TABLE dish
(
	dishid varchar(15) NOT NULL,
	me varchar(20),
	price double,
	orid varchar(15) NOT NULL,
	PRIMARY KEY (dishid)
);


CREATE TABLE employee
(
	emid varchar(15) NOT NULL,
	name varchar(20),
	passwd varchar(16) NOT NULL,
	sex varchar(4),
	wages double,
	maid varchar(15) NOT NULL,
	PRIMARY KEY (emid)
);


CREATE TABLE manager
(
	maid varchar(15) NOT NULL,
	name varchar(20),
	passwd varchar(16) NOT NULL,
	sex varchar(4),
	PRIMARY KEY (maid)
);


CREATE TABLE menus
(
	menu varchar(20) NOT NULL,
	price double,
	PRIMARY KEY (menu)
);


CREATE TABLE orders
(
	orid varchar(15) NOT NULL,
	price double,
	op tinyint,
	flag tinyint,
	cuid varchar(15) NOT NULL,
	PRIMARY KEY (orid)
);


CREATE TABLE takeout
(
	taid varchar(15) NOT NULL,
	name varchar(20),
	passwd varchar(16) NOT NULL,
	sex varchar(4),
	wages double,
	maid varchar(15) NOT NULL,
	PRIMARY KEY (taid)
);



/* Create Foreign Keys */

ALTER TABLE orders
	ADD FOREIGN KEY (cuid)
	REFERENCES customer (cuid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE apply
	ADD FOREIGN KEY (emid)
	REFERENCES employee (emid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE employee
	ADD FOREIGN KEY (maid)
	REFERENCES manager (maid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE takeout
	ADD FOREIGN KEY (maid)
	REFERENCES manager (maid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE dish
	ADD FOREIGN KEY (orid)
	REFERENCES orders (orid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE apply
	ADD FOREIGN KEY (taid)
	REFERENCES takeout (taid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



