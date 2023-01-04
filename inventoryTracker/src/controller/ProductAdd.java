package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Inventory;
import model.Outsourced;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Eric Trevorrow
 */

 /** This class launches the Add Product interface of the project.*/
public class ProductAdd implements Initializable {
    private static ObservableList<Part> offParts = FXCollections.observableArrayList();
    private static ObservableList<Part> onParts = FXCollections.observableArrayList();
    public TableView offPartTable;
    public TableColumn offPartID;
    public TableColumn offPartName;
    public TableColumn offInventory;
    public TableColumn offPriceUnit;
    public TableView onPartTable;
    public TableColumn onPartID;
    public TableColumn onPartName;
    public TableColumn onInventory;
    public TableColumn onPriceUnit;
    public Button addPartToProduct;
    public Button remPartFromProduct;
    public TextField prodIDTextfield;
    public TextField prodInvTextfield;
    public TextField prodNameTextfield;
    public TextField prodPriceTextfield;
    public TextField prodMaxTextfield;
    public TextField prodMinTextfield;
    public TextField offPartSearchBar;
    public Button saveButton;
    public Button cancelButton;
    public Label exceptionLabelName;
    public Label exceptionLabelInv;
    public Label exceptionLabelPrice;
    public Label exceptionLabelMax;
    public Label exceptionLabelMin;
    public Label searchErrorLabel;

     /**This is the initialize method. This sets all of the tables to be populated with information.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

       offPartID.setCellValueFactory(new PropertyValueFactory<>("id"));
       offPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
       offInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
       offPriceUnit.setCellValueFactory(new PropertyValueFactory<>("price"));

       onPartTable.setItems(onParts);
       onPartID.setCellValueFactory(new PropertyValueFactory<>("id"));
       onPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
       onInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
       onPriceUnit.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

     /**This is the search method for the top table.
      * Searches through the list and returns a populated table of found items.*/
    public void searchOffParts(ActionEvent actionEvent) {

        String q = offPartSearchBar.getText();

        ObservableList<Part> parts = searchPartName(q);

        searchErrorLabel.setText("");

        if(parts.size() == 0){
            try {

                int partialID = Integer.parseInt(q);
                Part ps = searchPartNumber(partialID);

                if (ps != null) {
                    parts.add(ps);
                } else {
                    searchErrorLabel.setText("No parts found.");
                }
            } catch (NumberFormatException e) {
               searchErrorLabel.setText("No parts found.");
            }
        }
        offPartTable.setItems(parts);
    }

    /**This method searches through the names of the parts.
     * Calls an inventory method to look through the names of a list.
     * @return Returns the parts that were found.*/
    private ObservableList<Part> searchPartName(String partialName) {

        return Inventory.lookupPart(partialName);
    }

     /**This method searches through the ID's of the parts.
      * Calls an inventory method to look through the ID's of a list.
      * @return Returns the parts that were found.*/
    private Part searchPartNumber(int partialID) {

        return Inventory.lookupPart(partialID);
    }

     /**This method adds allParts to a temporary list offParts for the table.
      * Sets the items of the table to display the the list.
      * @param offParts The temporary list.*/
    public void addAllPartsToList(ObservableList<Part> offParts){

        this.offParts = offParts;
        offPartTable.setItems(offParts);
    }

     /**This method validates if text is present in the name field.
      * Validates if there is text or nothing within the text box.*/
    public boolean nameValidation(String name){

        if(name == null || name.isEmpty()) {
            exceptionLabelName.setText("Exception: No data in name field ");
            return false;
        } else {
            exceptionLabelName.setText("");
            return true;
        }
    }

     /**This method validates if the price is a double.
      * Validates if there is text in the text box and validates if it can be converted into a double.*/
    public boolean priceValidation(){

        try{
            String pricePart = prodPriceTextfield.getText();
            Double.parseDouble(pricePart);
        } catch (NumberFormatException e){
            exceptionLabelPrice.setText("Exception: price not a double");
            return false;
        }
        exceptionLabelPrice.setText("");
        return true;
    }

     /**This method validates if the inventory is a double and if it is between max and min.
      * Validates if there is text in the text box and validates if it can be converted into an int,
      * while also validating whether the entered text is between the min and max values.*/
    public boolean invValidation(){

        String invPart = prodInvTextfield.getText();
        String maxPart = prodMaxTextfield.getText();
        String minPart = prodMinTextfield.getText();

        try {
            Integer.parseInt(invPart);
        } catch (NumberFormatException e){
            exceptionLabelInv.setText("Exception: inventory not an int");
            return false;
        }

        try {
            if((Integer.parseInt(invPart) < Integer.parseInt(minPart)) || (Integer.parseInt(invPart) > Integer.parseInt(maxPart))) {
                exceptionLabelInv.setText("Inv must be between min and max");
                return false;
            }
        } catch (NumberFormatException e){
            exceptionLabelInv.setText("Inv must be between min and max");
            return false;
        }

        exceptionLabelInv.setText("");
        return true;
    }

     /**This method validates if the min is an int.
      * Validates if there is text in the text box and validates if it can be converted into an int.*/
    public boolean minValidation(){

        try {
            String minPart = prodMinTextfield.getText();
            Integer.parseInt(minPart);
        } catch (NumberFormatException e){
            exceptionLabelMin.setText("Exception: min not an int");
            return false;
        }

        exceptionLabelMin.setText("");
        return true;
    }

     /**This method validates if the max is an int and if it is less than the min value.
      * Validates if there is text in the text box and validates if it can be converted into an int,
      * while validating whether max is greater than the min value.*/
    public boolean maxValidation(){

        String maxPart = prodMaxTextfield.getText();
        String minPart = prodMinTextfield.getText();

        try {
            Integer.parseInt(maxPart);
        } catch (NumberFormatException e){
            exceptionLabelMax.setText("Exception: max not an int");
            return false;
        }

        try {
            if(Integer.parseInt(maxPart) < Integer.parseInt(minPart)){
                exceptionLabelMax.setText("Exception: max less than min");
                return false;
            }
        } catch (NumberFormatException e){
            exceptionLabelMax.setText("Exception: max less than min");
            return false;
        }

        exceptionLabelMax.setText("");
        return true;
    }


    private Stage stageObject;
    private Scene sceneObject;

    /**This method adds a part from the top list to a temporary list on the bottom.
     * Takes the selected item from the offParts list and puts it in a list of onParts.*/
    public void addPartToProduct(ActionEvent actionEvent) {
        Part SP = (Part) offPartTable.getSelectionModel().getSelectedItem();
      if (SP == null) {
            return;
       }

     onParts.add(SP);
    }

     /**This method adds a part from the bottom list and removes it.
      * Takes the selected item from the onParts list and removes it from the list.*/
    public void removePartFromProduct(ActionEvent actionEvent) {

        if(onPartTable.getSelectionModel().getSelectedItem() != null) {
            Alert alertObject = new Alert(Alert.AlertType.CONFIRMATION);
            alertObject.setTitle("Associated Parts");
            alertObject.setHeaderText("Remove");
            alertObject.setContentText("Do you want to remove the selected part?");

            if (alertObject.showAndWait().get() == ButtonType.OK) {

                Part SP = (Part) onPartTable.getSelectionModel().getSelectedItem();
                if (SP == null) {
                    return;
                }

                onParts.remove(SP);
            }
        }
    }

     /**This method saves a new product and adds it to the Product list.
      * Validates if all of the fields are valid and saves the new product and its associated parts before it switches to the main menu.*/
    public void saveProduct(ActionEvent event) throws IOException{

        String nameProduct = prodNameTextfield.getText();
        String priceProduct = prodPriceTextfield.getText();
        String invProduct = prodInvTextfield.getText();
        String minProduct = prodMinTextfield.getText();
        String maxProduct = prodMaxTextfield.getText();

        priceValidation();
        invValidation();
        minValidation();
        maxValidation();

        if((nameValidation(nameProduct) == true) && (priceValidation() == true)
                && (invValidation() == true) && (minValidation() == true) && (maxValidation() == true)) {

            int newProductID = Inventory.newProductID();

            Product newProduct = new Product(newProductID, nameProduct, Double.parseDouble(priceProduct),
                    Integer.parseInt(invProduct), Integer.parseInt(minProduct), Integer.parseInt(maxProduct));

            for(Part associatedPart : onParts){
                newProduct.addAssociatedPart(associatedPart);
            }

            Inventory.addProduct(newProduct);

            Parent root = FXMLLoader.load(getClass().getResource("/view/Main.fxml"));
            stageObject = (Stage) ((Node) event.getSource()).getScene().getWindow();
            sceneObject = new Scene(root);
            stageObject.setScene(sceneObject);
            stageObject.show();
        }
    }

     /**This method cancels out of the Add Product menu.
      * Returns to the main menu with no changes made.*/
    public void cancelProduct(ActionEvent event) throws IOException {
        onParts.clear();
        Parent root = FXMLLoader.load(getClass().getResource("/view/Main.fxml"));
        stageObject = (Stage)((Node)event.getSource()).getScene().getWindow();
        sceneObject = new Scene(root);
        stageObject.setScene(sceneObject);
        stageObject.show();
    }
}