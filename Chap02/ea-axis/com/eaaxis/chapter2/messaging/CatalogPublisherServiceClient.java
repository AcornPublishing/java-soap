package com.eaaxis.chapter2.messaging;

import org.w3c.dom.Element;
import org.apache.axis.client.Service;
import org.apache.axis.client.Call;
import org.apache.axis.message.SOAPBodyElement;
import org.apache.axis.utils.XMLUtils;


import java.io.FileInputStream;
import java.net.URL;
import java.util.Vector;


public class CatalogPublisherServiceClient {

    public static void main(String[] args) throws Exception {

        String endpointURL = "http://localhost:8080/ea-axis/services/CatalogPublisherService";

	Service service = new Service();
	Call call = (Call) service.createCall();

        call.setTargetEndpointAddress(new URL(endpointURL));
        SOAPBodyElement[] reqSOAPBodyElements = new SOAPBodyElement[1];

        reqSOAPBodyElements[0] = new SOAPBodyElement(XMLUtils.newDocument(new java.io.FileInputStream(new java.io.File("d:\\ea-axis\\com\\eaaxis\\chapter2\\messaging\\catalog.xml"))).getDocumentElement());

        Vector resSOAPBodyElements = (Vector) call.invoke(reqSOAPBodyElements);
        SOAPBodyElement resSOAPBodyElement = null ;
        resSOAPBodyElement = (SOAPBodyElement) resSOAPBodyElements.get(0);

        String response = XMLUtils.ElementToString(resSOAPBodyElement.getAsDOM());
        System.out.println("Received the following XML Response");
        System.out.println("-----------------------------------");
        System.out.println(response);
    }
}
