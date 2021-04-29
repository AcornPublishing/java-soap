package wroxaxis.chapter5;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import javax.xml.rpc.namespace.QName;

import org.apache.axis.encoding.Deserializer;
import org.apache.axis.encoding.DeserializerFactory;
import org.apache.axis.Constants;

import java.util.Iterator;
import java.util.Vector;

/**
 * DeserializerFactory for SparePartBean
 *
 */
public class WroxAxisDeserializerFactory implements DeserializerFactory {
    private Vector mechanisms;

    public WroxAxisDeserializerFactory() {
    }
    public javax.xml.rpc.encoding.Deserializer getDeserializerAs(String mechanismType) {
        return new WroxAxisDeserializer();
    }
    public Iterator getSupportedMechanismTypes() {
        if (mechanisms == null) {
            mechanisms = new Vector();
            mechanisms.add(Constants.AXIS_SAX);
        }
        return mechanisms.iterator();
    }
}
