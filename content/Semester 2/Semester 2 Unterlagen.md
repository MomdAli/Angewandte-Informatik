- [[Semester-2/Programmiertechnik/]]
	- [[Java Stream Collection]]
	- [[Semester-2/Programmiertechnik/Probeklausuren]]
	- [[Semester 2/Programmiertechnik/Klausurvorbereitung|Klausurvorbereitung]]
- [[Semester-2/Analysis/]]
	- [[Semester 2/Analysis/Klausurvorbereitung|Klausurvorbereitung]]


```mermaid
classDiagram
        Animal <|-- Duck
        Animal <|-- Fish
        Animal <|-- Zebra
        Animal : +int age
        Animal : +String gender
        Animal: +isMammal()
        Animal: +mate()
        class Duck{
            +String beakColor
            +swim()
            +quack()
        }
        class Fish{
            -int sizeInFeet
            -canEat()
        }
        class Zebra{
            +bool is_wild
            +run()
        }
```

