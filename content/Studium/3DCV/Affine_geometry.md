---
title: Affine Geometry
tags:
  - 3DCV
  - Semester-5
  - Informatik
date: 2025-04-30
---
## 🧭 Introduction

Affine Geometry studies geometric transformations that preserve **points, straight lines, and planes**. While lengths and angles may change, **parallelism** and **ratios along lines** remain invariant. This makes affine geometry fundamental in computer vision and rendering systems.

---

## 2D Affine Geometry

### Translation

A point $P = (p_1, p_2)^T$ is translated by a vector $T = (t_1, t_2)^T$:

$$
P' = P + T = \begin{bmatrix} p_1 + t_1 \\ p_2 + t_2 \end{bmatrix}
$$

Used when coordinate systems shift position but retain orientation.

---

### Rotation

If both systems have the same origin and differ only in orientation:

$$
P' = R(\varphi) \cdot P
$$

With:

$$
R(\varphi) = \begin{bmatrix}
\cos\varphi & -\sin\varphi \\
\sin\varphi &  \cos\varphi
\end{bmatrix}
$$

👉 Properties of an **orthonormal matrix** $R$: $$ R^{-1} = R^T $$
- Length and angles are preserved

---

### Scaling

Uniform or non-uniform stretching:

$$
P' = S \cdot P,\quad S = \begin{bmatrix}
\lambda_1 & 0 \\
0 & \lambda_2
\end{bmatrix}
$$

Can distort angles and lengths.

---

### Shearing

Deforms the shape by slanting:

$$
P' = S \cdot P,\quad S = \begin{bmatrix}
1 & s_1 \\
s_2 & 1
\end{bmatrix}
$$

---

### Reflections

Several common 2D reflection matrices:
- About $y = x$: $$ \begin{bmatrix} 0 & 1 \\ 1 & 0 \end{bmatrix} $$
- About $y = -x$: $$ \begin{bmatrix} 0 & -1 \\ -1 & 0 \end{bmatrix} $$
- About x-axis: $$ \begin{bmatrix} 1 & 0 \\ 0 & -1 \end{bmatrix} $$

---

### General Form of Affine Transformations

$$
P' = A \cdot P + T
$$

Where:
- $A \in \mathbb{R}^{2 \times 2}$ : Linear part
- $T \in \mathbb{R}^2$ : Translation vector

---

### Properties

1. **Affine invariance**: Ratios and linear combinations are preserved
2. **Parallelism preserved**
3. **Lengths and angles may change**
4. **3 non-collinear point pairs are needed** to define the transformation

---

### Similarity Transformations

$$
P' = s \cdot R \cdot P + T
$$

Where:
- $s$: Scaling factor
- $R$: Rotation matrix
- $T$: Translation vector

**Preserves**: ratios, angles, and parallel lines

---

### Rigid Body Motion

$$
P' = A \cdot P + T, \quad \text{with } A \text{ orthogonal}
$$

Preserves:
- Ratios
- Angles
- Lengths
- Parallelism

Reflection allowed if $$ \det(A) = -1 $$

---

### Homogeneous Coordinates

Lift 2D points into 3D for unified matrix transformations:

$$
(x, y)^T \rightarrow (x, y, 1)^T
$$

Affine transformations as 3×3 matrices:

#### Translation:
$$
\begin{bmatrix}
1 & 0 & t_1 \\
0 & 1 & t_2 \\
0 & 0 & 1
\end{bmatrix}
$$

#### Rotation:
$$
\begin{bmatrix}
\cos\varphi & -\sin\varphi & 0 \\
\sin\varphi &  \cos\varphi & 0 \\
0 & 0 & 1
\end{bmatrix}
$$

---

## 3D Affine Geometry
### Translation, Scaling
#### Translation Matrix:
$$
T(tx, ty, tz) = \begin{bmatrix}
1 & 0 & 0 & tx \\
0 & 1 & 0 & ty \\
0 & 0 & 1 & tz \\
0 & 0 & 0 & 1
\end{bmatrix}
$$

#### Scaling Matrix:
$$
S(\lambda_1, \lambda_2, \lambda_3) = \begin{bmatrix}
\lambda_1 & 0 & 0 & 0 \\
0 & \lambda_2 & 0 & 0 \\
0 & 0 & \lambda_3 & 0 \\
0 & 0 & 0 & 1
\end{bmatrix}
$$

---

### Rotation Around Coordinate Axes

#### $R_z(\varphi)$  – Rotation around Z-axis:
$$
\begin{bmatrix}
\cos\varphi & -\sin\varphi & 0 & 0 \\
\sin\varphi &  \cos\varphi & 0 & 0 \\
0 & 0 & 1 & 0 \\
0 & 0 & 0 & 1
\end{bmatrix}
$$

(Analogous for  $R_x(\varphi) and R_y(\varphi) )$

---

### Rotation Around Arbitrary Axis

1. Rotate the axis to align with z-axis
2. Apply  $R_z(\psi)$
3. Invert the alignment transformation

Final matrix:
$$
M_b(\psi) = T(a) \cdot R_z(\theta) \cdot R_y(\phi) \cdot R_z(\psi) \cdot R_y(-\phi) \cdot R_z(-\theta) \cdot T(-a)
$$

---

### General Form of 3D Affine Transformation
$$
P' = A \cdot P + T
\quad\text{or in homogeneous coordinates:}\quad
P' = M \cdot P
$$
Where $A \in \mathbb{R}^{3 \times 3} ,  T \in \mathbb{R}^3 ,  M \in \mathbb{R}^{4 \times 4}$

---

## Sources

- Hartley & Zisserman, *Multiple View Geometry in Computer Vision*, 2nd ed.
- Wikipedia: [Affine Transformation](https://en.wikipedia.org/wiki/Affine_transformation), [Homogeneous Coordinates](https://en.wikipedia.org/wiki/Homogeneous_coordinates)
- Szeliski, R. (2010). *Computer Vision: Algorithms and Applications*
