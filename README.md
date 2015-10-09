# InMemoryDBSpringApp

Sample example for setting up in-memory H2 database with Spring
The in-memory database would be used for integration testing and as an embedded database for the application.

To use the in-memory database for testing, simply execute your tests or use maven to run all the tests. The current
configuration of the database for testing is private only i.e only the application can access this database. It will
be wiped off when the tests complete. Details about configuration can be found at test-application.properties file.


To use the h2 database for local deployment and persistence, need to follow
1. Start the H2 web console
    java -cp h2-1.4.189.jar org.h2.tools.Console

2. Use the Generic H2 embedded mode.

3. Use the jbdc url as found in application.properties file. This will persists your database in form of following files

test_db.mv.db
test_db_auto.lock.db
test_db_auto.mv.db
test_db_auto.trace.db


4. This is also a Spring MVC application set up with annotations.

5. Enabled Rest Web services. To try out, use the following
http://localhost:9090/rest/persons

With header
Accept - application/json