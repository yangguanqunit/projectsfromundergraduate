/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ldu.se.test.GetSubnetMask;

import ldu.se.tools.GetSubnetMask;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author yangguanqun
 */
public class GetSubnetMaskJUnitTest {
    static GetSubnetMask getSunbnetMask;
    
    public GetSubnetMaskJUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        getSunbnetMask = new GetSubnetMask();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testGetSubnetMask(){
        String address_1 = "123.143.12.1";
        String address_2 = "146.13.12.1";
        String address_3 = "202.43.2.1";
        String result_1;
        String result_2;
        String result_3;
        result_1 = getSunbnetMask.getSubnetMask(address_1);
        result_2 = getSunbnetMask.getSubnetMask(address_2);
        result_3 = getSunbnetMask.getSubnetMask(address_3);
        assertEquals("255.0.0.0", result_1);
//        assertEquals("255.255.0.0", result_1);
        assertEquals("255.255.0.0", result_2);
        assertEquals("255.255.255.0", result_3);        
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
