#include <stdio.h>
#include <stdlib.h>
#include "vector.h"

int main()
{
    vector v = {.data = (int *)malloc(sizeof(int)), .size = 1, .capacity = 1};
    v.data[0] = 4;

    vector *vp = &v;
    vector_add(vp, 5);
    vector_add(vp, 6);
    vector_add(vp, 7);
    vector_delete(vp);

    vector_print(vp);
    vector_free(vp);

    return 0;
}