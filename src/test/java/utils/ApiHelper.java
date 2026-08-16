package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class ApiHelper {
    public static void validateEmployee(String empId) {
        RestAssured.baseURI = "https://reqres.in/api"; // sample API
        Response res = given().get("/users/" + empId);
        System.out.println("API Response: " + res.asString());
        // Add assertions comparing UI data vs API data
    }
}
