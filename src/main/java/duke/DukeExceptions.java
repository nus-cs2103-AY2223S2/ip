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
        String toReturn = "This isn't even a valid command you dumb orc!";
        if (printThisOut.equals("Wrong size for mark/unmark")) {
            toReturn = "Dumb orc, the number input is wrong. You'd better check"
                    + " the possible indexes again using list!";
        }
        if (printThisOut.equals("deadline") || printThisOut.equals("todo")) {
            toReturn = "Dumb orc, the description of a " + this.printThisOut + " cannot be empty!";
        }
        if (printThisOut.equals("event")) {
            toReturn = "Dumb orc, the description of an " + this.printThisOut + " cannot be empty!";
        }
        if (printThisOut.equals("find")) {
            toReturn = "Dumb orc, you didn't input a keyword!";
        }
        if (printThisOut.equals("bad tag")) {
            toReturn = "Oi dumb orc, can't you follow the tag format properly? "
                    + "Try again with this format: tag index-of-task #tagname";
        }
        return toReturn;
    }
}
