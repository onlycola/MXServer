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

/**
 * @author yuzhao
 *
 */
@Entity
public class MenuItem {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	int id; //unique id
	int sort_order; //sort the item based on sort_order
	@Column(nullable = false)
	String title; //title for menu item
	String subtitle; //subtitle for menu item
	String picturURL; //the image url for this menu item
	Date lastPickupDate; //the food can not pickup after the date
	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	Set<MenuItemOption> menuItemOptionList; // Menu Options for this menu item
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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the subtitle
	 */
	public String getSubtitle() {
		return subtitle;
	}
	/**
	 * @param subtitle the subtitle to set
	 */
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
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
	 * @return the menuItemOptionList
	 */
	public Set<MenuItemOption> getMenuItemOptionList() {
		return menuItemOptionList;
	}
	/**
	 * @param menuItemOptionList the menuItemOptionList to set
	 */
	public void setMenuItemOptionList(Set<MenuItemOption> menuItemOptionList) {
		this.menuItemOptionList = menuItemOptionList;
	}
	/**
	 * @return the picturURL
	 */
	public String getPicturURL() {
		return picturURL;
	}
	/**
	 * @param picturURL the picturURL to set
	 */
	public void setPicturURL(String picturURL) {
		this.picturURL = picturURL;
	}
	/**
	 * @return the lastPickupDate
	 */
	public Date getLastPickupDate() {
		return lastPickupDate;
	}
	/**
	 * @param lastPickupDate the lastPickupDate to set
	 */
	public void setLastPickupDate(Date lastPickupDate) {
		this.lastPickupDate = lastPickupDate;
	}
	@Override
	public String toString() {
		return "MenuItem [id=" + id + ", sort_order=" + sort_order + ", title="
				+ title + ", subtitle=" + subtitle + ", picturURL=" + picturURL
				+ ", menuItemOptionList=" + menuItemOptionList + "]";
	}
}
