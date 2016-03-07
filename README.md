# sonar-portability-plugin

[![Build Status](https://travis-ci.org/uniba-dsg/sonar-prope-plugin.svg?branch=master)](https://travis-ci.org/uniba-dsg/sonar-prope-plugin)

sonar-portability-plugin is a plugin to the [SonarQube](http://www.sonarqube.org/) code quality management platform that calculates portability metrics for BPEL 2.0 processes. It computes the metrics using the [prope](https://github.com/uniba-dsg/prope) metrics suite and presents them in a portability dashboard in the SonarQube server. It offers support for drilling down on file level and  highlighting issues in code with short issue description. 


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
2. build the plugin as a jar file (using `mvn install`)
3. copy the in the downloads directory of sonarqube server (sonarqube-5.x.x/extensions/downloads)
4. start sonarqube server (sonarqube-5.x.x/bin/`your system software`)
5. login as admin and activate all bpel rules in the rules tab
6. analyze a BPEL project (with e.g. sonar-runner) and open portability dashboard (on sonarqube server) for this project

Please note:
- SonarQube server has to be running before starting an analysis
- Projects that should be analyzed by SonarQube require a properties file in project root folder.
properties file should at least look like this:

```
# Required metadata
sonar.projectKey=test-bpel
sonar.projectName=test-bpel
sonar.projectVersion=1.0
# Comma-seperated paths to directories with sources (required)
sonar.sources=src

# Language
sonar.language=bpel

# Ecoding of the source files
sonar.sourceEncoding=UTF-8
```



## Project Structure

| Package     | Purpose          | 
| ------------- |-------------| 
| `src/main/java/de/uniba/dsg/sonar/portability `     | plugin start class and all defined metrics| 
| `src/main/java/de/uniba/dsg/sonar/portability/batch `     | classes that implement the main workflow, such as traversing a directory structure and triggering the metrics computation and reporting | 
| `src/main/java/de/uniba/dsg/sonar/portability/checks`     | check for existing BPEL repository in SonarQube| 
| `src/main/java/de/uniba/dsg/sonar/portability/language`     | class(es) that defines the languages that can analyzed with the plugin| 
| `src/main/java/de/uniba/dsg/sonar/portability/rules`     | classes to define profiles and rules| 
| `src/main/java/de/uniba/dsg/sonar/portability/ui`     | class to create the dashboard with the calculated metrics| 
| `src/main/de/uniba/dsg/sonar/portability/utility`     | class for helping to calculate metrics| 


## Authors 

Matthias Weiß, [Jörg Lenhard](https://joerglenhard.wordpress.com/)
