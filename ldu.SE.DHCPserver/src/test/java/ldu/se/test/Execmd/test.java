/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ldu.se.test.Execmd;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import ldu.se.ExecScript.ExecScript;
import ldu.se.ExecScript.Exec_Backup_dhcpConf;

/**
 *
 * @author yangguanqun
 */
public class test {
    
    public static void main(String[] args){
        ExecScript execute = new Exec_Backup_dhcpConf();
        
        try {
            execute.execute(null);
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(execute.getResult());
        
        
        
    }
    
    
}
