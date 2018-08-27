# employeeTrackerService
</br></br>
#Requirements : </br> </br>
  Java 10 </br>
  mvn 3.5.x or above
</br>
#Steps  </br></br>
  1) Clone this repo </br>
  2) Run "mvn clean install" </br>
  3) Run employeeTracker*.jar </br>
      java -Dspring.profiles.active=local -Duser.timezone=Asia/Kolkata -Dspring.employeeTracker.log.dir={Your_log_path} 
      -jar employeeTracker*.jar
</br></br>
#Swagger Details
</br></br>
  Url: http://localhost:8080//technomak/employeeTracker/documentation/swagger-ui.html
  </br>
  For all end points ,provide following header values 
  </br>
  Content-Type :application/json
  </br>
  Accept-Language:application/json 
