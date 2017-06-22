//CSCI 3300
//Assignment: 7
//Author:     Jose Salinas
//File:       equiv.cpp
//Tab stops:  2
extern const int tracer;
#include "equiv.h"

//==============================================================
//                      leader
//==============================================================

int leader(const Equiv& E, int x)
{
    int r = x;
    while(E.boss[r] != 0)
    {
        r = E.boss[r];
    }
    return r;
}

//==============================================================
//                      together
//==============================================================

bool together(Equiv& E, int x, int y)
{
    if(tracer ==1)
    {
        printf("\nThe parameters for together are %i and %i.\n",x,y);
    }
    if(leader(E,x) == leader(E,y))
    {
        if(tracer ==1)
        {
            printf("%i and %i are in the same set.\n",x,y);
        }
        return true;
    }
    else
    {
        if(tracer ==1)
        {
            printf("%i and %i are not in the same set.\n",x,y);
        }
        return false;
    }
}

//==============================================================
//                      combine
//==============================================================

void combine(Equiv& E, int x, int y)
{
    if(tracer ==1)
    {
        printf("\nThe parameters for combine are %i and %i.\n",x,y);
    }
    if(!together(E,x,y))
    {
        E.boss[leader(E,x)] = y;
    }
    if(tracer==1)
    {
        for(int L = 1; L < E.size-1;L++)
        {
            printf("%i is the boss of %i.\n",E.boss[L],L);
        }
    }
}