/*
 * SparePartBean.java
 */

package com.eaaxis.chapter5;

/**
 *
 * @author  Administrator
 * @version
 */
public class SparePartBean {

    public String sku = null;
    public float price = 0.0f;
    public String description = null;

    /** Creates new SparePart */
    public SparePartBean() {
    }


    public String getSku() {
		return sku;
	}

    public float getPrice() {
        return price;
    }

    public String getDescription(){
		return description;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}

