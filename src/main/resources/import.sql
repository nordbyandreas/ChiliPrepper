-- Insert role
insert into role (name) values ('ROLE_USER');

-- Insert two users (passwords are both 'password')
insert into user (username,enabled,password,role_id, email) values ('user',true,'$2a$08$wgwoMKfYl5AUE9QtP4OjheNkkSDoqDmFGjjPE2XTPLDe9xso/hy7u',1, 'example@example.com');
insert into user (username,enabled,password,role_id, email) values ('user2',true,'$2a$08$wgwoMKfYl5AUE9QtP4OjheNkkSDoqDmFGjjPE2XTPLDe9xso/hy7u',1, 'test@test.no');

insert into course (courseName, accessCode, user_id) values ('testCourse', 'thePassword', 1);
insert into course (courseName, accessCode, user_id) values ('testTestCourse', 'theword', 1);
insert into course (courseName, accessCode, user_id) values ('othertestCourse', 'otherPassword', 2);
insert into course (courseName, accessCode, user_id) values ('osetest', 'otestord', 2);


