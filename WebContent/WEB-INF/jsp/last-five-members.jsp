<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="tab-pane active" id="tab_popular">
   <ul class="list-unstyled">
       <li>
       	<c:set var="membersCount" value="0" scope="page" />
       	
       	<c:forEach items="${lastFiveMembers}" var="member">
       		<c:set var="membersCount" value="${membersCount + 1}" scope="page"/>
       	
            <div class="kp-group clearfix">
                <span>${membersCount}</span>
                <a href="#">${member.username}</a>
                 <ul class="kp-metadata clearfix">
                     <li>10 view&nbsp&nbsp&nbsp-&nbsp&nbsp&nbsp</li>
                     <li>3 comment</li>
                 </ul>
             </div>
            </c:forEach>
        </li>
    </ul>
</div>