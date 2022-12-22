function validateForm(myStringArray) {
	var element = { 1: "accountNumber", 2: "beneficiaryId", 3: "connectedAccount", 4: "transactionLimit", 5: "oldMPin", 6: "newMPin", 7: "amount", 8: "description", 9: "otherAccount", 10: "branchName", 11: "addressLine1", 12: "addressLine2", 13: "pincode", 14: "branchId", 15: "name", 16: "dateOfBirth", 17: "aadharNumber", 18: "panNumber", 19: "phoneNumber", 20: "mailId", 21: "addressLine1", 22: "addressLine2", 23: "customerId", 24: "initialDeposit", 25: "mPin", 26: "userId", 27: "password", 28: "newPassword",29:"oldPassword",30:"accountHolderName",31:"ifsccode", 32:"phoneNumber"};
	var arrayLength = myStringArray.length;
	for (var i = 0; i < arrayLength; i++) {
		var x = document.forms["myForm"][element[myStringArray[i]]].value;
  		if (x=="" || x == null) {
    		alert(element[myStringArray[i]]+" must be filled out");
   			return false;
  		}  	
	}
 
}

