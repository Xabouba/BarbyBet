<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
    <head>
    	<title>Groupes</title>
        <%@include file="import.jsp" %>
		<link rel="stylesheet" type="text/css" href="css/match.css">
    </head>
    <body onLoad="goforit()">
    <div id="fb-root"></div>
    	<div class="wraper">
        	<%@include file="header.jsp" %>
    		
    		<!-- page-header -->
            <div id="content" class="group-content">
                <div class="top-effect clearfix">
                    <span class="pull-left"><img src="images/top-left-effect.png" class="img-responsive" alt=""></span>
                    <span class="pull-right"><img src="images/top-right-effect.png" class="img-responsive" alt=""></span>
                </div>
                <!-- top-effect -->
                <div id="main-content" class="pull-left group-content">
                    <div id="sidebar-main-content" class="pull-left group-rank">
                       <ul class="list-unstyled classement">
							<li class="clearfix">
							    <div class="widget kp-review rank">
							        <h2 class="widget-title"><span>Classement</span></h2>
							        <div class="widget-content">
								        <ul id="minimized-rank" class="list-unstyled">
											<%@include file='minimized-rank-part.jsp'%>
										</ul>
							        </div>
							    </div>
						    </li>
						</ul>
                    </div>
                    <!-- sidebar-main-content -->
                    <div id="main-col" class="pull-left group-info">
                            <div class="widget kp-last-news">
                                <h2 class="widget-title"><span>${group.name}</span></h2>
                                <div class="widget-content">
                                    <ul class="list-unstyled">
                                    	<li class="format-standard">
	                                    	<div class="item">
		                                        <figure class="zoom-image">
		                                        	<c:choose>
													    <c:when test="${empty groupImagePath}">
													    	<!-- <img src="images/default-group-pic.jpg" class="img-responsive" alt=""> <span class="icon-image post-format"></span> -->
													    	<img src="images/groups-default-img.jpg" class="img-responsive" alt=""> <!-- <span class="icon-image post-format"></span> -->
													    </c:when>
													    <c:otherwise>
													    	<img src="${groupImagePath}" class="img-responsive" alt="">  <!-- <span class="icon-image post-format"></span> -->
													    </c:otherwise>
													</c:choose>
		                                        </figure>
		                                        <ul class="kp-metadata clearfix">
		                                            <li>${group.numberOfMembersFullStr}&nbsp&nbsp&nbsp</li>
		                                            <li></li>
		                                            <li class="kp-time">Groupe ${group.statusStr}</li>
		                                        </ul>
		                                        <!-- kp-metadata -->
		                                        <br />
		                                        <p style="font-weight:bold">${group.description}</p>
		                                        <br />
		                                        <footer class="clearfix">
		                                            <ul class="meta-post pull-left">
		                                                <li><span>Groupe crée par : </span><a href="account?userId=${group.groupCreatorId}">${group.groupCreator}</a></li>
		                                                <li><span>Groupe crée le  : </span>${group.creationDate}</li>
		                                            </ul>
		                                        </footer>
		                                    </div> 
                                  		</li>
                                  	</ul>
                                </div>
                                <br /> 
                                <br /> 
                                <ul class="list-unstyled">
		                            <li class="clearfix">
		                                <div class="widget kp-review">
		                                    <h2 class="widget-title"><span>Chat</span></h2>
		                                    <div id="chat" class="widget-content">
		                                   		<%@include file='chat.jsp'%>
		                                    </div>
		                                    <br />
										  	<label for="comments">Message: </label>
										 	<textarea style="width: 100%;" rows="5" name="comment" id="comments" maxlength="450" ></textarea>
											<br />
											<br />
											<div style="width:100%; text-align: center">
												<input class="btn btn-primary" type="submit" value="Envoyer" onclick="post()"/>
		                                    </div>
		                                    <!-- widget-content -->
		                                </div>
		                                <!-- kp-review -->
		                            </li>
		                        </ul>
                                <!-- widget-content -->
                            </div>
                    </div>
                    <!-- main-col -->
                    <div class="clearfix"></div>
                </div>
                <!-- main-content -->
            	<div id="sidebar" class="pull-right">
            		<ul class="clearfix list-unstyled">
            			<c:if test="${not empty userGroupList}">
            				<li class="clearfix">
                                <div class="widget kp-review">
                                    <h2 class="widget-title"><span>Mes Groupes</span></h2>
	                                <div class="widget-content groups">
	                                    <ul class="list-unstyled">
	                                    	<li class="format-standard">
			                                    <div id="match">
			                                    	<form name="userGroupsForm" action="group" method="POST">
				                                    	<select id="user-groups-list" name="groupId" onchange="javascript:document.userGroupsForm.submit();" style="width: 100%; margin-bottom: 2px;">
                                                   			<option value="" disabled selected style="display:none;"></option>
                                                   			<c:forEach items="${userGroupList}" var="userGroup">
	                                                   			<c:choose>
	                                                   				<c:when test="${userGroup.id == group.id}">
															  			<option value="${userGroup.id}" selected>${userGroup.name}</option>
																	</c:when>
																	<c:otherwise>
													  					<option value="${userGroup.id}">${userGroup.name}</option>
																	</c:otherwise>
																</c:choose>
	                                                       	</c:forEach>
														</select>
			                                    	</form>
			                                    </div>
	                                    	</li>
	                                   	</ul>
	                                </div>
                                </div>
                            </li>
                        </c:if>
            			<li class="clearfix">
                        	<div class="widget kp-review">
	                            <h2 class="widget-title"><span>Chercher un groupe</span></h2>
	                            <div class="widget-content groups">
	                            	<ul class="list-unstyled">
                                    	<li class="format-standard">
				                            <p>Ici vous pouvez chercher un groupe et le rejoindre (seuls les groups publics sont accessibles)</p>
				
								            <div class="form-group" style="text-align:center">
								            	<form action="group" method="post">
									            	<input type="text" placeholder="Nom du groupe" class="form-control" id="look-for-group" name="groupName">
									            	<br />
									            	
									            	<c:if test="${not empty lookForGroupMsg}">
										           	 	<div id="look-for-group-msg" style="font-weight:bold">
					                                		${lookForGroupMsg}
					                                	</div>
														<br />
				                                	</c:if>
			                                		<input type="hidden" name="actionType" value="look-for-group" />
			                                		<input type="hidden" name="groupId" value="${group.id}" />
			                                		<input type="submit" class="btn btn-primary" value="Valider" id="s">
				                            	</form>
				                            </div>
		                                </li>
                                	</ul>
                                </div>
                        	</div>
                        </li>
                        
            		    <!-- If the connected user is the group admin, they can add a user to this group, delete a user from this group & delete this group -->
            			<c:choose>
	                    	<c:when test="${cookie.currentUserName.value == group.groupCreator}">
		                    	<li class="clearfix">
		                        	<div class="widget kp-review">
			                            <h2 class="widget-title"><span>Ajouter un utilisateur</span></h2>
			                            <div class="widget-content groups">
			                            	<ul class="list-unstyled">
		                                    	<li class="format-standard">
						                            <p>Entrez ici le nom d'utilisateur de la personne que vous souhaitez ajouter à ce groupe</p>
						
					                                <div class="form-group" style="text-align:center">
					                                    <input type="text" placeholder="Nom d'utilisateur" class="form-control" id="add-user-from-group-search" name="add-user-from-group-search">
					                                	<br />
					                                	<div id="add-user-to-group-msg" style="display:none; font-weight:bold">
					                                		Test div
					                                	</div>
					                                	<div id="add-user-to-group-msg-line-break" style="display:none">
					                                		<br />
					                                	</div>
					                                	<input type="submit" class="btn btn-primary" value="Valider" id="s" onclick="addUserToGroup()">
					                                </div>
				                                </li>
		                                	</ul>
		                                </div>
		                        	</div>
		                        </li>
		                        <li class="clearfix">
		                        	<div class="widget kp-review">
			                            <h2 class="widget-title"><span>Supprimer un utilisateur</span></h2>
			                            <div class="widget-content groups">
			                            	<ul class="list-unstyled">
		                                    	<li class="format-standard">
						                            <p>Entrez ici le nom d'utilisateur de la personne que vous souhaitez supprimer de ce groupe</p>
													<div class="form-group" style="text-align:center">
					                                    <input type=text placeholder="Nom d'utilisateur" class="form-control" id="delete-user-from-group-search" name="delete-user-from-group-search">
						                               	<br />
					                                	<div id="delete-user-from-group-msg" style="display:none; font-weight:bold">
					                                		Test div
					                                	</div>
					                                	<div id="delete-user-from-group-msg-line-break" style="display:none">
					                                		<br />
					                                	</div>
						                                <input type="submit" value="Valider" class="btn btn-primary" id="s" onclick="deleteUserFromGroup()">
					                                </div>
				                                </li>
			                                </ul>
	                                	</div>
		                        	</div>
		                        </li>
		                        <li class="clearfix">
		                        	<div class="widget kp-review">
			                            <h2 class="widget-title"><span>Supprimer ce groupe</span></h2>
			                             <div class="widget-content groups">
			                            	<ul class="list-unstyled">
		                                    	<li class="format-standard">
						                            <p>Cliquez sur ce bouton si vous souhaitez supprimer ce groupe. Attention lors de la suppression d'un groupe, plus aucun utilisateur n'aura accès à ce groupe.</p>
						
													<br />
										            <div class="form-group" style="text-align:center">
										            	<c:if test="${not empty deleteGroupMsg}">
											           	 	<div id="delete-group-msg" style="display:none; font-weight:bold; color:red;">
						                                		${deleteGroupMsg}
						                                	</div>
						                                	<div id="delete-group-msg-line-break" style="display:none">
						                                	</div>
					                                	</c:if>
					                                	<form action="group" method="post">
					                                		<input type="hidden" name="groupIdLeaveDeleteGroup" value="${group.id}" />
					                                		<input type="hidden" name="actionType" value="delete-group" />
						                                	<input type="submit" value="Supprimer groupe" class="btn btn-primary" id="s">
						                            	</form>
						                            </div>
		                            			</li>
	                            			</ul>
		                            	</div>
		                        	</div>
		                        </li>
	                        </c:when>
	                        <c:otherwise>
								<!-- In this case the connected user is not the group administrator & we need to check wether the group is public or not to give the appropriate options -->
	                        	<!-- Group status = 0 : group is public -->
	                        	<c:choose>
	                        		<c:when test="${group.status == '0' }">
	                        			<!-- We check if the user is a member of the group -->
	                        			<c:choose>
		                        			<c:when test="${isUserInGroup == true}">
	                       						<!-- If the user is a member, they can add a user to the group and leave the group -->
	                        					<li class="clearfix">
						                        	<div class="widget kp-review">
							                            <h2 class="widget-title"><span>Ajouter un utilisateur</span></h2>
							                            <div class="widget-content groups">
							                            	<ul class="list-unstyled">
						                                    	<li class="format-standard">
										                            <p>Entrez ici le nom d'utilisateur de la personne que vous souhaitez ajouter à ce groupe</p>
										
									                                <div class="form-group" style="text-align:center">
									                                    <input type="text" placeholder="Nom d'utilisateur" class="form-control" id="add-user-from-group-search" name="add-user-from-group-search">
									                                	<br />
									                                	<div id="add-user-to-group-msg" style="display:none; font-weight:bold">
									                                		Test div
									                                	</div>
									                                	<div id="add-user-to-group-msg-line-break" style="display:none">
									                                		<br />
									                                	</div>
									                                	<input type="submit" value="Valider" id="s" class="btn btn-primary" onclick="addUserToGroup()" />
									                                </div>
								                                </li>
							                                </ul>
						                                </div>
						                        	</div>
						                        </li>
						                        <!-- Anybody can leave a group if they are not the group admin (otherwise they need to delete the group) -->
					                        	<li class="clearfix">
						                        	<div class="widget kp-review">
						                        		<div class="widget-content groups">
							                            	<ul class="list-unstyled">
						                                    	<li class="format-standard">
										                            <h2 class="widget-title"><span>Quitter ce groupe</span></h2>
										                            <p>Cliquez sur ce bouton si vous souhaitez quitter ce groupe.</p>
										
										                             <div class="form-group" style="text-align:center">
										                             	<c:if test="${not empty leaveGroupMsg}">
											                                <div id="leave-group-msg" style="display:none; font-weight:bold; color:red !important">
										                                		${leaveGroupMsg}
										                                	</div>
										                                	<br />
									                                	</c:if>
									                                	<form action="group" method="post">
											                                <input type="hidden" name="actionType" value="leave-group" />
											                                <input type="hidden" name="groupIdLeaveDeleteGroup" value="${group.id}" />
											                                <input type="hidden" name="username" value="${cookie.currentUserName.value}" />
											                                <input type="submit" value="Quitter groupe" class="btn btn-primary" id="s" />
																		</form>
										                            </div>
									                            </li>
								                            </ul>
							                            </div>
						                        	</div>
						                        </li>
					                        </c:when>
	                        				<c:otherwise>
	                        					<!-- Otherwise you can only join this group -->
	                        					<li class="clearfix">
						                        	<div class="widget kp-review">
							                            <h2 class="widget-title"><span>Rejoindre ce groupe</span></h2>
							                            <div class="widget-content groups">
							                            	<ul class="list-unstyled">
						                                    	<li class="format-standard">
										                            <p>Cliquez sur ce bouton si vous souhaitez rejoindre ce groupe.</p>
										
										                             <div class="form-group" style="text-align:center">
										                             	<c:if test="${not empty joinGroupMsg}">
											                                <div id="join-group-msg" style="font-weight:bold; color:red !important">
										                                		${joinGroupMsg}
										                                	</div>
										                                	<br />
									                                	</c:if>
									                                	<form action="group" method="post">
											                                <input type="hidden" name="actionType" value="join-group" />
											                                <input type="hidden" name="groupIdJoinGroup" value="${group.id}" />
										                                	<input type="submit" value="Rejoindre groupe" class="btn btn-primary" id="s" />
																		</form>
										                            </div>
									                            </li>
								                            </ul>
							                            </div>
						                        	</div>
						                        </li>
	                        				</c:otherwise>
                        				</c:choose>
                       				</c:when>
	                        	</c:choose>
	                        </c:otherwise>
                        </c:choose>
                        <li>
                            <div class="widget widget-kp-tab">
                                <h2 class="widget-title"><span>Les 5 derniers membres</span></h2>
                                <div class="widget-content">
                                    <div class="tab-content" id="last-five-members">
                                        <%@include file="last-five-members.jsp" %>
                                    </div>
                                </div>
                                <!-- widget-content -->
                        	</div>
                        </li>
                    </ul>
                </div>
                <!-- sidebar -->
                <div class="clearfix"></div>
            </div>
    	</div>
    	<%@include file="footer.jsp" %>
    	
    	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
        <script src="js/jqueryUi.js"></script>
        <script src="js/bootstrap.js"></script>
        <script src="js/superfish.js"></script>
        <script src="js/jquery.validate.min.js"></script>
        <script src="js/jquery.carouFredSel-6.2.1-packed.js"></script>
        <script src="js/jflickrfeed.min.js"></script>
        <script src="js/tweetable.jquery.js"></script>
        <script src="js/jquery.timeago.js"></script>
        <script src="js/jquery.prettyPhoto.js"></script>
        <script src="js/modernizr.js"></script>
        <script src="js/grid.js"></script>
        <script src="js/masonry.pkgd.min.js"></script>
        <script src="js/chart/jquery.canvasjs.js"></script>
        <script src="js/chart/canvasjs.js"></script>
        <script src="js/chart/excanvas.js"></script>
        <script type="text/javascript" src="js/custom.js"></script>
        <script src="js/autocomplete.js"></script>
        
        <script type="text/javascript">
        	initializeAutocompleteDeleteUserFromGroup(${group.id});
        	initializeAutocompleteLookForUser();
        	initializeAutocompleteLookForGroup();
        	initializeAutocompleteAddUserToGroup();
        	
        	// Add a user to the group
	        addUserToGroup = function () {
	        	var username = $("#add-user-from-group-search").val();
	            var groupId = ${group.id};
	            	            
	            $.ajax({
	                method: "POST",
	           		url: "group",
	           		data: {username: username, groupId: groupId, actionType: "add-user-to-group"}
	            }).done(function(msg) {
	            	$("#add-user-from-group-search").val('');
	            	$("#add-user-to-group-msg").html(msg);
	            	$("#add-user-to-group-msg").show();
	            	$("#add-user-to-group-msg-line-break").show();
	            	
	            	// Update the last five added members
	            	$("#last-five-members").load("lastFiveMembersGenerator", {groupId: groupId}).fadeIn("slow");
	            	// Update ranking
	            	$("#minimized-rank").load("rankAction", {group: groupId}).fadeIn("slow");
	            });
	        }
        	
	        // Delete a user from the group
	        deleteUserFromGroup = function () {
	        	var username = $("#delete-user-from-group-search").val();
	            var groupId = ${group.id};
	            	            
	            $.ajax({
	                method: "POST",
	           		url: "group",
	           		data: {username: username, groupId: groupId, actionType: "delete-user-from-group"}
	            }).done(function(msg) {
	            	$("#delete-user-from-group-search").val('');
	            	$("#delete-user-from-group-msg").html(msg);
					$("#delete-user-from-group-msg").show();
					$("#delete-user-from-group-msg-line-break").show();
					
					// Update the last five added members
	            	$("#last-five-members").load("lastFiveMembersGenerator", {groupId: groupId}).fadeIn("slow");
					// Update ranking
	            	$("#minimized-rank").load("rankAction", {group: groupId}).fadeIn("slow");
	            });
	        }
	        
	        post = function()
			  {
	        	var groupId = ${group.id};
				  var comment = $("#comments").val();
				  
				  $.ajax(
				  {
			      	method: "POST",
					url: "commentAction",
					data: {groupId: groupId, comment: comment, comingFrom: "group"}
				  }).done(function( msg ) {
						$("#comments").val("");
						$("#chat").load("commentAction", {groupId: groupId, refresh: "true", comingFrom: "group"}).fadeIn("slow");
				  });
			  }
	        
			  setInterval(function(){
				  var groupId = ${group.id};
				  $("#chat").load("commentAction", {groupId: groupId, refresh: "true", comingFrom: "group"}).fadeIn("slow");
				}, 30000);
    	</script>
	</body>
</html>    