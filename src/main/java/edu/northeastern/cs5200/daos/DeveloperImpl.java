package edu.northeastern.cs5200.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import edu.northeastern.cs5200.DBConnection;
import edu.northeastern.cs5200.models.Address;
import edu.northeastern.cs5200.models.Developer;
import edu.northeastern.cs5200.models.Phone;

public class DeveloperImpl implements DeveloperDao {
  Connection conn;
  PreparedStatement preparedStatement;

  @Override
  public void createDeveloper(Developer developer) {

    int id = developer.getId();
    String key = developer.getDeveloperKey();
    String firstName = developer.getFirstName();
    String lastName = developer.getLastName();
    String userName = developer.getUserName();
    String password = developer.getPassword();
    Date dob = developer.getDob();
    String email = developer.getEmail();

    String devEntry = "insert into developer values(?, ?)";
    String personEntry = "insert into person(id,firstName,lastName,username,password,email,dob) values(?, ?, ?, ?, ?, ?, ?)";

    String addressEntry = "insert into address values(?,?,?,?,?,?,?)";
    try {
      conn = DBConnection.getConnection();
      preparedStatement = conn.prepareStatement(personEntry);
      preparedStatement.setInt(1, id);
      preparedStatement.setString(2, firstName);
      preparedStatement.setString(3, lastName);
      preparedStatement.setString(4, userName);
      preparedStatement.setString(5, password);
      preparedStatement.setString(6, email);
      preparedStatement.setDate(7, new java.sql.Date(dob.getTime()));
      preparedStatement.executeUpdate();

      preparedStatement = conn.prepareStatement(devEntry);
      preparedStatement.setInt(2, id);
      preparedStatement.setString(1, key);
      preparedStatement.executeUpdate();


    } catch (SQLException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }


  }

  @Override
  public Collection<Developer> findAllDevelopers() {

    try {
      conn = DBConnection.getConnection();
      preparedStatement = conn.prepareStatement("select * from developer");
      ResultSet resultSet = preparedStatement.executeQuery();
      Collection<Developer> developers = new ArrayList<>();
      while (resultSet.next()) {
        Developer d = new Developer();
        d.setId(resultSet.getInt("id"));
        d.setDeveloperKey(resultSet.getString("developer_key"));
        d = this.findOtherDetails(d);
        /*preparedStatement = conn.prepareStatement("select * from person where id = ?");
        preparedStatement.setInt(1, d.getId());
        ResultSet resultSet1 = preparedStatement.executeQuery();
        if (resultSet.next()) {
          d.setFirstName(resultSet.getString("firstName"));
          d.setLastName(resultSet.getString("lastName"));
          d.setUserName(resultSet.getString("username"));
          d.setPassword(resultSet.getString("password"));
          //d.setPhone((List)resultSet.getArray("phone"));
          //d.setAddress((List)resultSet.getArray("address"));
          d.setEmail(resultSet.getString("email"));
          d.setDob(resultSet.getDate("dob"));
          preparedStatement = conn.prepareStatement("select * from address where id = ?");
          preparedStatement.setInt(1, d.getId());
          ResultSet resultSet2 = preparedStatement.executeQuery();
          List<Address> addressList = new ArrayList<>();
          while (resultSet2.next()) {
            Address a = new Address();
            a.setStreet1(resultSet2.getString("street1"));
            a.setStreet2(resultSet2.getString("street2"));
            a.setState(resultSet2.getString("state"));
            a.setCity(resultSet2.getString("city"));
            a.setZip(resultSet2.getString("zip"));
            a.setPrimary(resultSet2.getBoolean("is_primary"));
            a.setPersonId(d.getId());
            addressList.add(a);
          }
          d.setAddress(addressList);
          preparedStatement = conn.prepareStatement("select * from phone where id = ?");
          preparedStatement.setInt(1, d.getId());
          resultSet2 = preparedStatement.executeQuery();
          List<Phone> phoneList = new ArrayList<>();

          while (resultSet2.next()) {
            Phone p = new Phone();
            p.setPrimary(resultSet2.getBoolean("is_primary"));
            p.setDevId(d.getId());
            p.setPhone(resultSet.getString("phone"));
            phoneList.add(p);
          }
          d.setPhone(phoneList);
        }*/
        developers.add(d);
      }
      return developers;
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
    return null;
  }

  @Override
  public Developer findDeveloperById(int developerId) {
    try {
      conn = DBConnection.getConnection();
      preparedStatement = conn.prepareStatement("select * from developer where id = ?");
      ResultSet resultSet = preparedStatement.executeQuery();
      Developer d = new Developer();
      if (resultSet.next()) {
        d.setId(developerId);
        d.setDeveloperKey(resultSet.getString("developer_key"));
        d = findOtherDetails(d);
      }
      return d;
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }

    return null;
  }

  @Override
  public Developer findDeveloperByUsername(String username) {
    Developer d = new Developer();

    try {
      conn = DBConnection.getConnection();
      preparedStatement = conn.prepareStatement("select id from person where username = ?");
      preparedStatement.setString(1,username);
      ResultSet resultSet = preparedStatement.executeQuery();
      if(resultSet.next()){
        d.setId(resultSet.getInt("id"));
      }
      preparedStatement = conn.prepareStatement("select * from developer where id = ?");
      preparedStatement.setInt(1,d.getId());
      resultSet = preparedStatement.executeQuery();
      if(resultSet.next()){
        d.setDeveloperKey(resultSet.getString("developer_key"));
      }
      d = findOtherDetails(d);
      return d;
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    /*try {
      conn = DBConnection.getConnection();
      String getPerson = "select * from person where username=?";


      preparedStatement = conn.prepareStatement(getPerson);
      preparedStatement.setString(1, username);
      ResultSet resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        d.setFirstName(resultSet.getString("firstName"));
        d.setLastName(resultSet.getString("lastName"));
        d.setUserName(resultSet.getString("username"));
        d.setPassword(resultSet.getString("password"));
        d.setEmail(resultSet.getString("email"));
        d.setDob(resultSet.getDate("dob"));
        d.setId(resultSet.getInt("id"));
      }
      String getDeveloper = "select * from developer where id=?";
      preparedStatement = conn.prepareStatement(getDeveloper);
      preparedStatement.setInt(1, d.getId());
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        d.setDeveloperKey(resultSet.getString("developer_key"));
      }

      String getAddr = "select * from address where id=?";
      preparedStatement = conn.prepareStatement(getAddr);
      preparedStatement.setInt(1, d.getId());
      resultSet = preparedStatement.executeQuery();
      List<Address> addressList = new ArrayList<>();

      while (resultSet.next()) {
        Address a = new Address();
        a.setStreet1(resultSet.getString("street1"));
        a.setStreet2(resultSet.getString("street2"));
        a.setCity(resultSet.getString("city"));
        a.setState(resultSet.getString("state"));
        a.setZip(resultSet.getString("zip"));
        a.setPrimary(resultSet.getBoolean("is_primary"));
        addressList.add(a);
      }
      d.setAddress(addressList);

      String getPhone = "select * from phone where id=?";
      preparedStatement = conn.prepareStatement(getPhone);
      preparedStatement.setInt(1, d.getId());
      resultSet = preparedStatement.executeQuery();
      List<Phone> phoneList = new ArrayList<>();

      while (resultSet.next()) {
        Phone p = new Phone();
        p.setPhone(resultSet.getString("phone"));
        p.setPrimary(resultSet.getBoolean("is_primary"));
        phoneList.add(p);
      }
      d.setPhone(phoneList);
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }*/

    return d;
  }

  @Override
  public Developer findDeveloperByCredentials(String username, String password) {
    Developer d = new Developer();

    try {
      conn = DBConnection.getConnection();
      preparedStatement = conn.prepareStatement("select id from person where username = ? and password = ?");
      preparedStatement.setString(1,username);
      preparedStatement.setString(2,password);
      ResultSet resultSet = preparedStatement.executeQuery();
      if(resultSet.next()){
        d.setId(resultSet.getInt("id"));
      }
      preparedStatement = conn.prepareStatement("select * from developer where id = ?");
      preparedStatement.setInt(1,d.getId());
      resultSet = preparedStatement.executeQuery();
      if(resultSet.next()){
        d.setDeveloperKey(resultSet.getString("developer_key"));
      }
      d = findOtherDetails(d);
      return d;
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public int updateDeveloper(int developerId, Developer developer) {
    try {
      conn = DBConnection.getConnection();
      String updatePerson = "update person set firstName=?, lastName=?, username= ?,password=?," +
              "email=?,dob=? where id=?";
      preparedStatement = conn.prepareStatement(updatePerson);
      preparedStatement.setString(1, developer.getFirstName());
      preparedStatement.setString(2, developer.getLastName());
      preparedStatement.setString(3, developer.getUserName());
      preparedStatement.setString(4, developer.getPassword());
      preparedStatement.setString(5, developer.getEmail());
      preparedStatement.setDate(6, developer.getDob());
      preparedStatement.setInt(7, developer.getId());
      preparedStatement.executeUpdate();


      String updateDeveloper = "update developer set developer_key=? where id=?";
      preparedStatement = conn.prepareStatement(updateDeveloper);
      preparedStatement.setString(1, developer.getDeveloperKey());
      preparedStatement.setInt(2, developerId);
      preparedStatement.executeUpdate();

   /*   Set<String> currAddr = new HashSet<>();
      Set<String> newAddr = new HashSet<>();
      for (Address p : developer.getAddress()) {
        newAddr.add(p.getStreet1());
      }

      String getCurrAddr = "select * from address where id=?";
      preparedStatement = conn.prepareStatement(getCurrAddr);
      preparedStatement.setInt(1, developerId);
      ResultSet resultSet = preparedStatement.executeQuery();
      String replaceStreet1 = "";
      while (resultSet.next()) {
        String s = resultSet.getString("street1");
        if (newAddr.contains(s)) {
          currAddr.add(s);
        } else
          replaceStreet1 = s;
      }*/

      AddressDao addressDao = new AddressImpl();
      for (Address a : developer.getAddress()) {
        if(a.isChanged()){
          addressDao.updateAddress(a.getPersonId(), a);
        }
      }

      /*String updateAddress = "update address set street1=?, street2=?, city=?, state=?, zip=?, " +
              "is_primary=? where id=?";
      preparedStatement = conn.prepareStatement(updateAddress);
      for (Address a : developer.getAddress()) {
        preparedStatement.setString(1, a.getStreet1());
        preparedStatement.setString(2, a.getStreet2());
        preparedStatement.setString(3, a.getCity());
        preparedStatement.setString(4, a.getState());
        preparedStatement.setString(5, a.getZip());
        preparedStatement.setBoolean(6, a.isPrimary());
        preparedStatement.setInt(7, developerId);
        preparedStatement.executeUpdate();
      }*/

      PhoneDao phoneDao = new PhoneImpl();
      for(Phone p : developer.getPhone()) {
        if(p.isChanged()) {
          phoneDao.updatePhone(p.getDevId(),p);
        }
      }

      // Update phones for the developer
      /*Collection<Phone> currPhone = new ArrayList<>();
      Set<String> phnString = new HashSet<>();
      Phone oldPhone = new Phone();

      PhoneDao phoneDao = new PhoneImpl();
      currPhone = phoneDao.findPhonesByPersonId(developerId);
      *//*for(Phone p : currPhone){
        phnString.add(p.getPhone());
      }*//*

      Phone newPhone = new Phone();
      List<Phone> newPh = developer.getPhone();

      for(Phone p : newPh) {
          if(currPhone.removeIf(x -> x.getPhone().equals(p.getPhone())))
            ;
          else
            newPhone = p;
        }
*/
      /*String getCurrPh = "select * from phone where id=?";
      preparedStatement = conn.prepareStatement(getCurrPh);
      preparedStatement.setInt(1, developerId);
      resultSet = preparedStatement.executeQuery();
      String replacePhone = "";
      while (resultSet.next()) {
        String s = resultSet.getString("phone");
        Phone p = new Phone();
        p.setPhone(s);
        phnString.add(s);
        p.setDevId(developerId);
        p.setPrimary(resultSet.getBoolean("is_primary"));
        currPhone.add(p);
      }*/



     /* for (Phone p : newPh) {
        if (phnString.contains(p.getPhone())) {
          phnString.remove(p.getPhone());
        } else
          newPhone = p;
      }*/
     /* for (String p : phnString) {
        oldPhone.setPhone(p);
        oldPhone.setDevId(developerId);
      }*/
  /*    for(Phone p : currPhone){
        oldPhone = p;
        if (oldPhone.isPrimary()) {
          phoneDao.updatePrimaryPhone(developerId, newPhone);
        } else {
          phoneDao.updateNonPrimaryPhone(developerId, oldPhone, newPhone);
        }
      }*/

      /*for (Phone p : currPhone) {
        if (p.getPhone().equals(oldPhone.getPhone()))
          oldPhone.setPrimary(p.isPrimary());
      }*/

      //PhoneDao phoneDao1 = new PhoneImpl();


        /*
        String updatePhone = "update phone set phone = ?, is_primary = ? where id = ? and phone = ?";
        preparedStatement = conn.prepareStatement(updatePhone);
        for(Phone p : developer.getPhone()) {
          if (!currPhone.contains(p.getPhone())) {
            preparedStatement.setString(1, p.getPhone());
            preparedStatement.setBoolean(2, p.isPrimary());
            preparedStatement.setInt(3, developerId);
            preparedStatement.setString(4, replacePhone);
            //preparedStatement.setString(5, p.getPhone());
            preparedStatement.executeUpdate();
          }
        }*/
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return 0;
  }

  @Override
  public int deleteDeveloper(int developerId) {
    try {
      conn = DBConnection.getConnection();

      /*preparedStatement = conn.prepareStatement("delete from address where id = ?");
      preparedStatement.setInt(1, developerId);
      int x = preparedStatement.executeUpdate();*/
      int x = 0;
      AddressDao addressDao = new AddressImpl();
      Collection<Address> addresses= addressDao.findAddressByPertonId(developerId);
      for(Address a : addresses){
        x += addressDao.deleteAddress(a);
      }

      /*preparedStatement = conn.prepareStatement("delete from phone where id = ?");
      preparedStatement.setInt(1, developerId);
      x += preparedStatement.executeUpdate();*/

      PhoneDao phoneDao = new PhoneImpl();
      Collection<Phone> phones= phoneDao.findPhonesByPersonId(developerId);
      for(Phone p : phones){
        x += phoneDao.deletePhone(p);
      }

      preparedStatement = conn.prepareStatement("delete from person where id = ?");
      preparedStatement.setInt(1, developerId);
      x += preparedStatement.executeUpdate();

      preparedStatement = conn.prepareStatement("delete from developer where id = ?");
      preparedStatement.setInt(1 , developerId);
      x += preparedStatement.executeUpdate();
      return x;
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
      return 0;
    }

    return 0;
  }


  private Developer findOtherDetails(Developer d) {

    try {
      conn = DBConnection.getConnection();
      preparedStatement = conn.prepareStatement("select * from person where id = ?");
      preparedStatement.setInt(1, d.getId());
      ResultSet resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        d.setFirstName(resultSet.getString("firstName"));
        d.setLastName(resultSet.getString("lastName"));
        d.setUserName(resultSet.getString("username"));
        d.setPassword(resultSet.getString("password"));
        //d.setPhone((List)resultSet.getArray("phone"));
        //d.setAddress((List)resultSet.getArray("address"));
        d.setEmail(resultSet.getString("email"));
        d.setDob(resultSet.getDate("dob"));
        preparedStatement = conn.prepareStatement("select * from address where id = ?");
        preparedStatement.setInt(1, d.getId());
        ResultSet resultSet2 = preparedStatement.executeQuery();
        List<Address> addressList = new ArrayList<>();
        while (resultSet2.next()) {
          Address a = new Address();
          a.setStreet1(resultSet2.getString("street1"));
          a.setStreet2(resultSet2.getString("street2"));
          a.setState(resultSet2.getString("state"));
          a.setCity(resultSet2.getString("city"));
          a.setZip(resultSet2.getString("zip"));
          a.setPrimary(resultSet2.getBoolean("is_primary"));
          a.setPersonId(d.getId());
          addressList.add(a);
        }
        d.setAddress(addressList);
        preparedStatement = conn.prepareStatement("select * from phone where id = ?");
        preparedStatement.setInt(1, d.getId());
        resultSet2 = preparedStatement.executeQuery();
        List<Phone> phoneList = new ArrayList<>();

        while (resultSet2.next()) {
          Phone p = new Phone();
          p.setPrimary(resultSet2.getBoolean("is_primary"));
          p.setDevId(d.getId());
          p.setPhone(resultSet2.getString("phone"));
          phoneList.add(p);
        }
        d.setPhone(phoneList);
      }
      return d;
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
      return d;
    }

    return d;
  }
}
