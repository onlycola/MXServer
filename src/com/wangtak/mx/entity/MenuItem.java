/**
 * 
 */
package com.wangtak.mx.entity;

import java.util.List;

import javax.persistence.CascadeType;
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
public class MenuItem {
	@Id @GeneratedValue
	int id;
	String title;
	String subtitle;
	Boolean isSpecialPrice;
	Boolean isMultiOption;
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.DETACH)
	List<MenuItemOption> menuItemOptionList;
}
