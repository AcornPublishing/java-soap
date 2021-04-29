package com.eaaxis.chapter5;

/*
 * JMSClient.java
 *
 * Our client program to the SparePartPrice Web service
 */

import java.net.URL;
import org.apache.axis.client.Service;
import org.apache.axis.client.Call;
import org.apache.axis.encoding.XMLType;

import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.namespace.QName;

public class JMSClient {

    public JMSClient() {
    }

    public static void main (String args[]) {
        try {

		      // EndPoint URL for the SparePartPrice  Web Service
              String endpointURL = "http://localhost:8080/ea-axis/services/SparePartPrice";

              // Method Name to invoke for the SparePartPrice Web Service
              String methodName  = "getPrice";

              // Create the Service call
              Service service = new Service();
              Call call = (Call) service.createCall();
              call.setTargetEndpointAddress(new java.net.URL(endpointURL));
              call.setOperationName(new QName("SparePartPrice",methodName));
              call.addParameter("sku",XMLType.XSD_STRING,ParameterMode.PARAM_MODE_IN);
              call.setTransport(new JMSTransport());

              call.setReturnType(XMLType.XSD_FLOAT);

			  //Setup the Parameters i.e. the Part SKU to be passed as input parameter to the
			  //SparePartPrice Web Service
			  Object[] params = new Object[] {"SKU-123"};

              System.out.println("---Start call.invoke()---\n");
			  //Invoke the SparePartPrice Web Service
			  Float price = (Float) call.invoke(params);
			  System.out.println("---End call.invoke()---");
			  //Print out the result
			  System.out.println("---Displaying the result---");
              System.out.println("The price is $" + price.floatValue());
        }
        catch (Exception e) {
            System.err.println(e.toString());
        }
    }
}
