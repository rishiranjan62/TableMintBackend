package com.tablemint.events;

import com.tablemint.model.OrderStatus;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

/**
 * Published when an order reaches READY or SERVED so recipe stock deduction can run after commit.
 */
@Getter
public class OrderRecipeDeductionEvent extends ApplicationEvent {
    private final Long orderId;
    private final Long actorUserId;
    private final OrderStatus newStatus;


    public OrderRecipeDeductionEvent(Object source, Long orderId, Long actorUserId, OrderStatus newStatus) {
        super(source);
        this.orderId = orderId;
        this.actorUserId = actorUserId;
        this.newStatus = newStatus;
    }
}
