package edu.northeastern.cs5200.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import edu.northeastern.cs5200.DBConnection;
import edu.northeastern.cs5200.models.Page;
import edu.northeastern.cs5200.models.Website;

public class WebsiteImpl implements WebsiteDao {
  Connection conn;
  PreparedStatement preparedStatement;

  @Override
  public void createWebsiteForDeveloper(int developerId, Website website) {
    String websiteToDeveloper = "insert into website values(?, ?, ?, ?, ?, ?,?)";

    try {
      conn = DBConnection.getConnection();
      preparedStatement = conn.prepareStatement(websiteToDeveloper);
      preparedStatement.setInt(1, website.getId());
      preparedStatement.setString(2, website.getName());
      preparedStatement.setString(3,website.getDescription());
      preparedStatement.setDate(4, website.getCreated());
      //new java.sql.Date(website.getCreated().getTime())
      preparedStatement.setDate(5, website.getUpdated());
      preparedStatement.setInt(6,website.getVisited());
      preparedStatement.setInt(7,developerId);

      preparedStatement.executeUpdate();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  @Override
  public Collection<Website> findAllWebsites() {
    Collection<Website> websites= new ArrayList<>();
    try {
      conn = DBConnection.getConnection();
      preparedStatement = conn.prepareStatement("select * from website");
      ResultSet resultSet = preparedStatement.executeQuery();
      while(resultSet.next())
      {
        Website w = new Website();
        w.setId(resultSet.getInt("id"));
        w.setName(resultSet.getString("name"));
        w.setDescription(resultSet.getString("description"));
        w.setCreated(resultSet.getDate("created"));
        w.setUpdated(resultSet.getDate("updated"));
        w.setVisited(resultSet.getInt("visits"));
        w.setDeveloperId(resultSet.getInt("developerid"));
        websites.add(w);
      }
      return websites;
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return null;
  }

  @Override
  public Collection<Website> findWebsitesForDeveloper(int developerId) {
    Collection<Website> websites= new ArrayList<>();
    try {
      conn = DBConnection.getConnection();
      preparedStatement = conn.prepareStatement("select * from website where developerid = ?");
      preparedStatement.setInt(1,developerId);
      ResultSet resultSet = preparedStatement.executeQuery();
      while(resultSet.next())
      {
        Website w = new Website();
        w.setId(resultSet.getInt("id"));
        w.setName(resultSet.getString("name"));
        w.setDescription(resultSet.getString("description"));
        w.setCreated(resultSet.getDate("created"));
        w.setUpdated(resultSet.getDate("updated"));
        w.setVisited(resultSet.getInt("visits"));
        w.setDeveloperId(developerId);
        websites.add(w);
      }
      return websites;
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return null;
  }

  @Override
  public Website findWebsiteById(int websiteId) {
    try {
      conn = DBConnection.getConnection();
      preparedStatement = conn.prepareStatement("select * from website where id = ?");
      preparedStatement.setInt(1, websiteId);
      ResultSet resultSet = preparedStatement.executeQuery();
      Website w = new Website();
      if(resultSet.next()) {
        w.setId(websiteId);
        w.setName(resultSet.getString("name"));
        w.setDescription(resultSet.getString("description"));
        w.setCreated(resultSet.getDate("created"));
        w.setUpdated(resultSet.getDate("updated"));
        w.setVisited(resultSet.getInt("visits"));
        w.setDeveloperId(resultSet.getInt("developerid"));
      }
      return w;
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public int updateWebsite(int websiteId, Website website) {
    try {
      conn = DBConnection.getConnection();
      preparedStatement = conn.prepareStatement("update website set name = ? , description = ?, " +
              "created = ?, updated=?, visits = ?, developerid=? where id = ?");
      preparedStatement.setString(1, website.getName());
      preparedStatement.setString(2, website.getDescription());
      preparedStatement.setDate(3, website.getCreated());
      preparedStatement.setDate(4, website.getUpdated());
      preparedStatement.setInt(5, website.getVisited());
      preparedStatement.setInt(6, website.getDeveloperId());
      preparedStatement.setInt(7, websiteId);
      return preparedStatement.executeUpdate();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return 0;
  }

  @Override
  public int deleteWebsite(int websiteId) {
    try {
      conn = DBConnection.getConnection();
      preparedStatement = conn.prepareStatement("delete from website where id = ?");
      preparedStatement.setInt(1,websiteId);
      PageDao pageDao = new PageImpl();
      Collection<Page> pagesToDelete = pageDao.findPagesForWebsite(websiteId);
      int x = 0;
      for(Page pg : pagesToDelete) {
        x += pageDao.deletePage(pg.getId());
      }
      RoleDao roleDao = new RoleImpl();
      x += roleDao.deleteRolesForWebsite(websiteId);
      return x + preparedStatement.executeUpdate();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }


    return 0;


  }

  @Override
  public int findWebsiteIdByName(String name) {
    try {
      conn = DBConnection.getConnection();
      preparedStatement = conn.prepareStatement("select id from website where name=?");
      preparedStatement.setString(1, name);
      ResultSet resultSet = preparedStatement.executeQuery();
      if(resultSet.next())
        return resultSet.getInt("id");

    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return -1;
  }
}
