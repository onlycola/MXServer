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
import com.wangtak.mx.entity.Poster;

/**
 * @author yuzhao
 *
 */
@Path("/poster")
public class PosterEndpoint {
	private static Logger log = Logger.getLogger(PosterEndpoint.class);
	
	@GET
	@Path("/")
	@Produces("application/json")
	public List<Poster> getPoster()
	{
		log.info("getPoster");
		List<Poster> posterList = new ArrayList();
		EntityManager em = EMF.get().createEntityManager();
		try {
			 Query query = em.createQuery("SELECT c FROM Poster c", Poster.class);
			 posterList = query.getResultList();
		}finally {
			em.close();
		}
		return posterList;
	}
}
