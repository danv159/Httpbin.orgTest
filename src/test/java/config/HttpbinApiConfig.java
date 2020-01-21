package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;

import static org.hamcrest.Matchers.lessThan;

/***
 * Config class to set base URI, header, expected status code and response time
 * to be called before the test class is run.
 *
 * @author
 *      <a href="mailto:ali.pala@ymail.com">Ali Pala</a>
 */
public class HttpbinApiConfig {
    public static RequestSpecification requestSpecification;
    public static ResponseSpecification responseSpecification;

    @BeforeClass
    public static void setup() {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://httpbin.org/")
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();

        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectResponseTime(lessThan(3000L))
                .build();

        RestAssured.requestSpecification = requestSpecification;
        RestAssured.responseSpecification = responseSpecification;

    }
}
