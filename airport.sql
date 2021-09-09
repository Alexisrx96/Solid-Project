CREATE DATABASE airport;

USE airport;

CREATE TABLE status(
status_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
status_state VARCHAR(50) UNIQUE NOT NULL,
status_abbreviation VARCHAR(3) UNIQUE NOT NULL,
PRIMARY KEY(status_id)
);

INSERT INTO status(status_id,status_state,status_abbreviation)
VALUES
(0,'ACTIVE','ACT'),
(0,'INACTIVE','INA');

SELECT * FROM status s;

CREATE TABLE user(
    user_id INT UNSIGNED NOT NULL  AUTO_INCREMENT,
    user_name VARCHAR(100) NOT NULL,
    user_lastname VARCHAR(100) NOT NULL,
    user_account VARCHAR(100) NOT NULL,
    user_password VARCHAR(100) NOT NULL,
    status_id INT UNSIGNED NOT NULL,
    PRIMARY KEY (user_id),
    CONSTRAINT FK_USE_STA_ID FOREIGN KEY (status_id) REFERENCES status(status_id) ON UPDATE CASCADE
);

/**Delimiter de user*/
DELIMITER //
CREATE PROCEDURE setUser(IN user_id INT, IN user_name VARCHAR(1000), IN user_lastname VARCHAR(100),IN user_account VARCHAR(100), IN user_password VARCHAR(100), IN status_id INT)
BEGIN
    INSERT INTO user
    VALUES (user_id,user_name, user_lastname, user_account, user_password, status_id);
END //
DELIMITER ;

SELECT * FROM user;

CREATE TABLE country(
  country_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  country_name VARCHAR(100) UNIQUE NOT NULL,
  country_coordinates DOUBLE NOT NULL,
  PRIMARY KEY (country_id)
);

/**Delimiter de Country*/
DELIMITER //
CREATE PROCEDURE setCountry(IN country_id INT, IN country_name VARCHAR(100), IN country_coordinates DOUBLE)
BEGIN
    INSERT INTO country VALUES (country_id,country_name, country_coordinates);
END //
DELIMITER ;

SELECT * FROM country c;

CREATE TABLE city(
    city_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    city_name VARCHAR(100) UNIQUE NOT NULL,
    city_coordinates DOUBLE NOT NULL,
    country_id INT UNSIGNED NOT NULL,
    PRIMARY KEY(city_id),
    CONSTRAINT FK_CIT_ID FOREIGN KEY(country_id) REFERENCES country(country_id) ON UPDATE CASCADE
);

/**Delimiter de city*/
DELIMITER //
CREATE PROCEDURE setCity(IN city_id INT, IN city_name VARCHAR(100), IN city_coordinates DOUBLE, IN country_id INT)
BEGIN
    INSERT INTO city VALUES(city_id,city_name, city_coordinates, country_id);
END //
DELIMITER ;

SELECT * FROM city c;

CREATE TABLE airline(
    airline_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    airline_name VARCHAR(100) UNIQUE NOT NULL,
    PRIMARY KEY (airline_id)
);

/**Delimiter de Airline*/
DELIMITER //
CREATE PROCEDURE setAirline(IN airline_id INT,airline_name VARCHAR(100))
BEGIN
    INSERT INTO airline VALUES(airline_id,airline_name);
end //
DELIMITER ;

SELECT * FROM airline a;

CREATE TABLE aircraft(
    aircraft_id INT UNSIGNED NOT NULL  AUTO_INCREMENT,
    aircraft_registration VARCHAR(30) UNIQUE NOT NULL,
    aircraft_model VARCHAR(100) NOT NULL,
    aircraft_passenger INT NOT NULL,
    aircraft_fuel_range DOUBLE NOT NULL,
    status_id INT UNSIGNED NOT NULL,
    PRIMARY KEY(aircraft_id),
    CONSTRAINT FK_STAT_ID FOREIGN KEY (status_id) REFERENCES status(status_id) ON UPDATE CASCADE
);

/**Delimiter de aircraft*/
DELIMITER //
CREATE PROCEDURE setAircraft(IN aircraft_id INT, IN aircraft_registration VARCHAR(30), IN aircraft_model VARCHAR(100), IN aircraft_passenger INT, IN aircraft_fuel_range DOUBLE, IN status_id INT)
BEGIN
    INSERT INTO aircraft VALUES(aircraft_id, aircraft_registration, aircraft_model, aircraft_passenger, aircraft_fuel_range, status_id);
end //
DELIMITER ;

SELECT * FROM aircraft a;

CREATE TABLE flight(
    flight_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    flight_datetime_depart DATETIME NOT NULL,
    flight_datetime_arrival DATETIME NOT NULL,
    flight_weather_report VARCHAR(100) NOT NULL,
    flight_city_depart_id INT UNSIGNED NOT NULL,
    flight_city_arrival_id INT UNSIGNED NOT NULL,
    status_id INT UNSIGNED NOT NULL,
    aircraft_id INT UNSIGNED NOT NULL,
    airline_id INT UNSIGNED NOT NULL,
    user_id INT UNSIGNED NOT NULL,

    PRIMARY KEY (flight_id),
    CONSTRAINT FK_CIT_DEP_ID FOREIGN KEY (flight_city_depart_id) REFERENCES city(city_id) ON UPDATE CASCADE,
    CONSTRAINT FK_CIT_ARR_ID FOREIGN KEY (flight_city_arrival_id) REFERENCES city(city_id) ON UPDATE CASCADE,
    CONSTRAINT FK_STA_ID FOREIGN KEY (status_id) REFERENCES status(status_id) ON UPDATE CASCADE,
    CONSTRAINT FK_AIRC_ID FOREIGN KEY (aircraft_id) REFERENCES aircraft(aircraft_id) ON UPDATE CASCADE,
    CONSTRAINT FK_AIRL_ID FOREIGN KEY (airline_id) REFERENCES airline(airline_id) ON UPDATE CASCADE,
    CONSTRAINT FK_USER_ID FOREIGN KEY (user_id) REFERENCES user(user_id) ON UPDATE CASCADE

);
/**Delimiter de FLight*/
DELIMITER //
CREATE PROCEDURE setFlight(IN flight_id INT, IN flight_datetime_depart DATETIME,IN flight_datetime_arrival DATETIME, IN flight_weather_report VARCHAR(100),  IN flight_city_depart_id INT , IN flight_city_arrival_id INT, IN status_id INT,IN aircraft_id INT, IN airline_id INT,IN user_id INT)
BEGIN
    INSERT INTO flight VALUES (flight_id, flight_datetime_depart, flight_datetime_arrival, flight_weather_report, flight_city_depart_id, flight_city_arrival_id, status_id, aircraft_id, airline_id, user_id);
end //
DELIMITER ;

SELECT * FROM flight f;

CREATE TABLE reportSummary(
    reportSummary_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    reportSummary_datetime DATETIME NOT NULL,
    flight_id INT UNSIGNED NOT NULL,
    user_id INT UNSIGNED NOT NULL,
    status_id INT UNSIGNED NOT NULL,
    PRIMARY KEY(reportSummary_id),
    CONSTRAINT FK_FLI_ID FOREIGN KEY (flight_id) REFERENCES flight(flight_id) ON UPDATE  CASCADE,
    CONSTRAINT UPDATE_ID_FK_USER_ID FOREIGN KEY (user_id) REFERENCES user(user_id) ON UPDATE CASCADE,
    CONSTRAINT UPDATE_ID_FK_STAT_ID FOREIGN KEY (status_id) REFERENCES status(status_id) ON UPDATE CASCADE
);

/**Delimiter de reportSummary*/
DELIMITER //
CREATE PROCEDURE setReportSummary(IN reportSummary_id INT,IN reportSummary_datetime DATETIME, IN flight_id INT, IN user_id INT,IN status_id INT)
BEGIN
    INSERT INTO reportSummary VALUES(reportSummary_id,reportSummary_datetime, flight_id, user_id, status_id);
end //
DELIMITER ;

SELECT * FROM reportSummary r;

CREATE TABLE reportDetails(
    reportDetails_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    reportDetails_datetime DATETIME NOT NULL,
    reportDetails_description VARCHAR(1000) NOT NULL,
    reportDetails_clasification VARCHAR(100) NOT NULL,
    reportSummary_id INT UNSIGNED NOT NULL,
    PRIMARY KEY(reportDetails_id),
    CONSTRAINT FK_REP_SUMM_ID FOREIGN KEY (reportSummary_id) REFERENCES reportSummary(reportSummary_id) ON UPDATE CASCADE
);
/**Delimiter de reportDetails*/
DELIMITER //
CREATE PROCEDURE setReportDetails(IN reportDetails_id INT, IN reportDetails_datetime DATETIME, IN reportDetails_description VARCHAR(1000), IN reportDetails_clasification VARCHAR(100), IN reportSummary_id INT)
BEGIN
    INSERT INTO reportDetails VALUES (reportDetails_id,reportDetails_datetime, reportDetails_description, reportDetails_clasification, reportSummary_id);
end //
DELIMITER ;



SELECT * FROM reportDetails;


