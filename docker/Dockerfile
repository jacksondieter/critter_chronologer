FROM mysql:latest

ENV MYSQL_ROOT_PASSWORD=my-secret-pw
ENV MYSQL_USER=myuser
ENV MYSQL_PASSWORD=mypassword
ENV MYSQL_DATABASE=exampledb

COPY dockerdb.sql /docker-entrypoint-initdb.d/

CMD ["mysqld"]

EXPOSE 3306
