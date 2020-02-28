package edu.northeastern.cs5200;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

  private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
  private static final String URL = "jdbc:mysql://cs5200-spring2020-urunkar.c8unn48xovms.us-east-2.rds.amazonaws.com/cs5200_A3";
  private static final String USER = "uradmin";
  private static final String PASSWORD = "admin3105";
  private static 	java.sql.Connection dbConnection = null;

  public static Connection getConnection() throws ClassNotFoundException, SQLException {
    Class.forName(DRIVER);
    if (dbConnection == null) {
      try {
        dbConnection = DriverManager.getConnection(URL, USER, PASSWORD);
      } catch (SQLException e) {
        e.printStackTrace();
      }
      return dbConnection;
    } else {
      return dbConnection;
    }
  }


  public static void closeConnection() {
    try {
      if (dbConnection != null) {
        dbConnection.close();
        dbConnection = null; }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}

