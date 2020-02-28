package edu.northeastern.cs5200.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import edu.northeastern.cs5200.DBConnection;
import edu.northeastern.cs5200.models.Privilege;

public class RoleImpl implements RoleDao {
  Connection conn;
  PreparedStatement preparedStatement;
  PrivilegeDao privilegeDao;
  Map<Integer, String> roleIdName = new HashMap<>();

  public RoleImpl() {
    roleIdName.put(1, "owner");
    roleIdName.put(2, "admin");
    roleIdName.put(3, "writer");
    roleIdName.put(4, "editor");
    roleIdName.put(5, "reviewer");
    privilegeDao = new PrivilegeImpl();
  }

  @Override
  public void assignWebsiteRole(int developerId, int websiteId, int roleId) {

    try {
      conn = DBConnection.getConnection();
      String assignWebsiteRole = "insert into website_role values(?,?,?)";
      preparedStatement = conn.prepareStatement(assignWebsiteRole);
      preparedStatement.setInt(1, websiteId);
      preparedStatement.setInt(2, developerId);
      preparedStatement.setString(3, roleIdName.get(roleId));
      preparedStatement.executeUpdate();
      //PrivilegeDao privilegeDao = new PrivilegeImpl();
      switch (roleId){
        case 1:
          privilegeDao.assignWebsitePrivilege(developerId, websiteId, "create");
          privilegeDao.assignWebsitePrivilege(developerId, websiteId, "read");
          privilegeDao.assignWebsitePrivilege(developerId, websiteId, "update");
          privilegeDao.assignWebsitePrivilege(developerId, websiteId, "delete");
          break;

        case 2:
          privilegeDao.assignWebsitePrivilege(developerId, websiteId, "create");
          privilegeDao.assignWebsitePrivilege(developerId, websiteId, "read");
          privilegeDao.assignWebsitePrivilege(developerId, websiteId, "update");
          privilegeDao.assignWebsitePrivilege(developerId, websiteId, "delete");
          break;

        case 3:
          privilegeDao.assignWebsitePrivilege(developerId, websiteId, "create");
          privilegeDao.assignWebsitePrivilege(developerId, websiteId, "read");
          privilegeDao.assignWebsitePrivilege(developerId, websiteId, "update");
          break;

        case 4:
          privilegeDao.assignWebsitePrivilege(developerId, websiteId, "read");
          privilegeDao.assignWebsitePrivilege(developerId, websiteId, "update");
          break;

        case 5:
          privilegeDao.assignWebsitePrivilege(developerId, websiteId, "read");
          break;

        default:
          break;
      }


    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }


  }

  @Override
  public void assignPageRole(int developerId, int pageId, int roleId) {
    try {
      conn = DBConnection.getConnection();
      String assignWebsiteRole = "insert into page_role values(?,?,?)";
      preparedStatement = conn.prepareStatement(assignWebsiteRole);
      preparedStatement.setInt(1, pageId);
      preparedStatement.setInt(2, developerId);
      preparedStatement.setString(3, roleIdName.get(roleId));
      preparedStatement.executeUpdate();

      switch (roleId){
        case 1:
          privilegeDao.assignPagePriviledge(developerId, pageId, "create");
          privilegeDao.assignPagePriviledge(developerId, pageId, "read");
          privilegeDao.assignPagePriviledge(developerId, pageId, "update");
          privilegeDao.assignPagePriviledge(developerId, pageId, "delete");
          break;

        case 2:
          privilegeDao.assignPagePriviledge(developerId, pageId, "create");
          privilegeDao.assignPagePriviledge(developerId, pageId, "read");
          privilegeDao.assignPagePriviledge(developerId, pageId, "update");
          privilegeDao.assignPagePriviledge(developerId, pageId, "delete");
          break;

        case 3:
          privilegeDao.assignPagePriviledge(developerId, pageId, "create");
          privilegeDao.assignPagePriviledge(developerId, pageId, "read");
          privilegeDao.assignPagePriviledge(developerId, pageId, "update");
          break;

        case 4:
          privilegeDao.assignPagePriviledge(developerId, pageId, "read");
          privilegeDao.assignPagePriviledge(developerId, pageId, "update");
          break;

        case 5:
          privilegeDao.assignPagePriviledge(developerId, pageId, "read");
          break;

        default:
          break;
      }


    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }


  }

  @Override
  public void deleteWebsiteRole(int developerId, int websiteId, int roleId) {

    try {
      conn = DBConnection.getConnection();
      preparedStatement = conn.prepareStatement("delete from website_role where website_id = ? " +
              "and developer_id = ? and role = ?");
      preparedStatement.setInt(1,websiteId);
      preparedStatement.setInt(2,developerId);
      preparedStatement.setString(3,roleIdName.get(roleId));
      preparedStatement.executeUpdate();

      preparedStatement = conn.prepareStatement("select * from website_privilege where website_id = ? and developer_id = ?");
      preparedStatement.setInt(1, websiteId);
      preparedStatement.setInt(2,developerId);
      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()){
        privilegeDao.deleteWebsitePriviledge(developerId, websiteId, resultSet.getString("privilege"));
      }

    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  @Override
  public void deletePageRole(int developerId, int pageId, int roleId) {
    try {
      conn = DBConnection.getConnection();
      preparedStatement = conn.prepareStatement("delete from page_role where page_id = ? " +
              "and developer_id = ? and role = ?");
      preparedStatement.setInt(1,pageId);
      preparedStatement.setInt(2,developerId);
      preparedStatement.setString(3, roleIdName.get(roleId));
      preparedStatement.executeUpdate();

      preparedStatement = conn.prepareStatement("select * from page_privilege where page_id = ? and developer_id = ?");
      preparedStatement.setInt(1, pageId);
      preparedStatement.setInt(2,developerId);
      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()){
        privilegeDao.deletePagePriviledge(developerId, pageId, resultSet.getString("privilege"));
      }

    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public int roleByWebsiteAndDeveloper(int developerId, int websiteId) {
    try {
      conn = DBConnection.getConnection();
      preparedStatement = conn.prepareStatement("select role from website_role where website_id = ? and developer_id = ?");
      preparedStatement.setInt(1, websiteId);
      preparedStatement.setInt(2, developerId);
      ResultSet resultSet = preparedStatement.executeQuery();
      if(resultSet.next()){
        switch (resultSet.getString("role")){
          case "owner":
            return 1;
          case "admin":
            return 2;
          case "wirter":
            return 3;
          case "editor":
            return 4;
          case "reviewer":
            return 5;
          default:
            return 0;
        }
      }
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return 0;
  }

  @Override
  public int deleteRolesForPage(int pageId) {
    try {
      conn = DBConnection.getConnection();
      preparedStatement = conn.prepareStatement("delete from page_role where page_id = ?");
      preparedStatement.setInt(1,pageId);
      int x = preparedStatement.executeUpdate();
      //preparedStatement = conn.prepareStatement("delete from page_privilege where page_id = ?");
      PrivilegeDao privilegeDao = new PrivilegeImpl();
      x += privilegeDao.deletePrivilegeForPage(pageId);
      return x;
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return 0;
  }

  @Override
  public int deleteRolesForWebsite(int websiteId) {
    try {
      conn = DBConnection.getConnection();
      preparedStatement = conn.prepareStatement("delete from website_role where website_id = ?");
      preparedStatement.setInt(1,websiteId);
      int x = preparedStatement.executeUpdate();
      PrivilegeDao privilegeDao = new PrivilegeImpl();
      x += privilegeDao.deletePrivilegeForWebsite(websiteId);
      return x;
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return 0;
  }

  @Override
  public int roleByPageAndDeveloper(int developerId, int pageId) {
    try {
      conn = DBConnection.getConnection();
      preparedStatement = conn.prepareStatement("select role from page_role where page_id = ? and developer_id = ?");
      preparedStatement.setInt(1,pageId);
      preparedStatement.setInt(2,developerId);
      ResultSet resultSet = preparedStatement.executeQuery();
      if(resultSet.next()){
        switch (resultSet.getString("role")){
          case "owner":
            return 1;
          case "admin":
            return 2;
          case "writer":
            return 3;
          case "editor":
            return 4;
          case "reviewer":
            return 5;
          default:
            return 0;
        }
      }
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return 0;
  }


}
