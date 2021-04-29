package com.eaaxis.chapter4;

/**
 *
 * @author  Administrator
 * @version 
 */
public class SparePartInfo {

    /** Creates new SparePartPriceService */
    public SparePartInfo() {
    }

    public String getPartInfo(String PartSKU) throws Exception {
        //if (PartSKU != null) throw new Exception("No such part");
        return PartSKU + " - Part Info";
    }
}
