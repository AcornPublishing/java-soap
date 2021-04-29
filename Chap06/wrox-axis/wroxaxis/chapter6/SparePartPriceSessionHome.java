package wroxaxis.chapter6;

import javax.ejb.EJBHome;
import javax.ejb.CreateException;
import javax.ejb.EJBException;
import java.rmi.RemoteException;
import wroxaxis.chapter6.SparePartPriceSession;
import wroxaxis.chapter6.SparePartPriceSessionBean;

public interface SparePartPriceSessionHome extends EJBHome {
wroxaxis.chapter6.SparePartPriceSession create() throws CreateException, EJBException, RemoteException;

}
