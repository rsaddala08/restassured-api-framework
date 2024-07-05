package com.api.lib;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Authentications {
    //Basic
    //Digest
    //Preemptive

    //Bearer
    //Oauth1
    //Oauth2
    //AWT
    @Test(priority = 1)
    void basicAuthentications(){
        given().auth().basic("postman","password")
                .when().get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated",equalTo(true))
                .log().all();
    }

    @Test(priority = 2)
    void digestAuthentications(){
        given().auth().digest("postman","password")
                .when().get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated",equalTo(true))
                .log().all();
    }

    @Test(priority = 3)
    void preemptiveAuthentications(){
        given().auth().preemptive().basic("postman","password")
                .when().get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated",equalTo(true))
                .log().all();
    }

    @Test(priority = 4)
    void bearerAuthentications(){
        String token="ajsdafkakagkd";
        given().header("Authorization","Bearer "+token)
                .when().get("https://api.github.com/user/repos")
                .then()
                .statusCode(200)
                .log().all();
    }
    //https://api.github.com/user/repos
    @Test(priority = 5)
    void ouath1Authentications(){
        given().auth().oauth("consumerKey","consumerSecrate","accessToken","accessSecrate")
                .when().get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated",equalTo(true))
                .log().all();
    }
    @Test(priority = 6)
    void ouath2Authentications(){
        given().auth().oauth2("accessToken")
                .when().get("https://api.github.com/user/repos")
                .then()
                .statusCode(200)
                .log().all();
    }

    //Generate Authentication Token Value
    public static Response apigeeTokenGenerate(String endpoint) {
        try {
            RequestSpecification request = RestAssured.given();
            String Username ="0oa1fju2iijDXQusZ0h8";
            String Password ="y67o0P5QUMO3RTZY2rPL_s0ZshiUo2ANIE86_WuR";
            Response response = request.
                    auth().preemptive().basic(Username,Password)
                    .header("scope", "api_scope ")
                    .header("grant_type","client_credentials")
                    .get(endpoint);
            return response;
        } catch (Throwable t) {
           t.printStackTrace();
            return null;
        }
    }
}
