//CSCI 3300
//Assignment: 7
//Author:     Jose Salinas
//File:       graph.cpp
//Tab stops:  2

#include <cstdio>
#include <cstdlib>
#include "graph.h"
#include "equiv.h"
extern const int tracer;
typedef int (*QSORT_COMPARE_TYPE)(const void*, const void*);

//=============================================================
//                     readGraph
//=============================================================

void readGraph(Graph& g)
{
    int n = -1;
    scanf("%i", &g.numVert);
    int k = 0;
    while(n != 0)
    {
        scanf("%i", &n);
        if(n != 0)
        {
            g.arrayEdges[k].vertOne = n;
            scanf("%i", &g.arrayEdges[k].vertTwo);
            scanf("%i", &g.arrayEdges[k].weight);
            k++;
        }
        g.numEdges = k;
    }
}

//=============================================================
//                        printGraph
//=============================================================

void printGraph(Graph& g)
{
    printf("vertices      weight\n");
    for(int L = 0; L < g.numEdges; L++)
    {
        printf("%i    %i ", g.arrayEdges[L].vertOne,g.arrayEdges[L].vertTwo);
        printf("\t         %3i \n", g.arrayEdges[L].weight);
    }
}

int compareEdges(const Edge* A, const Edge* B)
{
    return A->weight - B->weight;
}

//=============================================================
//                        kruskal
//=============================================================

Graph kruskal(Graph& g)
{
    Graph N;
    Equiv e(g.numEdges);
    qsort((void*) g.arrayEdges, g.numEdges, sizeof(Edge), (QSORT_COMPARE_TYPE) compareEdges);
    if(tracer==2)
    {
        printf("\nThe array of edges after sorting:\n");
        printGraph(g);
    }
    for(int i = 0; i < g.numEdges; i++)
    {
        if(tracer==2)
        {
            printf("\nThe edges %i and %i are being considered.\n",g.arrayEdges[i].vertOne,g.arrayEdges[i].vertTwo);
        }
        if(!together(e, g.arrayEdges[i].vertOne, g.arrayEdges[i].vertTwo))
        {
            if(tracer==2)
            {
                printf("The edges %i and %i were combined.\n",g.arrayEdges[i].vertOne,g.arrayEdges[i].vertTwo);
            }
            combine(e, g.arrayEdges[i].vertOne, g.arrayEdges[i].vertTwo);
            N.arrayEdges[N.numEdges++] = g.arrayEdges[i];
        }
        else
        {
            if(tracer==2)
            {
                printf("The edges %i and %i were not combined.\n",g.arrayEdges[i].vertOne,g.arrayEdges[i].vertTwo);
            }
        }
    }
    return N;
}

//=============================================================
//                        weight
//=============================================================

int weight(Graph& G)
{
    int M = 0;
    for(int L = 0; L < G.numEdges; L++)
    {
        M += G.arrayEdges[L].weight;
    }
    return M;
}