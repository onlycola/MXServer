/**
 * 
 */
package com.wangtak.mx.common;

import java.util.Date;

import javax.annotation.ManagedBean;
import javax.annotation.Resource;
import javax.ejb.AccessTimeout;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

import com.wangtak.mx.database.EMF;
import com.wangtak.mx.entity.CustomerOrder;
import com.wangtak.mx.entity.EmailRecord;

/**
 * @author piglet
 * 
 */
@Stateless
@Local(NotificationManager.class)
@Asynchronous
public class NotificationManager {
	private static final String MX_Operation_Email = "cn.yu.zhao@gmail.com";

	private static Logger log = Logger.getLogger(NotificationManager.class);

	@PersistenceContext(unitName = "Mysql")
	EntityManager em;
	
	@Asynchronous
	@AccessTimeout(60*1000)
	public void notifySuccess(CustomerOrder order, Session mailSession) {
		log.info("notifySuccess: order " + order);
		EmailRecord record = new EmailRecord();
		record.setEmailAddress(order.getCustomerEmail());
		record.setOrderCode(order.getOrderCode());
		record.setSendDate(new Date());
		try {
			if (mailSession != null) {

				MimeMessage m = new MimeMessage(mailSession);
				// Address from = new
				// InternetAddress("noreply@wangtaktech.com");
				Address[] to = new InternetAddress[] {
						new InternetAddress(order.getCustomerEmail()),
						new InternetAddress(MX_Operation_Email) };
				// m.setFrom(from);
				m.setRecipients(Message.RecipientType.TO, to);

				m.setSubject(order.generateEmailSubject());

				m.setSentDate(new java.util.Date());

				m.setContent(order.generateEmailContent(), order.generateEmailContentType());

				Transport.send(m);
				record.setSucceed(true);

			} else {
				log.error("Failed to send email: mail session is null");
				record.setSucceed(false);
			}
		} catch (AddressException e) {
			record.setSucceed(false);
			e.printStackTrace();
		} catch (MessagingException e) {
			record.setSucceed(false);
			e.printStackTrace();
		} finally {
			//EntityManager em = EMF.get().createEntityManager();
			try {
				//em.setFlushMode(FlushModeType.COMMIT);
				//em.getTransaction().begin();
				em.persist(record);
				//em.getTransaction().commit();

			} finally {
				//em.close();
			}
		}

	}

}
