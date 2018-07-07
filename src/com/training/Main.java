package com.training;


import com.sun.jdi.PathSearchingVirtualMachine;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

public class Main {

    //TODO: Add in your key using the Google API Console
    String key = "";

        @Test
        public void getGoogleRestaurantsTest()
        {
            RestAssured.baseURI = "https://maps.googleapis.com";

            given()
                    .param("query", "restaurants in Cibolo")
                    .param("radius", "10")
                    .param("key", key)
                    .when()
                        .get("/maps/api/place/textsearch/json")
                    .then()
                        .assertThat()
                            .statusCode(200).and()
                            .contentType(ContentType.JSON).and()
                            .body("results[0].name", equalTo("Catalano's Pizzeria")).and()
                            .body("results[1].formatted_address", equalTo("170 Buffalo Pl, Cibolo, TX 78108, USA"));

        }

        @Test
    public void postGoogleTest()
        {
            RestAssured.baseURI = "https://maps.googleapis.com";

            given()
                    .body("")
                    .when()
                        .post("/maps/api/place/textsearch/json")
                    .then()
                        .assertThat()
                            .statusCode(200);

        }
}
