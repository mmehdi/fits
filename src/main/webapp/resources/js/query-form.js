		$(function() {
			$( "#sortable" ).sortable();
			$( "#sortable" ).disableSelection();
			$( "#sortable2" ).sortable();
			$( "#sortable2" ).disableSelection();
		});
		
		$(document).ready(function() {
			$("#outward_slct_home_orgn").change(function() {
				if($('#outward_slct_home_dstn').is(':checked')){
					$.prompt("Your home address has already been selected as your outward destination. Do you want to change your original address as your home address? ", {
						title: "Replace the outward original address as your home address.",
						buttons: { "Yes, I do.": true, "No, I don't.": false },
						submit: function(e,v,m,f){
							if(v){
								$("#outward_slct_home_orgn").prop('checked', true);
								$("#outward_slct_home_dstn").prop('checked', false);
								fillOutwardOrgnAsHomeAddr();
								removeOutwardDstnAsHomeAddr();
							}else{
								if($('#outward_slct_home_orgn').is(':checked')){
									$("#outward_slct_home_orgn").prop('checked', false);
								}
							}
						}
					});
				}else{
					fillOutwardOrgnAsHomeAddr();// fill 
				}
				if(!$('#outward_slct_home_orgn').is(':checked')){
					removeOutwardOrgnAsHomeAddr();
				}
			});
			$("#outward_slct_home_dstn").change(function() {
				if($('#outward_slct_home_orgn').is(':checked')){
					$.prompt("Your home address has already been selected as your outward original address. Do you want to change your destination address as your home address? ", {
						title: "Replace the outward destination address as your home address.",
						buttons: { "Yes, I do.": true, "No, I don't.": false },
						submit: function(e,v,m,f){
							if(v){
								$("#outward_slct_home_dstn").prop('checked', true);
								$("#outward_slct_home_orgn").prop('checked', false);
								fillOutwardDstnAsHomeAddr();
								removeOutwardOrgnAsHomeAddr();
							}else{
								if($("#outward_slct_home_dstn").is(':checked')){
									$("#outward_slct_home_dstn").prop('checked', false);
								}
							}
						}
					});
				}else{
					fillOutwardDstnAsHomeAddr(); // fill
				}
				if(!$('#outward_slct_home_dstn').is(':checked')){
					removeOutwardDstnAsHomeAddr();
				}
			});
			$("input[name=return_jrny_required]").change(function() {
				if($('input[name=return_jrny_required]').is(':checked')){
					$("#return_journey").fadeIn('slow');
					$("#time_rtn").fadeIn('slow');
					fillReturnJourney();
				}else{
					$("#return_journey").fadeOut('slow');
					$("#time_rtn").fadeOut('slow');
					removeReturnJourney();
				}
			});
			$("#timepicker").change(function() {
	    		 var time = $("#timepicker").val();
	    		 console.log("timepicker text is changed "+ time);
	    		 if(time.match(/^(([01]?[0-9]|2[0-3])[0-5][0-9])$/)){
	    			 console.log(time.match(/^(([01]?[0-9]|2[0-3])[0-5][0-9])$/));
	    			 time = time.substr(0, 2) +":" +time.substr(2, 3);
		    		 $("#timepicker").val(time);
	    		 }
	    	});
			
			$("#timepicker_rtn").change(function() {
	    		 var time = $("#timepicker_rtn").val();
	    		 console.log("timepicker text is changed "+ time);
	    		 if(time.match(/^(([01]?[0-9]|2[0-3])[0-5][0-9])$/)){
	    			 console.log(time.match(/^(([01]?[0-9]|2[0-3])[0-5][0-9])$/));
	    			 time = time.substr(0, 2) +":" +time.substr(2, 3);
		    		 $("#timepicker_rtn").val(time);
	    		 }
	    	});
			
			$("#return_select_home_orgn").change(function() {
				if($('#return_select_home_dstn').is(':checked')){
					$.prompt("Your home address has already been selected as your return journey's destination. Do you want to change your original address as your home address? ", {
						title: "Replace the return journey's original address as your home address.",
						buttons: { "Yes, I do.": true, "No, I don't.": false },
						submit: function(e,v,m,f){
							if(v){
								$("#return_select_home_orgn").prop('checked', true);
								$("#return_select_home_dstn").prop('checked', false);
								fillReturnOrgnAsHomeAddr();
								removeReturnDstnAsHomeAddr();
							}else{
								if($('#return_select_home_orgn').is(':checked')){
									$("#return_select_home_orgn").prop('checked', false);
								}
							}
						}
					});
				}else{
					fillReturnOrgnAsHomeAddr();// fill 
				}
				
				if(!$('#return_select_home_orgn').is(':checked')){
					removeReturnOrgnAsHomeAddr();
				}
		});
		$("#return_select_home_dstn").change(function() {
			if($('#return_select_home_orgn').is(':checked')){
				$.prompt("your home address has already been selected as your return journey's original address. Do you want to change your destination address as your home address? ", {
					title: "Replace the return journey's destination address as your home address.",
					buttons: { "Yes, I do.": true, "No, I don't.": false },
					submit: function(e,v,m,f){
						if(v){
							$("#return_select_home_dstn").prop('checked', true);
							$("#return_select_home_orgn").prop('checked', false);
							fillReturnDstnAsHomeAddr();
							removeReturnOrgnAsHomeAddr();
						}else{
							if($("#return_select_home_dstn").is(':checked')){
								$("#return_select_home_dstn").prop('checked', false);
							}
						}
					}
				});
			}else{
				fillReturnDstnAsHomeAddr(); // fill
			}
			if(!$('#return_select_home_dstn').is(':checked')){
				removeReturnDstnAsHomeAddr();
			}
		});
		$('select[name=mobility_status]').change(function() { 
			checkMobilityStatusWheelchairUser();
		});
		$('#wheelchair_user').hide();
		});
		
		function fillReturnJourney(){
			// fill return origin address
			$("#street_number_f_return").val($("#street_number_t_outward").val());
			$("#route_f_return").val($("#route_t_outward").val());
			$("#locality_f_return").val($("#locality_t_outward").val());
			$("#postal_code_f_return").val($("#postal_code_t_outward").val());
			$("#street_number_f_return").prop('disabled', false);
			$("#route_f_return").prop('disabled', false);
			$("#locality_f_return").prop('disabled', false);
			$("#postal_code_f_return").prop('disabled', false);
			// fill return destination address
			$("#street_number_t_return").val($("#street_number_f_outward").val());
			$("#route_t_return").val($("#route_f_outward").val());
			$("#locality_t_return").val($("#locality_f_outward").val());
			$("#postal_code_t_return").val($("#postal_code_f_outward").val());
			$("#street_number_t_return").prop('disabled', false);
			$("#route_t_return").prop('disabled', false);
			$("#locality_t_return").prop('disabled', false);
			$("#postal_code_t_return").prop('disabled', false);
		}
		function removeReturnJourney(){
			// remove return original address
			$("#street_number_f_return").val("");
			$("#route_f_return").val("");
			$("#locality_f_return").val("");
			$("#postal_code_f_return").val("");
			// remove return destination address
			$("#street_number_t_return").val("");
			$("#route_t_return").val("");
			$("#locality_t_return").val("");
			$("#postal_code_t_return").val("");
			$("#autocomplete_f_return").val("");
			$("#autocomplete_t_return").val("");
		}
		function fillOutwardOrgnAsHomeAddr() {
			// replace the outward - original address
			$("#street_number_f_outward").val($("#addr_line1").val());
			$("#route_f_outward").val($("#addr_line2").val());
			$("#locality_f_outward").val($("#addr_line3").val());
			$("#postal_code_f_outward").val($("#postcode").val());
			$("#street_number_f_outward").prop('disabled', false);
			$("#route_f_outward").prop('disabled', false);
			$("#locality_f_outward").prop('disabled', false);
			$("#postal_code_f_outward").prop('disabled', false);
		}
		function removeOutwardOrgnAsHomeAddr() {
			$("#street_number_f_outward").val("");
			$("#route_f_outward").val("");
			$("#locality_f_outward").val("");
			$("#postal_code_f_outward").val("");
		}
		function fillOutwardDstnAsHomeAddr() {
			// replace the outward - destination address
			$("#street_number_t_outward").val($("#addr_line1").val());
			$("#route_t_outward").val($("#addr_line2").val());
			$("#locality_t_outward").val($("#addr_line3").val());
			$("#postal_code_t_outward").val($("#postcode").val());
			$("#street_number_t_outward").prop('disabled', false);
			$("#route_t_outward").prop('disabled', false);
			$("#locality_t_outward").prop('disabled', false);
			$("#postal_code_t_outward").prop('disabled', false);
		}
		function removeOutwardDstnAsHomeAddr() {
			$("#street_number_t_outward").val("");
			$("#route_t_outward").val("");
			$("#locality_t_outward").val("");
			$("#postal_code_t_outward").val("");
		}
		function fillReturnOrgnAsHomeAddr() {
			// replace the return - original address
			$("#street_number_f_return").val($("#addr_line1").val());
			$("#route_f_return").val($("#addr_line2").val());
			$("#locality_f_return").val($("#addr_line3").val());
			$("#postal_code_f_return").val($("#postcode").val());
			$("#street_number_f_return").prop('disabled', false);
			$("#route_f_return").prop('disabled', false);
			$("#locality_f_return").prop('disabled', false);
			$("#postal_code_f_return").prop('disabled', false);
		}
		function removeReturnOrgnAsHomeAddr() {
			$("#street_number_f_return").val("");
			$("#route_f_return").val("");
			$("#locality_f_return").val("");
			$("#postal_code_f_return").val("");
		}
		function fillReturnDstnAsHomeAddr() {
			// replace the return - destination address
			$("#street_number_t_return").val($("#addr_line1").val());
			$("#route_t_return").val($("#addr_line2").val());
			$("#locality_t_return").val($("#addr_line3").val());
			$("#postal_code_t_return").val($("#postcode").val());
			//$("#autocomplete_t_return").prop('disabled', true);
			$("#street_number_t_return").prop('disabled', false);
			$("#route_t_return").prop('disabled', false);
			$("#locality_t_return").prop('disabled', false);
			$("#postal_code_t_return").prop('disabled', false);
		}
		function removeReturnDstnAsHomeAddr() {
			$("#street_number_t_return").val("");
			$("#route_t_return").val("");
			$("#locality_t_return").val("");
			$("#postal_code_t_return").val("");
		}
		function checkMobilityStatusWheelchairUser(){
			var str = $('select[name=mobility_status]').val();
			console.log("checkMobilityStatusWheelchairUser: "+ str);
			if (str.toLowerCase().indexOf("wheelchair") >= 0 && str.toLowerCase().indexOf("disabled") >= 0){
				$.prompt("Are you able to get out of wheelchair to travel?", {
					title: "Dialog",
					buttons: { "Yes, I am.": true, "No, I am not.": false },
					submit: function(e,v,m,f){
						if(v){
							//document.getElementById('able_travel_outof_wheelchair1').checked  = true;
							$('input[name=able_travel_outof_wheelchair]').prop('checked', true);
							$('#wheelchair_user').fadeIn('slow');
							
						}else{
							$('input[name=able_travel_outof_wheelchair]').prop('checked', false);
							$('#wheelchair_user').fadeIn('slow');
						}
					}
				});
			}else{
				$('#wheelchair_user').fadeOut('slow');
			}
		}