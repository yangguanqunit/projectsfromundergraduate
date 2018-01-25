/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ldu.se.dhcpserver;

import org.apache.log4j.Logger;

/**
 *
 * @author yangguanqun
 */
public class WriteLogs {

    private Logger logger;
    public WriteLogs(Logger logger) {
        this.logger = logger;
    }
    public void writeLogs(String WAY,String MAC,String IP){
        
        logger.info("way:"+WAY+";MAC:"+MAC+";IP:"+IP);
        
    }
    public void writeLogs(String WAY,String subnet,String first,String last){
        
        logger.info("way:"+WAY+";subnet:"+subnet+";"+first+" "+last);
        
    }
    
    
    
}
