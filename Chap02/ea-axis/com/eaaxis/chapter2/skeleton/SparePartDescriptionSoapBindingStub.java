/**
 * SparePartDescriptionSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis Wsdl2java emitter.
 */

package com.eaaxis.chapter2.skeleton;

public class SparePartDescriptionSoapBindingStub extends org.apache.axis.client.Stub implements com.eaaxis.chapter2.skeleton.SparePartDescription {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();
    private boolean firstCall = true;

    public SparePartDescriptionSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public SparePartDescriptionSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public SparePartDescriptionSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        try {
            if (service == null) {
                super.service = new org.apache.axis.client.Service();
            } else {
                super.service = service;
            }
        }
        catch(Exception t) {
            throw org.apache.axis.AxisFault.makeFault(t);
        }
    }

    private javax.xml.rpc.Call getCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call call =
                    (org.apache.axis.client.Call) super.service.createCall();
            if (maintainSessionSet) {
                call.setMaintainSession(maintainSession);
            }
            if (super.cachedEndpoint != null) {
                call.setTargetEndpointAddress(super.cachedEndpoint);
                call.setProperty(org.apache.axis.transport.http.HTTPTransport.URL, super.cachedEndpoint.toString());
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                String key = (String) keys.nextElement();
                call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            if (firstCall) {
                firstCall = false;
                for (int i = 0; i < cachedSerFactories.size(); ++i) {
                    Class cls = (Class) cachedSerClasses.get(i);
                    javax.xml.rpc.namespace.QName qName =
                            (javax.xml.rpc.namespace.QName) cachedSerQNames.get(i);
                    Class sf = (Class)
                             cachedSerFactories.get(i);
                    Class df = (Class)
                             cachedDeserFactories.get(i);
                    call.registerTypeMapping(cls, qName, sf, df, false);
                }
            }
            return call;
        }
        catch (Throwable t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", t);
        }
    }

    public java.lang.String getDescription(java.lang.String in0) throws java.rmi.RemoteException{
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        javax.xml.rpc.Call call = getCall();
        call.addParameter("in0", new javax.xml.rpc.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), javax.xml.rpc.ParameterMode.PARAM_MODE_IN);
        call.setReturnType(new javax.xml.rpc.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        call.setProperty("soap.http.soapaction.use", Boolean.TRUE);
        call.setProperty("soap.http.soapaction.uri", "");
        call.setOperationName(new javax.xml.rpc.namespace.QName("http://localhost:8080/ea-axis/services/SparePartDescription", "getDescription"));

        Object resp = call.invoke(new Object[] {in0});

        if (resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)resp;
        }
        else {
             return (java.lang.String) resp;
        }
    }

}
