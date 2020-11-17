#include<stdio.h>
#include<string.h>

int main(int argc, char *argv[]){
    // printf("%s", argv[]);
     for (int i = 0; i < argc; ++i) 
        printf("%s\n", argv[i]);
    return 0;
}