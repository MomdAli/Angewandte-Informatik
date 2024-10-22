#include <stdlib.h>

int main()
{
    int *data = (int *)malloc(100 * sizeof(int));
    free(&data[32]);

    return 0;
}