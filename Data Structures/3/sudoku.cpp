// CSCI 3300
// Assignment: 3a
// Author:     Jose Salinas
// File:       sudoku.cpp
// Tab stops:  2

// sudoku reads in a sudoku puzzle from user input
// and generates a solution in it's solveable or unsolveable
// if it's not.

#include <cctype>
#include <cstdio>
#include <string>
#include "intset.h"
using namespace std;
int tracer1 = 0;
int tracer2 = 0;
typedef SetOfSmallInts** Puzzle;
typedef SetOfSmallInts** PuzzleSection;
#define DEBUGGER
enum SolutionStatus {solved, unsolvable, working};

SolutionStatus solver(Puzzle p);

//==============================================================
//                      newPuzzle
//==============================================================
// newPuzzle() returns a newly allocated puzzle.
//==============================================================

Puzzle newPuzzle()
{
    Puzzle p = new SetOfSmallInts*[9];
    
    for(int i = 0; i < 9; i++)
    {
        p[i] = new SetOfSmallInts[9];
    }
    return p;
}

//==============================================================
//                      deletePuzzle
//==============================================================
// deletePuzzle(p) deallocates puzzle p that was allocated
// using newPuzzle.
//==============================================================

void deletePuzzle(Puzzle p)
{
    for(int i = 0; i < 9; i++)
    {
        delete[] p[i];
    }
    delete[] p;
}

//==============================================================
//                      newPuzzleSection
//==============================================================
// newPuzzleSection() returns a newly allocates PuzzleSection.
//==============================================================

PuzzleSection newPuzzleSection()
{
    return new SetOfSmallInts*[9];
}

//==============================================================
//                      deletePuzzleSection
//==============================================================
// deletePuzzleSection(s) deallocates puzzle section s that
// was allocated by newPuzzleSection.
//==============================================================

void deletePuzzleSection(PuzzleSection s)
{
    delete[] s;
}

//==============================================================
//                      copySetArray
//==============================================================
// Parameters p and q are arrays of 9 sets.  Copy array p into
// array q.
//==============================================================

void copySetArray(SetOfSmallInts* q, SetOfSmallInts* p)
{
    for(int i = 0; i < 9; i++)
    {
        q[i] = p[i];
    }
}

//==============================================================
//                      copyPuzzle
//==============================================================
// Copy puzzle p into q.  For example, if p is a puzzle, then
//    Puzzle q = newPuzzle();
//    copyPuzzle(q, p);
// stores a copy of puzzle p into q.  (Only allocate q if it
// was not already allocated.)
//==============================================================

void copyPuzzle(Puzzle q, Puzzle p)
{
    for(int i = 0; i < 9; i++)
    {
        copySetArray(q[i], p[i]);
    }
}

//==============================================================
//                      getRow
//==============================================================
// Store the i-th row of puzzle p into puzzle section S.
// The rows are numbered from 0 to 8.
//
// After doing this, the k-th set in row i is *(S[k]).
// The cells in the row are numbered 0,1,...,8.
//==============================================================

void getRow(PuzzleSection S, Puzzle p, int i)
{
    for(int j = 0; j < 9; j++)
    {
        S[j] = &(p[i][j]);
    }
}


//==============================================================
//                      getColumn
//==============================================================
// Store the j-th column of puzzle p into puzzle section S.
// The columns are numbered from 0 to 8.
//
// After doing this, the k-th set in column j is
// *(S[i]).  The cells in the column are numbered 0,1,...,8.
//==============================================================

void getColumn(PuzzleSection S, Puzzle p, int j)
{
    for(int i = 0; i < 9; i++)
    {
        S[i] = &(p[i][j]);
    }
}

//==============================================================
//                      getSquare
//==============================================================
// Store the k-th square of puzzle p into puzzle section S.
// The squares are numbered as follows.
//           0 1 2
//           3 4 5
//           6 7 8
// For example, square 4 is the middle square in the puzzle.
//
// After doing getSquare, the i-th set in the square is *(S[i]).
// The cells in the square are numbered 0,1,...,8, in the same
// pattern shown above for the squares themselves.
// For example *(R[3]) is the first position in the second row
// of the square.
//==============================================================

void getSquare(PuzzleSection S, Puzzle p, int k)
{
    for(int i = 0; i < 9; i++)
    {
        S[i] = &(p[k - k%3 + i/3][3*(k%3) + i%3]);
    }
}

//==============================================================
//                   readRow
//==============================================================
// A helper function for readPuzzle which reads columns(i) from
// Puzzle(p).
//==============================================================

void readRow(Puzzle p, int i)
{
    char c;
    for(int j = 0; j < 9; j++)
    {
        scanf(" %c", &c);
        if(c == '-' || c =='*')
        {
            p[i][j] = rangeSet(1, 9);
        }
        else
        {
            p[i][j] = singletonSet(c - '0');
        }
    }
}

//==============================================================
//                   readPuzzle
//==============================================================
// Read a sudoku puzzle from user input from rows
//==============================================================

Puzzle readPuzzle()
{
    Puzzle p = newPuzzle();
    for(int i = 0;i < 9; i++)
    {
        readRow(p,i);
    }
    return p;
}

//==============================================================
//                      breakPuzzle
//==============================================================
// breakPuzzle(b) is a helper function for printPuzzle that breaks
// the puzzle up into standard sudoku form, a space every
// 3 numbers, a new line every 9 numbers, and a new line every
// 3 rows.
//==============================================================

void breakPuzzle(int b)
{
    if (b%3 == 0)
    {
        printf(" ");
    }
    if (b%9 == 0)
    {
        printf("\n");
    }
    if (b%27 == 0)
    {
        printf("\n");
    }
}

//==============================================================
//                      printPuzzle
//==============================================================
// Prints the sudoku Puzzle(p) from user input
//==============================================================

void printPuzzle(Puzzle p)
{
    int s;
    int numberOfSets = 0;
    for(int i = 0;i < 9; i++)
    {
        for(int j = 0;j < 9; j++)
        {
            if(isEmpty(p[i][j]))
            {
                ++numberOfSets;
                printf("*");
            }
            else if(isSingleton(p[i][j]))
            {
                ++numberOfSets;
                s = smallest(p[i][j]);
                printf("%i", s);
            }
            else
            {
                ++numberOfSets;
                printf("%c", '-');
            }
            breakPuzzle(numberOfSets);
        }
    }
}

//==============================================================
//                      printSet
//==============================================================
// printSet(p) is a helper function for showPuzzle(p) which
// prints the values for a given set from 1 to 9. Also formats
// spaces in between the sets.
//==============================================================

void printSet(SetOfSmallInts p)
{
    for (int n = 1; n < 10; n++)
    {
        if(member(n,p))
        {
            printf("%i", n);
        }
    }
    for(int m = 0; m < 10-size(p); m++)
    {
        printf(" ");
    }
}

//==============================================================
//                      showPuzzle
//==============================================================
// showPuzzle is used for debugging and checking what value are
// possible for each set.
//==============================================================

void showPuzzle(Puzzle p)
{
    for(int i = 0;i < 9; i++)
    {
        for(int j = 0;j < 9; j++)
        {
            printSet(p[i][j]);
            if (j == 8)
            {
                printf("\n");
            }
        }
    }
}

//==============================================================
//                      isSolved
//==============================================================
// returns false if at least one value of the sudoku puzzle is
// not a singleton set, returns true if otherwise.
//==============================================================

bool isSolved(Puzzle p)
{
    for(int i = 0;i < 9; i++)
    {
        for(int j = 0;j < 9; j++)
        {
            if(!isSingleton(p[i][j]))
            {
                return false;
            }
        }
    }
    return true;
}

//==============================================================
//                      isUnsolveable
//==============================================================
// if value at p[i][j] is an empty set, returns true. Returns
// false if it is not an empty set.
//==============================================================

bool isUnsolveable(Puzzle p)
{
    for(int i = 0;i < 9; i++)
    {
        for(int j = 0;j < 9; j++)
        {
            if(isEmpty(p[i][j]))
            {
                return true;
            }
        }
    }
    return false;
}

//==============================================================
//                      constant
//==============================================================
// constant(s) returns the member of s. If s is not a singleton
// set, then constant(s) returns 0.
//==============================================================

int constant(SetOfSmallInts n)
{
    int m = smallest(n);
    if (m != 0 && isEmpty(remove(m, n)))
    {
        return m;
    }
    else
    {
        return 0;
    }
}

//==============================================================
//                      tacticOnePasserHelper
//==============================================================
// TacticOnePasserHelper is a helper function for tacticOnePasser
// which removes repeating numbers. Returns true if any changes
// were made.
//==============================================================

bool tacticOnePasserHelper(int i, int n, PuzzleSection section)
{
    bool changes  = false;
    for (int j = 0; j <9; j++)
    {
        if (i != j && member(n, *section[j]))
        {
            *section[j] = remove(n, *section[j]);
            changes = true;
        }
    }
    return changes;
    
}

//==============================================================
//                      tacticOnePasser
//==============================================================
// TacticOnePasser(section) is a helper function for tacticOne which
// checks a location on PuzzleSection section to find singleton
// sets.
//==============================================================

bool tacticOnePasser(PuzzleSection section)
{
    bool changes = false;
    for(int i = 0; i < 9; i++)
    {
        SetOfSmallInts* location = section[i];
        if (isSingleton(*location))
        {
            int n = constant(*location);
            tacticOnePasserHelper(i, n, section);
        }
    }
    return changes;
}

//==============================================================
//                      tacticOne
//==============================================================
// tacticOne(p) locates all the singleton sets from rows, columns
// and square sections on Puzzle p.
//==============================================================

bool tacticOne(Puzzle p)
{
#ifdef DEBUGGER
    if(tracer1 > 0)
    {
        printf("tactic 1: here is the initial puzzle\n");
        showPuzzle(p);
        printf("\n");
    }
#endif
    bool result = false;
    PuzzleSection section = newPuzzleSection();
    for (int i = 0; i < 9; i++)
    {
        getRow(section, p, i);
        if (tacticOnePasser(section))
        {
            result = true;
        }
        getColumn(section, p, i);
        if (tacticOnePasser(section))
        {
            result = true;
        }
        getSquare(section, p, i);
        if (tacticOnePasser(section))
        {
            result = true;
        }
    }
#ifdef DEBUGGER
    if(tracer1 > 1)
    {
        printf("\n tactic 1: Puzzle after running tactic 1.\n");
        showPuzzle(p);
        printf("\n");
    }
#endif
    delete section;
    return result;
}

//==============================================================
//                      speculate
//==============================================================
// speculate(p,i,j) tries each value for the set at row i, column j of
// puzzle p. If solver is successful, speculate returns solved.
// If solver says the puzzle is unsolvable, speculate tries the
// next speculated value. If none of the values leads to a success,
// speculate returns unsolvable.
//==============================================================

SolutionStatus speculate(Puzzle p, int i, int j)
{
#ifdef DEBUGGER
    if(tracer2 > 1)
    {
        printf("\ntactic2: This is speculate\n");
        showPuzzle(p);
    }
#endif
    Puzzle q = newPuzzle();
    for (int k = 1; k < 10; k++)
    {
        copyPuzzle(q,p);
        if(member(k, p[i][j]))
        {
            q[i][j] = singletonSet(k);
            if(solver(q)==solved)
            {
                copyPuzzle(p, q);
                return solved;
            }
        }
    }
    delete q;
    return unsolvable;
}

//==============================================================
//                      tacticTwo
//==============================================================
// tacticTwo(p) attempt to solve the sudoku puzzle p and speculates
// whether a solution exists for a given sudoku puzzle if it is
// a nonsingleton set. Return solved if it solves the puzzle,
// returns unsolvable if it cannot.
//==============================================================

SolutionStatus tacticTwo(Puzzle p)
{
#ifdef DEBUGGER
    if(tracer2 > 0)
    {
        printf("tactic2: This is the initial puzzle\n");
        showPuzzle(p);
        printf("\n");
    }
#endif
    for(int i = 0;i < 9; i++)
    {
        for(int j = 0;j < 9; j++)
        {
            if (!isSingleton(p[i][j]))
            {
                return speculate(p, i, j);
            }
        }
    }
#ifdef DEBUGGER
    if(tracer2 > 1)
    {
        printf("tactic2: This is the puzzle with tactic 2.\n");
        showPuzzle(p);
        printf("\n");
    }
#endif
    return unsolvable;
}

//==============================================================
//                      solver
//==============================================================
// Solver(p) runs tactic one and tactic two. If solver solved the
// sudoku puzzle, returns solved. If solver cannot solve the
// puzzle, returns unsolvable.
//==============================================================

SolutionStatus solver(Puzzle p)
{
    bool hasChanged = true;
    while(hasChanged)
    {
        hasChanged = tacticOne(p);
    }
    if(isSolved(p))
    {
        return solved;
    }
    else if(isUnsolveable(p))
    {
        return unsolvable;
    }
    return tacticTwo(p);
}

void setTrace(const char* arg)
{
    std::string argument = arg;
    if(argument == "-t1=1")
    {
        tracer1 = 1;
    }
    else if(argument == "-t1=2")
    {
        tracer1 = 2;
    }
    else if(argument == "-t2=1")
    {
        tracer2 = 1;
    }
    
    else if(argument == "-t2=2")
    {
        tracer2 = 2;
    }
}


//==============================================================
//                   main
//==============================================================

int main(int argc, char** argv)
{
    for(int k = 0; k < argc; k++)
    {
        setTrace(argv[k]);
    }
    
    Puzzle p = readPuzzle();
    printf("\n\n");
    printPuzzle(p);
    SolutionStatus status = solver(p);
    if(status == solved)
    {
        printPuzzle(p);
        printf("The puzzle is solved.\n");
    }
    else if(status == unsolvable)
    {
        printPuzzle(p);
        printf("The puzzle is unsolvable.\n");
    }
    return 0;
}