#include <stdio.h>

int main(int argc, char *argv[])
{
    int *x = 0;
    int y = *x + 1;
    printf("y: %d", y);
    return 0;
}