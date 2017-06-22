// CSCI 3300
// Assignment: 6b
// Author:     Jose Salinas
// File:       trace.cpp
// Tab stops:  2

#include <cctype>
#include <cstdio>
#include <string>
#include "trace.h"
#include "tree.h"
using namespace std;
extern const int tracer;

//==============================================================
//                          trace
//==============================================================

void trace(string opt, int& v)
{
    if(opt == "-t1")
    {
        v = 1;
    }
    else if(opt == "-t2")
    {
        v = 2;
    }
}

//==============================================================
//                      writeFrequency
//==============================================================

void writeFrequency(int count[])
{
    printf("The character frequencies are:\n\n");
    for(int k=0; k<256; k++)
    {
        if(count[k] > 0)
        {
            writeChar(k);
            printf(" %d\n", count[k]);
        }
    }
}

//==============================================================
//                      writeChar
//==============================================================

void writeChar(unsigned char c)
{
    if (c == '\t')
    {
        printf("(tab)");
    }
    else if(c == '\n')
    {
        printf("(newline)");
    }
    else if(c == ' ')
    {
        printf("(space)");
    }
    else
    {
        printf("%c", c);
    }
}

//==============================================================
//                      printTree
//==============================================================

void printTree(const Tree t)
{
    if(t->kind == leaf)
    {
        writeChar(t->ch);
    }
    else
    {
        printf("(");
        printTree(t->left);
        printf(",");
        printTree(t->right);
        printf(")");
    }
}

//==============================================================
//                      printTreeCodes
//==============================================================

void printTreeCodes(string array[])
{
    printf("\n\nA Huffman code is as follows.\n\n");
    for(int k=0; k<256; k++)
    {
        if(array[k] != "")
        {
            writeChar(k);
            printf(" %s\n", array[k].c_str());
        }
    }
}
