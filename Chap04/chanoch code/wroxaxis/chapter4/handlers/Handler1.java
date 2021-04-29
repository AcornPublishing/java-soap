// Handler1.java
package wroxaxis.chapter4.handlers;

import org.apache.axis.AxisFault;
import org.apache.axis.Handler;
import org.apache.axis.MessageContext;
import org.apache.axis.handlers.BasicHandler;

import org.apache.axis.SOAPPart;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Date;

public class Handler1 extends BasicHandler {

  public Handler1() {
  }

  public void invoke(MessageContext messageContext) 
    throws org.apache.axis.AxisFault {
    try {
      String logfilename = (String) getOption("logfilename");
      if ((logfilename == null) || (logfilename.equals(""))) {
        throw new AxisFault("Server.NoLogFileConfigured",
        "The logfilename parameter option for Handler1 was" +
        " not set",null,null);
      }
      StringBuffer logStr = new StringBuffer();
      logStr.append("----Handler 1 called----" + "\r\n");
      logMessage(logfilename,logStr.toString());
    }
    catch (Exception e) {
      throw AxisFault.makeFault(e);
    }
  }
  
  public void onFault(MessageContext messageContext) {
    try {
      String logfilename = (String) getOption("logfilename");
      if ((logfilename == null) || (logfilename.equals(""))) {
        throw new AxisFault("Server.NoLogFileConfigured",
          "The logfilename parameter option for Handler1 was" +
          "not set",null,null);
      }

      StringBuffer logStr = new StringBuffer();
      logStr.append("Fault in Handler 1 called" + "\r\n");
      logMessage(logfilename,logStr.toString());
    }
    catch (Exception e) {}
  }

  private void logMessage(String logfileName,String logStr) 
          throws Exception {
    try {
      FileOutputStream os = 
        new FileOutputStream(logfileName, true);
      PrintWriter writer = new PrintWriter(os);
      writer.println(logStr);
      writer.close();
    }
    catch (Exception e) {
      throw new Exception(e.getMessage());
    }
  }
}
