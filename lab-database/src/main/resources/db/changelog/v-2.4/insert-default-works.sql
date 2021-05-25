INSERT INTO `works` (`client_name`, `customer`, `number_contract`, `object_name`, `Registration_date`, `actual`)
VALUES ('11', '11', '11', '11', '2021-01-01', true ),
       ('22', '22', '22', '22', '2021-01-01', true ),
       ('33', '33', '33', '33', '2021-01-01', false ),
       ('44', '44', '44', '44', '2021-01-01', false );
GO

INSERT INTO `users_works`(`user_id`, `work_id`)
VALUE
((SELECT id FROM `users` WHERE `last_name` = 'executor'), (SELECT id FROM `works` WHERE `client_name` = '11')),
((SELECT id FROM `users` WHERE `last_name` = 'executor'), (SELECT id FROM `works` WHERE `client_name` = '22')),
((SELECT id FROM `users` WHERE `last_name` = 'executor'), (SELECT id FROM `works` WHERE `client_name` = '33')),
((SELECT id FROM `users` WHERE `last_name` = 'executor3'), (SELECT id FROM `works` WHERE `client_name` = '11')),
((SELECT id FROM `users` WHERE `last_name` = 'executor2'), (SELECT id FROM `works` WHERE `client_name` = '44')),
((SELECT id FROM `users` WHERE `last_name` = 'executor1'), (SELECT id FROM `works` WHERE `client_name` = '22'));


GO