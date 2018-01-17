//Jose Salinas <salinasj14>
//CSCI 2310 Section 3
//November 20 2014
// Fall 2014

import java.util.*;

public class LetCount
{
  public static final int NUMCHARS = 27;
  // Method addr provided
  public static int addr(char ch)  
  {
    return (int) ch - (int) 'A' + 1; 
  }
    
    public static char min(int[] countArray)//made a method for minimum charater occurance
    {
        int min = countArray[addr('A')];
        char temp = 'A';
        for(char ch = 'A'; ch <='Z'; ch++)
        {
            if(countArray[addr(ch)] < min)
            {
                min = countArray[addr(ch)];
                temp = ch;
            }
        }
        return temp;
    }
    public static char max(int[] countArray)//made a method for maximum charater occurance
    {
        int max = 0;
        char temp = 'A';
        for(char ch = 'A'; ch <= 'Z'; ch++)
        {
            if(countArray[addr(ch)] > max)
            {
                max = countArray[addr(ch)];
                temp = ch;
            }
        }
        return temp;
    }
    public static int count(String testString, char testChar)//method keeps tally of character occurence
    {
        int tally = 0; // initializing my counter
        for(int i = 0; i < testString.length(); i++)
        {
            if (testString.charAt(i) == testChar)
            {
                tally++;//keeps tally of how many times a character occured
            }
        }
        return tally;// returns amount of times letter occured
    }
    public static void result(int[] count, char high, char low)
    {
        char ch;
        for(ch = 'A'; ch <= 'Z'; ch++)
        {
            System.out.println(ch + " occured " + count[addr(ch)] + " times.");
        }
        System.out.println();
        System.out.println("Most frequent letter = " + high);
        System.out.println("Least frequent letter = " + low);
    }
// Required method definitions for (1) analyzing each character in an input
// line to update the appropriate count; (2) determining most frequent letter; 
// (3) determining least frequent letter; and (4) printing final results 
// should be defined here. 

  public static void main(String[] args)
  {
      System.out.println();
      Scanner keyboard = new Scanner(System.in); // reads input
      int [] count = new int [NUMCHARS]; // count `characters
      String line;
      while(keyboard.hasNext())
      {
          line = keyboard.nextLine();
          for(char ch = 'A'; ch <= 'Z'; ch++)
          {
              count[addr(ch)] = count[addr(ch)] + count(line,ch);
          }
          System.out.println(line);
      }
      System.out.println();
      char highest = max(count);//highest number a letter occured
      char lowest = min(count);//lowest number a letter occured
      result(count, highest, lowest);
      System.out.println();   // Other declarations as needed

 
   // Method calls and other statements to complete problem solution go here


  } // end of main

} // end of class
