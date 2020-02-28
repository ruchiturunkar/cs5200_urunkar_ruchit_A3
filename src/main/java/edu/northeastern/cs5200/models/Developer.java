package edu.northeastern.cs5200.models;

import java.sql.Date;
import java.util.List;;

public class Developer extends Person {
private String developerKey;

  public Developer(String developerKey, int id, String firstName, String lastName) {
    super(id,firstName,lastName);
    this.developerKey = developerKey;
  }

  public Developer (String developerKey, int id, String firstName, String lastName, String userName, String password,
  String email, Date dob) {
    super(id, firstName, lastName, userName, password, email, dob);
    this.developerKey = developerKey;
  }

  public Developer (String developerKey, int id, String firstName, String lastName, String userName, String password,
                    String email, Date dob, List<Address> address, List<Phone> phone) {
    super(id, firstName, lastName, userName, password, email, dob, address, phone);
    this.developerKey = developerKey;
  }

  public Developer() {}

  public String getDeveloperKey() {
    return developerKey;
  }

  public void setDeveloperKey(String developerKey) {
    this.developerKey = developerKey;
  }


}
