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

import com.wangtak.mx.common.LocalizationManager;
import com.wangtak.mx.endpoint.OnlineOrderEndpoint;


/**
 * @author yuzhao
 *
 */
@Entity
public class CustomerOrder {
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	@Column(unique = true)
	String uniqueURL;
	String orderCode;//PT+date+phone+shop id+2 digital number
	@Column(unique = true)
	String paymentReference; //the tx reference return from payment gateway
	OrderStatus status;
	Date createDate;
	double amount;
	double deliveryFee;
	String remark;
	
	//free gift
	int minuteMaidAmount;// Minute Maid amount
	int milkTeaCouponAmount; // MX milk Tea Coupon amount
	int colaAmount; //coca cola amount
	int redBeanPuddingAmount; // red bean pudding amount
	
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

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
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
				+ deliveryFee + ", minuteMaidAmount=" + minuteMaidAmount
				+ ", milkTeaCouponAmount=" + milkTeaCouponAmount
				+ ", colaAmount=" + colaAmount + ", redBeanPuddingAmount="
				+ redBeanPuddingAmount + ", menuOrderList=" + menuOrderList
				+ ", comboOrderList=" + comboOrderList + ", giftList="
				+ giftList + ", customerName=" + customerName
				+ ", customerPhoneNumber=" + customerPhoneNumber
				+ ", customerEmail=" + customerEmail + ", receivePromotion="
				+ receivePromotion + ", creditCard=" + creditCard + ", pickup="
				+ pickup + ", pickupInfo=" + pickupInfo + ", deliveryInfo="
				+ deliveryInfo + ", remark="+remark+"]";
	}

	public String generateOrderCode() {
		SimpleDateFormat shortDateFormat = new SimpleDateFormat("MMdd");
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
		
		return "F"+shortDateFormat.format(date)+this.customerPhoneNumber+(int)randomNumber;
	}

	public String generateEmailContentType() {
		return "text/html; charset=UTF-8";
	}

	public String generateEmailContent() {
		String orderContent = "<h3>訂單參考編號:"+this.orderCode+"</h3><br>－閣下已完成網上訂購，於24小時內將有專人以電話核實閣下的訂單及告知閣下有關之訂單編號<br>－客戶可憑落單時輸入的手提電話號碼或訂單編號作訂單查詢<h3>訂單內容:</h3>";

		orderContent += " <table BORDER=1 CELLPADDING=3 CELLSPACING=1 RULES=ROWS FRAME=HSIDES cellpadding=\"0\" cellspacing=\"0\" class=\"mobile_table\"><tbody><tr style=\"background:#9D1523;color:#fff\"><th style=\"text-align:left\">您所選擇的產品</th> <th width=\"40\" style=\"text-align:center\">   數量    </th> <th width=\"30\" style=\"text-align:right\">總額 </th>";
		CustomerOrder order = this;
		double totalPrice = 0.0;
		//Menu item
		List<MenuOrder> itemList = new ArrayList(order.getMenuOrderList());
		Collections.sort(itemList, new Comparator<MenuOrder>(){

			@Override
			public int compare(MenuOrder o1, MenuOrder o2) {
				return o2.getId()-o1.getId();
			}
		});
		boolean hasSpecial = false;
		
		for(MenuOrder o:itemList)
		{
			if(o.getAmount()>0)
			{
				double itemTotalPrice =0.0;
				int amount = 0 ;
				if(OnlineOrderEndpoint.isBeforeDec17()&&o.getTitleForDisplay().contains("紐約芝士餅"))
				{
					//list "cheese cake" stand alone
					hasSpecial = true;
					amount = o.getAmount()-1;
					itemTotalPrice = o.getUnitPrice()*amount;
					
				}
				else
				{
					amount = o.getAmount();
					itemTotalPrice= o.getAmount() * o.getUnitPrice();
				}
				totalPrice += itemTotalPrice;
				if(amount>0)
				{
					orderContent +="<tr><td>"+o.getTitleForDisplay()+"</td><td style=\"text-align:center\">"+amount+"</td><td style=\"text-align:center\">"+NumberFormat.getCurrencyInstance().format(itemTotalPrice)+"</td></tr>";
				}
			}
		}
		
		//Combo
		List<ComboOrder> comboList = new ArrayList(order.getComboOrderList());
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
				double itemTotalPrice =0.0;
				itemTotalPrice = o.getAmount() * o.getUnitPrice();
				totalPrice += itemTotalPrice;
				orderContent +="<tr><td>"+o.getTitleForDisplay()+"</td><td style=\"text-align:center\">"+o.getAmount()+"</td><td style=\"text-align:center\">"+NumberFormat.getCurrencyInstance().format(itemTotalPrice)+"</td></tr>";
			}

		}
		double discount =1;
		switch(order.getCreditCard().getCreditCardBankId())
		{
		case 0:
			discount = 0.8;
			break;
		case 1:
		case 2:
			discount =0.68;
			break;
		default:
			discount = 1;
		}
		orderContent += "<tr><td></td><td style=\"text-align:center\";background-color:#9D1523;color:#fff;>原價</td><td><strong>"+NumberFormat.getCurrencyInstance().format(totalPrice)+"</strong></td></tr>";
		orderContent += "<tr><td></td><td style=\"text-align:center\"; background-color:red; color:#fff;><strong>信用卡折扣價</strong></td><td><strong>"+NumberFormat.getCurrencyInstance().format(totalPrice*discount)+"</strong></td></tr>";
		//orderContent += "<table border=\"1\">";
		if(hasSpecial)
		{
			orderContent += "<tr><td>特價產品</td></tr><tr><td>紐約芝士餅一客</td><td style=\"text-align:center\"><b>1</b></td><td>"+NumberFormat.getCurrencyInstance().format(148.0)+"</td></tr>";
		}
		
		//Gifts
		List<CustomerOrderGift> gifts = new ArrayList<CustomerOrderGift>(order.getGiftList());
		Collections.sort(gifts, new Comparator<CustomerOrderGift>(){

			@Override
			public int compare(CustomerOrderGift o1, CustomerOrderGift o2) {
				return o2.getId() - o1.getId();
			}
			
		});
		
		//orderContent += "<table border=\"1\">";
		if(gifts.size()>0)
		{
			orderContent += "<tr><td></td></tr>";
		}
		for(CustomerOrderGift gift: gifts)
		{
			if(gift.getTitle().equalsIgnoreCase(LocalizationManager.GetGift6()))
			{
				orderContent += "<tr><td>"+gift.getTitle()+"</td><td  style=\"text-align:center\">"+gift.getAmount()+"</td><td>"+NumberFormat.getCurrencyInstance().format(10*gift.getAmount())+"</td></tr>";
			}
			else
			{
				orderContent += "<tr><td>"+gift.getTitle()+"</td><td style=\"text-align:center\">"+gift.getAmount()+"</td><td></td></tr>";
			}
		}
		if(order.isPickup()||order.getAmount()<=0.0)
		{
			double amount = 0;
			if(order.getAmount()>=0)
			{
				amount = order.getAmount();
			}
			orderContent += "<tr></tr><tr><td></td><td style=\"text-align:center\"><b>總計</b></td><td>"+NumberFormat.getCurrencyInstance().format(amount)+"</td></tr>";
		}
		else
		{
			orderContent += "<tr><td></td><td style=\"text-align:center\">小計</td><td>"+NumberFormat.getCurrencyInstance().format(order.getAmount())+"</td></tr>";
			orderContent +="<tr><td>送貨費</td><td></td><td>"+NumberFormat.getCurrencyInstance().format(order.getDeliveryFee())+"</td></tr>";
			orderContent += "<tr><td></td><td style=\"text-align:center\"><b>總計</b></td><td>"+NumberFormat.getCurrencyInstance().format(order.getAmount()+order.getDeliveryFee())+"</td></tr>";
		}
		//orderContent += "</table>";
		//orderContent += "<br/>";
		
		
		orderContent += "</tbody></table>";
		
		orderContent +="<br/>";
		
		orderContent +="<h3 style=\"margin:0;background-color:#9D1523;color:#fff;width:200px\">顧客信息:</h3><table style='table-layout:fixed;' BORDER=1 CELLPADDING=3 CELLSPACING=1 RULES=ROWS FRAME=HSIDES cellpadding=\"0\" cellspacing=\"0\"><tbody>";
		//orderContent +="<th width=\"80\" style=\"text-align:center\">顧客信息:  </th><th></th>";
		orderContent +="<tbody><tr><td width=\"100\">顧客姓名：</td><td>"+order.getCustomerName()+"</td></tr>";
		orderContent +="<tr><td>顧客電話：</td><td>"+order.getCustomerPhoneNumber()+"</td></tr>";
		orderContent +="<tr><td>顧客電郵：</td><td>"+order.getCustomerEmail()+"</td></tr>";
		orderContent +="<tr><td>付款方式：</td><td>信用卡</td></tr>";
		orderContent +="<tr><td>類別：</td><td>"+order.getCreditCard().getCardTypeString()+"</td></tr>";
		orderContent +="<tr><td>銀行：</td><td>"+order.getCreditCard().getCreditCardBank()+"</td></tr>";
		orderContent +="<tr><td>號碼：</td><td>xxxx xxxx xxxx "+order.getCreditCard().getCardNumber()+"</td></tr>";
		orderContent +="<tr><td>取貨方式：</td>";
		if(order.isPickup())
		{
			orderContent+="<td>分店取貨</td></tr>";
			orderContent +="<tr><td>取貨日期：</td><td>"+dateFormat.format(order.getPickupInfo().getPickupDate())+"</td></tr>";
			orderContent +="<tr><td>取貨時段：</td><td>"+order.getPickupInfo().getPickupPeriod()+"</td></tr>";
			orderContent +="<tr><td>分店名稱：</td><td>"+order.getPickupInfo().getStoreTitle()+"</td></tr>";
			orderContent +="<tr><td>分店編號：</td><td>"+order.getPickupInfo().getStoreCode()+"</td></tr>";
			
			orderContent +="<tr><td>分店地址：</td><td>"+order.getPickupInfo().getStoreAddress()+"</td></tr>";
			orderContent +="<tr><td>分店電話：</td><td>"+order.getPickupInfo().getStorePhonenumber()+"</td></tr>";
			
		}
		else
		{
			orderContent+="<td>送貨服務</td></tr>";
			orderContent +="<tr><td>送貨日期：</td><td>"+dateFormat.format(order.getDeliveryInfo().getDate())+"</td></tr>";
			orderContent +="<tr><td>送貨時段：</td><td>"+order.getDeliveryInfo().getDeliveryPeriod()+"</td></tr>";
			orderContent +="<tr><td>送貨地址：</td><td>"+order.getDeliveryInfo().getDeliveryAddress()+"</td></tr>";
			orderContent +="<tr><td>收貨人：</td><td>"+order.getDeliveryInfo().getName()+"</td></tr>";
			orderContent +="<tr><td>收貨人電話：</td><td>"+order.getDeliveryInfo().getPhoneNumber()+"</td></tr>";
		}								
		orderContent += "</tbody></table>";
		
		orderContent +="<div style=\"width:200px\">";
		orderContent +="<br><strong>註明事項:</strong><br>";
		if(order.remark!=null)
		{
			orderContent +=order.remark;
		}
		else
		{
			orderContent +="無";
		}
		orderContent += "</div>";
		orderContent += "<br>";
		orderContent += "謝謝惠顧！";
		orderContent += "<br>";
		orderContent += "如需修改訂單請撥打熱線2101-1293";
		
		return "<html><head><meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\" /><body>"
				+ orderContent+ "</body></html>";
	}

	public String generateEmailSubject() {
		return "美心MX聖誕美食訂購確認";
	}

}
