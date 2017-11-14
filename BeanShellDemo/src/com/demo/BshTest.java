package com.demo;

import java.util.HashMap;
import java.util.Map;

public class BshTest {

	public static void main(String[] args) {
		BshUtils bsh = new BshUtils();  
        String expr = "a==1&&b.equals(\"c\")";  
        String expr2 = "a+c";  
        Map<String, Object> map = new HashMap<String, Object>();  
        map.put("a", 1);  
        map.put("b", "c");  
        map.put("c", 2);  
        try {  
            System.out.println("--------expr--------------> "  
                    + bsh.eval(expr, map).toString());  
            System.out.println("--------expr2--------------> "  
                    + bsh.eval(expr2, map).toString());  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  

	}

}
