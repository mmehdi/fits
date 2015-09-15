package uk.ac.abdn.fits.business.delegate;



/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

public class ServiceLocator {

	private static ServiceLocator instance = null;
	
	private InitialContext init_context;
	
	private Cache cache;
	
	private ServiceLocator(){
		init_context = new InitialContext();
		cache = null;
		
	}
	
	public static ServiceLocator getInstance(){
		if(instance != null){
			return instance;
		}
		instance = new ServiceLocator();
		return instance;
	}
	
	public void initContext(){
		init_context.initContext();
	}
	
	
}
