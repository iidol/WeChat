package com.zxj.wechat.config;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.SingleValueConverter;

public class Main {

	public static void main(String[] args) {
		Blog blog = new Blog(new Author("Sam"));
		blog.addEntry(new Entry("SE", "计算机科学"));
		blog.addEntry(new Entry("CS", "运筹"));
		XStream xs = new XStream();
		xs.alias("blog", Blog.class);
		xs.alias("entry", Entry.class);
		xs.aliasField("author", Blog.class, "writer");
		xs.addImplicitCollection(Blog.class, "entries");
		xs.useAttributeFor(Blog.class, "writer");
		xs.registerConverter(new AuthorConverter());
		System.out.println(xs.toXML(blog));
	}
}

class AuthorConverter implements SingleValueConverter {
	
	// The first method to implement tells XStream which types it can deal with:
	public boolean canConvert(Class type) {
		return type.equals(Author.class);
	}
	
	// The second one is used to extract a String from an Author:
	public String toString(Object obj) {
		return ((Author) obj).getName();
	}
	
	//And the third one does the opposite job: takes a String and returns an Author:
	public Object fromString(String name) {
		return new Author(name);
	}

}
