/*
 * SparePartPriceStubClient.java
 *
 * Created on February 9, 2002, 12:50 PM
 */

package com.eaaxis.chapter2.stubs;

/**
 *
 * @author  Administrator
 * @version 
 */
public class SparePartPriceStubClient {

    /** Creates new SparePartPriceStubClient */
    public SparePartPriceStubClient() {
    }

    /**
    * @param args the command line arguments
    */
    public static void main (String[] args) throws Exception {
        SparePartPriceServiceLocator serviceLocator = new SparePartPriceServiceLocator();
        SparePartPriceSoapBindingStub stub  = (SparePartPriceSoapBindingStub) serviceLocator.getSparePartPrice();
        float price = stub.getPrice("SKU-123");
        System.out.println("The price is : $" + price);
    }

}
