package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Eric Trevorrow
 */

/** This class starts the program.
 *
 * FUTURE ENHANCEMENT: I think a future enhancement that would help the program is adding more intricate ways for the products to be sorted.
 * You can create products and list them, but there isn't a way to take those products and then divide them further.
 * For instance, what if I had a Bicycle product and a Computer product? Both are very different from each other, but they can be further divided up.
 * What if I had a Dirt Bike and an Apple computer product? Now the products are becoming numerous and different from each other,
 * while also retaining a similar type to other products. I think adding more functionality for putting these different
 * kinds of products into specific groups or lists would help for sorting through the inventory system.*/
public class Main extends Application {

    @Override
    /** This is the start method. This loads the Main Menu interface.*/
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/Main.fxml"));
        primaryStage.setTitle("Inventory Tracker System");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /** This is the main method. This is the first method that gets called when the program is launched. The Javadocs are located in the Javadoc folder.*/
    public static void main(String[] args) {
        launch(args);
    }
}
