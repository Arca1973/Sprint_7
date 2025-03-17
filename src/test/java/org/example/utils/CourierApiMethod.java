package org.example.utils;

import io.restassured.response.Response;
import org.example.models.GetFieldsByResponse;

import static org.example.utils.ApiConstants.*;

public class CourierApiMethod extends BaseApiMethod {

    public Response createCourier(String login, String password, String firstName) {
            String body = "{\"login\": \"" + login+ "\", \"password\": \"" + password+ "\", \"firstName\": \"" + firstName  + "\"}";
        return sendPostRequest(CREATE_COURIER_ENDPOINT,body);
    }
    public Response loginCourier(String login, String password)  {
        String body = "{\"login\": \"" + login + "\", \"password\": \"" + password + "\"}";
       return  sendPostRequest(LOGIN_COURIER_ENDPOINT, body);
    }

    public  Response deleteCourier(String id) {

        return sendDeleteRequest(id);
    }
    public int killCourier(int statusCode) {
        if(statusCode == 201){
        Response response = new CourierApiMethod().loginCourier(LOGIN, PASSWORD);
        var responseData = response.as(GetFieldsByResponse.class);
        String id = responseData.getId();
        new CourierApiMethod().deleteCourier(id);
        return 0;
        }

        return statusCode;
    }

    public  Response getOrderList() {

        return sendGetRequest(ORDER_ENDPOINT,"");
    }

}
