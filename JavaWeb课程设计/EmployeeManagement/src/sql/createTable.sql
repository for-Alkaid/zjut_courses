use employeemanage;

# 部门表
create TABLE if not exists `department`(
  `id` int(4) NOT NULL AUTO_increment,
  `dept_name` VARCHAR(30) not null,
  PRIMARY key(id)
)ENGINE = INNODB DEFAULT charset =utf8;

# 职位表
create TABLE if not exists `position`(
  `position_id` int(4) NOT NULL AUTO_increment,
  `position_name` VARCHAR(30) not null,
  PRIMARY key(position_id)
)ENGINE = INNODB DEFAULT charset =utf8;

# 员工表且用于用户登录
create table if not exists employee(
  `emp_id` varchar(4) NOT NULL ,
  `dept_id` int(4) NOT NULL ,
  `position_id` int(4) not null ,
  `emp_name` VARCHAR(30) not null,
  `password` varchar(32) not null ,
  PRIMARY key(emp_id)
)ENGINE = INNODB DEFAULT charset =utf8;


# 项目表
create table if not exists project(
  `p_id` int(6) not null auto_increment,
  `p_name` varchar(30) not null ,
  primary key (p_id)
)ENGINE = INNODB DEFAULT charset =utf8;

# 任务表
create table if not exists task(
  `t_id` int(4) not null auto_increment,
  `t_name` varchar(30) not null ,
  `p_id` int(6) not null ,
  `details` varchar(200) ,
  primary key (t_id)
)ENGINE = INNODB DEFAULT charset =utf8;

# 任务与员工关联表
create table if not exists taskToEmp(
  `te_id` int(6) not null auto_increment,
  `emp_id` varchar(4) not null ,
  `task_id` int(4) not null ,
  `startTime` date not null ,
  `endTime` date not null ,
  `isAccomplish` int(1) not null ,
  primary key (te_id)
)ENGINE = INNODB DEFAULT charset =utf8;

# 评价表
create table if not exists evaluation(
  `task_id` varchar(4) NOT NULL,
  `departmentManagerScore` int(3) not null ,
  `technicalDirectorScore` int(3) not null ,
  `generalManagerScore` int(3) not null ,
  `taskScore` int(3) not null ,
  `eva_time` date not null
)ENGINE = INNODB DEFAULT charset =utf8;