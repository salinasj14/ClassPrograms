// Jose Salinas
// fall 2015
// Assignment #2
import java.util.*;

public class MemSim
{
    // remainingPages(prog_usage)calculates how many free pages are remaining
    public static int remainingPages(List prog_usage)
    {
        
        PageUsage one = new PageUsage();
        int totalPages = 0;
        int listSize = prog_usage.size();
        int count = 1;
        while(listSize > 0) // check which free pages are available
        {
            one = (PageUsage)prog_usage.get(count);
            totalPages = totalPages + (one.getEnd() - one.getStart()+1);
            count++;
            listSize--;
        }
        return totalPages;
    }
    
    // initiate(prog_id,size,prog_usage,bytes,progList)finds enough
    // memory to initiate program that was called
    public static void initiate(int prog_id, int size, List prog_usage,int[] bytes, List[] progList)
    {
        PageUsage one = new PageUsage();
        int listSize = prog_usage.size();
        int count = 1;
        boolean initiated = false;
        int remainingPages = remainingPages(prog_usage);
        int pageLength = 0;
        PageUsage two;
        if(bytes[prog_id] != -1) // program already exists
        {
            System.out.print("Error on initiate command: Program " + prog_id + " already exist\n");
        }
        else // program did not exist
        {
            int pagesNeeded = (size + MemParam.PAGE_SIZE - 1) / MemParam.PAGE_SIZE;
            if(pagesNeeded > remainingPages) // no space for program
            {
                System.out.println("Error on initiate command: insufficient space for Program" +prog_id+"\n");
            }
            else // space was available for program
            {
                while(listSize > 0)
                {
                    one = (PageUsage)prog_usage.get(count);
                    pageLength = (one.getEnd()-one.getStart()+1);
                    if(pagesNeeded > pageLength)
                    {
                        two = new PageUsage(one.getStart(), one.getEnd());
                        progList[prog_id].add(progList[prog_id].size()+1,two);
                        prog_usage.remove(count);
                        pagesNeeded = pagesNeeded - pageLength;
                    }
                    else
                    {
                        two = new PageUsage(one.getStart(),one.getStart()+pagesNeeded - 1);
                        progList[prog_id].add(progList[prog_id].size()+1,two);
                        if(pagesNeeded == pageLength)
                        {
                            prog_usage.remove(count);
                        }
                        else // pages needed was shorter than pages in freePage
                        {
                            PageUsage newPage = new PageUsage(one.getStart()+pagesNeeded,one.getEnd());
                            prog_usage.add(count, newPage);
                            prog_usage.remove(count+1);
                        }
                        bytes[prog_id] = size;
                        initiated = true;
                        break;
                    }
                    listSize--;
                }
                if(initiated)
                {
                    System.out.println("Program " + prog_id + " initiated, size = " + size);
                }
            }
        }
    }
    
    // termiate(prog_id,prog_usage,bytes,progList) terminates the progList and frees used pages
    public static void terminate(int prog_id, List prog_usage,int[] bytes, List[] progList)
    {
        PageUsage one = new PageUsage();
        int totalPages = 0;
        if(bytes[prog_id] == -1) // if program doesn't exist
        {
            System.out.println("Error on terminate command: Program " + prog_id + " does not exist\n");
        }
        else // if program existed
        {
            bytes[prog_id] = -1;
            one = (PageUsage)progList[prog_id].get(1);
            totalPages = totalPages + (one.getStart()+one.getEnd()+1);
            progList[prog_id].removeAll();
            prog_usage.add(1, one);
            System.out.println("Program " + prog_id + " terminated, "+ totalPages + " pages freed");
        }
    }
    
    // print(prog_id,prog_usage,bytes,progList) Prints the data in progList
    // for the program with prog_id.
    public static void print(int prog_id, List prog_usage,int[] bytes, List[] progList)
    {
        PageUsage one = new PageUsage();
        int listSize = prog_usage.size();
        int count = 1;
        int test = -1;
        if(prog_id == test)  //if prog_id is -1, print out free page list
        {
            System.out.println("Contents of Free Page List");
            System.out.println("Start Page     Free Page");
            while(listSize > 0)
            {
                one = (PageUsage)prog_usage.get(count);
                System.out.printf("%7d        %7d\n",one.getStart(), one.getEnd());
                count++;
                listSize--;
            }
            System.out.println();
        }
        else
        {
            if(bytes[prog_id] != test) // if program exists
            {
                listSize = progList[prog_id].size();
                System.out.println("Page usage for Program "+prog_id+" --- size = "+bytes[prog_id]+" bytes");
                System.out.println("Start Page     Free Page");
                while(listSize > 0)
                {
                    one = (PageUsage)progList[prog_id].get(count);
                    System.out.printf("%7d        %7d\n",one.getStart(),one.getEnd());
                    listSize--;
                    count++;
                }
                System.out.println();
            }
            else // if program does not exist
            {
                System.out.println("Error on print command:  Program "+prog_id +" does not exist.\n");
            }
        }
    }
    
    // exit(prog_usage,bytes) exits and gives a summary of total the number of
    // programs currently in memory and number of occupied pages
    public static void exit(List prog_usage, int[] bytes)
    {
        int totalPrograms = 0;
        int occupiedPages = 0;
        int freePages = 0;
        for(int i = 0; i < MemParam.NUM_PROGRAMS; i++)
        {
            if(bytes[i] != -1)
            {
                totalPrograms += 1;
            }
        }
        freePages = remainingPages(prog_usage);
        occupiedPages = MemParam.NUM_PAGES - freePages;
        System.out.println("SIMULATOR EXIT:  "+totalPrograms+" programs exist, occupying "+occupiedPages+" pages");
    }
    
    public static void main(String[] args)
    {
        List prog_usage = new List();
        int bytes[] = new int[MemParam.NUM_PROGRAMS];
        List progList[] = new List[MemParam.NUM_PROGRAMS];
        String opcode;
        int prog_id = 0;
        int size = 0;
        for(int k = 0; k < MemParam.NUM_PROGRAMS; k++)
        {
            bytes[k] = -1;
        }
        for(int i = 0; i < MemParam.NUM_PROGRAMS; i++)
        {
            progList[i] = new List<PageUsage>();
        }
        prog_usage.add(1,new PageUsage(0, MemParam.NUM_PAGES -1));
        Scanner keyboard = new Scanner(System.in);
        while(keyboard.hasNext())
        {
            opcode = keyboard.next();
            prog_id =keyboard.nextInt();
            size = keyboard.nextInt();
            if(opcode.equals("i"))
            {
                initiate(prog_id, size, prog_usage, bytes, progList);
            }
            else if(opcode.equals("t"))
            {
                terminate(prog_id, prog_usage, bytes, progList);
            }
            else if(opcode.equals("p"))
            {
                print(prog_id, prog_usage, bytes, progList);
            }
            else if(opcode.equals("x"))
            {
                exit(prog_usage, bytes);
            }
        }
    }
}