/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Databases;

import Employee.Employee;
import Employee.Manager;
import Employee.Owner;
import Employee.SalesClerk;
import Exceptions.MoreThanOneInstance;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.function.Predicate;
import junit.framework.TestCase;

/**
 *
 * @author ridri
 */
public class EmployeeDatabaseTest extends TestCase {
    
    private String userid = "SYSTEM";
    private String password = "system";
    private String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:XE";
    
    public EmployeeDatabaseTest(String testName) {
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
     * Test of isSalesClerk method, of class EmployeeDatabase.
     */
    public void testIsSalesClerk() throws Exception {
        try {
            System.out.println("+++++isSalesClerk+++++");
            String salId = "";
            EmployeeDatabase instance = new EmployeeDatabase(userid, password);
            try {
                salId = instance.getEmployeeList(e->e.getPosition().equals("SAL")).get(0).getEmp_id();
                assertTrue(instance.isSalesClerk(salId));
            }
            catch(Exception e) {
                //no sales clerk
                assertTrue(true);
            }
            // TODO review the generated test code and remove the default call to fail.
        }
        catch(Exception e) {
            fail("The test case is a prototype.");
        }
    }

    /**
     * Test of isEmployee method, of class EmployeeDatabase.
     */
    public void testIsEmployee() throws Exception {
        try {
            System.out.println("++++isEmployee+++++");
            String empId = "";
            EmployeeDatabase instance = new EmployeeDatabase(userid, password);
            try {
                empId = instance.getEmployeeList(e -> true).get(0).getEmp_id();
                assertTrue(instance.isEmployee(empId));
            } catch (Exception e) {
                assertTrue(true);
            }
        } // TODO review the generated test code and remove the default call to fail.
        catch (Exception e) {
            fail("The test case is a prototype.");
        }
    }

    /**
     * Test of isOwner method, of class EmployeeDatabase.
     */
    public void testIsOwner() throws Exception {
        try {
            System.out.println("+++++isOwner+++++");
            String ownId = "";
            EmployeeDatabase instance = new EmployeeDatabase(userid, password);
            try {
                ownId = instance.getEmployeeList(e->e.getPosition().equals("OWN")).get(0).getEmp_id();
                assertTrue(instance.isOwner(ownId));
            }
            catch(Exception e) {
                //no owner
                assertTrue(true);
            }
            // TODO review the generated test code and remove the default call to fail.
        }
        catch(Exception e) {
            fail("The test case is a prototype.");
        }
    }

    /**
     * Test of isManager method, of class EmployeeDatabase.
     */
    public void testIsManager() throws Exception {
        try {
            System.out.println("+++++isManager+++++");
            String manId = "";
            EmployeeDatabase instance = new EmployeeDatabase(userid, password);
            try {
                manId = instance.getEmployeeList(e->e.getPosition().equals("MAN")).get(0).getEmp_id();
                assertTrue(instance.isManager(manId));
            }
            catch(Exception e) {
                //no sales clerk
                assertTrue(true);
            }
            // TODO review the generated test code and remove the default call to fail.
        }
        catch(Exception e) {
            fail("The test case is a prototype.");
        }
    }
    
    /**
     * Test of addManager method, of class EmployeeDatabase.
     */
    public void testAddManager() throws Exception {
        try {
            System.out.println("+++++addManager++++");
            Manager employee = new Manager("hell", "none", 120.0, 1234567890, "none");
            EmployeeDatabase instance = new EmployeeDatabase(userid, password);
            try {
                String result = instance.addManager(employee);
                assertEquals("hell", instance.getManager().getName());
            } catch (MoreThanOneInstance e) {
                //only one unstance of manager is allowed in database
                assertTrue(true);
            }
            // TODO review the generated test code and remove the default call to fail.
        } catch (Exception e) {
            fail("The test case is a prototype.");
        }
    }
    

    /**
     * Test of addEmployee method, of class EmployeeDatabase.
     */
    public void testAddEmployee() throws Exception {
        try {
            System.out.println("+++++++addEmployee++++++");
            String name = LocalDateTime.now().toString();
            name = name.substring(4);
            Employee employee = new SalesClerk(name, "none", 1200, 1234567890, "none");
            EmployeeDatabase instance = new EmployeeDatabase(userid, password);
            String expResult = name;
            String result = instance.addEmployee(employee);
            assertEquals(expResult, instance.getEmployeeList(e -> e.getName().equals(employee.getName())).get(0).getName());
            String prop[]= {"emp_name"};
            String value[] = {name};
            instance.delEmployee(prop, value);
            // TODO review the generated test code and remove the default call to fail.
        } catch (Exception e) {
            fail("The test case is a prototype.");
        }
    }
    
    /**
     * Test of getEmployee method, of class EmployeeDatabase.
     */
    public void testGetEmployee() throws Exception {
        try {
            System.out.println("+++++getEmployee+++++");

            EmployeeDatabase instance = new EmployeeDatabase(userid, password);
            String name = LocalDateTime.now().toString().substring(4);
            Employee expResult = new SalesClerk(name, "none", 1200, 1234567890, "none");
            
            String[] property = {"emp_name"};
            String[] value = {name};

            instance.addEmployee(expResult);
            Employee result = instance.getEmployee(property, value);
            
            assertEquals(expResult.getName(), result.getName());
            assertEquals(expResult.getPosition(), result.getPosition());
            assertEquals(expResult.getAddress(), result.getAddress());
            assertEquals(expResult.getEmail(), result.getEmail());
            // TODO review the generated test code and remove the default call to fail.
            
            instance.delEmployee(property, value);
        } catch (Exception e) {
            fail("The test case is a prototype.");
        }
    }
    

    /**
     * Test of getManager method, of class EmployeeDatabase.
     */
    public void testGetManager() throws Exception {
        System.out.println("+++++getManager+++++");
        try {
            EmployeeDatabase instance = new EmployeeDatabase(userid, password);
            Manager result = instance.getManager();
            assertTrue(result.getPosition().equals("MAN"));
            // TODO review the generated test code and remove the default call to fail.
        } catch (Exception e) {
            fail("The test case is a prototype.");
        }
    }
    

    /**
     * Test of getOwner method, of class EmployeeDatabase.
     */
    public void testGetOwner() throws Exception {
        System.out.println("+++++getOwner++++++");
        try {
            EmployeeDatabase instance = new EmployeeDatabase(userid, password);
            Owner result = instance.getOwner();
            assertTrue(result.getPosition().equals("OWN"));
            // TODO review the generated test code and remove the default call to fail.
        } catch (Exception e) {
            fail("The test case is a prototype.");
        }
    }
    

    /**
     * Test of getEmployeeList method, of class EmployeeDatabase.
     */
    public void testGetEmployeeList() throws Exception {
        try {
            System.out.println("+++++getEmployeeList++++++");

            EmployeeDatabase instance = new EmployeeDatabase(userid, password);
            String name = LocalDateTime.now().toString().substring(4);
            Employee expResult = new SalesClerk(name, "none", 1200, 1234567890, "none");
            
            String[] property = {"emp_name"};
            String[] value = {name};

            instance.addEmployee(expResult);
            Employee result = instance.getEmployeeList(e->e.getName().equals(name)).get(0);
            
            assertEquals(expResult.getName(), result.getName());
            assertEquals(expResult.getPosition(), result.getPosition());
            assertEquals(expResult.getAddress(), result.getAddress());
            assertEquals(expResult.getEmail(), result.getEmail());
            // TODO review the generated test code and remove the default call to fail.
            
            instance.delEmployee(property, value);
        } catch (Exception e) {
            fail("The test case is a prototype.");
        }
    }
    

    /**
     * Test of changeEmployee method, of class EmployeeDatabase.
     */
    public void testChangeEmployee() throws Exception {
        System.out.println("+++++changeEmployee+++++");
        try {
            EmployeeDatabase instance = new EmployeeDatabase(userid, password);
            String name = LocalDateTime.now().toString().substring(4);
            name = name.substring(13);
            Employee expResult = new SalesClerk(name, "none", 1200, 1234567890, "none");
            instance.addEmployee(expResult);
            expResult.setName(name+name);

            String[] property = {"emp_name"};
            String[] value = {name};

            String result = instance.changeEmployee(property, value, expResult);
            value[0] = name + name;
            assertEquals(expResult.getName(), instance.getEmployee(property, value).getName());
            // TODO review the generated test code and remove the default call to fail.
            System.out.println("passed");
            instance.delEmployee(property, value);
        } catch (Exception e) {
            System.out.println(e.getClass());
            fail("The test case is a prototype.");
        }
    }
    
    
}
