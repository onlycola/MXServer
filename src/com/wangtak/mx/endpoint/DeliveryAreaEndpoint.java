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
import com.wangtak.mx.entity.DeliveryArea;

/**
 * @author yuzhao
 *
 */
@Path("/deliveryarea")
public class DeliveryAreaEndpoint {
	private static Logger log = Logger.getLogger(DeliveryAreaEndpoint.class);
	
	@GET
	@Path("/")
	@Produces("application/json")
	public List<DeliveryArea> getDeliveryArea()
	{
		log.info("getCreditCardDiscountRule");
		List<DeliveryArea> areaList = new ArrayList();
		EntityManager em = EMF.get().createEntityManager();
		try {
			 Query query = em.createQuery("SELECT c FROM DeliveryArea c", DeliveryArea.class);
			 areaList = query.getResultList();
		}finally {
			em.close();
		}
		return areaList;
	}
}
