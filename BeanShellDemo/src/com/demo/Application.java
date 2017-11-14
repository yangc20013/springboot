package com.demo;

import java.util.Date;

import bsh.EvalError;
import bsh.Interpreter;

public class Application {

	public static void main(String[] args) {
		// test();
		// test1();
		// test2();
		readBshFile();
		System.out.println("====================");
		readText();
	}

	public static void test() {
		Interpreter interpreter = new Interpreter();
		String s = "1>2;";
		try {
			Object object = interpreter.eval(s);
			System.out.println(object.toString());
		} catch (EvalError e) {
			e.printStackTrace();
		}
	}

	public static void test1() {
		Interpreter interpreter = new Interpreter();
		String s = "return \"hello\"";
		try {
			Object object = interpreter.eval(s);
			System.out.println(object.toString());
		} catch (EvalError e) {
			e.printStackTrace();
		}
	}

	public static void test2() {
		Interpreter interpreter = new Interpreter();
		try {
			interpreter.set("date", new Date().getTime());// 将new Date().getTime()赋值给date
			interpreter.set("now", 10);// 将10赋值给now
			Object object = interpreter.eval("return date*now;");
			System.out.println(object.toString());
		} catch (EvalError e) {
			e.printStackTrace();
		}
	}

	public static void readBshFile() {
		Interpreter bsh = new Interpreter();
		try {
			bsh.source("bsh/a.bsh");
			String script = String.format("return add(%s,%s)", 5, 4);
			Object object = bsh.eval(script);
			System.out.println(object.toString());

			script = String.format("return sub(%s,%s)", 5, 4);
			object = bsh.eval(script);
			System.out.println(object.toString());
		} catch (Exception e) {
		}
	}
	public static void readText() {
		Interpreter bsh = new Interpreter();
		try {
			bsh.eval("int add(int a, int b ) {\n" + 
					"	print(\"--------bsh add log--------\");\n" + 
					"    return a + b;\n" + 
					"}\n" + 
					"int sub(int a, int b ) {\n" + 
					"	print(\"--------bsh sub log--------\");\n" + 
					"    return a - b;\n" + 
					"}");
			String script = String.format("return add(%s,%s)", 5, 4);
			Object object = bsh.eval(script);
			System.out.println(object.toString());
			
			script = String.format("return sub(%s,%s)", 5, 4);
			object = bsh.eval(script);
			System.out.println(object.toString());
		} catch (Exception e) {
		}
	}

}
