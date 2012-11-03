/**
 * 
 */
package com.wangtak.mx.endpoint.data;

import java.util.List;

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
	boolean isReceivePromotion;
	//Credit Card
	CreditCard creditCard;
	
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SubmitOrderInput [menuOrderList=" + menuOrderList
				+ ", comboOrderList=" + comboOrderList + ", customerName="
				+ customerName + ", customerPhoneNumber=" + customerPhoneNumber
				+ ", customerEmail=" + customerEmail + "]";
	}
	
}
