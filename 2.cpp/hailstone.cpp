// CSCI 3300
// Assignment: 2
// Author:     Jose Salinas
// File:       hailstone.cpp
// Tab stops:  2

#include <cstdio>
#include <cmath>
using namespace std;

//===========================================
//             hailFormula
//===========================================
// hailFormula calculates the value that
// follows the given value(n) in the hail
// stone sequence
//===========================================

int hailFormula(int n)
{
    if(n % 2 == 0)
    {
        return n/2;
    }
    else
    {
        return 3 * n + 1;
    }
}

//===========================================
//             sizeHail
//===========================================
// sizeHail takes in a value(n) and returns
// the length of the hailstone sequence
// starting at value(n)
//===========================================

int sizeHail(int n)
{
    if (n ==1)
    {
        return 1;
    }
    else
    {
        return 1 + sizeHail(hailFormula(n));
    }
}

//===========================================
//             hailSequence
//===========================================
// hailSequence takes in an integer(n)
// and writes the hailstone sequence starting
// at (n) on 1 line as standard output,
// seperating values by spaces. This function
// does not return any value.
//===========================================

void hailSequence(int n)
{
    if (n == 1)
    {
        printf("%i ", n);
        
    }
    else
    {
        printf("%i ", n);
        return hailSequence(hailFormula(n));
    }
}

//===========================================
//             localMaximaHelper
//===========================================
// localMaxima takes in a value(prev,n) and
// returns the number of local maxima in the
// hailstone sequence starting at value(prev).
//===========================================

int localMaximaHelper(int prev, int n)
{
    int firstMaxima;
    if( prev > 1)
    {
        firstMaxima = prev;
        prev = hailFormula(prev);
        if(prev > firstMaxima)
        {
            ++n;
        }
        return localMaximaHelper(prev, n);
    }
    return n;
}

//===========================================
//             localMaxima
//===========================================
// localMaxima takes in a value(m) and
// returns the number of local maxima in the
// hailstone sequence starting at value(m).
//===========================================

int localMaxima(int m)
{
    int counter=0;
    if (m == 1 || m > hailFormula(m))
    {
        counter = 1;
    }
    return localMaximaHelper(m, counter);
}

//===========================================
//             largestHailValue
//===========================================
// largestHailValue takes in an integer(b)
// and returns the largest value in the
// hailstone sequence starting at value(b).
//===========================================

int largeHailValue(int b)
{
    if (b == 1)
    {
        return 1;
    }
    else
    {
        return fmax(b,largeHailValue(hailFormula(b)));
    }
}

//===========================================
//             longestHail
//===========================================
// longestHail takes in a value(n) and
// returns the length of the longest hailstone
// sequence that starts with a number from 1
// to value(n).
//===========================================

int longestHail(int n)
{
    if (n == 1)
    {
        return 1;
    }
    else
    {
        return fmax(sizeHail(n),longestHail(n-1));
    }
}

//===========================================
//             main
//===========================================
// main interacts with the user, asking for
// the start number n, and then writing the
// results.
//===========================================

int main(int argc, char** argv)
{
    int n;
    printf("What number should I start with? ");
    scanf("%i", &n);
    printf("The hailstone sequence starting at %i is:\n", n);
    hailSequence(n);
    printf("\n");
    printf("The length of the sequence is %i. ", sizeHail(n));
    printf("\n");
    printf("The largest number in the sequence is %i.", largeHailValue(n));
    printf("\n");
    printf("There are %i local maxima.", localMaxima(n));
    printf("\n");
    printf("The longest hailstone sequence starting with a number up to %i has length %i.", n,longestHail(n));
    printf("\n");
    return 0;
}

