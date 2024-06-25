CREATE TABLE schedule_item (
id LONG PRIMARY KEY AUTO_INCREMENT,
title VARCHAR(100),
date DATE,
time VARCHAR(10) ,
briefMessage VARCHAR(255),
eventType VARCHAR(50)
);