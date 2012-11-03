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
import com.wangtak.mx.entity.DeliveryNote;
import com.wangtak.mx.entity.PickupNote;

/**
 * @author yuzhao
 *
 */
@Path("/note")
public class NotesEndpoint {
//	private static final String PickupNote = "1）請於取貨／送貨日3工作天前完成訂購\r2）若貴客未能在訂購的指定期日取貨，請於不少於36小時撥打服務熱線2101-1850，修改取消，否則繳付之金額將不獲退還\r3)親身蒞臨取貨，可獲贈汽水卷1張";
//	private static final String DeliveryNote = "1）請於取貨／送貨日3工作天前完成訂購\r2）如惠顧不足＄2，000（折實價），送貨費＄200，西貢及東涌額外收費＄400，離島、大嶼山、赤蠟角、馬灣及愉景灣不設送貨服務\n3）免費送貨服務範圍不包括離島、大嶼山、赤蠟角、馬灣及愉景灣\n4）訂購1滿＄688（折實價），可享免費送貨服務一次";
	
	private static Logger log = Logger.getLogger(NotesEndpoint.class);
	
	@GET
	@Path("/delivery")
	@Produces("text/plain")
	public String getDeliveryNote()
	{
		String note = null;
		log.info("getDeliveryNote");
		List<DeliveryNote> results = new ArrayList();
		EntityManager em = EMF.get().createEntityManager();
		try {
			 Query query = em.createQuery("SELECT c FROM DeliveryNote c", DeliveryNote.class);
			 results = query.getResultList();
			 if(results.size()>0)
			 {
				 note = results.get(0).getNote();
			 }
		}finally {
			em.close();
		}
		return note;
	}
	
	@GET
	@Path("/pickup")
	@Produces("text/plain")
	public String getPickupNote()
	{
		String note = null;
		log.info("getPickupNote");
		List<PickupNote> results = new ArrayList();
		EntityManager em = EMF.get().createEntityManager();
		try {
			 Query query = em.createQuery("SELECT c FROM PickupNote c", PickupNote.class);
			 results = query.getResultList();
			 if(results.size()>0)
			 {
				 note = results.get(0).getNote();
			 }
		}finally {
			em.close();
		}
		return note;
	}

}
