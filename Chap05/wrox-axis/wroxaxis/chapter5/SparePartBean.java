/*
 * SparePartBean.java
 */

package wroxaxis.chapter5;

/**
 *
 * @author  Administrator
 * @version
 */
public class SparePartBean {

    private String sku = null;
    private float price = 0.0f;
    private String description = null;

    // Default constructor to creates new SparePartBean
    public SparePartBean() {
    }

    // Get methods
    public String getSku() {
		return sku;
	}

    public float getPrice() {
        return price;
    }

    public String getDescription(){
		return description;
	}

    // Set methods
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

