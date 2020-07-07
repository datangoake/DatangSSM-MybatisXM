---------------------------------------------
-- Export file for user SCOTT              --
-- Created by TT315 on 2015/8/12, 22:56:24 --
---------------------------------------------

set define off
spool data1.log

prompt
prompt Creating table T_ROLE
prompt =====================
prompt
create table SCOTT.T_ROLE
(
  role_id   NUMBER not null,
  role_name VARCHAR2(20) not null,
  role_desc VARCHAR2(30) not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 16K
    next 8K
    minextents 1
    maxextents unlimited
  );
alter table SCOTT.T_ROLE
  add constraint PK_ROLE_ID primary key (ROLE_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table T_EMPLOYEE
prompt =========================
prompt
create table SCOTT.T_EMPLOYEE
(
  employee_id   NUMBER not null,
  employee_name VARCHAR2(20) not null,
  password      VARCHAR2(10) not null,
  real_name     VARCHAR2(20) not null,
  sex           VARCHAR2(4) not null,
  birthday      DATE,
  duty          VARCHAR2(30) not null,
  enrolldate    DATE not null,
  education     VARCHAR2(30) not null,
  major         VARCHAR2(30) not null,
  experience    VARCHAR2(30) not null,
  role_id       NUMBER,
  parent_id     NUMBER
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 16K
    next 8K
    minextents 1
    maxextents unlimited
  );
alter table SCOTT.T_EMPLOYEE
  add constraint PK_EMPLOYEE_ID primary key (EMPLOYEE_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table SCOTT.T_EMPLOYEE
  add constraint FK_EMPLOYEE_ID foreign key (PARENT_ID)
  references SCOTT.T_EMPLOYEE (EMPLOYEE_ID);
alter table SCOTT.T_EMPLOYEE
  add constraint FK_ROLE_ID foreign key (ROLE_ID)
  references SCOTT.T_ROLE (ROLE_ID);

prompt
prompt Creating table T_TASK
prompt =====================
prompt
create table SCOTT.T_TASK
(
  task_id         NUMBER not null,
  task_name       VARCHAR2(50),
  begin_date      DATE,
  end_date        DATE,
  real_begin_date DATE,
  real_end_date   DATE,
  status          VARCHAR2(10),
  implementor_id  NUMBER,
  assigner_id     NUMBER,
  task_desc       VARCHAR2(100)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 16K
    next 8K
    minextents 1
    maxextents unlimited
  );
alter table SCOTT.T_TASK
  add constraint PK_TASK_ID primary key (TASK_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table SCOTT.T_TASK
  add constraint FK_ASSIGNER_ID foreign key (ASSIGNER_ID)
  references SCOTT.T_EMPLOYEE (EMPLOYEE_ID);
alter table SCOTT.T_TASK
  add constraint FK_IMPLEMENTOR_ID foreign key (IMPLEMENTOR_ID)
  references SCOTT.T_EMPLOYEE (EMPLOYEE_ID);

prompt
prompt Creating table T_PLAN
prompt =====================
prompt
create table SCOTT.T_PLAN
(
  plan_id       NUMBER not null,
  plan_name     VARCHAR2(50),
  status        VARCHAR2(10),
  is_feedback   VARCHAR2(5),
  begin_date    DATE,
  end_date      DATE,
  task_id       NUMBER,
  feedback_info VARCHAR2(100),
  plan_desc     VARCHAR2(100)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 16K
    next 8K
    minextents 1
    maxextents unlimited
  );
alter table SCOTT.T_PLAN
  add constraint PK_PLAN_ID primary key (PLAN_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table SCOTT.T_PLAN
  add constraint FK_TASK_ID foreign key (TASK_ID)
  references SCOTT.T_TASK (TASK_ID);


spool off


select * from datang.T_PLAN
select * from datang.T_TASK
select * from datang.T_EMPLOYEE
select * from datang.T_ROLE
insert into SCOTT.T_EMPLOYEE set datang.T_EMPLOYEE='李信' where datang.T_EMPLOYEE=16


  select * from T_EMPLOYEE
  
  create sequence seq_T_PLAN
minvalue 1  --增长最小值
maxvalue 9999999999  --增长最大值,也可以设置NOMAXvalue -- 不设置最大值
start with 30  --从101开始计数
increment by 1  --自增步长为1
cache 50  --设置缓存cache个序列，如果系统down掉了或者其它情况将会导致序列不连续，也可以设置为---NOCACHE防止跳号
cycle; 




create or replace trigger tri_T_TASK
before insert on datang.T_TASK
for each row
	WHEN ( NEW.TASK_ID IS NULL OR NEW.TASK_ID = 0 ) BEGIN
	select seq_T_TASK.nextval into :new.TASK_ID from dual;
end

insert into T_EMPLOYEE ( employee_name, password, real_name, sex, birthday, duty, enrolldate, education, major, experience, role_id, parent_id)
values ('lixin', '123456', '李信', '男', to_date('12-08-1999', 'dd-mm-yyyy'), '中级工程师', to_date('18-04-2008', 'dd-mm-yyyy'), '大学', '计算机', '5年', 4, 8);
  
insert into T_TASK(TASK_NAME,BEGIN_DATE,END_DATE,STATUS,IMPLEMENTOR_ID,ASSIGNER_ID,TASK_DESC) values('任务8',to_date('2001-01-01','yyyy-MM-dd'),?,?,?,?,?)