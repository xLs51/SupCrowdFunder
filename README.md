SupCrowdFunder
======

SupTracking is a web application made with Java EE and its Android application.

It is a Proof of Concept of a crowdfunding website.

Functionalities
------------

- Registered user :
  - Contribute on projects
  - Create a project
  - View and edit his profile

- Administrator :
  - View all important data
  - CRUD operation for projects
  - CRUD operation for users
  - CRUD operation for categories
  
The Android application implements the registered functionalities via web service.

Installation
------------

- Install Apache Tomcat
- Download this project
- Edit the [`persistence.xml`][1] with your database settings
- Deploy the web application
- Replace the URL `"http://YOUR_URL/SupCrowdFunder/";` of all the .java files in the [Android Web Service folder][2] by the URL of the web application
- Launch the Android application

------------
###### SUPINFO Project - 3JVA - 12/2013

[1]: https://github.com/xLs51/SupCrowdFunder/blob/master/SupCrowdFunder/WebContent/WEB-INF/classes/META-INF/persistence.xml
[2]: https://github.com/xLs51/SupCrowdFunder/tree/master/SupCrowdFunderAndroid/src/com/supinfo/supcrowdfunderandroid/dao/webservice
