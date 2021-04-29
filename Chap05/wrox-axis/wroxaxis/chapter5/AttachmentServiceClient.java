package wroxaxis.chapter5;

/*
 * AttachmentServiceClient.java
 *
 * Our client program to the Attachment Web service
 */

import java.net.URL;
import org.apache.axis.client.Service;
import org.apache.axis.client.Call;
import org.apache.axis.encoding.XMLType;

import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.namespace.QName;
import org.apache.axis.encoding.ser.JAFDataHandlerSerializerFactory;
import org.apache.axis.encoding.ser.JAFDataHandlerDeserializerFactory;
import javax.activation.FileDataSource;
import javax.activation.DataHandler;

public class AttachmentServiceClient {

    public AttachmentServiceClient() {
    }

    public static void main (String args[]) {
        try {
		     String filename = "d:/wrox-axis/sparkplug.jpg";
			 //Create the data for the attached file.
             DataHandler dhSource = new DataHandler( new FileDataSource( filename ));

			  // EndPoint URL for the SparePartPrice  Web Service
              String endpointURL = "http://localhost:8080/axis/services/AttachmentService";

              // Method Name to invoke for the SparePartPrice Web Service
              String methodName  = "addImage";

              // Create the Service call
              Service service = new Service();
              Call call = (Call) service.createCall();
              call.setTargetEndpointAddress(new java.net.URL(endpointURL));
              call.setOperationName(new QName("AttachmentService",methodName));


              QName qname = new QName("AttachmentService", "DataHandler");

              call.addParameter("sku",XMLType.XSD_STRING,ParameterMode.PARAM_MODE_IN);


         	  // register the SparePartBean class

			  call.registerTypeMapping(dhSource.getClass(),
			  						   qname,
			  						   JAFDataHandlerSerializerFactory.class,
			  						   JAFDataHandlerDeserializerFactory.class);

			  call.addParameter( "image", qname, ParameterMode.PARAM_MODE_IN );

              call.setReturnType( XMLType.XSD_STRING );

              //Setup the Parameters i.e. the Part SKU to be passed as input parameter to the
              //SparePartPrice Web Service
              Object[] params = new Object[] {"SKU-111", dhSource };

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
