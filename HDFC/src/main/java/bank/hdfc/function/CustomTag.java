package bank.hdfc.function;


import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class CustomTag extends TagSupport{
	
	public int doStartTag() throws JspException {  
	    JspWriter out=pageContext.getOut();  
	    try {
			out.print("Custome tag");
		} catch (IOException e) {
			e.printStackTrace();
		}  
	    return SKIP_BODY;  
	}  

}
