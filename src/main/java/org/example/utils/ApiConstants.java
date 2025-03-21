package org.example.utils;


public class ApiConstants {
    public static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";
    public static final String CREATE_COURIER_ENDPOINT = "/api/v1/courier";
    public static final String LOGIN_COURIER_ENDPOINT = "/api/v1/courier/login";
    public static final String DELETE_COURIER_ENDPOINT = "/api/v1/courier/%s";
    public static final String ORDER_ENDPOINT = "/api/v1/orders";

    // Данные курьера
    public static final String LOGIN = "ninja" + (int) (Math.random() * 1000000);
    public static final String PASSWORD = "1234" + (int) (Math.random() * 1000000);
    public static final String FIRST_NAME = "saske" + (int) (Math.random() * 1000000);

}
