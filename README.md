# SE_JavaChip_RMIClient
JavaChip's Assignment 2 and 3 for CS 370 Software Engineering.  

ReadMe - Basics

*You must create two separate directories one for client, the other for the server side.

Requirements:
    jdk and jre (1.8.0)
    Eclipse J2EE
    Apache Tomcat (v. 7.0 suggested)

Server repository: https://github.com/jwu130/SE_JavaChip_RMIServer

Server-Side:
    Run with two arguments
        1. Port for the RMI registry to listen on
        2. Port for the RMI server to listen on

Client-Side:
    Create tomcat server and deploy client application  (src files from https://github.com/jwu130/SE_JavaChip_RMIClient)
        The main servlet will take two parameters upon initialization:
            1. The port that the RMI client is allowed to listen on
            2. The IP address of the machine that the RMI server is running on
