/*SQLyog Ultimate v8.55 MySQL - 5.1.54-community **********************************************************************//*!40101 SET NAMES utf8 */;insert into `app_user` (`USER_ID`, `ADDRESS`, `DL_NO`, `DOB`, `DOJ`, `EMAIL`, `FIRST_NAME`, `GENDER`, `LAST_NAME`, `PASSWORD`, `PHONE`, `USER_NAME`) values('1','SIT Extension,6th Croos','12345678','06/05/2017','06/07/2017','raviradha9964@gmail.com','Anith','Male','Marnoor','$2a$10$mGHHWJPR/TSOsc6e6coB4umtc6akabMU38T4KwSz8HMHlJYUWpOTO','9731872151','anma1213');insert into `app_user` (`USER_ID`, `ADDRESS`, `DL_NO`, `DOB`, `DOJ`, `EMAIL`, `FIRST_NAME`, `GENDER`, `LAST_NAME`, `PASSWORD`, `PHONE`, `USER_NAME`) values('2','S.i.t Extension','123','07/01/2017','07/01/2017','a@gmail.com','Anith','Male','MarnoorA','$2a$10$AL/GyYMyccTb9iV.6V.L0Ol4VJh8YzM8Ollu/hiZMUDGb8yeNXl/G','9731873251','anma1214');insert into `app_user` (`USER_ID`, `ADDRESS`, `DL_NO`, `DOB`, `DOJ`, `EMAIL`, `FIRST_NAME`, `GENDER`, `LAST_NAME`, `PASSWORD`, `PHONE`, `USER_NAME`) values('3','S.i.t Extension','124','07/01/2017','07/01/2017','b@g.com','Anith','Male','MarnoorB','$2a$10$Ku3rvlH1t7xSr5vV9FO0Ye.S/cVofO6n0JLnXSAgq7AfRH0.HCQH2','9731873252','anma1215');insert into `app_user` (`USER_ID`, `ADDRESS`, `DL_NO`, `DOB`, `DOJ`, `EMAIL`, `FIRST_NAME`, `GENDER`, `LAST_NAME`, `PASSWORD`, `PHONE`, `USER_NAME`) values('4','S.i.t Extension','125','07/01/2017','07/01/2017','c@g.com','Anith','Male','MarnoorC','$2a$10$7u5vybm6pMsRZGgpqTkIPOO9DjEgD6SeqLEQBRttb54mDUnVcAgCq','9731873253','anma1216');insert into `app_user` (`USER_ID`, `ADDRESS`, `DL_NO`, `DOB`, `DOJ`, `EMAIL`, `FIRST_NAME`, `GENDER`, `LAST_NAME`, `PASSWORD`, `PHONE`, `USER_NAME`) values('5','S.i.t Extension','126','07/01/2017','07/01/2017','d@g.com','Anith','Male','MarnoorD','$2a$10$rC2FPP11mLmKasi5vKeTfupXq9s/ogLGokWUP2czX8qJOOwtRINz.','9731873255','anma1217');insert into `app_user` (`USER_ID`, `ADDRESS`, `DL_NO`, `DOB`, `DOJ`, `EMAIL`, `FIRST_NAME`, `GENDER`, `LAST_NAME`, `PASSWORD`, `PHONE`, `USER_NAME`) values('6','S.i.t Extension','127','07/01/2017','07/01/2017','e@g.com','Anith','Male','MarnoorE','$2a$10$v9Rkj/DtRX0bMsTjAKvYu.1ImD81LJnnxc7zmQgyHCyNCg81ohFbK','9731873256','anma1218');insert into `app_user` (`USER_ID`, `ADDRESS`, `DL_NO`, `DOB`, `DOJ`, `EMAIL`, `FIRST_NAME`, `GENDER`, `LAST_NAME`, `PASSWORD`, `PHONE`, `USER_NAME`) values('7','S.i.t Extension','128','07/01/2017','07/01/2017','f@g.com','Anith','Male','MarnoorF','$2a$10$DbXRiFsFOl1YvAVgtztHR.eX6fRyJnyHFuNM7nl1leXKWkvX4Ddcq','9731873257','anma1219');insert into `app_user` (`USER_ID`, `ADDRESS`, `DL_NO`, `DOB`, `DOJ`, `EMAIL`, `FIRST_NAME`, `GENDER`, `LAST_NAME`, `PASSWORD`, `PHONE`, `USER_NAME`) values('8','S.i.t Extension','129','07/01/2017','07/01/2017','g@g.com','Anith','Male','MarnoorG','$2a$10$bLwyOythlPvMTxJquL0HseXbf27fo/JYoSd7f8BFNjRkMqqmpGlwm','9731873259','anma1220');insert into `app_user` (`USER_ID`, `ADDRESS`, `DL_NO`, `DOB`, `DOJ`, `EMAIL`, `FIRST_NAME`, `GENDER`, `LAST_NAME`, `PASSWORD`, `PHONE`, `USER_NAME`) values('9','S.i.t Extension','130','07/01/2017','07/01/2017','h@g.com','Anith','Male','MarnoorH','$2a$10$SElIsMOAownD.2/cNaMWieg//nPvh93zYGXs3Wa6EDRxMBdpUR/Yu','9731873258','anma1221');insert into `app_user` (`USER_ID`, `ADDRESS`, `DL_NO`, `DOB`, `DOJ`, `EMAIL`, `FIRST_NAME`, `GENDER`, `LAST_NAME`, `PASSWORD`, `PHONE`, `USER_NAME`) values('10','S.i.t Extension','131','07/01/2017','07/01/2017','i@g.com','Anith','Male','MarnoorI','$2a$10$.4FAQ6uof/HK3ukm7hHH4.uU0cQwPKiEeJ9pyWxBd2q50ohzRF6re','9731873260','anma1222');INSERT  INTO `user_profile`(`USER_PROFILE_ID`,`TYPE`) VALUES (2,'ADMIN'),(1,'USER');INSERT  INTO `app_user_user_profile`(`USER_ID`,`USER_PROFILE_ID`) VALUES (1,2);INSERT  INTO `app_user_user_profile`(`USER_ID`,`USER_PROFILE_ID`) VALUES (2,1);INSERT  INTO `app_user_user_profile`(`USER_ID`,`USER_PROFILE_ID`) VALUES (3,1);INSERT  INTO `app_user_user_profile`(`USER_ID`,`USER_PROFILE_ID`) VALUES (4,1);INSERT  INTO `app_user_user_profile`(`USER_ID`,`USER_PROFILE_ID`) VALUES (5,1);INSERT  INTO `app_user_user_profile`(`USER_ID`,`USER_PROFILE_ID`) VALUES (6,1);INSERT  INTO `app_user_user_profile`(`USER_ID`,`USER_PROFILE_ID`) VALUES (7,1);INSERT  INTO `app_user_user_profile`(`USER_ID`,`USER_PROFILE_ID`) VALUES (8,1);INSERT  INTO `app_user_user_profile`(`USER_ID`,`USER_PROFILE_ID`) VALUES (9,1);INSERT  INTO `app_user_user_profile`(`USER_ID`,`USER_PROFILE_ID`) VALUES (10,1);