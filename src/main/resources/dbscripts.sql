CREATE TABLE recipes (
    id bigserial NOT NULL PRIMARY KEY,
    name character(20) NOT NULL,
    description character(128) NOT NULL,
    ingredients character(64) NOT NULL,
    addedAt DATE not null default CURRENT_DATE,
    duration character(15)
);


INSERT INTO recipes( name, description, ingredients, duration)
VALUES ('omleta','sparg oua, pun sare, omogenizam', 'oua, legume, sare, chili', '15 min');