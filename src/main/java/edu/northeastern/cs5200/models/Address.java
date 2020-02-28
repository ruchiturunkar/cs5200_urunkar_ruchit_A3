package edu.northeastern.cs5200.models;

public class Address {
  private boolean isPrimary;
  private String street1;
  private String street2;
  private String city;
  private String state;
  private String zip;
  private int personId;
  private boolean changed;
  private String oldStreet1;
  private String oldStreet2;

  public boolean getOldIsPrimary() {
    return oldIsPrimary;
  }

  public void setOldIsPrimary(boolean oldIsPrimary) {
    this.oldIsPrimary = oldIsPrimary;
  }

  private boolean oldIsPrimary;

  public boolean isChanged() {
    return changed;
  }

  public void setChanged(boolean changed) {
    this.changed = changed;
  }

  public String getOldStreet1() {
    return oldStreet1;
  }

  public void setOldStreet1(String oldStreet1) {
    this.oldStreet1 = oldStreet1;
  }

  public String getOldStreet2() {
    return oldStreet2;
  }

  public void setOldStreet2(String oldStreet2) {
    this.oldStreet2 = oldStreet2;
  }

  public String getOldCity() {
    return oldCity;
  }

  public void setOldCity(String oldCity) {
    this.oldCity = oldCity;
  }

  public String getOldState() {
    return oldState;
  }

  public void setOldState(String oldState) {
    this.oldState = oldState;
  }

  public String getOldZip() {
    return oldZip;
  }

  public void setOldZip(String oldZip) {
    this.oldZip = oldZip;
  }

  private String oldCity;
  private String oldState;
  private String oldZip;

  public int getPersonId() {
    return personId;
  }

  public void setPersonId(int personId) {
    this.personId = personId;
  }

  public Address(boolean isPrimary, String street1, String street2, String city, String state, String zip) {
    this.isPrimary = isPrimary;
    this.street1 = street1;
    this.street2 = street2;
    this.city = city;
    this.state = state;
    this.zip = zip;
  }

  public Address() {
  }

  public boolean isPrimary() {
    return isPrimary;
  }

  public void setPrimary(boolean primary) {
    oldIsPrimary = isPrimary;
    this.setChanged(true);
    isPrimary = primary;
  }

  public String getStreet1() {
    return street1;
  }

  public void setStreet1(String street1)
  {
    if(this.getStreet1()!=null){
      this.setOldStreet1(this.getStreet1());
      this.setChanged(true);
    }
    this.street1 = street1;
  }

  public String getStreet2() {
    return street2;
  }

  public void setStreet2(String street2) {
    if(this.getStreet2()!=null){
      this.setOldStreet2(this.getStreet2());
      this.setChanged(true);
    }
    this.street2 = street2;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    if(this.getCity()!=null){
      this.setOldCity(this.getCity());
      this.setChanged(true);
    }

    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    if(this.getState()!=null) {
      this.setOldState(this.getState());
      this.setChanged(true);
    }
    this.state = state;
  }

  public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    if(this.getZip()!=null){
      this.setOldZip(this.getZip());
      this.setChanged(true);
    }
    this.zip = zip;
  }
}
