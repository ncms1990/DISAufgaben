package de.dis2011;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class GUITools {

    public static boolean initWindowNewEntry(Stage pStage, List res, List defaultTextfields) {
        WindowAddNewEntry w = new WindowAddNewEntry(pStage, res, defaultTextfields);
        return w.somethingChanged();
    }


    /*
     * Window is a showAndWait Window. To be used with lambda expr or anony classes.
     * */
    static class WindowAddNewEntry {

        private boolean modified = false;
        private boolean modifyEntryState;
        public boolean somethingChanged(){return modified;}

        /*
        * Note: List<String> has the labels and is used as answer if button is pressed.
        * */
        public WindowAddNewEntry(final Stage primaryStage, List<String> responses, List<String> defaultEntries) {
            // New window (Stage)
            //There are two stages: Either modifyEntryState or addEntryState.
            modifyEntryState = ! defaultEntries.isEmpty();
            Stage newWindow = new Stage();

            Label introLabel = new Label("Add or Modify data:");
            Node[] labels = new Node[responses.size()];
            TextField[] textFields = new TextField[responses.size()];
            for (int i = 0; i< responses.size(); i++){
                labels[i] = new Label(responses.get(i));
                textFields[i]= new TextField();
            }
//            TextField textField1 = new TextField();
            //If default entries is not empty, then its intended to modify (update) an entry. Which means,
            //insert default text to each entry
            if (modifyEntryState){
                for (int i = 0; i< defaultEntries.size(); i++){
                    textFields[i].setText(defaultEntries.get(i));
                }
            }
            Button but = new Button("Add/Modify");
            but.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            modified=true;
                            for(int i = 0; i< responses.size() ; i++){
                                responses.set(i, textFields[i].getText());
                            }
                            newWindow.close();
                        }});

            VBox layout = new VBox();
            layout.getChildren().add(introLabel);
            for (int i=0; i< responses.size(); i++) {
                layout.getChildren().add(labels[i]);
                layout.getChildren().add(textFields[i]);
            }
            layout.getChildren().add(but);

            Scene scene = new Scene(layout, 230, 100 * responses.size());

            newWindow.setTitle("Modify");
            newWindow.setScene(scene);

            // Specifies the modality for new window.
            newWindow.initModality(Modality.WINDOW_MODAL);
            // Specifies the owner Window (parent) for new window
            newWindow.initOwner(primaryStage);

            // Set position of second window, related to primary window.
            newWindow.setX(primaryStage.getX() + 200);
            newWindow.setY(primaryStage.getY() + 100);

            newWindow.showAndWait();
        }
    }

}