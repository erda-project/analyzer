ARG DOCKER_REGISTRY
FROM ${DOCKER_REGISTRY}/terminus/erda-flink-1.12.2:20210317-54929f2

RUN echo "Asia/Shanghai" | tee /etc/timezone

WORKDIR $FLINK_HOME

ARG APP
COPY dist/${APP}.jar $FLINK_JOB_ARTIFACTS_DIR/${APP}.jar
