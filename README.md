![Selenium](https://img.shields.io/badge/-selenium-%43B02A?style=for-the-badge&logo=selenium&logoColor=white)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Maven](https://img.shields.io/badge/apachemaven-C71A36.svg?style=for-the-badge&logo=apachemaven&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)

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
5. Read these great posts written below by [Automation Hacks](https://github.com/automationhacks) to configure the
   Report
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

<img width="1728" alt="Web_Tests_ReportPortal_Launch" src="https://github.com/iamcharankumar/web_test_framework/assets/29479534/51effb54-8318-4705-ab04-fe4431f7e040">
<img width="1728" alt="Web_Tests_ReportPortal_Test_Methods" src="https://github.com/iamcharankumar/web_test_framework/assets/29479534/ed758683-1e83-4020-acaa-97151941896d">
<img width="1728" alt="Web_Tests_ReportPortal_Failed" src="https://github.com/iamcharankumar/web_test_framework/assets/29479534/979cfb69-5ba7-4560-b3dc-a4884f9da388">

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

<img width="854" alt="Web_Tests_Discord" src="https://github.com/iamcharankumar/web_test_framework/assets/29479534/bb19fb36-6246-43b8-a8ab-5fbc1a373b7b">

# STEPS FOR THE TEST EXECUTION

1. `git clone https://github.com/iamcharankumar/web_test_framework.git`
2. `cd web_test_framework`
3. `git pull`
4. `mvn clean test -Dgroups=SAUCE_LABS_SMOKE,SAUCE_LABS_REGRESSION -Dthreads=3 -Ddataproviderthreadcount=3`

#### BROWSERS & RUN MODES

| Sl.No | Browser Name | Run Mode | mvn command                                                                                                                                   |
|-------|--------------|----------|-----------------------------------------------------------------------------------------------------------------------------------------------|
| 1     | Chrome       | Local    | `mvn clean test -Dgroups=SAUCE_LABS_SMOKE,SAUCE_LABS_REGRESSION -Dthreads=3 -Ddataproviderthreadcount=3`                                      |
| 2     | Chrome       | Headless | `mvn clean test -Drunmode=headless -Dgroups=SAUCE_LABS_SMOKE,SAUCE_LABS_REGRESSION -Dthreads=3 -Ddataproviderthreadcount=3`                   |
| 3     | Chrome       | Remote   | `mvn clean test -Drunmode=remote -Dgroups=SAUCE_LABS_SMOKE,SAUCE_LABS_REGRESSION -Dthreads=3 -Ddataproviderthreadcount=3`                     |
| 4     | Firefox      | Local    | `mvn clean test -Dbrowser=firefox -Dgroups=SAUCE_LABS_SMOKE,SAUCE_LABS_REGRESSION -Dthreads=3 -Ddataproviderthreadcount=3`                    |
| 5     | Firefox      | Headless | `mvn clean test -Drunmode=headless -Dbrowser=firefox -Dgroups=SAUCE_LABS_SMOKE,SAUCE_LABS_REGRESSION -Dthreads=3 -Ddataproviderthreadcount=3` |
| 6     | Firefox      | Remote   | `mvn clean test -Drunmode=remote -Dbrowser=firefox -Dgroups=SAUCE_LABS_SMOKE,SAUCE_LABS_REGRESSION -Dthreads=3 -Ddataproviderthreadcount=3`   |
| 7     | Edge         | Local    | `mvn clean test -Dbrowser=edge -Dgroups=SAUCE_LABS_SMOKE,SAUCE_LABS_REGRESSION -Dthreads=3 -Ddataproviderthreadcount=3`                       |
| 8     | Edge         | Headless | `mvn clean test -Drunmode=headless -Dbrowser=edge -Dgroups=SAUCE_LABS_SMOKE,SAUCE_LABS_REGRESSION -Dthreads=3 -Ddataproviderthreadcount=3`    |

**NOTE**: These above commands (no testng.xml required) will run the tests in parallel with the specified thread count
and with the respective groups and thread counts.
The screenshot listeners are configured in "pom.xml" under "< property >" tag.

# PROPERTIES SETUP FOR SELENIUM GRID

- Run mode value can be 'local' or 'remote'
- You can configure it in the "config.properties" file.
- To run in remote server, follow the below docker setup instructions.

# DOCKER SETUP FOR SELENIUM GRID

**Make sure to have the docker desktop and docker compose installed on your machine.**

**NOTE:** Since I'm running the `docker-compose-v3.yml` on Apple Chip, I'm using `docker-seleniarm` images.
More on the Docker images for Selenium (ARM/NON-ARM)
read this GitHub's [README](https://github.com/seleniumhq-community/docker-seleniarm).

# 🧪 ARM-Ready Selenium Grid with Docker Compose

> ⚠️ Tired of Selenium Docker setups breaking on your M1/M2 Mac? Here's a fully functional ARM-based Selenium Grid using [`seleniarm`](https://github.com/SeleniumHQ/docker-selenium#arm-support).

---

## 💡 Why This Exists

Most Selenium Docker images are built for **x86_64** architecture — which fails to run on modern **ARM64** systems like:

- Apple Silicon Macs (M1/M2)
- Raspberry Pi
- ARM-based CI runners

This project uses the [`seleniarm`](https://github.com/SeleniumHQ/docker-selenium#arm-support) images to build a **cross-platform, lightweight, plug-and-play** Selenium Grid. 🌍

---

## ⚙️ Tech Stack

- Selenium Grid Hub (ARM compatible)
- Chrome & Firefox Nodes (ARM)
- Docker Compose
- VNC Enabled for visual debugging
- Parallel test sessions

---

## 🧱 Folder Structure

Goto `/web_test_framework/src/main/java/resources` and execute the below docker commands to setup the `Seleniarm Gird`.

`docker ps -a`

###### Run the below command to delete all the docker containers (if only required)

`docker rm -vf $(docker ps -aq)`

###### To spin up 10 nodes in the grid we use --scale before the node's name.

`docker-compose -f docker-compose-v3.yml up --scale chrome=10 --scale firefox=10 -d --force-recreate`

###### To kill a session in the selenium grid.

`curl -X DELETE http://localhost:4444/wd/hub/session/{session_id}`

#### SELENIUM GRID OUTPUTS

<img width="1728" alt="7_Threads_Chrome_Remote" src="https://github.com/user-attachments/assets/52070b74-fed6-4880-a668-1a3743e3946c" />
<img width="1728" alt="Max_9_Threads_Firefox_Remote" src="https://github.com/user-attachments/assets/cc6ab5a8-3aa1-489d-972a-9f6c915da7a8" />

#### DECLUTTERING MAVEN OUTPUT

- Maven usually floods the console with logs during test execution, making it hard to spot what's important.
  A clean, minimal, Node.js-style output for a Java project felt impossible—until I
  found this [maven dependency](https://mvnrepository.com/artifact/me.fabriciorby/maven-surefire-junit5-tree-reporter).
- Its purpose is simple: **"What happened to my test cases?"** That’s exactly what it shows—straight to the point, no
  clutter.
- By following this [post](https://medium.com/wearewaes/my-journey-to-a-clear-test-output-in-maven-df82fe272249)
  by [Fabricio](https://github.com/fabriciorby),
  I was able to configure it easily and get the clean output shown below.
- The output works locally and in GitHub Actions as well.
- Huge respect and thanks to the author for this
  brilliant [work](https://github.com/fabriciorby/maven-surefire-junit5-tree-reporter?tab=readme-ov-file)!
  ❤️

## Star History

[![Star History Chart](https://api.star-history.com/svg?repos=iamcharankumar/web_test_framework&type=Date)](https://star-history.com/#iamcharankumar/web_test_framework&Date)
