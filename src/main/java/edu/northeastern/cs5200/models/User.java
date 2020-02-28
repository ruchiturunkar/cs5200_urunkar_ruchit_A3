package edu.northeastern.cs5200.models;

import java.security.PublicKey;

public class User extends Person {
  boolean userAgreement;

  public User(boolean userAgreement, int id, String firstName, String lastName) {
    super(id, firstName, lastName);
    this.userAgreement = userAgreement;
  }

  public User() {}

  public boolean isUserAgreement() {
    return userAgreement;
  }

  public void setUserAgreement(boolean userAgreement) {
    this.userAgreement = userAgreement;
  }

}
