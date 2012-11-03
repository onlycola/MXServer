/**
 * 
 */
package com.wangtak.mx.endpoint.data;

/**
 * @author yuzhao
 *
 */
public class SubmitOrderResponse {
	String orderId; //identity for the order
	String url; // url for get the order content, must fetch by https://
	
	/**
	 * @return the orderId
	 */
	public String getOrderId() {
		return orderId;
	}
	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
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
