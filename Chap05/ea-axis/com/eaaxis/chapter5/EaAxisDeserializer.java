package com.eaaxis.chapter5;

import org.apache.axis.encoding.DeserializationContext;
import org.apache.axis.encoding.Deserializer;
import org.apache.axis.encoding.DeserializerImpl;
import org.apache.axis.encoding.FieldTarget;
import org.apache.axis.Constants;
import org.apache.axis.message.SOAPHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import javax.xml.rpc.namespace.QName;
import java.io.IOException;
import java.util.Hashtable;

public class EaAxisDeserializer extends DeserializerImpl
{
    public static final String SKU = "sku";
    public static final String PRICE = "price";
    public static final String DESCRIPTION = "description";

    private Hashtable typesByMemberName = new Hashtable();

    public EaAxisDeserializer()
    {
		System.out.println("inside eaaxis deserializer");
        typesByMemberName.put(SKU, Constants.XSD_STRING);
        typesByMemberName.put(PRICE, Constants.XSD_FLOAT);
        typesByMemberName.put(DESCRIPTION, Constants.XSD_STRING);
        typesByMemberName.put(DESCRIPTION, Constants.XSD_STRING);
        value = new SparePartBean();
    }

    public SOAPHandler onStartChild(String namespace,
                                    String localName,
                                    String prefix,
                                    Attributes attributes,
                                    DeserializationContext context)
        throws SAXException
    {
		System.out.println("in onStartChild");
        QName typeQName = (QName)typesByMemberName.get(localName);
        if (typeQName == null)
            throw new SAXException("Invalid element in SparePartBean struct - " + localName);

        // These can come in either order.
        Deserializer dSer = context.getDeserializerForType(typeQName);
        try {
            dSer.registerValueTarget(new FieldTarget(value, localName));
        } catch (NoSuchFieldException e) {
            throw new SAXException(e);
        }

        if (dSer == null)
            throw new SAXException("No deserializer for a " + typeQName + "???");

        return (SOAPHandler) dSer;
    }
}
