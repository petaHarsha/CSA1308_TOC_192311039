// Online C compiler to run C program online
#include <stdio.h>
#include<string.h>
int main() {
    printf("enther the string: ");
    char c[100];
    scanf("%99s",c);
    int sw=0;
    int n=strlen(c);
    for(int i=0;i<n;i++){
        switch(sw){
         case 0:if(c[i]=='a'){
             sw=1;
         }else{sw=3;}
         break;
         case 1:if(c[i]=='a'){
             sw=1;
         }else{sw=2;}break;
         case 2:if(c[i]=='a'){
             sw=1;
         }else{sw=2;}break;
         case 3:sw=3;break;
        }
    }
     if(sw == 1) {
        printf("\nThe string starts and ends with 'a'\n");
    } else {
        printf("\nThe string does not start and end with 'a'\n");
    }
    return 0;
}
