package edu.northeastern.cs5200.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import edu.northeastern.cs5200.DBConnection;
import edu.northeastern.cs5200.models.Address;
import edu.northeastern.cs5200.models.Developer;
import edu.northeastern.cs5200.models.Phone;
import edu.northeastern.cs5200.models.User;

public class UserImpl implements UserDao {
  Connection conn;
  PreparedStatement preparedStatement;
  @Override
  public void createUser(User user) {
    String userEntry = "insert into user values(?, ?)";
    String personEntry = "insert into person values(?, ?, ?, ?, ?, ?, ?)";
    try {
      conn = DBConnection.getConnection();
      preparedStatement = conn.prepareStatement(personEntry);
      preparedStatement.setInt(1, user.getId());
      preparedStatement.setString(2, user.getFirstName());
      preparedStatement.setString(3, user.getLastName());
      preparedStatement.setString(4, user.getUserName());
      preparedStatement.setString(5,user.getPassword());
      preparedStatement.setString(6, user.getEmail());
      preparedStatement.setDate(7, new java.sql.Date(user.getDob().getTime()));
      preparedStatement.executeUpdate();

      preparedStatement = conn.prepareStatement(userEntry);
      preparedStatement.setInt(2, user.getId());
      preparedStatement.setBoolean(1, user.isUserAgreement());
      preparedStatement.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

  }

  @Override
  public Collection<User> findAllUsers() {
    Collection<User> users = new ArrayList<>();
    try {
      conn = DBConnection.getConnection();
      preparedStatement = conn.prepareStatement("select * from user");
      ResultSet resultSet = preparedStatement.executeQuery();
      while(resultSet.next()) {
        User u = new User();
        u.setUserAgreement(resultSet.getBoolean("user_agreement"));
        u.setId(resultSet.getInt("id"));
        u = findOtherDetailsOfUsers(u);
        users.add(u);
      }
      return users;
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public User findUserById(int userId) {
    try {
      conn = DBConnection.getConnection();
      preparedStatement = conn.prepareStatement("select * from user where id = ?");
      ResultSet resultSet = preparedStatement.executeQuery();
      User u = new User();
      if (resultSet.next()) {
        u.setId(userId);
        u.setUserAgreement(resultSet.getBoolean("user_agreement"));
        u = findOtherDetailsOfUsers(u);
      }
      return u;
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
    return null;
  }

  @Override
  public User findUserByUsername(String username) {
    User u = new User();

    try {
      conn = DBConnection.getConnection();
      preparedStatement = conn.prepareStatement("select id from person where username = ?");
      preparedStatement.setString(1,username);
      ResultSet resultSet = preparedStatement.executeQuery();
      if(resultSet.next()){
        u.setId(resultSet.getInt("id"));
      }
      preparedStatement = conn.prepareStatement("select * from user where id = ?");
      preparedStatement.setInt(1,u.getId());
      resultSet = preparedStatement.executeQuery();
      if(resultSet.next()){
        u.setUserAgreement(resultSet.getBoolean("user_agreement"));
      }
      u = findOtherDetailsOfUsers(u);
      return u;
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return u;
  }

  @Override
  public User findUserByCredentials(String username, String password) {

    User u = new User();

    try {
      conn = DBConnection.getConnection();
      preparedStatement = conn.prepareStatement("select id from person where username = ? and password = ?");
      preparedStatement.setString(1,username);
      preparedStatement.setString(2,password);
      ResultSet resultSet = preparedStatement.executeQuery();
      if(resultSet.next()){
        u.setId(resultSet.getInt("id"));
      }
      preparedStatement = conn.prepareStatement("select * from user where id = ?");
      preparedStatement.setInt(1,u.getId());
      resultSet = preparedStatement.executeQuery();
      if(resultSet.next()){
        u.setUserAgreement(resultSet.getBoolean("user_agreement"));
      }
      u = findOtherDetailsOfUsers(u);
      return u;
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return u;
  }

  @Override
  public int updateUser(int userId, User user) {
    try {
      conn = DBConnection.getConnection();
      String updatePerson = "update person set firstName=?, lastName=?, username= ?,password=?," +
              "email=?,dob=? where id=?";
      preparedStatement = conn.prepareStatement(updatePerson);
      preparedStatement.setString(1, user.getFirstName());
      preparedStatement.setString(2, user.getLastName());
      preparedStatement.setString(3, user.getUserName());
      preparedStatement.setString(4, user.getPassword());
      preparedStatement.setString(5, user.getEmail());
      preparedStatement.setDate(6, user.getDob());
      preparedStatement.setInt(7, user.getId());
      int x = preparedStatement.executeUpdate();

      preparedStatement = conn.prepareStatement("update user set user_agreement = ? where id = ?");
      preparedStatement.setBoolean(1, user.isUserAgreement());
      preparedStatement.setInt(2, userId);
      x += preparedStatement.executeUpdate();

      AddressDao addressDao = new AddressImpl();
      for (Address a : user.getAddress()) {
        if(a.isChanged()){
          x += addressDao.updateAddress(a.getPersonId(), a);
        }
      }

      PhoneDao phoneDao = new PhoneImpl();
      for(Phone p : user.getPhone()) {
        if(p.isChanged()) {
          x+=phoneDao.updatePhone(p.getDevId(),p);
        }
      }
      return x;
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
      return 0;
    }

      return 0;
    }

  @Override
  public int deleteUser(int userId) {
    try {
      conn = DBConnection.getConnection();
      int x = 0;
      AddressDao addressDao = new AddressImpl();
      Collection<Address> addresses= addressDao.findAddressByPertonId(userId);
      for(Address a : addresses){
        x += addressDao.deleteAddress(a);
      }

      PhoneDao phoneDao = new PhoneImpl();
      Collection<Phone> phones= phoneDao.findPhonesByPersonId(userId);
      for(Phone p : phones){
        x += phoneDao.deletePhone(p);
      }

      preparedStatement = conn.prepareStatement("delete from person where id = ?");
      preparedStatement.setInt(1, userId);
      x += preparedStatement.executeUpdate();

      preparedStatement = conn.prepareStatement("delete from developer where id = ?");
      preparedStatement.setInt(1 , userId);
      x += preparedStatement.executeUpdate();
      return x;

    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return 0;
  }


  private User findOtherDetailsOfUsers(User d) {
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
