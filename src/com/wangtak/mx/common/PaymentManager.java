/**
 * 
 */
package com.wangtak.mx.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.jboss.logging.Logger;

import com.wangtak.mx.endpoint.OnlineOrderEndpoint;
import com.wangtak.mx.entity.CreditCard;

import ePayLinkPKI.DRObject;
import ePayLinkPKI.ePayLinkClient;

/**
 * @author yuzhao
 * 
 */
@Startup
@Singleton(name = "PaymentManager")
public class PaymentManager {
	private static final String Succeed_Code = "000000";

	private static Logger log = Logger.getLogger(OnlineOrderEndpoint.class);

	/* Create the ePayLink Client Object */
	private static ePayLinkClient eplc = new ePayLinkClient();

	@PostConstruct
	void init() {
		log.info("=========== INITIALIZING! ===========");

		/* Load the config file */
		eplc.setConfigFile("C:\\Program Files (x86)\\ePayLinkPKI\\conf\\ePayLinkPKI.conf");
		/* Check whether the config file is correctly loaded */
		if (eplc.isConfigFileCorrect() == false) {
			log.error("Config file incorrect");
		} else {
			log.info("Config file correct");
		}
	}

	public static PaymentResult transaction(String holder, String cardNumber,
			String cardType, String validMonth, String validYear,
			double amountInHKD, String orderCode, String customerEmail) {
		log.info("transaction holder:" + holder + "cardType:" + cardType
				+ ", validMonth:" + validMonth + ", validYear:" + validYear
				+ ", amount " + amountInHKD + ", orderCode:" + orderCode
				+ ", customerEmail:" + customerEmail);
		PaymentResult paymentResult = new PaymentResult();
		if (eplc.isConfigFileCorrect() == false) {
			log.error("Config file incorrect");
			paymentResult.setSucceed(false);
			paymentResult.setErrorCode(PaymentResult.Error_PaymentGateway);
		} else {
			boolean setCC = false;
			String cvc2 = new String("");
			String cid = new String("");
			try {
				setCC = eplc.setCC(cardType, cardNumber, validYear, validMonth,
						holder, cvc2, cid);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (setCC != true) {/* setCC failed, display the last error code */
				log.info("Error when setting credit card information.<BR>");
				paymentResult.setSucceed(false);
				paymentResult.setErrorCode(PaymentResult.Error_CreditCard);
			} else {
				/* Define the parameters for the transaction */
				String paymentType = new String("cc");
				String referenceNo = orderCode;
				double amount = amountInHKD * 100.0;
				String locale = new String("zh");
				String currCode = new String("344");
				String opCode = new String("00");// capture and sale
				String successURL = new String("http://");
				String failURL = new String("http://");
				String cancelURL = new String("http://");
				String payerEmail = customerEmail;
				String payFor = new String("maxims mx party menu");
				/* Generate the URL with the Digital Order (DO) encrypted */
				String EncDoURL = null;
				try {
					EncDoURL = new String(eplc.genEncDoURL(paymentType,
							referenceNo, String.valueOf((int)amount), locale,
							currCode, opCode, successURL, failURL, cancelURL,
							payerEmail, payFor));
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (EncDoURL == null || EncDoURL == "") {
					/* Gen DO failed, display the last error code */
					log.info("Error when generating DO<BR>");
					log.info("Last Error is : " + eplc.getLastError() + "<BR>");
					paymentResult.setErrorCode(PaymentResult.Error_CreditCard);
					paymentResult.setSucceed(false);
				} else {
					// out.println(EncDoURL);
					try{
						/* Prepare to send to DO to ePayLink Host */
						System.setProperty("java.protocol.handler.pkgs",
								"com.sun.net.ssl.internal.www.protocol");
						URL ePayLink = new URL(EncDoURL);
						BufferedReader in = new BufferedReader(
								new InputStreamReader(ePayLink.openStream()));
						String DR;
						DR = in.readLine();
						DRObject dro = eplc.decEncDR(DR);
						log.info("DR = " + DR + "<BR>");
						log.info("DR Format Correct = " + dro.isMsgFormatCorrect()
								+ "<BR>");
						log.info("PaymentType = " + dro.getPaymentType() + "<BR>");
						log.info("MerchantID = " + dro.getMerchantID() + "<BR>");
						log.info("ReferenceNo = " + dro.getReferenceNo() + "<BR>");
						log.info("Amount = " + dro.getAmount() + "<BR>");
						log.info("Locale = " + dro.getLocale() + "<BR>");
						log.info("CurrCode = " + dro.getCurrCode() + "<BR>");
						log.info("OpCode = " + dro.getOpCode() + "<BR>");
						log.info("ResponseCode = " + dro.getResponseCode() + "<BR>");
						log.info("ePayLinkTxID = " + dro.getePayLinkTxID() + "<BR>");
						in.close();
						if (dro.getResponseCode().compareTo(Succeed_Code)==0) {
							// payment succeed
							paymentResult.setSucceed(true);
							paymentResult.setTransactionId(dro.getePayLinkTxID());
						} else {
							// payment failed
							paymentResult.setSucceed(false);
							paymentResult
									.setErrorCode(PaymentResult.Error_CreditCard);
						}
					}
					catch(Exception e)
					{
						e.printStackTrace();
						// payment failed
						paymentResult.setSucceed(false);
						paymentResult
								.setErrorCode(PaymentResult.Error_CreditCard);
					}
					
				}
			}

		}

		return paymentResult;
	}
}
