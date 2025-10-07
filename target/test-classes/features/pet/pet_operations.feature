Feature: Pet operations

  Background:
    Given the pet store API is available

  Scenario: Add a new pet successfully
    Given a valid pet with name "Doggie" and photo URLs
    When I add the pet to the store
    Then the pet should be added successfully

  Scenario: Add a new pet with invalid input
    Given a pet without required fields
    When I try to add the pet with invalid input
    Then I should receive a 405 method not allowed error

  Scenario: Update an existing pet successfully
    Given an existing pet with id 1
    When I update the pet with new name "Updated Doggie"
    Then the pet should be updated successfully

  Scenario: Update a pet with invalid input
    Given a pet with invalid data
    When I try to update the pet with invalid input
    Then I should receive a 400 bad request error

  Scenario: Get pet by ID successfully
    Given a pet exists with id 1
    When I get the pet by id 1
    Then I should receive the pet details

  Scenario: Get pet with invalid ID
    Given an invalid pet id like "abc"
    When I try to get the pet
    Then I should receive a 400 bad request error

  Scenario: Get pet with non-existent ID
    Given a pet id that does not exist like 9999999999
    When I try to get the pet
    Then I should receive a 404 not found error

  Scenario: Find pets by status successfully
    Given pets exist with status "available"
    When I search for pets by status "available"
    Then I should receive a list of available pets

  Scenario: Find pets by invalid status
    Given an invalid status like "unknown"
    When I search for pets by status
    Then I should receive a 400 bad request error

  Scenario: Update pet with form data successfully
    Given a pet with id 1 exists
    When I update the pet with form data name "New Name" and status "sold"
    Then the pet should be updated successfully

  Scenario: Update pet with form data invalid ID
    Given an invalid pet id like "invalid"
    When I update the pet with form data
    Then I should receive a 400 bad request error

  Scenario: Delete a pet successfully
    Given a pet with id 1 exists
    When I delete the pet by id 1
    Then the pet should be deleted successfully

  Scenario: Delete pet with invalid ID
    Given an invalid pet id like "xyz"
    When I try to delete the pet
    Then I should receive a 400 bad request error

  Scenario: Delete pet that does not exist
    Given a pet id that does not exist like 1234567890
    When I try to delete the pet
    Then I should receive a 404 not found error

  Scenario: Upload image for pet
    Given a pet with id 1 exists
    And an image file path "/tmp/sample.png"
    When I upload the image for the pet
    Then the image should be uploaded successfully
