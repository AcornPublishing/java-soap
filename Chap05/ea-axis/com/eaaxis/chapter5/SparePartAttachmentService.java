/*
 * SparePartAttachmentService.java
 *
 */

package com.eaaxis.chapter5;

import javax.activation.DataHandler;
import java.io.*;

public class SparePartAttachmentService {

	   public SparePartAttachmentService(){}

   public String addImage(String sku, DataHandler dataHandler) {
	      try {
		   String filepath =
                 "d:/ea-axis/com/eaaxis/chapter5/"+sku+"-image.jpg";
		   FileOutputStream fout = new FileOutputStream(
                                                new File(filepath));

		   BufferedInputStream in =
                new BufferedInputStream(dataHandler.getInputStream());
		   while(in.available()!=0)
		   {
			fout.write(in.read());
		   }
	       }catch(Exception e) {
               return e.toString();
        }
      return "Image with SKU: "+sku+" has been added successfully!!";
	   }
}
