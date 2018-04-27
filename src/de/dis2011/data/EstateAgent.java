package de.dis2011.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;

/**
 * EstateAgent-Bean
 * 
 * Beispiel-Tabelle:WRONG GIVES -542 SQL-ERROR(CODE)
 * CREATE TABLE makler(id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1, NO CACHE) PRIMARY KEY,
 * name varchar(255),
 * address varchar(255),
 * login varchar(40) UNIQUE,
 * password varchar(40));
 *
 * RIGHT:
 * CREATE TABLE makler(id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1, NO CACHE) PRIMARY KEY,
 * name varchar(255), address varchar(255), login varchar(40), password varchar(40));
 * Insert into makler(name, address, login, password) values ('Jose A. Ramon', 'Vecindad numero 72', 'donRamon','fueSinQuererQueriendo');
 */
public class EstateAgent {
	private int id = -1;
	private SimpleStringProperty name = new SimpleStringProperty("");
	private SimpleStringProperty address = new SimpleStringProperty("");
	private SimpleStringProperty login = new SimpleStringProperty("");
	private SimpleStringProperty password = new SimpleStringProperty("");


    public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name.get();
	}
	
	public void setName(String name) {
		this.name.set(name);
	}

	
	public String getAddress() {
		return address.get();
	}
	
	public void setAddress(String address) {
		this.address.set(address);
	}
	
	public String getLogin() {
		return login.get();
	}
	
	public void setLogin(String login) {
		this.login.set(login);
	}
	
	public String getPassword() {
		return password.get();
	}
	
	public void setPassword(String password) {
		this.password.set(password);
	}
	
	/**
     * THIS METHOD IS FROM EXAMPLE
     *
	 * Lädt einen EstateAgent aus der Datenbank
	 * @param id ID des zu ladenden Maklers
	 * @return EstateAgent-Instanz
	 */
	public static EstateAgent load(int id) {
		try {
			// Hole Verbindung
			Connection con = DB2ConnectionManager.getInstance().getConnection();

			// Erzeuge Anfrage
			String selectSQL = "SELECT * FROM makler WHERE id = ?";
			PreparedStatement pstmt = con.prepareStatement(selectSQL);
			pstmt.setInt(1, id);//Id hardcoded: -1 ... WHY?

			// Führe Anfrage aus
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				EstateAgent ts = new EstateAgent();
				ts.setId(id);
				ts.setName(rs.getString("name"));
				ts.setAddress(rs.getString("address"));
				ts.setLogin(rs.getString("login"));
				ts.setPassword(rs.getString("password"));

				rs.close();
				pstmt.close();
				return ts;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Speichert den EstateAgent in der Datenbank. Ist noch keine ID vergeben
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
				String insertSQL = "INSERT INTO makler(name, address, login, password) VALUES (?, ?, ?, ?)";

				PreparedStatement pstmt = con.prepareStatement(insertSQL,
						Statement.RETURN_GENERATED_KEYS);

				// Setze Anfrageparameter und fC<hre Anfrage aus
				pstmt.setString(1, getName());
				pstmt.setString(2, getAddress());
				pstmt.setString(3, getLogin());
				pstmt.setString(4, getPassword());
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
				String updateSQL = "UPDATE makler SET name = ?, address = ?, login = ?, password = ? WHERE id = ?";
				PreparedStatement pstmt = con.prepareStatement(updateSQL);

				// Setze Anfrage Parameter
				pstmt.setString(1, getName());
				pstmt.setString(2, getAddress());
				pstmt.setString(3, getLogin());
				pstmt.setString(4, getPassword());
				pstmt.setInt(5, getId());
				pstmt.executeUpdate();

				pstmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static EstateAgent createEstateAgent(String name, String address, String username, String password){
	    EstateAgent ea = new EstateAgent();
	    ea.setName(name);
	    ea.setAddress(address);
	    ea.setLogin(username);
	    ea.setPassword(password);
	    return ea;
    }

    public static void getEstateAgents(List<EstateAgent> eas){
        try {
            // Hole Verbindung
            Connection con = DB2ConnectionManager.getInstance().getConnection();

            // Erzeuge Anfrage
            String selectSQL = "SELECT * FROM makler";
            PreparedStatement pstmt = con.prepareStatement(selectSQL);

            // Führe Anfrage aus
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                EstateAgent estateAgent = EstateAgent.createEstateAgent(
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("login"),
                        rs.getString("password"));
                eas.add(estateAgent);
            }
            //Close all the things!
            rs.close();
            pstmt.close();
        }
        catch (SQLException e1) {
            e1.printStackTrace();
        }
        //return eas;//No need:Array
    }
    public static void delete(EstateAgent ea) {
        try {
            // Hole Verbindung
            Connection con = DB2ConnectionManager.getInstance().getConnection();

            // Erzeuge Anfrage
            String deleteSQL = "DELETE FROM makler WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(deleteSQL);
            pstmt.setString(1, Integer.toString(ea.getId()));
            // Führe Anfrage aus
            int result = pstmt.executeUpdate();

            //TODO: With int result how to know if success or failure?

        }
        catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
}
