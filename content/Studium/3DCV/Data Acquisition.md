---
title: Data Acquisition
tags:
  - 3DCV
  - Semester-5
  - Informatik
date: 2025-07-19
publish: true
---
## Taxonomy of Acquisition Methods
|Axis|Categories|
|---|---|
|**Tactile vs. Non-tactile**|Tactile (e.g., coordinate measuring machines) vs. non-tactile (optical scanners, sonar)|
|**Destructive vs. Non-destructive**|Destructive (e.g., slicing in tomography) vs. non-destructive (laser scanning, stereo)|
|**Active vs. Passive**|Active (projects its own illumination: structured light, ToF) vs. Passive (relies on ambient light: stereo)|
|**Optical vs. Non-optical**|Optical (laser, stereo cameras) vs. non-optical (ultrasound, radar, MRI)|
On a rough scale of resolution ↔ measurement range:
- **Interferometry, AFM, TEM** - sub-nanometre to nanometre
- **Laser line triangulation, structured light** - micrometre to millimetre
- **Stereo triangulation, stereo vision** - micrometre to centimetre
- **Time-of-Flight lidar** - centimetre to meters
- **Radar** - centimetres to kilometres
---

# Stereo Vision (Passive, Optical)

## Basics of Disparity and Parallax
Stereoscopic vision and depth perception is based on:
- Disparity: the difference in position of an object seen by two (or more) images.
- Parallax: Change of position of an object, as seen from different perspectives,
	- different image coordinates from identical world coordinates of an object,
	- different camera coordinates from identical world coordinates of an object.

Both images together form the so-called **stereogram**, the individual images are so-called **half-images**.

### Disparity
Differences in two (or more) images of the same object.
1. Point disparity - Horizontal and vertical disparity
	- Horizontal disparity: Difference in horizontal position of the same point in two images. (e.g., left and right eye)
	- Vertical disparity: Difference in vertical position of the same point in two images. (e.g. when the viewing directions are not perfectly co-planer)
2. Orientation disparity respectively general form and size differences between both images.
	- Leads always to point disparities.
3. Gray value disparity and shading disparity caused by different Normals of object points in diffuse reflection in the images. 
![[Gray-value-disparity.png|center|400]]
4. Photometric disparity caused by surface reflectivity, aka specular reflection
![[Photometric-disparity.png|center|400]]
5. Monocular occlusion at object edges (Difficult for steroe algorithms)
![[monocular-occlusion.png|center|400]]

***Remark***: The camera calibration must be very accurate, because small errors in the calibration lead to large errors in the reconstruction.

### Parallax
**Parallax** refers to how an object’s apparent position shifts when viewed from two locations; in the “normal case” only the horizontal component matters.

## Depth Reconstruction Equations

For a scene point $P$ with image coordinates $(x'_{1},y'_1)$ in the left camera and $(x'_{2},y'_2)$ in the right, the depth $z$ is
$$
\large
z = -\frac{f\,b}{p'_{x}}= -\frac{f\,b}{x'_1-x'_2},
$$
and the horizontal/vertical coordinates follow
$$
\large
x = -\frac{z\,x'_{1}}{f} , y = -\frac{z\,y'_{1}}{f}.
$$
Larger disparity $p'_{x} \Rightarrow z$ (closer object).

## Error Propagation
Depth accuracy degrades with distance $z$ and depends on two ratios
$$
\large
\text{image scale } m_{b} = \frac{z}{f}, \quad \text{base ratio } m_{y} = \frac{b}{z} 
$$
Roughly, $\large \sigma_{z} \propto \frac{z^{2}}{f\,b}\sigma_p:$ errors grow quadratically with distance.  

## Laser Scanning (Active Optical)
Laser-based scanners elevate resolution and range by **actively** projecting light and measuring its return. There are two main principles:
1. **Time-of-Flight (ToF)**
	- Emit a short laser pulse at time $t_0$, detect its return at $t_1$.
	- Distance $d = \frac{c(t_1 - t_0)}{2}$, where $c$ is the speed of light.
	- Simple concept, but needs very fast timing electronics; typical accuracy: centimetres.
2. **Optical triangulation (Structured Light / Laser Triangulation)**
- A laser line or pattern is projected from a known position $O_2$​; a camera at $O_1$​ sees the distorted pattern on the object.
- By measuring angles $\varphi_1​,\varphi_2$​ of projection vs. observation, depth $z$ is recovered via simple trigonometry:    
$$
\large
z \;=\; b\,\frac{\sin\varphi_1\,\sin\varphi_2}{\sin(\pi - \varphi_1 - \varphi_2)},
$$
where $b = |O_1O_2|$ is the fixed baseline.
- Offers micrometre–millimetre accuracy over moderate ranges, but requires very precise calibration of both camera and projector.

## Calibration and Sensor Localization
All active and passive methods rely on knowing the **intrinsic** (focal length, principal point, distortion) and **extrinsic** (rotation $R$, translation $N$) parameters of the sensor(s). Calibration typically uses a calibration object with known 3D “ground truth” points whose image coordinates are measured, yielding a linear or non-linear system to solve for the parameters

## Exam Questions to Prepare
- **Classify** different 3D acquisition methods by tactile/non-tactile, active/passive, optical/non-optical.
- Define **disparity** and **parallax**, and explain why vertical disparity vanishes in the normal stereo case.
- **Derive** the normal-case stereo reconstruction equations and discuss error propagation ($\sigma_z$).
- Explain the **principle of ToF** and derive $d=c(t_1-t_0)/2$.
- Explain **optical triangulation** (structured light) geometrically, including its trigonometric depth formula.
- Describe the two-stage pipeline: **sensor localization** and **3-D data localization** relative to the sensor.