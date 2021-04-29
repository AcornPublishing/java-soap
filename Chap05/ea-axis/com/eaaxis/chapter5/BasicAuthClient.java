package com.eaaxis.chapter5;

import java.net.URL;
import org.apache.axis.client.Service;
import org.apache.axis.client.Call;
import org.apache.axis.encoding.XMLType;

import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.namespace.QName;

public class BasicAuthClient {

   public static void main(String args[]) {

    try {

		if(args == null)
		{
			System.out.println("Usage: java com.eaaxis.chapter5.BasicAuthClient <username> <password>");
			System.exit(0);
		}

        // For HTTP Basic Authentication
        String username = args[0];
        String password = args[1];


       	// EndPoint URL for the SparePartPrice  Web Service
		String endpointURL = "http://localhost:8080/ea-axis/services/protected/SparePartPrice";

		// Method Name to invoke for the SparePartPrice Web Service
		String methodName  = "getPrice";

		// Create the Service call
		Service service = new Service();
		Call call = (Call) service.createCall();
		call.setTargetEndpointAddress(new java.net.URL(endpointURL));
		call.setOperationName(new QName("SparePartPrice",methodName));
		call.addParameter("sku",XMLType.XSD_STRING,ParameterMode.PARAM_MODE_IN);
		call.setReturnType(XMLType.XSD_FLOAT);
		call.setUsername(username);
		call.setPassword(password);

		//Setup the Parameters i.e. the Part SKU to be passed as input parameter to the
		//SparePartPrice Web Service
		Object[] params = new Object[] {"SKU-123"};

		//Invoke the SparePartPrice Web Service
		Float price = (Float) call.invoke(params);

		//Print out the result
		System.out.println("The price is $" + price.floatValue());
     }
           catch (Exception e) {
               System.err.println(e.toString());
           }
    }
}
