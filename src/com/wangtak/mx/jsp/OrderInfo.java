/**
 * 
 */
package com.wangtak.mx.jsp;

import java.text.DateFormat;
import java.util.Date;

import com.wangtak.mx.entity.CustomerOrder;

/**
 * @author piglet
 *
 */
public class OrderInfo {
	enum Status{
		Unknown,
		Pickuped,
		Deliveried,
		Cancel,
		NotPickup
	}
	String orderCode;
	Date date;
	boolean pickup;
	Status status;
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
	 * @return the pickup
	 */
	public boolean isPickup() {
		return pickup;
	}
	/**
	 * @param pickup the pickup to set
	 */
	public void setPickup(boolean pickup) {
		this.pickup = pickup;
	}
	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public String getStatusStr()
	{
		return "未取貨";
	}
	
	public String getDateStr()
	{
		return java.text.DateFormat.getDateInstance(DateFormat.MEDIUM).format(this.date);
	}
	public static OrderInfo createOrderInfo(CustomerOrder o) {
		OrderInfo orderInfo = new OrderInfo();
		if(o.isPickup())
		{
			orderInfo.date = o.getPickupInfo().getPickupDate();
		}
		else
		{
			orderInfo.date = o.getDeliveryInfo().getDate();
		}
		orderInfo.pickup = o.isPickup();
		orderInfo.orderCode = o.getOrderCode();
		return orderInfo;
	}
}
