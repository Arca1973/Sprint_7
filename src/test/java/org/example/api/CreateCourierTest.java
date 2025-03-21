package org.example.api;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
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
    @DisplayName("Успешное создание курьера при вводе валидных данных")
    @Step("Успешное создание курьера при вводе валидных данных")
    public void validDataCourierCreationTest() {
        Response response=  new CourierApiMethod().createCourier(LOGIN, PASSWORD, FIRST_NAME);
        var responseData = response.as(GetFieldsByResponse.class);
        statusCode = response.getStatusCode();
        Assert.assertEquals(HttpStatus.SC_CREATED, statusCode);
        Assert.assertTrue((boolean) responseData.getOk());

    }

    @Test
    @DisplayName("Безуспешное создание курьера с повторяющимся логином")
    public void duplicateLoginCreationTest() {
        Response response1=  new CourierApiMethod().createCourier(LOGIN, PASSWORD, FIRST_NAME);
        Response response=  new CourierApiMethod().createCourier(LOGIN, PASSWORD, FIRST_NAME);
        var responseData = response.as(GetFieldsByResponse.class);
        statusCode = HttpStatus.SC_CREATED;
        Assert.assertEquals(HttpStatus.SC_CONFLICT, response.getStatusCode());
        Assert.assertEquals("Этот логин уже используется", responseData.getMessage());

    }

    @Test
    @DisplayName("Безуспешное создание курьера без логина")
    public void withoutLoginCreationTest() {
        Response response=  new CourierApiMethod().createCourier("", PASSWORD, FIRST_NAME);
        var responseData = response.as(GetFieldsByResponse.class);
        statusCode = response.getStatusCode();
        Assert.assertEquals(HttpStatus.SC_BAD_REQUEST, statusCode);
        Assert.assertEquals("Недостаточно данных для создания учетной записи", responseData.getMessage());

    }

    @Test
    @DisplayName("Безуспешное создание курьера без пароля")
    public void withoutPasswordCreationTest() {
        Response response=  new CourierApiMethod().createCourier(LOGIN, "", FIRST_NAME);
        var responseData = response.as(GetFieldsByResponse.class);
        statusCode = response.getStatusCode();
        Assert.assertEquals(HttpStatus.SC_BAD_REQUEST, statusCode);
        Assert.assertEquals("Недостаточно данных для создания учетной записи", responseData.getMessage());

    }

    @After public void tearDown() {
        statusCode = new CourierApiMethod().killCourier(statusCode);
    }

}





