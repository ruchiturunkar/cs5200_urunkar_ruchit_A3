package edu.northeastern.cs5200.daos;


import java.util.Collection;
import java.util.List;

import edu.northeastern.cs5200.models.Phone;

public interface PhoneDao {
  void createPhone(int personId, Phone phone);
  int updatePhone(int personId, Phone phone);
  Collection<Phone> findAllPhone();
  Collection<Phone> findPhonesByPersonId(int personId);
  int updateNonPrimaryPhone(int personId, Phone oldPhone, Phone newPhone);
  int deletePhone(Phone phone);
}
