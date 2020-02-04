package com.nirvacsh.models;

public class Periodicals {
	
	protected int pid;
	protected String title;
	protected float price;
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public Periodicals(int pid, String title, float price) {
		this.pid = pid;
		this.title = title;
		this.price = price;
	}	
	public Periodicals(int pid) {
		
	}
	public Periodicals() {

	}
	public Periodicals(String title, float price) {
		this.title = title;
		this.price = price;
	}
	@Override
	public String toString() {
		return "Periodicals [pid=" + pid + ", title=" + title + ", price=" + price + "]";
	}
	
}
