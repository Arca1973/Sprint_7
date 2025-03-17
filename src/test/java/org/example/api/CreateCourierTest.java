package org.example.api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.example.models.GetFieldsByResponse;
import org.example.utils.CourierApiMethod;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.example.utils.ApiConstants.*;

@RunWith(JUnit4.class)
public class CreateCourierTest{

    int statusCode = 0;

    @Test
    @Step("Успешное создание курьера при вводе валидных данных")
    public void validDataCourierCreationTest() {
        Response response=  new CourierApiMethod().createCourier(LOGIN, PASSWORD, FIRST_NAME);
        var responseData = response.as(GetFieldsByResponse.class);
        statusCode = response.getStatusCode();
        Assert.assertEquals(201, statusCode);
        Assert.assertTrue((boolean) responseData.getOk());

    }

    @Test
    @Step("Безуспешное создание курьера с повторяющимся логином")
    public void duplicateLoginCreationTest() {
        Response response1=  new CourierApiMethod().createCourier(LOGIN, PASSWORD, FIRST_NAME);
        Response response=  new CourierApiMethod().createCourier(LOGIN, PASSWORD, FIRST_NAME);
        var responseData = response.as(GetFieldsByResponse.class);
        statusCode = 201;
        Assert.assertEquals(409, response.getStatusCode());
        Assert.assertEquals("Этот логин уже используется", responseData.getMessage());

    }

    @Test
    @Step("Безуспешное создание курьера без логина")
    public void withoutLoginCreationTest() {
        Response response=  new CourierApiMethod().createCourier("", PASSWORD, FIRST_NAME);
        var responseData = response.as(GetFieldsByResponse.class);
        statusCode = response.getStatusCode();
        Assert.assertEquals(400, statusCode);
        Assert.assertEquals("Недостаточно данных для создания учетной записи", responseData.getMessage());

    }

    @Test
    @Step("Безуспешное создание курьера без пароля")
    public void withoutPasswordCreationTest() {
        Response response=  new CourierApiMethod().createCourier(LOGIN, "", FIRST_NAME);
        var responseData = response.as(GetFieldsByResponse.class);
        statusCode = response.getStatusCode();
        Assert.assertEquals(400, statusCode);
        Assert.assertEquals("Недостаточно данных для создания учетной записи", responseData.getMessage());

    }

    @After public void tearDown() {
        // Удаляем созданного курьера
        statusCode = new CourierApiMethod().killCourier(statusCode);
    }

}





