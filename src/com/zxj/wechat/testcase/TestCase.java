package com.zxj.wechat.testcase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestCase {
	
	private ArrayList testList;
	
	@BeforeClass
	public static void onceExecutedBeforeAll() {

		System.out.println("@BeforeClass: onceExecutedBeforeAll");

	}

	@Before
	public void executedBeforeEach() {

		testList = new ArrayList();

		System.out.println("@Before: executedBeforeEach");

	}

	@Test
	public void testCharSet() {
		 try {
			String name = "abc";
//			System.out.println(String.valueOf(name.getBytes("UTF-8")));
			System.out.println(name.getBytes("unicode").length);//9
//			System.out.println(name.getBytes("ISO8859-1").length);//6
//			System.out.println(name.getBytes("utf-8").length);//12
//			System.out.println(name.getBytes().length);//12
			byte []a = "中文".getBytes("utf-8");
			new String();
//			for(byte i : a)
//				System.out.print(i+" ");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //9
	}


	@Test
	public void testCollection() {
		assertTrue(testList.isEmpty());
		System.out.println("@Test: EmptyArrayList");
	}

	@Test
	public void testItemCollection() {

		testList.add("oneItem");

		assertEquals(1, testList.size());

		System.out.println("@Test: OneItemArrayList");

	}
	
	@Test
	public void parseTime(){
/*		long longTime = 1373206143378L;
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println(format.format(new Date().getTime()));
		String bike = String.valueOf(Character.toChars(0x1F466));
		System.out.println(bike);*/
		System.out.println(System.getProperty("file.encoding"));
	}

}
