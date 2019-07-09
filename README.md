# thymeleaf
Taking exmple from spring-mvc-forms-thymeleaf. Try to build a web application for the initial PCB board fitting module.
To start the application you can do one of following: 
  1) With gradle installed, just run following in the root dir after clone the repo : gradle bootRun
  2) From the root dir of repo, run : 
    java -jar dist/thymeleaf.war 
  Or if you want to debug (debug port is 5105 for this example)
    java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5105 -jar dist/thymeleaf.war
After application start, point you web broswer to http://localhost:8081

