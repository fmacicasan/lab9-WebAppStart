CREATE TABLE article (
    id bigserial NOT NULL PRIMARY KEY,
    link character(300) NOT NULL,
    domain character(40),
    summary character(120),
    date DATE NOT NULL default CURRENT_DATE
);

INSERT INTO article( link, domain, summary, date) VALUES ('https://medium.freecodecamp.com/things-i-wish-someone-had-told-me-when-i-was-learning-how-to-code-565fc9dcb329#.k6upfeizh', 'learning', 'The path from “not working” to “working” might not be obvious, but with patience you can usually find it', '2016-12-20');