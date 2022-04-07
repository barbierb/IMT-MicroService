$(document).ready(function() {
	let $categories = $("#imyat");
	let $buttaddcat = $("#il8oq");
	let $buttlogin = $("#i47x88");

	$buttlogin.click(function(){
		document.location.href = "http://localhost:8080/login";
	});

	$.ajax({
		url: 'http://localhost:8080/api/user',
		type: 'GET',
		success: function(data){ 
			console.log(data);
		},
		error: function(data) {
			console.log(data);
		}
	});
	
	
	$categories.append("<button>butt</button>");
});