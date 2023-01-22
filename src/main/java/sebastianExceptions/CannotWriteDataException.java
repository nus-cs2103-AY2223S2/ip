package sebastianExceptions;

public class CannotWriteDataException extends SebastianException{
    public CannotWriteDataException() {
        super("Task cannot be saved to disk, exiting the program will cause data to be lost");
    }
}
