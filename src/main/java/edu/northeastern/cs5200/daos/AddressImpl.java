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

public class AddressImpl implements AddressDao {
  Connection conn;
  PreparedStatement preparedStatement;
  @Override
  public void createAddress(int personId, Address a) {
    try {
      conn = DBConnection.getConnection();
      String addressEntry = "insert into address values(?,?,?,?,?,?,?)";

      preparedStatement = conn.prepareStatement(addressEntry);

     // for(Address a : addrList) {
        preparedStatement.setString(1, a.getStreet1());
        preparedStatement.setString(2, a.getStreet2());
        preparedStatement.setString(3, a.getCity());
        preparedStatement.setString(4, a.getState());
        preparedStatement.setString(5, a.getZip());
        preparedStatement.setBoolean(6, a.isPrimary());
        preparedStatement.setInt(7, personId);
        preparedStatement.executeUpdate();
      //}

    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  @Override
  public Collection<Address> findAllAddress() {
    return null;
  }

  @Override
  public Collection<Address> findAddressByPertonId(int personId) {
    Collection<Address> addresses = new ArrayList<>();
    try {
      conn = DBConnection.getConnection();
      preparedStatement = conn.prepareStatement("select * from address where id = ?");
      preparedStatement.setInt(1, personId);
      ResultSet resultSet = preparedStatement.executeQuery();

      while(resultSet.next()){
        Address a = new Address();
        a.setStreet1(resultSet.getString("street1"));
        a.setStreet2(resultSet.getString("street2"));
        a.setCity(resultSet.getString("city"));
        a.setState(resultSet.getString("state"));
        a.setZip(resultSet.getString("zip"));
        a.setPrimary(resultSet.getBoolean("is_primary"));
        a.setPersonId(resultSet.getInt("id"));
        addresses.add(a);
      }

      return addresses;

    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }

    return null;
  }

  @Override
  public int updateAddress(int personId, Address addr) {
    try {
      conn = DBConnection.getConnection();
      preparedStatement = conn.prepareStatement("update address set street1 = ?, street2 = ?, " +
              "city = ?, state = ?, zip = ?, is_primary = ? " +
              "where id=? and street1 =? and street2 = ? and city = ? and state = ? and zip = ? " +
              "and is_primary = ?");
      preparedStatement.setString(1, addr.getStreet1());
      preparedStatement.setString(2, addr.getStreet2());
      preparedStatement.setString(3, addr.getCity());
      preparedStatement.setString(4, addr.getState());
      preparedStatement.setString(5, addr.getZip());
      preparedStatement.setBoolean(6, addr.isPrimary());
      preparedStatement.setInt(7, personId);
      preparedStatement.setString(8, addr.getOldStreet1());
      preparedStatement.setString(9, addr.getOldStreet2());
      preparedStatement.setString(10, addr.getOldCity());
      preparedStatement.setString(11, addr.getOldState());
      preparedStatement.setString(12,addr.getOldZip());
      preparedStatement.setBoolean(13,addr.getOldIsPrimary());
      return preparedStatement.executeUpdate();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return 0;
  }

  @Override
  public int deleteAddress(Address addr) {
    try {
      conn = DBConnection.getConnection();
      preparedStatement = conn.prepareStatement("delete from address where street1 = ?" +
              " and city = ? and state = ? and zip = ? and is_primary = ? and id = ?");
      preparedStatement.setString(1, addr.getStreet1());
      preparedStatement.setString(2,addr.getCity());
      preparedStatement.setString(3, addr.getState());
      preparedStatement.setString(4, addr.getZip());
      preparedStatement.setBoolean(5, addr.isPrimary());
      preparedStatement.setInt(6,addr.getPersonId());
      return preparedStatement.executeUpdate();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return 0;
  }


}
