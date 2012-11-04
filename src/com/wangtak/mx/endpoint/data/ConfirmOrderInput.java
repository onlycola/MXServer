/**
 * 
 */
package com.wangtak.mx.endpoint.data;

/**
 * @author yuzhao
 *
 */
public class ConfirmOrderInput {
	String orderId;
	String uniqueURL;
	String cardNumber; //full credit card number
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
	 * @return the uniqueURL
	 */
	public String getUniqueURL() {
		return uniqueURL;
	}
	/**
	 * @param uniqueURL the uniqueURL to set
	 */
	public void setUniqueURL(String uniqueURL) {
		this.uniqueURL = uniqueURL;
	}
	/**
	 * @return the cardNumber
	 */
	public String getCardNumber() {
		return cardNumber;
	}
	/**
	 * @param cardNumber the cardNumber to set
	 */
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ConfirmOrderInput [orderId=" + orderId + ", uniqueURL="
				+ uniqueURL + ", cardNumber=" + cardNumber + "]";
	}
}
