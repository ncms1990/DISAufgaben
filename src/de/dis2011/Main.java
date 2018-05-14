package de.dis2011;

import de.dis2011.data.EstateAgent;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Hauptklasse
 */
public class Main extends Application {


    /**
	 * Startet die Anwendung
	 */
	public static void main(String[] args) {
		//showMainMenu();//FROM EXAMPLE
		launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
