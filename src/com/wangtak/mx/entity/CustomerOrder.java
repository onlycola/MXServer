/**
 * 
 */
package com.wangtak.mx.entity;

import java.util.Date;
import java.util.List;

/**
 * @author yuzhao
 *
 */
public class CustomerOrder {
	int id;
	String phoneNumber;
	String customerName;
	String email;
	Date createDate;
	List<CustomerOrderItem> orderItemList;
	List<CustomerOrderGift> giftItemList;
	String creditCard;
}
