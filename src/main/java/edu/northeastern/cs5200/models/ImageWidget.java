package edu.northeastern.cs5200.models;

public class ImageWidget extends Widget {
  String src;
  public ImageWidget() {}

  public ImageWidget(String src, int id, String name, int width, int height, String text, int order) {
    //super(id, name, width, height, text, order);
    this.src = src;
  }
}
