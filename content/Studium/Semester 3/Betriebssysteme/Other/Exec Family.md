---
title: Exec
tags:
  - Betriebssysteme
  - C
  - Semester-3
  - Informatik
date: 2024-10-07
aliases: 
cssclasses: 
---
## execl()
**execl()** receives the location of the executable file as its first argument. The next arguments will be available to the file when it’s executed. The last argument has to be _NULL_:<br>
```c
int execl(const char *pathname, const char *arg, ..., NULL)
```
#### Example:
```c
#include <unistd.h> 

int main(void) 
{ 
	char *file = "/usr/bin/echo"; 
	char *arg1 = "Hello world!"; 
	execl(file, file, arg1, NULL); 
	return 0; 
}
```

## execlp()
**execlp()** is very similar to **execl()**. However, **execlp()** uses the PATH environment variable to look for the file. Therefore, the path to the executable file is not needed:<br>
```c
int execlp(const char *file, const char *arg, ..., NULL)
```
#### Example:
```c
#include <unistd.h> 

int main(void) 
{ 
	char *file = "echo"; 
	char *arg1 = "Hello world!";
	execlp(file, file, arg1, NULL); 
	return 0; 
}
```

## execle()
If we use **execle()**, we can pass environment variables to the function, and it’ll use them:<br>
```c
int execle(const char *pathname, const char *arg, ..., NULL, char *const envp[])
```
#### Example:
```c
#include <unistd.h> 

int main(void) 
{ 
	char *file = "/usr/bin/bash"; 
	char *arg1 = "-c"; 
	char *arg2 = "echo $ENV1 $ENV2!"; 
	char *const env[] = {"ENV1=Hello", "ENV2=World", NULL}; 
	execle(file, file, arg1, arg2, NULL, env); 
	return 0; 
}
```

## execv()

**execv()**, unlike **execl()**, receives a vector of arguments that will be available to the executable file. In addition, the last element of the vector has to be NULL:<br>
```c
int execv(const char *pathname, char *const argv[])
```
#### Example:
```c
#include <unistd.h> 

int main(void) 
{ 
	char *file = "/usr/bin/echo"; 
	char *const args[] = {"/usr/bin/echo", "Hello world!", NULL}; 
	execv(file, args); 
	return 0; 
}
```

## execvp()

Just like **execlp()**, **execvp()** looks for the program in the PATH environment variable:<br>
```c
int execvp(const char *file, char *const argv[])
```
#### Example:
```c
#include <unistd.h> 

int main(void) 
{ 
	char *file = "echo"; 
	char *const args[] = {"/usr/bin/echo", "Hello world!", NULL}; 
	execvp(file, args); 
	return 0; 
}
```

## execve()

We can pass environment variables to **execve()**. In addition, the arguments need to be inside a NULL-terminated vector:<br>
```c
int execve(const char *pathname, char *const argv[], char *const envp[])
```
#### Example:
```c
#include <unistd.h> 

int main(void) 
{ 
	char *file = "/usr/bin/bash"; 
	char *const args[] = {"/usr/bin/bash", "-c", "echo Hello $ENV!", NULL}; 
	char *const env[] = {"ENV=World", NULL}; 
	execve(file, args, env); 
	return 0; 
}
```

