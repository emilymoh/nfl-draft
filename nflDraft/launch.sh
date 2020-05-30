# Remove all old docker instances
# docker stop nflDraft-postgres;
# docker rm nflDraft-postgres;

# # Start a new docker instance
# docker run -p5432:5432 --name nflDraft-postgres -e POSTGRES_PASSWORD=password -e POSTGRES_USER=emilymohrenweiser -d postgres

# Run App
echo "Please be patient, this process will take a few moments. Disregard all messages until prompted..."
mvn compile -q -B
# mvn clean
mvn package -B -Dmaven.test.skip --quiet 
clear
mvn -q -B exec:java
# POSTGRES_IP=127.0.0.1 POSTGRES_PORT=5432 POSTGRES_USER=emilymohrenweiser POSTGRES_PASS=password mvn exec:java

#java -jar target/nflDraft-1.0-SNAPSHOT-jar-with-dependencies.jar