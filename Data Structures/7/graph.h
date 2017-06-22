//CSCI 3300
//Assignment: 7
//Author:     Jose Salinas
//File:       graph.h
//Tab stops:  2

const int maxEdges = 100;

/**********************************************
 *                 Edge                       *
 **********************************************
 * Edge is a structure that holds two vertices*
 * and the weight.                            *
 **********************************************/

struct Edge
{
    int vertOne;
    int vertTwo;
    int weight;
};

/**********************************************
 *                 Graph                      *
 **********************************************
 * Graph is a structure that holds the number *
 * of vertices, the number of edges, and a   *
 * pointer to the array of edges.            *
 **********************************************/

struct Graph
{
    int numVert;
    int numEdges;
    Edge* arrayEdges;
    Graph()
    {
        numEdges = 0;
        arrayEdges = new Edge[maxEdges];
    }
};

//=============================================================
//                     readGraph
//=============================================================
// readGraph reads information about a weighted graph
// from the standard input.
//=============================================================

void readGraph(Graph& g);

//=============================================================
//                        printGraph
//=============================================================
// printGraph outputs the number of vertices n and their
// connecting edges and weight from graph g.
//=============================================================


void printGraph(Graph& g);

//=============================================================
//                        kruskal
//=============================================================
// kruskal computes the minimal spanning tree using Kruskal's
// algorithm, it takes graph g as a parameter and yields and
// yields a new graph.
//=============================================================

Graph kruskal(Graph& g);

//=============================================================
//                        weight
//=============================================================
// weight outputs the weight of the minimal spanning tree from
// graph g.
//=============================================================

int weight(Graph& g);

