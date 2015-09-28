<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>
<%@ taglib uri='http://www.springframework.org/security/tags' prefix='security' %>
<%@ page session="true" %>
<%pageContext.setAttribute("newLine", "\n"); %>

<html>
<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <meta http-equiv="content-language" content="cs" />
	<title>Matching result</title>
	<link href="<c:url value="/resources/css/screen.css" />" type="  text/css" rel="stylesheet" media="screen,projection" />

	<script type="text/javascript" src="<c:url value="/resources/js/jquery/1.6/jquery.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/reveal/jquery.reveal.js" />"></script>

	 
	 
	 <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
  
	<link href="<c:url value="/resources/css/reveal/reveal.css" />" rel="stylesheet"  type="text/css" />
	<style>
	body {
			font-family: Lucida Grande, sans-serif;
			font-size: .75em;
			margin: 1em auto;
			width: 763px;
		}
		
	#navigation ul li {	padding: 5px; }
	h2 { color: #2567ee; } 
	b { font-weight: bold; }
	
	table.ift  {
		margin: 6 0 6 0;
	}
	th.dep {
		width: 150px;
	}
	th.fare {
		width: 15%;
	}
	
	th.comments {
		width: 10%;
	}
	p.upper{
		margin: 0 0 0 0;
		font-size: 13px;
	}
	p.mid{
		margin: 0 0 0 0;
		padding-left:14px;
		font-size: 13px;
		font-weight: 300;
	}
	p.lower{
		margin: 0 0 0 0;
		padding-left:31px;
		font-size: 13px;
		font-weight: 300;
		color: #777;
	}
	img.icon {
		width: 15px;
		height: 15px; 
	}
	span.date {
		font-size: 13px;
		color: #777;
	}
	#relaxed_option {
		display: none;
	}
	
	#relaxed_option_rtn {
		display: none;
	}
	
	div.option{
		margin-bottom:20px;
	}
	
	
	#send_mail{
		cursor:pointer;
         color:#2567ee;
         text-decoration:underline;
	}
	
	#send_mail:hover {
   	text-decoration:none;
	}
	

	</style>
	
		<link href="<c:url value="/resources/css/bootstrap.css" />" type="text/css" rel="stylesheet" />
	<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" type="text/css" />

	<script type="text/javascript" src="<c:url value="/resources/js/bootstrap/bootstrap.js" />"></script>
		
		
	<script>

	var dialog;
	function sendEmail(){
		console.log("send email");	
		
		 var myObj = {};
		myObj["email"] = $("input[name='email']").val();
		    
		$.ajax({
		    url: "sendMailSimple",  //   sendmail
		    type: 'POST',
		    dataType: 'json',
		    data: JSON.stringify(myObj),
		    contentType: 'application/json',
		    mimeType: 'application/json',
		    success: function(data) {
				console.log("mail sent "+data.content);
				alert(data.content);
		    },
		    error:function(data,status,er) {
		        console.log(data);
		        console.log(er);
		    }
		});
		dialog.dialog( "close" );
	}
	
	$(document).ready(function() {
		 dialog = $("#dialog-form").dialog({
		      autoOpen: false,
		      buttons: {
		          "Send": sendEmail,
		          Cancel: function() {
		            dialog.dialog( "close" );
		          }
		        },
		        close: function() {
		         
		        }
		     });
		
		
		
		 
		$('#send_mail').click(function(e){
			  e.preventDefault();
			  console.log("click email this page");
			  dialog.dialog( "open" );
			  
			  
			  $.ajax({
				    url: "sendMailSimple",  //   sendmail
				    type: 'POST',
				    dataType: 'json',
				    data: "",
				    contentType: 'application/json',
				    mimeType: 'application/json',
				    success: function(data) {
						//console.log("mail sent "+data.content);
						//alert(data.content);
				    },
				    error:function(data,status,er) {
				        console.log(data);
				        console.log(er);
				    }
				});
			  
			});
		
		
	});
	
</script>
	
</head>
<body>
<div id="layout">
      <div id="header">
         <%@ include file="header.jsp" %>
      </div>
      <div id="main">
      <div id="content">
      
      
       	<div id = "matching">
        		<div class="header">
			  		
			  		<c:if test="${not empty message}">
						<div id="message" class="success">${message}</div>	
			  		</c:if>
			  		
			  		<c:if test="${empty email_view}">
						<span id="send_mail" style = "float: right; width: 100%; text-align: right; font-size: 13px; padding-right: 10px;">Email this page</span>
			  		
			  		</c:if>
				</div>
				
<div id="dialog-form" title="send email" style="display:none;">
 
  <form >
    <fieldset>
      <label for="email">Email</label>
      <input type="text" name="email" id="email" value="${email}" class="text ui-widget-content ui-corner-all">

    </fieldset>
  </form>
</div>
 <input type="text" name="_email" id="_email" value="${email}" style="display:none">

        <div id = "result">
        	<table summary="" >
	        	<caption align="top" style = "margin-bottom:10px;">
	        	<h2>${caption}</h2>
	        	<span class="date">Date of travel: ${date_of_travel}</span>
	        	</caption>
	        	<tbody>
	        	</tbody>
        	 </table>
        	 <p style="font-size: 150%;">Outward journey - ${date_of_travel}</p>
        	 <div id="options" >
	        		<c:forEach items="${options}" var="option">
	        		<div class="option">
	        		
		            	<table cellspacing="0" cellpadding="0" class="ift table">
	                    		<tr>
	                    			<th rowspan="2" style="font-weight: normal;">
	                    				<c:choose>
	        			 <c:when test="${option.getSourceType()  == 'google_transit'}">
	        			 	<a href="#" class="big-link" data-reveal-id="${option.getSourceID()}_info" data-animation="fade">Public bus service</a>
		        		
	        			 </c:when>
	        			 <c:when test="${option.getSourceType()  == 'taxi'}">
	        			 	<a href="#" class="big-link" data-reveal-id="${option.getOperator().getOperatorId()}" data-animation="fade">${option.getOperator().getName()}</a>
	        			 	<div id="${option.getOperator().getOperatorId()}" class="reveal-modal">
									<h1>${option.getOperator().getName()}</h1>
									<table>
									<tr><td>Description:</td><td>${option.getOperator().getDescription()}</td></tr>
									<tr><td>Operating Time:</td><td>${option.getOperator().getOperatingTime()}</td></tr>
									<tr><td>Yellow page link:</td><td>
									<a href="http://www.yell.com/s/taxis-${origin_postcode}.html" target="_blank">http://www.yell.com/s/taxis-${origin_postcode}.html</a>
									<!--  a href="http://www.yell.com/s/taxis-uk.html" target="_blank">http://www.yell.com/s/taxis-uk.html</a-->
									
									</td></tr>
									<c:if test="${not empty option.getOperator().getTel()}">
										<tr><td>Booking hotline:</td><td>${option.getOperator().getTel()}</td></tr>
									</c:if>
									<c:if test="${not empty option.getOperator().getWebsite()}">
										<tr><td>Web:</td><td><a href="${option.getOperator().getWebsite()}" target="_blank">${option.getOperator().getWebsite()}</a></td></tr>
									</c:if>
									<c:if test="${not empty option.getOperator().getContactUs()}">
										<tr><td>Contact details:</td><td>${option.getOperator().getContactUs()}</td></tr>
									</c:if>
									</table>
									<a class="close-reveal-modal">&#215;</a>
								</div>
	        			 </c:when>
	        			 <c:when test="${option.getSourceType()  == 'registry_operator'}">
	        			 	<a href="#" class="big-link" data-reveal-id="${option.getOperator().getOperatorId()}_registry" data-animation="fade">${option.getOperator().getName()}</a>
	        			 	<div id="${option.getOperator().getOperatorId()}_registry" class="reveal-modal">
									<h1>${option.getOperator().getName()}</h1>
									<table>
									<tr><td>Description:</td><td>${option.getOperator().getDescription()}</td></tr>
									<tr><td>How to book:</td><td>${option.getOperator().getContactUs()}</td></tr>
									</table>
									<a class="close-reveal-modal">&#215;</a>
								</div>
	        			 </c:when>
	        			 
	        			 <c:otherwise>
	        			 	<a href="#" class="big-link" data-reveal-id="${option.getOperator().getOperatorId()}" data-animation="fade">${option.getOperator().getName()}</a>
								<div id="${option.getOperator().getOperatorId()}" class="reveal-modal">
									<h1>${option.getOperator().getName()}</h1>
									<table>
									<tr><td>Description:</td><td>${option.getOperator().getDescription()}</td></tr>
									<tr><td>Operating Time:</td><td>${option.getOperator().getOperatingTime()}</td></tr>
									<c:if test="${not empty option.getOperator().getTel()}">
										<tr><td>Booking hotline:</td><td>${option.getOperator().getTel()}</td></tr>
									</c:if>
									<c:if test="${not empty option.getOperator().getWebsite()}">
										<tr><td>Web:</td><td><a href="${option.getOperator().getWebsite()}" target="_blank">${option.getOperator().getWebsite()}</a></td></tr>
									</c:if>
									<c:if test="${not empty option.getOperator().getContactUs()}">
										<tr><td>Contact details:</td><td>
										${fn:replace(option.getOperator().getContactUs(), linefeed, "<br/>")}
										</td></tr>
									</c:if>
									</table>
									<a class="close-reveal-modal">&#215;</a>
								</div>
	        			 </c:otherwise>
	        		</c:choose>
	                    			</th>
	                    		
									 <c:choose>
				        			 <c:when test="${option.getSourceType()  == 'google_transit'}">
				        			 	<th width= "10%">Departs</th>
										<th class="from">From</th>
										<th class="to">To</th>
				        			 	<th class="arr" >Arrives</th>
										<th class="chg" >Changes</th>
										<th class="info" >Info</th>
				        			 </c:when>
				        			 <c:when test="${option.getSourceType()  == 'taxi'}">
				        			 	<th width= "10%">Departs</th>
										<th class="from">From</th>
										<th class="to">To</th>
				        			 </c:when>
				        			 
				        			 <c:otherwise>
				        			 	<th class="dep">Departs</th>
										<th class="from">From</th>
										<th class="to">To</th>
				        			 </c:otherwise>
				        			 </c:choose>
										<th class="fare" >Fare</th>
										<th class="comments" >Comments</th>
									</tr>
		              
	                     		<tr>
	                     		<td class="dep">
	                     		 <c:choose>
			        			 <c:when test="${option.getSourceType()  == 'google_transit'}">
			        			 	${option.getDepartureTime()} 
			        			 </c:when>
			        			 <c:when test="${option.getSourceType()  == 'taxi'}">
			        			 	Subject to availability
			        			 </c:when>
			        			  <c:when test="${option.getSourceType()  == 'registry_operator'}">
				        			 Subject to availability, please contact the operator 
				        			 <a href="#" class="big-link" data-reveal-id="${option.getOperator().getOperatorId()}_registry" data-animation="fade">${option.getOperator().getName()}</a>
				        			
			        			 </c:when>
			        			 <c:otherwise>
			        			 	Subject to availability, please contact the operator 
							        <a href="#" class="big-link" data-reveal-id="${option.getOperator().getOperatorId()}_contact" data-animation="fade">${option.getOperator().getName()}</a>
							        <div id="${option.getOperator().getOperatorId()}_contact" class="reveal-modal">
										<h1>${option.getOperator().getName()}</h1>
										<table>
										<tr><td>Description:</td><td>${option.getOperator().getDescription()}</td></tr>
										<tr><td>Operating Time:</td><td>${option.getOperator().getOperatingTime()}</td></tr>
										<c:if test="${not empty option.getOperator().getTel()}">
											<tr><td>Booking hotline:</td><td>${option.getOperator().getTel()}</td></tr>
										</c:if>
										<c:if test="${not empty option.getOperator().getWebsite()}">
											<tr><td>Web:</td><td><a href="${option.getOperator().getWebsite()}" target="_blank">${option.getOperator().getWebsite()}</a></td></tr>
										</c:if>
										<c:if test="${not empty option.getOperator().getContactUs()}">
											<tr><td>Contact details:</td><td>
											${fn:replace(option.getOperator().getContactUs(), newLine, "<br/>")}
											</td></tr>
										</c:if>
										</table>
										<a class="close-reveal-modal">&#215;</a>
									 </div>
				        		  </c:otherwise>
				        		  </c:choose>
	                     	      </td>
	                     		  <td class="from">${option.getStart_address()}</td>
	                     		  <td class="to">${option.getEnd_address()}</td>
		                     		 <c:choose>
				        			 <c:when test="${option.getSourceType()  == 'google_transit'}">
				        			 	<td class="arr">${option.getArrivalTime()}</td>
				        			 	<td class="chg">
				        			 		${option.getChanges()}
				        			 	 </td>
				        			 	<td class="info">
				        			 	<a href="#" class="big-link" data-reveal-id="${option.getSourceID()}_info" data-animation="fade">journey detail</a>
				        			 		<div id="${option.getSourceID()}_info" class="reveal-modal">
				        			 		<h1>Journey Details:</h1>
				        			 		${option.getChangesInfo()}
				        			 		<a class="close-reveal-modal">&#215;</a>
				        			 		</div>
				        			 	</td>
				        			 </c:when>
				        			 </c:choose>
	                     		    <td class="fare">${option.getFareInfo()}</td>
	                     		    <td class="comments">${option.getComments()}</td>
	                     		  </tr>
		                    
		                     </table>
		          
                
                	   
                	</div> <!-- end of div option -->
               	 	</c:forEach>
        	
        	</div><!-- end of div options -->
        	
        	 <div id = "relaxed_result" style = "margin-top:10px;">
        		<!--  <button id="showr">Show</button>
				<button id="hidr">Hide</button> 
				<button id="toggle">Toggle </button>
				-->
				<a id = "show_relaxed" href="#" style="margin-left: 610px; margin-right: 0px;">Show relaxed options</a>
				<script>
				var isChanged = false;
					$( "#show_relaxed" ).click(function( event ) {
						event.preventDefault();
						$( "#relaxed_option" ).toggle("slow");
						if(!isChanged){
							$("#show_relaxed").text("Hide relaxed options");
							isChanged = true;
						}else{
							$("#show_relaxed").text("Show relaxed options");
							isChanged = false;
						}
					});
					
				</script>
				
        	 	<div id ="relaxed_option" >
        	 		 <c:choose>
				     	<c:when test="${relaxed_options.size()  > 0}">
				     		<table class="hidden">
		           				<thead>
	                   				<tr>
				        			<th>Provider</th>
									<th>Constraints to relax</th>
									<th>Fare</th>
									</tr>
		                		</thead>
		                		<tbody>
		                		<c:forEach items="${relaxed_options}" var="r_option">
		                			<tr>
		                				<td>
		                				<a href="#" class="big-link" data-reveal-id="${r_option.getOperator().getOperatorId()}_relInfo" data-animation="fade">${r_option.getOperator().getName()}</a>
				        			 		<div id="${r_option.getOperator().getOperatorId()}_relInfo" class="reveal-modal">
				        			 		<h1>${r_option.getOperator().getName()}</h1>
											<table>
											<tr><td>Description:</td><td>${r_option.getOperator().getDescription()}</td></tr>
											<!--  <tr><td>Operating Time:</td><td>${r_option.getOperator().getOperatingTime()}</td></tr> -->
											<c:if test="${not empty r_option.getOperator().getWebsite()}">
												<tr><td>Web:</td><td><a href="${r_option.getOperator().getWebsite()}" target="_blank">${r_option.getOperator().getWebsite()}</a></td></tr>
											</c:if>
											<tr><td>Eligible Passengers:</td><td>${r_option.getOperator().getEligInfo()}</td></tr>
											<tr><td>Preferences:</td><td>${r_option.getOperator().getPreferences()}</td></tr>
											</table>
											<a class="close-reveal-modal">&#215;</a>
				        			 		</div>
		                				</td>
		                				<td>${r_option.getRelaxingInfo()}</td>
		                				<td>${r_option.getFareInfo()}</td>
		                			</tr>
		                		</c:forEach>
		                		</tbody>
		                	</table>
				     	</c:when>
				     	<c:otherwise>
				     		<table class="hidden">
		                			<tr>
		                				<td>No relaxed options are available for this query.</td>
		                			</tr>
		                	</table>
				     	</c:otherwise>
				     </c:choose>
				  
        	 	</div>
        	 	
           	</div>
           	
        	
        	<c:if test="${not empty options_rtn}">
        		<p style="font-size: 150%;margin-top:50px;">Return journey - ${date_of_travel_rtn}</p>
        		<div class="options" >
	        		<c:forEach items="${options_rtn}" var="option">
	        		<div class="option">
	        		
		            	<table cellspacing="0" cellpadding="0" class="ift table">
		            
	                    		<tr>
	                    			<th rowspan="2" style="font-weight: normal;">
	                    				<c:choose>
	        			 <c:when test="${option.getSourceType()  == 'google_transit'}">
	        			 	<a href="#" class="big-link" data-reveal-id="${option.getSourceID()}_info_rtn" data-animation="fade">Public bus service</a>
		        		
	        			 </c:when>
	        			 <c:when test="${option.getSourceType()  == 'taxi'}">
	        			 	<a href="#" class="big-link" data-reveal-id="${option.getOperator().getOperatorId()}_rtn" data-animation="fade">${option.getOperator().getName()}</a>
	        			 	<div id="${option.getOperator().getOperatorId()}_rtn" class="reveal-modal">
									<h1>${option.getOperator().getName()}</h1>
									<table>
									<tr><td>Description:</td><td>${option.getOperator().getDescription()}</td></tr>
									<tr><td>Operating Time:</td><td>${option.getOperator().getOperatingTime()}</td></tr>
									<tr><td>Yellow page link:</td><td>
									<a href="http://www.yell.com/s/taxis-${origin_postcode_rtn}.html" target="_blank">http://www.yell.com/s/taxis-${origin_postcode_rtn}.html</a>
									<!-- a href="http://www.yell.com/s/taxis-uk.html" target="_blank">http://www.yell.com/s/taxis-uk.html</a-->
									</td></tr>
									<c:if test="${not empty option.getOperator().getTel()}">
										<tr><td>Booking hotline:</td><td>${option.getOperator().getTel()}</td></tr>
									</c:if>
									<c:if test="${not empty option.getOperator().getWebsite()}">
										<tr><td>Web:</td><td><a href="${option.getOperator().getWebsite()}" target="_blank">${option.getOperator().getWebsite()}</a></td></tr>
									</c:if>
									<c:if test="${not empty option.getOperator().getContactUs()}">
										<tr><td>Contact details:</td><td>${option.getOperator().getContactUs()}</td></tr>
									</c:if>
									</table>
									<a class="close-reveal-modal">&#215;</a>
								</div>
	        			 </c:when>  
	        			  <c:when test="${option.getSourceType()  == 'registry_operator'}">
	        			 	<a href="#" class="big-link" data-reveal-id="${option.getOperator().getOperatorId()}_registry_rtn" data-animation="fade">${option.getOperator().getName()}</a>
	        			 	<div id="${option.getOperator().getOperatorId()}_registry_rtn" class="reveal-modal">
									<h1>${option.getOperator().getName()}</h1>
									<table>
									<tr><td>Description:</td><td>${option.getOperator().getDescription()}</td></tr>
									<tr><td>How to book:</td><td>${option.getOperator().getContactUs()}</td></tr>
									</table>
									<a class="close-reveal-modal">&#215;</a>
								</div>
	        			 </c:when>
	        			 
	        			 <c:otherwise>
	        			 	<a href="#" class="big-link" data-reveal-id="${option.getOperator().getOperatorId()}_rtn" data-animation="fade">${option.getOperator().getName()}</a>
								<div id="${option.getOperator().getOperatorId()}_rtn" class="reveal-modal">
									<h1>${option.getOperator().getName()}</h1>
									<table>
									<tr><td>Description:</td><td>${option.getOperator().getDescription()}</td></tr>
									<tr><td>Operating Time:</td><td>${option.getOperator().getOperatingTime()}</td></tr>
									<c:if test="${not empty option.getOperator().getTel()}">
										<tr><td>Booking hotline:</td><td>${option.getOperator().getTel()}</td></tr>
									</c:if>
									<c:if test="${not empty option.getOperator().getWebsite()}">
										<tr><td>Web:</td><td><a href="${option.getOperator().getWebsite()}" target="_blank">${option.getOperator().getWebsite()}</a></td></tr>
									</c:if>
									<c:if test="${not empty option.getOperator().getContactUs()}">
										<tr><td>Contact details:</td><td>
										${fn:replace(option.getOperator().getContactUs(), linefeed, "<br/>")}
										</td></tr>
									</c:if>
									</table>
									<a class="close-reveal-modal">&#215;</a>
								</div>
	        			 </c:otherwise>
	        		</c:choose>
	                    			</th>
	                    		
									 <c:choose>
				        			 <c:when test="${option.getSourceType()  == 'google_transit'}">
				        			 	<th width= "10%">Departs</th>
										<th class="from">From</th>
										<th class="to">To</th>
				        			 	<th class="arr" >Arrives</th>
										<th class="chg" >Changes</th>
										<th class="info" >Info</th>
				        			 </c:when>
				        			 <c:when test="${option.getSourceType()  == 'taxi'}">
				        			 	<th width= "10%">Departs</th>
										<th class="from">From</th>
										<th class="to">To</th>
				        			 </c:when>
			        			 
				        			 <c:otherwise>
				        			 	<th class="dep">Departs</th>
										<th class="from">From</th>
										<th class="to">To</th>
				        			 </c:otherwise>
				        			 </c:choose>
										<th class="fare" >Fare</th>
										<th class="comments" >Comments</th>
									</tr>
		              
	                     		<tr>
	                     		<td class="dep">
	                     		 <c:choose>
			        			 <c:when test="${option.getSourceType()  == 'google_transit'}">
			        			 	${option.getDepartureTime()} 
			        			 </c:when>
			        			 <c:when test="${option.getSourceType()  == 'taxi'}">
			        			 	Subject to availability
			        			 </c:when>
			        			  <c:when test="${option.getSourceType()  == 'registry_operator'}">
			        			 Subject to availability, please contact the operator 
			        			 <a href="#" class="big-link" data-reveal-id="${option.getOperator().getOperatorId()}_registry_rtn" data-animation="fade">${option.getOperator().getName()}</a>
			        			 <div id="${option.getOperator().getOperatorId()}_registry_rtn" class="reveal-modal">
									<h1>${option.getOperator().getName()}</h1>
									<table>
									<tr><td>Description:</td><td>${option.getOperator().getDescription()}</td></tr>
									<tr><td>How to book:</td><td>${option.getOperator().getContactUs()}</td></tr>
									
									</table>
									<a class="close-reveal-modal">&#215;</a>
								</div>
			        			 </c:when>
			        			 <c:otherwise>
			        			 	Subject to availability, please contact the operator 
							        <a href="#" class="big-link" data-reveal-id="${option.getOperator().getOperatorId()}_contact_rtn" data-animation="fade">${option.getOperator().getName()}</a>
							        <div id="${option.getOperator().getOperatorId()}_contact_rtn" class="reveal-modal">
										<h1>${option.getOperator().getName()}</h1>
										<table>
										<tr><td>Description:</td><td>${option.getOperator().getDescription()}</td></tr>
										<tr><td>Operating Time:</td><td>${option.getOperator().getOperatingTime()}</td></tr>
										<c:if test="${not empty option.getOperator().getTel()}">
											<tr><td>Booking hotline:</td><td>${option.getOperator().getTel()}</td></tr>
										</c:if>
										<c:if test="${not empty option.getOperator().getWebsite()}">
											<tr><td>Web:</td><td><a href="${option.getOperator().getWebsite()}" target="_blank">${option.getOperator().getWebsite()}</a></td></tr>
										</c:if>
										<c:if test="${not empty option.getOperator().getContactUs()}">
											<tr><td>Contact details:</td><td>
											${fn:replace(option.getOperator().getContactUs(), newLine, "<br/>")}
											</td></tr>
										</c:if>
										</table>
										<a class="close-reveal-modal">&#215;</a>
									 </div>
				        		  </c:otherwise>
				        		  </c:choose>
	                     	      </td>
	                     		  <td class="from">${option.getStart_address()}</td>
	                     		  <td class="to">${option.getEnd_address()}</td>
		                     		 <c:choose>
				        			 <c:when test="${option.getSourceType()  == 'google_transit'}">
				        			 	<td class="arr">${option.getArrivalTime()}</td>
				        			 	<td class="chg">
				        			 		${option.getChanges()}
				        			 	 </td>
				        			 	<td class="info">
				        			 	<a href="#" class="big-link" data-reveal-id="${option.getSourceID()}_info_rtn" data-animation="fade">journey detail</a>
				        			 		<div id="${option.getSourceID()}_info_rtn" class="reveal-modal">
				        			 		<h1>Journey Details:</h1>
				        			 		${option.getChangesInfo()}
				        			 		<a class="close-reveal-modal">&#215;</a>
				        			 		</div>
				        			 	</td>
				        			 </c:when>
				        			 </c:choose>
	                     		    <td class="fare">${option.getFareInfo()}</td>
	                     		    <td class="comments">${option.getComments()}</td>
	                     		  </tr>
		                    
		                     </table>
		          
                
                	   
                	</div> <!-- end of div option return-->
               	 	</c:forEach>
        	
        	</div><!-- end of div options return -->
        		<!-- return journey relaxed options -->
           		<div id = "relaxed_result_rtn" style = "margin-top:10px;">
				<a id = "show_relaxed_rtn" href="#" style="margin-left: 610px; margin-right: 0px;">Show relaxed options</a>
				<script>
					
					var isChanged_rtn = false;
					$( "#show_relaxed_rtn" ).click(function( event ) {
						event.preventDefault();
						$( "#relaxed_option_rtn" ).toggle("slow");
						if(!isChanged){
							$("#show_relaxed_rtn").text("Hide relaxed options");
							isChanged_rtn = true;
						}else{
							$("#show_relaxed_rtn").text("Show relaxed options");
							isChanged_rtn = false;
						}
					});
					
				</script>
				
        	 	<div id ="relaxed_option_rtn" >
        	 		 <c:choose>
				     	<c:when test="${relaxed_options_rtn.size()  > 0}">
				     		<table class="hidden">
		           				<thead>
	                   				<tr>
				        			<th>Provider</th>
									<th>Constraints to relax</th>
									<th>Fare</th>
									</tr>
		                		</thead>
		                		<tbody>
		                		<c:forEach items="${relaxed_options_rtn}" var="r_option">
		                			<tr>
		                				<td>
		                				<a href="#" class="big-link" data-reveal-id="${r_option.getOperator().getOperatorId()}_relInfo_rtn" data-animation="fade">${r_option.getOperator().getName()}</a>
				        			 		<div id="${r_option.getOperator().getOperatorId()}_relInfo_rtn" class="reveal-modal">
				        			 		<h1>${r_option.getOperator().getName()}</h1>
											<table>
											<tr><td>Description:</td><td>${r_option.getOperator().getDescription()}</td></tr>
											<!--  <tr><td>Operating Time:</td><td>${r_option.getOperator().getOperatingTime()}</td></tr> -->
											<c:if test="${not empty r_option.getOperator().getWebsite()}">
												<tr><td>Web:</td><td><a href="${r_option.getOperator().getWebsite()}" target="_blank">${r_option.getOperator().getWebsite()}</a></td></tr>
											</c:if>
											<tr><td>Eligible Passengers:</td><td>${r_option.getOperator().getEligInfo()}</td></tr>
											<tr><td>Preferences:</td><td>${r_option.getOperator().getPreferences()}</td></tr>
											</table>
											<a class="close-reveal-modal">&#215;</a>
				        			 		</div>
		                				</td>
		                				<td>${r_option.getRelaxingInfo()}</td>
		                				<td>${r_option.getFareInfo()}</td>
		                			</tr>
		                		</c:forEach>
		                		</tbody>
		                	</table>
				     	</c:when>
				     	<c:otherwise>
				     		<table class="hidden">
		                			<tr>
		                				<td>No relaxed options are available for this query.</td>
		                			</tr>
		                	</table>
				     	</c:otherwise>
				     </c:choose>
				  
        	 	</div>
        	 	
        	 	
           	</div>
          
        	
        	</c:if>
        	
        	
           	
           	 	
        	
        	 	
        	 	
			</div>
			</div>
           	</div>
       </div>
       <div id="footer">
        	<%@ include file="footer.jsp" %>
       </div>
</div>

</body>
</html>