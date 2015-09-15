package uk.ac.abdn.fits.support.javamail;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class SimpleOrderManager implements OrderManager {

    private MailSender mailSender;
    private SimpleMailMessage templateMessage;

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setTemplateMessage(SimpleMailMessage templateMessage) {
        this.templateMessage = templateMessage;
    }
    
    public void placeOrder(Order order) {

        // Do the business calculations...

        // Call the collaborators to persist the order...

        // Create a thread safe "copy" of the template message and customize it
        SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
        msg.setTo(order.getCustomer().getEmailAddress());
        msg.setText(
            "Dear " + order.getCustomer().getFirstName()
                + order.getCustomer().getLastName()
                + ", thank you for placing order. Your order number is "
                + order.getOrderNumber());
        try{
            this.mailSender.send(msg);
        }
        catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }
    }

    
    public static void main(String[] args){
    	
    	/* basic method */
    
    	ApplicationContext context = 
    			new ClassPathXmlApplicationContext("file:D:\\workspaces\\github\\dot.r\\workspace\\fits\\ke\\src\\main\\webapp\\WEB-INF\\spring\\appServlet\\javamail.xml");
    	SimpleOrderManager simpleOrderManager =(SimpleOrderManager)context.getBean("orderManager");
//    	Order order = new Order();
//    	Customer customer = new Customer();
//    	customer.setFirstName("Cheng");
//    	customer.setLastName("Zeng");
//    	customer.setEmailAddress("c.zeng@abdn.ac.uk");
//    	order.setCustomer(customer);
//    	order.setOrderNumber("on00001");
//    	simpleOrderManager.placeOrder(order);
    
    	
//    	JavaMailSenderImpl sender = new JavaMailSenderImpl();
//    	sender.setHost("smtp.gmail.com");
//    	sender.setPort(587);
//    	sender.setUsername("zengc0213");
//    	sender.setPassword("abdn2011haha");
//    	
//    	
//    	MimeMessage message = sender.createMimeMessage();
//    	MimeMessageHelper helper = new MimeMessageHelper(message);
//    	try {
//			helper.setTo("c.zeng@abdn.ac.uk");
//			helper.setText("Thank you for ordering!");
//		} catch (MessagingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    	sender.send(message);
    	
    	
    	
    	
    	
    }
}
