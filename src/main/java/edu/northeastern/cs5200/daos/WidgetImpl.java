package edu.northeastern.cs5200.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import java.sql.Connection;
import edu.northeastern.cs5200.DBConnection;
import edu.northeastern.cs5200.models.Widget;
import edu.northeastern.cs5200.models.WidgetType;

public class WidgetImpl implements WidgetDao {
  Connection conn;
  PreparedStatement preparedStatement;
  @Override
  public void createWidgetForPage(int pageId, Widget widget) {

    String createWidgetforPage = "";
    try {
      conn = DBConnection.getConnection();

    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    switch (widget.getType().name()) {
      case "HEADING":
        createWidgetforPage = "insert into widget(id, name, dtype, text, size, widget_order, page_id)" +
                 "values (?,?,?,?,?,?,?)";

        try {
          preparedStatement = conn.prepareStatement(createWidgetforPage);
          preparedStatement.setInt(1, widget.getId());
          preparedStatement.setString(2, widget.getName());
          preparedStatement.setString(3, widget.getType().toString());
          preparedStatement.setString(4, widget.getText());
          preparedStatement.setInt(5, widget.getSize());
          preparedStatement.setInt(6, widget.getOrder());
          preparedStatement.setInt(7, pageId);
          break;
        } catch (SQLException e) {
          e.printStackTrace();
        }

      case "HTML":
        createWidgetforPage = "insert into widget(id, name, dtype, text, size, widget_order, page_id)" +
                "values (?,?,?,?,?,?,?)";

        try {
          preparedStatement = conn.prepareStatement(createWidgetforPage);
          preparedStatement.setInt(1, widget.getId());
          preparedStatement.setString(2, widget.getName());
          preparedStatement.setString(3, widget.getType().toString());
          preparedStatement.setString(4, widget.getText());
          preparedStatement.setInt(5, widget.getSize());
          preparedStatement.setInt(6, widget.getOrder());
          preparedStatement.setInt(7, pageId);
          break;
        } catch (SQLException e) {
          e.printStackTrace();
        }

      case "IMAGE":
        createWidgetforPage = "insert into widget(id, name, dtype, width, height, src, widget_order, page_id)" +
                "values (?,?,?,?,?,?,?,?)";

        try {
          preparedStatement = conn.prepareStatement(createWidgetforPage);
          preparedStatement.setInt(1, widget.getId());
          preparedStatement.setString(2, widget.getName());
          preparedStatement.setString(3,widget.getType().toString());
          preparedStatement.setInt(4, widget.getWidth());
          preparedStatement.setInt(5, widget.getHeight());
          preparedStatement.setString(6, widget.getSrc());
          preparedStatement.setInt(7, widget.getOrder());
          preparedStatement.setInt(8, pageId);
          break;
        } catch (SQLException e) {
          e.printStackTrace();
        }

      case "YOUTUBE":
        createWidgetforPage = "insert into widget(id, name, dtype, width, height, url, sharable, " +
                "expandable, widget_order, page_id)" +
                "values (?,?,?,?,?,?,?,?,?, ?)";

        try {
          preparedStatement = conn.prepareStatement(createWidgetforPage);
          preparedStatement.setInt(1, widget.getId());
          preparedStatement.setString(2, widget.getName());
          preparedStatement.setString(3, widget.getType().toString());
          preparedStatement.setInt(4, widget.getWidth());
          preparedStatement.setInt(5, widget.getHeight());
          preparedStatement.setString(6, widget.getUrl());
          preparedStatement.setBoolean(7, widget.isSharable());
          preparedStatement.setBoolean(8, widget.isExpandable());
          preparedStatement.setInt(9, widget.getOrder());
          preparedStatement.setInt(10, pageId);
          break;
        } catch (SQLException e) {
          e.printStackTrace();
        }

      default:
        break;

    }

    try {
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }


  }

  @Override
  public Collection<Widget> findAllWidgets() {
    Collection<Widget> widgets = new ArrayList<>();

    try {
      conn = DBConnection.getConnection();
      preparedStatement = conn.prepareStatement("select * from widget");
      ResultSet resultSet = preparedStatement.executeQuery();
      Widget w = new Widget();
      while(resultSet.next()) {
        w.setId(resultSet.getInt("id"));
        w.setName(resultSet.getString("name"));
        w.setWidth(resultSet.getInt("width"));
        w.setHeight(resultSet.getInt("height"));
        w.setCssClass(resultSet.getString("css_Class"));
        w.setCssStyle(resultSet.getString("css_Style"));
        w.setText(resultSet.getString("text"));
        w.setUrl(resultSet.getString("url"));
        w.setSharable(resultSet.getBoolean("sharable"));
        w.setSharable(resultSet.getBoolean("expandable"));
        w.setSrc(resultSet.getString("src"));
        w.setSize(resultSet.getInt("size"));
        w.setOrder(resultSet.getInt("widget_order"));
        w.setPageId(resultSet.getInt("page_id"));
        switch (resultSet.getString("dtype")) {
          case "YOUTUBE":
            w.setType(WidgetType.YOUTUBE);
            break;
          case "IMAGE":
            w.setType(WidgetType.IMAGE);
            break;
          case "HEADING":
            w.setType(WidgetType.HEADING);
            break;
          case "HTML":
            w.setType(WidgetType.HTML);
            break;
          default:
            break;
        }
      widgets.add(w);
      }
      return widgets;
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public Widget findWidgetById(int widgetId) {

    try {
      conn = DBConnection.getConnection();
      preparedStatement = conn.prepareStatement("select * from widget where id=?");
      preparedStatement.setInt(1,widgetId);
      ResultSet resultSet = preparedStatement.executeQuery();
      Widget w = new Widget();
      if(resultSet.next()) {
        w.setId(widgetId);
        w.setName(resultSet.getString("name"));
        w.setWidth(resultSet.getInt("width"));
        w.setHeight(resultSet.getInt("height"));
        w.setCssClass(resultSet.getString("css_Class"));
        w.setCssStyle(resultSet.getString("css_Style"));
        w.setText(resultSet.getString("text"));
        w.setUrl(resultSet.getString("url"));
        w.setSharable(resultSet.getBoolean("sharable"));
        w.setSharable(resultSet.getBoolean("expandable"));
        w.setSrc(resultSet.getString("src"));
        w.setSize(resultSet.getInt("size"));
        w.setOrder(resultSet.getInt("widget_order"));
        w.setPageId(resultSet.getInt("page_id"));
        switch (resultSet.getString("dtype")){
          case "YOUTUBE":
            w.setType(WidgetType.YOUTUBE);
            break;
          case "IMAGE":
            w.setType(WidgetType.IMAGE);
            break;
          case "HEADING":
            w.setType(WidgetType.HEADING);
            break;
          case "HTML":
            w.setType(WidgetType.HTML);
            break;
          default:
            break;
        }
        return w;
      }

    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public Widget findWidgetByName(String name) {

    try {
      conn = DBConnection.getConnection();
      preparedStatement = conn.prepareStatement("select * from widget where name=?");
      preparedStatement.setString(1,name);
      ResultSet resultSet = preparedStatement.executeQuery();
      Widget w = new Widget();
      if(resultSet.next()) {
        w.setId(resultSet.getInt("id"));
        w.setName(resultSet.getString("name"));
        w.setWidth(resultSet.getInt("width"));
        w.setHeight(resultSet.getInt("height"));
        w.setCssClass(resultSet.getString("css_Class"));
        w.setCssStyle(resultSet.getString("css_Style"));
        w.setText(resultSet.getString("text"));
        w.setUrl(resultSet.getString("url"));
        w.setSharable(resultSet.getBoolean("sharable"));
        w.setSharable(resultSet.getBoolean("expandable"));
        w.setSrc(resultSet.getString("src"));
        w.setSize(resultSet.getInt("size"));
        w.setOrder(resultSet.getInt("widget_order"));
        w.setPageId(resultSet.getInt("page_id"));
        switch (resultSet.getString("dtype")){
          case "YOUTUBE":
            w.setType(WidgetType.YOUTUBE);
            break;
          case "IMAGE":
            w.setType(WidgetType.IMAGE);
            break;
          case "HEADING":
            w.setType(WidgetType.HEADING);
            break;
          case "HTML":
            w.setType(WidgetType.HTML);
            break;
          default:
            break;
        }
        return w;
      }

    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public Collection<Widget> findWidgetsForPage(int pageId) {

    try {
      conn = DBConnection.getConnection();
      preparedStatement = conn.prepareStatement("select * from widget where page_id = ?");
      preparedStatement.setInt(1,pageId);
      ResultSet resultSet = preparedStatement.executeQuery();
      Collection<Widget> widgets = new ArrayList<>();
      while(resultSet.next()){
        Widget w = new Widget();
        w.setId(resultSet.getInt("id"));
        w.setName(resultSet.getString("name"));
        w.setWidth(resultSet.getInt("width"));
        w.setHeight(resultSet.getInt("height"));
        w.setCssClass(resultSet.getString("css_Class"));
        w.setCssStyle(resultSet.getString("css_Style"));
        w.setText(resultSet.getString("text"));
        w.setUrl(resultSet.getString("url"));
        w.setSharable(resultSet.getBoolean("sharable"));
        w.setSharable(resultSet.getBoolean("expandable"));
        w.setSrc(resultSet.getString("src"));
        w.setSize(resultSet.getInt("size"));
        w.setOrder(resultSet.getInt("widget_order"));
        w.setPageId(resultSet.getInt("page_id"));
        switch (resultSet.getString("dtype")){
          case "YOUTUBE":
            w.setType(WidgetType.YOUTUBE);
            break;
          case "IMAGE":
            w.setType(WidgetType.IMAGE);
            break;
          case "HEADING":
            w.setType(WidgetType.HEADING);
            break;
          case "HTML":
            w.setType(WidgetType.HTML);
            break;
          default:
            break;
        }
        widgets.add(w);
      }
      return widgets;
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return null;
  }

  @Override
  public int updateWidget(int widgetId, Widget widget) {
    try {
      conn = DBConnection.getConnection();
      preparedStatement = conn.prepareStatement("update widget set name = ?, dtype = ?, " +
              "width = ?, height = ?, text = ?, url = ?, sharable = ?, expandable = ?, widget_order = ?, " +
              "page_id = ?, css_Style = ?, css_class = ? where id = ?");
      preparedStatement.setString(1, widget.getName());
      preparedStatement.setString(2, widget.getType().toString());
      preparedStatement.setInt(3, widget.getWidth());
      preparedStatement.setInt(4, widget.getHeight());
      preparedStatement.setString(5, widget.getText());
      preparedStatement.setString(6, widget.getUrl());
      preparedStatement.setBoolean(7, widget.isSharable());
      preparedStatement.setBoolean(8, widget.isExpandable());
      preparedStatement.setInt(9, widget.getOrder());
      preparedStatement.setInt(10, widget.getPageId());
      preparedStatement.setString(11, widget.getCssStyle());
      preparedStatement.setString(12, widget.getCssClass());
      preparedStatement.setInt(13, widgetId);
      preparedStatement.executeUpdate();
      return 1;
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
      return 0;
    }
    return 0;
  }

  @Override
  public int deleteWidget(int widgetId) {
    try {
      conn = DBConnection.getConnection();
      preparedStatement = conn.prepareStatement("delete from widget where id = ?");
      preparedStatement.setInt(1,widgetId);
      preparedStatement.executeUpdate();
      return 1;
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
      return 0;
    }
    return 0;
  }
}
