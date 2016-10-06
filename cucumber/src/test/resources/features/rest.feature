#Install eclipse plugin from marketplace: http://cucumber.io/cucumber-eclipse/update-site
@rest
Feature: Rest service tests

  #================================================================
  # Succesful tests
  #================================================================
  Scenario: Get successful response from rest service
    Given Id "123" and text "ping"
    When I call the rest service
    Then the response contains
      | myTextProperty   | Echo: ping |
      | myNumberProperty | 123        |
    And status code is "200"

  @wip
  Scenario: Get successful response from rest service
    Given Id "321" and text "hello"
    When I call the rest service
    Then the response contains
      | myTextProperty   | Echo: hello |
      | myNumberProperty | 321         |
    And status code is "200"
