/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ldu.se.dhcpserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ldu.se.ExecScript.*;

/**
 *
 * @author yangguanqun
 */
public class DynamicConf extends ConfBase {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        map = new HashMap();
        wi = new Writteninto();
        map.put("firstname", req.getParameter("firstname"));
        map.put("lastname", req.getParameter("lastname"));
        map.put("lease", req.getParameter("lease"));
        map.put("netmask", req.getParameter("netmask"));
        map.put("primary", req.getParameter("primary"));
        map.put("vice", req.getParameter("vice"));
        map.put("MAC", "0");
        wi.notifyStaticInfo(map);
        
        if (wi.deDuplicate()) {
            PrintWriter out = resp.getWriter();
            out.print("<script>alert('has existed');history.back();</script>");

        } else {
            try {
                ebd.execute(null);
                wi.dyauto();
                erd.execute(null);
            } catch (InterruptedException ex) {
                Logger.getLogger(DynamicConf.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    private void test() {
        System.out.print(ipPool_start + ipPool_end + lease + netMask + primary_DNS + vice_DNS);
    }

}
