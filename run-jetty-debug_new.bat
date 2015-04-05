set MAVEN_OPTS=-Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=4000,server=y,suspend=n -Xmx512m -XX:MaxPermSize=128m
call mvn clean jetty:run %1