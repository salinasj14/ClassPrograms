//CSCI 3300
//Assignment: 5b
//Author:     Jose Salinas
//File:       graph.cpp
//Tab stops:  2

// graph.cpp is a discrete event simulation
// that prints a graph and the shortest path from start to
// end to the standard output.
#include <cstdio>
#include <iostream>
#include <math.h>
#include <string>
#include "event.h"
#include "pqueue.h"
using namespace std;
int tracer1 = 0;

struct Edge
{
    Edge* next;
    int adjcVertex;
    double weight;
    Edge(int V, double w, Edge* n)
    {
        adjcVertex = V;
        weight = w;
        next = n;
    }
};

struct Vertex
{
    int signals;
    double distance;
    Edge* cell;
    Vertex()
    {
        signals = -1;
        distance = 0.0;
        cell = NULL;
    }
};

struct Graph
{
    int start, end;
    int numVertices;
    Vertex* info;
    Graph(int g)
    {
        numVertices = g;
        info = new Vertex[g+1];
        for (int L = 1; L <= g; L++)
        {
            info[L].signals = -1;
        }
    }
};

//=============================================================
//                     readGraph
//=============================================================
// readGraph reads information about a weighted graph
// from the standard input.
//=============================================================

Graph readGraph()
{
    int n = -1;
    int v, numVertices;
    double weight;
    scanf("%i", &numVertices);
    Graph g(numVertices);
    while(n != 0)
    {
        scanf("%i", &n);
        if(n != 0)
        {
            scanf("%i", &v);
            scanf("%lf", &weight);
            g.info[n].cell = new Edge(v,weight, g.info[n].cell);
            g.info[v].cell = new Edge(n,weight, g.info[v].cell);
        }
    }
    return g;
}

//=============================================================
//                      printGraphHelper
//=============================================================
// printGraphHelper is a helper function for printGraph which
// outputs the distance and weight from Edge l to vertex n.
//=============================================================

void printGraphHelper(int n, Edge* L)
{
    if(L != NULL && L->adjcVertex > n)
    {
        printf("(%i,%i)", n, L-> adjcVertex);
        printf(" weight %.3f\n", L-> weight);
        printGraphHelper(n, L->next);
    }
}

//=============================================================
//                        printGraph
//=============================================================
// printGraph outputs the number of vertices n and their
// connecting edges and weight from graph g
//=============================================================

void printGraph(int n, const Graph g)
{
    printf("\nThere are %i vertices.\n", n);
    printf("The edges are as follows.\n\n");
    for(int L = 1; L < n; L++)
    {
        printGraphHelper(L , g.info[L].cell);
    }
}

//=============================================================
//                        sEvents
//=============================================================
// sEvents is a helper function for mainEvents which stores
// vertex v into PriorityQueue queue and updates Graph g if a
// signal has not arrived.
//=============================================================

void sEvents(PriorityQueue& queue, Graph g, int v)
{
    for(Edge* cellVal = g.info[v].cell; cellVal != NULL; cellVal = cellVal -> next)
    {
        if(g.info[cellVal->adjcVertex].signals == -1)
        {
            Event* e = new Event(v, cellVal->adjcVertex, cellVal->weight + g.info[v].distance);
            insert(e, cellVal->weight + g.info[v].distance ,queue);
        }
    }
}

//=============================================================
//                        mainEvents
//=============================================================
// mainEvents checks the PriorityQueue queue, updates Graph g
// if a signal has not yet arrived to vertex v.
//=============================================================

void mainEvents(PriorityQueue& queue, Graph g, int v)
{
    sEvents(queue, g, v);
    Event* n;
    PQPriorityType t;
    remove(n, t, queue);
    
    if(g.info[n->vertexTwo].signals == -1)
    {
        g.info[n->vertexTwo].signals = n->vertexOne;
        g.info[n->vertexTwo].distance = t;
        
        if(tracer1 > 0)
        {
            printf("\nThe path %i to %i is %.3f \n",n->vertexOne, v,t);
        }
        
    }
    if(g.end != v)
    {
        mainEvents(queue,g,n->vertexTwo);
    }
}

//=============================================================
//						            dijkstras
//=============================================================
// dijkstras implements dijkstras algorithm on Graph g.
//=============================================================

void dijkstras(Graph& g)
{
    PriorityQueue queue;
    Event* firstEvent = new Event(0,g.start,0.0);
    insert(firstEvent,0.0, queue);
    mainEvents(queue, g,g.start);
}

//=============================================================
//						          shortestPath
//=============================================================
// * Requires dijkstras to have been run *
// shortestPath outputs the shortest path from start to finish
// with updated Graph g.
//=============================================================

void shortestPath(int start, int finish, Graph& g)
{
    if (start == finish)
    {
        printf("%i", start);
    }
    else
    {
        shortestPath(start, g.info[finish].signals,g);
        printf(" -> %i", finish);
    }
}

//=============================================================
//						            setTrace
//=============================================================

void setTrace(const char* arg)
{
    string argument = arg;
    if(argument == "-t")
    {
        tracer1 = 1;
    }
}

int main(int argc, char** argv)
{
    for(int k = 0; k < argc; k++)
    {
        setTrace(argv[k]);
    }
    
    Graph g = readGraph();
    scanf("%i", &g.start);
    scanf("%i", &g.end);
    printGraph(g.numVertices, g);
    dijkstras(g);
    printf("\nThe shortest path from %i to %i has length %.3f and is\n",g.start, g.end, g.info[g.end].distance);
    shortestPath(g.start, g.end, g);
    printf("\n");
    return 0;
}
