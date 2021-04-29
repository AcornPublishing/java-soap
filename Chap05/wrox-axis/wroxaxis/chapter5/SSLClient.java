package wroxaxis.chapter5;

import java.net.URL;
import org.apache.axis.client.Service;
import org.apache.axis.client.Call;
import org.apache.axis.encoding.XMLType;

import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.namespace.QName;

public class SSLClient {

   public static void main(String args[]) {

    try {


		// Set the protocol for handling SSL based connecting
		System.setProperty("java.protocol.handler.pkgs","com.sun.net.ssl.internal.www.protocol");

		// Add the provider implementation class for handling SSL based connecting
		java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

		// Set the truststore for authentication
		System.setProperty("javax.net.ssl.trustStore","d:/wrox-axis/wroxaxis/chapter5/clienttruststore");
		System.setProperty("javax.net.ssl.keyStore","d:/wrox-axis/wroxaxis/chapter5/clientkeystore");
		System.setProperty("javax.net.ssl.keyStorePassword","changeit");

		// For HTTP Basic Authentication
		// EndPoint URL for the SparePartPrice  Web Service
		String endpointURL = "https://localhost:8443/axis/services/SparePartPrice";

		// Method Name to invoke for the SparePartPrice Web Service
		String methodName  = "getPrice";

		// Create the Service call
		Service service = new Service();
		Call call = (Call) service.createCall();
		call.setTargetEndpointAddress(new java.net.URL(endpointURL));
		call.setOperationName(new QName("SparePartPrice",methodName));
		call.addParameter("sku",XMLType.XSD_STRING,ParameterMode.PARAM_MODE_IN);
		call.setReturnType(XMLType.XSD_FLOAT);

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
