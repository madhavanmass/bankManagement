function formatCheck(dict) {

	var element = { 1: "accountNumber", 2: "beneficiaryId", 3: "connectedAccount", 4: "transactionLimit", 5: "oldMPin", 6: "newMPin", 7: "amount", 8: "description", 9: "otherAccount", 10: "branchName", 11: "addressLine1", 12: "addressLine2", 13: "pincode", 14: "branchId", 15: "name", 16: "dateOfBirth", 17: "aadharNumber", 18: "panNumber", 19: "phoneNumber", 20: "mailId", 21: "addressLine1", 22: "addressLine2", 23: "customerId", 24: "initialDeposit", 25: "mPin", 26: "userId", 27: "password", 28: "newPassword",29:"oldPassword",30:"accountHolderName",31:"ifsccode", 32:"phoneNumber",33:"newMPin",34:"oldMPin"};


	for (let i in dict) {
		var x = document.forms["myForm"][element[i]].value;

		switch (dict[i]) {
			case 1:
				if (!x.match(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9])(?!.*\s).{8,15}$/)) {
					alert('PLEASE MATCH ALL THE ' + element[i] + ' SPECIFICATION');
					return false;
				}
				break;
			case 2:

				if (!x.match(/^\d{10}$/)) {
					alert('PLEASE MATCH ALL THE ' + element[i] + ' SPECIFICATION');
					return false;
				}
				break;
			case 3:

				if (!x.match(/^\d{6}$/)) {
					alert('PLEASE MATCH ALL THE ' + element[i] + ' SPECIFICATION');
					return false;
				}
				break;
			case 4:

				if (!x.match(/^\d{12}$/)) {
					alert('PLEASE MATCH ALL THE ' + element[i] + ' SPECIFICATION');
					return false;
				}
				break;

			case 5:

				if (!x.match(/^\d{4}}$/)) {
					alert('PLEASE MATCH ALL THE ' + element[i] + ' SPECIFICATION');
					return false;
				}
				break;

			case 6:

				if (!x.match(/^([a-zA-Z]){5}([0-9]){4}([a-zA-Z]){1}?$/)) {
					alert('PLEASE MATCH ALL THE ' + element[i] + ' SPECIFICATION');
					return false;
				}
				break;
			case 7:
				if (x == null || x== "") {
					alert('PLEASE FILL OUT ' + element[i]+ ' SECTION' );
					return false;
				}
				break;
				
		}

	}

}
