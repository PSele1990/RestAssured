package stepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import utils.Readresponsefile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class WeatherAPITest {

    RequestSpecification request;
    Response response;
    Properties prop;
    private ArrayList<String> cityName;
    private ArrayList<String> countryCode;
    private ArrayList<String> weatherDescriptions;
    private ArrayList<String> weatherCode;
    private ArrayList<String> weatherIcons;
    String responseStatusCode;
    String writeResponse;
    String responsefile;
@Before
public void setup() throws IOException {
    prop = new Properties();
    FileInputStream file = new FileInputStream(System.getProperty("user.dir")
                        +"/src/test/resources/config/config.properties");
    prop.load(file);
}
    @Given("User makes a Get call API request url")
    public void user_makes_a_Get_call_API_request()  {

        RestAssured.baseURI =  prop.getProperty("baseURL");
    }

    @When("I use the parameter lat and lon values as \\({double},{double}),\\({double},{double}),\\({double},{double})")
    public void i_use_the_parameter_lat_and_lon_values_as(Double lat1, Double lon1, Double lat2, Double lon2, Double lat3, Double lon3) {
        String points = "(" + lat1 + "," + lon1 + "),(" + lat2 + "," + lon2 + "),(" + lat3 + "," + lon3 + ")";
        request = given()
                .queryParam("points", points)
                .queryParam("key",prop.getProperty("API_KEY"));
        response= request.when().get(baseURI);
        response.
                then().log().body();
      writeResponse =response.asString();
      responsefile= System.getProperty("user.dir")+"/src/test/resources/responseData/Currentweather_latlon.json";
        Readresponsefile.saveResponse(writeResponse, responsefile);
    }

    @When("I use the parameter postcode {string}")
    public void i_use_the_parameter_postcode(String postCode) {
        request = given()
                .queryParam("postal_code",postCode)
                .queryParam("key",prop.getProperty("API_KEY"));
        response= request.when().get(baseURI);
        response.
                then().log().body();
        writeResponse =response.asString();
        responsefile= System.getProperty("user.dir")+"/src/test/resources/responseData/Currentweather_postcode.json";
        Readresponsefile.saveResponse(writeResponse, responsefile);

    }
    @Then("validate the response status is {string}")
    public void validate_the_response_status_is(String code) {

         try{
             responseStatusCode= String.valueOf(response.getStatusCode());
            Assert.assertTrue("200 Response Successful"+responseStatusCode ,responseStatusCode.contains("200"));
         }
        catch(Exception e) {
             e.printStackTrace();
        }
    }
    @And("check the current weather for multiple places displayed in response")
    public void check_The_Current_Weather_For_Multiple_Places_Displayed_In_Response() {

        try{
            cityName = response.then().extract().path("data.city_name");
            countryCode = response.then().extract().path("data.country_code");
            weatherDescriptions = response.then().extract().path("data.weather.description");
            weatherCode = response.then().extract().path("data.weather.code");
            weatherIcons = response.then().extract().path("data.weather.icon");
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }

@After
    public void tearDown(Scenario scenario) {

    // Print the values in the Cucumber HTML report
        for (int i = 0; i < cityName.size(); i++) {
            String city = cityName.get(i);
            String code = countryCode.get(i);
            String description = weatherDescriptions.get(i);
            String weathercode = String.valueOf(weatherCode.get(i));
            String icon = weatherIcons.get(i);
            scenario.log("City Name: " + city);
            scenario.log("Country Code: " + code);
            scenario.log("Weather Description: " + description);
            scenario.log("Weather Code: " + weathercode);
            scenario.log("Weather Icon: " + icon);
        }
    }


}
