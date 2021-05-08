INSERT INTO `users` (`last_name`, `password`, `phone`)
    VALUE   ('director', '$2a$10$uedJ6jkBS08x5mxZY6gV6.LAKSd202CiVutxz5VDq3TIyj9alkmIq', '89639567916'),
            ('chief', '$2a$10$uedJ6jkBS08x5mxZY6gV6.LAKSd202CiVutxz5VDq3TIyj9alkmIq', '89639567916'),
            ('executor', '$2a$10$uedJ6jkBS08x5mxZY6gV6.LAKSd202CiVutxz5VDq3TIyj9alkmIq', '89639567916'),
            ('secretary', '$2a$10$uedJ6jkBS08x5mxZY6gV6.LAKSd202CiVutxz5VDq3TIyj9alkmIq', '89639567916');
GO

INSERT INTO `roles` (`name`)
VALUE ('ROLE_DIRECTOR'), ('ROLE_CHIEF'), ('ROLE_EXECUTOR'), ('ROLE_SECRETARY');
GO

INSERT INTO `users_roles`(`user_id`, `role_id`)
VALUE
((SELECT id FROM `users` WHERE `last_name` = 'director'), (SELECT id FROM `roles` WHERE `name` = 'ROLE_DIRECTOR')),
((SELECT id FROM `users` WHERE `last_name` = 'chief'), (SELECT id FROM `roles` WHERE `name` = 'ROLE_CHIEF')),
((SELECT id FROM `users` WHERE `last_name` = 'executor'), (SELECT id FROM `roles` WHERE `name` = 'ROLE_EXECUTOR')),
((SELECT id FROM `users` WHERE `last_name` = 'secretary'), (SELECT id FROM `roles` WHERE `name` = 'ROLE_SECRETARY')),
((SELECT id FROM `users` WHERE `last_name` = 'chief'), (SELECT id FROM `roles` WHERE `name` = 'ROLE_EXECUTOR'));

GO
