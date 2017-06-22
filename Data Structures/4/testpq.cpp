// Author:     Jose Salinas
// File:       testpq.cpp
// Tab stops:  2

#include <cctype>
#include <cstdio>
#include "pqueue.h"
using namespace std;

void printString(const PQItemType& s)
{
    printf("%s: ", s);
}

void printDouble(PQPriorityType x)
{
    printf("%lf\n", x);
}

int main(int argc, char** argv)
{
    PriorityQueue q;
    PQItemType A = "My name is";
    PQItemType B = "Jose Salinas";
    PQItemType C = "How are you today?";
    PQItemType D = "I am good.";
    PQPriorityType p1 = 1.0;
    PQPriorityType p2 = 1.5;
    PQPriorityType p3 = 2.0;
    PQPriorityType p4 = 2.5;
    insert(A, p1, q);
    insert(B, p2, q);
    insert(C, p3, q);
    insert(D, p4, q);
    if(isEmpty(q))
    {
        printf("This set is empty");
    }
    else
    {
        printf("Now removing the items from the Priority Queue.\n");
        remove(A, p1, q);
        remove(B, p2, q);
        remove(C, p3, q);
        remove(D, p4, q);
        if(isEmpty(q))
        {
            printf("This set is now empty.\n");
        }
        else
        {
            printPriorityQueue(q, printString, printDouble);
        }
    }
    return 0;
}
