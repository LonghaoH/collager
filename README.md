# collager
This program functions like a photo-editing software, allowing the user to create and modify images 
with different functionalities, such as filters. Currently available features are adding multiple layers to the program,
with multiple images per layer, and each layer can have a filter on it. It supports image of file types JPG, PNG, PPM,
and JPEG. These filter types are Brighten, Darken, Color Components, which filters the layer to just have the one color.
You can create multiple projects, and save and load these projects to work on them again later.
You can also save the final composite image in all the different file formats listed above.

System Requirements to compile the code:
- Java 11 or higher
- JUnit 4 for testing

How to use:
- Please refer to the USEME.md file for use instructions

## Code Design:

### --Controller--

CollagerController\
    - This interface represents a controller for the Collager, connecting both the model and the view.\
    - There is a run method to run the controller normally using input from System.in.\
    - a run method to run the controller with the GUI.\
    - a run method to run the controller with a provided path to a .txt file
containing a script.

CollagerControllerImpl\
    - This implements CollagerController interface and the three methods to run the Controller in different ways,
normal, GUI mode, and script mode.\
    - This also contains a method appendMessage, which helps to output helpful feedback in response
to the user actions.\
    - This now contains an actionPerformed method to link the controller and view, as the view
passes the controller an action, and this method parses that action.

### --Model--
Represents a model for the Collager program, where the back-end software is.\

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
    - Creates an interactive, functional GUI connected by the Controller, using JSwing

All images in the program are created by me and I authorize their use in this project.

Contains code in the RepresentationConverter class written by Professor Lucia Nunez,
who authorized its use in this project.