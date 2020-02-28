package edu.northeastern.cs5200.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import edu.northeastern.cs5200.DBConnection;

public class PrivilegeImpl implements PrivilegeDao {
  Connection conn;
  PreparedStatement preparedStatement;
  @Override
  public void assignWebsitePrivilege(int developerId, int websiteId, String priviledge) {
    try {
      conn = DBConnection.getConnection();
      String assignWebSitePrivilege = "insert into website_privilege values(?,?,?)";
      preparedStatement = conn.prepareStatement(assignWebSitePrivilege);
      preparedStatement.setInt(1,websiteId);
      preparedStatement.setInt(2,developerId);
      preparedStatement.setString(3,priviledge);
      preparedStatement.executeUpdate();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  @Override
  public void assignPagePriviledge(int developerId, int pageId, String priviledge) {
    try {
      conn = DBConnection.getConnection();
      String assignPagePrivilege = "insert into page_privilege values(?,?,?)";
      preparedStatement = conn.prepareStatement(assignPagePrivilege);
      preparedStatement.setInt(1,pageId);
      preparedStatement.setInt(2,developerId);
      preparedStatement.setString(3,priviledge);
      preparedStatement.executeUpdate();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void deleteWebsitePriviledge(int developerId, int websiteId, String priviledge) {


  }

  @Override
  public void deletePagePriviledge(int developerId, int pageId, String priviledge) {
    try {
      conn = DBConnection.getConnection();
      preparedStatement = conn.prepareStatement("delete from page_privilege where page_id = ? and developer_id = ? and privilege = ?");
      preparedStatement.setInt(1,pageId);
      preparedStatement.setInt(2,developerId);
      preparedStatement.setString(3,priviledge);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  @Override
  public int deletePrivilegeForPage(int pageId) {
    try {
      conn = DBConnection.getConnection();
      preparedStatement = conn.prepareStatement("delete from page_privilege where page_id = ?");
      preparedStatement.setInt(1, pageId);
      return preparedStatement.executeUpdate();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
      return 0;
    }
    return 0;
  }

  @Override
  public int deletePrivilegeForWebsite(int websiteId) {
    try {
      conn = DBConnection.getConnection();
      preparedStatement = conn.prepareStatement("delete from website_privilege where website_id = ?");
      preparedStatement.setInt(1, websiteId);
      return preparedStatement.executeUpdate();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
      return 0;
    }
    return 0;
  }
}
