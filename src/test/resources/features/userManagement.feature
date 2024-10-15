Feature: User Management

  Scenario: Validate Checkout Process
    Given I log in with username "standard_user" and password "secret_sauce"
    When I add an item to cart
    And Go to checkout page
    Then The process is completed

  Scenario: Validate Adding to Cart Process
    Given I log in with username "standard_user" and password "secret_sauce"
    When I add an item to cart
    Then The item is added successfully to cart

  Scenario: Validate removing from Cart Process
    Given I log in with username "standard_user" and password "secret_sauce"
    When I add an item to cart
    And I remove the item from cart
    Then The item is removed successfully from cart

  Scenario: Checkout process doesnt complete with empty cart
    Given I log in with username "standard_user" and password "secret_sauce"
    When I add no items to cart
    And Go to checkout page
    Then Checkout process doesn't get completed

  Scenario: User info is required to complete checkout
    Given I log in with username "standard_user" and password "secret_sauce"
    When I add an item to cart
    And Go to checkout page without adding info
    Then Error message appears

  Scenario: validate sorting items
    Given I log in with username "standard_user" and password "secret_sauce"
    When I click sort by price from low to high
    Then Items are sorted successfully



