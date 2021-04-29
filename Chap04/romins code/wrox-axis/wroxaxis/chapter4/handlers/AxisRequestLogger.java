package wroxaxis.chapter4.handlers;

import org.apache.axis.AxisFault;
import org.apache.axis.Handler;
import org.apache.axis.MessageContext;
import org.apache.axis.handlers.BasicHandler;

import org.apache.axis.SOAPPart;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Date;

public class AxisRequestLogger extends BasicHandler {

    public AxisRequestLogger() {
    }

    public void invoke(org.apache.axis.MessageContext messageContext) throws org.apache.axis.AxisFault {
        try {
            //Handler handler = messageContext.getServiceHandler();
            String logfilename = (String) getOption("logfilename");
            if ((logfilename == null) || (logfilename.equals(""))) {
                throw new AxisFault("Server.NoLogFileConfigured",
                                    "The logfilename parameter option for AxisRequestLogger was not set",
                                    null,null);
			}

            FileOutputStream os = new FileOutputStream(logfilename, true);
            PrintWriter writer = new PrintWriter(os);

            //Let us collect some data from the Request
            String targetServiceName = messageContext.getTargetService();
            String transportName = messageContext.getTransportName();
            boolean getPastPivot = messageContext.getPastPivot();

            StringBuffer logStr = new StringBuffer();
            logStr.append("###################################################" + "\r\n");
            logStr.append("###########      Request Details    ###############" + "\r\n");
            logStr.append("###################################################" + "\r\n");
            logStr.append("Request Intercepted at : " + new Date().toString() + "\r\n");
            logStr.append("Target Service Name : " + targetServiceName + "\r\n");
            logStr.append("Transport Name : " + transportName + "\r\n");
            logStr.append("getPivotPoint property : " + getPastPivot + "\r\n");

            writer.println(logStr);
            writer.close();
        }
        catch (Exception e) {
            throw AxisFault.makeFault(e);
        }
    }
}
