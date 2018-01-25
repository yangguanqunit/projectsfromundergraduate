/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ldu.se.test.WriteIntoLog;

import ldu.se.dhcpserver.*;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author yangguanqun
 */
public class testLogs {

    public static void main(String[] args) {
        try {
            PropertyConfigurator.configure("/Users/yangguanqun/NetBeansProjects/ldu.SE.DHCPserver/src/test/java/ldu/se/test/WriteIntoLog");
        }
        catch(Exception e)
        {
            System.out.print("test");
        }
    }

}
