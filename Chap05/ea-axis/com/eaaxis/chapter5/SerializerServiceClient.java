package com.eaaxis.chapter5;

/*
 * SerializerServiceClient.java
 *
 * Our client program to the SparePartDetailsSer Web service
 */

import java.net.URL;
import org.apache.axis.client.Service;
import org.apache.axis.client.Call;
import org.apache.axis.encoding.XMLType;

import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.namespace.QName;


public class SerializerServiceClient {

    public SerializerServiceClient() {
    }

    public static void main (String args[]) {
        try {

			  // EndPoint URL for the SparePartPrice  Web Service
              String endpointURL = "http://localhost:8080/ea-axis/services/SparePartDetailsSer";

              // Method Name to invoke for the SparePartPrice Web Service
              String methodName  = "addSparePart";

              // Create the Service call
              Service service = new Service();
              Call call = (Call) service.createCall();
              call.setTargetEndpointAddress(new java.net.URL(endpointURL));
              call.setOperationName(new QName("SparePartDetailsSer",methodName));

              SparePartBean spBean = new SparePartBean();
              spBean.setSku("SKU-114");
              spBean.setPrice(50.00f);
              spBean.setDescription("Air Filter Model: 12345");

              QName qname = new QName("SparePartDetailsSer", "SparePartBean");

              Class cls = com.eaaxis.chapter5.SparePartBean.class;

   			  // register the SparePartBean class

			  call.registerTypeMapping(cls, qname, EaAxisSerFactory.class, EaAxisDeserFactory.class);

			  call.addParameter( "SparePart", qname, ParameterMode.PARAM_MODE_IN );
              call.setReturnType( XMLType.XSD_STRING );

              //Setup the Parameters i.e. the Part SKU to be passed as input parameter to the
              //SparePartPrice Web Service
              Object[] params = new Object[] { spBean };

              //Invoke the SparePartPrice Web Service
              String result = (String) call.invoke(params);

              //Print out the result
              System.out.println("The response: " + result);
        }
        catch (Exception e) {
            System.err.println(e.toString());
        }
    }
}
