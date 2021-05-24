INSERT INTO `work_statuses` (`name`)
    VALUE ('NEW'), ('ON_CHECK'), ('COMPLETED');
GO

UPDATE `works` SET `workStatus_id` = (SELECT id FROM `work_statuses` WHERE `name` = 'NEW') WHERE (`client_name` = '11');
GO

UPDATE `works` SET `workStatus_id` = (SELECT id FROM `work_statuses` WHERE `name` = 'ON_CHECK') WHERE (`client_name` = '22');
GO

UPDATE `works` SET `workStatus_id` = (SELECT id FROM `work_statuses` WHERE `name` = 'COMPLETED') WHERE (`client_name` = '33');
GO

UPDATE `works` SET `workStatus_id` = (SELECT id FROM `work_statuses` WHERE `name` = 'ON_CHECK') WHERE (`client_name` = '44');
GO
