/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ldu.naivybayes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author yangguanqun
 */
public class RemoveStopWords {
    private ArrayList<String> stopWordsList;
    public RemoveStopWords() throws FileNotFoundException, IOException{
        File file = new File("/Users/yangguanqun/groceries/txt/stopwords.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        stopWordsList = new ArrayList<>();
        String tmp;
        while((tmp=reader.readLine())!=null){
            stopWordsList.add(tmp);
        }
    }
          
    public String rmStopWords(String input){
        String afterRm="";
        String raw = input.replaceAll("\\pP", " ").toLowerCase();
        for(String tmp : raw.split(" ")){
            if(stopWordsList.contains(tmp)){
                continue;
            }
            afterRm+=tmp+" ";
        }   
        return afterRm.toLowerCase();
    }
    
}
