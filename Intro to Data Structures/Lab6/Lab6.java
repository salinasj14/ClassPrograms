//salinasj14

import java.util.Scanner;
public class Lab6
 {
public static void main(String[] args)
  {
    Scanner keyboard = new Scanner(System.in);
    int humans = 0;
    int aliens = 0;
    while (keyboard.hasNext())
    {
        String dna = keyboard.nextLine();
        String dna2 = keyboard.nextLine();
        int counter = 0;
        int counter2 = 0;
        int counter3 = 0;
       while (counter<dna.length())
    {
    if (dna.charAt(counter) == 'A' && dna2.charAt(counter)=='T')
      {
       counter2++;
      }
    if (dna.charAt(counter) == 'T' && dna2.charAt(counter)=='A')
      {
       counter2++;
       }
    if (dna.charAt(counter) == 'C' && dna2.charAt(counter)=='G')
      {
       counter2++;
        }
    if (dna.charAt(counter) == 'G' && dna2.charAt(counter)=='C')
      {
        counter2++;
      }
     counter++;    
    } 
      counter3 = (dna.length()-counter2);
       
      if(counter3 == 0)
	{ 
	     System.out.println();
	     System.out.println("Analysis of " + dna + " and " + dna2);
	     System.out.println("DNA is from earth creature.");
        humans++;    
    }
	if(counter3 == 1)
	 {
        System.out.println();
        System.out.println("Analysis of " + dna + " and " + dna2);	
        System.out.println("DNA is from alien. There is 1 mismatch.");
        aliens++;	
        }
	if (counter3 > 1)
	{
	     System.out.println();
        System.out.println("Analysis of " + dna + " and " + dna2); 	
        System.out.println("DNA is from alien. There are " + counter3 + " mismatches.");      
        aliens++;
   }
  }
     System.out.println();
     System.out.println("SUMMARY ANALYSIS:");
     System.out.println(humans +" creature and "+ aliens +" aliens");
     System.out.println();
   }
 }


