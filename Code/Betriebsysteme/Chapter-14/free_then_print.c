#include <stdlib.h>
#include <stdio.h>

int main()
{
    int *data = (int *)malloc(100 * sizeof(int));
    free(data);

    printf("The value of data[0] is %d\n", data[0]);

    return 0;
}