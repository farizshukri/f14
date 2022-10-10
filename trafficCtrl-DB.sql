CREATE DATABASE traffic_control;

USE traffic_control;

CREATE TABLE roads (
    road_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    length INT
);

CREATE TABLE traffic_lights (
    light_id INT AUTO_INCREMENT PRIMARY KEY,
    road_id INT,
    position VARCHAR(50),
    status ENUM('RED', 'GREEN', 'YELLOW'),
    FOREIGN KEY (road_id) REFERENCES roads(road_id)
);
