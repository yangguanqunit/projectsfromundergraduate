/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ldu.se.ExecScript;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

/**
 *
 * @author root
 */
abstract public class ExecScript {
    
    protected String cmd;
    protected String result;
    
    
    protected Object exec(String cmd) {  
            try {  
                String[] cmdA = { "/bin/sh", "-c", cmd };  
                Process process = Runtime.getRuntime().exec(cmdA);  
                LineNumberReader br = new LineNumberReader(new InputStreamReader(  
                        process.getInputStream()));  
                StringBuffer sb = new StringBuffer();  
                String line;  
             while ((line = br.readLine()) != null) {  
                    //System.out.println(line);  
                    sb.append(line).append("\n");  
                }  
                return sb.toString();  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
            return null;  
        }
    
    protected void excmd(String command) throws IOException, InterruptedException
    {
        result=exec(command).toString(); 
        //System.out.println(command);
    }
    
    abstract public void execute(String info) throws IOException, InterruptedException;
    
    public String getResult()
    {
        return this.result;
    }
}
