//salinasj14

import java.util.Scanner;
public class Lab4
 {
public static void main(String[] args)
  {
    Scanner keyboard = new Scanner(System.in);
    System.out.println("Please enter a phrase.");
    String phrase = keyboard.nextLine();
    int numPhrase = phrase.length();
    char end = phrase.charAt(phrase.length()-1);
    if(end == '?')
    {
     System.out.println("I see you have a question");
    }
    else if (end =='!')
    {
     System.out.println("why are you yelling?");
    }
    else if (end == ';')
    {
     System.out.println("are you talking in code?");
    }
    else if (end == '.')
    {
    System.out.println("you always say " + phrase);
    }
    else
    {
    System.out.println("ERROR");
    }
  }
 }
