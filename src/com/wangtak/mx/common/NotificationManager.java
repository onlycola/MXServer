/**
 * 
 */
package com.wangtak.mx.common;

import javax.annotation.ManagedBean;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.jboss.logging.Logger;

import com.wangtak.mx.entity.CustomerOrder;

/**
 * @author piglet
 *
 */
public class NotificationManager {
	private static final String MX_Operation_Email = "cn.yu.zhao@gmail.com";

	private static Logger log = Logger.getLogger(NotificationManager.class);

    
	public static void notifySuccess(CustomerOrder order, Session mailSession) {
		log.info("notifySuccess: order "+order);
		if(mailSession != null)
		{
			try{			    
				MimeMessage m = new MimeMessage(mailSession);
		        //Address from = new InternetAddress("noreply@wangtaktech.com");
		        Address[] to = new InternetAddress[] {new InternetAddress(order.getCustomerEmail()),
		        		new InternetAddress(MX_Operation_Email)};
		        //m.setFrom(from);
		         m.setRecipients(Message.RecipientType.TO, to);

		         m.setSubject("order reception");

		         m.setSentDate(new java.util.Date());
	
		         m.setContent(order.toString(),"text/plain");
		         
		         Transport.send(m);
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				
			}
			 	

		}
		else
		{
			log.error("Failed to send email: mail session is null");
		}
		
	}

}
