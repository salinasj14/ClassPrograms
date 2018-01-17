//  coordinator.c
//  Created by Jose Salinas on 1/26/16.
//  Copyright © 2016 Jose Salinas. All rights reserved.
//

#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <stdlib.h>
#include <stdio.h>
#define SIZE 100
#define index 15

int main(int argc, char *argv[])
{
    int status;
    int test = 1;
    int sumTotal = 0;
    int arraySum[SIZE];// random number for my array size
    pid_t pid;
    for(int i = 1; i <= argc - 1; i = i+2)
    {
        pid = fork();
        if(pid < 0)
        {
            perror("Fork failed.");
            exit(1);
        }
        else if(pid == 0)
        {
            if(argv[i+1] != NULL)
            {
                execlp("./worker","./worker",argv[i],argv[i+1], NULL);
            }
            else
            {
                execlp("./worker","./worker", argv[i],"0", NULL);//in case only 3 numbers in command line
            }
        }
        else
        {
            wait(&status);
            arraySum[i-1] = WEXITSTATUS(status);//save sum of child process in array
            printf("The PID of worker is %i and the total is %i\n\n",pid,arraySum[i-1]);
        }
    }
    while (test<argc-1)
    {
        for(int j = 0; j < argc; j = j+2)
        {
            pid = fork();
            if(pid < 0)
            {
                perror("Fork failed.");
                exit(1);
            }
            else if(pid == 0)
            {
                char strTwo[index];
                char str[index];
                sprintf(str, "%i", arraySum[j]);//values from array chnaged to string
                sprintf(strTwo, "%i", arraySum[j+2]);
                if(arraySum[j+2] == -1)
                {
                    arraySum[j+2] = 0;//adding one of the numbers by 0 for error handling
                }
                execlp("./worker","./worker",str,strTwo, NULL);
                test++;
            }
            else
            {
                wait(&status);
                arraySum[j+2] = WEXITSTATUS(status);
                if(arraySum[j] == WEXITSTATUS(status))//in case answer is same from parent, exit
                {
                    printf("\n");
                    break;
                }
                printf("The PID of worker is %i and the total is %i\n\n",pid,arraySum[j+2]);
                sumTotal = arraySum[j+2];
                test++;
            }
            test++;
        }
        test++;
    }
    printf("Sum of all numbers is : %d \n", sumTotal);
}