/**
 * 
 */
package com.wangtak.mx.entity;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
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
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("MMM/dd/yyyy");
	
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

	public String generateOrderCode() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		//PT+date+phone+shop id+2 digital number
		Date date = null;
		String storeCode = null;
		if(this.isPickup())
		{
			date = this.pickupInfo.pickupDate;
			storeCode = this.pickupInfo.storeCode;
		}
		else
		{
			date = this.deliveryInfo.date;
			storeCode = "0000";
		}
		double randomNumber =  Math.random()*1000;
		
		return "PT"+dateFormat.format(date)+storeCode+(int)randomNumber;
	}

	public String generateEmailContentType() {
		return "text/html; charset=UTF-8";
	}

	public String generateEmailContent() {
		String orderContent = "<h3>訂單內容:</h3>";
		orderContent += "<table border=\"1\"><tr> <th>您所選擇的產品</th> <th>數量</th> <th>金額 </th></tr>";
		//Menu item
		List<MenuOrder> itemList = new ArrayList(this.getMenuOrderList());
		Collections.sort(itemList, new Comparator<MenuOrder>(){

			@Override
			public int compare(MenuOrder o1, MenuOrder o2) {
				return o2.getId()-o1.getId();
			}
		});
		for(MenuOrder o:itemList)
		{
			if(o.getAmount()>0)
			{
				orderContent +="<tr><td>"+o.getTitleForDisplay()+"</td><td>x"+o.getAmount()+"</td><td>"+NumberFormat.getCurrencyInstance().format(o.getTotalPrice())+"</td></tr>";
			}
		}
		
		//Combo
		List<ComboOrder> comboList = new ArrayList(this.getComboOrderList());
		Collections.sort(comboList, new Comparator<ComboOrder>(){

			@Override
			public int compare(ComboOrder o1, ComboOrder o2) {
				return o2.getComboId()-o1.getComboId();
			}
		});
		for(ComboOrder o:comboList)
		{
			if(o.getAmount()>0)
			{
				orderContent +="<tr><td>"+o.getTitleForDisplay()+"</td><td>x"+o.getAmount()+"</td><td>"+NumberFormat.getCurrencyInstance().format(o.getTotalPrice())+"</td></tr>";
			}

		}
		
		
		//orderContent += "<table border=\"1\">";
		if(this.isPickup()||this.getAmount()<=0.0)
		{
			double amount = 0;
			if(this.getAmount()>=0)
			{
				amount = this.getAmount();
			}
			orderContent += "<tr><td></td><td><b>總計</b></td><td>"+NumberFormat.getCurrencyInstance().format(amount)+"</td></tr>";
		}
		else
		{
			orderContent += "<tr><td></td><td>小計</td><td>"+NumberFormat.getCurrencyInstance().format(this.getAmount())+"</td></tr>";
			orderContent +="<tr><td>送貨費</td><td></td><td>"+NumberFormat.getCurrencyInstance().format(this.getDeliveryFee())+"</td></tr>";
			orderContent += "<tr><td></td><td><b>總計</b></td><td>"+NumberFormat.getCurrencyInstance().format(this.getAmount()+this.getDeliveryFee())+"</td></tr>";
		}
		//orderContent += "</table>";
		//orderContent += "<br/>";
		
		//Gifts
		List<CustomerOrderGift> gifts = new ArrayList<CustomerOrderGift>(this.getGiftList());
		Collections.sort(gifts, new Comparator<CustomerOrderGift>(){

			@Override
			public int compare(CustomerOrderGift o1, CustomerOrderGift o2) {
				return o2.getId() - o1.getId();
			}
			
		});
		
		//orderContent += "<table border=\"1\">";
		for(CustomerOrderGift gift: gifts)
		{
			orderContent += "<tr><td>"+gift.getTitle()+"</td><td>x"+gift.getAmount()+"</td><td></td></tr>";
			
		}
		orderContent += "</table>";
		
		orderContent +="<br/>";
		
		orderContent +="<h3>客戶信息:</h3>";
		orderContent += "<table border=\"1\">";
		orderContent +="<tr><td>客戶姓名：</td><td>"+this.getCustomerName()+"</td></tr>";
		orderContent +="<tr><td>客戶電話：</td><td>"+this.getCustomerPhoneNumber()+"</td></tr>";
		orderContent +="<tr><td>客戶電郵：</td><td>"+this.getCustomerEmail()+"</td></tr>";
		orderContent +="<tr><td>付款方式：</td><td>信用卡</td></tr>";
		orderContent +="<tr><td>信用卡類別：</td><td>"+this.getCreditCard().getCardTypeString()+"</td></tr>";
		orderContent +="<tr><td>信用卡銀行：</td><td>"+this.getCreditCard().getCreditCardBank()+"</td></tr>";
		orderContent +="<tr><td>信用卡號碼：</td><td>xxxx xxxx xxxx "+this.getCreditCard().getCardNumber()+"</td></tr>";
		orderContent +="<tr><td>取貨方式：</td>";
		if(this.isPickup())
		{
			orderContent+="<td>分店取貨</td></tr>";
			orderContent +="<tr><td>取貨日期：</td><td>"+dateFormat.format(this.getPickupInfo().getPickupDate())+"</td></tr>";
			orderContent +="<tr><td>取貨時段：</td><td>"+this.getPickupInfo().getPickupPeriod()+"</td></tr>";
			orderContent +="<tr><td>分店地址：</td><td>"+this.getPickupInfo().getStoreAddress()+"</td></tr>";
			orderContent +="<tr><td>分店電話：</td><td>"+this.getPickupInfo().getStorePhonenumber()+"</td></tr>";
			
		}
		else
		{
			orderContent+="<td>送貨服務</td></tr>";
			orderContent +="<tr><td>送貨日期：</td><td>"+dateFormat.format(this.getDeliveryInfo().getDate())+"</td></tr>";
			orderContent +="<tr><td>送貨時段：</td><td>"+this.getDeliveryInfo().getDeliveryPeriod()+"</td></tr>";
			orderContent +="<tr><td>送貨地址：</td><td>"+this.getDeliveryInfo().getDeliveryAddress()+"</td></tr>";
			orderContent +="<tr><td>收貨人：</td><td>"+this.getDeliveryInfo().getName()+"</td></tr>";
			orderContent +="<tr><td>收貨人電話：</td><td>"+this.getDeliveryInfo().getPhoneNumber()+"</td></tr>";
		}								
		orderContent += "</table>";
		
		orderContent += "<br>";
		orderContent += "謝謝惠顧！";
		orderContent += "<br>";
		orderContent += "如需修改或取消訂單請撥打熱線2101-1850";
		
		return "<html><head><meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\" /><body>"
				+ orderContent+ "</body></html>";
	}

	public String generateEmailSubject() {
		return "美心美食派對訂單" + this.getOrderCode();
	}

}
