FROM ubuntu:latest
WORKDIR /AutomationPractice

COPY src/test /AutomationPractice/src/test
COPY pom.xml /AutomationPractice

RUN apt-get update && \
    apt-get install -y maven && \
    mvn dependency:resolve

CMD ["mvn","clean","test","-Dcucumber.options--plugin pretty --glue step_definitions src/test/java/org/automationtest/RunWebTest.java"]

