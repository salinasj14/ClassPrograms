/********************************************************
 * File: pqueue.h                                       *
 * Author: Jose Salinas                                 *
 ********************************************************/
#include <cstdio>
struct Node;
typedef Node* PQItemType;
typedef int PQPriorityType;
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
