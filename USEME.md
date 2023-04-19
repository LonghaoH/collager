## GUI Mode:
In the GUI, the main panel is where the images will be viewed, in the top left there are controls that allow you
to use the program. In the bottom, there is a layer panel which shows the current layers in the project.
### File:
    - New project
    - Load
    - Save Image
    - Save Project
Modifies the project, either allowing to make a new project, load a project, save the image, or
save the project.

### Edit
    - Add image
    - Add layer
    - Color components
    - Brighten
    - Darken
Allows the user to edit the project, either by adding an image, adding a new layer, or by
filtering by color components, brightening, or darkening the layer.

## Text Mode:
In text mode, the collager runs off of text-based commands given by the user. These commands can be given by System.in,
or in a supplied .txt script file.

### Supported image commands:

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