package com.eaaxis.chapter6;

import java.net.URL;
import org.apache.axis.client.Service;
import org.apache.axis.client.Call;
import org.apache.axis.encoding.XMLType;

import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.namespace.QName;

public class JBOSSServiceClient {

    public JBOSSServiceClient() {
    }

    public static void main (String args[]) {
        try {

              String endpointURL = "http://localhost:8080/ea-axis/services/SparePartPriceJBOSSEJB";

              // Method Name in the EJB
              String methodName  = "getSparePartPrice";

              // Create the Service call
              Service service = new Service();
              Call call = (Call) service.createCall();
              call.setTargetEndpointAddress(new java.net.URL(endpointURL));
              call.setOperationName(new QName("SparePartPriceJBOSSEJB",methodName));
              call.addParameter("name",XMLType.XSD_STRING,ParameterMode.PARAM_MODE_IN);

              Object[] params = new Object[] {"SKU-123"};

              Float price = (Float) call.invoke(params);

              //Print out the result
              System.out.println("The price is : " + price.floatValue());
        }
        catch (Exception e) {
            System.err.println(e.toString());
        }
    }
}
