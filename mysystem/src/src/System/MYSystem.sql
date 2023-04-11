SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS dish;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS depart;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS takeout;
DROP TABLE IF EXISTS manager;
DROP TABLE IF EXISTS menus;




/* Create Tables */

CREATE TABLE customer
(
	cuorder int,
	cuid varchar(15) NOT NULL,
	name varchar(20),
	passwd varchar(16) NOT NULL,
	flag boolean,
	PRIMARY KEY (cuid)
);


CREATE TABLE depart
(
	deorder int,
	deid varchar(15) NOT NULL,
	name varchar(20),
	passwd varchar(16),
	reason varchar(10000),
	date varchar(20),
	PRIMARY KEY (deid)
);


CREATE TABLE dish
(
	diorder int,
	dishid varchar(15) NOT NULL,
	me varchar(20),
	price double,
	orid varchar(15) NOT NULL,
	PRIMARY KEY (dishid)
);


CREATE TABLE employee
(
	emorder int,
	emid varchar(15) NOT NULL,
	name varchar(20),
	passwd varchar(16) NOT NULL,
	wages double,
	maid varchar(15) NOT NULL,
	PRIMARY KEY (emid)
);


CREATE TABLE manager
(
	maorder int,
	maid varchar(15) NOT NULL,
	name varchar(20),
	passwd varchar(16) NOT NULL,
	PRIMARY KEY (maid)
);


CREATE TABLE menus
(
	meorder int,
	meid varchar(15) NOT NULL,
	menu varchar(20) NOT NULL,
	price double,
	PRIMARY KEY (menu)
);


CREATE TABLE orders
(
	ororder int,
	orid varchar(15) NOT NULL,
	price double,
	op boolean,
	flag boolean,
	cuid varchar(15) NOT NULL,
	PRIMARY KEY (orid)
);


CREATE TABLE takeout
(
	taorder int,
	taid varchar(15) NOT NULL,
	name varchar(20),
	passwd varchar(16) NOT NULL,
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



