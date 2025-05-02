---
title: Stereo Camera Implementation
tags:
  - 3DCV
  - Semester-5
  - Informatik
date: 2025-05-02
---
## Part 1: Second Perspective Camera Model

### Camera Parameters

The implementation uses two instances of the `PerspectiveCamera` class to create a stereo pair:

```cpp
class StereoCamera : public SceneObject {
private:
    PerspectiveCamera leftCam;
    PerspectiveCamera rightCam;
    // ...
};
```

Each perspective camera has the following key parameters:
- **Center of projection (N)**: Position of the camera
- **Rotation matrix (R)**: Orientation of the camera
- **Focal length (f)**: Distance from center of projection to image plane
- **Field of view (fovY)**: Vertical field of view in degrees
- **Aspect ratio**: Width-to-height ratio of the image plane

### Camera Initialization

The stereo camera is initialized with two perspective cameras:

```cpp
StereoCamera::StereoCamera(const PerspectiveCamera& left,
                          const PerspectiveCamera& right)
    : leftCam(left), rightCam(right)
{
    type = SceneObjectType::ST_STEREO_CAMERA;
}
```

In the GLWidget initialization, the cameras are set up with:

```cpp
QMatrix4x4 camRotation;
camRotation.setToIdentity();
QVector4D camPosition = E1 + E2 + 3.0f * E3;
float focalLength = 1.0f;
float fovY = 54.0f;
float aspectRatio = 16/9;

PerspectiveCamera camL(camPosition, camRotation, focalLength, fovY, aspectRatio);
PerspectiveCamera camR(camPosition + QVector4D(1.0f, 0, 0, 0), camRotation, focalLength, fovY, aspectRatio);
sceneManager.push_back(new StereoCamera(camL, camR));
```

### Visualization

The camera visualization shows:
- Centers of projection (red for left camera, cyan for right camera)
- Image planes
- Viewing frustums
- Camera axes

The `draw` method in StereoCamera delegates to each camera's draw method:

```cpp
void StereoCamera::draw(const RenderCamera& renderer,
                        const QColor& /*color*/,
                        float lineWidth) const
{
    leftCam.draw(renderer, QColor(255, 0, 0), lineWidth);
    rightCam.draw(renderer, QColor(0, 255, 255), lineWidth);
    
    // Draw reconstructed points and edges...
}
```

## Part 2: Perspective Reconstruction

### Stereo Vision Normal Case

The normal case of stereo vision refers to a configuration where:
- Two cameras have parallel optical axes
- The baseline (line connecting camera centers) is perpendicular to the viewing direction
- Both cameras have identical intrinsic parameters

In this configuration, corresponding points in the left and right images have the same y-coordinate, simplifying the search for correspondences to a 1D problem.

### Reconstruction Process

The `reconstruct` method implements the stereo vision normal case:

```cpp
void StereoCamera::reconstruct(const std::vector<SceneObject*>& scene)
{
    // Clear previous reconstructions
    reconstructedPoints.clear();
    hexes.clear();

    // Compute camera parameters
    QVector3D oL = leftCam.getCenter().toVector3D();
    QMatrix4x4 RL = leftCam.getRotation();
    float fL = leftCam.getFocalLength();
    // ...similar for right camera

    // Calculate baseline
    float baseline = QVector3D::dotProduct(oR - oL, cxL);
    const float depthScale = 0.5f;

    // For each object in the scene
    for (auto* obj : scene) {
        auto* hex = dynamic_cast<const Hexahedron*>(obj);
        if (!hex) continue;
        
        // Get original points
        auto pts4 = hex->getPoints();
        std::vector<QVector4D> ptsRe(pts4.size(), QVector4D(0,0,0,0));

        // For each point in the object
        for (size_t i = 0; i < pts4.size(); ++i) {
            // Project points onto image planes
            QVector3D qL = leftCam.projectPoint(pts4[i]);
            QVector3D qR = rightCam.projectPoint(pts4[i]);
            
            // Calculate image coordinates (u,v)
            QVector2D uvL = ...;
            QVector2D uvR = ...;
            
            // Check if points are within view frustums
            if (out_of_bounds) continue;
            
            // Calculate disparity
            float disp = uvL.x() - uvR.x();
            if (disp too small) continue;
            
            // Reconstruct 3D point using disparity
            float z = -fL * baseline / disp;
            z *= depthScale;
            float x = -z * uvL.x() / fL;
            float y = -z * uvL.y() / fL;
            
            // Transform back to world coordinates
            QVector3D Pw = oL + RL.map(QVector3D(x,y,z));
            ptsRe[i] = QVector4D(Pw, 1.0f);
        }
        
        // Store reconstructed points
        reconstructedPoints.push_back(std::move(ptsRe));
        hexes.push_back(hex);
    }
}
```

### Key Reconstruction Equations

The main equations used for reconstruction are:
- z = -f·b/disparity
- x = -z·x'/f
- y = -z·y'/f

Where:
- z, x, y are 3D coordinates relative to the left camera
- f is the focal length
- b is the baseline (distance between cameras)
- disparity is x'₁ - x'₂ (difference in x-coordinates of corresponding image points)
- x', y' are image coordinates

### Averaging Both Camera Reconstructions

To make scaling operations affect the object relative to both cameras (rather than just the left camera), we calculate reconstructions from both cameras and average them:

```cpp
// Calculate reconstruction from left camera view
QVector3D PwL = oL + RL.map(QVector3D(x,y,z));

// Calculate reconstruction from right camera view 
float xR = -z * uvR.x() / fR;
float yR = -z * uvR.y() / fR;
QVector3D PwR = oR + RR.map(QVector3D(xR,yR,z));

// Average the two positions
QVector3D Pw = (PwL + PwR) * 0.5f;
```

This ensures the reconstructed point moves relative to both cameras when adjusting the depth scale.

### Triangulation Method

For non-ideal cases (e.g., when camera axes aren't perfectly parallel), the implementation includes a triangulation method to find the 3D point closest to the two viewing rays:

```cpp
QVector3D StereoCamera::triangulate(const QVector3D& o1, const QVector3D& d1,
                                  const QVector3D& o2, const QVector3D& d2) const
{
    // Find closest points on two skew rays
    QVector3D r = o2 - o1;
    float a = QVector3D::dotProduct(d1,d1);
    float b = QVector3D::dotProduct(d1,d2);
    float c = QVector3D::dotProduct(d2,d2);
    float d = QVector3D::dotProduct(d1,r);
    float e = QVector3D::dotProduct(d2,r);
    float den = a*c - b*b;
    
    // Handle near-parallel rays
    if (std::fabs(den) < 1e-6f) return 0.5f*(o1+o2);
    
    // Calculate parameters for closest points
    float t1 = (d*c - b*e)/den;
    float t2 = (a*e - b*d)/den;
    
    // Get closest points on rays
    QVector3D p1 = o1 + t1*d1;
    QVector3D p2 = o2 + t2*d2;
    
    // Return midpoint
    return 0.5f*(p1+p2);
}
```

## Part 3: Error Propagation

### Camera Misalignment

To demonstrate error propagation from calibration errors, the implementation applies a small rotation (1°) to the right camera:

```cpp
void StereoCamera::misalignRightCamera(float angleInDegrees)
{
    // Create rotation matrix for misalignment
    QMatrix4x4 misalignmentRotation;
    misalignmentRotation.setToIdentity();
    misalignmentRotation.rotate(angleInDegrees, 0, 1, 0); // Rotate around Y-axis
    
    // Apply to right camera
    QMatrix4x4 currentRotation = rightCam.getRotation();
    rightCam.setRotation(currentRotation * misalignmentRotation);
}
```

This is called in the constructor:
```cpp
StereoCamera::StereoCamera(const PerspectiveCamera& left,
                           const PerspectiveCamera& right)
{
    // ...
    misalignRightCamera(1.0f);
}
```

### Error Measurement and Visualization

The implementation measures and visualizes errors resulting from camera misalignment:

1. **Y-Parallax**: In a perfect normal case, corresponding points should have the same y-coordinate. With misalignment, y-parallax emerges:
   ```cpp
   float yParallax = std::fabs(uvL.y() - uvR.y());
   ```

2. **Reconstruction Error**: The difference between points reconstructed from left and right views:
   ```cpp
   float reconstructionError = (PwL - PwR).length();
   ```

3. **Triangulation Error**: In `triangulate()`, we calculate the distance between closest points on rays:
   ```cpp
   float error = (p1 - p2).length();
   ```

4. **Error Visualization**: Error magnitude is stored in the w component of reconstructed points and used for color coding:
   ```cpp
   float totalError = reconstructionError + yParallax;
   ptsRe[i] = QVector4D(Pw, 1.0f + totalError);
   ```

   In the `draw` method:
   ```cpp
   float errorFactor = (p4.w() > 1.0f) ? (p4.w() - 1.0f) : 0.0f;
   QColor pointColor = QColor(255, 0, std::max(0, int(255 * (1.0f - errorFactor))));
   ```

### Observations on Error Propagation

The implementation demonstrates key properties of error propagation in stereo vision:

1. **Depth Error Growth**: Errors in depth reconstruction grow quadratically with distance from the cameras. Points farther away show greater reconstruction error.

2. **Y-Parallax**: Camera misalignment introduces y-parallax, which shouldn't exist in a perfect normal case.

3. **Error Visualization**: Points with higher reconstruction error are colored differently (shifting from magenta toward red), providing visual feedback on error magnitude.

4. **Error vs. Baseline Ratio**: Following the error analysis from the lecture, the error in z-coordinate is inversely proportional to the base ratio (b/z). This is evident in the reconstructed scene, where distant objects have higher reconstruction errors.

## Technical Implementation Details

### Data Structures

The StereoCamera class maintains:
- Two PerspectiveCamera objects (left and right)
- Reconstructed points grouped by object
- References to original objects for validation

```cpp
class StereoCamera : public SceneObject {
private:
    PerspectiveCamera leftCam;
    PerspectiveCamera rightCam;
    std::vector<std::vector<QVector4D>> reconstructedPoints;
    std::vector<const Hexahedron*> hexes;
    // ...
};
```

### Points and Edges Rendering

The implementation draws:
1. Valid reconstructed points (color-coded by error magnitude)
2. Edges connecting valid points
3. Projections of 3D objects onto both image planes

```cpp
void StereoCamera::draw(const RenderCamera& renderer, const QColor& color, float lineWidth) const
{
    // Draw cameras
    leftCam.draw(renderer, QColor(255, 0, 0), lineWidth);
    rightCam.draw(renderer, QColor(0, 255, 255), lineWidth);

    // Draw reconstructed points and edges
    for (size_t h = 0; h < hexes.size(); ++h) {
        // Draw points with error-based coloring
        // Draw valid edges between reconstructed points
    }
}
```

### Projections Visualization

The implementation shows projections of 3D objects onto both image planes:

```cpp
void StereoCamera::drawProjections(const RenderCamera& renderer,
                                  const std::vector<SceneObject*>& scene) const
{
    leftCam.drawProjections(renderer, scene, Qt::red);
    rightCam.drawProjections(renderer, scene, Qt::cyan);
}
```