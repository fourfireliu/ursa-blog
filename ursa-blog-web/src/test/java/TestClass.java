import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;


public class TestClass extends  AbstractDispatcherServletInitializer  {

//	@Override
//	public void onStartup(ServletContext servletContext)
//			throws ServletException {
//		XmlWebApplicationContext context = new XmlWebApplicationContext();
//		ServletRegistration.Dynamic dynamic = servletContext.addServlet("test", new DispatcherServlet(context))
//		
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	protected WebApplicationContext createServletApplicationContext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected WebApplicationContext createRootApplicationContext() {
		// TODO Auto-generated method stub
		return null;
	}

}
