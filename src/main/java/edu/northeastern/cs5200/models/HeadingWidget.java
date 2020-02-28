package edu.northeastern.cs5200.models;

public class HeadingWidget extends Widget {
  private Integer size;
  public HeadingWidget(){}

  public HeadingWidget(Integer size, int id, String name, int width, int height, String text, int order) {
    //super(id, name, width, height, text, order);
    this.size = size;
  }
}
