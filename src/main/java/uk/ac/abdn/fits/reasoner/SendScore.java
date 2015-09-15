package uk.ac.abdn.fits.reasoner;


/***********************************************************
 ** SendScore.java
 ***********************************************************
 ***********************************************************
 ** A Jess primitive function for sending the score.
 ***********************************************************/

import jess.*;
import java.io.*;
import java.util.*;

public class SendScore implements Userfunction {

// Maps a resourcetype to the number available to this agent.
    //static private Hashtable _scoreHT = new Hashtable();
    
    public String getName() { return "SendScore"; }

    // This function expects three arguments:
    // 1. The requestid of the option
    // 2. The optionid of the option
    // 3. The score of the option

    // The function returns TRUE or FALSE, which are ATOMs that
    // represent boolean values.
    public Value call(ValueVector vv, Context context) throws JessException {
	String rqid = vv.get(1).stringValue(context);
	String optionid = vv.get(2).stringValue(context);
	String score = vv.get(3).stringValue(context);

	try{
		
		GlobalVar._HT.put(optionid, score);
		GlobalVar.rtnStr = GlobalVar.rtnStr + optionid + "," + score + "#";

		//new ScoreSendRequest(rqid, optionid, score);

		System.out.println("SentScore: "+rqid+" optionid: " + optionid +" score: " + score);

		System.out.println("Sent Unicast Reason");
		return new Value("TRUE", RU.ATOM);

    	}catch (Exception e) {
		System.err.println(e);
		return new Value("FALSE", RU.ATOM);
	}

    }
}
