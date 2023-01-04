package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Eric Trevorrow
 */

/**This class launches the Main Menu interface of the project.*/
public class MainInterface implements Initializable {
    public TableView partTable;
    public TableColumn partIDCol;
    public TableColumn partNameCol;
    public TableColumn partInventoryCol;
    public TableColumn pricePartUnitCol;
    public TableView productTable;
    public TableColumn productIDCol;
    public TableColumn productNameCol;
    public TableColumn prodInventoryCol;
    public TableColumn priceProdUnitCol;
    public TextField queryParts;
    public TextField queryProducts;


    private static boolean firstTime = true;
    public Button partDeleteButton;
    public Button productDeleteButton;
    public Button exitButton;
    public AnchorPane mainPane;
    public Label errorField;
    public Label prodErrorLabel;

    /**This is the Test Data method. This adds test data to the tables.*/
    private void addTestData() {

        if(!firstTime){
            return;
        }
        firstTime = false;

        Internal bikeSeat = new Internal(Inventory.newPartID(), "Bike Seat", 20.00,5,1,10,190);
        Inventory.addPart(bikeSeat);

        Outsourced bikeHandle = new Outsourced(Inventory.newPartID(), "Bike Handle", 15.00,2,1,10,"Parts Auto");
        Inventory.addPart(bikeHandle);

        Product bicycle = new Product(Inventory.newProductID(), "Bicycle", 150.00,3,1,5);
        Inventory.addProduct(bicycle);

        Product exBicycle = new Product(Inventory.newProductID(), "Extreme Bicycle", 250.00,4,1,5);
        Inventory.addProduct(exBicycle);
    }

    /**This is the initialize method. Initializes the tables with data from parts and products.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        addTestData();

        partTable.setItems(Inventory.getAllParts());
        partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        pricePartUnitCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        productTable.setItems(Inventory.getAllProducts());
        productIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        prodInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        priceProdUnitCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    private Stage stageObject;
    private Scene sceneObject;

    /**This method switches to the Add Part menu. Initializes and switches scenes to Add Part.*/
    public void switchToAddPart(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/PartAddView.fxml"));
        stageObject = (Stage)((Node)event.getSource()).getScene().getWindow();
        sceneObject = new Scene(root);
        stageObject.setScene(sceneObject);
        stageObject.show();
    }

    /**This method switches to the Modify Part menu. Initializes and switches scenes to Modify Part.*/
    public void switchToModifyPart(ActionEvent event) throws IOException {

        if(partTable.getSelectionModel().getSelectedItem() != null) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PartModifyView.fxml"));
            Parent root = loader.load();

            PartModify partToModify = loader.getController();
            partToModify.changePart((Part) partTable.getSelectionModel().getSelectedItem());

            stageObject = (Stage) ((Node) event.getSource()).getScene().getWindow();
            sceneObject = new Scene(root);
            stageObject.setScene(sceneObject);
            stageObject.show();
        } else {
            errorField.setText("No part selected to modify.");
        }
    }

    /**This method switches to the Add Product menu. Initializes and switches scenes to Add Product.*/
    public void switchToAddProduct(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ProductAddView.fxml"));
        Parent root = loader.load();

        ProductAdd offParts = loader.getController();
        offParts.addAllPartsToList(partTable.getItems());

        stageObject = (Stage)((Node)event.getSource()).getScene().getWindow();
        sceneObject = new Scene(root);
        stageObject.setScene(sceneObject);
        stageObject.show();
    }

    /**This method switches to the Modifying Product menu. Initializes and switches scenes to Modify Product.*/
    public void switchToModifyProduct(ActionEvent event) throws IOException {

        if(productTable.getSelectionModel().getSelectedItem() != null) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ProductModifyView.fxml"));
            Parent root = loader.load();

            ProductModify offParts = loader.getController();
            offParts.addAllPartsToList(partTable.getItems());

            ProductModify productToModify = loader.getController();
            productToModify.changeProduct((Product) productTable.getSelectionModel().getSelectedItem());

            stageObject = (Stage) ((Node) event.getSource()).getScene().getWindow();
            sceneObject = new Scene(root);
            stageObject.setScene(sceneObject);
            stageObject.show();
        } else {
            prodErrorLabel.setText("No part selected to modify.");
        }
    }

    /**This method searches through the parts table. Looks through a list of parts and sets the table to the parts found.*/
    public void getPartSearch(ActionEvent actionEvent) {

        String q = queryParts.getText();

        ObservableList<Part> parts = searchPartName(q);

        errorField.setText("");

            if(parts.size() == 0){
                try {

                    int partialID = Integer.parseInt(q);
                    Part ps = searchPartNumber(partialID);

                    if (ps != null) {
                        parts.add(ps);
                    } else {
                        errorField.setText("No parts found.");
                    }
                }
                catch (NumberFormatException e) {
                    errorField.setText("No parts found.");
                }
            }
        partTable.setItems(parts);
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

    /**This method searches through the product table. Looks through a list of products and sets the table to the products found.*/
    public void getProductSearch(ActionEvent actionEvent) {

        String q = queryProducts.getText();

        ObservableList<Product> products = searchProdName(q);

        prodErrorLabel.setText("");

        if (products.size() == 0) {
            try {
                int partialID = Integer.parseInt(q);
                Product ps = searchProdNumber(partialID);

                if (ps != null) {
                    products.add(ps);
                }
                prodErrorLabel.setText("");
            } catch (NumberFormatException e) {
                prodErrorLabel.setText("No products found.");
            }
        }
        productTable.setItems(products);
    }

    /**This method searches through the names of the products.
     * Calls an inventory method to look through the names of a list.
     * @return Returns the products that were found.*/
    private ObservableList<Product> searchProdName(String partialName) {
        return Inventory.lookupProduct(partialName);
    }

    /**This method searches through the ID's of the products.
     * Calls an inventory method to look through the ID's of a list.
     * @return Returns the products that were found.*/
    private Product searchProdNumber(int partialID) {
        return Inventory.lookupProduct(partialID);
    }

    /**This method deletes a selected part.
     * Prompts the user if they wish to delete a part, and deletes the chosen part if OK.*/
    public void deletePart(ActionEvent actionEvent) {

        if(partTable.getSelectionModel().getSelectedItem() != null) {
            Alert alertObject = new Alert(Alert.AlertType.CONFIRMATION);
            alertObject.setTitle("Delete part");
            alertObject.setHeaderText("Delete");
            alertObject.setContentText("Do you want to delete the selected part?");

            if(alertObject.showAndWait().get() == ButtonType.OK) {

                stageObject = (Stage) mainPane.getScene().getWindow();

                Part DP = (Part) partTable.getSelectionModel().getSelectedItem();
                Inventory.deletePart(DP);
                ObservableList<Part> allParts = Inventory.getAllParts();
                partTable.setItems(allParts);
                partTable.refresh();
            }
        } else {
            errorField.setText("No part selected to delete.");
        }
    }

    /**This method deletes a selected product.
     * Prompts the user if they wish to delete a product, and deletes the chosen product if OK.*/
    public void deleteProduct(ActionEvent actionEvent) {

        if(productTable.getSelectionModel().getSelectedItem() != null) {
            Alert alertObject = new Alert(Alert.AlertType.CONFIRMATION);
            alertObject.setTitle("Delete product");
            alertObject.setHeaderText("Delete");
            alertObject.setContentText("Do you want to delete the selected product?");

            if (alertObject.showAndWait().get() == ButtonType.OK) {

                stageObject = (Stage) mainPane.getScene().getWindow();

                Product DP = (Product) productTable.getSelectionModel().getSelectedItem();

                if(!Inventory.deleteProduct(DP)){
                    prodErrorLabel.setText("Cannot delete. This product has associated parts.");
                } else {
                    prodErrorLabel.setText("");
                }
                ObservableList<Product> allProducts = Inventory.getAllProducts();
                productTable.setItems(allProducts);
                productTable.refresh();
            }
        } else {
            prodErrorLabel.setText("No product selected to delete.");
        }
    }

    /**This method exits the application.
     * Prompts the user if they wish to exit the application, and closes if OK.*/
    public void exitApplication(ActionEvent actionEvent) {
        Alert alertObject = new Alert(Alert.AlertType.CONFIRMATION);
        alertObject.setTitle("Exit");
        alertObject.setHeaderText("You are about to exit.");
        alertObject.setContentText("Do you want to close the program?");

        if(alertObject.showAndWait().get() == ButtonType.OK) {

            stageObject = (Stage) mainPane.getScene().getWindow();
            stageObject.close();
        }
    }
}
