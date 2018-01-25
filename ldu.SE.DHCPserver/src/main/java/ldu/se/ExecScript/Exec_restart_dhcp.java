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
public class Exec_restart_dhcp extends ExecScript {
    
    
    public void execute(String info) throws IOException, InterruptedException{
        cmd="service isc-dhcp-server restart";
        excmd(cmd);
        
    }
    
}
