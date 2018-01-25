/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ldu.se.test.GetSubnetMask;
import ldu.se.tools.*;
/**
 *
 * @author yangguanqun
 */
public class test {
    
    public static void main(String[] args){
        GetSubnetMask gsm = new GetSubnetMask();
        System.out.print(gsm.getSubnetMask("120.168.1.1"));
        
        
    }
    
}
