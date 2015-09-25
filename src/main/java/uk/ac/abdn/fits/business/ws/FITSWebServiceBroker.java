package uk.ac.abdn.fits.business.ws;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import java.util.ArrayList;
import java.util.List;

import uk.ac.abdn.fits.business.client.QueryCommand;
import uk.ac.abdn.fits.business.client.option.TOption;
import uk.ac.abdn.fits.business.delegate.BusinessDelegate;
import uk.ac.abdn.fits.business.delegate.FTSBusinessDelegate;
import uk.ac.abdn.fits.business.delegate.GoogleBusinessDelegate;
import uk.ac.abdn.fits.business.delegate.Reasoner;
import uk.ac.abdn.fits.business.delegate.ReasoningDelegate;
import uk.ac.abdn.fits.business.delegate.ServiceLocator;


public class FITSWebServiceBroker {
	
	
	private ArrayList<BusinessDelegate> business_delegates;
	
	private ReasoningDelegate reasoner;
	
	public FITSWebServiceBroker(){
		business_delegates = new ArrayList<BusinessDelegate>();
		business_delegates.add(FTSBusinessDelegate.getInstance());
		business_delegates.add(new GoogleBusinessDelegate()); //add GoogleBusinessDelegate as BusinessDelegate
		reasoner = new Reasoner();
		ServiceLocator.getInstance().initContext();
	}
	
	public List<TOption> query(QueryCommand query){
		
		List<TOption> options = new ArrayList<TOption>();
		for(BusinessDelegate business_delegate: business_delegates){
			options.addAll(business_delegate.query(query));
		}
		List<TOption> relaxed = new ArrayList<TOption>();
		List<TOption> not_relaxed = new ArrayList<TOption>();
		for(TOption option: options){
			if(option.isRelaxed()){
				relaxed.add(option);
				continue;
			}
			not_relaxed.add(option);
		}
			options.clear();
			options = reasoner.rank(not_relaxed, query);
			options.addAll(relaxed);
		return options;
	}
	
	
	private double[] rescale_similarity(List<TOption> options){
		double[] similarity = new double[options.size()];
		double total = 0;
		double avg = 0;
		for(int i=0; i< similarity.length; i++){
			similarity[i] = new Double(options.get(i).getScore());
			total += similarity[i];
		}
		avg = total/similarity.length;
		for(int i=0; i< similarity.length; i++){
			similarity[i] = avg/ (similarity[i] + avg/2);
		}
		return similarity;
	}
	
	
}
