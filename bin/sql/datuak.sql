
-- Erabiltzaile datuak
INSERT INTO `Lehiaketa`.`Erabiltzailea` (`erabiltzaile_ID`, `izena`, `abizena`, `erabiltzaile_izena`, `pasahitza`, `epailea_izan`) VALUES
(1, 'Juan', 'López', 'juan_lopez', 'ju4nP4sS', 'bai'),
(2, 'María', 'García', 'maria84', 'MaRiA1984', 'ez'),
(3, 'David', 'Martínez', 'davidm', 'dav1dM', 'ez'),
(4, 'Ana', 'Fernández', 'anafernandez', 'An@F3rn@nd3z', 'bai'),
(5, 'Pedro', 'Rodríguez', 'pedror', 'p3dr0r0dr1g', 'ez'),
(6, 'Elena', 'Sánchez', 'elenas', '3len@S4nch3z', 'bai'),
(7, 'Carlos', 'Gómez', 'carlos99', 'C@rl0sG0m3z', 'ez'),
(8, 'Laura', 'Pérez', 'laurap', 'L4uR@PeR3z', 'ez'),
(9, 'Sara', 'López', 'saral', 'S@r@l0p3z', 'bai'),
(10, 'Javier', 'García', 'javigar', 'J@v1G@rc14', 'ez'),
(11, 'Lucía', 'Martín', 'luciam', 'Luc14M4rt1n', 'ez'),
(12, 'Pablo', 'González', 'pablog', 'P@bl0G0nz@l3z', 'bai'),
(13, 'Carmen', 'Rodríguez', 'carmenr', 'C@rm3nR0dr1gu3z', 'ez'),
(14, 'Manuel', 'Martínez', 'manuelm', 'M@nu3lM@rt1n3z', 'ez'),
(15, 'Silvia', 'Díaz', 'silviad', 'S1lv14D14z', 'bai'),
(16, 'Alonso', 'Ruiz', 'alonsor', '4l0ns0Ru1z', 'ez'),
(17, 'Marina', 'García', 'marinag', 'M@r1n@g@rc14', 'ez'),
(18, 'Roberto', 'Fernández', 'robertof', 'R0b3rt0F3rn@nd3z', 'ez'),
(19, 'Raquel', 'López', 'raquell', 'R@qu3LL0p3z', 'bai'),
(20, 'Andrea', 'Sánchez', 'andreas', '4ndr34S@nch3z', 'ez');

-- Lehiaketa datuak
INSERT INTO `Lehiaketa`.`Lehiaketa` (`lehiaketa_ID`, `lehiaketa_izena`, `data_hasiera`, `data_bukaera`, `Deskribapena`, `helbidea_logotipoa`) VALUES
(1, 'Euskal Argazki Lehiaketa 2024', '2024-05-01', '2024-05-30', 'Euskal argazkilarien arteko lehiaketa.', '/lehiaketak/euskal_argazki_lehiaketa_logo.png'),
(2, 'Herriko Argazki Txapelketa', '2024-08-20', '2024-09-15', 'Herriko argazki lehiaketa.', '/lehiaketak/herriko_argazki_txapelketa_logo.png'),
(3, 'Txakur Lehiaketa 2024', '2024-06-10', '2024-06-15', 'Txakur bat baino gehiago duen argazki lehiaketa.', '/lehiaketak/txakur_lehiaketa_logo.png'),
(4, 'Landare Lehiaketa 2024', '2024-07-10', '2024-07-20', 'Landareen argazki lehiaketa.', '/lehiaketak/landare_lehiaketa_logo.png'),
(5, 'Bazkideen Elkartearen Argazki Lehiaketa 2024', '2024-09-10', '2024-09-30', 'Bazkideen elkartearen argazki lehiaketa.', '/lehiaketak/bazkideen_elkartearen_argazki_lehiaketa_logo.png');



-- Euskal Argazki Lehiaketa 2024
INSERT INTO `Lehiaketa`.`Faseak` (`ID_Fasea`, `etapa`, `data_hasiera`, `data_bukaera`, `Lehiaketa_ID`) VALUES
(1, 'Hautaketa fasea', '2024-05-10', '2024-05-20', 1),
(2, 'Inskripzioak', '2024-05-01', '2024-05-09', 1),
(3, 'Botoak', '2024-05-28', '2024-05-30', 1),
(4, 'Finala', '2024-05-25', '2024-05-30', 1);

-- Herriko Argazki Txapelketa
INSERT INTO `Lehiaketa`.`Faseak` (`ID_Fasea`, `etapa`, `data_hasiera`, `data_bukaera`, `Lehiaketa_ID`) VALUES
(5, 'Txapelketa hasiera', '2024-08-20', '2024-09-05', 2),
(6, 'Inskripzioak', '2024-08-10', '2024-08-19', 2),
(7, 'Botoak', '2024-09-12', '2024-09-14', 2),
(8, 'Txapelketa amaiera', '2024-09-10', '2024-09-15', 2);

-- Txakur Lehiaketa 2024
INSERT INTO `Lehiaketa`.`Faseak` (`ID_Fasea`, `etapa`, `data_hasiera`, `data_bukaera`, `Lehiaketa_ID`) VALUES
(9, 'Argazki bidaltzea', '2024-06-10', '2024-06-12', 3),
(10, 'Inskripzioak', '2024-06-01', '2024-06-09', 3),
(11, 'Botoak', '2024-06-18', '2024-06-20', 3),
(12, 'Txapelketaren emaitzak', '2024-06-15', '2024-06-20', 3);

-- Landare Lehiaketa 2024
INSERT INTO `Lehiaketa`.`Faseak` (`ID_Fasea`, `etapa`, `data_hasiera`, `data_bukaera`, `Lehiaketa_ID`) VALUES
(13, 'Argazkiak bidaltzea', '2024-07-10', '2024-07-15', 4),
(14, 'Inskripzioak', '2024-07-01', '2024-07-09', 4),
(15, 'Botoak', '2024-07-18', '2024-07-20', 4),
(16, 'Hautaketa eta epaimahaia', '2024-07-17', '2024-07-20', 4);

-- Bazkideen Elkartearen Argazki Lehiaketa 2024
INSERT INTO `Lehiaketa`.`Faseak` (`ID_Fasea`, `etapa`, `data_hasiera`, `data_bukaera`, `Lehiaketa_ID`) VALUES
(17, 'Argazkiak bidaltzea', '2024-09-10', '2024-09-20', 5),
(18, 'Inskripzioak', '2024-09-01', '2024-09-09', 5),
(19, 'Botoak', '2024-09-28', '2024-09-30', 5),
(20, 'Txapelketaren irabazleak', '2024-09-25', '2024-09-30', 5);

---------------- ATALAK -----------------

-- Euskal Argazki Lehiaketa 2024 - Kategoriak
INSERT INTO `Lehiaketa`.`Atala` (`atala_ID`, `izena`, `saria`, `helbidea_Irudiak`, `argazki_maximo`, `lehiaketa_ID`, `Irabazlea`) VALUES
(1, 'Natura Paisaia', 'Euskal kostaldean bidaia', '/atalak/paisaia_naturala/', 10, 1, NULL),
(2, 'Hiri-bizitza', 'Casa-Julian jatetxean afaria', '/atalak/urbanoa/', 10, 1, NULL),
(3, 'Erretratuak', 'Richard argazki dendan dohainikoa-txartela', '/atalak/retratoa/', 10, 1, NULL);

-- Herriko Argazki Txapelketa - Kategoriak
INSERT INTO `Lehiaketa`.`Atala` (`atala_ID`, `izena`, `saria`, `helbidea_Irudiak`, `argazki_maximo`, `lehiaketa_ID`, `Irabazlea`) VALUES
(4, 'Herriko Bizikidetza', 'Herriko bazkaria dohainik 4 pertsonentzako', '/atalak/herriko_bizikidetza/', 20, 2, NULL),
(5, 'Herriko Txokoak', 'Herriko zesta (100 euro baloratua)', '/atalak/herriko_txokoak/', 20, 2, NULL),
(6, 'Herriko Paisaia', '100 euro', '/atalak/herriko_paisaia/', 20, 2, NULL);

-- Txakur Lehiaketa 2024 - Kategoriak
INSERT INTO `Lehiaketa`.`Atala` (`atala_ID`, `izena`, `saria`, `helbidea_Irudiak`, `argazki_maximo`, `lehiaketa_ID`, `Irabazlea`) VALUES
(7, 'Parkeko Txakurrak', 'Urte bateko bonua txakur janarirako', '/atalak/txakurak_parkean/', 10, 3, NULL),
(8, 'Hondartzako Txakurrak', '200 euro', '/atalak/txakurak_hondartzan/', 10, 3, NULL);

-- Landare Lehiaketa 2024 - Kategoriak
INSERT INTO `Lehiaketa`.`Atala` (`atala_ID`, `izena`, `saria`, `helbidea_Irudiak`, `argazki_maximo`, `lehiaketa_ID`, `Irabazlea`) VALUES
(9, 'Parkeko Landareak', 'Landare-jardineria kit profesionala', '/atalak/landareak_parkean/', 10, 4, NULL),
(10, 'Etxeko Landareak', '100 euro', '/atalak/landareak_etxean/', 10, 4, NULL);

-- Bazkideen Elkartearen Argazki Lehiaketa 2024 - Kategoriak
INSERT INTO `Lehiaketa`.`Atala` (`atala_ID`, `izena`, `saria`, `helbidea_Irudiak`, `argazki_maximo`, `lehiaketa_ID`, `Irabazlea`) VALUES
(11, 'Mendiak', 'Pireneoen bailaraerdiko etxeko buelta-astea rurala', '/atalak/mendiak/', 5, 5, NULL),
(12, 'Landareak Zelaietan', '200 euro', '/atalak/landareak_zelaietan/', 5, 5, NULL);

-- Euskal Argazki Lehiaketa 2024
INSERT INTO `Lehiaketa`.`Parte_hartu` (`Erabiltzaileak_erabiltzaile_ID`, `Lehiaketa_lehiaketa_ID`) VALUES
(1, 1),  -- User 1 participating in competition 1 (Euskal Argazki Lehiaketa 2024)
(2, 1),  -- User 2 participating in competition 1
(3, 1),  -- User 3 participating in competition 1
(4, 1),  -- User 4 participating in competition 1
(5, 1);  -- User 5 participating in competition 1

-- Herriko Argazki Txapelketa - Users participating
INSERT INTO `Lehiaketa`.`Parte_hartu` (`Erabiltzaileak_erabiltzaile_ID`, `Lehiaketa_lehiaketa_ID`) VALUES
(6, 2),  -- User 6 participating in competition 2 (Herriko Argazki Txapelketa)
(7, 2);  -- User 7 participating in competition 2

-- Txakur Lehiaketa 2024 - Users participating
INSERT INTO `Lehiaketa`.`Parte_hartu` (`Erabiltzaileak_erabiltzaile_ID`, `Lehiaketa_lehiaketa_ID`) VALUES
(8, 3),  -- User 8 participating in competition 3 (Txakur Lehiaketa 2024)
(9, 3);  -- User 9 participating in competition 3

-- Landare Lehiaketa 2024 - Users participating
INSERT INTO `Lehiaketa`.`Parte_hartu` (`Erabiltzaileak_erabiltzaile_ID`, `Lehiaketa_lehiaketa_ID`) VALUES
(10, 4); -- User 10 participating in competition 4 (Landare Lehiaketa 2024)

-- Bazkideen Elkartearen Argazki Lehiaketa 2024 - Users participating
INSERT INTO `Lehiaketa`.`Parte_hartu` (`Erabiltzaileak_erabiltzaile_ID`, `Lehiaketa_lehiaketa_ID`) VALUES
(11, 5), -- User 11 participating in competition 5 (Bazkideen Elkartearen Argazki Lehiaketa 2024)
(12, 5), -- User 12 participating in competition 5
(13, 6); -- User 13 participating in competition 6 (Euskal Argazki Lehiaketa 2024)






