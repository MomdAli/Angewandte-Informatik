---
title: Homework Chapter 13
tags:
  - Betriebssysteme
  - Semester-3
  - Informatik
  - Linux
  - Homework
date: 2024-10-21
aliases: 
cssclasses:
---

## Question 1:
The first Linux tool you should check out is the very simple tool free. First, type man free and read its entire manual page; it’s short, don’t worry!

### Answer:

The `free` command displays the total amount of free and used physical and swap memory in the system, as well as the buffers and caches used by the kernel. The shared memory column should be ignored; it is obsolete.

- **Total**: This is the total amount of physical memory (RAM) available on your system.
- **Used**: This shows how much memory is currently being used by applications and the operating system.
- **Free**: This is the amount of memory that is completely unused.
- **Shared**: The amount of memory being used by the temporary file system (shared memory).
- **Buff/cache**: This shows the amount of memory used by the kernel for buffers and caches. Buffers are used to store raw data for input/output operations, and caches are used to store recently used files to speed up future access.
- **Available**: This is the amount of memory that is readily available for new applications. It takes into account the memory currently used for buffers/cache, which can be freed if necessary.
- **Swap**: Swap memory is disk space used as an extension of RAM when your physical memory is full. The `Swap` section shows how much swap memory is used and free.

## Question 2:

Run `free`, perhaps using some of the arguments that might be useful (e.g., -m, to display memory totals in megabytes). How much memory is in your system? How much is free? Do these numbers match your intuition?

### Answer:

The `free` command gets its data by reading from the **/proc/meminfo** file, which contains memory statistics.
The values displayed in the `used` column aren't as simple as "all used memory," because Linux uses memory aggressively for caching to optimize performance. That's why the `buff/cache` column shows how much is being used for these optimizations. The `available` column is a more useful indicator of how much memory you really have free to allocate to new processes.

## Question 3:

Next, create a little program that uses a certain amount of memory, called memory-user.c. This program should take one commandline argument: the number of megabytes of memory it will use. When run, it should allocate an array, and constantly stream through the array, touching each entry. The program should do this indefinitely, or, perhaps, for a certain amount of time also specified at the command line.

### Answer:

```c title="memory-user.c"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <time.h>

int main(int argc, char *argv[])
{
    if (argc < 2 || argc > 3)
    {
        fprintf(stderr, "Usage: %s <memory in MB> [time in s]\n", argv[0]);
        return 1;
    }

    long long int size;
    if (atoi(argv[1]) > 0)
    {
        size = atoi(argv[1]);
    }
    else
    {
        fprintf(stderr, "Memory size should be greater than 0\n");
        return 1;
    }

    size *= 1024 * 1024 / sizeof(int);
    int *arr = malloc(size * sizeof(int));

    if (arr == NULL)
    {
        fprintf(stderr, "Failed to allocate memory\n");
        return 1;
    }

    time_t run_time = -1;
    if (argc == 3)
    {
        run_time = atoi(argv[2]);
        if (run_time <= 0)
        {
            fprintf(stderr, "Time should be greater than 0\n");
            return 1;
        }
    }

    time_t start_time = time(NULL);

    while (1)
    {
        for (long long int i = 0; i < size; i++)
        {
            arr[i] = i; // Touching each byte of memory to keep it in use
        }

        if (run_time > 0 && difftime(time(NULL), start_time) >= run_time)
        {
            printf("Completed running for %ld seconds.\n", run_time);
            break;
        }
    }

    free(arr);
    return 0;
}
```

## Question 4:

Now, while running your memory-user program, also (in a different terminal window, but on the same machine) run the free tool. How do the memory usage totals change when your program is running? How about when you kill the memory-user program? Do the numbers match your expectations? Try this for different amounts of memory usage. What happens when you use really large amounts of memory?

### Answer:

When the `memory-user` program is running, the `used` memory increases, and the `free` memory decreases. When the program is killed, the `used` memory decreases, and the `free` memory increases. The `buff/cache` memory also increases when the program is running because the kernel caches the memory used by the program. When the program is killed, the `buff/cache` memory decreases, and the `free` memory increases.

When using really large amounts of memory, the system may start swapping memory to disk, which can slow down the system significantly. The `Swap` column in the `free` output will show how much swap memory is being used. If the system starts swapping memory, it's a sign that it's running out of physical memory and needs more RAM.

## Question 5 and 6:
Let’s try one more tool, known as pmap. Spend some time, and read the pmap manual page in detail. To use pmap, you have to know the process ID of the process you’re interested in. Thus, first run ps auxw to see a list of all processes; then, pick an interesting one, such as a browser. You can also use your memory-user program in this case (indeed, you can even have that program call getpid() and print out its PID for your convenience).

### Answer:
After using my own program, I can use the `pmap` command to see the memory usage of the process. The `pmap` command shows the memory map of a process or processes. It displays the memory usage of the process, including the memory regions used by the process and their permissions.

```sh
pmap -x 33289
33289:   ./memory-user 2000 200
Address           Kbytes     RSS   Dirty Mode  Mapping
000055a22db11000       4       4       0 r---- memory-user
000055a22db12000       4       4       0 r-x-- memory-user
000055a22db13000       4       4       0 r---- memory-user
000055a22db14000       4       4       4 r---- memory-user
000055a22db15000       4       4       4 rw--- memory-user
000055a22df19000     132       4       4 rw---   [ anon ]
00007fac5a7de000 2048016 2048012 2048012 rw---   [ anon ]
00007facd77e2000     160     160       0 r---- libc.so.6
00007facd780a000    1620    1092       0 r-x-- libc.so.6
00007facd799f000     352     124       0 r---- libc.so.6
00007facd79f7000       4       0       0 ----- libc.so.6
00007facd79f8000      16      16      16 r---- libc.so.6
00007facd79fc000       8       8       8 rw--- libc.so.6
00007facd79fe000      52      20      20 rw---   [ anon ]
00007facd7a11000       8       4       4 rw---   [ anon ]
00007facd7a13000       8       8       0 r---- ld-linux-x86-64.so.2
00007facd7a15000     168     168       0 r-x-- ld-linux-x86-64.so.2
00007facd7a3f000      44      40       0 r---- ld-linux-x86-64.so.2
00007facd7a4b000       8       8       8 r---- ld-linux-x86-64.so.2
00007facd7a4d000       8       8       8 rw--- ld-linux-x86-64.so.2
00007fffa2ce6000     136      16      16 rw---   [ stack ]
00007fffa2dd5000      16       0       0 r----   [ anon ]
00007fffa2dd9000       8       4       0 r-x--   [ anon ]
---------------- ------- ------- -------
total kB         2050784 2049712 2048104
```

The `pmap` command shows the memory usage of the process, including the memory regions used by the process and their permissions. The `RSS` column shows the Resident Set Size, which is the amount of memory the process is using in RAM. The `Dirty` column shows the amount of memory that has been modified since it was last written to disk. The `Kbytes` column shows the size of each memory region in kilobytes.

## Question 7:
Now run pmap on some of these processes, using various flags (like -X) to reveal many details about the process. What do you see? How many different entities make up a modern address space, as opposed to our simple conception of code/stack/heap?

### Answer:
- `pmap` command shows the memory usage of the process, including the memory regions used by the process and their permissions. 
- `Perm` column shows the permissions of each memory region (read, write, execute). 
- `Size` column shows the size of each memory region in kilobytes. 
- `Rss` column shows the Resident Set Size, which is the amount of memory the process is using in RAM. 
- `Pss` column shows the Proportional Set Size, which is the amount of memory the process is using in RAM, divided by the number of processes sharing that memory region. 
- `Referenced` column shows the amount of memory that has been accessed since it was last written to disk. 
- `Anonymous` column shows the amount of memory that is not associated with a file on disk. 
- `LazyFree` column shows the amount of memory that is marked for freeing but has not been freed yet. 
- `ShmemPmdMapped` column shows the amount of shared memory that is mapped to the process. 
- `FilePmdMapped` column shows the amount of memory that is mapped to files on disk. 
- `Shared_Hugetlb` column shows the amount of memory that is shared with other processes and is using huge pages. 
- `Private_Hugetlb` column shows the amount of memory that is private to the process and is using huge pages. 
- `Swap` column shows the amount of swap memory that is being used. 
- `SwapPss` column shows the Proportional Set Size of swap memory. 
- `Locked` column shows whether the memory region is locked in RAM. 
- `THPeligible` column shows whether the memory region is eligible for Transparent Huge Pages. 
- `Mapping` column shows the name of the memory region.

## Question 8:
Finally, let’s run pmap on your memory-user program, with different amounts of used memory. What do you see here? Does the output from pmap match your expectations?

### Answer:
