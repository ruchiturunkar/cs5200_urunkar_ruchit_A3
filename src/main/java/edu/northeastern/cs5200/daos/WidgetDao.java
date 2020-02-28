package edu.northeastern.cs5200.daos;

import edu.northeastern.cs5200.models.Widget;
import java.util.Collection;

public interface WidgetDao {
  void createWidgetForPage(int pageId, Widget widget);
  Collection<Widget> findAllWidgets();
  Widget findWidgetById(int widgetId);
  Widget findWidgetByName(String name);
  Collection<Widget> findWidgetsForPage(int pageId);
  int updateWidget(int widgetId, Widget widget);
  int deleteWidget(int widgetId);
}
