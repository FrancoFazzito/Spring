insert into users (username,password,enabled) values('andres','$2a$10$dF9QjlbdekWxe42sO2hpiuVtpcz6S5jXRlECFpcpTJAlLgynmerue',1);
insert into users (username,password,enabled) values('admin','$2a$10$AOSWUfGzvP1rY3zA5NQdlu55oLhq8WnTANIrNZI3CZhE.G4fp167m',1);

insert into authorities (authorities.user_id,authorities.authoritiy) values (1,'ROLE_USER');
insert into authorities (authorities.user_id,authorities.authoritiy) values (2,'ROLE_USER');
insert into authorities (authorities.user_id,authorities.authoritiy) values (2,'ADMIN_USER');