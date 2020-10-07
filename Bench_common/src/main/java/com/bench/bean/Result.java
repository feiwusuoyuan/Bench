package com.bench.bean;

public class Result implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int code;
	private String msg;
	private Object data;
	
	/**
	 * 	JavaBean 规范 ：一定要定义无参数的构造函数
	 */
	public Result() { }
	
	public Result(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	
	
	public Result( String msg) {
		this.code = 0;
		this.msg = msg;
	}
	
	public Result(int code, String msg, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	
	
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	

}
