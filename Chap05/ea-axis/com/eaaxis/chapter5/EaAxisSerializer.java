package com.eaaxis.chapter5;

import org.apache.axis.encoding.SerializationContext;
import org.apache.axis.encoding.Serializer;
import org.apache.axis.message.SOAPHandler;
import org.apache.axis.Constants;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.apache.axis.Constants;
import org.apache.axis.wsdl.fromJava.Types;

import javax.xml.rpc.namespace.QName;
import java.io.IOException;
import java.util.Hashtable;

public class EaAxisSerializer implements Serializer
{
    public static final String SKU = "sku";
    public static final String PRICE = "price";
    public static final String DESCRIPTION = "description";

    public void serialize(QName name, Attributes attributes,
                          Object value, SerializationContext context)
        throws IOException
    {
        System.out.println("inside axis serializer");
        if (!(value instanceof SparePartBean))
            throw new IOException("Can't serialize a " + value.getClass().getName() + " with EaAxisSerializer.");

        SparePartBean spBean = (SparePartBean)value;

        context.startElement(name, attributes);
        context.serialize(new QName("", SKU), null, spBean.getSku(), String.class);
        context.serialize(new QName("", PRICE), null, ""+spBean.getPrice(), float.class);
        context.serialize(new QName("", DESCRIPTION), null, spBean.getDescription(), String.class);
        context.endElement();
    }
    public String getMechanismType() { return Constants.AXIS_SAX; }

    public boolean writeSchema(Types types) throws Exception {
        return false;
    }
}
