package wroxaxis.chapter6;

import javax.ejb.EJBObject;
import java.rmi.RemoteException;
import javax.ejb.EJBException;
import wroxaxis.chapter6.SparePartPriceSessionBean;

public interface SparePartPriceSession extends EJBObject {
    Float getSparePartPrice(String sku) throws RemoteException, EJBException;
}
