
package codes.recursive.messaging;

import codes.recursive.domain.Order;
import codes.recursive.domain.Shipment;
import codes.recursive.domain.ShipmentStatus;
import codes.recursive.service.OrderService;
import io.micronaut.rabbitmq.annotation.Queue;
import io.micronaut.rabbitmq.annotation.RabbitListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RabbitListener
public class ShipmentConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(ShipmentConsumer.class);
    private final OrderService orderService;

    public ShipmentConsumer(OrderService orderService) {
        this.orderService = orderService;
    }

    @Queue("shipment-queue")
    public void receive(
            Shipment shipment) {
        LOG.info("Shipment message received!");
        LOG.info("Updating order shipment status...");
        Order order = orderService.getOrderById(shipment.getOrderId());
        order.setShipmentStatus(ShipmentStatus.SHIPPED);
        orderService.updateOrder(order);
        LOG.info("Order shipment status updated!");
    }
}