package curso.java.tienda.controller.base;

import java.net.URL;

import javax.servlet.http.HttpServlet;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Servlet implementation class BaseServlet
 */
public class BaseServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	protected final Logger logger = Logger.getLogger(this.getClass());
      
//    public BaseServlet() {
//
//		ClassLoader loader = Thread.currentThread().getContextClassLoader();
//		URL appResourceURL = loader.getResource("log4j.properties");
//		String dbConfigFileRoute = appResourceURL.getPath();
//		System.out.println(dbConfigFileRoute);
//		PropertyConfigurator.configure(dbConfigFileRoute);
//		//logger.debug("esto es una prueba");
//    }
}
