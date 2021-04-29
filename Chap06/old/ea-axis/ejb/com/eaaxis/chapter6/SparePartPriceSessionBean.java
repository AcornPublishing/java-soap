package com.eaaxis.chapter6;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import java.rmi.RemoteException;
import javax.ejb.EJBException;
import javax.ejb.CreateException;
import com.eaaxis.chapter6.SparePartPriceSessionHome;
import com.eaaxis.chapter6.SparePartPriceSession;

public class SparePartPriceSessionBean implements SessionBean {
    private SessionContext ctx;

    public void setSessionContext(SessionContext context) throws RemoteException, EJBException {
        ctx = context;
    }

    public void ejbActivate() throws RemoteException, EJBException {
    }

    public void ejbPassivate() throws RemoteException, EJBException {
    }

    public void ejbRemove() throws RemoteException, EJBException {
    }

    public void ejbCreate() throws CreateException, EJBException, RemoteException {
            // Write your code here
        }

    public Float getSparePartPrice(String sku){
	  return new Float((float)10.95);
    }
}
