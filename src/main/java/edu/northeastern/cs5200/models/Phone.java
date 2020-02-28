package edu.northeastern.cs5200.models;

public class Phone {
  private boolean isPrimary;
  private String phone;
  private int devId;
  private boolean changed;
  private String oldPhone;

  public boolean isChanged() {
    return changed;
  }

  public void setChanged(boolean changed) {
    this.changed = changed;
  }

  public String getOldPhone() {
    return oldPhone;
  }

  public void setOldPhone(String oldPhone) {
    this.oldPhone = oldPhone;
  }



  public int getDevId() {
    return devId;
  }

  public void setDevId(int devId) {
    this.devId = devId;
  }

  public Phone(boolean isPrimary, String phone) {
    this.isPrimary = isPrimary;
    this.phone = phone;
  }

  public Phone() {
  }

  public boolean isPrimary() {
    return isPrimary;
  }

  public void setPrimary(boolean primary) {
    isPrimary = primary;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    if(!(this.getPhone() == null))
    {this.setChanged(true);
      this.setOldPhone(this.getPhone());
    }
    this.phone = phone;
  }


  /*public boolean equals(Phone obj) {
    if(! (obj instanceof Phone))
      return false;
    if(this == obj)
      return true;
    return this.getPhone().equals(obj.getPhone()) && this.isPrimary() == obj.isPrimary();
  }



  public int hashcode() {
    return this.getPhone().hashCode();
  }*/

}
