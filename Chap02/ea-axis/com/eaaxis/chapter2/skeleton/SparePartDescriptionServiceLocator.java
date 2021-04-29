/**
 * SparePartDescriptionServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis Wsdl2java emitter.
 */

package com.eaaxis.chapter2.skeleton;

public class SparePartDescriptionServiceLocator extends org.apache.axis.client.Service implements com.eaaxis.chapter2.skeleton.SparePartDescriptionService {

    // Use to get a proxy class for SparePartDescription
    private final java.lang.String SparePartDescription_address = "http://localhost:8080/ea-axis/services/SparePartDescription";

    public String getSparePartDescriptionAddress() {
        return SparePartDescription_address;
    }

    public com.eaaxis.chapter2.skeleton.SparePartDescription getSparePartDescription() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SparePartDescription_address);
        }
        catch (java.net.MalformedURLException e) {
            return null; // unlikely as URL was validated in WSDL2Java
        }
        return getSparePartDescription(endpoint);
    }

    public com.eaaxis.chapter2.skeleton.SparePartDescription getSparePartDescription(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            return new com.eaaxis.chapter2.skeleton.SparePartDescriptionSoapBindingStub(portAddress, this);
        }
        catch (org.apache.axis.AxisFault e) {
            return null; // ???
        }
    }
}
