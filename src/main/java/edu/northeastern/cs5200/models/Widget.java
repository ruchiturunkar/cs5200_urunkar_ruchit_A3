package edu.northeastern.cs5200.models;

import java.math.BigInteger;

public class Widget {
  private int id;
  private String name;
  private int width;
  private int height;
  private String text;
  private String cssStyle;
  private String cssClass;
  private int size;
  private String src;
  private String url;
  private boolean expandable;
  private boolean sharable;

  public String getSrc() {
    return src;
  }

  public void setSrc(String src) {
    this.src = src;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public boolean isExpandable() {
    return expandable;
  }

  public void setExpandable(boolean expandable) {
    this.expandable = expandable;
  }

  public boolean isSharable() {
    return sharable;
  }

  public void setSharable(boolean sharable) {
    this.sharable = sharable;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  private int order;
  private WidgetType type;
  private int pageId;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public int getOrder() {
    return order;
  }

  public void setOrder(int order) {
    this.order = order;
  }

  public WidgetType getType() {
    return type;
  }

  public void setType(WidgetType type) {
    this.type = type;
  }

  public int getPageId() {
    return pageId;
  }

  public void setPageId(int pageId) {
    this.pageId = pageId;
  }

  public Widget(int id, String name, int width, int height, String cssStyle, String cssClass, String text, int order) {
    this.id = id;
    this.name = name;
    this.width = width;
    this.height = height;
    this.cssStyle = cssStyle;
    this.cssClass = cssClass;
    this.text = text;
    this.order = order;
  }

  public String getCssStyle() {
    return cssStyle;
  }

  public void setCssStyle(String cssStyle) {
    this.cssStyle = cssStyle;
  }

  public String getCssClass() {
    return cssClass;
  }

  public void setCssClass(String cssClass) {
    this.cssClass = cssClass;
  }

  public Widget(int id, String name, int width, int height, String cssStyle, String cssClass,
                String text, int order, int size, String src, String url, boolean sharable,
                boolean expandable, WidgetType w) {
    this.id = id;
    this.name = name;
    this.width = width;
    this.height = height;
    this.cssStyle = cssStyle;
    this.cssClass = cssClass;
    this.text = text;
    this.order = order;
    this.size = size;
    this.src = src;
    this.url = url;
    this.expandable = expandable;
    this.sharable = sharable;
    this.type = w;
  }


  public Widget(){}
}
