package ch.leafit.dtd;

/**
 * Describes a product to purchase and its quantity.
 */
public final class Item {
	/**
	 * Constructs an item from the product and quantity
	 * 
	 * @param aProduct
	 *            the product
	 * @param aQuantity
	 *            the item quantity
	 */
	public Item(Product aProduct, int aQuantity) {
		theProduct = aProduct;
		quantity = aQuantity;
	}

	/**
	 * Computes the total cost of this item.
	 * 
	 * @return the total price
	 */
	public double getTotalPrice() {
		return theProduct.getPrice() * quantity;
	}

	/**
	 * Formats this item.
	 * 
	 * @return a formatted string of this item
	 */
	public String format() {
		final int COLUMN_WIDTH = 30;
		String description = theProduct.getDescription();

		String r = description;

		// pad with spaces to fill column

		int pad = COLUMN_WIDTH - description.length();
		for (int i = 1; i <= pad; i++)
			r = r + " ";

		r = r + theProduct.getPrice() + "   " + quantity + "   "
				+ getTotalPrice();

		return r;
	}

	private int quantity;
	private Product theProduct;
}
