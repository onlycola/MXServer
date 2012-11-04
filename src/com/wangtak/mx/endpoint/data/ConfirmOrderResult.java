/**
 * 
 */
package com.wangtak.mx.endpoint.data;

/**
 * @author yuzhao
 *
 */
public class ConfirmOrderResult {
	boolean succeed; // true for succeed, otherwise, false
	String url;
	/**
	 * @return the succeed
	 */
	public boolean isSucceed() {
		return succeed;
	}
	/**
	 * @param succeed the succeed to set
	 */
	public void setSucceed(boolean succeed) {
		this.succeed = succeed;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
}
