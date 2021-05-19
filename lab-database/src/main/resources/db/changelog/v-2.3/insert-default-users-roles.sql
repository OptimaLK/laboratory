INSERT INTO `users` (`last_name`, `password`, `phone`)
    VALUE   ('executor1', '$2a$10$uedJ6jkBS08x5mxZY6gV6.LAKSd202CiVutxz5VDq3TIyj9alkmIq', '89639567916'),
            ('executor2', '$2a$10$uedJ6jkBS08x5mxZY6gV6.LAKSd202CiVutxz5VDq3TIyj9alkmIq', '89639567916'),
            ('executor3', '$2a$10$uedJ6jkBS08x5mxZY6gV6.LAKSd202CiVutxz5VDq3TIyj9alkmIq', '89639567916'),
            ('executor4', '$2a$10$uedJ6jkBS08x5mxZY6gV6.LAKSd202CiVutxz5VDq3TIyj9alkmIq', '89639567916');
GO

INSERT INTO `users_roles`(`user_id`, `role_id`)
VALUE
((SELECT id FROM `users` WHERE `last_name` = 'executor1'), (SELECT id FROM `roles` WHERE `name` = 'ROLE_EXECUTOR')),
((SELECT id FROM `users` WHERE `last_name` = 'executor2'), (SELECT id FROM `roles` WHERE `name` = 'ROLE_EXECUTOR')),
((SELECT id FROM `users` WHERE `last_name` = 'executor3'), (SELECT id FROM `roles` WHERE `name` = 'ROLE_EXECUTOR')),
((SELECT id FROM `users` WHERE `last_name` = 'executor4'), (SELECT id FROM `roles` WHERE `name` = 'ROLE_EXECUTOR'));

GO
