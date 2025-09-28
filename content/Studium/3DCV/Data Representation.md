---
title: Data Representation
tags:
  - 3DCV
  - Semester-5
  - Informatik
date: 2025-07-19
publish: true
---
## Overview 
In a 3D reconstruction pipeline, **data representation** determines how we store and manipulate both the shape of objects and any associated attributes (color, normals, texture coordinates, etc.). At each stage—acquisition, preprocessing, reconstruction, and rendering—the chosen representation affects efficiency, precision, and the types of algorithms we can apply.

A complete representation has two facets:

- **Topology**, the abstract “connectivity” graph (which vertices are joined by edges, which edges form faces, etc.).
- **Geometry**, the embedding of that graph in ℝ³ via coordinates and per-vertex or per-face attributes.

---

## Topology vs. Geometry, Explicit vs. Implicit

A robust understanding of representations begins with these distinctions:

- **Topology** captures _combinatorial_ structure; geometry gives _metric_ detail.
- An **explicit** topology (e.g. a mesh’s adjacency lists) names each connection directly; an **implicit** topology (e.g. a distance field zero‐set) encodes structure in the form of level-sets or graph-implicit rules.
- **Explicit geometry** stores point coordinates or control points (as in splines); **implicit geometry** is defined by the zeros of a function $f(x,y,z)=0$ (e.g. signed distance fields or height maps).

---

## Point‐Cloud and Spatial Partitioning Structures

When data is unstructured (just a collection of 3D points), we often impose spatial data structures for search, queries, and neighbor relations.

### Uniform Grids

A **uniform Cartesian grid** partitions space into equally sized voxels (cubes). Each cell simply holds a list of points or a scalar value. This is ideal for dense, regularly sampled data (e.g. volume images), but wastes memory if the scene is sparse.

### Quadtrees and Octrees

To adapt to varying density, **quadtrees** (in 2D) and **octrees** (in 3D) recursively subdivide space:

1. Start with a root cell bounding all points.
2. If a cell contains more than a threshold $⁡n_{\max}$ points, split it into 4 (quadtree) or 8 (octree) children.
3. Continue until each leaf cell is “small enough.”

The maximum tree depth $d$ satisfies roughly

$$
\large
d \le \log_2\!\bigl(\tfrac{\text{bounding‐size}}{\text{min‐cell‐size}}\bigr) + \tfrac32
$$

Octrees give fast range queries and memory proportional to scene complexity, but pointer overhead and tree imbalance can be drawbacks.
A quadtree of depth $d$ that stores a set of $n$ points has $O((d+1)n)$ nodes and can be constructed on $O((d+1)n)$.

### k-d Trees

A **k-d tree** partitions points in ℝᵏ by recursively selecting an axis-aligned splitting plane:

- At each node, pick one coordinate axis (often cycling $x,y,z$).
- Split the point set at the median along that axis.
- Recursively build left/right subtrees.

Well balanced, a k-d tree yields $O(\log n)$ nearest-neighbor queries. Yet, axis-aligned splits may cut through geometry inefficiently.

### BSP Trees

**Binary Space Partitioning (BSP) trees** generalize k-d trees by allowing arbitrary splitting planes of the form
$$
\large
\{\,\mathbf{p}\mid \mathbf{n}^\top \mathbf{p} = d\,\}
$$
This flexibility lets splits align with scene geometry (e.g. walls), useful in ray tracing and constructive solid geometry, at the cost of more complex tree‐building.

---

## Meshes: Manifolds, Euler Characteristic, and Operations

A **mesh** $M=(V,E,F)$ consists of vertices $V$, edges $E$, and faces $F$. A mesh is a **(two‐manifold)** if every edge is incident to at most two faces and the neighborhood of each vertex is topologically a disk (or half‐disk on a boundary).

The fundamental invariant is the **Euler characteristic**:

$$
\large |V| - |E| + |F| \;=\; 2\,(1 - g),
$$

where gg is the genus (number of “handles”) of the surface. For a sphere $g=0$, this gives 2; for a torus $g=1$, it gives 0.

### Winged-Edge vs. Half-Edge vs. Directed-Edge

Several data structures encode mesh connectivity; each trades off memory for traversal speed:

- **Winged-Edge**  
    Each edge record stores pointers to the two incident faces and the four neighboring edges that “wing” around it. Traversals are constant‐time but pointers per edge can be large (8 or more).
- **Half-Edge**  
    Split each edge into two oppositely oriented half-edges. A single half-edge record holds:
    1. `halfedge.opposite` → other half of the same edge
    2. `halfedge.next` → next half-edge around the face
    3. `halfedge.face` → incident face
    4. `halfedge.vertex` → its destination vertex  
        Additionally, each vertex points to one outgoing half-edge, and each face to one of its boundary half-edges. This structure supports **all** common mesh operations—vertex/edge/face adjacency, one-ring neighborhood enumeration, edge flips, etc.—in constant time.

- **Directed-Edge (Triangle-Only)**  
    Optimized for pure triangle meshes: number half-edges implicitly by face and local index (3 per face). Only two fields need storage per half-edge:
    - `opposite_edge` (an integer index)
    - `to_vertex` (an integer index)  
        The face and `next` pointer are computed algebraically:    
    
    ```text
    face(h)    = floor(h / 3),
    next(h)    = (h % 3 == 2) ? (h − 2) : (h + 1).
    ```
    
    This compaction reduces memory at the expense of slightly more computation.

---

## Edge‐Collapse and Local Operations

An **edge-collapse** is a local mesh simplification primitive that contracts an edge $(u,v)$ to a single vertex ww. All faces incident to that edge are removed, and all other faces formerly touching $u$ or $v$ are re-triangulated around $w$. This operation:

1. Reduces $|V|$ by 1 and $|E|$, $|F|$ accordingly.
2. Preserves manifoldness if performed carefully (only edges whose collapse does not create non-manifold connectivity).

Edge-collapse underlies **progressive meshes** and many simplification algorithms.

---

## Enumerating the One‐Ring Neighborhood

To apply a function $f$ to each vertex in the one-ring of a central vertex $v$:

```cpp
HalfEdge* h_start = v->outgoing_halfedge;
HalfEdge* h = h_start;
do {
    Vertex* u = h->opposite->vertex;
    f(u);
    h = h->opposite->next;
} while (h != h_start);
```

- Start at any outgoing half-edge `h_start` from $v$.    
- At each step, `h->opposite` jumps across the edge, and `->next` moves around the adjacent face.
- Loop until you return to `h_start`.

This runs in time proportional to the degree of $v$.

---

## Memory and Performance Considerations

- A **winged-edge** entry can cost 32 bytes or more per edge (four wing pointers, two face pointers, plus vertex pointers).
- A **half-edge** can be implemented in about 24 bytes per half-edge (4 pointers × 8 bytes each) plus per‐vertex/face overhead.
- A **directed-edge** (triangle only) can be as low as 12 bytes per half-edge (two 4-byte integers plus padding) if indices are 32 bits.