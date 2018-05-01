DROP TABLE estate;
DROP TABLE estate_agent;
DROP TABLE apartment;
DROP TABLE house;
DROP TABLE person;
DROP TABLE contract;
DROP TABLE tenancy_contract;
DROP TABLE purchase_contract;

CREATE TABLE estate (ID int NOT NULL,
                     City VARCHAR(50) NOT NULL,
                     Postal_Code VARCHAR(50) NOT NULL,
                     Street VARCHAR(50) NOT NULL,
                     Street_Number int NOT NULL,
                     Square_Area DOUBLE NOT NULL,
                     ESTATE_AGENT_ID int NOT NULL,
                     PRIMARY KEY (ID));


CREATE TABLE estate_agent (ID int NOT NULL,
                           Name VARCHAR(50) NOT NULL,
                           Address VARCHAR(50) NOT NULL,
                           Login VARCHAR(50) NOT NULL,
                           Password VARCHAR(50) NOT NULL,
                           PRIMARY KEY (ID));


CREATE TABLE apartment (ESTATE_ID int NOT NULL,
                        Floor int NOT NULL,
                        Rent DOUBLE NOT NULL,
                        Rooms int NOT NULL,
                        Balcony char(1) NOT NULL,
                        Built_in_Kitchen char(1) NOT NULL,
                        Renter_ID int, PRIMARY KEY (ESTATE_ID), 
       CHECK (Balcony IN ('Y', 'N')), 
       CHECK (Built_in_Kitchen IN ('Y','N')));


CREATE TABLE house (ESTATE_ID int NOT NULL,
                    Floors int NOT NULL,
                    Price DOUBLE NOT NULL,
                    Garden char(1) NOT NULL,
                    Owner_ID int,PRIMARY KEY (ESTATE_ID), CHECK (Garden IN ('Y','N')));


CREATE TABLE person (ID int NOT NULL,
                     First_Name VARCHAR(50) NOT NULL,
                     Name VARCHAR(50) NOT NULL,
                     Address VARCHAR(50) NOT NULL,
                     PRIMARY KEY (ID));


CREATE TABLE contract (Contract_Number int NOT NULL, Date DATE NOT NULL,
                       Place VARCHAR(50) NOT NULL,
                       PRIMARY KEY (Contract_Number));


CREATE TABLE tenancy_contract (Contract_Number int NOT NULL,
                                Start_Date DATE NOT NULL,
                                Duration DATE NOT NULL,
                                Additional_Costs DOUBLE NOT NULL,
                                Apartment_ID int NOT NULL,
                                PRIMARY KEY (Contract_Number));


CREATE TABLE purchase_contract (Contract_Number int NOT NULL,
                                No_of_Installments int NOT NULL,
                                Interest_Rate DOUBLE NOT NULL,
                                House_ID int NOT NULL,
                                PRIMARY KEY (Contract_Number));


ALTER TABLE estate
FOREIGN KEY (ESTATE_AGENT_ID) REFERENCES estate_agent(ID);


ALTER TABLE apartment
FOREIGN KEY (ESTATE_ID) REFERENCES estate(ID);


ALTER TABLE apartment
FOREIGN KEY (Renter_ID) REFERENCES person(ID);


ALTER TABLE house
FOREIGN KEY (ESTATE_ID) REFERENCES estate(ID);


ALTER TABLE house
FOREIGN KEY (Owner_ID) REFERENCES person(ID);


ALTER TABLE tenancy_contract
FOREIGN KEY (Contract_Number) REFERENCES contract(Contract_Number);


ALTER TABLE tenancy_contract
FOREIGN KEY (Apartment_ID) REFERENCES apartment(ESTATE_ID);


ALTER TABLE purchase_contract
FOREIGN KEY (Contract_Number) REFERENCES contract(Contract_Number);


ALTER TABLE purchase_contract
FOREIGN KEY (House_ID) REFERENCES house(ESTATE_ID);


INSERT INTO estate_agent (ID, NAME, ADDRESS, LOGIN, PASSWORD)
VALUES (1,
        'Mustermann',
        'Musterstraße',
        'mmuster',
        'passwort');


INSERT INTO estate
VALUES (1,
        'Hamburg',
        '22222',
        'Zoostraße',
        48,
        90.15,
        1);


INSERT INTO estate
VALUES (2,
        'Hamburg',
        '22222',
        'Zoostraße',
        50,
        200.8,
        1);


INSERT INTO apartment
VALUES (1,
        3,
        300.50,
        4,
        'Y',
        'N',
        NULL);
