package Model;

public class Item {

	
	String sku;
	

	public Item(String sku) {
		this.sku = sku;
	}


	@Override
	public boolean equals(Object obj) {

		if (obj == this) {
			return true;
		}

		/* Check if o is an instance of Complex or not
	          "null instanceof [type]" also returns false */
		if (!(obj instanceof Item)) {
			return false;
		}
		Item i = (Item) obj;
		
		if(sku == i.sku)
		{
			return true;
		}
		return false;

	}


	@Override
	public String toString() {
		return sku;
	
	}


}
