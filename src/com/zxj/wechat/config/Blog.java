package com.zxj.wechat.config;

import java.util.ArrayList;
import java.util.List;

public class Blog {
	
	private Author writer;
	
	private List<Entry> entries = new ArrayList<>();
	
	Blog(Author writer){
		this.writer = writer;
	}
	
	public void addEntry(Entry entry){
		this.entries.add(entry);
	}
	
	public List<Entry> getEntries(){
		return this.entries;
	}
}
