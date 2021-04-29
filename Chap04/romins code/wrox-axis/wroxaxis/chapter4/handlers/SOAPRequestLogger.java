package wroxaxis.chapter4.handlers;

import org.apache.axis.AxisFault;
import org.apache.axis.MessageContext;
import org.apache.axis.Handler;
import org.apache.axis.Message;
import org.apache.axis.SOAPPart;
import org.apache.axis.message.SOAPEnvelope;
import org.apache.axis.handlers.BasicHandler;

import org.w3c.dom.Element;
import org.apache.axis.utils.XMLUtils;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Date;

public class SOAPRequestLogger extends BasicHandler {

    public SOAPRequestLogger() {}

    public void invoke(org.apache.axis.MessageContext messageContext) throws org.apache.axis.AxisFault {
        try {
            Message reqMessage = messageContext.getRequestMessage();
            SOAPEnvelope env = reqMessage.getSOAPEnvelope();
            Element envElement = env.getAsDOM();
            String strSOAPBody = XMLUtils.ElementToString(envElement);

            String logfilename = (String) getOption("logfilename");
            /* Mention in the text - several other calls also */

            if ((logfilename == null) || (logfilename.equals(""))) {
                throw new AxisFault("Server.NoLogFileConfigured",
                                    "The logfilename parameter option for SOAPRequestLogger was not set",
                                    null,null);
     		}

            FileOutputStream os = new FileOutputStream(logfilename, true);
            PrintWriter writer  = new PrintWriter(os);
            StringBuffer logStr = new StringBuffer();
            logStr.append("==== SOAP Request  : " + new Date().toString() + " : =====\r\n");
            logStr.append(strSOAPBody);
            writer.println(logStr);
            writer.close();

            //Track the time the call was made
            Long startTime = new Long(System.currentTimeMillis());
            //Store the startTime in the messageContext
            messageContext.setProperty("StartTime",(Long) startTime);
        }
        catch (Exception e) {
            throw AxisFault.makeFault(e);
        }
    }
}
