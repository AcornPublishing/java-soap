/**
 * SparePartPriceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis Wsdl2java emitter.
 */

package com.eaaxis.chapter2.stubs;

public class SparePartPriceServiceLocator extends org.apache.axis.client.Service implements com.eaaxis.chapter2.stubs.SparePartPriceService {

    // Use to get a proxy class for SparePartPrice
    private final java.lang.String SparePartPrice_address = "http://localhost:8080/ea-axis/services/SparePartPrice";

    public String getSparePartPriceAddress() {
        return SparePartPrice_address;
    }

    public com.eaaxis.chapter2.stubs.SparePartPrice getSparePartPrice() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SparePartPrice_address);
        }
        catch (java.net.MalformedURLException e) {
            return null; // unlikely as URL was validated in WSDL2Java
        }
        return getSparePartPrice(endpoint);
    }

    public com.eaaxis.chapter2.stubs.SparePartPrice getSparePartPrice(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            return new com.eaaxis.chapter2.stubs.SparePartPriceSoapBindingStub(portAddress, this);
        }
        catch (org.apache.axis.AxisFault e) {
            return null; // ???
        }
    }
}
