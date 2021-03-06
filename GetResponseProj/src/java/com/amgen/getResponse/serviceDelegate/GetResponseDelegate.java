package com.amgen.getResponse.serviceDelegate;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amgen.getResponse.entity.userProfileManagement.User;
import com.amgen.getResponse.service.BusinessService;
import com.amgen.getResponse.serviceLookup.ServiceLookup;
import com.amgen.getResponse.utility.UtilityService;

/**
 * Servlet implementation class GetResponseDelegate
 */
@WebServlet(name="GetResponseDelegate",urlPatterns ={"/GetResponseDelegate"})
public class GetResponseDelegate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BusinessService serviceType;


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetResponseDelegate() {
		super();
		// TODO Auto-generated constructor stub
	}


	public BusinessService getServiceType() {
		return serviceType;
	}


	public void setServiceType(BusinessService serviceType) {
		this.serviceType = serviceType;
	}
	public BusinessService serviceDelegate(String serviceType) /*throws ServiceException*/{
		ServiceLookup.getInstance().getService(serviceType).service();
		System.out.println("serviceType"+serviceType);

		return this.getServiceType();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		User u = new User();
		BusinessService bs = serviceDelegate(request.getParameter("serviceType"));
		u.setFirst_name(request.getParameter("First_Name"));
		u.setLast_name(request.getParameter("Last_Name"));
		u.setEmail(request.getParameter("EMAIL"));
		u.setUsername(request.getParameter("USERNAME"));
		u.setPassword(request.getParameter("PASSWORD"));
		u.setPhone(Integer.parseInt(request.getParameter("PHONE")));
		u.setAddress(request.getParameter("Address"));
		u.setFax(Integer.parseInt(request.getParameter("Fax")));
		String username;
		String password;
		username=request.getParameter("USERNAME");
		System.out.println(username);
		password=request.getParameter("PASSWORD");

		try {
			UtilityService.addUser(u);
			request.getRequestDispatcher("/success.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
