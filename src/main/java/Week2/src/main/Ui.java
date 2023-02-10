package Week2.src.main;
import java.util.logging.Logger;
/**
 * Deals with interactions with the user
 */
public class Ui {

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
        String msg = "Hello! I'm Bada\nWhat can I do for you?";
        return msg;
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
        return "OOPS!!! The description of a todo cannot be empty.";
    }

    /**
     * Prints a error message when the file doesn't exist
     * @return
     */
    public String showFileError(){
        return "File doesn't exist!";
    }
}
