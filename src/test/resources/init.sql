SET REFERENTIAL_INTEGRITY FALSE;
truncate table ADMIN_SECURITY;
truncate table LOGS;
truncate table USERS;
SET REFERENTIAL_INTEGRITY TRUE;
insert into ADMIN_SECURITY(id, username, password) values (1, 'Admin', '$2a$12$LV62Vehuivc.rtG8e5AO3uGQNv5.tMEZy0NrKEQ2GAja2tK1p1EcO');
insert into USERS(id, username) values (1, 'Admin');
insert into USERS(id, username) values (2, 'User');
insert into LOGS(id, subject_user, action, object_user, action_time)
values (1, 1, 'User logged in application', null, {ts '2012-09-17 10:15:00.000'});
insert into LOGS(id, subject_user, action, object_user, action_time)
values (2, 1, 'User registered a new account of', 2, {ts '2012-09-17 10:16:00.000'});
