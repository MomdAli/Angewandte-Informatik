#include <stdio.h>
#include <stdlib.h>

typedef struct
{
    int *data;
    int size;
    int capacity;
} vector;

void vector_add(vector *v, int value)
{
    if (v->size == v->capacity)
    {
        v->capacity = v->capacity * 2;
        v->data = (int *)realloc(v->data, v->capacity * sizeof(int));
    }
    (v->data)[v->size++] = value;
}

void vector_delete(vector *v)
{
    (v->data)[--(v->size)] = 0;
    if (v->size == (int)(v->capacity / 4))
    {
        v->capacity = (int)(v->capacity / 2);
        v->data = (int *)realloc(v->data, v->capacity * sizeof(int));
    }
}

void vector_print(vector *v)
{
    for (int i = 0; i < v->size; i++)
    {
        printf("[%d]: %d\n", i, (v->data)[i]);
    }

    printf("size: %d\n", v->size);
    printf("capacity: %d\n", v->capacity);
}

void vector_free(vector *v)
{
    free(v->data);
    v->size = 0;
    v->capacity = 0;
}