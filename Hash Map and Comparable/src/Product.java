// Name : Abishek Bupathi

import org.joda.money.Money;


public class Product implements Comparable<Product>{

    // Initializing instance variables
    long itemCode;
    String itemName;
    Money itemCost;
    String description;
    int inventory;

    public Product(long itemCode, String itemName, Money itemCost, String description, int inventory){
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemCost = itemCost;
        this.description = description;
        this.inventory = inventory;
    }

    // implementing compareTo method which compares the item code of the products
    @Override
    public int compareTo(Product product) {
        return Long.compare(itemCode, product.itemCode);
    }

    // implementing equals method which checks for itemCode, itemName and itemCost
    public boolean equals(Object o){

        if(this == o)
            return true;

        if(o == null || o.getClass() != o.getClass())
            return false;

        Product p = (Product) o;
        return itemCode == p.itemCode && itemName.equals(p.itemName) && itemCost == p.itemCost;
    }

    // implementing hashCode methods that returns the hashed value of the object
    public int hashCode(){

        int prime = 31;
        int hash = 1;

        hash = prime + hash + ((itemCode == 0) ? 0 : Long.hashCode(itemCode));
        hash = prime + hash + ((itemName == null) ? 0 : itemName.hashCode());
        hash = prime + hash + ((itemCost == null) ? 0 : itemCost.hashCode());
        hash = prime + hash + ((description == null) ? 0 : description.hashCode());
        hash = prime + hash + ((inventory == 0) ? 0 : Integer.hashCode(inventory));

        return hash;
    }

    // implementing toString method to customize the way object is displayed
    @Override
    public String toString() {
        return "Product: " +
                "itemCode= " + itemCode +
                ", itemName= " + itemName +
                ", itemCost= " + itemCost +
                ", description= " + description +
                ", inventory= " + inventory +"\n";
    }

    public long getItemCode() {
        return itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public Money getItemCost() {
        return itemCost;
    }

    public String getDescription() {
        return description;
    }

    public int getInventory() {
        return inventory;
    }
}
