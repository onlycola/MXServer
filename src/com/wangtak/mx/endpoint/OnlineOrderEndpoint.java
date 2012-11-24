/**
 * 
 */
package com.wangtak.mx.endpoint;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.ManagedBean;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.mail.Session;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.Servlet;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.jboss.logging.Logger;

import com.wangtak.mx.common.LocalizationManager;
import com.wangtak.mx.common.NotificationManager;
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
import com.wangtak.mx.entity.MenuItem;
import com.wangtak.mx.entity.MenuItemOption;
import com.wangtak.mx.entity.MenuOrder;
import com.wangtak.mx.entity.OrderStatus;

/**
 * @author yuzhao
 * 
 */
@Path("/order")
@ManagedBean
public class OnlineOrderEndpoint {
	public static final int GeneralError = 0;
	public static final int CreditCardError = 1;
	public static final int OutOfDate = 2;
	public static final int NotExist = 3;

	// private static final String Prefix =
	// "http://127.0.0.1:8080/MXServer/api/order/";
	private static final String Prefix = LocalizationManager.GetAPIPref();
	private static final int VALID_PERIOD = -10; // 10min
	private static Logger log = Logger.getLogger(OnlineOrderEndpoint.class);
	private static Pattern p = Pattern.compile("\\d{4}");
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    
	
    @Resource(lookup="java:jboss/mail/Default")
    private static Session mailSession;
    
    @EJB
    NotificationManager notificationManager;
    
    @EJB
    PaymentManager paymentManager;

	@GET
	@Path("/email")
	@Produces("text/plain")
    public String testEmail()
    {
		// 4.send email
		CustomerOrder order = new CustomerOrder();
		order.setOrderCode("PT1");
		order.setCustomerEmail("cn.yu.zhao@gmail.com");
		notificationManager.notifySuccess(order, mailSession);
		return "succeed";
    }
	
    
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
		//Todo: Add credit card later
//		Matcher m = p.matcher(order.getCreditCard().getCardNumber());
//		if(!m.matches())
//		{
//			log.warn("credit card is not the 4 digital number");
//			response.setError("credit card must be the last 4 digital number");
//			response.setOrderId(0);
//			return response;
//		}
		EntityManager em = EMF.get().createEntityManager();
		try {
			// 1.calculate order
			// Generate Order Code
			order.setOrderCode(order.generateOrderCode());
			// 1.1 caculate menu item
			double totalPrice =0.0;
			int bankId = order.getCreditCard().getCreditCardBankId();
			double discountRate=1.0;
			switch (bankId)
			{
			case 1:
			case 2:
				discountRate = 0.68;
				break;
			default:
				discountRate =0.8;
			}
			double triggerAmount =0.0;
//			CreditCardDiscountRule rule = em.find(CreditCardDiscountRule.class,
//					bankId);
//			double triggerAmount = 0.0;
//			double discountRate = 1;
//			if (rule != null) {
//				log.info("find the discount rule: " + rule);
//				triggerAmount = rule.getTriggerAmount();
//				discountRate = rule.getDiscountRate();
//				order.getCreditCard().setCreditCardBank(rule.getCardtitle());
//			}
//			double totalPrice = 0.0;
			// go through all menu item in order
			
			boolean isSpecial = false;
			if (order.getMenuOrderList() != null) {
				for (MenuOrder i : order.getMenuOrderList()) {
					MenuItemOption itemOption = em.find(MenuItemOption.class,
							i.getMenuOptionId());
					MenuItem item = em.find(MenuItem.class,
							i.getMenuItemId());
					//valid the input data
					if (itemOption == null||item==null||!item.getMenuItemOptionList().contains(itemOption)) {
						response.setError("Menu is updated.");
						response.setOrderId(0);
						hasError = true;
						log.info("can not find menu option: "
								+ i.getMenuOptionId());
						break;
					} else {
						double subTotal = 0.0;
						if(i.getAmount()>0)
						{
							i.setTitleForDisplay(item.getTitle()+" ("+itemOption.getTitle()+")");
							i.setUnitPrice(itemOption.getPrice());
							if(this.isBeforeDec17() && !isSpecial&&item.getTitle().equalsIgnoreCase("紐約芝士餅"))
							{
								subTotal = 148 + itemOption.getPrice() * (i.getAmount()-1)*discountRate;
							}
							else
							{
								subTotal = itemOption.getPrice() * i.getAmount();
								subTotal = subTotal * discountRate;
							}
							
							i.setTotalPrice(subTotal);
							totalPrice = totalPrice + subTotal;
						}
					}
				}
			}
			// go through all combo item in order
			if (order.getComboOrderList() != null) {
				for (ComboOrder j : order.getComboOrderList())

				{
					if (hasError) {
						break;
					}

					MenuCombo item = em.find(MenuCombo.class, j.getComboId());
					if (item == null) {
						response.setError("can not find combo id:"+j.getComboId());
						response.setOrderId(0);
						hasError = true;
						log.info("can not find combo:" + j.getComboId());
						break;
					} else {
						double subTotal = 0.0;
						if(j.getAmount()>0)
						{
							j.setTitleForDisplay(item.getTitle());
							if (item.isEnableSpecialPrice()) {
								j.setUnitPrice(item.getSpecialPrice());
								subTotal = item.getSpecialPrice() * j.getAmount();
								
							} else {
								j.setUnitPrice(item.getPrice());
								subTotal = item.getPrice() * j.getAmount();
								if (subTotal >= triggerAmount) {
									subTotal = subTotal * discountRate;
								}
							}
							j.setTotalPrice(subTotal);
							totalPrice = totalPrice + subTotal;
						}
					}
				}
			}
			order.setAmount(totalPrice+order.getMinuteMaidAmount()*10);

			// Add delivery fee
			if (order.isPickup()) {
				order.setDeliveryFee(0);
			} else {
				DeliveryArea item = em.find(DeliveryArea.class, order
						.getDeliveryInfo().getDeliveryAreaId());
				double deliveryFee = 0.0;
				if (item != null) {
					if (totalPrice > 1500)
					{
						if( item.isFreeArea()) {
						deliveryFee = 0.0;
						}
						else
						{
							deliveryFee = 300;
						}
					} else {
						deliveryFee = item.getDeliveryFee();
					}
				} else {
					deliveryFee = 500;
				}
				order.setDeliveryFee(deliveryFee);
			}

			// Free gift
			Set<CustomerOrderGift> gifts = new HashSet();
			// Rule 2,3,4,6
			if (this.isBeforeDec17()) {
				gifts.add(new CustomerOrderGift(LocalizationManager.GetGift1(), 2));

//				gifts.add(new CustomerOrderGift(
//						LocalizationManager.GetGift2(), 1));

				if (order.isPickup()&&order.getAmount()>=1500.0) {
					gifts.add(new CustomerOrderGift(
							LocalizationManager.GetGift3(), 1));
				}

			
			}

			int sum = (int) (totalPrice / 500)*2;
			if(sum>0)
			{
				if(order.getMinuteMaidAmount()>0)
				{
					gifts.add(new CustomerOrderGift(LocalizationManager.GetGift6(), order.getMinuteMaidAmount()));
				}
				if(sum-order.getMinuteMaidAmount()>0)
				{
					gifts.add(new CustomerOrderGift(LocalizationManager.GetGift5(), sum-order.getMinuteMaidAmount()));
				}
			}
			
			// Rule 5
			int t = (int) (totalPrice / 1500);
			if(t>0)
			{
				gifts.add(new CustomerOrderGift(LocalizationManager.GetGift4(), t));
			}

			order.setGiftList(gifts);

			if (!hasError) {
				order.setCreateDate(new Date());
				order.setUniqueURL(uniqueURL);
				order.setStatus(OrderStatus.Submit);
				log.info("update order:" + order.toString());
				// 3.save to database
				em.getTransaction().begin();
				em.persist(order);
				em.getTransaction().commit();

				// test send email

			}

		} finally {
			em.close();
		}
		if(hasError)
		{
			response.setOrderId(0);
		}
		else
		{
			response.setOrderId(order.getId());
		}
		response.setUrl(Prefix + "review/" + uniqueURL);
		return response;
	}

	@GET
	@Path("/testpayment")
	@Produces("text/plain")
	public String testPayment() {
		paymentManager.transaction("zy", "4444111122223333", "VISA", "07",
				"2019", 1.00, "PT90909010", "cn.yu.zhao@gmail.com");
		return "succeed";
	}

	@GET
	@Path("/review/{uniqueURL}")
	@Produces("text/html ;charset=utf-8")
	public String getOrderReview(@PathParam("uniqueURL") String unqiueURL) {
		log.info("getOrderReview:" + unqiueURL);
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
				String orderContent = "<h3>訂單內容:</h3>";
				orderContent += " <table BORDER=1 CELLPADDING=3 CELLSPACING=1 RULES=ROWS FRAME=HSIDES cellpadding=\"0\" cellspacing=\"0\" class=\"mobile_table\"><tbody><tr style=\"background:#9D1523;color:#fff\"><th style=\"text-align:left\">您所選擇的產品</th> <th width=\"40\" style=\"text-align:center\">   數量    </th> <th width=\"30\" style=\"text-align:right\">總額 </th>";
				CustomerOrder order = orders.get(0);
				double totalPrice = 0.0;
				//Menu item
				List<MenuOrder> itemList = new ArrayList(order.getMenuOrderList());
				Collections.sort(itemList, new Comparator<MenuOrder>(){

					@Override
					public int compare(MenuOrder o1, MenuOrder o2) {
						return o2.getId()-o1.getId();
					}
				});
				boolean hasSpecial = false;
				
				for(MenuOrder o:itemList)
				{
					if(o.getAmount()>0)
					{
						double itemTotalPrice =0.0;
						int amount = 0 ;
						if(this.isBeforeDec17()&&o.getTitleForDisplay().contains("紐約芝士餅"))
						{
							//list "cheese cake" stand alone
							hasSpecial = true;
							amount = o.getAmount()-1;
							itemTotalPrice = o.getUnitPrice()*amount;
							
						}
						else
						{
							amount = o.getAmount();
							itemTotalPrice= o.getAmount() * o.getUnitPrice();
						}
						totalPrice += itemTotalPrice;
						if(amount>0)
						{
							orderContent +="<tr><td>"+o.getTitleForDisplay()+"</td><td style=\"text-align:center\">"+amount+"</td><td style=\"text-align:center\">"+NumberFormat.getCurrencyInstance().format(itemTotalPrice)+"</td></tr>";
						}
					}
				}
				
				//Combo
				List<ComboOrder> comboList = new ArrayList(order.getComboOrderList());
				Collections.sort(comboList, new Comparator<ComboOrder>(){

					@Override
					public int compare(ComboOrder o1, ComboOrder o2) {
						return o2.getComboId()-o1.getComboId();
					}
				});
				for(ComboOrder o:comboList)
				{
					if(o.getAmount()>0)
					{
						double itemTotalPrice =0.0;
						itemTotalPrice = o.getAmount() * o.getUnitPrice();
						totalPrice += itemTotalPrice;
						orderContent +="<tr><td>"+o.getTitleForDisplay()+"</td><td style=\"text-align:center\">"+o.getAmount()+"</td><td style=\"text-align:center\">"+NumberFormat.getCurrencyInstance().format(itemTotalPrice)+"</td></tr>";
					}

				}
				double discount =1;
				switch(order.getCreditCard().getCreditCardBankId())
				{
				case 0:
					discount = 0.8;
					break;
				case 1:
				case 2:
					discount =0.68;
					break;
				default:
					discount = 1;
				}
				orderContent += "<tr><td></td><td style=\"text-align:center\";background-color:#9D1523;color:#fff;>原價</td><td><strong>"+NumberFormat.getCurrencyInstance().format(totalPrice)+"</strong></td></tr>";
				orderContent += "<tr><td></td><td style=\"text-align:center\"; background-color:red; color:#fff;><strong>信用卡折扣價</strong></td><td><strong>"+NumberFormat.getCurrencyInstance().format(totalPrice*discount)+"</strong></td></tr>";
				//orderContent += "<table border=\"1\">";
				if(hasSpecial)
				{
					orderContent += "<tr><td>特價產品</td></tr><tr><td>紐約芝士餅一客</td><td style=\"text-align:center\"><b>1</b></td><td>"+NumberFormat.getCurrencyInstance().format(148.0)+"</td></tr>";
				}
				
				//Gifts
				List<CustomerOrderGift> gifts = new ArrayList<CustomerOrderGift>(order.getGiftList());
				Collections.sort(gifts, new Comparator<CustomerOrderGift>(){

					@Override
					public int compare(CustomerOrderGift o1, CustomerOrderGift o2) {
						return o2.getId() - o1.getId();
					}
					
				});
				
				//orderContent += "<table border=\"1\">";
				if(gifts.size()>0)
				{
					orderContent += "<tr><td></td></tr>";
				}
				for(CustomerOrderGift gift: gifts)
				{
					if(gift.getTitle().equalsIgnoreCase(LocalizationManager.GetGift6()))
					{
						orderContent += "<tr><td>"+gift.getTitle()+"</td><td  style=\"text-align:center\">"+gift.getAmount()+"</td><td>"+NumberFormat.getCurrencyInstance().format(10*gift.getAmount())+"</td></tr>";
					}
					else
					{
						orderContent += "<tr><td>"+gift.getTitle()+"</td><td style=\"text-align:center\">"+gift.getAmount()+"</td><td></td></tr>";
					}
				}
				if(order.isPickup()||order.getAmount()<=0.0)
				{
					double amount = 0;
					if(order.getAmount()>=0)
					{
						amount = order.getAmount();
					}
					orderContent += "<tr></tr><tr><td></td><td style=\"text-align:center\"><b>總計</b></td><td>"+NumberFormat.getCurrencyInstance().format(amount)+"</td></tr>";
				}
				else
				{
					orderContent += "<tr><td></td><td style=\"text-align:center\">小計</td><td>"+NumberFormat.getCurrencyInstance().format(order.getAmount())+"</td></tr>";
					orderContent +="<tr><td>送貨費</td><td></td><td>"+NumberFormat.getCurrencyInstance().format(order.getDeliveryFee())+"</td></tr>";
					orderContent += "<tr><td></td><td style=\"text-align:center\"><b>總計</b></td><td>"+NumberFormat.getCurrencyInstance().format(order.getAmount()+order.getDeliveryFee())+"</td></tr>";
				}
				//orderContent += "</table>";
				//orderContent += "<br/>";
				
				
				orderContent += "</tbody></table>";
				
				orderContent +="<br/>";
				
				orderContent +="<h3 style=\"margin:0;background-color:#9D1523;color:#fff;width:200px\">顧客信息:</h3><table style='table-layout:fixed;' BORDER=1 CELLPADDING=3 CELLSPACING=1 RULES=ROWS FRAME=HSIDES cellpadding=\"0\" cellspacing=\"0\"><tbody>";
				//orderContent +="<th width=\"80\" style=\"text-align:center\">顧客信息:  </th><th></th>";
				orderContent +="<tbody><tr><td width=\"100\">顧客姓名：</td><td>"+order.getCustomerName()+"</td></tr>";
				orderContent +="<tr><td>顧客電話：</td><td>"+order.getCustomerPhoneNumber()+"</td></tr>";
				orderContent +="<tr><td>顧客電郵：</td><td>"+order.getCustomerEmail()+"</td></tr>";
				orderContent +="<tr><td>付款方式：</td><td>信用卡</td></tr>";
				orderContent +="<tr><td>類別：</td><td>"+order.getCreditCard().getCardTypeString()+"</td></tr>";
				orderContent +="<tr><td>銀行：</td><td>"+order.getCreditCard().getCreditCardBank()+"</td></tr>";
				orderContent +="<tr><td>號碼：</td><td>xxxx xxxx xxxx "+order.getCreditCard().getCardNumber()+"</td></tr>";
				orderContent +="<tr><td>取貨方式：</td>";
				if(order.isPickup())
				{
					orderContent+="<td>分店取貨</td></tr>";
					orderContent +="<tr><td>取貨日期：</td><td>"+dateFormat.format(order.getPickupInfo().getPickupDate())+"</td></tr>";
					orderContent +="<tr><td>取貨時段：</td><td>"+order.getPickupInfo().getPickupPeriod()+"</td></tr>";
					orderContent +="<tr><td>分店名稱：</td><td>"+order.getPickupInfo().getStoreTitle()+"</td></tr>";
					orderContent +="<tr><td>分店編號：</td><td>"+order.getPickupInfo().getStoreCode()+"</td></tr>";
					
					orderContent +="<tr><td>分店地址：</td><td>"+order.getPickupInfo().getStoreAddress()+"</td></tr>";
					orderContent +="<tr><td>分店電話：</td><td>"+order.getPickupInfo().getStorePhonenumber()+"</td></tr>";
					
				}
				else
				{
					orderContent+="<td>送貨服務</td></tr>";
					orderContent +="<tr><td>送貨日期：</td><td>"+dateFormat.format(order.getDeliveryInfo().getDate())+"</td></tr>";
					orderContent +="<tr><td>送貨時段：</td><td>"+order.getDeliveryInfo().getDeliveryPeriod()+"</td></tr>";
					orderContent +="<tr><td>送貨地址：</td><td>"+order.getDeliveryInfo().getDeliveryAddress()+"</td></tr>";
					orderContent +="<tr><td>收貨人：</td><td>"+order.getDeliveryInfo().getName()+"</td></tr>";
					orderContent +="<tr><td>收貨人電話：</td><td>"+order.getDeliveryInfo().getPhoneNumber()+"</td></tr>";
				}								
				orderContent += "</tbody></table>";
				
				orderContent +="<div style=\"width:200px\">";
				orderContent +="<br><strong>註明事項:</strong><br>";
				if(order.getRemark()!=null)
				{
					orderContent +=order.getRemark();
				}
				else
				{
					orderContent += "無";
				}
				orderContent+="</div>";
				
				response = "<html><head><meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\" /><style>table {table-layout:fixed}</style></header><body style=\"margin:0;padding:0;border:0;max-width:318px\">"
						+ orderContent+ "</body></html>";
			} else {
				response = "<html><head><meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\" /></header><body bgcolor=\"#EBEBEB\"><h1>Not Found</h1></body></html>";
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
								log.warn("header " + header + " end "
										+ order.getCreditCard().getCardNumber()
										+ " and number inconsist:"
										+ input.getCardNumber());
								result.setSucceed(false);
								result.setUrl(Prefix + "failed/"
										+ CreditCardError);
								break;
							}
						}
					}
					Calendar calendar = Calendar.getInstance();
					calendar.add(Calendar.MINUTE, VALID_PERIOD);
					if (order.getCreateDate().compareTo(calendar.getTime()) >= 0) {
						
						// 3. make payment
						
						PaymentResult paymentResult = paymentManager
								.transaction(order.getCreditCard().getHolder(),
										input.getCardNumber(), order.getCreditCard().getCardTypeString(), order
												.getCreditCard()
												.getValidMonth(),
										order.getCreditCard().getValidYear(),
										order.getAmount()+order.getDeliveryFee(),
										order.getOrderCode(), order
												.getCustomerEmail());
												
						
						if (paymentResult.isSucceed()) {
							// transaction success

							// 3.update DB
							em.getTransaction().begin();
							order.setStatus(OrderStatus.Confirm);
							order.setPaymentReference(paymentResult
									.getTransactionId());
							result.setSucceed(true);
							result.setUrl(Prefix + "succeed/"
									+ input.getOrderId());
							em.getTransaction().commit();

							// 4.send email
							notificationManager.notifySuccess(order, mailSession);

						} else {
							// transaction failed
							result.setSucceed(false);
							switch (paymentResult.getErrorCode()) {
							case PaymentResult.Error_CreditCard:
								result.setUrl(Prefix + "failed/"
										+ CreditCardError);
								break;
							case PaymentResult.Error_PaymentGateway:
								result.setUrl(Prefix + "failed/" + GeneralError);
								break;
							default:
								result.setUrl(Prefix + "failed/" + GeneralError);
							}

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
	@Path("/succeed/{orderId}")
	@Produces("text/html;charset=utf-8")
	public String getOrderResult(@PathParam("orderId") int orderId) {
		log.info("getOrderResult" + orderId);
		String response="";
		EntityManager em = EMF.get().createEntityManager();
		try {
			CustomerOrder order = em.find(CustomerOrder.class, orderId);
			if(order!=null)
			{
				if(order.getStatus()==OrderStatus.Confirm)
				{
					response += "<h3>您已訂購成功，參考編號:"+order.getOrderCode()+"</h3>";
					response += "您將在15分鐘內收到訂單郵件,如需修改訂單請撥打熱線2101-1293";
					response += "<br>謝謝惠顧！";
				}
				else
				{
					response += "<h3>未完成訂單</h3>";
				}
			}
			else
			{
				response += "<h3>未找到訂單</h3>";
			}
		} finally {
			em.close();
		}
		return "<html<head><meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\" /><body bgcolor=\"#EBEBEB\">"+response+"</body></html>";
	}

	@GET
	@Path("/failed/{errorCode}")
	@Produces("text/html;charset=utf-8")
	public String getOrderError(@PathParam("errorCode") int errorCode) {
		log.info("getOrderError" + errorCode);
		String ErrorString = "訂單錯誤";
		switch (errorCode) {
		case CreditCardError:
			ErrorString = "信用卡錯誤";
			break;
		case OutOfDate:
			ErrorString = "過期訂單";
			break;
		case NotExist:
			ErrorString = "訂單不存在";
			break;
		default:
			break;
		}
		return "<html><body bgcolor=\"#EBEBEB\"><h1>" + ErrorString + "</h1></body></html>";
	}
	
	public static boolean isBeforeDec17()
	{
		Date today = new Date();
		Date date = new Date();
		date.setYear(2012);
		date.setMonth(12);
		date.setDate(17);
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		return today.before(date);
	}
}
