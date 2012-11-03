/**
 * 
 */
package com.wangtak.mx.endpoint;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.jboss.logging.Logger;

import com.wangtak.mx.database.EMF;
import com.wangtak.mx.entity.MenuCategory;
import com.wangtak.mx.entity.MenuCombo;

/**
 * @author yuzhao
 *
 */
@Path("/menu")
public class MenuEndpoint {
	private static Logger log = Logger.getLogger(MenuEndpoint.class);
	
	@GET
	@Path("/category")
	@Produces("application/json")
	public List<MenuCategory> getMenuCatagory()
	{
		log.info("getMenuCategory");
		List<MenuCategory> catagoryList = new ArrayList();
		EntityManager em = EMF.get().createEntityManager();
		try {
			 Query query = em.createQuery("SELECT c FROM MenuCategory AS c", MenuCategory.class);
			 //results.addAll(query.getResultList());
			 catagoryList = query.getResultList();
//			 for(Property p:results)
//			 {
//				 PropertyBasic basic = PropertyBasic.createPropertyBasic(p);
//				 log.info("property:"+ new String(p.getHighlight().getBytes("utf-8"),"utf-8"));
//				 log.info("property:"+ p.getHighlight());
//				 propertyList.add(basic); 
//			 }
		}finally {
			em.close();
		}
		return catagoryList;
	}
	
	@GET
	@Path("/combo")
	@Produces({ "application/json" })
	public List<MenuCombo> getMenuCombo()
	{
		log.info("getMenuCombo");
		List<MenuCombo> comboList = new ArrayList();
		EntityManager em = EMF.get().createEntityManager();
		try {
			 Query query = em.createQuery("SELECT c FROM MenuCombo AS c", MenuCombo.class);
			 //results.addAll(query.getResultList());
			 comboList = query.getResultList();
//			 for(Property p:results)
//			 {
//				 PropertyBasic basic = PropertyBasic.createPropertyBasic(p);
//				 log.info("property:"+ new String(p.getHighlight().getBytes("utf-8"),"utf-8"));
//				 log.info("property:"+ p.getHighlight());
//				 propertyList.add(basic); 
//			 }
		}finally {
			em.close();
		}
		return comboList;
	}
}
