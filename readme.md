Demonstrating the usage of websocket usage through gateway with stomp (websockets from server are relayed through RabbitMq).  
Websockets are working however initial hanshake is not working due to invalid header passed through zuul (keep-alive instead of Update)

For this to work it's needed to have installed RabbitMq locally and configured the [stomp adapter](https://www.rabbitmq.com/stomp.html)