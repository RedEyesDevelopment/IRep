USE `idearepository`;

insert into USERS (USER_NAME, LOGIN, PSWD) values ('qwerty', 'qwerty', 'qwerty');
insert into USERS (USER_NAME, LOGIN, PSWD) values ('abs', 'abs', 'abs');
insert into USERS (USER_NAME, LOGIN, PSWD) values ('mod', 'mod', 'mod');
insert into USERS (USER_NAME, LOGIN, PSWD) values ('list', 'list', 'list');

insert into CONTENTS (IDEA_CONTENT) values ('framework created by Gavin King in 2001.');
insert into CONTENTS (IDEA_CONTENT) values ('It is a powerful.');
insert into CONTENTS (IDEA_CONTENT) values ('Hibernate maps Java classes.');

insert into IDEAS (IDEA_NAME, DESCRIPTION, IDEA_AUTHOR_ID, IDEA_CONTENT_ID) values ('idea', 'I hev idea', '1', '1');
insert into IDEAS (IDEA_NAME, DESCRIPTION, IDEA_IMAGE, IDEA_AUTHOR_ID, IDEA_CONTENT_ID) values ('idea2', 'I hev idea2', 'kartinka', '2', '2');
insert into IDEAS (IDEA_NAME, DESCRIPTION, IDEA_AUTHOR_ID, IDEA_CONTENT_ID) values ('idea3', 'I hev idea3', '3', '3');

insert into COMMENTS (COMMENT_AUTHOR_ID, COMMENT_IDEA_ID, COMMENT_CONTENT) values ('1', '1', 'Captain Obvious');
insert into COMMENTS (COMMENT_AUTHOR_ID, COMMENT_IDEA_ID, COMMENT_CONTENT) values ('2', '1', 'Das is fantasnik');
insert into COMMENTS (COMMENT_AUTHOR_ID, COMMENT_IDEA_ID, COMMENT_CONTENT) values ('3', '2', 'Ya ya');

insert into TAGS (TAG_CONTENT) values ('Sisk');
insert into TAGS (TAG_CONTENT) values ('big_Sisk');
insert into TAGS (TAG_CONTENT) values ('midl_Sisk');
insert into TAGS (TAG_CONTENT) values ('small_Sisk');
insert into TAGS (TAG_CONTENT) values ('bez_Sisek');

insert into TAG_MAGAZINE (TAG_MAG_ID, TAG_IDEA_ID) values ('1', '1');
insert into TAG_MAGAZINE (TAG_MAG_ID, TAG_IDEA_ID) values ('1', '2');
insert into TAG_MAGAZINE (TAG_MAG_ID, TAG_IDEA_ID) values ('2', '1');
insert into TAG_MAGAZINE (TAG_MAG_ID, TAG_IDEA_ID) values ('3', '1');
