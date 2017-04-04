/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Databases;

import Book.Transaction;
import SQL_DataHandler.DataHandler;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import junit.framework.TestCase;
import oracle.jdbc.pool.OracleDataSource;

/**
 *
 * @author ridri
 */
public class SalesDatabaseTest extends TestCase {
    
    public SalesDatabaseTest(String testName) {
        super(testName);
    }
    private String localhost = "localhost";
    private String username = "SYSTEM";
    private String password = "system";
    private String jdbcUrl = "jdbc:oracle:thin:@" + localhost + ":1521:XE";
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        System.out.println("this will delete all the data in databases...");
        DataHandler d;
        d = new DataHandler("jdbc:oracle:thin:@" + localhost + ":1521:XE", username, password);
        try {
            d.executeQuery("insert into Sales values('TRN292264050014062','ISBN412.410182452061',4,'10-APR-16','rick','none','ridk',1234567890,'SAL182.243250014061')");
        }
        catch(Exception e) {
            System.out.println("Valur in already present");
        }
        d.close();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    

    /**
     * Test of getTransaction method, of class SalesDatabase.
     * @throws java.lang.Exception
     */
    public void testGetTransaction() throws Exception {
        System.out.println("getTransaction");
        String cusName = "";
        LocalDateTime date = null;
        String transId = "TRN292264050014062";
        SalesDatabase instance = new SalesDatabase(username, password);
        ArrayList<Transaction> expResult = new ArrayList<>();
        expResult.add(new Transaction("TRN292264050014062",new InventoryDatabase(username, password).searchBookInStock("ISBN412.410182452061", null, null, null).get(0)
                                        ,4,LocalDateTime.parse("2016-04-10T00:00:00"),"rick","none","ridk",1234567890,"SAL182.243250014061"));
        ArrayList<Transaction> result = instance.getTransaction(cusName, date, transId);
        assertEquals(expResult.get(0).getTransId(), result.get(0).getTransId());
        // TODO review the generated test code and remove the default call to fail.
        
        DataHandler d = new DataHandler();
        d.executeQuery("delete from Sales where trans_id = 'TRN292264050014062'");
        d.close();
    }
    
}
