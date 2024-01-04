package com.nikea.orderservice.service.logic.impl;

import com.nikea.orderservice.dao.model.Order;
import com.nikea.orderservice.dao.model.OutboxMessage;
import com.nikea.orderservice.dao.repository.OrderRepository;
import com.nikea.orderservice.dao.repository.OutboxRepository;
import com.nikea.orderservice.service.dto.OrderCreationEvent;
import com.nikea.orderservice.service.dto.OrderDto;
import com.nikea.orderservice.service.logic.OutboxService;
import com.nikea.orderservice.service.logic.messaging.MessageProducer;
import com.nikea.orderservice.service.mapper.OrderMapper;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class OutboxServiceImpl implements OutboxService {

    private OutboxRepository outboxRepository;
    private OrderRepository orderRepository;
    private OrderMapper orderMapper;
    private MessageProducer messageProducer;

    @Override
    @Transactional
    public Order saveAndSend(OrderDto orderDto) {
        Order order = orderRepository.save(orderMapper.toEntity(orderDto));
        OutboxMessage outbox = new OutboxMessage();
        outbox.setDateTime(LocalDateTime.now());
        outbox.setMessage(new OrderCreationEvent(order.getId(), orderDto.getFurnitureId()));
        outboxRepository.save(outbox);
        return order;
    }

    @Scheduled(fixedDelayString = "20000") // hard-coded in demo purposes, in real application delay time should be placed in properties file
    public void sendAndDelete() {
        List<OutboxMessage> outboxEntities = outboxRepository.findAll();
        for (OutboxMessage outbox : outboxEntities) {
            messageProducer.sendOrderCreationEvent(outbox.getMessage());
            outboxRepository.deleteById(outbox.getId());
        }
    }
}
