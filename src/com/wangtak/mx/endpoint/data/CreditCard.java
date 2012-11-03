/**
 * 
 */
package com.wangtak.mx.endpoint.data;

/**
 * @author yuzhao
 *
 */
public class CreditCard {
	String holder;
	String cardNumber;
	int cardType;//0 for visa, 1 for master
	int creditCardBankId; // consistent to CrediCardDsicountRuleId
	int validMonth;
	int validYear;
	/**
	 * @return the holder
	 */
	public String getHolder() {
		return holder;
	}
	/**
	 * @param holder the holder to set
	 */
	public void setHolder(String holder) {
		this.holder = holder;
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
	/**
	 * @return the cardType
	 */
	public int getCardType() {
		return cardType;
	}
	/**
	 * @param cardType the cardType to set
	 */
	public void setCardType(int cardType) {
		this.cardType = cardType;
	}
	/**
	 * @return the creditCardBankId
	 */
	public int getCreditCardBankId() {
		return creditCardBankId;
	}
	/**
	 * @param creditCardBankId the creditCardBankId to set
	 */
	public void setCreditCardBankId(int creditCardBankId) {
		this.creditCardBankId = creditCardBankId;
	}
	/**
	 * @return the validMonth
	 */
	public int getValidMonth() {
		return validMonth;
	}
	/**
	 * @param validMonth the validMonth to set
	 */
	public void setValidMonth(int validMonth) {
		this.validMonth = validMonth;
	}
	/**
	 * @return the validYear
	 */
	public int getValidYear() {
		return validYear;
	}
	/**
	 * @param validYear the validYear to set
	 */
	public void setValidYear(int validYear) {
		this.validYear = validYear;
	}
}
