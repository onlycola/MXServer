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
public class CustomerOrderGift {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	int id; //unqiue id
	String title;
	int amount;
	
	public CustomerOrderGift()
	{
		super();
	}
	
	public CustomerOrderGift(String title, int amount)
	{
		this.title = title;
		this.amount = amount;
	}
	
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
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
		return "CustomerOrderGift [id=" + id + ", title=" + title + ", amount="
				+ amount + "]";
	}
	
}
