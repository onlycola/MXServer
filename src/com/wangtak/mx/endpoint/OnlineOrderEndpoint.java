/**
 * 
 */
package com.wangtak.mx.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.jboss.logging.Logger;

import com.wangtak.mx.endpoint.data.SubmitOrderInput;
import com.wangtak.mx.endpoint.data.SubmitOrderResponse;
import com.wangtak.mx.entity.Poster;

/**
 * @author yuzhao
 *
 */
@Path("/order")
public class OnlineOrderEndpoint {
	private static Logger log = Logger.getLogger(OnlineOrderEndpoint.class);
	
	@POST
	@Path("/submit")
	@Consumes("application/json")
	@Produces("application/json")
	public SubmitOrderResponse submitOrder(SubmitOrderInput input)
	{
		log.info("SubmitOrderResponse: with input:"+ input.getCustomerName());
		SubmitOrderResponse response = new SubmitOrderResponse();
		response.setOrderId("1234567890");
		response.setUrl("http://mx.wangtaktech.com");
		return response;
	}
}
