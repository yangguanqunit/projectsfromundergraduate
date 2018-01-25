/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ldu.se.dhcpserver;

import javax.servlet.Servlet;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author yangguanqun
 */
public class NewClass {

    public static void main(String[] args) {
        System.out.print("111");
        String jsonStr = ServletActionContext.getRequest().getParameter("mydata");

        JSONArray jsonArray = JSONArray.fromObject(jsonStr);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonJ = jsonArray.getJSONObject(i);
            jsonJ.getInt("name");
            jsonJ.getString("age");
        }
    }

}
