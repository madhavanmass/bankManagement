package bank.hdfc.pack;


public class BankDefinition {
	
	public static String roleName(int roleint){
        switch(roleint){
            case 1:return "ACCOUNTANT";
            case 2:return "MANAGER";
            case 3:return "OWNER";
        }
        return null;
    }
    
    public static String accountName(int accounttype){
        switch(accounttype){
            case 1:return "SAVING ACCOUNT";
            case 2:return "CURRENT ACCOUNT";
            case 3:return "LOAN ACCOUNT";
        }
        return null;
    }
    
    public static float savingAccountInterest(int amount) {
    	if(amount<100000) {
    		return (float) 0.01;
    	}
    	else if(amount >100000 && amount<1000000) {
    		return (float)0.02;
    	}
    	else {
    		return (float)0.04;
    	}
    }
    
    public static String actionType(int action) {
    	switch(action) {
	    	case 1:return "CREDITED";
	    	case 2:return "DEBITED";
    	}
    	return null;
    }
    public static int getDue(int loanType) {
    	switch(loanType) {
	    	case 1:return 12;
	    	case 2:return 24;
	    	case 3:return 48;
	    	case 4:return 6;
	    	case 5:return 6;
    	}
		return 0;
    }
    
    public static float getLoanInterest(int loanInterest) {
    	switch(loanInterest) {
    	case 1:return (float) 0.1;
    	case 2:return (float) 0.2;
    	case 3:return (float) 0.05;
    	case 4:return (float) 0.15;
    	case 5:return (float) 0.12;
    	}
		return 0;
    }
    
    public static int getDepositTime(int depositPolicy) {
    	switch(depositPolicy) {
	    	case 1: return 1;
	    	case 2:return 2;
	    	case 3:return 3;
	    	case 4:return 6;
	    	case 5:return 12;
	    	case 6:return 60;
    	}
    	return 0;
    }
    
    public static String status(int status) {
    	switch (status) {
		case 1: return "ACTIVE";
		case 2: return "LOCKED";
		case 3: return "CLOSED";
		}
    	return null;
    }
    
    public static String accountMessage(int messageInt) {
    	switch (messageInt) {
		case 1: return "<h2 style=\"background-color: rgb(67 176 51 / 37%);color: green;\">DEBITED SUCCESSFULLY</h2>";
		case 2: return "<h2 style=\"background-color: rgb(176 170 51 / 37%);color: #ff9200;\">BENEFICIARY LIMIT REACHED</h2>";
		case 3: return "<h2 style=\"background-color: rgb(176 170 51 / 37%);color: #ff9200;\">THE PAYMENT WILL BE PROCESSED FOR YOUR CHOOSEN ACCOUNT</h2>";
		case 4: return "<h2 style=\"background-color: rgb(176 51 51 / 37%);color: red;\">INSUFFICIENT BALANCE IN YOUR ACCOUNT</h2>";
		case 5: return "<h2 style=\"background-color: rgb(176 170 51 / 37%);color: #ff9200;\">TRANSACTION LIMIT HAS BEEN REACHED</h2>";
		}
    	return null;
    }
    
    public static String employeeMessage(int messageInt) {
    	switch (messageInt) {
    	case 4: return "<h2 style=\"background-color: rgb(176 170 51 / 37%);color: #ff9200;\">CURRENT ACCOUNT ALREADY EXIST FOR THIS CUSTOMER</h2>";
		case 3: return "<h2 style=\"background-color: rgb(67 176 51 / 37%);color: green;\">ACTION DONE SUCESSFULLY</h2>";
		case 1: return "<h2 style=\"background-color: rgb(176 170 51 / 37%);color: #ff9200;\">CUSTOMER ID DOES'T EXIST THE BANK</h2>";
		case 2: return "<h2 style=\"background-color: rgb(176 170 51 / 37%);color: #ff9200;\">WRONG ACCOUNT NUMBER</h2>";
		case 0: return "<h2 style=\"background-color: rgb(176 170 51 / 37%);color: #ff9200;\">YOU ALREADY HAVE AN SAVING ACCOUNT IN THIS BRANCH</h2>";
		
		}
    	return null;
    }

	public static String getBankName(int bankName) {
		switch (bankName) {
		case 1: return "HDFC";
		case 2: return "SOUTH INDIAN BANK";
		case 3: return "STATE BANK OF INDIA";
		case 4: return "CANARA BANK";
		case 5: return "BANK OF BARODA";
		case 6: return "CENTRAL BANK OF INDIA";
		case 7:return "INDIAN BANK";
	    
	   
		}
		return null;
	}
	
	public static int getBankId(String bankName) {
		if(bankName.equals("HDFC")) {
			return 1;
		}
		else if(bankName.equals("SOUTH INDIAN BANK")) {
			return 2;
		}
		else if(bankName.equals("STATE BANK OF INDIA")) {
			return 3;
		}
		else if(bankName.equals("CANARA BANK")) {
			return 4;
		}
		else if(bankName.equals("BANK OF BARODA")) {
			return 5;
		}
		else if(bankName.equals("CENTRAL BANK OF INDIA")) {
			return 6;
		}
		else  {
			return 7;
		}
		
		
	}
	
    
}
