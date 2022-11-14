Feature: Beymen Website Testing
  Scenario: Adding and removing products

    Given Navigate to Beymen Website and Verify that you are on the homepage
    When Search for -sort- with Apache POI and delete the word -sort-
    And Search for -Gomlek- with Apache POI and Press enter on keyboard
    And Choose random product
    And Write the product information and product amount to the text file
    And Add product to cart
    And Check if the price on the product page is the same as the price in the cart.
    And Add one more of the product and verify that the quantity is two.
    Then Delete product from cart and verify deletion

