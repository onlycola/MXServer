/**
 * 
 */
package com.wangtak.mx.endpoint.data;

/**
 * @author yuzhao
 *
 */
public class ComboOrder
{
	int comboId;
	int amount;
	/**
	 * @return the comboId
	 */
	public int getComboId() {
		return comboId;
	}
	/**
	 * @param comboId the comboId to set
	 */
	public void setComboId(int comboId) {
		this.comboId = comboId;
	}
	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ComboOrder [comboId=" + comboId + ", amount=" + amount + "]";
	}
}
