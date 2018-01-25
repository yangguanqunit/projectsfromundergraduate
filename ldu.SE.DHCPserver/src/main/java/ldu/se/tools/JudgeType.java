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
public class JudgeType {
    
    public String judgeType(String netmask)
    {
        String[] firstByte=netmask.split("\\.");
        //System.out.println(" show me in here "+firstByte.length);
        if(firstByte[0].equals(firstByte[1])&&firstByte[1].equals(firstByte[2])&&firstByte[0].equals("255"))
        {
            return "C";
        }
        else if(firstByte[0].equals(firstByte[1])&&firstByte[0].equals("255"))
        {
            return "B";
        }
        else if(firstByte[0].equals("255"))
        {
            return "A";
        }
        else 
            return null;
        
    }
    
}
