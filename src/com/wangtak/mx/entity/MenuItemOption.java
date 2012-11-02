/**
 * 
 */
package com.wangtak.mx.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author yuzhao
 *
 */
@Entity
public class MenuItemOption {
	@Id @GeneratedValue
	int id;
	String title;
	double price;
	double specialPrice;
}
