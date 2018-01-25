/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ldu.se.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author yangguanqun
 */
public class AddManualInfo {

    ArrayList<String> fileArry;
    ArrayList<String> modifiedArray;
    File file;
    Regular rg = new Regular();
    boolean isModified;

    private ArrayList readFile(String path) throws FileNotFoundException, IOException {
        String tmp;
        fileArry = new ArrayList<>();
        modifiedArray = new ArrayList<>();

        file = new File(path);
        if (file.exists()) {
            InputStreamReader ips = new InputStreamReader(new FileInputStream(file));
            BufferedReader BR = new BufferedReader(ips);
            while ((tmp = BR.readLine()) != null) {

                fileArry.add(tmp + "\n");
            }
            BR.close();
            ips.close();
        }
        return fileArry;

    }

    private void writeFile(String context) {

        try {
            BufferedWriter BW = new BufferedWriter(new FileWriter(file));
            BW.write(context);
            BW.close();
        } catch (IOException e) {
        }

    }

    public String added(String path, String subnet, String confInfo) throws IOException {
        String result = null;
        readFile(path);
        isModified = false;

        int i = 0;

        for (String tmp : fileArry) {
            modifiedArray.add(tmp);
            i++;
            if (rg.judge("^subnet " + subnet + "[\\S|\\s]*[\\{|\\n]$", tmp)) {
//                System.out.print(tmp+"qq");
//                System.out.print("abc");
                modifiedArray.add(confInfo);
                isModified = !isModified;
            }

        }
        for (String tmp : modifiedArray) {
            result = result + tmp;
        }
        if (isModified) {
            writeFile(result.substring(4, result.length()));
        }
        return result.substring(4, result.length());
    }

    public void added(String path, String confInfo) throws IOException {
        String result = null;
        readFile(path);
        isModified = false;
        String mlt = "max-lease-time ";

        for (String tmp : fileArry) {
            //System.out.print(tmp);
            if (rg.judge("^\\s*max-lease-time[\\s|\\d]*[;|\\s]*", tmp)) {
//                System.out.print(tmp+"qq");
                //System.out.print("abc");
                modifiedArray.add(mlt + confInfo + ";\n");
                isModified = !isModified;
                continue;
            }
            modifiedArray.add(tmp);

        }
        for (String tmp : modifiedArray) {
            result = result + tmp;
        }
        if (isModified) {
            writeFile(result.substring(4, result.length()));
        }

    }

}
