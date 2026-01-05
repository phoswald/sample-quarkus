# sample-quarkus

Experiments with Quarkus, featuring:

- Static web content
- Dynamic web content using JAX-RS and Qute
- REST endpoints using JAX-RS 
- Postgres database using JPA and Hibernate

## Run Standalone

~~~
$ mvn clean verify
$ export APP_SAMPLE_CONFIG=ValueFromShell
$ java \
  -Dquarkus.http.port=8081 \
  -Dquarkus.http.auth.session.encryption-key=mysecretforsession \
  -Dquarkus.datasource.jdbc.url=jdbc:postgresql://localhost:5432/mydb \
  -Dquarkus.datasource.username=myuser \
  -Dquarkus.datasource.password=mypassword \
  -jar target/quarkus-app/quarkus-run.jar
~~~

There is no main class to run from the IDE. Use Dev Mode with Maven instead!

## Run with Docker

~~~
$ mvn clean verify -P docker
$ docker run -it --name sample-quarkus --rm \
  -p 8081:8080 \
  -e QUARKUS_HTTP_AUTH_SESSION_ENCRYPTION_KEY=mysecretforsession \
  -e QUARKUS_DATASOURCE_JDBC_URL=jdbc:postgresql://surin.home:5432/mydb \
  -e QUARKUS_DATASOURCE_USERNAME=myuser \
  -e QUARKUS_DATASOURCE_PASSWORD=mypassword \
  -e APP_SAMPLE_CONFIG=ValueFromDockerRun \
  philip/sample-quarkus:0.1.0-SNAPSHOT
~~~

### Run with Dev Mode

~~~
$ mvn quarkus:dev \
  -Dquarkus.http.port=8081 \
  -Dquarkus.http.auth.session.encryption-key=mysecretforsession \
  -Dquarkus.datasource.jdbc.url=jdbc:postgresql://localhost:5432/mydb \
  -Dquarkus.datasource.username=myuser \
  -Dquarkus.datasource.password=mypassword \
  -Dquarkus.hibernate-orm.database.generation=update
~~~

Note: JVM System Properties can be passed as normal command line arguments (using `-D...=...` syntax).

## Run Natively

~~~
$ mvn clean verify -P native
$ target/sample-quarkus-0.1.0-SNAPSHOT-runner \
  -Dquarkus.http.port=8081 \
  -Dquarkus.http.auth.session.encryption-key=mysecretforsession \
  -Dquarkus.datasource.jdbc.url=jdbc:postgresql://localhost:5432/mydb \
  -Dquarkus.datasource.username=myuser \
  -Dquarkus.datasource.password=mypassword
~~~

Note: JVM System Properties can be passed as normal command line arguments (using `-D...=...` syntax).

Verify native executable dependencies:

~~~
$ ldd target/sample-quarkus-0.1.0-SNAPSHOT-runner
    linux-vdso.so.1 (0x00007ffdbb115000)
    libz.so.1 => /lib/x86_64-linux-gnu/libz.so.1 (0x00007f2618ae6000)
    libpthread.so.0 => /lib/x86_64-linux-gnu/libpthread.so.0 (0x00007f2618ae1000)
    libdl.so.2 => /lib/x86_64-linux-gnu/libdl.so.2 (0x00007f2618adc000)
    librt.so.1 => /lib/x86_64-linux-gnu/librt.so.1 (0x00007f2618ad7000)
    libc.so.6 => /lib/x86_64-linux-gnu/libc.so.6 (0x00007f26188af000)
    /lib64/ld-linux-x86-64.so.2 (0x00007f2618b18000)
~~~

## URLs

- http://localhost:8081/
- http://localhost:8081/q/dev-ui
- http://localhost:8081/q/dev-ui/endpoints (leider fehlt der Prefix `/app` aus @ApplicationPath)

~~~
$ curl 'http://localhost:8081/app/rest/sample/time' -i
$ curl 'http://localhost:8081/app/rest/sample/config' -i
$ curl 'http://localhost:8081/app/rest/sample/echo-xml' -i -X POST \
  -H 'content-type: text/xml' \
  -d '<EchoRequest><input>This is CURL</input></EchoRequest>'
$ curl 'http://localhost:8081/app/rest/sample/echo-json' -i -X POST \
  -H 'content-type: application/json' \
  -d '{"input":"This is CURL"}'
$ curl 'http://localhost:8081/app/rest/tasks' -i
$ curl 'http://localhost:8081/app/rest/tasks' -i -X POST \
  -H 'content-type: application/json' \
  -d '{"title":"Some task","description":"This is CURL","done":true}'
$ curl 'http://localhost:8081/app/rest/tasks/5b89f266-c566-4d1f-8545-451bc443cf26' -i
$ curl 'http://localhost:8081/app/rest/tasks/5b89f266-c566-4d1f-8545-451bc443cf26' -i -X PUT \
  -H 'content-type: application/json' \
  -d '{"title":"Some updated task","description":"This is still CURL","done":false}'
$ curl 'http://localhost:8081/app/rest/tasks/5b89f266-c566-4d1f-8545-451bc443cf26' -i -X DELETE
~~~

## Security

See SQL DDL for [Postgres](src/main/resources/schema.postgresql.sql).

Create passwords for form-based authentication (table `user_`):

~~~
$ java -cp 'target/quarkus-app/lib/boot/*:target/quarkus-app/lib/main/*:target/classes' \
  com.github.phoswald.sample.security.PasswordUtility
~~~
