package com.eaaxis.chapter5;

import java.io.*;
import org.apache.axis.AxisEngine;
import org.apache.axis.AxisFault;
import org.apache.axis.Message;
import org.apache.axis.MessageContext;
import org.apache.axis.configuration.FileProvider;
import org.apache.axis.server.AxisServer;
import org.apache.axis.message.SOAPEnvelope;
import org.apache.axis.message.SOAPFaultElement;
import org.apache.log4j.Category;
import javax.xml.rpc.namespace.QName;
import org.apache.axis.Constants;
import java.util.Properties;
import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.axis.message.SOAPEnvelope;

import org.w3c.dom.Element;
import org.apache.axis.utils.XMLUtils;

public class JMSListener implements MessageListener {

   //Define JMS Queue family classes
   private QueueConnectionFactory queueConnFactory;
   private QueueConnection queueConn;
   private QueueSession session;
   private QueueSender sender;
   private QueueReceiver receiver;
   private Queue queue;
   private boolean quit = false;

   //Define TextMessage for wrapping the SOAP (request) Envelope
   private TextMessage jmsMsg;

   private AxisEngine engine=null;
   private String factoryName;

   private String transportName;

   javax.jms.Message msg;
    static Category category =
            Category.getInstance(JMSListener.class.getName());

    public JMSListener() {}


    public AxisEngine getAxisEngine() throws Exception {

        if (engine != null)
            return engine;

        String prefix = "d:\\jakarta-tomcat\\webapps\\ea-axis\\WEB-INF\\";
        String config = prefix+"server-config.wsdd";
        System.out.println("filename :"+config);
        FileProvider provider =  new FileProvider(config);
        return new AxisServer(provider);
    }

    private void init() throws AxisFault {

		try {
				InitialContext ictx = JMSUtil.getInitialContext();
				queueConnFactory = (QueueConnectionFactory) ictx.lookup(JMSUtil.JMS_FACTORY);

				//Look up the Queue
				queue = (Queue) ictx.lookup(JMSUtil.QUEUE);
				queueConn = queueConnFactory.createQueueConnection();

				// Create a session (non-transactional) that automatically acknowledges message receipt.
				session = queueConn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

				receiver = session.createReceiver(queue);
				queueConn.start();

				System.out.println("Ready to receive messages!!\n");

				/* Let this JMSListener be notified everytime a message is received
				by its associated queue. */
				receiver.setMessageListener(this);

		} catch (Exception e) {
			//System.out.println(e);
			throw AxisFault.makeFault(e);
		  }
    }

	// This method is invoked when ever a new message arrives in the QUEUE.
	public void onMessage(javax.jms.Message msg) {

		System.out.println("---In onMessage()---");
        quit = true;
		MessageContext msgContext = null;
		org.apache.axis.Message responseMsg = null;

		try {
				String msgTxt = null;

				TextMessage jmsMsg = (TextMessage) msg;
				System.out.println(jmsMsg.getText());
				Queue replyQueue = (Queue)msg.getJMSReplyTo();
		 		sender = session.createSender(replyQueue);

		 		// Create an instance of AxisServer
		 		AxisEngine engine = getAxisEngine();

				// Creae a MessageContext and associate with the engine
				msgContext = new MessageContext(engine);

		 		// Wrap the incoming TextMessage into Axis Message
		 		org.apache.axis.Message soapMessage = new org.apache.axis.Message(jmsMsg.getText());

		 		System.out.println("SOAP Request:");
		 		System.out.println(jmsMsg.getText()+"\n");

		 		// Set the request message in MessageContext
		 		msgContext.setRequestMessage(soapMessage);

		 		System.out.println("---Invoking AxisServer.invoke()---");

		 		// Invoke AxisEngine (AxisServer)
		 		engine.invoke(msgContext);

		 		System.out.println("---AxisServer.invoke() is completed---");
		 		System.out.println("---Getting response message from MessageContext---");

		 		// Get the response message from the MessageContext
		 		responseMsg = msgContext.getResponseMessage();

		 		// The next three lines are to get a String representation of response message
		 		SOAPEnvelope envelope = responseMsg.getSOAPEnvelope();
		 		Element envElement = envelope.getAsDOM();
				String strSOAPBody = XMLUtils.ElementToString(envElement);
				System.out.println("SOAP Response:");
				System.out.println(strSOAPBody);

				// Create an instance of TextMessage to wrap the SOAP (response) Message
				TextMessage jmsResponseMsg = session.createTextMessage();
				jmsResponseMsg.setText(strSOAPBody);

		 		//Put the response into the queue: 'replyQueue'
		 		System.out.println("\n---Sending SOAP Response using the Queue: EaAxisReplyQueue ---");
		 		sender.send(jmsResponseMsg);
		 		System.out.println("---Sent SOAP Response---\n");
		 		System.out.println("---Waiting for new SOAP Requests---");

		}catch(Exception e) {

			e.printStackTrace();
			if ( !(e instanceof AxisFault) )
				e = AxisFault.makeFault(e);
				responseMsg = msgContext.getResponseMessage();
			if (responseMsg == null) {
				responseMsg = new org.apache.axis.Message((AxisFault)e);
				msgContext.setResponseMessage(responseMsg);
			} else {
				try {
					SOAPEnvelope env = responseMsg.getSOAPEnvelope();
					env.clearBody();
					env.addBodyElement(new SOAPFaultElement((AxisFault)e));
				} catch (AxisFault af) {
					af.printStackTrace();
				}
            }
		  }

   }


   public static void main(String args[]) throws Exception {
      		JMSListener listener = new JMSListener();
     		listener.init();

     		// Wait until a "quit" message has been received.
			synchronized(listener) {
			  while (! listener.quit) {
			     try {
			          listener.wait();
			        } catch (InterruptedException ie) {}
			  }
            }
   }
}