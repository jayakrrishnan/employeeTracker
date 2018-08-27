# employeeTrackerService

#Requirements : 
  Java 10
  mvn 3.5.x or above
</br>
#Steps
  1) Clone this repo
  2) Run "mvn clean install"
  3) Run employeeTracker*.jar
      java -Dspring.profiles.active=local -Duser.timezone=Asia/Kolkata -Dspring.employeeTracker.log.dir={Your_log_path} 
      -jar employeeTracker*.jar
</br>
#Swagger Details
  Url: http://localhost:8080//technomak/employeeTracker/documentation/swagger-ui.html
  </br>
  For all end points ,provide following header values </br>
  Content-Type :application/json
  </br>
  Accept-Language:application/json 
