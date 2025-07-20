---
title: Projective Geometry
tags:
  - 3DCV
  - Semester-5
  - Informatik
date: 2025-07-19
---
## Definition

Projective Geometry is a branch of mathematics that studies the properties of geometric objects that are invariant under projective transformations. It extends the concepts of Euclidean geometry by considering points at infinity and the relationships between lines, planes, and higher-dimensional objects.

A ***Projection*** is a mapping, that maps a space of dimension $n$ to a space of dimension $< n$.
Because displays and camera are 2d devices, 3d-objects are plotted on them by projecting them onto a 2d plane.

| ![[Projection-infinity.svg]] | ![[Projection-centre.svg]] |
| ---------------------------- | -------------------------- |

### Classification of projections
- Abbreviations:
	- projection ray = p-ray
	- projection plane = p-plane
	- principal axes = p-axes
- Direction of p-ray:
	1. central -> relative pose of p-axes to p-plane. 
		- Number of intersections of p-axes with p-plane.
	2. parallel -> angle of p-ray to p-plane. 
		- orthogonal and skew, relative pose of p-axes/-ray to p-plane. Angle of p-axes/-ray to p-plane.
---

## Parallel Projection

- All ***projection rays*** are parallel to one direction.
- The ***centre of projections*** is a point at infinity.
- **Disadvantage**: Less realistic.
- **Advantage**: Allows for exact measurements from the image.
![[Parallel-Projection-example.png]]

---
## Perspective Projection (Central Projection)

Perspective projection models how a 3D scene is captured by a pinhole camera (or the human eye). Rays emanate from a single **centre of projection** and pass through points on the **image plane**. Distant objects appear smaller; parallel lines converge to vanishing points.

### Pinhole Camera Model

#### Geometry
- **centre of projection** (camera centre) at O.  
- **Image plane** at distance f (the focal length) in front of O.
#### Projection Equations
Given a 3D point $(X,Y,Z)$ in camera coordinates $(Z>0)$, its image $(x,y)$ on the image plane at $Z=f$ is

$$
x = \frac{f\,X}{Z},
\qquad
y = \frac{f\,Y}{Z}.
$$

### Homogeneous Coordinates

Embed $(X,Y,Z)$ and $(x,y)$ into projective space:

$$
\begin{bmatrix}x\\y\\1\end{bmatrix}
\;\propto\;
\underbrace{\begin{bmatrix}
f & 0 & 0 & 0\\
0 & f & 0 & 0\\
0 & 0 & 1 & 0
\end{bmatrix}}_{P_{\text{int}}}
\begin{bmatrix}X\\Y\\Z\\1\end{bmatrix}.
$$

- ($P_{\text{int}}$) is the $3\times4$ **intrinsic** camera matrix for focal length $f$.

In full generality:

$$
K = 
\begin{bmatrix}
\alpha_x & s       & c_x\\
0        & \alpha_y & c_y\\
0        & 0        & 1
\end{bmatrix},
\quad
P = K\,\bigl[R \mid \mathbf t\bigr],
$$

where  
- $\alpha_x = f\,m_x,\;\alpha_y = f\,m_y$ (focal length $\times$ pixel scales),  
- $s$ = skew,  
- $(c_x,c_y)$ = principal point,  
- $[R\mid \mathbf t]$ = extrinsic rotation and translation from world to camera.

### Intrinsic & Extrinsic Parameters

- **Extrinsic** $[R\mid \mathbf t]$: maps world point $\mathbf X_w$ to camera frame $\mathbf X_c = R\,\mathbf X_w + \mathbf t$.  
- **Intrinsic** $K$: maps camera-frame $\mathbf X_c$ to image coordinates.

Full mapping in homogeneous coordinates:

$$
\begin{bmatrix}u\\v\\1\end{bmatrix}
\;\propto\;
K\,[\,R\mid \mathbf t\,]
\begin{bmatrix}X_w\\Y_w\\Z_w\\1\end{bmatrix}.
$$

### Key Properties & Effects

- **Depth division**: projection is **nonlinear** in $(X,Y,Z)$ because of division by $Z$.  
- **Vanishing points**: a 3D direction $\mathbf d=(d_x,d_y,d_z)$ projects to  

  $$
  \begin{pmatrix}u\\v\end{pmatrix}
  =
  \begin{pmatrix}
    \displaystyle \frac{\alpha_x\,d_x + s\,d_y}{d_z} + c_x \\[6pt]
    \displaystyle \frac{\alpha_y\,d_y}{d_z} + c_y
  \end{pmatrix}.
  $$

- **Straight lines** in 3D map to straight lines in the image (since projection is linear in homogeneous coords).  
- **Parallel lines** in space meet at the same vanishing point.

### Common Exam Questions

1. **Derive** the perspective equations $x = fX/Z,\;y = fY/Z$.  
2. **Explain** why projection is _not_ an affine transform.  
3. **Define** the camera matrix $P$ and its decomposition $P = K [R\mid \mathbf t]$.  
4. **Show** how a 3D line or plane is mapped under perspective projection.  
5. **Compute** the vanishing point for a given direction vector.  
6. **Discuss** how intrinsic parameters affect the image.

### Example: Vanishing Point of the x-axis

For direction $\mathbf d=(1,0,0), as d_z\to0$ the image point $(u,v)$ tends to

$$
u \;\to\; \lim_{d_z\to0}\frac{\alpha_x\,1 + s\cdot0}{d_z} + c_x \;=\; \infty,
\quad
v = c_y.
$$

So all lines parallel to the x-axis converge to the horizontal line $v=c_y$.

### Exam Tips

- **Draw** the camera centre, object point, image plane, and projection ray.  
- **Explain** each parameter: $f$ controls magnification; $(c_x,c_y)$ shifts the image centre; s handles sensor skew.  
- **Contrast** with orthographic (parallel) projection, where you omit division by $Z$.  
- **Highlight** that recovering 3D from a single image is only possible up to an unknown scale (depth ambiguity).

