package com.eaaxis.chapter6;

import javax.ejb.EJBHome;
import javax.ejb.CreateException;
import javax.ejb.EJBException;
import java.rmi.RemoteException;
import com.eaaxis.chapter6.SparePartPriceSession;
import com.eaaxis.chapter6.SparePartPriceSessionBean;

public interface SparePartPriceSessionHome extends EJBHome {
com.eaaxis.chapter6.SparePartPriceSession create() throws CreateException, EJBException, RemoteException;

}
