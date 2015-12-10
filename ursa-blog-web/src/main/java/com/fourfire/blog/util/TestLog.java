package com.fourfire.blog.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestLog {
	Logger logger = LogManager.getLogger();
	public static void main(String args[]) {
		TestLog t = new TestLog();
		t.test();
		System.out.println("Hello world");
		
	}
	
	public void test() {
		logger.info("hello world");
		logger.error("world hello");
	}
}
