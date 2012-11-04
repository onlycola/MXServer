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
public class PickupInfo {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	Date pickupDate;
	String storeCode;
	String pickupPeriod;
	String storeAddress;
	String storePhonenumber;
	/**
	 * @return the pickupDate
	 */
	public Date getPickupDate() {
		return pickupDate;
	}
	/**
	 * @param pickupDate the pickupDate to set
	 */
	public void setPickupDate(Date pickupDate) {
		this.pickupDate = pickupDate;
	}
	/**
	 * @return the storeCode
	 */
	public String getStoreCode() {
		return storeCode;
	}
	/**
	 * @param storeCode the storeCode to set
	 */
	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}
	/**
	 * @return the pickupPeriod
	 */
	public String getPickupPeriod() {
		return pickupPeriod;
	}
	/**
	 * @param pickupPeriod the pickupPeriod to set
	 */
	public void setPickupPeriod(String pickupPeriod) {
		this.pickupPeriod = pickupPeriod;
	}
	/**
	 * @return the storeAddress
	 */
	public String getStoreAddress() {
		return storeAddress;
	}
	/**
	 * @param storeAddress the storeAddress to set
	 */
	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}
	/**
	 * @return the storePhonenumber
	 */
	public String getStorePhonenumber() {
		return storePhonenumber;
	}
	/**
	 * @param storePhonenumber the storePhonenumber to set
	 */
	public void setStorePhonenumber(String storePhonenumber) {
		this.storePhonenumber = storePhonenumber;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PickupInfo [pickupDate=" + pickupDate + ", storeCode="
				+ storeCode + ", pickupPeriod=" + pickupPeriod
				+ ", storeAddress=" + storeAddress + ", storePhonenumber="
				+ storePhonenumber + "]";
	}
}
