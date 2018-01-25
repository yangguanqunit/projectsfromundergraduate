/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ldu.se.dhcpserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ldu.se.ExecScript.Exec_Backup_dhcpConf;
import ldu.se.ExecScript.Exec_reset_host;
import ldu.se.ExecScript.Exec_restart_dhcp;

/**
 *
 * @author yangguanqun
 */
public class Artificial extends HttpServlet {

    protected String ipPool_start;
    protected String ipPool_end;
    protected String netMask;
    protected String MAC;
    protected String primary_DNS;
    protected String vice_DNS;
    protected String ip;
    protected Integer lease;
    protected HashMap map;
    protected Writteninto wi;
    Exec_Backup_dhcpConf ebd = new Exec_Backup_dhcpConf();
    Exec_restart_dhcp erd = new Exec_restart_dhcp();
    Exec_reset_host erh = new Exec_reset_host();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        map = new HashMap();
        wi = new Writteninto();

        map.put("ip", req.getParameter("ip"));
        map.put("MAC", req.getParameter("mac"));
        map.put("netmask", req.getParameter("netmask"));
        map.put("primary", req.getParameter("primary"));
        map.put("vice", req.getParameter("vice"));
        wi.notifyStaticInfo(map);
        if (wi.deDuplicate()) {
            PrintWriter out = resp.getWriter();
            out.print("<script>alert('has existed');history.back();</script>");

        } else {
            try {
                ebd.execute(null);
                wi.manual(this.map);
                erd.execute(null);
            } catch (InterruptedException ex) {
                Logger.getLogger(Artificial.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    private void test() {
        System.out.print(ip + MAC + netMask + primary_DNS + vice_DNS);
    }

}
