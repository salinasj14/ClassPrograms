/********************************************************
* File: pqueue.h                                       *
* Author: Jose Salinas                                 *
********************************************************/
#include <cstdio>

typedef const char* PQItemType;
typedef double PQPriorityType;
typedef void (ItemPrinter)(const PQItemType&);
typedef void (PriorityPrinter)(PQPriorityType);

struct PQCell;
struct PriorityQueue
{
    PQCell* ptr;
    PriorityQueue()
    {
        ptr = NULL;
    }
};

/****************************************************************
 *                     isEmpty                                *
 ****************************************************************
 * return true if q is empty.                                 *
 ****************************************************************/

bool isEmpty(const PriorityQueue& q);

/****************************************************************
 *                     insert                                 *
 ****************************************************************
 *  Insert item x with priority p into q.                     *
 ****************************************************************/

void insert(const PQItemType& x, PQPriorityType p, PriorityQueue& q);

/****************************************************************
 *                     remove                                    *
 ****************************************************************
 *  Remove the item from q that has the smallest priority. Store *
 *  the removed item into variable x and store its priority into *
 *  variable p.                                                  *
 ****************************************************************/

void remove(PQItemType& x, PQPriorityType& p, PriorityQueue& q);

/****************************************************************
 *                     printPriorityQueue                         *
 ****************************************************************
 * Prints the contents of q, in order from lowest priority to     *
 * highest priority, to the standard output.                      *
 ****************************************************************/

void printPriorityQueue(const PriorityQueue& q, ItemPrinter pi, PriorityPrinter pp);

