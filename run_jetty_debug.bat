call set MAVEN_OPTS=-Xdebug -Xnoagent -Xrunjdwp:transport=dt_socket,address=8484,server=y,suspend=n %MAVEN_OPTS%
call mvn jetty:run -DskipTests=true