CREATE TABLE someTable (
    idSomeTable bigserial NOT NULL PRIMARY KEY,
    field1 character(20),
    field2 int,
    field3 DATE not null default CURRENT_DATE,
    field4 numeric(7,2)
);

CREATE TABLE someOtherTable (
    idSomeOtherTable bigserial NOT NULL PRIMARY KEY,
    field1 character(20),
    field2 int NOT NULL REFERENCES someTable(idSomeTable)
);

INSERT INTO sometable( field1, field2, field4) VALUES ('value1', 10, 5.2);
INSERT INTO someOtherTable( field1, field2) VALUES ('value2', 1);