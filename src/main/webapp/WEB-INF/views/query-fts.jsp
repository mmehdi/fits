<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri='http://www.springframework.org/security/tags' prefix='security' %>
<%@ page session="false" %>
<html>
<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <meta http-equiv="content-language" content="cs" />
	<title>Query</title>
	

	<link href="<c:url value="/resources/css/form.css" />" rel="stylesheet"  type="text/css" />	
	<link href="<c:url value="/resources/css/screen.css" />" type="text/css" rel="stylesheet" />
	<link href="<c:url value="/resources/css/jquery-ui/1.10.3/jquery-ui.css" />" type="text/css" rel="stylesheet" />
	<link href="<c:url value="/resources/css/bootstrap-datepicker.css" />" rel="stylesheet"  type="text/css" />	
	<link href="<c:url value="/resources/css/jquery.timepicker.css" />" rel="stylesheet"  type="text/css" />	
	<link href="<c:url value="/resources/css/jquery impromptu/jquery-impromptu.css" />" rel="stylesheet"  type="text/css" />
	<link href="<c:url value="/resources/css/query-form.css" />" rel="stylesheet"  type="text/css" />
	
	<script type="text/javascript" src="<c:url value="/resources/js/jquery/1.10/jquery-1.10.2.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jqueryui/1.10/jquery-ui-1.10.js" />"></script>
	<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery.timepicker.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/bootstrap/bootstrap-datepicker.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/datepair.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery.datepair.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery impromptu/jquery-impromptu.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/editable_dropdown.js" />"></script>
	
	<script type="text/javascript" src="<c:url value="/resources/js/autocomplete.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/query-form.js" />"></script>
	
	<link href="<c:url value="/resources/css/bootstrap.css" />" type="text/css" rel="stylesheet" />
	<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" type="text/css" />

	<script type="text/javascript" src="<c:url value="/resources/js/bootstrap/bootstrap.js" />"></script>
	
	
		<style>
		td{
			padding:4px 6px 0px !important;
		}
		td.label{
			 width: 100px;
			 color:black; 
			 vertical-align: inherit; 
			 font-size: 100%;    
			 font-weight: inherit; 
		}
	</style>
	
	
	<script language="JavaScript">
	function disableEnterKey(e)
	{
	     var key;
	     if(window.event)
	          key = window.event.keyCode;     //IE
	     else
	          key = e.which;     //firefox
	
	     if(key == 13)
	          return false;
	     else
	          return true;
	}
</script>

	
</head>
<body onload="initialize()">
	<div id="layout">
       <div id="header">
         <%@ include file="header.jsp" %>
      </div>
      <div id="main">
        <div id="content">
			<div id="formsContent">
				<!-- form:form id="query_fts" method="post" modelAttribute="ftsQueryFormBean" cssClass="cleanform" -->
				<form:form id="query_fts" method="post" cssClass="cleanform" modelAttribute="ftsQueryFormBean" onKeyPress="return disableEnterKey(event)" >
					<div class="header">
				  		<h2>Query Form</h2>
					</div>
					<fieldset>
				  		<legend>Passenger Info</legend>
				  		<div class="title-group">
				  		<label for = "title">Title - please select from the following list:</label>
				  		<form:select path="title">
								<form:option value="Mr">Mr</form:option>
								<form:option value="Mrs">Mrs</form:option>
								<form:option value="Miss">Miss</form:option>
								<form:option value="Ms">Ms</form:option>
								<form:option value="Other">Other</form:option>
						</form:select>
						</div>
				  		<div class="name-group">
				  		<label for = "first_name">First Name:</label>
      					<form:input path="first_name" placeholder="Enter your first name"
             				 type="text"></form:input>
             			</div>
             			<div class="name-group">
             			<label for = "last_name" >Last Name:</label>
      					<form:input path="last_name" placeholder="Enter your Last name"
             				 type="text" class="name"></form:input>
             			</div>
             			<div class="name-group">
             			<label for = "email" >Email:</label>
      					<form:input path="email" placeholder="Enter your email address"
             				 type="text" class="name"></form:input>
             			</div>
             			<div class="sub-title">
             			<label>Home Address</label>
             			</div>
             			<div class="form-group extra">
             			<label for = "addr_line1" >House number or name:</label>
             			<form:input path="addr_line1" placeholder=""
             				 type="text"></form:input>
             			</div>
             			<div class="form-group">
             			<label for = "addr_line2">Street name:</label>
             			<form:input path="addr_line2" placeholder=""
             				 type="text"></form:input>
             			</div>
             			<div class="form-group">
             			<label for = "addr_line3">Town/city:</label>
             			<form:input path="addr_line3" placeholder=""
             				 type="text"></form:input>
             			</div>
             			<div class="form-group">
             			<label for = "addr_line4">County:</label>
             			<form:input path="addr_line4" placeholder=""
             				 type="text"></form:input>
             			</div>
             			<div class="form-group">
             			<label for = "postcode">Postcode:</label>
             			<form:input path="postcode" 
             				 type="text"></form:input>
             			</div>
             			<div class="form-group extra">
             			<label for = "psg_CHI_num">Passenger CHI number:</label>
             			<form:input path="psg_CHI_num" type="text"></form:input>
             			</div>
				</fieldset>
					
					
					
				<fieldset>
				  	<legend>Outward</legend>
				  	<div class="journey-title">
             			<label >From:</label>
             		</div>
             		<div class = "journey-group">
				  		<div class="form-group checkbox">
				  		<input type="checkbox" id="outward_slct_home_orgn"  value="" > <p>Select home address as the original address</p>
				  		</div>
				  		
				  		<div>
				  		<label for = "search_from">search:</label>
      					<input id="autocomplete_f_outward" placeholder="Enter your address"
             				onFocus="geolocate_f_outward()" type="text" ></input>
             			<div class = "table_from">
				  			<table class="address">
				  			<tr>
						        <td class="label" style="padding:4px 6px 0px; color:black; vertical-align: inherit; font-size: 100%;    font-weight: inherit; ">House number</td>
						        <td class="wideField">
						        	<form:input path="street_number_f_outward" class="field"   style="width: 200px;float:left;margin-right: 0px;"/><span class="compulsory">*</span><form:errors path="street_number_f_outward" cssClass="error" />
						        </td>
						      </tr>
						      
						      <tr>
						        <td class="label">Street</td>
						        <td class="wideField" colspan="2">
						        	<form:input path="route_f_outward" class="field"   style="width: 200px;float:left;margin-right: 0px;"/><span class="compulsory">*</span><form:errors path="route_f_outward" cssClass="error" />
						         </td>
						      </tr>
						      <tr>
						        <td class="label">City</td>
						        <td class="wideField" colspan="3">
						        	<form:input path="locality_f_outward" class="field"  style="float:left;margin-right: 0px;" /><span class="compulsory">*</span><form:errors path="locality_f_outward" cssClass="error" />
						        </td>
						      </tr>
						      <tr>
        						<td class="label">postcode</td>
        						<td class="slimField">
        							<form:input path="postal_code_f_outward" class="field"   style="width: 150px;float:left;margin-right: 0px"/><span class="compulsory">*</span><form:errors path="postal_code_f_outward" cssClass="error" />
        						</td>
      							</tr>
    						</table>
					 </div> 
					 </div>		
					 </div>
					 
					 
					 <div class="journey-title">
             				<label>To:</label>
             		 </div>
             			
             			<div class = "journey-group">
					<div class="form-group checkbox">
						<input type="checkbox" id="outward_slct_home_dstn" value="" > <p>Select home address as the destination address</p>
					</div>
					<div>
				  	<label for = "search_to">search:</label>
      				<input id="autocomplete_t_outward" placeholder="Enter your address"
             				onFocus="geolocate_t_outward()" type="text"></input>
             		<div class = "table_from">
				  		<table class="address">
				  		<tr>
					        <td class="label">House number</td>
					        <td class="wideField">
					              <form:input path="street_number_t_outward" class="field"   style="width: 200px;float:left;margin-right: 0px"/><span class="compulsory">*</span><form:errors path="street_number_t_outward" cssClass="error" />
					        </td>
					      </tr>
					      <tr>
					        <td class="label">Street</td>
					        <td class="wideField" colspan="2">
					               <form:input path="route_t_outward" class="field"   style="width: 200px;float:left;margin-right: 0px"/><span class="compulsory">*</span><form:errors path="route_t_outward" cssClass="error" />
					        </td>
					      </tr>
					      <tr>
					        <td class="label">City</td>
					        <td class="wideField" colspan="3">
					               <form:input path="locality_t_outward" class="field" style="float:left;margin-right: 0px" /><span class="compulsory">*</span><form:errors path="locality_t_outward" cssClass="error" />
					        </td>
					      </tr>
					      <tr>
       						<td class="label">postcode</td>
       						<td class="slimField">
             						 <form:input path="postal_code_t_outward" class="field"   style="width: 150px;float:left;margin-right: 0px"/><span class="compulsory">*</span><form:errors path="postal_code_t_outward" cssClass="error" />
             						</td>
     							</tr>
    					</table>
					</div> 		
					</div>
					 </div>
				</fieldset>
				
				<fieldset>
				  	<legend>Return</legend>
				  	<div class="form-group checkbox">
				  	<form:checkbox  path="return_jrny_required" value=""/>
				  	<p>Return required</p>
				  	</div>
				  		
				  	<div id = "return_journey" class="hidden">
				  		<div class="journey-title">
             			<label >From:</label>
             			</div>
             		
             			<div class = "journey-group">
				  		<div class="form-group checkbox">
				  		<input type="checkbox" id="return_select_home_orgn" value="" > <p>Select home address as the original address</p>
				  		</div>
				  		
				  		<div>
				  		<label for = "search_from">search:</label>
      					<input id="autocomplete_f_return" placeholder="Enter your address"
             				onFocus="geolocate_f_return()" type="text"></input>
             			<div class = "table_from">
				  			<table class="address">
				  			 <tr>
						        <td class="label">House number</td>
						        <td class="wideField">
						        	<form:input path="street_number_f_return" class="field"   style="width: 200px;"/>
						        </td>
						        
						      </tr>
						      <tr>
						        <td class="label">Street</td>
						        
						        <td class="wideField" colspan="2">
						        	<form:input path="route_f_return" class="field"   style="width: 200px;"/>
						         </td>
						      </tr>
						      <tr>
						        <td class="label">City</td>
						        <td class="wideField" colspan="3">
						        	<form:input path="locality_f_return" class="field"   />
						        </td>
						      </tr>
						      <tr>
        						<td class="label">postcode</td>
        						<td class="slimField">
        							<form:input path="postal_code_f_return" class="field"  style="width: 150px;"/>
        						</td>
      							</tr>
    						</table>
					 	</div> 
					 	</div>		
					 	</div>
					 
					 
					 	<div class="journey-title">
             				<label>To:</label>
             		 	</div>
             			<div class = "journey-group">
							<div class="form-group checkbox">
						<input type="checkbox" id="return_select_home_dstn" value="" > <p>Select home address as the destination address</p>
						</div>
						<div>
				  		<label for = "search_to">search:</label>
      					<input id="autocomplete_t_return" placeholder="Enter your address"
             				onFocus="geolocate_t_return()" type="text"></input>
             			<div class = "table_from">
				  		<table class="address">
				  		 <tr>
					        <td class="label">House number</td>
					        <td class="wideField">
					              <form:input path="street_number_t_return" class="field"   style="width: 200px;"/>
					        </td>
					      </tr>
					      <tr>
					        <td class="label">Street</td>
					        <td class="wideField" colspan="2">
					               <form:input path="route_t_return" class="field"   style="width: 200px;"/>
					        </td>
					      </tr>
					      <tr>
					        <td class="label">City</td>
					        <td class="wideField" colspan="3">
					               <form:input path="locality_t_return" class="field"  />
					        </td>
					      </tr>
					      <tr>
       						<td class="label">postcode</td>
       						<td class="slimField">
             						 <form:input path="postal_code_t_return" class="field"   style="width: 150px;"/>
             						</td>
     							</tr>
    					</table>
					</div> 		
					</div>
					 </div>
				  	</div>
				</fieldset>
				
				
				
				<fieldset style = "padding-right: 0px;">
				  	<legend>Time</legend>
					
					<div class="time-group">
					<p style="font-weight: bold; ">Outward journey</p>
				  	<div class = "inline">
				  			<form:select path="time_constraint">
								<form:option value="depart_after">Depart after</form:option>
								<form:option value="arrive_by">Arrive by</form:option>
							</form:select>
				  	</div>
				  	
				  	<p id="datepair">
					    <!-- input type="text" class="date" /-->
					    <form:input path ="datepicker" class="date"/><form:errors path="datepicker" cssClass="error" />
					    <label for = "timepicker" >time<span class="compulsory">*</span>: </label>
					    
					    <!--  input type="text" class="time" /-->
					    <form:input path="timepicker" class="time "/>
					    
					    <span style="display: inline-flex; width: 255px;">(please enter the time using 24 hour clock in the format of HHmm)</span> 
					    <form:errors path="timepicker" cssClass="error" />
					</p>

					<script>
					    // initialize input widgets first
					    /* $('#datepair .time').timepicker({
					        'showDuration': true,
					        'timeFormat': 'H:i:s'
					    });*/
					    
					    var nowDate = new Date();
		                var today = new Date(nowDate.getFullYear(), nowDate.getMonth(), nowDate.getDate(), 0, 0, 0, 0);
					    $('#datepair .date').datepicker({
					        'format': 'dd/mm/yyyy',
					        'autoclose': true,
					        'startDate': today
					    });
					    // initialize datepair
					    $('#datepair').datepair();
					    
					</script>
					</div>
					
		 
					<div id="time_rtn" class="time-group hidden">
					<p style="font-weight: bold; ">Return journey</p>
				  	<div class = "inline">
				  			<form:select path="time_constraint_rtn">
								<form:option value="depart_after">Depart after</form:option>
							</form:select>
				  	</div>
				  	
				  	<p id="datepair_rtn">
					    <form:input path ="datepicker_rtn" class="date"/><form:errors path="datepicker_rtn" cssClass="error" />
					    <label for = "timepicker_rtn" >time<span class="compulsory">*</span>: </label>
					    <form:input path="timepicker_rtn" class="time "/>
					    <span style="display: inline-flex; width: 255px;">(please enter the time using 24 hour clock in the format of HHmm)</span> 
					    <form:errors path="timepicker_rtn" cssClass="error" />
					</p>

					<script>
				    var nowDate = new Date();
	                var today = new Date(nowDate.getFullYear(), nowDate.getMonth(), nowDate.getDate(), 0, 0, 0, 0);
			
					    $('#datepair_rtn .date').datepicker({
					        'format': 'dd/mm/yyyy',
					        'autoclose': true,
					        'startDate': today
					    });
					    // initialize datepair
					    $('#datepair_rtn').datepair();
					    
					</script>

					</div>
				</fieldset>
				<fieldset>
				  	<legend>Passenger</legend>
				  	<form:label path="age_group">
						Age group<span class="compulsory">*</span>:
					</form:label>
					
					<!-- deprecated comboBox -->
					<!--  form:input type="text" path = "age_group" value="under 16" selectBoxOptions="under 16;16 to 21;22 to 54;55 to 59;60 and above" /-->
					<form:select path="age_group" style="width: 200px;" onKeyDown="fnKeyDownHandler_A(this, event);" onKeyUp="fnKeyUpHandler_A(this, event); return false;" onKeyPress = "return fnKeyPressHandler_A(this, event);"  onChange="fnChangeHandler_A(this);" onFocus="fnFocusHandler_A(this);">
					  	<!-- value="" style="color:#ff0000;background-color:#ffff00;">--Editable Dropdown-- --> <!-- This is the Editable Option -->
						<form:option value="under 16">under 16</form:option>
						<form:option value="16 to 21">16 to 21</form:option>
						<form:option value="22 to 54">22 to 54</form:option>
						<form:option value="55 to 59">55 to 59</form:option>
						<form:option value="60 and above" selected="selected">60 and above</form:option>
					</form:select>
					
					<form:label path="mobility_status">
						Mobility Status<span class="compulsory">*</span>:
					</form:label>
					
					<!-- deprecated comboBox -->
					<!--  form:input type="text" path = "mobility_status" value="Able bodied" selectBoxOptions="Able bodied;Disabled - wheelchair user;Disabled - other;Mobility impaired - unable to use regular PT" /-->
					<form:select path="mobility_status" style="width: 200px;" onKeyDown="fnKeyDownHandler_A(this, event);" onKeyUp="fnKeyUpHandler_A(this, event); return false;" onKeyPress = "return fnKeyPressHandler_A(this, event);"  onChange="fnChangeHandler_A(this);" onFocus="fnFocusHandler_A(this);">
					  <!-- value="" style="color:#ff0000;background-color:#ffff00;">--Editable Dropdown-- --> <!-- This is the Editable Option -->
					  <form:option value="Able bodied" selected="selected">Able bodied</form:option>
					  <form:option value="Disabled - wheelchair user">Disabled - wheelchair user</form:option>
					  <form:option value="Disabled - other" >Disabled - other</form:option>
					  <form:option value="Mobility impaired - unable to use regular PT">Mobility impaired - unable to use regular PT</form:option>
					</form:select>
					
					<form:label path="journey_purpose">
						Journey purpose<span class="compulsory">*</span>:
					</form:label>
					<!-- deprecated comboBox -->
					<!-- form:input type="text" path = "journey_purpose" value="Health Appointment" selectBoxOptions="Health Appointment;Shopping;Social/Leisure;School/Education;Work/Commuting;Other" / -->
					<form:select path="journey_purpose" style="width: 200px;" onKeyDown="fnKeyDownHandler_A(this, event);" onKeyUp="fnKeyUpHandler_A(this, event); return false;" onKeyPress = "return fnKeyPressHandler_A(this, event);"  onChange="fnChangeHandler_A(this);" onFocus="fnFocusHandler_A(this);">
					  	<!--   value="" style="color:#ff0000;background-color:#ffff00;">--Editable Dropdown-- --> <!-- This is the Editable Option -->
					  	<form:option value="Health Appointment" selected="selected">Health Appointment&nbsp;&nbsp;</form:option>
						<form:option value="Shopping">Shopping</form:option>
						<form:option value="Social/Leisure">Social/Leisure</form:option>
						<form:option value="School/Education">School/Education</form:option>
						<form:option value="Work/Commuting">Work/Commuting</form:option>
						<form:option value="Other">Other</form:option>
					</form:select>
					<div>
						<div class="form-group checkbox">
						<form:checkbox path="any_accompanying" value="" style="margin-top:2px;" />
						<p>Do you need any accompanying person?</p> <span style = "font-size:12px;">(if yes, check the box)</span>
						</div>
					</div>
					<div>
					<div class="form-group checkbox">
					<form:checkbox path="individual_transport" value="" style="margin-top:2px;" />
					<p>Individual transport required</p> <span style = "font-size:12px;">(if yes, check the box)</span>
					</div>
					</div>
					
					<div>
					<div class="form-group checkbox" id="wheelchair_user" >
					<form:checkbox path="able_travel_outof_wheelchair" value="" style="margin-top:2px;" />
					<p>Able to get out of wheelchair to travel</p> <span style = "font-size:12px;">(if yes, check the box)</span>
					</div>
					</div>
					
					<form:label path="journey_purpose">
						Other issues to be considered? Please select from the list below:
					</form:label>
					<!-- deprecated comboBox -->
					<!-- form:input type="text" path = "journey_purpose" value="Health Appointment" selectBoxOptions="Health Appointment;Shopping;Social/Leisure;School/Education;Work/Commuting;Other" / -->
					<form:select path="other_issue" style="width: 200px;"  >
					  	<form:option value="None" selected="selected">None</form:option>
					  	<c:if test="${not empty other_issues}">
					  	<c:forEach items="${other_issues}" var="issue">
							<form:option value="${issue}" >${issue}</form:option>
						</c:forEach>	
					  	</c:if>
					</form:select>
					
				</fieldset>
				<fieldset>
					<legend>Preference</legend>
					<div class="wrapper">
					<div class="left">
					<label for = "sortable">preferred options:</label>
					<ol id="sortable">
						<li id='ranking_1'  class="ui-state-default"><span class="ui-icon ui-icon-arrowthick-2-n-s"></span>Minimise travel time</li>
						<li id='ranking_2' class="ui-state-default"><span class="ui-icon ui-icon-arrowthick-2-n-s"></span>Minimise fare</li>
						<li id='ranking_3' class="ui-state-default"><span class="ui-icon ui-icon-arrowthick-2-n-s"></span>Minimise number of changes</li>
					</ol>
					<div style = "font-size: 11px;margin-top:4px;">(please click and drag to change your preference ordering)</div>
					</div>
					<div class="right">
					<div class= "prefer_img">
						<p class="up">more preferred</p>
						<img width="30" height="113" alt="" src="<c:url value="/resources/img/gradient arrow.png" />" ></img>
						<p class="down">less preferred</p>
					</div>
					</div>
					</div>
				</fieldset>
				<form:hidden path="prefered_options" />
				<p><button class="btn btn-success" type="submit">Submit</button></p>
			</form:form>
			
			
			<script type="text/javascript">
				$("#query_fts").submit(function() {  
					$('#prefered_options').val($( "#sortable" ).sortable("serialize"));
					//$('#prefered_operators').val($( "#sortable2" ).sortable("serialize"));
				});	
			</script>
			</div>
        </div>
    </div>
	<div id="footer">
		<%@ include file="footer.jsp" %>
    </div>
</div>

</body>
</html>