package org.example.api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.example.models.ModelWithOrders;
import org.example.utils.CourierApiMethod;
import org.junit.Assert;
import org.junit.Test;

public class GetOrdersTest {

    @Test
    public void shouldReturnNonEmptyListOfOrders() {
        checkGetOrdersResponse();
    }

    @Step("Проверка получения списка заказов")
    private void checkGetOrdersResponse() {
        Response response = new CourierApiMethod().getOrderList();
        var responseData = response.as(ModelWithOrders.class);
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertFalse(responseData.getOrders().isEmpty());

    }
}

