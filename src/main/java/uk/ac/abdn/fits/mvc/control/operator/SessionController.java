package uk.ac.abdn.fits.mvc.control.operator;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import java.security.Principal;
import java.util.List;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/async/save")
public class SessionController {
	
	
	
	// receives json data sent by client --> map it to GeneralInfo object
	// return GeneralInfo object as json
	@RequestMapping(value="/generalInfo", method = RequestMethod.POST)
	public @ResponseBody GeneralInfo saveGeneralInfo(
			@RequestBody final  GeneralInfo generalInfo,Principal principal,
			HttpSession session) {
		System.out.println("general info: \n"+generalInfo.toString());
		session.setAttribute("general_info", generalInfo);
		HibUtils utils = new HibUtils();
		utils.insertGeneralInfo(principal.getName(), generalInfo);
		return generalInfo;
	}
	
	@RequestMapping(value="/vehicle_info", method = RequestMethod.POST)
	public @ResponseBody VehicleInfo saveVehicleInfo(
			@RequestBody final  VehicleInfo vehicleInfo,
			HttpSession session) {
		System.out.println("vehicle info: \n"+vehicleInfo.toString());
		session.setAttribute("vehicle_info", vehicleInfo);
		HibUtils utils = new HibUtils();
//		utils.insertVehicle(vehicleInfo);
		return vehicleInfo;
	}
	
	
	
	
	// receives json data sent by client --> map it to GeneralInfo object
		// return GeneralInfo object as json
		@RequestMapping(value="/operatingAreaInfo", method = RequestMethod.POST)
		public @ResponseBody OperatingAreaInfo saveOperatingAreaInfo(
				@RequestBody final  OperatingAreaInfo operatingAreaInfo,
				HttpSession session) {
			System.out.println("operating area info: \n"+operatingAreaInfo.toString());
			session.setAttribute("operating_area_info", operatingAreaInfo);
			HibUtils utils = new HibUtils();
//			utils.insertGeneralInfo(operatingAreaInfo);
			return operatingAreaInfo;
		}
		
	
	
	@RequestMapping(value="/elig", method = RequestMethod.POST)
	public @ResponseBody Elig saveElig(
			@RequestBody final  Elig elig,
			HttpSession session) {
		
		System.out.println(elig.toString());
		HibUtils utils = new HibUtils();
		utils.insertElig(0 ,elig);
		session.setAttribute("elig", elig);
		return elig;
	}
	
	
	
	@RequestMapping("/fare")
	public @ResponseBody Fare  saveFareStructure(
			@RequestBody final  Fare fare,
			HttpSession session) {
		
		System.out.println(fare.toString());
		session.setAttribute("fare", fare);
		return fare;
	}
	
	@RequestMapping("/surcharge")
	public @ResponseBody Surcharge saveSurcharge(
			@RequestBody Surcharge surcharge,
			HttpSession session) {
		session.setAttribute("surcharge", surcharge);
		System.out.println(surcharge.toString());
		return surcharge;
	}
	

}
