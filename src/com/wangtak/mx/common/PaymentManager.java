/**
 * 
 */
package com.wangtak.mx.common;

import org.jboss.logging.Logger;

import com.wangtak.mx.endpoint.OnlineOrderEndpoint;
import com.wangtak.mx.entity.CreditCard;

/**
 * @author yuzhao
 * 
 */
public class PaymentManager {
	private static Logger log = Logger.getLogger(OnlineOrderEndpoint.class);

	public static PaymentResult transaction(String holder, String cardNumber,
			String cardType, String validMonth, String validYear,
			double amount, String orderCode, String customerEmail) {
		log.info("transaction holder:" + holder + "cardType:" + cardType
				+ ", validMonth:" + validMonth + ", validYear:" + validYear
				+ ", amount " + amount + ", orderCode:" + orderCode
				+ ", customerEmail:" + customerEmail);

		PaymentResult paymentResult = new PaymentResult();
		paymentResult.setSucceed(true);
		return paymentResult;
	}
}
