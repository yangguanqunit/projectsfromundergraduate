/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ldu.se.dhcpserver;

import javax.servlet.http.HttpServlet;

/**
 *
 * @author yangguanqun
 */
public class GetNetworkInfo extends HttpServlet {

    protected String ipPool_start;
    protected String ipPool_end;
    protected String netMask;
    protected String MAC;
    protected String primary_DNS;
    protected String vice_DNS;
    protected String ip;
    protected Integer lease;

    public String getIpPool_start() {
        return ipPool_start;
    }

    public String getIpPool_end() {
        return ipPool_end;
    }

    public Integer getLease() {
        return lease;
    }

    public String getNetMask() {
        return netMask;
    }

    public String getMAC() {
        return MAC;
    }

    public String getPrimary_DNS() {
        return primary_DNS;
    }

    public String getVice_DNS() {
        return vice_DNS;
    }

    public String getIp() {
        return ip;
    }

}
