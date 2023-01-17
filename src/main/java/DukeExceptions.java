public class DukeExceptions extends Exception {
    String printThisOut;

    DukeExceptions(String commandType) {
        this.printThisOut = commandType;
    }

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
        return toReturn;
    }
}
