/*
 * SparePartService.java
 */

package com.eaaxis.chapter5;
import java.io.*;
import java.util.*;

/**
 *
 * @author  Administrator
 * @version
 */
public class SparePartService {

	String file = "d:/ea-axis/com/eaaxis/chapter5/sparepartlist.txt";
	String delimiter = "|";
	public SparePartBean getSparePart(String PartSKU) {

      SparePartBean spBean = new SparePartBean();

      try {
         	BufferedReader br = new BufferedReader(new FileReader(file));
   		 	String line = null;

   			while ( (line = br.readLine()) != null)
   			{
   				StringTokenizer sToken = new StringTokenizer(line, delimiter);
   				while(sToken.hasMoreTokens())
   				{
   					String sku = sToken.nextToken();
   					if(sku.equalsIgnoreCase(PartSKU)) {
   						spBean.setPrice(Float.parseFloat(sToken.nextToken()));
   						spBean.setDescription(sToken.nextToken());
   						break;
   					}
   	   			}
   			}
   	  } catch(IOException e){
   		  System.out.println(e);
   	    }
	return spBean;
    }

    public String addSparePart(SparePartBean spBean) {
      try {

			FileOutputStream fout = new FileOutputStream(file, true);
			PrintWriter out = new PrintWriter(fout);
			out.print("\n");
			out.print(spBean.getSku()+delimiter);
			out.print(spBean.getPrice()+delimiter);
			out.print(spBean.getDescription()+delimiter);
			out.close();
			fout.flush();
			fout.close();
		}catch(Exception e) { System.out.println(e);return e.toString();}

    return "SparePart with SKU: "+spBean.getSku()+" has been added successfully!!";
	}
}
