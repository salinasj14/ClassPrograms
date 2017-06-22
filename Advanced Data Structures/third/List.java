// ****************************************************
// Reference-based implementation of ADT list using arrays.
// Due to the limitations with array of generics, the
// "data type" for the list items is fixed to be of type
// PageUsage.  Any program using this class must specify
// <PageUsage> as the value for the type parameter.
// ****************************************************
public class List<T>
{
    // reference to linked list of items
    
    public static final int MAX_LIST = 20;
    public static final int NULL = -1;
    
    private PageUsage item[] = new PageUsage[MAX_LIST];  // data
    private int      next[] = new int[MAX_LIST];       // pointer to next item
    
    private int head;     // pointer to front of list
    private int free;     // pointer to front of free list
    private int numItems; // number of items in list
    
    // Constructor must initialize used list to empty and free list to
    // all available nodes.
    
    public List()
    {
        int index;
        for (index = 0; index < MAX_LIST-1; index++)
            next[index] = index + 1;
        
        next[MAX_LIST-1] = NULL;
        
        numItems = 0;
        head = NULL;
        free = 0;
    }  // end default constructor
    
    public void removeAll()
    {   // reinitialize all nodes to free
        int index;
        for (index = 0; index < MAX_LIST-1; index++)
            next[index] = index + 1;
        
        next[MAX_LIST-1] = NULL;
        
        numItems = 0;
        head = NULL;
        free = 0;
    } // end removeAll
    
    public boolean isEmpty()
    {  // ** YOU IMPLEMENT **
        return numItems == 0;
    }  // end isEmpty
    
    public int size()
    {  // ** YOU IMPLEMENT **
        return numItems;
    }  // end size
    
    private int find(int index)
    {  // ** YOU IMPLEMENT **
        // --------------------------------------------------
        // Locates a specified node in a linked list.
        // Precondition: index is the number of the desired
        // node. Assumes that 1 <= index <= numItems
        // Postcondition: Returns a reference to the desired
        // node.
        // --------------------------------------------------
        int curr = head;
        for(int i = 0; i <= index-1; i++)
        {
            curr = next[curr];
        }
        return curr;
    } // end find
    
    public PageUsage get(int index)
    { // ** YOU IMPLEMENT **
        PageUsage temp = new PageUsage();
        if(index >= 0 && index <= numItems)
        {
            temp = item[find(index-1)];
            return temp;
        }
        else
        {
            return null;
        }
    } // end get
    
    public void add(int index, PageUsage newItem)
    {  // ** YOU IMPLEMENT **
        if(index - 1 >= 0 && index - 1 <= numItems + 1)
        {
            int pos = free;
            if (index-1 == 0)
            {
                item[pos] = newItem;
                head  = pos;
                free = next[pos];
            }
            else
            {
                int temp  = find(index - 1);
                item[pos] = newItem;
                free = next[pos];
            }
            numItems+=1;
        }
    }  // end add
    
    public void remove(int index)
    {  // ** YOU IMPLEMENT **
        if ((index - 1 >= 0) && (index - 1 <= numItems))
        {
            int temp = free;
            if (index - 1 == 0)
            {
                if (head != 0)
                {
                    free = 0;
                    next[temp] = temp;
                }
                else
                {
                    free = head;
                    head = next[head];
                    next[temp] = temp;
                }
            }
            else
            {
                int pos = find(index - 2);
                int curr = next[pos];
                next[pos] = next[curr];
                next[curr] = temp;
            }
            numItems -= 1;
        }
        else
        {
            System.out.println("List index out of bounds on remove");
        }
    }   // end remove
} // end List