---
title: Homework 14
tags:
  - Betriebssysteme
  - Semester-3
  - Informatik
  - Homework
date: 2024-10-22
aliases: 
cssclasses:
---

## Question 1:
First, write a simple program called null.c that creates a pointer to an integer, sets it to NULL, and then tries to dereference it. Compile this into an executable called null. What happens when you run this program?

### Answer:
```c
#include <stdio.h>

int main() {
    int *p = NULL;
    printf("%d\n", *p);
    return 0;
}
```
When running this program, it will crash with a segmentation fault. This is because we are trying to dereference a NULL pointer, which is not allowed.

## Question 2:
Next, compile this program with symbol information included (with the -g flag). Doing so let’s put more information into the executable, enabling the debugger to access more useful information about variable names and the like. Run the program under the debugger by typing gdb null and then, once gdb is running, typing run. What does gdb show you?

### Answer:
```sh
Starting program: /mnt/c/Users/mohsa/Desktop/Github/quartz/Code/Betriebsysteme/Chapter 14/null 
[Thread debugging using libthread_db enabled]
Using host libthread_db library "/lib/x86_64-linux-gnu/libthread_db.so.1".

Program received signal SIGSEGV, Segmentation fault.
0x0000555555555161 in main () at null.c:7
7           printf("The value of p is %d\n", *p);
```
GDB shows that the program crashed with a segmentation fault at line 7 in the main function.

## Question 3:
Finally, use the valgrind tool on this program. We’ll use memcheck that is a part of valgrind to analyze what happens. Run this by typing in the following: valgrind --leak-check=yes null. What happens when you run this? Can you interpret the output from the tool?

### Answer:
```sh
==6842== Invalid read of size 4
==6842== at 0x109161: main (null.c:7)
==6842== Address 0x0 is not stack'd, malloc'd or (recently) free'd
```
Valgrind shows that the program crashed with a segmentation fault at line 7 in the main function. It also shows that ==the address 0x0 is not stack'd, malloc'd or (recently) free'd.==

## Question 4:
Write a simple program that allocates memory using malloc() but forgets to free it before exiting. What happens when this program runs? Can you use gdb to find any problems with it? How about valgrind (again with the --leak-check=yes flag)?

### Answer:
```c
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
```
When running this program, it will not crash, but it will have a memory leak. This is because we forgot to free the memory allocated by malloc. GDB will not show any problems with this program, but Valgrind will show that there is a memory leak.

```sh
==11790== 4 bytes in 1 blocks are definitely lost in loss record 1 of 1
==11790==    at 0x4848899: malloc (in /usr/libexec/valgrind/vgpreload_memcheck-amd64-linux.so)
==11790==    by 0x10917E: main (leak.c:6)
==11790== 
==11790== LEAK SUMMARY:
==11790==    definitely lost: 4 bytes in 1 blocks
==11790==    indirectly lost: 0 bytes in 0 blocks
==11790==      possibly lost: 0 bytes in 0 blocks
==11790==    still reachable: 0 bytes in 0 blocks
==11790==         suppressed: 0 bytes in 0 blocks
```
Valgrind shows that there is a memory leak in the program. It says that 4 bytes in 1 block are definitely lost.

## Question 5:
Write a program that creates an array of integers called data of size 100 using malloc; then, set data[100] to zero. What happens when you run this program? What happens when you run this program using valgrind? Is the program correct?

### Answer:
```c
#include <stdlib.h>

int main()
{
    int *data = (int *)malloc(100 * sizeof(int));
    data[100] = 0;
    free(data);

    return 0;
}
```
When running this program, it will not crash, but it will write to memory that is out of bounds. This is because we are accessing data[100], which is out of bounds of the allocated memory. Valgrind will show that there is an invalid write of size 4.

```bash
==17004== Invalid write of size 4
==17004== at 0x10918D: main (size_100.c:6)
==17004== Address 0x4a8d1d0 is 0 bytes after a block of size 400 allocd
==17004== at 0x4848899: malloc (in /usr/libexec/valgrind/vgpreload_memcheck-amd64-linux.so)
==17004== by 0x10917E: main (size_100.c:5)
```

## Question 6:
Create a program that allocates an array of integers (as above), frees them, and then tries to print the value of one of the elements of the array. Does the program run? What happens when you use valgrind on it?

### Answer:
```c
#include <stdlib.h>
#include <stdio.h>

int main()
{
    int *data = (int *)malloc(100 * sizeof(int));
    free(data);

    printf("The value of data[0] is %d\n", data[0]);

    return 0;
}
```
When running this program, it will not crash, but it will read from memory that has been freed. This is because we are trying to access data[0] after freeing the memory. Valgrind will show that there is an invalid read of size 4.

```bash
==19240== Invalid read of size 4
==19240== at 0x1091B3: main (free_then_print.c:9)
==19240== Address 0x4a8d040 is 0 bytes inside a block of size 400 freed
==19240== at 0x484B27F: free (in /usr/libexec/valgrind/vgpreload_memcheck-amd64-linux.so)
==19240== by 0x1091AE: main (free_then_print.c:7)
==19240== Block was allocd at
==19240== at 0x4848899: malloc (in /usr/libexec/valgrind/vgpreload_memcheck-amd64-linux.so)
==19240== by 0x10919E: main (free_then_print.c:6)
```

## Question 7:
Now pass a funny value to free (e.g., a pointer in the middle of the array you allocated above). What happens? Do you need tools to find this type of problem?

### Answer:
```c
#include <stdlib.h>

int main()
{
    int *data = (int *)malloc(100 * sizeof(int));
    free(&data[32]);

    return 0;
}
```
When trying to compile this file, it will give a warning:

```sh
free_then_print_2.c: In function ‘main’:
free_then_print_2.c:6:5: warning: ‘free’ called on pointer ‘data’ with nonzero offset 128 [-Wfree-nonheap-object]
    6 |     free(&data[32]);
      |     ^~~~~~~~~~~~~~~
free_then_print_2.c:5:24: note: returned from ‘malloc’
    5 |     int *data = (int *)malloc(100 * sizeof(int));
      |                        ^~~~~~~~~~~~~~~~~~~~~~~~~
```
that we are trying to free a pointer that was not allocated by malloc. When running this program, it will abort the program with this message: 

```sh
free(): invalid pointer
Aborted
```

After running Valgrind:
```sh
==26733== Invalid free() / delete / delete[] / realloc()
==26733== at 0x484B27F: free (in /usr/libexec/valgrind/vgpreload_memcheck-amd64-linux.so)
==26733== by 0x109192: main (free_then_print_2.c:6)
==26733== Address 0x4a8d0c0 is 128 bytes inside a block of size 400 allocd
==26733== at 0x4848899: malloc (in /usr/libexec/valgrind/vgpreload_memcheck-amd64-linux.so)
==26733== by 0x10917E: main (free_then_print_2.c:5)
```

## Question 8:
Try out some of the other interfaces to memory allocation. For example, create a simple vector-like data structure and related routines that use realloc() to manage the vector. Use an array to store the vectors elements; when a user adds an entry to the vector, use realloc() to allocate more space for it. How well does such a vector perform? How does it compare to a linked list? Use valgrind to help you find bugs.

### Answer:

```c title="vector.c"
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
```
  
```c title="vector.h"
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
```

The vector performs well when adding elements to it. It uses realloc to allocate more space when needed, which is efficient. However, when deleting elements, it may not perform as well because it reallocates memory when the size is 1/4 of the capacity.

A linked list is more flexible than a vector because it can grow and shrink without reallocating memory. However, a linked list uses more memory because it needs to store pointers to the next element.