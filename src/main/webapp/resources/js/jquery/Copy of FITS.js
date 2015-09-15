

$(document).ready(function() {
	
	
$("#tab_fare_structure_form_radioBtns").change();

    $("input[name$='tab_fare_structure_radioBtns']").click(function() {
        var radioVal = $(this).val();
      if(radioVal==0){
    	  //alert("raido button fare yes");
          $("#tab-fare-structure-form").fadeIn('slow');
      }
      else{
    	  //alert("radio button fare no");
    	  $("#tab-fare-structure-form").fadeOut('slow');
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

  //show hide surcharge fields depending on selection in passenger eligibility

   $("input[name^='eligible_checkbox']").each( function () {
        var value = $(this).val();
        var passenger_elig_id = 'tab-surcharge-structure-pt-field-'+value+'group';
        if(this.checked)
          $('#'+passenger_elig_id).hide();
        else
          $('#'+passenger_elig_id).show();
   });
   
   //debug
   // reset all the input whose name starts with 'fare_'
//   $("input[name^='fare_']").each( function () {
//       var name = $(this).attr('name');
//       if(!name.match("^fare_structure_checkbox_")){
//    	   $(this).val(""); 
//       }
//  });
   //debug
// reset all the input whose name starts with 'surcharge_'
//   $("input[name^='surcharge_']").each( function () {
//       var name = $(this).attr('name');
//       if(!name.match("^surcharge_checkbox")){
//    	   $(this).val("");  //debug 
//       }
//  });
   
     $("input[name^='eligible_checkbox']").click(function() {
        //var id = $(this).attr('id');
        var value = $(this).val();
        var passenger_elig_id = 'tab-surcharge-structure-pt-field-'+value+'-group';
        if(this.checked)
          $('#'+passenger_elig_id).hide();
        else
          $('#'+passenger_elig_id).show();
      });

//disable enable surcharge checkboxes
     
   $("input[name^='surcharge_checkbox']").each( function () {
        var field_id = $(this).attr('id');
        var field_group = field_id+'-group';
        var field_group_input = field_id+'-input';
        if(this.checked){
           //grey out
                //$("#"+field_group).removeClass("input-disabled"); 
                //disable inputs inside parent div
                $("#"+field_group+" :input").attr("disabled", false);
            }
            else{
                //$("#"+field_group).addClass("input-disabled"); 
            	$("#"+field_group+" :input").attr("placeholder","0.00");// reset the default value if the checkbox is disabled. Cheng
            	
            	$("#"+field_group+" :input").attr("disabled", true);
            }
          $("#"+field_id).attr("disabled", false);
   });


   $("input[name^='surcharge_checkbox']").click(function() {
         // $("#review-field-no-records" ).addClass("review-filters-disabled");
         var field_id = $(this).attr('id');
         var field_group = field_id+'-group';
         var field_group_input = field_id+'-input';
         if(this.checked){
                //$("#"+field_group).removeClass("input-disabled"); 
                //disable inputs inside parent div
                $("#"+field_group+" :input").attr("disabled", false);
            }
            else{
                //$("#"+field_group).addClass("input-disabled");   
            	$("#"+field_group+" :input").attr("placeholder","0.00");// reset the default value if the checkbox is disabled. Cheng
            	if( $("#"+field_group_input).val() != "" ){
            		$("#"+field_group_input).val(""); // debug 
            		var filed_name = $("#"+field_group_input).attr('name');
            		$('#data_input_form').data('bootstrapValidator')
            	    .updateStatus(filed_name, 'NOT_VALIDATED');
//            	    .validateField(filed_name);
            	}
                $("#"+field_group+" :input").attr("disabled", true);
            }
          $("#"+field_id).attr("disabled", false);
        });
});




//range slider
$(document).ready(function() {
	

var rangeTimes = [];

$(".range-slider").slider({
		range: true,
    min: 0,
    max: 1440,
    values: [540, 1080],//1080
    step:15,
    slide: slideTime,
    change: updateOpeningHours
	});

	function slideTime(event, ui){
		if (event && event.target) {
		      var $rangeslider = $(event.target);
		      var $rangeday = $rangeslider.closest(".range-day");
		      var rangeday_d = parseInt($rangeday.data('day'));
		      var $rangecheck = $rangeday.find(":checkbox");
		      var $rangetime = $rangeslider.next(".range-time");
		    }
		    
		    if ($rangecheck.is(':checked')) {
		      $rangeday.removeClass('range-day-disabled');
		      $rangeslider.slider('enable');
		      
		      if (ui!==undefined) {
		        var val0 = ui.values[0],
					      val1 = ui.values[1];
		      } else {
		        var val0 = $rangeslider.slider('values', 0),
					      val1 = $rangeslider.slider('values', 1);
		      }
		      
		      var minutes0 = parseInt(val0 % 60, 10),
					    hours0 = parseInt(val0 / 60 % 24, 10),
					    minutes1 = parseInt(val1 % 60, 10),
					    hours1 = parseInt(val1 / 60 % 24, 10);
		      if (hours1==0) hours1=24;
		      
				  rangeTimes[rangeday_d] = [getTime(hours0, minutes0),getTime(hours1, minutes1)];
		      $rangetime.text(rangeTimes[rangeday_d][0] + ' - ' + rangeTimes[rangeday_d][1]);
		      
		    } else {
		      $rangeday.addClass('range-day-disabled');
		      $rangeslider.slider('disable');
		      
		      rangeTimes[rangeday_d] = [];
		      
		      $rangetime.text('Closed');
		    }
	}

  function updateOpeningHours() {
	  
	  if ($('#schedule').length) {
	      $('#schedule tbody').empty();
	    } else {
	      $('#tab-general-form').append('<br>\
	      <table id="schedule">\
		    <thead>\
			    <tr>\
	          <th>Day</th>\
				    <th>Opening Time</th>\
				    <th>Closing Time</th>\
			    </tr>\
		    </thead>\
		    <tbody>\
		    </tbody>\
	      </table>');
	    }
//	    var days = {1:"Monday",2:"Tuesday",3:"Wednesday",4:"Thursday",5:"Friday",6:"Saturday",7:"Sunday"};
//	    for (d=1; d<=7; d++) {
//	      $('#schedule tbody').append('<tr>'+
//				   '<td>'+days[d]+'</td>'+
//				   '<td>'+(rangeTimes[d][0]===undefined?'Closed':rangeTimes[d][0])+'</td>'+
//	         '<td>'+(rangeTimes[d][1]===undefined?'':rangeTimes[d][1])+'</td>'+
//				'</tr>');
//	    }
	    var days = {1:"Monday",2:"Tuesday",3:"Wednesday",4:"Thursday",5:"Friday",6:"Saturday",7:"Sunday"};
	    var tbody_str = "";
	    for (d=1; d<=7; d++) {
	    	
	    	 $('#schedule tbody').append('<tr>'+
	  			   '<td>'+days[d]+'</td>'+
	  			   '<td>'+(rangeTimes[d][0]===undefined?'Closed':rangeTimes[d][0])+'</td>'+
	           '<td>'+(rangeTimes[d][1]===undefined?'':rangeTimes[d][1])+'</td>'+
	  			'</tr>');
	    	 
	    	if(d==1){
	    		tbody_str = "";
	    	}
	    	 tbody_str += '<tr>'+
		   		'<td>'+days[d]+'</td>'+
		   		'<td>'+(rangeTimes[d][0]===undefined?'Closed':rangeTimes[d][0])+'</td>'+
		   		'<td>'+(rangeTimes[d][1]===undefined?'':rangeTimes[d][1])+'</td>'+
		   		'</tr>';
	    }
	    $('#opening_hours_hidden_table').val(tbody_str);
	    console.log($('#opening_hours_hidden_table').val());
	  
  }
  

	function getTime(hours, minutes) {
		var time = null; 
		minutes = minutes + "";
		if (minutes.length == 1) {
      minutes = "0" + minutes;
		}

    //make number two digits 01,02 etc
    hours =  hours > 9 ? "" + hours: "0" + hours;
		
    return hours + ":" + minutes;
	}
	

  $('.range-checkbox').on('change', function(){
    var $rangecheck = $(this);
    var $rangeslider = $rangecheck.closest('.range-day').find('.range-slider');
    slideTime({target:$rangeslider});
    updateOpeningHours();
  });

  
  $("#scheduleSubmit").on('click', updateOpeningHours);
	
slideTime({target:$('#range-slider-1')});
slideTime({target:$('#range-slider-2')});
slideTime({target:$('#range-slider-3')});
slideTime({target:$('#range-slider-4')});
slideTime({target:$('#range-slider-5')});
slideTime({target:$('#range-slider-6')});
slideTime({target:$('#range-slider-7')});
updateOpeningHours();

/*
 * bootstrap form validation
 * 
 */
$('#data_input_form')
.bootstrapValidator({
	excluded: [':disabled'],
    message: 'This value is not valid',
    submitHandler: function(validator, form, submitButton) {
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
        fare_distance1_mile: {
            validators: {
            	between: {
                    min: 0.0001,
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
                    min: 0.0001,
                    max: 100000,
                    message: '*&nbsp;the mileage must be greater than 0.'
                }
            }
        },
        fare_dist2_mile_2: {
            validators: {
            	between: {
                    min: 0.0001,
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
                    min: 0.0001,
                    max: 100000,
                    message: '*&nbsp;the mileage must be greater than 0.'
                }
            }
        },
        fare_dist3_mile_2: {
            validators: {
            	between: {
                    min: 0.0001,
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
                    min: 0.0001,
                    max: 100000,
                    message: '*&nbsp;the mileage must be greater than 0.'
                }
            }
        },
        fare_dist4_mile_2: {
            validators: {
            	between: {
                    min: 0.0001,
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
                    min: 1,
                    max: 2,
                    message: '*&nbsp;The return fare multiplier must be greater than 1.0 but less than 2.0.'
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
                    min: 0.0001,
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
                    min: 0.0001,
                    max: 100000,
                    message: '*&nbsp;the mileage must be greater than 0.'
                }
            }
        },
        surcharge_dist2_2: {
            validators: {
            	between: {
                    min: 0.0001,
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
                    min: 0.0001,
                    max: 100000,
                    message: '*&nbsp;the mileage must be greater than 0.'
                }
            }
        },
        surcharge_dist3_mile_2: {
            validators: {
            	between: {
                    min: 0.0001,
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
                    min: 0.0001,
                    max: 100000,
                    message: '*&nbsp;the mileage must be greater than 0.'
                }
            }
        },
        surcharge_dist4_mile_2: {
            validators: {
            	between: {
                    min: 0.0001,
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
    },
    submitButtons: 'button[type="submit"]',
})
.find('button[data-toggle]')
    .on('click', function() {
        var target = $(this).attr('data-toggle');
        // Show or hide the additional fields
        // They will or will not be validated based on their visibilities
        if (target == '#fare_structure'){
        	
        	if(!$('#fare_distance_buffer_1').is(":visible")){
        		$('#fare_distance_buffer_1').show();
        	}else if(!$('#fare_distance_buffer_2').is(":visible")){
        		$('#fare_distance_buffer_2').show();
        	}else{
        		$('#fare_table').append('\
                		<div class="row col-lg-12 col-sm-12 col-xs-12">\
        				  <div class="col-md-3 "><input type="text" class="form-control"  placeholder="0.0"></div>\
        				  <div class="col-md-3"><input type="text" class="form-control"  placeholder="0.0"></div>\
        				  <div class="col-md-3">\
        				  	<select class="form-control">\
        					    <option value="flat_rate">Flat rate</option>\
        					    <option value="fare_per_mile">Fare per mile</option>\
        					</select></div>\
        				  <div class="col-md-3">&pound; <input type="text" class="form-control charge"  placeholder="0.00"></div>\
        			</div>'
                );
        	}
        }else if(target == '#surcharge_structure'){
        	if(!$('#surcharge_distance_buffer_1').is(":visible")){
        		$('#surcharge_distance_buffer_1').show();
        	}else if(!$('#surcharge_distance_buffer_2').is(":visible")){
        		$('#surcharge_distance_buffer_2').show();
        	}else{
        		
        		$('#surcharge_table').append('\
                 		<div class="row col-lg-12 col-sm-12 col-xs-12">\
         				  <div class="col-md-3 "><input type="text" class="form-control"  placeholder="0.0"></div>\
         				  <div class="col-md-3"><input type="text" class="form-control"  placeholder="0.0"></div>\
         				  <div class="col-md-3">\
         				  	<select class="form-control">\
         					    <option value="flat_rate">Flat rate</option>\
         					    <option value="fare_per_mile">Fare per mile</option>\
         					</select></div>\
         				  <div class="col-md-3">&pound; <input type="text" class="form-control charge" placeholder="0.00"></div>\
         			</div>'
                 );
        	}
        }
    });


$('#fare_distance_buffer_1').hide();
$('#fare_distance_buffer_2').hide();
$('#surcharge_distance_buffer_1').hide();
$('#surcharge_distance_buffer_2').hide();

//$("input[name='return_fare_multiplier']").val('');
//$("input[name='discount_for_over60']").val('');
//$("input[name='discount_for_under16']").val('');


$( "#save_general_info" ).click(function() {
	
            var myObj = {};
            myObj["name"] = $( "input[name='service_name']" ).val();
            myObj["description"] = $( "textarea[name='service_description']" ).val();
           $.ajax({
        	    url: "/abdn/async/save/generalInfo",
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
    myObj["health_appointment"] = $('#tab-eligibility-field-10').is(":checked");
    myObj["social_care"] = $('#tab-eligibility-field-11').is(":checked");
    myObj["shopping"] = $('#tab-eligibility-field-12').is(":checked");
    myObj["leisure_or_visiting_friends"] = $('#tab-eligibility-field-13').is(":checked");
    myObj["school_or_education"] = $('#tab-eligibility-field-14').is(":checked");
    myObj["work_or_commuting"] = $('#tab-eligibility-field-15').is(":checked");
    myObj["other_purpose"] = $('#tab-eligibility-field-16').is(":checked");
//    console.log(JSON.stringify(myObj));
   $.ajax({
	    url: "/abdn/async/save/elig",
	    type: 'POST',
	    dataType: 'json',
	    data: JSON.stringify(myObj),
	    contentType: 'application/json',
	    mimeType: 'application/json',
	    success: function(data) {
	    	alert("success");
	    },
	    error:function(data,status,er) {
	        console.log(data);
	        console.log(er);
	    }
	});
});

$( "#save_fare" ).click(function() {
    var myObj = {};
    myObj["charge_standard_fare"] = $("input[name='tab_fare_structure_radioBtns']").val();
    myObj["fare_distance1_mile"] = $("input[name='fare_distance1_mile']").val(); 
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
	    url: "/abdn/async/save/fare",
	    type: 'POST',
	    dataType: 'json',
	    data: JSON.stringify(myObj),
	    contentType: 'application/json',
	    mimeType: 'application/json',
	    success: function(data) {
	        alert("success");
	    },
	    error:function(data,status,er) {
	        alert("error: "+data+" status: "+status+" er:"+er);
	        console.log(data);
	        console.log(er);
	    }
	});
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
  //console.log(JSON.stringify(myObj));
   $.ajax({
	    url: "/abdn/async/save/surcharge",
	    type: 'POST',
	    dataType: 'json',
	    data: JSON.stringify(myObj),
	    contentType: 'application/json',
	    mimeType: 'application/json',
	    success: function(data) {
	        alert("success");
	    },
	    error:function(data,status,er) {
	        alert("error: "+data+" status: "+status+" er:"+er);
	        console.log(data);
	        console.log(er);
	    }
	});
});



});
//range slider end


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
        $('#editMap').html('Save it')
    }else{
        BlitzMap.toggleEditable();
        isConfirmed = true;
        $('#jsonString').val(BlitzMap.toJSONString());
        BlitzMap.toKML();
        console.log("jsonString:" + $('#jsonString').val());
        console.log("kmlString:" + $('#kmlString').val());
        $('#editMap').html(' Edit it')
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




