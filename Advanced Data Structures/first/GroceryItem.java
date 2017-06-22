/* 
Jose Salinas
CSCI 3310 Program 1
Fall 2015
 */
public class GroceryItem

{
	private int productNumber;
	private String description;
	private int quantity;
	private double price;
	private String tax;

	public int getNumber()
	{
		return productNumber;
	}

	public java.lang.String getName()
	{
		return description;
	}
	public int getQuantity()
	{
		return quantity;
	}

	public double getPrice()
	{
		return price;
	}

	public java.lang.String getTaxCode()
	{
		return tax;
	}

	public void setQuantity(int value)
	{
		quantity = value;
	}

//redirects input to fill variables in GroceryItem
	public void readItem(java.util.Scanner inFile)
	{
		productNumber= inFile.nextInt();
		description=inFile.next();
		quantity=inFile.nextInt();
		price=inFile.nextDouble();
		tax=inFile.next();
	}

// returns location in array item of size numItems from productId
	public static int itemSearch(GroceryItem[] item, int numItems, int productID)
	{
		int index = -1;
		for(int i = 0; i < numItems; i++)
		{
			if(item[i].getNumber() == productID)
			{
				index = i;
				break;
			}                     
		}
		return index;	
	}

//prints inventory from array item of size numItems
	public static void printInventory(GroceryItem[] item, int numItems)
	{
		for(int i=0; i<numItems; i++)
		{
			System.out.printf("%5d",item[i].getNumber());
			System.out.printf("%15s",item[i].getName());
			System.out.printf("%6d",item[i].getQuantity());
			System.out.printf("%8.2f",item[i].getPrice());
			System.out.printf("%3s",item[i].getTaxCode());
			System.out.println();
		}
	}
}
