/**
 * 
 */
package com.wangtak.mx.common;

/**
 * @author yuzhao
 *
 */
public class PaymentResult {
	boolean isSucceed;
	String error;
	String transactionId;
	/**
	 * @return the isSucceed
	 */
	public boolean isSucceed() {
		return isSucceed;
	}
	/**
	 * @param isSucceed the isSucceed to set
	 */
	public void setSucceed(boolean isSucceed) {
		this.isSucceed = isSucceed;
	}
	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}
	/**
	 * @param error the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}
	/**
	 * @return the transactionId
	 */
	public String getTransactionId() {
		return transactionId;
	}
	/**
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
}
