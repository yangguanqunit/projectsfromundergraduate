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
public class Exec_reset_host extends ExecScript {
    
    public void execute(String hostip) throws IOException, InterruptedException
    {
        cmd="ifconfig eth0 "+hostip;
        excmd(cmd);
    }
}
