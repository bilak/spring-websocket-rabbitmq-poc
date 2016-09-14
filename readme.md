Demonstration of WebSocket usage through [Zuul](https://github.com/Netflix/zuul) gateway with Stomp (websockets from server are relayed through RabbitMq).  

The problem of connection=keep-alive header is fixed on backend application with WebSocketHeaderFilter 

####***RABBITMQ CONFIGURATION***
(in this demo {username=cloud} {password=cloud} {vhost=/cloud})
* enable management console (Rabbit web console: http://{server}:15672/)   
```rabbitmq-plugins enable rabbitmq_management```
* enable stomp plugin   
```rabbitmq-plugins enable rabbitmq_stomp```
* add user   
```rabbitmqctl add_user {username} {password}```
* add vhost   
```rabbitmqctl add_vhost {vhost}```
* add permissions to vhost for given user   
```rabbitmqctl set_permissions -p {vhost} {username} ".*" ".*" ".*"```