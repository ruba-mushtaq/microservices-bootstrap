package com.cobstack.notificationservice.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.cobstack.notificationservice.dto.InventoryEvent;
import com.cobstack.notificationservice.dto.OrderEvent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class NotificationListener {

    @KafkaListener(topics = "order-events", groupId = "notification-group")
    public void handleOrderEvent(OrderEvent event) {
        // For demo: log notifications
        log.info("Notification: Order [{}] by User [{}] has status [{}]. Products: {}",
                event.getOrderId(), event.getUserId(), event.getStatus(), event.getProductIds());
    }

    @KafkaListener(topics = "inventory-events", groupId = "notification-group")
    public void handleInventoryEvent(InventoryEvent event) {
        log.info("Notification: Inventory for Product [{}] changed by [{}]. Action: [{}]",
                event.getProductId(), event.getQuantityChanged(), event.getAction());
    }
}
