INSERT INTO `categories` (`name`)
    VALUE ('Освещение'), ('Микроклимат'), ('Шум, вибрация, инфр'), ('Не оиниз.'), ('Иониз. излучения'), ('Хим.факторы'), ('Иное');
GO

UPDATE `equipments` SET `category_id` = (SELECT id FROM `categories` WHERE `name` = 'Освещение') WHERE (`name` = 'Газосигнализатор мультигазовый «Комета-М»');
GO

UPDATE `equipments` SET `category_id` = (SELECT id FROM `categories` WHERE `name` = 'Микроклимат') WHERE (`name` = 'Анализатор аэрозоля KANOMAX, модель 3521');
GO

UPDATE `equipments` SET `category_id` = (SELECT id FROM `categories` WHERE `name` = 'Шум, вибрация, инфр') WHERE (`name` =  'Газоанализатор "Геолан-1П"');
GO

UPDATE `equipments` SET `category_id` =(SELECT id FROM `categories` WHERE `name` = 'Не оиниз.') WHERE (`name` =  'Секундомер СОСпр-2б-2-010');
GO

UPDATE `equipments` SET `category_id` = (SELECT id FROM `categories` WHERE `name` = 'Иониз. излучения') WHERE (`name` =  'Ротаметр от 1,0 до 20,0 л/мин');
GO

UPDATE `equipments` SET `category_id` = (SELECT id FROM `categories` WHERE `name` = 'Хим.факторы') WHERE (`name` =  'Весы лабораторные AF-R220CE');
GO

UPDATE `equipments` SET `category_id` = (SELECT id FROM `categories` WHERE `name` = 'Иное') WHERE (`name` =  'Спектрофотометр ПЭ-5400УФ');
GO

UPDATE `equipments` SET `category_id` = (SELECT id FROM `categories` WHERE `name` = 'Иониз. излучения') WHERE (`name` =  'Газоанализатор ЭКОЛАБ');
GO

UPDATE `equipments` SET `category_id` = (SELECT id FROM `categories` WHERE `name` = 'Хим.факторы') WHERE (`name` =  'МАРК-603/1 с ДП -3 № 2049');
GO

UPDATE `equipments` SET `category_id` = (SELECT id FROM `categories` WHERE `name` = 'Освещение') WHERE (`name` =  'Аспиратор ПУ-4Э исп.1');
GO