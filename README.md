# ABOUT

A Web Test Framework for developing regression suites. The test cases can be run locally and in remote server.

# STEPS FOR INTEGRATING TESTNG & REPORT PORTAL

1. Download the latest docker-compose.yml
   from [here](https://github.com/reportportal/reportportal/blob/master/docker-compose.yml), a quicker way to download
   this is to use below command
   `curl https://raw.githubusercontent.com/reportportal/reportportal/master/docker-compose.yml -o docker-compose.yml`
2. Once downloaded, execute the below command to pull the required images and start containers
   `docker-compose -p reportportal up -d --force-recreate`
3. Verify login http://localhost:8080/ui/#login with `default\1q2w3e` or `superadmin\erebus`
4. Create a blank project and copy and paste the below config in `reportportal.properties` under `src/test/resources`.
   See the table below.
5. Read these great posts written below by [Automation Hacks](https://github.com/automationhacks) to configure the Report
   portal.
   By far, these are the only posts with accurate steps.
   1. Further reading on setting up the reportportal
      is [here](https://automationhacks.io/2020/03/02/how-to-setup-reportportal-on-a-local-docker-instance/).
   2. Further reading on configuring logback with reportportal to push logs
      is [here](https://automationhacks.io/2020/09/25/logging-integration-with-logback-testng-in-report-portal/).

| Sl.No | Report portal Property Name | Report portal Property Value |
|-------|-----------------------------|------------------------------|
| 1     | rp.endpoint                 | http://localhost:8080        |
| 2     | rp.api.key                  | <API_KEY>                    |
| 3     | rp.launch                   | Java launch                  |
| 4     | rp.project                  | web_tests                    |

#### REPORT PORTAL OUTPUTS

# STEPS FOR INTEGRATING TEST REPORTS [REPORT PORTAL URL] WITH DISCORD MESSAGE SERVICE

1. Create a discord account and follow the steps
   given [here](https://www.svix.com/resources/guides/how-to-make-webhook-discord/) to configure a message channel and
   send the test reports after the test execution.
2. Pass your channel's webhook token in the `SauceLabsPortalConstants` class.
3. Here we will send the Report Portal Launch URL along with test case metrics.
   So make sure that your report portal is
   up and running.
4. You're ready to execute your tests now. Follow the below section.

#### DISCORD OUTPUTS

**STEPS FOR THE TEST EXECUTION**

1. git clone https://github.com/iamcharankumar/web_test_framework.git
2. cd web_test_framework
3. git pull
4. mvn clean test -Dgroups=SAUCE_LABS_SMOKE,SAUCE_LABS_REGRESSION -Dtestng.parallel=methods -DthreadPoolSize=3
   -Ddataproviderthreadcount=3

**NOTE**

Run the above maven command (no testng.xml required) with the respective groups and thread counts.
The screenshot listeners are configured in "pom.xml" under "< property >" tag.

# PROPERTIES SETUP FOR SELENIUM GRID

- Run mode value can be 'local' or 'remote'
- You can configure it in the "config.properties" file.
- To run in remote server, follow the below docker setup instructions.

# DOCKER SETUP FOR SELENIUM GRID

###### Make sure to have the docker desktop and docker compose installed on your machine.

`docker ps -a`

###### Run the below command to delete all the docker containers (if only required)

`docker rm -vf $(docker ps -aq)`

###### Run the below command from the docker compose file (docker-compose-v3.yml) directory

###### To spin up 10 nodes in the grid we use --scale before the node's name.

`docker compose -f docker-compose-v3.yml up --scale chrome=10`

###### To kill a session in the selenium grid.

`curl -X DELETE http://localhost:4444/wd/hub/session/{session_id}`
