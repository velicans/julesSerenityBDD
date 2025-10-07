package com.velicans.steps.serenity;

import com.velicans.models.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import java.util.List;

public class UserSteps extends ScenarioSteps {

    static {
        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "/v2";
    }

    @Step
    public void createUser(User user) {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(user)
        .when()
                .post("/user")
        .then()
                .statusCode(200);
    }

    @Step
    public User getUserByName(String username) {
        return RestAssured.given()
        .when()
                .get("/user/{username}", username)
        .then()
                .statusCode(200)
                .extract().as(User.class);
    }

    @Step
    public void getUserWithInvalidUsername() {
        RestAssured.given()
        .when()
                .get("/user/{username}", "")
        .then()
                .statusCode(400);
    }

    @Step
    public void getUserWithNotFoundUsername(String username) {
        RestAssured.given()
        .when()
                .get("/user/{username}", username)
        .then()
                .statusCode(404);
    }

    @Step
    public void updateUser(String username, User user) {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(user)
        .when()
                .put("/user/{username}", username)
        .then()
                .statusCode(200);
    }

    @Step
    public void updateUserWithInvalidUser(String username, User user) {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(user)
        .when()
                .put("/user/{username}", username)
        .then()
                .statusCode(400);
    }

    @Step
    public void updateUserNotFound(String username, User user) {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(user)
        .when()
                .put("/user/{username}", username)
        .then()
                .statusCode(404);
    }

    @Step
    public void deleteUser(String username) {
        RestAssured.given()
        .when()
                .delete("/user/{username}", username)
        .then()
                .statusCode(200);
    }

    @Step
    public void deleteUserNotFound(String username) {
        RestAssured.given()
        .when()
                .delete("/user/{username}", username)
        .then()
                .statusCode(404);
    }

    @Step
    public void createUsersWithList(List<User> users) {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(users)
        .when()
                .post("/user/createWithList")
        .then()
                .statusCode(200);
    }

    @Step
    public void createUsersWithArray(List<User> users) {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(users)
        .when()
                .post("/user/createWithArray")
        .then()
                .statusCode(200);
    }

    @Step
    public void loginUser(String username, String password) {
        RestAssured.given()
                .queryParam("username", username)
                .queryParam("password", password)
        .when()
                .get("/user/login")
        .then()
                .statusCode(200);
    }

    @Step
    public void loginUserInvalidCredentials(String username, String password) {
        RestAssured.given()
                .queryParam("username", username)
                .queryParam("password", password)
        .when()
                .get("/user/login")
        .then()
                .statusCode(400);
    }

    @Step
    public void logoutUser() {
        RestAssured.given()
        .when()
                .get("/user/logout")
        .then()
                .statusCode(200);
    }
}
