/*
SQLyog - Free MySQL GUI v5.02
Host - 5.0.19-nt : Database - biometric_details
*********************************************************************
Server version : 5.0.19-nt
*/


create database if not exists `biometric_details`;

USE `biometric_details`;

/*Data for the table `app_user` */

insert into `app_user` values 

(1,'SIT Extension,6th Croos','06/05/2017','06/07/2017','raviradha9964@gmail.com','Anith','Male','Marnoor',NULL,'$2a$10$mGHHWJPR/TSOsc6e6coB4umtc6akabMU38T4KwSz8HMHlJYUWpOTO','9731872151','Active','anma1213',NULL),

(2,'S.i.t Extension','07/01/2017','07/01/2017','a@gmail.com','Anith','Male','MarnoorA','19/10/2017','$2a$10$AL/GyYMyccTb9iV.6V.L0Ol4VJh8YzM8Ollu/hiZMUDGb8yeNXl/G','9731873251','Left','anma1214',NULL),

(3,'S.i.t Extension','07/01/2017','07/01/2017','b@g.com','Anith','Male','MarnoorB',NULL,'$2a$10$Ku3rvlH1t7xSr5vV9FO0Ye.S/cVofO6n0JLnXSAgq7AfRH0.HCQH2','9731873252','Active','anma1215',NULL),

(4,'S.i.t Extension','07/01/2017','07/01/2017','c@g.com','Anith','Male','MarnoorC',NULL,'$2a$10$7u5vybm6pMsRZGgpqTkIPOO9DjEgD6SeqLEQBRttb54mDUnVcAgCq','9731873253','Active','anma1216',NULL),

(5,'S.i.t Extension','07/01/2017','07/01/2017','d@g.com','Anith','Male','MarnoorD',NULL,'$2a$10$rC2FPP11mLmKasi5vKeTfupXq9s/ogLGokWUP2czX8qJOOwtRINz.','9731873255','Active','anma1217',NULL),

(6,'S.i.t Extension','07/01/2017','07/01/2017','e@g.com','Anith','Male','MarnoorE',NULL,'$2a$10$v9Rkj/DtRX0bMsTjAKvYu.1ImD81LJnnxc7zmQgyHCyNCg81ohFbK','9731873256','Active','anma1218',NULL),

(7,'S.i.t Extension','07/01/2017','07/01/2017','f@g.com','Anith','Male','MarnoorF',NULL,'$2a$10$DbXRiFsFOl1YvAVgtztHR.eX6fRyJnyHFuNM7nl1leXKWkvX4Ddcq','9731873257','Active','anma1219',NULL),

(8,'S.i.t Extension','07/01/2017','07/01/2017','g@g.com','Anith','Male','MarnoorG',NULL,'$2a$10$bLwyOythlPvMTxJquL0HseXbf27fo/JYoSd7f8BFNjRkMqqmpGlwm','9731873259','Active','anma1220',NULL),

(9,'S.i.t Extension','07/01/2017','07/01/2017','h@g.com','Anith','Male','MarnoorH',NULL,'$2a$10$SElIsMOAownD.2/cNaMWieg//nPvh93zYGXs3Wa6EDRxMBdpUR/Yu','9731873258','Active','anma1221',NULL),

(10,'S.i.t Extension','07/01/2017','07/01/2017','i@g.com','Anith','Male','MarnoorI',NULL,'$2a$10$.4FAQ6uof/HK3ukm7hHH4.uU0cQwPKiEeJ9pyWxBd2q50ohzRF6re','9731873260','Active','anma1222',NULL),

(12,'cfbhfgh','10/03/2017','10/05/2017','meghasrinivas122@gmail.com','Megha','Female','sony',NULL,'$2a$10$jDlH1NFxhDeqcWTPJ7I7gu/9TIIeSdFhHeDEx16gmYKY9QeUAqSuS','5896658974','Active','mehs1213',NULL),

(13,'egehgre','10/03/2017','10/04/2017','meghasrinivas122@gmail.com','Nithya','Female','B',NULL,'$2a$10$XKHsw1LuOszRGPqrgQ/y3uwHRxVreiYggGkm4Cl5u3lAI0SQdMFQi','5896658971','Active','nith1213','Half'),

(14,'cgdhfck','11/15/2017','11/25/2017','meghasrinivas122@gmail.com','Ramya','Female','K',NULL,'$2a$10$WxWN/xEUAUa/9gGMJvonWegs9baRiYaFNM3I//UB5kh4UmlEcY/PO','5698745896','Active','ramk1213','Half');

/*Data for the table `app_user_user_profile` */

insert into `app_user_user_profile` values 
(1,2),
(1,3),
(1,4),
(1,5),
(1,6),
(1,7),
(1,8),
(1,9),
(1,10),
(2,1),
(2,12),
(2,13);

/*Data for the table `biometric_data` */

/*Data for the table `department_designation` */

insert into `department_designation` values 
(1,2),
(1,3);

/*Data for the table `departments` */

insert into `departments` values 
(1,'Accounts'),
(2,'HR');

/*Data for the table `designation_overtime` */

insert into `designation_overtime` values 
(2,11),
(3,13);

/*Data for the table `designation_percentage` */

insert into `designation_percentage` values 
(2,1);

/*Data for the table `designation_wages` */

insert into `designation_wages` values 
(2,1);

/*Data for the table `designations` */

insert into `designations` values 
(2,'Accounts Manager'),
(3,'Accounts Assistant');

/*Data for the table `holidays` */

insert into `holidays` values 
(4,'16/11/2017','11','testing','2017');

/*Data for the table `leave_types` */

insert into `leave_types` values 
(5,'Sick'),
(6,'Vacation');

/*Data for the table `leave_user` */

insert into `leave_user` values 
(1,1),
(1,2),
(1,3),
(4,5),
(5,4);

/*Data for the table `leaves_available` */

insert into `leaves_available` values 
(1,0),
(2,3),
(3,0);

/*Data for the table `leaves_leave_type` */

insert into `leaves_leave_type` values 
(5,1),
(5,2),
(5,3);

/*Data for the table `leaves_limit` */

insert into `leaves_limit` values 
(6,'5'),
(7,'4');

/*Data for the table `leaves_type_limit` */

insert into `leaves_type_limit` values 
(5,6),
(6,7);

/*Data for the table `overtime` */

insert into `overtime` values 
(11,'110'),
(13,'150');

/*Data for the table `pay_details` */

/*Data for the table `percentage_division` */

insert into `percentage_division` values 
(1,1),
(1,2);

/*Data for the table `persistent_logins` */

/*Data for the table `salary_division` */

insert into `salary_division` values 
(1,'BASIC','No');

/*Data for the table `salary_division_percentages` */

insert into `salary_division_percentages` values 
(1,20),
(2,40);

/*Data for the table `user_available_leaves` */

insert into `user_available_leaves` values 
(1,1),
(4,3),
(5,2);

/*Data for the table `user_biometric_data` */

/*Data for the table `user_designation` */

/*Data for the table `user_leave_types` */

insert into `user_leave_types` values 
(5,1),
(5,2),
(5,3),
(5,4),
(5,5);

/*Data for the table `user_leaves` */

insert into `user_leaves` values 
(1,'testing123','14/11/2017','Half','Pending for Approval','0.5','14/11/2017'),
(2,'testing','21/11/2017','Full','Pending for Approval','4.0','17/11/2017'),
(3,'testing','14/11/2017','Half','Pending for Approval','0.5','14/11/2017'),
(4,'testing','15/11/2017','Full','Pending for Approval','2.0','14/11/2017'),
(5,'testing1234','20/11/2017','Full','Pending for Approval','5.0','15/11/2017');

/*Data for the table `user_profile` */

insert into `user_profile` values 
(2,'ADMIN'),
(1,'USER');

/*Data for the table `user_user_profile_designation` */

/*Data for the table `wages` */

insert into `wages` values 
(1,'547500','1500'),
(2,'450000','1232');
