package bank.hdfc.servlet.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.hdfc.function.Admin;
import bank.hdfc.function.Branch;

public class AddBranch extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String branchName=request.getParameter("branchName");
		String addressLine1=request.getParameter("addressLine1");
		String addressLine2=request.getParameter("addressLine2");
		String pinCode=request.getParameter("pincode");
		Admin admin=(Admin)request.getSession().getAttribute("admin");
		admin.loadBranch();
		int flage=0;
		String area=addressLine1+addressLine2+pinCode;
		for(Branch branch: admin.getBranchDetails().values()) {
			if(area.equals(branch.getAddressLine1()+branch.getAddressLine2()+branch.getPinCode())){
				flage=1;
			}
		}
		String message=null;
		if(flage==0) {
			int messageInt=admin.createBranch(branchName, addressLine1, addressLine2, pinCode);
			message="<h2 style=\"background-color: rgb(67 176 51 / 37%);color: green;\" >!! A NEW BRANCH HAS BEEN ADDED SUCCESSFULLY !! <br> THE ID FOR NEW BRANCH IS : "+messageInt+" </h2>";
		}
		else {
			message="<h2 style=\"background-color: rgb(176 51 51 / 37%);color: red;\">THIS LOCATION ALREADY HAS A BRANCH</h2>";
		}
		request.getSession().setAttribute("message",message);
		request.getRequestDispatcher("addBranch").forward(request, response);
	}

}
