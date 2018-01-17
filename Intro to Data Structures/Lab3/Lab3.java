//Jose Salinas <jfsalinas14>

import java.util.Scanner;
public class Lab3
  {
public static void main(String[] args)
  {
    Scanner keyboard = new Scanner(System.in);
    System.out.println("Please enter text");
    String sentence;
    sentence = keyboard.nextLine();
    System.out.println("Enter a character to search for.");
    String character;
    character = keyboard.nextLine();
    int where = sentence.indexOf(character);
    int start= sentence.indexOf(" ")+1;
    int end = sentence.indexOf(" ",start+1);
    System.out.println(character + " is character " + (where+1) + " of the input string."); 
    System.out.println("index = " + start);
    System.out.println("The second word is: " + sentence.substring(start,end));
  
  }
  }
