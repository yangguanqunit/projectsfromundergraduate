/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ldu.se.ExecScript;

import java.io.IOException;

/**
 *
 * @author root
 */
public class Exec_Backup_dhcpConf extends ExecScript {
    String cmd = "cp /etc/dhcp/dhcpd.conf /etc/dhcp/dhcpd.conf.bak";
    
    
    @Override
    public void execute(String info) throws IOException, InterruptedException
    {
        excmd(cmd);
//        System.out.print(cmd);
        
    }
    
}
