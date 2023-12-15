# ABOUT

A Web Test Framework for developing regression suites. The test cases can be run locally and in remote server.

STEPS FOR THE TEST EXECUTION
1. git clone https://github.com/iamcharankumar/web_test_framework.git
2. cd web_test_framework
3. git pull
4. mvn clean test -DsuiteXmlFile=swaglabs.xml -Dparallel=methods -Ddataproviderthreadcount=2 -Dthreadcount=3

# PROPERTIES SETUP FOR SELENIUM GRID

###### runmode value can be 'local' or 'remote'. To run in remote server, follow the below docker setup instructions.

# DOCKER SETUP FOR SELENIUM GRID

###### Make sure to have the docker desktop and docker compose installed in your machine.

`docker ps -a`

###### Run the below command from the docker compose file (docker-compose-v3.yml) directory

###### To spin up 10 nodes in the grid we use --scale before the node's name.

`docker compose -f docker-compose-v3.yml up --scale chrome=10`

###### To kill a session in the selenium grid.

`curl -X DELETE http://localhost:4444/wd/hub/session/{session_id}`