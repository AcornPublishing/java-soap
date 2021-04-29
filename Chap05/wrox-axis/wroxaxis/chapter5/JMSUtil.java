package wroxaxis.chapter5;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.util.Properties;

public class JMSUtil {


   public static final String providerURL = "t3://localhost:7001";

   //Defines the JNDI context factory.
   public static final String JNDI_FACTORY="weblogic.jndi.WLInitialContextFactory";

   //Defines the JMS context factory.
   public static final String JMS_FACTORY="AxisConnectionFactory";

   //Defines the queue.
   public static final String QUEUE="WroxAxisQueue";
   public static final String QUEUE1="WroxAxisReplyQueue";


   public static final InitialContext getInitialContext() throws javax.naming.NamingException {

   	Properties props = new Properties();
   	props.put(javax.naming.Context.PROVIDER_URL,providerURL);
   	props.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY, JNDI_FACTORY);
   	InitialContext ictx= new javax.naming.InitialContext(props);
  return ictx;
  }
}