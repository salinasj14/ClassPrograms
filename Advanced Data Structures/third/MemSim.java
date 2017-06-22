// Jose Salinas
// fall 2015
// Assignment #3
import java.util.*;

public class MemSim
{
    // remainingPages(prog_usage)calculates how many free pages are remaining
    public static int remainingPages(List<PageUsage> prog_usage)
    {
        
        PageUsage temp = new PageUsage();
        int totalPages = 0;
        int listSize = prog_usage.size();
        int count = 1;
        while(listSize > 0) // check which free pages are available
        {
            temp = prog_usage.get(count);
            totalPages = totalPages + (temp.getEnd() - temp.getStart()+1);
            count++;
            listSize--;
        }
        return totalPages;
    }
    
    public static void grow(int prog_id,int size, List<PageUsage> prog_usage, ProgInfo[] progList)
    {
        PageUsage pages = new PageUsage();
        PageUsage curr = new PageUsage();
        PageUsage table = new PageUsage();
        PageUsage growing = new PageUsage();
        int totalSize = progList[prog_id].bytes + size;
        int count = 1;
        int pagesNeeded = (size + MemParam.PAGE_SIZE - 1) / MemParam.PAGE_SIZE;
        int remainingPages = remainingPages(prog_usage);
        int pageLength = (pages.getEnd()-pages.getStart()+1);
        pages = progList[prog_id].prog_usage.get(count);
        if(progList[prog_id].bytes == -1) // if program doesn't exist
        {
            System.out.println("Error on grow command: Program " + prog_id + " does not exist\n");
        }
        else if(pagesNeeded > remainingPages) // no space for program
        {
            System.out.println("Error on grow command: insufficient space for Program " +prog_id+"\n");
        }
        else if(totalSize > MemParam.MAX_SIZE) // no space for program
        {
            System.out.println("ERROR on grow command: MAX SIZE exceeded for Program " +prog_id+"\n");
        }
        else
        {
            int temp = pagesNeeded+pages.getEnd();
            curr = new PageUsage(pages.getStart(), temp);
            table = prog_usage.get(1);
            progList[prog_id].prog_usage.removeAll();
            progList[prog_id].prog_usage.add(progList[prog_id].prog_usage.size()+1,curr);
            growing = new PageUsage(table.getStart()+pagesNeeded,table.getEnd());
            prog_usage.add(count, growing);
            prog_usage.remove(count+1);
            progList[prog_id].bytes = totalSize;
            System.out.println("Program "+ prog_id +" increased by "+size+" bytes, new size = "+totalSize);
        }
    }
    
    public static void shrink(int prog_id,int size, List<PageUsage> prog_usage, ProgInfo[] progList)
    {
        PageUsage pages = new PageUsage();
        PageUsage curr = new PageUsage();
        PageUsage table = new PageUsage();
        PageUsage shrinking = new PageUsage();
        int count = 1;
        int pagesNeeded = (size + MemParam.PAGE_SIZE - 1) / MemParam.PAGE_SIZE;
        pages = progList[prog_id].prog_usage.get(count);
        if(progList[prog_id].bytes == -1) // if program doesn't exist
        {
            System.out.println("ERROR on shrink command: program "+ prog_id +" does not exist.\n");
        }
        else if(size > progList[prog_id].bytes) // size bigger than bytes
        {
            System.out.println("ERROR on shrink command: insufficient allocation for Program "+prog_id+".\n");
        }
        else
        {
            curr = new PageUsage(pages.getStart(),pages.getEnd()-pagesNeeded+1);
            table = prog_usage.get(1);
            progList[prog_id].prog_usage.removeAll();
            progList[prog_id].prog_usage.add(progList[prog_id].prog_usage.size()+1,curr);
            shrinking = new PageUsage(table.getStart()-pagesNeeded,table.getEnd());
            prog_usage.add(count, shrinking);
            prog_usage.remove(count+1);
            int totalSize = progList[prog_id].bytes - size;
            progList[prog_id].bytes = totalSize;
            System.out.println("Program "+ prog_id +" decreased by "+size+" bytes, new size = "+totalSize);
        }
    }
    
    // initiate(prog_id,size,prog_usage,bytes,progList)finds enough
    // memory to initiate program that was called
    public static void initiate(int prog_id, int size, List<PageUsage> prog_usage, ProgInfo[] progList)
    {
        PageUsage temp = new PageUsage();
        int listSize = prog_usage.size();
        int count = 1;
        boolean initiated = false;
        int remainingPages = remainingPages(prog_usage);
        int pageLength = 0;
        PageUsage currently;
        if(progList[prog_id].bytes != -1) // program already exists
        {
            System.out.print("Error on initiate command: Program " + prog_id + " already exist\n");
        }
        else // program did not exist
        {
            int pagesNeeded = (size + MemParam.PAGE_SIZE - 1) / MemParam.PAGE_SIZE;
            if(pagesNeeded > remainingPages) // no space for program
            {
                System.out.println("Error on initiate command: insufficient space for Program " +prog_id+"\n");
            }
            else // space was available for program
            {
                while(listSize > 0)
                {
                    temp = prog_usage.get(count);
                    pageLength = (temp.getEnd()-temp.getStart()+1);
                    if(pagesNeeded > pageLength)
                    {
                        currently = new PageUsage(temp.getStart(), temp.getEnd());
                        progList[prog_id].prog_usage.add(progList[prog_id].prog_usage.size()+1,currently);
                        prog_usage.remove(count);
                        pagesNeeded = pagesNeeded - pageLength;
                    }
                    else
                    {
                        currently = new PageUsage(temp.getStart(),temp.getStart()+pagesNeeded - 1);
                        progList[prog_id].prog_usage.add(progList[prog_id].prog_usage.size()+1,currently);
                        if(pagesNeeded == pageLength)
                        {
                            prog_usage.remove(count);
                        }
                        else // pages needed was shorter than pages in freePage
                        {
                            PageUsage newPage = new PageUsage(temp.getStart()+pagesNeeded,temp.getEnd());
                            prog_usage.add(count, newPage);
                            prog_usage.remove(count+1);
                        }
                        progList[prog_id].bytes = size;
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
    public static void terminate(int prog_id, List<PageUsage> prog_usage, ProgInfo[] progList)
    {
        PageUsage tempPage = new PageUsage();
        int totalPages = 0;
        if(progList[prog_id].bytes == -1) // if program doesn't exist
        {
            System.out.println("Error on terminate command: Program " + prog_id + " does not exist\n");
        }
        else
        {
            progList[prog_id].bytes = -1;
            tempPage = progList[prog_id].prog_usage.get(1);
            totalPages = totalPages + (tempPage.getStart()+tempPage.getEnd()+1);
            progList[prog_id].prog_usage.removeAll();
            prog_usage.add(1, tempPage);
            System.out.println("Program " + prog_id + " terminated, "+ totalPages + " pages freed");
        }
    }
    
    // print(prog_id,prog_usage,bytes,progList) Prints the data in progList
    // for the program with prog_id.
    public static void print(int prog_id, List<PageUsage> prog_usage, ProgInfo[] progList)
    {
        PageUsage temp = new PageUsage();
        int listSize = prog_usage.size();
        int count = 1;
        int test = -1;
        if(prog_id == test)  //if prog_id is -1, print out free page list
        {
            System.out.println("Contents of Free Page List");
            System.out.println("Start Page     Free Page");
            while(listSize > 0)
            {
                temp = prog_usage.get(count);
                System.out.printf("%7d        %7d\n",temp.getStart(), temp.getEnd());
                count++;
                listSize--;
            }
            System.out.println();
        }
        else
        {
            if(progList[prog_id].bytes != test) // if program exists
            {
                listSize = progList[prog_id].prog_usage.size();
                System.out.println("Page usage for Program " + prog_id + " --- size = " + progList[prog_id].bytes + " bytes");
                System.out.println("Start Page     Free Page");
                while(listSize > 0)
                {
                    temp = progList[prog_id].prog_usage.get(count);
                    System.out.printf("%7d        %7d\n",temp.getStart(),temp.getEnd());
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
    public static void exit(List<PageUsage> prog_usage, ProgInfo[] progList)
    {
        int totalPrograms = 0;
        int occupiedPages = 0;
        int freePages = 0;
        for(int i = 0; i < MemParam.NUM_PROGRAMS-1; i++)
        {
            if(progList[i].bytes != -1)
            {
                totalPrograms += 1;
            }
        }
        freePages = remainingPages(prog_usage);
        occupiedPages = MemParam.NUM_PAGES - freePages;
        System.out.println("SIMULATOR EXIT:  "+totalPrograms+" programs exist, occupying "+occupiedPages+" pages");
        System.exit(0);
    }
    
    public static void main(String[] args)
    {
        List<PageUsage> prog_usage = new List<PageUsage>();
        ProgInfo[] progList = new ProgInfo[MemParam.NUM_PROGRAMS - 1];
        String opcode;
        int prog_id = 0;
        int size = 0;
        for(int k = 0; k < MemParam.NUM_PROGRAMS - 1; k++)
        {
            progList[k] = new ProgInfo();
        }
        for(int i = 0; i < MemParam.NUM_PROGRAMS - 1; i++)
        {
            progList[i].bytes = -1;
            progList[i].prog_usage = new List<PageUsage>();
        }
        prog_usage.add(1,new PageUsage(0, MemParam.NUM_PAGES - 1));
        Scanner keyboard = new Scanner(System.in);
        while(keyboard.hasNext())
        {
            opcode = keyboard.next();
            prog_id =keyboard.nextInt();
            size = keyboard.nextInt();
            if(opcode.equals("i"))
            {
                initiate(prog_id, size, prog_usage, progList);
            }
            else if(opcode.equals("t"))
            {
                terminate(prog_id, prog_usage, progList);
            }
            else if(opcode.equals("p"))
            {
                print(prog_id, prog_usage, progList);
            }
            else if(opcode.equals("x"))
            {
                exit(prog_usage, progList);
            }
            else if(opcode.equals("g"))
            {
                grow(prog_id,size, prog_usage, progList);
            }
            else if(opcode.equals("s"))
            {
                shrink(prog_id,size, prog_usage, progList);
            }
        }
    }
}