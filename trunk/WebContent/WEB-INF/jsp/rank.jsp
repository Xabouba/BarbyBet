<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="import.jsp" %>
    </head>
    <body onLoad="goforit()">
    <div id="fb-root"></div>
<%--     	<% response.setIntHeader("Refresh", 120); %> --%>
    	<div class="wraper">
        	<%@include file="header.jsp" %>
    		
    		<!-- page-header -->
            <!-- page-header -->
            <div id="content">
                <div class="wraper clearfix general-rank">
                    <div class="top-effect clearfix">
                        <span class="pull-left"><img src="images/top-left-effect.png" class="img-responsive" alt=""></span>
                        <span class="pull-right"><img src="images/top-right-effect.png" class="img-responsive" alt=""></span>
                    </div>
                    <div id="main-content" class="pull-left">
                    	<%@include file='rank-part.jsp'%>
                    </div>
                    <!-- main-content -->

                    <div id="sidebar" class="pull-right">
                        <ul class="clearfix list-unstyled">
                        <li>
                            <div class="widget widget-kp-tab">
                                <h2 class="widget-title"><span>Groupes</span></h2>
                                <div class="widget-content">
                                   <div class="tab-content">
                                       <div class="tab-pane active" id="tab_popular">
                                           <ul class="list-unstyled">
                                           		<li>
                                                   <div class="kp-group clearfix" onclick="changeGroup(this.id, 'all')" id="group-general">
                                                       <span>G</span>
                                                       <a>General</a>
                                                       <ul class="kp-metadata clearfix">
                                                           <li>
                                                           	<c:choose>
                                                           		<c:when test="${userRank > 1}">${userRank}ème</c:when>
                                                           		<c:otherwise>${userRank}er</c:otherwise>
                                                           	</c:choose>
                                                           	&nbsp&nbsp&nbsp-&nbsp&nbsp&nbsp
                                                           </li>
                                                           <li>
                                                           	${userPoint}
                                                           	<c:choose>
                                                           		<c:when test="${userPoint > 1}">points</c:when>
                                                           		<c:otherwise>point</c:otherwise>
                                                           	</c:choose>
                                                           </li>
                                                       </ul>
                                                   </div>
                                               </li>
                                           	 <c:forEach items="${groups}" var="userGroup" varStatus="i">
                                               <li>
                                                   <div class="kp-group clearfix" onclick="changeGroup(this.id, ${userGroup.key})" id="group-${i.index}">
                                                       <span>${i.index}</span>
                                                       <a href="match">${userGroup.value.name}</a>
                                                       <ul class="kp-metadata clearfix">
                                                           <li>
                                                           	<c:choose>
                                                           		<c:when test="${userGroup.value.rank > 1}">${userGroup.value.rank}ème</c:when>
                                                           		<c:otherwise>${userGroup.value.rank}er</c:otherwise>
                                                           	</c:choose>
                                                           	&nbsp&nbsp&nbsp-&nbsp&nbsp&nbsp
                                                           </li>
                                                           <li>
                                                           	10 points
                                                           </li>
                                                       </ul>
                                                   </div>
                                               </li>
                                             </c:forEach>
                                         	</ul>
                                        </div>
                                     </div>
                                 </div>
                                <!-- widget-content -->
                            </div>
                            <!-- kp-last-news -->
                        </li>
                    </ul>
                    </div>

                    <!-- sidebar -->
                </div>
                <!-- wraper -->
                <div class="clearfix"></div>
            </div>
            <!-- content -->
        </div>
        
        <footer id="page-footer">
        </footer>
        <!-- page-footer -->
		
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js">
        </script>
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
        <script type="text/javascript" src="js/custom.js"></script>
        
        <script type="text/javascript">
			$("#group-general")[0].style.backgroundColor = "#ebebeb";
			$("#group-general")[0].style.border = "solid 2px #fff";
			changeGroup = function(id, idGroup)
			{
				$("#group-general")[0].style.backgroundColor = "#fff";
				$("#group-general")[0].style.border = "solid 1px #ebebeb";
				
				var i = 0;
				var group = $("#group-" + i);
				while (group[0] != null)
				{
					group[0].style.backgroundColor = "#fff";
					group[0].style.border = "solid 1px #ebebeb";
					i++;
					group = $("#group-" + i)
				}

				$("#" + id)[0].style.backgroundColor = "#ebebeb";
				$("#" + id)[0].style.border = "solid 2px #fff";
			
				$("#main-content").load("rank", {page: 1, group: idGroup}).fadeIn("slow");
			}
        	
        
			var pieData = [
				{
					value: 67,
					color:"#F7464A",
					highlight: "#FF5A5E",
					label: "Réussi"
				},
				{
					value: 33,
					color: "#46BFBD",
					highlight: "#5AD3D1",
					label: "Raté"
				}
			];

			window.onload = function(){
				var ctx = document.getElementById("chart-area").getContext("2d");
				window.myPie = new Chart(ctx).Pie(pieData);
			};

		</script>
    </body>
</html>    