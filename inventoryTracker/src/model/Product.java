package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Eric Trevorrow
 */

/** This class creates the Products objects.*/
public class Product {

    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    public Product(int id, String name, double price, int stock, int min, int max) {
        setId(id);
        setName(name);
        setPrice(price);
        setStock(stock);
        setMin(min);
        setMax(max);
    }

    /**This method finds and returns the ID of a product.
     * Gets the ID of the product.
     * @return Returns the Product ID.*/
    public int getId() {
        return id;
    }

    /**This method sets the ID of the product.
     * Sets the ID of the product
     * @param id The ID of the product.*/
    public void setId(int id) {

        this.id = id;
    }

    /**This method finds and returns the name of a product.
     * Gets the name of the product.
     * @return Returns the Product name.*/
    public String getName() {

        return name;
    }

    /**This method sets the name of the product.
     * Sets the name of the product
     * @param name The name of the product.*/
    public void setName(String name) {

        this.name = name;
    }

    /**This method finds and returns the price of a product.
     * Gets the price of the product.
     * @return Returns the Product price.*/
    public double getPrice() {

        return price;
    }

    /**This method sets the price of the product.
     * Sets the price of the product
     * @param price The price of the product.*/
    public void setPrice(double price) {

        this.price = price;
    }

    /**This method finds and returns the stock of a product.
     * Gets the inventory count of the product.
     * @return Returns the Product's stock.*/
    public int getStock() {

        return stock;
    }

    /**This method sets the stock of the product.
     * Sets the amount of the product that is in inventory
     * @param stock The stock of the product.*/
    public void setStock(int stock) {

        this.stock = stock;
    }

    /**This method finds and returns the minimum of a product.
     * Gets the minimum amount of a product that can be had.
     * @return Returns the Product minimum.*/
    public int getMin() {

        return min;
    }

    /**This method sets the min of the product.
     * Sets the minimum amount of the product that can be in the inventory.
     * @param min The minimum amount of the product.*/
    public void setMin(int min) {

        this.min = min;
    }

    /**This method finds and returns the maximum of a product.
     * Gets the maximum amount of a product that can be had.
     * @return Returns the Product maximum.*/
    public int getMax() {

        return max;
    }

    /**This method sets the max of the product.
     * Sets the maximum amount of the product that can be in the inventory.
     * @param max The maximum amount of the product.*/
    public void setMax(int max) {

        this.max = max;
    }

    /**This method adds an associated part to the product.
     * Adds an associated part that will now be associated to the product.
     * @param selectedAssociatedPart The part to be associated with the product.*/
    public void addAssociatedPart(Part selectedAssociatedPart){

        associatedParts.add(selectedAssociatedPart);
    }

    /**This method deletes an associated part from the product.
     * Deletes an associated part that will now be disassociated to the product.
     * @param selectedAssociatedPart The part to be deleted from the product.*/
    public boolean deleteAssociatedPart(Part selectedAssociatedPart){
        associatedParts.remove((selectedAssociatedPart));
        return true;
    }

    /**This method returns a list of associated parts.
     * Obtains all associated parts related to a specific product and returns the list.
     * @return Returns the list of associated parts from a product.*/
    public ObservableList<Part> getAllAssociatedParts(){

        return associatedParts;
    }



}
