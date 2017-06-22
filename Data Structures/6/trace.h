/********************************************************
 * File: trace.h                                       *
 * Author: Jose Salinas                                 *
 ********************************************************/
struct Node;
typedef Node* Tree;
using namespace std;

//==============================================================
//                        trace
//==============================================================

void trace(string opt, int& v);

//==============================================================
//                    writeFrequency
//==============================================================
// writeFrequency prints out the frequency of a character at a
// location of array count[]
//==============================================================

void writeFrequency(int count[]);

//==============================================================
//                      writeChar
//==============================================================
// writeChar prints out the char at a value in array count[]
//==============================================================

void writeChar(unsigned char c);

//==============================================================
//                      printTree
//==============================================================
// printTree prints Tree t to the standard output.
//==============================================================

void printTree(const Tree t);

//==============================================================
//                      printTreeCodes
//==============================================================
// printTreeCodes prints out the tree codes from the location on
// string array[].
//==============================================================

void printTreeCodes(string array[]);
