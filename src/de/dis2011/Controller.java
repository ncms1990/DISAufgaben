package de.dis2011;

import de.dis2011.data.EstateAgent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {
    @FXML
    private Accordion ap;

    @FXML
    TextField fxAgentUserName;

    @FXML
    TableView<EstateAgent> fxEstateAgentsAccounts;

    @FXML
    TableColumn fxColName;
    @FXML
    TableColumn fxColAddress;
    @FXML
    TableColumn fxColUsername;
    @FXML
    TableColumn fxColPassword;

    @FXML
    Button addAcc;


    @FXML
    public void initialize(){
        fxColName.setCellValueFactory(new PropertyValueFactory<EstateAgent, String>("name"));
        fxColAddress.setCellValueFactory(new PropertyValueFactory<EstateAgent, String>("address"));
        fxColUsername.setCellValueFactory(new PropertyValueFactory<EstateAgent, String>("login"));
        fxColPassword.setCellValueFactory(new PropertyValueFactory<EstateAgent, String>("password"));
    }

    private Stage getPrimaryStage(){
        return (Stage) ap.getScene().getWindow();
    }


    public void loginUsername(ActionEvent actionEvent) {
        System.out.println("HELLO THERE! You typed:" + fxAgentUserName.getText());
    }

    public void registerUserName(ActionEvent actionEvent) {

    }

    public void refreshAccs(ActionEvent actionEvent) {

        ObservableList<EstateAgent> accs = FXCollections.observableArrayList();

        EstateAgent.getEstateAgents(accs);
        ObservableList<EstateAgent> estateAgs = fxEstateAgentsAccounts.getItems();
        estateAgs.clear();
        estateAgs.addAll(accs);
    }

    public void deleteAcc(ActionEvent actionEvent) {
        EstateAgent ea = fxEstateAgentsAccounts.getSelectionModel().getSelectedItem();
        EstateAgent.delete(ea);
    }

    public void modifyAcc(ActionEvent actionEvent) {
        EstateAgent ea = fxEstateAgentsAccounts.getSelectionModel().getSelectedItem();
    }

    public void addAccount(ActionEvent actionEvent) {
        Stage pStage = getPrimaryStage();
        List<String> textFields = new ArrayList<String>(Arrays.asList("Name", "Address", "Username", "Password"));
        boolean newEntry = GUITools.initWindowNewEntry(pStage, textFields);
        if (newEntry){
            System.out.println(textFields.get(0));
            System.out.println(textFields.get(1));
            System.out.println(textFields.get(2));
            System.out.println(textFields.get(3));
        }
    }



}
