FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD weather-springboot-0.0.1.jar api.jar
RUN sh -c 'touch /api.jar'
ENV JAVA_OPTS="-Dspring.profiles.active=prod"
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /api.jar" ]