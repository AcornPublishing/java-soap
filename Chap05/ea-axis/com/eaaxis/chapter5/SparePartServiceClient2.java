package com.eaaxis.chapter5;

/*
 * SparePartServiceClient1.java
 *
 * Our client program to the SparePart Web service
 */

import java.net.URL;
import org.apache.axis.client.Service;
import org.apache.axis.client.Call;
import org.apache.axis.encoding.XMLType;

import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.namespace.QName;
import org.apache.axis.encoding.ser.BeanSerializerFactory;
import org.apache.axis.encoding.ser.BeanDeserializerFactory;

public class SparePartServiceClient2 {

    public SparePartServiceClient2() {
    }

    public static void main (String args[]) {
        try {

			  // EndPoint URL for the SparePartPrice  Web Service
              String endpointURL = "http://localhost:8080/ea-axis/services/SparePartDetails";

              // Method Name to invoke for the SparePartPrice Web Service
              String methodName  = "addSparePart";

              // Create the Service call
              Service service = new Service();
              Call call = (Call) service.createCall();
              call.setTargetEndpointAddress(new java.net.URL(endpointURL));
              call.setOperationName(new QName("SparePartDetails",methodName));

              SparePartBean spBean = new SparePartBean();
              spBean.setSku("SKU-333");
              spBean.setPrice(50.00f);
              spBean.setDescription("Air Filter Model: 12345");

              QName qname = new QName("SparePartDetails", "SparePartBean");

              Class cls = com.eaaxis.chapter5.SparePartBean.class;

   			  // register the SparePartBean class

			  call.registerTypeMapping(cls, qname, BeanSerializerFactory.class, BeanDeserializerFactory.class);

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
