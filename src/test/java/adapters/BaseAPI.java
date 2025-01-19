package adapters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import utils.PropertyReader;

import static io.restassured.RestAssured.given;

public class BaseAPI {

    public static String email = System.getProperty("email");
    public static String api_key = System.getProperty("api_key");
    public static String base_url_api = PropertyReader.getProperty("base_url_api");

    public static Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    public static RequestSpecification spec =
            given()
                    .log().all()
                    .auth()
                    .preemptive().basic(email, api_key)
                    .contentType(ContentType.JSON);

    public static String testpost(String body, String url) {
        return spec
                .body(body)
                .when()
                .post(url)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .asString();
    }
}
