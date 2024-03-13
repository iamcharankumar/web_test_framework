# ABOUT

A Web Test Framework for developing regression suites. The test cases can be run locally and in remote server.

STEPS FOR THE TEST EXECUTION

1. git clone https://github.com/iamcharankumar/web_test_framework.git
2. cd web_test_framework
3. git pull
4. mvn clean test -Dgroups=SAUCE_LABS_SMOKE,SAUCE_LABS_REGRESSION -Dtestng.parallel=methods -DthreadPoolSize=3
   -Ddataproviderthreadcount=3

### NOTE

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