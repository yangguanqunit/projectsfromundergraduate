/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package us.codecraft.webmagic.pipeline;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.logging.Level;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.utils.FilePersistentBase;

/**
 *
 * @author yangguanqun
 */
public class FileMapPipeline extends FilePersistentBase implements Pipeline {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private PrintWriter printWriter;
    private Integer fileNumber=1;
    private File currentFile;

    public FileMapPipeline(String path) {
        setPath(path);
        try {
            printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(getFile(path + "MITDATA_1.txt")), "UTF-8"));
        } catch (IOException ex) {
            System.out.println("write error");
        }
        currentFile = new File("/Volumes/MITDATA/MITDATA_1.txt");
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
//        String path = this.path + PATH_SEPERATOR + task.getUUID() + PATH_SEPERATOR;
//                    PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(getFile(path + DigestUtils.md5Hex(resultItems.getRequest().getUrl()) + ".txt")),"UTF-8"));
        //printWriter.println("url:\t" + resultItems.getRequest().getUrl());
        for (Map.Entry<String, Object> entry : resultItems.getAll().entrySet()) {
            if (entry.getValue() instanceof Iterable) {
                Iterable value = (Iterable) entry.getValue();
                printWriter.println(entry.getKey() + ":");
                for (Object o : value) {
                    printWriter.println(o);
                }
            } else {
                printWriter.println(entry.getKey() + ":" + entry.getValue());
            }
        }
        if(currentFile.length()>51200)
        {
            System.out.println(currentFile.length());
            fileNumber++;
            printWriter.close();
            try {
                printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(getFile(path + "MITDATA_"+fileNumber.toString()+".txt")), "UTF-8"));
        } catch (IOException ex) {
            System.out.println("write error");
        }
            currentFile = new File("/Volumes/MITDATA/"+ "MITDATA_"+fileNumber.toString()+".txt");
            
        }
//        printWriter.close();
        
    }

}
