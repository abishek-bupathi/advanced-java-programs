// Name : Abishek Bupathi

import java.io.*;
import java.util.*;
import org.joda.money.Money;


public class Test {

    public static void main(String[] args) {

        // Creating a list to store the products
        List<Product> products = new ArrayList<>();

        try {
            // Creating test data
            createTestData();
            // populating the list with data read from product.csv file
            products = readData("products.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Priting the unsorted data from the file
        System.out.println("Data read from the file (Unsorted): ");
        System.out.println(products);

        // Sorting and printing according to natural order
        Collections.sort(products);
        System.out.println("\nSorted in Natural order:\n" + products + "\n");

        // Sort based on item cost in descending order using a Comparator implemented as an anonymous inner class
        Collections.sort(products, new Comparator<Product>() {
            public int compare(Product p1, Product p2) {
                return p2.getItemCost().compareTo(p1.getItemCost());
            }
        });
        System.out.println("Sorted by descending Item cost:\n" + products + "\n");

        // Sort based on Inventory in ascending order using a Comparator implemented as a lambda function
        Collections.sort(products, (Product p1, Product p2) ->
                Integer.compare(p1.getInventory(), p2.getInventory()));
        System.out.println("Sorted by ascending Inventory:\n" + products + "\n");

        // Getting unsorted data from the file again
        try {
            products = readData("products.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // creating the object to be searched
        Product item = new Product(29302001127L, "iPhone9",Money.parse("EUR 330.00"),"Latest and greatest from Apple",423);
        // Sorting based on itemCost in ascending order
        Collections.sort(products, Comparator.comparing(Product::getItemCost));

        System.out.println("Sorted by ascending Cost:\n" + products + "\n");

        System.out.println("Searching for Item with code " + item.getItemCode() + "...\nResult: ");
        // Searching for a product using binarySearch()
        // Calling the searchList function by passing the list, item to be searched for and comparison key(itemCost in this case)
        searchList(products, item, Comparator.comparing(Product::getItemCost));

        System.out.println("\nList contents after search\n" + products + "\n");

        // Creating a HashMap
        Map<Long, Product> productsMap = new HashMap<Long, Product>();

        // Populating the hasp map with itemCode as key and product object as value
        for (Product product: products)
            productsMap.put(product.getItemCode(), product);

        // creating a key to get object from the hashmap
        long itemKey =  29301001129L;
        // getting the object from the hashmap using the key
        Product itemFromMap = productsMap.get(itemKey);

        // Checking if the key is valid
        if(itemFromMap != null)
            System.out.println("Product got from Map using key (itemCode = 29301001129):\n"+itemFromMap);
        else
            System.out.println("Item with key "+itemKey+" is not found!");
    }

    /*
     * Function Name    :   createTestData
     * Parameters       :   None
     * Returns          :   None
     * Description      :   Creates test data with 10 items in products.csv file
     */
    public static void createTestData() throws IOException {

        // String array to store the items with it's attributes
        String[][] testData = {

                {"29301001125","iPhone7","EUR 545.00","Same but more expensive from Apple","969"},
                {"29301001123","iPhone5","EUR 250.00","Great from Apple","345"},
                {"29301001128","iPhone10","EUR 150.00","Expensive from Apple","645"},
                {"29301001122","iPhone4","EUR 230.00","Old and bad from Apple","244"},
                {"29301001121","iPhone3","EUR 450.00","Oldest and worst from Apple","234"},
                {"29301001126","iPhone8","EUR 487.00","Not so innovative from Apple","122"},
                {"29301001124","iPhone6","EUR 790.00","Better from Apple","421"},
                {"29301001130","iPhone12","EUR 99.00","Latest and greatest from Apple","123"},
                {"29301001129","iPhone11","EUR 56.00","Same as previous from Apple","765"},
                {"29301001127","iPhone9","EUR 330.00","Skipped from Apple","423"},

        };

        System.out.println("--------------------------------------");
        System.out.println("Creating test data file products.csv...");
        // Initializing fileWriter to write data into products.csv
        FileWriter fileWriter = new FileWriter("products.csv");

        // Looping through the testData array and writing the attributes of the item separated by "," on separate line
        System.out.println("Populating test data into products.csv...");
        for ( int i = 0; i < 10 ; i++ ) {
            fileWriter.append(testData[i][0]+","+testData[i][1]+","+testData[i][2]+","+testData[i][3]+","+testData[i][4]+"\n");
        }

        fileWriter.close();
        System.out.println("Completed !");
    }

    /*
     * Function Name    :   readData
     * Parameters       :   File name
     * Returns          :   List of products
     * Description      :   Reads the csv data from the passed filename and return the data in form of Product List
     */
    public static List<Product> readData(String fileName) throws IOException {

        // Initalizing file reader and buffered read to read data from the given csv file
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        System.out.println("--------------------------------------");
        System.out.println("File found!");

        // Initalising local variables to store raw data read from the file
        List<Product> products_temp = new ArrayList<>();

        System.out.println("Reading data from "+fileName+"...");
        // Reading the 1st line of the file to get the loop start running
        String line = bufferedReader.readLine();

        // Loop that runs until last line is reached
        while (line != null){

            // Using split() function of String to separate the values from each line
            String product[] = line.split(",");

            // Populating the list with product attributes on the each line
             products_temp.add(new Product(Long.parseLong(product[0]), product[1], Money.parse(product[2]), product[3], Integer.parseInt(product[4])));

            // Reading the next line of the data file
            line = bufferedReader.readLine();

        }

        System.out.println("Completed");
        System.out.println("--------------------------------------\n");
        // returns the list containing the items
        return products_temp;
    }

    /*
     * Function Name    :   searchList
     * Parameters       :   list, key, comparison factor
     * Returns          :   None
     * Description      :   Checks for the item in the list using the key object and comparison factor using binarySearch method
     */
    private static void searchList(List<Product> list, Product key, Comparator<Product> c) {
        int pos = Collections.binarySearch(list, key, c);
        if (pos >= 0) {
            // If item found
            System.out.println(key + "already in the list at position " + pos);
        }
        else {
            // If item not found
            System.out.println("Oops! Product not found in the list.");
        }
    }
}

/*
 * The potential advantages of storing the list of products in Map instead of List are:
 *  1. Easier and efficient to find Items using Keys (itemCode in this case)
 *  2. Insertion of element in the middle is more efficient
 *
 */
