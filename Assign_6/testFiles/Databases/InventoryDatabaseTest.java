/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Databases;

import Book.BookInStock;
import Book.BookNotInStock;
import Book.Vendor;
import SQL_DataHandler.DataHandler;
import java.time.LocalDateTime;
import java.util.ArrayList;
import junit.framework.TestCase;

/**
 *
 * @author ridri
 */
public class InventoryDatabaseTest extends TestCase {
    
    private static String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:XE";
    private static String userid = "SYSTEM";
    private static String password = "system"; 
    
    public InventoryDatabaseTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    /**
     * Test of getBooksBelowThreshold method, of class InventoryDatabase.
     */
    public void testGetBooksBelowThreshold() throws Exception {
        try {
            System.out.println("+++++++getBooksBelowThreshold+++++++");
            InventoryDatabase instance = new InventoryDatabase(userid, password);
            String title = LocalDateTime.now().toString();
            instance.addtoInventory(new BookInStock("ISBN562.357561314061", title, "masashi", "konami", 12, 12, 12, 12, 12, 12, new Vendor ("japan", "japan", 1234567890)));
            try {
                BookInStock bs = new BookInStock("ISBN562.357561314061", title, "masashi", "konami", 12, 12, 12, 12, 12, 12, new Vendor ("japan", "japan", 1234567890));
                InventoryDatabase.setThreshold(13);
                ArrayList<BookInStock> result = instance.getBooksBelowThreshold();
                ArrayList<String> rstr = new ArrayList<>();
                result.forEach(e-> {
                    rstr.add(e.author);
                        });
                assertTrue(rstr.contains(bs.author));
                instance.removeBookInStock(title, 12);
            }
            catch(Exception e) {
                fail("The test case is a prototype.");
            }
            instance.removeBookInStock(instance.searchBookInStock(null, title, null, null).get(0).getISBN(), 12);
            // TODO review the generated test code and remove the default call to fail.
        }
        catch(Exception e) {
            fail("Addition of test object to inventory has failed");
        }
    }

    /**
     * Test of addtoInventory method, of class InventoryDatabase.
    */
    
    public void testAddtoInventory() throws Exception {
        System.out.println("++++++++addtoInventory++++++++");
        try {
            BookInStock bookInStock = null;
            InventoryDatabase instance = new InventoryDatabase(userid, password);
            bookInStock = instance.searchBookInStock(null, null, null, null).get(0);
            String result = null;
            try {
                result = instance.addtoInventory(bookInStock);
                result = result.substring(0, result.indexOf(" (alread"));
                bookInStock.setCopyNum(bookInStock.getCopyNum()*2);
                System.out.println(bookInStock.getCopyNum()+"--->" + instance.searchBookInStock(result, null, null, null).get(0).getCopyNum());
                assertEquals(bookInStock.getCopyNum(), instance.searchBookInStock(result, null, null, null).get(0).getCopyNum());
            }
            catch(Exception e)  {
                System.out.println(e.getMessage());
                fail("The test case is a prototype.");
            }
            instance.removeBookInStock(result, (int)bookInStock.getCopyNum()/2);
            // TODO review the generated test code and remove the default call to fail.
            
        }
        catch(Exception e) {
            try {
                String title = LocalDateTime.now().toString();
                BookInStock bookInStock = new BookInStock(title, "masashi", "konami", 12, 12, 12, 12, 12, new Vendor("japan", "japan", 1234567890));
                InventoryDatabase instance = new InventoryDatabase(userid, password);
                String result = null;
                try {
                    result = instance.addtoInventory(bookInStock);
                    assertEquals(bookInStock.title, instance.searchBookInStock(result, null, null, null).get(0).title);
                } catch (Exception ex) {
                    fail("The test case is a prototype.");
                }
                instance.removeBookInStock(result, (int) bookInStock.getCopyNum());
            }
            catch(Exception ex) {
                fail("addition of book failed");
            }
        }
        Database d = new Database(userid, password);
            d.commit();
    }
 
    /**
     * Test of removeBookInStock method, of class InventoryDatabase.
     */
    public void testRemoveBookInStock() throws Exception {
        System.out.println("++++++removeBookInStock+++++++");
        InventoryDatabase instance = new InventoryDatabase(userid, password);
            String title = LocalDateTime.now().toString();
            instance.addtoInventory(new BookInStock("ISBN562.357561314061", title, "masashi", "konami", 12, 12, 12, 12, 12, 12, new Vendor ("japan", "japan", 1234567890)));
            try {
                BookInStock bs = new BookInStock("ISBN562.357561314061", title, "masashi", "konami", 12, 12, 12, 12, 12, 12, new Vendor ("japan", "japan", 1234567890));
                instance.searchBookInStock(null, title, "masashi", "kodnami");
                assertTrue(instance.removeBookInStock(instance.searchBookInStock(null, title, null, null).get(0).getISBN(), 10));
                assertTrue(instance.removeBookInStock(instance.searchBookInStock(null, title, null, null).get(0).getISBN(), 2));
            }
            catch(Exception e) {
                // TODO review the generated test code and remove the default call to fail.
                instance.removeBookInStock(title, 12);
                fail("The test case is a prototype.");
            }
    }

    /**
     * Test of searchBookInStock method, of class InventoryDatabase.
     */
    public void testSearchBookInStock() throws Exception {
        
        try {
            System.out.println("+++++searchBookInStock+++++");
            String ISBN = "";
            String title = "";
            String title1 = "";
            String author = "";
            String publish = "";
            InventoryDatabase instance = new InventoryDatabase(userid, password);
            title = LocalDateTime.now().toString();
            instance.addtoInventory(new BookInStock("ISBN562.357561314061", title, (title + title), "konami", 12, 12, 12, 12, 12, 12, new Vendor ("japan", "japan", 1234567890)));
            title1 = LocalDateTime.now().toString();
            instance.addtoInventory(new BookInStock("ISBN562.357561314061", title, (title1+title1), "konami", 12, 12, 12, 12, 12, 12, new Vendor ("japan", "japan", 1234567890)));
            
            ArrayList<BookInStock> result = instance.searchBookInStock(null, title, null, null);
            assertTrue(result.size() == 2);
            instance.removeBookInStock(instance.searchBookInStock(null, title, null, null).get(0).getISBN(), 12);
            instance.removeBookInStock(instance.searchBookInStock(null, title, null, null).get(0).getISBN(), 12);
            // TODO review the generated test code and remove the default call to fail.
        }
        catch(Exception e) {
            fail("The test case is a prototype.");
        }
    }

    /**
     * Test of changeBookInStock method, of class InventoryDatabase.
     */
    public void testChangeBookInStock() throws Exception {
        
        System.out.println("++++++changeBookInStock+++++++");
        try {
            String ISBN = "";
            InventoryDatabase instance = new InventoryDatabase(userid, password);
            String title = LocalDateTime.now().toString();
            instance.addtoInventory(new BookInStock("ISBN562.357561314061", title, "masashi", "konami", 12, 12, 12, 12, 12, 12, new Vendor ("japan", "japan", 1234567890)));
            BookInStock newBs = new BookInStock("ISBN562.357561314061", title + "rick", "masashi", "konami", 12, 12, 12, 12, 12, 12, new Vendor ("japan", "japan", 1234567890));
            ISBN = instance.searchBookInStock(null, title, null, null).get(0).getISBN();
            String result = instance.changeBookInStock(ISBN, newBs);
            assertEquals(instance.searchBookInStock(result, null, null, null).get(0).title
                            , newBs.title);
            // TODO review the generated test code and remove the default call to fail.
            instance.removeBookInStock(result, 12);
        }
        catch(Exception e){
            fail("The test case is a prototype.");
        }
    }

    /**
     * Test of searchBookNotInStock method, of class InventoryDatabase.
     */
    public void testSearchBookNotInStock() throws Exception {
        try {
            System.out.println("++++++searchBookNotInStock+++++");
            String title = "";
            String author = "";
            String publish = "";
            InventoryDatabase instance = new InventoryDatabase(userid, password);
            title = LocalDateTime.now().toString();
            instance.addToRequests(title, author, publish, 12);
            BookNotInStock expResult = new BookNotInStock(title, author, publish, 12, 1);
            BookNotInStock result = instance.searchBookNotInStock(title, author, publish).get(0);
            assertEquals(expResult.title, result.title);
            assertEquals(expResult.costPrice, result.costPrice);
            
            DataHandler d= new DataHandler(jdbcUrl, userid, password);
            d.executeQuery("delete from Requests where title = '" + title +"'");
            d.close();
            // TODO review the generated test code and remove the default call to fail.
        }
        catch(Exception e ) {
            fail("The test case is a prototype.");
        }
    }

    /**
     * Test of addToRequests method, of class InventoryDatabase.
     */
    public void testAddToRequests() throws Exception {
        System.out.println("addToRequests");
        try {
            String title = "";
            String author = "";
            String publish = "";
            InventoryDatabase instance = new InventoryDatabase(userid, password);
            title = LocalDateTime.now().toString();
            instance.addToRequests(title, author, publish, 12);
            BookNotInStock expResult = new BookNotInStock(title, author, publish, 12, 1);
            BookNotInStock result = instance.searchBookNotInStock(title, author, publish).get(0);
            assertEquals(expResult.title, result.title);
            assertEquals(expResult.costPrice, result.costPrice);
            
            DataHandler d= new DataHandler(jdbcUrl, userid, password);
            d.executeQuery("delete from Requests where title = '" + title +"'");
            d.close();
            // TODO review the generated test code and remove the default call to fail.
        }
        catch(Exception e ) {
            fail("The test case is a prototype.");
        }
    }
    
}
