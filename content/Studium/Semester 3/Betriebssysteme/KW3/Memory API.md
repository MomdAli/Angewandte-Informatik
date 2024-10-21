---
title: Memory API
tags:
  - Betriebssysteme
  - Semester-3
  - Informatik
date: 2024-10-21
aliases: 
cssclasses:
---

## Types of Memory

### Stack memory
- Variables declared inside a function are stored in the stack memory
- Stack memory is used to store local variables and function call
- Stack memory is always referenced in LIFO order
- Stack memory is very fast when compared to heap memory
- Stack memory has a limited size

Declaring a variable in a function:

```c 
void foo() {
    int a = 10; // declares a variable in the stack memory
}
```

### Heap memory
- Variables declared in the heap memory are accessible globally
- Heap memory is not as fast as stack memory
- Heap memory has no size limit
- Heap memory is allocated during runtime

Declaring a variable in the heap memory:

```c
int *a = (int *)malloc(sizeof(int)); // declares a variable in the heap memory
```


## The malloc() Function

The `malloc()` function is used to allocate memory in the heap memory. The `malloc()` function takes the number of bytes to be allocated as an argument and returns a pointer to the first byte of the allocated memory.

The manual page shows the following prototype:

```c
void *malloc(size_t size);
```

The `malloc()` function returns a pointer to the allocated memory, or `NULL` if the request fails.

### Sizeof Operator

The `sizeof` operator is used to determine the size of a data type. The `sizeof` operator returns the size of the data type in bytes.
  
  ```c
  int a = 10;
  printf("%d", sizeof(a)); // prints 4
  ```

However when we use sizeof() with a pointer, it returns the size of the pointer, not the size of the data type it points to.

```c
 int *a = (int *)malloc(sizeof(int) * 4);
 printf("%d", sizeof(a)); // expected 16, but prints 8
 ```

### The free() Function

The `free()` function is used to deallocate memory allocated by the `malloc()` function. The `free()` function takes a pointer to the memory to be deallocated as an argument. The function does not return anything. 

```c
 int *a = (int *)malloc(sizeof(int));
 free(a); // deallocates the memory allocated by malloc()
 ```

### Difference between System calls and Library calls

- **System calls**: System calls are used to interact with the operating system. They are used to perform tasks such as creating a new process, reading from a file, or allocating memory.
- **Library calls**: Library calls are used to interact with libraries that provide additional functionality. They are used to perform tasks such as sorting an array, printing to the console, or allocating memory.

## Common Mistakes

- **Dangling Pointers**: A pointer pointing to a memory location that has been deallocated.
- **Memory Leak**: Failure to deallocate memory after use.
- **Using Uninitialized Pointers**: Using pointers that have not been initialized.
- **Returning Local Variables**: Returning a pointer to a local variable that is deallocated after the function returns.
- **Overwriting Memory**: Writing to memory that has not been allocated.
- **Double Free**: Deallocating memory that has already been deallocated.

## Summary

- Stack memory is used to store local variables and function calls.
- Heap memory is used to store global variables and is allocated during runtime.
- The `malloc()` function is used to allocate memory in the heap memory.
- The `free()` function is used to deallocate memory allocated by the `malloc()` function.
- Common mistakes include dangling pointers, memory leaks, using uninitialized pointers, returning local variables, overwriting memory, and double free.

#### Further Reading
- [Memory Management in C](https://www.geeksforgeeks.org/memory-layout-of-c-program/)
- [Memory Allocation in C](https://www.geeksforgeeks.org/dynamic-memory-allocation-in-c-using-malloc-calloc-free-and-realloc/)
- [Memory Leak in C](https://www.geeksforgeeks.org/memory-leak-in-c-and-how-to-avoid-it/)
- [Dangling Pointers in C](https://www.geeksforgeeks.org/d)
- [Double Free in C](https://www.geeksforgeeks.org/double-pointer-pointer-pointer-c/)

##### Next Chapter: 