package de.dis2011.data;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.*;
import java.util.List;

public class Estate {

    private static String tableName = "estate";

    private int id = -1;
    private SimpleStringProperty city = new SimpleStringProperty("");
    private SimpleStringProperty postalCode = new SimpleStringProperty("");
    private SimpleStringProperty street = new SimpleStringProperty("");
    private SimpleIntegerProperty streetNumber = new SimpleIntegerProperty(0);
    private SimpleDoubleProperty squareArea = new SimpleDoubleProperty(0);
    private int estateAgentID = -1;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city.get();
    }

    public SimpleStringProperty cityProperty() {
        return city;
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public String getPostalCode() {
        return postalCode.get();
    }

    public SimpleStringProperty postalCodeProperty() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode.set(postalCode);
    }

    public String getStreet() {
        return street.get();
    }

    public SimpleStringProperty streetProperty() {
        return street;
    }

    public void setStreet(String street) {
        this.street.set(street);
    }

    public int getStreetNumber() {
        return streetNumber.get();
    }

    public SimpleIntegerProperty streetNumberProperty() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber.set(streetNumber);
    }

    public double getSquareArea() {
        return squareArea.get();
    }

    public SimpleDoubleProperty squareAreaProperty() {
        return squareArea;
    }

    public void setSquareArea(double squareArea) {
        this.squareArea.set(squareArea);
    }

    public int getEstateAgentID() {
        return estateAgentID;
    }

    public void setEstateAgentID(int estateAgentID) {
        this.estateAgentID=estateAgentID;
    }

    public static Estate createEstate(int id, String city, String postalCode, String street,
                                      int streetNum, double squareArea, int foreignID){
        Estate e = new Estate();
        e.setId(id);
        e.setCity(city);
        e.setPostalCode(postalCode);
        e.setStreet(street);
        e.setStreetNumber(streetNum);
        e.setSquareArea(squareArea);
        e.setEstateAgentID(foreignID);
        return e;
    }

    public static Estate createEstate(String city, String postalCode, String street,
                                      int streetNum, double squareArea, int foreignID){
        Estate e = new Estate();
        e.setCity(city);
        e.setPostalCode(postalCode);
        e.setStreet(street);
        e.setStreetNumber(streetNum);
        e.setSquareArea(squareArea);
        e.setEstateAgentID(foreignID);
        return e;
    }


    public static void getEstates(List<Estate> es, int estateAgentID){
        try {
            // Hole Verbindung
            Connection con = DB2ConnectionManager.getInstance().getConnection();

            // Erzeuge Anfrage
            String selectSQL = "SELECT * FROM " + tableName + " WHERE estate_agent_id="
                    + String.valueOf(estateAgentID);
            PreparedStatement pstmt = con.prepareStatement(selectSQL);

            // Führe Anfrage aus
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Estate estate = Estate.createEstate(
                        rs.getInt("id"),
                        rs.getString("city"),
                        rs.getString("postal_code"),
                        rs.getString("street"),
                        rs.getInt("street_number"),
                        rs.getDouble("square_area"),
                        rs.getInt("estate_agent_id"));
                es.add(estate);
            }
            //Close all the things!
            rs.close();
            pstmt.close();
        }
        catch (SQLException e1) {
            e1.printStackTrace();
        }
        //return es;//No need:Array
    }


    public static void delete(Estate e) {
        try {
            // Hole Verbindung
            Connection con = DB2ConnectionManager.getInstance().getConnection();

            // Erzeuge Anfrage
            String deleteSQL = "DELETE FROM "+ tableName + " WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(deleteSQL);
            pstmt.setString(1, Integer.toString(e.getId()));
            // Führe Anfrage aus
            int result = pstmt.executeUpdate();

            //TODO: With int result how to know if success or failure?

        }
        catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * Speichert den Estate in der Datenbank. Ist noch keine ID vergeben
     * worden, wird die generierte Id von DB2 geholt und dem Model übergeben.
     */
    public void save() {
        // Hole Verbindung
        Connection con = DB2ConnectionManager.getInstance().getConnection();

        try {
            // FC<ge neues Element hinzu, wenn das Objekt noch keine ID hat.
            if (getId() == -1) {
                // Achtung, hier wird noch ein Parameter mitgegeben,
                // damit spC$ter generierte IDs zurC<ckgeliefert werden!
                String insertSQL = "INSERT INTO  " + tableName +
                        "(city, postal_code, street, street_number, square_area, estate_agent_id)" +
                        " VALUES (?, ?, ?, ?, ? ,?)";

                System.out.println(insertSQL);

                PreparedStatement pstmt = con.prepareStatement(insertSQL,
                        Statement.RETURN_GENERATED_KEYS);

                // Setze Anfrageparameter und fC<hre Anfrage aus
                pstmt.setString(1, getCity());
                pstmt.setString(2, getPostalCode());
                pstmt.setString(3, getStreet());
                pstmt.setInt(4, getStreetNumber());
                pstmt.setDouble(5, getSquareArea());
                pstmt.setInt(6, getEstateAgentID());

                pstmt.executeUpdate();

                // Hole die Id des engefC<gten Datensatzes
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    setId(rs.getInt(1));
                }

                rs.close();
                pstmt.close();
            } else {
                // Falls schon eine ID vorhanden ist, mache ein Update...
                String updateSQL = "UPDATE " + tableName +
                        " SET city = ?, postal_code = ?, street = ?, street_number = ? ,"+
                        "square_area = ? , estate_agent_id = ?" +
                        " WHERE id = ?";
                PreparedStatement pstmt = con.prepareStatement(updateSQL);

                // Setze Anfrage Parameter
                pstmt.setString(1, getCity());
                pstmt.setString(2, getPostalCode());
                pstmt.setString(3, getStreet());
                pstmt.setInt(4, getStreetNumber());
                pstmt.setDouble(5, getSquareArea());
                pstmt.setInt(6, getEstateAgentID());
                pstmt.setInt(7, getId());
                pstmt.executeUpdate();

                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
