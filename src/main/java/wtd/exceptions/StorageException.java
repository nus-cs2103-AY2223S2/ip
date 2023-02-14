package wtd.exceptions;

public class StorageException extends WtdException {
    public StorageException() {
        super("Your data cannot be accessed.\n");
    }
}
