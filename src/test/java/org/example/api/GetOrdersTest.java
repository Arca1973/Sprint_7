package org.example.api;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.example.models.ModelWithOrders;
import org.example.utils.OrderApiMethod;
import org.junit.Assert;
import org.junit.Test;

public class GetOrdersTest {

    @Test
    @DisplayName("Проверка получения списка заказов")
    public void shouldReturnNonEmptyListOfOrders() {
        checkGetOrdersResponse();
    }

    @Step("Отправляем запрос на получение списка заказов")
    private void checkGetOrdersResponse() {

        Response response = new OrderApiMethod().getOrderList();
        var responseData = response.as(ModelWithOrders.class);
        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
        Assert.assertFalse(responseData.getOrders().isEmpty());

    }
}

