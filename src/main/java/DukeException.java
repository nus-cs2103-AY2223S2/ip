import java.lang.Exception;

public class DukeException extends Exception{
    public DukeException(String message)
    {
        throw new IllegalArgumentException(message);
    }
}
