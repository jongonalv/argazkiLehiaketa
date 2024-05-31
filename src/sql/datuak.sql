INSERT INTO `argazkiLehiaketa`.`Erabiltzailea` (`izena`, `abizena`, `korreoa`, `erabiltzaile_izena`, `pasahitza`, `mota`) VALUES
('Ane', 'Lopez', 'ane.lopez@example.com', 'anelo', 'contraseña123', 'arrunta'),
('Mikel', 'Garcia', 'mikel.garcia@example.com', 'mikgar', 'p4ssw0rd', 'arrunta'),
('Jone', 'Martinez', 'jone.martinez@example.com', 'jmartinez', '12345678', 'arrunta'),
('Unai', 'Fernandez', 'unai.fernandez@example.com', 'unaif', 'password', 'arrunta'),
('Leire', 'Sanchez', 'leire.sanchez@example.com', 'leisan', 'abcd1234', 'arrunta'),
('Alberto', 'Gonzalez', 'iker.rodriguez@example.com', 'albertomago', 'qwerty123', 'arrunta'),
('Nerea', 'Perez', 'nerea.perez@example.com', 'nereap', 'nereapass', 'arrunta'),
('Asier', 'Lopez', 'asier.lopez@example.com', 'asierlo', 'contraseña123', 'arrunta'),
('Maite', 'Gonzalez', 'maite.gonzalez@example.com', 'maigon', 'p4ssw0rd', 'arrunta'),
('Eneko', 'Hernandez', 'eneko.hernandez@example.com', 'enekoh', '12345678', 'arrunta'),
('Ainhoa', 'Jimenez', 'ainhoa.jimenez@example.com', 'aijim', 'password', 'arrunta'),
('Jon', 'Ruiz', 'jon.ruiz@example.com', 'jonrui', 'abcd1234', 'arrunta'),
('Naiara', 'Alvarez', 'naiara.alvarez@example.com', 'naial', 'qwerty123', 'arrunta'),
('Aitor', 'Gomez', 'aitor.gomez@example.com', 'aitogo', 'naiarapass', 'arrunta'),
('Izaro', 'Vazquez', 'izaro.vazquez@example.com', 'izarova', 'qwertyui', 'arrunta'),
('Urko', 'Iglesias', 'urko.iglesias@example.com', 'urkoig', 'contraseña123', 'arrunta'),
('Ane', 'Lopez', 'ane.lopez@example.com', 'anelo', 'contraseña123', 'admin'),
('Eneko', 'Fernandez', 'eneko.fernandez@example.com', 'enefer', '12345678', 'epailea'),
('Miren', 'Garcia', 'miren.garcia@example.com', 'mirgar', 'password', 'kudeatzailea'),
('Amaia', 'Sanchez', 'amaia.sanchez@example.com', 'amaisan', 'abcd1234', 'arrunta');

INSERT INTO `argazkiLehiaketa`.`Lehiaketa` (`izena`, `data_hasiera`, `data_bukaera`, `deskribapena`, `logotipoa`) VALUES
('Natura I', '2024-05-01', '2024-06-01', 'Izena eman zure argazkiak, naturaren edertasuna azaltzen dutenak.', '/logotipoak/natura1.jpg'),
('Urban I', '2024-06-15', '2024-07-15', 'Lor ditudan argazkiak hiriko paisaiak eta gune urbanoen edertasuna adierazten dute.', '/logotipoak/urban1.jpg'),
('Animaliak I', '2024-08-01', '2024-09-01', 'Argazkiak hartu gure animaliak, naturaren parte dira eta merezi dute ikusgarritasuna.', '/logotipoak/animaliak1.jpg'),
('Loreak I', '2024-09-15', '2024-10-15', 'Lore eta landareak azaltzen dituzten argazkiak, koloreak eta forma interesgarriak dituztenak.', '/logotipoak/loreak1.jpg');

-- Insertar participantes en competiciones específicas

-- Participantes para la competición "Natura I"
INSERT INTO `argazkiLehiaketa`.`Parte_hartu` (`erabiltzailea`, `lehiaketa`)
VALUES (1, 1), (2, 1), (3, 1), (4, 1), (5, 1);

-- Participantes para la competición "Urban I"
INSERT INTO `argazkiLehiaketa`.`Parte_hartu` (`erabiltzailea`, `lehiaketa`)
VALUES (6, 2), (7, 2), (8, 2), (9, 2), (10, 2);

-- Participantes para la competición "Animaliak I"
INSERT INTO `argazkiLehiaketa`.`Parte_hartu` (`erabiltzailea`, `lehiaketa`)
VALUES (11, 3), (12, 3), (13, 3), (14, 3), (15, 3);

-- Participantes para la competición "Loreak I"
INSERT INTO `argazkiLehiaketa`.`Parte_hartu` (`erabiltzailea`, `lehiaketa`)
VALUES (16, 4), (17, 4), (18, 4), (19, 4), (20, 4);

-- Otorgar acceso a todas las competiciones a los usuarios con ID 18 y 19

-- Obtener el ID de todas las competiciones
SET @lehiaketa_id_1 = (SELECT `lehiaketa_ID` FROM `argazkiLehiaketa`.`Lehiaketa` WHERE `izena` = 'Natura I');
SET @lehiaketa_id_2 = (SELECT `lehiaketa_ID` FROM `argazkiLehiaketa`.`Lehiaketa` WHERE `izena` = 'Urban I');
SET @lehiaketa_id_3 = (SELECT `lehiaketa_ID` FROM `argazkiLehiaketa`.`Lehiaketa` WHERE `izena` = 'Animaliak I');
SET @lehiaketa_id_4 = (SELECT `lehiaketa_ID` FROM `argazkiLehiaketa`.`Lehiaketa` WHERE `izena` = 'Loreak I');

-- Insertar temáticas para cada competición

-- Competición: Natura I
INSERT INTO `argazkiLehiaketa`.`Atala` (`izena`, `saria`, `lehiaketa_ID`)
VALUES ('Landareak', '1', @lehiaketa_id_1), ('Animaliak', '1', @lehiaketa_id_1), ('Paisaiak', '1', @lehiaketa_id_1);

-- Competición: Urban I
INSERT INTO `argazkiLehiaketa`.`Atala` (`izena`, `saria`, `lehiaketa_ID`)
VALUES ('Hiri-lurra', '1', @lehiaketa_id_2), ('Hiriburu', '1', @lehiaketa_id_2), ('Grafitiak', '1', @lehiaketa_id_2);

-- Competición: Animaliak I
INSERT INTO `argazkiLehiaketa`.`Atala` (`izena`, `saria`, `lehiaketa_ID`)
VALUES ('Mamalia', '1', @lehiaketa_id_3), ('Arrainak', '1', @lehiaketa_id_3), ('Insektuak', '1', @lehiaketa_id_3);

-- Competición: Loreak I
INSERT INTO `argazkiLehiaketa`.`Atala` (`izena`, `saria`, `lehiaketa_ID`)
VALUES ('Barbakoa', '1', @lehiaketa_id_4), ('Suharrak', '1', @lehiaketa_id_4), ('Zuhaitzak', '1', @lehiaketa_id_4);

-- Competición: Natura I
SET @lehiaketa_id_1 = (SELECT lehiaketa_ID FROM argazkiLehiaketa.Lehiaketa WHERE izena = 'Natura I');

-- Fases para Natura I
INSERT INTO argazkiLehiaketa.Fasea (etapa, data_hasiera, data_bukaera, lehiaketa)
VALUES ('Inskripzioa', '2024-05-01', '2024-05-31', @lehiaketa_id_1),
       ('Hautatzea', '2024-06-01', '2024-06-30', @lehiaketa_id_1),
       ('Finala', '2024-07-01', '2024-07-31', @lehiaketa_id_1);

-- Competición: Urban I
SET @lehiaketa_id_2 = (SELECT lehiaketa_ID FROM argazkiLehiaketa.Lehiaketa WHERE izena = 'Urban I');

-- Fases para Urban I
INSERT INTO argazkiLehiaketa.Fasea (etapa, data_hasiera, data_bukaera, lehiaketa)
VALUES ('Inskripzioa', '2024-06-01', '2024-06-30', @lehiaketa_id_2),
       ('Hautatzea', '2024-07-01', '2024-07-31', @lehiaketa_id_2),
       ('Finala', '2024-08-01', '2024-08-31', @lehiaketa_id_2);

-- Competición: Animaliak I
SET @lehiaketa_id_3 = (SELECT lehiaketa_ID FROM argazkiLehiaketa.Lehiaketa WHERE izena = 'Animaliak I');

-- Fases para Animaliak I
INSERT INTO argazkiLehiaketa.Fasea (etapa, data_hasiera, data_bukaera, lehiaketa)
VALUES ('Inskripzioa', '2024-07-01', '2024-07-31', @lehiaketa_id_3),
       ('Hautatzea', '2024-08-01', '2024-08-31', @lehiaketa_id_3),
       ('Finala', '2024-09-01', '2024-09-30', @lehiaketa_id_3);

-- Competición: Loreak I
SET @lehiaketa_id_4 = (SELECT lehiaketa_ID FROM argazkiLehiaketa.Lehiaketa WHERE izena = 'Loreak I');

-- Fases para Loreak I
INSERT INTO argazkiLehiaketa.Fasea (etapa, data_hasiera, data_bukaera, lehiaketa)
VALUES ('Inskripzioa', '2024-08-01', '2024-08-31', @lehiaketa_id_4),
       ('Hautatzea', '2024-09-01', '2024-09-30', @lehiaketa_id_4),
       ('Finala', '2024-10-01', '2024-10-31', @lehiaketa_id_4);
       
-- Competición: Natura I
INSERT INTO `argazkiLehiaketa`.`Argazkia` (`kokapena`, `botoak`, `egilea`, `atala`)
VALUES 
('/Landareak/imagen1.jpg', 0, FLOOR(RAND() * 15) + 1, 1),
('/Landareak/imagen2.jpg', 0, FLOOR(RAND() * 15) + 1, 1),
('/Landareak/imagen3.jpg', 0, FLOOR(RAND() * 15) + 1, 1),
('/Animaliak/imagen4.jpg', 0, FLOOR(RAND() * 15) + 1, 2),
('/Animaliak/imagen5.jpg', 0, FLOOR(RAND() * 15) + 1, 2),
('/Animaliak/imagen6.jpg', 0, FLOOR(RAND() * 15) + 1, 2),
('/Paisaiak/imagen7.jpg', 0, FLOOR(RAND() * 15) + 1, 3),
('/Paisaiak/imagen8.jpg', 0, FLOOR(RAND() * 15) + 1, 3),
('/Paisaiak/imagen9.jpg', 0, FLOOR(RAND() * 15) + 1, 3),

-- Competición: Urban I
('/Hiri-lurra/imagen10.jpg', 0, FLOOR(RAND() * 15) + 1, 4),
('/Hiri-lurra/imagen11.jpg', 0, FLOOR(RAND() * 15) + 1, 4),
('/Hiri-lurra/imagen12.jpg', 0, FLOOR(RAND() * 15) + 1, 4),
('/Hiriburu/imagen13.jpg', 0, FLOOR(RAND() * 15) + 1, 5),
('/Hiriburu/imagen14.jpg', 0, FLOOR(RAND() * 15) + 1, 5),
('/Hiriburu/imagen15.jpg', 0, FLOOR(RAND() * 15) + 1, 5),
('/Grafitiak/imagen16.jpg', 0, FLOOR(RAND() * 15) + 1, 6),
('/Grafitiak/imagen17.jpg', 0, FLOOR(RAND() * 15) + 1, 6),
('/Grafitiak/imagen18.jpg', 0, FLOOR(RAND() * 15) + 1, 6),

-- Competición: Animaliak I
('/Mamalia/imagen19.jpg', 0, FLOOR(RAND() * 15) + 1, 7),
('/Mamalia/imagen20.jpg', 0, FLOOR(RAND() * 15) + 1, 7),
('/Mamalia/imagen21.jpg', 0, FLOOR(RAND() * 15) + 1, 7),
('/Arrainak/imagen22.jpg', 0, FLOOR(RAND() * 15) + 1, 8),
('/Arrainak/imagen23.jpg', 0, FLOOR(RAND() * 15) + 1, 8),
('/Arrainak/imagen24.jpg', 0, FLOOR(RAND() * 15) + 1, 8),
('/Insektuak/imagen25.jpg', 0, FLOOR(RAND() * 15) + 1, 9),
('/Insektuak/imagen26.jpg', 0, FLOOR(RAND() * 15) + 1, 9),
('/Insektuak/imagen27.jpg', 0, FLOOR(RAND() * 15) + 1, 9),

-- Competición: Loreak I
('/Barbakoa/imagen28.jpg', 0, FLOOR(RAND() * 15) + 1, 10),
('/Barbakoa/imagen29.jpg', 0, FLOOR(RAND() * 15) + 1, 10),
('/Barbakoa/imagen30.jpg', 0, FLOOR(RAND() * 15) + 1, 10),
('/Suharrak/imagen31.jpg', 0, FLOOR(RAND() * 15) + 1, 11),
('/Suharrak/imagen32.jpg', 0, FLOOR(RAND() * 15) + 1, 11),
('/Suharrak/imagen33.jpg', 0, FLOOR(RAND() * 15) + 1, 11),
('/Zuhaitzak/imagen34.jpg', 0, FLOOR(RAND() * 15) + 1, 12),
('/Zuhaitzak/imagen35.jpg', 0, FLOOR(RAND() * 15) + 1, 12),
('/Zuhaitzak/imagen36.jpg', 0, FLOOR(RAND() * 15) + 1, 12);
       



