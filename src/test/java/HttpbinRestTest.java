import config.Endpoints;
import config.HttpbinApiConfig;
import io.qameta.allure.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utility.LogUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.lessThan;

/***
 * Httpbin.org API Test
 * <ol>
 * Test cases:
 * <li>POST with body Test</li>
 * <li>Simple GET Test</li>
 * <li>PUT with body Test</li>
 * <li>PATCH Test</li>
 * <li>DELETE Test</li>
 * <li>GET Content Type Test</li>
 * <li>GET Response Time Test</li>
 * <li>POST with Serialization by JSON Test</li>
 * <li>GET IP Test</li>
 * </ol>
 *
 * @author
 *      <a href="mailto:ali.pala@ymail.com">Ali Pala</a>
 */

public class HttpbinRestTest extends HttpbinApiConfig {

    // Using this can get current Method
    @BeforeMethod(description = "Get and print method name")
    public void getCurrentMethodName(ITestResult result) {
        LogUtil.info(result.getMethod().getMethodName() + " is started");
    }

    @Test(priority = 0, description = "POST with body Test")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test Description: POST with body")
    @Story("POST Test with body")
    public void PostWithBodyTest() {

        String channelBodyJson = "{\n" +
                "  \"id\": 11,\n" +
                "  \"ChannelName\": \"Eurosport\",\n" +
                "  \"ChannelRating\": \"80\",\n" +
                "  \"Category\": Sport,\n" +
                "  \"Country\": \"France\",\n" +
                "}";

        Response response = given()
                .body(channelBodyJson).
                when()
                .post(Endpoints.CREATE).
                then().extract().response();


        ResponseBody responseBody = response.getBody();
        String bodyAsString = responseBody.asString();
        JsonPath jsonPath = new JsonPath(bodyAsString);
        Assert.assertEquals(jsonPath.getString("data").contains("Eurosport"), true, "POST with body Test Failed");

        LogUtil.info("POST with body Test finished: Passed");
    }

    @Test(priority = 1, description = "Simple GET Test")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test Description: Simple GET")
    @Story("Simple GET Test")
    public void SimpleGETTest() {

        Response response = given().
                when()
                .get(Endpoints.GET).
                then().extract().response();

        Assert.assertEquals(response.getStatusCode(), 200, "Simple GET Test Failed");

        LogUtil.info("Simple GET Test finished: Passed");
    }

    @Test(priority = 2, description = "PUT with body Test")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description: PUT with body")
    @Story("PUT Test with body")
    public void PUTWithBodyTest() {

        String channelBodyJson = "{\n" +
                "  \"id\": 500,\n" +
                "  \"ChannelName\": \"Fox TV\",\n" +
                "  \"ChannelRating\": \"90\",\n" +
                "  \"Category\": News,\n" +
                "  \"Country\": \"USA\",\n" +
                "}";

        Response response = given().
                body(channelBodyJson).
                when().
                put(Endpoints.UPDATE).
                then().extract().response();


        ResponseBody responseBody = response.getBody();
        String bodyAsString = responseBody.asString();
        JsonPath jsonPath = new JsonPath(bodyAsString);
        Assert.assertEquals(jsonPath.getString("data").contains("Fox TV"), true, "PUT with body Test Failed");

        LogUtil.info("PUT with body Test finished: Passed");
    }

    @Test(priority = 3, description = "PATCH Test")
    @Severity(SeverityLevel.MINOR)
    @Description("Test Description: Update the only id")
    @Story("Update the only id")
    public void PATCHTest() {

        String gameBodyJson = "{\n" +
                "  \"id\": 1,\n" +
                "}";

        Response response = given().
                body(gameBodyJson).
                when().
                patch(Endpoints.PATCH).
                then().extract().response();

        ResponseBody responseBody = response.getBody();
        String bodyAsString = responseBody.asString();
        JsonPath jsonPath = new JsonPath(bodyAsString);
        Assert.assertEquals(jsonPath.getString("data").contains("1"), true, "PATCH Test Failed");

        LogUtil.info("PATCH Test finished: Passed");

    }

    @Test(priority = 4, description = "DELETE Test")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test Description: DELETE Test")
    @Story("DELETE Test")
    public void DELETETest() {

        Response response = given().
                when()
                .delete(Endpoints.DELETE).
                then().extract().response();

        Assert.assertEquals(response.getStatusCode(), 200, "DELETE Test Failed");

        LogUtil.info("DELETE Test finished: Passed");
    }

    @Test(priority = 5, description = "GET Content Type Test")
    @Severity(SeverityLevel.MINOR)
    @Description("Test Description: GET Content Type")
    @Story("GET Content Type Test")
    public void GETContentTypeTest() {

        Response response = given().
        when()
                .get(Endpoints.GET);

        Assert.assertEquals(response.getContentType(), ContentType.JSON, "GET Content Type Test Failed");

        LogUtil.info("GET Content Type Test: Passed");
    }

    @Test(priority = 6, description = "GET Response Time Test")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description: GET Response Time")
    @Story("GET Response Time Test")
    public void GETResponseTimeTest() {

            when().
                get(Endpoints.GET).
            then().
                time(lessThan(2000L));

        LogUtil.info("GET Response Time Test: Passed");
    }

    @Test(priority = 7, description = "POST with Serialization by JSON Test")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description: POST with Serialization by JSON")
    @Story("POST with Serialization by JSON Test")
    public void POSTWithSerializationByJSONTest() {

        Channel channel = new Channel("BBC", "Sir David Clementi", "90", 120, "News");
        Response response = given().
                body(channel).
        when().
                post(Endpoints.CREATE);

        Assert.assertEquals(response.statusCode(), 200, "POST with Serialization by JSON Test Failed");

        LogUtil.info("POST with Serialization by JSON Test: Passed");
    }

    @Test(priority = 8, description = "GET IP Test")
    @Severity(SeverityLevel.MINOR)
    @Description("Test Description: GET IP")
    @Story("GET IP Test")
    public void GETIPTest() {

        Response response =
                given().
                        when().get(Endpoints.IP).
                        then().extract().response();

        ResponseBody body = response.getBody();
        String bodyAsString = body.asString();
        Assert.assertEquals(bodyAsString.contains(findMyIPAddress()), true, "GET IP Test: Failed");

        LogUtil.info("GET IP Test: Passed");
    }


    /***
     * Helper method to find the public ip
     * @return
     */
    private String findMyIPAddress() {
        // Find public IP address
        String systemipaddress = "";
        try
        {
            URL url_name = new URL("http://bot.whatismyipaddress.com");
            BufferedReader sc =
                    new BufferedReader(new InputStreamReader(url_name.openStream()));

            // reads system IPAddress
            systemipaddress = sc.readLine().trim();
            LogUtil.info("Public IP address provided");
        }
        catch (Exception e)
        {
            LogUtil.error("Cannot executed properly");
            e.printStackTrace();
        }

        return systemipaddress;
    }

}
