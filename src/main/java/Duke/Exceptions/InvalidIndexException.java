package Duke.Exceptions;

public class InvalidIndexException extends DukeMainExceptions {
    private static final String ERROR_MESSAGE = "Sorry, the index is out of bounds!";
    private int maxIndex;

    public InvalidIndexException() {
        super(ERROR_MESSAGE);
    }

    public InvalidIndexException(int maxIndex) {
        super(ERROR_MESSAGE);
        this.maxIndex = maxIndex;
    }

    @Override
    public String toString() {
        String result = ERROR_MESSAGE;
        if (this.maxIndex != 0) {
            result += "\nMaximum value: " + this.maxIndex;
        }
        return result;
    }


}
