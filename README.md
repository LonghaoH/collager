# collager
This program functions like a photo-editing software, allowing the user to create and modify images 
with different functionalities.

Supported image commands:

new-project canvas-height canvas-width\
    - creates a new project with the given name and given dimensions.\
    - every project has a white background by default.

load-project path-to-project-file\
    - loads a project into the program.

save-project\
    - save the project as one file.

add-layer layer-name\
    - adds a new layer with the given name on top of the whole project.

add-image-to-layer layer-name image-name x-pos y-pos\
    - adds a given image to the given layer at the specified position.

set-filter layer-name filter-option\
    - sets the filter of the given layer with the given filter.

save-image file-name\
    - save the result of applying all filters on the image.

quit or q\
    - quits the project and loses all unsaved work.

### --Controller--

CollagerController\
    - This interface represents a Collager controller, containing 3 different run methods.\
    - There is a run method to run the controller normally using input from System.in.\
    - a run method to run the controller with the GUI.\
    - a run method to run the controller with a provided path to a .txt file
containing a script.

CollagerControllerImpl\
    - This implements the three methods to run the Controller in different ways,
normal, GUI mode, and script mode.\
    - This also contains a method appendMessage, which helps to output helpful feedback in response
to the user actions.\
    - This now contains an actionPerformed method to link the controller and view, as the view
passes the controller an action, and this method parses that action.

### --Model--
ViewPPM\
    - This is a provided utility class that is used for reading PPM images.

PixelColor\
    - This class represents the data of a single pixel of images.\
    - It holds the RGB values, and brightness components.\
    - It allows for changes according to filter effects.

ICollager\
    - This interface represents a Collager project, containing various methods that can be used.\
    - The methods are used to interact with the current project.

AbstractCollager\
    - This is an abstract class for a Collager project, which can be used with any image type.\

Collager\
    - This class represents a Collager project, which starts with a transparent background.

CollagerPPM\
    - This class represents a Collager project for PPM images, with maximum value set to 255.

Layer\
    - This class represents a layer in the Collager project.\
    - A Layer has a name, height, and width.\
    - Filters can be applied to individual layers.

CollagerUtil\
    - Contains utility methods for reading collager files, or ppm files, and getting the height and
width of given ppm files.

FilterImpl\
    - Separated the filters out of the layer class to make it easier to add new filters going
forward and improve design.\
    - Contains method applyFilter, which applies a filter to a pixel.\
    - Contains method applyBlendFilter, which takes in the filter type to apply, and a composite
pixel of all the pixels below the current one.

IFilter\
    - An interface to represent filters you can apply to a pixel.\
    - Two methods, one which applies a normal filter to a pixel.\
    - One which applies a blending filter to a pixel.

ILayer\
    - An interface to represent a layer in the collager program
    - Written to provide an interface for the Layer class, to allow different representations of a 
Layer.

ImageFiles\
    - A class to represent and accept different types of image extensions, including ppm, jpeg, jpg,
and png images.\
    - Contains a method to write an image to a file, aiding in saving the final image.\
    - Contains a method, toPixelImage, which takes the image and converts it to a 2d array of pixels
to differently represent the image.

### --View--
CollagerView\
    - Represents an interactive GUI for the collager program.

CollagerViewImpl\
    - Creates an interactive GUI, using JSwing

The image "purple50x50.ppm" was created by me and I authorize its use in this project.

Contains code in the ViewPPM, and RepresentationConverter classes written by Professor Lucia Nunez, who authorized their use in this project.

### --View Decoupling--
There are no additional files required to make the view compile.

### --A6 Notes:--
Added a new class to the model to handle conversions and different type of image 
extensions.\
Also updated the controller to be able to use these different types of images:\
    - saveImage and addImage both can accept multiple different image file types