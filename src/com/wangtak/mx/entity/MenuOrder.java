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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MenuOrder [menuItemId=" + menuItemId + ", menuOptionId="
				+ menuOptionId + ", amount=" + amount + "]";
	}
}
