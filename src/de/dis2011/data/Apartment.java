package de.dis2011.data;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.*;

public class Apartment extends Estate {
    private String tableName = "apartment";

    private int estate;
    private int floor;
    private Double rent;
    private int rooms;
    private String balcony;
    private String builtInKitchen;
    private int renter;

    public int getEstate() {
        return estate;
    }

    public void setEstate(int estate) {
        this.estate = estate;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public Double getRent() {
        return rent;
    }

    public void setRent(Double rent) {
        this.rent = rent;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public String getBalcony() {
        return balcony;
    }

    public void setBalcony(String balcony) {
        this.balcony = balcony;
    }

    public String getBuiltInKitchen() {
        return builtInKitchen;
    }

    public void setBuiltInKitchen(String builtInKitchen) {
        this.builtInKitchen = builtInKitchen;
    }

    public int getRenter() {
        return renter;
    }

    public void setRenter(int renter) {
        this.renter = renter;
    }

    /*
            Creates Apartment from input that is only Strings.
        */
    public static Apartment createApartmentStringInput(String id, String floor, String rent, String rooms,
                                                       String balcony, String builtInKitchen) {
        return Apartment.createApartment(Integer.parseInt(id), Integer.parseInt(floor), Double.parseDouble(rent),
                Integer.parseInt(rooms), balcony, builtInKitchen, -1);
    }

    private static Apartment createApartment(int id, int floor, Double rent, int rooms,
                                      String balcony, String builtInKitchen, int renterId){
        Apartment a = new Apartment();
        a.setId(id);
        a.setFloor(floor);
        a.setRent(rent);
        a.setRooms(rooms);
        a.setBalcony(balcony);
        a.setBuiltInKitchen(builtInKitchen);
        if (renterId != -1) {
            a.setRenter(renterId);
        }
        return a;
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
                        "(ESTATE_ID," +
                        "Floor," +
                        "Rent," +
                        "Rooms," +
                        "Balcony," +
                        "Built_in_Kitchen," +
                        "Renter_ID)" +
                        " VALUES (?, ?, ?, ?, ? ,?, ?)";

                System.out.println(insertSQL);

                PreparedStatement pstmt = con.prepareStatement(insertSQL,
                        Statement.RETURN_GENERATED_KEYS);

                // Setze Anfrageparameter und fC<hre Anfrage aus
                pstmt.setInt(1, getEstate());
                pstmt.setInt(2, getFloor());
                pstmt.setDouble(3, getRent());
                pstmt.setInt(4, getRooms());
                pstmt.setString(5, getBalcony());
                pstmt.setString(6, getBuiltInKitchen());
                pstmt.setInt(7, getRenter());

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
                        " SET floor = ?, rent = ?, rooms = ? ,"+
                        "balcony = ? , built_in_kitchen = ?," +
                        " renter_id = ?" +
                        " WHERE estate_id = ?";
                PreparedStatement pstmt = con.prepareStatement(updateSQL);

                // Setze Anfrageparameter

                pstmt.setInt(1, getFloor());
                pstmt.setDouble(2, getRent());
                pstmt.setInt(3, getRooms());
                pstmt.setString(4, getBalcony());
                pstmt.setString(5, getBuiltInKitchen());
                pstmt.setInt(6, getRenter());
                pstmt.setInt(7, getEstate());

                pstmt.executeUpdate();

                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString(){
        return "Apt: " + super.toString();
    }
}
