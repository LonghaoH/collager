# collager
This program functions like a photo-editing software, allowing the user to create and modify images 
with different functionalities.

Supported image commands:
new-project canvas-height canvas-width
    - creates a new project with the given name and given dimensions.
    - every project has a white background by default.

load-project path-to-project-file
    - loads a project into the program.

save-project
    - save the project as one file.

add-layer layer-name
    - adds a new layer with the given name on top of the whole project.

add-image-to-layer layer-name image-name x-pos y-pos
    - adds a given image to the given layer at the specified position.

set-filter layer-name filter-option
    - sets the filter of the given layer with the given filter.

save-image file-name
    - save the result of applying all filters on the image.

quit or q
    - quits the project and loses all unsaved work.

--Controller--
CollagerController 
    - This interface represents a Collager controller, containing a run method.
    - This method runs the program and allows for script-based commands.

CollagerControllerImpl
    - This class represents an implementation of the Collager controller.
    - It accepts user inputs as commands to run the program and perform various tasks.

--Model--
ImageUtil
    - This is a provided utility class that is used for reading PPM images.

PixelColor
    - This class represents the data of a single pixel of images.
    - It holds the RGB values, and brightness components.
    - It allows for changes according to filter effects.

ICollager
    - This interface represents a Collager project, containing various methods that can be used.
    - The methods are used to interact with the current project.

AbstractCollager
    - This is an abstract class for a Collager project, which can be used with any image type.

Collager
    - This class represents a Collager project, which starts with a transparent background.

CollagerPPM
    - This class represents a Collager project for PPM images, with maximum value set to 255.

Layer
    - This class represents a layer in the Collager project.
    - A Layer has a name, height, and width.
    - Filters can be applied to individual layers.