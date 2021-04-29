package com.eaaxis.chapter2.messaging;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

import java.util.Vector;
import java.util.Date;
import java.text.SimpleDateFormat;

import org.apache.axis.MessageContext ;
import org.apache.axis.utils.XMLUtils;
import org.apache.axis.message.SOAPBodyElement;

import java.io.*;

public class CatalogPublisherService {
    public Element[] publishCatalog(MessageContext context, Vector soapBodyElements) throws Exception {
        
        //Retrieve the SOAPBody Element i.e. the Catalog XML
        Element soapBody = (Element) soapBodyElements.get(0);
        
        //Get a DOM NodeList for all <PRODUCT> Elements
        NodeList productList = soapBody.getElementsByTagName("PRODUCT");
        
        //Get the count of <PRODUCT> elements in the NodeList
        int productCount = productList.getLength();

        /******************************
        **Call appropriate back-end code to update Supplier Catalog
        *******************************/
        
        //Start Building Response Document
        //Get a DocumentBuilder object
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        
        //The Response XML is in the form
        /*
          <CATALOGUPDATE>
           <ITEMCOUNT>nnn</ITEMCOUNT>
           <DATERECEIVED>mm/dd/yyyy</DATERECEIVED>
          </CATALOGUPDATE>
         */
        
        //Creat a new DOM Document 
        Document responseDoc = builder.newDocument();
        
        //Create the namespace aware root Element <CATALOGUPDATE>
        Element resRoot = responseDoc.createElementNS("http://www.wrox.com/eaaxis/catalogupdate","CATALOGUPDATE");
        resRoot.setPrefix("CU");
        
        //Create the ITEMCOUNT element
        Element itemCount = responseDoc.createElement("ITEMCOUNT");
        Text itemCountText = responseDoc.createTextNode(String.valueOf(productCount));

        //Create the DATE RECEIVED element
        Element dateReceived = responseDoc.createElement("DATERECEIVED");
        SimpleDateFormat fmt = new SimpleDateFormat("MM/dd/yyyy");
        String date = fmt.format(new Date());
        Text dateReceivedText = responseDoc.createTextNode(date);
        
        //Append the child elements appropriately
        resRoot.appendChild(itemCount);
        itemCount.appendChild(itemCountText);
        resRoot.appendChild(dateReceived);
        dateReceived.appendChild(dateReceivedText);

        
        //Create an org.w3c.dom.Element array and assign the first element to the 
        //resRoot Element that contains the XML response.
        Element[] result = new Element[1];
        result[0] = resRoot;
        
        //Return the XML Response (org.w3c.dom.Element Array)
        return(result);
    }
}
