package org.example.utils;

import com.google.gson.Gson;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.example.models.CourierModel;

import org.example.models.GetFieldsByResponse;

import static org.example.utils.ApiConstants.*;

public class CourierApiMethod extends BaseApiMethod {

    private static final Gson gson = new Gson(); // Используем Gson для сериализации

    @Step("Запрос на создание курьера")
    public Response createCourier(String login, String password, String firstName) {

        CourierModel.CourierData courierData = new CourierModel.CourierData(login, password, firstName);
        String body = gson.toJson(courierData);
        return sendPostRequest(CREATE_COURIER_ENDPOINT, body);
    }

    @Step("Запрос на логин курьера")
    public Response loginCourier(String login, String password) {

        CourierModel.LoginData loginData = new CourierModel.LoginData(login, password);
        String body = gson.toJson(loginData);
        return sendPostRequest(LOGIN_COURIER_ENDPOINT, body);
    }
    @Step("Запрос на удаление курьера")
    public Response deleteCourier(String id) {

        return sendDeleteRequest(id);
    }
    @Step("Запрос на логин и удаление курьера")
    public int killCourier(int statusCode) {
        if (statusCode == HttpStatus.SC_CREATED) {
            Response response = new CourierApiMethod().loginCourier(LOGIN, PASSWORD);
            var responseData = response.as(GetFieldsByResponse.class);
            String id = responseData.getId();
            new CourierApiMethod().deleteCourier(id);
            return 0;
        }

        return statusCode;
    }
}
