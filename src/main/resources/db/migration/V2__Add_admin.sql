insert into usr (username, password, active)
values ('admin', '$2a$08$iAlhJXaRwbMN1XeDa9ml1.UxnPjUjUItPhaCzjndKurvrzDgai9hS', true);

insert into user_role (user_id, roles)
values (1, 'USER'), (1, 'ADMIN');