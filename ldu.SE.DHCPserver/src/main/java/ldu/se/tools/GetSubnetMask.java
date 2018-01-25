/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ldu.se.tools;

/**
 *
 * @author root
 */
public class GetSubnetMask {
     String subnetmask;
    
    public String getSubnetMask(String ip)
            
    {
        Integer fByte=Integer.parseInt(ip.split("\\.")[0]);
        
        if(fByte<=127)
            subnetmask="255.0.0.0";
        else if(127<fByte&&fByte<=191)
            subnetmask="255.255.0.0";
        else if(fByte>191)
            subnetmask="255.255.255.0";
        
        return subnetmask;
       
        
    }
    
}
