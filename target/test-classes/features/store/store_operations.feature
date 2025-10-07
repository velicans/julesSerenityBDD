Feature: Store operations

  Background:
    Given the pet store API is available

  Scenario: Get inventory successfully
    When I get the inventory
    Then I should receive the inventory map

  Scenario: Place an order successfully
    Given a valid order with petId 1 and quantity 2
    When I place the order
    Then the order should be placed

  Scenario: Place an order with invalid input
    Given an order with invalid data
    When I try to place the order with invalid input
    Then I should receive a 400 bad request error

  Scenario: Get order by ID successfully
    Given an existing order with id 1
    When I get the order by id 1
    Then I should receive the order details

  Scenario: Get order with invalid ID
    Given an invalid order id like "abc"
    When I try to get the order with invalid id
    Then I should receive a 400 error

  Scenario: Get order with non-existent ID
    Given an order id 999999 that does not exist
    When I try to get the order not found
    Then I should receive a 404 error

  Scenario: Delete order successfully
    Given an existing order with id 2
    When I delete the order by id 2
    Then the order should be deleted successfully

  Scenario: Delete order with invalid ID
    Given an invalid order id like "xyz"
    When I try to delete the order with invalid id
    Then I should receive a 400 error

  Scenario: Delete order that does not exist
    Given an order id 123456789 that does not exist
    When I try to delete the order not found
    Then I should receive a 404 error
