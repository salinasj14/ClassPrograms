// CSCI 3300
// Assignment: 6b
// Author:     Jose Salinas
// File:       huffman.cpp
// Tab stops:  2
#include <cctype>
#include <cstdio>
#include <string>
#include <fstream>
#include "tree.h"
#include "pqueue.h"
#include "trace.h"
#include "binary.h"
#define DEBUGGER
int tracer = 0;
using namespace std;
typedef Node* Tree;

//==============================================================
//                      getFrequency
//==============================================================
// getFrequency counts the frequency of a char from FILE* file
// and stores it in array count[].
//==============================================================
void getFrequency(int count[], FILE* file)
{
    int c = getc(file);
    while(c != EOF)
    {
        count[c]+=1;
        c = getc(file);
    }
}

//==============================================================
//                      createPriority
//==============================================================
// createPriority inserts a character and its priority from
// array count[] into the queue.
//==============================================================
void createPriority(PriorityQueue& queue, int count[])
{
    Tree t;
    for(int k = 0; k < 256; k++)
    {
        if(count[k] > 0)
        {
            t = new Node(k);
            insert(t, count[k], queue);
        }
    }
}

//==============================================================
//                      createTree
//==============================================================
// returns a tree from PriorityQueue q
//==============================================================
Tree createTree(PriorityQueue& q)
{
    Tree left;
    Tree right;
    int priorityOne, priorityTwo;
    remove(left, priorityOne,q);
    if (isEmpty(q))
    {
        return left;
    }
    remove(right, priorityTwo,q);
    insert(new Node(left,right),priorityOne+priorityTwo,q);
    return createTree(q);
}

//==============================================================
//                      codeFromTree
//==============================================================
// traverses the tree t and stores the code for each character
// into the string array [] with variable codeSoFar being the
// path from the root of tree t to subtree t.
//==============================================================

void codeFromTree(string array[], Tree t,string codeSoFar)
{
    if (t->kind == leaf)
    {
        int s = t->ch;
        array[s] = codeSoFar;
        
    }
    else
    {
        codeFromTree(array, t->left,codeSoFar+"0");
        codeFromTree(array, t->right, codeSoFar+"1");
    }
}

//==============================================================
//                      writeTreeBinary
//==============================================================
// writeTreeBinary writes Tree t into binary file bf.
//==============================================================

void writeTreeBinary(Tree t, BFILE* bf)
{
    if (t->kind == leaf)
    {
        
        writeBit(bf, 1);
        writeByte(bf, t->ch);
    }
    else
    {
        writeBit(bf, 0);
        writeTreeBinary(t->left, bf);
        writeTreeBinary(t->right, bf);
    }
}

//==============================================================
//                      writeCompressed
//==============================================================
// writeCompressed writes the characters in string code to
// FILE* bf.
//==============================================================

void writeCompressed(BFILE* bf, string code)
{
    for(int C = 0; C < code.length(); C++)
    {
        writeBit(bf, code[C]-'0');
    }
}

//==============================================================
//                      writeEncoded
//==============================================================
// writeEncoded reads FILE* f and writes the codes from array[]
// to BFILE* bf.
//==============================================================

void writeEncoded(FILE* f,string* array,BFILE* bf)
{
    int c= getc(f);
    while(c!= EOF)
    {
        writeCompressed(bf, array[c]);
        c = getc(f);
    }
}

int main(int argc, char** argv)
{
    int position = 0;
    string L = argv[1];
    if(L == "-t1" || L == "-t2")
    {
        trace(L, tracer);
        position++;
    }
    int count[256] = { 0 };
    string array[256] = {""};
    string codeSoFar = "";
    FILE* f = fopen(argv[position+1], "r");
    
    if(f == NULL)
    {
        printf("Cannot open file %s for reading\n", argv[position+1]);
        return 1;
    }
    
    getFrequency(count ,f);
    PriorityQueue queue;
    createPriority(queue, count);
    Tree t = createTree(queue);
    codeFromTree(array, t, codeSoFar);
    
    if(tracer == 1)
    {
        writeFrequency(count);
        printf("\nThe Huffman tree is as follows: ");
        printTree(t);
        printTreeCodes(array);
    }
    f = fopen(argv[position+1], "r");
    BFILE* bf = openBinaryFileWrite(argv[position+2]);
    writeTreeBinary(t,bf);
    writeEncoded(f, array,bf);
    closeBinaryFileWrite(bf);
    fclose(f);
    return 0;
}
