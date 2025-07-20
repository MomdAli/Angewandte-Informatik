---
title: Basic Geometry
tags:
  - 3DCV
  - Semester-5
  - Informatik
date: 2025-07-18
---
## What is a Cartesian coordinate system?

A Cartesian coordinate system is a coordinate system that specifies each point uniquely in a plane by a pair of numerical coordinates, which are the signed distances to the point from two fixed perpendicular oriented lines, measured in the same unit of length. In three-dimensional space, a Cartesian coordinate system uses three axes (x, y, z) to define the position of points.

## How are translations, rotations, and scalings represented in 2D and 3D?

### 2D Transformations
- **Translation**: Represented by a vector (tx, ty) that shifts points in the x and y directions.
- **Rotation**: Represented by an angle θ, rotating points around the origin or a specified pivot point.
- **Scaling**: Represented by a scaling factor (sx, sy) that stretches or compresses points in the x and y directions.
$$
% 2D homogeneous transformation
\large
\begin{bmatrix}
x' \\[6pt]
y' \\[6pt]
1
\end{bmatrix}
=
\begin{bmatrix}
\sigma_x & \tau_{xy} & t_x \\[6pt]
\tau_{yx} & \sigma_y & t_y \\[6pt]
0        & 0        & 1
\end{bmatrix}
\begin{bmatrix}
x \\[3pt]
y \\[3pt]
1
\end{bmatrix}
$$
### 3D Transformations
- **Translation**: Represented by a vector (tx, ty, tz) that shifts points in the x, y, and z directions.
- **Rotation**: Represented by angles (α, β, γ) around the x, y, and z axes, respectively.
- **Scaling**: Represented by scaling factors (sx, sy, sz) that stretch or compress points in the x, y, and z directions.
$$
\large
% 3D homogeneous transformation
\begin{bmatrix}
x' \\[6pt]
y' \\[6pt]
z' \\[6pt]
1
\end{bmatrix}
=
\begin{bmatrix}
\sigma_x & \tau_{xy} & \tau_{xz} & t_x \\[6pt]
\tau_{yx} & \sigma_y & \tau_{yz} & t_y \\[6pt]
\tau_{zx} & \tau_{zy} & \sigma_z & t_z \\[6pt]
0        & 0        & 0        & 1
\end{bmatrix}
\begin{bmatrix}
x \\[3pt]
y \\[3pt]
z \\[3pt]
1
\end{bmatrix}
$$
<u>Hint</u>: $\sigma$ represents scaling, $\tau$ represents rotation, and $t$ represents translation.

## What is an affine transformation?
An affine transformation is a linear mapping method that preserves points, straight lines, and planes. In an affine transformation, parallel lines remain parallel after the transformation, and ratios of distances along parallel lines are preserved. It can be represented in both 2D and 3D using matrices, allowing for operations such as translation, rotation, scaling, and shearing.

## Translation

A translation is a type of transformation that shifts every point of an object or coordinate system by the same distance in a specified direction. In mathematical terms, a translation can be represented by adding a vector to each point's coordinates.

![[Translation.svg]]

## Rotation
A rotation is a transformation that turns every point of an object or coordinate system around a fixed point (the center of rotation) by a specified angle. In 2D, this is typically around the origin, while in 3D, it can be around any axis.

![[Rotation.svg]]

### Remark on orthonormal matrices
Why do we assume that the rotation matrix is orthonormal?
The orthonormality condition ensures that the transformation preserves angles and lengths, which is crucial for maintaining the geometric properties of the object being rotated. In 3D, this means that the columns (or rows) of the rotation matrix are orthogonal unit vectors.

## Rotation around an arbitrary point P
To rotate a point around an arbitrary point P, you can follow these steps:
1. Translate the point P to the origin by subtracting its coordinates from the point you want to rotate.
2. Apply the rotation matrix to the translated point.
3. Translate the point back by adding the coordinates of point P.

$$
\large
\begin{bmatrix}
x' \\[6pt]
y' \\[6pt]
1
\end{bmatrix}
=
\begin{bmatrix}
\cos(\theta) & -\sin(\theta) & t_x \\[6pt]
\sin(\theta) & \cos(\theta) & t_y \\[6pt]
0           & 0           & 1
\end{bmatrix}
\begin{bmatrix}
x - P_x \\[3pt]
y - P_y \\[3pt]
1
\end{bmatrix}
+
\begin{bmatrix}
P_x \\[3pt]
P_y \\[3pt]
1
\end{bmatrix}
$$

## Affine map, similarity transformation and rigid body motion

### Affine map
An affine map is a transformation that preserves points, straight lines, and planes. It can be expressed in matrix form, allowing for operations such as translation, rotation, scaling, and shearing. An affine map can be represented in both 2D and 3D using homogeneous coordinates.

#### Properties
1. **Linearity**: Affine maps preserve linear combinations of points.
2. **Parallelism**: Parallel lines remain parallel after an affine transformation.
3. **Ratios of distances**: The ratios of distances along parallel lines are preserved.
4. **Collinearity**: Collinear points remain collinear after the transformation.
-> In general, a square is mapped to a rotated and translated parallelogram.

### Similarity transformation
A similarity transformation is a specific type of affine transformation that preserves angles and distances, meaning it maintains the shape of geometric figures while allowing for scaling. In 2D, it can be represented by a matrix that combines rotation, scaling, and translation, while in 3D, it involves a similar combination of transformations.

$$
\large
P' = s \cdot R \cdot P + T \text{ with } R =
\begin{pmatrix} 
r_{11} & r_{12} \\[6pt]
r_{21} & r_{22} \\[6pt]
\end{pmatrix}
\text{ and } T =
\begin{pmatrix}
r_{10} \\[6pt]
r_{20} \\[6pt]
\end{pmatrix}
$$

#### Properties
1. **Ratios, angles and parallel lines** are preserved.
2. **Length, directions and orientations** can change.
3. To determine its four parameters $r_{10}$, $r_{20}$, $s$, $\alpha$ at least two corresponding points in both coordinate systems are necessary.
-> A square is mapped to a scaled, rotated and translated square.

### Rigid body motion
Rigid body motion refers to the movement of a solid object in space without deformation. It includes translations and rotations but does not involve scaling or shearing. In mathematical terms, rigid body motion can be represented by a combination of translation and rotation matrices, ensuring that the distances between points remain constant.

$$
\large
P' = A \cdot P + T \text{ with } R =
\begin{pmatrix} 
r_{11} & r_{12} \\[6pt]
r_{21} & r_{22} \\[6pt]
\end{pmatrix}
\text{ and } T =
\begin{pmatrix}
r_{10} \\[6pt]
r_{20} \\[6pt]
\end{pmatrix}
$$

#### Properties
1. It is a combination of a rotation, translation and reflection (if $det(A) = -1$).
2. Ratios, angles, lengths and parallel lines are preserved.
3. Orientation can change, if $det(A) = -1$. (aka **improper rigid motion**)
-> A square is mapped to a rotated and translated square.


### Preview
![[Transformations.svg|center]]
> Projective transformation is covered in [[Projective Geometry]]

