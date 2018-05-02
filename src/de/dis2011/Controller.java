package de.dis2011;

import de.dis2011.data.Estate;
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

    /*
    ATTRIBUTES FOR MANAGEMENT MODE FOR ESTATE AGENTS
     */

    @FXML
    private Accordion ap;

    @FXML
    TextField fxAgentUserName;

    @FXML
    TextField fxAgentLogin;

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


    /*
    ATTRIBUTES FOR MANAGEMENT MODE FOR ESTATE
     */

    @FXML
    TextField fx2AgentUsername;

    @FXML
    TextField fx2AgentPassword;

    @FXML
    TableView<Estate> fx2Estates;

    @FXML
    TableColumn fx2City;
    @FXML
    TableColumn fx2PostalCode;
    @FXML
    TableColumn fx2Street;
    @FXML
    TableColumn fx2StreetNum;
    @FXML
    TableColumn fx2SquareArea;


    /*
    ATTRIBUTES FOR CONTRACT MANAGEMENT
     */

    /*
    METHODS FOR MANAGEMENT MODE FOR ESTATE AGENTS
     */


    @FXML
    public void initialize(){
        fxAgentUserName.setText("See DB2ConnectionManager.");
        //Table Column Cells factory for Estate agents management
        fxColName.setCellValueFactory(new PropertyValueFactory<EstateAgent, String>("name"));
        fxColAddress.setCellValueFactory(new PropertyValueFactory<EstateAgent, String>("address"));
        fxColUsername.setCellValueFactory(new PropertyValueFactory<EstateAgent, String>("login"));
        fxColPassword.setCellValueFactory(new PropertyValueFactory<EstateAgent, String>("password"));
    }

    private Stage getPrimaryStage(){
        return (Stage) ap.getScene().getWindow();
    }


    public void fxLoginUsername(ActionEvent actionEvent) {
        System.out.println("HELLO THERE! You typed:" + fxAgentUserName.getText() + " and " + fxAgentLogin.getText());
    }

    public void registerUserName(ActionEvent actionEvent) {

    }

    public void fxRefreshAccs(ActionEvent actionEvent) {
        fx1RefreshTable();
    }

    public void fx1RefreshTable(){
        ObservableList<EstateAgent> accs = FXCollections.observableArrayList();

        EstateAgent.getEstateAgents(accs);
        ObservableList<EstateAgent> estateAgs = fxEstateAgentsAccounts.getItems();
        estateAgs.clear();
        estateAgs.addAll(accs);
    }

    public void fxDeleteAcc(ActionEvent actionEvent) {
        EstateAgent ea = fxEstateAgentsAccounts.getSelectionModel().getSelectedItem();
        System.out.println(ea.getId());
        EstateAgent.delete(ea);
        fx1RefreshTable();
    }

    public void fxModifyAcc(ActionEvent actionEvent) {
        EstateAgent ea = fxEstateAgentsAccounts.getSelectionModel().getSelectedItem();
        Stage pStage = getPrimaryStage();
        List<String> textFields = new ArrayList<String>(Arrays.asList("Name", "Address", "Username", "Password"));
        List<String> defaultFields =
                new ArrayList<String>(Arrays.asList(ea.getName(), ea.getAddress(), ea.getLogin(), ea.getPassword()));
        boolean newEntry = GUITools.initWindowNewEntry(pStage, textFields, defaultFields);
        if (newEntry){
            ea.setName(textFields.get(0));
            ea.setAddress(textFields.get(1));
            ea.setLogin(textFields.get(2));
            ea.setPassword(textFields.get(3));
            ea.save();
            fx1RefreshTable();
        }
    }

    public void fxAddAccount(ActionEvent actionEvent) {
        Stage pStage = getPrimaryStage();
        List<String> textFields = new ArrayList<String>(Arrays.asList("Name", "Address", "Username", "Password"));
        boolean newEntry = GUITools.initWindowNewEntry(pStage, textFields, new ArrayList<String>());
        if (newEntry){
            EstateAgent newEA = EstateAgent.createEstateAgent(
            textFields.get(0),
            textFields.get(1),
            textFields.get(2),
            textFields.get(3)
            );
            newEA.save();
        }
        fx1RefreshTable();
    }

    /*************************************************************
    METHODS FOR MANAGEMENT MODE FOR ESTATE
     *************************************************************
     */

    public void fx2LoginEstateAgent(ActionEvent ae){
        boolean loggedIn = EstateAgent.checkingEstateAgentUsernamePassword(fx2AgentUsername.getText(), fx2AgentPassword.getText());
        if (loggedIn){System.out.println("YOU LOGGED IN.");}else{System.out.println("WRONG!");}

    }

    public void fx2RefreshEstates(ActionEvent ae){
        fx2RefreshTable();
    }

    public void fx2RefreshTable(){
        ObservableList<Estate> accs = FXCollections.observableArrayList();

        Estate.getEstates(accs);
        ObservableList<Estate> estates = fx2Estates.getItems();
        estates.clear();
        estates.addAll(accs);
    }

    public void fx2UpdateEstate(ActionEvent ae){

    }

    public void fx2DeleteEstate(ActionEvent ae){
        Estate e = fx2Estates.getSelectionModel().getSelectedItem();
        System.out.println(e.getId());
        Estate.delete(e);
        fx2RefreshTable();

    }

    public void fx2AddEstate(ActionEvent ae){


    }

    public void fx2Update(ActionEvent actionEvent) {
        Estate e = fx2Estates.getSelectionModel().getSelectedItem();
        Stage pStage = getPrimaryStage();
        List<String> textFields = new ArrayList<String>(Arrays.asList("Name", "Address", "Username", "Password"));
        List<String> defaultFields =
                new ArrayList<String>(Arrays.asList(e.getCity(), e.getPostalCode(), e.getStreet(),
                        String.valueOf(e.getStreetNumber()), String.valueOf(e.getSquareArea())));
        boolean newEntry = GUITools.initWindowNewEntry(pStage, textFields, defaultFields);
        if (newEntry){
            e.setCity(textFields.get(0));
            e.setPostalCode(textFields.get(1));
            e.setStreet(textFields.get(2));
            e.setStreetNumber(Integer.parseInt(textFields.get(3)));
            e.setSquareArea(Double.parseDouble(textFields.get(4)));
            e.save();
            fx2RefreshTable();
        }

    }


    /*
    METHODS FOR CONTRACT MANAGEMENT
     */

}
