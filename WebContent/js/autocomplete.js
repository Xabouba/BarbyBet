$(document).ready(function() {
        $(function() {
                $("#user-search").autocomplete({     
	                source : function(request, response) {
		                $.ajax({
		                        url : "userAutoComplete",
		                        type : "GET",
		                        data : {
		                                term : request.term
		                        },
		                        dataType : "json",
		                        success : function(data) {
		                                response(data);
		                        }
		                });
	                }
                });
        });
});