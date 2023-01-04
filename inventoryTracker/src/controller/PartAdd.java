package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Internal;
import model.Inventory;
import model.Outsourced;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Eric Trevorrow
 */

/**This class launches the Add Part interface of the project.*/
public class PartAdd implements Initializable {
    public RadioButton outsourcedButton;
    public RadioButton internalButton;
    public ToggleGroup inOrOut;
    public Label idOrCompanyLabel;
    public Button saveButton;
    public TextField nameTextField;
    public TextField invTextField;
    public TextField priceTextField;
    public TextField maxTextField;
    public TextField machineCompanyTextField;
    public TextField minTextField;
    public Label exceptionLabelName;
    public Label exceptionLabelInv;
    public Label exceptionLabelPrice;
    public Label exceptionLabelMax;
    public Label exceptionLabelMin;
    public Label exceptionLabelMachine;

    /**This is the initialize method. It is not used on this menu.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    private Stage stageObject;
    private Scene sceneObject;

    /**This method switches to the main menu. Loads and switches back to the main menu.*/
    public void switchToMain(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Main.fxml"));
        stageObject = (Stage)((Node)event.getSource()).getScene().getWindow();
        sceneObject = new Scene(root);
        stageObject.setScene(sceneObject);
        stageObject.show();
    }

    /**This method sets a label if inHouse is selected.
     * If the radio button for InHouse is checked, the label switches to this.*/
    public void internalSelected(ActionEvent actionEvent) {

        idOrCompanyLabel.setText("Machine ID");
    }

    /**This method sets a label if Outsourced is selected.
     * If the radio button for Outsourced is checked, the label switches to this.*/
    public void outsourcedSelected(ActionEvent actionEvent) {

        idOrCompanyLabel.setText("Company Name");
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
                String pricePart = priceTextField.getText();
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

        String invPart = invTextField.getText();
        String maxPart = maxTextField.getText();
        String minPart = minTextField.getText();
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
            String minPart = minTextField.getText();
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

        String maxPart = maxTextField.getText();
        String minPart = minTextField.getText();
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

    /**This method validates if the machine ID is an int.
     * Validates if there is text in the text box and validates if it can be converted into an int.*/
    public boolean machineIDValidation(){

        try {
            String machinePart = machineCompanyTextField.getText();
            Integer.parseInt(machinePart);
        } catch (NumberFormatException e){
            exceptionLabelMachine.setText("Exception: Machine ID not an int");
            return false;
        }
        exceptionLabelMachine.setText("");
        return true;
    }

    /**This method validates if text is present in the company name field.
     * Validates if there is text or nothing within the text box.*/
    public boolean companyNameValidation(String name){
        if(name == null || name.isEmpty()) {
            exceptionLabelMachine.setText("Exception: No data in company field ");
            return false;
        } else {
            exceptionLabelMachine.setText("");
            return true;
        }
    }

    /**This method saves a new part and adds it to the allParts list.
     * Validates if all of the fields are valid and saves the new part before it switches to the main menu.*/
    public void saveNewPart(ActionEvent actionEvent) throws IOException{

        String namePart = nameTextField.getText();
        String pricePart = priceTextField.getText();
        String invPart = invTextField.getText();
        String minPart = minTextField.getText();
        String maxPart = maxTextField.getText();
        String machineID = machineCompanyTextField.getText();
        String companyNamePart = machineCompanyTextField.getText();

        priceValidation();
        invValidation();
        minValidation();
        maxValidation();

        if(internalButton.isSelected()){

            machineIDValidation();
        }

        if (outsourcedButton.isSelected()){

            companyNameValidation(companyNamePart);
        }

        if(internalButton.isSelected() && (nameValidation(namePart) == true) && (priceValidation() == true)
        && (invValidation() == true) && (minValidation() == true) && (maxValidation() == true)
                && (machineIDValidation() == true)){

            int newPartID = Inventory.newPartID();

            Internal newInternal = new Internal(newPartID, namePart, Double.parseDouble(pricePart),
                    Integer.parseInt(invPart), Integer.parseInt(minPart), Integer.parseInt(maxPart),
                    Integer.parseInt(machineID));

            Inventory.addPart(newInternal);

            Parent root = FXMLLoader.load(getClass().getResource("/view/Main.fxml"));
            stageObject = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            sceneObject = new Scene(root);
            stageObject.setScene(sceneObject);
            stageObject.show();

        }

        if(outsourcedButton.isSelected() && (nameValidation(namePart) == true) && (priceValidation() == true)
                && (invValidation() == true) && (minValidation() == true) && (maxValidation() == true)
                && (companyNameValidation(companyNamePart) == true)){

            int newPartID = Inventory.newPartID();

            Outsourced newOutsourced = new Outsourced(newPartID, namePart, Double.parseDouble(pricePart),
                    Integer.parseInt(invPart), Integer.parseInt(minPart),
                    Integer.parseInt(maxPart),companyNamePart);

            Inventory.addPart(newOutsourced);

            Parent root = FXMLLoader.load(getClass().getResource("/view/Main.fxml"));
            stageObject = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            sceneObject = new Scene(root);
            stageObject.setScene(sceneObject);
            stageObject.show();

        }
    }
}
