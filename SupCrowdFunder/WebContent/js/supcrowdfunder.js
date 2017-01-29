function validateForm(form) {
	// If mail empty or not valid, use the css error style from bootstrap
	if(form.mail.value == '' || ! /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i.test(form.mail.value))
	{
		document.getElementById("mail").className = "control-group error";
		var div = document.getElementById("mailControl");
		if(document.getElementById("mailError") == null) {
			var error = document.createElement('div');
			error.className = "help-inline";
			error.id = "mailError";
			error.innerHTML = "Please enter a correct mail";
			div.appendChild(error);
		}
		return false;
	}
	// Delete it
	else
	{
		document.getElementById("mail").className = "control-group";
		var div = document.getElementById("mailControl");
		if(document.getElementById("mailError") != null)
			div.removeChild(document.getElementById("mailError"));
	}

	// If last name empty or not valid
	if(form.lastName.value == '' || ! /^[A-Za-z0-9 ]{3,20}$/.test(form.lastName.value))
	{
		document.getElementById("lastName").className = "control-group error";
		var div = document.getElementById("lastNameControl");
		if(document.getElementById("lastNameError") == null) {
			var error = document.createElement('div');
			error.className = "help-inline";
			error.id = "lastNameError";
			error.innerHTML = "Please enter a correct last name";
			div.appendChild(error);
		}
		return false;
	}
	// Delete it
	else
	{
		document.getElementById("lastName").className = "control-group";
		var div = document.getElementById("lastNameControl");
		if(document.getElementById("lastNameError") != null)
			div.removeChild(document.getElementById("lastNameError"));
	}
	
	// If first name empty or not valid
	if(form.firstName.value == '' || ! /^[A-Za-z0-9 ]{3,20}$/.test(form.firstName.value))
	{
		document.getElementById("firstName").className = "control-group error";
		var div = document.getElementById("firstNameControl");
		if(document.getElementById("firstNameError") == null) {
			var error = document.createElement('div');
			error.className = "help-inline";
			error.id = "firstNameError";
			error.innerHTML = "Please enter a correct first name";
			div.appendChild(error);
		}		
		return false;
	}
	// Delete it
	else
	{
		document.getElementById("firstName").className = "control-group";
		var div = document.getElementById("firstNameControl");
		if(document.getElementById("firstNameError") != null)
			div.removeChild(document.getElementById("firstNameError"));
	}

	// If address name empty or not valid
	if(form.address.value == '' || ! /^[A-Za-z0-9 ]*$/.test(form.address.value))
	{
		document.getElementById("address").className = "control-group error";
		var div = document.getElementById("addressControl");
		if(document.getElementById("addressError") == null) {
			var error = document.createElement('div');
			error.className = "help-inline";
			error.id = "addressError";
			error.innerHTML = "Please enter a correct address";
			div.appendChild(error);
		}		
		return false;
	}
	// Delete it
	else
	{
		document.getElementById("address").className = "control-group";
		var div = document.getElementById("addressControl");
		if(document.getElementById("addressError") != null)
			div.removeChild(document.getElementById("addressError"));
	}
	
	// If password empty or not valid
	if(form.password.value == '' || ! /^[A-Za-z0-9!@#$%^&*()_]{6,20}$/.test(form.password.value))
	{
		document.getElementById("password").className = "control-group error";
		var div = document.getElementById("passwordControl");
		if(document.getElementById("passwordError") == null) {
			var error = document.createElement('div');
			error.className = "help-inline";
			error.id = "passwordError";
			error.innerHTML = "Please enter a correct password (6 min.)";
			div.appendChild(error);
		}
		return false;
	}
	// Delete it
	else
	{
		document.getElementById("password").className = "control-group";
		var div = document.getElementById("passwordControl");
		if(document.getElementById("passwordError") != null)
			div.removeChild(document.getElementById("passwordError"));
	}
	
	// If passwords match
	if(form.password.value != form.confirmPassword.value)
	{
		document.getElementById("confirmPassword").className = "control-group error";
		var div = document.getElementById("confirmPasswordControl");
		if(document.getElementById("confirmPasswordError") == null) {
			var error = document.createElement('div');
			error.className = "help-inline";
			error.id = "confirmPasswordError";
			error.innerHTML = "Please both passwords don't match";
			div.appendChild(error);
		}
		return false;
	}
	// Delete it
	else
	{
		document.getElementById("confirmPassword").className = "control-group";
		var div = document.getElementById("confirmPasswordControl");
		if(document.getElementById("confirmPasswordError") != null) {
			div.removeChild(document.getElementById("confirmPasswordError"));
		}
	}
	
	return true;
}

function validateUpdateProfilForm(form) {
	// If mail empty or not valid
	if(form.mail.value == '' || ! /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i.test(form.mail.value))
	{
		alertify.alert("Please enter a correct mail");
		return false;
	}

	// If last name empty or not valid
	if(form.lastName.value == '' || ! /^[A-Za-z0-9 ]{3,20}$/.test(form.lastName.value))
	{
		alertify.alert("Please enter a correct last name");
		return false;
	}

	// If first name empty or not valid
	if(form.firstName.value == '' || ! /^[A-Za-z0-9 ]{3,20}$/.test(form.firstName.value))
	{
		alertify.alert("Please enter a correct first name");
		return false;
	}

	// If address name empty or not valid
	if(form.address.value == '' || ! /^[A-Za-z0-9 ]*$/.test(form.address.value))
	{
		alertify.alert("Please enter a correct address");	
		return false;
	}
	
	// If password empty or not valid
	if(form.password.value == '' || ! /^[A-Za-z0-9!@#$%^&*()_]{6,20}$/.test(form.password.value))
	{
		alertify.alert("Please enter a correct password");
		return false;
	}
	
	// If passwords match
	if(form.password.value != form.confirmPassword.value)
	{
		alertify.alert("Please both passwords don't match");
		return false;
	}

	var div = document.getElementById("alert");
	div.className = "alert alert-success";
	div.innerHTML = "Your profil has been updated";
	return true;
}

function validateContributionForm(form) {
	//If amount empty or not valid
	if(form.amount.value == '' || ! /^[0-9]*$/.test(form.amount.value))
	{
		alertify.alert("Please enter a correct amount");
		return false;
	}
	
	//If button radio selected
	var radio = document.getElementsByName("rewards");
	var cpt = 0;

    for (var i in radio)
    {
    	if(radio[i].checked) {
    		cpt++;
    	}
    }
    
    if(cpt == 0) {
    	alertify.alert("Please choose to get a reward or not");
    	return false;
    }
	
	return true;
}

function allowRewards() {
	// Get the value of the amount
	var amount = document.getElementsByName("amount")[0].value;
	// Get all the radio button of the rewards
	var radio = document.getElementsByName("rewards");
	// Get all the minimum price of the rewards
	var price_min = document.getElementsByName("priceMin");
	
    for (var i in radio)
    {
    	// If the amount is < minimum price of the reward, disabled the radio button
    	if(parseInt(price_min[i].value) > parseInt(amount)) {
    		radio[i].disabled=true;
    		radio[i].checked=false;
    	}
    	else {
    		radio[i].disabled=false;
    	}
    }
}

function validateCreateProjectForm(form) {
	//If name empty or not valid
	if(form.name.value == '' || ! /^[A-Za-z0-9 ]{3,20}$/.test(form.name.value))
	{
		document.getElementById("name").className = "control-group error";
		var div = document.getElementById("nameControl");
		if(document.getElementById("nameError") == null) {
			var error = document.createElement('div');
			error.className = "help-inline";
			error.id = "nameError";
			error.innerHTML = "Please enter a correct name";
			div.appendChild(error);
		}
		
		return false;
	}
	else
	{
		document.getElementById("name").className = "control-group";
		var div = document.getElementById("nameControl");
		if(document.getElementById("nameError") != null) {
			div.removeChild(document.getElementById("nameError"));
		}
	}
	
	//If description empty or not valid
	if(form.description.value == '' || ! /^[A-Za-z0-9 ]{3,1000}$/.test(form.description.value))
	{
		document.getElementById("description").className = "control-group error";
		var div = document.getElementById("descriptionControl");
		if(document.getElementById("descriptionError") == null) {
			var error = document.createElement('div');
			error.className = "help-inline";
			error.id = "descriptionError";
			error.innerHTML = "Please enter a correct description";
			div.appendChild(error);
		}
		
		return false;
	}
	else
	{
		document.getElementById("description").className = "control-group";
		var div = document.getElementById("descriptionControl");
		if(document.getElementById("descriptionError") != null) {
			div.removeChild(document.getElementById("descriptionError"));
		}
	}
	
	//If start date empty or not valid
	if(form.start_date.value == '' || ! /^(0?[1-9]|[12][0-9]|3[01])[\/\-](0?[1-9]|1[012])[\/\-]\d{4}$/.test(form.start_date.value))
	{
		document.getElementById("start_date").className = "control-group error";
		var div = document.getElementById("start_dateControl");
		if(document.getElementById("start_dateError") == null) {
			var error = document.createElement('div');
			error.className = "help-inline";
			error.id = "start_dateError";
			error.innerHTML = "Please enter a correct date (dd/mm/yyyy)";
			div.appendChild(error);
		}
		
		return false;
	}
	else
	{
		document.getElementById("start_date").className = "control-group";
		var div = document.getElementById("start_dateControl");
		if(document.getElementById("start_dateError") != null) {
			div.removeChild(document.getElementById("start_dateError"));
		}
	}
	
	//If start date empty or not valid
	if(form.end_date.value == '' || ! /^(0?[1-9]|[12][0-9]|3[01])[\/\-](0?[1-9]|1[012])[\/\-]\d{4}$/.test(form.end_date.value))
	{
		document.getElementById("end_date").className = "control-group error";
		var div = document.getElementById("end_dateControl");
		if(document.getElementById("end_dateError") == null) {
			var error = document.createElement('div');
			error.className = "help-inline";
			error.id = "end_dateError";
			error.innerHTML = "Please enter a correct date (dd/mm/yyyy)";
			div.appendChild(error);
		}
		
		return false;
	}
	else
	{
		document.getElementById("end_date").className = "control-group";
		var div = document.getElementById("end_dateControl");
		if(document.getElementById("end_dateError") != null) {
			div.removeChild(document.getElementById("end_dateError"));
		}
	}
	
	//If amount empty or not valid
	if(form.goal.value == '' || ! /^[0-9]*$/.test(form.goal.value))
	{
		document.getElementById("goal").className = "control-group error";
		var div = document.getElementById("goalControl");
		if(document.getElementById("goalError") == null) {
			var error = document.createElement('div');
			error.className = "help-inline";
			error.id = "goalError";
			error.innerHTML = "Please enter a correct goal";
			div.appendChild(error);
		}
		
		return false;
	}
	else
	{
		document.getElementById("goal").className = "control-group";
		var div = document.getElementById("goalControl");
		if(document.getElementById("goalError") != null) {
			div.removeChild(document.getElementById("goalError"));
		}
	}
	
	//If rewards name empty or not valid
	if(form.rewardsName.value == '' || ! /^[A-Za-z0-9 ]{3,20}$/.test(form.rewardsName.value))
	{
		document.getElementById("rewards").className = "control-group error";
		var div = document.getElementById("rewardsControl");
		if(document.getElementById("rewardsError") == null) {
			var error = document.createElement('div');
			error.className = "help-inline";
			error.id = "rewardsError";
			error.innerHTML = "Please enter a correct name";
			div.appendChild(error);
		}
		
		return false;
	}
	else
	{
		document.getElementById("rewards").className = "control-group";
		var div = document.getElementById("rewardsControl");
		if(document.getElementById("rewardsError") != null) {
			div.removeChild(document.getElementById("rewardsError"));
		}
	}
	
	//If rewards description empty or not valid
	if(form.rewardsDescription.value == '' || ! /^[A-Za-z0-9 ]{3,1000}$/.test(form.rewardsDescription.value))
	{
		document.getElementById("rewards").className = "control-group error";
		var div = document.getElementById("rewardsControl");
		if(document.getElementById("rewardsError") == null) {
			var error = document.createElement('div');
			error.className = "help-inline";
			error.id = "rewardsError";
			error.innerHTML = "Please enter a correct description";
			div.appendChild(error);
		}
		
		return false;
	}
	else
	{
		document.getElementById("rewards").className = "control-group";
		var div = document.getElementById("rewardsControl");
		if(document.getElementById("rewardsError") != null) {
			div.removeChild(document.getElementById("rewardsError"));
		}
	}
	
	//If rewards price min or not valid
	if(form.rewardsPriceMin.value == '' || ! /^[0-9]*$/.test(form.rewardsPriceMin.value))
	{
		document.getElementById("rewards").className = "control-group error";
		var div = document.getElementById("rewardsControl");
		if(document.getElementById("rewardsError") == null) {
			var error = document.createElement('div');
			error.className = "help-inline";
			error.id = "rewardsError";
			error.innerHTML = "Please enter a correct price";
			div.appendChild(error);
		}
		
		return false;
	}
	else
	{
		document.getElementById("rewards").className = "control-group";
		var div = document.getElementById("rewardsControl");
		if(document.getElementById("rewardsError") != null) {
			div.removeChild(document.getElementById("rewardsError"));
		}
	}
	
	var div = document.getElementById("alert");
	div.className = "alert alert-success";
	div.innerHTML = "Your project has been created";
	return true;
}

function validateCategoryForm(form) {
	//If name empty or not valid
	if(form.name.value == '' || ! /^[A-Za-z0-9 ]{3,20}$/.test(form.name.value))
	{
		document.getElementById("name").className = "control-group error";
		var div = document.getElementById("nameControl");
		if(document.getElementById("nameError") == null) {
			var error = document.createElement('div');
			error.className = "help-inline";
			error.id = "nameError";
			error.innerHTML = "Please enter a correct name";
			div.appendChild(error);
		}
		
		return false;
	}
	else
	{
		document.getElementById("name").className = "control-group";
		var div = document.getElementById("nameControl");
		if(document.getElementById("nameError") != null) {
			div.removeChild(document.getElementById("nameError"));
		}
	}
	
	//If description empty or not valid
	if(form.description.value == '' || ! /^[A-Za-z0-9 ]{3,1000}$/.test(form.description.value))
	{
		document.getElementById("description").className = "control-group error";
		var div = document.getElementById("descriptionControl");
		if(document.getElementById("descriptionError") == null) {
			var error = document.createElement('div');
			error.className = "help-inline";
			error.id = "descriptionError";
			error.innerHTML = "Please enter a correct description";
			div.appendChild(error);
		}
		
		return false;
	}
	else
	{
		document.getElementById("description").className = "control-group";
		var div = document.getElementById("descriptionControl");
		if(document.getElementById("descriptionError") != null) {
			div.removeChild(document.getElementById("descriptionError"));
		}
	}
	
	var div = document.getElementById("alert");
	div.className = "alert alert-success";
	div.innerHTML = "Your category has been created";
	return true;

}

function validateCategoryAdminForm(form) {
	//If name empty or not valid
	if(form.name.value == '' || ! /^[A-Za-z0-9 ]{3,20}$/.test(form.name.value))
	{
		alertify.alert("Please enter a correct name");
		return false;
	}
	
	
	//If description empty or not valid
	if(form.description.value == '' || ! /^[A-Za-z0-9 ]{3,1000}$/.test(form.description.value))
	{
		alertify.alert("Please enter a correct description");
		return false;
	}

	var div = document.getElementById("alert");
	div.className = "alert alert-success";
	div.innerHTML = "Your category has been updated";
	return true;
}

function validateProjectAdminForm(form) {
	//If name empty or not valid
	if(form.name.value == '' || ! /^[A-Za-z0-9 ]{3,20}$/.test(form.name.value))
	{
		alertify.alert("Please enter a correct name");
		return false;
	}
	
	//If description empty or not valid
	if(form.description.value == '' || ! /^[A-Za-z0-9 ]{3,1000}$/.test(form.description.value))
	{
		alertify.alert("Please enter a correct description");
		return false;
	}
	
	//If amount empty or not valid
	if(form.goal.value == '' || ! /^[0-9]*$/.test(form.goal.value))
	{
		alertify.alert("Please enter a correct goal");
		return false;
	}
	
	//If current fund empty or not valid
	if(form.currentFund.value == '' || ! /^[0-9]*$/.test(form.currentFund.value))
	{
		alertify.alert("Please enter a correct current fund");
		return false;
	}
	
	//If nb contribution empty or not valid
	if(form.contribution.value == '' || ! /^[0-9]*$/.test(form.contribution.value))
	{
		alertify.alert("Please enter a correct nb contribution");
		return false;
	}
	
	//If start date empty or not valid
	if(form.start_date.value == '' || ! /^(0?[1-9]|[12][0-9]|3[01])[\/\-](0?[1-9]|1[012])[\/\-]\d{4}$/.test(form.start_date.value))
	{
		alertify.alert("Please enter a correct date (dd/mm/yyyy)");
		return false;
	}
	
	//If start date empty or not valid
	if(form.end_date.value == '' || ! /^(0?[1-9]|[12][0-9]|3[01])[\/\-](0?[1-9]|1[012])[\/\-]\d{4}$/.test(form.end_date.value))
	{
		alertify.alert("Please enter a correct date (dd/mm/yyyy)");
		return false;
	}
	
	//If id creator empty or not valid
	if(form.creator.value == '' || ! /^[0-9]*$/.test(form.creator.value))
	{
		alertify.alert("Please enter a correct the id of the creator only");
		return false;
	}

	var div = document.getElementById("alert");
	div.className = "alert alert-success";
	div.innerHTML = "Your project has been updated";
	return true;
}

function validateUserForm(form) {
	// If mail empty or not valid, use the css error style from bootstrap
	if(form.mail.value == '' || ! /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i.test(form.mail.value))
	{
		document.getElementById("mail").className = "control-group error";
		var div = document.getElementById("mailControl");
		if(document.getElementById("mailError") == null) {
			var error = document.createElement('div');
			error.className = "help-inline";
			error.id = "mailError";
			error.innerHTML = "Please enter a correct mail";
			div.appendChild(error);
		}
		return false;
	}
	// Delete it
	else
	{
		document.getElementById("mail").className = "control-group";
		var div = document.getElementById("mailControl");
		if(document.getElementById("mailError") != null)
			div.removeChild(document.getElementById("mailError"));
	}

	// If last name empty or not valid
	if(form.lastName.value == '' || ! /^[A-Za-z0-9 ]{3,20}$/.test(form.lastName.value))
	{
		document.getElementById("lastName").className = "control-group error";
		var div = document.getElementById("lastNameControl");
		if(document.getElementById("lastNameError") == null) {
			var error = document.createElement('div');
			error.className = "help-inline";
			error.id = "lastNameError";
			error.innerHTML = "Please enter a correct last name";
			div.appendChild(error);
		}
		return false;
	}
	// Delete it
	else
	{
		document.getElementById("lastName").className = "control-group";
		var div = document.getElementById("lastNameControl");
		if(document.getElementById("lastNameError") != null)
			div.removeChild(document.getElementById("lastNameError"));
	}
	
	// If first name empty or not valid
	if(form.firstName.value == '' || ! /^[A-Za-z0-9 ]{3,20}$/.test(form.firstName.value))
	{
		document.getElementById("firstName").className = "control-group error";
		var div = document.getElementById("firstNameControl");
		if(document.getElementById("firstNameError") == null) {
			var error = document.createElement('div');
			error.className = "help-inline";
			error.id = "firstNameError";
			error.innerHTML = "Please enter a correct first name";
			div.appendChild(error);
		}		
		return false;
	}
	// Delete it
	else
	{
		document.getElementById("firstName").className = "control-group";
		var div = document.getElementById("firstNameControl");
		if(document.getElementById("firstNameError") != null)
			div.removeChild(document.getElementById("firstNameError"));
	}

	// If address name empty or not valid
	if(form.address.value == '' || ! /^[A-Za-z0-9 ]*$/.test(form.address.value))
	{
		document.getElementById("address").className = "control-group error";
		var div = document.getElementById("addressControl");
		if(document.getElementById("addressError") == null) {
			var error = document.createElement('div');
			error.className = "help-inline";
			error.id = "addressError";
			error.innerHTML = "Please enter a correct address";
			div.appendChild(error);
		}		
		return false;
	}
	// Delete it
	else
	{
		document.getElementById("address").className = "control-group";
		var div = document.getElementById("addressControl");
		if(document.getElementById("addressError") != null)
			div.removeChild(document.getElementById("addressError"));
	}
	
	// If password empty or not valid
	if(form.password.value == '' || ! /^[A-Za-z0-9!@#$%^&*()_]{6,20}$/.test(form.password.value))
	{
		document.getElementById("password").className = "control-group error";
		var div = document.getElementById("passwordControl");
		if(document.getElementById("passwordError") == null) {
			var error = document.createElement('div');
			error.className = "help-inline";
			error.id = "passwordError";
			error.innerHTML = "Please enter a correct password (6 min.)";
			div.appendChild(error);
		}
		return false;
	}
	// Delete it
	else
	{
		document.getElementById("password").className = "control-group";
		var div = document.getElementById("passwordControl");
		if(document.getElementById("passwordError") != null)
			div.removeChild(document.getElementById("passwordError"));
	}
	
	return true;
}

function validateUserAdminForm(form) {
	// If mail empty or not valid
	if(form.mail.value == '' || ! /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i.test(form.mail.value))
	{
		alertify.alert("Please enter a correct mail");
		return false;
	}

	// If last name empty or not valid
	if(form.lastName.value == '' || ! /^[A-Za-z0-9 ]{3,20}$/.test(form.lastName.value))
	{
		alertify.alert("Please enter a correct last name");
		return false;
	}
	
	// If first name empty or not valid
	if(form.firstName.value == '' || ! /^[A-Za-z0-9 ]{3,20}$/.test(form.firstName.value))
	{
		alertify.alert("Please enter a correct first name");
		return false;
	}

	// If address name empty or not valid
	if(form.address.value == '' || ! /^[A-Za-z0-9 ]*$/.test(form.address.value))
	{
		alertify.alert("Please enter a correct address");
		return false;
	}
	
	// If password empty or not valid
	if(form.password.value == '' || ! /^[A-Za-z0-9!@#$%^&*()_]{6,20}$/.test(form.password.value))
	{
		alertify.alert("Please enter a correct password (6 min.)");
		return false;
	}

	return true;
}