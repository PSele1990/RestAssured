Feature: WeatherReport

  Scenario Outline: Get the current Weather Data for multiple places in the world based on Lat and Lon
    Given User makes a Get call API request url
    When I use the parameter lat and lon values as <points>
    Then validate the response status is "<code>"
    And check the current weather for multiple places displayed in response

    Examples:
  | points                                      |code|
  | (35.88,-78.79),(47.45,-122.3),(43.3,-2.93)  |200 |


    Scenario Outline: Get Current Weather Data for multiple places in the world based on PostCode
      Given User makes a Get call API request url
      When I use the parameter postcode "<postCode>"
      Then validate the response status is "<code>"
      And check the current weather for multiple places displayed in response

  Examples:
      |postCode|code|
      |27601   |200 |