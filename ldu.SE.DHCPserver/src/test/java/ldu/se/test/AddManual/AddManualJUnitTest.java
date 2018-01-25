/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ldu.se.test.AddManual;

import java.io.IOException;
import ldu.se.tools.AddManualInfo;
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
public class AddManualJUnitTest {
    static AddManualInfo addManulaInfo;
    
    public AddManualJUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        addManulaInfo = new AddManualInfo();
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void addManualInfotest() {
        String result=null;
        try{
            result = addManulaInfo.added("/Users/yangguanqun/groceries/test.conf", "192.168.144.0", "Test infomation");
//            result = addManulaInfo.added("/Users/yangguanqun/groceries/", "192.168.144.0", "Test infomation");
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally{
            assertNotNull(result);
        }
        
        
    }
}
