package bank.hdfc.servlet.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.hdfc.function.Admin;

public class AddBranch extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String branchName=request.getParameter("branchName");
		String addressLine1=request.getParameter("addressLine1");
		String addressLine2=request.getParameter("addressLine2");
		String pinCode=request.getParameter("pinCode");
		Admin admin=(Admin)request.getSession().getAttribute("admin");
		int messageInt=admin.createBranch(branchName, addressLine1, addressLine2, pinCode);
		request.getSession().setAttribute("message", messageInt);
		request.getRequestDispatcher("addBranch").forward(request, response);
	}

}
