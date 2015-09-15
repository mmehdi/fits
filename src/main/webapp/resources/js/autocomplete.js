	// This example displays an address form, using the autocomplete feature
	// of the Google Places API to help users fill in the information.

	var placeSearch, autocomplete_f, autocomplete_t;
	var componentForm = {
	  street_number: 'short_name',
	  route: 'long_name',
	  locality: 'long_name',
	  //administrative_area_level_1: 'short_name',
	  //country: 'long_name',
	  postal_code: 'short_name'
	};
	
	function initialize() {
	  // Create the autocomplete object, restricting the search
	  // to geographical location types.
	  autocomplete_f_outward = new google.maps.places.Autocomplete(
	      /** @type {HTMLInputElement} */(document.getElementById('autocomplete_f_outward')),
	      { types: ['geocode'] });
	  // When the user selects an address from the dropdown,
	  // populate the address fields in the form.
	  google.maps.event.addListener(autocomplete_f_outward, 'place_changed', function() {
	    fillInAddress('f_o');
	  });
	  
	  autocomplete_t_outward = new google.maps.places.Autocomplete(
		      /** @type {HTMLInputElement} */(document.getElementById('autocomplete_t_outward')),
		      { types: ['geocode'] });
	  
	  google.maps.event.addListener(autocomplete_t_outward, 'place_changed', function() {
		    fillInAddress('t_o');
		  });
	  
	  autocomplete_f_return = new google.maps.places.Autocomplete(
		      /** @type {HTMLInputElement} */(document.getElementById('autocomplete_f_return')),
		      { types: ['geocode'] });
		  // When the user selects an address from the dropdown,
		  // populate the address fields in the form.
	  google.maps.event.addListener(autocomplete_f_return, 'place_changed', function() {
	    fillInAddress('f_r');
	  });
	  
	  
	  autocomplete_t_return = new google.maps.places.Autocomplete(
		      /** @type {HTMLInputElement} */(document.getElementById('autocomplete_t_return')),
		      { types: ['geocode'] });
	  
	  google.maps.event.addListener(autocomplete_t_return, 'place_changed', function() {
		    fillInAddress('t_r');
		  });
	  
	}
	
	// The START and END in square brackets define a snippet for our documentation:
	// [START region_fillform]
	function fillInAddress(type) {
		
		var place = null;
		if (type == 'f_o'){
			// Get the place details from the autocomplete object.
			place = autocomplete_f_outward.getPlace();
			for (var component in componentForm) {
			    document.getElementById(component+"_f_outward").value = '';
//			    document.getElementById(component+"_f_outward").disabled = false;
			}
		}else if(type == 't_o'){
			 place = autocomplete_t_outward.getPlace();
			 for (var component in componentForm) {
				 document.getElementById(component+"_t_outward").value = '';
//				 document.getElementById(component+"_t_outward").disabled = false;
			 }
		}else if(type == 'f_r'){
			 place = autocomplete_f_return.getPlace();
			 for (var component in componentForm) {
				 document.getElementById(component+"_f_return").value = '';
//				 document.getElementById(component+"_f_return").disabled = false;
			 }
		}else if(type == 't_r'){
			 place = autocomplete_t_return.getPlace();
			 for (var component in componentForm) {
				 document.getElementById(component+"_t_return").value = '';
//				 document.getElementById(component+"_t_return").disabled = false;
			 }
		}
		
	
	  // Get each component of the address from the place details
	  // and fill the corresponding field on the form.
	  for (var i = 0; i < place.address_components.length; i++) {
		  
	    var addressType = place.address_components[i].types[0];
	    if (componentForm[addressType]) {
	      var val = place.address_components[i][componentForm[addressType]];
	      if(type == 'f_o'){
	    	  document.getElementById(addressType+"_f_outward").value = val;
	      }else if(type == 't_o'){
	    	  document.getElementById(addressType+"_t_outward").value = val;
	      }else if(type == 'f_r'){
	    	  document.getElementById(addressType+"_f_return").value = val;
	      }else if(type == 't_r'){
	    	  document.getElementById(addressType+"_t_return").value = val;
	      }
	      
	    }
	  }
	}
	// [END region_fillform]
	
	// [START region_geolocation]
	// Bias the autocomplete object to the user's geographical location,
	// as supplied by the browser's 'navigator.geolocation' object.
	function geolocate_f_outward() {
	  if (navigator.geolocation) {
	    navigator.geolocation.getCurrentPosition(function(position) {
	      var geolocation = new google.maps.LatLng(
	          position.coords.latitude, position.coords.longitude);
	      autocomplete_f_outward.setBounds(new google.maps.LatLngBounds(geolocation,
	          geolocation));
	    });
	  }
	}
	
	
	function geolocate_t_outward() {
		  if (navigator.geolocation) {
		    navigator.geolocation.getCurrentPosition(function(position) {
		      var geolocation = new google.maps.LatLng(
		          position.coords.latitude, position.coords.longitude);
		      autocomplete_t_outward.setBounds(new google.maps.LatLngBounds(geolocation,
		          geolocation));
		    });
		  }
		}
	
	
	function geolocate_f_return() {
		  if (navigator.geolocation) {
		    navigator.geolocation.getCurrentPosition(function(position) {
		      var geolocation = new google.maps.LatLng(
		          position.coords.latitude, position.coords.longitude);
		      autocomplete_f_return.setBounds(new google.maps.LatLngBounds(geolocation,
		          geolocation));
		    });
		  }
		}
		
		
	function geolocate_t_return() {
			  if (navigator.geolocation) {
			    navigator.geolocation.getCurrentPosition(function(position) {
			      var geolocation = new google.maps.LatLng(
			          position.coords.latitude, position.coords.longitude);
			      autocomplete_t_return.setBounds(new google.maps.LatLngBounds(geolocation,
			          geolocation));
			    });
			  }
			}
		
	// [END region_geolocation]
	
	
	
	
	
