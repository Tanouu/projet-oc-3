package com.tanou.projet.oc.backend.projet2.dto;

public class LoginDto {

        private String login;
        private String password;

        public LoginDto() {
        }

        public LoginDto(String login, String password) {
            this.login = login;
            this.password = password;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

    @Override
    public String toString() {
        return "LoginDto{" +
                "username='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}