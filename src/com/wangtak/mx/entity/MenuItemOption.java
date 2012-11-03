/**
 * 
 */
package com.wangtak.mx.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author yuzhao
 *
 */
@Entity
public class MenuItemOption {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	int id; //unqiue id
	int sort_order;//sort the item based on sort_order
	@Column(nullable = false)
	String title; //title for the Menu Option
	@Column(nullable = false)
	double price; //orignal price for the menu option
	@Column(nullable = false)
	boolean enableSpecialPrice = false; //if the specialPrice is used, the value is true. otherwise, false.
	double specialPrice; //special price for the menu option
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
	 * @return the sort_order
	 */
	public int getSort_order() {
		return sort_order;
	}
	/**
	 * @param sort_order the sort_order to set
	 */
	public void setSort_order(int sort_order) {
		this.sort_order = sort_order;
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
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	/**
	 * @return the specialPrice
	 */
	public double getSpecialPrice() {
		return specialPrice;
	}
	/**
	 * @param specialPrice the specialPrice to set
	 */
	public void setSpecialPrice(double specialPrice) {
		this.specialPrice = specialPrice;
	}
	/**
	 * @return the enableSpecialPrice
	 */
	public boolean isEnableSpecialPrice() {
		return enableSpecialPrice;
	}
	/**
	 * @param enableSpecialPrice the enableSpecialPrice to set
	 */
	public void setEnableSpecialPrice(boolean enableSpecialPrice) {
		this.enableSpecialPrice = enableSpecialPrice;
	}
}
