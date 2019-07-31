
function formValidation() {
	var firstname = document.forms["signup"]["firstname"].value;
	var lastname = document.forms["signup"]["lastname"].value;
	var username = document.forms["signup"]["username"].value;
	var password = document.forms["signup"]["password"].value;

    if (firstname==null || firstname=="") {
        alert("First name must be filled out");
        return false;
    }
    if (lastname==null || lastname=="") {
        alert("Last name must be filled out");
        return false;
    }
    if (username==null || username=="") {
        alert("User name must be filled out");
        return false;
    }
    
    if(!validatePassword(password)){
    	alert("Password: \n At least six characters \n At least one number, \n one lowercase, and \n one uppercase letter");
    	return false;
    }

   
}

function validatePassword(password){
	var re = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}/;
	return re.test(password);
}

