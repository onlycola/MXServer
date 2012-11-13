/**
 * 
 */
package com.wangtak.mx.endpoint.data;

import java.util.List;

import com.wangtak.mx.entity.ComboOrder;
import com.wangtak.mx.entity.CreditCard;
import com.wangtak.mx.entity.DeliveryInfo;
import com.wangtak.mx.entity.MenuOrder;
import com.wangtak.mx.entity.PickupInfo;

/**
 * @author yuzhao
 *
 */
public class SubmitOrderInput {
	//Order Content
	List<MenuOrder> menuOrderList;
	List<ComboOrder> comboOrderList;
	//Customer Info
	String customerName;
	String customerPhoneNumber;
	String customerEmail;
	boolean receivePromotion;
	//Credit Card
	CreditCard creditCard;
	
	boolean pickup;// true for pick up, false for delivery
	PickupInfo pickupInfo;
	DeliveryInfo deliveryInfo;
	
	//free gift
	int minuteMaidAmount;// Minute Maid amount
	int milkTeaCouponAmount; // MX milk Tea Coupon amount
	int colaAmount; //coca cola amount
	int redBeanPuddingAmount; // red bean pudding amount
	
	/**
	 * @return the menuOrderList
	 */
	public List<MenuOrder> getMenuOrderList() {
		return menuOrderList;
	}
	/**
	 * @param menuOrderList the menuOrderList to set
	 */
	public void setMenuOrderList(List<MenuOrder> menuOrderList) {
		this.menuOrderList = menuOrderList;
	}
	/**
	 * @return the comboOrderList
	 */
	public List<ComboOrder> getComboOrderList() {
		return comboOrderList;
	}
	/**
	 * @param comboOrderList the comboOrderList to set
	 */
	public void setComboOrderList(List<ComboOrder> comboOrderList) {
		this.comboOrderList = comboOrderList;
	}
	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}
	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	/**
	 * @return the customerPhoneNumber
	 */
	public String getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}
	/**
	 * @param customerPhoneNumber the customerPhoneNumber to set
	 */
	public void setCustomerPhoneNumber(String customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}
	/**
	 * @return the customerEmail
	 */
	public String getCustomerEmail() {
		return customerEmail;
	}
	/**
	 * @param customerEmail the customerEmail to set
	 */
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	
	/**
	 * @return the creditCard
	 */
	public CreditCard getCreditCard() {
		return creditCard;
	}
	/**
	 * @param creditCard the creditCard to set
	 */
	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}
	
	/**
	 * @return the pickupInfo
	 */
	public PickupInfo getPickupInfo() {
		return pickupInfo;
	}
	/**
	 * @param pickupInfo the pickupInfo to set
	 */
	public void setPickupInfo(PickupInfo pickupInfo) {
		this.pickupInfo = pickupInfo;
	}
	/**
	 * @return the deliveryInfo
	 */
	public DeliveryInfo getDeliveryInfo() {
		return deliveryInfo;
	}
	/**
	 * @param deliveryInfo the deliveryInfo to set
	 */
	public void setDeliveryInfo(DeliveryInfo deliveryInfo) {
		this.deliveryInfo = deliveryInfo;
	}
	/**
	 * @return the receivePromotion
	 */
	public boolean isReceivePromotion() {
		return receivePromotion;
	}
	/**
	 * @param receivePromotion the receivePromotion to set
	 */
	public void setReceivePromotion(boolean receivePromotion) {
		this.receivePromotion = receivePromotion;
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
	 * @return the minuteMaidAmount
	 */
	public int getMinuteMaidAmount() {
		return minuteMaidAmount;
	}
	/**
	 * @param minuteMaidAmount the minuteMaidAmount to set
	 */
	public void setMinuteMaidAmount(int minuteMaidAmount) {
		this.minuteMaidAmount = minuteMaidAmount;
	}
	/**
	 * @return the milkTeaCouponAmount
	 */
	public int getMilkTeaCouponAmount() {
		return milkTeaCouponAmount;
	}
	/**
	 * @param milkTeaCouponAmount the milkTeaCouponAmount to set
	 */
	public void setMilkTeaCouponAmount(int milkTeaCouponAmount) {
		this.milkTeaCouponAmount = milkTeaCouponAmount;
	}
	/**
	 * @return the colaAmount
	 */
	public int getColaAmount() {
		return colaAmount;
	}
	/**
	 * @param colaAmount the colaAmount to set
	 */
	public void setColaAmount(int colaAmount) {
		this.colaAmount = colaAmount;
	}
	/**
	 * @return the redBeanPuddingAmount
	 */
	public int getRedBeanPuddingAmount() {
		return redBeanPuddingAmount;
	}
	/**
	 * @param redBeanPuddingAmount the redBeanPuddingAmount to set
	 */
	public void setRedBeanPuddingAmount(int redBeanPuddingAmount) {
		this.redBeanPuddingAmount = redBeanPuddingAmount;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SubmitOrderInput [menuOrderList=" + menuOrderList
				+ ", comboOrderList=" + comboOrderList + ", customerName="
				+ customerName + ", customerPhoneNumber=" + customerPhoneNumber
				+ ", customerEmail=" + customerEmail + ", receivePromotion="
				+ receivePromotion + ", creditCard=" + creditCard + ", pickup="
				+ pickup + ", pickupInfo=" + pickupInfo + ", deliveryInfo="
				+ deliveryInfo + ", minuteMaidAmount=" + minuteMaidAmount
				+ ", milkTeaCouponAmount=" + milkTeaCouponAmount
				+ ", colaAmount=" + colaAmount + ", redBeanPuddingAmount="
				+ redBeanPuddingAmount + "]";
	}
	
}
