//salinasj14   Jose Salinas
// 12/12/2014

import java.util.Random;
import java.util.Scanner;
import java.io.*;



public class lab12 {
	public static final int MAXSIZE=15000;
	public static Scanner kbd;
	public static final int MAXGUESSES=8;
	public static final int LENGTH=20;

	public static void main(String[] args) 
	{
		int wins=0;
		int losses=0;
		kbd = new Scanner(System.in);
		try
		{
			Scanner input = new Scanner(new FileReader("dictionary.txt"));
			String []dictionary =new String [MAXSIZE];
			int wordchosen= filldictionary(input,dictionary);
			input.close();
			int random=pickrandom(wordchosen);
			int tries=MAXGUESSES;
			String randWord=dictionary[random];

			// Set up connection to an output file
			//PrintWriter output = new PrintWriter(new FileOutputStream("newfile.txt"));



			System.out.println("H A N G M A N");
			System.out.println("This is a word guessing game.  A word will be selected at random");
			System.out.println("and kept hidden. You will try to figure out the secret word by");
			System.out.println("guessing letters which you think are in the word.  You will guess");
			System.out.println("one letter at a time.  If the letter you guess is correct, the");
			System.out.println("position(s) of the letter in the secret word will be shown.");
			System.out.println("You will be allowed 8 wrong guesses.  If you guess incorrectly 8");
			System.out.println("times, you lose the game.  If you guess all of the letters in the");
			System.out.println("word, you win.\n");

			System.out.println("Press enter to continue...");
			kbd.nextLine();
			clrScrn();
			//System.out.println(randWord); this was my test
			char []asterisks=getAsterisks(randWord);



			String cont;
			//plays multiple games
			do{
				astPrnt(asterisks);
				System.out.println("Enter letter to try(or 9 to quit)" +":");
				String guess= kbd.next();
				//allows one game
				//int guesses=0;
				String guesses = "";
				//tries = tries - 1;

				while(!guess.equals("9") && wins !=randWord.length()-1 && tries  > 0)

				{
					//guesses++;                
					char letter = guess.toLowerCase().charAt(0);


					if (randWord.indexOf(letter) <0 ){

						tries--;
						System.out.println("\n"+"Guesses Remaining: "+ tries);

						letter = guess.charAt(0);
						guesses += letter;

						System.out.print("Incorrect letters tried: " + guesses.toUpperCase());

						System.out.print(", Enter letter to try(or 9 to quit)" +":");
						System.out.println();
						//char[]wrongletters=new char[20];
						//Arrays.fill(wrongletters, 0);

					}
					//letter is in word
					else
					{
						
						wins++;
						System.out.println("\n"+"Guesses Remaining: "+ tries);
						System.out.print("Incorrect letters tried: " + guesses.toUpperCase());
						System.out.print(" Enter letter to try(or 9 to quit)" +":");
						System.out.println();
						newAst(randWord,letter,asterisks);

					}
					astPrnt(asterisks);
					guess= kbd.next();    
					//}
					//if the guess is equal to 0 then it will exit program 
					if(guess.equals("9"))
					{
						System.out.println ("Thank you for playing!");
					}//if you choose correct word then you win
				   if(wins ==randWord.length()-1)
					{
						System.out.println(randWord.toUpperCase());
						System.out.println("You Won!");
					}

					//if your attempts equals to 0 then it will ask you to play again and tell you remaining guesses
				if(tries==0)
					{
						System.out.println("You have no guesses remaining");
						losses--;

					}
				}
				System.out.print( '\n'+"Do you want to play again (Y or N) ? " );
				cont = kbd.next(); 


			} while (cont.equalsIgnoreCase( "y" ) ); 

		}

		catch(FileNotFoundException e) 
		{
			System.out.println("There was an error opening one of the files.");
		}


	}

	// Picks a random number
	// input: The data from the text file
	// returns: The position
	public static int pickrandom(int count) 
	{
		Random generator = new Random();
		return generator.nextInt(count);
		//        return 1;
	}

	// Fills dictionary
	// input: The data from the text file
	// returns: The word from dictionary
	public static int filldictionary(Scanner input, String [] nums)
	{
		int pos;
		for(pos =0; pos<MAXSIZE && input.hasNextLine(); pos++)

		{
			nums[pos]=input.nextLine();

		}
		return pos;
	}

	// clear screen
	public static void clrScrn()
	{
		for(int blanks = 0; blanks <80; blanks++)
		{
			System.out.println();
		}
	}

	//get ast
	public static char[] getAsterisks(String wordchosen)
	{
		char [] asterisks = new char[wordchosen.length()];
		System.out.println("Word to Guess:");

		for(int pos=0; pos < wordchosen.length(); pos++)
		{
			asterisks[pos] = '*';
		}


		return asterisks;
	}

	// print ast
	public static void astPrnt(char [] asterisks ){
		for(int x=0; x<asterisks.length;x++)
		{
			System.out.print(Character.toUpperCase(asterisks[x]) + " ");

		}
		System.out.println();
	}

	// Picks char
	// returns: char
	public static char charVal(){

		String line=kbd.nextLine();
		while(line.equals(""))
		{
			line =kbd.nextLine();
		}
		line = line.trim();
		char ch = line.charAt(0);
		return ch;
	}

	// Picks a random number
	// input: The data from the text file
	// returns: The position
	public static void play( String wordchosen,String wordchosen1)
	{

		int attempts = MAXGUESSES;
		//String misses="";
		char[] nothing=getAsterisks(wordchosen1);
		for (int i = 0; i < wordchosen.length(); i++)
		{
			System.out.println(wordchosen);
		}
		System.out.println("\n"+"Guesses Remaining: "+ attempts);
		attempts--;
		System.out.println("Incorrect letters tried: "+attempts);
		char[] asterisks = getAsterisks(wordchosen1);
		System.out.println(asterisks);
	}


	// Picks a random number
	// input: The data from the text file
	// returns: The position
	public static void newAst(String word,char ch,char[] ast){
		//System.out.println("Word to guess");
		for (int i = 0; i < word.length(); i++)
		{
			if(ch == word.charAt(i)){
				ast[i] = ch;
			}
		}
	}

	// Picks a random number
	// input: The data from the text file
	// returns: The position
	public static String letCheck(String wordchosen, String wordchosen2, String guess){


		for(int i=0; i < wordchosen2.length();i++){

			char [] emptyArray;
			if (wordchosen2.length() != guess.length() ){

			}
		}
		return guess;

	}

}
