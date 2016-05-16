// Variables to tell the register button to be active or not
var isEmailValid = false;
var isPasswordValid = false;
var isUsernameLengthValid = true;

/**
 * Function that checks live if the email address is valid 
 */
$(document).ready(function() {
	$('#register_form_mail_input').blur(function() {
		var email = $('#register_form_mail_input').val();

		if(validateEmail(email)) {
			$('#register-email-error').hide();
			isEmailValid = true;
		} else if(email == "") {
			$('#register-email-error').hide();
			isEmailValid = false;
		} else {
			$('#register-email-error').show();
			isEmailValid = false;
		}
	}).keyup(function() {
		var email = $('#register_form_mail_input').val();

		if(validateEmail(email)) {
			$('#register-email-error').hide();
			isEmailValid = true;
		} else if(email == "") {
			$('#register-email-error').hide();
			isEmailValid = false;
		} else {
			$('#register-email-error').show();
			isEmailValid = false;
		}
	});
});

/**
 * Function that checks if both passwords are the same
 */
$(document).ready(function() {
	$('#register_form_repeat_password_input').blur(function() {
		var password = $('#register_form_password_input').val();
		var repeatPassword = $('#register_form_repeat_password_input').val();
		if(password == repeatPassword) {
			$('#register-repeat-password-error').hide();
			isPasswordValid = true;
		} else {
			$('#register-repeat-password-error').show();
			isPasswordValid = false;
		}
	}).keyup(function() {
		var password = $('#register_form_password_input').val();
		var repeatPassword = $('#register_form_repeat_password_input').val();
		if(password == repeatPassword) {
			$('#register-repeat-password-error').hide();
			isPasswordValid = true;
		} else {
			$('#register-repeat-password-error').show();
			isPasswordValid = false;
		}
	});
});

$(document).ready(function() {
	$('#register_form_mail_input').blur(function() {
		if($('#register_form_username_input').val() != "" && isPasswordValid && isEmailValid) {
			$('#register_form_submit').css("opacity", 1);
			$('#register_form_submit').prop("disabled", false);
		} else {
			$('#register_form_submit').css("opacity", 0.4);
			$('#register_form_submit').prop("disabled", true);
		}
	});
	
	$('#register_form_password_input').blur(function() {
		if($('#register_form_username_input').val() != "" && isPasswordValid && isEmailValid) {
			$('#register_form_submit').css("opacity", 1);
			$('#register_form_submit').prop("disabled", false);
		} else {
			$('#register_form_submit').css("opacity", 0.4);
			$('#register_form_submit').prop("disabled", true);
		}
	});
	
	$('#register_form_repeat_password_input').blur(function() {
		if($('#register_form_username_input').val() != "" && isPasswordValid && isEmailValid) {
			$('#register_form_submit').css("opacity", 1);
			$('#register_form_submit').prop("disabled", false);
		} else {
			$('#register_form_submit').css("opacity", 0.4);
			$('#register_form_submit').prop("disabled", true);
		}
	});
	
	$('#register_form_username_input').blur(function() {
		if($('#register_form_username_input').val() != "" && isUsernameLengthValid && isPasswordValid && isEmailValid) {
			$('#register_form_submit').css("opacity", 1);
			$('#register_form_submit').prop("disabled", false);
		} else {
			$('#register_form_submit').css("opacity", 0.4);
			$('#register_form_submit').prop("disabled", true);
		}
	}).keyup(function() {
		var usernameLength = $('#register_form_username_input').val().toString();
		if(usernameLength.length > 15) {
			isUsernameLengthValid = false;
			$('#register-username-length-error').show();
		} else {
			isUsernameLengthValid = true;
			$('#register-username-length-error').hide();
		}
	});
});

/**
 * Check the email format
 * @param email
 * @returns
 */
function validateEmail(email) {
    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}