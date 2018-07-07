------------Common Errors During Build---------
Error1)
Fatal error compiling: tools.jar not found: C:\Program Files\Java\jre1.8.0_131\..\lib\tools.jar -> [Help 1]
OR
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.2:compile (default-compile) on project biometric-application: Compilation failure
[ERROR] No compiler is provided in this environment. Perhaps you are running on a JRE rather than a JDK?


Solution1)
Change the installed JREs to jdk path instead of JRE
Window-> Preference -> Java -> Installed JREs ->click add->Choose standard VM-> and select JDK path
and select that as installed JREs

 Error 2) -Dmaven.multiModuleProjectDirectory=$M2_HOME
Solution 2) 

In Eclipse you need to go like this.

Window-> Preference -> Java -> Installed JREs -> Edit(if jdk path is already set,. if JRE is set, first follow Solution1 steps and proceed with below steps)

In the edit Default VM arguments you need to put

If you already set the maven home.

-Dmaven.multiModuleProjectDirectory=$M2_HOME
 

------------------------MYSQL-------------------
After Finishing installng MySQL 
1) Set/create mysql username and password as root and root

2)Create database with name-> biometric_details

-------------------Tomcat installation----------------------
Install tomcat
download zip and extract to c drive
1) set CATALINA_HOME to path of tomcat
2) append  ;%CATALINA_HOME%/bin to path variable(samething should be done for M2_HOME also.. i.e., adding ;%M2_HOME%/bin to path variable)
once all these are done

3) in tomcat-users.xml
<role rolename="manager-gui"/>
<role rolename="manager"/>
<role rolename="manager-script"/>
<user username="root" password="root" roles="manager,manager-gui,manager-script" />


-----------------------------Maven Installation--------------------------------
1)Download maven zip and extract to some drive(say C Drive).
2)Configure M2_HOME environment with the directory where maven is extracted.(Ex: C:\apache-maven-3.3.9) and 
add %M2_HOME%/bin to path environment variable 
3)In Maven settings.xml
    <server>
   <id>TomcatServer</id>
   <username>root</username>
   <password>root</password>
  </server>


---------------------Building and deploying an application---------------------------
Make sure you have followed the steps mentioned in Mysql Installation before proceeding to below steps
1) right click on project -> Run AS-> Maven Build(2nd one- only for first time.)
2) in goal field add  clean install and click run
above steps if for building
now to deploy project  (before this, run the sql script- it will create table. thats all..) 
1) Open command prompt and type startup ( tomcat server should start)- You can do this from eclipse also.Ensure all settings is correct

once Server is started, follow below steps
3) Right click again on project-> Run As -> Maven Build(2nd one, only for first time)
4) in goal section, add tomcat7:deploy(this will build war file and deploy to tomcat)
5) it will take sometime to deploy and you will see success status when finished..

If you face any problem while deploying, delete project files and war from tomcat->webapps directory and shutdown tomcat server and 
start again. Follow above steps(from step 3) once it starts.

Once deploy is successful, open localhost:<tomcat http port>/DAPS(Ex: http://localhost:8081/DAPS/) it will ask for 
Login page will be displayed. 
Now go to DB and run the queries with sample data present in initialData.sql file

Now login with admin->username=sam and password=abc125
for driver, login with username=anma1213 and password=anma1213



*********************************For Developer************************************
1) To redirect from POST to POST, use below before return statement.
request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);

 