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
package uk.ac.abdn.fits.support.thymeleaf.springmail.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.WebContext;

import uk.ac.abdn.fits.business.client.option.TOption;

@Service
public class EmailService {

    @Autowired 
    private JavaMailSender mailSender;
    
    @Autowired 
    private TemplateEngine templateEngine;

    

    /* 
     * Send HTML mail (simple) 
     */
    public void sendSimpleMail(
            final String recipientName, final String recipientEmail, final Locale locale) 
            throws MessagingException {
        
        // Prepare the evaluation context
        final Context ctx = new Context(locale);
        ctx.setVariable("name", recipientName);
        ctx.setVariable("subscriptionDate", new Date());
        ctx.setVariable("hobbies", Arrays.asList("Cinema", "Sports", "Music"));
        
        // Prepare message using a Spring helper
        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
        message.setSubject("Example HTML email (simple)");
        message.setFrom("thymeleaf@example.com");
        message.setTo(recipientEmail);

        // Create the HTML body using Thymeleaf
        final String htmlContent = this.templateEngine.process("email-simple.html", ctx);
        
//        System.out.println("htmlContent:");
//        System.out.println(htmlContent);
        message.setText(htmlContent, true /* isHtml */);
        
        // Send email
        this.mailSender.send(mimeMessage);
    }
    
    /* 
     * Send HTML mail (rich html) 
     */
    public void sendRichMail(
            final String recipientName, final String recipientEmail, final Locale locale,
           String URL
    		) 
            throws MessagingException {
        
    	
        // Prepare the evaluation context
        final Context ctx = new Context();
        ctx.setVariable("name", recipientName);
        ctx.setVariable("sentDate", new Date());
        ctx.setVariable("link", URL);
       
        // Prepare message using a Spring helper
        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
        message.setSubject("Your FITS KE Query Result");
        message.setFrom("info.fitske@gmail.com");
        message.setTo(recipientEmail);
        // Create the HTML body using Thymeleaf
        final String htmlContent = this.templateEngine.process("email-match.html", ctx);
        message.setText(htmlContent, true /* isHtml */);
        // Send email
        this.mailSender.send(mimeMessage);
    }

    /* 
     * Send HTML mail (rich html) 
     */
    public void sendRemindMail(
            final String recipientName, final String recipientEmail, final Locale locale,
           final Date last_update, final int operatorId
    		) 
            throws MessagingException {
        
//    	String path = "http://139.133.73.11:8080/ke/operator";
    	String path = "http://fits.abdn.ac.uk/ke/operator";
    	String link1 = path+"/operator_data_input_constraint";
    	String link2 = path+"/"+operatorId+"/no_changes";
    	
        // Prepare the evaluation context
        final Context ctx = new Context();
        ctx.setVariable("name", recipientName);
        ctx.setVariable("last_update", last_update);
        ctx.setVariable("operatorId", operatorId);
        ctx.setVariable("link1", link1);
        ctx.setVariable("link2", link2);
        
       
        // Prepare message using a Spring helper
        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
        message.setSubject("Your FITS KE Query Result");
        message.setFrom("info.fitske@gmail.com");
        message.setTo(recipientEmail);
        // Create the HTML body using Thymeleaf
        final String htmlContent = this.templateEngine.process("email-remind.html", ctx);
        message.setText(htmlContent, true /* isHtml */);
        // Send email
        this.mailSender.send(mimeMessage);
    }
    
    
    
    
    /* 
     * Send HTML mail with attachment. 
     */
    public void sendMailWithAttachment(
            final String recipientName, final String recipientEmail, final String attachmentFileName, 
            final byte[] attachmentBytes, final String attachmentContentType, final Locale locale) 
            throws MessagingException {
        
        // Prepare the evaluation context
        final Context ctx = new Context(locale);
        ctx.setVariable("name", recipientName);
        ctx.setVariable("subscriptionDate", new Date());
        ctx.setVariable("hobbies", Arrays.asList("Cinema", "Sports", "Music"));
        
        // Prepare message using a Spring helper
        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message = 
                new MimeMessageHelper(mimeMessage, true /* multipart */, "UTF-8");
        message.setSubject("Example HTML email with attachment");
        message.setFrom("thymeleaf@example.com");
        message.setTo(recipientEmail);

        // Create the HTML body using Thymeleaf
        final String htmlContent = this.templateEngine.process("email-withattachment.html", ctx);
        message.setText(htmlContent, true /* isHtml */);
        
        // Add the attachment
        final InputStreamSource attachmentSource = new ByteArrayResource(attachmentBytes);
        message.addAttachment(
                attachmentFileName, attachmentSource, attachmentContentType);
        
        // Send mail
        this.mailSender.send(mimeMessage);
        
    }

    
    
    /* 
     * Send HTML mail with inline image
     */
    public void sendMailWithInline(
            final String recipientName, final String recipientEmail, final String imageResourceName, 
            final byte[] imageBytes, final String imageContentType, final Locale locale)
            throws MessagingException {
        
        // Prepare the evaluation context
        final Context ctx = new Context(locale);
        ctx.setVariable("name", recipientName);
        ctx.setVariable("subscriptionDate", new Date());
        ctx.setVariable("hobbies", Arrays.asList("Cinema", "Sports", "Music"));
        ctx.setVariable("imageResourceName", imageResourceName); // so that we can reference it from HTML
        
        // Prepare message using a Spring helper
        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message = 
                new MimeMessageHelper(mimeMessage, true /* multipart */, "UTF-8");
        message.setSubject("Example HTML email with inline image");
        message.setFrom("thymeleaf@example.com");
        message.setTo(recipientEmail);

        // Create the HTML body using Thymeleaf
        final String htmlContent = this.templateEngine.process("email-inlineimage.html", ctx);
        message.setText(htmlContent, true /* isHtml */);
        
        // Add the inline image, referenced from the HTML code as "cid:${imageResourceName}"
        final InputStreamSource imageSource = new ByteArrayResource(imageBytes);
        message.addInline(imageResourceName, imageSource, imageContentType);
        
        // Send mail
        this.mailSender.send(mimeMessage);
        
    }


}
