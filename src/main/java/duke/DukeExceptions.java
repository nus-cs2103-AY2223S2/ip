package duke;

/**
 * Custom class of exception for Duke chatbot.
 */
public class DukeExceptions extends Exception {
    protected String printThisOut;

    /**
     * Constructor to initialize an instance of DukeException
     *
     * @param commandType String which indicates type of DukeException
     */
    public DukeExceptions(String commandType) {
        this.printThisOut = commandType;
    }

    /**
     * Function to return String representation based on stored String.
     *
     * @return String representation, which changes depending on the stored String for the object.
     */
    @Override
    public String toString() {
        String toReturn = ":( Sorry, this is not a supported command!";
        if (printThisOut.equals("Wrong size for mark/unmark")) {
            toReturn = ":( Sorry, the number input is wrong. Please check the possible indexes again using list!";
        }
        if (printThisOut.equals("deadline") || printThisOut.equals("todo")) {
            toReturn = ":( Sorry, the description of a " + this.printThisOut + " cannot be empty!";
        }
        if (printThisOut.equals("event")) {
            toReturn = ":( Sorry, the description of an " + this.printThisOut + " cannot be empty!";
        }
        if (printThisOut.equals("find")) {
            toReturn = ":( Sorry, please input a keyword!";
        }
        return toReturn;
    }
}
