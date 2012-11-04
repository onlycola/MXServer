/**
 * 
 */
package com.wangtak.mx.endpoint;

import java.util.Date;
import java.util.UUID;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.jboss.logging.Logger;

import com.wangtak.mx.database.EMF;
import com.wangtak.mx.endpoint.data.ConfirmOrderInput;
import com.wangtak.mx.endpoint.data.ConfirmOrderResult;
import com.wangtak.mx.endpoint.data.SubmitOrderResponse;
import com.wangtak.mx.entity.CustomerOrder;

/**
 * @author yuzhao
 *
 */
@Path("/order")
public class OnlineOrderEndpoint {
	//private static final String Prefix = "http://127.0.0.1:8080/MXServer/api/order/";
	private static final String Prefix = "http://mx.wangtaktech.com/api/order/";
	private static Logger log = Logger.getLogger(OnlineOrderEndpoint.class);
	
	@POST
	@Path("/submit")
	@Consumes("application/json")
	@Produces("application/json")
	public SubmitOrderResponse submitOrder(CustomerOrder order)
	{
		log.info("SubmitOrderResponse: with order:"+ order);
		SubmitOrderResponse response = new SubmitOrderResponse();
		//1.valid the order
		//2.generate url
		String uniqueURL = UUID.randomUUID().toString();
		//3.save to database
		order.setCreateDate(new Date());
		order.setUniqueURL(uniqueURL);
		EntityManager em = EMF.get().createEntityManager();
		try {
			em.getTransaction().begin();
			//update db
			em.persist(order);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
		response.setOrderId(String.valueOf(order.getId()));
		response.setUrl(Prefix+"review/"+uniqueURL);
		return response;
	}
	
	@GET
	@Path("/review/{uniqueURL}")
	@Produces("text/html")
	public String getOrderReview(@PathParam("uniqueURL") String unqiueURL)
	{
		log.info("getOrderReview"+ unqiueURL);
		return "<html><body><h1>Party Order</h1></body></html>";
	}
	
	@POST
	@Path("/confirm")
	@Consumes("application/json")
	@Produces("application/json")
	public ConfirmOrderResult confirmOrder(ConfirmOrderInput input)
	{
		log.info("confirmOrder"+ input);
		ConfirmOrderResult result = new ConfirmOrderResult();
		//1.find out the order
		//2.make payment
		//3.send email
		//4.update DB
		result.setSucceed(true);
		result.setUrl(Prefix+"result/"+input.getOrderId());
		return result;
	}
	
	@GET
	@Path("/result/{uniqueURL}")
	@Produces("text/html")
	public String getOrderResult(@PathParam("uniqueURL") String unqiueURL)
	{
		log.info("getOrderResult"+ unqiueURL);
		return "<html><body><h1>Order Result</h1></body></html>";
	}
}
