package com.eaaxis.chapter5;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import javax.xml.rpc.namespace.QName;

import org.apache.axis.encoding.Serializer;
import org.apache.axis.encoding.SerializerFactory;
import org.apache.axis.encoding.SerializationContext;
import org.apache.axis.Constants;

import java.util.Iterator;
import java.util.Vector;

/**
 * SerializerFactory for SparePartBean
 *
 */
public class EaAxisSerFactory implements SerializerFactory {

    private Vector mechanisms;

    public EaAxisSerFactory() {
    }
    public javax.xml.rpc.encoding.Serializer getSerializerAs(String mechanismType) {
        return new EaAxisSerializer();

    }
    public Iterator getSupportedMechanismTypes() {
        if (mechanisms == null) {
            mechanisms = new Vector();
            mechanisms.add(Constants.AXIS_SAX);
        }
        return mechanisms.iterator();
    }
}
