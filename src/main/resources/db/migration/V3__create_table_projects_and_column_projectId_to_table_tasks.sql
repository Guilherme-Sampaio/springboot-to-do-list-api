create table projects (
    id numeric not null primary key,
    name text not null
);

create sequence project_sequence;

alter table tasks add column project_id numeric;

alter table tasks add constraint fk_project_task foreign key (project_id) references projects(id);
