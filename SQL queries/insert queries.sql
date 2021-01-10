insert into `atm`.`role` values(1, "USER");
insert into `atm`.`role` values(2, "ADMIN");
insert into `atm`.`user_role` values(2, 1, 2);

insert into `atm`.`account` values(1, '2020-09-29 12:17:58.000000', 'savings', true, 1000000);
insert into `atm`.`account` values(2, '2020-09-29 12:17:58.000000', 'savings', true, 2000000);

update  `atm`.`user` set acct_id = 1 where user_id = 1;
update  `atm`.`user` set acct_id = 2 where user_id = 2;
update  `atm`.`user` set acct_id = 2 where user_id = 4;

select * from `atm`.`user`;