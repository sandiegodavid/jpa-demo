insert into course (Id, name, is_deleted) values (10, 'course 10', false);
insert into course (Id, name, is_deleted) values (11, 'course 11', false);
insert into course (Id, name, is_deleted) values (12, 'course 12', false);
insert into course (Id, name, is_deleted) values (13, 'course 13', false);
insert into course (Id, name, is_deleted) values (14, 'course 14', false);
insert into course (Id, name, is_deleted) values (15, 'course 15', false);
insert into course (Id, name, is_deleted) values (16, 'course 16', false);
insert into course (Id, name, is_deleted) values (17, 'course 17', false);
insert into course (Id, name, is_deleted) values (18, 'course 18', false);
insert into course (Id, name, is_deleted) values (19, 'course 19', false);
insert into course (Id, name, is_deleted) values (20, 'course 20', false);
insert into course (Id, name, is_deleted) values (21, 'course 21', false);
insert into course (Id, name, is_deleted) values (22, 'course 22', false);
insert into course (Id, name, is_deleted) values (23, 'course 23', false);
insert into course (Id, name, is_deleted) values (24, 'course 24', false);
insert into course (Id, name, is_deleted) values (25, 'course 25', false);
insert into course (Id, name, is_deleted) values (26, 'course 26', false);
insert into course (Id, name, is_deleted) values (27, 'course 27', false);
insert into course (Id, name, is_deleted) values (28, 'course 28', false);
insert into course (Id, name, is_deleted) values (29, 'course 29', false);

insert into course_detail (Id, full_name, last_update_time, create_time) 
values (10, 'course_detail 10', sysdate(), sysdate());
insert into course_detail (Id, full_name, last_update_time, create_time) 
values (11, 'course_detail 11', sysdate(), sysdate());
insert into course_detail (Id, full_name, last_update_time, create_time) 
values (12, 'course_detail 12', sysdate(), sysdate());

insert into passport (Id, number) values (1000, 'P1000');
insert into passport (Id, number) values (2000, 'P2000');
insert into passport (Id, number) values (3000, 'P3000');
insert into passport (Id, number) values (4000, 'P4000');

insert into student (Id, name, passport_id) values (1, 'Peppa Pig', 1000);
insert into student (Id, name, passport_id) values (2, 'George Pig', 2000);
insert into student (Id, name, passport_id) values (3, 'Dad Pig', 3000);
insert into student (Id, name, passport_id) values (4, 'Mom Pig', 4000);

insert into review (Id, rating, description, course_id) values (10000, 'FIVE', 'Best course', 10);
insert into review (Id, rating, description, course_id) values (20000, 'FOUR', 'Great course', 11);
insert into review (Id, rating, description, course_id) values (30000, 'FIVE', 'Excellent course', 11);
insert into review (Id, rating, description, course_id) values (40000, 'FIVE', 'Wonderful course', 12);

insert into student_course (student_id, course_id) values (1, 10);
insert into student_course (student_id, course_id) values (1, 11);
insert into student_course (student_id, course_id) values (1, 12);
insert into student_course (student_id, course_id) values (2, 10);
insert into student_course (student_id, course_id) values (2, 11);
insert into student_course (student_id, course_id) values (3, 12);
insert into student_course (student_id, course_id) values (4, 12);

