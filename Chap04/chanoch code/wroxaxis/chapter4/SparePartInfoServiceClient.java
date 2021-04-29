// SparePartInfoServiceClient.java
package wroxaxis.chapter4;

import java.net.URL;

import org.apache.axis.client.Service;
import org.apache.axis.client.Call;
import org.apache.axis.encoding.XMLType;

import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.namespace.QName;

public class SparePartInfoServiceClient {

  public SparePartInfoServiceClient() {}

  public static void main (String args[]) {
    try {

      // EndPoint URL for the SparePartInfo web service
      String endpointURL = 
        "http://localhost:8080/axis/services/SparePartInfo";
      // Method Name to invoke for the SparePartInfo web service
      String methodName  = "getPartInfo";
      Service service = new Service();
      Call call = (Call) service.createCall();
      call.setTargetEndpointAddress(new 
        java.net.URL(endpointURL));
      call.setOperationName(new 
        QName("SparePartInfo",methodName));
      call.addParameter("sku",XMLType.XSD_STRING,
        ParameterMode.PARAM_MODE_IN);
      call.setReturnType(XMLType.XSD_FLOAT);

      //Pass the Part SKU as input parameter to the web service
      Object[] params = new Object[] {"SKU-123"};

      //Invoke the SparePartInfo web service
      String info = (String) call.invoke(params);

      System.out.println("Spare Part Information : " + info);
    }
    catch (Exception e) {
      System.err.println(e.toString());
    }
  }
}
