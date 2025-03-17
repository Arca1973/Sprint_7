package org.example.api;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;
import static io.restassured.RestAssured.given;
import static org.example.utils.ApiConstants.*;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class createOrderTest {

    private String color;

    public createOrderTest(String color) {
        this.color = color;
    }

    @Parameterized.Parameters(name = "Цвет {0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"BLACK"},
                {"GREY"},
                {"BLACK,GREY"},
                {null}
        });
    }

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void createOrderTest() {
        testCreateOrder(color);
    }

    @Step("Тестирование создания заказа")
    private void testCreateOrder(String color) {
        String requestBody = getRequestBody(color);

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(ORDER_ENDPOINT )
                .then()
                .assertThat().statusCode(201) .and()
                .body("track", notNullValue());

    }

    @Step("Формирование тела запроса")
    private String getRequestBody(String color) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"firstName\": \"Иван\",");
        sb.append("\"lastName\": \"Иванов\",");
        sb.append("\"address\": \"ул. Ленина, д. 25\",");
        sb.append("\"metroStation\": \"Площадь Революции\",");
        sb.append("\"phone\": \"+79161234567\",");
        sb.append("\"rentTime\": 7,");
        sb.append("\"deliveryDate\": \"2023-10-01T10:00:00.000Z\",");
        if (color != null) {
            sb.append("\"color\": [\"").append(color).append("\"],");
        }
        sb.append("\"comment\": \"Без комментариев\"");
        sb.append("}");
        return sb.toString();
    }
}
