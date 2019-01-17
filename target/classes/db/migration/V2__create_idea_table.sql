CREATE TABLE IF NOT EXISTS ideas (
id SERIAL PRIMARY KEY NOT NULL,
title VARCHAR (255) NOT NULL,
description VARCHAR (255) NOT NULL,
added_at TIMESTAMP NOT NULL,
score INT NOT NULL,
active BOOLEAN NOT NULL,
id_user INT,
CONSTRAINT user_constraint FOREIGN KEY (id_user) REFERENCES users(id)
);