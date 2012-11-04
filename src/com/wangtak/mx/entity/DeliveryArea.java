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
public class DeliveryArea {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	int id; //unique id
	int sort_order; //sort the items based on sort_order
	String title; //area title
	double deliveryFee; //delivery fee
	boolean freeArea; //free promotion cover this area
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
	 * @return the deliveryFee
	 */
	public double getDeliveryFee() {
		return deliveryFee;
	}
	/**
	 * @param deliveryFee the deliveryFee to set
	 */
	public void setDeliveryFee(double deliveryFee) {
		this.deliveryFee = deliveryFee;
	}
	/**
	 * @return the freeArea
	 */
	public boolean isFreeArea() {
		return freeArea;
	}
	/**
	 * @param freeArea the freeArea to set
	 */
	public void setFreeArea(boolean freeArea) {
		this.freeArea = freeArea;
	}
}
