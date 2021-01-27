Write a Java class called Product that has the following class attributes:

long itemCode, String itemName, Money itemCost, String description, int inventory;

Write a Java program that loads up a list of Products from a CSV file called products.csv

Each line of the products.csv file will look something like this:

29301001122, iPhone12, EUR 450.00, Latest and greatest from Apple, 234

For testing purposes, create a products.csv file with at least 10 products

The natural order for the product class should be based on the itemCode attribute.

Define equality for the Product class as being true if the itemCode, itemName and itemCost are all the same.

Implement the hashcode() method for the Product class based on the example covered during the live class.

Implement an appropriate toString() method for the Product class.

Then perform the following tests:

   - Use the Collections.sort() method to sort the list of products based on Natural Order and print out the result.
   - Sort the list of products again based on the cost, in descending order, using a Comparator defined as an anonymous inner class, print out the results.
   - Sort the list of products again based on the available inventory, in ascending order, using a Comparator defined as a lambda function, print out the results.
   - Use the Collections.binarySearch() method to search for a product in the list that has been pre-sorted using the cost in ascending order.
   - Add the list of products to a Map where the itemCode is used as the key and the Product object is the value. Then retrieve a Product from the Map using an appropriate key and print out the result. Are there any potential advantages in using a Map instead of a List type data structure to store the list of products in memory?
