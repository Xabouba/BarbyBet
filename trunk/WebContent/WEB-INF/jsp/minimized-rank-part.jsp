<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:forEach items="${rank}" var="user" varStatus="i">
      	<c:if test="${user.value.hasBefore == 'false'}">
       	<li class="format-standard">
       		<p style="text-align: center">..........................................................</p>
       	</li>
      	</c:if>
      	<li class="format-standard">
       	<table style="width: 100%">
       		<tr>
       			<td class="td_rank_nb">${user.value.rank}</td>
       			<td class="td_rank_progress">
       				<c:choose>
       					<c:when test="${user.value.diff > 0}">
       						<span class="positif">+${user.value.diff}</span>
       					</c:when>
       					<c:when test="${user.value.diff < 0}">
       						<span class="negatif">${user.value.diff}</span>
       					</c:when>
       					<c:otherwise>=</c:otherwise>
       				</c:choose>
       			</td>
       			<td class="td_rank_name">
       				<a href="account?userId=${user.value.id}">
       				<c:choose>
       					<c:when test="${user.value.currentUser == 'true'}">
       						<span class="current_user">${user.key}</span>
       					</c:when>
       					<c:otherwise>
         						${user.key}
       					</c:otherwise>
       				</c:choose>
       				</a>
    			</td>
       			<td class="td_rank_credit">
       				<c:choose>
       					<c:when test="${user.value.point > 0}">
       						${user.value.point}
       					</c:when>
       					<c:otherwise>0</c:otherwise>
       				</c:choose>
       			</td>
       		</tr>
       	</table>
       </li>
</c:forEach>
<li class="link-rank">
	<c:choose>
		<c:when test="${not empty currentGroupId}">
			<a href="rank?group=${currentGroupId}">Classement Complet ...</a>
		</c:when>
		<c:otherwise>
			<a href="rank">Classement Complet ...</a>
		</c:otherwise>
	</c:choose>
</li>