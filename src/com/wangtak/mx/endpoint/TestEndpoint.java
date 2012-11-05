/**
 * 
 */
package com.wangtak.mx.endpoint;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.jboss.logging.Logger;

import com.wangtak.mx.common.NotificationManager;
import com.wangtak.mx.entity.CustomerOrder;

/**
 * @author piglet
 *
 */
@Path("/test")
public class TestEndpoint {
	private static Logger log = Logger.getLogger(TestEndpoint.class);
		
	@GET
	@Path("/email")
	@Produces("text/plain")
	public String testEmail()
	{
		log.info("testEmail");

		return "succeed";
	}
}
