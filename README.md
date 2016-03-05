# sonar-portability-plugin

This plugin calculates (using `PROPE`) portability metrics for a process. It will analyze most notably processes written in BPEL 2.0. After computing the metrics will be represent in an own portability dashboard. It's also possible to drill down on file level and detect problems highlighted in code with short issue description. 


## Software Requirements
- JDK 1.8.X
  - `JAVA_HOME` should point to the jdk directory
  - `PATH` should include `JAVA_HOME/bin`
- apache maven 3.3.X
- sonarqube-5.X.X
- sonar-runner-dist-2.4 or other analyzer (ant, maven)
- sonarqube has its embedded H2 database, but it is not able to export or save data from there. If data should be able to export and make a backup of them it is recommended to install an other database like MySQL 5.x, Oracle 10g/11g, PostgreSQL 8.x/9.x, Microsoft SQLServer 2008/2012.
  
## Licensing
[MIT license](http://opensource.org/licenses/MIT)

## Usage
1. install all required software
2. build the plugin as a jar file
3. copy the in the downloads directory of sonarqube server. (sonarqube-5.x.x/extensions/downloads)
4. start sonarqube server (sonarqube-5.x.x/bin/`your system software`)
5. login as admin and activate in the rules tab all bpel rules
6. analyze a BPEL project (with e.g. sonar-runner) and open portability dashboard (on sonarqube server)for this project after.

Please note:
 -Sonarqube server have run before analyze is startet
 -Projects that should be analyzed by sonarqube need a properties file in project root folder.
properties file should at least look like this:

`# Required metadata
sonar.projectKey=test-bpel
sonar.projectName=test-bpel
sonar.projectVersion=1.0
`# Comma-seperated paths to directories with sources (required)
sonar.sources=src

`# Language
sonar.language=bpel

`# Ecoding of the source files
sonar.sourceEncoding=UTF-8



## Project Structure

| Package     | Purpose          | 
| ------------- |-------------| 
| `src/main/java/de/uniba/dsg/sonar/portability `     | plugin start class and all defined metrics| 
| `src/main/java/de/uniba/dsg/sonar/portability/batch `     | classes that implement the main workflow, such as traversing a directory structure and triggering the metrics computation and reporting | 
| `src/main/java/de/uniba/dsg/sonar/portability/checks`     | check for existing BPEL repository in Sonarqube| 
| `src/main/java/de/uniba/dsg/sonar/portability/language`     | class(es) that defines the languages that can analyzed with the plugin| 
| `src/main/java/de/uniba/dsg/sonar/portability/rules`     | classes to define profiles and rules| 
| `src/main/java/de/uniba/dsg/sonar/portability/ui`     | class to create the dashboard with the calculated metrics| 
| `src/main/de/uniba/dsg/sonar/portability/utility`     | class for helping to calculate metrics| 


## Authors 

Matthias Weiß
