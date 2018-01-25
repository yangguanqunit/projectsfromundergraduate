/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ldu.se.dhcpserver;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yangguanqun
 */
public class LoginServlet extends HttpServlet{
    
    protected void doPost(HttpServletRequest req,HttpServletResponse res){
		String name=req.getParameter("firstname");
		String password=req.getParameter("lastname");
		System.out.println(name+","+password);
	}
    
}
