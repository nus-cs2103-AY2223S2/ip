package Duke.Exceptions;

import Duke.Tasks.Task;

public class InvalidIndexException extends DukeMainExceptions {
    private static final String ERRORMSG = "Sorry, the index is out of bounds!";
    private int maxIndex;

    public InvalidIndexException() {
        super(ERRORMSG);
    }

    public InvalidIndexException(int maxIndex) {
        super(ERRORMSG);
        this.maxIndex = maxIndex;
    }

    @Override
    public String toString() {
        String result = ERRORMSG;
        if (this.maxIndex != 0) {
            result += "\nMaximum value: " + this.maxIndex;
        }
        return result;
    }


}
