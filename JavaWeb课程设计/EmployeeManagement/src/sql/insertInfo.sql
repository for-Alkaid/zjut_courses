
truncate table employeemanage.tasktoemp;
truncate table employeemanage.task;
truncate table employeemanage.employee;

insert into employeemanage.department (name) value ('市场部');
insert into employeemanage.department (name) value ('运营部');
insert into employeemanage.department (name) value ('研发部');
insert into employeemanage.department (name) value ('后勤部');

insert into employeemanage.employee (emp_id, dept_id, position_id, emp_name, password) VALUES ('0000',0,0,'admin',md5('admin'));
insert into employeemanage.employee (emp_id, dept_id, position_id, emp_name, password) VALUES ('0001',1,1,'小明',md5(123456));
insert into employeemanage.employee (emp_id, dept_id, position_id, emp_name, password) VALUES ('0002',2,1,'小王',md5(123456));
insert into employeemanage.employee (emp_id, dept_id, position_id, emp_name, password) VALUES ('0003',3,2,'小红',md5(123456));
insert into employeemanage.employee (emp_id, dept_id, position_id, emp_name, password) VALUES ('0004',4,3,'小黑',md5(123456));
insert into employeemanage.employee (emp_id, dept_id, position_id, emp_name, password) VALUES ('0005',3,4,'大Q',md5(123456));



insert into employeemanage.project (p_name) values ('员工管理系统');
insert into employeemanage.project (p_name) values ('项目管理系统');
insert into employeemanage.project (p_name) values ('任务追踪系统');




insert into employeemanage.position (position_name) VALUES ('common');
insert into employeemanage.position (position_name) VALUES ('technicalDirector');
insert into employeemanage.position (position_name) VALUES ('departmentManager');
insert into employeemanage.position (position_name) VALUES ('generalManager');



insert into employeemanage.task(t_name, p_id) VALUES ('页面美化',1);
insert into employeemanage.task(t_name, p_id) VALUES ('后端研发',1);
insert into employeemanage.task(t_name, p_id) VALUES ('测试',1);
insert into employeemanage.task(t_name, p_id) VALUES ('页面美化',2);
insert into employeemanage.task(t_name, p_id) VALUES ('后端研发',2);
insert into employeemanage.task(t_name, p_id) VALUES ('测试',2);
insert into employeemanage.task(t_name, p_id) VALUES ('页面美化',3);
insert into employeemanage.task(t_name, p_id) VALUES ('后端研发',3);
insert into employeemanage.task(t_name, p_id) VALUES ('测试',3);


insert into employeemanage.tasktoemp(emp_id, task_id, startTime, endTime, details, isAccomplish) VALUES ('0001',1,CURRENT_DATE+FLOOR(RAND()*3+1),CURRENT_DATE+FLOOR(RAND()*5+3),'1、运用bootstrap美化界面。2、保证页面流畅稳定。',0);
insert into employeemanage.tasktoemp(emp_id, task_id, startTime, endTime, details, isAccomplish) VALUES ('0001',2,CURRENT_DATE+FLOOR(RAND()*3+1),CURRENT_DATE+FLOOR(RAND()*5+3),'1、实现并发控制，负载均衡。2、提升系统的流畅性和稳固性。',1);
insert into employeemanage.tasktoemp(emp_id, task_id, startTime, endTime, details, isAccomplish) VALUES ('0001',3,CURRENT_DATE+FLOOR(RAND()*3+1),CURRENT_DATE+FLOOR(RAND()*5+3),'1、运用黑盒白盒测试对系统进行检测。2、撰写测试报告。',0);
insert into employeemanage.tasktoemp(emp_id, task_id, startTime, endTime, details, isAccomplish) VALUES ('0001',4,CURRENT_DATE+FLOOR(RAND()*3+1),CURRENT_DATE+FLOOR(RAND()*5+3),'1、运用bootstrap美化界面。2、保证页面流畅稳定',0);
insert into employeemanage.tasktoemp(emp_id, task_id, startTime, endTime, details, isAccomplish) VALUES ('0002',5,CURRENT_DATE+FLOOR(RAND()*3+1),CURRENT_DATE+FLOOR(RAND()*5+3),'1、实现并发控制，负载均衡。2、提升系统的流畅性和稳固性',1);
insert into employeemanage.tasktoemp(emp_id, task_id, startTime, endTime, details, isAccomplish) VALUES ('0002',6,CURRENT_DATE+FLOOR(RAND()*3+1),CURRENT_DATE+FLOOR(RAND()*5+3),'1、运用黑盒白盒测试对系统进行检测。2、撰写测试报告。',0);
insert into employeemanage.tasktoemp(emp_id, task_id, startTime, endTime, details, isAccomplish) VALUES ('0002',7,CURRENT_DATE+FLOOR(RAND()*3+1),CURRENT_DATE+FLOOR(RAND()*5+3),'1、运用bootstrap美化界面。2、保证页面流畅稳定',0);

insert into employeemanage.evaluation (emp_id, departmentManagerScore, technicalDirectorScore, generalManagerScore, monthScore, eva_time) VALUES ('0001',FLOOR(RAND()*100),FLOOR(RAND()*100),FLOOR(RAND()*100),0.3*technicalDirectorScore+0.4*departmentManagerScore+0.3*generalManagerScore,'2021-1-1');
insert into employeemanage.evaluation (emp_id, departmentManagerScore, technicalDirectorScore, generalManagerScore, monthScore, eva_time) VALUES ('0001',FLOOR(RAND()*100),FLOOR(RAND()*100),FLOOR(RAND()*100),0.3*technicalDirectorScore+0.4*departmentManagerScore+0.3*generalManagerScore,'2021-2-1');
insert into employeemanage.evaluation (emp_id, departmentManagerScore, technicalDirectorScore, generalManagerScore, monthScore, eva_time) VALUES ('0001',FLOOR(RAND()*100),FLOOR(RAND()*100),FLOOR(RAND()*100),0.3*technicalDirectorScore+0.4*departmentManagerScore+0.3*generalManagerScore,'2021-3-1');
insert into employeemanage.evaluation (emp_id, departmentManagerScore, technicalDirectorScore, generalManagerScore, monthScore, eva_time) VALUES ('0001',FLOOR(RAND()*100),FLOOR(RAND()*100),FLOOR(RAND()*100),0.3*technicalDirectorScore+0.4*departmentManagerScore+0.3*generalManagerScore,'2021-4-1');
insert into employeemanage.evaluation (emp_id, departmentManagerScore, technicalDirectorScore, generalManagerScore, monthScore, eva_time) VALUES ('0001',FLOOR(RAND()*100),FLOOR(RAND()*100),FLOOR(RAND()*100),0.3*technicalDirectorScore+0.4*departmentManagerScore+0.3*generalManagerScore,'2021-5-1');
insert into employeemanage.evaluation (emp_id, departmentManagerScore, technicalDirectorScore, generalManagerScore, monthScore, eva_time) VALUES ('0001',FLOOR(RAND()*100),FLOOR(RAND()*100),FLOOR(RAND()*100),0.3*technicalDirectorScore+0.4*departmentManagerScore+0.3*generalManagerScore,'2021-6-1');
insert into employeemanage.evaluation (emp_id, departmentManagerScore, technicalDirectorScore, generalManagerScore, monthScore, eva_time) VALUES ('0001',FLOOR(RAND()*100),FLOOR(RAND()*100),FLOOR(RAND()*100),0.3*technicalDirectorScore+0.4*departmentManagerScore+0.3*generalManagerScore,'2021-7-1');
insert into employeemanage.evaluation (emp_id, departmentManagerScore, technicalDirectorScore, generalManagerScore, monthScore, eva_time) VALUES ('0001',FLOOR(RAND()*100),FLOOR(RAND()*100),FLOOR(RAND()*100),0.3*technicalDirectorScore+0.4*departmentManagerScore+0.3*generalManagerScore,'2021-8-1');
insert into employeemanage.evaluation (emp_id, departmentManagerScore, technicalDirectorScore, generalManagerScore, monthScore, eva_time) VALUES ('0001',FLOOR(RAND()*100),FLOOR(RAND()*100),FLOOR(RAND()*100),0.3*technicalDirectorScore+0.4*departmentManagerScore+0.3*generalManagerScore,'2021-9-1');
insert into employeemanage.evaluation (emp_id, departmentManagerScore, technicalDirectorScore, generalManagerScore, monthScore, eva_time) VALUES ('0001',FLOOR(RAND()*100),FLOOR(RAND()*100),FLOOR(RAND()*100),0.3*technicalDirectorScore+0.4*departmentManagerScore+0.3*generalManagerScore,'2021-10-1');
insert into employeemanage.evaluation (emp_id, departmentManagerScore, technicalDirectorScore, generalManagerScore, monthScore, eva_time) VALUES ('0001',FLOOR(RAND()*100),FLOOR(RAND()*100),FLOOR(RAND()*100),0.3*technicalDirectorScore+0.4*departmentManagerScore+0.3*generalManagerScore,'2021-11-1');
insert into employeemanage.evaluation (emp_id, departmentManagerScore, technicalDirectorScore, generalManagerScore, monthScore, eva_time) VALUES ('0001',FLOOR(RAND()*100),FLOOR(RAND()*100),FLOOR(RAND()*100),0.3*technicalDirectorScore+0.4*departmentManagerScore+0.3*generalManagerScore,'2021-12-1');
insert into employeemanage.evaluation (emp_id, departmentManagerScore, technicalDirectorScore, generalManagerScore, monthScore, eva_time) VALUES ('0002',FLOOR(RAND()*100),FLOOR(RAND()*100),FLOOR(RAND()*100),0.3*technicalDirectorScore+0.4*departmentManagerScore+0.3*generalManagerScore,'2021-1-1');
