package ldu.se.dhcpserver;


import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import ldu.se.dhcpserver.Writteninto;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sherlos
 */
public class test {
    
    protected Object exec(String cmd) {  
            try {  
                String[] cmdA = { "/bin/sh", "-c", cmd };  
                Process process = Runtime.getRuntime().exec(cmdA);  
                LineNumberReader br = new LineNumberReader(new InputStreamReader(  
                        process.getInputStream()));  
                StringBuffer sb = new StringBuffer();  
                String line;  
             while ((line = br.readLine()) != null) {  
                    System.out.println(line);  
                    sb.append(line).append("\n");  
                }  
                return sb.toString();  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
            return null;  
        }
    
    public void excmd(String command) throws IOException, InterruptedException
    {
        String cmd;
        //Writteninto wi = new Writteninto();
        //wi.test();
        //exec("ls /root").toString();
        cmd=exec(command).toString();
        
        System.out.print(cmd);
    }
    
}
