# VCS testing tool
## Main Technologies
### Java 17
### TestNg framework
### Selenide/Selenium
### REST-assured
### Allure
### Lombok
### Github actions

Preconditions:
- 
On test start tool creates new repository, adds 3 new branches, adds commits to 2 created branches, creates PR for one of the branches

Implemented test cases for github UI application
- 
- Login
- Add commit to the branch
- Create PR
- Merge PR
- Create new branch

UI tests added to ui_tests.xml suite

Additional tests for Github api:
-
- Create repo test
- Delete repo test
- Create branch test
- Delete branch test

API tests added to api_tests.xml suite

## How to execute
## UI tests (due to security constraints can't be executed in Github actions)
### Run tests using maven:
- run UI tests: `mvn clean test -Dvcs.token=#### -Dvcs.username=##### -Dvcs.userpwd=#####`
#### Required parameters
- -Dvcs.token - API token of the version control system
- -Dvcs.username - VCS system user
- -Dvcs.userpwd - user's password

#### Optional parameters
Selenide provides options to prepare system via configuration, e.g. to change browser -Dselenide.browser can be used

### For allure report go to the target and run allure serve

## REST API tests (additional github resp api tests for CI demo, can be executed both locally/CI)
### Run tests using maven:
- run UI tests: `mvn clean test -Dvcs.token=#### -Dvcs.username=##### -Dvcs.userpwd=##### "-Dsurefire.suiteXmlFiles=src/test/resources/suites/api_tests.xml"`
#### Required parameters
- -Dvcs.token - API token of the version control system
- -Dvcs.username - VCS system user
- -Dvcs.userpwd - user's password
- --Dsurefire.suiteXmlFiles - path to API tests suite

### Link to Github actions workflow https://github.com/khadasevich/control-version-automation/actions/workflows/maven.yml
### Link to the latest allure report https://khadasevich.github.io/control-version-automation/34/index.html

