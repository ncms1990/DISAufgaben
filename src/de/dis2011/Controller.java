package de.dis2011;

import de.dis2011.data.Contract;
import de.dis2011.data.Estate;
import de.dis2011.data.EstateAgent;
import de.dis2011.data.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

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

    private int estateAgentIDLoggedIn = -1;


    /*
    ATTRIBUTES FOR CONTRACT MANAGEMENT
     */

    @FXML
    ListView<Person> fx3PersonsList;

    @FXML
    ListView<String> fx3ContractsList;


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
        //INITIALIZE FOR MANAGEMENTE MODE FOR ESTATES
        fx2City.setCellValueFactory(new PropertyValueFactory<Estate, String>("city"));
        fx2PostalCode.setCellValueFactory(new PropertyValueFactory<Estate, String>("postalCode"));
        fx2Street.setCellValueFactory(new PropertyValueFactory<Estate, String>("street"));
        fx2StreetNum.setCellValueFactory(new PropertyValueFactory<Estate, String>("streetNumber"));
        fx2SquareArea.setCellValueFactory(new PropertyValueFactory<Estate, String>("squareArea"));
        //INITIALIZE FOR CONTRACT MANAGEMENT MODE
        ObservableList data =
                FXCollections.observableArrayList();

        Person p1 = Person.createPerson("Al","Bundy","mofo");
        Person p2 = Person.createPerson("Chavo","Chespirito","Barril numero 8");
        Person p3 = Person.createPerson("Don Ramon","Ximenes","Apt 73");


        data.add(p1);
        data.add(p2);
        data.add(p3);

//        for (int i = 0; i < 18; i++) {
//            data.add("anonym");
//        }

        fx3PersonsList.setItems(data);


        //fx3PersonsList.setCellFactory(ComboBoxListCell.forListView(names));

    }

    private Stage getPrimaryStage(){
        return (Stage) ap.getScene().getWindow();
    }


    public void fxLoginUsername(ActionEvent actionEvent) {
        System.out.println("HELLO THERE! You typed:" + fxAgentUserName.getText() + " and " + fxAgentLogin.getText());
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
        if (!fxEstateAgentsAccounts.getSelectionModel().isEmpty()) {
            EstateAgent ea = fxEstateAgentsAccounts.getSelectionModel().getSelectedItem();
            System.out.println(ea.getId());
            EstateAgent.delete(ea);
            fx1RefreshTable();
        }
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

    private boolean checkIfLogedIn() {
        return estateAgentIDLoggedIn != -1;
    }

    public void fx2LoginEstateAgent(ActionEvent ae){
        int estateAgentId = EstateAgent.checkingEstateAgentUsernamePassword(fx2AgentUsername.getText(), fx2AgentPassword.getText());
        if (estateAgentId != -1)
        {
            estateAgentIDLoggedIn = estateAgentId;
            fx2RefreshTable();
            System.out.println("YOU LOGGED IN.");
        }
        else{
            estateAgentIDLoggedIn = -1;
            System.out.println("WRONG!");
        }

    }

    public void fx2RefreshEstates(ActionEvent ae){
        if (checkIfLogedIn()){
            fx2RefreshTable();
        }
    }



    private void fx2RefreshTable(){
        if (checkIfLogedIn()) {
            ObservableList<Estate> accs = FXCollections.observableArrayList();

            Estate.getEstates(accs, estateAgentIDLoggedIn);
            ObservableList<Estate> estates = fx2Estates.getItems();
            estates.clear();
            estates.addAll(accs);
        }
        else{
            System.out.println("NEED TO LOG IN.");
        }
    }

    public void fx2DeleteEstate(ActionEvent ae){
        if (checkIfLogedIn()) {
            Estate e = fx2Estates.getSelectionModel().getSelectedItem();
            System.out.println(e.getId());
            Estate.delete(e);
            fx2RefreshTable();
        }
        else{
            System.out.println("NEED TO LOG IN.");
        }

    }


    public void fx2UpdateEstate(ActionEvent actionEvent) {
        if (checkIfLogedIn()) {
            Estate e = fx2Estates.getSelectionModel().getSelectedItem();
            Stage pStage = getPrimaryStage();
            List<String> textFields = new ArrayList<String>(Arrays.asList("City", "Postal code",
                    "Street", "Street number", "Square area"));
            List<String> defaultFields =
                    new ArrayList<String>(Arrays.asList(e.getCity(), e.getPostalCode(), e.getStreet(),
                            String.valueOf(e.getStreetNumber()), String.valueOf(e.getSquareArea())));
            boolean newEntry = GUITools.initWindowNewEntry(pStage, textFields, defaultFields);
            if (newEntry) {
                e.setCity(textFields.get(0));
                e.setPostalCode(textFields.get(1));
                e.setStreet(textFields.get(2));
                e.setStreetNumber(Integer.parseInt(textFields.get(3)));
                e.setSquareArea(Double.parseDouble(textFields.get(4)));
                e.setEstateAgentID(estateAgentIDLoggedIn);
                e.save();
                fx2RefreshTable();
            }
        }
        else{
            System.out.println("NEED TO LOG IN.");
        }

    }

    public void fx2AddEstate(ActionEvent actionEvent) {
        if (checkIfLogedIn()){
            Estate estate = fx2Estates.getSelectionModel().getSelectedItem();
            Stage pStage = getPrimaryStage();
            List<String> textFields = new ArrayList<String>(Arrays.asList("City", "Postal code",
                    "Street", "Street number", "Square area"));
            boolean newEntry = GUITools.initWindowNewEntry(pStage, textFields, new ArrayList<Estate>());
            if (newEntry){
                Estate newE = Estate.createEstate(
                        textFields.get(0),
                        textFields.get(1),
                        textFields.get(2),
                        Integer.parseInt(textFields.get(3)),
                        Double.parseDouble(textFields.get(4)),
                        estateAgentIDLoggedIn
                );
                newE.save();
                fx2RefreshTable();
            }
        }
        else{
            System.out.println("NEED TO LOG IN.");
        }
    }


    /*
    METHODS FOR CONTRACT MANAGEMENT
     */

    public void fx3AddPerson(ActionEvent actionEvent){


    }

    public void fx3SignContract(ActionEvent actionEvent){


    }

}
