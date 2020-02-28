package edu.northeastern.cs5200.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Website {
  private int id;
  private String name;
  private String description;
  private Date created;
  private Date updated;
  private int visited;
  private int developerId;

  public int getDeveloperId() {
    return developerId;
  }

  public void setDeveloperId(int developerId) {
    this.developerId = developerId;
  }


  public Website(int id, String name, String description, Date created, Date updated, int visited) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.created = created;
    this.updated = updated;
    this.visited = visited;
  }

  public Website() {
  }

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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public Date getUpdated() {
    return updated;
  }

  public void setUpdated(Date updated) {
    this.updated = updated;
  }

  public int getVisited() {
    return visited;
  }

  public void setVisited(int visited) {
    this.visited = visited;
  }
}
