package edu.northeastern.cs5200.daos;


import java.util.Collection;
import java.util.List;

import edu.northeastern.cs5200.models.Address;
import edu.northeastern.cs5200.models.Phone;

public interface AddressDao {
  void createAddress(int personId, Address addr);
  Collection<Address> findAllAddress();
  Collection<Address> findAddressByPertonId(int personId);
  int updateAddress(int personId, Address addr);
  int deleteAddress(Address addr);
}
