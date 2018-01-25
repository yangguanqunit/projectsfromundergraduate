/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ldu.se.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author yangguanqun
 */
public class Regular {
    
    
    public boolean judge(String rgx, String input){
        
    
    Pattern pattern = Pattern.compile(rgx);
    
    Matcher matcher = pattern.matcher(input);
    
    boolean rs = matcher.matches();
    return rs;
    }
    
    
}
