create table message (
    id bigint not null,
    user_id bigint,
    filename varchar(255),
    tag varchar(255),
    text varchar(255),
    primary key (id)
                     ) engine=InnoDB;

    create table message_seq (
        next_val bigint
                             ) engine=InnoDB;

    insert into message_seq values (
                                    1
                                    );

    create table user_role (
        user_id bigint not null,
        roles enum ('ADMIN','USER')
                           ) engine=InnoDB;

create table usr (
    active bit not null,
    id bigint not null AUTO_INCREMENT,
    activation_code varchar(255),
    email varchar(255),
    password varchar(255),
    username varchar(255),
    primary key (id)
                 ) engine=InnoDB;

    create table usr_seq (
        next_val bigint
                         ) engine=InnoDB;

    insert into usr_seq values (
                                1
                                );

    alter table message add constraint message_user_fk foreign key (user_id) references usr (id);
alter table user_role add constraint user_role_user_fk foreign key (user_id) references usr (id);