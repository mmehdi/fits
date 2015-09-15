package uk.ac.abdn.fits.business.restful;



/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

public class RESTFulRequest {

	private final long id;
    private final String content;

    public RESTFulRequest(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
    
}
