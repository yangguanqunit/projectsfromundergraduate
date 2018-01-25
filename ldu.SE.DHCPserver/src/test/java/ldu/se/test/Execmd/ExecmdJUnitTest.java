/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ldu.se.test.Execmd;

import java.io.IOException;
import ldu.se.ExecScript.ExecScript;
import ldu.se.ExecScript.Exec_Backup_dhcpConf;
import ldu.se.ExecScript.Exec_reset_host;
import ldu.se.ExecScript.Exec_restart_dhcp;
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
public class ExecmdJUnitTest {
    static ExecScript exec_backup;
    static ExecScript exec_resethost;
    static ExecScript exec_restartnetwork;
    
    public ExecmdJUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
        exec_backup = new Exec_Backup_dhcpConf();
        exec_resethost = new Exec_reset_host();
        exec_restartnetwork = new Exec_restart_dhcp();
        
        
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
    public void exectest(){
        try{
            exec_backup.execute(null);
            exec_resethost.execute("192.168.145.1");
            exec_restartnetwork.execute(null);
        }
        catch(IOException | InterruptedException e)
        {
            fail();
        }
        
        
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
