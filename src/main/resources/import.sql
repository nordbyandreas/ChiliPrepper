-- Insert role
insert into role (name) values ('ROLE_USER');

-- Insert two users (passwords are both 'password')
insert into user (username,enabled,password,role_id, email) values ('user',true,'$2a$08$wgwoMKfYl5AUE9QtP4OjheNkkSDoqDmFGjjPE2XTPLDe9xso/hy7u',1, 'chiliprepper.bot@gmail.com');
insert into user (username,enabled,password,role_id, email) values ('user2',true,'$2a$08$wgwoMKfYl5AUE9QtP4OjheNkkSDoqDmFGjjPE2XTPLDe9xso/hy7u',1, 'chiliprepper.bot@gmail.com');
insert into user (username,enabled,password,role_id, email) values ('user3',true,'$2a$08$wgwoMKfYl5AUE9QtP4OjheNkkSDoqDmFGjjPE2XTPLDe9xso/hy7u',1, 'chiliprepper.bot@gmail.com');
insert into user (username,enabled,password,role_id, email) values ('user4',true,'$2a$08$wgwoMKfYl5AUE9QtP4OjheNkkSDoqDmFGjjPE2XTPLDe9xso/hy7u',1, 'chiliprepper.bot@gmail.com');


insert into course (courseName, accessCode, user_id) values ('testCourse', 'thePassword', 1);
insert into course (courseName, accessCode, user_id) values ('testTestCourse', 'theword', 1);
insert into course (courseName, accessCode, user_id) values ('othertestCourse', 'otherPassword', 2);
insert into course (courseName, accessCode, user_id) values ('osetest', 'otestord', 2);



insert into quiz (quiz_name, published, course_id) values ('Databaser', true, 1);
insert into quiz (quiz_name, published, course_id) values ('MMI', true, 1);
insert into quiz (quiz_name, published, course_id) values ('PVU', false, 1);
insert into quiz (quiz_name, published, course_id) values ('testQuiz', true, 2);
insert into quiz (quiz_name, published, course_id) values ('film', false, 2);
insert into quiz (quiz_name, published, course_id) values ('sport', true, 3);
insert into quiz (quiz_name, published, course_id) values ('bøker', true, 4);




insert into question (topic, theQuestion, correct_answer, quiz_id) values ('SQL', 'Hva er en svak entitetsklasse?', 'En klasse som ikke har noen unike attributter', 1);
insert into question (topic, theQuestion, correct_answer, quiz_id) values ('SQL', 'Hvorfor bruker man CASCADE?', 'For å unngå at en tabell kan referere til noe som ikke finnes lengre', 1);
insert into question (topic, theQuestion, correct_answer, quiz_id) values ('SQL', 'Hva er en database?', 'Lagret informasjon, spesifikasjon og rammeverk for å manipulere data etc.', 1);
insert into question (topic, theQuestion, correct_answer, quiz_id) values ('MMI', 'Hvem er Von Neumann?', 'En designer', 2);
insert into question (topic, theQuestion, correct_answer, quiz_id) values ('MMI', 'Hvilke av disse er et designprinsipp?', 'Affordance', 2);
insert into question (topic, theQuestion, correct_answer, quiz_id) values ('MMI', 'Test spørsmaal', 'Riktig', 2);



insert into alternative (alternative, question_id) values ('En klasse som ikke er sterk', 1);
insert into alternative (alternative, question_id) values ('En relasjonsklasse', 1);
insert into alternative (alternative, question_id) values ('Feil svar', 1);

insert into alternative (alternative, question_id) values ('For å validere input', 2);
insert into alternative (alternative, question_id) values ('For å gjøre ting mer komplisert', 2);
insert into alternative (alternative, question_id) values ('For å bruke mindre lagringsplass', 2);

insert into alternative (alternative, question_id) values ('en base', 3);
insert into alternative (alternative, question_id) values ('En base i baseballl hvor man lagrer data', 3);
insert into alternative (alternative, question_id) values ('En militærbase', 3);

insert into alternative (alternative, question_id) values ('En filosof', 4);
insert into alternative (alternative, question_id) values ('En software ingenør', 4);
insert into alternative (alternative, question_id) values ('En mann', 4);

insert into alternative (alternative, question_id) values ('Coolness', 5);
insert into alternative (alternative, question_id) values ('Hotness', 5);
insert into alternative (alternative, question_id) values ('Greenability', 5);



insert into answer (correct, answer, question_id, quiz_id, course_id, user_id) values (false, 'En klasse som ikke har noen unike attributter', 1, 1, 1, 2);
insert into answer (correct, answer, question_id, quiz_id, course_id, user_id) values (false, 'En klasse som ikke er sterk', 1, 1, 1, 3);
insert into answer (correct, answer, question_id, quiz_id, course_id, user_id) values (false, 'En klasse som ikke er sterk', 1, 1, 1, 4);



insert into answer (correct, answer, question_id, quiz_id, course_id, user_id) values (true, 'For å unngå at en tabell kan referere til noe som ikke finnes lengre', 2, 1, 1, 3);
insert into answer (correct, answer, question_id, quiz_id, course_id, user_id) values (false, 'For å unngå at en tabell kan referere til noe som ikke finnes lengre', 2, 1, 1, 2);
insert into answer (correct, answer, question_id, quiz_id, course_id, user_id) values (true, 'For å unngå at en tabell kan referere til noe som ikke finnes lengre', 2, 1, 1, 4);


insert into answer (correct, answer, question_id, quiz_id, course_id, user_id) values (true, 'Lagret informasjon, spesifikasjon og rammeverk for å manipulere data etc.', 3, 1, 1, 3);
insert into answer (correct, answer, question_id, quiz_id, course_id, user_id) values (false, 'Lagret informasjon, spesifikasjon og rammeverk for å manipulere data etc.', 3, 1, 1, 2);
insert into answer (correct, answer, question_id, quiz_id, course_id, user_id) values (true, 'Lagret informasjon, spesifikasjon og rammeverk for å manipulere data etc.', 3, 1, 1, 4);



