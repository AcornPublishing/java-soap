// SOAPResponseLogger.java

package wroxaxis.chapter4.handlers;

import org.apache.axis.AxisFault;
import org.apache.axis.MessageContext;
import org.apache.axis.Handler;
import org.apache.axis.Message;
import org.apache.axis.SOAPPart;
import org.apache.axis.message.SOAPEnvelope;
import org.apache.axis.handlers.BasicHandler;
import org.apache.axis.utils.XMLUtils;

import org.w3c.dom.Element;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Date;

public class SOAPResponseLogger extends BasicHandler {

  public void invoke(MessageContext messageContext) 
    throws AxisFault {
    try {

      //Get the current Time
      long currentTimeMills = System.currentTimeMillis();

      //Retrieve the startTime from the messageContext "StartTime"
      //property that was set by the SOAPRequestLogger
      Long startTime = 
        (Long) messageContext.getProperty("StartTime");
      long startTimeMills = startTime.longValue();
            
      //Determine the duration of the call in seconds
      float duration = 
        ((float)(currentTimeMills - startTimeMills))/1000;
      String strDuration = "Time to make call : " + 
        String.valueOf(duration) + "seconds";

//Reterive the SOAPResponse from the messageContext / Message
      Message resMessage = messageContext.getResponseMessage();
      SOAPEnvelope env = resMessage.getSOAPEnvelope();
      Element envElement = env.getAsDOM();
      String strSOAPResponse = XMLUtils.ElementToString(
        envElement);

      //Log the SOAPResponse in the same SOAP.log file
      String logfilename = (String) getOption("logfilename");
      if ((logfilename == null) || (logfilename.equals(""))) {
        throw new AxisFault("Server.NoLogFileConfigured",
        "The logfilename parameter option for AccessLogHandler " +
        "was not set", null,null);
      }
      FileOutputStream os = new FileOutputStream(
        logfilename, true);
      PrintWriter writer  = new PrintWriter(os);
            
      StringBuffer logStr = new StringBuffer();
      logStr.append("==== SOAP Response  : " + 
        new Date().toString() + " : =====\r\n");
      logStr.append(strSOAPResponse);
      logStr.append("----------------------------------------");
      logStr.append(strDuration);

      writer.println(logStr);
      writer.close();
    }
    catch (Exception e) {throw AxisFault.makeFault(e);
    }
  }
}
