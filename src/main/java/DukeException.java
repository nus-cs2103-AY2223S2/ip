import java.lang.Exception;
public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super("\n____________________________________________________________\n" + errorMessage + "\n____________________________________________________________\n");
    }
}

