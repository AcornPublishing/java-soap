/**
 * SparePartDescriptionSoapBindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis Wsdl2java emitter.
 */

package com.eaaxis.chapter2.skeleton;

public class SparePartDescriptionSoapBindingSkeleton implements com.eaaxis.chapter2.skeleton.SparePartDescription,
    org.apache.axis.wsdl.Skeleton {
    private com.eaaxis.chapter2.skeleton.SparePartDescription impl;
    private static org.apache.axis.wsdl.SkeletonImpl skel = null;

    public SparePartDescriptionSoapBindingSkeleton() {
        this.impl = new com.eaaxis.chapter2.skeleton.SparePartDescriptionSoapBindingImpl();
        init();
    }

    public SparePartDescriptionSoapBindingSkeleton(com.eaaxis.chapter2.skeleton.SparePartDescription impl) {
        this.impl = impl;
        init();
    }
    public String getParameterName(String opName, int i) {
        return skel.getParameterName(opName, i);
    }

    public static String getParameterNameStatic(String opName, int i) {
        init();
        return skel.getParameterName(opName, i);
    }

    protected static void init() {
        if (skel != null) 
            return;
        skel = new org.apache.axis.wsdl.SkeletonImpl();
        skel.add("getDescription",
                 new String[] {
                 "return",
                 "in0",
                 });
    }

    public java.lang.String getDescription(java.lang.String in0) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.getDescription(in0);
        return ret;
    }

}
