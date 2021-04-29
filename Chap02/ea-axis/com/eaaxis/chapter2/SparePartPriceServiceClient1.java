package com.eaaxis.chapter2;

import java.net.URL;
import org.apache.axis.client.Service;
import org.apache.axis.client.Call;
import org.apache.axis.encoding.XMLType;

import javax.xml.rpc.namespace.QName;

public class SparePartPriceServiceClient1 {

    /** Creates new HelloWorldClient */
    public SparePartPriceServiceClient1() {
    }

    public static void main (String args[]) {
        try {

              // EndPoint URL for the SparePartPrice  Web Service
              String endpointURL = "http://localhost:8080/ea-axis/services";

              // Method Name to invoke for the SparePartPrice Web Service
              String methodName  = "getPrice";

              // Create the Service call
              Service service = new Service();
              Call call = (Call) service.createCall();
              call.setTargetEndpointAddress(new java.net.URL(endpointURL));
              call.setOperationName(new QName("SparePartPrice",methodName));

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
