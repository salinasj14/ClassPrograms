/* Jose Salinas
prefix.c
This program uses parallel prefix computation
to add up values in arrays
 must be compiled as such "gcc -pthread -lm prefix.c -o prefix"
 */

#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#define array_size 1000
int size = 0;
int distance = 1;
int iniArray[array_size];
int sumAll[array_size];
pthread_barrier_t barrier;
pthread_attr_t attr;
pthread_t tid[array_size];

void *arrayCompute(void* arg)
{
    int *i = (int *) arg;
    if(*i > distance - 1)
    {
        iniArray[*i] = sumAll[*i] + sumAll[*i - distance];
    }
    pthread_barrier_wait(&barrier);
    pthread_exit(NULL);
}

void readFile(FILE* f)
{
    if(f == NULL)
    {
        printf("readfile not found: File not opened\n");
    }
    int n;
    fscanf(f, "%i", &n);
    while(!feof(f) && size != array_size)
    {
        iniArray[size] = n;
        fscanf(f, "%i", &n);
        size++;
    }
    fclose(f);
}

int main(int argc, char *argv[])
{
    int i, n, m;
    FILE* f = fopen(argv[1], "r");
    readFile(f);
    pthread_attr_init(&attr);
    pthread_attr_setscope(&attr, PTHREAD_SCOPE_SYSTEM);
    pthread_barrier_init (&barrier,NULL,size);
    printf("Array Index\t\t");
    for(n = 1; n <= size; n++)
    {
        printf("%3i ", n);
        
    }
    printf("\n");
    printf("Initial Array\t\t");
    for(n = 0; n < size; n++)
    {
        sumAll[n] = iniArray[n];
        printf("%3i ", sumAll[n]);
        
    }
    printf("\n\n");
    for(i=0; i < (int) ceil(log2(size)); i++)
    {
        for(m = 0; m < size; m++)
        {
            int *arg = malloc(sizeof(*arg));
            *arg = m;
            int status = pthread_create(&tid[m], &attr, arrayCompute, arg);
            if(status != 0)
            {
                printf("Error creating the thread, error code %d\n", status);
                exit(-1);
            }
        }
        for(m = 0; m < size; m++)
        {
            pthread_join(tid[m], NULL);
        }
        printf("Sum after distance %d    ", distance);
        for(n = 0; n < size; n++)
            
        {
            sumAll[n] = iniArray[n];
            printf("%3i ", sumAll[n]);
        }
        printf("\n\n");
        distance = distance * 2;
    }
}