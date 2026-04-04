
create table task_ (
  task_id_     varchar(32) not null,
  user_id_     varchar(32) not null,
  timestamp_   timestamp without time zone not null,
  title_       varchar not null,
  description_ varchar null,
  done_        boolean null
);

alter table task_ add constraint task_pk_ primary key (task_id_);

-- Quarkus form-based authentication

create table user_ (
  username_ varchar(32) not null,
  password_ varchar null,
  roles_    varchar null
);

alter table user_ add constraint user_pk_ primary key (username_);

-- Migrations

-- alter table task_
--   alter column task_id_     type varchar(32),
--   alter column user_id_     type varchar(32),
--   alter column title_       type varchar,
--   alter column description_ type varchar;
