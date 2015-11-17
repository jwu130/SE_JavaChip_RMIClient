# SE_JavaChip_RMIClient
JavaChip's Assignment 2 and 3 for CS 370 Software Engineering.  

ReadMe - Basics

*Replace the brackets and the contents of the elements here that are represented in this doc by "< >" with your own paths
*You must create two separate directories one for client, the other for the server side.

Requirements:
    jdk and jre (1.8.0)
    Eclipse J2EE
    Apache Tomcat (v. 7.0 suggested)

Server repository: https://github.com/jwu130/SE_JavaChip_RMIServer

Set Up:
    
    Server-Side:
        
    
    Client-Side:
        Create tomcat server and deploy client application  (src files from https://github.com/jwu130/SE_JavaChip_RMIClient)
            The main servlet will take two parameters upon initialization:
                1. The port that the RMI client is allowed to listen on
                2. The IP address of the machine that the RMI Server is running on
        <!--Edit your security policy:-->
        <!--open catalina.policy-->
        <!--add: -->
        <!--    grant {-->
        <!--        permission java.security.AllPermission;-->
        <!--    };-->
        
        <!--open cmd prompt-->
    
<!--    run rmi server jar file-->
<!--    run RMI_BioAPI_AsteriskJava_Server with "java -Djava.security.manager -Djava.security.policy=<\path\to\your\policy\file>         -jar RMI_BioAPI_AsteriskJava_Server <your_rmi_registry_port> <your_server_port_number>"-->
    
<!--    open your eclipse-->
<!--    deploy war file to the server-->
<!--    run tomcat server-->

<!--Check to see if its working:-->
<!--    open browser-->
<!--    go to localhost:<tomcat_portnumber>/<your_context_path_foundinserver.xml>/main-->
<!--    You should see helloworld and in your system console, see the contents of txtServer displayed on console of server and tomcat-->
