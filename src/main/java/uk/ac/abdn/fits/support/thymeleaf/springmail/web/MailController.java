/*
 * =============================================================================
 * 
 *   Copyright (c) 2011-2014, The THYMELEAF team (http://www.thymeleaf.org)
 * 
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 * 
 * =============================================================================
 */
package uk.ac.abdn.fits.support.thymeleaf.springmail.web;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletConfigAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.ui.Model;

import uk.ac.abdn.fits.business.client.option.TOption;
import uk.ac.abdn.fits.business.restful.RESTFulRequest;
import uk.ac.abdn.fits.support.thymeleaf.springmail.service.EmailService;



@Controller
public class MailController implements ServletConfigAware{

    @Autowired 
    private EmailService emailService;


    @Autowired
    private ViewResolver viewResolver;
    
    
    public static HashMap<String, Calendar> htmlCreationTime = new HashMap<String, Calendar>(); 
    
//    @RequestMapping("/")
//    public String root() {
//        return "redirect:/index.html";
//    }

    
    /* Home page. */
//    @RequestMapping("/index.html")
//    public String index() {
//        return "index.html";
//    }

    
    /* Sending confirmation page. */
//    @RequestMapping("/sent.html")
//    public String sent() {
//        return "sent.html";
//    }
    
    private ServletConfig config;

    public void setServletConfig(ServletConfig servletConfig) {
        this.config = servletConfig;
    }
    
    
    
    
    
    /* 
     * Send HTML mail (simple) 
     */
    @RequestMapping(value = "/sendMailSimple", method = RequestMethod.POST)
    public @ResponseBody RESTFulRequest sendSimpleMail(
    		HttpSession session,
    		HttpServletRequest request, 
    		@RequestBody final EmailAddress emailAddr,
//    		HttpServletResponse response,
    		Model model,
            final Locale locale) 
            throws MessagingException {

    	
    	
    	 String recipientName = "Customer";
    	 String recipientEmail = "recipient@email.com";
    	 String fname = (String)session.getAttribute("fname");
    	 String lname = (String)session.getAttribute("lname");
    	 String email = null;
    	 
    	 if(emailAddr!=null){
    		 email = emailAddr.getEmail();
     	}else{
     		email = (String)session.getAttribute("email");
     	}
    	 
    	 if(fname!= null && lname != null){
    		 recipientName = fname+" "+ lname;
    		 System.out.println("recipientName: "+recipientName);
    	 }
    	 if(email!= null && !email.equals("")){
    		 recipientEmail = email;
    		 System.out.println("recipientEmail: "+recipientEmail);
    	 }
    	 
    	List<TOption> not_relaxed = (List<TOption>)session.getAttribute("options");
    	List<TOption> relaxed_options = (List<TOption>)session.getAttribute("relaxed_options");
    	String date_of_travel = (String)session.getAttribute("date_of_travel");
    	String date_of_travel_rtn = (String)session.getAttribute("date_of_travel_rtn");

    	String origin_postcode = (String)session.getAttribute("origin_postcode");
    	
    	List<TOption> not_relaxed_rtn = (List<TOption>)session.getAttribute("options_rtn");
    	List<TOption> relaxed_options_rtn = (List<TOption>)session.getAttribute("relaxed_options_rtn");
    	String origin_postcode_rtn = (String)session.getAttribute("origin_postcode_rtn");
    	
    	
    	String url = null;
    	View resolvedView;
    	
		try {
			model.addAttribute("date_of_travel", date_of_travel);
			model.addAttribute("date_of_travel_rtn", date_of_travel_rtn);
			model.addAttribute("origin_postcode",origin_postcode);
			model.addAttribute("options", not_relaxed);
			model.addAttribute("relaxed_options", relaxed_options);
			model.addAttribute("caption", "Transport options ranked using preferences" );
			model.addAttribute("origin_postcode_rtn",origin_postcode_rtn);
			model.addAttribute("options_rtn", not_relaxed_rtn);
			model.addAttribute("relaxed_options_rtn", relaxed_options_rtn);
			model.addAttribute("email_view", "email_view");
			resolvedView = this.viewResolver.resolveViewName("matching", Locale.UK);
			MockHttpServletResponse mockResp = new MockHttpServletResponse();
	    	resolvedView.render(model.asMap(), request, mockResp);
//	    	System.out.println("rendered html : " + mockResp.getContentAsString());
	    	url = saveAsHtml(request, mockResp.getContentAsString()); 
	    	emailService.sendRichMail(recipientName, recipientEmail, locale, url);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return new RESTFulRequest(1,
        		"mail sent to "+ recipientEmail);
    }

    
    @RequestMapping(value = "/result/{filename:[\\w]+\\.html}", method = RequestMethod.GET)
    public String retrieveHtml(
    		@PathVariable("filename") String filename,
    		Model model,
            final Locale locale){
	
    	String path = this.getClass().getClassLoader().getResource("").getPath();
    	path = path+"../files/match_outputs";
    	File file = new File(path+File.separator+filename);
    	
    	if(!file.exists()){
    		System.out.println("request resource does not exist.");
    		return "error";
    	}
    	System.out.println("retrieveHtml redirect: "+ "redirect: /files/"+filename);
    	return "redirect:/files/"+filename;
    	
	}
    
    private String saveAsHtml(HttpServletRequest request, String htmlContent){
    	
    	
//    	String path = request.getSession().getServletContext().getRealPath("");
    	String path = this.getClass().getClassLoader().getResource("").getPath();
    	path = path+"../files";
    	File  dir = new File(path);
    	if(!dir.exists()){
    		dir.mkdirs();
    	}
    	path = path+File.separator+"match_outputs";
    	dir = new File(path);
    	if(!dir.exists()){
    		dir.mkdirs();
    	}
    	String fileName = getFileName();
    	String url = null;
    	FileWriter fw;
    	BufferedWriter bw;
    	Calendar c = Calendar.getInstance();
    	try {
			fw = new FileWriter(new File(path+File.separator+fileName));
			bw = new BufferedWriter(fw);
			bw.write(htmlContent);
			bw.flush();
			bw.close();   
			htmlCreationTime.put(fileName, c);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	//path = request.getSession().getServletContext().getRealPath("/WEB-INF/files/");
    	String server_uri = request.getScheme() + "://" + request.getServerName() +  ("http".equals(request.getScheme()) && request.getServerPort() == 80 || "https".equals(request.getScheme()) && request.getServerPort() == 443 ? "" : ":" + request.getServerPort() ) +request.getContextPath();
    	
    	server_uri = server_uri +"/result";
    	System.out.println("session path: "+path);
    	path = "http://localhost1:8585/ke/result";
//    	path = "http://139.133.73.11:8080/ke/result";
//    	path = "http://fits.abdn.ac.uk/ke/result";
    	path = server_uri; 	
		System.out.println("URL: "+path+"/"+fileName);
    	return path+"/"+fileName;
    }
    
    public String getFileName(){
		String timeAsString = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String id = RandomStringUtils.randomAlphabetic(4);
		return timeAsString+id+".html";
	}
    
    /* 
     * Send HTML mail with attachment. 
     */
    @RequestMapping(value = "/sendMailWithAttachment", method = RequestMethod.POST)
    public String sendMailWithAttachment(
            @RequestParam("recipientName") final String recipientName,
            @RequestParam("recipientEmail") final String recipientEmail,
            @RequestParam("attachment") final MultipartFile attachment,
            final Locale locale) 
            throws MessagingException, IOException {

        this.emailService.sendMailWithAttachment(
                recipientName, recipientEmail, attachment.getOriginalFilename(), 
                attachment.getBytes(), attachment.getContentType(), locale);
        return "redirect:sent.html";
        
    }

    
    
    /* 
     * Send HTML mail with inline image
     */
    @RequestMapping(value = "/sendMailWithInlineImage", method = RequestMethod.POST)
    public String sendMailWithInline(
            @RequestParam("recipientName") final String recipientName,
            @RequestParam("recipientEmail") final String recipientEmail,
            @RequestParam("image") final MultipartFile image,
            final Locale locale)
            throws MessagingException, IOException {

        this.emailService.sendMailWithInline(
                recipientName, recipientEmail, image.getName(), 
                image.getBytes(), image.getContentType(), locale);
        return "redirect:sent.html";
        
    }

    
    
    @ExceptionHandler(Exception.class)
    public String error() {
        return "error.html";
    }
    
}

class EmailAddress implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2033219618344674668L;
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
