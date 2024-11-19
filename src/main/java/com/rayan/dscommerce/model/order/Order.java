package com.rayan.dscommerce.model.order;

import com.rayan.dscommerce.model.OrderStatus;
import com.rayan.dscommerce.model.user.User;

import java.time.Instant;

public class Order {

    private Long id;

    private Instant moment;

    private OrderStatus status;

    private User client;

    public Order() {}

    public Order(Long id, Instant moment, OrderStatus status, User client) {
        this.id = id;
        this.moment = moment;
        this.status = status;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }
}
