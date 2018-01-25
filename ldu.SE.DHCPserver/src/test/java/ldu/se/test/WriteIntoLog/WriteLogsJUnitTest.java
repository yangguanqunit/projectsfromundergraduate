/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ldu.se.test.WriteIntoLog;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;
import java.lang.ExceptionInInitializerError;

/**
 *
 * @author yangguanqun
 */
public class WriteLogsJUnitTest {

    Logger logger;

    public WriteLogsJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() {
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
    public void testLog() {
        logger =null;
        try {
            PropertyConfigurator.configure("/Users/yangguanqun/NetBeansProjects/ldu.SE.DHCPserver/src/test/java/ldu/se/test/WriteIntoLog/log4j.properties");
            logger=Logger.getLogger(WriteLogsJUnitTest.class);
        } catch (ExceptionInInitializerError e) {
            throw new ExceptionInInitializerError();
        }
        finally{
            assertNotNull(logger);
        }
        
    }

}
