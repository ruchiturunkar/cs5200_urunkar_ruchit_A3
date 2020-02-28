package edu.northeastern.cs5200.models;

public class HtmlWidget extends Widget {

  String html;
  public HtmlWidget() {}
  public HtmlWidget(String html, int id, String name, int width, int height, String text, int order) {
    //super(id, name, width, height, text, order);
    this.html = html;
  }
}
