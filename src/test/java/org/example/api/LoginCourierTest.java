package org.example.api;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.example.models.GetFieldsByResponse;
import org.example.utils.CourierApiMethod;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.example.utils.ApiConstants.*;


public class LoginCourierTest {

    @Before
    @Step("Отправляем запрос на  создание курьера")
        public void setUp() {
            new CourierApiMethod().createCourier(LOGIN, PASSWORD, FIRST_NAME);
        };

    @Test
    @DisplayName("Успешная авторизация курьера при вводе валидных данных")
    public void validDataCourierLoginTest() {
        Response response=  new CourierApiMethod().loginCourier(LOGIN, PASSWORD);
        var responseData = response.as(GetFieldsByResponse.class);
        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
        Assert.assertNotEquals("", responseData.getId());
    }

    @Test
    @DisplayName("Без успешная авторизация  курьера без пароля")
    public void unsuccessfulCourierAuthenticationWithoutPasswordTest() {
        Response response=  new CourierApiMethod().loginCourier(LOGIN, "");
        var responseData = response.as(GetFieldsByResponse.class);
        Assert.assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatusCode());
        Assert.assertEquals("Недостаточно данных для входа", responseData.getMessage());
    }
    @Test
    @DisplayName("Без успешная авторизация  курьера без логина")
    public void unsuccessfulCourierAuthenticationWithoutLoginTest() {
        Response response=  new CourierApiMethod().loginCourier("", PASSWORD);
        var responseData = response.as(GetFieldsByResponse.class);
        Assert.assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatusCode());
        Assert.assertEquals("Недостаточно данных для входа", responseData.getMessage());
    }
    @Test
    @DisplayName("Без успешная авторизация  курьера без логина и без пароля")
    public void unsuccessfulCourierAuthenticationWithoutLoginWithoutPasswordTest() {
        Response response=  new CourierApiMethod().loginCourier("", "");
        var responseData = response.as(GetFieldsByResponse.class);
        Assert.assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatusCode());
        Assert.assertEquals("Недостаточно данных для входа", responseData.getMessage());
    }

    @Test
    @DisplayName("Без успешная авторизация  курьера с несуществующим паролем")
    public void unsuccessfulCourierAuthenticationWithNonexistentPasswordTest() {
        Response response=  new CourierApiMethod().loginCourier(LOGIN, "NonexistentPassword");
        var responseData = response.as(GetFieldsByResponse.class);
        Assert.assertEquals(HttpStatus.SC_NOT_FOUND, response.getStatusCode());
        Assert.assertEquals("Учетная запись не найдена", responseData.getMessage());
    }
    @Test
    @DisplayName("Без успешная авторизация  курьера с несуществующим логином")
    public void unsuccessfulCourierAuthenticationWithNonexistentLoginTest() {
        Response response=  new CourierApiMethod().loginCourier("NonexistentLogin", PASSWORD);
        var responseData = response.as(GetFieldsByResponse.class);
        Assert.assertEquals(HttpStatus.SC_NOT_FOUND, response.getStatusCode());
        Assert.assertEquals("Учетная запись не найдена", responseData.getMessage());
    }



    @After public void tearDown() {
        Response response = new CourierApiMethod().loginCourier(LOGIN, PASSWORD);
        var responseData = response.as(GetFieldsByResponse.class);
        String id = responseData.getId();
        new CourierApiMethod().deleteCourier(id);
    }
}
