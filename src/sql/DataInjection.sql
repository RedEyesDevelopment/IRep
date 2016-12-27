USE `idearepository`;

insert into USERS (USER_NAME, LOGIN, PSWD) values ('System_Programmer', 'sysprog', 'sysprog');
insert into USERS (USER_NAME, LOGIN, PSWD) values ('QwertyUser', 'qwerty', 'qwerty');
insert into USERS (USER_NAME, LOGIN, PSWD) values ('ModUser', 'mod', 'mod');
insert into USERS (USER_NAME, LOGIN, PSWD) values ('ListUser', 'list', 'list');

insert into CONTENTS (IDEA_CONTENT) values ('framework created by Gavin King in 2001.');
insert into CONTENTS (IDEA_CONTENT) values ('It is a powerful.');
insert into CONTENTS (IDEA_CONTENT) values ('Hibernate maps Java classes.');

insert into IDEAS (IDEA_NAME, DESCRIPTION, IDEA_AUTHOR_ID, IDEA_CONTENT_ID) values ('Interface idea', 'I suggest to change our company name to CATACOMB GOBLINS', '1', '1');
insert into IDEAS (IDEA_NAME, DESCRIPTION, IDEA_IMAGE, IDEA_AUTHOR_ID, IDEA_CONTENT_ID) values ('Оптимизация затрат', 'I hev idea2', 'kartinka', '2', '2');
insert into IDEAS (IDEA_NAME, DESCRIPTION, IDEA_AUTHOR_ID, IDEA_CONTENT_ID) values ('NEW SUPPLIER!!!', 'New supplier wants to be our partner', '3', '3');

insert into COMMENTS (COMMENT_AUTHOR_ID, COMMENT_IDEA_ID, COMMENT_CONTENT) values ('1', '1', 'Idea is brilliant!');
insert into COMMENTS (COMMENT_AUTHOR_ID, COMMENT_IDEA_ID, COMMENT_CONTENT) values ('2', '1', 'Первым делом уволить к чёрту всех кадровиков!');
insert into COMMENTS (COMMENT_AUTHOR_ID, COMMENT_IDEA_ID, COMMENT_CONTENT) values ('3', '2', 'Where do you want to take money for that partnership?');

insert into TAGS (TAG_CONTENT) values ('company');
insert into TAGS (TAG_CONTENT) values ('common');
insert into TAGS (TAG_CONTENT) values ('funds');
insert into TAGS (TAG_CONTENT) values ('interface');
insert into TAGS (TAG_CONTENT) values ('company_name');

insert into TAG_MAGAZINE (TAG_MAG_ID, TAG_IDEA_ID) values ('1', '1');
insert into TAG_MAGAZINE (TAG_MAG_ID, TAG_IDEA_ID) values ('4', '1');
insert into TAG_MAGAZINE (TAG_MAG_ID, TAG_IDEA_ID) values ('5', '1');
insert into TAG_MAGAZINE (TAG_MAG_ID, TAG_IDEA_ID) values ('1', '2');
insert into TAG_MAGAZINE (TAG_MAG_ID, TAG_IDEA_ID) values ('3', '2');
insert into TAG_MAGAZINE (TAG_MAG_ID, TAG_IDEA_ID) values ('1', '3');
insert into TAG_MAGAZINE (TAG_MAG_ID, TAG_IDEA_ID) values ('2', '3');
insert into TAG_MAGAZINE (TAG_MAG_ID, TAG_IDEA_ID) values ('3', '3');
