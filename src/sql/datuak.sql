INSERT INTO `argazkiLehiaketa`.`Erabiltzailea` (`izena`, `abizena`, `korreoa`, `erabiltzaile_izena`, `pasahitza`, `mota`) VALUES
('Ane', 'Lopez', 'ane.lopez@example.com', 'anelo', 'contraseña123', 'arrunta'),
('Mikel', 'Garcia', 'mikel.garcia@example.com', 'mikgar', 'p4ssw0rd', 'arrunta'),
('Jone', 'Martinez', 'jone.martinez@example.com', 'jmartinez', '12345678', 'arrunta'),
('Unai', 'Fernandez', 'unai.fernandez@example.com', 'unaif', 'password', 'arrunta'),
('Leire', 'Sanchez', 'leire.sanchez@example.com', 'leisan', 'abcd1234', 'arrunta'),
('Iker', 'Rodriguez', 'iker.rodriguez@example.com', 'ikerod', 'qwerty123', 'arrunta'),
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

-- Insertar participaciones para el usuario con ID 18
INSERT INTO `argazkiLehiaketa`.`Parte_hartu` (`erabiltzailea`, `lehiaketa`)
VALUES (18, @lehiaketa_id_1), (18, @lehiaketa_id_2), (18, @lehiaketa_id_3), (18, @lehiaketa_id_4);

-- Insertar participaciones para el usuario con ID 19
INSERT INTO `argazkiLehiaketa`.`Parte_hartu` (`erabiltzailea`, `lehiaketa`)
VALUES (19, @lehiaketa_id_1), (19, @lehiaketa_id_2), (19, @lehiaketa_id_3), (19, @lehiaketa_id_4);

-- Insertar temáticas para cada competición

-- Competición: Natura I
INSERT INTO `argazkiLehiaketa`.`Atala` (`izena`, `saria`, `argazki_max`, `lehiaketa_ID`)
VALUES ('Planta', '1', 10, @lehiaketa_id_1), ('Animalia', '1', 10, @lehiaketa_id_1), ('Paisaia', '1', 10, @lehiaketa_id_1);

-- Competición: Urban I
INSERT INTO `argazkiLehiaketa`.`Atala` (`izena`, `saria`, `argazki_max`, `lehiaketa_ID`)
VALUES ('Hiri-lurra', '1', 10, @lehiaketa_id_2), ('Hiriburu', '1', 10, @lehiaketa_id_2), ('Grafitiak', '1', 10, @lehiaketa_id_2);

-- Competición: Animaliak I
INSERT INTO `argazkiLehiaketa`.`Atala` (`izena`, `saria`, `argazki_max`, `lehiaketa_ID`)
VALUES ('Mamalia', '1', 10, @lehiaketa_id_3), ('Arrainak', '1', 10, @lehiaketa_id_3), ('Insektuak', '1', 10, @lehiaketa_id_3);

-- Competición: Loreak I
INSERT INTO `argazkiLehiaketa`.`Atala` (`izena`, `saria`, `argazki_max`, `lehiaketa_ID`)
VALUES ('Barbakoa', '1', 10, @lehiaketa_id_4), ('Suharrak', '1', 10, @lehiaketa_id_4), ('Zuhaitzak', '1', 10, @lehiaketa_id_4);

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



