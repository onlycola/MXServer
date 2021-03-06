/**
 * 
 */
package com.wangtak.mx.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author yuzhao
 *
 */
@Entity
public class CreditCard {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	String holder;
	String cardNumber; //only last 4 digital
	int cardType;//0 for visa, 1 for master
	int creditCardBankId; // 0 for other cards, 1 for Manhattan, 2 for 
	String creditCardBank;
	String validMonth;
	String validYear;
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
	public String getValidMonth() {
		return validMonth;
	}
	/**
	 * @param validMonth the validMonth to set
	 */
	public void setValidMonth(String validMonth) {
		this.validMonth = validMonth;
	}
	/**
	 * @return the validYear
	 */
	public String getValidYear() {
		return validYear;
	}
	/**
	 * @param validYear the validYear to set
	 */
	public void setValidYear(String validYear) {
		this.validYear = validYear;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CreditCard [id=" + id + ", holder=" + holder + ", cardNumber="
				+ cardNumber + ", cardType=" + cardType + ", creditCardBankId="
				+ creditCardBankId + ", creditCardBank=" + creditCardBank
				+ ", validMonth=" + validMonth + ", validYear=" + validYear
				+ "]";
	}
	public String getCardTypeString() {
		switch(this.cardType)
		{
		case 0:
			return "VISA";
		case 1:
			return "MASTERCARD";
		default:
			return "";
		}
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the creditCardBank
	 */
	public String getCreditCardBank() {
		switch(this.creditCardBankId)
		{
		case 1:
			return "Manhattan信用卡";
		case 2:
			return "渣打信用卡";
		default:
			return "其它信用卡";
		}
	}
	/**
	 * @param creditCardBank the creditCardBank to set
	 */
	public void setCreditCardBank(String creditCardBank) {
		this.creditCardBank = creditCardBank;
	}
}
