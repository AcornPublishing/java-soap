package wroxaxis.chapter5;

/*
 * SparePartServiceClient1.java
 *
 */

import java.net.URL;
import org.apache.axis.client.Service;
import org.apache.axis.client.Call;
import org.apache.axis.encoding.XMLType;

import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.namespace.QName;
import org.apache.axis.encoding.ser.BeanSerializerFactory;
import org.apache.axis.encoding.ser.BeanDeserializerFactory;

public class SparePartServiceClient1 {

    public SparePartServiceClient1() {
    }

    public static void main (String args[]) {
        try {

			  if(args.length == 0) {
				  System.out.println("Usage: java wroxaxis.chapter5.SparePartServiceClient1 <sku-number>");
				  System.exit(1);
			  }

              // EndPoint URL for the SparePartPrice  Web Service
              String endpointURL = "http://localhost:8080/axis/services/SparePartDetails";

              // Method Name to invoke for the SparePartPrice Web Service
              String methodName  = "getSparePart";

              // Create the Service call
              Service service = new Service();
              Call call = (Call) service.createCall();
              call.setTargetEndpointAddress(new java.net.URL(endpointURL));
              call.setOperationName(new QName("SparePartDetails",methodName));
              call.addParameter("sku",XMLType.XSD_STRING,ParameterMode.PARAM_MODE_IN);

              QName qname = new QName("SparePartDetails", "SparePartBean");

              Class cls = wroxaxis.chapter5.SparePartBean.class;

   			  // register the SparePartBean class

			  call.registerTypeMapping(cls, qname, BeanSerializerFactory.class, BeanDeserializerFactory.class);

			  call.setReturnType(qname);

              //Setup the Parameters i.e. the Part SKU to be passed as input parameter to the
              //SparePartPrice Web Service
              Object[] params = new Object[] { args[0] };

              //Invoke the SparePartPrice Web Service
              SparePartBean spBean = (SparePartBean) call.invoke(params);

              //Print out the result
              System.out.println("The price is $" + spBean.getPrice());
			  System.out.println("The descrption is " + spBean.getDescription());
        }
        catch (Exception e) {
            System.err.println(e.toString());
        }
    }
}
