import java.lang.Exception;

public class DukeStoreFullException extends Exception{
    public DukeStoreFullException() {
        super("SOCCat is full and has run out of capacity");
    }
}
