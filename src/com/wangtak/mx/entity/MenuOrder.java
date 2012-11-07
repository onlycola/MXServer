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
public class MenuOrder
{
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	int menuItemId;
	int menuOptionId;
	int amount;
	double unitPrice;
	double totalPrice;
	String titleForDisplay;
	/**
	 * @return the menuItemId
	 */
	public int getMenuItemId() {
		return menuItemId;
	}
	/**
	 * @param menuItemId the menuItemId to set
	 */
	public void setMenuItemId(int menuItemId) {
		this.menuItemId = menuItemId;
	}
	/**
	 * @return the menuOptionId
	 */
	public int getMenuOptionId() {
		return menuOptionId;
	}
	/**
	 * @param menuOptionId the menuOptionId to set
	 */
	public void setMenuOptionId(int menuOptionId) {
		this.menuOptionId = menuOptionId;
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
		return "MenuOrder [id=" + id + ", menuItemId=" + menuItemId
				+ ", menuOptionId=" + menuOptionId + ", amount=" + amount
				+ ", unitPrice=" + unitPrice + ", totalPrice=" + totalPrice
				+ "]";
	}
}
