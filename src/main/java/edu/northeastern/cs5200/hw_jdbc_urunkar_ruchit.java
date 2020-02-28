package edu.northeastern.cs5200;


import java.sql.Date;
import java.util.ArrayList;
//
import java.util.Collection;
import java.util.List;

import edu.northeastern.cs5200.daos.AddressDao;
import edu.northeastern.cs5200.daos.AddressImpl;
import edu.northeastern.cs5200.daos.DeveloperDao;
import edu.northeastern.cs5200.daos.DeveloperImpl;
import edu.northeastern.cs5200.daos.PageDao;
import edu.northeastern.cs5200.daos.PageImpl;
import edu.northeastern.cs5200.daos.PhoneDao;
import edu.northeastern.cs5200.daos.PhoneImpl;
import edu.northeastern.cs5200.daos.RoleDao;
import edu.northeastern.cs5200.daos.RoleImpl;
import edu.northeastern.cs5200.daos.UserDao;
import edu.northeastern.cs5200.daos.UserImpl;
import edu.northeastern.cs5200.daos.WebsiteDao;
import edu.northeastern.cs5200.daos.WebsiteImpl;
import edu.northeastern.cs5200.daos.WidgetDao;
import edu.northeastern.cs5200.daos.WidgetImpl;
import edu.northeastern.cs5200.models.Address;
import edu.northeastern.cs5200.models.Developer;
import edu.northeastern.cs5200.models.Page;
import edu.northeastern.cs5200.models.Phone;
import edu.northeastern.cs5200.models.User;
import edu.northeastern.cs5200.models.Website;
import edu.northeastern.cs5200.models.Widget;
import edu.northeastern.cs5200.models.WidgetType;

public class hw_jdbc_urunkar_ruchit {
  DeveloperDao developerDao = new DeveloperImpl();
  public void createDeveloper() {

    Developer d1 = new Developer("4321rewq", 12, "Alice",
            "Wonder", "alice", "alice", "alice@wonder.com",
            Date.valueOf("1992-05-31"));

    developerDao.createDeveloper(d1);
    Developer d2 = new Developer("5432trew", 23, "Bob",
            "Marley", "bob", "bob", "bob@marley.com",
            Date.valueOf("1993-12-17"));


    developerDao.createDeveloper(d2);

    Developer d3 = new Developer("6543ytre", 34, "Charles",
            "Garcia", "charlie", "charlie", "chuch@garcia.com",
            Date.valueOf("1996-05-19"));

    developerDao.createDeveloper(d3);


  }

  public void createAddressForDeveloper() {
    List<Address> addressList = new ArrayList<>();
    Address a = new Address(true, "123 Adam St.", "", "Alton","", "01234");
    addressList.add(a);
    a = new Address(false, "234 Birch St.", "", "Boston","MA", "02345");
    addressList.add(a);
    AddressDao addressDao = new AddressImpl();
    for(Address addr : addressList)
    addressDao.createAddress(12, addr);

    addressList = new ArrayList<>();
    a = new Address(true, "345 Charles St.", "", "Chelms","", "03455");
    addressList.add(a);
    a = new Address(false, "456 Down St.", "", "Dalton","", "04566");
    addressList.add(a);
    a = new Address(false, "543 East St.", "", "Everett","", "01112");
    addressList.add(a);
    for(Address addr : addressList)
      addressDao.createAddress(23, addr);


    addressList = new ArrayList<>();
    a = new Address(true, "654 Frank St.", "", "Foulton","", "04322");
    addressList.add(a);
    for(Address addr : addressList)
      addressDao.createAddress(34, addr);

  }

  public void createPhoneForDeveloper() {
    List<Phone> phoneList = new ArrayList<>();
    Phone p = new Phone(true, "123-234-3456");
    phoneList.add(p);
    p = new Phone(false, "234-345-4566");
    phoneList.add(p);

    PhoneDao phoneDao = new PhoneImpl();
    for(Phone ph : phoneList)
    phoneDao.createPhone(12, ph);

    phoneList = new ArrayList<>();
    p = new Phone(true, "345-456-5677");
    phoneList.add(p);
    for(Phone ph : phoneList)
      phoneDao.createPhone(23, ph);

    phoneList = new ArrayList<>();
    p = new Phone(true, "321-432-5435");
    phoneList.add(p);
    p = new Phone(false, "432-432-5433");
    phoneList.add(p);
    p = new Phone(false, "543-543-6544");
    phoneList.add(p);
    for(Phone ph : phoneList)
      phoneDao.createPhone(34, ph);
  }

  public void createUser() {
    User u1 = new User();
    u1.setId(45);
    u1.setFirstName("Dan");
    u1.setLastName("Martin");
    u1.setUserName("dan");
    u1.setPassword("dan");
    u1.setEmail("dan@martin.com");
    u1.setUserAgreement(true);
    u1.setDob(Date.valueOf("1992-10-26"));

    User u2 = new User();
    u2.setId(56);
    u2.setFirstName("Ed");
    u2.setLastName("Karaz");
    u2.setUserName("ed");
    u2.setPassword("ed");
    u2.setEmail("ed@kar.com");
    u2.setUserAgreement(false);
    u2.setDob(Date.valueOf("1997-04-10"));

    UserDao userDao = new UserImpl();
    userDao.createUser(u1);
    userDao.createUser(u2);

  }

  public void createWebsite() {
    WebsiteDao websiteDao = new WebsiteImpl();
    java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
    Website website = new Website(123, "Facebook", "an online social media and social networking service",sqlDate, sqlDate,1234234);
    websiteDao.createWebsiteForDeveloper(12, website);
    websiteDao.createWebsiteForDeveloper(23, website);
    websiteDao.createWebsiteForDeveloper(34, website);

    website = new Website(234, "Twitter", "an online news and social networking service",sqlDate, sqlDate,4321543);
    websiteDao.createWebsiteForDeveloper(23, website);
    websiteDao.createWebsiteForDeveloper(34, website);
    websiteDao.createWebsiteForDeveloper(12, website);


    website = new Website(345, "Wikipedia", "a free online encyclopedia",sqlDate, sqlDate,3456654);
    websiteDao.createWebsiteForDeveloper(23, website);
    websiteDao.createWebsiteForDeveloper(34, website);
    websiteDao.createWebsiteForDeveloper(12, website);

    website = new Website(456, "CNN", "an American basic cable and satellite television news channel",sqlDate, sqlDate,6543345);
    websiteDao.createWebsiteForDeveloper(23, website);
    websiteDao.createWebsiteForDeveloper(34, website);
    websiteDao.createWebsiteForDeveloper(12, website);

    website = new Website(567, "CNET",
            "an American media website that publishes reviews, news, articles, blogs, podcasts " +
            "and videos on technology and consumer electronics",sqlDate, sqlDate,5433455);
    websiteDao.createWebsiteForDeveloper(23, website);
    websiteDao.createWebsiteForDeveloper(34, website);
    websiteDao.createWebsiteForDeveloper(12, website);

    website = new Website(678, "Gizmodo",
              "a design, technology, science and science fiction website that also writes articles on politics", sqlDate, sqlDate,4322345);
    websiteDao.createWebsiteForDeveloper(23, website);
    websiteDao.createWebsiteForDeveloper(34, website);
    websiteDao.createWebsiteForDeveloper(12, website);
  }

  public void createPage() {
    PageDao pageDao = new PageImpl();
    java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
    Page page = new Page(123, "Home", "Landing page", sqlDate, sqlDate, 123434);
    pageDao.createPageForWebsite(567, page);

    page = new Page(234, "About", "Website description", sqlDate, sqlDate, 234545);
    pageDao.createPageForWebsite(678, page);

    page = new Page(345, "Contact", "Addresses, phones, and contact info", sqlDate, sqlDate, 345656);
    pageDao.createPageForWebsite(345, page);

    page = new Page(456, "Preferences", "Where users can configure their preferences", sqlDate, sqlDate, 456776);
    pageDao.createPageForWebsite(456, page);

    page = new Page(567, "Profile", "Users can configure their personal information", sqlDate, sqlDate, 567878);
    pageDao.createPageForWebsite(567, page);
  }

  public void createWidget() {
    WidgetDao widgetDao = new WidgetImpl();

    Widget widget = new Widget(1,"head123", 0, 0, "", "", "Welcome", 0, 2, null, null, false, false, WidgetType.HEADING);
    widgetDao.createWidgetForPage(123, widget);

    widget = new Widget(2,"post234", 0, 0, "", "", "<p>Lorem</p>", 0, 0, null, null, false, false, WidgetType.HTML);
    widgetDao.createWidgetForPage(234, widget);

    widget = new Widget(3,"head345", 0, 0, "","" ,"Hi", 1, 3, null, null, false, false, WidgetType.HEADING);
    widgetDao.createWidgetForPage(345, widget);

    widget = new Widget(4,"intro456", 0, 0, "", "","<h1>Hi</h1>", 2, 0, null, null, false, false, WidgetType.HTML);
    widgetDao.createWidgetForPage(345, widget);

    widget = new Widget(5,"image345", 50, 100, "","","<p>Lorem</p>", 3, 0, "/img/567.png", null, false, false, WidgetType.IMAGE);
    widgetDao.createWidgetForPage(345, widget);

    widget = new Widget(6,"video456", 400, 300, "","","<p>Lorem</p>", 0, 0, null, "https://youtu.be/h67VX51QXiQ", true, true, WidgetType.YOUTUBE);
    widgetDao.createWidgetForPage(456, widget);


  }

  public void createWebsiteRole() {
    RoleDao roleDao = new RoleImpl();
    roleDao.assignWebsiteRole(12,123,1);
    roleDao.assignWebsiteRole(23,123,4);
    roleDao.assignWebsiteRole(34,123,2);

    roleDao.assignWebsiteRole(23,234,1);
    roleDao.assignWebsiteRole(34,234,4);
    roleDao.assignWebsiteRole(12,234,2);

    roleDao.assignWebsiteRole(34,345,1);
    roleDao.assignWebsiteRole(12,345,4);
    roleDao.assignWebsiteRole(23,345,2);

    roleDao.assignWebsiteRole(12,456,1);
    roleDao.assignWebsiteRole(23,456,4);
    roleDao.assignWebsiteRole(34,456,2);

    roleDao.assignWebsiteRole(23,567,1);
    roleDao.assignWebsiteRole(34,567,4);
    roleDao.assignWebsiteRole(12,567,2);

    roleDao.assignWebsiteRole(34,678,1);
    roleDao.assignWebsiteRole(12,678,4);
    roleDao.assignWebsiteRole(23,678,2);

  }

  public void createPageRole() {
    RoleDao roleDao = new RoleImpl();
    roleDao.assignPageRole(12, 123, 4);
    roleDao.assignPageRole(23, 123, 5);
    roleDao.assignPageRole(34, 123, 3);

    roleDao.assignPageRole(23, 234, 4);
    roleDao.assignPageRole(34, 234, 5);
    roleDao.assignPageRole(12, 234, 3);

    roleDao.assignPageRole(34, 345, 4);
    roleDao.assignPageRole(12, 345, 5);
    roleDao.assignPageRole(23, 345, 3);

    roleDao.assignPageRole(12, 456, 4);
    roleDao.assignPageRole(23, 456, 5);
    roleDao.assignPageRole(34, 456, 3);

    roleDao.assignPageRole(23, 567, 4);
    roleDao.assignPageRole(34, 567, 5);
    roleDao.assignPageRole(12, 567, 3);



  }

  public void updatePhoneByName(String name, String phoneNumber) {
    DeveloperDao d= new DeveloperImpl();
    Developer dev = d.findDeveloperByUsername(name);
    List<Phone> phList = dev.getPhone();
    /*for(int i=0; i< phList.size(); i++) {// p : phList){
      if (phList.get(i).isPrimary()) {
        phList.get(i).setPhone(phoneNumber);
        //phList.add(new Phone(true,phoneNumber));
        break;
      }
    }*/

    for(Phone p : phList){
      if(p.isPrimary()){
        p.setPhone(phoneNumber);
      }
    }
    dev.setPhone(phList);
    developerDao.updateDeveloper(dev.getId(), dev);

  /*  PhoneDao phoneDao = new PhoneImpl();
    Phone p = new Phone();
    p.setPhone(phoneNumber);
    p.setPrimary(true);
    p.setDevId(dev.getId());
    phoneDao.updatePrimaryPhone(dev.getId(), p);*/

  }

  public void updatePage() {
    PageDao p = new PageImpl();
    WebsiteDao websiteDao = new WebsiteImpl();
    Collection<Page> pages = p.findPagesForWebsite(websiteDao.findWebsiteIdByName("CNET"));
    for (Page p1 : pages) {
      p1.setTitle("CNET - " + p1.getTitle());
      p.updatePage(p1.getId(), p1);
    }
  }
    public void swapRoles() {
      PageDao p = new PageImpl();
      WebsiteDao websiteDao = new WebsiteImpl();
      Collection<Page> pages= p.findPagesForWebsite(websiteDao.findWebsiteIdByName("CNET"));
      int page_id=0;
      for(Page p1 : pages){
        if(p1.getTitle().equals("CNET - Home")){
          page_id = p1.getId();
          break;
        }
      }
      RoleDao roleDao = new RoleImpl();
      Developer d1 = new DeveloperImpl().findDeveloperByUsername("bob");
      int role1 = roleDao.roleByPageAndDeveloper(d1.getId(),page_id);
      Developer d2 = new DeveloperImpl().findDeveloperByUsername("charlie");
      int role2 = roleDao.roleByPageAndDeveloper(d2.getId(),page_id);
      System.out.println(role1 + " " + role2);

      roleDao.deletePageRole(d1.getId(),page_id,role1);
      roleDao.deletePageRole(d2.getId(),page_id,role2);

      roleDao.assignPageRole(d1.getId(),page_id,role2);
      roleDao.assignPageRole(d2.getId(),page_id,role1);

    }

    public void changeOrder(){
    WidgetDao widgetDao = new WidgetImpl();
    Widget w = widgetDao.findWidgetByName("head345");

    Collection<Widget> widgets = widgetDao.findWidgetsForPage(w.getPageId());

    for(Widget w1 : widgets) {
        w1.setOrder(((w1.getOrder() + 1) % 3) + 1 );
        widgetDao.updateWidget(w1.getId(), w1);
    }


    }

    void deletePrimaryAddressForAlice() {
      DeveloperDao d= new DeveloperImpl();
      Developer dev = d.findDeveloperByUsername("alice");
      AddressDao addressDao = new AddressImpl();
      Collection<Address> addresses = addressDao.findAddressByPertonId(dev.getId());
      //Address primary = new Address();
      for(Address a : addresses){
        if(a.isPrimary()){
          addressDao.deleteAddress(a);
          break;
        }
      }

    }

    void deleteLastWidget() {
      PageDao p = new PageImpl();
      //WebsiteDao websiteDao = new WebsiteImpl();
      Page page = p.findPageByName("Contact");
      WidgetDao widgetDao = new WidgetImpl();
      Collection<Widget> widgets = widgetDao.findWidgetsForPage(page.getId());
      //System.out.println(widgets.size());
      int i = -1;
      Widget hightest = new Widget();
      for(Widget w : widgets) {
        if(w.getOrder() > i) {
          i = w.getOrder();
          hightest = w;
        }
      }
      widgetDao.deleteWidget(hightest.getId());
    }

    void deleteLastPageWikipedia() {
      PageDao p = new PageImpl();
      WebsiteDao websiteDao = new WebsiteImpl();
      Collection<Page> pages = p.findPagesForWebsite(websiteDao.findWebsiteIdByName("Wikipedia"));
      Date d = new Date(-1000);
      Page toDelete = new Page();
      for(Page pg : pages) {
        if (pg.getUpdated().compareTo(d) > 0) {
          d = pg.getUpdated();
          toDelete = pg;
        }
      }

     p.deletePage(toDelete.getId());

      //System.out.println(pages.size());
    }

    int deleteWebsiteCNET(){
      WebsiteDao websiteDao = new WebsiteImpl();
      return websiteDao.deleteWebsite(websiteDao.findWebsiteIdByName("CNET"));

    }

  }




