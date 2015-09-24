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
    <link href="<c:url value="/resources/css/bootstrap-datepicker.css" />" rel="stylesheet"  type="text/css" />	
    <link rel="stylesheet" href="<c:url value="/resources/bootstrapvalidator/css/bootstrapValidator.min.css" />"/>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet"  type="text/css" />
    <link href="<c:url value="/resources/css/form.css" />" rel="stylesheet"  type="text/css" />		
	<link href="<c:url value="/resources/css/screen.css" />" type="text/css" rel="stylesheet" media="screen,projection" />
	<link href="<c:url value="/resources/css/messages/messages.css" />" rel="stylesheet"  type="text/css" />
	<link href="<c:url value="/resources/css/jquery impromptu/jquery-impromptu.css" />" rel="stylesheet"  type="text/css" />
	<style>
		#dotrural{margin-bottom: 29px;}
		.help-block{color: #A94442;}
		.datepicker {padding: 0px;}
		label.tab-field {margin-left: 5px;margin-right: 5px;width:95px;}
		input.tab-field {margin-left: 5px;margin-right: 5px;width: 80px;}
		div.row-space{margin-bottom: 3px;}
		div.jqi {width: auto;}
		label.left-margin{margin-left: 15px;}
		.lead { margin-bottom: 0px; }
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
			 isGenInfoChanged = true;
			 console.log("checkbox not available is changed ");
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
	
	var isOperatingAreaChanged = false;
	var isEligChanged = false;
	
	/** detect change */
	
	function isChangedGenInfo(){
		var isGenInfoChanged = false;
		if($("input[name='service_name']").val() == $("input[name='_service_name']").val()){
			//console.log("service name is not changed ");
		}else{
			isGenInfoChanged = true;
			console.log("service name is changed ");
		}
		if($("textarea[name='service_description']").val() == $("textarea[name='_service_description']").val()){
			//console.log("service description is not changed ");
		}else{
			isGenInfoChanged = true;
			console.log("service description is changed ");
		}
		
		if($("input[name='type_of_permit']").val() == $("input[name='_type_of_permit']").val()){
			//console.log("type of permit is not changed ");
		}else{
			isGenInfoChanged = true;
			console.log("type of permit is changed ");
		}
		
		if($("textarea[name='how_to_book']").val() == $("textarea[name='_how_to_book']").val()){
			//console.log("how to book is not changed ");
		}else{
			isGenInfoChanged = true;
			console.log("how to book is changed ");
		}
		if($("input[name='cb_not_avail']").is(":checked") == $("input[name='_cb_not_avail']").is(":checked")) {
			//console.log("checkbox not available is not changed ");
		}else{
			isGenInfoChanged = true;
			console.log("checkbox not available is changed ");
		}
		if($("input[name='not_valid_from']").val() == $("input[name='_not_valid_from']").val() ){
			//console.log("not_valid_from is not changed ");
		}else{
			isGenInfoChanged = true;
			console.log("not_valid_from is changed ");
		}
		if($("input[name='not_valid_to']").val() == $("input[name='_not_valid_to']").val()){
			//console.log("not_valid_to is not changed ");
		}else{
			isGenInfoChanged = true;
			console.log("not_valid_to is changed ");
		}
		var len = $( "input[name='_service_not_avail_ind']" ).val();
		for (var d=2; d<=len; d++) {
			if($("input[name='not_valid_from_"+d+"']").val() == $("input[name='_not_valid_from_"+d+"']").val()){
				//console.log("fare_distance2 mile 1 is not changed ");
			}else{
				isGenInfoChanged = true;
				//$("input[name='fare_dist"+d+"_mile_1']").val($("input[name='_fare_dist"+d+"_mile_1']").val());
				console.log("not_valid_from_"+d+" is changed ");
			}
			if($("input[name='not_valid_to_"+d+"']").val() == $("input[name='_not_valid_to_"+d+"']").val()){
				//console.log("fare_distance2 mile 2 is not changed ");
			}else{
				isGenInfoChanged = true;
				//$("input[name='fare_dist"+d+"_mile_2']").val($("input[name='_fare_dist"+d+"_mile_2']").val());
				console.log("not_valid_to_"+d+" is changed ");
			}
		}
		
		if($( "input[name='service_not_avail_ind']" ).val() > $( "input[name='_service_not_avail_ind']" ).val()){
			isGenInfoChanged = true;
			console.log("more service not available period is added");
		}
		
		$( "input[name^='tab_operating_hours_field_monday']" ).each(function() {
			var name = $(this).attr("name");
			if( $(this).val() == $( "input[name='_"+name+"']" ).val() ){
				//console.log("monday time "+name+" is not changed ");
			}else{
				isGenInfoChanged = true;
				console.log("monday time "+name+" is changed ");
			}
		});
		$( "input[name^='tab_operating_hours_field_tuesday']" ).each(function() {
			var name = $(this).attr("name");
			if( $(this).val() == $( "input[name='_"+name+"']" ).val() ){
				//console.log("tuesday time "+name+" is not changed ");
			}else{
				isGenInfoChanged = true;
				console.log("tuesday time "+name+" is changed ");
			}
		});
		$( "input[name^='tab_operating_hours_field_wednesday']" ).each(function() {
			var name = $(this).attr("name");
			if( $(this).val() == $( "input[name='_"+name+"']" ).val() ){
				//console.log("wednesday time "+name+" is not changed ");
			}else{
				isGenInfoChanged = true;
				console.log("wednesday time "+name+" is changed ");
			}
		});
		$( "input[name^='tab_operating_hours_field_thursday']" ).each(function() {
			var name = $(this).attr("name");
			if( $(this).val() == $( "input[name='_"+name+"']" ).val() ){
				//console.log("thursday time "+name+" is not changed ");
			}else{
				isGenInfoChanged = true;
				console.log("thursday time "+name+" is changed ");
			}
		});
		$( "input[name^='tab_operating_hours_field_friday']" ).each(function() {
			var name = $(this).attr("name");
			if( $(this).val() == $( "input[name='_"+name+"']" ).val() ){
				//console.log("friday time "+name+" is not changed ");
			}else{
				isGenInfoChanged = true;
				console.log("friday time "+name+" is changed ");
			}
		});
		$( "input[name^='tab_operating_hours_field_saturday']" ).each(function() {
			var name = $(this).attr("name");
			if( $(this).val() == $( "input[name='_"+name+"']" ).val() ){
				//console.log("saturday time "+name+" is not changed ");
			}else{
				isGenInfoChanged = true;
				console.log("saturday time "+name+" is changed ");
			}
		});
		$( "input[name^='tab_operating_hours_field_sunday']" ).each(function() {
			var name = $(this).attr("name");
			if( $(this).val() == $( "input[name='_"+name+"']" ).val() ){
				//console.log("sunday time "+name+" is not changed ");
			}else{
				isGenInfoChanged = true;
				console.log("sunday time "+name+" is changed ");
			}
		});
		return isGenInfoChanged;
	}
	
	function restoreGenInfo(){
		
		if($("input[name='service_name']").val() == $("input[name='_service_name']").val()){
			//console.log("service name is not changed ");
		}else{
			$("input[name='service_name']").val($("input[name='_service_name']").val());
			console.log("service name is restored ");
		}
		if($("textarea[name='service_description']").val() == $("textarea[name='_service_description']").val()){
			//console.log("service description is not changed ");
		}else{
			$("textarea[name='service_description']").val($("textarea[name='_service_description']").val());
			console.log("service description is restored ");
		}
		
		if($("input[name='type_of_permit']").val() == $("input[name='_type_of_permit']").val()){
			//console.log("type of permit is not changed ");
		}else{
			$("input[name='type_of_permit']").val($("input[name='_type_of_permit']").val());
			console.log("type of permit is restored ");
		}
		
		if($("textarea[name='how_to_book']").val() == $("textarea[name='_how_to_book']").val()){
			//console.log("how to book is not changed ");
		}else{
			$("textarea[name='how_to_book']").val($("textarea[name='_how_to_book']").val());
			console.log("how to book is restored ");
		}
		if($("input[name='cb_not_avail']").is(":checked") == $("input[name='_cb_not_avail']").is(":checked")) {
			//console.log("checkbox not available is not changed ");
		}else{
			$("input[name='cb_not_avail']").prop(":checked", $("input[name='_cb_not_avail']").is(":checked"));
			console.log("checkbox not available is restored ");
		}
		if($("input[name='not_valid_from']").val() == $("input[name='_not_valid_from']").val() ){
			//console.log("not_valid_from is not changed ");
		}else{
			$("input[name='not_valid_from']").val($("input[name='_not_valid_from']").val());
			console.log("not_valid_from is restored ");
		}
		if($("input[name='not_valid_to']").val() == $("input[name='_not_valid_to']").val()){
			//console.log("not_valid_to is not changed ");
		}else{
			$("input[name='not_valid_to']").val($("input[name='_not_valid_to']").val());
			console.log("not_valid_to is restored ");
		}
		
		var len = $( "input[name='_service_not_avail_ind']" ).val();
		for (var d=2; d<=len; d++) {
			if($("input[name='not_valid_from_"+d+"']").val() == $("input[name='_not_valid_from_"+d+"']").val()){
				//console.log("fare_distance2 mile 1 is not changed ");
			}else{
				$("input[name='not_valid_from_"+d+"']").val($("input[name='_not_valid_from_"+d+"']").val());
				console.log("not_valid_from_"+d+" is restored ");
			}
			if($("input[name='not_valid_to_"+d+"']").val() == $("input[name='_not_valid_to_"+d+"']").val()){
				//console.log("fare_distance2 mile 2 is not changed ");
			}else{
				$("input[name='not_valid_to_"+d+"']").val($("input[name='_not_valid_to_"+d+"']").val());
				console.log("not_valid_to_"+d+" is restored ");
			}
		}
		
		if($( "input[name='service_not_avail_ind']" ).val() > $( "input[name='_service_not_avail_ind']" ).val()){
			isGenInfoChanged = true;
			console.log("more service not available period is added");
		}
		$( "input[name='service_not_avail_ind']" ).val($( "input[name='_service_not_avail_ind']" ).val()); 
		$( "div.addedMoreSNA" ).remove();
		
		$( "input[name^='tab_operating_hours_field_monday']" ).each(function() {
			var name = $(this).attr("name");
			if( $(this).val() == $( "input[name='_"+name+"']" ).val() ){
				
			}else{
				$(this).val($( "input[name='_"+name+"']" ).val()) 
				console.log("monday time "+name+" is restored ");
			}
		});
		$( "input[name^='tab_operating_hours_field_tuesday']" ).each(function() {
			var name = $(this).attr("name");
			if( $(this).val() == $( "input[name='_"+name+"']" ).val() ){
				
			}else{
				$(this).val($( "input[name='_"+name+"']" ).val());
				console.log("tuesday time "+name+" is restored ");
			}
		});
		$( "input[name^='tab_operating_hours_field_wednesday']" ).each(function() {
			var name = $(this).attr("name");
			if( $(this).val() == $( "input[name='_"+name+"']" ).val() ){
				
			}else{
				$(this).val($( "input[name='_"+name+"']" ).val());
				console.log("wednesday time "+name+" is restored ");
			}
		});
		$( "input[name^='tab_operating_hours_field_thursday']" ).each(function() {
			var name = $(this).attr("name");
			if( $(this).val() == $( "input[name='_"+name+"']" ).val() ){
				
			}else{
				$(this).val($( "input[name='_"+name+"']" ).val());
				console.log("thursday time "+name+" is restored ");
			}
		});
		$( "input[name^='tab_operating_hours_field_friday']" ).each(function() {
			var name = $(this).attr("name");
			if( $(this).val() == $( "input[name='_"+name+"']" ).val() ){
				
			}else{
				$(this).val($( "input[name='_"+name+"']" ).val());
				console.log("friday time "+name+" is restored ");
			}
		});
		$( "input[name^='tab_operating_hours_field_saturday']" ).each(function() {
			var name = $(this).attr("name");
			if( $(this).val() == $( "input[name='_"+name+"']" ).val() ){
				
			}else{
				$(this).val($( "input[name='_"+name+"']" ).val());
				console.log("saturday time "+name+" is restored ");
			}
		});
		$( "input[name^='tab_operating_hours_field_sunday']" ).each(function() {
			var name = $(this).attr("name");
			if( $(this).val() == $( "input[name='_"+name+"']" ).val() ){
				
			}else{
				$(this).val($( "input[name='_"+name+"']" ).val());
				console.log("sunday time "+name+" is restored ");
			}
		});
		
	}
	function isChangedVehInfo(){
		var isVehInfoChanged = false;
		if($("input[name='regNum']").val() == $("input[name='_regNum']").val() ){
			//console.log("registration num is not changed ");
		}else{
			isVehInfoChanged = true;
			console.log("registration num is changed ");
		}
		if($("select[name='vehicle_type']").val() == $("input[name='_vehicle_type']").val()){
			//console.log("vehicle type is not changed ");
		}else{
			isVehInfoChanged = true;
			console.log("vehicle type is changed ");
		}
		return isVehInfoChanged;
	}
	
	function restoreVehInfo(){
		if($("input[name='regNum']").val() == $("input[name='_regNum']").val() ){
			//console.log("registration num is not changed ");
		}else{
			$("input[name='regNum']").val($("input[name='_regNum']").val());
			console.log("registration num is restored ");
		}
		if($("select[name='vehicle_type']").val() == $("input[name='_vehicle_type']").val()){
			//console.log("vehicle type is not changed ");
		}else{
			$("select[name='vehicle_type']").val($("input[name='_vehicle_type']").val());
			console.log("vehicle type is restored ");
		}
		if($("select[name='vehicle_type']").val() == "other"){
			$('#other_vehicle').fadeIn('slow');
		}else{
			$('#other_vehicle').fadeOut('slow');
		}
	}
	
	function isOperatingAreaChanged(){
		return isOperatingAreaChanged;
	}
	
	function isChangedElig(){
		var isEligChanged = false;
		$("input[name^='eligible_checkbox']").each(function() {
			var name = $(this).attr("name");
			if($(this).is(':checked') == $( "input[name='_"+name+"']" ).is(':checked')){
				//console.log("elig checkbox "+name+" is not changed ");
			}else{
				isEligChanged = true;
				console.log("elig checkbox "+name+" is changed ");
			}
		});
		if($("select[name='other_elig_type']").val() == $("input[name='_other_elig_type']").val()){
			//console.log("other elig type is not changed ");
		}else if($("select[name='other_elig_type']").val() == 'none' && $("input[name='_other_elig_type']").val() == ''){
			//console.log("other elig type is not changed ");
		}else{
			isEligChanged = true;
			console.log("other elig type is changed ");
		}
		if($("input[name='tab_elig_radioBtns']:checked").val() == $("input[name='_tab_elig_radioBtns']").val()){
			//console.log("tab_elig_radioBtns is not changed ");
		}else{
			isEligChanged = true;
			console.log("tab_elig_radioBtns is changed ");
		}
		if($("textarea[name='explain_opening_up_elig']").val() == $("textarea[name='_explain_opening_up_elig']").val()){
			//console.log("explain_opening_up_elig is not changed ");
		}else{
			isEligChanged = true;
			console.log("explain_opening_up_elig is changed ");
		}
		return isEligChanged;
	}
	
	function restoreElig() {
		
		$("input[name^='eligible_checkbox']").each(function() {
			var name = $(this).attr("name");
			if($(this).is(':checked') == $( "input[name='_"+name+"']" ).is(':checked')){
				
			}else{
				$(this).prop('checked', $( "input[name='_"+name+"']" ).is(':checked'));
				console.log("elig checkbox "+name+" is restored ");
			}
		});
		if($("select[name='other_elig_type']").val() == $("input[name='_other_elig_type']").val()){
			//console.log("other elig type is not changed ");
		}else if($("select[name='other_elig_type']").val() == 'none' && $("input[name='_other_elig_type']").val() == ''){
			//console.log("other elig type is not changed ");
		}else{
			if($("input[name='_other_elig_type']").val() == ""){
				$("select[name='other_elig_type']").val("none");
			}else{
				$("select[name='other_elig_type']").val($("input[name='_other_elig_type']").val());
			}
			console.log("other elig type is restored ");
		}
		if($("select[name='other_elig_type']").val() == 'other'){
			$('input[name="other_elig"]').fadeIn('slow');
		}else{
			$('input[name="other_elig"]').fadeOut('slow');
		}		
		if($("input[name='tab_elig_radioBtns']").val() == $("input[name='_tab_elig_radioBtns']").val()){
			//console.log("tab_elig_radioBtns is not changed ");
		}else{
			$("input[name='tab_elig_radioBtns']").val([$("input[name='_tab_elig_radioBtns']").val()]);
			console.log("tab_elig_radioBtns is restored ");
		}
		if($("textarea[name='explain_opening_up_elig']").val() == $("textarea[name='_explain_opening_up_elig']").val()){
			//console.log("explain_opening_up_elig is not changed ");
		}else{
			$("textarea[name='explain_opening_up_elig']").val($("textarea[name='_explain_opening_up_elig']").val());
			console.log("explain_opening_up_elig is restored ");
		}
		if($("input[name='tab_elig_radioBtns']:checked").val() == "1"){
			$("#tab-elig-openning-up").fadeOut("slow");
		}else{
			$("#tab-elig-openning-up").fadeIn("slow");
		}
	}
	
	function isChangedFare(){
		var isFareChanged = false;
		if($("input[name='tab_fare_structure_radioBtns']:checked").val() == $("input[name='_tab_fare_structure_radioBtns']").val()){
			//console.log("tab_fare_structure_radioBtns is not changed ");
		}else{
			isFareChanged = true;
			console.log("tab_fare_structure_radioBtns is changed ");
		}
		if($("input[name='tab_fare_structure_how_charge_radioBtns']:checked").val() == $("input[name='_tab_fare_structure_how_charge_radioBtns']").val()){
			//console.log("tab_fare_structure_how_charge_radioBtns is not changed ");
		}else{
			isFareChanged = true;
			console.log("tab_fare_structure_how_charge_radioBtns is changed ");
		}
		if($("input[name='fare_distance1_mile']").val() == $("input[name='_fare_distance1_mile']").val()){
			//console.log("fare_distance1 mile is not changed ");
		}else{
			isFareChanged = true;
			console.log("fare_distance1 mile is changed ");
		}
		if($("input[name='_fare_dist1_type']").val() == ''){
			if($("select[name='fare_dist1_type']").val() == "flat rate"){
				//console.log("fare_distance1 type is not changed ");
			}else{
				isFareChanged = true;
				console.log("fare_distance1 type is changed ");
			}
		}else if($("select[name='fare_dist1_type']").val() ==$("input[name='_fare_dist1_type']").val()){
			//console.log("fare_distance1 type is not changed ");
		}else{
			isFareChanged = true;
			console.log("fare_distance1 type is changed ");
		}
		if($("input[name='fare_dist1_charge']").val() ==$("input[name='_fare_dist1_charge']").val()){
			//console.log("fare_distance1 charge is not changed ");
		}else{
			isFareChanged = true;
			console.log("fare_distance1 charge is changed ");
		}
		
		var len = $( "input[name='_fare_dist_ind']" ).val();
		for (var d=2; d<=len; d++) {
			if($("input[name='fare_dist"+d+"_mile_1']").val() == $("input[name='_fare_dist"+d+"_mile_1']").val()){
				//console.log("fare_distance2 mile 1 is not changed ");
			}else{
				isFareChanged = true;
				console.log("fare_distance"+d+" mile 1 is changed ");
			}
			if($("input[name='fare_dist"+d+"_mile_2']").val() == $("input[name='_fare_dist"+d+"_mile_2']").val()){
				//console.log("fare_distance2 mile 2 is not changed ");
			}else{
				isFareChanged = true;
				console.log("fare_distance"+d+" mile 2 is changed ");
			}
			if($("input[name='_fare_dist"+d+"_type']").val() == ''){
				if($("select[name='fare_dist"+d+"_type']").val() == "flat rate"){
					//console.log("fare_distance2 type is not changed ");
				}else{
					isFareChanged = true;
					console.log("fare_distance"+d+" type is changed ");
				}
			}else if($("select[name='fare_dist"+d+"_type']").val() == $("input[name='_fare_dist"+d+"_type']").val()){
				//console.log("fare_distance2 type is not changed ");
			}else{
				isFareChanged = true;
				console.log("fare_distance"+d+" type is changed ");
			}
			if($("input[name='fare_dist"+d+"_charge']").val() == $("input[name='_fare_dist"+d+"_charge']").val()){
				//console.log("fare_distance2 charge is not changed ");
			}else{
				isFareChanged = true;
				console.log("fare_distance"+d+" charge is changed ");
			}
		}
		
		if($( "input[name='fare_dist_ind']" ).val() > $( "input[name='_fare_dist_ind']" ).val()){
			isFareChanged = true;
			console.log("more distance band is added");
		}
		
		/*
		if($("input[name='fare_dist2_mile_1']").val() == $("input[name='_fare_dist2_mile_1']").val()){
			//console.log("fare_distance2 mile 1 is not changed ");
		}else{
			isFareChanged = true;
			console.log("fare_distance2 mile 1 is changed ");
		}
		if($("input[name='fare_dist2_mile_2']").val() == $("input[name='_fare_dist2_mile_2']").val()){
			//console.log("fare_distance2 mile 2 is not changed ");
		}else{
			isFareChanged = true;
			console.log("fare_distance2 mile 2 is changed ");
		}
		if($("input[name='_fare_dist2_type']").val() == ''){
			if($("select[name='fare_dist2_type']").val() == "flat rate"){
				//console.log("fare_distance2 type is not changed ");
			}else{
				isFareChanged = true;
				console.log("fare_distance2 type is changed ");
			}
		}else if($("select[name='fare_dist2_type']").val() == $("input[name='_fare_dist2_type']").val()){
			//console.log("fare_distance2 type is not changed ");
		}else{
			isFareChanged = true;
			console.log("fare_distance2 type is changed ");
		}
		if($("input[name='fare_dist2_charge']").val() == $("input[name='_fare_dist2_charge']").val()){
			//console.log("fare_distance2 charge is not changed ");
		}else{
			isFareChanged = true;
			console.log("fare_distance2 charge is changed ");
		}
		if($("input[name='fare_dist3_mile_1']").val() == $("input[name='_fare_dist3_mile_1']").val()){
			//console.log("fare_distance3 mile 1 is not changed ");
		}else{
			isFareChanged = true;
			console.log("fare_distance3 mile 1 is changed ");
		}
		if($("input[name='fare_dist3_mile_2']").val() == $("input[name='_fare_dist3_mile_2']").val()){
			//console.log("fare_distance3 mile 2 is not changed ");
		}else{
			isFareChanged = true;
			console.log("fare_distance3 mile 2 is changed ");
		}
		if($("input[name='_fare_dist3_type']").val() == ''){
			if($("select[name='fare_dist3_type']").val() == "flat rate"){
				//console.log("fare_distance3 type is not changed ");
			}else{
				isFareChanged = true;
				console.log("fare_distance3 type is changed ");
			}
		}else if($("select[name='fare_dist3_type']").val() == $("input[name='_fare_dist3_type']").val()){
			//console.log("fare_distance3 type is not changed ");
		}else{
			isFareChanged = true;
			console.log("fare_distance3 type is changed ");
		}
		if($("input[name='fare_dist3_charge']").val() == $("input[name='_fare_dist3_charge']").val()){
			//console.log("fare_distance3 charge is not changed ");
		}else{
			isFareChanged = true;
			console.log("fare_distance3 charge is changed ");
		}
		if($("input[name='fare_dist4_mile_1']").val() ==$("input[name='_fare_dist4_mile_1']").val()){
			//console.log("fare_distance4 mile 1 is not changed ");
		}else{
			isFareChanged = true;
			console.log("fare_distance4 mile 1 is changed ");
		}
		if($("input[name='fare_dist4_mile_2']").val() ==$("input[name='_fare_dist4_mile_2']").val()){
			//console.log("fare_distance4 mile 2 is not changed ");
		}else{
			isFareChanged = true;
			console.log("fare_distance4 mile 2 is changed ");
		}
		if($("input[name='_fare_dist4_type']").val() == ''){
			if($("select[name='fare_dist4_type']").val() == "flat rate"){
				//console.log("fare_distance4 type is not changed ");
			}else{
				isFareChanged = true;
				console.log("fare_distance4 type is changed ");
			}
		}else if($("select[name='fare_dist4_type']").val() == $("input[name='_fare_dist4_type']").val()){
			//console.log("fare_distance4 type is not changed ");
		}else{
			isFareChanged = true;
			console.log("fare_distance4 type is changed ");
		}
		if($("input[name='fare_dist4_charge']").val() == $("input[name='_fare_dist4_charge']").val()){
			//console.log("fare_distance4 charge is not changed ");
		}else{
			isFareChanged = true;
			console.log("fare_distance4 charge is changed ");
		}
		*/
		
		//console.log("return_fare_multiplier: " + $("input[name='return_fare_multiplier']").val());
		//console.log("_return_fare_multiplier: " + $("input[name='_return_fare_multiplier']").val());
		if($("input[name='return_fare_multiplier']").val() == $("input[name='_return_fare_multiplier']").val()){
			//console.log("return_fare_multiplier is not changed ");
		}else{
			isFareChanged = true;
			console.log("return_fare_multiplier is changed ");
		}
		if($("input[name='discount_for_over60']").val() == $("input[name='_discount_for_over60']").val()){
			//console.log("discount_for_over60 is not changed ");
		}else{
			isFareChanged = true;
			console.log("discount_for_over60 is changed ");
		}
		if($("input[name='discount_for_under16']").val() == $("input[name='_discount_for_under16']").val()){
			//console.log("discount_for_under16 is not changed ");
		}else{
			isFareChanged = true;
			console.log("discount_for_under16 is changed ");
		}
		if($("input[name='discount_for_other_concessionary']").val() == $("input[name='_discount_for_other_concessionary']").val()){
			//console.log("discount_for_other_concessionary is not changed ");
		}else{
			isFareChanged = true;
			console.log("discount_for_other_concessionary is changed ");
		}
		if($("input[name='fare_structure_checkbox_escort']").is(':checked') == $("input[name='_fare_structure_checkbox_escort']").is(':checked')){
			//console.log("fare_structure_checkbox_escort is not changed ");
		}else{
			isFareChanged = true;
			console.log("fare_structure_checkbox_escort is changed ");
		}
		if($("input[name='fare_structure_checkbox_charge_for_dead_mileage']").is(':checked') == $("input[name='_fare_structure_checkbox_charge_for_dead_mileage']").is(':checked')){
			//console.log("fare_structure_checkbox_charge_for_dead_mileage is not changed ");
		}else{
			isFareChanged = true;
			console.log("fare_structure_checkbox_charge_for_dead_mileage is changed ");
		}
		return isFareChanged;
	}
	
	
	function restoreFare(){
		
		if($("input[name='tab_fare_structure_radioBtns']:checked").val() == $("input[name='_tab_fare_structure_radioBtns']").val()){
			//console.log("tab_fare_structure_radioBtns is not changed ");
		}else{
			$("input[name='tab_fare_structure_radioBtns']").val([$("input[name='_tab_fare_structure_radioBtns']").val()]);
			console.log("tab_fare_structure_radioBtns is restored ");
		}
		if($("input[name='tab_fare_structure_radioBtns']:checked").val() == "1"){
			$("#tab-fare-structure-form").fadeOut("slow");
		}else{
			$("#tab-fare-structure-form").fadeIn("slow");
		}
		
		if($("input[name='tab_fare_structure_how_charge_radioBtns']:checked").val() == $("input[name='_tab_fare_structure_how_charge_radioBtns']").val()){
			//console.log("tab_fare_structure_how_charge_radioBtns is not changed ");
		}else{
			$("input[name='tab_fare_structure_how_charge_radioBtns']").val([$("input[name='_tab_fare_structure_how_charge_radioBtns']").val()]);
			console.log("tab_fare_structure_how_charge_radioBtns is restored ");
		}
		if($("input[name='fare_distance1_mile']").val() == $("input[name='_fare_distance1_mile']").val()){
			//console.log("fare_distance1 mile is not changed ");
		}else{
			$("input[name='fare_distance1_mile']").val($("input[name='_fare_distance1_mile']").val());
			console.log("fare_distance1 mile is restored ");
		}
		if($("input[name='_fare_dist1_type']").val() == ''){
			if($("select[name='fare_dist1_type']").val() == "flat rate"){
				//console.log("fare_distance1 type is not changed ");
			}else{
				$("select[name='fare_dist1_type']").val("flat rate");
				console.log("fare_distance1 type is restored ");
			}
		}else if($("select[name='fare_dist1_type']").val() ==$("input[name='_fare_dist1_type']").val()){
			//console.log("fare_distance1 type is not changed ");
		}else{
			if($("input[name='_fare_dist1_type']").val() == ""){
				$("select[name='fare_dist1_type']").val("flat rate");
			}else{
				$("select[name='fare_dist1_type']").val($("input[name='_fare_dist1_type']").val());
			}
			console.log("fare_distance1 type is restored ");
		}
		if($("input[name='fare_dist1_charge']").val() ==$("input[name='_fare_dist1_charge']").val()){
			//console.log("fare_distance1 charge is not changed ");
		}else{
			$("input[name='fare_dist1_charge']").val($("input[name='_fare_dist1_charge']").val());
			console.log("fare_distance1 charge is restored ");
		}
		
		var len = $( "input[name='_fare_dist_ind']" ).val();
		for (var d=2; d<=len; d++) {
			if($("input[name='fare_dist"+d+"_mile_1']").val() == $("input[name='_fare_dist"+d+"_mile_1']").val()){
				//console.log("fare_distance2 mile 1 is not changed ");
			}else{
				$("input[name='fare_dist"+d+"_mile_1']").val($("input[name='_fare_dist"+d+"_mile_1']").val());
				console.log("fare_distance"+d+" mile 1 is restored ");
			}
			if($("input[name='fare_dist"+d+"_mile_2']").val() == $("input[name='_fare_dist"+d+"_mile_2']").val()){
				//console.log("fare_distance2 mile 2 is not changed ");
			}else{
				$("input[name='fare_dist"+d+"_mile_2']").val($("input[name='_fare_dist"+d+"_mile_2']").val());
				console.log("fare_distance"+d+" mile 2 is restored ");
			}
			if($("input[name='_fare_dist"+d+"_type']").val() == ''){
				if($("select[name='fare_dist"+d+"_type']").val() == "flat rate"){
					//console.log("fare_distance2 type is not changed ");
				}else{
					$("input[name='_fare_dist"+d+"_type']").val("flat rate");
					console.log("fare_distance"+d+" type is restored ");
				}
			}else if($("select[name='fare_dist"+d+"_type']").val() == $("input[name='_fare_dist"+d+"_type']").val()){
				//console.log("fare_distance2 type is not changed ");
			}else{
				if($("input[name='_fare_dist"+d+"_type']").val() == ''){
					$("input[name='_fare_dist"+d+"_type']").val("flat rate");
				}else{
					$("input[name='_fare_dist"+d+"_type']").val($("input[name='_fare_dist"+d+"_type']").val());
				}
				console.log("fare_distance"+d+" type is restored ");
			}
			if($("input[name='fare_dist"+d+"_charge']").val() == $("input[name='_fare_dist"+d+"_charge']").val()){
				//console.log("fare_distance2 charge is not changed ");
			}else{
				$("input[name='fare_dist"+d+"_charge']").val($("input[name='_fare_dist"+d+"_charge']").val());
				console.log("fare_distance"+d+" charge is restored ");
			}
		}
		/*
		if($("input[name='fare_dist2_mile_1']").val() == $("input[name='_fare_dist2_mile_1']").val()){
			//console.log("fare_distance2 mile 1 is not changed ");
		}else{
			$("input[name='fare_dist2_mile_1']").val($("input[name='_fare_dist2_mile_1']").val());
			console.log("fare_distance2 mile 1 is restored ");
		}
		if($("input[name='fare_dist2_mile_2']").val() == $("input[name='_fare_dist2_mile_2']").val()){
			//console.log("fare_distance2 mile 2 is not changed ");
		}else{
			$("input[name='fare_dist2_mile_2']").val($("input[name='_fare_dist2_mile_2']").val());
			console.log("fare_distance2 mile 2 is restored ");
		}
		if($("input[name='_fare_dist2_type']").val() == ''){
			if($("select[name='fare_dist2_type']").val() == "flat rate"){
				//console.log("fare_distance2 type is not changed ");
			}else{
				$("input[name='_fare_dist2_type']").val("flat rate");
				console.log("fare_distance2 type is restored ");
			}
		}else if($("select[name='fare_dist2_type']").val() == $("input[name='_fare_dist2_type']").val()){
			//console.log("fare_distance2 type is not changed ");
		}else{
			if($("input[name='_fare_dist2_type']").val() == ''){
				$("input[name='_fare_dist2_type']").val("flat rate");
			}else{
				$("input[name='_fare_dist2_type']").val($("input[name='_fare_dist2_type']").val());
			}
			console.log("fare_distance2 type is restored ");
		}
		if($("input[name='fare_dist2_charge']").val() == $("input[name='_fare_dist2_charge']").val()){
			//console.log("fare_distance2 charge is not changed ");
		}else{
			$("input[name='fare_dist2_charge']").val($("input[name='_fare_dist2_charge']").val());
			console.log("fare_distance2 charge is restored ");
		}
		if($("input[name='fare_dist3_mile_1']").val() == $("input[name='_fare_dist3_mile_1']").val()){
			//console.log("fare_distance3 mile 1 is not changed ");
		}else{
			$("input[name='fare_dist3_mile_1']").val($("input[name='_fare_dist3_mile_1']").val());
			console.log("fare_distance3 mile 1 is restored ");
		}
		if($("input[name='fare_dist3_mile_2']").val() == $("input[name='_fare_dist3_mile_2']").val()){
			//console.log("fare_distance3 mile 2 is not changed ");
		}else{
			$("input[name='fare_dist3_mile_2']").val($("input[name='_fare_dist3_mile_2']").val());
			console.log("fare_distance3 mile 2 is restored ");
		}
		if($("input[name='_fare_dist3_type']").val() == ''){
			if($("select[name='fare_dist3_type']").val() == "flat rate"){
				//console.log("fare_distance3 type is not changed ");
			}else{
				$("select[name='fare_dist3_type']").val("flat rate");
				console.log("fare_distance3 type is restored ");
			}
		}else if($("select[name='fare_dist3_type']").val() == $("input[name='_fare_dist3_type']").val()){
			//console.log("fare_distance3 type is not changed ");
		}else{
			if($("input[name='_fare_dist3_type']").val() == ''){
				$("select[name='fare_dist3_type']").val("flat rate");
			}else{
				$("select[name='fare_dist3_type']").val($("input[name='_fare_dist3_type']").val());
			}
			console.log("fare_distance3 type is restored ");
		}
		if($("input[name='fare_dist3_charge']").val() == $("input[name='_fare_dist3_charge']").val()){
			//console.log("fare_distance3 charge is not changed ");
		}else{
			$("input[name='fare_dist3_charge']").val($("input[name='_fare_dist3_charge']").val());
			console.log("fare_distance3 charge is restored ");
		}
		if($("input[name='fare_dist4_mile_1']").val() == $("input[name='_fare_dist4_mile_1']").val()){
			//console.log("fare_distance4 mile 1 is not changed ");
		}else{
			$("input[name='fare_dist4_mile_1']").val($("input[name='_fare_dist4_mile_1']").val());
			console.log("fare_distance4 mile 1 is restored ");
		}
		if($("input[name='fare_dist4_mile_2']").val() == $("input[name='_fare_dist4_mile_2']").val()){
			//console.log("fare_distance4 mile 2 is not changed ");
		}else{
			$("input[name='fare_dist4_mile_2']").val($("input[name='_fare_dist4_mile_2']").val());
			console.log("fare_distance4 mile 2 is restored ");
		}
		if($("input[name='_fare_dist4_type']").val() == ''){
			if($("select[name='fare_dist4_type']").val() == "flat rate"){
				//console.log("fare_distance4 type is not changed ");
			}else{
				$("select[name='fare_dist4_type']").val("flat rate");
				console.log("fare_distance4 type is restored ");
			}
		}else if($("select[name='fare_dist4_type']").val() == $("input[name='_fare_dist4_type']").val()){
			//console.log("fare_distance4 type is not changed ");
		}else{
			if($("input[name='_fare_dist4_type']").val() == ''){
				$("select[name='fare_dist4_type']").val("flat rate");
			}else{
				$("select[name='fare_dist4_type']").val($("input[name='_fare_dist4_type']").val());
			}
			console.log("fare_distance4 type is restored ");
		}
		if($("input[name='fare_dist4_charge']").val() == $("input[name='_fare_dist4_charge']").val()){
			//console.log("fare_distance4 charge is not changed ");
		}else{
			$("input[name='fare_dist4_charge']").val($("input[name='_fare_dist4_charge']").val());
			console.log("fare_distance4 charge is restored ");
		}
		
		*/
		
		
		if($("input[name='return_fare_multiplier']").val() == $("input[name='_return_fare_multiplier']").val()){
			//console.log("return_fare_multiplier is not changed ");
		}else{
			$("input[name='return_fare_multiplier']").val($("input[name='_return_fare_multiplier']").val());
			console.log("return_fare_multiplier is restored ");
		}
		if($("input[name='discount_for_over60']").val() == $("input[name='_discount_for_over60']").val()){
			//console.log("discount_for_over60 is not changed ");
		}else{
			$("input[name='discount_for_over60']").val($("input[name='_discount_for_over60']").val());
			console.log("discount_for_over60 is restored ");
		}
		if($("input[name='discount_for_under16']").val() == $("input[name='_discount_for_under16']").val()){
			//console.log("discount_for_under16 is not changed ");
		}else{
			$("input[name='discount_for_under16']").val($("input[name='_discount_for_under16']").val());
			console.log("discount_for_under16 is restored ");
		}
		if($("input[name='discount_for_other_concessionary']").val() == $("input[name='_discount_for_other_concessionary']").val()){
			//console.log("discount_for_other_concessionary is not changed ");
		}else{
			$("input[name='discount_for_other_concessionary']").val($("input[name='_discount_for_other_concessionary']").val());
			console.log("discount_for_other_concessionary is restored ");
		}
		if($("input[name='fare_structure_checkbox_escort']").is(':checked') == $("input[name='_fare_structure_checkbox_escort']").is(':checked')){
			//console.log("fare_structure_checkbox_escort is not changed ");
		}else{
			$("input[name='fare_structure_checkbox_escort']").prop("checked", $("input[name='_fare_structure_checkbox_escort']").is(':checked'));
			console.log("fare_structure_checkbox_escort is restored ");
		}
		if($("input[name='fare_structure_checkbox_charge_for_dead_mileage']").is(':checked') == $("input[name='_fare_structure_checkbox_charge_for_dead_mileage']").is(':checked')){
			//console.log("fare_structure_checkbox_charge_for_dead_mileage is not changed ");
		}else{
			$("input[name='fare_structure_checkbox_charge_for_dead_mileage']").prop("checked", $("input[name='_fare_structure_checkbox_charge_for_dead_mileage']").is(':checked'));
			console.log("fare_structure_checkbox_charge_for_dead_mileage is restored ");
		}
		
		$( "div.addedMore" ).remove();
		$( "input[name='fare_dist_ind']" ).val($( "input[name='_fare_dist_ind']" ).val()); 
	}
	
	
	</script>
</head>
<body>
    <div id="layout">
        <div id="header">
			<%@ include file="header.jsp" %>
        </div>
        <div id="main">
            <div id="main-box">
                <div id="quote">
                    "If we could get a system in place...with a common booking entry, 
                    so that somebody could call up a vehicle that was available, irrespective
                    of whether that was actually in the ownership of the health board, the council, 
                    the third sector, then it might lead to more viability, more sustainability 
                    all round...That is what we should be aiming for."
                    <span style = "font-size: 14px;">---  Audit Scotland, 2011.</span>
                </div>
            </div>
            <div id="input-content">
                <div class="container">
				<div class="row">

	<form:form id="data_input_form_update" action="${pageContext.request.contextPath}/data_input_form_update" method="post" modelAttribute="operatorDataInputForm"  onsubmit="doTasksBeforeSubmission();" > 
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
	                <input type="text" id = "service_name" name="service_name" class="form-control" placeholder="Please enter service name." value="<c:if test="${not empty serv_name}"><c:out value="${serv_name}"/></c:if>" disabled/>
	                <input type="text" name="_service_name" style="display:none;"  value="<c:if test="${not empty serv_name}"><c:out value="${serv_name}"/></c:if>" />
	                
	                <input type="hidden" name="operator_id" id="operator_id" value="<c:if test="${not empty operator_id}"><c:out value="${operator_id}"/></c:if>">	               
	                </div>
	            </div>
                </div>
                <div class="row  row-space" style="margin-left:1px;">
                	<div class="form-group">
                    <label for="service_description" class="col-sm-2 control-label">Service Description</label>
                    <div class="col-sm-10">
                    <textarea rows="5" cols="5" class="form-control" id="service_description" name="service_description" placeholder="Please describe your service." disabled><c:if test="${not empty serv_desr}"><c:out value="${serv_desr}"/></c:if></textarea>   
                    <textarea rows="5" cols="5" style="display:none;" name="_service_description" ><c:if test="${not empty serv_desr}"><c:out value="${serv_desr}"/></c:if></textarea>   
                    </div>
                    </div>
                </div>
                <div class="row  row-space" style="margin-left:1px;">
                 <div class="form-group">
                    <label for="how_to_book" class="col-sm-2 control-label">How to Book</label>
                    <div class="col-sm-10">
                    <textarea rows="4" cols="5" class="form-control" id="how_to_book" name="how_to_book" placeholder="Describe how the passengers can book the journeys with you, e.g., call the operator two days before they travel." disabled><c:if test="${not empty how2book}"><c:out value="${how2book}"/></c:if></textarea>   
                    <textarea rows="4" cols="5"  name="_how_to_book" style="display:none;"><c:if test="${not empty how2book}"><c:out value="${how2book}"/></c:if></textarea>   
                    </div>
                  </div>
                </div>
                <div class="row  row-space" style="margin-left:1px;">
                <div class="form-group">
	                <label for="type_of_permit" class="col-sm-2 control-label">Type of Permit</label>
	                <div class="col-sm-10">
	                <!-- input type="text" name="type_of_permit" class="form-control" placeholder="" value="<c:if test="${not empty type_of_permit}"><c:out value="${type_of_permit}"/></c:if>" disabled /-->
	                <select name="type_of_permit" class="form-control" disabled>
	                	<option value="---" >---</option>
	                	<option value="Section 19 permit" >Section 19 permit</option>
	                	<option value="Section 21 permit" >Section 21 permit</option>
	                	<option value="Section 22 permit" >Section 22 permit</option>
	                	
	                </select>
	                <input type="text" name="_type_of_permit" style="display:none;" class="form-control" placeholder="" value="<c:if test="${not empty type_of_permit}"><c:out value="${type_of_permit}"/></c:if>"  />
	                </div>
	            </div>
                </div>
                <fieldset id = "service_not_avail">
                <div id="service_not_avail_table">
                <div class="form-group">
             		<div class="row" style="margin-top:10px;">
             			 <div  class="col-md-3 control-label"> 
             			   <c:choose>
							  <c:when test="${not empty not_avail}">
							   <input type="checkbox" style="margin-top:3px;" name="cb_not_avail" checked disabled/> 
							   <input type="checkbox" style="display:none;" name="_cb_not_avail" checked/> 
							  </c:when>
							  <c:otherwise>
							   <input type="checkbox" style="margin-top:3px;" name="cb_not_avail" disabled/> 
							   <input type="checkbox" style="display:none;" name="_cb_not_avail"/> 
							  </c:otherwise>
							</c:choose>  
	                     	<label style="top: -1px; vertical-align: top;">
	                     	Service is not available</label>
	                    </div>
             			 <div class="col-md-4 control-label">
             			 <label for="not_valid_from" style="margin-right: 10px;">from</label>
             			   <c:choose>
							  <c:when test="${not empty not_valid_from}">
							   <input name="not_valid_from" type="text" style="width: 150px;" value="<c:if test="${not empty not_valid_from}"><c:out value="${not_valid_from}"/></c:if>" disabled></input>
							   <input name="_not_valid_from" type="text" style="display:none;" value="<c:if test="${not empty not_valid_from}"><c:out value="${not_valid_from}"/></c:if>"></input>
							  </c:when>
							  <c:otherwise>
							   <input name="not_valid_from" type="text" style="width: 150px;" disabled></input>
							   <input name="_not_valid_from" type="text" style="display:none;"></input>
							  </c:otherwise>
							</c:choose>  
             			 </div>
             			 <div class="col-md-5 control-label">
             			 	<label for="not_valid_to" style="margin-right: 10px;">to</label>
             			    <c:choose>
							  <c:when test="${not empty not_valid_to}">
							   <input name="not_valid_to" type="text" style="width: 150px;" value="<c:if test="${not empty not_valid_to}"><c:out value="${not_valid_to}"/></c:if>" disabled></input>
							   <input name="_not_valid_to" type="text" style="display:none;" value="<c:if test="${not empty not_valid_to}"><c:out value="${not_valid_to}"/></c:if>"></input>
							  </c:when>
							  <c:otherwise>
							  <input name="not_valid_to" type="text" style="width: 150px;" disabled></input> 
							  <input name="_not_valid_to" type="text" style="display:none;" ></input> 
							  </c:otherwise>
							</c:choose>  
             			 </div>
             		</div>
                </div>
                
                <c:if test="${not empty service_not_avail_list}">
                	<c:forEach items="${service_not_avail_list}" var="item" varStatus="status">
                	<div class="row" style="margin-top:10px;">
                		<div  class="col-md-3 control-label">
                		</div>
                		<div class="col-md-4 control-label">
                			<label for="not_valid_from_${status.index+2}" style="margin-right: 10px;">from</label>
                			<input name="not_valid_from_${status.index+2}" type="text" style="width: 150px;" value="${item.getFromStr()}" disabled></input>
                			<input name="_not_valid_from_${status.index+2}" type="text" style="display:none;" value="${item.getFromStr()}"></input>
                		</div>
                		<div class="col-md-5 control-label">
                			<label for="not_valid_to_${status.index+2}" style="margin-right: 10px;">to</label>
                			<input name="not_valid_to_${status.index+2}" type="text" style="width: 150px;" value="${item.getToStr()}" disabled></input>
                			<input name="_not_valid_to_${status.index+2}" type="text" style="display:none" value="${item.getToStr()}" ></input>
                		</div>
                	</div>
                	</c:forEach>
                </c:if>
                
                </div>
                <div class="row col-lg-12 col-sm-12 col-xs-12">
						<div class="col-md-2 ">
					  	<button type="button" class="btn btn-info btn-sm addMoreSerivceNotAvail" data-toggle="#service_not_avail" disabled>
                		Add more
            			</button>
            			<input type="text" name="service_not_avail_ind" value = "<c:if test="${not empty service_not_avail_ind}"><c:out value="${service_not_avail_ind}"/></c:if><c:if test="${empty service_not_avail_ind}"><c:out value="1"/></c:if>" style="display:none;" >
            			<input type="text" name="_service_not_avail_ind" value = "<c:if test="${not empty service_not_avail_ind}"><c:out value="${service_not_avail_ind}"/></c:if><c:if test="${empty service_not_avail_ind}"><c:out value="1"/></c:if>" style="display:none;" >
            			<input type="text" name="added_service_not_avail" value = "added_service_not_avail" style="display:none;" >
            			</div>
            			<div class="col-md-2 " style="margin-left: -20px;">
            			<button type="button" class="btn btn-info btn-sm addMoreSerivceNotAvail" data-toggle="#service_not_avail_del" disabled>
                		Delete
            			</button>
            			</div>
						<div class="col-md-8 "></div>
					</div>
                </fieldset>
                 <script>
                   var nowDate = new Date();
                   var today = new Date(nowDate.getFullYear(), nowDate.getMonth(), nowDate.getDate(), 0, 0, 0, 0);
                  
                   $('input[name^="not_valid_from"]').each(function() {
                	   $(this).datepicker({
					        'format': 'yyyy-mm-dd',
					        'autoclose': true,
					        'startDate': today
					    });
                   });
                   
                   $('input[name^="not_valid_to"]').each(function() {
                	   $(this).datepicker({
					        'format': 'yyyy-mm-dd',
					        'autoclose': true,
					        'startDate': today
					    });
                   });
                   /*
                   $('input[name="not_valid_from"]').datepicker({
					        'format': 'yyyy-mm-dd',
					        'autoclose': true,
					        'startDate': today
					    });
                     $('input[name="not_valid_to"]').datepicker({
					        'format': 'yyyy-mm-dd',
					        'autoclose': true,
					        'startDate': today
					    });
                     */
                     function new_datepicker(){
                    	 console.log("call new_datepicker");
                    	 
                    	var ind = $('input[name="service_not_avail_ind"]').val();
                 		var added_ind = parseInt(ind);
                    	$('input[name="not_valid_from_'+(added_ind)+'"]').datepicker({
        				        'format': 'yyyy-mm-dd',
        				        'autoclose': true,
        				        'startDate': today
        				    });
                        $('input[name="not_valid_to_'+(added_ind)+'"]').datepicker({
        				        'format': 'yyyy-mm-dd',
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
                     <input type="checkbox" style="margin-top:3px;margin-left:0px;" name="tab_operating_hours_field_cb_weekdays" disabled/> 
                     <label style="top: -1px; vertical-align: top;">
                      All weekdays</label>
                	</div>
                	<div class="col-sm-4">
                	<input type="text" id="tab_operating_hours_field_weekdays_1" name="tab_operating_hours_field_weekdays_1" class="tab-field" disabled/> 
                	<input type="text" id="tab_operating_hours_field_weekdays_2" name="tab_operating_hours_field_weekdays_2" class="tab-field" disabled/> 
                	</div>
                	<div class="col-sm-5">
                	<input type="text" id="tab_operating_hours_field_weekdays_3" name="tab_operating_hours_field_weekdays_3" class="tab-field" disabled/> 
                	<input type="text" id="tab_operating_hours_field_weekdays_4" name="tab_operating_hours_field_weekdays_4" class="tab-field" disabled/> 
                	</div>
                	</div>
                	<div class="row row-space">
                		<div class="col-sm-3">
                     <!--  input type="checkbox" style="margin-top:3px;margin-left:17px;" name="tab_operating_hours_field_cb-monday" /--> 
                     <label style="top: -1px; vertical-align: top;" class="left-margin">
                      Monday</label>
                	</div>
                	<div class="col-sm-4">
                	<input type="text" id="tab_operating_hours_field_monday_1" name="tab_operating_hours_field_monday_1" class="tab-field" value="<c:if test="${not empty mon1}"><c:out value="${mon1}"/></c:if>" disabled/> 
                	<input type="text" name="_tab_operating_hours_field_monday_1" style="display:none;"  value="<c:if test="${not empty mon1}"><c:out value="${mon1}"/></c:if>"/> 
                	<input type="text" id="tab_operating_hours_field_monday_2" name="tab_operating_hours_field_monday_2" class="tab-field" value="<c:if test="${not empty mon2}"><c:out value="${mon2}"/></c:if>" disabled/> 
                	<input type="text"  name="_tab_operating_hours_field_monday_2" style="display:none;" value="<c:if test="${not empty mon2}"><c:out value="${mon2}"/></c:if>"/> 
                	</div>
                	<div class="col-sm-5">
                	<input type="text" id="tab_operating_hours_field_monday_3" name="tab_operating_hours_field_monday_3" class="tab-field" value="<c:if test="${not empty mon3}"><c:out value="${mon3}"/></c:if>" disabled/> 
                	<input type="text" name="_tab_operating_hours_field_monday_3" style="display:none;" value="<c:if test="${not empty mon3}"><c:out value="${mon3}"/></c:if>" /> 
                	<input type="text" id="tab_operating_hours_field_monday_4" name="tab_operating_hours_field_monday_4" class="tab-field"/ value="<c:if test="${not empty mon4}"><c:out value="${mon4}"/></c:if>" disabled/>
                	<input type="text" name="_tab_operating_hours_field_monday_4" style="display:none;" value="<c:if test="${not empty mon4}"><c:out value="${mon4}"/></c:if>" /> 
                	</div>
                	</div>
                	<div class="row row-space">
                		<div class="col-sm-3">
                     <!--  input type="checkbox" style="margin-top:3px;margin-left:17px;" name="tab-operating-hours-field-cb-tuesday" /--> 
                     <label style="top: -1px; vertical-align: top;" class="left-margin">
                      Tuesday</label>
                	</div>
                	<div class="col-sm-4">
                	<input type="text" id="tab_operating_hours_field_tuesday_1" name="tab_operating_hours_field_tuesday_1" class="tab-field" value="<c:if test="${not empty tue1}"><c:out value="${tue1}"/></c:if>" disabled/> 
                	<input type="text" name="_tab_operating_hours_field_tuesday_1" style="display:none;" value="<c:if test="${not empty tue1}"><c:out value="${tue1}"/></c:if>"/> 
                	<input type="text" id="tab_operating_hours_field_tuesday_2" name="tab_operating_hours_field_tuesday_2" class="tab-field" value="<c:if test="${not empty tue2}"><c:out value="${tue2}"/></c:if>" disabled/> 
                	<input type="text" name="_tab_operating_hours_field_tuesday_2" style="display:none;"  value="<c:if test="${not empty tue2}"><c:out value="${tue2}"/></c:if>" /> 
                	
                	</div>
                	<div class="col-sm-5">
                	<input type="text" id="tab_operating_hours_field_tuesday_3" name="tab_operating_hours_field_tuesday_3" class="tab-field" value="<c:if test="${not empty tue3}"><c:out value="${tue3}"/></c:if>" disabled/> 
                	<input type="text" name="_tab_operating_hours_field_tuesday_3" style="display:none;" value="<c:if test="${not empty tue3}"><c:out value="${tue3}"/></c:if>" /> 
                	<input type="text" id="tab_operating_hours_field_tuesday_4" name="tab_operating_hours_field_tuesday_4" class="tab-field"  value="<c:if test="${not empty tue4}"><c:out value="${tue4}"/></c:if>" disabled/> 
                	<input type="text" name="_tab_operating_hours_field_tuesday_4" style="display:none;"  value="<c:if test="${not empty tue4}"><c:out value="${tue4}"/></c:if>"/> 
                	</div>
                	</div>
                	<div class="row row-space">
                		<div class="col-sm-3">
                     <!--  input type="checkbox" style="margin-top:3px;margin-left:17px;" name="tab-operating-hours-field-cb-wednesday"  /--> 
                     <label style="top: -1px; vertical-align: top;" class="left-margin">
                      Wednesday</label>
                	</div>
                	<div class="col-sm-4">
                	<input type="text" id="tab_operating_hours_field_wednesday_1" name="tab_operating_hours_field_wednesday_1" class="tab-field" value="<c:if test="${not empty wed1}"><c:out value="${wed1}"/></c:if>" disabled/> 
                	<input type="text" name="_tab_operating_hours_field_wednesday_1" style="display:none;"  value="<c:if test="${not empty wed1}"><c:out value="${wed1}"/></c:if>" /> 
                	<input type="text" id="tab_operating_hours_field_wednesday_2" name="tab_operating_hours_field_wednesday_2" class="tab-field" value="<c:if test="${not empty wed2}"><c:out value="${wed2}"/></c:if>" disabled/> 
                	<input type="text" name="_tab_operating_hours_field_wednesday_2" style="display:none;" value="<c:if test="${not empty wed2}"><c:out value="${wed2}"/></c:if>" /> 
                	</div>
                	<div class="col-sm-5">
                	<input type="text" id="tab_operating_hours_field_wednesday_3" name="tab_operating_hours_field_wednesday_3" class="tab-field" value="<c:if test="${not empty wed3}"><c:out value="${wed3}"/></c:if>"  disabled/> 
                	<input type="text" name="_tab_operating_hours_field_wednesday_3" style="display:none;"  value="<c:if test="${not empty wed3}"><c:out value="${wed3}"/></c:if>" /> 
                	
                	<input type="text" id="tab_operating_hours_field_wednesday_4" name="tab_operating_hours_field_wednesday_4" class="tab-field" value="<c:if test="${not empty wed4}"><c:out value="${wed4}"/></c:if>"  disabled/> 
                	<input type="text" name="_tab_operating_hours_field_wednesday_4" style="display:none;" value="<c:if test="${not empty wed4}"><c:out value="${wed4}"/></c:if>" /> 
                	
                	</div>
                	</div>
                	<div class="row row-space">
                		<div class="col-sm-3">
                     <!--  input type="checkbox" style="margin-top:3px;margin-left:17px;" name="tab-operating-hours-field-cb-thursday" /--> 
                     <label style="top: -1px; vertical-align: top;" class="left-margin">
                      Thursday</label>
                	</div>
                	<div class="col-sm-4">
                	<input type="text" id="tab_operating_hours_field_thursday_1" name="tab_operating_hours_field_thursday_1" class="tab-field"  value="<c:if test="${not empty thu1}"><c:out value="${thu1}"/></c:if>" disabled /> 
                	<input type="text" name="_tab_operating_hours_field_thursday_1" style="display:none;" value="<c:if test="${not empty thu1}"><c:out value="${thu1}"/></c:if>"  /> 
                	<input type="text" id="tab_operating_hours_field_thursday_2" name="tab_operating_hours_field_thursday_2" class="tab-field" value="<c:if test="${not empty thu2}"><c:out value="${thu2}"/></c:if>"  disabled/> 
                	<input type="text" name="_tab_operating_hours_field_thursday_2" style="display:none;" value="<c:if test="${not empty thu2}"><c:out value="${thu2}"/></c:if>" /> 
                	</div>
                	<div class="col-sm-5">
                	<input type="text" id="tab_operating_hours_field_thursday_3" name="tab_operating_hours_field_thursday_3" class="tab-field" value="<c:if test="${not empty thu3}"><c:out value="${thu3}"/></c:if>" disabled/> 
                	<input type="text" name="_tab_operating_hours_field_thursday_3" style="display:none;" value="<c:if test="${not empty thu3}"><c:out value="${thu3}"/></c:if>"/> 
                	<input type="text" id="tab_operating_hours_field_thursday_4" name="tab_operating_hours_field_thursday_4" class="tab-field" value="<c:if test="${not empty thu4}"><c:out value="${thu4}"/></c:if>" disabled/> 
                	<input type="text" name="_tab_operating_hours_field_thursday_4" style="display:none;" value="<c:if test="${not empty thu4}"><c:out value="${thu4}"/></c:if>"/> 
                	</div>
                	</div>
                	<div class="row row-space">
                		<div class="col-sm-3">
                     <!--  input type="checkbox" style="margin-top:3px;margin-left:17px;" name="tab-operating-hours-field-cb-friday" /--> 
                     <label style="top: -1px; vertical-align: top;" class="left-margin">
                      Friday</label>
                	</div>
                	<div class="col-sm-4">
                	<input type="text" id="tab_operating_hours_field_friday_1" name="tab_operating_hours_field_friday_1" class="tab-field"  value="<c:if test="${not empty fri1}"><c:out value="${fri1}"/></c:if>"  disabled/> 
                	<input type="text" name="_tab_operating_hours_field_friday_1" style="display:none;" value="<c:if test="${not empty fri1}"><c:out value="${fri1}"/></c:if>"  /> 
                	<input type="text" id="tab_operating_hours_field_friday_2" name="tab_operating_hours_field_friday_2" class="tab-field" value="<c:if test="${not empty fri2}"><c:out value="${fri2}"/></c:if>" disabled/> 
                	<input type="text" name="_tab_operating_hours_field_friday_2" style="display:none;" value="<c:if test="${not empty fri2}"><c:out value="${fri2}"/></c:if>" /> 
                	</div>
                	<div class="col-sm-5">
                	<input type="text" id="tab_operating_hours_field_friday_3" name="tab_operating_hours_field_friday_3" class="tab-field" value="<c:if test="${not empty fri3}"><c:out value="${fri3}"/></c:if>" disabled/> 
                	<input type="text" name="_tab_operating_hours_field_friday_3" style="display:none;" value="<c:if test="${not empty fri3}"><c:out value="${fri3}"/></c:if>" /> 
                	<input type="text" id="tab_operating_hours_field_friday_4" name="tab_operating_hours_field_friday_4" class="tab-field" value="<c:if test="${not empty fri4}"><c:out value="${fri4}"/></c:if>" disabled/> 
                	<input type="text" name="_tab_operating_hours_field_friday_4" style="display:none;" value="<c:if test="${not empty fri4}"><c:out value="${fri4}"/></c:if>" /> 
                	</div>
                	</div>
                	<div class="row row-space">
                		<div class="col-sm-3">
                     <!--  input type="checkbox" style="margin-top:3px;margin-left:17px;" name="tab-operating-hours-field-cb-saturday" /--> 
                     <label style="top: -1px; vertical-align: top;" class="left-margin">
                      Saturday</label>
                	</div>
                	<div class="col-sm-4">
                	<input type="text" id="tab_operating_hours_field_saturday_1" name="tab_operating_hours_field_saturday_1" class="tab-field"  value="<c:if test="${not empty sat1}"><c:out value="${sat1}"/></c:if>" disabled /> 
                	<input type="text" name="_tab_operating_hours_field_saturday_1" style="display:none;"  value="<c:if test="${not empty sat1}"><c:out value="${sat1}"/></c:if>"  /> 
                	<input type="text" id="tab_operating_hours_field_saturday_2" name="tab_operating_hours_field_saturday_2" class="tab-field"  value="<c:if test="${not empty sat2}"><c:out value="${sat2}"/></c:if>" disabled/> 
                	<input type="text" name="_tab_operating_hours_field_saturday_2" style="display:none;"  value="<c:if test="${not empty sat2}"><c:out value="${sat2}"/></c:if>"/> 
                	</div>
                	<div class="col-sm-5">
                	<input type="text" id="tab_operating_hours_field_saturday_3" name="tab_operating_hours_field_saturday_3" class="tab-field"  value="<c:if test="${not empty sat3}"><c:out value="${sat3}"/></c:if>" disabled/> 
                	<input type="text" name="_tab_operating_hours_field_saturday_3" style="display:none;" value="<c:if test="${not empty sat3}"><c:out value="${sat3}"/></c:if>" /> 
                	<input type="text" id="tab_operating_hours_field_saturday_4" name="tab_operating_hours_field_saturday_4" class="tab-field"  value="<c:if test="${not empty sat4}"><c:out value="${sat4}"/></c:if>" disabled/> 
                	<input type="text" name="_tab_operating_hours_field_saturday_4" style="display:none;"  value="<c:if test="${not empty sat4}"><c:out value="${sat4}"/></c:if>" /> 
                	</div>
                	</div>
                	<div class="row row-space">
                		<div class="col-sm-3">
                     <!--  input type="checkbox" style="margin-top:3px;margin-left:17px;" name="tab-operating-hours-field-cb-sunday"/--> 
                     <label style="top: -1px; vertical-align: top;" class="left-margin">
                      Sunday</label>
                	</div>
                	<div class="col-sm-4">
                	<input type="text" id="tab_operating_hours_field_sunday_1" name="tab_operating_hours_field_sunday_1" class="tab-field"  value="<c:if test="${not empty sun1}"><c:out value="${sun1}"/></c:if>" disabled/> 
                	<input type="text" name="_tab_operating_hours_field_sunday_1" style="display:none;"  value="<c:if test="${not empty sun1}"><c:out value="${sun1}"/></c:if>" /> 
                	<input type="text" id="tab_operating_hours_field_sunday_2" name="tab_operating_hours_field_sunday_2" class="tab-field" value="<c:if test="${not empty sun2}"><c:out value="${sun2}"/></c:if>" disabled/> 
                	<input type="text" name="_tab_operating_hours_field_sunday_2" style="display:none;" value="<c:if test="${not empty sun2}"><c:out value="${sun2}"/></c:if>" /> 
                	</div>
                	<div class="col-sm-5">
                	<input type="text" id="tab_operating_hours_field_sunday_3" name="tab_operating_hours_field_sunday_3" class="tab-field" value="<c:if test="${not empty sun3}"><c:out value="${sun3}"/></c:if>" disabled/> 
                	<input type="text" name="_tab_operating_hours_field_sunday_3" style="display:none;" value="<c:if test="${not empty sun3}"><c:out value="${sun3}"/></c:if>" /> 
                	<input type="text" id="tab_operating_hours_field_sunday_4" name="tab_operating_hours_field_sunday_4" class="tab-field"  value="<c:if test="${not empty sun4}"><c:out value="${sun4}"/></c:if>" disabled/> 
                	<input type="text" name="_tab_operating_hours_field_sunday_4" style="display:none;"  value="<c:if test="${not empty sun4}"><c:out value="${sun4}"/></c:if>" /> 
                	</div>
                	</div>
                </div>
        		</div>
        		<div class="row" style="margin-bottom: 6px;">
    			<div class="col-md-6"></div>
      			<div class="col-md-2 col-md-offset-2"><button type="button" class="btn btn-info" id="edit_general_info">Edit this section</button></div>
  				<!-- div class="col-md-2 col-md-offset-1"><button type="button" class="btn btn-info" id="save_general_info">Save this section</button></div -->
  				<div class="col-md-2"></div>
				</div>
		        <!-- div class="input-group col-md-offset-9 col-sm-offset-9 col-xs-offset-9" style="margin-bottom: 6px;">
            		<button type="button" class="btn btn-info" id="save_general_info">Save this section</button>
            	</div --><!--save clear button-->
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
	                <input type="text" id = "reg_no" name="regNum" class="form-control" placeholder="Please enter registration number." value="<c:if test="${not empty reg_num}"><c:out value="${reg_num}"/></c:if>"  disabled/>
	                <input type="text" name="_regNum" style="display:none;" value="<c:if test="${not empty reg_num}"><c:out value="${reg_num}"/></c:if>" />
	                </div>
	            </div>
	             <div class="form-group">
	                <label for="vehicle_type" class="col-sm-2 control-label">Vehicle type</label>
	                <div class="col-sm-10">
	                	<select name="vehicle_type" class="form-control" disabled>
							<!-- option value="pickup Van"  >Pickup Van</option --> 
							<c:if test="${not empty veh_types}">
						  	<c:forEach items="${veh_types}" var="type">
						  		<c:choose>
							  	<c:when test="${ not empty vehicle_type and type == vehicle_type}">
							   		<option value="${type}" selected>${type}</option>
							  	</c:when>
							  	<c:otherwise>
							  		<option value="${type}">${type}</option>
							  	</c:otherwise>
								</c:choose>  
							</c:forEach>	
						  	</c:if>
							<option value="other">Other</option>
						</select>
						<input type="text" name="_vehicle_type" value="${vehicle_type}" style="display:none;">
						<input type="text" id = "other_vehicle" name="other_vehicle" class="form-control" placeholder="Please enter the vehicle type." value="" style="display: none;"/>
	                </div>
	            </div>
                </div>
        		</div>
        		<div class="row" style="margin-bottom: 6px;">
    			<div class="col-md-6"></div>
      			<div class="col-md-2 col-md-offset-2"><button type="button" class="btn btn-info" id="edit_vehicle_info">Edit this section</button></div>
  				<!--  div class="col-md-2 col-md-offset-1"><button type="button" class="btn btn-info" id="save_vehicle_info">Save this section</button></div-->
  				<div class="col-md-2"></div>
				</div>
		        <!--  div class="input-group col-md-offset-9 col-sm-offset-9 col-xs-offset-9" style="margin-bottom: 6px;">
            		<button type="button" class="btn btn-info" id="save_vehicle_info">Save this section</button>
            	</div--><!--save clear button-->
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
					<div id="div_edit_operating_area_b3" class="input-group col-md-offset-6 col-sm-offset-9 col-xs-offset-9" style="margin-top: 9px;display:none;">
					
					<button type="button" class="btn btn-info" id = "confirmChanges" >Confirm changes</button>
					<!-- button type="button" class="btn btn-info" id = "undoChanges"  style="margin-left:30px;">Undo changes</button -->
            		<!--  button type="button" class="btn btn-info" id = "editMap" style="width:75px;">Save it</button-->
					<button style="margin-left:30px;" type="button" class="btn btn-danger" id = "clearMap" onclick="restoreMap();">Restore original one</button>
					<!-- button style="margin-left:30px;" type="button" class="btn btn-danger" id = "clearMap" onclick="BlitzMap.deleteAll();">Clear</button -->
            		</div>
            		
            		<div id="div_edit_operating_area_b1" class="input-group col-md-offset-9 col-sm-offset-9 col-xs-offset-9" style="margin-top: 9px;">
					<button type="button" class="btn btn-info" id="edit_operating_area" >Edit this section</button>
					</div>
            		
                     <textarea id="mapData" style="display:none;" >
                     ${jsonData}
                    </textarea>
                    
                    <textarea id="originalMapData" style="display:none;" >
                     ${jsonData}
                    </textarea>
                    
                     <textarea id="undoChangeMapData" style="display:none;" >
                    </textarea>
                    
                    <textarea id="mapDataKML" style="display:none;" >
                    </textarea>
                    
					<textarea name="jsonData" id= "jsonString" style="display:none;"></textarea>
					<textarea name="kmlData" id= "kmlString" style="display:none;" ></textarea>
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
				            <input type="checkbox" name="eligible_checkbox_able_bodied" id="tab-eligibility-field-6" value="6" <c:if test="${not empty isAble_bodied}"><c:out value="checked"/></c:if> disabled /> 
				            <input type="checkbox" name="_eligible_checkbox_able_bodied" style="display:none;" value="6" <c:if test="${not empty isAble_bodied}"><c:out value="checked"/></c:if>  /> 
			                Able bodied
				            </label>
				        </div>
				        <div class="form-group col-lg-12 col-sm-11 col-md-10">
				        	<label label-default="" for="tab-eligibility-field-7">
				            <input type="checkbox" name="eligible_checkbox_mobility_prevents_PT" id="tab-eligibility-field-7" value="7" <c:if test="${not empty isMobility_prevents_PT}"><c:out value="checked"/></c:if> disabled/> 
			                <input type="checkbox" name="_eligible_checkbox_mobility_prevents_PT"  style="display:none;" value="7" <c:if test="${not empty isMobility_prevents_PT}"><c:out value="checked"/></c:if> /> 
			               Mobility prevents use of regular Public Transport
				            </label>
				        </div>
				         <div class="form-group col-lg-12 col-sm-8 col-md-8">
				            <label label-default="" for="tab-eligibility-field-9">
				            <input type="checkbox" name="eligible_checkbox_disable_other" id="tab-eligibility-field-9" value="9" <c:if test="${not empty isDisable_other}"><c:out value="checked"/></c:if> disabled/> 
				            <input type="checkbox" name="_eligible_checkbox_disable_other" style="display:none;" value="9" <c:if test="${not empty isDisable_other}"><c:out value="checked"/></c:if> /> 
				            Disabled other
				            </label>
				         </div>
					</div>   <!-- 1st column-->
				    	<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6"> <!--2nd column-->
				        	<div class="form-group col-lg-12 col-sm-11 col-md-8">
				            <label label-default="" for="tab-eligibility-field-8">
				            <input type="checkbox" name="eligible_checkbox_disable_wheelchair_user" id="tab-eligibility-field-8" value="8" <c:if test="${not empty isDisable_wheelchair_user}"><c:out value="checked"/></c:if> disabled/> 
			                <input type="checkbox" name="_eligible_checkbox_disable_wheelchair_user" style="display:none;" value="8" <c:if test="${not empty isDisable_wheelchair_user}"><c:out value="checked"/></c:if> /> 
			                Disabled wheelchair user
				            </label>
				            </div>
				              <div class="form-group col-lg-12 col-sm-11 col-md-10">
				        	<label label-default="" for="tab-eligibility-field-17">
				            <input type="checkbox" name="eligible_checkbox_cant_live_on_a_bus_route" id="tab-eligibility-field-17" value="17" <c:if test="${not empty cant_live_on_a_bus_route}"><c:out value="checked"/></c:if> disabled/> 
			                <input type="checkbox" name="_eligible_checkbox_cant_live_on_a_bus_route" style="display:none;"  value="17" <c:if test="${not empty cant_live_on_a_bus_route}"><c:out value="checked"/></c:if> /> 
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
			                <input type="checkbox" name="eligible_checkbox_under16" id="tab-eligibility-field-1" value="1" <c:if test="${not empty isAge_under16}"><c:out value="checked"/></c:if> disabled/>  
			                <input type="checkbox" name="_eligible_checkbox_under16"  style="display:none;"  value="1" <c:if test="${not empty isAge_under16}"><c:out value="checked"/></c:if> />  
			                Under 16 years of age 
			                </label>                
			            </div>      
			            <div class="form-group col-lg-12 col-sm-8 col-md-8">
			            	<label label-default="" for="tab-eligibility-field-2">
			                <input type="checkbox" name="eligible_checkbox_17_to_21" id="tab-eligibility-field-2" value="2" <c:if test="${not empty isAge_17to21}"><c:out value="checked"/></c:if> disabled/> 
			                <input type="checkbox" name="_eligible_checkbox_17_to_21" style="display:none;"  value="2" <c:if test="${not empty isAge_17to21}"><c:out value="checked"/></c:if> /> 
			                17 to 21 years of age
			                </label>                                        
			            </div>
			            <div class="form-group col-lg-12 col-sm-8 col-md-8">
			            	<label label-default="" for="tab-eligibility-field-3">
			                <input type="checkbox" name="eligible_checkbox_22_to_54" id="tab-eligibility-field-3" value="3" <c:if test="${not empty isAge_22to54}"><c:out value="checked"/></c:if> disabled/> 
			                <input type="checkbox" name="_eligible_checkbox_22_to_54" style="display:none;" value="3" <c:if test="${not empty isAge_22to54}"><c:out value="checked"/></c:if> /> 
			                22 to 54 years of age
			                </label>
			            </div>
					</div>   <!--1st column-->
			        	<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6"> <!--2nd column-->
			            	<div class="form-group col-lg-12 col-sm-8 col-md-8">
			                <label label-default="" for="tab-eligibility-field-4">
			                <input type="checkbox" name="eligible_checkbox_55_to_59" id="tab-eligibility-field-4" value="4" <c:if test="${not empty isAge_55to59}"><c:out value="checked"/></c:if> disabled/> 
			                <input type="checkbox" name="_eligible_checkbox_55_to_59" style="display:none;" value="4" <c:if test="${not empty isAge_55to59}"><c:out value="checked"/></c:if> /> 
			                55 to 59 years of age
			                </label>
			                </div>
			                <div class="form-group col-lg-12 col-sm-8 col-md-8">
			                <label label-default="" for="tab-eligibility-field-5">
			                <input type="checkbox" name="eligible_checkbox_over60" id="tab-eligibility-field-5" value="5" <c:if test="${not empty isAge_over60}"><c:out value="checked"/></c:if> disabled/> 
			                <input type="checkbox" name="_eligible_checkbox_over60" style="display:none;" value="5" <c:if test="${not empty isAge_over60}"><c:out value="checked"/></c:if> /> 
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
				        <input type="checkbox" name="eligible_checkbox_health_appointment" id="tab-eligibility-field-10" value="10" <c:if test="${not empty isHealth_appointment}"><c:out value="checked"/></c:if> disabled/> 
				        <input type="checkbox" name="_eligible_checkbox_health_appointment" style="display:none;"  value="10" <c:if test="${not empty isHealth_appointment}"><c:out value="checked"/></c:if> /> 
				        Health Appointment
				        </label>                
				        </div>             
				        <div class="form-group col-lg-12 col-sm-8 col-md-8">
				        <label label-default="" for="tab-eligibility-field-11">
				        <input type="checkbox" name="eligible_checkbox_social_care" id="tab-eligibility-field-11" value="11" <c:if test="${not empty isSocial_care}"><c:out value="checked"/></c:if> disabled/> 
				        <input type="checkbox" name="_eligible_checkbox_social_care" style="display:none;" value="11" <c:if test="${not empty isSocial_care}"><c:out value="checked"/></c:if> /> 
				        Social Care
				        </label>                                        
				        </div>
				        <div class="form-group col-lg-12 col-sm-8 col-md-8">
				        <label label-default="" for="tab-eligibility-field-12">
				        <input type="checkbox" name="eligible_checkbox_shopping" id="tab-eligibility-field-12" value="12" <c:if test="${not empty isShopping}"><c:out value="checked"/></c:if> disabled/> 
				        <input type="checkbox" name="_eligible_checkbox_shopping" style="display:none;"  value="12" <c:if test="${not empty isShopping}"><c:out value="checked"/></c:if> /> 
				        Shopping
				        </label>
				        </div>
				        <div class="form-group col-lg-12 col-sm-8 col-md-8">
				        <label label-default="" for="tab-eligibility-field-13">
				        <input type="checkbox" name="eligible_checkbox_leisure_or_visiting_friends" id="tab-eligibility-field-13" value="13" <c:if test="${not empty isLeisure_or_visiting_friends}"><c:out value="checked"/></c:if> disabled/> 
				        <input type="checkbox" name="_eligible_checkbox_leisure_or_visiting_friends" style="display:none;"  value="13" <c:if test="${not empty isLeisure_or_visiting_friends}"><c:out value="checked"/></c:if> /> 
				        Leisure/Visiting Friends
				        </label>
				        </div>
					</div> <!--1st column-->
				    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6"> <!--2nd column-->
				    	<div class="form-group col-lg-12 col-sm-8 col-md-8">
				        	<label label-default="" for="tab-eligibility-field-14">
				            <input type="checkbox" name="eligible_checkbox_school_or_education" id="tab-eligibility-field-14" value="14" <c:if test="${not empty isSchool_or_education}"><c:out value="checked"/></c:if> disabled/> 
				            <input type="checkbox" name="_eligible_checkbox_school_or_education" style="display:none;"  value="14" <c:if test="${not empty isSchool_or_education}"><c:out value="checked"/></c:if> /> 
				            School/Education
				            </label>
				            </div>
				            <div class="form-group col-lg-12 col-sm-8 col-md-8">
				            <label label-default="" for="tab-eligibility-field-15">
				            <input type="checkbox" name="eligible_checkbox_work_or_commuting" id="tab-eligibility-field-15" value="15" <c:if test="${not empty isWork_or_commuting}"><c:out value="checked"/></c:if> disabled/> 
				            <input type="checkbox" name="_eligible_checkbox_work_or_commuting" style="display:none;"  value="15" <c:if test="${not empty isWork_or_commuting}"><c:out value="checked"/></c:if> /> 
				            Work/Commuting
				            </label>
				            </div>
				            <div class="form-group col-lg-12 col-sm-8 col-md-8">
				            <label label-default="" for="tab-eligibility-field-16">
				            <input type="checkbox" name="eligible_checkbox_other_purpose" id="tab-eligibility-field-16" value="16" <c:if test="${not empty isOther_purpose}"><c:out value="checked"/></c:if> disabled/> 
				            <input type="checkbox" name="_eligible_checkbox_other_purpose" style="display:none;"  value="16" <c:if test="${not empty isOther_purpose}"><c:out value="checked"/></c:if> /> 
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
	                <select name="other_elig_type" class="form-control" disabled>
	                		<option value="none">None</option>
							<c:if test="${not empty other_elig_types}">
						  	<c:forEach items="${other_elig_types}" var="type">
						  		<c:choose>
							  	<c:when test="${ not empty other_elig_type and type == other_elig_type}">
							   		<option value="${type}" selected>${type}</option>
							  	</c:when>
							  	<c:otherwise>
							  		<option value="${type}">${type}</option>
							  	</c:otherwise>
								</c:choose>  
							</c:forEach>	
						  	</c:if>
							<option value="other">Other</option>
						</select>
						
					<input type="text" name="_other_elig_type" value="${other_elig_type}" style="display:none;">	
						
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
            		<input type="radio"  name="tab_elig_radioBtns" value="0" <c:if test="${not empty tab_elig_radioBtns and tab_elig_radioBtns == '0'}"><c:out value="checked"/></c:if>  disabled/>
            		Yes
        			</label>
        			<label class="col-md-2">
            		<!--  form:radiobutton path="tab_elig_radioBtns" value="1" checked="checked" disabled/-->
            		<input type="radio" name="tab_elig_radioBtns"  value="1" <c:if test="${not empty tab_elig_radioBtns and tab_elig_radioBtns == '1'}"><c:out value="checked"/></c:if> disabled/>
            		No
        			</label>
        			<label class="col-md-2">
            		<input  type = "radio" name="tab_elig_radioBtns"  value="2" <c:if test="${not empty tab_elig_radioBtns and tab_elig_radioBtns == '2'}"><c:out value="checked"/></c:if>  disabled/>
            		Maybe
        			</label>
        			<input type="text" name="_tab_elig_radioBtns" value="<c:if test="${not empty tab_elig_radioBtns}"><c:out value="${tab_elig_radioBtns}"/></c:if>" style="display:none;" />
        			</div> <!--form group for radio buttons-->
    				</div>
    				
    				<div class="form-group" id="tab-elig-openning-up" style="display:none;">
    				<label class="col-sm-12 control-label">Please explain</label>
                    	<div class="col-sm-12">
                    	<textarea rows="5" cols="5" class="form-control" id="tab-elig-field-18" name="explain_opening_up_elig" placeholder="Please explain openning up your service" disabled><c:if test="${not empty explain_opening_up_elig}"><c:out value="${explain_opening_up_elig}"/></c:if></textarea>   
                    	<textarea rows="5" cols="5" name="_explain_opening_up_elig" style="display:none;"><c:if test="${not empty explain_opening_up_elig}"><c:out value="${explain_opening_up_elig}"/></c:if></textarea>   
                    </div>
    				</div>
                    </fieldset>
                </div>
                <div class="row" style="margin-bottom: 6px;">
    			<div class="col-md-6"></div>
      			<div class="col-md-2 col-md-offset-2"><button type="button" class="btn btn-info" id="edit_elig">Edit this section</button></div>
  				<!--  div class="col-md-2 col-md-offset-1"><button type="button" class="btn btn-info" id="save_elig">Save this section</button></div-->
  				<div class="col-md-2"></div>
				</div>
            	<!--  div class="input-group col-md-offset-9 col-sm-offset-9 col-xs-offset-9">
            	<button type="button" class="btn btn-info" id="save_elig" >Save this section</button>
            	</div--><!--save clear button-->
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
            		<input type="radio"  name="tab_fare_structure_radioBtns"  value="0" <c:if test="${not empty tab_fare_structure_radioBtns and tab_fare_structure_radioBtns == '0'}"><c:out value="checked"/></c:if>   disabled/>
            		Yes
        			</label>
        			<label class="col-md-2">
            		<!-- form:radiobutton path="tab_fare_structure_radioBtns" value="1" checked="checked" disabled/ -->
            		<input type="radio"  name="tab_fare_structure_radioBtns"  value="1" <c:if test="${not empty tab_fare_structure_radioBtns and tab_fare_structure_radioBtns == '1'}"><c:out value="checked"/></c:if> disabled />
            		No
        			</label>
        			<label class="col-md-8">
            		<input type="radio"  name="tab_fare_structure_radioBtns"  value="2" <c:if test="${not empty tab_fare_structure_radioBtns and tab_fare_structure_radioBtns == '2'}"><c:out value="checked"/></c:if> disabled />
            		Donations appreciated
        			</label>
        			
        			<input type="text" name="_tab_fare_structure_radioBtns" value="<c:if test="${not empty tab_fare_structure_radioBtns}"><c:out value="${tab_fare_structure_radioBtns}"/></c:if>" style="display:none;" />
        			
        			</div> <!--form group for radio buttons-->
    				</div>
    		
    				
    				<c:choose>
					    <c:when test="${not empty tab_fare_structure_radioBtns and tab_fare_structure_radioBtns == '0'}">
							<div id= "tab-fare-structure-form" style="display:block;">
					    </c:when>
					    <c:otherwise>
							<div id= "tab-fare-structure-form" style="display:none;">
					    </c:otherwise>      
					</c:choose>
					<div class="form-group" >
        			<label class=" control-label" style="margin-top:10px;"> How do you charge fares?</label>
       				<div class="form-group"> <!--form group for radio buttons-->
          			<label class="col-md-2">
            		<input type="radio"   name="tab_fare_structure_how_charge_radioBtns"   value="0" <c:if test="${not empty tab_fare_structure_how_charge_radioBtns and tab_fare_structure_how_charge_radioBtns == '0'}"><c:out value="checked"/></c:if> disabled />
            		Flat fare
        			</label>
        			<label class="col-md-2">
            		<input type="radio"  name="tab_fare_structure_how_charge_radioBtns"   value="1" <c:if test="${not empty tab_fare_structure_how_charge_radioBtns and tab_fare_structure_how_charge_radioBtns == '1'}"><c:out value="checked"/></c:if>  disabled/>
            		Zonal fare
        			</label>
        			<label class="col-md-8">
            		<input type="radio"  name="tab_fare_structure_how_charge_radioBtns" value="2" <c:if test="${not empty tab_fare_structure_how_charge_radioBtns and tab_fare_structure_how_charge_radioBtns == '2'}"><c:out value="checked"/></c:if>  disabled/>
            		Fare per mile
        			</label>
        			<input type="text" name="_tab_fare_structure_how_charge_radioBtns" value="<c:if test="${not empty tab_fare_structure_how_charge_radioBtns}"><c:out value="${tab_fare_structure_how_charge_radioBtns}"/></c:if>" style="display:none;" />
        			
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
						<div class="col-md-3"><label>0 mile</label></div>
						<div class="col-md-3">
						<input name="fare_dist1_mile_2" type="text" class="form-control" placeholder="1.0" value="<c:if test="${empty distance1_mile}"><c:out value="1"/></c:if><c:if test="${not empty distance1_mile}"><c:out value="${distance1_mile}"/></c:if>" disabled />
						<input name="_fare_dist1_mile_2" type="text" style="display:none;" value="<c:if test="${empty distance1_mile}"><c:out value="1"/></c:if><c:if test="${not empty distance1_mile}"><c:out value="${distance1_mile}"/></c:if>"  />
						</div>
						<div class="col-md-3">
						<select name= "fare_dist1_type" class="form-control" disabled>
								<c:choose>
							  	<c:when test="${ not empty fare_dist1_type and fare_dist1_type == 'fare per mile'}">
							   		<option value="flat rate"  >Flat rate</option>
									<option value="fare per mile" selected >Fare per mile</option>
							  	</c:when>
							  	<c:otherwise>
							  		<option value="flat rate"  >Flat rate</option>
									<option value="fare per mile" >Fare per mile</option>
							  	</c:otherwise>
								</c:choose>  
						</select>
						<input name="_fare_dist1_type" type="text" style="display:none;" value="<c:if test="${not empty fare_dist1_type}"><c:out value="${fare_dist1_type}"/></c:if>" />
							
						 </div>
						 <div class="col-md-3">£ 
						 <input name = "fare_dist1_charge" type="text" class="form-control charge"  placeholder="0.00" value="<c:if test="${empty dist1_charge}"><c:out value="0"/></c:if><c:if test="${not empty dist1_charge}"><c:out value="${dist1_charge}"/></c:if>" disabled/>
						 <input name = "_fare_dist1_charge" type="text" style="display:none;" value="<c:if test="${empty dist1_charge}"><c:out value="0"/></c:if><c:if test="${not empty dist1_charge}"><c:out value="${dist1_charge}"/></c:if>" />
						 
						 <span class="question" data-toggle="tooltip" data-placement="right" title="If you have flat rate, please specify 0 mile - 1 mile as flat rate.">?</span>
						 </div>
					</div>
					
					<!--  div class="row col-lg-12 col-sm-12 col-xs-12">
						<div class="col-md-3 ">
						<input name="fare_dist2_mile_1" type="text" class="form-control" placeholder="1.0" 
						value="<c:if test="${empty dist2_mile_1}"><c:out value="0"/></c:if><c:if test="${not empty dist2_mile_1}"><c:out value="${dist2_mile_1}"/></c:if>" disabled/>
						<input name="_fare_dist2_mile_1" type="text" style="display:none;" value="<c:if test="${empty dist2_mile_1}"><c:out value="0"/></c:if><c:if test="${not empty dist2_mile_1}"><c:out value="${dist2_mile_1}"/></c:if>" />
						</div>
						<div class="col-md-3">
						<input name="fare_dist2_mile_2" type="text" class="form-control" placeholder="2.0" 
						value="<c:if test="${empty dist2_mile_2}"><c:out value="0"/></c:if><c:if test="${not empty dist2_mile_2}"><c:out value="${dist2_mile_2}"/></c:if>" disabled/>
						<input name="_fare_dist2_mile_2" type="text" style="display:none;" value="<c:if test="${empty dist2_mile_2}"><c:out value="0"/></c:if><c:if test="${not empty dist2_mile_2}"><c:out value="${dist2_mile_2}"/></c:if>" />
						</div>
						<div class="col-md-3">
					  	<select name="fare_dist2_type" class="form-control" disabled>
					  			<c:choose>
							  	<c:when test="${ not empty fare_dist2_type and fare_dist2_type == 'fare per mile'}">
							   		<option value="flat rate"  >Flat rate</option>
									<option value="fare per mile" selected >Fare per mile</option>
							  	</c:when>
							  	<c:otherwise>
							  		<option value="flat rate"  >Flat rate</option>
									<option value="fare per mile" >Fare per mile</option>
							  	</c:otherwise>
								</c:choose>  
						</select>
						<input name="_fare_dist2_type" type="text" style="display:none;" value="<c:if test="${not empty fare_dist2_type}"><c:out value="${fare_dist2_type}"/></c:if>" />
						</div>
						<div class="col-md-3">£ <input name="fare_dist2_charge" type="text" class="form-control charge" placeholder="0.00" value="<c:if test="${empty dist2_charge}"><c:out value="0"/></c:if><c:if test="${not empty dist2_charge}"><c:out value="${dist2_charge}"/></c:if>"  disabled/>
						<input name="_fare_dist2_charge" type="text" style="display:none;" value="<c:if test="${not empty dist2_charge}"><c:out value="${dist2_charge}"/></c:if>"  /></div>
					</div-->
					
					<c:if test="${not empty fare_dist_band}">
					
					<c:forEach items="${fare_dist_band}" var="band" varStatus="status">
						<div class="row col-lg-12 col-sm-12 col-xs-12" >
							<div class="col-md-3 ">
							<input name="fare_dist${status.index +2}_mile_1" type="text" class="form-control" placeholder="2.0" value="${band.getMile_1()}" disabled readonly/>
							<input name="_fare_dist${status.index +2}_mile_1" type="text" style="display:none;"  value="${band.getMile_1()}" />
							</div>
							<div class="col-md-3">
							<input name="fare_dist${status.index+2}_mile_2" type="text" class="form-control" placeholder="2.0" value="${band.getMile_2()}" disabled/>
							<input name="_fare_dist${status.index+2}_mile_2" type="text" style="display:none;" value="${band.getMile_2()}" />
							</div>
							<div class="col-md-3">
						  	<select name="fare_dist${status.index+2}_type" class="form-control" disabled>
						  			<c:choose>
								  	<c:when test="${ band.getType() == 'fare per mile'}">
								   		<option value="flat rate" >Flat rate</option>
										<option value="fare per mile" selected >Fare per mile</option>
								  	</c:when>
								  	<c:otherwise>
								  		<option value="flat rate"  >Flat rate</option>
										<option value="fare per mile" >Fare per mile</option>
								  	</c:otherwise>
									</c:choose>  
							</select>
							<input type="text" name="_fare_dist${status.index+2}_type" style="display:none;" value="${ band.getType()}" >
							</div>
							<div class="col-md-3">£ 
							<input name = "fare_dist${status.index+2}_charge" type="text" class="form-control charge" value="${ band.getCharge()}" placeholder="0.00" disabled/>
							<input name = "_fare_dist${status.index+2}_charge" type="text" style="display:none;" value="${ band.getCharge()}" />
							</div>
						</div>
					</c:forEach>
					</c:if>
					<!-- div id="fare_distance_buffer_1" class="row col-lg-12 col-sm-12 col-xs-12" >
						<div class="col-md-3 ">
						<input name="fare_dist3_mile_1" type="text" class="form-control" placeholder="2.0" value="<c:if test="${empty dist3_mile_1}"><c:out value="0"/></c:if><c:if test="${not empty dist3_mile_1}"><c:out value="${dist3_mile_1}"/></c:if>" disabled/>
						<input name="_fare_dist3_mile_1" type="text" style="display:none;"  value="<c:if test="${not empty dist3_mile_1}"><c:out value="${dist3_mile_1}"/></c:if>" />
						</div>
						<div class="col-md-3">
						<input name="fare_dist3_mile_2" type="text" class="form-control" placeholder="2.0" value="<c:if test="${empty dist3_mile_2}"><c:out value="0"/></c:if><c:if test="${not empty dist3_mile_2}"><c:out value="${dist3_mile_2}"/></c:if>" disabled/>
						<input name="_fare_dist3_mile_2" type="text" style="display:none;" value="<c:if test="${not empty dist3_mile_2}"><c:out value="${dist3_mile_2}"/></c:if>" />
						</div>
						<div class="col-md-3">
					  	<select name="fare_dist3_type" class="form-control" disabled>
					  			<c:choose>
							  	<c:when test="${ not empty fare_dist3_type and fare_dist3_type == 'fare per mile'}">
							   		<option value="flat rate" >Flat rate</option>
									<option value="fare per mile" selected >Fare per mile</option>
							  	</c:when>
							  	<c:otherwise>
							  		<option value="flat rate"  >Flat rate</option>
									<option value="fare per mile" >Fare per mile</option>
							  	</c:otherwise>
								</c:choose>  
						</select>
						<input type="text" name="_fare_dist3_type" style="display:none;" value="<c:if test="${not empty fare_dist3_type}"><c:out value="${fare_dist3_type}"/></c:if>" >
						</div>
						<div class="col-md-3">£ 
						<input name = "fare_dist3_charge" type="text" class="form-control charge" value="<c:if test="${empty dist3_charge}"><c:out value="0"/></c:if><c:if test="${not empty dist3_charge}"><c:out value="${dist3_charge}"/></c:if>" placeholder="0.00" disabled/>
						<input name = "_fare_dist3_charge" type="text" style="display:none;" value="<c:if test="${not empty dist3_charge}"><c:out value="${dist3_charge}"/></c:if>" />
						</div>
					</div>
					<div id="fare_distance_buffer_2" class="row col-lg-12 col-sm-12 col-xs-12">
						<div class="col-md-3 ">
						<input name="fare_dist4_mile_1" type="text" class="form-control" placeholder="3.0" 
						value="<c:if test="${ empty dist4_mile_1}"><c:out value="0"/></c:if><c:if test="${not empty dist4_mile_1}"><c:out value="${dist4_mile_1}"/></c:if>" disabled/>
						<input name="_fare_dist4_mile_1" type="text" style="display:none;"  value="<c:if test="${not empty dist4_mile_1}"><c:out value="${dist4_mile_1}"/></c:if>" />
						</div>
						<div class="col-md-3">
						<input name="fare_dist4_mile_2" type="text" class="form-control" placeholder="5.0" 
						value="<c:if test="${empty dist4_mile_2}"><c:out value="0"/></c:if><c:if test="${not empty dist4_mile_2}"><c:out value="${dist4_mile_2}"/></c:if>" disabled/>
						<input name="_fare_dist4_mile_2" type="text" style="display:none;"  value="<c:if test="${not empty dist4_mile_2}"><c:out value="${dist4_mile_2}"/></c:if>" />
						</div>
						<div class="col-md-3">
					  	<select name="fare_dist4_type" class="form-control" disabled>
					  			<c:choose>
							  	<c:when test="${ not empty fare_dist4_type and fare_dist4_type == 'fare per mile'}">
							   		<option value="flat rate">Flat rate</option>
									<option value="fare per mile" selected >Fare per mile</option>
							  	</c:when>
							  	<c:otherwise>
							  		<option value="flat rate">Flat rate</option>
									<option value="fare per mile" >Fare per mile</option>
							  	</c:otherwise>
								</c:choose>  
						</select>
						<input type="text" name="_fare_dist4_type" style="display:none;" 
						value="<c:if test="${not empty fare_dist4_type}"><c:out value="${fare_dist4_type}"/></c:if>">
						</div>
					  	<div class="col-md-3">£ 
					  	<input name = "fare_dist4_charge" type="text"  class="form-control charge" 
					  	value="<c:if test="${empty dist4_charge}"><c:out value="0"/></c:if><c:if test="${not empty dist4_charge}"><c:out value="${dist4_charge}"/></c:if>" placeholder="0.00" disabled/>
					  	<input name = "_fare_dist4_charge" type="text" style="display:none;" value="<c:if test="${not empty dist4_charge}"><c:out value="${dist4_charge}"/></c:if>" />
					  	</div>
					</div -->
					
					</div>
					<div class="row col-lg-12 col-sm-12 col-xs-12">
						<div class="col-md-2 ">
					  	<button type="button" class="btn btn-info btn-sm addMore" data-toggle="#fare_structure" disabled>
                		Add more
            			</button>
            			<input type="text" name="fare_dist_ind" value = "<c:if test="${not empty fare_dist_ind}"><c:out value="${fare_dist_ind}"/></c:if><c:if test="${empty fare_dist_ind}"><c:out value="1"/></c:if>" style="display:none;" >
            			<input type="text" name="_fare_dist_ind" value = "<c:if test="${not empty fare_dist_ind}"><c:out value="${fare_dist_ind}"/></c:if><c:if test="${empty fare_dist_ind}"><c:out value="1"/></c:if>" style="display:none;" >
            			
            			<input type="text" name="added_fare_dist" value = "added_fare_dist" style="display:none;" >
            			</div>
            			<div class="col-md-2" style="margin-left:-20px;">
            			<button type="button" class="btn btn-info btn-sm addMore" data-toggle="#fare_structure_del" disabled>
                		Delete
            			</button>
            			</div>
						<div class="col-md-8 "></div>
					</div>
               		</fieldset>
               		<fieldset id = "zonal_charge" style="display:none; border: 0px solid #CCC;" >
               		Dear Operator, You have selected zonal fare. Please note that we will be unable to estimate fares for your passengers.
               		</fieldset>
            		<fieldset>
                	<legend>Other:</legend>
                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6"> <!--1st column-->
						<div class="form-group col-lg-12 col-sm-12 col-xs-12 col-md-12">
                        <label for="tab-fare-structure-dist-field-9" class="col-sm-6 col-xs-6 col-md-6">Return fare multiplier:</label>
                        <div class="col-xs-4">
                        <input type= "text" class="form-control" id="tab-fare-structure-dist-field-9" name = "return_fare_multiplier" placeholder="2.0" value = "<c:if test="${empty return_fare_multiplier}"><c:out value="2"/></c:if><c:if test="${not empty return_fare_multiplier}"><c:out value="${return_fare_multiplier}"/></c:if>" disabled/>
                        <input type= "text" name = "_return_fare_multiplier" style="display:none;" value = "<c:if test="${not empty return_fare_multiplier}"><c:out value="${return_fare_multiplier}"/></c:if>" />
                        </div>
                        </div>
                        <div class="form-group col-lg-12 col-sm-12 col-xs-12 col-md-12">
                        <label for="tab-fare-structure-dist-field-10" class="col-sm-6 col-xs-6 col-md-6">Discount % for over 60s:</label>
                        <div class="col-xs-4">
                        <input type= "text" class="form-control discount" id="tab-fare-structure-dist-field-10" name = "discount_for_over60" placeholder="0" value = "<c:if test="${empty discount_for_over60}"><c:out value="0"/></c:if><c:if test="${not empty discount_for_over60}"><c:out value="${discount_for_over60}"/></c:if>" disabled/>%
                        <input type= "text" name = "_discount_for_over60" style="display:none;" value = "<c:if test="${not empty discount_for_over60}"><c:out value="${discount_for_over60}"/></c:if>" />
                        </div>
                        </div>
                        <div class="form-group col-lg-12 col-sm-12 col-xs-12 col-md-12">
                        <label for="tab-fare-structure-dist-field-11" class="col-sm-6 col-xs-6 col-md-6">Discount % for under 16s:</label>
                        <div class="col-xs-4">
                        <input type= "text" class="form-control discount" id="tab-fare-structure-dist-field-11" name = "discount_for_under16" placeholder="0" value = "<c:if test="${empty discount_for_under16}"><c:out value="0"/></c:if><c:if test="${not empty discount_for_under16}"><c:out value="${discount_for_under16}"/></c:if>" disabled/>%
                        <input type= "text" name = "_discount_for_under16" style="display:none;" value = "<c:if test="${not empty discount_for_under16}"><c:out value="${discount_for_under16}"/></c:if>" />
                        </div>
                        </div>
                        <div class="form-group col-lg-12 col-sm-12 col-xs-12 col-md-12">
                        <label for="tab-fare-structure-dist-field-14" class="col-sm-6 col-xs-6 col-md-6">Discount % for other concessionary passengers:</label>
                        <div class="col-xs-4">
                        <!-- discount_for_other_concessionary tab-fare-structure-dist-field-14 -->
                        <input type= "text" class="form-control discount" id="discount_for_other_concessionary" name = "discount_for_other_concessionary" placeholder="0" value = "<c:if test="${ empty discount_for_other_concessionary}"><c:out value="0"/></c:if><c:if test="${not empty discount_for_other_concessionary}"><c:out value="${discount_for_other_concessionary}"/></c:if>" disabled/>%
                        <input type= "text" name = "_discount_for_other_concessionary"  style="display:none;" value = "<c:if test="${not empty discount_for_other_concessionary}"><c:out value="${discount_for_other_concessionary}"/></c:if>" />
                        </div>
                        </div>
                     </div><!--1st column-->
                     <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6"> <!--2nd column-->
                      	<div class="form-group col-lg-12 col-sm-12 col-xs-12 col-md-12">
                        <label for="tab-fare-structure-dist-field-12" class="col-sm-12 col-xs-12 col-md-12">
                        <input type="checkbox" name="fare_structure_checkbox_escort" id="tab-fare-structure-dist-field-12" value="true" <c:if test="${not empty isFare_structure_checkbox_escort}"><c:out value="checked"/></c:if> disabled/> Please tick if escorts are charged a fare?</label>
                        <input type="checkbox" name="_fare_structure_checkbox_escort" style="display:none;"  value="true" <c:if test="${not empty isFare_structure_checkbox_escort}"><c:out value="checked"/></c:if> /> 
                        </div>
                        <div class="form-group col-lg-12 col-sm-12 col-xs-12 col-md-12">
                        <label for="tab-fare-structure-dist-field-13" class="col-sm-12 col-xs-12 col-md-12">
                        <input type="checkbox" name="fare_structure_checkbox_charge_for_dead_mileage" id="tab-fare-structure-dist-field-13" value="true"  <c:if test="${not empty isFare_structure_checkbox_charge_for_dead_mileage}"><c:out value="checked"/></c:if> disabled/> Please tick if this service charges for dead mileage?</label>
                        <input type="checkbox" name="_fare_structure_checkbox_charge_for_dead_mileage" style="display:none;"  value="true"  <c:if test="${not empty isFare_structure_checkbox_charge_for_dead_mileage}"><c:out value="checked"/></c:if> /> 
                        </div>
                     </div><!--2nd column-->
                     </fieldset>
                     <!--  /div-->
					</div>
					<div class="row" style="margin-bottom: 6px;">
	    			<div class="col-md-6"></div>
	      			<div class="col-md-2 col-md-offset-2"><button type="button" class="btn btn-info" id="edit_fare">Edit this section</button></div>
	  				<!--  div class="col-md-2 col-md-offset-1"><button type="button" class="btn btn-info" id="save_fare">Save this section</button></div-->
	  				<div class="col-md-2"></div>
					</div>
					<!--  div class="input-group col-md-offset-9 col-sm-offset-9 col-xs-offset-9">
            		<button type="button" class="btn btn-info" id="save_fare">Save this section</button>
            		</div--><!--save clear button-->
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
            <!--  <button type="submit" class="btn-lg btn-warning">Preview</button>-->
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
   	<script src="<c:url value="/resources/js/FITS_EDIT.js" />"></script> 
    
    <script type="text/javascript">
	//fix the map showing problem
	window.onload = function() {
    	//$("#collapseTwo").removeClass("in");
    	//$("#collapseOne").addClass("in");
    	//$('a[href="#collapseOne"]').addClass("highlight");
    	
    	//fix the map showing problem
    	//BlitzMap.setMap( 'myMap', true, 'mapData' );
    	//BlitzMap.toggleEditable();// swith not to be editable
    	//BlitzMap.init();// initialize BlitzMap
    	//BlitzMap.setEditable(false);
    	
   
    	// put it after the init of the map
    	$("#collapseTwo").removeClass("in");
    	$("#collapseOne").addClass("in");
    	$('a[href="#collapseOne"]').addClass("highlight");
    	
    	var radioVal = $("input[name='tab_elig_radioBtns']:checked").val();
    	  if(radioVal==1){
    	      $("#tab-elig-openning-up").fadeOut('slow');
    	  }
    	  else{
    		  $("#tab-elig-openning-up").fadeIn('slow');
    	  }
    	  radioVal = $("input[name='tab_fare_structure_radioBtns']:checked").val();
      	  if(radioVal==1){
      	      $("#tab-fare-structure-form").fadeOut('slow');
      	  }
      	  else{
      		  $("#tab-fare-structure-form").fadeIn('slow');
      	  }
    	
	};
	
	
	function doTasksBeforeSubmission(){
		confirmMapChanges();
	}
	//var radioVal = $("input[name$='tab_elig_radioBtns']").val();
	//  if(radioVal==1){
	//      $("#tab-elig-openning-up").fadeOut('slow');
	  //}
	 // else{
	//	  $("#tab-elig-openning-up").fadeIn('slow');
	 // }
	</script>
      
</body>
</html>