package org.example.api;


import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.example.utils.OrderApiMethod;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;
import static org.example.utils.ApiConstants.*;


@RunWith(Parameterized.class)
@DisplayName("Успешная создание заказа сразными цветами")
public class СreateOrderTest {

    private String color;

    public СreateOrderTest(String color) {
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
        OrderApiMethod orderApiMethod = new OrderApiMethod();
        orderApiMethod.createOrder(color);
    }
}
