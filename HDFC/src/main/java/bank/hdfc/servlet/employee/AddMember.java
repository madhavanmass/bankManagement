package bank.hdfc.servlet.employee;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.hdfc.function.Admin;
import bank.hdfc.function.Employee;
import bank.hdfc.function.Manager;

public class AddMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String customerName=request.getParameter("name");
        String dateOfBirth=request.getParameter("dateOfBirth");
        String aadharNumber=request.getParameter("aadharNumber");
        String panNumber=request.getParameter("panNumber");
        String phoneNumber=request.getParameter("phoneNumber");
        String mailId=request.getParameter("mailId");
        String addressline1=request.getParameter("addressLine1");
        String addressline2=request.getParameter("addressLine2");
        String pincode=request.getParameter("pincode");
        String password=request.getParameter("password");
        String role=request.getParameter("role");
        String branchId=request.getParameter("branchId");
        Employee employee= (Employee)request.getSession().getAttribute("employee");
        Admin admin=(Admin)request.getSession().getAttribute("admin");
        if(employee!=null && employee.getRole()==2 && role!=null) {
        	Manager manager=(Manager) request.getSession().getAttribute("manager");
        	int roleInt=Integer.parseInt(role);
        	if(roleInt==1) {
        		int personId=manager.createPerson(customerName, dateOfBirth, aadharNumber, panNumber, phoneNumber, mailId, addressline1, addressline2, pincode, password);
        		manager.addEmployee(personId);
        	}
        	request.getSession().setAttribute("message", "");
        	request.getRequestDispatcher("addEmployee").forward(request, response);
        }
        else if(admin!=null && role!=null) {
        	int roleInt=Integer.parseInt(role);
			if(roleInt==1) {
        		int personId=admin.createPerson(customerName, dateOfBirth, aadharNumber, panNumber, phoneNumber, mailId, addressline1, addressline2, pincode, password);
        		admin.assignManager(Integer.parseInt(branchId), personId);
        		
        	}
			request.getSession().setAttribute("message", "");
        	request.getRequestDispatcher("assignManager").forward(request, response);
        }
        else if(employee!=null){
        	int personId=employee.createPerson(customerName, dateOfBirth, aadharNumber, panNumber, phoneNumber, mailId, addressline1, addressline2, pincode, password);
        	employee.addCustomer(personId);
        	request.getSession().setAttribute("message", "");
        	request.getRequestDispatcher("createAccount").forward(request, response);
        }
        
	}

}