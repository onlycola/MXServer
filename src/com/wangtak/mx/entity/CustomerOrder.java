/**
 * 
 */
package com.wangtak.mx.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


/**
 * @author yuzhao
 *
 */
@Entity
public class CustomerOrder {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	@Column(unique = true)
	String uniqueURL;
	@Column(unique = true)
	String orderCode;//PT+date+phone+shop id+2 digital number
	@Column(unique = true)
	String paymentReference; //the tx reference return from payment gateway
	OrderStatus status;
	Date createDate;
	double amount;
	double deliveryFee;
	
	// Order Content
	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	Set<MenuOrder> menuOrderList;
	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	Set<ComboOrder> comboOrderList;
	
	//Gift
	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	Set<CustomerOrderGift> giftList;
	
	// Customer Info
	String customerName;
	String customerPhoneNumber;
	String customerEmail;
	boolean receivePromotion;
	
	// Credit Card
	@OneToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	CreditCard creditCard;

	boolean pickup;// true for pick up, false for delivery
	@OneToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	PickupInfo pickupInfo;
	@OneToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	DeliveryInfo deliveryInfo;

	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName
	 *            the customerName to set
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
	 * @param customerPhoneNumber
	 *            the customerPhoneNumber to set
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
	 * @param customerEmail
	 *            the customerEmail to set
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
	 * @param creditCard
	 *            the creditCard to set
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
	 * @param pickupInfo
	 *            the pickupInfo to set
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
	 * @param deliveryInfo
	 *            the deliveryInfo to set
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
	 * @param receivePromotion
	 *            the receivePromotion to set
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
	 * @param pickup
	 *            the pickup to set
	 */
	public void setPickup(boolean pickup) {
		this.pickup = pickup;
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
	 * @return the paymentReference
	 */
	public String getPaymentReference() {
		return paymentReference;
	}

	/**
	 * @param paymentReference the paymentReference to set
	 */
	public void setPaymentReference(String paymentReference) {
		this.paymentReference = paymentReference;
	}

	/**
	 * @return the status
	 */
	public OrderStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the uniqueURL
	 */
	public String getUniqueURL() {
		return uniqueURL;
	}

	/**
	 * @param uniqueURL the uniqueURL to set
	 */
	public void setUniqueURL(String uniqueURL) {
		this.uniqueURL = uniqueURL;
	}

	/**
	 * @return the menuOrderList
	 */
	public Set<MenuOrder> getMenuOrderList() {
		return menuOrderList;
	}

	/**
	 * @param menuOrderList the menuOrderList to set
	 */
	public void setMenuOrderList(Set<MenuOrder> menuOrderList) {
		this.menuOrderList = menuOrderList;
	}

	/**
	 * @return the comboOrderList
	 */
	public Set<ComboOrder> getComboOrderList() {
		return comboOrderList;
	}

	/**
	 * @param comboOrderList the comboOrderList to set
	 */
	public void setComboOrderList(Set<ComboOrder> comboOrderList) {
		this.comboOrderList = comboOrderList;
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
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
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
	 * @return the giftList
	 */
	public Set<CustomerOrderGift> getGiftList() {
		return giftList;
	}

	/**
	 * @param giftList the giftList to set
	 */
	public void setGiftList(Set<CustomerOrderGift> giftList) {
		this.giftList = giftList;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CustomerOrder [id=" + id + ", uniqueURL=" + uniqueURL
				+ ", orderCode=" + orderCode + ", paymentReference="
				+ paymentReference + ", status=" + status + ", createDate="
				+ createDate + ", amount=" + amount + ", deliveryFee="
				+ deliveryFee + ", menuOrderList=" + menuOrderList
				+ ", comboOrderList=" + comboOrderList + ", giftList="
				+ giftList + ", customerName=" + customerName
				+ ", customerPhoneNumber=" + customerPhoneNumber
				+ ", customerEmail=" + customerEmail + ", receivePromotion="
				+ receivePromotion + ", creditCard=" + creditCard + ", pickup="
				+ pickup + ", pickupInfo=" + pickupInfo + ", deliveryInfo="
				+ deliveryInfo + "]";
	}

}
