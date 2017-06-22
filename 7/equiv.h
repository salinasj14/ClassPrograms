//CSCI 3300
//Assignment: 7
//Author:     Jose Salinas
//File:       equiv.h
//Tab stops:  2
#include <cctype>
#include <cstdio>
#include <string>

struct Equiv
{
    int* boss;
    int size;
    Equiv(int n)
    {
        size = n+1;
        boss = new int[size];
        for(int L = 0; L <= size ;L++)
        {
            boss[L]=0;
        }
    }
};

//==============================================================
//                      together
//==============================================================
// together returns true if x and y are currently in the same
// set in object e and false if they are in different sets.
//==============================================================

bool together(Equiv& E, int x, int y);

//==============================================================
//                      combine
//==============================================================
// combine makes x and y equivalent by combining the sets that
// contain them.
//==============================================================

void combine(Equiv& E, int x, int y);