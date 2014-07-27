package junit.test;

import org.junit.Test;  
import org.springframework.context.ApplicationContext;  
import org.springframework.context.support.ClassPathXmlApplicationContext;  
  
import com.bird.service.PersonServer;  
  
public class SpringAOPTest {  
      
    @Test  
    public void inteceptorTest(){  

    	System.out.println("begin-->");
    	ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/application-config.xml");  

    	
        PersonServer bean = (PersonServer)ctx.getBean("personServiceBean");  
        bean.save("jack");  
        System.out.println("end-->");
    }  
      
  
}  
