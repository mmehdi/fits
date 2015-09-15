package uk.ac.abdn.fits.support.thymeleaf.springmail.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.TemplateEngine;

import uk.ac.abdn.fits.hibernate.model.Operator;
import uk.ac.abdn.fits.hibernate.operator.OperatorDataManager;

public class ReminderEmailService extends TimerTask{

	@Autowired 
	private EmailService emailService;
	
	@Override
	public void run() {
		System.out.println("run reminder email service");
		OperatorDataManager operatorDataManager;
			ApplicationContext ctx = new ClassPathXmlApplicationContext("../spring/appServlet/hibernate.xml");
			operatorDataManager = 
			        (OperatorDataManager) ctx.getBean("operatorDataManagerImpl");
			
		List<Operator> operators = operatorDataManager.getOperators();
		int days = 0;
		for(Operator operator: operators){
			days = checkLastDate(operator);
			if(days>180){
				sendReminderEmail(operator);
			}else if(days>=190){ // plus 10 send email
				sendReminderEmail(operator);
			}else if(days>=195){ // plus 5 send email
				sendReminderEmail(operator);
			}else if(days>=197){ // plus 2 send email
				sendReminderEmail(operator);
			}else if(days>=198){ // plus 1 send email and send email every day
				sendReminderEmail(operator);
			}
		}
	}
	
	public int checkLastDate(Operator operator){
		int days = 0;
		if(operator.getLast_update()!=null){
			Calendar last_update = Calendar.getInstance();
			last_update.setTime(operator.getLast_update());
			
			Calendar today = Calendar.getInstance();
			long diff = today.getTimeInMillis() - last_update.getTimeInMillis();
			days = (int) (diff/(3600*1000*24));
			return 181;
		}
		return days;
	}
	
	public void sendReminderEmail(Operator operator){
		
		Date operator_last_update = operator.getLast_update();
		int operatorId = operator.getOperator_id();
		String recipientName = "Cheng Zeng";
		String recipientEmail = "c.zeng@abdn.ac.uk";
//		try {
//			emailService.sendRemindMail(operator.getName(), recipientEmail, null, operator_last_update, operatorId);
//		} catch (MessagingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
	// out of date 6 months
	// out of date 6 months + 1day
	// out of date 6 months + 2day
	// out of date 6 months + 3day
	// out of date 6 months + 4day
	// out of date 6 months + 5day
}
