This project contains the homework project. This project is built with Java SDK 15.0.1.  The project contains one test for the homework. The project also built in Page Object Model which contains two classes for two pages, a test folder with one test class and other important folders such as base, configuration, localdriver, listeners, utils. 

Installation: After pulling the project from GIT Repo, you need the copy the necessary chromedriver to “src/test/resources/chromedriver/windows” folder,then you can run mvn  clean install -U -DskipTests. 

Execution:  1) After entering the project folder in the command prompt, the project can be installed and test can be run with this command “mvn clean verify”. 
2)  The test can be run with “mvn test” command in command prompt (in the project folder) after running this command for installation “mvn clean install -U -DskipTests”
3) The test can be run from IDE by running the testing.xml file.

Reporting: The extent html report can be found in “results” folder and all the screenshots can be found in “screenShot” folder with timestamps as the names of the screenshots. After a successful completion of the test one screenshot is saved before tearing down the running instances and added to extent html report.

Although this project contains single test, I have designed the test to run parallel. The number of threads is set to 1 and if needed it can be increased. The test can be modified to take multiple sets of search parameters using DataProvider annotation and setting the thread number > 1.



