package org.example.api;


import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.example.models.GetFieldsByResponse;
import org.example.utils.CourierApiMethod;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.example.utils.ApiConstants.*;

@RunWith(JUnit4.class)
public class DeleteCourierTest {

    @Test
    @DisplayName("Успешное удаления курьера существующий ID")
    public void successfulCourierDeletionExistingIDTest() {
        Response response = new CourierApiMethod().createCourier(LOGIN, PASSWORD, FIRST_NAME);
        Assert.assertEquals(HttpStatus.SC_CREATED, response.getStatusCode());
        Response response1 = new CourierApiMethod().loginCourier(LOGIN, PASSWORD);
        Assert.assertEquals(HttpStatus.SC_OK, response1.getStatusCode());
        var responseData = response1.as(GetFieldsByResponse.class);
        String ExistingID = responseData.getId();
        Response response2 = new CourierApiMethod().deleteCourier(ExistingID);
        Assert.assertEquals(HttpStatus.SC_OK, response2.getStatusCode());
        var responseData2 = response2.as(GetFieldsByResponse.class);
        Assert.assertTrue((boolean) responseData2.getOk());
    }
    @Test
    @DisplayName("Безуспешное удаления курьера несуществующий ID")
    public void unsuccessfulCourierDeletionNonExistingIDTest() {
        String NonExistingID = "2147483647";
        Response response = new CourierApiMethod().deleteCourier(NonExistingID);
        var responseData = response.as(GetFieldsByResponse.class);
        Assert.assertEquals(HttpStatus.SC_NOT_FOUND, response.getStatusCode());
        Assert.assertEquals("Курьера с таким id нет", responseData.getMessage());
    }
    @Test
    @DisplayName("Безуспешное удаления курьера не указан ID")


    public void unsuccessfulCourierDeletionNoIDTest() {
        String NoID = "";
        Response response = new CourierApiMethod().deleteCourier(NoID);
        var responseData = response.as(GetFieldsByResponse.class);
        Assert.assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatusCode());
        Assert.assertEquals("Недостаточно данных для удаления курьера", responseData.getMessage());
    }
}
