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
import com.wangtak.mx.entity.CreditCardDiscountRule;


/**
 * @author yuzhao
 *
 */
@Path("/creditcard")
public class CreditCardEndpoint {
	private static Logger log = Logger.getLogger(CreditCardEndpoint.class);
	
	@GET
	@Path("/discount")
	@Produces("application/json")
	public List<CreditCardDiscountRule> getCreditCardDiscountRule()
	{
		log.info("getCreditCardDiscountRule");
		List<CreditCardDiscountRule> discountList = new ArrayList();
		EntityManager em = EMF.get().createEntityManager();
		try {
			 Query query = em.createQuery("SELECT c FROM CreditCardDiscountRule c", CreditCardDiscountRule.class);
			 discountList = query.getResultList();
		}finally {
			em.close();
		}
		return discountList;
	}
}
