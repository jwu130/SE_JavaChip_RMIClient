# SE_JavaChip_RMIClient
JavaChip's Assignment 2 and 3 for CS 370.  

ReadMe - Basics

Will make this document more readable later

"Level 1 rmi with servlet"
*Replace the brackets and the contents of the elements here that are represented in this doc by "< >" with your own paths
*You must create two separate directories one for client, the other for the server side.

Pre-requisites:
jdk and jre (1.8.0)
Eclipse J2EE
Apache Tomcat (v. 7.0 suggested)

Set Up:

Server:
add repo into directory from: https://github.com/jwu130/SE_JavaChip_RMIServer

Client:
create dynamic web project on eclipse and import src from: https://github.com/jwu130/SE_JavaChip_RMIClient
create a tomcat server on eclipse and add your project
open server configuration files (may be found in navigator view on eclipse - Servers)
open catalina.policy
add: 
grant {
    permission java.security.AllPermission;
};
grant codeBase "file:${catalina.home}/wtpwebapps/Assignment3Servlet/WEB-INF/classes/-" { 
    permission java.security.AllPermission "", ""; 
}; 
 
grant codeBase "file:${catalina.home}/wtpwebapps/Assignment3Servlet/WEB-INF/lib/-" { 
    permission java.security.AllPermission "", ""; 
}; 
grant codeBase "file:${catalina.home}/wtpwebapps/Assignment3Servlet/WEB-INF/lib/some-common-3.0.jar" { 
    permission java.io.FilePermission "*", "read, write"; 
};

open catalina.properties
add: ${catalina.base}/<the_location_of_your_classfiles_for_client_project>
to shared.loader property

Start:
open cmd prompt
navigate to your java development kit installation directory bin folder ie. ..\jre1.8.0_45\bin
set classpath on machine to server side project class files with command set classpath=<...>
execute command start rmiregistry 1099

run RMI_BioAPI_AsteriskJava_Server with "start java RMI_BioAPI_AsteriskJava_Server 1099 <your_server_port_number>"

open your eclipse
change the virtual machine arguments in run configurations of tomcat server to include " -Djava.security.manager -Djava.security.policy="<path_to_your_catalina_policy_file>"
run server

Check to see if its working:
open browser
go to localhost:<tomcat_portnumber>/<your_context_path_foundinserver.xml>/main
You should see helloworld and in your system console, see the contents of txtServer displayed
