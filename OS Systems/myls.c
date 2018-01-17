//Professor said sorting is not necessary.
//Jose Salinas
//gcc -Wall myls.c -o myls
#include <dirent.h>
#include <sys/stat.h>
#include <stdio.h>
#include <pwd.h>
#include <grp.h>
#include <time.h>
#include <string.h>
#include <stdlib.h>
#include <math.h>
#define MAXSIZE 15
#define YEARS 1900

void fileInfo(struct stat *buf, char *entry,int n)
{
    struct passwd *pwd;
    struct group *grop;
    struct tm *tr;
    time_t times;
    char* date;
    char filetype[4] = "-dcb";
    char usr[4] = "---";
    char grp[4] = "---";
    char oth[4] = "---";
    if (buf->st_mode & S_IRUSR)
        usr[0] = 'r';
    if (buf->st_mode & S_IWUSR)
        usr[1] = 'w';
    if (buf->st_mode & S_IXUSR)
        usr[2] = 'x';
    if (buf->st_mode & S_IRGRP)
        grp[0] = 'r';
    if (buf->st_mode & S_IWGRP)
        grp[1] = 'w';
    if (buf->st_mode & S_IXGRP)
        grp[2] = 'x';
    if (buf->st_mode & S_IROTH)
        oth[0] = 'r';
    if (buf->st_mode & S_IWOTH)
        oth[1] = 'w';
    if (buf->st_mode & S_IXOTH)
        oth[2] = 'x';
    pwd = getpwuid(buf->st_uid);
    grop = getgrgid(buf->st_gid);
    times = buf->st_mtime; 
    tr = gmtime(&times);
    date = 4 + ctime(&times);
    int temp = tr->tm_year;
    if (entry[0] != '.')
    {
    	{
        printf("%c",filetype[S_ISDIR(buf->st_mode)]);
        printf("%s%s%s ",usr,grp,oth);
        printf("%ld ",buf->st_nlink);
        printf("%s %s ",pwd->pw_name,grop->gr_name);
        printf("%9i ",(int)buf->st_size);
        if(temp < n)
          {
          	printf("%.6s  %i ",date,temp+YEARS);
          }
        else
          {
        	printf("%.12s ",date);
    	  }
        printf("%s \n",entry);
   		}
    }
}

int main(int argc, char *argv[])
{
	time_t now;
	struct tm *ts;
	time(&now);
	ts = gmtime(&now);
	int n = ts->tm_year;
    DIR* dir = opendir((const char*)".");
    struct dirent* entry;
    struct stat buffer;
    if (argc ==1)
    {
        while((entry = readdir(dir)))
        {
            stat(entry->d_name, &buffer);
            fileInfo(&buffer,entry -> d_name,n);
        }
        closedir(dir);
    }
    else
    {
    	closedir(dir);
        DIR* dirFile;
        struct stat files;
        struct dirent* entrys;
        stat(argv[1],&files);
        if(S_ISREG(files.st_mode))
        {
            char *sub = argv[1];
            fileInfo(&files,sub,n);
        }
        else
        {
            if((dirFile = opendir(argv[1])) == NULL)
		       {
		         printf("Can't open %s, does not exist.\n",argv[1]);
			   }
			   else
			   {
                 while((entrys = readdir(dirFile)))
                 {
                  stat(entrys->d_name, &files);
                  fileInfo(&files, entrys->d_name,n);
                 }
            closedir(dirFile);
            }
        }
    }
    return 0;
}
