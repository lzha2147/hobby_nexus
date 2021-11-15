# ELEC5619

## Environment Setup

### Add project to Tomcat server (v8.5)
- Right click Servers>Tomcat server, then select “Add and Remove”
- Add the project to the “Configured” section, then click finish
- Then right click Tomcat server, then click “Start”
- Type http://localhost:8080/nexus in browser


### Set up MySQL Database
- Make sure MySQL server is running on computer (on Windows >Servcies>MySQL80>Start)
- Configure local MySQL DB according to src>main>resources>database.properties
- Create database nexus in mySQL and run database.sql
- If error "Field <name> doesn't have a default value, delete and create database again
- start the MySQL Server 'sudo /usr/local/MySQL/support-files/mysql.server start'
 
 ## Project Setup 
 - Make sure Spring STS version 3.9 is installed 
 
 ### Through project import:
  - Click File > Import Projects > Maven > Existing Maven Projects
  - Click Browse and choose the project folder and click Finish
  - Add project to Tomcat Server and run
  
 ### Through Github link:
  - Make sure EGit extension is installed in Eclipse
  - Window > Show View > Others> Git > Git Repositories 
  - In Git Repositories window, click "Clone a repository and add the clone to this view"
  - In the URI, enter "https://github.sydney.edu.au/lzha2147/ELEC5619.git"
  - Enter user and password
  - Click next
  - Choose master branch and click next
  - Choose a local git directory to clone repository and click Finsih
  - Right click on the cloned repository in Git Repository Window and choose "Import Maven Project"
  - Add project to Tomcat Server and run
  
  ## Project Walkthrough
  
  1. Hobby
  - Users can search for hobbies through the search bar or links available in the homepage
  - Users can view the Hobby board for a particular hobby and read posts and comments made by other users.
  - Users must have an account to create hobbies or comment on posts.
  
  2. Event
  - Users can search for events through the search bar or links available in the homepage
  - Users can view the event information including location, date and attendees and join events.
  - Users must have an account to create and join events.

  
