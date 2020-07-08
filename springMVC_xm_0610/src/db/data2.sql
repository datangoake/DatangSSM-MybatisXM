select * from T_ROLE;

insert into T_EMPLOYEE (employee_id, employee_name, password, real_name, sex, birthday, duty, enrolldate, education, major, experience, role_id, parent_id)
values (9, 'zhaoliu', '123456', '赵六', '女', to_date('12-05-2000', 'dd-mm-yyyy'), '初级工程师', to_date('12-05-2009', 'dd-mm-yyyy'), '大学', '计算机', '2年', 2, 8);
insert into T_EMPLOYEE (employee_id, employee_name, password, real_name, sex, birthday, duty, enrolldate, education, major, experience, role_id, parent_id)
values (8, 'lisi', '123456', '李四', '女', to_date('12-05-1999', 'dd-mm-yyyy'), '高级工程师', to_date('12-04-2006', 'dd-mm-yyyy'), '大学', '计算机', '4年', 3, null);

rollback;
select * from T_EMPLOYEE where EMPLOYEE_ID=10;

select * from T_TASK ;
update T_TASK set status='实施中' where task_id=1
update T_TASK set task_name=?,begin_date=?,end_date=?,implementor_id=?,task_desc=? where task_id=?
delete from T_TASK where task_id=?
insert into T_TASK(TASK_NAME,BEGIN_DATE,END_DATE,STATUS,IMPLEMENTOR_ID,ASSIGNER_ID,TASK_DESC) values(?,?,?,?,?,?);
select * from T_PLAN where task_id=5;

create sequence seq_T_PLAN;
select seq_T_PLAN.nextval from dual;
--使用序列配合触发器实现主键自增
create or replace trigger tri_T_PLAN
before insert
on T_PLAN
for each row
begin
    select seq_T_PLAN.nextval into :new.PLAN_ID from dual;
end;