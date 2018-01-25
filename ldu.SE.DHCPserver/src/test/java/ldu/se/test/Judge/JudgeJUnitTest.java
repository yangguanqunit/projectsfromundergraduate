/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ldu.se.test.Judge;

import ldu.se.tools.JudgeType;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author yangguanqun
 */
public class JudgeJUnitTest {
    JudgeType judge;
    
    public JudgeJUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        judge = new JudgeType();
        
    }
    
    @After
    public void tearDown() {
    }
    @Test
    public void judgetest(){
        
        String Type_1="A";
        String Type_2="B";
        String Type_3="C";
        String netmask_1="255.0.0.0";
        String netmask_2="255.255.0.0";
        String netmask_3="255.255.255.0";
        
        assertEquals(Type_1, judge.judgeType(netmask_1));
//        assertEquals(Type_1, judge.judgeType(netmask_2));
        assertEquals(Type_2, judge.judgeType(netmask_2));
        assertEquals(Type_3, judge.judgeType(netmask_3));
        
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
}
