package uk.ac.abdn.fits.business.bc;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

import uk.ac.abdn.fits.business.client.QueryCommand;
import uk.ac.abdn.fits.business.client.option.TOption;
import uk.ac.abdn.fits.reasoner.GlobalVar;
import uk.ac.abdn.fits.reasoner.jessReasoner;
import jess.JessException;

public class JessReasoner implements BusinessComponent{

	public JessReasoner(){
		
	}
	
	public List<TOption> ranking(List<TOption> options, QueryCommand query){
		
//		UtilsHelper utilsHelper = new UtilsHelper();
		List<TOption> ranked_options = null;
//		TransportOption option = null;
//		for(TOption response: responses){
//			option = utilsHelper.createTransportOption(response, calculate_fare(response), queryForm);
//			tranport_options.add(option);
//		}
		String jess_result = pass_to_reasoner(options, query); // jess reasoner is expired.
//		String jess_result = "";
		ranked_options = parseJessResult(options, jess_result);
		return ranked_options;
	}
	
	
	private String pass_to_reasoner(List<TOption> options, QueryCommand query){
		
		String para = getParameter(options, query);
		jessReasoner reasoner = new jessReasoner();
		String result_from_reasoner = null;
		try {
			result_from_reasoner = reasoner.startReasonerAgent(para);
		} catch (JessException e) {
			e.printStackTrace();
		}
//		System.out.println(result_from_reasoner);
		if(result_from_reasoner != null){
			GlobalVar.rtnStr = "";
		}
		return result_from_reasoner;
	}
	
	
	private String getParameter(List<TOption> options, QueryCommand query){
		final int standard_bus_fare = 10;
		StringBuilder para = new StringBuilder();
		TOption option = null;
//		option = options.get(0);
		String optionId = ""+query.getOriginalRequest().getLoggedTime();
		for(int i=0; i<  options.size(); i++){
			option = options.get(i);
			para.append(optionId); // unix time 
			para.append(",");
			para.append(""+option.getSourceID()+"");
			para.append(",");
			if(option.getFare() == -1){ // free
				para.append(0); 
			}else if(option.getFare() == -2 || option.getFare() == -3){ // standard bus fare for FTS
				para.append(standard_bus_fare); 
			}else if(option.getFare() == 0){ // google transit; the fare is 0 by default.
				para.append(standard_bus_fare); 
			}else{
				para.append(option.getFare()); // double 
			}
			para.append(",");
			if(option.getSourceType().equals("operators")){
				para.append((int)(new Double(option.getDirectionsResponse().getRoutes()[0].getLegs()[0].getDuration().getValue(BusinessOperaotor.TIME_PENALTY))/60)); // second
			}else{
				para.append((int)(new Double(option.getDirectionsResponse().getRoutes()[0].getLegs()[0].getDuration().getValue())/60)); // second
			}
			para.append(",");
			para.append(option.getChanges());
			para.append(",");
			para.append(""+query.getOriginalRequest().getPreference()[0]+"");
			para.append(",");
			para.append(""+query.getOriginalRequest().getPreference()[1]+"");
			para.append(",");
			para.append(""+query.getOriginalRequest().getPreference()[2]+"");
			para.append("#");
		}
//		System.out.println(para.toString());
		return para.toString();
	}
	
	
	private int getRandInt(int low, int high){
		int range = high - low;
		Random generator = new Random(); 
		int rand = generator.nextInt(range) + low;
		return rand;
	}
	
	private List<TOption> parseJessResult(List<TOption> options, String result){
		
		ArrayList<TOption> ranking = new ArrayList<TOption>();
		StringTokenizer st = new StringTokenizer(result, "#");
		String temp = null;
		TOption option = null;
		String[] sourceID_and_score = new String[2];
		HashSet<String> sourceIDs = new HashSet<String>();
		while(st.hasMoreTokens()){
			temp = st.nextToken().trim();
			sourceID_and_score = temp.split(",");
			if(sourceID_and_score.length == 2){ 
				if(!sourceIDs.contains(sourceID_and_score[0].trim())){
					sourceIDs.add(sourceID_and_score[0].trim());
					option = getBySourceID(options, sourceID_and_score[0].trim(), sourceID_and_score[1].trim());
					if(option != null){
						ranking.add(option);
					}
				}
			}
		}
		Collections.sort(ranking, new TOptionComparable());
		
		for(TOption to: ranking){
			System.out.println(to.getSourceID()+" "+to.getScore());
		}
		
//		return ranking; // jess reasoner is expired
		return options;
	}
	
	private TOption getBySourceID(List<TOption> options, String sourceID, String score){
		
		for(int i=0; i< options.size(); i++){
			if(options.get(i).getSourceID().equals(sourceID)){
				options.get(i).setScore(new Double(score));
				return options.get(i);
			}
		}
		return null;
	}

}

class TOptionComparable implements Comparator<TOption>{
	 
    @Override
    public int compare(TOption o1, TOption o2) {
        return (o1.getScore()>o2.getScore() ? 1 : (o1.getScore()==o2.getScore() ? 0 : -1));
    }
} 
