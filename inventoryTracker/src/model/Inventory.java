package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Eric Trevorrow
 */

/** This class creates the Inventory part of the project that encompasses Parts (InHouse and Outsourced) and Products.*/
public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private static int partID = 0;
    private static int productID = 0;


    /**This method increments a new Part's ID.
     * Generates a unique ID for each new part.
     * @return Returns the Part ID.*/
    public static int newPartID(){

        partID = partID+1;
        return partID;
    }

    /**This method increments a new Product's ID.
     * Generates a unique ID for each new product.
     * @return Returns the Product ID.*/
    public static int newProductID(){

        productID = productID+1;
        return productID;
    }

    /**This method adds a new part.
     * Adds a new part to the allParts list.
     * @param newPart The part being added to the allParts list.*/
    public static void addPart(Part newPart){

        allParts.add(newPart);
    }

    /**This method adds a new product.
     * Adds a new product to the allProducts list.
     * @param newProduct The product being added to the allProducts list.*/
    public static void addProduct(Product newProduct){

        allProducts.add(newProduct);
    }

    /**This method searches through the parts list.
     * Searches through the list of allParts by ID and returns the corresponding part if matched.
     * @param partID The ID of the part.
     * @return Returns null if nothing is found.*/
    public static Part lookupPart(int partID){

        ObservableList<Part> allParts = getAllParts();

        for(int i = 0; i < allParts.size(); i++){
            Part ps = allParts.get(i);

            if(ps.getId() == partID){
                return ps;
            }
        }
        return null;
    }

    /**This method searches through the product list.
     * Searches through the list of allProduct by ID and returns the corresponding product if matched.
     * @param productID The ID of the product.
     * @return Returns null if nothing is found.*/
    public static Product lookupProduct(int productID){

        ObservableList<Product> allProducts = getAllProducts();

        for(int i = 0; i < allProducts.size(); i++){
            Product ps = allProducts.get(i);

            if(ps.getId() == productID){
                return ps;
            }
        }
        return null;
    }

    /**This method searches through a list of parts.
     * Searches through the list of allParts by name and returns the corresponding part if matched.
     * @param partName The name of the part.
     * @return Returns a list of the parts with the corresponding name.*/
    public static ObservableList<Part> lookupPart(String partName){

        ObservableList<Part> namedParts = FXCollections.observableArrayList();

        ObservableList<Part> allParts = Inventory.getAllParts();

        for(Part ps : allParts){
           if(ps.getName().toLowerCase().contains(partName.toLowerCase())){
               namedParts.add(ps);
           }
        }
        return namedParts;
    }

    /**This method searches through a list of products.
     * Searches through the list of allProducts by name and returns the corresponding product if matched.
     * @param productName The name of the product.
     * @return Returns a list of the products with the corresponding name.*/
    public static ObservableList<Product> lookupProduct(String productName){

       ObservableList<Product> namedProducts = FXCollections.observableArrayList();

       ObservableList<Product> allProducts = Inventory.getAllProducts();

       for(Product ps : allProducts){
           if(ps.getName().toLowerCase().contains(productName.toLowerCase())){
               namedProducts.add(ps);
           }
       }
       return namedProducts;
   }

    /**This method updates the part.
     * Updates the index of the selected part.
     * @param index The index of the part.
     * @param selectedPart The part that is selected.*/
    public static void updatePart(int index, Part selectedPart){

        allParts.set(index, selectedPart);
    }

    /**This method updates the product.
     * Updates the index of the selected product.
     * @param index The index of the product.
     * @param selectedProduct The product that is selected.*/
    public static void updateProduct(int index, Product selectedProduct){

        allProducts.set(index, selectedProduct);
    }

    /**This method deletes a part.
     * Deletes the selected part from the allParts list.
     * @param selectedPart The selected part.*/
    public static boolean deletePart(Part selectedPart){

        allParts.remove((selectedPart));
        return true;
    }

    /**This method deletes a product.
     * Deletes the selected product from the allProducts list.
     * @param selectedProduct The selected part.*/
    public static boolean deleteProduct(Product selectedProduct){

        if(selectedProduct.getAllAssociatedParts().size() == 0) {
            allProducts.remove((selectedProduct));
            return true;
        } else {
            return false;
        }

    }

    /**This method returns all parts.
     * Obtains a list of each item in the allParts list.
     * @return Returns a list of All Parts.*/
    public static ObservableList<Part> getAllParts(){

        return allParts;
    }

    /**This method returns all products.
     * Obtains a list of each item in the allProducts list.
     * @return Returns a list of All Products.*/
    public static ObservableList<Product> getAllProducts(){

        return allProducts;
    }
}
