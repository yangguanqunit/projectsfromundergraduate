/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ldu.se.dhcpserver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
//import ldu.se.tools.JudgeType;
import ldu.se.tools.*;

/**
 *
 * @author root
 */
public class WriteIntoConf {
    
    String subnet;
    String netmask;
    String first;
    String last;
    String broadcast;
    String routers;
    String lt = "600";
    String mlt = "7200";
    String MAC;
    String ip;
    AddManualInfo ami;
    String path;
    GetSubnetMask gsm;
    getBroadcast gb;
    String WAY;
    String path_log;
    Regular rg;
    
    public WriteIntoConf() {
        rg = new Regular();
        gsm = new GetSubnetMask();
        gb = new getBroadcast();
        ami = new AddManualInfo();
        path = "/Users/yangguanqun/groceries/test.conf";
        path_log = "/Users/yangguanqun/logs/info.log";
    }
    
    protected String static_info_line1 = "ddns-update-style interim;\n";
    protected String static_info_line2 = "ignore client-updates;\n";
    protected String static_info_dynamic;
    
    protected void notifyStaticInfo(Map map) {
        WAY = null;
        if (!map.get("MAC").equals("0")) {
            MAC = map.get("MAC").toString();
            ip = map.get("ip").toString();
            static_info_dynamic = "\n" + "    host virtual {\n"
                    + "           hardware ethernet " + MAC + ";\n"
                    + "           fixed-address " + ip + ";\n}\n";
            WAY = "M";
        } else if (!map.get("lease").equals("0")) {
            this.first = map.get("firstname").toString();
            this.last = map.get("lastname").toString();
            this.netmask = gsm.getSubnetMask(first);
            //System.out.println("this + " + this.netmask);
            this.mlt = map.get("lease").toString();
            this.broadcast = gb.shift(first, "255", netmask);
            this.routers = gb.shift(first, "1", netmask);
            this.subnet = gb.shift(first, "0", netmask);
            static_info_dynamic = "subnet " + subnet + " netmask " + netmask + "{\n"
                    + " range " + first + " " + last + ";\n"
                    + " option broadcast-address " + broadcast + ";\n"
                    + " option routers " + routers + ";\n"
                    + " option subnet-mask " + netmask + ";\n}\n";
            WAY = "D";
        } else {
            this.first = map.get("firstname").toString();
            this.last = map.get("lastname").toString();
            this.netmask = gsm.getSubnetMask(first);
            //this.lt = map.get("lease").toString();
            this.mlt = "172800";
            this.broadcast = gb.shift(first, "255", netmask);
            this.routers = gb.shift(first, "1", netmask);
            this.subnet = gb.shift(first, "0", netmask);
            static_info_dynamic = "subnet " + subnet + " netmask " + netmask + "{\n"
                    + " range " + first + " " + last + ";\n"
                    + " option broadcast-address " + broadcast + ";\n"
                    + " max-lease-time " + mlt + ";\n"
                    + " option routers " + routers + ";\n"
                    + " option subnet-mask " + netmask + ";\n}\n";
            WAY = "A";
        }
    }
    
    public boolean deDuplicate() throws FileNotFoundException {
        File file = new File(path_log);
        ArrayList<String> logs = new ArrayList();
        String tmp;
        boolean isExisted = false;
        if (file.exists()) {
            try {
                InputStreamReader ips = new InputStreamReader(new FileInputStream(file));
                BufferedReader BR = new BufferedReader(ips);
                while ((tmp = BR.readLine()) != null) {
                    if (rg.judge("[\\s|\\S]*subnet:" + subnet + "[\\S|\\s]*", tmp) || rg.judge("[\\s|\\S]*MAC:" + MAC + "[\\S|\\s]*", tmp)) {
                        isExisted = true;
                    }
                }
                BR.close();
                ips.close();
            } catch (IOException e) {
                
            }
            
        }
        return isExisted;
    }
    
    String getHostname() {
        return this.routers;
    }
    
    public String getStaticInfo() {
        return static_info_dynamic;
    }
    
}
