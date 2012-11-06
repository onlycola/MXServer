/**
 * 
 */
package com.wangtak.mx.common;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.jboss.logging.Logger;

import com.wangtak.mx.endpoint.OnlineOrderEndpoint;

/**
 * @author piglet
 *
 */
@Startup
@Singleton(name = "LocalizationManager")
public class LocalizationManager {
	private static Logger log = Logger.getLogger(LocalizationManager.class);

	@PostConstruct
	void init() {
		log.info("=========== INITIALIZING! ===========");
	}

	public static String GetGift1() {
		return "$25 MX美食禮券";
	}

	public static String GetGift2() {
		return "烤焗美國火雞禮券";
	}

	public static String GetGift4() {
		return "兩磅裝麵包步甸";
	}

	public static String GetGift3() {
		return "免費皇牌豬扒飯連汽水卷";
	}

	public static String GetGift5() {
		return "1.25公升『可口可樂』";
	}

	public static String GetAPIPref() {
		return "http://mx.wangtaktech.com/api/order/";
	}
	
	
}
