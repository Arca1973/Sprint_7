package org.example.models;

public class CourierModel {

    // Класс для хранения данных курьера
    public static class CourierData {
        private String login;
        private String password;
        private String firstName;

        public CourierData(String login, String password, String firstName) {
            this.login = login;
            this.password = password;
            this.firstName = firstName;
        }
    }

    // Класс для хранения данных авторизации
    public static class LoginData {
        private String login;
        private String password;

        public LoginData(String login, String password) {
            this.login = login;
            this.password = password;
        }

    }
}