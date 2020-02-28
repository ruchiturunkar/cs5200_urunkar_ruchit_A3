package edu.northeastern.cs5200.daos;

import java.util.Collection;

import edu.northeastern.cs5200.models.Developer;
import edu.northeastern.cs5200.models.User;

public interface UserDao {
  /**
   * inserts properties in User instance parameter in tables User and Person
   * @param user to be inserted
   */
  void createUser(User user);
  Collection<User> findAllUsers();
  User findUserById(int userId);
  User findUserByUsername(String username);
  User findUserByCredentials(String username, String password);
  int updateUser(int userId, User user);
  int deleteUser(int developerId);

}
