package edu.northeastern.cs5200;

import org.junit.jupiter.api.Test;


/**
 * Test case to call functions written in hw_jdbc_urunkar_ruchit.java
 * These are functions that in-turn call DAOs.
 */
class hw_jdbc_urunkar_ruchitTest {

@Test
  public void test() {
  hw_jdbc_urunkar_ruchit obj = new hw_jdbc_urunkar_ruchit();
  // inserts
 obj.createDeveloper();
  obj.createAddressForDeveloper();
  obj.createPhoneForDeveloper();
  obj.createUser();
  obj.createWebsite();
  obj.createPage();
  obj.createWidget();
  obj.createWebsiteRole();
  obj.createPageRole();

  // updates

  obj.updatePhoneByName("charlie","333-444-5555");
  obj.updatePage();
  obj.swapRoles();
  obj.changeOrder();

  //delete
  obj.deletePrimaryAddressForAlice();
  obj.deleteLastWidget();
  obj.deleteLastPageWikipedia();
  obj.deleteWebsiteCNET();

}
}