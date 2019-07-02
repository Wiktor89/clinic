FROM registry-test.alfa-bank.kz/auqa/openjdk:8-jre-alpine-lc

COPY target/clinic-*.jar /srv/service.jar

ENV TZ=Asia/Almaty \
    CONFIG_SECRET=password \
    LOG_LEVEL=info \
    SHOW_SQL=false \
    JAVA_OPTS="-server -Xms32m -Xmx512M -XX:+UseG1GC -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/tmp/heap_log.hprof"

RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && \
    echo $TZ > /etc/timezone

EXPOSE 8080
WORKDIR /srv
HEALTHCHECK --start-period=90s --interval=60s --timeout=15s CMD /usr/bin/curl -sS --fail http://localhost:8080/health || exit 1
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /srv/service.jar" ]