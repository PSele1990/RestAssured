Weatherbit

Clone WeatherBit from git repo - git clone https://github.com/PSele1990/RestAssured
Run mvn clean install to builds the project described by Maven POM file and installs the resulting artifact (JAR) into local Maven repository
Test Data Path - src/test/resources/responseData
Feature file Path - src/test/resources/features/CurrentWeather.feature
Step Definitions file path - src/test/java/stepDefinition
Framework :Implement the automation script using Cucumber framework and RestAssured library in Java.
In Cucumber I am using gherkin as language to create the feature file.
The feature file has the test scenarios.
The test scenarios are mapped with step definition where test cases are present.
Execute the test using test runner

**Test Scenario 1**:Get the current Weather Data for multiple places in the world based on Lat and Lon
**Steps:**
1. Make a GET call API request with the provided url.
2. Pass the  parameters to the request .Parameters passed are :
*         latitude and longitude
*         API_key(API key was provided in the account dashboard)
3. Verify the response status code is 200 (OK).
4. Validate the response data for city name, country code and weather, including description, code, icon parameters.
**Expected Results:** Current weather data for the multiple places is retrieved successfully.

**Test Scenario 2**: Get Current Weather Data for multiple places in the world based on PostCode
**Steps**
1. Make a GET call API request with the provided url.
2. Pass the  parameters to the request .Parameters passed are :
*         postcode 
*         API_key(API key was provided in the account dashboard)
3. Verify the response status code is 200 (OK).
4. Validate the response data for city name, country code and weather, including description, code, icon parameters.
      **Expected Results:** Current weather data for the multiple places is retrieved successfully.
       **Actual Result** : Current weather data was place with postcode mentioned is retrieved successfully.
5.Below are observation made during testing the scenario using postcode as parameter
    1. Multiple postcode cannot be passed under single parameter 
    2. Postcode as passed as different parameter - still fetches current weather for single places but with invalid cityname and country code.
    3. Response is 200 okay
Utilities - read the response file
Reports Path - target/html/cucumber-reports.html
Use Test runner to run the tests / generate test report
************************************************************************************************************************************************
Below are few more testing coverage made during the test which is not included
Preconditions: Invalid latitude and longitude .
Steps:
Make an API request with the provided invalid latitude and longitude by entering a alpha numeric, alphabets or special characters
The response status code is 400 (Bad Request)  is returned
Expected Results 1: Error response indicating invalid latitude and longitude is received.
{
"error": "Invalid lat/lon supplied."
}
Expected results 2:
{
"error": "No points to retrieve. Did you provide a valid query string? (lat1, lon1), (lat2, lon2), ..."
}
