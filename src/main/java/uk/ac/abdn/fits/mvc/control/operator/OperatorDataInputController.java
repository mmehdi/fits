package uk.ac.abdn.fits.mvc.control.operator;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import java.security.Principal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.supercsv.cellprocessor.ParseInt;

import uk.ac.abdn.fits.hibernate.model.FareMileageBands;
import uk.ac.abdn.fits.hibernate.model.FareStructure;
import uk.ac.abdn.fits.hibernate.model.OperatingArea;
import uk.ac.abdn.fits.hibernate.model.OperationalHours;
import uk.ac.abdn.fits.hibernate.model.Operator;
import uk.ac.abdn.fits.hibernate.model.OtherEligTable;
import uk.ac.abdn.fits.hibernate.model.PassengerElig;
import uk.ac.abdn.fits.hibernate.model.ServiceNotAvailable;
import uk.ac.abdn.fits.hibernate.model.User;
import uk.ac.abdn.fits.hibernate.model.Vehicle;
import uk.ac.abdn.fits.hibernate.operator.OperatorDataManager;
import uk.ac.abdn.fits.mvc.control.operator.HibUtils.Day;
import uk.ac.abdn.fits.mvc.control.operator.HibUtils.DaySession;
import uk.ac.abdn.fits.mvc.extensions.ajax.AjaxUtils;


/**
 * Handles requests for operators' data input
 */
@Controller
//@SessionAttributes("operatorInputForm")
public class OperatorDataInputController{
	
	private static final Logger logger = LoggerFactory.getLogger(OperatorDataInputController.class);
	
	private OperatorDataInputForm sessionOperatorInputForm = null;
	
	@InitBinder
	public void registerCustomerBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(double.class, new MyCustomNumberEditor(Double.class));
	    binder.registerCustomEditor(float.class, new MyCustomNumberEditor(Float.class));
	    binder.registerCustomEditor(long.class, new MyCustomNumberEditor(Long.class));
	    binder.registerCustomEditor(int.class, new MyCustomNumberEditor(Integer.class));
	}
	
	
	@ModelAttribute
	public void ajaxAttribute(WebRequest request, Model model) {
		System.out.println("ajaxRequest: " + AjaxUtils.isAjaxRequest(request));
		model.addAttribute("ajaxRequest", AjaxUtils.isAjaxRequest(request));
	}
	
	@ModelAttribute("operatorDataInputForm")
	public OperatorDataInputForm createQueryFormBean() {
		System.out.println("create OperatorDataInputForm");
		return new OperatorDataInputForm();
	}
	
	@RequestMapping(value = "/operator_data_input", method = RequestMethod.GET)
	public String getDataInputTemplate(Locale locale, Model model) {
		return "operator-data-input-template";
	}
	
	@RequestMapping(value = "/operator_data_input_constraint", method = RequestMethod.GET)
	public String getDataInputTemplateConstraint(Locale locale, Model model) {
		System.out.println("GET: /operator_data_input_constraint");
		ApplicationContext ctx = new ClassPathXmlApplicationContext("../spring/appServlet/hibernate.xml");
		OperatorDataManager operatorDataManager = 
		        (OperatorDataManager) ctx.getBean("operatorDataManagerImpl");
		List<Vehicle> vehcleTypes = operatorDataManager.getAllVehicle();
		List<String> vehicles = new ArrayList<String>();
		for(Vehicle v: vehcleTypes){
			if(!vehicles.contains(v.getDescription())){
				vehicles.add(v.getDescription());
			}
		}
		model.addAttribute("veh_types", vehicles);
		
		List<OtherEligTable> otherEligTypes = operatorDataManager.getOperatorOtherEligs();
		List<String> other_elig_types = new ArrayList<String>();
		for(OtherEligTable oe: otherEligTypes){
			if(oe.getElig() != null && !oe.getElig().equals("")){
				if(!other_elig_types.contains(oe.getElig())){
					other_elig_types.add(oe.getElig());
				}
			}
		}
		model.addAttribute("other_elig_types", other_elig_types);
		
		return "Operator data input";
	}
	
	@RequestMapping(value = "/operator_data_input_constraint_update", method = RequestMethod.GET)
	public String updateDataInputTemplateConstraint(Locale locale, Model model, HttpSession session, Principal principal) {
		System.out.println("GET: /operator_data_input_constraint_update");
		String name = principal.getName();
		System.out.println("login: "+ name);
		ApplicationContext ctx = new ClassPathXmlApplicationContext("../spring/appServlet/hibernate.xml");
		OperatorDataManager operatorDataManager = 
		        (OperatorDataManager) ctx.getBean("operatorDataManagerImpl");
		User user = operatorDataManager.getUser(name);
		
		List<Operator> operators = operatorDataManager.getOperatorByUserId(user.getId());
		List<String> operator_names = new ArrayList<String>();
		if(operators != null && operators.size() >0){
			for(Operator o: operators){
				if(o.getName()!=null){
					operator_names.add(o.getName());
				}
			}
		}else{
			operator_names.add("none");
			model.addAttribute("warning_no_operators", "0");
		}
		model.addAttribute("operators", operator_names);
		return "Operator data input update";
	}
	
	@RequestMapping(value = "/selectOperatorUpdate", method = RequestMethod.POST)
	public ModelAndView selectOperatorUpdate(Locale locale, Model model, @RequestParam("operator") String operatorName) {
		System.out.println("POST: /selectOperatorUpdate");
		ApplicationContext ctx = new ClassPathXmlApplicationContext("../spring/appServlet/hibernate.xml");
		OperatorDataManager operatorDataManager = 
		        (OperatorDataManager) ctx.getBean("operatorDataManagerImpl");
		Operator operator = operatorDataManager.getOperator(operatorName);
		
		return getDataInputTemplateConstraintEdit(operator.getOperator_id(), locale, model);
	}
	
	

	@RequestMapping(value = "/data_input_form_update", method = RequestMethod.POST)
	public String acceptOperatorUpdate(Locale locale, Model model, 
			@Valid OperatorDataInputForm operatorInputForm,
			HttpSession session, Principal principal) {
		
		int opId = operatorInputForm.getOperator_id();

		System.out.println("xxxxxxxxxxxxxxxxxxxx radio: "+ operatorInputForm.getTab_fare_structure_radioBtns());
		System.out.println("");

		ApplicationContext ctx = new ClassPathXmlApplicationContext("../spring/appServlet/hibernate.xml");
		OperatorDataManager operatorDataManager = 
		        (OperatorDataManager) ctx.getBean("operatorDataManagerImpl");
		Operator operator = operatorDataManager.getOperatorById(opId);
		HibUtils utils = new HibUtils();

		GeneralInfo generalInfo = new GeneralInfo();
		generalInfo.setFields(operatorInputForm);
		utils.updateGeneralInfo(opId, generalInfo);
	
//		session.setAttribute("general_info", generalInfo);

		VehicleInfo vehInfo = new VehicleInfo();
		vehInfo.setFields(operatorInputForm);
		utils.updateVehicle(opId, vehInfo);
	
		//jsonString empty crash
		if(operatorInputForm.getJsonData()==null)
			operatorInputForm.setJsonData("");
		
		OperatingAreaInfo operatingAreaInfo = new OperatingAreaInfo();
		operatingAreaInfo.setFields(operatorInputForm);
		utils.updateOperatingArea(opId,operatingAreaInfo);
		Elig eligInfo = new Elig();
		eligInfo.setFields(operatorInputForm);
		PassengerElig passenger_elig = operatorDataManager.getPassengerElig(opId);

		utils.updateElig(passenger_elig,eligInfo,opId);
		
		Fare fare = new Fare();
		fare.setField(operatorInputForm);
		utils.updateFare(opId, fare);
		
		
//		System.out.println("post /data_input_form_update");
	//	System.out.println("added_service_not_avail: " + operatorInputForm.getAdded_service_not_avail());
		//System.out.println("not avail from: "+ operatorInputForm.getNot_valid_from());*/
		return updateDataInputTemplateConstraint(locale, model, session, principal);
	
	}
	
	
	
	
	@RequestMapping(value = "/operator_data_input_constraint_edit/{opId}", method = RequestMethod.GET)
	public ModelAndView getDataInputTemplateConstraintEdit(@PathVariable("opId") int opId, Locale locale, Model model) {
		
//		System.out.println("post: /operator_data_input_constraint_edit  opId: "+ opId);
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("../spring/appServlet/hibernate.xml");
		OperatorDataManager operatorDataManager = 
		        (OperatorDataManager) ctx.getBean("operatorDataManagerImpl");
		Operator operator = operatorDataManager.getOperatorById(opId);
		model.addAttribute("operator_id",opId);
		model.addAttribute("serv_name", operator.getName());
		model.addAttribute("serv_desr", operator.getDescription());
		model.addAttribute("type_of_permit", operator.getType_of_permit());
		model.addAttribute("how2book", operator.getHow2book());
		if(operator.getName().equals("Cheng Service C")){
			System.out.println("debug: "+ operator.isInactive());
		}
		if(operator.isInactive()){
			model.addAttribute("not_avail", operator.isInactive());
		}
		List<OperationalHours> operationalHours = operatorDataManager.getOperationalHoursByOpId(opId);
		OperationalHours hours = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.UK);
		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		if(operationalHours!=null)
		for(int i=0; i< operationalHours.size();i++){
			hours = operationalHours.get(i);
			if(hours != null){
				switch (hours.getDay_of_week()) {
		         case "Monday":
		             if(hours.getSession().equals(DaySession.Morning.toString())){
		            	 if(hours.getOpening_time()!=null){
		            		 model.addAttribute("mon1", dateFormat.format(hours.getOpening_time()));
		            	 }
		            	 if(hours.getClosing_time()!=null){
		            		 model.addAttribute("mon2", dateFormat.format(hours.getClosing_time()));
		            	 }
		             }else{
		            	 if(hours.getOpening_time()!=null){
		            		 model.addAttribute("mon3", dateFormat.format(hours.getOpening_time()));
		            	 }
		            	 if(hours.getClosing_time()!=null){
		            		 model.addAttribute("mon4", dateFormat.format(hours.getClosing_time()));
		            	 }
		             }
		             break;
		         case "Tuesday":
		        	 if(hours.getSession().equals(DaySession.Morning.toString())){
		        		 if(hours.getOpening_time()!=null){
		        			 model.addAttribute("tue1", dateFormat.format(hours.getOpening_time()));
		            	 }
		            	 if(hours.getClosing_time()!=null){
		            		 model.addAttribute("tue2", dateFormat.format(hours.getClosing_time()));
		            	 }
		             }else{
		            	 if(hours.getOpening_time()!=null){
		            		 model.addAttribute("tue3", dateFormat.format(hours.getOpening_time()));
		            	 }
		            	 if(hours.getClosing_time()!=null){
		            		 model.addAttribute("tue4", dateFormat.format(hours.getClosing_time()));
		            	 }
		             }
		             break;
		         case "Wednesday":
		        	 if(hours.getSession().equals(DaySession.Morning.toString())){
		        		 if(hours.getOpening_time()!=null){
		        			 model.addAttribute("wed1", dateFormat.format(hours.getOpening_time()));
		            	 }
		            	 if(hours.getClosing_time()!=null){
		            		 model.addAttribute("wed2", dateFormat.format(hours.getClosing_time()));
		            	 }
		             }else{
		            	 if(hours.getOpening_time()!=null){
		            		 model.addAttribute("wed3", dateFormat.format(hours.getOpening_time()));
		            	 }
		            	 if(hours.getClosing_time()!=null){
		            		 model.addAttribute("wed4", dateFormat.format(hours.getClosing_time()));
		            	 }
		             }
		             break;
		         case "Thursday":
		        	 if(hours.getSession().equals(DaySession.Morning.toString())){
		        		 if(hours.getOpening_time()!=null){
		        			 model.addAttribute("thu1", dateFormat.format(hours.getOpening_time()));
		            	 }
		            	 if(hours.getClosing_time()!=null){
		            		 model.addAttribute("thu2", dateFormat.format(hours.getClosing_time()));
		            	 }
		             }else{
		            	 if(hours.getOpening_time()!=null){
		            		 model.addAttribute("thu3", dateFormat.format(hours.getOpening_time()));
		            	 }
		            	 if(hours.getClosing_time()!=null){
		            		 model.addAttribute("thu4", dateFormat.format(hours.getClosing_time()));
		            	 }
		             }
		             break;
		         case "Friday":
		        	 if(hours.getSession().equals(DaySession.Morning.toString())){
		        		 if(hours.getOpening_time()!=null){
		        			 model.addAttribute("fri1", dateFormat.format(hours.getOpening_time()));
		            	 }
		            	 if(hours.getClosing_time()!=null){
		            		 model.addAttribute("fri2", dateFormat.format(hours.getClosing_time()));
		            	 }
		             }else{
		            	 if(hours.getOpening_time()!=null){
		            		 model.addAttribute("fri3", dateFormat.format(hours.getOpening_time()));
		            	 }
		            	 if(hours.getClosing_time()!=null){
		            		 model.addAttribute("fri4", dateFormat.format(hours.getClosing_time()));
		            	 }
		             }
		             break;
		         case "Saturday":
		        	 if(hours.getSession().equals(DaySession.Morning.toString())){
		        		 if(hours.getOpening_time()!=null){
		        			 model.addAttribute("sat1", dateFormat.format(hours.getOpening_time()));
		        		 }
		        		 if(hours.getClosing_time()!=null){
		        			 model.addAttribute("sat2", dateFormat.format(hours.getClosing_time()));
		        		 }
		             }else{
		            	 if(hours.getOpening_time()!=null){
		            		 model.addAttribute("sat3", dateFormat.format(hours.getOpening_time()));
		            	 }
		            	 if(hours.getClosing_time()!=null){
		            		 model.addAttribute("sat4", dateFormat.format(hours.getClosing_time()));
		            	 }
		             }
		             break;
		         case "Sunday":
		        	 if(hours.getSession().equals(DaySession.Morning.toString())){
		        		 if(hours.getOpening_time()!=null){
		        			 model.addAttribute("sun1", dateFormat.format(hours.getOpening_time()));
		            	 }
		            	 if(hours.getClosing_time()!=null){
		            		 model.addAttribute("sun2", dateFormat.format(hours.getClosing_time()));
		            	 }
		             }else{
		            	 if(hours.getOpening_time()!=null){
		            		 model.addAttribute("sun3", dateFormat.format(hours.getOpening_time()));
		            	 }
		            	 if(hours.getClosing_time()!=null){
		            		 model.addAttribute("sun4", dateFormat.format(hours.getClosing_time()));
		            	 }
		             }
		             break;
		         default:
		             throw new IllegalArgumentException("Invalid day of the week: " + hours.getDay_of_week());
				}
			}
		}
		
		List<Vehicle> vehcleTypes = operatorDataManager.getAllVehicle();
		List<String> vehicles = new ArrayList<String>();
		for(Vehicle v: vehcleTypes){
			if(!vehicles.contains(v.getDescription())){
				vehicles.add(v.getDescription());
			}
		}
		/*vehicles.add("pickup Van");
		vehicles.add("minibus");
		vehicles.add("Wheelchair Accessible Car");
		vehicles.add("pickUpVan");*/
		model.addAttribute("vehicles", vehcleTypes);
		model.addAttribute("veh_types", vehicles);
		Vehicle veh = operatorDataManager.getVehicleByOpId(opId);

		if(veh != null){
			if(veh.getReg_num()!=null){
				 model.addAttribute("reg_num", veh.getReg_num());
			}
			if(veh.getVehicle_type()!=null){
				model.addAttribute("vehicle_type", veh.getVehicle_type());
				model.addAttribute("vehicle_description", veh.getDescription());

			}
		}
		OperatingArea area = operatorDataManager.getOperatingAreaByOpId(opId);
		if(area != null){
			model.addAttribute("jsonData", area.getJson().replaceAll("\"", "&#034;"));
//			model.addAttribute("kmlData", area.getKml().replaceAll("\"", "&#034;"));
//			System.out.println("kml: "+area.getKml().replaceAll("\"", "&#034;"));
		}
		PassengerElig elig = operatorDataManager.getPassengerElig(opId);
		if(elig != null){
			if(elig.isAble_bodied()){
				model.addAttribute("isAble_bodied", "true");
			}
			if(elig.isMobility_prevents_PT()){
				model.addAttribute("isMobility_prevents_PT", "true");
			}
			if(elig.isDiable_wheelchair_user()){
				model.addAttribute("isDisable_wheelchair_user", "true");
			}
			if(elig.isDiable_other()){
				model.addAttribute("isDisable_other", "true");
			}
			if(elig.isCant_live_on_a_bus_route()){
				model.addAttribute("cant_live_on_a_bus_route", "true");
			}
			if(elig.isAge_under16()){
				model.addAttribute("isAge_under16", "true");
			}
			if(elig.isAge_17to21()){
				model.addAttribute("isAge_17to21", "true");
			}
			if(elig.isAge_22to54()){
				model.addAttribute("isAge_22to54", "true");
			}
			if(elig.isAge_55to59()){
				model.addAttribute("isAge_55to59", "true");
			}
			if(elig.isAge_over60()){
				model.addAttribute("isAge_over60", "true");
			}
			if(elig.isHealth_appointment()){
				model.addAttribute("isHealth_appointment", "true");
			}
			if(elig.isSocial_care()){
				model.addAttribute("isSocial_care", "true");
			}
			if(elig.isShopping()){
				model.addAttribute("isShopping", "true");
			}
			if(elig.isLeisure_or_visiting_friends()){
				model.addAttribute("isLeisure_or_visiting_friends", "true");
			}
			if(elig.isSchool_or_education()){
				model.addAttribute("isSchool_or_education", "true");
			}
			if(elig.isWork_or_commuting()){
				model.addAttribute("isWork_or_commuting", "true");
			}
			if(elig.isOther_purpose()){
				model.addAttribute("isOther_purpose", "true");
			}
			if(elig.getElig_radioBtns()!=null){
				model.addAttribute("tab_elig_radioBtns", elig.getElig_radioBtns());
			}
			if(elig.getExplain_opening_up_elig()!=null){
				model.addAttribute("explain_opening_up_elig", elig.getExplain_opening_up_elig());
			}
		}
		List<OtherEligTable> otherEligTypes = operatorDataManager.getOperatorOtherEligs();
		List<String> other_elig_types = new ArrayList<String>();
		for(OtherEligTable oe: otherEligTypes){
			if(oe.getElig() != null && !oe.getElig().equals("")){
				if(!other_elig_types.contains(oe.getElig())){
					other_elig_types.add(oe.getElig());
				}
			}
		}
		model.addAttribute("other_elig_types", other_elig_types);
		otherEligTypes = operatorDataManager.getOperatorOtherEligByOpID(opId);
		if(otherEligTypes != null && otherEligTypes.size()>0){
			model.addAttribute("other_elig_type", otherEligTypes.get(0).getElig());
		}
		
		
		FareStructure fare = operatorDataManager.getFareStructureByOpId(opId);
		
		if(fare != null){
			if(fare.getFare_structure_radioBtns()!=null){
				model.addAttribute("tab_fare_structure_radioBtns", fare.getFare_structure_radioBtns());
			}
			if(fare.getHow_charge_radioBtns()!=null){
				model.addAttribute("tab_fare_structure_how_charge_radioBtns", fare.getHow_charge_radioBtns());
			}
			if(fare.getReturn_fare_multiplier()>=0){
				model.addAttribute("return_fare_multiplier", fare.getReturn_fare_multiplier());
			}
			if(fare.getDiscount_for_under16()>=0){
				model.addAttribute("discount_for_under16", fare.getDiscount_for_under16());
			}
			if(fare.getDiscount_for_over60()>=0){
				model.addAttribute("discount_for_over60", fare.getDiscount_for_over60());
			}
			if(fare.getDiscount_for_other_concessionary()>=0){
				model.addAttribute("discount_for_other_concessionary", fare.getDiscount_for_other_concessionary());
			}
			if(fare.isHas_escort()){
				model.addAttribute("isFare_structure_checkbox_escort", fare.isHas_escort());
			}
			if(fare.isCharge_for_dead_mileage()){
				model.addAttribute("isFare_structure_checkbox_charge_for_dead_mileage", fare.isCharge_for_dead_mileage());
			}
			List<FareMileageBands> bands = operatorDataManager.getFareMileageBandsByFareStrctId(fare.getId());
			FareMileageBands band = null;
			if(bands != null){
				if(bands.size()>0){
					band = bands.get(0);
					model.addAttribute("distance1_mile", band.getMile_2());
					model.addAttribute("fare_dist1_type", band.getType());
					model.addAttribute("dist1_charge", band.getCharge());
					System.out.println("fare dist ind: " + bands.size());
					model.addAttribute("fare_dist_ind", bands.size());
				}
//				if(bands.size()>1){
//					band = bands.get(1);
//					model.addAttribute("dist2_mile_1", band.getMile_1());
//					model.addAttribute("fare_dist2_type", band.getType());
//					model.addAttribute("dist2_mile_2", band.getMile_2());
//					model.addAttribute("dist2_charge", band.getCharge());
//				}
				if(bands.size()>1){
					bands.remove(0);
//					bands.remove(1);
					model.addAttribute("fare_dist_band", bands);
				}
			}
		}
		
		/* start to process service not available */
		dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.UK);
		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		try{
			List<ServiceNotAvailable> service_not_avail_list = operatorDataManager.getServiceNotAvailableByOpId(opId);
			if(service_not_avail_list != null && service_not_avail_list.size() >0){
				if(operator.isInactive()){
					model.addAttribute("not_valid_from", dateFormat.format(service_not_avail_list.get(0).getFrom()));
					model.addAttribute("not_valid_to", dateFormat.format(service_not_avail_list.get(0).getTo()));
					service_not_avail_list.remove(0);
					if(service_not_avail_list.size()>0){
						model.addAttribute("service_not_avail_list", service_not_avail_list);
						model.addAttribute("service_not_avail_ind", service_not_avail_list.size()+1);
					}
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		return new ModelAndView("Operator data input edit");
	}
	
	
	
	@RequestMapping(value = "/operator_data_input_constraint_preview", method = RequestMethod.GET)
	public String getDataInputTemplateConstraintPreview(Locale locale, Model model,
			@Valid OperatorDataInputForm operatorInputForm, HttpSession session) {
		
		System.out.println("post: /operator_data_input_constraint_preview");
		GeneralInfo generalInfo;
		Elig elig;
		Fare fare;
		Surcharge surcharge;
		generalInfo = new GeneralInfo();
		generalInfo.setFields(operatorInputForm);
		session.setAttribute("general_info", generalInfo);
		elig = new Elig();
		elig.setFields(operatorInputForm);
		session.setAttribute("elig", elig);
		
		return "Operator data input preview";
	}
	
	@RequestMapping(value = "/operator_data_input_constraint_preview_submit", method = RequestMethod.POST)
	public String getDataInputTemplateConstraintPreviewSubmit(Locale locale, Model model,
			@Valid OperatorDataInputForm operatorInputForm, HttpSession session) {
		
		System.out.println("post: /operator_data_input_constraint_preview_submit");
		return "Operator data input";
	}
	
	
	
	
	@RequestMapping(value = "/operator_data_input_complete", method = RequestMethod.GET)
	public String getDataInputTemplateComplete(Locale locale, Model model) {
		
		return "operator data input complete";
	}
	
	
	/**
	 * process the operator data input form 
	 * 
	 * @return
	 */
	@RequestMapping(value = "/operator_data_input_constraint", method = RequestMethod.POST)
	public ModelAndView  responseDataInput(@Valid OperatorDataInputForm operatorInputForm,
			@ModelAttribute("ajaxRequest") boolean ajaxRequest, HttpSession session,Principal principal,
			Model model, RedirectAttributes redirectAttrs) {
		
		System.out.println("post: /operator_data_input_constraint");
		session.setAttribute("service_name" , operatorInputForm.getService_name());
		session.setAttribute("service_description" , operatorInputForm.getService_description());
		session.setAttribute("jsonData" , operatorInputForm.getJsonData().replaceAll("\"", "&#034;"));
		session.setAttribute("eligible_checkbox_under16" , operatorInputForm.getEligible_checkbox_under16());
		session.setAttribute("eligible_checkbox_17_to_21" , operatorInputForm.getEligible_checkbox_17_to_21());
		session.setAttribute("eligible_checkbox_22_to_54" , operatorInputForm.getEligible_checkbox_22_to_54());
		session.setAttribute("eligible_checkbox_55_to_59" , operatorInputForm.getEligible_checkbox_55_to_59());
		session.setAttribute("eligible_checkbox_over60" , operatorInputForm.getEligible_checkbox_over60());
		session.setAttribute("eligible_checkbox_able_bodied" , operatorInputForm.getEligible_checkbox_able_bodied());
		session.setAttribute("eligible_checkbox_mobility_prevents_PT" , operatorInputForm.getEligible_checkbox_mobility_prevents_PT());
		session.setAttribute("eligible_checkbox_disable_wheelchair_user" , operatorInputForm.getEligible_checkbox_disable_wheelchair_user());
		session.setAttribute("eligible_checkbox_disable_other" , operatorInputForm.getEligible_checkbox_disable_other());
		session.setAttribute("eligible_checkbox_health_appointment" , operatorInputForm.getEligible_checkbox_health_appointment());
		session.setAttribute("eligible_checkbox_social_care" , operatorInputForm.getEligible_checkbox_social_care());
		session.setAttribute("eligible_checkbox_shopping" , operatorInputForm.getEligible_checkbox_shopping());
		session.setAttribute("eligible_checkbox_leisure_or_visiting_friends" , operatorInputForm.getEligible_checkbox_leisure_or_visiting_friends());
		session.setAttribute("eligible_checkbox_school_or_education" , operatorInputForm.getEligible_checkbox_school_or_education());
		session.setAttribute("eligible_checkbox_work_or_commuting" , operatorInputForm.getEligible_checkbox_work_or_commuting());
		session.setAttribute("eligible_checkbox_other_purpose" , operatorInputForm.getEligible_checkbox_other_purpose());
		/* fare structure - distance start to be done*/
		session.setAttribute("tab_fare_structure_radioBtns" , operatorInputForm.getTab_fare_structure_radioBtns());
		/* fare structure - distance end */
		session.setAttribute("return_fare_multiplier" , operatorInputForm.getReturn_fare_multiplier());
		System.out.println("return fare multiplier: "+ operatorInputForm.getReturn_fare_multiplier());
		session.setAttribute("discount_for_over60" , discount(operatorInputForm.getDiscount_for_over60()));
		session.setAttribute("discount_for_under16" ,discount(operatorInputForm.getDiscount_for_under16()));
		session.setAttribute("fare_structure_checkbox_escort" , operatorInputForm.isFare_structure_checkbox_escort());
		session.setAttribute("fare_structure_checkbox_charge_for_dead_mileage" , operatorInputForm.isFare_structure_checkbox_charge_for_dead_mileage());

		if(operatorInputForm.getTab_fare_structure_radioBtns().equals("0")){
			session.setAttribute("chargeFare" , "true");
			session.setAttribute("fareDist4Tuple" , getFareDistEntry(operatorInputForm));
		}
		
		
		GeneralInfo generalInfo = new GeneralInfo();
		generalInfo.setFields(operatorInputForm);
//		session.setAttribute("general_info", generalInfo);
		
		String username = principal.getName();
		HibUtils utils = new HibUtils();
		int opId = utils.insertGeneralInfo(username, generalInfo);
		
		VehicleInfo vehInfo = new VehicleInfo();
		vehInfo.setFields(operatorInputForm);
		utils.insertVehicle(opId, vehInfo);
		
		OperatingAreaInfo operatingArea = new OperatingAreaInfo();
		operatingArea.setFields(operatorInputForm);
		utils.insertOperatingArea(opId, operatingArea);
		
		Elig elig = new Elig();
		elig.setFields(operatorInputForm);
		utils.insertElig(opId, elig);
		
		Fare fare = new Fare();
		fare.setField(operatorInputForm);
		utils.insertFare(opId, fare);
		return new ModelAndView("Operator data input preview");
	}
	
	
	
	private List<_4TupleEntry> getFareDistEntry(OperatorDataInputForm operatorInputForm){
		
		List<_4TupleEntry> entries = new ArrayList<_4TupleEntry>();
			
		if(operatorInputForm.getFare_dist1_mile_2()>0){
			entries.add(new _4TupleEntry(0, operatorInputForm.getFare_dist1_mile_2(), operatorInputForm.getFare_dist1_type(), 
					operatorInputForm.getFare_dist1_charge()));
			if(operatorInputForm.getFare_dist2_mile_1()>0 
					&& operatorInputForm.getFare_dist2_mile_1() < operatorInputForm.getFare_dist2_mile_2()){
				entries.add(new _4TupleEntry(operatorInputForm.getFare_dist2_mile_1(), operatorInputForm.getFare_dist2_mile_2(), 
						operatorInputForm.getFare_dist2_type(), operatorInputForm.getFare_dist2_charge()));
			}
			String fare_dist = operatorInputForm.getAdded_fare_dist();
			String entry = null;
			StringTokenizer st = null;
			StringTokenizer entryST = null;
			String ind = null;
			String mile1 = null;
			String mile2 = null;
			String type = null;
			String fare = null;
			if(fare_dist != null && fare_dist.length()>0){
				st = new StringTokenizer(fare_dist, "#");
				while(st.hasMoreTokens()){
					entryST = new StringTokenizer(st.nextToken(), ",");
					ind = entryST.nextToken();
					mile1 = entryST.nextToken().trim();
					mile2 = entryST.nextToken().trim();
					type = entryST.nextToken();
					fare = entryST.nextToken().trim();
					if(mile1.length()>0){
						entries.add(new _4TupleEntry(new Double(mile1), new Double(mile2), type, new Double(fare)));
					}
				}
			}
			
			
//			if(operatorInputForm.getFare_dist3_mile_1()>0
//					&& operatorInputForm.getFare_dist3_mile_1()< operatorInputForm.getFare_dist3_mile_2()){
//				entries.add(new _4TupleEntry(operatorInputForm.getFare_dist3_mile_1(), operatorInputForm.getFare_dist3_mile_2(), 
//						operatorInputForm.getFare_dist3_type(), operatorInputForm.getFare_dist3_charge()));
//			}
//			if(operatorInputForm.getFare_dist4_mile_1()>0
//					&& operatorInputForm.getFare_dist4_mile_1()< operatorInputForm.getFare_dist4_mile_2()){
//				entries.add(new _4TupleEntry(operatorInputForm.getFare_dist4_mile_1(), operatorInputForm.getFare_dist4_mile_2(), 
//						operatorInputForm.getFare_dist4_type(), operatorInputForm.getFare_dist4_charge()));
//			}
//			
			
		}
		return entries;
	}
	
	
	
	private String discount(double per){
		DecimalFormat df = new DecimalFormat("0");
		String result = df.format(per);
		return result+"%";
	}
	
	private String currency(double amount){
		DecimalFormat df = new DecimalFormat("0.00");
		String result = df.format(amount);
		return "&#163;"+result;
	}
	
}
