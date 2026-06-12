FROM quay.io/wildfly/wildfly:latest

ENV JBOSS_HOME=/opt/jboss/wildfly

ADD target/library.war ${JBOSS_HOME}/standalone/deployments/

COPY server/module.xml ${JBOSS_HOME}/modules/system/layers/base/org/postgresql/main/
COPY server/postgresql-42.7.11.jar ${JBOSS_HOME}/modules/system/layers/base/org/postgresql/main/
COPY server/configure-datasource.cli /opt/jboss/configure-datasource.cli

RUN ${JBOSS_HOME}/bin/jboss-cli.sh --file=/opt/jboss/configure-datasource.cli