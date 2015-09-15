package uk.ac.abdn.fits.reasoner;


/***********************************************************
 ** SendEndSignal.java
 ***********************************************************
 ***********************************************************
 ** A Jess primitive function for sending the end signal to the caller.
 ***********************************************************/

import jess.*;
import java.io.*;
import java.util.*;

public class SendEndSignal implements Userfunction {

    
    public String getName() { return "SendEndSignal"; }

    // This function expects three arguments:
    // 1. The total number of option processed

    // The function returns TRUE or FALSE, which are ATOMs that
    // represent boolean values.
    public Value call(ValueVector vv, Context context) throws JessException {

	try{
		String totalprocessed = vv.get(1).stringValue(context);
		GlobalVar.totalproc = Integer.parseInt(totalprocessed);

		System.out.println("SentEndSignal: Total processed-> "+ totalprocessed);

		System.out.println("Sent Total Processed");
		return new Value("TRUE", RU.ATOM);

    	}catch (Exception e) {
		System.err.println(e);
		return new Value("FALSE", RU.ATOM);
	}

    }
}
