package org.example.api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.example.models.GetFieldsByResponse;
import org.example.utils.CourierApiMethod;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.example.utils.ApiConstants.*;


public class LoginCourierTest {

    @Before
        // Данные курьера
        public void setUp() {
            new CourierApiMethod().createCourier(LOGIN, PASSWORD, FIRST_NAME);
        };

    @Test
    @Step("Успешная авторизация курьера при вводе валидных данных")
    public void validDataCourierLoginTest() {
        Response response=  new CourierApiMethod().loginCourier(LOGIN, PASSWORD);
        var responseData = response.as(GetFieldsByResponse.class);
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertNotEquals("", responseData.getId());
    }

    @Test
    @Step("Без успешная авторизация  курьера без пароля")
    public void unsuccessfulCourierAuthenticationWithoutPasswordTest() {
        Response response=  new CourierApiMethod().loginCourier(LOGIN, "");
        var responseData = response.as(GetFieldsByResponse.class);
        Assert.assertEquals(400, response.getStatusCode());
        Assert.assertEquals("Недостаточно данных для входа", responseData.getMessage());
    }
    @Test
    @Step("Без успешная авторизация  курьера без логина")
    public void unsuccessfulCourierAuthenticationWithoutLoginTest() {
        Response response=  new CourierApiMethod().loginCourier("", PASSWORD);
        var responseData = response.as(GetFieldsByResponse.class);
        Assert.assertEquals(400, response.getStatusCode());
        Assert.assertEquals("Недостаточно данных для входа", responseData.getMessage());
    }
    @Test
    @Step("Без успешная авторизация  курьера без логина и без пароля")
    public void unsuccessfulCourierAuthenticationWithoutLoginWithoutPasswordTest() {
        Response response=  new CourierApiMethod().loginCourier("", "");
        var responseData = response.as(GetFieldsByResponse.class);
        Assert.assertEquals(400, response.getStatusCode());
        Assert.assertEquals("Недостаточно данных для входа", responseData.getMessage());
    }

    @Test
    @Step("Без успешная авторизация  курьера с несуществующим паролем")
    public void unsuccessfulCourierAuthenticationWithNonexistentPasswordTest() {
        Response response=  new CourierApiMethod().loginCourier(LOGIN, "NonexistentPassword");
        var responseData = response.as(GetFieldsByResponse.class);
        Assert.assertEquals(404, response.getStatusCode());
        Assert.assertEquals("Учетная запись не найдена", responseData.getMessage());
    }
    @Test
    @Step("Без успешная авторизация  курьера с несуществующим логином")
    public void unsuccessfulCourierAuthenticationWithNonexistentLoginTest() {
        Response response=  new CourierApiMethod().loginCourier("NonexistentLogin", PASSWORD);
        var responseData = response.as(GetFieldsByResponse.class);
        Assert.assertEquals(404, response.getStatusCode());
        Assert.assertEquals("Учетная запись не найдена", responseData.getMessage());
    }



    @After public void tearDown() {
        // Удаляем созданного курьера
        Response response = new CourierApiMethod().loginCourier(LOGIN, PASSWORD);
        var responseData = response.as(GetFieldsByResponse.class);
        String id = responseData.getId();
        new CourierApiMethod().deleteCourier(id);
    }
}
