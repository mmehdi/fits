$(document).ready(function() {
		
	$("#tab_fare_structure_form_radioBtns").change();
	    $("input[name$='tab_fare_structure_radioBtns']").click(function() {
	        var radioVal = $(this).val();
	      if(radioVal==0){
	          $("#tab-fare-structure-form").fadeIn('slow');
	      }
	      else{
	    	  $("#tab-fare-structure-form").fadeOut('slow');
	      }
	    });
	    
	    $("input[name$='tab_elig_radioBtns']").click(function() {
	        var radioVal = $(this).val();
	      if(radioVal==1){
	          $("#tab-elig-openning-up").fadeOut('slow');
	      }
	      else{
	    	  $("#tab-elig-openning-up").fadeIn('slow');
	      }
	    });
	    
	    $("input[name$='tab_fare_structure_how_charge_radioBtns']").click(function() {
	        var radioVal = $(this).val();
	      if(radioVal==0){
	          $("#distance_charge").fadeIn('slow');
	          $("#zonal_charge").hide();
	          console.log('tab fare how charge flat rate is selected');
	          $("#fare_dist1_type").val('flat rate');
	          $("#fare_dist2_type").val('flat rate');
	          $("#fare_dist3_type").val('flat rate');
	          $("#fare_dist4_type").val('flat rate');
	      }else if(radioVal==1){
	    	  $("#zonal_charge").fadeIn('slow');
	    	  $("#distance_charge").hide();
	      }else{
	    	  $("#distance_charge").fadeIn('slow');
	    	  $("#zonal_charge").hide();
	    	  console.log('tab fare how charge fare per mile is selected');
	    	  $("#fare_dist1_type").val('fare per mile');
	    	  $("#fare_dist2_type").val('fare per mile');
	    	  $("#fare_dist3_type").val('fare per mile');
	    	  $("#fare_dist4_type").val('fare per mile');
	      }
	    });
	
	    
	  $('#tab-eligibility-field-9-text').hide();
	
	  $('#tab-eligibility-field-9').change(function() {
	      if (this.checked) {
	          $('#tab-eligibility-field-9-text').fadeIn('slow');
	      }
	      else {
	        $('#tab-eligibility-field-9-text').fadeOut('slow');
	      }
	  });
	
	  $('#tab-eligibility-field-16-text').hide();
	
	  $('#tab-eligibility-field-16').change(function() {
	      if (this.checked) {
	          $('#tab-eligibility-field-16-text').fadeIn('slow');
	      }
	      else {
	        $('#tab-eligibility-field-16-text').fadeOut('slow');
	      }
	  });
	
	   $("select[name='vehicle_type']").change(function() {
		   var value = $(this).val(); 
		   console.log('vehicle type '+ value);
		      if (value == 'other') {
	//	    	  $('#'+passenger_elig_id).hide();
		    	  console.log('true');
		          $('#other_vehicle').fadeIn('slow');
		      } else {
		    	  console.log('false');
		        $('#other_vehicle').fadeOut('slow');
		      }
		  });
	   
	   $('#other_elig_type').change(function() {
		   var value = $(this).val(); 
		   console.log('other_elig_type:  '+ value);
		      if (value == 'other') {
		          $("input[name='other_elig']").fadeIn('slow');
		      } else {
		    	  $("input[name='other_elig']").fadeOut('slow');
		      }
		  });
   $(function () {
	   $('[data-toggle="tooltip"]').tooltip();
	 });
});

function createAddedFareDist(){
	var ind = $('input[name="fare_dist_ind"]').val();
	var added_ind = parseInt(ind);
	var text = '';
	for (var i = 5; i <= added_ind; i++) { 
	    text += i;
	    text += ',';
	    text += $('input[name="fare_dist'+i+'_mile_1"]').val();
	    text += ',';
	    text += $('input[name="fare_dist'+i+'_mile_2"]').val();
	    text += ',';
	    text += $('select[name="fare_dist'+i+'_type"]').val();
	    text += ',';
	    text += $('input[name="fare_dist'+i+'_charge"]').val();
	    if(i < added_ind){
	    	text += '#';
	    }
	}
	return text;
}


function createAddedServiceNotAvail(){
	var ind = $('input[name="service_not_avail_ind"]').val();
	var added_ind = parseInt(ind);
	var text = '';
	for (var i = 2; i <= added_ind; i++) { 
	    text += i;
	    text += ',';
	    text += $('input[name="not_valid_from_'+i+'"]').val();
	    text += ',';
	    text += $('input[name="not_valid_to_'+i+'"]').val();
	    if(i < added_ind){
	    	text += '#';
	    }
	}
	console.log("created Added serivce not avail: "+ text);
	return text;
}
	



$(document).ready(function() {
		
	/*
	 * bootstrap form validation
	 * 
	 */
	$('#data_input_form')
	.bootstrapValidator({
		excluded: [':disabled', ':hidden'],
	    message: 'This value is not valid',
	    submitHandler: function(validator, form, submitButton) {
	    	$('input[name="added_fare_dist"]').val(createAddedFareDist());
	    	$('input[name="added_service_not_avail"]').val(createAddedServiceNotAvail());
	    	validator.defaultSubmit();
	    },
	    fields: {
	        service_name: {
	            validators: {
	                notEmpty: {
	                    message: '*&nbsp;The service name is required'
	                }
	            }
	        },
	        service_description: {
	            validators: {
	                notEmpty: {
	                    message: '*&nbsp;The service description is required'
	                }
	            }
	        },
	        fare_dist1_mile_2: {
	            validators: {
	            	between: {
	                    min: 0.000,
	                    max: 100000,
	                    message: '*&nbsp;the mileage must be greater than 0.'
	                }
	            }
	        },
	        fare_dist1_charge: {
	            validators: {
	                between: {
	                    min: 0,
	                    max: 100000,
	                    message: '*&nbsp;the surchage must be greater than or equal to 0.'
	                }
	            }
	        },
	        fare_dist2_mile_1: {
	            validators: {
	            	between: {
	                    min: 0.000,
	                    max: 100000,
	                    message: '*&nbsp;the mileage must be greater than 0.'
	                }
	            }
	        },
	        fare_dist2_mile_2: {
	            validators: {
	            	between: {
	                    min: 0.0000,
	                    max: 100000,
	                    message: '*&nbsp;the mileage must be greater than 0.'
	                }
	            }
	        },
	        fare_dist2_charge: {
	            validators: {
	            	between: {
	                    min: 0,
	                    max: 100000,
	                    message: '*&nbsp;the surchage must be greater than or equal to 0.'
	                }
	            }
	        },
	        fare_dist3_mile_1: {
	            validators: {
	            	between: {
	                    min: 0,
	                    max: 100000,
	                    message: '*&nbsp;the mileage must be greater than 0.'
	                }
	            }
	        },
	        fare_dist3_mile_2: {
	            validators: {
	            	between: {
	                    min: 0,
	                    max: 100000,
	                    message: '*&nbsp;the mileage must be greater than 0.'
	                }
	            }
	        },
	        fare_dist3_charge: {
	            validators: {
	            	between: {
	                    min: 0,
	                    max: 100000,
	                    message: '*&nbsp;the surchage must be greater than or equal to 0.'
	                }
	            }
	        },
	        fare_dist4_mile_1: {
	            validators: {
	            	between: {
	                    min: 0,
	                    max: 100000,
	                    message: '*&nbsp;the mileage must be greater than 0.'
	                }
	            }
	        },
	        fare_dist4_mile_2: {
	            validators: {
	            	between: {
	                    min: 0,
	                    max: 100000,
	                    message: '*&nbsp;the mileage must be greater than 0.'
	                }
	            }
	        },
	        fare_dist4_charge: {
	            validators: {
	            	between: {
	                    min: 0,
	                    max: 100000,
	                    message: '*&nbsp;the surchage must be greater than or equal to 0.'
	                }
	            }
	        },
	        return_fare_multiplier: {
	            validators: {
	                between: {
	                    min: 0,
	                    max: 2,
	                    message: '*&nbsp;The return fare multiplier must be greater than 0 but less than 2.'
	                }
	            }
	        },
	        discount_for_over60: {
	            validators: {
	                between: {
	                    min: 0,
	                    max: 100,
	                    message: '*&nbsp;The discount percentage must be between 0 and 100.'
	                }
	            }
	        },
	        discount_for_under16: {
	            validators: {
	            	between: {
	                    min: 0,
	                    max: 100,
	                    message: '*&nbsp;The discount percentage must be between 0 and 100.'
	                }
	            }
	        },
	        surcharge_dist1_mile: {
	            validators: {
	            	between: {
	                    min: 0,
	                    max: 100000,
	                    message: '*&nbsp;the mileage must be greater than 0.'
	                }
	            }
	        },
	        surcharge_dist1_charge: {
	            validators: {
	            	between: {
	                    min: 0,
	                    max: 100000,
	                    message: '*&nbsp;the surchage must be greater than or equal to 0.'
	                }
	            }
	        },
	        surcharge_dist2_1: {
	            validators: {
	            	between: {
	                    min: 0,
	                    max: 100000,
	                    message: '*&nbsp;the mileage must be greater than 0.'
	                }
	            }
	        },
	        surcharge_dist2_2: {
	            validators: {
	            	between: {
	                    min: 0,
	                    max: 100000,
	                    message: '*&nbsp;the mileage must be greater than 0.'
	                }
	            }
	        },
	        surcharge_dist2_charge: {
	            validators: {
	            	between: {
	                    min: 0,
	                    max: 100000,
	                    message: '*&nbsp;the surchage must be greater than or equal to 0.'
	                }
	            }
	        },
	        surcharge_dist3_mile_1: {
	            validators: {
	            	between: {
	                    min: 0,
	                    max: 100000,
	                    message: '*&nbsp;the mileage must be greater than 0.'
	                }
	            }
	        },
	        surcharge_dist3_mile_2: {
	            validators: {
	            	between: {
	                    min: 0,
	                    max: 100000,
	                    message: '*&nbsp;the mileage must be greater than 0.'
	                }
	            }
	        },
	        surcharge_dist3_charge: {
	            validators: {
	            	between: {
	                    min: 0,
	                    max: 100000,
	                    message: '*&nbsp;the surchage must be greater than or equal to 0.'
	                }
	            }
	        },
	        surcharge_dist4_mile_1: {
	            validators: {
	            	between: {
	                    min: 0,
	                    max: 100000,
	                    message: '*&nbsp;the mileage must be greater than 0.'
	                }
	            }
	        },
	        surcharge_dist4_mile_2: {
	            validators: {
	            	between: {
	                    min: 0,
	                    max: 100000,
	                    message: '*&nbsp;the mileage must be greater than 0.'
	                }
	            }
	        },
	        surcharge_dist4_charge: {
	            validators: {
	            	between: {
	                    min: 0,
	                    max: 100000,
	                    message: '*&nbsp;the surchage must be greater than or equal to 0.'
	                }
	            }
	        },
	        surcharge_under_16_num:{
	            validators: {
	            	between: {
	                    min: 0,
	                    max: 100000,
	                    message: '*&nbsp;The surcharge must be greater than or equal to 0.'
	                }
	            }
	        },
	        surcharge_17_21_years_old_num:{
	            validators: {
	            	between: {
		                min: 0,
		                max: 100000,
		                message: '*&nbsp;The surcharge must be greater than or equal to 0.'
	            	}
	            }
	        },
	        surcharge_22_54_years_old_num:{
	            validators: {
	                between: {
	                    min: 0,
	                    max: 100000,
	                    message: '*&nbsp;The surcharge must be greater than or equal to 0.'
	                }
	            }
	        },
	        surcharge_55_59_years_old_num:{
	            validators: {
	                between: {
	                    min: 0,
	                    max: 100000,
	                    message: '*&nbsp;The surcharge must be greater than or equal to 0.'
	                }
	            }
	        },
	        surcharge_over_60_years_old_num: {
	            validators: {
	                between: {
	                    min: 0,
	                    max: 100000,
	                    message: '*&nbsp;The surcharge must be greater than or equal to 0.'
	                }
	            }
	        },
	        surcharge_able_bodied_num: {
	            validators: {
	                between: {
	                    min: 0,
	                    max: 100000,
	                    message: '*&nbsp;The surcharge must be greater than or equal to 0.'
	                }
	            }
	        },
	        surcharge_mobility_prevents_PT_num: {
	            validators: {
	                between: {
	                    min: 0,
	                    max: 100000,
	                    message: '*&nbsp;The surcharge must be greater than or equal to 0.'
	                }
	            }
	        },
	        surcharge_disable_wheelchair_user_num: {
	            validators: {
	                between: {
	                    min: 0,
	                    max: 100000,
	                    message: '*&nbsp;The surcharge must be greater than or equal to 0.'
	                }
	            }
	        },
	        surcharge_disable_other_num: {
	            validators: {
	                between: {
	                    min: 0,
	                    max: 100000,
	                    message: '*&nbsp;The surcharge must be greater than or equal to 0.'
	                }
	            }
	        },
	        surcharge_health_appointment_num: {
	            validators: {
	                between: {
	                    min: 0,
	                    max: 100000,
	                    message: '*&nbsp;The surcharge must be greater than or equal to 0.'
	                }
	            }
	        },
	        surcharge_social_care_num: {
	            validators: {
	                between: {
	                    min: 0,
	                    max: 100000,
	                    message: '*&nbsp;The surcharge must be greater than or equal to 0.'
	                }
	            }
	        },
	        surcharge_shopping_num: {
	            validators: {
	                between: {
	                    min: 0,
	                    max: 100000,
	                    message: '*&nbsp;The surcharge must be greater than or equal to 0.'
	                }
	            }
	        },
	        surcharge_leisure_visiting_friends_num: {
	            validators: {
	                between: {
	                    min: 0,
	                    max: 100000,
	                    message: '*&nbsp;The surcharge must be greater than or equal to 0.'
	                }
	            }
	        },
	        surcharge_school_education_num: {
	            validators: {
	                between: {
	                    min: 0,
	                    max: 100000,
	                    message: '*&nbsp;The surcharge must be greater than or equal to 0.'
	                }
	            }
	        },
	        surcharge_work_commuting_num: {
	            validators: {
	                between: {
	                    min: 0,
	                    max: 100000,
	                    message: '*&nbsp;The surcharge must be greater than or equal to 0.'
	                }
	            }
	        },
	        surcharge_other_purpose_num: {
	            validators: {
	                between: {
	                    min: 0,
	                    max: 100000,
	                    message: '*&nbsp;The surcharge must be greater than or equal to 0.'
	                }
	            }
	        },
	        surcharge_start_05_hours_earlier: {
	            validators: {
	                between: {
	                    min: 0,
	                    max: 100000,
	                    message: '*&nbsp;The surcharge must be greater than or equal to 0.'
	                }
	            }
	        },
	        surcharge_start_1_hour_earlier: {
	            validators: {
	                between: {
	                    min: 0,
	                    max: 100000,
	                    message: '*&nbsp;The surcharge must be greater than or equal to 0.'
	                }
	            }
	        },
	        surcharge_start_15_hours_earlier: {
	            validators: {
	                between: {
	                    min: 0,
	                    max: 100000,
	                    message: '*&nbsp;The surcharge must be greater than or equal to 0.'
	                }
	            }
	        },
	        surcharge_finish_05_hours_later: {
	            validators: {
	                between: {
	                    min: 0,
	                    max: 100000,
	                    message: '*&nbsp;The surcharge must be greater than or equal to 0.'
	                }
	            }
	        },
	        surcharge_finish_1_hour_later: {
	            validators: {
	                between: {
	                    min: 0,
	                    max: 100000,
	                    message: '*&nbsp;The surcharge must be greater than or equal to 0.'
	                }
	            }
	        },
	        surcharge_finish_15_hours_later: {
	            validators: {
	                between: {
	                    min: 0,
	                    max: 100000,
	                    message: '*&nbsp;The surcharge must be greater than or equal to 0.'
	                }
	            }
	        },
	        'fare_dist1[]':{
	        	 validators: {
	             	between: {
	                     min: 0,
	                     max: 100000,
	                     message: '*&nbsp;the mileage must be greater than 0.'
	                 }
	             }
	        },
	    },
	    submitButtons: 'button[type="submit"]',
	})
	.find('button[data-toggle]')
	    .on('click', function() {
	    	console.log("add more button");
	        var target = $(this).attr('data-toggle');
	        // Show or hide the additional fields
	        // They will or will not be validated based on their visibilities
	        if (target == '#fare_structure'){
	        	
	        		var ind = $('input[name="fare_dist_ind"]').val();
	        		var added_ind = parseInt(ind);
	        		console.log("added_ind: " + added_ind);
	        		var div_html = '';
        			div_html += '<div class="row col-lg-12 col-sm-12 col-xs-12 addedMore" id="addedMore'+(added_ind+1)+'">';
        			if($('input[name="fare_dist'+added_ind+'_mile_2"]').val().length >0 ){
        				div_html += '<div class="col-md-3 "><input type="text" class="form-control" name="fare_dist'+(added_ind+1)+'_mile_1" placeholder="" value="'+$('input[name="fare_dist'+added_ind+'_mile_2"]').val()+'" readonly></div>';
        			}else{
        				div_html += '<div class="col-md-3 "><input type="text" class="form-control" name="fare_dist'+(added_ind+1)+'_mile_1" placeholder=""></div>';
        			}
        			div_html +=  '<div class="col-md-3"><input type="text" class="form-control"  name="fare_dist'+(added_ind+1)+'_mile_2" placeholder=""></div>';
        			div_html +=  '<div class="col-md-3">';
        			div_html +=  '<select name = "fare_dist'+(added_ind+1)+'_type" class="form-control">';
        			if($("input[name$='tab_fare_structure_how_charge_radioBtns']:checked").val() == 2){
        				div_html +=  '<option value="flat rate" >Flat rate</option>';
	        			div_html +=  '<option value="fare per mile" selected>Fare per mile</option>';
        			}else{
        				div_html +=  '<option value="flat rate" >Flat rate</option>';
	        			div_html +=  '<option value="fare per mile">Fare per mile</option>';
        			}
        			div_html +=  '</select></div>';
        			div_html +=  '<div class="col-md-3">&pound; <input type="text" name = "fare_dist'+(added_ind+1)+'_charge" class="form-control charge"  placeholder="0.00"></div>';
        			div_html +=  '</div>';
        			$('#fare_table').append(div_html);
	        		$('input[name="fare_dist_ind"]').val(added_ind+1);
	        		
	        		
//	        		var ind = $('input[name="fare_dist_ind"]').val();
//	        		var added_ind = parseInt(ind);
//	        		added_ind = added_ind +1;
//	        		$('#fare_table').append('\
//	                	<div class="row col-lg-12 col-sm-12 col-xs-12">\
//	        				  <div class="col-md-3 "><input type="text" class="form-control" name="fare_dist'+added_ind+'_mile_1" placeholder=""></div>'+
//	        				  '<div class="col-md-3"><input type="text" class="form-control"  name="fare_dist'+added_ind+'_mile_2" placeholder=""></div>'+
//	        				  '<div class="col-md-3">\
//	        				  	<select name = "fare_dist'+added_ind+'_type" class="form-control">' +
//	        					   '<option value="flat rate">Flat rate</option>\
//	        					    <option value="fare per mile">Fare per mile</option>\
//	        					</select></div>\
//	        				  <div class="col-md-3">&pound; <input type="text" name = "fare_dist'+added_ind+'_charge" class="form-control charge"  placeholder="0.00"></div>'+ 
//	        			'</div>'
//	                );
//	        		$('input[name="fare_dist_ind"]').val(added_ind);
	        		
	        		
	        }else if(target == '#fare_structure_del'){
	        	var ind = $('input[name="fare_dist_ind"]').val();
	        	var added_ind = parseInt(ind);
	        	if(added_ind > 1){
	        		$('#addedMore'+ind).remove();
	        		$('input[name="fare_dist_ind"]').val(added_ind-1);
	        	}else{
	        		$.prompt("Can't delete the first row", {
	    				title: "Info",
	    				buttons: { "Yes": true, "No": false },
	    				submit: function(e,v,m,f){
	    					
	    				}
	    			});
	        	}
	        }else if(target == '#service_not_avail'){
	        	var ind = $('input[name="service_not_avail_ind"]').val();
        		var added_ind = parseInt(ind);
        		var div_html = '';
        		div_html += '<div class="row" id = "addedMoreSNA'+(added_ind+1)+'"  style="margin-top:10px;">';
        		div_html += '<div  class="col-md-3 control-label">';
        		div_html += '</div>';
        		div_html += '<div class="col-md-4 control-label">';
        		div_html += '<label for="not_valid_from_'+(added_ind+1)+'" style="margin-right: 13px;">from</label>';
        		
        		if($( "input[name='cb_not_avail']" ).is(":checked")){
        			div_html += '<input name="not_valid_from_'+(added_ind+1)+'" type="text" style="width: 150px;"></input>';
        		}else{
        			div_html += '<input name="not_valid_from_'+(added_ind+1)+'" type="text" style="width: 150px;" disabled></input>';
        		}
        		div_html += '</div>';
        		div_html += '<div class="col-md-5 control-label">';
        		div_html += '<label for="not_valid_to_'+(added_ind+1)+'" style="margin-right: 14px;">to</label>';
        		if($( "input[name='cb_not_avail']" ).is(":checked")){
            		div_html += '<input name="not_valid_to_'+(added_ind+1)+'" type="text" style="width: 150px;" value="" ></input>';

        		}else{
            		div_html += '<input name="not_valid_to_'+(added_ind+1)+'" type="text" style="width: 150px;" value="" disabled></input>';
        		}
        		div_html += '</div>';
        		div_html += '</div>';
        		$('#service_not_avail_table').append(div_html);
        		$('input[name="service_not_avail_ind"]').val(added_ind+1);
        		new_datepicker();
	        }else if(target == '#service_not_avail_del'){
	        	var ind = $('input[name="service_not_avail_ind"]').val();
	        	var added_ind = parseInt(ind);
	        	if(added_ind > 1){
	        		$('#addedMoreSNA'+ind).remove();
	        		$('input[name="service_not_avail_ind"]').val(added_ind-1);
	        	}else{
	        		$.prompt("Can't delete the first row", {
	    				title: "Info",
	    				buttons: { "Yes": true, "No": false },
	    				submit: function(e,v,m,f){
	    					
	    				}
	    			});
	        	}
	        }
	    });
	
//	$('#fare_distance_buffer_1').hide();
//	$('#fare_distance_buffer_2').hide();
//	$('#surcharge_distance_buffer_1').hide();
//	$('#surcharge_distance_buffer_2').hide();
	
	$( "#save_general_info" ).click(function() {
		/* added by cheng*/
		$.prompt(getOperatingTimeSummary(), {
			title: "Please confirm the operating time.",
			buttons: { "Yes": true, "No": false },
			submit: function(e,v,m,f){
				if(v){
					
				}else{
					
				}
			}
		});
	    var myObj = {};
	    myObj["name"] = $( "input[name='service_name']" ).val();
	    myObj["description"] = $( "textarea[name='service_description']" ).val();
	    myObj["how2book"] = $( "textarea[name='how_to_book']" ).val();
	    myObj["cb_not_avail"] = $( "input[name='cb_not_avail']" ).is(":checked");
	    myObj["not_valid_from"] = $( "input[name='not_valid_from']" ).val();
	    myObj["not_valid_to"] = $( "input[name='not_valid_to']" ).val();
	    myObj["oprHouMon1"] = $( "input[name='tab_operating_hours_field_monday_1']" ).val();
	    myObj["oprHouMon2"] = $( "input[name='tab_operating_hours_field_monday_2']" ).val();
	    myObj["oprHouMon3"] = $( "input[name='tab_operating_hours_field_monday_3']" ).val();
	    myObj["oprHouMon4"] = $( "input[name='tab_operating_hours_field_monday_4']" ).val();
	    myObj["oprHouTue1"] = $( "input[name='tab_operating_hours_field_tuesday_1']" ).val();
	    myObj["oprHouTue2"] = $( "input[name='tab_operating_hours_field_tuesday_2']" ).val();
	    myObj["oprHouTue3"] = $( "input[name='tab_operating_hours_field_tuesday_3']" ).val();
	    myObj["oprHouTue4"] = $( "input[name='tab_operating_hours_field_tuesday_4']" ).val();
	    myObj["oprHouWed1"] = $( "input[name='tab_operating_hours_field_wednesday_1']" ).val();
	    myObj["oprHouWed2"] = $( "input[name='tab_operating_hours_field_wednesday_2']" ).val();
	    myObj["oprHouWed3"] = $( "input[name='tab_operating_hours_field_wednesday_3']" ).val();
	    myObj["oprHouWed4"] = $( "input[name='tab_operating_hours_field_wednesday_4']" ).val();
	    myObj["oprHouThu1"] = $( "input[name='tab_operating_hours_field_thursday_1']" ).val();
	    myObj["oprHouThu2"] = $( "input[name='tab_operating_hours_field_thursday_2']" ).val();
	    myObj["oprHouThu3"] = $( "input[name='tab_operating_hours_field_thursday_3']" ).val();
	    myObj["oprHouThu4"] = $( "input[name='tab_operating_hours_field_thursday_4']" ).val();
	    myObj["oprHouFri1"] = $( "input[name='tab_operating_hours_field_friday_1']" ).val();
	    myObj["oprHouFri2"] = $( "input[name='tab_operating_hours_field_friday_2']" ).val();
	    myObj["oprHouFri3"] = $( "input[name='tab_operating_hours_field_friday_3']" ).val();
	    myObj["oprHouFri4"] = $( "input[name='tab_operating_hours_field_friday_4']" ).val();
	    myObj["oprHouSat1"] = $( "input[name='tab_operating_hours_field_saturday_1']" ).val();
	    myObj["oprHouSat2"] = $( "input[name='tab_operating_hours_field_saturday_2']" ).val();
	    myObj["oprHouSat3"] = $( "input[name='tab_operating_hours_field_saturday_3']" ).val();
	    myObj["oprHouSat4"] = $( "input[name='tab_operating_hours_field_saturday_4']" ).val();
	    myObj["oprHouSun1"] = $( "input[name='tab_operating_hours_field_sunday_1']" ).val();
	    myObj["oprHouSun2"] = $( "input[name='tab_operating_hours_field_sunday_2']" ).val();
	    myObj["oprHouSun3"] = $( "input[name='tab_operating_hours_field_sunday_3']" ).val();
	    myObj["oprHouSun4"] = $( "input[name='tab_operating_hours_field_sunday_4']" ).val();
	    $.ajax({
		    url: "/ke/async/save/generalInfo",
		    type: 'POST',
		    dataType: 'json',
		    data: JSON.stringify(myObj),
		    contentType: 'application/json',
		    mimeType: 'application/json',
	    	    success: function(data) {
	    	    },
	    	    error:function(data,status,er) {
	    	        console.log(data);
	    	        console.log(er);
	    	    }
	    	});
	});
	
	var editable_gen_info = false;
	$( "#edit_general_info" ).click(function() {
		console.log("edit general info button is clicked");
		
		if(!editable_gen_info){
			editable_gen_info = true;
		}else{
			editable_gen_info = false;
		}
		if(editable_gen_info){
			$( "input[name='service_name']" ).prop('disabled', false);
		    $( "textarea[name='service_description']" ).prop('disabled', false);
		    $( "textarea[name='how_to_book']" ).prop('disabled', false);
		    $( "input[name='cb_not_avail']" ).prop('disabled', false);
		    $( "input[name='not_valid_from']" ).prop('disabled', false);
		    $( "input[name='not_valid_to']" ).prop('disabled', false);
		    $( "input[name='tab_operating_hours_field_cb_weekdays']" ).prop('disabled', false);
		    $( "input[name='tab_operating_hours_field_weekdays_1']" ).prop('disabled', false);
		    $( "input[name='tab_operating_hours_field_weekdays_2']" ).prop('disabled', false);
		    $( "input[name='tab_operating_hours_field_weekdays_3']" ).prop('disabled', false);
		    $( "input[name='tab_operating_hours_field_weekdays_4']" ).prop('disabled', false);
		    $( "input[name='tab_operating_hours_field_monday_1']" ).prop('disabled', false);
		    $( "input[name='tab_operating_hours_field_monday_2']" ).prop('disabled', false);
		    $( "input[name='tab_operating_hours_field_monday_3']" ).prop('disabled', false);
		    $( "input[name='tab_operating_hours_field_monday_4']" ).prop('disabled', false);
		    $( "input[name='tab_operating_hours_field_tuesday_1']" ).prop('disabled', false);
		    $( "input[name='tab_operating_hours_field_tuesday_2']" ).prop('disabled', false);
		    $( "input[name='tab_operating_hours_field_tuesday_3']" ).prop('disabled', false);
		    $( "input[name='tab_operating_hours_field_tuesday_4']" ).prop('disabled', false);
		    $( "input[name='tab_operating_hours_field_wednesday_1']" ).prop('disabled', false);
		    $( "input[name='tab_operating_hours_field_wednesday_2']" ).prop('disabled', false);
		    $( "input[name='tab_operating_hours_field_wednesday_3']" ).prop('disabled', false);
		    $( "input[name='tab_operating_hours_field_wednesday_4']" ).prop('disabled', false);
		    $( "input[name='tab_operating_hours_field_thursday_1']" ).prop('disabled', false);
		    $( "input[name='tab_operating_hours_field_thursday_2']" ).prop('disabled', false);
		    $( "input[name='tab_operating_hours_field_thursday_3']" ).prop('disabled', false);
		    $( "input[name='tab_operating_hours_field_thursday_4']" ).prop('disabled', false);
		    $( "input[name='tab_operating_hours_field_friday_1']" ).prop('disabled', false);
		    $( "input[name='tab_operating_hours_field_friday_2']" ).prop('disabled', false);
		    $( "input[name='tab_operating_hours_field_friday_3']" ).prop('disabled', false);
		    $( "input[name='tab_operating_hours_field_friday_4']" ).prop('disabled', false);
		    $( "input[name='tab_operating_hours_field_saturday_1']" ).prop('disabled', false);
		    $( "input[name='tab_operating_hours_field_saturday_2']" ).prop('disabled', false);
		    $( "input[name='tab_operating_hours_field_saturday_3']" ).prop('disabled', false);
		    $( "input[name='tab_operating_hours_field_saturday_4']" ).prop('disabled', false);
		    $( "input[name='tab_operating_hours_field_sunday_1']" ).prop('disabled', false);
		    $( "input[name='tab_operating_hours_field_sunday_2']" ).prop('disabled', false);
		    $( "input[name='tab_operating_hours_field_sunday_3']" ).prop('disabled', false);
		    $( "input[name='tab_operating_hours_field_sunday_4']" ).prop('disabled', false);
		}else{
		    $( "input[name='service_name']" ).prop('disabled', true);
		    $( "textarea[name='service_description']" ).prop('disabled', true);
		    $( "textarea[name='how_to_book']" ).prop('disabled', true);
		    $( "input[name='cb_not_avail']" ).prop('disabled', true);
		    $( "input[name='not_valid_from']" ).prop('disabled', true);
		    $( "input[name='not_valid_to']" ).prop('disabled', true);
		    $( "input[name='tab_operating_hours_field_cb_weekdays']" ).prop('disabled', true);
		    $( "input[name='tab_operating_hours_field_weekdays_1']" ).prop('disabled', true);
		    $( "input[name='tab_operating_hours_field_weekdays_2']" ).prop('disabled', true);
		    $( "input[name='tab_operating_hours_field_weekdays_3']" ).prop('disabled', true);
		    $( "input[name='tab_operating_hours_field_weekdays_4']" ).prop('disabled', true);
		    $( "input[name='tab_operating_hours_field_monday_1']" ).prop('disabled', true);
		    $( "input[name='tab_operating_hours_field_monday_2']" ).prop('disabled', true);
		    $( "input[name='tab_operating_hours_field_monday_3']" ).prop('disabled', true);
		    $( "input[name='tab_operating_hours_field_monday_4']" ).prop('disabled', true);
		    $( "input[name='tab_operating_hours_field_tuesday_1']" ).prop('disabled', true);
		    $( "input[name='tab_operating_hours_field_tuesday_2']" ).prop('disabled', true);
		    $( "input[name='tab_operating_hours_field_tuesday_3']" ).prop('disabled', true);
		    $( "input[name='tab_operating_hours_field_tuesday_4']" ).prop('disabled', true);
		    $( "input[name='tab_operating_hours_field_wednesday_1']" ).prop('disabled', true);
		    $( "input[name='tab_operating_hours_field_wednesday_2']" ).prop('disabled', true);
		    $( "input[name='tab_operating_hours_field_wednesday_3']" ).prop('disabled', true);
		    $( "input[name='tab_operating_hours_field_wednesday_4']" ).prop('disabled', true);
		    $( "input[name='tab_operating_hours_field_thursday_1']" ).prop('disabled', true);
		    $( "input[name='tab_operating_hours_field_thursday_2']" ).prop('disabled', true);
		    $( "input[name='tab_operating_hours_field_thursday_3']" ).prop('disabled', true);
		    $( "input[name='tab_operating_hours_field_thursday_4']" ).prop('disabled', true);
		    $( "input[name='tab_operating_hours_field_friday_1']" ).prop('disabled', true);
		    $( "input[name='tab_operating_hours_field_friday_2']" ).prop('disabled', true);
		    $( "input[name='tab_operating_hours_field_friday_3']" ).prop('disabled', true);
		    $( "input[name='tab_operating_hours_field_friday_4']" ).prop('disabled', true);
		    $( "input[name='tab_operating_hours_field_saturday_1']" ).prop('disabled', true);
		    $( "input[name='tab_operating_hours_field_saturday_2']" ).prop('disabled', true);
		    $( "input[name='tab_operating_hours_field_saturday_3']" ).prop('disabled', true);
		    $( "input[name='tab_operating_hours_field_saturday_4']" ).prop('disabled', true);
		    $( "input[name='tab_operating_hours_field_sunday_1']" ).prop('disabled', true);
		    $( "input[name='tab_operating_hours_field_sunday_2']" ).prop('disabled', true);
		    $( "input[name='tab_operating_hours_field_sunday_3']" ).prop('disabled', true);
		    $( "input[name='tab_operating_hours_field_sunday_4']" ).prop('disabled', true);
		}
	});
	
	
	$( "#save_vehicle_info" ).click(function() {
		 var myObj = {};
		    myObj["name"] = $( "#reg_no" ).val();
		    myObj["description"] = $( "#vehicle_type" ).val();
		    myObj["how2book"] = $( "#other_vehicle" ).val();
	});
	
	var editable_veh_info = false;
	$( "#edit_vehicle_info" ).click(function() {
		if(!editable_veh_info){
			editable_veh_info = true;
		}else{
			editable_veh_info = false;
		}
		if(editable_veh_info){
			 $( "input[name='regNum']" ).prop('disabled', false);
			 $( "select[name='vehicle_type']" ).prop('disabled', false);
			 $( "#other_vehicle" ).prop('disabled', false);
		}else{
			$( "input[name='regNum']" ).prop('disabled', true);
			$( "select[name='vehicle_type']" ).prop('disabled', true);
			$( "#other_vehicle" ).prop('disabled', true);
		}
	});
	
	$( "#save_operating_area_info" ).click(function() {
		 var myObj = {};
		 myObj["jsonData"] = $( "#jsonString" ).val();
		 myObj["kmlData"] = $( "#kmlString" ).val();
		 $.ajax({
			    url: "/ke/async/save/operatingAreaInfo",
			    type: 'POST',
			    dataType: 'json',
			    data: JSON.stringify(myObj),
			    contentType: 'application/json',
			    mimeType: 'application/json',
		    	    success: function(data) {
		    	    	alert("suc");
		    	    },
		    	    error:function(data,status,er) {
		    	        console.log(data);
		    	        console.log(er);
		    	    }
		    	});
	});
		    
		
	$( "#save_elig" ).click(function() {
	    var myObj = {};
	    myObj["age_under16"] = $('#tab-eligibility-field-1').is(":checked");
	    myObj["age_17to21"] = $('#tab-eligibility-field-2').is(":checked");
	    myObj["age_22to54"] = $('#tab-eligibility-field-3').is(":checked");
	    myObj["age_55to59"] = $('#tab-eligibility-field-4').is(":checked");
	    myObj["age_over60"] = $('#tab-eligibility-field-5').is(":checked");
	    myObj["able_bodied"] = $('#tab-eligibility-field-6').is(":checked");
	    myObj["mobility_prevents_PT"] = $('#tab-eligibility-field-7').is(":checked");
	    myObj["disable_wheelchair_user"] = $('#tab-eligibility-field-8').is(":checked");
	    myObj["disable_other"] = $('#tab-eligibility-field-9').is(":checked");
	    myObj["cant_live_on_a_bus_route"] = $('#tab-eligibility-field-17').is(":checked");
	    myObj["health_appointment"] = $('#tab-eligibility-field-10').is(":checked");
	    myObj["social_care"] = $('#tab-eligibility-field-11').is(":checked");
	    myObj["shopping"] = $('#tab-eligibility-field-12').is(":checked");
	    myObj["leisure_or_visiting_friends"] = $('#tab-eligibility-field-13').is(":checked");
	    myObj["school_or_education"] = $('#tab-eligibility-field-14').is(":checked");
	    myObj["work_or_commuting"] = $('#tab-eligibility-field-15').is(":checked");
	    myObj["other_purpose"] = $('#tab-eligibility-field-16').is(":checked");
	    myObj["other_elig"] = $("input[name='other_elig']").val(); 
	   $.ajax({
		    url: "/ke/async/save/elig",
		    type: 'POST',
		    dataType: 'json',
		    data: JSON.stringify(myObj),
		    contentType: 'application/json',
		    mimeType: 'application/json',
		    success: function(data) {
		    },
		    error:function(data,status,er) {
		        console.log(data);
		        console.log(er);
		    }
		});
	});
	
	var editable_elig = false;
	$( "#edit_elig" ).click(function() {
		if(!editable_elig){
			editable_elig = true;
		}else{
			editable_elig = false;
		}
		if(editable_elig){
			$('#tab-eligibility-field-1').prop('disabled', false);
		    $('#tab-eligibility-field-2').prop('disabled', false);
		    $('#tab-eligibility-field-3').prop('disabled', false);
		    $('#tab-eligibility-field-4').prop('disabled', false);
		    $('#tab-eligibility-field-5').prop('disabled', false);
		    $('#tab-eligibility-field-6').prop('disabled', false);
		    $('#tab-eligibility-field-7').prop('disabled', false);
		    $('#tab-eligibility-field-8').prop('disabled', false);
		    $('#tab-eligibility-field-9').prop('disabled', false);
		    $('#tab-eligibility-field-17').prop('disabled', false);
		    $('#tab-eligibility-field-10').prop('disabled', false);
		    $('#tab-eligibility-field-11').prop('disabled', false);
		    $('#tab-eligibility-field-12').prop('disabled', false);
		    $('#tab-eligibility-field-13').prop('disabled', false);
		    $('#tab-eligibility-field-14').prop('disabled', false);
		    $('#tab-eligibility-field-15').prop('disabled', false);
		    $('#tab-eligibility-field-16').prop('disabled', false);
		    $('#tab-eligibility-field-17').prop('disabled', false);
		    $("input[name='other_elig']").prop('disabled', false);
		    $("select[name='other_elig_type']").prop('disabled', false);
		    $("input[name='tab_elig_radioBtns']").prop('disabled', false);
		    $("textarea[name='explain_opening_up_elig']").prop('disabled', false);
		}else{
			$('#tab-eligibility-field-1').prop('disabled', true);
		    $('#tab-eligibility-field-2').prop('disabled', true);
		    $('#tab-eligibility-field-3').prop('disabled', true);
		    $('#tab-eligibility-field-4').prop('disabled', true);
		    $('#tab-eligibility-field-5').prop('disabled', true);
		    $('#tab-eligibility-field-6').prop('disabled', true);
		    $('#tab-eligibility-field-7').prop('disabled', true);
		    $('#tab-eligibility-field-8').prop('disabled', true);
		    $('#tab-eligibility-field-9').prop('disabled', true);
		    $('#tab-eligibility-field-17').prop('disabled', true);
		    $('#tab-eligibility-field-10').prop('disabled', true);
		    $('#tab-eligibility-field-11').prop('disabled', true);
		    $('#tab-eligibility-field-12').prop('disabled', true);
		    $('#tab-eligibility-field-13').prop('disabled', true);
		    $('#tab-eligibility-field-14').prop('disabled', true);
		    $('#tab-eligibility-field-15').prop('disabled', true);
		    $('#tab-eligibility-field-16').prop('disabled', true);
		    $('#tab-eligibility-field-17').prop('disabled', true);
		    $("input[name='other_elig']").prop('disabled', true);
		    $("select[name='other_elig_type']").prop('disabled', true);
		    $("input[name='tab_elig_radioBtns']").prop('disabled', true);
		    $("textarea[name='explain_opening_up_elig']").prop('disabled', true);
		}
	});
	
	$( "#save_fare" ).click(function() {
	    var myObj = {};
	    myObj["charge_standard_fare"] = $("input[name='tab_fare_structure_radioBtns']").val();
	    myObj["fare_dist1_mile_2"] = $("input[name='fare_dist1_mile_2']").val(); 
	    myObj["fare_dist1_type"] = $("select[name='fare_dist1_type']").val(); 
	    myObj["fare_dist1_charge"] = $("input[name='fare_dist1_charge']").val(); 
	    myObj["fare_dist2_mile_1"] = $("input[name='fare_dist2_mile_1']").val(); 
	    myObj["fare_dist2_mile_2"] = $("input[name='fare_dist2_mile_2']").val(); 
	    myObj["fare_dist2_type"] = $("select[name='fare_dist2_type']").val(); 
	    myObj["fare_dist2_charge"] = $("input[name='fare_dist2_charge']").val(); 
	    myObj["fare_dist3_mile_1"] = $("input[name='fare_dist3_mile_1']").val(); 
	    myObj["fare_dist3_mile_2"] = $("input[name='fare_dist3_mile_2']").val(); 
	    myObj["fare_dist3_type"] = $("select[name='fare_dist3_type']").val(); 
	    myObj["fare_dist3_charge"] = $("input[name='fare_dist3_charge']").val(); 
	    myObj["fare_dist4_mile_1"] = $("input[name='fare_dist4_mile_1']").val(); 
	    myObj["fare_dist4_mile_2"] = $("input[name='fare_dist4_mile_2']").val(); 
	    myObj["fare_dist4_type"] = $("select[name='fare_dist4_type']").val(); 
	    myObj["fare_dist4_charge"] = $("input[name='fare_dist4_charge']").val(); 
	    myObj["return_fare_multiplier"] = $("input[name='return_fare_multiplier']").val(); 
	    myObj["discount_for_over60"] = $("input[name='discount_for_over60']").val(); 
	    myObj["discount_for_under16"] = $("input[name='discount_for_under16']").val(); 
	    myObj["fare_structure_checkbox_escort"] = $("input[name='fare_structure_checkbox_escort']").is(":checked");
	    myObj["fare_structure_checkbox_charge_for_dead_mileage"] = $("input[name='fare_structure_checkbox_charge_for_dead_mileage']").is(":checked");
	    console.log(JSON.stringify(myObj));
	   $.ajax({
		    url: "/ke/async/save/fare",
		    type: 'POST',
		    dataType: 'json',
		    data: JSON.stringify(myObj),
		    contentType: 'application/json',
		    mimeType: 'application/json',
		    success: function(data) {
		    },
		    error:function(data,status,er) {
		        console.log(data);
		        console.log(er);
		    }
		});
	});
	
	var editable_fare = false;
	
	$( "#edit_fare" ).click(function() {
		if(!editable_fare){
			editable_fare =  true;
		}else{
			editable_fare = false;
		}
		if(editable_fare){
			 $("input[name='tab_fare_structure_radioBtns']").prop('disabled', false);
			    $("input[name='tab_fare_structure_how_charge_radioBtns']").prop('disabled', false);
			    $("input[name='fare_dist1_mile_2']").prop('disabled', false);
			    $("select[name='fare_dist1_type']").prop('disabled', false);
			    $("input[name='fare_dist1_charge']").prop('disabled', false);
			    $("input[name='fare_dist2_mile_1']").prop('disabled', false);
			    $("input[name='fare_dist2_mile_2']").prop('disabled', false);
			    $("select[name='fare_dist2_type']").prop('disabled', false);
			    $("input[name='fare_dist2_charge']").prop('disabled', false);
			    $("input[name='fare_dist3_mile_1']").prop('disabled', false);
			    $("input[name='fare_dist3_mile_2']").prop('disabled', false);
			    $("select[name='fare_dist3_type']").prop('disabled', false);
			    $("input[name='fare_dist3_charge']").prop('disabled', false);
			    $("input[name='fare_dist4_mile_1']").prop('disabled', false);
			    $("input[name='fare_dist4_mile_2']").prop('disabled', false);
			    $("select[name='fare_dist4_type']").prop('disabled', false);
			    $("input[name='fare_dist4_charge']").prop('disabled', false);
			    $("input[name='return_fare_multiplier']").prop('disabled', false);
			    $("input[name='discount_for_over60']").prop('disabled', false);
			    $("input[name='discount_for_under16']").prop('disabled', false);
			    $("input[name='discount_for_other_concessionary']").prop('disabled', false);
			    $("input[name='fare_structure_checkbox_escort']").prop('disabled', false);
			    $("input[name='fare_structure_checkbox_charge_for_dead_mileage']").prop('disabled', false);
		}else{
			 $("input[name='tab_fare_structure_radioBtns']").prop('disabled', true);
			    $("input[name='tab_fare_structure_how_charge_radioBtns']").prop('disabled', true);
			    $("input[name='fare_dist1_mile_2']").prop('disabled', true);
			    $("select[name='fare_dist1_type']").prop('disabled', true);
			    $("input[name='fare_dist1_charge']").prop('disabled', true);
			    $("input[name='fare_dist2_mile_1']").prop('disabled', true);
			    $("input[name='fare_dist2_mile_2']").prop('disabled', true);
			    $("select[name='fare_dist2_type']").prop('disabled', true);
			    $("input[name='fare_dist2_charge']").prop('disabled', true);
			    $("input[name='fare_dist3_mile_1']").prop('disabled', true);
			    $("input[name='fare_dist3_mile_2']").prop('disabled', true);
			    $("select[name='fare_dist3_type']").prop('disabled', true);
			    $("input[name='fare_dist3_charge']").prop('disabled', true);
			    $("input[name='fare_dist4_mile_1']").prop('disabled', true);
			    $("input[name='fare_dist4_mile_2']").prop('disabled', true);
			    $("select[name='fare_dist4_type']").prop('disabled', true);
			    $("input[name='fare_dist4_charge']").prop('disabled', true);
			    $("input[name='return_fare_multiplier']").prop('disabled', true);
			    $("input[name='discount_for_over60']").prop('disabled', true);
			    $("input[name='discount_for_under16']").prop('disabled', true);
			    $("input[name='discount_for_other_concessionary']").prop('disabled', true);
			    $("input[name='fare_structure_checkbox_escort']").prop('disabled', true);
			    $("input[name='fare_structure_checkbox_charge_for_dead_mileage']").prop('disabled', true);
		}
	});
	
	$( "#save_surcharge" ).click(function() {
	    var myObj = {};
	    myObj["surcharge_dist1_mile"] = $("input[name='surcharge_dist1_mile']").val(); 
	    myObj["surcharge_dist1_type"] = $("select[name='surcharge_dist1_type']").val(); 
	    myObj["surcharge_dist1_charge"] = $("input[name='surcharge_dist1_charge']").val(); 
	    myObj["surcharge_dist2_1"] = $("input[name='surcharge_dist2_1']").val(); 
	    myObj["surcharge_dist2_2"] = $("input[name='surcharge_dist2_2']").val(); 
	    myObj["surcharge_dist2_type"] = $("select[name='surcharge_dist2_type']").val(); 
	    myObj["surcharge_dist2_charge"] = $("input[name='surcharge_dist2_charge']").val(); 
	    myObj["surcharge_dist3_mile_1"] = $("input[name='surcharge_dist3_mile_1']").val(); 
	    myObj["surcharge_dist3_mile_2"] = $("input[name='surcharge_dist3_mile_2']").val(); 
	    myObj["surcharge_dist3_type"] = $("select[name='surcharge_dist3_type']").val(); 
	    myObj["surcharge_dist3_charge"] = $("input[name='surcharge_dist3_charge']").val(); 
	    myObj["surcharge_dist4_mile_1"] = $("input[name='surcharge_dist4_mile_1']").val(); 
	    myObj["surcharge_dist4_mile_2"] = $("input[name='surcharge_dist4_mile_2']").val(); 
	    myObj["surcharge_dist4_type"] = $("select[name='surcharge_dist4_type']").val(); 
	    myObj["surcharge_dist4_charge"] = $("input[name='surcharge_dist4_charge']").val(); 
	    myObj["surcharge_checkbox_under16"] = $("input[name='tab_fare_structure_radioBtns']").is(":checked");
	    myObj["surcharge_under_16_num"] = $("input[name='surcharge_under_16_num']").val(); 
	    myObj["surcharge_checkbox_17_to_21"] = $("input[name='surcharge_checkbox_17_to_21']").is(":checked");
	    myObj["surcharge_17_21_years_old_num"] = $("input[name='surcharge_17_21_years_old_num']").val(); 
	    myObj["surcharge_checkbox_22_to_54"] = $("input[name='surcharge_checkbox_22_to_54']").is(":checked");
	    myObj["surcharge_22_54_years_old_num"] = $("input[name='surcharge_22_54_years_old_num']").val(); 
	    myObj["surcharge_checkbox_55_to_59"] = $("input[name='surcharge_checkbox_55_to_59']").is(":checked");
	    myObj["surcharge_55_59_years_old_num"] = $("input[name='surcharge_55_59_years_old_num']").val(); 
	    myObj["surcharge_checkbox_over60"] = $("input[name='surcharge_checkbox_over60']").is(":checked");
	    myObj["surcharge_over_60_years_old_num"] = $("input[name='surcharge_over_60_years_old_num']").val(); 
	    myObj["surcharge_checkbox_able_bodied"] = $("input[name='surcharge_checkbox_able_bodied']").is(":checked");
	    myObj["surcharge_able_bodied_num"] = $("input[name='surcharge_able_bodied_num']").val(); 
	    myObj["surcharge_checkbox_mobility_prevents_PT"] = $("input[name='surcharge_checkbox_mobility_prevents_PT']").is(":checked");
	    myObj["surcharge_mobility_prevents_PT_num"] = $("input[name='surcharge_mobility_prevents_PT_num']").val(); 
	    myObj["surcharge_checkbox_diable_wheelchair_user"] = $("input[name='surcharge_checkbox_diable_wheelchair_user']").is(":checked");
	    myObj["surcharge_disable_wheelchair_user_num"] = $("input[name='surcharge_disable_wheelchair_user_num']").val(); 
	    myObj["surcharge_checkbox_disable_other"] = $("input[name='surcharge_checkbox_disable_other']").is(":checked");
	    myObj["surcharge_disable_other_num"] = $("input[name='surcharge_disable_other_num']").val(); 
	    myObj["surcharge_checkbox_health_appointment"] = $("input[name='surcharge_checkbox_health_appointment']").is(":checked");
	    myObj["surcharge_health_appointment_num"] = $("input[name='surcharge_health_appointment_num']").val();
	    myObj["surcharge_checkbox_social_care"] = $("input[name='surcharge_checkbox_social_care']").is(":checked");
	    myObj["surcharge_social_care_num"] = $("input[name='surcharge_social_care_num']").val();
	    myObj["surcharge_checkbox_shopping"] = $("input[name='surcharge_checkbox_shopping']").is(":checked");
	    myObj["surcharge_shopping_num"] = $("input[name='surcharge_shopping_num']").val();
	    myObj["surcharge_checkbox_leisure_visiting_friends"] = $("input[name='surcharge_checkbox_leisure_visiting_friends']").is(":checked");
	    myObj["surcharge_leisure_visiting_friends_num"] = $("input[name='surcharge_leisure_visiting_friends_num']").val();
	    myObj["surcharge_checkbox_school_education"] = $("input[name='surcharge_checkbox_school_education']").is(":checked");
	    myObj["surcharge_school_education_num"] = $("input[name='surcharge_school_education_num']").val();
	    myObj["surcharge_checkbox_work_commuting"] = $("input[name='surcharge_checkbox_work_commuting']").is(":checked");
	    myObj["surcharge_work_commuting_num"] = $("input[name='surcharge_work_commuting_num']").val();
	    myObj["surcharge_checkbox_other_purpose"] = $("input[name='surcharge_checkbox_other_purpose']").is(":checked");
	    myObj["surcharge_other_purpose_num"] = $("input[name='surcharge_other_purpose_num']").val();
	    myObj["surcharge_checkbox_start_05_hours_earlier"] = $("input[name='surcharge_checkbox_start_05_hours_earlier']").is(":checked");
	    myObj["surcharge_start_05_hours_earlier"] = $("input[name='surcharge_start_05_hours_earlier']").val();
	    myObj["surcharge_checkbox_start_1_hour_earlier"] = $("input[name='surcharge_checkbox_start_1_hour_earlier']").is(":checked");
	    myObj["surcharge_start_1_hour_earlier"] = $("input[name='surcharge_start_1_hour_earlier']").val();
	    myObj["surcharge_checkbox_start_15_hours_earlier"] = $("input[name='surcharge_checkbox_start_15_hours_earlier']").is(":checked");
	    myObj["surcharge_start_15_hours_earlier"] = $("input[name='surcharge_start_15_hours_earlier']").val();
	    myObj["surcharge_checkbox_finish_05_hours_later"] = $("input[name='surcharge_checkbox_finish_05_hours_later']").is(":checked");
	    myObj["surcharge_finish_05_hours_later"] = $("input[name='surcharge_finish_05_hours_later']").val();
	    myObj["surcharge_checkbox_finish_1_hour_later"] = $("input[name='surcharge_checkbox_finish_1_hour_later']").is(":checked");
	    myObj["surcharge_finish_1_hour_later"] = $("input[name='surcharge_finish_1_hour_later']").val();
	    myObj["surcharge_checkbox_finish_15_hours_later"] = $("input[name='surcharge_checkbox_finish_15_hours_later']").is(":checked");
	    myObj["surcharge_finish_15_hours_later"] = $("input[name='surcharge_finish_15_hours_later']").val();
	   $.ajax({
		    url: "/ke/async/save/surcharge",
		    type: 'POST',
		    dataType: 'json',
		    data: JSON.stringify(myObj),
		    contentType: 'application/json',
		    mimeType: 'application/json',
		    success: function(data) {
		    },
		    error:function(data,status,er) {
		        console.log(data);
		        console.log(er);
		    }
		});
	});
});




$(document).ready(function() {
	// initialize google map
	BlitzMap.setMap( 'myMap', true, 'mapData' );
	BlitzMap.init();// initialize BlitzMap
	$('#myMap').show();
});




var isConfirmed = false;

$( "#editMap" ).click(function() {
    if(isConfirmed){
        BlitzMap.toggleEditable();
        isConfirmed = false;
        $('#editMap').html('Save it');
    }else{
        BlitzMap.toggleEditable();
        isConfirmed = true;
        $('#jsonString').val(BlitzMap.toJSONString());
        BlitzMap.toKML();
        //console.log("jsonString:" + $('#jsonString').val());
        //console.log("kmlString:" + $('#kmlString').val().replace(/(\r\n|\n|\r)/gm,""));
        $('#kmlString').val($('#kmlString').val());
        $('#editMap').html(' Edit it');
    }
	});


/* 
 * *********************
 * adds-on
 * *********************
*/

/* highlight the text on the collapse*/
$('#collapseOne').on('shown.bs.collapse', function () {
	$('a[href="#collapseOne"]').addClass("highlight");
});
$('#collapseOne').on('hidden.bs.collapse', function () {
	$('a[href="#collapseOne"]').removeClass("highlight");
});
$('#collapseTwo').on('shown.bs.collapse', function () {
	$('a[href="#collapseTwo"]').addClass("highlight");
});
$('#collapseTwo').on('hidden.bs.collapse', function () {
	$('a[href="#collapseTwo"]').removeClass("highlight");
});
$('#collapseThree').on('shown.bs.collapse', function () {
	$('a[href="#collapseThree"]').addClass("highlight");
});
$('#collapseThree').on('hidden.bs.collapse', function () {
	$('a[href="#collapseThree"]').removeClass("highlight");
});
$('#collapseFour').on('shown.bs.collapse', function () {
	$('a[href="#collapseFour"]').addClass("highlight");
});
$('#collapseFour').on('hidden.bs.collapse', function () {
	$('a[href="#collapseFour"]').removeClass("highlight");
});
$('#collapseFive').on('shown.bs.collapse', function () {
	$('a[href="#collapseFive"]').addClass("highlight");
});
$('#collapseFive').on('hidden.bs.collapse', function () {
	$('a[href="#collapseFive"]').removeClass("highlight");
});
$('#collapseFive').on('shown.bs.collapse', function () {
	$('a[href="#collapseSix"]').addClass("highlight");
});
$('#collapseFive').on('hidden.bs.collapse', function () {
	$('a[href="#collapseSix"]').removeClass("highlight");
});

  


