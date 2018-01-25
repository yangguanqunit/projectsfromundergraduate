/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ldu.se.dhcpserver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;
import ldu.se.tools.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author root
 */
public class Writteninto extends WriteIntoConf {
    WriteLogs writeLogs;

    public Writteninto() {
        PropertyConfigurator.configure("/Users/yangguanqun/NetBeansProjects/ldu.SE.DHCPserver/src/main/java/ldu/se/log4j/log4j.properties");
        Logger logger = Logger.getLogger(Writteninto.class);
        writeLogs = new WriteLogs(logger);
        
    }

    public void dyauto() throws IOException {

        
        //System.out.print(getStaticInfo());
        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
        bw.write(getStaticInfo());
        bw.close();
        ami.added(path, mlt);
        writeLogs.writeLogs(WAY, subnet, first, last);
        
    }

    public void manual(Map map) throws IOException {
        ami.added(path, gb.shift(map.get("ip").toString(), "0", gsm.getSubnetMask(map.get("netmask").toString())), static_info_dynamic);
        writeLogs.writeLogs(WAY, MAC, ip);
    }
}
