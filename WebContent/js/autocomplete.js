// Add user to group
initializeAutocompleteAddUserToGroup = function() {
	$("#add-user-from-group-search").autocomplete({     
        source : function(request, response) {
            $.ajax({
                    url : "groupAutoComplete",
                    type : "GET",
                    data : {term : request.term, actionType:"add-user-to-group"},
                    dataType : "json",
                    success : function(data) {
                            response(data);
                    }
            });
        }
    });
}

// Delete user from group
initializeAutocompleteDeleteUserFromGroup = function(groupId) {
	$("#delete-user-from-group-search").autocomplete({     
        source : function(request, response) {
            $.ajax({
                    url : "groupAutoComplete",
                    type : "GET",
                    data : {term : request.term, groupId: groupId, actionType:"delete-user-from-group"},
                    dataType : "json",
                    success : function(data) {
                            response(data);
                    }
            });
        }
    });
}

//Look for groups
initializeAutocompleteLookForGroup = function() {
	$("#look-for-group").autocomplete({     
        source : function(request, response) {
            $.ajax({
                    url : "groupAutoComplete",
                    type : "GET",
                    data : {term : request.term, actionType:"look-for-group"},
                    dataType : "json",
                    success : function(data) {
                            response(data);
                    }
            });
        }
    });
}

// Look for users to see their bets
initializeAutocompleteLookForUser = function() {
	$("#look-for-user").autocomplete({     
        source : function(request, response) {
            $.ajax({
                    url : "groupAutoComplete",
                    type : "GET",
                    data : {term : request.term, actionType:"look-for-user"},
                    dataType : "json",
                    success : function(data) {
                            response(data);
                    }
            });
        }
    });
}