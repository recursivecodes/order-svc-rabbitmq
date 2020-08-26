package codes.recursive.messaging;

import codes.recursive.domain.Order;
import io.micronaut.rabbitmq.annotation.Binding;
import io.micronaut.rabbitmq.annotation.RabbitClient;

@RabbitClient("micronaut.demo")
public interface OrderProducer {
    @Binding(value = "order")
    void send(Order order);
}