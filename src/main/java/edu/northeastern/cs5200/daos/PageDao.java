package edu.northeastern.cs5200.daos;

import java.util.Collection;

import edu.northeastern.cs5200.models.Page;

public interface PageDao {
  void createPageForWebsite(int websiteId, Page page);
  Collection<Page> findAllPages();
  Page findPageById(int pageId);
  Page findPageByName(String name);
  Collection<Page> findPagesForWebsite(int websiteId);
  int updatePage(int pageId, Page page);
  int deletePage(int pageId);
}
