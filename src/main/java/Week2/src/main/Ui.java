package Week2.src.main;

import javafx.scene.image.Image;

/**
 * Deals with interactions with the user
 */
public class Ui {
    private Image bot = new Image(this.getClass().getResourceAsStream("/images/DaDuke.jpg"));

    /**
     * Ui Constructor
     */
    public Ui(){

    }

    /**
     * It prints Loadding error message to user
     */
    public String showLoadingError() {
        return "Loading...";
    }

    /**
     * First interaction with user
     */
    public String hello() {
        return "Hello-meow! I'm Bada.\nHyunjin's adorable but aggressive cat.\nWhat can I do for you?";
    }

    /**
     * Prints a bye message to user
     * @return
     */
    public String bye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints a error message when the task is empty
     * @return
     */
    public String showEmptyError() {
        return "OOPS!!! The description of a task cannot be empty.";
    }

    /**
     * Prints a error message when the file doesn't exist
     * @return
     */
    public String showFileError(){
        return "File doesn't exist!";
    }
}
