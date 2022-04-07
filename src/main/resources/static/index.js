let $userID = -1;

$(document).ready(function() {
	let $buttaddcat = $("#il8oq");
	let $buttlogin = $("#i47x88");

	let $subtitle = $("#ijo1ko");


	$.ajax({
		url: 'http://localhost:8080/api/user',
		type: 'GET',
		success: function(data){
			console.log(data);
			$userID = data['id'];
			$subtitle.text('Bienvenue '+data['login']+'.');

			$buttlogin.text('Déconnexion');	
			$buttlogin.click(function(){
				document.location.href = "http://localhost:8080/logout";
			});

			updatecateg();

		},
		error: function(data) {
			$buttlogin.click(function(){
				document.location.href = "http://localhost:8080/login";
			});
			console.log(data);
		}
	});

	$buttlogin.click(function(){
		document.location.href = "http://localhost:8080/login";
	});

	$buttaddcat.click(function(){
		let $title = $("#i6ntj");
		let $emoji = $("#i02at");

		$.ajax({
			url: 'http://localhost:8080/api/user/'+$userID+"/cat",
			type: 'POST',
    		contentType: "application/json; charset=utf-8",
			data: JSON.stringify({
				emoji: $emoji.val(),
				name: $title.val()
			}),
			success: function(data){
				console.log(data);
				$title.val('');
				$emoji.val('');
				updatecateg();
			},
			error: function(data) {
				console.log("error when inserting new category");
			}
		});
	});
	
	
});

function updatecateg() {
	let $categories = $("#list-categs");
	$categories.empty();
	$.ajax({
		url: 'http://localhost:8080/api/user/'+$userID+"/cat",
		type: 'GET',
		success: function(data){
			console.log(data);
			let categs = [];
			categs = data;
			categs.forEach(categ => {
				$categories.append('<div id="iq78r" class="row"><div id="itfo" class="row rowcustom"><div id="intp" class="cell cell-emoji"><div title="😃" id="idbl" class="categ-emoji"><span id="i8psl">'+categ['emoji']+'</span></div></div><div id="i4mo" class="cell cell-title"><div id="is2l" class="categ-title">'+categ['name']+'</div></div><div id="inkc" class="cell cell-bin"><div id="iancs" class="categ-bin"><h3 id="bin'+categ['id']+'" class="categ-bin">🗑️</h3></div></div></div></div>')
				
				let tmpname = "#bin"+categ['id'];
				$(tmpname).click(function(){
					console.log(categ['id']);
					$.ajax({
						url: 'http://localhost:8080/api/user/'+$userID+"/cat/"+categ['id'],
						type: 'DELETE',
						success: function(data){
							console.log(data);
							updatecateg();
						},
						error: function(data) {
							console.log(data);
						}
					});
				});
			
			});
		},
		error: function(data) {
			$buttlogin.click(function(){
				document.location.href = "http://localhost:8080/login";
			});
			console.log(data);
		}
	});
}