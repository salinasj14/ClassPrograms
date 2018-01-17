// Jose Salinas
// Assignment 3, semaphores
// must be compiled as gcc -pthread assn3.c -o assn3
#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <semaphore.h>
#include <time.h>
#include <math.h>

#define MAXSIZE 10

int buffer[MAXSIZE];
int x=0;
int y=0;
int numOfRandoms=0;
sem_t mutex;
sem_t empty;
sem_t full;

void *producer(void *arg)
{
    FILE* f = fopen("production.txt", "a");
    int randomNumber,i;
    int ptr = 0;
    for(i=0; i<numOfRandoms; i++)
    {
        randomNumber =rand()%100+1;//generate random number between 1-100
        sem_wait(&empty);
        sem_wait(&mutex);
        buffer[ptr] = randomNumber;//put random number in buffer
        ptr = ptr+1;
        fprintf(f,"Producer: %i\n",randomNumber);//write random number to file
        printf("Producer: %i\n",randomNumber);//write random number to terminal
        sem_post(&mutex);
        sem_post(&full);
    }
    fclose(f);
}

void *consumer(void *arg)
{
    int item,i,itemSquare;
    int ptr = 0;
    FILE* f = fopen("consumption.txt", "a");
    for(i =0; i<numOfRandoms;i++)
    {
        sem_wait(&full);
        sem_wait(&mutex);
        item= buffer[ptr];
        ptr = ptr+1;
        itemSquare=item*item;
        fprintf(f,"Consumer: %i    %3i\n",item,itemSquare);//write random number to file
        printf("Consumer: %i    %3i\n",item,itemSquare);//write the squared number to file
        sem_post(&mutex);
        sem_post(&empty);
    }
    fclose(f);
}
int main(int argc,char ** argv)
{
    srand(time(NULL));
    sem_init(&mutex,0,1);
    sem_init(&empty,0,MAXSIZE);
    sem_init(&full,0,0);
    numOfRandoms= atoi(argv[1]);
    pthread_t pro, con;
    pthread_create(&pro,NULL, producer, NULL);//creating producer thread
    pthread_create(&con,NULL, consumer, NULL);//creating consumer thread
    pthread_join(pro,NULL);
    pthread_join(con, NULL);
    exit(0);
}