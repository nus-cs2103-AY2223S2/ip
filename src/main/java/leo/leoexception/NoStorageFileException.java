package leo.leoexception;

/**
 * Represents an exception when no data file containing the list of Tasks is found.
 */
public class NoStorageFileException extends LeoException {

    public NoStorageFileException() {
        super("No file found!! >:-(");
    }
}
