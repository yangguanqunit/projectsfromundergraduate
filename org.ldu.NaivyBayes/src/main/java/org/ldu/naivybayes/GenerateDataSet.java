package org.ldu.naivybayes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class GenerateDataSet {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        File file = new File("/Users/yangguanqun/groceries/train/MITDATA_1.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        ArrayList<String> trainSet = new ArrayList<>();
        ArrayList<String> testSet = new ArrayList<>();
        ArrayList<String> cmpSet = new ArrayList<>();
        String tmp = null;
        HashMap<String, ArrayList<String>> hash = new HashMap<>();
        int index;
        int line = 0;
        while ((tmp = reader.readLine()) != null) {
//            System.out.println(++line);
            index = tmp.indexOf(":");
            String key = tmp.substring(0, index);
            String value = tmp.substring(index + 1);
            if (!hash.containsKey(key)) {
                ArrayList<String> values = new ArrayList<>();
                hash.put(key, values);
                hash.get(key).add(value);
            } else {
                hash.get(key).add(value);
            }
        }
        RemoveStopWords RSW = new RemoveStopWords();
        Random rand = new Random();

        for (String K : hash.keySet()) {
            int boundary = 0;
            int test_index = 0;
            int k = 1;
            System.out.print(K);
            while (boundary < hash.get(K).size()) {
                test_index = boundary + rand.nextInt(3);
                for (int i = boundary; i < boundary + 4; i++) {
                    if (i == test_index) {
                        testSet.add(RSW.rmStopWords(hash.get(K).get(i)));
                        cmpSet.add(K + ":" + RSW.rmStopWords(hash.get(K).get(i)));
                    } else {
                        trainSet.add(K + ":" + RSW.rmStopWords(hash.get(K).get(i)));
                    }
                }
                boundary = 4 * (k++);
            }
        }
        File trainSet_File = new File("/Users/yangguanqun/groceries/train/train.txt");
        File testSet_File = new File("/Users/yangguanqun/groceries/test/test.txt");
        File cmpSet_File = new File("/Users/yangguanqun/groceries/test/cmp.txt");
        if(!trainSet_File.exists())
            trainSet_File.createNewFile();
        else{
            trainSet_File.delete();
            trainSet_File.createNewFile();
        }
        if(!testSet_File.exists())
            testSet_File.createNewFile();
        else{
            testSet_File.delete();
            testSet_File.createNewFile();
        }
        if(!cmpSet_File.exists())
            cmpSet_File.createNewFile();
        else{
            cmpSet_File.delete();
            cmpSet_File.createNewFile();
        }
        BufferedWriter Bw = new BufferedWriter(new FileWriter(trainSet_File,true));
        for(String train : trainSet){
            Bw.write(train+"\n");
            
        }
        Bw.flush();
        BufferedWriter TBW = new BufferedWriter(new FileWriter(testSet_File,true));
        for(String test : testSet){
            TBW.write(test+"\n");
        }
        Bw = new BufferedWriter(new FileWriter(cmpSet_File,true));
        for(String cmp : cmpSet){
            Bw.write(cmp+"\n");
        }
        TBW.close();
        Bw.close();
        reader.close();   
    }
}
