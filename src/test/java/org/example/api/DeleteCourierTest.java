package org.example.api;


import io.qameta.allure.Step;
import io.restassured.response.Response;
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
    @Step("Успешное удаления курьера существующий ID")
    public void successfulCourierDeletionExistingIDTest() {

        Response response = new CourierApiMethod().createCourier(LOGIN, PASSWORD, FIRST_NAME);
        Assert.assertEquals(201, response.getStatusCode());

        Response response1 = new CourierApiMethod().loginCourier(LOGIN, PASSWORD);
        Assert.assertEquals(200, response1.getStatusCode());
        var responseData = response1.as(GetFieldsByResponse.class);
        String ExistingID = responseData.getId();

        Response response2 = new CourierApiMethod().deleteCourier(ExistingID);
        Assert.assertEquals(200, response2.getStatusCode());
        var responseData2 = response2.as(GetFieldsByResponse.class);
        Assert.assertTrue((boolean) responseData2.getOk());
    }
    @Test
    @Step("Безуспешное удаления курьера несуществующий ID")
    public void unsuccessfulCourierDeletionNonExistingIDTest() {
        String NonExistingID = "2147483647";
        Response response = new CourierApiMethod().deleteCourier(NonExistingID);
        var responseData = response.as(GetFieldsByResponse.class);
        Assert.assertEquals(404, response.getStatusCode());
        Assert.assertEquals("Курьера с таким id нет", responseData.getMessage());
    }
    @Test
    @Step("Безуспешное удаления курьера не указан ID")
    public void UnsuccessfulCourierDeletionNoIDTest() {
        String NoID = "";
        Response response = new CourierApiMethod().deleteCourier(NoID);
        var responseData = response.as(GetFieldsByResponse.class);
        Assert.assertEquals(400, response.getStatusCode());
        Assert.assertEquals("Недостаточно данных для удаления курьера", responseData.getMessage());
    }
}
