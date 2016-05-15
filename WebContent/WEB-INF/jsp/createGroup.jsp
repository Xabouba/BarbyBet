<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="import.jsp" %>
		<link rel="stylesheet" type="text/css" href="css/match.css">
    </head>
    <body onLoad="goforit()">
    <div id="fb-root"></div>
    	<div class="wraper">
        	<%@include file="header.jsp" %>
    		
    		<!-- page-header -->
            <div id="content">
                <div class="top-effect clearfix">
                    <span class="pull-left"><img src="images/top-left-effect.png" class="img-responsive" alt=""></span>
                    <span class="pull-right"><img src="images/top-right-effect.png" class="img-responsive" alt=""></span>
                </div>
                <!-- top-effect -->
                <div id="main-content" class="pull-left">
                    <div id="sidebar-main-content" class="pull-left">
                        <%@include file='minimized-rank.jsp'%>
                    </div>
                    <!-- sidebar-main-content -->
                    <div id="main-col" class="pull-left">
                            <div class="widget kp-last-news">
                                <h2 class="widget-title"><span>Nouveau groupe</span></h2>
                                <div class="widget-content">
                                    <ul class="list-unstyled">
                                    	<li class="format-standard">
                                    	
        									<form method="POST" action="createGroup" enctype="multipart/form-data">
		                                    	<table style="margin-left: auto; margin-right: auto; width: 100%;">
		                                    		<caption style="margin-top: 5px;">
	                                    			</caption>
	                                    			<tbody>
	                                    			<%if(request.getAttribute("error")!=null){ %>
			                                    		<tr>
			                                    			<td class="error" style="float: left; margin-left: 20px; margin-top:20px; font-weight: bold;">
			                                    				<%=request.getAttribute("error")%>
			                                    			</td>
			                                    		</tr>
			                                    		<%} %>
			                                    		<tr>
			                                    			<td style="float: left; margin-left: 5%; margin-top:20px; font-weight: bold; width:90%; text-align:center;">
			                                    				<input type="text" placeholder="Nom du groupe" class="form-control" id="group-name" name="groupName" value="${param.groupname}">
			                                    			</td>
			                                    		</tr>
			                                    		<tr>
			                                    			<td style="float: left; margin-left: 5%; margin-top:20px; font-weight: bold; width:90%">
			                                    				<input type="radio" name="status" value="public" checked="checked"> <span title="Tout le monde peut rejoindre le groupe">Public</span>
				                                    			<input style="margin-left:4em" type="radio" name="status" value="private"> <span title="Le groupe ne peut être rejoint que par invitation" >Privé</span>
			                                    			</td>
			                                    		</tr>
			                                    		<tr>
			                                    			<td style="float: left; margin-left: 5%; margin-top:20px; font-weight: bold; width:100%">
                                  								<textarea placeholder="Description du groupe" class="form-control" name="groupDescription" rows="6" maxlength="250" style="resize: none; width: 90%">${param.groupdesc}</textarea>
			                                    			</td>
			                                    		</tr>
			                                    		
														<tr>
															<td>
														  		<div id="holder">
														  			<img src="images/icn-upload.png" />
														  			<p style="text-align:center; font-weight:300; color:#b1b3b7">Déposez une image ici</p>
														  			<p style="text-align:center; font-weight:100; color:#000">ou alors ...</p>
																</div> 
																<p id="upload">
																	<input type="file" style="margin:0 auto;" name="group-pic">
																</p>
															 	<p id="filereader">File API &amp; FileReader API not supported</p>
															 	<p id="formdata">XHR2's FormData is not supported</p>
															 	<p id="progress">XHR2's upload progress isn't supported</p>
														 	</td>
														</tr>
			                                    		<tr>
			                                    			<td style="float: left; margin-left: 0; margin-top:20px; font-weight: bold; width:100%; text-align:center;">
	                                  							<input type="submit" name="submit" class="btn btn-primary" value="Valider" id="input-submit">
           													</td>
			                                    		</tr>
			                                    	</tbody>
			                                    </table>
			                                    </form>
                                  		</li>
                                  	</ul>
                                </div>
                                <!-- widget-content -->
                            </div>
                    </div>
                    
                    <!-- main-col -->
                    <div class="clearfix"></div>
                </div>
                <div id="sidebar" class="pull-right">
            		<ul class="clearfix list-unstyled">
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
			                                		<input type="hidden" name="comingFromCreateGroupPage" value="yes" />
			                                		<input type="submit" class="btn btn-primary" value="Valider" id="s">
				                            	</form>
				                            </div>
		                                </li>
                                	</ul>
                                </div>
                        	</div>
                        </li>
           			</ul>
            	</div>
            </div>
            <!-- sidebar -->
            <div class="clearfix"></div>
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
        <script src="js/custom.js"></script>
        <script src="js/autocomplete.js"></script>
        
    	<script type="text/javascript">
	    	initializeAutocompleteLookForGroup();
    	</script>
		<script>
			var holder = document.getElementById('holder'),
			    tests = {
			      filereader: typeof FileReader != 'undefined',
			      dnd: 'draggable' in document.createElement('span'),
			      formdata: !!window.FormData,
			      progress: "upload" in new XMLHttpRequest
			    }, 
			    support = {
			      filereader: document.getElementById('filereader'),
			      formdata: document.getElementById('formdata'),
			      progress: document.getElementById('progress')
			    },
			    acceptedTypes = {
			      'image/png': true,
			      'image/jpeg': true,
			      'image/gif': true
			    },
			    progress = document.getElementById('uploadprogress'),
			    fileupload = document.getElementById('upload');
			"filereader formdata progress".split(' ').forEach(function (api) {
			  if (tests[api] === false) {
			    support[api].className = 'fail';
			  } else {
			    // FFS. I could have done el.hidden = true, but IE doesn't support
			    // hidden, so I tried to create a polyfill that would extend the
			    // Element.prototype, but then IE10 doesn't even give me access
			    // to the Element object. Brilliant.
			    support[api].className = 'hidden';
			  }
			});
			
			function previewfile(file) {
			  if (tests.filereader === true && acceptedTypes[file.type] === true) {
			    var reader = new FileReader();
			    reader.onload = function (event) {
			      var image = new Image();
			      image.src = event.target.result;
			      image.width = 150; // a fake resize
			      
			      // Sets the image where the "cloud" image is
			      var item = holder.childNodes[1];
				  holder.replaceChild(image,item);
			    };
			    reader.readAsDataURL(file);
			  }  else {
			    holder.innerHTML += '<p>Uploaded ' + file.name + ' ' + (file.size ? (file.size/1024|0) + 'K' : '');
			    console.log(file);
			  }
			}
			
			function readfiles(files) {
			    debugger;
			    var formData = tests.formdata ? new FormData() : null;
			    for (var i = 0; i < files.length; i++) {
			      if (tests.formdata) formData.append('file', files[i]);
			      previewfile(files[i]);
			    }
			    // now post a new XHR request
			    if (tests.formdata) {
			      var xhr = new XMLHttpRequest();
			      xhr.open('POST', '/devnull.php');
			      xhr.onload = function() {
			        progress.value = progress.innerHTML = 100;
			      };
			      if (tests.progress) {
			        xhr.upload.onprogress = function (event) {
			          if (event.lengthComputable) {
			            var complete = (event.loaded / event.total * 100 | 0);
			            progress.value = progress.innerHTML = complete;
			          }
			        }
			      }
			      xhr.send(formData);
			    }
			}
			
			if (tests.dnd) { 
			  holder.ondragover = function () { this.className = 'hover'; return false; };
			  holder.ondragend = function () { this.className = ''; return false; };
			  holder.ondrop = function (e) {
			    this.className = '';
			    e.preventDefault();
			    readfiles(e.dataTransfer.files);
			  }
			} else {
			  fileupload.className = 'hidden';
			  fileupload.querySelector('input').onchange = function () {
			    readfiles(this.files);
			  };
			}
		</script>
	</body>
</html>    