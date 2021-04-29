package com.eaaxis.chapter5;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import javax.xml.rpc.namespace.QName;
import java.io.IOException;

import org.apache.axis.encoding.Deserializer;
import org.apache.axis.encoding.DeserializerFactory;
import org.apache.axis.encoding.DeserializationContext;
import org.apache.axis.encoding.DeserializerImpl;
import org.apache.axis.Constants;

import java.util.Iterator;
import java.util.Vector;

/**
 * DeserializerFactory for SparePartBean
 *
 */
public class EaAxisDeserFactory implements DeserializerFactory {
    private Vector mechanisms;

    public EaAxisDeserFactory() {
    }
    public javax.xml.rpc.encoding.Deserializer getDeserializerAs(String mechanismType) {
        return new EaAxisDeserializer();
    }
    public Iterator getSupportedMechanismTypes() {
        if (mechanisms == null) {
            mechanisms = new Vector();
            mechanisms.add(Constants.AXIS_SAX);
        }
        return mechanisms.iterator();
    }
}
