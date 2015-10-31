# SE_JavaChip_RMIClient
JavaChip's Assignment 2 and 3 for CS 370 Software Engineering.  

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
        <!--create dynamic web project on eclipse and import src files from: https://github.com/jwu130/SE_JavaChip_RMIClient-->
        <!--create a tomcat server on eclipse and add your project to the server-->
        
        Create tomcat server and deploy client application  (src files from https://github.com/jwu130/SE_JavaChip_RMIClient              packaged as war)
        
        Edit your security policy:
        open catalina.policy
        add: 
            grant {
                permission java.security.AllPermission;
            };

Start:
    open cmd prompt
    
    run rmi server jar file
    run RMI_BioAPI_AsteriskJava_Server with "java -Djava.security.manager -Djava.security.policy=<\path\to\your\policy\file>         -jar RMI_BioAPI_AsteriskJava_Server <your_rmi_registry_port> <your_server_port_number>"
    
    open your eclipse
    change the virtual machine arguments in run configurations of tomcat server to include " -Djava.security.manager -Djava.security.policy="<path_to_your_catalina_policy_file>"
    run tomcat server

Check to see if its working:
    open browser
    go to localhost:<tomcat_portnumber>/<your_context_path_foundinserver.xml>/main
    You should see helloworld and in your system console, see the contents of txtServer displayed on console of server and tomcat
