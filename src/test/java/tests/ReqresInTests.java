package tests;

import models.*;
import models.GetSuccessfulSingleUserResponseLombokModel;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static specs.RequestResponseSpecs.*;
import static io.qameta.allure.Allure.step;


public class ReqresInTests {
    @Test
    void successfulGetSingleUserTest() {
        GetSuccessfulSingleUserResponseLombokModel response = step("Make request", () ->
                given(requestSpec)
                .when()
                .get("users/2")
                .then()
                .spec(responseSpecStatusCodeIs200)
                .extract().as(GetSuccessfulSingleUserResponseLombokModel.class));

        step("Verify response", () -> {
                assertThat(response.getData().getId()).isEqualTo(2);
                assertThat(response.getData().getFirstName()).isEqualTo("Janet");});
    }

    @Test
    void unSuccessfulGetSingleUserTest() {
        step("Make request", () ->
                given(requestSpec)
                .when()
                .get("/users/абв")
                .then()
                .spec(responseSpecStatusCodeIs404));
    }

    @Test
    void successfulUpdateUserTest() {
        UpdateUserLombokModel data = new UpdateUserLombokModel();
        data.setName("morpheus");
        data.setJob("zion resident");

        UpdateUserLombokModel response = step("Make request", () ->
                given(requestSpec)
                .body(data)
                .when()
                .put("/users/2")
                .then()
                .spec(responseSpecStatusCodeIs200)
                .extract().as(UpdateUserLombokModel.class));

        step("Verify response", () -> {
            assertThat(response.getName()).isEqualTo("morpheus");
            assertThat(response.getJob()).isEqualTo("zion resident");});
    }

    @Test
    void successfulLoginTest() {
        LoginBobyLombokModel data = new LoginBobyLombokModel();
        data.setEmail("eve.holt@reqres.in");
        data.setPassword("cityslicka");

        LoginSuccessfulResponseLombokModel response = step("Make request", () ->
                given(requestSpec)
                .body(data)
                .when()
                .post("/login")
                .then()
                .spec(responseSpecStatusCodeIs200)
                .extract().as(LoginSuccessfulResponseLombokModel.class));

        step("Verify response", () ->
                assertThat(response.getToken()).isNotEmpty());
    }

    @Test
    void unSuccessfulLoginTest() {
        LoginBobyLombokModel data = new LoginBobyLombokModel();
        data.setEmail("eve.holt@reqres.in");
        data.setPassword("");

        LoginUnSuccessfulResponseLombokModel response = step("Make request", () ->
                given(requestSpec)
                .body(data)
                .when()
                .post("/login")
                .then()
                .spec(responseSpecStatusCodeIs400)
                .extract().as(LoginUnSuccessfulResponseLombokModel.class));

        step("Verify response", () ->
                assertThat(response.getError()).isEqualTo("Missing password"));
    }


}
