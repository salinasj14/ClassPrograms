/* 
Jose Salinas
CSCI 3310 Program 1
Fall 2015
 */
// GroceryTest.java is a program to simulate a store's inventory
// and adjust the inventory accordingly with user input.

import java.io.*;
import java.util.Scanner;

public class GroceryTest
{
	public static final int MAXNUMBEROFITEMS = 100;

	public static void main(String[] args)
	{
		GroceryItem[] list = new GroceryItem[MAXNUMBEROFITEMS];
		int inventory = 0;
		Scanner fileInput; 
		int error = 0;
		File inFile = new File("inventory.dat");
		try 
		{
			fileInput = new Scanner(inFile);  // open inventory file and read
			while (fileInput.hasNext())
			{
				list[inventory]= new GroceryItem(); // creating new item
				list[inventory].readItem(fileInput); // putting it in table
				error = GroceryItem.itemSearch(list,inventory,list[inventory].getNumber());
				if(error != -1)//if the item is already in the list, overwrite the location in array 
				{
					System.out.printf("** ERROR: duplicate item %5d %5s ignored.\n",list[inventory].getNumber(),list[inventory].getName());
					inventory--;
				}
				inventory++;  //update inventory in array.
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.println(e); 
			System.exit(0);
		}
		System.out.println("INITIAL INVENTORY\n");
		GroceryItem.printInventory(list,inventory);
		int prodNum = 0;
		int quantity = 0;
		int custNum = 1;
		double totalCost = 0;
		double totalPrice = 0;
		double grandTotal = 0;
		double food = 0.02;
		double nonFood = 0.08;
		double taxTotal = 0;
		int item = 0;
		Scanner keyboard = new Scanner(System.in);
		while(keyboard.hasNext())
		{
			System.out.println("\nPURCHASE SIMULATION");
			System.out.println("\nCustomer " + custNum);
			while(keyboard.hasNext())
			{
				prodNum = keyboard.nextInt();
				quantity = keyboard.nextInt();
				if(prodNum == 0 && quantity == 0) //if 0 and 0 are detected, print out totalCost,taxTotal and grandTotal
				{
					custNum++;
					System.out.printf("\nTotal Cost  = $  %5.2f\n", totalCost);
					System.out.printf("Total Tax   = $  %5.2f\n", taxTotal);
					grandTotal = totalCost + taxTotal;
					System.out.printf("Grand Total = $  %5.2f\n\n", grandTotal);
					if(keyboard.hasNext())//if there is still input, there is a new customer
					{
						System.out.println("Customer " + custNum);
					}
					totalCost = 0;
					taxTotal = 0;
					totalPrice = 0;
				}
				else
				{
					item = GroceryItem.itemSearch(list, inventory, prodNum);
					if (item != -1)
					{
						totalPrice = list[item].getPrice() * quantity;
						System.out.printf("%13s %2d @ %4.2f, cost =%7.2f", list[item].getName(), quantity, list[item].getPrice(), totalPrice);
						list[item].setQuantity(list[item].getQuantity()-quantity);
						if (list[item].getTaxCode().equals("N"))
						{
							taxTotal += (totalPrice * nonFood);
							System.out.printf("%3s",list[item].getTaxCode());
							System.out.println();
						}
						else
						{
							taxTotal += (totalPrice * food);
							System.out.printf("%3s",list[item].getTaxCode());
							System.out.println();
						}
						totalCost += totalPrice;
					}
					else
					{
						System.out.println("    *** item "+ prodNum + " not in inventory ***");
					}
				}
			}
		}
		System.out.println("Final Inventory\n");
		GroceryItem.printInventory(list, inventory);
	}
}
