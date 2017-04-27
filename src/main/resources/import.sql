-- Insert role
insert into role (name) values ('ROLE_USER');

-- Insert two users (passwords are both 'password')
insert into user (username,enabled,password,role_id, email, creator_quiz_results, creator_course_update, participant_quiz_results, participant_topic_update) values ('user',true,'$2a$08$wgwoMKfYl5AUE9QtP4OjheNkkSDoqDmFGjjPE2XTPLDe9xso/hy7u',1, 'chiliprepper.bot@gmail.com', false, false, false, false);
insert into user (username,enabled,password,role_id, email, creator_quiz_results, creator_course_update, participant_quiz_results, participant_topic_update) values ('user2',true,'$2a$08$wgwoMKfYl5AUE9QtP4OjheNkkSDoqDmFGjjPE2XTPLDe9xso/hy7u',1, 'chiliprepper.bot@gmail.com', false, false, false, false);
insert into user (username,enabled,password,role_id, email, creator_quiz_results, creator_course_update, participant_quiz_results, participant_topic_update) values ('user3',true,'$2a$08$wgwoMKfYl5AUE9QtP4OjheNkkSDoqDmFGjjPE2XTPLDe9xso/hy7u',1, 'chiliprepper.bot@gmail.com', false, false, false, false);
insert into user (username,enabled,password,role_id, email, creator_quiz_results, creator_course_update, participant_quiz_results, participant_topic_update) values ('user4',true,'$2a$08$wgwoMKfYl5AUE9QtP4OjheNkkSDoqDmFGjjPE2XTPLDe9xso/hy7u',1, 'chiliprepper.bot@gmail.com', false, false, false, false);
insert into user (username,enabled,password,role_id, email, creator_quiz_results, creator_course_update, participant_quiz_results, participant_topic_update) values ('user5',true,'$2a$08$wgwoMKfYl5AUE9QtP4OjheNkkSDoqDmFGjjPE2XTPLDe9xso/hy7u',1, 'chiliprepper.bot@gmail.com', false, false, false, false);



insert into course (courseName, accessCode, user_id) values ('Software development', 'thePassword', 1);
insert into course (courseName, accessCode, user_id) values ('testTestCourse', 'theword', 1);
insert into course (courseName, accessCode, user_id) values ('brukertest', '1234', 2);
insert into course (courseName, accessCode, user_id) values ('osetest', 'otestord', 2);



insert into quiz (quiz_name, published, course_id) values ('Computers', true, 1);
insert into quiz (quiz_name, published, course_id) values ('MMI', true, 1);
insert into quiz (quiz_name, published, course_id) values ('PVU', false, 1);
insert into quiz (quiz_name, published, course_id) values ('testQuiz', true, 2);
insert into quiz (quiz_name, published, course_id) values ('film', false, 2);
insert into quiz (quiz_name, published, course_id) values ('Brukertest Quiz', true, 3);
insert into quiz (quiz_name, published, course_id) values ('bøker', true, 4);




insert into question (topic, theQuestion, correct_answer, quiz_id) values ('SQL', 'What does CPU stand for?', 'Central Processing Unit', 1);
insert into question (topic, theQuestion, correct_answer, quiz_id) values ('SQL', 'Hvorfor bruker man CASCADE?', 'For å unngå at en tabell kan referere til noe som ikke finnes lengre', 1);
insert into question (topic, theQuestion, correct_answer, quiz_id) values ('SQL', 'Hva er en database?', 'Lagret informasjon, spesifikasjon og rammeverk for å manipulere data etc.', 1);
insert into question (topic, theQuestion, correct_answer, quiz_id) values ('MMI', 'Hvem er Von Neumann?', 'En designer', 2);
insert into question (topic, theQuestion, correct_answer, quiz_id) values ('MMI', 'Hvilke av disse er et designprinsipp?', 'Affordance', 2);

insert into question (topic, theQuestion, correct_answer, quiz_id) values ('brukertest', 'Hva er 2+2 ??', '4', 6);
insert into question (topic, theQuestion, correct_answer, quiz_id) values ('brukertest', 'Hva er 16-1?', '15', 6);
insert into question (topic, theQuestion, correct_answer, quiz_id) values ('brukertest', 'Hva er 1-1?', '0', 6);




insert into alternative (alternative, question_id) values ('Central Process Unit', 1);
insert into alternative (alternative, question_id) values ('Centralize Process Unit', 1);
insert into alternative (alternative, question_id) values ('Central Processing Unity', 1);

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

insert into alternative (alternative, question_id) values ('2', 6);
insert into alternative (alternative, question_id) values ('1', 6);
insert into alternative (alternative, question_id) values ('5', 6);

insert into alternative (alternative, question_id) values ('17', 7);
insert into alternative (alternative, question_id) values ('-15', 7);
insert into alternative (alternative, question_id) values ('16', 7);

insert into alternative (alternative, question_id) values ('1', 8);
insert into alternative (alternative, question_id) values ('ingenting', 8);
insert into alternative (alternative, question_id) values ('2', 8);



insert into answer (correct, answer, question_id, quiz_id, course_id, user_id) values (true, 'En klasse som ikke har noen unike attributter', 1, 1, 1, 2);
insert into answer (correct, answer, question_id, quiz_id, course_id, user_id) values (false, 'En klasse som ikke er sterk', 1, 1, 1, 3);
insert into answer (correct, answer, question_id, quiz_id, course_id, user_id) values (false, 'En klasse som ikke er sterk', 1, 1, 1, 4);



insert into answer (correct, answer, question_id, quiz_id, course_id, user_id) values (false, 'For å unngå at en tabell kan referere til noe som ikke finnes lengre', 2, 1, 1, 3);
insert into answer (correct, answer, question_id, quiz_id, course_id, user_id) values (true, 'For å unngå at en tabell kan referere til noe som ikke finnes lengre', 2, 1, 1, 2);
insert into answer (correct, answer, question_id, quiz_id, course_id, user_id) values (true, 'For å unngå at en tabell kan referere til noe som ikke finnes lengre', 2, 1, 1, 4);


insert into answer (correct, answer, question_id, quiz_id, course_id, user_id) values (false, 'Lagret informasjon, spesifikasjon og rammeverk for å manipulere data etc.', 3, 1, 1, 3);
insert into answer (correct, answer, question_id, quiz_id, course_id, user_id) values (true, 'Lagret informasjon, spesifikasjon og rammeverk for å manipulere data etc.', 3, 1, 1, 2);
insert into answer (correct, answer, question_id, quiz_id, course_id, user_id) values (true, 'Lagret informasjon, spesifikasjon og rammeverk for å manipulere data etc.', 3, 1, 1, 4);








insert into answer (correct, answer, question_id, quiz_id, course_id, user_id) values (true, 'Lagret informasjon, spesifikasjon og rammeverk for å manipulere data etc.', 4, 2, 1, 2);
insert into answer (correct, answer, question_id, quiz_id, course_id, user_id) values (true, 'Lagret informasjon, spesifikasjon og rammeverk for å manipulere data etc.', 4, 2, 1, 3);
insert into answer (correct, answer, question_id, quiz_id, course_id, user_id) values (true, 'Lagret informasjon, spesifikasjon og rammeverk for å manipulere data etc.', 4, 2, 1, 4);

insert into answer (correct, answer, question_id, quiz_id, course_id, user_id) values (true, 'Lagret informasjon, spesifikasjon og rammeverk for å manipulere data etc.', 5, 2, 1, 2);
insert into answer (correct, answer, question_id, quiz_id, course_id, user_id) values (true, 'Lagret informasjon, spesifikasjon og rammeverk for å manipulere data etc.', 5, 2, 1, 3);
insert into answer (correct, answer, question_id, quiz_id, course_id, user_id) values (true, 'Lagret informasjon, spesifikasjon og rammeverk for å manipulere data etc.', 5, 2, 1, 4);



insert into answer (correct, answer, question_id, quiz_id, course_id, user_id) values (true, '4', 6, 6, 3, 2);
insert into answer (correct, answer, question_id, quiz_id, course_id, user_id) values (false, '16', 7, 6, 3, 3);
insert into answer (correct, answer, question_id, quiz_id, course_id, user_id) values (false, '1', 8, 6, 3, 4);

insert into answer (correct, answer, question_id, quiz_id, course_id, user_id) values (true, '4', 6, 6, 3, 2);
insert into answer (correct, answer, question_id, quiz_id, course_id, user_id) values (true, '15', 7, 6, 3, 3);
insert into answer (correct, answer, question_id, quiz_id, course_id, user_id) values (false, 'ingenting', 8, 6, 3, 4);

insert into answer (correct, answer, question_id, quiz_id, course_id, user_id) values (false, '2', 6, 6, 3, 2);
insert into answer (correct, answer, question_id, quiz_id, course_id, user_id) values (true, '15', 7, 6, 3, 3);
insert into answer (correct, answer, question_id, quiz_id, course_id, user_id) values (true, '0', 8, 6, 3, 4);