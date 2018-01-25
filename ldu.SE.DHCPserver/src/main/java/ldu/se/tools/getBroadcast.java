/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ldu.se.tools;

/**
 *
 * @author root
 */
public class getBroadcast extends JudgeType {
    private String beShifted;
    private String[] ipBitList;
    public String shiftTo(String ipLike,Integer position,Boolean isBroadcast,String num){
        beShifted=null;
        ipBitList=ipLike.split("\\.");
        if(isBroadcast)
        {
            for(int i=position;i<ipBitList.length;i++)
            {
                ipBitList[i]=num;
            }
        }
        else
            
             ipBitList[position]=num;
        for(String tmp : ipBitList)
        {
            beShifted = beShifted+tmp+".";
        }
        
        return beShifted.substring(4,beShifted.length()-1);
    }
    public String shift(String ipLike,String num,String netmask){
        String res = judgeType(netmask);
        //System.out.println(res);
        String tmp=null;
        
        switch (res) {
            case "A":
                tmp=shiftTo(ipLike, 1, true,num);
                break;
            case "B":
                tmp= shiftTo(ipLike, 2, true,num );
                //System.out.println(tmp);
                break;
            case "C":
                tmp= shiftTo(ipLike, 3,true, num);
                break;
            default:
                break;
        }
       return tmp;
    }
    
}
