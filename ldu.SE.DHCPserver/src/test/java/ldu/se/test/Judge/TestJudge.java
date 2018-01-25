/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ldu.se.test.Judge;
import java.io.IOException;
import ldu.se.tools.*;
import ldu.se.ExecScript.*;
import ldu.se.dhcpserver.test;
/**
 *
 * @author root
 */
public class TestJudge {
    public static void main(String[] args) throws IOException, InterruptedException
    {
//        getBroadcast gb = new getBroadcast();
//        System.out.print(gb.shift("192.168.1.1", "1", "255.255.255.0"));
//         System.out.print(gb.shift("192.168.1.1", "255", "255.255.255.0"));
//          System.out.print(gb.shift("192.168.1.1", "0", "255.255.255.0"));
//        Exec_Backup_dhcpConf ebd=new Exec_Backup_dhcpConf();
//        Exec_restart_dhcp erd = new Exec_restart_dhcp();
//        Exec_reset_host erh = new Exec_reset_host();
//        ebd.exec_backup();
//        erh.exec_Reset_host("192.168.144.101");
//        erd.exec_Restart_dhcp();
//        
        GetSubnetMask gsm = new GetSubnetMask();
        System.out.print(gsm.getSubnetMask("130.168.1.1"));
    }
    
}
