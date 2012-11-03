/**
 * 
 */
package com.wangtak.mx.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author yuzhao
 *
 */
@Entity
public class CreditCardDiscountRule {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	int id; //unique id
	int sort_order; //sort credit card based on sort_order
	@Column(nullable = false)
	String cardtitle; //credit card title
	@Column(nullable = false)
	double triggerAmount=0.0; //if the amount >= the trigger amount, the discount Rate is applied
	@Column(nullable = false)
	double discountRate =1.0; //discount rate
	@Column(nullable = true)
	String discountDescription; //the description for the discount rule
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
	 * @return the sort_order
	 */
	public int getSort_order() {
		return sort_order;
	}
	/**
	 * @param sort_order the sort_order to set
	 */
	public void setSort_order(int sort_order) {
		this.sort_order = sort_order;
	}
	/**
	 * @return the cardtitle
	 */
	public String getCardtitle() {
		return cardtitle;
	}
	/**
	 * @param cardtitle the cardtitle to set
	 */
	public void setCardtitle(String cardtitle) {
		this.cardtitle = cardtitle;
	}
	/**
	 * @return the triggerAmount
	 */
	public double getTriggerAmount() {
		return triggerAmount;
	}
	/**
	 * @param triggerAmount the triggerAmount to set
	 */
	public void setTriggerAmount(double triggerAmount) {
		this.triggerAmount = triggerAmount;
	}
	/**
	 * @return the discountRate
	 */
	public double getDiscountRate() {
		return discountRate;
	}
	/**
	 * @param discountRate the discountRate to set
	 */
	public void setDiscountRate(double discountRate) {
		this.discountRate = discountRate;
	}
	/**
	 * @return the discountDescription
	 */
	public String getDiscountDescription() {
		return discountDescription;
	}
	/**
	 * @param discountDescription the discountDescription to set
	 */
	public void setDiscountDescription(String discountDescription) {
		this.discountDescription = discountDescription;
	}
}
