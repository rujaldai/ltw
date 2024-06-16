MCVE for question: https://stackoverflow.com/questions/78606285/could-not-safely-identify-store-assignment-for-repository-candidate-interface-ev?noredirect=1#comment138594112_78606285

Could not safely identify store assignment for repository candidate interface even when the entity is annotated with jakarta.persistence.Entity

The rest of the issue is posted in the stack overflow link above.

To run:

Execute bootRun task from build.gradle
- This adds spring-instrumentation jar as a VM argument when running application

Notes:
- 'org.springframework.boot:spring-boot-starter-web' is not required. It's just there so that application does not stop immediately after it starts. It can also be used to verify the change is working correctly
- Spring data redis is there intentionally to replicate this issue. The application is supposed to have multiple datasource to confuse spring

![image](https://github.com/rujaldai/ltw/assets/49880648/aa38bef1-e822-425e-953d-e336dc1b47c3)


------------ Not related (For future reference)
I faced https://github.com/spring-projects/spring-data-jpa/issues/3473 issue when using springframework.boot plugin 3.3.0. So, downgraded to 3.2.6
