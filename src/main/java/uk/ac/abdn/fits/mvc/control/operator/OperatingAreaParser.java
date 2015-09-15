package uk.ac.abdn.fits.mvc.control.operator;

import java.io.IOException;

import uk.ac.abdn.fits.mvc.control.operator.map.json.MapJsonString;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OperatingAreaParser {
	
	public static ObjectMapper mapper = new ObjectMapper();
	
	
	public void getResult(String jsonStr){
		try {
			MapJsonString value = mapper.readValue(jsonStr, MapJsonString.class);
			
			System.out.println(value.toString());
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void main(String[] args){
		String str = "{\"zoom\":5,\"tilt\":0,\"mapTypeId\":\"roadmap\",\"center\":{\"lat\":58.503988167829235,\"lng\":-7.092717700529065},\"overlays\":[{\"type\":\"polygon\",\"title\":\"service area\",\"content\":\"\",\"paths\":[[{\"lat\":\"58.539594766640484\",\"lng\":\"-3.0278317630290985\"},{\"lat\":\"56.022948079627454\",\"lng\":\"-7.8618161380290985\"},{\"lat\":\"55.45394132943304\",\"lng\":\"3.4760744869709015\"},{\"lat\":\"57.39762405500045\",\"lng\":\"-0.4350583255290985\"}]]},{\"type\":\"polygon\",\"title\":\"drop-off point\",\"content\":\"\",\"paths\":[[{\"lat\":\"58.65408464530598\",\"lng\":\"5.2338869869709015\"},{\"lat\":\"57.302789656350086\",\"lng\":\"2.8168947994709015\"},{\"lat\":\"56.58369172128337\",\"lng\":\"8.222168236970901\"}]]},{\"type\":\"polygon\",\"title\":\"drop-off point\",\"content\":\"\",\"paths\":[[{\"lat\":\"58.401711667608\",\"lng\":\"-12.739745825529099\"},{\"lat\":\"57.468589192089325\",\"lng\":\"-12.959472388029099\"},{\"lat\":\"57.961503094284794\",\"lng\":\"-7.8618161380290985\"}]]}]}";
		
		OperatingAreaParser parser = new OperatingAreaParser();
		parser.getResult(str);
	}

}
