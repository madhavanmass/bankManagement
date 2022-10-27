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

	
	
    
}
