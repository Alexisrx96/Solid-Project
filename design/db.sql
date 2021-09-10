use flights;

CREATE TABLE IF NOT EXISTS country(
  country_id INT NOT NULL AUTO_INCREMENT,
  country_name VARCHAR(100) UNIQUE NOT NULL,
  country_coordinates DOUBLE NOT NULL,
  PRIMARY KEY (country_id)
);
​

CREATE TABLE IF NOT EXISTS city(
    city_id INT NOT NULL AUTO_INCREMENT,
    city_name VARCHAR(100) UNIQUE NOT NULL,
    city_coordinates DOUBLE NOT NULL,
    country_id INT NOT NULL,
    PRIMARY KEY(city_id),
    CONSTRAINT FK_CIT_ID FOREIGN KEY(country_id) REFERENCES country(country_id) ON UPDATE CASCADE
);

CREATE TABLE  IF NOT EXISTS airline(
    airline_id INT NOT NULL AUTO_INCREMENT,
    airline_name VARCHAR(100) UNIQUE NOT NULL,
    PRIMARY KEY (airline_id)
);
​
CREATE TABLE IF NOT EXISTS aircraft(
    aircraft_id INT NOT NULL  AUTO_INCREMENT,
    aircraft_registration VARCHAR(30) UNIQUE NOT NULL,
    aircraft_model VARCHAR(100) NOT NULL,
    aircraft_passenger INT NOT NULL,
    aircraft_fuel_range DOUBLE NOT NULL,
    status_id INT NOT NULL,
    PRIMARY KEY(aircraft_id),
    CONSTRAINT FK_STAT_ID FOREIGN KEY (status_id) REFERENCES status(status_id) ON UPDATE CASCADE
);
​
CREATE TABLE IF NOT EXISTS flight(
    flight_id INT NOT NULL AUTO_INCREMENT,
    flight_datetime_depart DATETIME NOT NULL,
    flight_datetime_arrival DATETIME NOT NULL,
    flight_weather_report VARCHAR(100) NOT NULL,
    flight_country_depart_id INT NOT NULL,
    flight_city_depart_id INT NOT NULL,
    flight_city_arrival_id INT NOT NULL,
    flight_country_arrival_id INT NOT NULL,
    status_id INT NOT NULL,
    aircraft_id INT NOT NULL,
    airline_id INT NOT NULL,
    user_id INT NOT NULL,
​
    PRIMARY KEY (flight_id),
    CONSTRAINT FK_COUN_DEP_ID FOREIGN KEY(flight_country_depart_id) REFERENCES country(country_id) ON UPDATE CASCADE,
    CONSTRAINT FK_CIT_DEP_ID FOREIGN KEY (flight_city_depart_id) REFERENCES city(city_id) ON UPDATE CASCADE,
    CONSTRAINT FK_COUN_ARR_ID FOREIGN KEY(flight_country_arrival_id) REFERENCES country(country_id) ON UPDATE CASCADE,
    CONSTRAINT FK_CIT_ARR_ID FOREIGN KEY (flight_city_arrival_id) REFERENCES city(city_id) ON UPDATE CASCADE,
    CONSTRAINT FK_STA_ID FOREIGN KEY (status_id) REFERENCES status(status_id) ON UPDATE CASCADE,
    CONSTRAINT FK_AIRC_ID FOREIGN KEY (aircraft_id) REFERENCES aircraft(aircraft_id) ON UPDATE CASCADE,
    CONSTRAINT FK_AIRL_ID FOREIGN KEY (airline_id) REFERENCES airline(airline_id) ON UPDATE CASCADE,
    CONSTRAINT FK_USER_ID FOREIGN KEY (user_id) REFERENCES user(user_id) ON UPDATE CASCADE
​
);

CREATE TABLE IF NOT EXISTS reportSummary(
    reportSummary_id INT NOT NULL AUTO_INCREMENT,
    reportSummary_datetime DATETIME NOT NULL,
    flight_id INT NOT NULL,
    user_id INT NOT NULL,
    status_id INT NOT NULL,
    PRIMARY KEY(reportSummary_id),
    CONSTRAINT FK_FLI_ID FOREIGN KEY (flight_id) REFERENCES flight(flight_id) ON UPDATE  CASCADE,
    CONSTRAINT UPDATE_ID_FK_USER_ID FOREIGN KEY (user_id) REFERENCES user(user_id) ON UPDATE CASCADE,
    CONSTRAINT UPDATE_ID_FK_STAT_ID FOREIGN KEY (status_id) REFERENCES status(status_id) ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS reportDetails(
    reportDetails_id INT NOT NULL AUTO_INCREMENT,
    reportDetails_datetime DATETIME NOT NULL,
    reportDetails_description VARCHAR(1000) NOT NULL,
    reportDetails_clasification VARCHAR(100) NOT NULL,
    reportSummary_id INT NOT NULL,
    PRIMARY KEY(reportDetails_id),
    CONSTRAINT FK_REP_SUMM_ID FOREIGN KEY (reportSummary_id) REFERENCES reportSummary(reportSummary_id) ON UPDATE CASCADE
);


