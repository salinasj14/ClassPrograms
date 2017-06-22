// CSCI 3300
// Assignment: 1
// Author:     Jose Salinas
// File:       hailstone.cpp
// Tab stops:  2

#include <cstdio>
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
// sizeHail takes in a value(length) and returns
// the length of the hailstone sequence
// starting at value(length).
//===========================================

int sizeHail(int length)
{
    int control = 1;
    while( length > 1)
    {
        length = hailFormula(length);
        ++control;
    }
    return control;
}

//===========================================
//             hailSequence
//===========================================
// hailSequence takes in an integer(numero)
// and writes the hailstone sequence starting
// at (numero) on 1 line as standard output,
// seperating values by spaces. This function
// does not return any value.
//===========================================

void hailSequence(int numero)
{
    int out = numero;
    int in = sizeHail(numero);
    for (int x = 1; in >= x; x++)
    {
        if (out == 1)
        {
            printf("%i ", out);
        }
        else
        {
            printf("%i ", out);
            out=hailFormula(out);
        }
    }
}

//===========================================
//             localMaxima
//===========================================
// localMaxima takes in a value(m) and
// returns the number of local values in the
// hailstone sequence starting at value(m).
//===========================================

int localMaxima(int m)
{
    int firstMaxima;
    int counter = 0;
    if (m == 1 || m > hailFormula(m))
    {
        counter = 1;
    }
    while( m > 1)
    {
        firstMaxima = m;
        m = hailFormula(m);
        if(m > firstMaxima)
        {
            ++counter;
        }
    }
    return counter;
}

//===========================================
//             largestHailValue
//===========================================
// largestHailValue takes in an integer(largeValue)
// and returns the largest value in the
// hailstone sequence starting at value(largeValue).
//===========================================

int largeHailValue(int largeValue)
{
    int n = largeValue;
    while( largeValue > 1)
    {
        largeValue = hailFormula(largeValue);
        if (largeValue > n)
        {
            n = largeValue;
        }
    }
    return n;
}

//===========================================
//             longestHail
//===========================================
// longestHail takes in a value(longest) and
// returns the length of the longest hailstone
// sequence that starts with a number from 1
// to value(longest).
//===========================================

int longestHail(int longest)
{
    int size = 1;
    int n = 1;
    while ( longest >= n)
    {
        if (sizeHail(n) > size)
        {
            size = sizeHail(n);
            ++n;
        }
        else
        {
            ++n;
        }
    }
    return size;
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
    printf("The length of the sequence is %i. \n", sizeHail(n));
    printf("The largest number in the sequence is %i.\n", largeHailValue(n));
    printf("There are %i local maxima.\n", localMaxima(n));
    printf("The longest hailstone sequence starting with a number up to %i has length %i.\n", n,longestHail(n));
    return 0;
}

