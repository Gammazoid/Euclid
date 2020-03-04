# Euclid
A rotation-invariant shape recognition system



The idea comes from using polar coordinate to represent the points making a shape. The points are scaled with repesct to the radius.
Rotation is implemented by add/substracting the angle. This solves both problem of rotation and scaling in comparing two shapes. 

From there, one can compare to hard-coded regular polygon (square, equilateral triangle and pentagon) defined by the Enumeration SHAPE.
however, this is limited to regular polygon with the same length edges.


The extension comes from plotting the polar coordinate in the cartesian plane with angle into the x-axis and radius into the y-axis. 
The corner of a polygon are the points closest to 1 and form maximums. A d-polygon has d-maximum and can be approximated with 
a polynomial of degree d.



The next extension currently in development uses a sum of d laplacian distribution to fit the curve in the polar points the 
cartesian plane. Hence generalizing shape recognition to any irregular polygon.




The next phase would generalized the idea to compare any two shapes, polygon or not. 
