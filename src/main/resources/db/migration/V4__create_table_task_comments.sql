create table task_comments (
    id numeric not null primary key,
    message text not null,
    task_id numeric not null,
    constraint fk_task_comment foreign key (task_id) references tasks(id)
);

create sequence comment_sequence;