# Added files:
### AbstractCollager:
- added because it is the main collager implementation, and 
CollagerPPM extends this file so the controller doesn't compile without
the implementation of CollagerPPM

### CollagerPPM:
- The currentCollager in the controller is declared as a CollagerPPM.

### CollagerUtil:
- Controller utilizes methods from this class to read the collager files

### CollagerView:
- Needed to represent the view in the collager

### ILayer:
- To represent a layer in a collager, used in the controller to declare example layers
for the controller to put into the collager

### Layer:
- Needed because methods in the ILayer class return type Layer instead of ILayer, so methods used on ILayer instead 
return type Layer

### PixelColor:
- used to represent images in the controller and modify PixelColor values by using methods in this class