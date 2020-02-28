package edu.northeastern.cs5200.models;

public class YouTubeWidget extends Widget {
  String url;
  boolean sharable;
  boolean expandable;

  public YouTubeWidget() {}

  public YouTubeWidget(int id, String name, int width, int height, String text, int order,
                       WidgetType w, String url, boolean sharable, boolean expandable) {
    //super(id, name, width, height, text, order);
    this.url = url;
    this.sharable = sharable;
    this.expandable = expandable;
  }

}
