#include <stdlib.h>
#include <stdio.h>

int main()
{
    int *p = (int *)malloc(sizeof(int));
    if (p == NULL)
    {
        return 1;
    }

    *p = 42;
    printf("The value of p is %d\n", *p);

    // free(p); // No free() call, memory leak

    return 0;
}