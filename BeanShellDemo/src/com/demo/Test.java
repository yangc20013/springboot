package com.demo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Test {

	public static void main(String[] args) {
		Map<String, Integer> maps = new HashMap<>();
		for (int i = 0; i < 10; i++) {
			maps.put("key" + i, i);
		}
		List<String> keys = maps.keySet().stream().filter(k -> Integer.parseInt(maps.get(k)+"") > 5 ).collect(Collectors.toList());
		for (String string : keys) {
			System.out.println(string);
		}
		Object avg = maps.keySet().stream().filter(k -> Integer.parseInt(maps.get(k)+"") > 5 ).mapToInt(k -> maps.get(k)).average().getAsDouble();
		System.out.println(avg);
		Object sum = maps.keySet().stream().filter(k -> Integer.parseInt(maps.get(k)+"") > 5 ).mapToInt(k -> maps.get(k)).sum();
		System.out.println(sum);
		for(Iterator<String> iter = maps.keySet().iterator(); iter.hasNext();) {
			System.out.println(iter.next());
		}
		System.out.println("=====================");
		maps.forEach((k,v)-> {System.out.println(k+":"+ v);});
		System.out.println("=====================");
//		maps.entrySet().stream().forEach(k -> System.out.println(k.getValue()));
		List<?> list = maps.entrySet().stream().filter(k -> ((Integer)k.getValue() > 3)).collect(Collectors.toList());
		
		list.forEach(a -> System.out.println(a));
	}

}
