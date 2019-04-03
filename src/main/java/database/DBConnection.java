package database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	private String DBPath = "jdbc:sqlite:database/tweets.db";
    private Connection connection = null;
    private Statement statement = null;
 
 
    public void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(DBPath);
            statement = connection.createStatement();
            System.out.println("Connexion a " + DBPath + " avec succès");
        } catch (ClassNotFoundException notFoundException) {
            notFoundException.printStackTrace();
            System.out.println("Erreur de connexion 1");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.out.println("Erreur de connexion 2");
        }
    }
    
    public boolean checkForTableExists(String table) throws SQLException{
        DatabaseMetaData dbm = this.connection.getMetaData();
        ResultSet rs = dbm.getTables(null, null, table, null);
        if (rs.next()) {
          return true;
        } else {
          return false;
        }
    }
    
    public void createNewTable() {
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS tweets (\n"
                + "	date text NOT NULL,\n"
                + "	trend text NOT NULL\n"
                + ");";
        try {
        	Statement stmt = this.connection.createStatement();
            stmt.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void insertTrend(String date, String trend) {
    	String sql = "INSERT INTO tweets (date, trend) VALUES (?, ?)";
    	try {
    		PreparedStatement stmt = this.connection.prepareStatement(sql);
    		stmt.setString (1, date);
    		stmt.setString (2, trend);
    		stmt.execute();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
 
    public void close() {
        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
