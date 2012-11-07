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
public class ComboOrder
{
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	int comboId;
	int amount;
	double unitPrice;
	double totalPrice;
	String titleForDisplay;
	/**
	 * @return the comboId
	 */
	public int getComboId() {
		return comboId;
	}
	/**
	 * @param comboId the comboId to set
	 */
	public void setComboId(int comboId) {
		this.comboId = comboId;
	}
	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}
	/**
	 * @return the unitPrice
	 */
	public double getUnitPrice() {
		return unitPrice;
	}
	/**
	 * @param unitPrice the unitPrice to set
	 */
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	/**
	 * @return the totalPrice
	 */
	public double getTotalPrice() {
		return totalPrice;
	}
	/**
	 * @param totalPrice the totalPrice to set
	 */
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	/**
	 * @return the titleForDisplay
	 */
	public String getTitleForDisplay() {
		return titleForDisplay;
	}
	/**
	 * @param titleForDisplay the titleForDisplay to set
	 */
	public void setTitleForDisplay(String titleForDisplay) {
		this.titleForDisplay = titleForDisplay;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ComboOrder [id=" + id + ", comboId=" + comboId + ", amount="
				+ amount + ", unitPrice=" + unitPrice + ", totalPrice="
				+ totalPrice + "]";
	}
}
