package edu.northeastern.cs5200.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import edu.northeastern.cs5200.DBConnection;
import edu.northeastern.cs5200.models.Phone;

public class PhoneImpl implements PhoneDao {
  Connection conn;
  PreparedStatement preparedStatement;
  @Override
  public void createPhone(int personId, Phone phone) {
    try {
      conn = DBConnection.getConnection();
      String phoneEntry = "insert into phone values(?,?,?)";
      preparedStatement = conn.prepareStatement(phoneEntry);
      preparedStatement.setString(1, phone.getPhone());
      preparedStatement.setBoolean(2, phone.isPrimary());
      preparedStatement.setInt(3, personId);
      preparedStatement.executeUpdate();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  @Override
  public int updatePhone(int personId, Phone phone) {

    try {
      conn = DBConnection.getConnection();
      String updatePhone = "update phone set phone = ? where id = ? and phone = ?";
      preparedStatement = conn.prepareStatement(updatePhone);
      preparedStatement.setString(1, phone.getPhone());
      preparedStatement.setInt(2, personId);
      preparedStatement.setString(3, phone.getOldPhone());
      preparedStatement.executeUpdate();
      return 1;
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
      return 0;
    }
    return 0;
  }

  @Override
  public Collection<Phone> findAllPhone() {
    Collection<Phone> phones = new ArrayList<>();
    try {
      conn = DBConnection.getConnection();
      preparedStatement = conn.prepareStatement("select * from phone");
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()){
        Phone p = new Phone();
        p.setPhone(resultSet.getString("phone"));
        p.setPrimary(resultSet.getBoolean("is_primary"));
        p.setDevId(resultSet.getInt("id"));
        phones.add(p);
      }
      return phones;
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return null;
  }

  @Override
  public Collection<Phone> findPhonesByPersonId(int personId) {
    Collection<Phone> phones = new ArrayList<>();
    try {
      conn = DBConnection.getConnection();
      preparedStatement = conn.prepareStatement("select * from phone where id = ?");
      preparedStatement.setInt(1, personId);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()){
        Phone p = new Phone();
        p.setPhone(resultSet.getString("phone"));
        p.setPrimary(resultSet.getBoolean("is_primary"));
        p.setDevId(personId);
        phones.add(p);
      }
      return phones;
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return null;
  }

  @Override
  public int updateNonPrimaryPhone(int personId, Phone oldPhone, Phone newPhone) {
    try {
      conn = DBConnection.getConnection();
      preparedStatement = conn.prepareStatement("update phone set phone = ?, is_primary = ? where id = ? and phone = ? and is_primary = ? ");
      preparedStatement.setString(1, newPhone.getPhone());
      preparedStatement.setBoolean(2, newPhone.isPrimary());
      preparedStatement.setInt(3, personId);
      preparedStatement.setString(4, oldPhone.getPhone());
      preparedStatement.setBoolean(5,oldPhone.isPrimary());
      preparedStatement.executeUpdate();
      return 1;
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
      return 0;
    }
    return 0;
  }

  @Override
  public int deletePhone(Phone phone) {
    try {
      conn = DBConnection.getConnection();
      preparedStatement = conn.prepareStatement("delete from phone where phone = ?");
      preparedStatement.setString(1, phone.getPhone());
      return preparedStatement.executeUpdate();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return 0;
  }
}
