package com.velicans.steps.serenity;

import com.velicans.models.Order;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import java.util.Map;

public class StoreSteps extends ScenarioSteps {

    static {
        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "/v2";
    }

    @Step
    public void placeOrder(Order order) {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(order)
        .when()
                .post("/store/order")
        .then()
                .statusCode(200);
    }

    @Step
    public void placeOrderInvalidInput(Order order) {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(order)
        .when()
                .post("/store/order")
        .then()
                .statusCode(400);
    }

    @Step
    public Order getOrderById(Long orderId) {
        return RestAssured.given()
        .when()
                .get("/store/order/{orderId}", orderId)
        .then()
                .statusCode(200)
                .extract().as(Order.class);
    }

    @Step
    public void getOrderWithInvalidId(Object invalidId) {
        RestAssured.given()
        .when()
                .get("/store/order/{orderId}", invalidId)
        .then()
                .statusCode(400);
    }

    @Step
    public void getOrderWithNotFoundId(Long orderId) {
        RestAssured.given()
        .when()
                .get("/store/order/{orderId}", orderId)
        .then()
                .statusCode(404);
    }

    @Step
    public void deleteOrder(Long orderId) {
        RestAssured.given()
        .when()
                .delete("/store/order/{orderId}", orderId)
        .then()
                .statusCode(200);
    }

    @Step
    public void deleteOrderWithInvalidId(Object invalidId) {
        RestAssured.given()
        .when()
                .delete("/store/order/{orderId}", invalidId)
        .then()
                .statusCode(400);
    }

    @Step
    public void deleteOrderNotFound(Long orderId) {
        RestAssured.given()
        .when()
                .delete("/store/order/{orderId}", orderId)
        .then()
                .statusCode(404);
    }

    @SuppressWarnings("unchecked")
    @Step
    public Map<String, Integer> getInventory() {
        return RestAssured.given()
                .header("api_key", "special-key")
        .when()
                .get("/store/inventory")
        .then()
                .statusCode(200)
                .extract().as(Map.class);
    }
}
