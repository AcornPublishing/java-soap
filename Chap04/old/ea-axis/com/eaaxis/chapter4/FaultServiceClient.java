package com.eaaxis.chapter4;
import java.net.URL;
import org.apache.axis.client.Service;
import org.apache.axis.client.Call;
import org.apache.axis.encoding.XMLType;

import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.namespace.QName;

public class FaultServiceClient {

    /** Creates new HelloWorldClient */
    public FaultServiceClient() {
    }

    public static void main (String args[]) {
        try {

              // EndPoint URL for the SparePartInfo  Web Service
              String endpointURL = "http://localhost:7080/ea-axis/services/FaultService";

              // Method Name to invoke for the SparePartInfo Web Service
              String methodName  = "getPartInfo";

              // Create the Service call
              Service service = new Service();
              Call call = (Call) service.createCall();
              call.setTargetEndpointAddress(new java.net.URL(endpointURL));
              call.setOperationName(new QName("FaultService",methodName));
              call.addParameter("sku",XMLType.XSD_STRING,ParameterMode.PARAM_MODE_IN);
              call.setReturnType(XMLType.XSD_FLOAT);

              //Setup the Parameters i.e. the Part SKU to be passed as input parameter to the
              //SparePartInfo Web Service
              Object[] params = new Object[] {"SKU-123"};

              //Invoke the SparePartInfo Web Service
              String info = (String) call.invoke(params);

              //Print out the result
              System.out.println("Spare Part Information : " + info);
        }
        catch (Exception e) {
            System.err.println(e.toString());
        }
    }
}
