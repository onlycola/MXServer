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
 * @author piglet
 *
 */
@Entity
public class EmailRecord {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	String emailAddress;
	boolean succeed;
	String orderCode;
	Date sendDate;
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
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}
	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	/**
	 * @return the succeed
	 */
	public boolean isSucceed() {
		return succeed;
	}
	/**
	 * @param succeed the succeed to set
	 */
	public void setSucceed(boolean succeed) {
		this.succeed = succeed;
	}
	/**
	 * @return the orderCode
	 */
	public String getOrderCode() {
		return orderCode;
	}
	/**
	 * @param orderCode the orderCode to set
	 */
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	/**
	 * @return the sendDate
	 */
	public Date getSendDate() {
		return sendDate;
	}
	/**
	 * @param sendDate the sendDate to set
	 */
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
}
