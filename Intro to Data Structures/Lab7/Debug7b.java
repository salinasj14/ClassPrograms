/* Ronnie W. Smith (rws)
   CSCI 2310 Section 001

   This program reads in a sequence of text lines from standard input and
   produces as output the count of how many vowels occur in every word, one
   word per line.

   Program uses a simple loop that examines each letter of a word to determine
   if it's a vowel or not, and increments a counter as appropriate.

   PROGRAM ASSUMPTIONS

   1. All letters are lower case.
   2. The vowels are a, e, i, o, u.
   3. The sentinel value marking "end of input" is the_end.  No information
      is to be output for the sentinel.

*/

import java.util.*;

public class Debug7b
{
  public static void main(String[] args)
  {
    String word;      // current input word
    int length;       // length of current word
    int position;     // location of letter within word being analyzed
    int vowelCount;   // count of number of vowels in current word
    char ch;          // character in current word being analyzed

    Scanner keyboard = new Scanner(System.in);   // input device

    word = keyboard.next();          // initial word

    while (!word.equals("the_end"))  // examine each letter of word
    {
      length = word.length();
      vowelCount = 0;

      // loop to fetch each letter and check
      for (position = 0; position <= length-1; position++) 
      {
        ch = word.charAt(position); 
        if ((ch == 'a') || (ch == 'e') || (ch == 'i') || (ch == 'o') 
                        || (ch == 'u')) vowelCount++;
      }

      // handle final output with proper pluralization depending on count

      System.out.print("There ");
      if (vowelCount == 1) System.out.print ("is 1 vowel in ");
      else System.out.print("are " + vowelCount + " vowels in ");

      System.out.println(word);

      word = keyboard.next();   // fetch next word
    }
  }
}
