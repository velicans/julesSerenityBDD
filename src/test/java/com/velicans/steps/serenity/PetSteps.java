package com.velicans.steps.serenity;

import com.velicans.models.ApiResponse;
import com.velicans.models.Pet;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.hamcrest.Matchers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.MatcherAssert.assertThat;

public class PetSteps extends ScenarioSteps {

    static {
        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "/v2";
    }

    @Step
    public void addPet(Pet pet) {
        RestAssured.given()
                .header("api_key", "special-key")
                .contentType(ContentType.JSON)
                .body(pet)
        .when()
                .post("/pet")
        .then()
                .statusCode(200);
    }

    @Step
    public void updatePet(Pet pet) {
        RestAssured.given()
                .header("api_key", "special-key")
                .contentType(ContentType.JSON)
                .body(pet)
        .when()
                .put("/pet")
        .then()
                .statusCode(200);
    }

    @Step
    public Pet getPetById(Long petId) {
        return RestAssured.given()
                .header("api_key", "special-key")
        .when()
                .get("/pet/{petId}", petId)
        .then()
                .statusCode(200)
                .extract().as(Pet.class);
    }

    @Step
    public void getPetWithInvalidId(Object invalidId) {
        RestAssured.given()
                .header("api_key", "special-key")
        .when()
                .get("/pet/{petId}", invalidId)
        .then()
                .statusCode(400);
    }

    @Step
    public void getPetWithNotFoundId(Long petId) {
        RestAssured.given()
                .header("api_key", "special-key")
        .when()
                .get("/pet/{petId}", petId)
        .then()
                .statusCode(404);
    }

    @Step
    public Pet[] findPetsByStatus(String status) {
        return RestAssured.given()
                .header("api_key", "special-key")
                .queryParam("status", status)
        .when()
                .get("/pet/findByStatus")
        .then()
                .statusCode(200)
                .extract().as(Pet[].class);
    }

    @Step
    public void findPetsByInvalidStatus(String invalidStatus) {
        RestAssured.given()
                .header("api_key", "special-key")
                .queryParam("status", invalidStatus)
        .when()
                .get("/pet/findByStatus")
        .then()
                .statusCode(400);
    }

    @Step
    public void updatePetWithForm(Long petId, String name, String status) {
        RestAssured.given()
                .header("api_key", "special-key")
                .contentType(ContentType.URLENC)
                .formParam("name", name)
                .formParam("status", status)
        .when()
                .post("/pet/{petId}", petId)
        .then()
                .statusCode(200);
    }

    @Step
    public void updatePetWithFormInvalidId(Object invalidId) {
        RestAssured.given()
                .header("api_key", "special-key")
                .contentType(ContentType.URLENC)
                .formParam("name", "test")
        .when()
                .post("/pet/{petId}", invalidId)
        .then()
                .statusCode(400);
    }

    @Step
    public void deletePet(Long petId) {
        RestAssured.given()
                .header("api_key", "special-key")
        .when()
                .delete("/pet/{petId}", petId)
        .then()
                .statusCode(200);
    }

    @Step
    public void deletePetInvalidId(Object invalidId) {
        RestAssured.given()
                .header("api_key", "special-key")
        .when()
                .delete("/pet/{petId}", invalidId)
        .then()
                .statusCode(400);
    }

    @Step
    public void deletePetNotFound(Long petId) {
        RestAssured.given()
                .header("api_key", "special-key")
        .when()
                .delete("/pet/{petId}", petId)
        .then()
                .statusCode(404);
    }

    @Step
    public void uploadPetImage(Long petId, String filePath) throws IOException {
        Path path = Paths.get(filePath);
        byte[] fileContent = Files.readAllBytes(path);

        RestAssured.given()
                .header("api_key", "special-key")
                .multiPart("file", "image.png", fileContent, "image/png")
        .when()
                .post("/pet/{petId}/uploadImage", petId)
        .then()
                .statusCode(200);
    }

    @Step
    public void addPetInvalidInput(Pet pet) {
        RestAssured.given()
                .header("api_key", "special-key")
                .contentType(ContentType.JSON)
                .body(pet)
        .when()
                .post("/pet")
        .then()
                .statusCode(405);
    }
}
