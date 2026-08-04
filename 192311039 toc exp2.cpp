// Online C compiler to run C program online
#include <stdio.h>
#include<string.h>
int main() {
    printf("enther the string: ");
    char c[100];
    scanf("%99s",c);
    int sw=0;
    int n=strlen(c);
    if(n<2){
    	printf("invalid input");return 0;
	}
    for(int i=0;i<n;i++){
        switch(sw){
         case 0:if(c[i]=='0'){
             sw=1;
         }else{sw=3;}
         break;
         case 1:if(c[i]=='0'){
             sw=1;
         }else{sw=2;}break;
         case 2:if(c[i]=='1'){
             sw=2;
         }else{sw=1;}break;
         case 3:sw=3;break;
        }
    }
     if(sw == 2) {
        printf("\nThe string starts '0'and ends with '1'\n");
    } else {
        printf("\nThe string does not '0'start and end with '1'\n");
    }
    return 0;
}
