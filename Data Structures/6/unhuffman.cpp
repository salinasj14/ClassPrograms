// CSCI 3300
// Assignment: 6b
// Author:     Jose Salinas
// File:       unhuffman.cpp
// Tab stops:  2
#include <cctype>
#include <cstdio>
#include <string>
#include <fstream>
#include "trace.h"
#include "binary.h"
#include "tree.h"
int tracer = 0;
using namespace std;
typedef Node* Tree;

//==============================================================
//                      readTree
//==============================================================
// readTree reads in binary file BFILE* bf and returns a variable
// of type Node*.
//==============================================================
Tree readTree(BFILE* bf)
{
    Tree T;
    if (readBit(bf) == 1)
    {
        T = new Node(readByte(bf));
    }
    else
    {
        Tree one = readTree(bf);
        Tree two = readTree(bf);
        T = new Node(one, two);
    }
    return T;
}

//==============================================================
//                      writeEncodedHelper
//==============================================================
// writeEncodedHelper writes the characters in BFILE* bf using
// codes from Tree T to FILE* f from the order of the bits in
// int c.
//==============================================================

void writeEncodedHelper(const Tree T, BFILE* bf,FILE* f,int& c)
{
    if (T->kind == leaf)
    {
        fprintf(f, "%c", T->ch);
    }
    else
    {
        if(c == 0)
        {
            writeEncodedHelper(T->left, bf,f,c = readBit(bf));
        }
        else
        {
            writeEncodedHelper(T->right, bf,f,c = readBit(bf));
        }
    }
}

//==============================================================
//                      writeEncoded
//==============================================================
// writeEncoded reads characters in Tree T and uncompresses
// BFILE* bf in to FILE* f.
//==============================================================

void writeEncoded(const Tree T, BFILE* bf, FILE* f)
{
    int c = readBit(bf);
    while(c != EOF)
    {
        writeEncodedHelper(T,bf,f,c);
    }
}

int main(int argc,char ** argv)
{
    int position = 0;
    string L = argv[1];
    if(L == "-t1" || L == "-t2")
    {
        trace(L, tracer);
        position++;
    }
    BFILE* bf = openBinaryFileRead(argv[position+1]);
    if(bf == NULL)
    {
        printf("Cannot open file %s for reading\n", argv[position+1]);
        return 1;
    }
    FILE* f = fopen(argv[position+2], "w");
    Tree T = readTree(bf);
    if(tracer == 1)
    {
        printTree(T);
        printf("\n");
    }
    writeEncoded(T,bf,f);
    closeBinaryFileRead(bf);
    fclose(f);
    return 0;
}
