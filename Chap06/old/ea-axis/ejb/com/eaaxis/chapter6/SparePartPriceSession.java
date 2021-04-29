package com.eaaxis.chapter6;

import javax.ejb.EJBObject;
import java.rmi.RemoteException;
import javax.ejb.EJBException;
import com.eaaxis.chapter6.SparePartPriceSessionBean;

public interface SparePartPriceSession extends EJBObject {
    Float getSparePartPrice(String sku) throws RemoteException, EJBException;
}
