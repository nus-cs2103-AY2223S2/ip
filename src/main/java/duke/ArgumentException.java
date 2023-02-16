package duke;

public class ArgumentException extends Exception{

    /**
     * this class will be created whenever the user types in an invalid command or when the file cannot be found
     * @param msg indicates the issue with the user input
     */
    public ArgumentException(String msg) {
        super(msg);
    }
}
