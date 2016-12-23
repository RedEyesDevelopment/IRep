	jQuery(document).ready(function($) {
			event.preventDefault();
			getTagCloudViaAjax();
		});

	});

	function getTagCloudViaAjax() {
			var tagscloud = {}
    		tagscloud["per"] = 'givemetagcloud';

		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "/ajaxapi/tagscloud",
			data : JSON.stringify(tagscloud),
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS: ", data);
				display(data);
			},
			error : function(e) {
				console.log("ERROR: ", e);
				display(e);
			},
			done : function(e) {
				console.log("DONE");
			}
		});
	}

	function display(data) {
		var json = "<h4>Ajax Response</h4><pre>"
				+ JSON.stringify(data, null, 4) + "</pre>";
		$('#result').html(json);
	}