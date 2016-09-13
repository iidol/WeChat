package com.zxj.wechat.config;

import java.io.InputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ParseXml {
	
	public static void main(String[] args) {
		try {
			SAXReader sr = new SAXReader();
			InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("com/zxj/wechat/config/msg.xml");
			InputStream is = ParseXml.class.getClassLoader().getResourceAsStream("com/zxj/wechat/config/msg.xml");
			Document document = sr.read(is);
			Element root = document.getRootElement();
			@SuppressWarnings("unchecked")
			List<Element> list = root.elements();
			for(Element ele : list){
				System.out.println(ele.getName()+" "+ele.getTextTrim());
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
