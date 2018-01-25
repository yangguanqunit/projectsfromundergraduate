/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ldu.se.dhcpserver;

import java.util.Map;
import javax.servlet.http.HttpServlet;
import ldu.se.ExecScript.ExecScript;
import ldu.se.ExecScript.Exec_Backup_dhcpConf;
import ldu.se.ExecScript.Exec_reset_host;
import ldu.se.ExecScript.Exec_restart_dhcp;

/**
 *
 * @author yangguanqun
 */
public class ConfBase extends HttpServlet{
    protected String ipPool_start;
    protected String ipPool_end;
    protected String netMask;
    protected String MAC;
    protected String primary_DNS;
    protected String vice_DNS;
    protected String ip;
    protected Integer lease;
    ExecScript es;
    Writteninto wi;
    Map map;
    Exec_Backup_dhcpConf ebd;
    Exec_restart_dhcp erd ;
    Exec_reset_host erh ;

    public ConfBase() {
        this.ebd = new Exec_Backup_dhcpConf();
        this.erd = new Exec_restart_dhcp();
        this.erh = new Exec_reset_host();
    }
    
    
}
