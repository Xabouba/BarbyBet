<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"  %>
<div class="page-single">
     <ul class="list-unstyled">
         <li class="clearfix">
             <div class="widget kp-review rank">
              <h2 class="widget-title"><span>Classement</span></h2>
              <div class="widget-content">
				<ul class="list-unstyled">
					<li class="format-standard rank-title">
						<span>${groupName}</span>
					</li>
					<c:forEach items="${rank}" var="user" varStatus="i">
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
					<li class="format-standard rank-pagination">
						<table class="pagination">
							<tr>
								<td class="previous">
									<c:choose>
										<c:when test="${page == 1}">
											<input disabled id="previous-button" class="btn btn-primary" type="button" onclick="previous()" value="<<"/>
										</c:when>
										<c:otherwise>
											<input id="previous-button" class="btn btn-primary" type="button" onclick="previous()" value="<<"/>
										</c:otherwise>
									</c:choose>
								</td>
									<c:if test="${fn:length(pagination) gt 1}">
										<c:forEach items="${pagination}" var="page">
											<c:choose>
												<c:when test="${page.value.hasNext == 'false' and not(page.value.end == 'true')}">
													<td class="page">	
														<a href="javascript:changePage(${page.key})">${page.key}</a>
													</td>
													<td class="next-page">...</td>
												</c:when>
												<c:otherwise>
													<td class="page">
														<c:choose>
															<c:when test="${page.value.current == 'true'}">
																<span class="current">${page.key}</span>
															</c:when>
															<c:otherwise>
																<a href="javascript:changePage(${page.key})">${page.key}</a>
															</c:otherwise>
														</c:choose>
													</td>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</c:if>
								<td class="next">
									<c:choose>
										<c:when test="${page == totalPage}">
											<input disabled id="next-button" class="btn btn-primary" type="button" value=">>" onclick="next()"/>
										</c:when>
										<c:otherwise>
											<input id="next-button" class="btn btn-primary" type="button" value=">>" onclick="next()"/>
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</table>
					</li>
              </ul>
             </div>
           </div>
         </li>
     </ul>
</div>
<div class="clearfix"></div>
<!-- main-col -->

<script type="text/javascript">
  changePage = function(page)
  {
	$("#main-content").load("rank", {page: page}).fadeIn("slow");
  }

  next = function()
  {
	var page = ${page};
	$("#main-content").load("rank", {page: page + 1}).fadeIn("slow");
  };

  previous = function()
  {
	var page = ${page};
	if (page != 1)
	{
		page--;
	}	
	$("#main-content").load("rank", {page: page}).fadeIn("slow");
  };
</script>