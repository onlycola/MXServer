/**
 * 
 */
package com.wangtak.mx.common;

import com.wangtak.mx.entity.CreditCard;

/**
 * @author yuzhao
 *
 */
public class PaymentManager {

	public static PaymentResult transaction(String holder, String cardNumber,
			String cardType, String validMonth, String validYear,
			double amount, String orderCode, String customerEmail) {
		PaymentResult paymentResult = new PaymentResult();
		paymentResult.setSucceed(true);
		return paymentResult;
	}
}
