CREATE TABLE IF NOT EXISTS ideas (
id SERIAL PRIMARY KEY NOT NULL,
title VARCHAR (255) NOT NULL,
description VARCHAR (255) NOT NULL,
added TIMESTAMP NOT NULL,
score INT NOT NULL,
active BOOLEAN NOT NULL,
iduser INT NOT NULL,
CONSTRAINT user_constraint FOREIGN KEY (iduser) REFERENCES users(id)
);