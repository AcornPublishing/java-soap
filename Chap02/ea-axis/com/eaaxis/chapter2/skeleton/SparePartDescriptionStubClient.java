/*
 * SparePartDescriptionStubClient.java
 *
 * Created on February 9, 2002, 12:50 PM
 */

package com.eaaxis.chapter2.skeleton;

/**
 *
 * @author  Administrator
 * @version 
 */
public class SparePartDescriptionStubClient {

    /** Creates new SparePartDescriptionStubClient */
    public SparePartDescriptionStubClient() {
    }

    /**
    * @param args the command line arguments
    */
    public static void main (String args[]) throws Exception {
        SparePartDescriptionServiceLocator serviceLocator = new SparePartDescriptionServiceLocator();
        SparePartDescriptionSoapBindingStub stub  = (SparePartDescriptionSoapBindingStub) serviceLocator.getSparePartDescription();
        String productDesc = (String) stub.getDescription("SKU-123");
        System.out.println("Product Description : " + productDesc);
    }

}
