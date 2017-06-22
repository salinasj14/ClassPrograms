//  CSCI 3310 Program 5 Template
//  CountWords.java
//  Jose Salinas
//  This is a program that outputs the most occcurring word and how many
//  times the word occurred from starting letter a - z

import java.util.*;

public class CountWords
{
    public static final int TABLESIZE = 2003;// hash table size
    
    //storeWord stores String word into appropriate hashtable by value of first letter
    public static void storeWord(String word,ArrayList<Hashtable<String,WordItem>> arrayHash)
    {
        int location = (int) word.charAt(0) - (int) 'a';//location of hashtable in ArrayList
        if (arrayHash.get(location).containsKey(word))//word exists, update counter
        {
            arrayHash.get(location).get(word).incCount();
        }
        else//insert new word to Hashtable
        {
            WordItem item = new WordItem(word);
            arrayHash.get(location).put(word,item);
        }
    }
    
    //final results prints values from arrayList with the most occurring word
    public static void finalResults(ArrayList<Hashtable<String,WordItem>> arrayHash)
    {
        int totalUniqueWords = 0;
        for (int i =0; i < arrayHash.size(); i++)
        {
            Hashtable<String,WordItem> tempHash = arrayHash.get(i);
            ArrayList<WordItem> modified = new ArrayList<WordItem>(tempHash.values());
            char letter = (char)(i+97);
            int uniqueWords = modified.size();//how many unique words with letter a,b,c...z
            if(uniqueWords != 0)//if letter had an occuring word
            {
                WordItem tempItem = Collections.max(modified);//wordItem with most occurrence
                totalUniqueWords += uniqueWords;
                String word = tempItem.getWord();//most occurring word with letter a,b,c...z
                int count = tempItem.getCount();//number of most occurring word with letter a,b,c...z
                System.out.printf("Letter "+letter+"  %5d %15s %6d\n",uniqueWords,word,count);
            }
            else
            {
                System.out.printf("Letter "+letter+"      0\n");
            }
        }
        System.out.println("\nThere were a total of "+totalUniqueWords+" unique words.");
    }
    
    public static void main(String [] args)
    {
        if (args.length < 1)
        {
            System.out.print("ERROR: insufficient number of command line ");
            System.out.println("arguments. Program aborted.");
            return;
        }
        ArrayList<Hashtable<String,WordItem>> arrayHash = new ArrayList<Hashtable<String,WordItem>>();//list of hashtables
        for(int i = 0; i<26;i++)//adding a hashtable for every letter of alphabet
        {
            Hashtable<String,WordItem> hashTable = new Hashtable<String,WordItem>(TABLESIZE);
            arrayHash.add(hashTable);
        }
        TextIterator book = new TextIterator();
        book.readText(args[0]);
        while (book.hasNext())
        {
            storeWord(book.next(),arrayHash);
        }
        finalResults(arrayHash);
    }
}