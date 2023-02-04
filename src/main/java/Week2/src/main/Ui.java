package Week2.src.main;

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
        return "Hello! I'm Bada\nWhat can I do for you?";
    }

    public String bye() {
        return "Bye. Hope to see you again soon!";
    }

    public String showEmptyError() {
        return "OOPS!!! The description of a todo cannot be empty.";
    }

    public String showFileError(){
        return "File doesn't exist!";
    }
}
