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
  `clock_in` int(2) default 0,
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
  primary key (t_id)
)ENGINE = INNODB DEFAULT charset =utf8;

# 任务与员工关联表
create table if not exists taskToEmp(
  `te_id` int(6) not null auto_increment,
  `emp_id` varchar(4) not null ,
  `task_id` int(4) not null ,
  `startTime` date not null ,
  `endTime` date not null ,
  `details` varchar(200) ,
  `isAccomplish` int(1) not null default 0,
  primary key (te_id)
)ENGINE = INNODB DEFAULT charset =utf8;

# 评价表
create table if not exists evaluation(
  `eva_id` int(6) NOT NULL auto_increment,
  `emp_id` varchar(4) NOT NULL,
  `departmentManagerScore` double(5,2) not null default 0,
  `technicalDirectorScore` double(5,2) not null default 0,
  `generalManagerScore` double(5,2) not null default 0,
  `monthScore` double(5,2) not null default 0,
  `eva_time` date not null ,
  primary key (eva_id,emp_id,eva_time)
)ENGINE = INNODB DEFAULT charset =utf8;

#评价分数详情表
use employeemanage;
create table if not exists evaluationDetails(
  `eva_id` int(6) not null,
  `eva_emp_id` varchar(4) not null ,
  `progressRate` int(3) NOT NULL ,
  `quality` int(3) NOT NULL ,
  `workload` int(3) NOT NULL ,
  `customerReviews` int(3) NOT NULL ,
  `responsibility` int(3) NOT NULL ,
  `attendance` int(3) NOT NULL ,
  `positivity` int(3) NOT NULL ,
  `conduct` int(3) NOT NULL ,
  `teamCooperation` int(3) NOT NULL ,
  `improvingCapability` int(3) NOT NULL ,
  `examSituation` int(3) NOT NULL ,
  `specialContribution` int(3) NOT NULL ,
  `reasonableSuggestion` int(3) NOT NULL ,
  `Score` double(5,2) NOT NULL
#   `dm_isEvaluated` bool not null default false comment '部门经理是否打过分',
#   `td_isEvaluated` bool not null default false comment '技术总监是否打过分',
#   `gm_isEvaluated` bool not null default false comment '总经理是否打过分'
)ENGINE = INNODB DEFAULT charset =utf8;
