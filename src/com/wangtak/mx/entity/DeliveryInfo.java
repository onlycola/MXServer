/**
 * 
 */
package com.wangtak.mx.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author yuzhao
 *
 */
@Entity
public class DeliveryInfo {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	Date date;
	String deliveryAddress;
	String deliveryAreaId;//consitent to DeliveryArea id
	String deliveryPeriod;
	String name;
	String phoneNumber;
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * @return the deliveryAddress
	 */
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	/**
	 * @param deliveryAddress the deliveryAddress to set
	 */
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	/**
	 * @return the deliveryAreaId
	 */
	public String getDeliveryAreaId() {
		return deliveryAreaId;
	}
	/**
	 * @param deliveryAreaId the deliveryAreaId to set
	 */
	public void setDeliveryAreaId(String deliveryAreaId) {
		this.deliveryAreaId = deliveryAreaId;
	}
	/**
	 * @return the deliveryPeriod
	 */
	public String getDeliveryPeriod() {
		return deliveryPeriod;
	}
	/**
	 * @param deliveryPeriod the deliveryPeriod to set
	 */
	public void setDeliveryPeriod(String deliveryPeriod) {
		this.deliveryPeriod = deliveryPeriod;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DeliveryInfo [date=" + date + ", deliveryAddress="
				+ deliveryAddress + ", deliveryAreaId=" + deliveryAreaId
				+ ", deliveryPeriod=" + deliveryPeriod + ", name=" + name
				+ ", phoneNumber=" + phoneNumber + "]";
	}
}
