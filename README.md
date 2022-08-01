# api-starter

# Open Telemetry Setup 
This open telemetry project uses Java SDK agent to collect the logs, traces and spans. Then it will send the data to otel collector. The Otel collector is service which can be used to collect the log data and send it to any log parsing platform we want, example prometheus, kibana, etc.

In this project, we send data to otel collector using java agent and in turn it will send the data to kafka and Jaeger for processing.

## Command to set up the Project
1) First bring up the Kafka, Jaeger and Otel collector services.
 ```shell
    docker-compose -f docker-compose-kafka.yml up
  ```
wait of some time for the services to be up and running.
 ```shell
    docker-compose -f docker-compose-otel.yml up
 ```
2) Run the Example Project with java agent.
```shell
java \                                      
  -Dotel.service.name=api_test \
  -javaagent:opentelemetry-javaagent.jar \
  -jar build/libs/api-starter-0.0.1-SNAPSHOT.jar
```  
3) Hit the endpoint and see the data in the console.
```shell
curl -X GET http://localhost:8080
```
4) Verify the data in Jaeger : open the browser and go to http://localhost:16686
   1) documentation link : https://www.jaegertracing.io/docs/1.36/
5) Verify the data in Kafka : 
   1) kafka docker doc link : https://developer.confluent.io/quickstart/kafka-docker/
```shell
docker exec --interactive --tty kafka \
  kafka-console-consumer --bootstrap-server kafka:29092 \
                       --topic otlp_spans \
                       --from-beginning
                       
docker exec --interactive --tty kafka \
  kafka-console-consumer --bootstrap-server kafka:29092 \
                       --topic otlp_logs \
                       --from-beginning
                       
docker exec --interactive --tty kafka \
  kafka-console-consumer --bootstrap-server kafka:29092 \
                       --topic otlp_metrics \
                       --from-beginning
```
## References 
https://github.com/open-telemetry/opentelemetry-java  

https://github.com/open-telemetry/opentelemetry-java/tree/main/exporters

https://github.com/open-telemetry/opentelemetry-collector-contrib/

https://github.com/open-telemetry/opentelemetry-collector-contrib/tree/main/exporter

https://developer.confluent.io/quickstart/kafka-docker/

https://www.jaegertracing.io/docs/1.36/