package edu.northeastern.cs5200.models;

import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;

public class Person {
  private int id;
  private String firstName;
  private String lastName;
  private String userName;
  private String password;
  private String email;
  private Date dob;
  List<Address> address = new ArrayList<>();
  List<Phone> phone = new ArrayList<>();


  public Person(int id, String firstName, String lastName) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.userName = firstName;
    this.password = firstName;
    this.email = firstName + "@example.com";
  }

  public Person(int id, String firstName, String lastName, String userName, String password,
                String email, Date dob) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.userName = userName;
    this.password = password;
    this.email = email;
    this.dob = dob;

  }

  public Person(int id, String firstName, String lastName, String userName, String password,
                String email, Date dob, List<Address> address, List<Phone> phone) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.userName = userName;
    this.password = password;
    this.email = email;
    this.dob = dob;
    this.address = address;
    this.phone = phone;
  }

  public Person() {}

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<Address> getAddress() {
    return address;
  }

  public void setAddress(List<Address> address) {
    this.address = address;
  }

  public List<Phone> getPhone() {
    return phone;
  }

  public void setPhone(List<Phone> phone) {
    this.phone = phone;
  }


  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Date getDob() {
    return dob;
  }

  public void setDob(Date dob) {
    this.dob = dob;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}
