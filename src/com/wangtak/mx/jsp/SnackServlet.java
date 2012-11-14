/**
 * 
 */
package com.wangtak.mx.jsp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;

import com.wangtak.mx.entity.CustomerOrder;
import com.wangtak.mx.entity.MenuCategory;
import com.wangtak.mx.entity.MenuItem;

/**
 * @author piglet
 *
 */
@WebServlet("/snack.html")
public class SnackServlet extends HttpServlet {
	private static Logger log = Logger.getLogger(FindOrderServlet.class);

	@PersistenceContext(unitName = "Mysql")
	EntityManager em;
	
	
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("doGet");
		List<MenuItem> itemList = new ArrayList<MenuItem>();
		if(em==null)
		{
			response.getOutputStream().print("Server Error");
			return;
		}
		try {
			//em.setFlushMode(FlushModeType.COMMIT);
			//em.getTransaction().begin();
			MenuCategory category = em.find(MenuCategory.class, 1);
			itemList = new ArrayList(category.getMenuItemList());
			Collections.sort(itemList, new Comparator<MenuItem>(){

				@Override
				public int compare(MenuItem o1, MenuItem o2) {
					
					return o1.getSort_order()-o2.getSort_order();
				}
				
			});

		} finally {
			//em.close();
		}
		request.setAttribute("items", itemList);
		request.getRequestDispatcher("/WEB-INF/snack.jsp").forward(request, response);
	}
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //request.setAttribute("users", usersFacade.findAllStudents());
		
		String phone  = request.getParameter("phone");
		String email = request.getParameter("email");
		log.info("doPost phone:"+phone+" email:"+email);
		List<OrderInfo> results = new ArrayList<OrderInfo>(); 
		if(em==null)
		{
			response.getOutputStream().print("Server Error");
			return;
		}
		try {
			//em.setFlushMode(FlushModeType.COMMIT);
			//em.getTransaction().begin();
			Query query = em.createQuery("SELECT c FROM CustomerOrder c where  c.customerPhoneNumber =:phone and c.customerEmail =:email", CustomerOrder.class);
			query.setParameter("email", email);
			query.setParameter("phone", phone);
			List<CustomerOrder> orders = query.getResultList();
			for(CustomerOrder o:orders)
			{
				//check status on POS
				results.add(OrderInfo.createOrderInfo(o));
				
			}
			Collections.sort(results, new Comparator<OrderInfo>(){

				@Override
				public int compare(OrderInfo o1, OrderInfo o2) {
					
					return o1.getDate().compareTo(o2.getDate());
				}
				
			});

		} finally {
			//em.close();
		}
		request.setAttribute("orders", results);
        request.getRequestDispatcher("/WEB-INF/find_order.jsp").forward(request, response);
    }
}
