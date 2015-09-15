package uk.ac.abdn.fits.manage;

/**
 * @author Cheng Zeng, University of Aberdeen
 * 
 */

import java.io.IOException;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import uk.ac.abdn.fits.hibernate.model.Operator;
import uk.ac.abdn.fits.hibernate.operator.OperatorDataManager;


public class OperatorManagement {

	
	private OperatorDataManager operatorManager = null;
	
	private static final OperatorManagement  instance= null;
	
	private OperatorManagement(){
		initOperatorManager();
	}
	
	public static OperatorManagement getInstance(){
		
		if(instance == null){
			
			return new OperatorManagement();
		}
		
		return instance;
	}
	
	private void initOperatorManager(){
		
		if(operatorManager == null){
			ApplicationContext ctx = 
			        new ClassPathXmlApplicationContext("hibernate.xml");
			operatorManager = (OperatorDataManager) ctx.getBean("operatorManagerImpl");
		}
	}
	
	public static void main(String[] args) throws IOException{
		
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("hibernate.xml");
		OperatorDataManager operatorManager = 
		        (OperatorDataManager) ctx.getBean("operatorManagerImpl");
		      
		      Operator operator = new Operator();
		      operator.setName("BABS");
		      operator.setDescription("description");
		      
		      operatorManager.insertOperator(operator);
		      
		      System.out.println("operator inserted!");
		      
		      operator = operatorManager.getOperator("BABS");
		      
		      System.out.println("\nUser fetched by username!"
		        + "\noperator_id: " + operator.getOperator_id()
		        + "\nname: " + operator.getName()
		        + "\ndescription: " + operator.getDescription());
		      
		      operator = operatorManager.getOperatorById(operator.getOperator_id());
		      
		      List<Operator> operators = operatorManager.getOperators();
		      
		      System.out.println("operators: " + operators.get(0).getName());
		      
		      System.out.println("\nUser list fetched!"
		          + "\nOperator count: " + operators.size());
		
		
	}
	
	
	
}
