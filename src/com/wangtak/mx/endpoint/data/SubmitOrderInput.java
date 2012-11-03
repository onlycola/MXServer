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
	class MenuOrder
	{
		int menuItemId;
		int menuOptionId;
		int amount;
	}
	class ComboOrder
	{
		int comboId;
		int amount;
	}
	List<MenuOrder> menuOrderList;
	List<ComboOrder> comboOrderList;
	String customerName;
	String customerPhoneNumber;
	String customerEmail;
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
	
}
