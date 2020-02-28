package edu.northeastern.cs5200.daos;

import java.util.Collection;

public interface RoleDao {
  void assignWebsiteRole(int developerId, int websiteId, int roleId);
  void assignPageRole(int developerId, int pageId, int roleId);
  void deleteWebsiteRole(int developerId, int websiteId, int roleId);
  void deletePageRole(int developerId, int pageId, int roleId);
  int roleByWebsiteAndDeveloper(int developerId, int websiteId);
  int roleByPageAndDeveloper(int developerId, int page);
  int deleteRolesForPage(int page);
  int deleteRolesForWebsite(int websiteId);
}
