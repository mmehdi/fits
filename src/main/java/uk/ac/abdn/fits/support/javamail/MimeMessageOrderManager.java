package uk.ac.abdn.fits.support.javamail;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;


public class MimeMessageOrderManager implements OrderManager {

    private JavaMailSender mailSender;

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void placeOrder(final Order order) {

        // Do the business calculations...

        // Call the collaborators to persist the order...

        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            public void prepare(MimeMessage mimeMessage) throws Exception {

                mimeMessage.setRecipient(Message.RecipientType.TO,
                        new InternetAddress(order.getCustomer().getEmailAddress()));
                mimeMessage.setFrom(new InternetAddress("mail@mycompany.com"));
                mimeMessage.setText(
                        "Dear " + order.getCustomer().getFirstName() + " "
                            + order.getCustomer().getLastName()
                            + ", thank you for placing order. Your order number is "
                            + order.getOrderNumber());
            }
        };

        try {
            this.mailSender.send(preparator);
        }
        catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }
    }

    public static void main(String[] args){
    	ApplicationContext context = 
    			new ClassPathXmlApplicationContext("file:D:\\workspaces\\github\\dot.r\\workspace\\fits\\ke\\src\\main\\webapp\\WEB-INF\\spring\\appServlet\\javamail.xml");
    	MimeMessageOrderManager simpleOrderManager =(MimeMessageOrderManager)context.getBean("mimeMessageOrderManager");
    	Order order = new Order();
    	Customer customer = new Customer();
    	customer.setFirstName("Cheng");
    	customer.setLastName("Zeng");
    	customer.setEmailAddress("c.zeng@abdn.ac.uk");
    	order.setCustomer(customer);
    	order.setOrderNumber("on00001");
    	simpleOrderManager.placeOrder(order);
    }
}
