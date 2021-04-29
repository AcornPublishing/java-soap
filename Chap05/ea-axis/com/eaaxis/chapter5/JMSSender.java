package com.eaaxis.chapter5;

import org.apache.axis.AxisFault;
import org.apache.axis.Message;
import org.apache.axis.MessageContext;
import org.apache.axis.encoding.Base64;
import org.apache.axis.handlers.BasicHandler;
import org.apache.axis.message.MessageElement;
import org.apache.axis.utils.JavaUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.axis.message.SOAPEnvelope;
import org.w3c.dom.Element;
import org.apache.axis.utils.XMLUtils;

import javax.jms.*;
import javax.naming.InitialContext;

public class JMSSender extends BasicHandler {

   static Log log = LogFactory.getLog(JMSSender.class.getName());

   //Define JMS Queue family classes
   private QueueConnectionFactory queueConnFactory;
   private QueueConnection queueConn;
   private QueueSession session;
   private QueueSender queueSender;
   private QueueReceiver receiver;
   private Queue queue;

   //Define TextMessage for wrapping the SOAP (request) Envelope
   private TextMessage jmsMsg;


   public JMSSender(){
	   System.out.println("---In constructor JMSSender()---\n");
   	   try {

	   // Get the initial context to lookup the AdministetedObjects
	   InitialContext ictx = JMSUtil.getInitialContext();

	   // Initialize Queue related objects.
	   queueConnFactory = (QueueConnectionFactory) ictx.lookup(JMSUtil.JMS_FACTORY);
	   queueConn = queueConnFactory.createQueueConnection();
	   session = queueConn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
	   queue = (Queue) ictx.lookup(JMSUtil.QUEUE);
	   queueSender = session.createSender(queue);

	   // Create an object of TextMessage
	   jmsMsg = session.createTextMessage();

	   // Create a temporary queue for receiving the SOAP (response) Envelope
	   //TemporaryQueue replyTo = session.createTemporaryQueue();
	   Queue queue1 = (Queue) ictx.lookup(JMSUtil.QUEUE1);
	   jmsMsg.setJMSReplyTo(queue1);
	   receiver = session.createReceiver(queue1);

	   queueConn.start();
    }catch(Exception e)
     {
		 System.out.println(e);
		 System.exit(0);
	 }
   }

   // Axis Engine invokes this method
   public void invoke(MessageContext msgContext) throws AxisFault {

	try
	{
		if (log.isDebugEnabled()) {
            log.debug( JavaUtils.getMessage("enter00","JMSSender::invoke") );
		}

		// Get the request Message from MessageContext
		org.apache.axis.Message axisMsg = msgContext.getRequestMessage();

		// The following three lines are to get a string representation of SOAP Envelope
		SOAPEnvelope soapEnvelope = axisMsg.getSOAPEnvelope();
		Element envElement = soapEnvelope.getAsDOM();
		String strSOAPBody = XMLUtils.ElementToString(envElement);

		System.out.println("---SOAP Request:\n");
		System.out.println(strSOAPBody+"\n");

		// Set SOAP Envelope as the text message
		jmsMsg.setText(strSOAPBody);

		System.out.println("---Sending SOAP Request---");
		queueSender.send(jmsMsg);
		System.out.println("---Sent SOAP Request---\n");

		System.out.println("---Receiving SOAP Response---");
		TextMessage replyMsg = (TextMessage) receiver.receive();
		System.out.println("---Received SOAP Response---\n");
		System.out.println("---SOAP Response:");
		System.out.println("\n");
		System.out.println(""+replyMsg.getText());
		System.out.println("\n");
		org.apache.axis.Message responseMsg = new org.apache.axis.Message(replyMsg.getText());
		msgContext.setResponseMessage(responseMsg);
		System.out.println("---Returning control back to JMSClient---\n");
	 } catch(Exception e){
		 throw AxisFault.makeFault(e);
	   }
  }
     public void cleanUp() {

	 try{
		queueSender.close();
		receiver.close();
		session.close();
		queueConn.close();
	}catch(Exception e){
		System.out.println(e);
		System.exit(0);
	 }
     }
}