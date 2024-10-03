<p align="center">
  <img src="https://github.com/raffaeleav/card-exchange/assets/114619463/0e955bc1-673b-49be-aa26-eef3be846388" width="512" heigth="120">
</p>

<p align="center">
  An e-commerce platform developed as a project for the Ingegneria del Software (Software Engineering) course, part of the 
  Computer Science Bachelor's Degree program at the University of Salerno
</p>


## Table of Contents
- [Authors](#Authors)
- [Overview](#Overview)
- [Preview](#Preview)
- [Features](#Features)
- [How to replicate](#How-to-replicate)
- [Dependencies](#Dependencies)
- [Built with](#Built-with)


## Authors
| Name | GitHub profile |
| ---- | --------- |
| Aviello Raffaele | [raffaeleav](https://github.com/raffaeleav) |
| Di Domenico Francesco | [FrancescoDD00](https://github.com/FrancescoDD00) |
| Menzione Michele | [Michibit](https://github.com/Michibit) |
| Sautariello Salvatore | [SSalvatore001](https://github.com/SSalvatore001) |


## Overview 
   Card EXchange was developed with the goal of appraoching software development as a team, with the aid of corporate collaboration tools such as [Slack](https://slack.com/intl/it-it/?utm_source=rachelandreago) and [Trello](https://trello.com/it).
 The project covered all phases of waterfall development method, from requirements gathering to testing. The web application offers to users the ability to explore a wide range of collectible 
 trading cards, purchase them, leave reviews, trade cards with other users, and participate in discussions on a forum.


## Preview
<p>
  <img src="https://github.com/raffaeleav/card-exchange/assets/114619463/1536f969-824a-40e3-a4c7-e09a66f5c302" width="400" heigth="400">
</p>


## Features
1) Purchase collectible trading cards
2) Create a discussion on the forum
3) Participate in a discussion on the forum
4) Leave a review of a purchased product
5) Trade cards with other users


## How to replicate
1) Clone the repository
```bash
git clone https://github.com/raffaeleav/card-exchange.git
```
2) Switch to the project directory
```bash
cd card-exchange
```
3) Execute the database script (be sure to change the mysql server credentials in storage.ConPool)
```bash
sudo mysql -u root -p < ./src/main/webapp/database/database.sql
```
4) Populate the database
```bash
sudo mysql -u root -p < ./src/main/webapp/database/popola-db.sql
sudo mysql -u root -p < ./src/main/webapp/database/popola-db-card.sql
```
4) Build the project
```bash
mvn install
```
5) Copy the .war file to the webapps directory of your Tomcat server
```bash
sudo cp target/ProgettoIS-1.0-SNAPSHOT.war /opt/tomcat/apache-tomcat-10.1.30/webapps
```
6) Start the Tomcat server (assuming you have created a tomcat systemd service)
```bash
sudo systemctl start tomcat
```
7) Open the browser and access the webapp running at localhost
```bash
xdg-open 'http://localhost/popshop-retry-1.0-SNAPSHOT'
```


## Dependencies 
- [JDK 19](https://www.oracle.com/java/technologies/downloads/#java19 "JDK 19")
- [Apache Tomcat 10](https://tomcat.apache.org/download-10.cgi)
- [MySQL](https://dev.mysql.com/downloads/installer/)
  - [Connector/J](https://dev.mysql.com/downloads/connector/j/)
- [Maven](https://maven.apache.org/)


## Built-with
- [Java](https://www.oracle.com/java/technologies/downloads/#java19 "JDK 19") - used for the backend implementation and business logic of the web app
- [HTML](https://www.w3schools.com/html/default.asp) - used for the frontend development of the web app
- [CSS](https://www.w3schools.com/css/) - used for formatting and managing the responsive layout of the web app
- [JavaScript](https://www.w3schools.com/js/) - used for form validation
- [AJAX](https://www.w3schools.com/js/js_ajax_intro.asp) - used for making asynchronous calls during product searches
- [SQL](https://www.w3schools.com/sql/) - used for implementing the physical schema of the database
- [Maven](https://maven.apache.org/) - used for for dependency management
