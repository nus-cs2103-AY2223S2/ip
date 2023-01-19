import java.lang.IllegalArgumentException;

public class DukeException extends IllegalArgumentException{
    public DukeException(String message)
    {
        throw new IllegalArgumentException(message);
    }
}
