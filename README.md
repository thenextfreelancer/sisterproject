# BayernId Testing Tool
This project is a Selenium AUTOMATION project based on selenium java for windows.

It is for testing the registration page here: 

> https://infra-pre.buergerserviceportal.de/bayern/freistaat/register


# Pre-requisites
System must have java 1.8 or above installed.

# How to build
Following steps should be followed to build the project:
1. Clone the project in local system by following commands:

> git clone https://github.com/thenextfreelancer/sisterproject

2. Go to /path/to/project/sisterproject and run following command

> mvn clean install

3. After running the command, maven build the project.

4. After successful build, user will find following files under target folder

/BayernIdTestingTool-1.0-jar-with-dependencies.jar


# How to execute
The project can be executed in 2 browsers
1. Chrome
2. Firefox

Note: Make sure you install latest updated browsers in the system which is selected above.

For running the project, go to the target repository by windows console. Run following command from windows console:

For Chrome:

> java -jar BayernIdTestingTool-1.0-jar-with-dependencies.jar --chrome=/path/to/chromedriver.exe

For Firefox:

> java -jar BayernIdTestingTool-1.0-jar-with-dependencies.jar --firefox=/path/to/geckodriver.exe
