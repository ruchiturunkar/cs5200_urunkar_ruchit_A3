package edu.northeastern.cs5200.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import edu.northeastern.cs5200.DBConnection;
import edu.northeastern.cs5200.models.Page;
import edu.northeastern.cs5200.models.Widget;

public class PageImpl implements PageDao {
  Connection conn;
  PreparedStatement preparedStatement;
  @Override
  public void createPageForWebsite(int websiteId, Page page) {
    String pageToWebsite = "insert into page values(?, ?, ?, ?, ?, ?,?)";
    try {
      conn = DBConnection.getConnection();
      preparedStatement = conn.prepareStatement(pageToWebsite);
      preparedStatement.setInt(1, page.getId());
      preparedStatement.setString(2, page.getTitle());
      preparedStatement.setString(3,page.getDescription());
      preparedStatement.setDate(4, page.getCreated());
      //new java.sql.Date(website.getCreated().getTime())
      preparedStatement.setDate(5, page.getUpdated());
      preparedStatement.setInt(6, page.getViews());
      preparedStatement.setInt(7, websiteId);

      preparedStatement.executeUpdate();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  @Override
  public Collection<Page> findAllPages() {
    Collection<Page> pages = new ArrayList<>();
    try {
      conn = DBConnection.getConnection();
      preparedStatement = conn.prepareStatement("select * from page");
      ResultSet resultSet = preparedStatement.executeQuery();
      while(resultSet.next()){
        Page page = new Page();
        page.setId(resultSet.getInt("id"));
        page.setTitle(resultSet.getString("title"));
        page.setDescription(resultSet.getString("description"));
        page.setCreated(resultSet.getDate("created"));
        page.setUpdated(resultSet.getDate("updated"));
        page.setViews(resultSet.getInt("views"));
        page.setWebsiteId(resultSet.getInt("website_id"));
        pages.add(page);
      }
      return pages;
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return null;
  }

  @Override
  public Page findPageById(int pageId) {
    Page page = null;
    try {
      conn = DBConnection.getConnection();
      preparedStatement = conn.prepareStatement("select * from page where title = ?");
      preparedStatement.setInt(1, pageId);
      ResultSet resultSet = preparedStatement.executeQuery();

      if(resultSet.next()) {
        page = new Page();
        page.setId(resultSet.getInt("id"));
        page.setTitle(resultSet.getString("title"));
        page.setDescription(resultSet.getString("description"));
        page.setCreated(resultSet.getDate("created"));
        page.setUpdated(resultSet.getDate("updated"));
        page.setViews(resultSet.getInt("views"));
        page.setWebsiteId(resultSet.getInt("website_id"));
        return page;
      }
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
      return page;
    }

    return page;
  }

  @Override
  public Page findPageByName(String name) {
    Page page = null;
    try {
      conn = DBConnection.getConnection();
      preparedStatement = conn.prepareStatement("select * from page where title = ?");
      preparedStatement.setString(1,name);
      ResultSet resultSet = preparedStatement.executeQuery();

      if(resultSet.next()) {
        page = new Page();
        page.setId(resultSet.getInt("id"));
        page.setTitle(resultSet.getString("title"));
        page.setDescription(resultSet.getString("description"));
        page.setCreated(resultSet.getDate("created"));
        page.setUpdated(resultSet.getDate("updated"));
        page.setViews(resultSet.getInt("views"));
        page.setWebsiteId(resultSet.getInt("website_id"));
        return page;
      }
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
      return page;
    }

    return page;
  }


  @Override
  public Collection<Page> findPagesForWebsite(int websiteId) {
    Collection<Page> pages = new ArrayList<>();
    try {
      conn = DBConnection.getConnection();
      preparedStatement = conn.prepareStatement("select * from page where website_id = ?");
      preparedStatement.setInt(1, websiteId);
      ResultSet resultSet = preparedStatement.executeQuery();
      while(resultSet.next()){
        Page p = new Page();
        p.setId(resultSet.getInt("id"));
        p.setTitle(resultSet.getString("title"));
        p.setDescription(resultSet.getString("description"));
        p.setCreated(resultSet.getDate("created"));
        p.setUpdated(resultSet.getDate("updated"));
        p.setViews(resultSet.getInt("views"));
        p.setWebsiteId(resultSet.getInt("website_id"));
        pages.add(p);
      }
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return pages;
  }

  @Override
  public int updatePage(int pageId, Page page) {
    try {
      conn = DBConnection.getConnection();
      preparedStatement = conn.prepareStatement("update page set title=? , description = ?, created = ?," +
              "updated = ?, views = ?, website_id=? where id=?");
      preparedStatement.setString(1,page.getTitle());
      preparedStatement.setString(2,page.getDescription());
      preparedStatement.setDate(3,page.getCreated());
      preparedStatement.setDate(4,page.getUpdated());
      preparedStatement.setInt(5, page.getViews());
      preparedStatement.setInt(6,page.getWebsiteId());
      preparedStatement.setInt(7,pageId);
      preparedStatement.executeUpdate();

    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }


    return 0;
  }

  @Override
  public int deletePage(int pageId) {
    try {
      conn = DBConnection.getConnection();
      preparedStatement = conn.prepareStatement("delete from page where id = ?");
      WidgetDao widgetDao = new WidgetImpl();
      Collection<Widget> widgets = widgetDao.findWidgetsForPage(pageId);
      int x = 0;
      for(Widget w :widgets){
        x += widgetDao.deleteWidget(w.getId());
      }
      preparedStatement.setInt(1, pageId);
      x = preparedStatement.executeUpdate();
      RoleDao roleDao = new RoleImpl();
      return x + roleDao.deleteRolesForPage(pageId);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return 0;
  }
}
