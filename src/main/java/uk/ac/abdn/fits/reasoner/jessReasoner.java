package uk.ac.abdn.fits.reasoner;


import jess.*;

import java.net.*;
import java.io.*;
import java.util.*;

public class jessReasoner {

    // Start the Jess interpreter.
    Rete _rete = new Rete();

    // Maps a resourcetype to the number available to this agent.
    static private Hashtable _resourcesHT = new Hashtable();

    private int py_optCounter = 0;
    private int py_optTotal = 0;

    private String[] splited = null;
    private String optdetails = "";


    private String py_rqid = "";
    private String py_optid = "";
    private double py_fare = 0;
    private double py_time = 0;
    private int py_changes = 0;

    private String pref1 = "";
    private String pref2 = "";
    private String pref3 = "";
    private double py_farew = 0;
    private double py_timew = 0;
    private double py_changesw = 0;

    public String startReasonerAgent(String myoptions) throws JessException {


	try {
	    //instantiate the global variables
	    //GlobalVar g = new GlobalVar();

	    // Define a template for holding requests from other
	    // agents.
	    _rete.executeCommand("(deftemplate options (slot rqid (default \"none\")) (slot optid) (slot fare (default 0)) (slot time (default 1)) (slot changes (default 0)) (slot evaluated (default FALSE)) )");

	    _rete.executeCommand("(deftemplate ordering (slot rqid) (slot farew (default 3.0)) (slot timew (default 2.0)) (slot changesw (default 1.0)) )");

	    _rete.executeCommand("(deftemplate result (slot rqid) (slot optid) (slot score (default 0)) )");

	    _rete.executeCommand("(deftemplate counter (slot rqid) (slot cnt (default 0)) (slot total (default 0)))");

	    _rete.executeCommand("(deftemplate step (slot fstep (default 1)) (slot tstep (default 1)) (slot cstep (default 1)))");

	    Double py_fstep = 1.0;
	    Double py_tstep = 5.0;
	    Double py_cstep = 1.0;

	    Fact steps = new Fact("step", _rete);
	    steps.setSlotValue("fstep", new Value(py_fstep, RU.FLOAT));
 	    steps.setSlotValue("tstep", new Value(py_tstep, RU.FLOAT));
 	    steps.setSlotValue("cstep", new Value(py_cstep, RU.FLOAT));

   	   _rete.assertFact(steps);
	   _rete.executeCommand("(facts)");

	    if (myoptions.length() != 0) {
		splited = myoptions.split("#");
		for (int i=0; i < splited.length; i++){
	      		String row = splited[i];
			String[] rowItem = row.split(",");
       			if (rowItem.length >= 7){
     				py_rqid = rowItem[0];
				py_optid = rowItem[1];
				py_fare = Double.parseDouble(rowItem[2]);
				py_time = Double.parseDouble(rowItem[3]);
				py_changes = Integer.parseInt(rowItem[4]);
				pref1 = rowItem[5];
				pref2 = rowItem[6];
				pref3 = rowItem[7];
				//operatorpref = rowItem[8];
				//String[] operators = operatorpref.split(";");

				Fact opts = new Fact("options", _rete);
	    			opts.setSlotValue("rqid", new Value(py_rqid, RU.STRING));
 	   			opts.setSlotValue("optid", new Value(py_optid, RU.STRING));
 	   			opts.setSlotValue("fare", new Value(py_fare, RU.FLOAT));
 	   			opts.setSlotValue("time", new Value(py_time, RU.FLOAT));
 	   			opts.setSlotValue("changes", new Value(py_changes, RU.INTEGER));
 	   			//opts.setSlotValue("evaluated", new Value('FALSE'));

			   	_rete.assertFact(opts);
	    			_rete.executeCommand("(facts)");

				if (pref1.equals("Minimise fare")){
					py_farew = 9.0;
					System.out.println("Pref1: fare");
				}else if (pref1.equals("Minimise travel time")){
					py_timew = 9.0;
					System.out.println("Pref1: time");
				}else if (pref1.equals("Minimise number of changes")){
					py_changesw = 9.0;
					System.out.println("Pref1: changes");
				}else{ 
					System.out.println("Pref1: Malformed set of preferences");
					py_farew = 9.0; py_timew = 3.0; py_changesw = 1.0;
				}

				if (pref2.equals("Minimise fare")){
					py_farew = 3.0;
					System.out.println("Pref2: fare");
				}else if (pref2.equals("Minimise travel time")){
					py_timew = 3.0;
					System.out.println("Pref2: time");
				}else if (pref2.equals("Minimise number of changes")){
					py_changesw = 3.0;
					System.out.println("Pref2: changes");
				}else{ 
					System.out.println("Pref2: Malformed set of preferences");
					py_farew = 9.0; py_timew = 3.0; py_changesw = 1.0;
				}

				if (pref3.equals("Minimise fare")){
					py_farew = 1.0;
					System.out.println("Pref3: fare");
				}else if (pref3.equals("Minimise travel time")){
					py_timew = 1.0;
					System.out.println("Pref3: time");
				}else if (pref3.equals("Minimise number of changes")){
					py_changesw = 1.0;
					System.out.println("Pref3: changes");
				}else{ 
					System.out.println("Pref3: Malformed set of preferences");
					py_farew = 9.0; py_timew = 3.0; py_changesw = 1.0;;
				} 

				Fact prefs = new Fact("ordering", _rete);
	    			prefs.setSlotValue("rqid", new Value(py_rqid, RU.STRING));
 	   			prefs.setSlotValue("farew", new Value(py_farew, RU.FLOAT));
 	   			prefs.setSlotValue("timew", new Value(py_timew, RU.FLOAT));
 	   			prefs.setSlotValue("changesw", new Value(py_changesw, RU.FLOAT));

			   	_rete.assertFact(prefs);
	    			_rete.executeCommand("(facts)");


			} else { 
				System.out.println("Malformed set of oPtions... pls review");

			} // end of if rowItem

			System.out.println("Loading options "+ i +" of "+splited.length);

		} // for loop ends here

		Fact counters = new Fact("counter", _rete);
		counters.setSlotValue("rqid", new Value(py_rqid, RU.STRING));
 		counters.setSlotValue("cnt", new Value(py_optCounter, RU.INTEGER));
 		counters.setSlotValue("total", new Value(py_optTotal, RU.INTEGER));

   		_rete.assertFact(counters);
		_rete.executeCommand("(facts)");


	    }  //end of if myoptions.length
	    
	    // Add the SendScore function to rete.
	    _rete.addUserfunction(new SendScore());

	    // Add the SendEndSignal function to rete.
	    _rete.addUserfunction(new SendEndSignal());

	    // Initialise the reasoner
	    String dir = this.getClass().getClassLoader().getResource("").getPath();
	    _rete.executeCommand("(batch "+dir.replaceFirst("/", "")+"TESTreasoner.clp)");
	    
	    _rete.executeCommand("(run)");
	    System.out.println("After run"); 

	   

	}
	catch (JessException je) {
	    System.err.println(je);
	}
	
	 return GlobalVar.rtnStr;
	 
    }	

    public static void main(String[] args) {
	try{
		String agentID = "REASONER";
		int limit =5; int amt=0;

		//	new GER().startAgent(args[0], Integer.parseInt(args[1]), agentID, resourcetype, amount);

		jessReasoner reasoner = new jessReasoner();
		
//		String result= reasoner.startReasonerAgent(
//		"001,OPT1,10.0,104.0,2,minimise travel time,minimise fare,minimise number of changes#" +
//		"001,OPT2,8.0,104.0,2,minimise travel time,minimise fare,minimise number of changes#" + 
//		"001,OPT3,10.0,114.0,1,minimise travel time,minimise fare,minimise number of changes#" +
//		"001,OPT4,10.0,104.0,1,minimise travel time,minimise fare,minimise number of changes#");
    		
		
		String result= reasoner.startReasonerAgent("1383436432887,BABS bus service,6.8,1352,12,Minimise travel time,Minimise fare,Minimise number of changes,Patient Transport Service;Fixed-route service;Door-to-door service;Car service#"+
		"1383436432887,BABS car service,11.5,1352,12,Minimise travel time,Minimise fare,Minimise number of changes,Patient Transport Service;Fixed-route service;Door-to-door service;Car service#"+
				"1383436432887,Comcab,8.6,1352,12,Minimise travel time,Minimise fare,Minimise number of changes,Patient Transport Service;Fixed-route service;Door-to-door service;Car service#1383436432891,google_transit0,4.2,2345,2,Minimise travel time,Minimise fare,Minimise number of changes,Patient Transport Service;Fixed-route service;Door-to-door service;Car service#"+
		"1383436432887,google_transit1,4.2,2345,2,Minimise travel time,Minimise fare,Minimise number of changes,Patient Transport Service;Fixed-route service;Door-to-door service;Car service");
		    		
				
				
		System.out.println("Processing has ended::::: and the result is:");
		System.out.println("::::::>>>> Total processed is: " +GlobalVar.totalproc);
		System.out.println("::::::>>>> The option score string contains: " +GlobalVar.rtnStr);

		System.out.println(result);
	}catch (Exception e) {
	    System.err.println(e);
	}
   }

}

