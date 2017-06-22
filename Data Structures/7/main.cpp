//CSCI 3300
//Assignment: 7
//Author:     Jose Salinas
//File:       main.cpp
//Tab stops:  2

#include <cstdio>
#include <iostream>
#include <math.h>
#include <string>
#include "equiv.h"
#include "graph.h"
using namespace std;
int tracer = 0;

void setTrace(const char* arg)
{
    string argument = arg;
    if(argument == "-te")
    {
        tracer = 1;
    }
    else if(argument == "-tm")
    {
        tracer = 2;
    }
}


int main(int argc, char** argv)
{
    for(int k = 0; k < argc; k++)
    {
        setTrace(argv[k]);
    }
    Graph g;
    readGraph(g);
    printf("\nThere are %i vertices", g.numVert);
    printf(", and its edges are as follows.\n\n");
    printGraph(g);
    Graph Q = kruskal(g);
    printf("\nA minimal spanning tree uses the following edges.\n\n");
    printGraph(Q);
    printf("\nThe total weight of the spanning tree is ");
    printf("%i.\n",weight(Q));
    return 0;
}
