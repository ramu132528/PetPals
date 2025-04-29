CREATE DATABASE IF NOT EXISTS petpals;
USE petpals;

-- pets table
CREATE TABLE pets (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    breed VARCHAR(100) NOT NULL,
    type VARCHAR(50) NOT NULL,
    dog_breed VARCHAR(100),
    cat_color VARCHAR(100)
);

-- donations table
CREATE TABLE donations (
    id INT AUTO_INCREMENT PRIMARY KEY,
    donor_name VARCHAR(100) NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    type VARCHAR(50) NOT NULL,
    donation_date DATE,
    item_type VARCHAR(100)
);

-- adoption_events table
CREATE TABLE adoption_events (
    id INT AUTO_INCREMENT PRIMARY KEY,
    event_name VARCHAR(100) NOT NULL,
    event_date DATE NOT NULL
);

-- participants table
CREATE TABLE participants (
    id INT AUTO_INCREMENT PRIMARY KEY,
    event_id INT,
    participant_name VARCHAR(100) NOT NULL,
    participant_type VARCHAR(50),
    FOREIGN KEY (event_id) REFERENCES adoption_events(id)
);

USE petpals_db;

CREATE TABLE IF NOT EXISTS pets (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    breed VARCHAR(100),
    type VARCHAR(50)
);
