/**
 * 
 */
package com.wangtak.mx.endpoint;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.jboss.logging.Logger;

import com.wangtak.mx.common.PaymentManager;
import com.wangtak.mx.common.PaymentResult;
import com.wangtak.mx.database.EMF;
import com.wangtak.mx.endpoint.data.ConfirmOrderInput;
import com.wangtak.mx.endpoint.data.ConfirmOrderResult;
import com.wangtak.mx.endpoint.data.SubmitOrderResponse;
import com.wangtak.mx.entity.ComboOrder;
import com.wangtak.mx.entity.CreditCardDiscountRule;
import com.wangtak.mx.entity.CustomerOrder;
import com.wangtak.mx.entity.CustomerOrderGift;
import com.wangtak.mx.entity.DeliveryArea;
import com.wangtak.mx.entity.MenuCombo;
import com.wangtak.mx.entity.MenuItemOption;
import com.wangtak.mx.entity.MenuOrder;
import com.wangtak.mx.entity.OrderStatus;

/**
 * @author yuzhao
 * 
 */
@Path("/order")
public class OnlineOrderEndpoint {
	public static final int GeneralError = 0;
	public static final int CreditCardError = 1;
	public static final int OutOfDate = 2;
	public static final int NotExist = 3;
	
	//private static final String Prefix = "http://127.0.0.1:8080/MXServer/api/order/";
	private static final String Prefix = "http://mx.wangtaktech.com/api/order/";
	private static final int VALID_PERIOD = -10; // 10min
	private static Logger log = Logger.getLogger(OnlineOrderEndpoint.class);
	
    @Resource(name="java:jboss/mail/Default")
    private Session mailSession;


	@POST
	@Path("/submit")
	@Consumes("application/json")
	@Produces("application/json")
	public SubmitOrderResponse submitOrder(CustomerOrder order) {
		log.info("SubmitOrderResponse: with order:" + order);
		boolean hasError = false;
		SubmitOrderResponse response = new SubmitOrderResponse();
		// 2.generate url
		String uniqueURL = UUID.randomUUID().toString();
		EntityManager em = EMF.get().createEntityManager();
		try {
			// 1.calculate order
			//Generate Order Code
			order.setOrderCode(order.generateOrderCode());
			// 1.1 caculate menu item
			int bankId = order.getCreditCard().getCreditCardBankId();
			CreditCardDiscountRule rule = em.find(CreditCardDiscountRule.class,
					bankId);
			double triggerAmount = 0.0;
			double discountRate = 1;
			if (rule != null) {
				log.info("find the discount rule: " + rule);
				triggerAmount = rule.getTriggerAmount();
				discountRate = rule.getDiscountRate();
			}
			double totalPrice=0.0;
			//go through all menu item in order
			for(MenuOrder i: order.getMenuOrderList())
			{
				MenuItemOption item = em.find(MenuItemOption.class, i.getMenuOptionId());
				if(item==null)
				{
					response.setError("Menu is updated.");
					response.setOrderId(0);
					hasError = true;
					log.info("can not find menu option: "+i.getMenuOptionId());
					break;
				}
				else
				{
					double subTotal =0.0;
					if(item.isEnableSpecialPrice())
					{
						i.setUnitPrice(item.getPrice());
						subTotal = item.getPrice()*i.getAmount();
						if(subTotal>=triggerAmount)
						{
							subTotal = subTotal * discountRate;
						}
					}
					else
					{
						i.setUnitPrice(item.getSpecialPrice());
						subTotal = item.getSpecialPrice()*i.getAmount();
					}
					i.setTotalPrice(subTotal);
					totalPrice = totalPrice + subTotal;
				}
			}
			//go through all combo item in order
			for(ComboOrder j: order.getComboOrderList())
			{
				if(hasError)
				{
					break;
				}
				
				MenuCombo item = em.find(MenuCombo.class, j.getComboId());
				if(item==null)
				{
					response.setError("Menu is updated.");
					response.setOrderId(0);
					hasError = true;
					log.info("can not find combo:"+j.getComboId());
					break;
				}
				else
				{
					double subTotal =0.0;
					if(item.isEnableSpecialPrice())
					{
						j.setUnitPrice(item.getPrice());
						subTotal = item.getPrice()*j.getAmount();
						if(subTotal>=triggerAmount)
						{
							subTotal = subTotal * discountRate;
						}
					}
					else
					{
						j.setUnitPrice(item.getSpecialPrice());
						subTotal = item.getSpecialPrice()*j.getAmount();
					}
					j.setTotalPrice(subTotal);
					totalPrice = totalPrice + subTotal;
				}
			}
			order.setAmount(totalPrice);
			
			//Add delivery fee
			if(order.isPickup())
			{
				order.setDeliveryFee(0);
			}
			else
			{
				DeliveryArea item = em.find(DeliveryArea.class, order.getDeliveryInfo().getDeliveryAreaId());
				double deliveryFee = 0.0;
				if(item!=null)
				{
					if(totalPrice>688&&item.isFreeArea())
					{
						deliveryFee = 0.0;
					}
					else
					{
						deliveryFee = item.getDeliveryFee();
					}
				}
				else
				{
					deliveryFee = 400;
				}
				order.setDeliveryFee(deliveryFee);
			}
			
			//Free gift
			Date now = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.set(2012, 12, 20, 0, 0, 0);
			Set<CustomerOrderGift> gifts = new HashSet();
			//Rule 2,3，4，6 
			if(now.before(calendar.getTime()))
			{
				gifts.add(new CustomerOrderGift("$25 MX美食禮券", 1));
				
				gifts.add(new CustomerOrderGift("烤鋦原隻美國火雞禮券", 1));
				
				if(order.isPickup())
				{
					gifts.add(new CustomerOrderGift("皇牌鋦豬扒飯連汽水卷", 1));
				}
				
				int s = (int) (totalPrice/1000);
				gifts.add(new CustomerOrderGift("兩磅裝麵包布丁", s));
			}
			
			//Rule 5
			int t = (int) (totalPrice/300);
			gifts.add(new CustomerOrderGift("1.25公升『可口可樂』", t));
			
			order.setGiftList(gifts);
			
			
			if(!hasError)
			{
				order.setCreateDate(new Date());
				order.setUniqueURL(uniqueURL);
				order.setStatus(OrderStatus.Submit);
				log.info("update order:"+order.toString());
				// 3.save to database
				em.getTransaction().begin();
				em.persist(order);
				em.getTransaction().commit();
				
				//test send email
				if(mailSession != null)
				{
					try{
						MimeMessage m = new MimeMessage(mailSession);
				         Address from = new InternetAddress("cn.yu.zhao@gmail.com");
				         Address[] to = new InternetAddress[] {new InternetAddress("cn.yu.zhao@gmail.com") };

				         m.setFrom(from);

				         m.setRecipients(Message.RecipientType.TO, to);

				         m.setSubject("JavaMail Test - 2 ");
	
				         m.setSentDate(new java.util.Date());
			
				         m.setContent("Test from inside JBoss AS7 Server","text/plain");
			
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
					log.equals("big problem");
				}
			}
			
		} finally {
			em.close();
		}
		response.setOrderId(order.getId());
		response.setUrl(Prefix + "review/" + uniqueURL);
		return response;
	}

	@GET
	@Path("/review/{uniqueURL}")
	@Produces("text/html ;charset=utf-8")
	public String getOrderReview(@PathParam("uniqueURL") String unqiueURL) {
		log.info("getOrderReview" + unqiueURL);
		String response = null;
		EntityManager em = EMF.get().createEntityManager();
		try {
			Query query = em
					.createQuery(
							"SELECT c FROM CustomerOrder c WHERE c.uniqueURL = :uniqueURL and c.status = :status and c.createDate>:date",
							CustomerOrder.class);
			// results.addAll(query.getResultList());
			query.setParameter("uniqueURL", unqiueURL);
			query.setParameter("status", OrderStatus.Submit);
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MINUTE, VALID_PERIOD);
			query.setParameter("date", calendar.getTime());
			List<CustomerOrder> orders = query.getResultList();
			if (orders.size() > 0) {
				response = "<html><head><meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\" /><body><h1>Order Content:</h1>"
						+ orders.get(0).toString()
						+ "</body></html>";
			} else {
				response = "<html><head><meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\" /><body><h1>Not Found</h1></body></html>";
			}
		} finally {
			em.close();
		}
		return response;
	}

	@POST
	@Path("/confirm")
	@Consumes("application/json")
	@Produces("application/json")
	public ConfirmOrderResult confirmOrder(ConfirmOrderInput input) {
		log.info("confirmOrder" + input);
		ConfirmOrderResult result = new ConfirmOrderResult();
		result.setSucceed(false);
		result.setUrl(Prefix + "failed/" + GeneralError);
		do {

			EntityManager em = EMF.get().createEntityManager();
			try {
				// 1.find out the order
				CustomerOrder order = em.find(CustomerOrder.class,
						input.getOrderId());
				if (order != null
						&& order.getStatus() == OrderStatus.Submit
						&& input.getUniqueURL().compareTo(
								Prefix + "review/" + order.getUniqueURL()) == 0) {
					log.info("get order: " + order.toString());

					CreditCardDiscountRule rule = em.find(
							CreditCardDiscountRule.class, order.getCreditCard()
									.getCreditCardBankId());
					if (rule != null) {
						String header = rule.getBankHeader();
						if (header != null && header.length() > 0) {
							if (!input.getCardNumber().startsWith(header)
									|| !input.getCardNumber().endsWith(
											order.getCreditCard()
													.getCardNumber())) {
								log.warn("header " + header +" end "+ order.getCreditCard().getCardNumber()
										+ " and number inconsist:"
										+ input.getCardNumber());
								result.setSucceed(false);
								result.setUrl(Prefix + "failed/" + CreditCardError);
								break;
							}
						}
					}
					Calendar calendar = Calendar.getInstance();
					calendar.add(Calendar.MINUTE, VALID_PERIOD);
					if (order.getCreateDate().compareTo(calendar.getTime()) >= 0) {
						// 2. calculate the amount
						String cardType = "";
						switch (order.getCreditCard().getCardType()) {
						case 0:
							cardType = "VISA";
							break;
						case 1:
							cardType = "MASTER";
							break;
						default:
						}						
						// 3. make payment
						PaymentResult paymentResult = PaymentManager
								.transaction(order.getCreditCard().getHolder(),
										input.getCardNumber(), cardType, order
												.getCreditCard()
												.getValidMonth(),
										order.getCreditCard().getValidYear(),
										order.getAmount(), order.getOrderCode(), order
												.getCustomerEmail());
						if (paymentResult.isSucceed()) {
							// transaction success

							// 3.send email
							
							// 4.update DB
							em.getTransaction().begin();
							order.setStatus(OrderStatus.Confirm);
							order.setPaymentReference(paymentResult
									.getTransactionId());
							result.setSucceed(true);
							result.setUrl(Prefix + "succeed/"
									+ input.getOrderId());
							em.getTransaction().commit();
						} else {
							// transaction failed
							result.setSucceed(false);
							result.setUrl(Prefix + "failed/" + CreditCardError);

						}
					} else {
						log.info("order is out of date");
						result.setSucceed(false);
						result.setUrl(Prefix + "failed/" + OutOfDate);
					}

				} else {
					log.info("can not find order");
					result.setSucceed(false);
					result.setUrl(Prefix + "failed/" + NotExist);
				}
			} finally {
				em.close();
			}
		} while (false);
		return result;
	}

	@GET
	@Path("/succeed/{uniqueURL}")
	@Produces("text/html;charset=utf-8")
	public String getOrderResult(@PathParam("uniqueURL") String unqiueURL) {
		log.info("getOrderResult" + unqiueURL);
		return "<html><body><h1>Order Result</h1></body></html>";
	}
	
	@GET
	@Path("/failed/{errorCode}")
	@Produces("text/html;charset=utf-8")
	public String getOrderError(@PathParam("errorCode") int errorCode) {
		log.info("getOrderError" + errorCode);
		String ErrorString = "訂單提交失敗";
		switch(errorCode)
		{
		case CreditCardError:
			ErrorString = "信用卡錯誤";
			break;
		case OutOfDate:
			ErrorString = "交易過期";
			break;
		case NotExist:
			ErrorString = "交易不存在";
			break;
		default:
			break;
		}
		return "<html><body><h1>"+ErrorString+"</h1></body></html>";
	}
}
