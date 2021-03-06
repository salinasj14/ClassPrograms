// CSCI 3300
// Assignment: 5b
// Author:     Jose Salinas
// File:       pqueue.cpp
// Tab stops:  2
#include "pqueue.h"
struct PQCell
{
    PQItemType item;
    PQPriorityType priority;
    PQCell* next;
    
    PQCell(PQItemType it, PQPriorityType pri, PQCell* pq)
    {
        item = it;
        priority = pri;
        next = pq;
    }
};

bool isEmpty(const PriorityQueue& q)
{
    return q.ptr == NULL;
}

void insertCell(const PQItemType& x, PQPriorityType p, PQCell*& q)
{
    if(q == NULL || p < q -> priority)
    {
        q = new PQCell(x, p, q);
    }
    else
    {
        insertCell(x, p, q->next);
    }
}

void insert(const PQItemType& x, PQPriorityType p, PriorityQueue& q)
{
    insertCell(x, p, q.ptr);
}

void removeCell(PQItemType& x, PQPriorityType& p, PQCell*& q)
{
    x = q -> item;
    p = q -> priority;
    PQCell* n = q;
    q = q -> next;
    delete n;
}

void remove(PQItemType& x, PQPriorityType& p, PriorityQueue& q)
{
    removeCell(x,p,q.ptr);
}

