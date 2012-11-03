/**
 * 
 */
package com.wangtak.mx.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * @author yuzhao
 *
 */
@Entity
public class MenuCategory {
	@Id @GeneratedValue
	int id; //unique id
	int sort_order; //sort the items based on sort_order
	@Column(nullable = false)
	String title; //title for MenuCategory
	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.DETACH)
	Set<MenuItem> menuItemList; // Menu items for this category
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
	 * @return the menuItemList
	 */
	public Set<MenuItem> getMenuItemList() {
		return menuItemList;
	}
	/**
	 * @param menuItemList the menuItemList to set
	 */
	public void setMenuItemList(Set<MenuItem> menuItemList) {
		this.menuItemList = menuItemList;
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
}
