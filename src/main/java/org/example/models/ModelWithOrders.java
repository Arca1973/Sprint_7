package org.example.models;

import java.util.List;

public class ModelWithOrders {
    private List<OrderModel> orders;

    // Конструктор
    public ModelWithOrders(List<OrderModel> orders) {
        this.orders = orders;
    }

    // Геттеры и сеттеры
    public List<OrderModel> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderModel> orders) {
        this.orders = orders;
    }

    // Дополнительные методы (если нужно)
    @Override
    public String toString() {
        return "ModelWithOrders{" +
                "orders=" + orders +
                '}';
    }
}
