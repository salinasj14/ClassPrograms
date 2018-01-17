//salinasj14

import java.util.Scanner;
public class Lab5
 {
public static void main(String[] args)
  {
    Scanner keyboard = new Scanner(System.in);
    int humans = 0;
    int aliens = 0;
    int test=0;
    do
    {
    System.out.println("Please enter the DNA strand.");
    String dna = keyboard.next();
    char gene = dna.charAt(0);
    System.out.println("Please enter the second DNA strand.");
    String dnap = keyboard.next();
    char gene2 = dnap.charAt(0);
     if ((gene == 'T')&&(gene2 == 'A'))
     {
      System.out.println("This is human DNA.");
      humans++;
     }
     else if ((gene == 'A')&&(gene2 == 'T'))
     {
     System.out.println("This is human DNA.");
     humans++;
     }
     else if ((gene == 'C')&&(gene2 == 'G'))
     {
     System.out.println("This is human DNA.");
     humans++;
     }
     else if ((gene == 'G')&&(gene2 == 'C'))
     {
     System.out.println("This is human DNA.");
     humans++;
     }
     else
     {
     System.out.println("This is alien DNA.");
     aliens++;
     }
     System.out.println("Do you want to continue?");
     System.out.println("Press 1 to continue, Press 2 to quit.");
     test = keyboard.nextInt();
     }while (test == 1);
     
     System.out.println("There are " + aliens + " mismatches.");
     System.out.println("There are " + humans + " matches.");
           
  }
 }
