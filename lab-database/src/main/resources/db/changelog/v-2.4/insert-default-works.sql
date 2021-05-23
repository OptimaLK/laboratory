INSERT INTO `works` (`client_name`, `customer`, `number_contract`, `object_name`, `Registration_date`, `status`)
VALUES ('11', '11', '11', '11', '2021-01-01', '0' ),
       ('22', '22', '22', '22', '2021-01-01', '0' ),
       ('33', '33', '33', '33', '2021-01-01', '2' ),
       ('44', '44', '44', '44', '2021-01-01', '3' );
GO

INSERT INTO `users_works`(`user_id`, `work_id`)
VALUE
((SELECT id FROM `users` WHERE `last_name` = 'executor'), (SELECT id FROM `works` WHERE `client_name` = '11')),
((SELECT id FROM `users` WHERE `last_name` = 'executor'), (SELECT id FROM `works` WHERE `client_name` = '22')),
((SELECT id FROM `users` WHERE `last_name` = 'executor'), (SELECT id FROM `works` WHERE `client_name` = '33')),
((SELECT id FROM `users` WHERE `last_name` = 'executor3'), (SELECT id FROM `works` WHERE `client_name` = '11')),
((SELECT id FROM `users` WHERE `last_name` = 'executor'), (SELECT id FROM `works` WHERE `client_name` = '44')),
((SELECT id FROM `users` WHERE `last_name` = 'executor1'), (SELECT id FROM `works` WHERE `client_name` = '22'));


GO