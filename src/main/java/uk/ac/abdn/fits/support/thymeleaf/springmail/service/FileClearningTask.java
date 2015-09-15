package uk.ac.abdn.fits.support.thymeleaf.springmail.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.TimerTask;

import uk.ac.abdn.fits.support.thymeleaf.springmail.web.MailController;

public class FileClearningTask extends TimerTask{
	
	

	@Override
	public void run() {
		System.out.println("clearning...");
		removeOutOfDatedHtml(MailController.htmlCreationTime);
	}

	
	private void removeOutOfDatedHtml(HashMap<String, Calendar> htmlCreationTime){
		
		Iterator<Entry<String, Calendar>> itr = htmlCreationTime.entrySet().iterator();
		Entry<String, Calendar> entry = null;
		long currentTimeInMillis = Calendar.getInstance().getTimeInMillis();
		Calendar c = null;
		long diff = 0;
		int diffHours = 0;
		boolean isRemoved = false;
		List<String> removed_html = new ArrayList<String>();
		while(itr.hasNext()){
			entry = itr.next();
			c = entry.getValue();
			diff = currentTimeInMillis - c.getTimeInMillis();
			diffHours = (int) (diff / ( 1000*60*60)); //(int) (diff / (60 *60 * 1000));
			if(diffHours >24){
				isRemoved = removeFile(entry.getKey());
				if(isRemoved){removed_html.add(entry.getKey());}
			}
		}
		for(int i=0; i< removed_html.size(); i++){
			htmlCreationTime.remove(removed_html.get(i));
		}
		
	}
	
	private boolean removeFile(String fileName){
		
		String path = this.getClass().getClassLoader().getResource("").getPath();
    	path = path+"../files/match_outputs";
    	File file = new File(path+File.separator+fileName);
    	if(file.exists()){
    		file.delete();
    		System.out.println("deleted file: "+path+File.separator+fileName);
    		return true;
    	}
		return false;
	}
}
