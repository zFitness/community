create table comment
(
	id bigint auto_increment primary key,
	parent_id bigint not null,
	type int not null,
	commentator int not null,
	gmt_create bigint not null,
	gmt_modified bigint not null,
	like_count bigint default 0
);

-- comment on column comment.type is '父类类型';
--
-- comment on column comment.commentator is '评论人id';
--
-- comment on column comment.gmt_create is '评论时间';
--
-- comment on column comment.gmt_modified is '更新时间';
--
-- comment on column comment.like_count is '点赞数';

