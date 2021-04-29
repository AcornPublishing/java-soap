package wroxaxis.chapter6;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import java.rmi.RemoteException;
import javax.ejb.EJBException;
import javax.ejb.CreateException;
import wroxaxis.chapter6.SparePartPriceSessionHome;
import wroxaxis.chapter6.SparePartPriceSession;

public class SparePartPriceSessionBean implements SessionBean {

    public void setSessionContext(SessionContext context) throws RemoteException, EJBException {
    }

    public void ejbActivate() throws RemoteException, EJBException {
    }

    public void ejbPassivate() throws RemoteException, EJBException {
    }

    public void ejbRemove() throws RemoteException, EJBException {
    }

    public void ejbCreate() throws CreateException, EJBException, RemoteException {
        }

    public Float getSparePartPrice(String sku){
	  return new Float((float)10.95);
    }
}
