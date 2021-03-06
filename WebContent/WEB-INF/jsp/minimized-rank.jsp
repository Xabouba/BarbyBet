<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<ul class="list-unstyled classement">
	<li class="clearfix">
	    <div class="widget kp-review rank">
	        <h2 class="widget-title"><span>Classement</span></h2>
	        <div class="widget-content">
		        <select onchange="changeGroup(this.value)" style="width: 100%; margin-bottom: 2px;">
					<option value="general">General</option>
					<c:forEach items="${userGroups}" var="userGroup">
						<c:choose>
							<c:when test="${userGroup.key == group.id}">
								<option value="${userGroup.key}" selected>${userGroup.value.name}</option>
							</c:when>
							<c:otherwise>
								<option value="${userGroup.key}">${userGroup.value.name}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
				<ul id="minimized-rank" class="list-unstyled">
					<%@include file='minimized-rank-part.jsp'%>
				</ul>
	        </div>
	    </div>
    </li>
</ul>

<script type="text/javascript">
	changeGroup = function(value)
	{
		$("#minimized-rank").load("rankAction", {group: value}).fadeIn("slow");
	}
</script>