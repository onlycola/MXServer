/**
 * 
 */
package com.wangtak.mx.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * @author yuzhao
 *
 */
@Entity
public class MenuCombo {
	@Id @GeneratedValue
	int id; //unique id
	int sort_order; //sort item based on sort_order
	@Column(nullable = false)
	int type; //1 for 10 Person, 2 for 20 Person, 3 for 30 Person. ignore other value
	@Column(nullable = false)
	double price; //orginal price for this combo
	@Column(nullable = false)
	boolean enableSpecialPrice = false; //if the special price is enable, the value is true. otherwise false
	double specialPrice; //special price for this combo
	@Column(nullable = false)
	boolean isEnable = true; //if the combo is enable, the value is true, otherwise, false
	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.DETACH)
	Set<ComboItem> itemList; // all combo items in this combo
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
	 * @return the type
	 */
	public int getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
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
	 * @return the itemList
	 */
	public Set<ComboItem> getItemList() {
		return itemList;
	}
	/**
	 * @param itemList the itemList to set
	 */
	public void setItemList(Set<ComboItem> itemList) {
		this.itemList = itemList;
	}
	/**
	 * @return the isEnable
	 */
	public boolean isEnable() {
		return isEnable;
	}
	/**
	 * @param isEnable the isEnable to set
	 */
	public void setEnable(boolean isEnable) {
		this.isEnable = isEnable;
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
