/**
 * 
 */
package com.wangtak.mx.common;

/**
 * @author yuzhao
 *
 */
public class PaymentResult {
	public static final int Error_General = 0;
	public static final int Error_CreditCard = 1;
	public static final int Error_PaymentGateway = 2;
	
	boolean isSucceed;
	int errorCode;
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
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
}
