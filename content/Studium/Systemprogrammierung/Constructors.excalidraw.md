---
{"publish":true,"draft":true,"created":"2024-07-25T10:19:58.397+02:00","modified":"2025-09-12T21:25:03.044+02:00","published":"2025-09-12T21:25:03.044+02:00","tags":["excalidraw","Informatik"],"cssclasses":""}
---

==⚠  Switch to EXCALIDRAW VIEW in the MORE OPTIONS menu of this document. ⚠== You can decompress Drawing data with the command palette: 'Decompress current Excalidraw file'. For more info check in plugin settings under 'Saving'


# Excalidraw Data
## Text Elements
class Person {
    private:
        int age;
        double size;
    public:
        ...
        Constructors
        ...
}; ^JVc4Po8z

Person person2 ^K4ogE23q

age = 25
size = 175.5 ^5rk3AQnX

Default Constructor: ^c3MPUbEC

Syntax:
    Person() {

    }
or
    Person() = default; ^CXXNSwtE

Person person1; ^QO90FMqT

Person person1 ^U6g9sltX

age = NULL
size = NULL ^xke9vyWt

Parameterized Constructor: ^jr72LUbz

Syntax:
    Person(int a, double s) 
        : age(a), size(s) { }

No default keyword ^WMlwWjpj

Person person2(25, 175.5);
or 
Person person2{25, 175.5}; ^DUwMr55y

Person person2 ^yOIrLYHp

age = 25
size = 175.5 ^EjXMyWiu

Copy Constructor: ^TK8CCXyq

Syntax:
    Person(const Person& other)
        : age(other.age), size(other.size) { }
or
    Person(const Person& other) = default; ^49hTUkz4

Person person3 = person2;
or
Person person3(person2); ^yVIbeZsD

Person person3 ^cw2rca5h

age = 25
size = 175.5 ^7LVoyDFK

New Object ^CiKoBWIz

Example of class Person: ^4YorYQ11

Person person2 ^r9vi54Kb

age = 25
size = 175.5 ^mq5VLyYb

Move Constructor: ^GOh6q0vU

Syntax:
    Person(Person&& other)
        : age(other.age), size(other.size) 
    { 
        other.age = 0;
        other.size = 0;
    }
or
    Person(Person&& other) = default; ^L9JtsfHl

Person person4 = std::move(person2); ^MeC2TNyv

Person person4 ^nVBnt6sA

age = 25
size = 175.5 ^vZpGkwRT

New Object ^F1wun5AR

Person person2 ^XscznmG7

age = 0
size = 0 ^1UaPTh5r

Person person3 ^NXguBY8U

age = 25
size = 175.5 ^NoR08u2S

Copy Assignment Operator: ^xTfypdiX

Syntax:
    Person& operator=(const Person& other)
    { 
        if (this != &other) {
            age = other.age;
            size = other.size;
        }
        return *this;
    }
or
    Person& operator=(const Person& other)
         = default; ^dmSAJiBg

Person person5(30, 180.5);
person5 = person3; ^JhZCVnoR

Person person5 ^VjzapzRh

age = 30
size = 180.5 ^V0BXG1Ti

Already existing object ^KlGml2On

da person5 schon initialisiert ist, muss man copy Zuweisung aufrufen ^a79dj8bk

25 ^6isoJEIi

175.5 ^D2oyF0aV

Move Assignment Operator: ^6ntH2tiQ

Syntax:
    Person& operator=(Person&& other)
    { 
        if (this != &other) {
            age = other.age;
            size = other.size;
            other.age = 0;
            other.size = 0;
        }
        return *this;
    }
or
    Person& operator=(Person&& other)
         = default ^f2AgQvAy

Person person3 ^t58jsaVx

age = 25
size = 175.5 ^hbeA6jzm

Person person6(35, 185.5);
person6 = std::move(person3); ^K15OMuZd

Person person6 ^ZQql1hCU

age = 35
size = 185.5 ^9jelBjbw

Already existing object ^XxYkUTuY

da person6 schon initialisiert ist, muss man move Zuweisung aufrufen ^Me7oZVc3

25 ^LUiryHai

175.5 ^QTyW8Vj5

Person person2 ^oatSHIOD

age = 0
size = 0 ^1pi9Azq2

