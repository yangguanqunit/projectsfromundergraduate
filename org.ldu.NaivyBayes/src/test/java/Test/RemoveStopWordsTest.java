/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import java.io.IOException;
import org.ldu.naivybayes.RemoveStopWords;

/**
 *
 * @author yangguanqun
 */
public class RemoveStopWordsTest {
    public static void main(String[] args) throws IOException{
        RemoveStopWords rsw = new RemoveStopWords();
        System.out.print(rsw.rmStopWords("asdas-a"));
        
    }
}
