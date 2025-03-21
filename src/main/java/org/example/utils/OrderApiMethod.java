package org.example.utils;

import io.qameta.allure.Step;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.example.models.OrderRequest;

import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.example.utils.ApiConstants.ORDER_ENDPOINT;
import static org.hamcrest.Matchers.notNullValue;

public class OrderApiMethod extends BaseApiMethod {
    @Step("Получение списка заказов")
    public Response getOrderList() {

        return sendGetRequest(ORDER_ENDPOINT,"");
    }

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Step("Создание заказа")
    public void createOrder(String color) {
        try {
            OrderRequest orderRequest = buildOrderRequest(color);
            String jsonRequest = objectMapper.writeValueAsString(orderRequest);

            RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(jsonRequest)
                    .when()
                    .post(ORDER_ENDPOINT)
                    .then()
                    .assertThat().statusCode(HttpStatus.SC_CREATED)
                    .and()
                    .body("track", Matchers.notNullValue());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private OrderRequest buildOrderRequest(String color) {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setFirstName("Иван");
        orderRequest.setLastName("Иванов");
        orderRequest.setAddress("ул. Ленина, д. 25");
        orderRequest.setMetroStation("Площадь Революции");
        orderRequest.setPhone("+79161234567");
        orderRequest.setRentTime(7);
        orderRequest.setDeliveryDate("2023-10-01T10:00:00.000Z");
        if (color != null) {
            orderRequest.setColor(Arrays.asList(color.split(",")));
        }
        orderRequest.setComment("Без комментариев");
        return orderRequest;
    }


}

