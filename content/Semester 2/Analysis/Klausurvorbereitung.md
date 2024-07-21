---
enableToc: false
date: 2024-07-17
tags:
  - Probeklausur
  - Zusammenfassung
  - Mathematik
publish: true
---

## <font color="#92d050">Folgen</font>

Die geordnete Menge aller Werte der Folge bezeichnen wir mit:
$$\Huge
(a_n)_{n\in\mathbb N} \::\: (a_1,a_2,a_3,...,a_k,...)
$$
---
Konvergenz und Grenzwert einer Folge mit $a$ = Grenzwert und $\epsilon\approx0$ gilt:
$$
|a_n-a|<\epsilon
$$

> [!Cite]  Eigenschaften von Folgen:
> Eine konvergente Folge besitzt genau einen Grenzwert.
> Monoton wachsende und nach oben beschrankte Folgen sind konvergent.
> Monoton fallende und nach unten beschrankte Folgen sind konvergent.
> Konvergente Folgen sind beschränkt.

## <font color="#ffc000">Funktionen</font>
> [!NOTE] **Injektivität, Surjektivität und Bijektivität**
> Eine Funktion ist **Injektiv**, wenn jedes Element der Zielmenge höchstens einmal als Funktionswert auftritt.
> Eine Funktion ist **surjektiv**, wenn jedes Element der Zielmenge mindestens einmal erreicht wird, und **bijektiv**, wenn sie sowohl injektiv als auch surjektiv ist.
 
| $$p(x)=x^r$$     | $$u(x)=a^x$$          | $$v(x)=sin\,x$$ |
| ---------------- | --------------------- | --------------- |
| $Potenzfunktion$ | $Exponentialfunktion$ | $Sinusfunktion$ |

![450|](funktionen.png)

| Schreibweise                       | Definition/Sprechweise                                                                                  |
| ---------------------------------- | ------------------------------------------------------------------------------------------------------- |
| y=f(x)                             | heißt Funktionswert von $f$ an der Stelle $x$.                                                          |
| A                                  | heißt Definitionsbereich von $f$ (auch $\mathbb D_f$).                                                  |
| B                                  | heißt Wertebereich von f.                                                                               |
| Für $A'\subseteq A, B'\subseteq B$ | heißt                                                                                                   |
| $f(A')$                            | $:= {y\in B \:\vert\: \exists x \in A' \;mit\; y=f(x)}$<br>$\;\;\;Bild (-menge)\; von\; A'(unter\; f).$ |
| $f^{-1}(B')$                       | $:={x \in A \;\vert\; f(x)\in B'}$<br>$\;\;\; Urbild (-menge)\; von\; B'(unter\; f).$                   |


### - <font color="#d99694">Grenzwert von Funktionen:</font>

> [!IMPORTANT]
> In der Mathematik ist der Limes oder Grenzwert einer Funktion an einer bestimmten Stelle **der Wert, dem sich die Funktion in der Umgebung der betrachteten Stelle annähert**. Ein solcher Grenzwert existiert jedoch nicht in allen Fällen.
>> [!Example] Beispiel
>>$$
>>f(x)=\frac{1}{x}
>>$$
>> $$
>> \lim_{x\nearrow 0} f(x)=\lim_{x\nearrow 0}-1=-1 
>> $$
>> $$
>> \lim_{x\searrow 0} f(x)=\lim_{x\searrow 0}1=1
>> $$

$$
\large \text{linksseitiger Grenzwert:} \lim_{x\nearrow a} f(x)
$$
$$
\large \text{rechtsseitiger Grenzwert:} \lim_{x\searrow a} f(x)
$$

### - <font color="#d99694">Stetigkeit einer funktion:</font>
> [!IMPORTANT] Wichtig
> Eine Funktion ist stetig, **wenn der Graph der Funktion im Definitionsbereich nahtlos gezeichnet werden kann**. Anders ausgedrückt: Der Graph muss in jedem zusammenhängenden Teilintervall aus dem Definitionsbereich nahtlos gezeichnet werden können.
>Noch einmal in Worte zusammengefasst bedeutet Stetigkeit von f an einer Stelle a: Sofern a ein innerer Punkt ist prüfen wir ob links– und rechtsseitiger Grenzwert jeweils existieren und auch übereinstimmen. Dann ist die Funktion $f(x)$ an der Stelle x = a stetig.
>>[!Example] Beispiel
>>$$
>>\begin{aligned}
>>\begin{equation}
>>  f(x)=\begin{cases}
>>    x^2, & für\:x<1\\
>>    0, & für\:x=1 \\
>>    2-x & für\:x>1
>>  \end{cases}
>>\end{equation} \;\;\;ist\; nicht\; stetig\: da\: f(1)=0\:
>>
>>\end{aligned}
>>$$
>>![Stetige_Funktion](Stetige_Funktion.png)



### - <font color="#e5b9b7">Polstelle und Lücken</font>

> [!IMPORTANT] Wichtig
> In der Mathematik bezeichnet man eine **einpunktige Definitionslücke einer Funktion** als Polstelle oder auch kürzer als Pol, wenn die Funktionswerte in jeder Umgebung des Punktes (betragsmäßig) beliebig groß werden. Damit gehören die Polstellen zu den isolierten Singularitäten.
>> [!Example] beispiel
>> $$
>> f(x)=\frac{x^2-1}{(x-1)^4}
>> $$
>> hat den Definitionsbereich $\mathbb D_{f}=\mathbb R \backslash \{1\}\newline$
>> Der rechtsseitige Limes liefert:
>> $$
>> \lim_{x\searrow 1}\frac{x^2-1}{(x-1)^4}=\lim_{x\searrow 1}\frac{(x-1)(x+1)}{(x-1)^4}=\lim_{x\searrow 1}\frac{(x+1)}{(x-1)^3}=\infty
>> $$
>> und der linksseitige 
>> $$
>> \lim_{x\nearrow 1}\frac{(x+1)}{(x-1)^3}=-\infty
>> $$
>> ![200|center](Pollstelle.png)



<hr class="do-not-print" style="page-break-after:always;"/>

## <font color="#fac08f">Ableitungen</font>

![700](Ableitungen.png)

### <font color="#b2a2c7">Newton-Verfahren</font>
> [!IMPORTANT] Wichtig
> Das Newton-Verfahren dient zur Annäherung an Nullstellen; durch das immer wieder neu Einsetzen des Ergebnisses in die Newton-Formel nähert man die Nachkommastellen der Nullstelle immer mehr an. Diese Art von Verfahren nennt man Iterationsverfahren.
> Iterationsformel:
> $$
> \large x_{n+1}=x_n-\frac{f(x_n)}{f'(x_n)}
> $$

![Newton_Verfahren_Ex](Newton_Verfahren_Ex.png)![](NewtonIteration_Ani.gif)

### <font color="#b2a2c7">Taylorpolynom</font>

>[!IMPORTANT] Wichtig
>Diesen Prozess können wir an allen Funktionen $f$ fur beliebig viele Ableitungen $n$ durchfuhren, sofern die Funktion an der Stelle $a$ auch $n$-mal differenzierbar ist. Idealerweise lasst sich die $n$-te Ableitung von $f$ formulieren.
>$$
>\large\sum\limits_{k=0}^{n}{\frac{f^{(k)}(a)}{k!}(x-a)^k}
>$$
>$$
>\normalsize \color{cyan}=f(a)+f'(a)(x-a)\color{white}+\color{lime}\frac{f^{(2)}(a)}{2!}(x-a)^2\color{white}+...+\color{orange}\frac{f^{(n)}(a)}{n!}(x-a)^n
>$$

![](https://www.youtube.com/watch?v=urPIxvNBXF0)

![n-2-taylor](n-2-taylor.gif)
## <font color="#938953">Multivariaten Funktionen</font>

> [!IMPORTANT] Wichtig
> Gegeben ist die Funktion: $u(x,y)$
> Die partiellen Ableitungen von u am Punkt $(x, y) \in \mathbb R^2$ sind gegeben durch
> $$
> \large\frac{\partial}{\partial x}u(x,y):=\lim_{h\to 0}\frac{u(x+h,y)-u(x,y)}{h}
> $$
> $$
> \Large\frac{\partial}{\partial y}u(x,y):=\lim_{h\to 0}\frac{u(x,y+h)-u(x,y)}{h}
> $$
> Der *Gradient* einer Funktion u ist gegeben durch
> $$
> \large\text{grad}(u)^T = \nabla u := \begin{pmatrix} u_{x_1} \\ \vdots \\ u_{x_n} \end{pmatrix}.
> $$
> Der Nabla-Operator $\nabla$ und grad beinhalten das Gleiche. Sie unterscheiden sich nur dahingehend ob man die Komponenten in einer Zeile oder einer Spalte zusammenfasst.
> > [!Example] Beispiel
>> Gegeben ist die Funktion:
>>$$
>>\large u(x,y)=x^{2}+x\sin(y)
>>$$
>>Partiellen Ableitungen von u:
>>$$
>>\normalsize\frac{\partial}{\partial x}u(x,y)=2x+\sin(y)
>>$$
>>$$
>>\normalsize\frac{\partial}{\partial y}u(x,y)=x\cos(y)
>>$$
>> Gradient: 
>> $$
>> \normalsize \nabla u = \begin{pmatrix} u_{x} \\ u_{y} \end{pmatrix} = \begin{pmatrix} 2x + \sin(y) \\ x \cos(y) \end{pmatrix}. 
>> $$



## <font color="#245bdb">Integration</font>

![Intergrale](Intergrale.png)

> [!IMPORTANT] 
> Regeln der unbestimmten Integration:
> $$
> \normalsize \int u(x)v'(x) \, dx = u(x)v(x) - \int u'(x)v(x) \, dx 
> $$
>wo $v'(x)$ der "einfache Funktion zum integrieren" ist
>> [!Example] Beispiel
>>$$
>>\normalsize
>>\begin{align*}
>>\int \ln x \, dx &= \int \ln x \cdot 1 \, dx \\
>>&= \int u(x)v'(x) \, dx \quad & u(x) := \ln x , \; v'(x) := 1 \\
>>&= u(x)v(x) - \int u'(x)v(x) \, dx \quad & u'(x) = \frac{1}{x} , \; v(x) = x \\
>>&= x \ln x - \int \frac{1}{x} x \, dx \\
>>&= x \ln x - x \\
>>&= x (\ln x - 1)
>>\end{align*}
>>$$

![](https://www.youtube.com/watch?v=UfbXbij5LW8)

> [!IMPORTANT] Wichtig
> Es sei $F$ eine Stammfunktion von $f$. Man substituiere gemäß $x = g(t)$. Dann ist $dx = g ′ (t) dt$ und es gilt:
>$$
>\large\int{f(x)\,dx}= \int{f(g(t))g'(t)\,dt} = F(g(t))+C
>$$
>> [!Example] Beispiel 
>>$$
>>\normalsize\begin{align*}
>>\int (2 - 3t)^4 \, dt & \\
>>&= -\frac{1}{3} \int g^4 \, dg \quad & \text{Substitution:} \quad g = 2 - 3t \\
>>&= -\frac{1}{3} \int g^4 \, dg \quad & dg = -3 \, dt \\
>>&= -\frac{1}{15} g^5 + C \\
>>&= -\frac{1}{15} (2 - 3t)^5 + C
>>\end{align*}
>>$$

![|center](https://youtu.be/rKGlE4av4-c)
