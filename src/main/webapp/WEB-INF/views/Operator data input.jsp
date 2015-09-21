<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri='http://www.springframework.org/security/tags' prefix='security' %>
<%@ page session="true" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <meta http-equiv="content-language" content="cs" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="authors" content="Mujtaba Mehdi; Cheng Zeng">
    <title>Operator data input template</title>
    <!-- Custom styles for this template -->
    <!-- Bootstrap core CSS -->
    <!--range slider css-->
    <!--  <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css"> -->
    <link rel="stylesheet" href="<c:url value="/resources/css/jquery-ui/1.10.3/jquery-ui.css" />">
    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet"> 
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet"  type="text/css" />
    <link href="<c:url value="/resources/css/bootstrap-datepicker.css" />" rel="stylesheet"  type="text/css" />	
    <link rel="stylesheet" href="<c:url value="/resources/bootstrapvalidator/css/bootstrapValidator.min.css" />"/>
    <link href="<c:url value="/resources/css/form.css" />" rel="stylesheet"  type="text/css" />		
	<link href="<c:url value="/resources/css/screen.css" />" type="text/css" rel="stylesheet" media="screen,projection" />
	<link href="<c:url value="/resources/css/messages/messages.css" />" rel="stylesheet"  type="text/css" />
	<link href="<c:url value="/resources/css/jquery impromptu/jquery-impromptu.css" />" rel="stylesheet"  type="text/css" />
	<style>
		#dotrural{margin-bottom: 29px;}
		.help-block{color: #A94442;}
		.datepicker {padding: 0px;}
		label.tab-field {
			margin-left: 5px;
			margin-right: 5px;
			width:95px;
		}
		input.tab-field {margin-left: 5px;margin-right: 5px;width: 80px;}
		div.row-space{margin-bottom: 3px;}
		div.jqi {width: auto;}
		label.left-margin{margin-left: 15px;}
	</style>
	<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=drawing,geometry"></script>
	<script src="<c:url value="/resources/js/jquery/1.10/jquery-1.10.2.js" />"></script>  
	<script type="text/javascript" src="<c:url value="/resources/js/geoxml/polys/geoxml3.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/geoxml/ProjectedOverlay.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/blitz-gmap-editor/jscolor/jscolor.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/blitz-gmap-editor/json2.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/blitz-gmap-editor/xmlwriter.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/blitz-gmap-editor/blitz.gmap3.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/bootstrap/bootstrap-datepicker.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery impromptu/jquery-impromptu.js" />"></script>
	<script>
	
	var days = {1:"monday",2:"tuesday",3:"wednesday",4:"thursday",5:"friday"};
	
	$(document).ready(function() {
		
		$("input[name='not_valid_from']").prop('disabled', true);
		$("input[name='not_valid_to']").prop('disabled', true);
		 
		 $("input[name='cb_not_avail']").change(function() {
			 if($(this).is(":checked")) {
				 $("input[name='not_valid_from']").prop('disabled', false);
				 $("input[name='not_valid_to']").prop('disabled', false);
				 $( "input[name^='not_valid_from_']" ).each(function() {
				    	$(this).prop('disabled', false);
					 });
					 $( "input[name^='not_valid_to_']" ).each(function() {
						$(this).prop('disabled', false);
					 });
			 }else{
				 $("input[name='not_valid_from']").val('');
				 $("input[name='not_valid_to']").val('');
				 $("input[name='not_valid_from']").prop('disabled', true);
				 $("input[name='not_valid_to']").prop('disabled', true);
				 $( "input[name^='not_valid_from_']" ).each(function() {
					 $(this).val('');
			    	 $(this).prop('disabled', true);
				 });
				 $( "input[name^='not_valid_to_']" ).each(function() {
					 $(this).val('');
					 $(this).prop('disabled', true);
				 });
			 }
		 });
		
	    $("input[name^='tab_operating_hours_field']").change(function() {
	        var time = $(this).val();
	   		 if(time.match(/^(([01]?[0-9]|2[0-3])[0-5][0-9])$/)){
	   			time = time.substr(0, 2) +":" +time.substr(2, 3);
	   			$(this).val(time);
	   		 }
	    });
	    
	    
	    $("input[name='tab_operating_hours_field_cb_weekdays']").change(function() {
	        if(!$(this).is(":checked")) {
	        	for(var i =1; i<=4; i++){
        			$("input[name='tab_operating_hours_field_weekdays_"+i+"']").val('');
        		}
	        }
	    });
	    
	    
	    
	    $("input[name='tab_operating_hours_field_weekdays_1']").change(function() {
	    	var name = $(this).prop("name");
	    	var targ = null;
	    	var val = null;
	        if($("input[name='tab_operating_hours_field_cb_weekdays']").is(":checked")) {
	        	for (var d=1; d<=5; d++) {
	        		targ = name.replace("weekdays", days[d]);
	        		val = $(this).val();
	        		$("input[name^='"+targ+"']").val(val);
	        	}
	        }
	    });
	    
	    $("input[name='tab_operating_hours_field_weekdays_2']").change(function() {
	    	var name = $(this).prop("name");
	    	var targ = null;
	    	var val = null;
	        if($("input[name='tab_operating_hours_field_cb_weekdays']").is(":checked")) {
	        	for (var d=1; d<=5; d++) {
	        		targ = name.replace("weekdays", days[d]);
	        		val = $(this).val();
	        		$("input[name^='"+targ+"']").val(val);
	        	}
	        }
	    });
	    
	    $("input[name='tab_operating_hours_field_weekdays_3']").change(function() {
	    	var name = $(this).prop("name");
	    	var targ = null;
	    	var val = null;
	        if($("input[name='tab_operating_hours_field_cb_weekdays']").is(":checked")) {
	        	for (var d=1; d<=5; d++) {
	        		targ = name.replace("weekdays", days[d]);
	        		val = $(this).val();
	        		$("input[name^='"+targ+"']").val(val);
	        	}
	        }
	    });
	    
	    $("input[name='tab_operating_hours_field_weekdays_4']").change(function() {
	    	var name = $(this).prop("name");
	    	var targ = null;
	    	var val = null;
	        if($("input[name='tab_operating_hours_field_cb_weekdays']").is(":checked")) {
	        	for (var d=1; d<=5; d++) {
	        		targ = name.replace("weekdays", days[d]);
	        		val = $(this).val();
	        		$("input[name^='"+targ+"']").val(val);
	        	}
	        }
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
            <div id="input-content">
                <div class="container">
				<div class="row">

	<form:form id="data_input_form" method="post" modelAttribute="operatorDataInputForm" >
			<div class="col-md-10 col-lg-8">
               <div class="panel-group" id="accordion">
               		<div class="panel panel-default" id="tab-general"> <div class="panel-heading"><h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
                            General Information
                        </a></h4></div>
                <div id="collapseOne" class="panel-collapse collapse">
                <div class="panel-body" id="tab-general-form">
                <!--form class="form-horizontal" role="form" id="tab-general-form"-->
                <div class="row  row-space" style="margin-left:1px;">
                <div class="form-group">
	                <label for="service_name" class="col-sm-2 control-label">Service Name</label>
	                <div class="col-sm-10">
	                <input type="text" name="service_name" class="form-control" placeholder="Please enter service name." value="<c:if test="${not empty general_info}"><c:out value="${general_info.getName()}"/></c:if>" />
	                </div>
	            </div>
                </div>
                <div class="row  row-space" style="margin-left:1px;">
                	<div class="form-group">
                    <label for="service_description" class="col-sm-2 control-label">Service Description</label>
                    <div class="col-sm-10">
                    <textarea rows="5" cols="5" class="form-control" id="service_description" name="service_description" placeholder="Please describe your service."><c:if test="${not empty general_info}"><c:out value="${general_info.getDescription()}"/></c:if></textarea>   
                    </div>
                    </div>
                </div>
                <div class="row  row-space" style="margin-left:1px;">
                 <div class="form-group">
                    <label for="how_to_book" class="col-sm-2 control-label">How to Book</label>
                    <div class="col-sm-10">
                    <textarea rows="4" cols="5" class="form-control" id="how_to_book" name="how_to_book" placeholder="Describe how the passengers can book the journeys with you, e.g., call the operator two days before they travel."><c:if test="${not empty general_info}"><c:out value="${general_info.getDescription()}"/></c:if></textarea>   
                    </div>
                  </div>
                </div>
                <div class="row  row-space" style="margin-left:1px;">
                <div class="form-group">
	                <label for="type_of_permit" class="col-sm-2 control-label">Type of Permit</label>
	                <div class="col-sm-10">
	                <!--  input type="text" name="type_of_permit" class="form-control" placeholder="" value="<c:if test="${not empty general_info}"><c:out value="${general_info.getType_of_permit()}"/></c:if>" /-->
	                <select name="type_of_permit" class="form-control">
	                	<option value="---" >---</option>
	                	<option value="Section 19 permit" >Section 19 permit</option>
	                	<option value="Section 21 permit" >Section 21 permit</option>
	                	<option value="Section 22 permit" >Section 22 permit</option>
	                </select>
	                
	                </div>
	            </div>
                </div>
                
                <fieldset id = "service_not_avail">
                <div id="service_not_avail_table">
                <div class="form-group">
             		<div class="row" style="margin-top:10px;">
             			 <div  class="col-md-3 control-label"> 
							   <input type="checkbox" style="margin-top:3px;" name="cb_not_avail" /> 
	                     	<label style="top: -1px; vertical-align: top;">
	                     	Service is not available</label>
	                    </div>
             			 <div class="col-md-4 control-label">
             			 <label for="not_valid_from" style="margin-right: 10px;">from</label>
							   <input name="not_valid_from" type="text" style="width: 150px;" disabled></input>
             			 </div>
             			 <div class="col-md-5 control-label">
             			 	<label for="not_valid_to" style="margin-right: 10px;">to</label>
							  <input name="not_valid_to" type="text" style="width: 150px;" disabled></input> 
             			 </div>
             		</div>
                </div>
                </div>
                <div class="row col-lg-12 col-sm-12 col-xs-12">
						<div class="col-md-2 ">
					  	<button type="button" class="btn btn-info btn-sm addMoreSerivceNotAvail" data-toggle="#service_not_avail" >
                		Add more
            			</button>
            			<input type="text" name="service_not_avail_ind" value = "1" style="display:none;" >
            			<input type="text" name="_service_not_avail_ind" value = "1" style="display:none;" >
            			
            			<input type="text" name="added_service_not_avail" value = "added_service_not_avail" style="display:none;" >
            			</div>
						<div class="col-md-2 " style="margin-left: -20px;">
            			<button type="button" class="btn btn-info btn-sm addMoreSerivceNotAvail" data-toggle="#service_not_avail_del" >
                		Delete
            			</button>
            			</div>
						<div class="col-md-8 "></div>
					</div>
                
                </fieldset>
                 <script>
	                 var nowDate = new Date();
	                 var today = new Date(nowDate.getFullYear(), nowDate.getMonth(), nowDate.getDate(), 0, 0, 0, 0);
	                   $('input[name=not_valid_from]').datepicker({
				        'format': 'dd/mm/yyyy',
				        'autoclose': true,
				        'startDate': today
				    });
	                   
	                   $('input[name=not_valid_to]').datepicker({
				        'format': 'dd/mm/yyyy',
				        'autoclose': true,
				        'startDate': today
				    });
	                   
	                   
	                     function new_datepicker(){
	                    	 console.log("call new_datepicker");
	                    	var ind = $('input[name="service_not_avail_ind"]').val();
	                 		var added_ind = parseInt(ind);
	                    	$('input[name="not_valid_from_'+(added_ind)+'"]').datepicker({
	        				        'format': 'dd/mm/yyyy',
	        				        'autoclose': true,
	        				        'startDate': today
	        				    });
	                        $('input[name="not_valid_to_'+(added_ind)+'"]').datepicker({
	        				        'format': 'dd/mm/yyyy',
	        				        'autoclose': true,
	        				        'startDate': today
	        				    });
	                    	 
	                     }
                 </script>
                <div class="form-group">
                	<legend style="margin-left:1px;height:0px;padding-top:20px;">Operating Hours:</legend>
                	<div class="row ">
                	<div class="col-sm-4"></div>
                	<div class="col-sm-4"><label>Morning</label></div>
                	<div class="col-sm-4"><label>Afternoon</label></div>
                	</div>
                	<div class="row">
                	<div class="col-sm-3"></div>
                	<div class="col-sm-4"><label for="tab_operating_hours_field_1"  class="tab-field">opening</label><label for="tab_operating_hours_field_2" class="tab-field">closing</label> </div>
                	<div class="col-sm-5">
                	<label for="tab_operating_hours_field_3" class="tab-field">opening</label><label for="tab_operating_hours_field_4" class="tab-field">closing</label> 
                	</div>
                	</div>
                	<div class="row row-space">
                	<div class="col-sm-3">
                     <input type="checkbox" style="margin-top:3px;margin-left:0px;" name="tab_operating_hours_field_cb_weekdays"/> 
                     <label style="top: -1px; vertical-align: top;">
                      All weekdays</label>
                	</div>
                	<div class="col-sm-4">
                	<input type="text" id="tab_operating_hours_field_weekdays_1" name="tab_operating_hours_field_weekdays_1" class="tab-field"/> 
                	<input type="text" id="tab_operating_hours_field_weekdays_2" name="tab_operating_hours_field_weekdays_2" class="tab-field"/> 
                	</div>
                	<div class="col-sm-5">
                	<input type="text" id="tab_operating_hours_field_weekdays_3" name="tab_operating_hours_field_weekdays_3" class="tab-field"/> 
                	<input type="text" id="tab_operating_hours_field_weekdays_4" name="tab_operating_hours_field_weekdays_4" class="tab-field"/> 
                	</div>
                	</div>
                	
                	<div class="row row-space">
                		<div class="col-sm-3">
                     <!--  input type="checkbox" style="margin-top:3px;margin-left:17px;" name="tab_operating_hours_field_cb-monday" /--> 
                     <label style="top: -1px; vertical-align: top;" class="left-margin">
                      Monday</label>
                	</div>
                	<div class="col-sm-4">
                	<input type="text" id="tab_operating_hours_field_monday_1" name="tab_operating_hours_field_monday_1" class="tab-field"/> 
                	<input type="text" id="tab_operating_hours_field_monday_2" name="tab_operating_hours_field_monday_2" class="tab-field"/> 
                	</div>
                	<div class="col-sm-5">
                	<input type="text" id="tab_operating_hours_field_monday_3" name="tab_operating_hours_field_monday_3" class="tab-field"/> 
                	<input type="text" id="tab_operating_hours_field_monday_4" name="tab_operating_hours_field_monday_4" class="tab-field"/> 
                	</div>
                	</div>
                	
                	<div class="row row-space">
                		<div class="col-sm-3">
                     <!--  input type="checkbox" style="margin-top:3px;margin-left:17px;" name="tab-operating-hours-field-cb-tuesday" /--> 
                     <label style="top: -1px; vertical-align: top;" class="left-margin">
                      Tuesday</label>
                	</div>
                	<div class="col-sm-4">
                	<input type="text" id="tab_operating_hours_field_tuesday_1" name="tab_operating_hours_field_tuesday_1" class="tab-field"/> 
                	<input type="text" id="tab_operating_hours_field_tuesday_2" name="tab_operating_hours_field_tuesday_2" class="tab-field"/> 
                	</div>
                	<div class="col-sm-5">
                	<input type="text" id="tab_operating_hours_field_tuesday_3" name="tab_operating_hours_field_tuesday_3" class="tab-field"/> 
                	<input type="text" id="tab_operating_hours_field_tuesday_4" name="tab_operating_hours_field_tuesday_4" class="tab-field"/> 
                	</div>
                	</div>
                	
                	<div class="row row-space">
                		<div class="col-sm-3">
                     <!--  input type="checkbox" style="margin-top:3px;margin-left:17px;" name="tab-operating-hours-field-cb-wednesday"  /--> 
                     <label style="top: -1px; vertical-align: top;" class="left-margin">
                      Wednesday</label>
                	</div>
                	<div class="col-sm-4">
                	<input type="text" id="tab_operating_hours_field_wednesday_1" name="tab_operating_hours_field_wednesday_1" class="tab-field"/> 
                	<input type="text" id="tab_operating_hours_field_wednesday_2" name="tab_operating_hours_field_wednesday_2" class="tab-field"/> 
                	</div>
                	<div class="col-sm-5">
                	<input type="text" id="tab_operating_hours_field_wednesday_3" name="tab_operating_hours_field_wednesday_3" class="tab-field"/> 
                	<input type="text" id="tab_operating_hours_field_wednesday_4" name="tab_operating_hours_field_wednesday_4" class="tab-field"/> 
                	</div>
                	</div>
                	
                	<div class="row row-space">
                		<div class="col-sm-3">
                     <!--  input type="checkbox" style="margin-top:3px;margin-left:17px;" name="tab-operating-hours-field-cb-thursday" /--> 
                     <label style="top: -1px; vertical-align: top;" class="left-margin">
                      Thursday</label>
                	</div>
                	<div class="col-sm-4">
                	<input type="text" id="tab_operating_hours_field_thursday_1" name="tab_operating_hours_field_thursday_1" class="tab-field"/> 
                	<input type="text" id="tab_operating_hours_field_thursday_2" name="tab_operating_hours_field_thursday_2" class="tab-field"/> 
                	</div>
                	<div class="col-sm-5">
                	<input type="text" id="tab_operating_hours_field_thursday_3" name="tab_operating_hours_field_thursday_3" class="tab-field"/> 
                	<input type="text" id="tab_operating_hours_field_thursday_4" name="tab_operating_hours_field_thursday_4" class="tab-field"/> 
                	</div>
                	</div>
                	
                	<div class="row row-space">
                		<div class="col-sm-3">
                     <!--  input type="checkbox" style="margin-top:3px;margin-left:17px;" name="tab-operating-hours-field-cb-friday" /--> 
                     <label style="top: -1px; vertical-align: top;" class="left-margin">
                      Friday</label>
                	</div>
                	<div class="col-sm-4">
                	<input type="text" id="tab_operating_hours_field_friday_1" name="tab_operating_hours_field_friday_1" class="tab-field"/> 
                	<input type="text" id="tab_operating_hours_field_friday_2" name="tab_operating_hours_field_friday_2" class="tab-field"/> 
                	</div>
                	<div class="col-sm-5">
                	<input type="text" id="tab_operating_hours_field_friday_3" name="tab_operating_hours_field_friday_3" class="tab-field"/> 
                	<input type="text" id="tab_operating_hours_field_friday_4" name="tab_operating_hours_field_friday_4" class="tab-field"/> 
                	</div>
                	</div>
                	
                	<div class="row row-space">
                		<div class="col-sm-3">
                     <!--  input type="checkbox" style="margin-top:3px;margin-left:17px;" name="tab-operating-hours-field-cb-saturday" /--> 
                     <label style="top: -1px; vertical-align: top;" class="left-margin">
                      Saturday</label>
                	</div>
                	<div class="col-sm-4">
                	<input type="text" id="tab_operating_hours_field_saturday_1" name="tab_operating_hours_field_saturday_1" class="tab-field"/> 
                	<input type="text" id="tab_operating_hours_field_saturday_2" name="tab_operating_hours_field_saturday_2" class="tab-field"/> 
                	</div>
                	<div class="col-sm-5">
                	<input type="text" id="tab_operating_hours_field_saturday_3" name="tab_operating_hours_field_saturday_3" class="tab-field"/> 
                	<input type="text" id="tab_operating_hours_field_saturday_4" name="tab_operating_hours_field_saturday_4" class="tab-field"/> 
                	</div>
                	</div>
                	
                	<div class="row row-space">
                		<div class="col-sm-3">
                     <!--  input type="checkbox" style="margin-top:3px;margin-left:17px;" name="tab-operating-hours-field-cb-sunday"/--> 
                     <label style="top: -1px; vertical-align: top;" class="left-margin">
                      Sunday</label>
                	</div>
                	<div class="col-sm-4">
                	<input type="text" id="tab_operating_hours_field_sunday_1" name="tab_operating_hours_field_sunday_1" class="tab-field"/> 
                	<input type="text" id="tab_operating_hours_field_sunday_2" name="tab_operating_hours_field_sunday_2" class="tab-field"/> 
                	</div>
                	<div class="col-sm-5">
                	<input type="text" id="tab_operating_hours_field_sunday_3" name="tab_operating_hours_field_sunday_3" class="tab-field"/> 
                	<input type="text" id="tab_operating_hours_field_sunday_4" name="tab_operating_hours_field_sunday_4" class="tab-field"/> 
                	</div>
                	</div>
                </div>
        		</div>
        		
        		<!--  div id="opening_hours_hidden_table" style="display:none;" ></div-->  
        		 
		        <div class="input-group col-md-offset-9 col-sm-offset-9 col-xs-offset-9" style="margin-bottom: 6px;">
            		<button type="button" class="btn btn-info" id="save_general_info">Save this section</button>
            	</div><!--save clear button-->
        		</div>
    			</div> <!--first-->
    			<!-- vehicle -->
    			<div class="panel panel-default" id="tab-general"> <div class="panel-heading"><h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#accordion" href="#collapseSix">
                            Vehicle
                        </a></h4></div>
                <div id="collapseSix" class="panel-collapse collapse">
                <div class="panel-body" id="tab-general-form">
                <!--form class="form-horizontal" role="form" id="tab-general-form"-->
                <div class="row  row-space" style="margin-left:1px;">
                <div class="form-group">
	                <label for="reg_no" class="col-sm-2 control-label">Registration No.</label>
	                <div class="col-sm-10">
	                <input type="text" id = "reg_no" name="regNum" class="form-control" placeholder="Please enter registration number." value="<c:if test="${not empty reg_num}"><c:out value="${reg_num}"/></c:if>" />
	                </div>
	            </div>
	             <div class="form-group">
	                <label for="vehicle_type" class="col-sm-2 control-label">Vehicle type</label>
	                <div class="col-sm-10">
	                	<form:select path="vehicle_type" class="form-control">
							<form:option value="pickup Van">Pickup Van</form:option>
							<c:if test="${not empty veh_types}">
						  	<c:forEach items="${veh_types}" var="type">
								<form:option value="${type}" >${type}</form:option>
							</c:forEach>	
						  	</c:if>
							<form:option value="other">Other</form:option>
						</form:select>
						<input type="text" id = "other_vehicle" name="other_vehicle" class="form-control" placeholder="Please enter the vehicle type." value="" style="display: none;"/>
	                </div>
	            </div>
                </div>
               
        		</div>
        		
		        <div class="input-group col-md-offset-9 col-sm-offset-9 col-xs-offset-9" style="margin-bottom: 6px;">
            		<button type="button" class="btn btn-info" id="save_vehicle_info">Save this section</button>
            	</div><!--save clear button-->
        		</div>
    			</div> <!--second-->
    			
    			
    			
    			<!-- operating area -->
				<div class="panel panel-default" id="tab-operatin-area"><div class="panel-heading"><h4 class="panel-title">
		            <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">
		                Operating Area
		            </a></h4></div>
    			<div id="collapseTwo" class="panel-collapse collapse in">
      			<div class="panel-body">
            	<div class="form-inline-row">
                <div class="col-md-12">
                        Please use the map below to draw your operating area:
				<div id="myMap" style="height:400px; width:100%;"></div>
					<div class="input-group col-md-offset-8 col-sm-offset-9 col-xs-offset-9" style="margin-top: 9px;">
            		<button type="button" class="btn btn-info" id = "editMap" style="width:75px;">Save it</button>
					
					
					<button style="margin-left:30px;" type="button" class="btn btn-danger" id = "clearMap" onclick="BlitzMap.setMapFromKML(document.getElementById('kmlString').value)">Clear</button>
            		
					<!-- button style="margin-left:30px;" type="button" class="btn btn-danger" id = "clearMap" onclick="BlitzMap.deleteAll();">Clear</button -->
            		</div>
                    <textarea id="mapData" style="display:none;"></textarea>
					<form:textarea path="jsonData" id= "jsonString" style="display:none;"/>
					<form:textarea path="kmlData" id= "kmlString" style="display:none;"/>
				</div>
            	</div>
            	 <!-- div class="input-group col-md-offset-9 col-sm-offset-9 col-xs-offset-9" style="margin-bottom: 6px;">
            		<button type="button" class="btn btn-info" id="save_operating_area_info">Save this section</button>
            	</div -->
    			</div> <!--panel body-->
				</div> <!---->
				</div> <!-- second -->
				
				<!-- eligibility  -->
    			<div class="panel panel-default" id="tab-eligibility"><div class="panel-heading"><h4 class="panel-title">
		            <a data-toggle="collapse" data-parent="#accordion" href="#collapseThree">
		                Passenger Eligibility
		            </a></h4></div>
    			<div id="collapseThree" class="panel-collapse collapse">
      			<div class="panel-body">
                
                <div class="col-lg-12 col-md-12">
					<fieldset>
                    <legend>Select mobility status:</legend>
					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6"> <!--1st column-->
				    	<div class="form-group col-lg-12 col-sm-8 col-md-8">
				        	<label label-default="" for="tab-eligibility-field-6">
				            <input type="checkbox" name="eligible_checkbox_able_bodied" id="tab-eligibility-field-6" value="6" <c:if test="${not empty elig and elig.isAble_bodied()}"><c:out value="checked"/></c:if>/> 
			                Able bodied
				            </label>
				        </div>
				        <div class="form-group col-lg-12 col-sm-11 col-md-10">
				        	<label label-default="" for="tab-eligibility-field-7">
				            <input type="checkbox" name="eligible_checkbox_mobility_prevents_PT" id="tab-eligibility-field-7" value="7" <c:if test="${not empty elig and elig.isMobility_prevents_PT()}"><c:out value="checked"/></c:if>/> 
			                Mobility prevents use of regular Public Transport
				            </label>
				        </div>
				         <div class="form-group col-lg-12 col-sm-8 col-md-8">
				            <label label-default="" for="tab-eligibility-field-9">
				            <input type="checkbox" name="eligible_checkbox_disable_other" id="tab-eligibility-field-9" value="9" <c:if test="${not empty elig and elig.isDisable_other()}"><c:out value="checked"/></c:if>/> 
				            Disabled other
				            </label>
				         </div>
					</div>   <!-- 1st column-->
				    	<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6"> <!--2nd column-->
				        	<div class="form-group col-lg-12 col-sm-11 col-md-8">
				            <label label-default="" for="tab-eligibility-field-8">
				            <input type="checkbox" name="eligible_checkbox_disable_wheelchair_user" id="tab-eligibility-field-8" value="8" <c:if test="${not empty elig and elig.isDisable_wheelchair_user()}"><c:out value="checked"/></c:if>/> 
			                Disabled wheelchair user
				            </label>
				            </div>
				              <div class="form-group col-lg-12 col-sm-11 col-md-10">
				        	<label label-default="" for="tab-eligibility-field-17">
				            <input type="checkbox" name="eligible_checkbox_cant_live_on_a_bus_route" id="tab-eligibility-field-17" value="17" <c:if test="${not empty elig and elig.isMobility_prevents_PT()}"><c:out value="checked"/></c:if>/> 
			                Don't live on a bus route
				            </label>
				        	</div>
				        </div>   <!--2nd column-->
					</fieldset>
				</div>
				<div class="col-lg-12 col-md-12">
                    <fieldset>
                    <legend>Select age groups:</legend>
					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6"> <!--1st column-->
			        	<div class="form-group col-lg-12 col-sm-8 col-md-8">
			            	<label label-default="" for="tab-eligibility-field-1">
			                <input type="checkbox" name="eligible_checkbox_under16" id="tab-eligibility-field-1" value="1" <c:if test="${not empty elig and elig.isAge_under16()}"><c:out value="checked"/></c:if>/>  
			                Under 16 years of age 
			                </label>                
			            </div>      
			            <div class="form-group col-lg-12 col-sm-8 col-md-8">
			            	<label label-default="" for="tab-eligibility-field-2">
			                <input type="checkbox" name="eligible_checkbox_17_to_21" id="tab-eligibility-field-2" value="2" <c:if test="${not empty elig and elig.isAge_17to21()}"><c:out value="checked"/></c:if>/> 
			                17 to 21 years of age
			                </label>                                        
			            </div>
			            <div class="form-group col-lg-12 col-sm-8 col-md-8">
			            	<label label-default="" for="tab-eligibility-field-3">
			                <input type="checkbox" name="eligible_checkbox_22_to_54" id="tab-eligibility-field-3" value="3" <c:if test="${not empty elig and elig.isAge_22to54()}"><c:out value="checked"/></c:if>/> 
			                22 to 54 years of age
			                </label>
			            </div>
					</div>   <!--1st column-->
			        	<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6"> <!--2nd column-->
			            	<div class="form-group col-lg-12 col-sm-8 col-md-8">
			                <label label-default="" for="tab-eligibility-field-4">
			                <input type="checkbox" name="eligible_checkbox_55_to_59" id="tab-eligibility-field-4" value="4" <c:if test="${not empty elig and elig.isAge_55to59()}"><c:out value="checked"/></c:if>/> 
			                55 to 59 years of age
			                </label>
			                </div>
			                <div class="form-group col-lg-12 col-sm-8 col-md-8">
			                <label label-default="" for="tab-eligibility-field-5">
			                <input type="checkbox" name="eligible_checkbox_over60" id="tab-eligibility-field-5" value="5" <c:if test="${not empty elig and elig.isAge_over60()}"><c:out value="checked"/></c:if>/> 
			                60 years or above
			                </label>
			                </div>
			            </div>   <!--2nd column-->
					</fieldset>
				</div>
                <div class="col-lg-12 col-md-12">
                    <fieldset>
                    <legend>Select journey purpose types:</legend>
					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6"> <!--1st column-->
				    	<div class="form-group col-lg-12 col-sm-8 col-md-8">
				        <label label-default="" for="tab-eligibility-field-10">
				        <input type="checkbox" name="eligible_checkbox_health_appointment" id="tab-eligibility-field-10" value="10" <c:if test="${not empty elig and elig.isHealth_appointment()}"><c:out value="checked"/></c:if>/> 
				        Health Appointment
				        </label>                
				        </div>             
				        <div class="form-group col-lg-12 col-sm-8 col-md-8">
				        <label label-default="" for="tab-eligibility-field-11">
				        <input type="checkbox" name="eligible_checkbox_social_care" id="tab-eligibility-field-11" value="11" <c:if test="${not empty elig and elig.isSocial_care()}"><c:out value="checked"/></c:if>/> 
				        Social Care
				        </label>                                        
				        </div>
				        <div class="form-group col-lg-12 col-sm-8 col-md-8">
				        <label label-default="" for="tab-eligibility-field-12">
				        <input type="checkbox" name="eligible_checkbox_shopping" id="tab-eligibility-field-12" value="12" <c:if test="${not empty elig and elig.isShopping()}"><c:out value="checked"/></c:if>/> 
				        Shopping
				        </label>
				        </div>
				        <div class="form-group col-lg-12 col-sm-8 col-md-8">
				        <label label-default="" for="tab-eligibility-field-13">
				        <input type="checkbox" name="eligible_checkbox_leisure_or_visiting_friends" id="tab-eligibility-field-13" value="13" <c:if test="${not empty elig and elig.isLeisure_or_visiting_friends()}"><c:out value="checked"/></c:if>/> 
				        Leisure/Visiting Friends
				        </label>
				        </div>
					</div> <!--1st column-->
				    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6"> <!--2nd column-->
				    	<div class="form-group col-lg-12 col-sm-8 col-md-8">
				        	<label label-default="" for="tab-eligibility-field-14">
				            <input type="checkbox" name="eligible_checkbox_school_or_education" id="tab-eligibility-field-14" value="14" <c:if test="${not empty elig and elig.isSchool_or_education()}"><c:out value="checked"/></c:if>/> 
				            School/Education
				            </label>
				            </div>
				            <div class="form-group col-lg-12 col-sm-8 col-md-8">
				            <label label-default="" for="tab-eligibility-field-15">
				            <input type="checkbox" name="eligible_checkbox_work_or_commuting" id="tab-eligibility-field-15" value="15" <c:if test="${not empty elig and elig.isWork_or_commuting()}"><c:out value="checked"/></c:if>/> 
				            Work/Commuting
				            </label>
				            </div>
				            <div class="form-group col-lg-12 col-sm-8 col-md-8">
				            <label label-default="" for="tab-eligibility-field-16">
				            <input type="checkbox" name="eligible_checkbox_other_purpose" id="tab-eligibility-field-16" value="16" <c:if test="${not empty elig and elig.isOther_purpose()}"><c:out value="checked"/></c:if>/> 
				            Other
				            </label>
				            </div>
					</div> <!--2nd column-->
                    </fieldset>
                </div>
                <div class="col-lg-12 col-md-12">
                    <fieldset>
                    <legend>Other</legend>
                    
                    <div class="form-group">
	                <label for="other_elig_type" class="col-sm-4 control-label">Please specify any other eligibility criteria:</label>
	                <div class="col-sm-8">
	                <form:select path="other_elig_type" class="form-control">
	                		<form:option value="none">None</form:option>
							<c:if test="${not empty other_elig_types}">
						  	<c:forEach items="${other_elig_types}" var="type">
								<form:option value="${type}" >${type}</form:option>
							</c:forEach>	
						  	</c:if>
							<form:option value="other">Other</form:option>
						</form:select>
	                 <input type="text" name="other_elig" class="form-control" placeholder="please specify" style="display:none; margin-top: 10px;" maxlength="50" value="" / >
	                </div>
	            	</div>
                    </fieldset>
                </div>
                
                <div class="col-lg-12 col-md-12">
                    <fieldset>
                    <div class="form-group">
        			<label for="tab-fare-structure-field-1" class=" control-label"> Would you ever consider opening up your service for other trip purposes or passenger types?</label>

       				<div class="form-group"> <!--form group for radio buttons-->
          			<label class="col-md-2">
            		<form:radiobutton  path="tab_elig_radioBtns" value="0" />
            		Yes
        			</label>
        			<label class="col-md-2">
            		<form:radiobutton path="tab_elig_radioBtns" value="1" checked="checked"/>
            		No
        			</label>
        			<label class="col-md-2">
            		<form:radiobutton path="tab_elig_radioBtns" value="2" />
            		Maybe
        			</label>
        			</div> <!--form group for radio buttons-->
    				</div>
    				
    				<div class="form-group" id="tab-elig-openning-up" style="display:none;">
    				 <label class="col-sm-12 control-label">Please explain</label>
                    	<div class="col-sm-12">
                    	<textarea rows="5" cols="5" class="form-control" id="tab-elig-field-18" name="explain_opening_up_elig" placeholder="Please explain openning up your service"></textarea>   
                    </div>
    				</div>
                    </fieldset>
                </div>
                
            	<div class="input-group col-md-offset-9 col-sm-offset-9 col-xs-offset-9">
            	<button type="button" class="btn btn-info" id="save_elig" >Save this section</button>
            	</div><!--save clear button-->
    		</div> <!--panel body-->
			</div> <!--collapse3-->
			</div> <!-- second -->
			<!-- fare -->
			<div class="panel panel-default" id="tab-fare-structure"><div class="panel-heading"><h4 class="panel-title">
        		<a data-toggle="collapse" data-parent="#accordion" href="#collapseFour">
          		Fare Structure
      			</a></h4></div>
				<div id="collapseFour" class="panel-collapse collapse">
				<div class="panel-body">
    				<div class="form-group">
        			<label for="tab-fare-structure-field-1" class=" control-label"> Do you charge a standard fare?</label>

       				<div class="form-group"> <!--form group for radio buttons-->
          			<label class="col-md-2">
            		<form:radiobutton  path="tab_fare_structure_radioBtns" value="0" />
            		Yes
        			</label>
        			<label class="col-md-2">
            		<form:radiobutton path="tab_fare_structure_radioBtns" value="1" checked="checked"/>
            		No
        			</label>
        			<label class="col-md-8">
            		<form:radiobutton path="tab_fare_structure_radioBtns" value="2" />
            		Donations appreciated
        			</label>
        			</div> <!--form group for radio buttons-->
    				</div>
    				
					<div id= "tab-fare-structure-form" style="display: none;">
					<!--  div class="col-lg-12 col-md-12"  id= "tab-fare-structure-form-distance"-->
					
					<div class="form-group" >
        			<label class=" control-label" style="margin-top:10px;"> How do you charge fares?</label>
       				<div class="form-group"> <!--form group for radio buttons-->
          			<label class="col-md-2">
            		<form:radiobutton  path="tab_fare_structure_how_charge_radioBtns" value="0" checked="checked"/>
            		Flat fare
        			</label>
        			<label class="col-md-2">
            		<form:radiobutton path="tab_fare_structure_how_charge_radioBtns" value="1" />
            		Zonal fare
        			</label>
        			<label class="col-md-8">
            		<form:radiobutton path="tab_fare_structure_how_charge_radioBtns" value="2" />
            		Fare per mile
        			</label>
        			</div> <!--form group for radio buttons-->
    				</div>
    				<!--  /div-->
					
					
            		<fieldset id = "distance_charge">
                	<legend>Distance:</legend>
					<div id="fare_table">
					<div class="row col-lg-12 col-sm-12 col-xs-12">
						  <div class="col-md-3 "><label>From</label></div>
						  <div class="col-md-3"><label>To</label></div>
						  <div class="col-md-3"><label>Type</label></div>
						  <div class="col-md-3"><label>Amount</label></div>
					</div>
					<div class="row col-lg-12 col-sm-12 col-xs-12">
						<div class="col-md-3 "><label>0 mile</label></div>
						<div class="col-md-3">
						<input name="fare_dist1_mile_2" type="text" class="form-control" placeholder="1.0" value="<c:if test="${not empty fare}"><c:out value="${fare.getFare_dist1_mile_2()}"/></c:if>" />
						</div>
						<div class="col-md-3">
						<form:select path= "fare_dist1_type" class="form-control">
							<form:option value="flat rate">Flat rate</form:option>
							<form:option value="fare per mile">Fare per mile</form:option>
							</form:select>
						 </div>
						 <div class="col-md-3">£ 
						 <input name = "fare_dist1_charge" type="text" class="form-control charge"  placeholder="0.00" value="<c:if test="${not empty fare}"><c:out value="${fare.getFare_dist1_charge()}"/></c:if>" />
					
						<span class="question" data-toggle="tooltip" data-placement="right" title="If you have flat rate, please specify 0 mile - 1 mile as flat rate.">?</span>
						 
						 </div>
						  
					</div>
					<!-- div class="row col-lg-12 col-sm-12 col-xs-12">
						<div class="col-md-3 ">
						<input name="fare_dist2_mile_1" type="text" class="form-control" placeholder="1.0" value="<c:if test="${not empty fare}"><c:out value="${fare.getFare_dist2_mile_1() }"/></c:if>" />
						</div>
						<div class="col-md-3">
						<input name="fare_dist2_mile_2" type="text" class="form-control" placeholder="2.0" value="<c:if test="${not empty fare}"><c:out value="${fare.getFare_dist2_mile_2() }"/></c:if>" />
						</div>
						<div class="col-md-3">
					  	<form:select path="fare_dist2_type" class="form-control">
							<form:option value="flat rate">Flat rate</form:option>
							<form:option value="fare per mile">Fare per mile</form:option>
						</form:select></div>
						<div class="col-md-3">£ <input name="fare_dist2_charge" type="text" class="form-control charge" placeholder="0.00" value="<c:if test="${not empty fare}"><c:out value="${fare.getFare_dist2_charge() }"/></c:if>"  /></div>
					</div -->
					
					<!-- div id="fare_distance_buffer_1" class="row col-lg-12 col-sm-12 col-xs-12" >
						<div class="col-md-3 ">
						<input name="fare_dist3_mile_1" type="text" class="form-control" placeholder="2.0" value="<c:if test="${not empty fare}"><c:out value="${fare.getFare_dist3_mile_1() }"/></c:if>" />
						</div>
						<div class="col-md-3">
						<input name="fare_dist3_mile_2" type="text" class="form-control" placeholder="2.0" value="<c:if test="${not empty fare}"><c:out value="${fare.getFare_dist3_mile_2() }"/></c:if>" />
						</div>
						<div class="col-md-3">
					  	<form:select path="fare_dist3_type" class="form-control">
						    <form:option value="flat rate">Flat rate</form:option>
						    <form:option value="fare per mile">Fare per mile</form:option>
						</form:select>
						</div>
						<div class="col-md-3">£ 
						<input name = "fare_dist3_charge" type="text" class="form-control charge" value="<c:if test="${not empty fare}"><c:out value="${fare.getFare_dist3_charge()}"/></c:if>" placeholder="0.00"/>
						</div>
					</div>
					<div id="fare_distance_buffer_2" class="row col-lg-12 col-sm-12 col-xs-12">
						<div class="col-md-3 ">
						<input name="fare_dist4_mile_1" type="text" class="form-control" placeholder="3.0" value="<c:if test="${not empty fare}"><c:out value="${fare.getFare_dist4_mile_1() }"/></c:if>" />
						</div>
						<div class="col-md-3">
						<input name="fare_dist4_mile_2" type="text" class="form-control" placeholder="5.0" value="<c:if test="${not empty fare}"><c:out value="${fare.getFare_dist4_mile_2() }"/></c:if>" />
						</div>
						<div class="col-md-3">
					  	<form:select path="fare_dist4_type" class="form-control">
							<form:option value="flat rate">Flat rate</form:option>
						    <form:option value="fare per mile">Fare per mile</form:option>
						</form:select></div>
					  	<div class="col-md-3">£ 
					  	<input name = "fare_dist4_charge" type="text" data-bv-notempty data-bv-notempty-message="The gender is required"  class="form-control charge" value="<c:if test="${not empty fare}"><c:out value="${fare.getFare_dist4_charge()}"/></c:if>" placeholder="0.00"/>
					  	</div>
					  	
					</div-->
					</div>
					<div class="row col-lg-12 col-sm-12 col-xs-12">
						<div class="col-md-2 ">
					  	<button type="button" class="btn btn-info btn-sm addMore" data-toggle="#fare_structure">
                		Add more
            			</button>
            			<input type="text" name="fare_dist_ind" value = "1" style="display:none;" >
            			<input type="text" name="added_fare_dist" value = "added_fare_dist" style="display:none;" >
            			</div>
						<div class="col-md-2" style="margin-left:-20px;">
            			<button type="button" class="btn btn-info btn-sm addMore" data-toggle="#fare_structure_del">
                		Delete
            			</button>
            			</div>
						<div class="col-md-8 "></div>
					</div>
               		</fieldset>
               		
               		
               		<fieldset id = "zonal_charge" style="display:none; border: 0px solid #CCC;" >
               		Dear Operator, You have selected zonal fare. Please note that we will be unable to estimate fares for your passengers.
               		</fieldset>
               		
               		
				
            		<!-- div class="col-lg-12 col-md-12" id= "tab-fare-structure-form-other" -->
            		<fieldset>
                	<legend>Other:</legend>
                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6"> <!--1st column-->
						<div class="form-group col-lg-12 col-sm-12 col-xs-12 col-md-12">
                        <label for="tab-fare-structure-dist-field-9" class="col-sm-6 col-xs-6 col-md-6">Return fare multiplier:</label>
                        <div class="col-xs-4">
                        <input type= "text" class="form-control" id="tab-fare-structure-dist-field-9" name = "return_fare_multiplier" placeholder="2.0" value = "<c:if test="${not empty fare}"><c:out value="${fare.getReturn_fare_multiplier()}"/></c:if>" />
                        </div>
                        </div>
                        <div class="form-group col-lg-12 col-sm-12 col-xs-12 col-md-12">
                        <label for="tab-fare-structure-dist-field-10" class="col-sm-6 col-xs-6 col-md-6">Discount % for over 60s:</label>
                        <div class="col-xs-4">
                        <input type= "text" class="form-control discount" id="tab-fare-structure-dist-field-10" name = "discount_for_over60" placeholder="0" value = "<c:if test="${not empty fare}"><c:out value="${fare.getDiscount_for_over60()}"/></c:if>" />%
                        </div>
                        </div>
                        <div class="form-group col-lg-12 col-sm-12 col-xs-12 col-md-12">
                        <label for="tab-fare-structure-dist-field-11" class="col-sm-6 col-xs-6 col-md-6">Discount % for under 16s:</label>
                        <div class="col-xs-4">
                        <input type= "text" class="form-control discount" id="tab-fare-structure-dist-field-11" name = "discount_for_under16" placeholder="0" value = "<c:if test="${not empty fare}"><c:out value="${fare.getDiscount_for_under16()}"/></c:if>" />%
                        </div>
                        </div>
                        <div class="form-group col-lg-12 col-sm-12 col-xs-12 col-md-12">
                        <label for="tab-fare-structure-dist-field-14" class="col-sm-6 col-xs-6 col-md-6">Discount % for other concessionary passengers:</label>
                        <div class="col-xs-4">
                        <input type= "text" class="form-control discount" id="tab-fare-structure-dist-field-14" name = "discount_for_other_concessionary" placeholder="0" value = "<c:if test="${not empty fare}"><c:out value="${fare.getDiscount_for_other_concessionary()}"/></c:if>" />%
                        </div>
                        </div>
                     </div><!--1st column-->
                     <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6"> <!--2nd column-->
                      	<div class="form-group col-lg-12 col-sm-12 col-xs-12 col-md-12">
                        <label for="tab-fare-structure-dist-field-12" class="col-sm-12 col-xs-12 col-md-12">
                        <input type="checkbox" name="fare_structure_checkbox_escort" id="tab-fare-structure-dist-field-12" value="true" <c:if test="${not empty fare and fare.isFare_structure_checkbox_escort()}"><c:out value="checked"/></c:if>/> Please tick if escorts are charged a fare?</label>
                        </div>
                        <div class="form-group col-lg-12 col-sm-12 col-xs-12 col-md-12">
                        <label for="tab-fare-structure-dist-field-13" class="col-sm-12 col-xs-12 col-md-12">
                        <input type="checkbox" name="fare_structure_checkbox_charge_for_dead_mileage" id="tab-fare-structure-dist-field-13" value="true"  <c:if test="${not empty fare and fare.isFare_structure_checkbox_charge_for_dead_mileage()}"><c:out value="checked"/></c:if> /> Please tick if this service charges for dead mileage?</label>
                        </div>
                     </div><!--2nd column-->
                     </fieldset>
                     <!--  /div-->
					</div>
					<div class="input-group col-md-offset-9 col-sm-offset-9 col-xs-offset-9">
            		<button type="button" class="btn btn-info" id="save_fare">Save this section</button>
            		</div><!--save clear button-->
				</div> <!--panel body-->
				</div> <!--collapse3-->
				</div> <!--third -->
				<!-- surcharge -->
				<div class="panel panel-default" id="tab-surcharge-structure" style="display:none;"><div class="panel-heading"><h4 class="panel-title">
        			<a data-toggle="collapse" data-parent="#accordion" href="#collapseFive">
          			Surcharge Structure
      				</a></h4></div>
					<div id="collapseFive" class="panel-collapse collapse" style="display:none;">
  					<div class="panel-body">
   					<p><h2> Surcharges</h2>
   					<span>Please enter your penalty surcharges for operating outside of your usual boundaries and operating time, and for transporting passengers who are not normally eligible.</span>
   					</p>
					<div class="col-lg-12 col-md-12">
            		<fieldset>
					<legend>Distance:</legend>
					<div id="surcharge_table">
						<div class="row col-lg-12 col-sm-12 col-xs-12">
							<div class="col-md-3 "><label>From</label></div>
							<div class="col-md-3"><label>To</label></div>
							<div class="col-md-3"><label>Type</label></div>
							<div class="col-md-3"><label>Surcharge</label></div>
						</div>
						<div class="row col-lg-12 col-sm-12 col-xs-12">
							<div class="col-md-3 "><label>0 mile</label></div>
							<div class="col-md-3">
							<input name="surcharge_dist1_mile" type="text" class="form-control" placeholder="1.0" value="<c:if test="${not empty surcharge}"><c:out value="${surcharge.getSurcharge_dist1_mile()}"/></c:if>" />
					 		</div>
							<div class="col-md-3">
							<form:select path="surcharge_dist1_type" class="form-control">
								<form:option value="flat rate">Flat rate</form:option>
								<form:option value="fare per mile">Fare per mile</form:option>
							</form:select></div>
							<div class="col-md-3">£ 
							<input name="surcharge_dist1_charge" type="text" class="form-control charge" placeholder="0.00" value="<c:if test="${not empty surcharge}"><c:out value="${surcharge.getSurcharge_dist1_charge()}"/></c:if>" />
					 		</div>
						</div>
						<div class="row col-lg-12 col-sm-12 col-xs-12">
							<div class="col-md-3 ">
							<input name="surcharge_dist2_1" type="text" class="form-control" placeholder="1.0" value="<c:if test="${not empty surcharge}"><c:out value="${surcharge.getSurcharge_dist2_1()}"/></c:if>" />
					 		</div>
							<div class="col-md-3">
							<input name="surcharge_dist2_2" type="text" class="form-control" placeholder="2.0" value="<c:if test="${not empty surcharge}"><c:out value="${surcharge.getSurcharge_dist2_2()}"/></c:if>" />
					 		</div>
							<div class="col-md-3">
							<form:select path="surcharge_dist2_type" class="form-control">
								<form:option value="flat rate">Flat rate</form:option>
								<form:option value="fare per mile">Fare per mile</form:option>
							</form:select>
							</div>
							<div class="col-md-3">£ 
							<input name="surcharge_dist2_charge" type="text" class="form-control charge" placeholder="0.00" value="<c:if test="${not empty surcharge}"><c:out value="${surcharge.getSurcharge_dist2_charge()}"/></c:if>" />
					 		</div>
						</div>
						<div id="surcharge_distance_buffer_1" class="row col-lg-12 col-sm-12 col-xs-12" >
							<div class="col-md-3 ">
								<input name="surcharge_dist3_mile_1" type="text" class="form-control" placeholder="2.0" value="<c:if test="${not empty surcharge}"><c:out value="${surcharge.getSurcharge_dist3_mile_1()}"/></c:if>" />
					 		  	</div>
							<div class="col-md-3">
							<input name="surcharge_dist3_mile_2" type="text" class="form-control" placeholder="3.0" value="<c:if test="${not empty surcharge}"><c:out value="${surcharge.getSurcharge_dist3_mile_2()}"/></c:if>" />
					 		</div>
							<div class="col-md-3">
							<form:select path="surcharge_dist3_type" class="form-control">
								<form:option value="flat rate">Flat rate</form:option>
								<form:option value="fare per mile">Fare per mile</form:option>
							</form:select></div>
							<div class="col-md-3">£ 
							<input name="surcharge_dist3_charge" type="text" class="form-control charge" placeholder="0.00" value="<c:if test="${not empty surcharge}"><c:out value="${surcharge.getSurcharge_dist3_charge()}"/></c:if>" />
					 		</div>
						</div>
						<div id="surcharge_distance_buffer_2" class="row col-lg-12 col-sm-12 col-xs-12">
							<div class="col-md-3 ">
							<input name="surcharge_dist4_mile_1" type="text" class="form-control" placeholder="2.0" value="<c:if test="${not empty surcharge}"><c:out value="${surcharge.getSurcharge_dist4_mile_1()}"/></c:if>" />
					 		</div>
							<div class="col-md-3">
							<input name="surcharge_dist4_mile_2" type="text" class="form-control" placeholder="2.0" value="<c:if test="${not empty surcharge}"><c:out value="${surcharge.getSurcharge_dist4_mile_2()}"/></c:if>" />
					 		</div>
							<div class="col-md-3">
								<form:select path="surcharge_dist4_type" class="form-control">
								    <form:option value="flat rate">Flat rate</form:option>
								    <form:option value="fare per mile">Fare per mile</form:option>
								</form:select></div>
							<div class="col-md-3">£ 
							<input name="surcharge_dist4_charge" type="text" class="form-control charge" placeholder="0.00" value="<c:if test="${not empty surcharge}"><c:out value="${surcharge.getSurcharge_dist4_charge()}"/></c:if>" />
					 		</div>
						</div>
						</div>
						<div class="row col-lg-12 col-sm-12 col-xs-12">
						<div class="col-md-2 ">
						<button type="button" class="btn btn-info btn-sm addMore" data-toggle="#surcharge_structure">
                		Add more
            			</button>
            			</div>
						<div class="col-md-10 "></div>
						</div>
                		</fieldset>
           			</div>
					<div class="col-lg-12 col-md-12">
            		<fieldset>
            		<legend>Passenger age group:</legend>
                    	<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6"> <!--1st column-->
                    	<div class="form-group col-lg-12 col-sm-12 col-xs-12 col-md-12" id="tab-surcharge-structure-pt-field-1-group">
	                     	<label for="tab-surcharge-structure-pt-field-1" class="col-sm-6 col-xs-6 col-md-6"> 
	                        <input type="checkbox" name="surcharge_checkbox_under16" id="tab-surcharge-structure-pt-field-1" value="true" <c:if test="${not empty surcharge and surcharge.isSurcharge_checkbox_under16()}"><c:out value="checked"/></c:if>/> 
	                        Under 16 years of age:
	                    	</label>
                    	<div class="col-xs-4">
                      	£<input type="text" class="form-control charge" id="tab-surcharge-structure-pt-field-1-input" name="surcharge_under_16_num" placeholder="0.00" value="<c:if test="${not empty surcharge and surcharge.isSurcharge_checkbox_under16()}"><c:out value="${surcharge.getSurcharge_under_16_num()}"/></c:if>" />
                     	</div>
                    	</div>
                    	<div class="form-group col-lg-12 col-sm-12 col-xs-12 col-md-12" id="tab-surcharge-structure-pt-field-2-group">
	                     	<label for="tab-surcharge-structure-pt-field-2" class="col-sm-6 col-xs-6 col-md-6"> 
	                        <input type="checkbox" name="surcharge_checkbox_17_to_21" id="tab-surcharge-structure-pt-field-2" value="true"  <c:if test="${not empty surcharge and surcharge.isSurcharge_checkbox_17_to_21()}"><c:out value="checked"/></c:if>/> 17 to 21 years of age:
	                    	</label>
	                     	<div class="col-xs-4">
	                        £<input type="text" class="form-control charge" id="tab-surcharge-structure-pt-field-2-input" name="surcharge_17_21_years_old_num" placeholder="0.00" value="<c:if test="${not empty surcharge and surcharge.isSurcharge_checkbox_17_to_21()}"><c:out value="${surcharge.getSurcharge_17_21_years_old_num()}"/></c:if>" />
	                  		</div>
                    	</div>
                    	<div class="form-group col-lg-12 col-sm-12 col-xs-12 col-md-12" id="tab-surcharge-structure-pt-field-3-group">
	                      	<label for="tab-surcharge-structure-pt-field-3" class="col-sm-6 col-xs-6 col-md-6">
	                      	<input type="checkbox" name="surcharge_checkbox_22_to_54" id="tab-surcharge-structure-pt-field-3" value="true" <c:if test="${not empty surcharge and surcharge.isSurcharge_checkbox_22_to_54()}"><c:out value="checked"/></c:if>/> 22 to 54 years of age:
	                  		</label>
	                      	<div class="col-xs-4">
	                        £<input type="text" class="form-control charge" id="tab-surcharge-structure-pt-field-3-input" name="surcharge_22_54_years_old_num" placeholder="0.00"   value = "<c:if test="${not empty surcharge and surcharge.isSurcharge_checkbox_22_to_54()}"><c:out value="${surcharge.getSurcharge_22_54_years_old_num()}"/></c:if>" />
	                    	</div>
                    	</div>
                     	</div><!--1st column-->
                    	<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6"> <!--2nd column-->
                    	<div class="form-group col-lg-12 col-sm-12 col-xs-12 col-md-12" id="tab-surcharge-structure-pt-field-4-group">
	                      	<label for="tab-surcharge-structure-pt-field-4" class="col-sm-6 col-xs-6 col-md-6">
	                      	<input type="checkbox" name="surcharge_checkbox_55_to_59" id="tab-surcharge-structure-pt-field-4" value="true" <c:if test="${not empty surcharge and surcharge.isSurcharge_checkbox_55_to_59()}"><c:out value="checked"/></c:if>/> 55 to 59 years of age:
	                    	</label>
	                      	<div class="col-xs-4">
	                        £<input type="text" class="form-control charge" id="tab-surcharge-structure-pt-field-4-input" name="surcharge_55_59_years_old_num" placeholder="0.00"   value="<c:if test="${not empty surcharge and surcharge.isSurcharge_checkbox_55_to_59()}"><c:out value="${surcharge.getSurcharge_55_59_years_old_num()}"/></c:if>" />
	                    	</div>
                    	</div>
                    	<div class="form-group col-lg-12 col-sm-12 col-xs-12 col-md-12" id="tab-surcharge-structure-pt-field-5-group">
	                      	<label for="tab-surcharge-structure-pt-field-5" class="col-sm-6 col-xs-6 col-md-6">
	                      	<input type="checkbox" name="surcharge_checkbox_over60" id="tab-surcharge-structure-pt-field-5" value="true"  <c:if test="${not empty surcharge and surcharge.isSurcharge_checkbox_over60()}"><c:out value="checked"/></c:if>  /> 60 years or above:
	                  		</label>
	                      	<div class="col-xs-4">
	                        £<input type="text" class="form-control charge" id="tab-surcharge-structure-pt-field-5-input" name="surcharge_over_60_years_old_num" placeholder="0.00"  value="<c:if test="${not empty surcharge and surcharge.isSurcharge_checkbox_over60()}"><c:out value="${surcharge.getSurcharge_over_60_years_old_num()}"/></c:if>"  />
	                    	</div>
                    	</div>
                    	</div> <!--2nd column-->
                		</fieldset>
            		</div>
        			<div class="col-lg-12 col-md-12">
            		<fieldset>
            		<legend>Passenger mobility status:</legend>
					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6"> <!--1st column-->
                    	<div class="form-group col-lg-12 col-sm-12 col-xs-12 col-md-12" id="tab-surcharge-structure-pt-field-6-group">
	                      	<label for="tab-surcharge-structure-pt-field-6" class="col-sm-6 col-xs-6 col-md-6">
	                      	<input type="checkbox" name="surcharge_checkbox_able_bodied" id="tab-surcharge-structure-pt-field-6" value="true" <c:if test="${not empty surcharge and surcharge.isSurcharge_checkbox_able_bodied()}"><c:out value="checked"/></c:if> /> Able bodied:
	                    	</label>
	                      	<div class="col-xs-4">
	                        £<input type="text" class="form-control charge" id="tab-surcharge-structure-pt-field-6-input" name="surcharge_able_bodied_num" placeholder="0.00"  value="<c:if test="${not empty surcharge and surcharge.isSurcharge_checkbox_able_bodied()}"><c:out value="${surcharge.getSurcharge_over_60_years_old_num()}"/></c:if>"  />
	                      	</div>
                    	</div>
                    	<div class="form-group col-lg-12 col-sm-12 col-xs-12 col-md-12" id="tab-surcharge-structure-pt-field-7-group">
	                      	<label for="tab-surcharge-structure-pt-field-7" class="col-sm-6 col-xs-6 col-md-6">
	                      	<input type="checkbox" name="surcharge_checkbox_mobility_prevents_PT" id="tab-surcharge-structure-pt-field-7" value="true" <c:if test="${not empty surcharge and surcharge.isSurcharge_checkbox_mobility_prevents_PT()}"><c:out value="checked"/></c:if> /> Mobility prevents use of regular Public Transport:
	                    	</label>
	                      	<div class="col-xs-4">
	                        £<input type="text" class="form-control charge" id="tab-surcharge-structure-pt-field-7-input" name="surcharge_mobility_prevents_PT_num" value = "<c:if test="${not empty surcharge and surcharge.isSurcharge_checkbox_mobility_prevents_PT()}"><c:out value="${surcharge.getSurcharge_mobility_prevents_PT_num()}"/></c:if>"  placeholder="0.00"/>
	                    	</div>
                    	</div>
					</div><!--1st column-->
                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6"> <!--2nd column-->
                    	<div class="form-group col-lg-12 col-sm-12 col-xs-12 col-md-12" id="tab-surcharge-structure-pt-field-8-group">
	                      	<label for="tab-surcharge-structure-pt-field-8" class="col-sm-6 col-xs-6 col-md-6">
	                      	<input type="checkbox" name="surcharge_checkbox_diable_wheelchair_user" id="tab-surcharge-structure-pt-field-8" value="true" <c:if test="${not empty surcharge and surcharge.isSurcharge_checkbox_diable_wheelchair_user()}"><c:out value="checked"/></c:if>  /> Disabled wheelchair user:
	                    	</label>
	                      	<div class="col-xs-4">
	                        £<input type="text" class="form-control charge" id="tab-surcharge-structure-pt-field-8-input" name="surcharge_disable_wheelchair_user_num" placeholder="0.00"  value="<c:if test="${not empty surcharge and surcharge.isSurcharge_checkbox_diable_wheelchair_user()}"><c:out value="${surcharge.getSurcharge_disable_wheelchair_user_num()}"/></c:if>"  />
	                    	</div>
                    	</div>
                    	<div class="form-group col-lg-12 col-sm-12 col-xs-12 col-md-12" id="tab-surcharge-structure-pt-field-9-group">
	                      	<label for="tab-surcharge-structure-pt-field-9" class="col-sm-6 col-xs-6 col-md-6">
	                      	<input type="checkbox" name="surcharge_checkbox_disable_other" id="tab-surcharge-structure-pt-field-9" value="true" <c:if test="${not empty surcharge and surcharge.isSurcharge_checkbox_disable_other()}"><c:out value="checked"/></c:if> /> Disabled other:
	                       	</label>
	                      	<div class="col-xs-4">
	                        £<input type="text" class="form-control charge" id="tab-surcharge-structure-pt-field-9-input" name="surcharge_disable_other_num" placeholder="0.00" value="<c:if test="${not empty surcharge and surcharge.isSurcharge_checkbox_disable_other()}"><c:out value="${surcharge.getSurcharge_disable_other_num()}"/></c:if>"  />
	                    	</div>
                    	</div>
                    </div> <!--2nd column-->
                	</fieldset>
            		</div>
					<div class="col-lg-12 col-md-12">
            		<fieldset>
            		<legend>Passenger journey purpose:</legend>
                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6"> <!--1st column-->
                    	<div class="form-group col-lg-12 col-sm-12 col-xs-12 col-md-12" id="tab-surcharge-structure-pt-field-10-group">
                        	<label for="tab-surcharge-structure-pt-field-10" class="col-sm-6 col-xs-6 col-md-6">
                      		<input type="checkbox" name="surcharge_checkbox_health_appointment" id="tab-surcharge-structure-pt-field-10" value="true" <c:if test="${not empty surcharge and surcharge.isSurcharge_checkbox_health_appointment()}"><c:out value="checked"/></c:if> /> Health Appointment:
                  			</label>
                      		<div class="col-xs-4">
                        	£<input type="text" class="form-control charge" id="tab-surcharge-structure-pt-field-10-input" name="surcharge_health_appointment_num" placeholder="0.00" value="<c:if test="${not empty surcharge and surcharge.isSurcharge_checkbox_health_appointment()}"><c:out value="${surcharge.getSurcharge_health_appointment_num()}"/></c:if>" />
                    		</div>
                    	</div>
                    	<div class="form-group col-lg-12 col-sm-12 col-xs-12 col-md-12" id="tab-surcharge-structure-pt-field-11-group">
	                      	<label for="tab-surcharge-structure-pt-field-11" class="col-sm-6 col-xs-6 col-md-6">
	                      	<input type="checkbox" name="surcharge_checkbox_social_care" id="tab-surcharge-structure-pt-field-11" value="true" <c:if test="${not empty surcharge and surcharge.isSurcharge_checkbox_social_care()}"><c:out value="checked"/></c:if> /> Social Care:
	                  		</label>
	                      	<div class="col-xs-4">
	                        £<input type="text" class="form-control charge" id="tab-surcharge-structure-pt-field-11-input" name="surcharge_social_care_num" placeholder="0.00" value="<c:if test="${not empty surcharge and surcharge.isSurcharge_checkbox_social_care()}"><c:out value="${surcharge.getSurcharge_social_care_num()}"/></c:if>"  />
	                    	</div>
                    	</div>
                   		<div class="form-group col-lg-12 col-sm-12 col-xs-12 col-md-12" id="tab-surcharge-structure-pt-field-12-group">
	                      	<label for="tab-surcharge-structure-pt-field-12" class="col-sm-6 col-xs-6 col-md-6">
	                      	<input type="checkbox" name="surcharge_checkbox_shopping" id="tab-surcharge-structure-pt-field-12" value="true"  <c:if test="${not empty surcharge and surcharge.isSurcharge_checkbox_shopping()}"><c:out value="checked"/></c:if> /> Shopping:
	                    	</label>
	                      	<div class="col-xs-4">
	                        £<input type="text" class="form-control charge" id="tab-surcharge-structure-pt-field-12-input" name="surcharge_shopping_num" placeholder="0.00" value="<c:if test="${not empty surcharge and surcharge.isSurcharge_checkbox_shopping()}"><c:out value="${surcharge.getSurcharge_shopping_num()}"/></c:if>" />
	                    	</div>
                    	</div>
                    	<div class="form-group col-lg-12 col-sm-12 col-xs-12 col-md-12" id="tab-surcharge-structure-pt-field-13-group">
	                      	<label for="tab-surcharge-structure-pt-field-13" class="col-sm-6 col-xs-6 col-md-6">
	                      	<input type="checkbox" name="surcharge_checkbox_leisure_visiting_friends" id="tab-surcharge-structure-pt-field-13" value="true" <c:if test="${not empty surcharge and surcharge.isSurcharge_checkbox_leisure_visiting_friends()}"><c:out value="checked"/></c:if>/> Leisure/Visiting Friends:
	                    	</label>
	                      	<div class="col-xs-4">
	                        £<input type="text" class="form-control charge" id="tab-surcharge-structure-pt-field-13-input" name="surcharge_leisure_visiting_friends_num" placeholder="0.00" value = "<c:if test="${not empty surcharge and surcharge.isSurcharge_checkbox_leisure_visiting_friends()}"><c:out value="${surcharge.getSurcharge_leisure_visiting_friends_num()}"/></c:if>" />
	                    	</div>
                    	</div>
					</div><!--1st column-->
                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6"> <!--2nd column-->
                  		<div class="form-group col-lg-12 col-sm-12 col-xs-12 col-md-12" id="tab-surcharge-structure-pt-field-14-group">
	                      	<label for="tab-surcharge-structure-pt-field-14" class="col-sm-6 col-xs-6 col-md-6">
	                      	<input type="checkbox" name="surcharge_checkbox_school_education" id="tab-surcharge-structure-pt-field-14" value="true" <c:if test="${not empty surcharge and surcharge.isSurcharge_checkbox_school_education()}"><c:out value="checked"/></c:if>/> School/Education:
	                    	</label>
	                      	<div class="col-xs-4">
	                        £<input type="text" class="form-control charge" id="tab-surcharge-structure-pt-field-14-input" name="surcharge_school_education_num" placeholder="0.00" value = "<c:if test="${not empty surcharge and surcharge.isSurcharge_checkbox_school_education()}"><c:out value="${surcharge.getSurcharge_school_education_num()}"/></c:if>" />
	                    	</div>
                    	</div>
                    	<div class="form-group col-lg-12 col-sm-12 col-xs-12 col-md-12" id="tab-surcharge-structure-pt-field-15-group">
	                      	<label for="tab-surcharge-structure-pt-field-15" class="col-sm-6 col-xs-6 col-md-6">
	                      	<input type="checkbox" name="surcharge_checkbox_work_commuting" id="tab-surcharge-structure-pt-field-15" value="true" <c:if test="${not empty surcharge and surcharge.isSurcharge_checkbox_work_commuting()}"><c:out value="checked"/></c:if>/> Work/Commuting:
	                    	</label>
	                      	<div class="col-xs-4">
	                        £<input type="text" class="form-control charge" id="tab-surcharge-structure-pt-field-15-input" name="surcharge_work_commuting_num" placeholder="0.00" value="<c:if test="${not empty surcharge and surcharge.isSurcharge_checkbox_work_commuting()}"><c:out value="${surcharge.getSurcharge_work_commuting_num()}"/></c:if>" />
	                    	</div>
                    	</div>
                    	<div class="form-group col-lg-12 col-sm-12 col-xs-12 col-md-12" id="tab-surcharge-structure-pt-field-16-group">
	                      	<label for="tab-surcharge-structure-pt-field-16" class="col-sm-6 col-xs-6 col-md-6">
	                      	<input type="checkbox" name="surcharge_checkbox_other_purpose" id="tab-surcharge-structure-pt-field-16" value="true" <c:if test="${not empty surcharge and surcharge.isSurcharge_checkbox_other_purpose()}"><c:out value="checked"/></c:if>/> Other:
	                    	</label>
	                      	<div class="col-xs-4">
	                        £<input type="text" class="form-control charge" id="tab-surcharge-structure-pt-field-16-input" name="surcharge_other_purpose_num" placeholder="0.00" value="<c:if test="${not empty surcharge and surcharge.isSurcharge_checkbox_other_purpose()}"><c:out value="${surcharge.getSurcharge_school_education_num()}"/></c:if>" />
	                    	</div>
                    	</div>
                    </div> <!--2nd column-->
                	</fieldset>
            		</div>
					<div class="col-lg-12 col-md-12">
            		<fieldset>
            		<legend>Time:</legend>
                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6"> <!--1st column-->
                    	<p class="col-xs-offset-2 col-sm-offset-2 col-md-offset-2 col-lg-offset-2"><strong>Start</strong></p>
                    	<div class="form-group col-lg-12 col-sm-12 col-xs-12 col-md-12" id="tab-surcharge-structure-time-field-1-group">
	                    	<label for="tab-surcharge-structure-time-field-1" class="col-sm-6 col-xs-6 col-md-6"> 
	                        <input type="checkbox" name="surcharge_checkbox_start_05_hours_earlier" id="tab-surcharge-structure-time-field-1" value="true" <c:if test="${not empty surcharge and surcharge.isSurcharge_checkbox_start_05_hours_earlier()}"><c:out value="checked"/></c:if>  /> Start 0.5 hours earlier:
	                     	</label>
	                    	<div class="col-xs-4">
	                        £<input type="text" class="form-control charge" id="tab-surcharge-structure-time-field-1-input" name="surcharge_start_05_hours_earlier"  placeholder="0.00" value="<c:if test="${not empty surcharge and surcharge.isSurcharge_checkbox_start_05_hours_earlier()}"><c:out value="${surcharge.getSurcharge_start_05_hours_earlier()}"/></c:if>" />
	                    	</div>
                     	</div>
                    	<div class="form-group col-lg-12 col-sm-12 col-xs-12 col-md-12" id="tab-surcharge-structure-time-field-2-group">
	                      	<label for="tab-surcharge-structure-time-field-2" class="col-sm-6 col-xs-6 col-md-6">
	                      	<input type="checkbox" name="surcharge_checkbox_start_1_hour_earlier" id="tab-surcharge-structure-time-field-2" value="true" <c:if test="${not empty surcharge and surcharge.isSurcharge_checkbox_start_1_hour_earlier()}"><c:out value="checked"/></c:if>  /> Start 1 hour earlier:
	                    	</label>
	                      	<div class="col-xs-4">
	                        £<input type="text" class="form-control charge" id="tab-surcharge-structure-time-field-2-input" name="surcharge_start_1_hour_earlier"  placeholder="0.00" value="<c:if test="${not empty surcharge and surcharge.isSurcharge_checkbox_start_1_hour_earlier()}"><c:out value="${surcharge.getSurcharge_start_1_hour_earlier()}"/></c:if>"/>
	                    	</div>
                    	</div>
                    	<div class="form-group col-lg-12 col-sm-12 col-xs-12 col-md-12" id="tab-surcharge-structure-time-field-3-group">
	                      	<label for="tab-surcharge-structure-time-field-3" class="col-sm-6 col-xs-6 col-md-6">
	                      	<input type="checkbox" name="surcharge_checkbox_start_15_hours_earlier" id="tab-surcharge-structure-time-field-3" value="true" <c:if test="${not empty surcharge and surcharge.isSurcharge_checkbox_start_15_hours_earlier()}"><c:out value="checked"/></c:if> /> Start 1.5 hours earlier:
	                    	</label>
	                      	<div class="col-xs-4">
	                        £<input type="text" class="form-control charge" id="tab-surcharge-structure-time-field-3-input" name="surcharge_start_15_hours_earlier"  placeholder="0.00"  value = "<c:if test="${not empty surcharge and surcharge.isSurcharge_checkbox_start_15_hours_earlier()}"><c:out value="${surcharge.getSurcharge_start_15_hours_earlier()}"/></c:if>" />
	                        </div>
                    	</div>
                    </div><!--1st column-->
                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6"> <!--2nd column-->
                    	<p class="col-xs-offset-2 col-sm-offset-2 col-md-offset-2 col-lg-offset-2"><strong>Finish</strong></p>
						<div class="form-group col-lg-12 col-sm-12 col-xs-12 col-md-12" id="tab-surcharge-structure-time-field-4-group">
	                     	<label for="tab-surcharge-structure-time-field-4" class="col-sm-6 col-xs-6 col-md-6">
	                     	<input type="checkbox" name="surcharge_checkbox_finish_05_hours_later" id="tab-surcharge-structure-time-field-4" value="true" <c:if test="${not empty surcharge and surcharge.isSurcharge_checkbox_finish_05_hours_later()}"><c:out value="checked"/></c:if>/> Finish 0.5 hours later:
	                    	</label>
	                        <div class="col-xs-4">
	                       	£<input type="text" class="form-control charge" id="tab-surcharge-structure-time-field-4-input" name="surcharge_finish_05_hours_later" name="surcharge_finish_05_hours_later" placeholder="0.00" value="<c:if test="${not empty surcharge and surcharge.isSurcharge_checkbox_finish_05_hours_later()}"><c:out value="${surcharge.getSurcharge_finish_05_hours_later()}"/></c:if>" />
	                        </div>
                    	</div>
                    	<div class="form-group col-lg-12 col-sm-12 col-xs-12 col-md-12" id="tab-surcharge-structure-time-field-5-group">
	                      	<label for="tab-surcharge-structure-time-field-5" class="col-sm-6 col-xs-6 col-md-6"> 
	                        <input type="checkbox" name="surcharge_checkbox_finish_1_hour_later" id="tab-surcharge-structure-time-field-5" value="true" <c:if test="${not empty surcharge and surcharge.isSurcharge_checkbox_finish_1_hour_later()}"><c:out value="checked"/></c:if>/> Finish 1 hour later:
	                        </label>
	                      	<div class="col-xs-4">
	                        £<input type="text" class="form-control charge" id="tab-surcharge-structure-time-field-5-input" name="surcharge_finish_1_hour_later" placeholder="0.00" value="<c:if test="${not empty surcharge and surcharge.isSurcharge_checkbox_finish_1_hour_later()}"><c:out value="${surcharge.getSurcharge_finish_1_hour_later()}"/></c:if>" />
	                    	</div>
                    	</div>
                    	<div class="form-group col-lg-12 col-sm-12 col-xs-12 col-md-12" id="tab-surcharge-structure-time-field-6-group">
	                      	<label for="tab-surcharge-structure-time-field-6" class="col-sm-6 col-xs-6 col-md-6"> 
	                        <input type="checkbox" name="surcharge_checkbox_finish_15_hours_later" id="tab-surcharge-structure-time-field-6" value="true"  <c:if test="${not empty surcharge and surcharge.isSurcharge_checkbox_finish_15_hours_later()}"><c:out value="checked"/></c:if>/> Finish 1.5 hours later:
	                        </label>
	                      	<div class="col-xs-4">
	                        £<input type="text" class="form-control charge" id="tab-surcharge-structure-time-field-6-input" name="surcharge_finish_15_hours_later" placeholder="0.00" value="<c:if test="${not empty surcharge and surcharge.isSurcharge_checkbox_finish_15_hours_later()}"><c:out value="${surcharge.getSurcharge_finish_15_hours_later()}"/></c:if>" />
	                    	</div>
                    	</div>
                    </div> <!--2nd column-->
                	</fieldset>
					</div>
            		<div class="input-group pull-right">
            		<button type="button" class="btn btn-info" id="save_surcharge">Save this section</button>
            		</div><!--save clear button-->
        		</div> <!--panel body-->
        		</div> <!--collapse3-->
				</div> <!--fourth -->
    		</div>
            <div class="input-group pull-left">
            <button type="submit" class="btn-lg btn-warning">Preview</button>
            <button type="submit" class="btn-lg btn-success">Submit</button>
            </div><!--save clear button-->
			</div>
	</form:form> <!-- end of the form -->
    </div>
</div><!-- /.container -->
</div>
</div>

<div id="footer">
 <p id="copyright">
    <span>&copy; Copyright 2013 - University of Aberdeen <br />Flexible Integrated Transport System Project<br />
        MacRobert Building, King's College, Aberdeen, AB24 5UA<br />
        t: +44 (0) 1224 274065 e: fits@abdn.ac.uk
    </span>
</p>  
	<div class="inline"><img id = "de" src="<c:url value="/resources/img/de.jpg" />" /></div>
	<div class="inline"> <img id = "abdn" src="<c:url value="/resources/img/abdn.gif" />" /></div>
</div>
</div>

<!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="<c:url value="/resources/js/bootstrap/bootstrap.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/bootstrapvalidator/js/bootstrapValidator.min.js" />"></script>

    <!-- Custom JavaScript-->
    <script src="<c:url value="/resources/js/jqueryui/1.10/jquery-ui-1.10.js"/>"></script>
    <script src="<c:url value="/resources/js/FITS.js" />"></script> 
    <script type="text/javascript">
	//fix the map showing problem
	window.onload = function() {
		
		// initialize google map
    	BlitzMap.setMap( 'myMap', true, 'mapData' );
    	BlitzMap.init();// initialize BlitzMap
    	$('#myMap').show();
    	
    	
    	$("#collapseTwo").removeClass("in");
    	$("#collapseOne").addClass("in");
    	$('a[href="#collapseOne"]').addClass("highlight");
    	
    	
    	
	};
	</script>
      
</body>
</html>